package gob.sct.medprev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.micper.excepciones.DAOException;

import gob.sct.medprev.dao.TDELIMINAExpAuto;

/**
 * @author AG SA L 2016-06-10
 */
public class ControlEliminaExp {

	// fichero TMP
	private String appPath = System.getProperties().getProperty("user.dir");
	private File fichero = new File(appPath + "\\miApp.tmp");
	// tiempo en que se actualiza el fichero TMP
	private int segundos = 180;// 900

	/**
	 * Constructor de clase
	 * 
	 * @return
	 */
	public void Control() {
	};

	/**
	 * Comprueba que archivo TMP exista, sino lo crea e inicia valores
	 */
	public boolean comprobar() {
		// //System.out.println(appPath);
		if (fichero.exists()) {
			// System.out.println("El fichero existe");
			long tiempo = leer();//
			long res = restarTiempo(tiempo);
			// System.out.println("tiempo" + res);
			if (res < segundos) {
				// JOptionPane.showMessageDialog(null,"Error: La aplicacion ya
				// esta en ejecución.");
				// System.out.println("no se hace nada");
				return false;
			} else {
				// System.out.println("realiza la tarea");
				programar_tarea();
				return true;
			}
		} else// no existe fichero
		{
			crearTMP();
			programar_tarea();
			return true;
		}
	}

	/**
	 * Lee el archivo TMP y retorna su valor
	 * 
	 * @return LONG cantidad de milisegundos
	 */
	public long leer() {
		String linea = "0";
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(fichero));
			while (bufferedReader.ready()) {
				linea = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return Long.valueOf(linea).longValue();
	}

	/**
	 * Programa un proceso que se repite cada cierto tiempo
	 */
	public void programar_tarea() {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				Date fecha = new Date();
				// System.out.println("programar_tarea = H " + fecha.getHours()
				// + " M " + fecha.getMinutes() + " Segundos = " +
				// fecha.getSeconds()+ " Time"+ fecha.getTime());
				if (fecha.getHours() > 6 && fecha.getHours() < 19) { 
					// System.out.println("Condicion > 8 && < 19");
					crearTMP();
					// elimna expedientes
					try {
						eliminaExpedientes();
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Eliminacion de expedientes caso especial
					if (fecha.getHours() == 7 && (fecha.getMinutes() > 0 || fecha.getMinutes() < 30)) {
						try {
							eliminaExpedientesCasoEspecial();
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					if (fecha.getHours() == 19 && fecha.getMinutes() < 56) {
						// System.out.println("Condicion == 19 && < 56");
						crearTMP();
						// elimna expedientes
						try {
							eliminaExpedientes();
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}, 1000, segundos * 1000, TimeUnit.MILLISECONDS); // comienza dentro de
															// 1 segundo y luego
															// se repite cada N
															// segundos

	}

	/**
	 * Programa un proceso que se repite cada cierto tiempo
	 */
	public void programar_tarea2() {
		crearTMP();
		// elimna expedientes
		try {
			eliminaExpedientes();
			// cerrarApp();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Crea un archivo TMP con un unico valor, el tiempo en milisegundos
	 */
	// @SuppressWarnings("deprecation")
	public void crearTMP() {
		Date fecha = new Date();
		// System.out.println("Ejecutando crearTMP = H " + fecha.getHours() + "
		// M " + fecha.getMinutes() + " Time" + fecha.getTime());
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));
			writer.write(String.valueOf(fecha.getTime()));
			writer.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Ejecuta las rutinas para eliminar expedientes
	 * 
	 * @throws DAOException
	 */
	public void eliminaExpedientes() throws DAOException, IOException {
		// Date fecha = new Date();
		// System.out.println("Ejecutando eliminaExpedientes = " +
		// fecha.getTime());
		TDELIMINAExpAuto dELIMINAExpAuto = new TDELIMINAExpAuto();
		// System.out.println("Ejecutando DeleteExpConExam = ");
		dELIMINAExpAuto.DeleteExpConExam();
		// System.out.println("Ejecutando DeleteExpConExam fin");
		// System.out.println("Ejecutando DeleteExpSinExam = ");
		dELIMINAExpAuto.DeleteExpSinExam();
		// System.out.println("Ejecutando DeleteExpSinExam fin ");
	}

	/**
	 * Ejecuta las rutinas para eliminar expedientes de 3 dias anteriores a la fecha
	 * 
	 * @throws DAOException
	 */
	public void eliminaExpedientesCasoEspecial() throws DAOException, IOException {
		// Date fecha = new Date();
		// System.out.println("Ejecutando eliminaExpedientes = " +
		// fecha.getTime());
		TDELIMINAExpAuto dELIMINAExpAuto = new TDELIMINAExpAuto();
		// System.out.println("Ejecutando DeleteExpConExam = ");
		dELIMINAExpAuto.DeleteExpConExamCasoEspecial();
		// System.out.println("Ejecutando DeleteExpConExam fin");
		// System.out.println("Ejecutando DeleteExpSinExam = ");
		dELIMINAExpAuto.DeleteExpSinExamCasoEspacial();
		// System.out.println("Ejecutando DeleteExpSinExam fin ");
	}

	
	/**
	 * Resta el tiempo expresado en milisegundos
	 * 
	 * @param tiempoActual
	 *            el tiempo actual del sistema expresado en milisegundos
	 * @return tiempo el resultado expresado en segundos
	 */
	public long restarTiempo(long tiempoActual) {
		Date date = new Date();
		long tiempoTMP = date.getTime();
		long tiempo = tiempoTMP - tiempoActual;
		tiempo = tiempo / 1000;
		return tiempo;
	}

	/**
	 * Elimina el fichero TMP si es que existe
	 */
	public void cerrarApp() {
		/*
		 * if ( fichero.exists() ) { fichero.delete(); }
		 */
		System.exit(0);
	}

}// --> fin clase