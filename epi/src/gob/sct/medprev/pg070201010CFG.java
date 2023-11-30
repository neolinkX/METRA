package gob.sct.medprev;

import java.util.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para EXAM Aplica
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104001CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070201010CFG.png'>
 */
public class pg070201010CFG extends CFGListBase2 {
	public pg070201010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {

			TDEXPExamAplica dExpExamAplica = new TDEXPExamAplica();
			TDEMOExamen dEMOExamen = new TDEMOExamen();
			TDEXPExamGenera dEXPExamGenera = new TDEXPExamGenera();
			TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
			TDEXPExamGrupo dEXPExamGrupo = new TDEXPExamGrupo();
			TDEXPExamPuesto dEXPExamPuesto = new TDEXPExamPuesto();
			TDGRLMotivo dGRLMotivo = new TDGRLMotivo();
			TDMEDSintExamen dMEDSintExamen = new TDMEDSintExamen();
			TDEXPServicio dEXPServicio = new TDEXPServicio();
			TDEXPRama dEXPRama = new TDEXPRama();
			TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
			TDEXPResultado dEXPResultado = new TDEXPResultado();
			TDGRLUniMed dGRLUniMed = new TDGRLUniMed();

			TVGRLMotivo vGRLMotivo = new TVGRLMotivo();
			TVMEDSintExamen vMEDSintExamen = new TVMEDSintExamen();
			TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
			TVGRLUniMed vGRLUniMed = new TVGRLUniMed();

			Object ObjExamAplica = new Vector();
			Object ObjExpExamCat = new Vector();
			Vector vcGrlMotivo = new Vector();
			Vector vcMedSintExamen = new Vector();
			Vector vcMedSintoma = new Vector();
			Vector vcGrlUniMed = new Vector();
			String cGenero = "";
			if (request.getParameter("cGenero") != null) {
				cGenero = request.getParameter("cGenero");
			}

			/**
			 * Paso 0
			 * 
			 * Se Llena los Objetos con los VO de:
			 * 
			 * - ExamAplica - ExamCat
			 * 
			 */

			ObjExamAplica = this.getInputsEXPExamAplica();
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) ObjExamAplica;

			ObjExpExamCat = this.getInputsExamCat();
			TVEXPExamCat vEXPExamCat = (TVEXPExamCat) ObjExpExamCat;

			/**
			 * Paso 1
			 * 
			 * Se graba en EXPExamAplica:
			 * 
			 */

			dExpExamAplica.insertEmo(null, this.getInputsEXPExamAplica());

			/**
			 * Paso 2
			 * 
			 * Se graba en EMOExamen
			 * 
			 */

			dEMOExamen.insert(null, this.getInputsEMOExamen());

			/**
			 * Paso 3
			 * 
			 * Se graba en EXPExamGenera
			 * 
			 */

			dEXPExamGenera.insertObj(null, this.getInputsExamGenera());

			/**
			 * Paso 4
			 * 
			 * Se graba en EXPExamCat
			 * 
			 */

			dEXPExamCat.insert(null, this.getInputsExamCat());

			/**
			 * Paso 5
			 * 
			 * Se graba en EXPExamGrupo
			 * 
			 */

			dEXPExamGrupo.insert(null, this.getInputsExamGrupo());

			/**
			 * Paso 6
			 * 
			 * Se graba en EXPExamPuesto
			 * 
			 */

			dEXPExamPuesto.insert(null, this.getInputsExamPuesto());

			/**
			 * Paso 7
			 * 
			 * Se Extrae MedSintExamen con el objeto de obtener Servicios y
			 * Ramas basados en la clave del proceso, clave del motivo y clave
			 * del modo de transporte
			 * 
			 */

			vcMedSintExamen = dMEDSintExamen
					.FindDSerRamaTMP(" Where iCveProceso = "
							+ vEXPExamAplica.getICveProceso()
							+ " And   iCveMotivo  = "
							+ vEXPExamCat.getICveMotivo()
							+ " And   iCveMdoTrans= "
							+ vEXPExamCat.getICveMdoTrans());

