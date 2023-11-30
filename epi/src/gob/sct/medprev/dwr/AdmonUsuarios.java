/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dwr;

import com.micper.excepciones.DAOException;
import com.micper.seguridad.vo.TVUsuario;
import gob.sct.medprev.dao.SEGAccPwd;
import gob.sct.medprev.dao.TDEXPBITMOD;
import gob.sct.medprev.dao.TDExpediente;
import gob.sct.medprev.dao.TDMEDInhabilita;
import gob.sct.medprev.dao.TDUsuXExpLogin;
import gob.sct.medprev.dao.TDUsuarios;
import gob.sct.medprev.dwr.framework.GenericResponse;
import gob.sct.medprev.dwr.vo.BitacoraRetornoVo;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.dwr.vo.GrlExpedientes;
import gob.sct.medprev.dwr.vo.GrlUsuXExpLogin;
import gob.sct.medprev.dwr.vo.GrlUsuarios;
import gob.sct.medprev.vo.TVMEDInhabilita;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author ISM
 */
public class AdmonUsuarios implements Serializable {
	/**
     * 
     */
	private static long serialVersionUID = 1L;
	SEGAccPwd dSEGAccPwd = new SEGAccPwd();
	TDExpediente tdExpediente = new TDExpediente();
	TDUsuarios tdUsuarios = new TDUsuarios();
	TDUsuXExpLogin tdUsuXExpLogin = new TDUsuXExpLogin();

