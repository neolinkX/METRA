/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dwr;

import gob.sct.medprev.dao.ConsultaGral;
import gob.sct.medprev.dao.SEGAccPwd;
import gob.sct.medprev.dao.TDEXPBITMOD;
import gob.sct.medprev.dao.TDGRLUSUMedicos;
import gob.sct.medprev.dao.TDMEDInhabilita;
import gob.sct.medprev.dao.TDSEGUsuario;
import gob.sct.medprev.dwr.framework.GenericResponse;
import gob.sct.medprev.dwr.vo.BitacoraRetornoVo;
import gob.sct.medprev.dwr.vo.ClaseDatosInicio;
import gob.sct.medprev.dwr.vo.DatosAvisoVo;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.dwr.vo.GrlTipoOperaBit;
import gob.sct.medprev.dwr.vo.PermisosUsuMedVo;
import gob.sct.medprev.vo.TVMEDInhabilita;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.seguridad.vo.TVUsuario;

/**
 * 
 * @author admin
 */
public class MedPredDwr implements Serializable {
	/**
     * 
     */
	private static long serialVersionUID = 1L;
	SEGAccPwd dSEGAccPwd = new SEGAccPwd();
	TDEXPBITMOD tdExpBitMod = new TDEXPBITMOD();

	public int insertAccess(int id, String ip, String Navegador,
			String versionNav, String macAddress, String computerName, 
			String CIpRealAcceso, String CPais, String CCiudad, String CRegion,
			double Latitud, double Longitud, String ZonaHoraria, String ProvSI, String Org,
			String Modelo,String NumSerie) {
		int insertAcces = 0;
		try {
			//System.out.println("Insertar");
			insertAcces = dSEGAccPwd.insertAcces(null, id, ip, Navegador,
					versionNav, macAddress, computerName,
					CIpRealAcceso, CPais, CCiudad, CRegion,
					Latitud, Longitud, ZonaHoraria, ProvSI, Org, Modelo, NumSerie);
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return insertAcces;
	}

	public int insertAccessFallidoBiometrico(int id, String ip,
			String Navegador, String versionNav, String macAddress,
			String computerName) {
		int insertAcces = 0;
		try {
			insertAcces = dSEGAccPwd.insertAccesoFallidoBiometrico(null, id,
					ip, Navegador, versionNav, macAddress, computerName);
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return insertAcces;
	}

	public int insertValidacionBiometrica(int id, String ip, String Navegador,
			String versionNav, String macAddress, String computerName) {
		int insertAcces = 0;
		try {
			insertAcces = dSEGAccPwd.insertValidacionBiometrica(null, id, ip,
					Navegador, versionNav, macAddress, computerName);
		} catch (DAOException ex) {
			ex.printStackTrace();
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return insertAcces;
	}

	public int insertValidacionFallidaBiometrico(int id, String ip,
			String Navegador, String versionNav, String macAddress,
			String computerName) {
		int insertAcces = 0;
		try {
			insertAcces = dSEGAccPwd.insertAccesoFallidoBiometrico(null, id,
					ip, Navegador, versionNav, macAddress, computerName);
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return insertAcces;
	}

	public int insertValidacionBiometricaExpediente(int icveExpediente) {
		int insertAcces = 0;
		try {
			insertAcces = dSEGAccPwd
					.insertValidacionBiometricaExpediente(icveExpediente);
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return insertAcces;
	}

	public int insertValidacionFallidaBiometricoExpediente(int icveExpediente) {
		int insertAcces = 0;
		try {
			insertAcces = dSEGAccPwd
					.insertAccesoFallidoBiometricoExp(icveExpediente);
		} catch (DAOException ex) {
			ex.printStackTrace();
		}
		return insertAcces;
	}

	/**
	 * Metodo para catalogo de tabla GRLTIPOOPERABIT
	 * 
	 * @return GenericResponse
	 */
	public GenericResponse findAllOperaBit() {
		GenericResponse response = new GenericResponse();
		List<GrlTipoOperaBit> lista = new ArrayList<GrlTipoOperaBit>();
		try {
			lista = tdExpBitMod.findAllTipoOperaBit();
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		if (lista.isEmpty()) {
			response.setSuccess(false);
		} else {
			response.setData(lista);
			response.setSuccess(true);
		}
		return response;
	}

	public GenericResponse findMovimientosBitacora(BitacoraRetornoVo retorno) {
		GenericResponse response = new GenericResponse();
		List<ExpBitMod> lista = new ArrayList<ExpBitMod>();
		try {
			if (!retorno.getFechaFin().equals("")) {
				// System.out.println(" que fecha traigo --- " +
				// retorno.getFechaFin());
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(
						"yyyy-MM-dd");
				Date fecha = null;
				Timestamp date = null;
				try {
					fecha = formatoDelTexto.parse(retorno.getFechaFin());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				Calendar calCalendario = Calendar.getInstance();
				calCalendario.setTimeInMillis(fecha.getTime());
				calCalendario.add(Calendar.DATE, 1);
				date = new Timestamp(calCalendario.getTimeInMillis());
				retorno.setFechaFin(formatoDelTexto.format(date));
			}
			if (retorno.getOperacion().equals("0")) {
				retorno.setOperacion("");
			}
			lista = tdExpBitMod.findExpBitMod(retorno.getPersona(),
					retorno.getUsuario(), retorno.getOperacion(),
					retorno.getFechaInicio(), retorno.getFechaFin());
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		if (lista.isEmpty()) {
			response.setSuccess(false);
		} else {
			response.setData(lista);
			response.setSuccess(true);
		}
		return response;
	}

	public int validaCLUES(String USUARIO, String CLUES) {
		int response = 0;
		try {
			response = tdExpBitMod.findCLUES(USUARIO, CLUES);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public int validaAccesosIncorrectosBitacora(String icveUsuario) {
		int response = 3;
		try {
			response = tdExpBitMod
					.findAccesosIncorrectosConcecutivos(icveUsuario);
			if (response >= 3) {
				TVUsuario vUsuario = new TVUsuario();
				vUsuario.setICveusuario(Integer.parseInt(icveUsuario));
				// vUsuario.bloqueaUsuario();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public int evaluaSiExamenEstaDictaminado(String iCveExp, String iNumExm) {
		int response = 0;
		try {
			response = tdExpBitMod.findExamenDictaminado(iCveExp, iNumExm);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public int ejecutaValidacionBiometrica(String icveUsuario) {
		int response = 0;
		try {
			response = tdExpBitMod
					.findValidacionBiometricaUsuarioXModulo(icveUsuario);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public int ejecutaValidacionBiometrica3(String icveUsuario,
			String ICVEEXPEDIENTE, String INUMEXAMEN) {
		int response = 0;
		try {
			response = tdExpBitMod.findValidacionBiometricaUsuarioXModulo3(
					icveUsuario, ICVEEXPEDIENTE, INUMEXAMEN);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public int ejecutaValidacionBiometrica5(String ICVEUNIMED, String ICVEMODULO) {
		/*System.out.println("ICVEUNIMED=" + ICVEUNIMED + "/ICVEMODULO="
				+ ICVEMODULO);*/
		int response = 0;
		try {
			response = tdExpBitMod.findValidacionBiometricaUsuarioXModulo5(
					ICVEUNIMED, ICVEMODULO);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public int validaAccesosIncorrectosBitacoraExpedientes(String icveExpediente) {
		int response = 3;
		try {
			response = tdExpBitMod
					.findAccesosIncorrectosConcecutivosExp(icveExpediente);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

	public int validaAccesosIncorrectosBitacoraExp(String icveExpediente,
			String icveUsuario) {
		int response = 3;
		try {
			response = tdExpBitMod
					.findAccesosIncorrectosConcecutivosExp(icveExpediente);

			TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
			TVMEDInhabilita MEDInhabilita = new TVMEDInhabilita();
			MEDInhabilita.setICvePersonal(Integer.parseInt(icveExpediente));
			MEDInhabilita.setICveMotivo(48);
			MEDInhabilita.setiCveUsuInh(Integer.parseInt(icveUsuario));
			MEDInhabilita
					.setCObservacion("Deshabilitado automaticamente por exceso de validaciones dactilares erroneas");

			java.util.Date today = new java.util.Date();
			java.sql.Date sqlToday = new java.sql.Date(today.getTime());
			Calendar cal = Calendar.getInstance();
			cal.setTime(sqlToday);
			cal.add(Calendar.DAY_OF_YEAR, 365);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			java.sql.Date sqlOneYear = new java.sql.Date(cal.getTimeInMillis());

			MEDInhabilita.setInicioInh(sqlToday);
			MEDInhabilita.setFinInh(sqlOneYear);

			if (response == 3) {
				dMEDInhabilita.insert(null, MEDInhabilita);
				// System.out.println("inserto idao "+response);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

	public ClaseDatosInicio getDatosInicio(int iCveUsuario) {
		System.out.println("Clave Usuario: "+ iCveUsuario);
		ClaseDatosInicio datoFinal = new ClaseDatosInicio();
		List<ClaseDatosInicio> lista = new ArrayList<ClaseDatosInicio>();
		try {
			lista = new TDSEGUsuario().findByUsuarioInicio(iCveUsuario);
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		/*
		 * if (lista.size() > 1) { lista.get(0).setDomLaboral("Administrador");
		 * }
		 */
		return lista.get(lista.size() - 1);
		//return lista.get(lista.size());
	}

	public DatosAvisoVo getDatosAviso() {
		DatosAvisoVo datos = new DatosAvisoVo();
		ConsultaGral consulta = new ConsultaGral();
		try {
			/*
			 * System.out.println("idPersona: " +
			 * request.getParameter("iCvePersona"));
			 */
			List busquedaArea = consulta
					.ejecutaSelectQueryAdmSeg("SELECT CVALOR FROM SEGPROPIEDAD WHERE ICVESISTEMA = 7 AND "
							+ " CPROPIEDAD IN ('AvisoConfigBody','AvisoConfigTit',"
							+ "'MostrarAvisoConfig','AvisoConfigTitLiga','AvisoConfigLiga')");
			if (busquedaArea.size() > 0) {
				Iterator it = busquedaArea.iterator();
				String[] x1 = (String[]) it.next();
				datos.setAvisoConfigBody(x1[0]);
				x1 = (String[]) it.next();
				datos.setAvisoConfigLiga(x1[0]);
				x1 = (String[]) it.next();
				datos.setAvisoConfigTit(x1[0]);
				x1 = (String[]) it.next();
				datos.setAvisoConfigTitLiga(x1[0]);
				x1 = (String[]) it.next();
				datos.setMostrarAvisoConfig(x1[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datos;
	}

	public DatosAvisoVo setDatosAviso(DatosAvisoVo avisoModificado) {
		DatosAvisoVo datos = new DatosAvisoVo();
		ConsultaGral consulta = new ConsultaGral();
		String q1 = "UPDATE SEGPROPIEDAD SET CVALOR = '"
				+ avisoModificado.getAvisoConfigBody()
				+ "' WHERE CPROPIEDAD = 'AvisoConfigBody'";
		String q2 = "UPDATE SEGPROPIEDAD SET CVALOR = '"
				+ avisoModificado.getAvisoConfigLiga()
				+ "' WHERE CPROPIEDAD = 'AvisoConfigLiga'";
		String q3 = "UPDATE SEGPROPIEDAD SET CVALOR = '"
				+ avisoModificado.getAvisoConfigTit()
				+ "' WHERE CPROPIEDAD = 'AvisoConfigTit'";
		String q4 = "UPDATE SEGPROPIEDAD SET CVALOR = '"
				+ avisoModificado.getAvisoConfigTitLiga()
				+ "' WHERE CPROPIEDAD = 'AvisoConfigTitLiga'";
		String q5 = "UPDATE SEGPROPIEDAD SET CVALOR = '"
				+ avisoModificado.getMostrarAvisoConfig()
				+ "' WHERE CPROPIEDAD = 'MostrarAvisoConfig'";

		int a = consulta.ejecutaInsert(q1);
		int b = consulta.ejecutaInsert(q2);
		int c = consulta.ejecutaInsert(q3);
		int d = consulta.ejecutaInsert(q4);
		int e = consulta.ejecutaInsert(q5);
		datos = this.getDatosAviso();
		return datos;
	}
/*
	public int getDatosUsuarioIE9(int id, String Navegador, String versionNav) {
		int insertAcces = 0;
		BufferedReader bf = null;
		String ip = "";
		String computerName = "";
		String macAddress = "";
		try {
			bf = new BufferedReader(new FileReader(
					"C:\\fingerprint\\temp\\FingerPrintInf.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ip = bf.readLine();
			computerName = bf.readLine();
			macAddress = bf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			insertAcces = dSEGAccPwd.insertAcces(null, id, ip, Navegador,
					versionNav, macAddress, computerName);
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return insertAcces;
	}
*/
	
	public GenericResponse findServiciosUsuario(int usuario, int unidadMedica,
			int proceso, int modulo) throws DAOException {
		TDGRLUSUMedicos tdUsuMed = new TDGRLUSUMedicos();
		GenericResponse response = new GenericResponse();
		List<PermisosUsuMedVo> lista = new ArrayList<PermisosUsuMedVo>();
		String wherePriv = " where icveusuario=" + usuario + " and icveunimed="
				+ unidadMedica + " and icveproceso=" + proceso
				+ " and icvemodulo=" + modulo;
		// System.out.println(wherePriv);
		lista = tdUsuMed.FindByAllSimplePermiso(wherePriv);
		if (lista.size() == 0) {
			response.setSuccess(false);
		}
		response.setData(lista);
		return response;
	}

	public void imageClient() {
		TParametro vParametros = new TParametro("07");
		String path = vParametros.getPropEspecifica("RutaDescargaImagen");// "/nas/img/medprev/";
		File directorio = new File(path);
		String[] ficheros = directorio.list();
		for (int i = 0; i < ficheros.length; i++) {
			/*
			 * BufferedReader br = new BufferedReader(new FileReader(path +
			 * ficheros[i]));
			 */
			if (ficheros[i].contains(".")) {
				// System.out.println(ficheros[i].substring(ficheros[i].length()-3,
				// ficheros[i].length()));
				// System.out.println("Contenido del fichero " + ficheros[i]);
				this.descargaImagenes(ficheros[i]);
			}

			/*
			 * while ((line = br.readLine()) != null) {
			 * System.out.println(line); }
			 */
		}
	}

	public void descargaImagenes(String nombreImg) {
		try {
			TParametro vParametros = new TParametro("07");
			URL url = new URL(vParametros.getPropEspecifica("UrlImagenes")
					+ nombreImg);
			URLConnection urlCon = url.openConnection();
			// System.out.println(urlCon.getContentType());
			String folder = "c:\\sct\\img\\";
			// Crea el directorio de destino en caso de que no exista
			File dir = new File(folder);

			if (!dir.exists())
				if (!dir.mkdir())
					return; // no se pudo crear la carpeta de destino

			dir = new File(folder + nombreImg);
			if (dir.exists()) {
				// System.out.println("Ya existe " + nombreImg);
				String path = vParametros
						.getPropEspecifica("RutaDescargaImagen");
				File fileServer = new File(path + nombreImg);
				long ms = fileServer.lastModified();

				Date dServerMod = new Date(ms);
				Date dLocalMod = new Date(dir.lastModified());
				if (dLocalMod.after(dServerMod)) {
					// System.out.println("Ya existe y no actualizar" +
					// nombreImg);
					return;
				}
			}
			InputStream is = urlCon.getInputStream();
			FileOutputStream fos = new FileOutputStream(folder + nombreImg);
			byte[] array = new byte[1000]; // buffer temporal de lectura.
			int leido = is.read(array);
			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}
			is.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int actualizaImagenes() {
		// System.out.println("Clave Usuario: "+ iCveUsuario);
		int dato = 0;
		ConsultaGral consulta = new ConsultaGral();
		try {
			/*
			 * System.out.println("idPersona: " +
			 * request.getParameter("iCvePersona"));
			 */
			List busquedaArea = consulta
					.ejecutaSelectQueryAdmSeg("SELECT CVALOR FROM SEGPROPIEDAD WHERE ICVESISTEMA = 7 AND "
							+ " CPROPIEDAD = 'ActualizaImg'");
			if (busquedaArea.size() > 0) {
				Iterator it = busquedaArea.iterator();
				String[] x1 = (String[]) it.next();
				dato = Integer.valueOf(x1[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dato;
	}
}