			if (vcMedSintExamen.size() > 0) {
				for (int y = 0; y < vcMedSintExamen.size(); y++) {
					vMEDSintExamen = (TVMEDSintExamen) vcMedSintExamen.get(y);

					/**
					 * Paso 8
					 * 
					 * Se inserta en la tabla EXPServicio los campos
					 * 
					 * - iCveExpediente - iCveServicio - iNumExamen
					 * 
					 */

					dEXPServicio.insertFromExpExamen(null,
							this.getInputsEXPExamAplica(),
							vMEDSintExamen.getICveServicio());

					/**
					 * Paso 9
					 * 
					 * Se inserta en la tabla EXPRama los campos
					 * 
					 * - iCveExpediente - iNumExamen - iCveServicio - iCveRama
					 * 
					 */

					dEXPRama.insertFromExpExamAplica(null,
							this.getInputsEXPExamAplica(),
							vMEDSintExamen.getICveServicio(),
							vMEDSintExamen.getICveRama());

					/**
					 * Paso 10
					 * 
					 * Se Obtienen los datos de los sintomas (MEDSintnoma)
					 * basados en:
					 * 
					 * - iCveServicio - iCveRama - cGenero
					 * 
					 */

					// vcMedSintoma = dMEDSintoma.FindByAll(
					vcMedSintoma = dMEDSintoma
							.FindByAllExaEmo(" Where a.iCveServicio = "
									+ vMEDSintExamen.getICveServicio()
									+ " And a.iCveRama = "
									+ vMEDSintExamen.getICveRama()
									+ " And (a.cGenero = '" + cGenero
									+ "' OR a.cGenero='A')"
									+ " And a.lactivo = 1 "
									+ " And MedSintExamenTMP.icveproceso = "
									+ vEXPExamAplica.getICveProceso()
									+ "	And MedSintExamenTMP.icvemotivo = "
									+ vEXPExamCat.getICveMotivo()
									+ "	And MedSintExamenTMP.icvemdotrans = "
									+ vEXPExamCat.getICveMdoTrans());

					if (vcMedSintoma.size() > 0) {
						for (int z = 0; z < vcMedSintoma.size(); z++) {
							vMEDSintoma = (TVMEDSintoma) vcMedSintoma.get(z);

							/**
							 * Paso 11
							 * 
							 * Se Graba En la Tabla EXPResultado los Valores:
							 * 
							 * - iCveExpediente - iNumExamen - iCveServicio -
							 * iCveRama - iCveSintoma
							 * 
							 */

							dEXPResultado.insertFromEXPExamAplica(null,
									this.getInputsEXPExamAplica(),
									vMEDSintExamen.getICveServicio(),
									vMEDSintExamen.getICveRama(),
									vMEDSintoma.getICveSintoma());
						}
					}
				}
			}

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {
			cClave = (String) dEXPExamAplica.update(null,
					this.getInputsEMOExamen());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo BorrarB
	 */
	public void BorrarB() {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {
			// cClave = (String) dEXPExamAplica.disable(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.BorrarB();
		}
	} // Metodo BorrarB

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		String lAction = request.getParameter("hdBoton");
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {
			// vDespliega = dEXPExamAplica.FindByAll();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo que devuelve la lista de unidades m�dicas v�lidas
	 * 
	 * @return un Vector con los value objects de las unidades m�dicas
	 */
	public Vector getUniMedsValidas(String cCveProceso) {
		Vector vcUMValidas = null;
		int iCveProceso = 0;
		try {
			TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true)
					.getAttribute("UsrID");
			if (cCveProceso != null && !cCveProceso.equals("null")
					&& !cCveProceso.equals("undefined")
					&& !cCveProceso.equals("")) {
				iCveProceso = Integer.parseInt(cCveProceso);
			}
			vcUMValidas = vUsuario.getVUniFiltro(iCveProceso);
		} catch (Exception ex) {
			error("getUniMedsValidas", ex);
		}
		return vcUMValidas;
	}

	public Vector getPaises() {
		Vector vPaises = null;
		TDPais dPaises = new TDPais();
		try {
			vPaises = dPaises.FindByAll();
		} catch (Exception ex) {
			error("getPaises", ex);
		}
		return vPaises;
	}

	public Vector getEdo(String cPais) {
		Vector vEstados = null;
		TDGRLEstado dEstados = new TDGRLEstado();
		try {
			vEstados = dEstados.FindByAll(" Where iCvePais= " + cPais);
		} catch (Exception ex) {
			error("getEdo", ex);
		}
		return vEstados;
	}

	/**
	 * Metodo findUser
	 */
	public TVPERDatos findUser() {
		TVPERDatos vPerDat = null;
		try {
			String cUserId = request.getParameter("iCvePersonal");
			// String cNumExa = request.getParameter("iNumExamen");

			if (cUserId == null) {
				cUserId = "0";
			}
			int iCvePersonal = Integer.parseInt(cUserId);

			/*
			 * if (cNumExa == null) { cNumExa = "0"; } int iNumExamen =
			 * Integer.parseInt(cNumExa);
			 */
			vPerDat = new TDEXPExamAplica().findUserNoExamNoAp(iCvePersonal); // ,
																				// iNumExamen);
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vPerDat;
	}

	/**
	 * Metodo getEdad
	 */
	public int getEdad(java.sql.Date dtFechaNac) {
		Calendar hoy = Calendar.getInstance();
		Calendar nac = Calendar.getInstance();
		nac.setTime(dtFechaNac);
		int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
		if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
				.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE)) {
			edad--;
		}
		return edad;
	}

