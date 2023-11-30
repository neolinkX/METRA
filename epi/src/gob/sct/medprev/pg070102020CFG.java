package gob.sct.medprev;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.TVUsuario;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuraciï¿½n para CFG Registro de Citas
 * <p>
 * Administraciï¿½n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. Gonzï¿½lez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070102020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070102020CFG.png'>
 */
public class pg070102020CFG extends CFGCatBase2 {

	private String cCita;
	private String cModulo;
	private String cFecha;

	public pg070102020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public void mainBlock() {

		cModulo = request.getParameter("iCveModulo");
		cFecha = request.getParameter("dtFecha");
		cCita = "";
	}

	/**
	 * MÃ©todo Guardar
	 */
	public void Guardar() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
			cClave = (String) dEPICisCita.insert(null, this.getInputs());

			cCita = cClave;
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			if (cClave.compareTo("ERROR") == 0) {
				vErrores.acumulaError("Registro Existente: ", 0,
						"No es posible registrar varias citas para la misma persona el mismo dï¿½a");
			}
			if (cClave.compareTo("ERROR2") == 0) {
				vErrores.acumulaError(
						"El último examen de este expediente fue NO APTO: ",
						0,
						// "Los terceros autorizados no pueden realizar revaloraciones cuando la no aptitud fue dictaminada por las unidades médicas de la SCT");
						"Los terceros autorizados no pueden realizar revaloraciones cuando la no aptitud fue dictaminada por las unidades médicas de la SCT o por un Tercero Autorizado");
			}
			super.Guardar();
			cActual = request.getParameter("iCveUniMed");
		}
	} // MÃ©todo Guardar

	/**
	 * MÃ©todo GuardarA
	 */
	public void GuardarA() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
			cClave = (String) dEPICisCita.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // MÃ©todo GuardarA

	/**
	 * MÃ©todo Borrar
	 */
	public void Borrar() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
			cClave = (String) dEPICisCita.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // MÃ©todo Borrar

	/**
	 * MÃ©todo fillVector
	 */
	public void fillVector() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		String cWhere;
		try {

			/*
			 * cWhere = " where EPICisCita.iCveUniMed = "; cWhere = cWhere +
			 * request.getParameter("iCveUniMed") + " "; cWhere = cWhere +
			 * " and EPICisCita.dtFecha = '" +
			 * tfCampo.getDateSQL(request.getParameter("dtFecha")) + "' ";
			 * cWhere = cWhere + " and EPICisCita.iCveModulo = " +
			 * request.getParameter("iCveModulo") + " ";
			 */

			if (request.getParameter("iCveUniMed") != null
					&& (request.getParameter("dtFecha") != null && request
							.getParameter("dtFecha").compareTo("") != 0)
					&& request.getParameter("iCveModulo") != null) {

				cWhere = " where EPICisCita.iCveUniMed = ";
				cWhere = cWhere + request.getParameter("iCveUniMed") + " ";
				cWhere = cWhere + " and EPICisCita.dtFecha = '"
						+ tfCampo.getDateSQL(request.getParameter("dtFecha"))
						+ "' ";
				cWhere = cWhere + " and EPICisCita.iCveModulo = "
						+ request.getParameter("iCveModulo") + " ";

				vDespliega = dEPICisCita.FindByAll(cWhere);
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * MÃ©todo fillPK
	 */
	public void fillPK() {

		mPk.add(cActual);
		mPk.add(cModulo);
		mPk.add(cFecha);
		mPk.add(cCita);

	}

	/**
	 * MÃ©todo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVEPICisCita vEPICisCita = new TVEPICisCita();

		// System.out.println("Bajando el usuario de la sesion");
		TVUsuario usuario = (TVUsuario) this.httpReq.getSession().getAttribute(
				"UsrID"); // BEA SYSTEMS 16/10/2006
		try {
			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveModulo(iCampo);

			cCampo = "" + request.getParameter("dtFecha");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisCita.setDtFecha(dtCampo);

			cCampo = "" + request.getParameter("iCveCita");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveCita(iCampo);
			// Hora
			cCampo = "" + request.getParameter("iHora");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setIHora(iCampo);

			cCampo = "" + request.getParameter("iMinutos");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setIMinutos(iCampo);

			/*
			 * cCampo = "" + request.getParameter("cHora"); if
			 * (cCampo.compareTo("null") == 0) { cCampo = ""; }
			 * vEPICisCita.setCHora(cCampo);
			 */
			cCampo = "" + request.getParameter("cApPaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCApPaterno(cCampo);

			cCampo = "" + request.getParameter("cApMaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCApMaterno(cCampo);

			cCampo = "" + request.getParameter("cNombre");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNombre(cCampo);

			cCampo = "" + request.getParameter("cGenero");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCGenero(cCampo);

			cCampo = "" + request.getParameter("dtNacimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisCita.setDtNacimiento(dtCampo);

			cCampo = "" + request.getParameter("cRFC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCRFC(cCampo);

			cCampo = "" + request.getParameter("cCURP");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCURP(cCampo);

			// Licencias
			cCampo = "" + request.getParameter("cLicencia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCLicencia(cCampo);

			cCampo = "" + request.getParameter("cLicenciaInt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCLiceniaInt(cCampo);

			cCampo = "" + request.getParameter("iCvePaisNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePaisNac(iCampo);

			cCampo = "" + request.getParameter("iCveEstadoNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveEstadoNac(iCampo);

			cCampo = "" + request.getParameter("cExpediente");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCExpediente(cCampo);

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cNumExt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNumExt(cCampo);

			cCampo = "" + request.getParameter("cNumInt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNumInt(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCColonia(cCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICP(iCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCiudad(cCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveMunicipio(iCampo);

			cCampo = "" + request.getParameter("cTelefono");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCTelefono(cCampo);

			cCampo = "" + request.getParameter("lCambioDir");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setLCambioDir(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCvePuesto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePuesto(iCampo);

			cCampo = "" + request.getParameter("iCveCategoria");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}

			vEPICisCita.setICveCategoria(iCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveMotivo(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("iCveSituacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveSituacion(iCampo);

			cCampo = "" + request.getParameter("iCveUsuCita");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUsuCita(iCampo);

			cCampo = "" + request.getParameter("lRealizoExam");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setLRealizoExam(iCampo);

			cCampo = "" + request.getParameter("iCveUsuMCIS");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUsuMCIS(iCampo);

			cCampo = "" + request.getParameter("lProdNoConf");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setLProdNoConf(iCampo);

			// Actualizar

			cCampo = "" + request.getParameter("iCveUniMedA");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUniMedA(iCampo);

			cCampo = "" + request.getParameter("iCveModuloA");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveModuloA(iCampo);

			cCampo = "" + request.getParameter("dtFechaA");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisCita.setDtFechaA(dtCampo);

			// Se agrega el usuario el Usuario BEA SYSTEMS 16/10/2006
			if (usuario != null) {
				vEPICisCita.setICveUsuCita(usuario.getICveusuario());
			}

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEPICisCita;
	}

}