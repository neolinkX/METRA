/*
 * SampleProcesor.java
 *
 * Created on March 9, 2006, 1:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gob.sct.medprev;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.lang.Long;
import java.util.Calendar;
import com.micper.util.TFechas;

/**
 * 
 * @author Administrator
 */
public class SampleProcesor {

	private ArrayList samples;

	/** Creates a new instance of SampleProcesor */
	public SampleProcesor() {
		setSamples(new ArrayList());
	}

	public void readZipFile(InputStream in) {
		char[] buffer = new char[1024 * 1024 * 5];
		Long lg;
		try {
			ZipInputStream zin = new ZipInputStream(new BufferedInputStream(in));
			InputStreamReader isr = new InputStreamReader(zin);
			ZipEntry entry;
			String contenido = null;
			while ((entry = zin.getNextEntry()) != null) {
				int i;
				contenido = new String();
				buffer = bufferCleaup(buffer);
				while ((i = isr.read(buffer, 0, buffer.length)) != -1) {
					contenido += new String(buffer);
				}
				// Agregar a las muestras solo las entradas que corresponden al
				// tipo de archivo
				if (SampleTokens.EXTENSION.equalsIgnoreCase(entry.getName()
						.substring(
								entry.getName().length()
										- SampleTokens.EXTENSION.length()))) {
					SampleData sd = new SampleData(entry.getName(),
							contenido.trim());
					getSamples().add(sd);
				}
			}
			isr.close();
			zin.close();
			in.close();
		} catch (Exception ex) {
			// System.out.println("Con excepcion en readZipFile: "+
			// ex.getMessage());
			ex.printStackTrace();
		}
	}

	private char[] bufferCleaup(char[] entrada) {
		for (int i = 0; i < entrada.length; i++)
			entrada[i] = 0;
		return entrada;
	}