	/**
	 * Metodo getInputsEMOExamen
	 */
	public Object getInputsEMOExamen() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		/*
		 * Calcula Fecha Actual
		 */
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");
		TDEMOExamen dEMOExamen = new TDEMOExamen();
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		TVEMOExamen vEMOExamen = new TVEMOExamen();

		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}

			vEMOExamen.setICveExpediente(iCampo);

			int iNumExamen = dEMOExamen.getINumExamenMaximo(vEMOExamen
					.getICveExpediente());

			cCampo = "" + request.getParameter("iCveMomentoAP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setICveMomentoAp(iCampo);

			cCampo = "" + request.getParameter("cMatricula");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEMOExamen.setCMatricula(cCampo);

			cCampo = "" + request.getParameter("cOrigen");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEMOExamen.setCOrigen(cCampo);

			cCampo = "" + request.getParameter("cDestino");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEMOExamen.setCDestino(cCampo);

			cCampo = "" + request.getParameter("iCvePaisOr");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setICvePaisOr(iCampo);

			cCampo = "" + request.getParameter("iCveEdoOr");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setICveEdoOr(iCampo);

			cCampo = "" + request.getParameter("iCvePaisDes");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setICvePaisDes(iCampo);

			cCampo = "" + request.getParameter("iCveEdoDes");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setICveEdoDes(iCampo);

			cCampo = "" + request.getParameter("lSinLicencia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setLSinLicencia(iCampo);

			cCampo = "" + request.getParameter("cLicencia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEMOExamen.setCLicencia(cCampo);

			cCampo = "" + request.getParameter("dtVenceLic");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEMOExamen.setDtVenceLic(dtCampo);

			cCampo = "" + request.getParameter("lLicVencida");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setLLicVencida(iCampo);

			cCampo = "" + request.getParameter("dtDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			
			//////////////Nuevos Campos MAGTIC EMO 25 Agosto 2015 - AG SA.
			cCampo = "" + request.getParameter("iCveSubModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setICveSubModulo(iCampo);
			
			cCampo = "" + request.getParameter("iCveCapturaDelExamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setICveCapturaDelExamen(iCampo);

			cCampo = "" + request.getParameter("iCveUsuAplicoEMO");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEMOExamen.setICveUsuAplicoEMO(iCampo);

			cCampo = "" + request.getParameter("cMedDic");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEMOExamen.setCMedDic(cCampo);
			
			cCampo = "" + request.getParameter("cCedula");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEMOExamen.setCCedula(cCampo);
			
			cCampo = "" + request.getParameter("iCveFolio");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEMOExamen.setICveFolio(cCampo);
			
			
			//final String OLD_FORMAT = "yyyy-MM-dd";
			final String OLD_FORMAT = "dd/MM/yyyy";
			final String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
			//int k = 0;
			//String[] createdArray = null;
			String oldDateString = "" + request.getParameter("dtSolicitado");
			String oldMinutos = "" + request.getParameter("horas") + ":00.000";
			String newDateString;
			oldDateString= oldDateString.substring(6, 10) +"-"
						   +oldDateString.substring(3, 5) +"-"
						   +oldDateString.substring(0, 2);
			System.out.println(oldDateString);
			System.out.println(oldMinutos);

			DateFormat formatter = new SimpleDateFormat(OLD_FORMAT);
			//Date d = formatter.parse(oldDateString);
			((SimpleDateFormat) formatter).applyPattern(NEW_FORMAT);
			//newDateString = formatter.format(d);
			//System.out.println(newDateString);

			//Timestamp ts = Timestamp.valueOf(newDateString);
			//System.out.println(ts);
			//String text = "2011-10-02 18:48:00.000";
			String text = oldDateString+" "+oldMinutos;
			System.out.println(text);
			Timestamp ts = Timestamp.valueOf(text);
			
			vEMOExamen.setTIAplicacion(ts);
			
			
			
						
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEMOExamen;
	}

	/**
	 * Metodo getInputsEXPExamAplica
	 */
	public Object getInputsEXPExamAplica() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		java.sql.Time fecFormatoTime = null;
		/*
		 * Calcula Fecha Actual
		 */
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");
		TDEMOExamen dEMOExamen = new TDEMOExamen();
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();

		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}

			vEXPExamAplica.setICveExpediente(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveProceso(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveModulo(iCampo);

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("UserId");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveUsuSolicita(iCampo);

			cCampo = "" + request.getParameter("iFolioEs");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setIFolioEs(iCampo);

			vEXPExamAplica.setDtAplicacion(defaultFecha);

			cCampo = "" + request.getParameter("dtConcluido");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtConcluido(dtCampo);

			cCampo = "" + request.getParameter("dtSolicitado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtSolicitado(dtCampo);

			cCampo = "" + request.getParameter("lIniciado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLIniciado(iCampo);

			cCampo = "" + request.getParameter("lDictaminado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLDictaminado(iCampo);

			cCampo = "" + request.getParameter("lInterconsulta");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLInterConsulta(iCampo);

			cCampo = "" + request.getParameter("lInterConcluye");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLInterConcluye(iCampo);

			cCampo = "" + request.getParameter("lConcluido");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLConcluido(iCampo);

			cCampo = "" + request.getParameter("dtDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEXPExamAplica.setDtDictamen(dtCampo);

			cCampo = "" + request.getParameter("dtArchivado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}

			vEXPExamAplica.setDtArchivado(dtCampo);

			cCampo = "" + request.getParameter("dtEntregaRes");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtEntregaRes(dtCampo);

			cCampo = "" + request.getParameter("dtArchivado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtArchivado(dtCampo);

			cCampo = "" + request.getParameter("hdiCveEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveNumEmpresa(iCampo);

			cCampo = "" + request.getParameter("horas") + ":00";
			try {
				SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						"hh:mm:ss", new Locale("es", "ES"));
				fecFormatoTime = new java.sql.Time(sdf.parse(cCampo).getTime());
				// System.out.println("Fecha con el formato java.sql.Time: " +
				// fecFormatoTime);
			} catch (Exception ex) {
				System.out
						.println("Error al obtener el formato de la fecha/hora: "
								+ ex.getMessage());
			}
			vEXPExamAplica.setTInicio(fecFormatoTime);

			vEXPExamAplica.setINumExamen(dEMOExamen
					.getINumExamenMaximo(vEXPExamAplica.getICveExpediente()));

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPExamAplica;
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputsExamGenera() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		/*
		 * Calcula Fecha Actual
		 */
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");
		TDEMOExamen dEMOExamen = new TDEMOExamen();
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		TVEXPExamGenera vEXPExamGenera = new TVEXPExamGenera();
		Vector vcMEDPerfilMC = new Vector();

		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamGenera.setICveExpediente(iCampo);

			int iNumExamen = dEMOExamen.getINumExamenMaximo(vEXPExamGenera
					.getICveExpediente());
			vEXPExamGenera.setINumExamen(iNumExamen);

			cCampo = "" + request.getParameter("lAplicado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamGenera.setLAplicado(iCampo);

			cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamGenera.setICveProceso(iCampo);

			cCampo = "" + request.getParameter("iNumExamenGenera");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamGenera.setINumExamenGenera(iCampo);

			cCampo = "" + request.getParameter("dtProgramado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamGenera.setDtProgramado(dtCampo);
			vEXPExamGenera.setDtPosibleAten(dtCampo);
			vEXPExamGenera.setDtAplicacion(dtCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPExamGenera;
	}

	/**
	 * Metodo getInputsExamCat
	 */
	public Object getInputsExamCat() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		/*
		 * Calcula Fecha Actual
		 */
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");
		TDEMOExamen dEMOExamen = new TDEMOExamen();
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		TVEXPExamCat vEXPExamCat = new TVEXPExamCat();
		Vector vcMEDPerfilMC = new Vector();

		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamCat.setICveExpediente(iCampo);

			int iNumExamen = dEMOExamen.getINumExamenMaximo(vEXPExamCat
					.getICveExpediente());
			vEXPExamCat.setINumExamen(iNumExamen);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamCat.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCveCategoria");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamCat.setICveCategoria(iCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamCat.setICveMotivo(iCampo);

			cCampo = "" + request.getParameter("iVigMeses");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamCat.setIVigMeses(iCampo);

			cCampo = "" + request.getParameter("cRefAlfaNum");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "  ";
			}
			vEXPExamCat.setCRefAlfanum(cCampo);

			cCampo = "" + request.getParameter("lDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamCat.setLDictamen(iCampo);

			cCampo = "" + request.getParameter("dtMovIngreso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamCat.setDtMovIngreso(dtCampo);

			cCampo = "" + request.getParameter("dtInicioVig");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamCat.setDtInicioVig(dtCampo);

			cCampo = "" + request.getParameter("dtFinVig");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamCat.setDtFinVig(dtCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPExamCat;
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputsExamGrupo() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		/*
		 * Calcula Fecha Actual
		 */
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");
		TDEMOExamen dEMOExamen = new TDEMOExamen();
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		TVEXPExamGrupo vEXPExamGrupo = new TVEXPExamGrupo();
		TVMEDPerfilMC vMEDPerfilMC = new TVMEDPerfilMC();
		Vector vcMEDPerfilMC = new Vector();

		try {
			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamGrupo.setICveExpediente(iCampo);

			int iNumExamen = dEMOExamen.getINumExamenMaximo(vEXPExamGrupo
					.getICveExpediente());
			vEXPExamGrupo.setINumExamen(iNumExamen);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamGrupo.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCveGrupo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamGrupo.setICveGrupo(iCampo);

			vcMEDPerfilMC = dMEDPerfilMC.findByAll(" Where iCveMdoTrans = "
					+ vEXPExamGrupo.getICveMdoTrans() + " And iCveGrupo = "
					+ vEXPExamGrupo.getICveGrupo() + " And lVigente = 1 ");

			if (vcMEDPerfilMC.size() > 0) {
				vMEDPerfilMC = (TVMEDPerfilMC) vcMEDPerfilMC.get(0);
				vEXPExamGrupo.setICvePerfil(vMEDPerfilMC.getICvePerfil());
			} else {
				vEXPExamGrupo.setICvePerfil(0);
			}

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPExamGrupo;
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputsExamPuesto() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		/*
		 * Calcula Fecha Actual
		 */
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");
		TDEMOExamen dEMOExamen = new TDEMOExamen();
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		TVEXPExamPuesto vEXPExamPuesto = new TVEXPExamPuesto();
		Vector vcMEDPerfilMC = new Vector();

		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamPuesto.setICveExpediente(iCampo);

			int iNumExamen = dEMOExamen.getINumExamenMaximo(vEXPExamPuesto
					.getICveExpediente());
			vEXPExamPuesto.setINumExamen(iNumExamen);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamPuesto.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCvePuesto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamPuesto.setICvePuesto(iCampo);

			cCampo = "" + request.getParameter("dtDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPExamPuesto;
	}

}