	/**
	 * Metodo para catalogo de tabla GRLTIPOOPERABIT
	 * 
	 * @return GenericResponse
	 */
	public GenericResponse findAllUsuarios() {
		GenericResponse response = new GenericResponse();
		List<GrlUsuarios> lista = new ArrayList<GrlUsuarios>();
		try {
			lista = tdUsuarios.findAllUsuarios();
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

	public GenericResponse desbloqueaUsuario(String icveUsuario) {

		GenericResponse response = new GenericResponse();
		boolean res = false;
		String comments = "";
		try {

			java.util.Date date = new java.util.Date();
			MedPredDwr obj = new MedPredDwr();
			TVUsuario vUsuario = new TVUsuario();
			vUsuario.setICveusuario(Integer.parseInt(icveUsuario));
			vUsuario.desbloqueaUsuario();
			// obj.insertValidacionBiometrica(Integer.parseInt(icveUsuario),
			// "NA", "NA", "NA", "NA", "NA");
			res = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (!res) {
			response.setData(comments);
			response.setSuccess(false);
		} else {
			response.setSuccess(true);
		}
		return response;

	}

	public GenericResponse desbloqueaExpediente(String iCveExpediente) {

		GenericResponse response = new GenericResponse();
		boolean res = false;
		String comments = "";
		try {
			java.util.Date date = new java.util.Date();
			MedPredDwr obj = new MedPredDwr();
			obj.insertValidacionBiometricaExpediente(Integer
					.parseInt(iCveExpediente));
			TVMEDInhabilita MEDInhabilita = new TVMEDInhabilita();
			MEDInhabilita.setICvePersonal(Integer.parseInt(iCveExpediente));
			MEDInhabilita.setICveMotivo(48);
			MEDInhabilita.setiCveUsuInh(Integer.parseInt(iCveExpediente));
			MEDInhabilita
					.setCObservacion("Deshabilitado automaticamente por exceso de validaciones dactilares erroneas");
			java.util.Date today = new java.util.Date();
			java.sql.Date sqlToday = new java.sql.Date(today.getTime());
			Calendar cal = Calendar.getInstance();
			cal.setTime(sqlToday);
			cal.add(Calendar.DAY_OF_YEAR, -1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			java.sql.Date yesterday = new java.sql.Date(cal.getTimeInMillis());

			MEDInhabilita.setInicioInh(yesterday);
			MEDInhabilita.setFinInh(yesterday);
			TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
			dMEDInhabilita.delete(null, MEDInhabilita);
			res = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (!res) {
			response.setData(comments);
			response.setSuccess(false);
		} else {
			response.setSuccess(true);
		}
		return response;

	}

	/**
	 * Metodo para catalogo de tabla GRLTIPOOPERABIT
	 * 
	 * @return GenericResponse
	 */
	public GenericResponse guardaAccesoUsuario(String iCveUsuario,
			String iCveExpediente, String iDedo, String iCveUsuarioRegistra, String Sistema, String Transporte) {

		GenericResponse response = new GenericResponse();
		boolean res = false;
		String comments = "";
		try {

			java.util.Date date = new java.util.Date();
			GrlUsuXExpLogin Login = new GrlUsuXExpLogin();

			Login.setICVEUSUARIO(Integer.parseInt(iCveUsuario));
			Login.setICVEEXPEDIENTE(Integer.parseInt(iCveExpediente));
			Login.setIDEDO(Integer.parseInt(iDedo));
			Login.setICVEUSUARIOREGISTRO(Integer.parseInt(iCveUsuarioRegistra));
			Login.setTSCAPTURA(new Timestamp(date.getTime()));
			Login.setINODOCTO(obtenNumeroUltimoDocumento(iCveExpediente, iDedo));
			Login.setLLICENCIAS(Integer.parseInt(Sistema));
			Login.setICVEMDOTRANS(Integer.parseInt(Transporte));

			if (!tdUsuXExpLogin.existeRegistroLoginExpediente(iCveExpediente)
					&& !tdUsuXExpLogin.existeRegistroLoginUsuario(iCveUsuario)) {
				res = tdUsuXExpLogin.guardaHuellasLogin(Login);
				if (res) {
					TDEXPBITMOD bitacora = new TDEXPBITMOD();
					ExpBitMod registrobitacora = new ExpBitMod();
					registrobitacora.setiCveExpediente(iCveExpediente);
					registrobitacora.setiNumExamen("0");
					registrobitacora.setOperacion("12");
					registrobitacora
							.setDescripcion("Creacion de login biometrico de Usuario: "
									+ iCveUsuario
									+ " con el Expediente: "
									+ iCveExpediente
									+ " y el dedo: "
									+ iDedo
									+ " registrado por el usuario: "
									+ iCveUsuarioRegistra
									+ " con el numero de documento: "
									+ Login.getINODOCTO()
									+ " del sistema:"
									+ Login.getLLICENCIAS()
									+ " del Transporte:"
									+ Login.getICVEMDOTRANS());
					registrobitacora.setiCveUsuarioRealiza(iCveUsuarioRegistra);
					bitacora.insertaRegistroBitacoraBiometricos(registrobitacora);
				}
			} else {
				comments = "Ya se tiene un registro para el expediente o el usuario seleccionado";
			}
		} catch (DAOException ex) {
			ex.printStackTrace();
		}
		if (!res) {
			response.setData(comments);
			response.setSuccess(false);
		} else {
			response.setSuccess(true);
		}
		return response;
	}

	/**
	 * Metodo para catalogo de tabla GRLTIPOOPERABIT
	 * 
	 * @return GenericResponse
	 */
	public GenericResponse modificaAccesoUsuario(String iCveUsuario,
			String iCveExpediente, String iDedo, String iCveUsuarioRegistra, String Sistema, String Transporte) {

		GenericResponse response = new GenericResponse();
		boolean res = false;
		String error = "";
		try {

			java.util.Date date = new java.util.Date();
			GrlUsuXExpLogin Login = new GrlUsuXExpLogin();

			Login.setICVEUSUARIO(Integer.parseInt(iCveUsuario));
			Login.setICVEEXPEDIENTE(Integer.parseInt(iCveExpediente));
			Login.setIDEDO(Integer.parseInt(iDedo));
			Login.setICVEUSUARIOREGISTRO(Integer.parseInt(iCveUsuarioRegistra));
			Login.setTSCAPTURA(new Timestamp(date.getTime()));
			Login.setINODOCTO(obtenNumeroUltimoDocumento(iCveExpediente, iDedo));
			Login.setLLICENCIAS(Integer.parseInt(Sistema));
			Login.setICVEMDOTRANS(Integer.parseInt(Transporte));

			res = tdUsuXExpLogin.modificaHuellasLogin(Login);
			if (res) {
				TDEXPBITMOD bitacora = new TDEXPBITMOD();
				ExpBitMod registrobitacora = new ExpBitMod();
				registrobitacora.setiCveExpediente(iCveExpediente);
				registrobitacora.setiNumExamen("0");
				registrobitacora.setOperacion("12");
				registrobitacora
						.setDescripcion("Modificacion del login biometrico de Usuario: "
								+ iCveUsuario
								+ " con el Expediente: "
								+ iCveExpediente
								+ " se cambio al dedo: "
								+ iDedo
								+ " modificado por el usuario: "
								+ iCveUsuarioRegistra
								+ " con el numero de documento: "
								+ Login.getINODOCTO()
								+ " del sistema:" 
								+ Login.getLLICENCIAS()
								+ " del transporte:" 
								+ Login.getICVEMDOTRANS());
				registrobitacora.setiCveUsuarioRealiza(iCveUsuarioRegistra);
				bitacora.insertaRegistroBitacoraBiometricos(registrobitacora);
			}

			if (Login.getINODOCTO() == -1) {
				res = false;
				error = "No se encontro imagen para el dedo seleccionado";
			}
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		if (!res) {
			response.setSuccess(false);
			response.setData(error);
		} else {
			response.setSuccess(true);
		}
		return response;
	}

	/**
	 * Metodo para catalogo de tabla GRLTIPOOPERABIT
	 * 
	 * @return GenericResponse
	 */
	public int obtenNumeroUltimoDocumento(String iCveExpediente, String iDedo) {
		int res = -1;
		try {
			res = tdUsuXExpLogin.buscaUltimoDocumentoContentManager(
					iCveExpediente, iDedo);
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return res;
	}

	/**
	 * Metodo para catalogo de tabla GRLTIPOOPERABIT
	 * 
	 * @return GenericResponse
	 */
	public GenericResponse findUsuario(String CUSUARIO, String ICVEUSUARIO,
			String CNOMBRE, String CAP, String CAM) {
		System.out.println("BUSCA USUARIO");
		if (CUSUARIO == null) {
			CUSUARIO = "";
		}
		if (ICVEUSUARIO.equals(null)) {
			ICVEUSUARIO = "";
		}
		if (CNOMBRE.equals(null)) {
			CNOMBRE = "";
		}
		if (CAP.equals(null)) {
			CAP = "";
		}
		if (CAM.equals(null)) {
			CAM = "";
		}
		GenericResponse response = new GenericResponse();
		List<GrlUsuarios> lista = new ArrayList<GrlUsuarios>();
		try {
			lista = tdUsuarios.findUsuario(CUSUARIO, ICVEUSUARIO, CNOMBRE, CAP,
					CAM);
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

	public GenericResponse findUsuarioBloqueado(String CUSUARIO,
			String ICVEUSUARIO, String CNOMBRE, String CAP, String CAM) {
		// System.out.println("BUSCA USUARIO");
		if (CUSUARIO == null || CUSUARIO.equals("")) {
			CUSUARIO = "";
		}
		if (ICVEUSUARIO.equals(null) || ICVEUSUARIO.equals("")) {
			ICVEUSUARIO = "";
		}
		if (CNOMBRE.equals(null) || CNOMBRE.equals("")) {
			CNOMBRE = "";
		}
		if (CAP.equals(null) || CAP.equals("")) {
			CAP = "";
		}
		if (CAM.equals(null) || CAM.equals("")) {
			CAM = "";
		}
		GenericResponse response = new GenericResponse();
		List<GrlUsuarios> lista = new ArrayList<GrlUsuarios>();
		try {
			lista = tdUsuarios.findUsuarioBloqueado(CUSUARIO, ICVEUSUARIO,
					CNOMBRE, CAP, CAM);
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

	/**
	 * Metodo para catalogo de tabla GRLTIPOOPERABIT
	 * 
	 * @return GenericResponse
	 */
	public GenericResponse findExpediente(String ICVEEXPEDIENTE, String CRFC,
			String CNOMBRE, String CAP, String CAM) {

		if (ICVEEXPEDIENTE == null || ICVEEXPEDIENTE.equals("")) {
			ICVEEXPEDIENTE = "";
		}
		if (CRFC.equals(null) || CRFC.equals("")) {
			CRFC = "";
		}
		if (CNOMBRE.equals(null) || CNOMBRE.equals("")) {
			CNOMBRE = "";
		}
		if (CAP.equals(null) || CAP.equals("")) {
			CAP = "";
		}
		if (CAM.equals(null) || CAM.equals("")) {
			CAM = "";
		}
		GenericResponse response = new GenericResponse();
		List<GrlExpedientes> lista = new ArrayList<GrlExpedientes>();
		try {
			lista = tdExpediente.findExpediente(ICVEEXPEDIENTE, CRFC, CNOMBRE,
					CAP, CAM);
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

	public GenericResponse findExpedienteBloqueado(String ICVEEXPEDIENTE,
			String CRFC, String CNOMBRE, String CAP, String CAM) {

		if (ICVEEXPEDIENTE == null || ICVEEXPEDIENTE.equals("")) {
			ICVEEXPEDIENTE = "";
		}
		if (CRFC.equals(null) || CRFC.equals("")) {
			CRFC = "";
		}
		if (CNOMBRE.equals(null) || CNOMBRE.equals("")) {
			CNOMBRE = "";
		}
		if (CAP.equals(null) || CAP.equals("")) {
			CAP = "";
		}
		if (CAM.equals(null) || CAM.equals("")) {
			CAM = "";
		}
		GenericResponse response = new GenericResponse();
		List<GrlExpedientes> lista = new ArrayList<GrlExpedientes>();
		try {
			lista = tdExpediente.findExpedienteBloqueado(ICVEEXPEDIENTE, CRFC,
					CNOMBRE, CAP, CAM);
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

	/**
	 * Metodo para catalogo de tabla GRLTIPOOPERABIT
	 * 
	 * @return GenericResponse
	 */
	public GenericResponse findAllExpedientes() {
		GenericResponse response = new GenericResponse();
		List<GrlExpedientes> lista = new ArrayList<GrlExpedientes>();
		try {
			lista = tdExpediente.findAllExpedientes();
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

	/**
	 * Metodo para catalogo de tabla GRLTIPOOPERABIT
	 * 
	 * @return GenericResponse
	 */
	public GenericResponse findAllUsuXExpLogin() {
		GenericResponse response = new GenericResponse();
		List<GrlUsuXExpLogin> lista = new ArrayList<GrlUsuXExpLogin>();
		try {
			lista = tdUsuXExpLogin.findAllUsuXExpLogin();
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

	public GenericResponse deleteUsuXExpLogin(String iCveusuario,
			String iCveExpediente) {
		GenericResponse response = new GenericResponse();
		int x = 0;
		try {
			x = tdUsuXExpLogin.deleteUsuXExp(Integer.parseInt(iCveusuario),
					Integer.parseInt(iCveExpediente));
		} catch (DAOException ex) {
			Logger.getLogger(MedPredDwr.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		if (x <= 0) {
			response.setSuccess(false);
		} else {
			// response.setData(lista);
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
			lista = tdExpediente.findExpBitMod(retorno.getPersona(),
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
}