	public void processSamples() {
		// para todos los samples, seguir llenando datos
		for (int i = 0; i < getSamples().size(); i++) {
			boolean lValidacion = true;
			int inicioBusqueda = 0;
			int finBusqueda = 0;
			SampleData tmp = (SampleData) getSamples().get(i);
			// Validar que la información tomada del archivo sea la correcta
			if (tmp.getOriginalContent().length() > 0) {
				// ****************** elementos unicos por muestra
				for (int j = 0; j < SampleTokens.UNIQUE_TOKENS.length; j++) {
					inicioBusqueda = tmp.getOriginalContent().indexOf(
							SampleTokens.UNIQUE_TOKENS[j], inicioBusqueda);
					finBusqueda = tmp.getOriginalContent().indexOf("\n",
							inicioBusqueda);
					String linea = tmp.getOriginalContent().substring(
							inicioBusqueda, finBusqueda);
					switch (j) {
					case 0:
						tmp.setLC_iCveAnalisis(linea.substring(linea
								.indexOf(": ") + 1));
						// System.out.println("iCveAnalisis = " +
						// tmp.getLC_iCveAnalisis().toString());
						break;
					case 1:
						int fecPos = 0;
						String fecha = linea.substring(linea.indexOf(": ") + 1,
								linea.indexOf("using")).trim();
						fecPos = fecha.indexOf(" ");
						int dia = Integer.parseInt(fecha.substring(0, fecPos));
						fecha = fecha.substring(fecPos).trim();
						fecPos = fecha.indexOf(" ");
						String mestemp = fecha.substring(0, fecPos);
						int mes = 0;
						if (mestemp.trim().equalsIgnoreCase("jan"))
							mes = Calendar.JANUARY;
						else if (mestemp.trim().equalsIgnoreCase("feb"))
							mes = Calendar.FEBRUARY;
						else if (mestemp.trim().equalsIgnoreCase("mar"))
							mes = Calendar.MARCH;
						else if (mestemp.trim().equalsIgnoreCase("apr"))
							mes = Calendar.APRIL;
						else if (mestemp.trim().equalsIgnoreCase("may"))
							mes = Calendar.MAY;
						else if (mestemp.trim().equalsIgnoreCase("jun"))
							mes = Calendar.JUNE;
						else if (mestemp.trim().equalsIgnoreCase("jul"))
							mes = Calendar.JULY;
						else if (mestemp.trim().equalsIgnoreCase("aug"))
							mes = Calendar.AUGUST;
						else if (mestemp.trim().equalsIgnoreCase("sep"))
							mes = Calendar.SEPTEMBER;
						else if (mestemp.trim().equalsIgnoreCase("oct"))
							mes = Calendar.OCTOBER;
						else if (mestemp.trim().equalsIgnoreCase("nov"))
							mes = Calendar.NOVEMBER;
						else if (mestemp.trim().equalsIgnoreCase("dec"))
							mes = Calendar.DECEMBER;
						fecha = fecha.substring(fecPos).trim();
						fecPos = fecha.indexOf(" ");
						int anio = Integer.parseInt(fecha.substring(0, fecPos));
						fecha = fecha.substring(fecPos).trim();
						fecPos = fecha.indexOf(":");
						int hrs = Integer.parseInt(fecha.substring(0, fecPos));
						fecha = fecha.substring(fecPos + 1).trim();
						int mins = Integer.parseInt(fecha);
						Calendar m = Calendar.getInstance();
						m.set(anio, mes, dia, hrs, mins);
						TFechas Fecha = new TFechas();
						tmp.setLC_dtAnalisisD(Fecha.getDateSQL(
								Integer.valueOf(String.valueOf(dia)),
								Integer.valueOf(String.valueOf(mes + 1)),
								Integer.valueOf(String.valueOf(anio))));
						tmp.setLC_dtAnalisis(m);
						// System.out.println("dtAnalisis = " +
						// tmp.getLC_dtAnalisisD());
						break;
					case 2:
						tmp.setLC_SampleName(linea.substring(linea
								.indexOf(": ") + 1));
						// System.out.println("SampleName = " +
						// tmp.getLC_SampleName().toString());
						break;
					case 3:
						tmp.setComponentes(linea.substring(linea.indexOf(": ") + 1));
						// System.out.println("Componentes = " +
						// tmp.getComponentes().toString());
						break;
					}
				} // Fin del For
					// ****************** elementos repetidos por muestra
				while (inicioBusqueda > 0
						&& tmp.getOriginalContent().indexOf(
								SampleTokens.TOKENS[0], inicioBusqueda) != -1) {

					for (int j = 0; j < SampleTokens.TOKENS.length; j++) {
						inicioBusqueda = tmp.getOriginalContent().indexOf(
								SampleTokens.TOKENS[j], inicioBusqueda);
						finBusqueda = tmp.getOriginalContent().indexOf("\n",
								inicioBusqueda);
						String linea = "";
						if (inicioBusqueda >= 0)
							linea = tmp.getOriginalContent().substring(
									inicioBusqueda, finBusqueda);
						else {
							j = SampleTokens.TOKENS.length;
							lValidacion = false;
						}

						int pos;
						Float m;
						// procesar linea para tener el dato
						// System.out.println("Información a procesar =" +
						// linea);

						String Valor = null;
						switch (j) {
						case 0:
							String cValor = linea.substring(
									linea.indexOf(": ") + 1).trim();
							if (cValor.length() > 0)
								m = new Float(cValor);
							else
								m = new Float("0.0");
							tmp.getCA_dTmpRetencD().add(m);
							// System.out.println("tm retencion j = " + j +
							// "- "+ tmp.getCA_dTmpRetencD());
							break;
						case 1:
							pos = linea.indexOf("|", linea.indexOf("|") + 1) + 1;
							m = new Float(linea.substring(pos, pos + 7));
							tmp.getCA_dIon05().add(m);
							break;
						case 2:
							tmp.getCA_Componente().add(
									linea.substring(linea.indexOf(": ") + 1)
											.trim());
							// System.out.println("Componente" +
							// tmp.getCA_Componente());
							break;
						case 3:
							Valor = linea.substring(linea.indexOf(": ") + 1);
							if (Valor != null && Valor.trim().length() > 0)
								m = new Float(linea.substring(linea
										.indexOf(": ") + 1));
							else
								m = new Float("0");
							tmp.getCA_dTmpRetenc().add(m);
							break;
						case 4:
							Valor = linea.substring(linea.indexOf(": ") + 1);
							if (Valor != null && Valor.trim().length() > 0)
								m = new Float(linea.substring(
										linea.indexOf(": ") + 1,
										linea.indexOf("NG")));
							else
								m = new Float("0");
							tmp.getCA_dResultadoDil().add(m);
							break;
						case 5:
							pos = linea.indexOf("|", linea.indexOf("|") + 1) + 1;
							m = new Float(linea.substring(pos, pos + 7));
							tmp.getCA_dIon02().add(m);
							break;
						case 6:
							pos = linea.indexOf("|", linea.indexOf("|") + 1) + 1;
							m = new Float(linea.substring(pos, pos + 7));
							tmp.getCA_dIon03().add(m);
							break;
						}
					}
				} // Fin del while
			} // Validación del tamaño del archivo
		} // Para cada muestra
	}

	public static void main(String args[]) {
		SampleProcesor sp = new SampleProcesor();
		InputStream in = null;
		try {
			in = new FileInputStream("c:\\a.zip");
		} catch (Exception ex) {
		}
		sp.readZipFile(in);
		sp.processSamples();
		ArrayList mismuestras = sp.getSamples();
		SampleData alfa = (SampleData) mismuestras.get(0);
		// System.out.println(" ...."+ alfa.getLC_iCveAnalisis());
		// System.out.println(" fecha "+
		// alfa.getLC_dtAnalisis().getTimeInMillis());
		// System.out.println(" Tiempo de Retención " +
		// alfa.getCA_dTmpRetencD().get(0));
		alfa = (SampleData) mismuestras.get(1);
		// System.out.println(" ...."+ alfa.getLC_iCveAnalisis());
		// System.out.println(" fecha "+
		// alfa.getLC_dtAnalisis().getTimeInMillis());
		// System.out.println(" Tiempo de Retención " +
		// alfa.getCA_dTmpRetencD().get(0));
		alfa = (SampleData) mismuestras.get(2);
		// System.out.println(" ...."+ alfa.getLC_iCveAnalisis());
		// System.out.println(" fecha "+
		// alfa.getLC_dtAnalisis().getTimeInMillis());
		// System.out.println(" Tiempo de Retención " +
		// alfa.getCA_dTmpRetencD().get(0));
	}

	public ArrayList getSamples() {
		return samples;
	}

	public void setSamples(ArrayList samples) {
		this.samples = samples;
	}
}
