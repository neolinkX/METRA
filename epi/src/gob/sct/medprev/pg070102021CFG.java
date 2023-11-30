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
import gob.sct.medprev.util.reglas.RImpedirExamenMedico;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.util.reglas.*;

/**
 * * Clase de configuraciÃ¯Â¿Â½n para CFG Registro de Citas
 * <p>
 * AdministraciÃ¯Â¿Â½n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>AG SA L
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070102021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070102020CFG.png'>
 */
public class pg070102021CFG extends CFGCatBase2 {

	private String cCita;
	private String cModulo;
	private String cFecha;

	public pg070102021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public void mainBlock() {

		cModulo = request.getParameter("iCveModulo");
		cFecha = request.getParameter("dtFecha");
		cCita = "";
	}

	/**
	 * MÃƒÂ©todo Guardar
	 */
	public void Guardar() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
			 ////Valida si proviene de CDI y si se impide registro por ser insulinodependiente///////
		    boolean CDI = false;
		    boolean InsulinoDependiente = false;
		    boolean Anticoagulante = false;
			if (request.getParameter("iCveUniMed") != null) {
				if (request.getParameter("iCveModulo") != null) {
					System.out.println(request.getParameter("iCveUniMed"));
					System.out.println(request.getParameter("iCveModulo"));
					if (request.getParameter("iCveUniMed").equals("1") || request.getParameter("iCveUniMed").equals("1")) {
						if (request.getParameter("iCveModulo").equals("1")) {
							CDI = true;
						}
					}
				}
			}
			
			RImpedirExamenMedico rImpedirExamen = new RImpedirExamenMedico();
			InsulinoDependiente = rImpedirExamen.DiagnosticoDeDiabetesMellitusInsulinodependiente(request.getParameter("iCvePersonal"));
			Anticoagulante = rImpedirExamen.DiagnosticoDeUsoDeAnticoagulantes(request.getParameter("iCvePersonal"));
			////////////////////////////////////
			System.out.println("InsulinoDependiente = "+InsulinoDependiente);
			System.out.println("CDI = "+CDI);
			if((InsulinoDependiente || Anticoagulante) && (!CDI)){
				if(InsulinoDependiente){
					cCita = "insulinodependiente";
					cClave= "insulinodependiente";
					System.out.println("Op1");
				}
				if(Anticoagulante){
					cCita = "anticoagulante";
					cClave= "anticoagulante";
					System.out.println("Op1");
				}
			}else{
				cClave = (String) dEPICisCita.insertCA(null, this.getInputs());
				cCita = cClave;
				System.out.println("Op2");
			}
			System.out.println("cCita = "+cCita);
			System.out.println("cClave = "+cClave);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			// System.out.println(cClave);
			if (cClave.compareTo("ERROR") == 0) {
				vErrores.acumulaError("Registro Existente: ", 0,
						"No es posible registrar varias citas para la misma persona el mismo d�a");
			}
			if (cClave.compareTo("ERROR2") == 0) {
				vErrores.acumulaError(
						"El �ltimo examen de este expediente fue NO APTO: ",
						0,
						"Los terceros autorizados no pueden realizar revaloraciones cuando la no aptitud fue dictaminada por las unidades m�dicas de la SCT o por un Tercero Autorizado");
			}
			if (cClave.compareTo("ERROR3") == 0) {
				vErrores.acumulaError(
						"El �ltimo examen de este expediente fue por el motivo Evaluaci�n T�cnica del Desempe�o: ",
						0,
						"por lo cual tendr�n que acudir a una Unidad M�dica para la realizaci�n de su examen.");
			}
			if (cClave.compareTo("ERROR4") == 0) {
				vErrores.acumulaError(
						"Todos aquellos expedientes cerrados como NO APTOS por art�culo 12 de todas las unidades m�dicas de la DGPMPT no podr�n ser revalorados por terceros particulares, ",
						0,
						"�nicamente podr�n revalorarse en alguna unidad m�dica de la DGPMPT.");
			}
			if (cClave.compareTo("insulinodependiente") == 0) {
				System.out.println("Acumulo Error insulinodependiente");
				vErrores.acumulaError(
						"Cita no Registrada: Este Expediente solo puede ser revalorado por la Direcci�n General de Protecci�n y Medicina Preventiva en el Transporte (CDI).",
						0," Debido al diagn�stico como Insulinodependiente en su �ltimo examen EPI realizado.");
			}
			if (cClave.compareTo("anticoagulante") == 0) {
				System.out.println("Acumulo Error anticoagulante");
				vErrores.acumulaError(
						"Cita no Registrada: Este Expediente solo puede ser revalorado por la Direcci�n General de Protecci�n y Medicina Preventiva en el Transporte (CDI).",
						0," Debido al diagn�stico de Uso de Anticoagulantes.");
			}
			super.Guardar();
			cActual = request.getParameter("iCveUniMed");
		}
	} // MÃƒÂ©todo Guardar

	/**
	 * MÃƒÂ©todo GuardarA
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
	} // MÃƒÂ©todo GuardarA

	/**
	 * MÃƒÂ©todo Borrar
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
	} // MÃƒÂ©todo Borrar

	/**
	 * MÃƒÂ©todo fillVector
	 */
	public void fillVector() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		String cWhere;
		String cWhere2;
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

				cWhere2 = " E.iCveUniMed = ";
				cWhere2 = cWhere2 + request.getParameter("iCveUniMed") + " ";
				cWhere2 = cWhere2 + " and E.dtFecha = '"
						+ tfCampo.getDateSQL(request.getParameter("dtFecha"))
						+ "' ";
				cWhere2 = cWhere2 + " and E.iCveModulo = "
						+ request.getParameter("iCveModulo") + " ";

				vDespliega = dEPICisCita.FindByAll2(cWhere, cWhere2);
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * MÃƒÂ©todo fillPK
	 */
	public void fillPK() {

		mPk.add(cActual);
		mPk.add(cModulo);
		mPk.add(cFecha);
		mPk.add(cCita);

	}

	/**
	 * MÃƒÂ©todo getInputs
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
			if (request.getParameter("cApPaterno") != null)
				cCampo = new String(request.getParameter("cApPaterno")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCApPaterno(cCampo);

			if (request.getParameter("cApMaterno") != null)
				cCampo = new String(request.getParameter("cApMaterno")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCApMaterno(cCampo);

			if (request.getParameter("cNombre") != null)
				cCampo = new String(request.getParameter("cNombre").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNombre(cCampo);

			if (request.getParameter("cGenero") != null)
				cCampo = new String(request.getParameter("cGenero").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCGenero(cCampo);
			
			
/////////// Se agrego el nuevo Campo Sexo que cumple con la norma DGIS 27-05-2014////
			if (request.getParameter("cGenero") != null)
				cCampo = new String(request.getParameter("cGenero").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			if(cCampo.equals("M")){
				cCampo = "H";
			}
			if(cCampo.equals("F")){
				cCampo = "M";
			}
			vEPICisCita.setCSexo_DGIS(cCampo);
////////////////////////////////////////////////////////////////////////////////////////			
			

			cCampo = "" + request.getParameter("dtNacimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				cCampo = cCampo.charAt(6)+""+cCampo.charAt(7)+"/"+cCampo.charAt(4)+""+cCampo.charAt(5)+"/"+cCampo.charAt(0)+""+cCampo.charAt(1)+""+cCampo.charAt(2)+""+cCampo.charAt(3);
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisCita.setDtNacimiento(dtCampo);

			if (request.getParameter("cRFC") != null)
				cCampo = new String(request.getParameter("cRFC").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCRFC(cCampo);

			if (request.getParameter("cCURP") != null)
				cCampo = new String(request.getParameter("cCURP").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCURP(cCampo);

			// Licencias
			if (request.getParameter("cLicencia") != null)
				cCampo = new String(request.getParameter("cLicencia").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCLicencia(cCampo);

			if (request.getParameter("cLicenciaInt") != null)
				cCampo = new String(request.getParameter("cLicenciaInt")
						.getBytes("ISO-8859-1"), "UTF-8");
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

			if (request.getParameter("cExpediente") != null)
				cCampo = new String(request.getParameter("cExpediente")
						.getBytes("ISO-8859-1"), "UTF-8");
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

			if (request.getParameter("cCalle") != null)
				cCampo = new String(request.getParameter("cCalle").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCalle(cCampo);

			if (request.getParameter("cNumExt") != null)
				cCampo = new String(request.getParameter("cNumExt").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNumExt(cCampo);

			if (request.getParameter("cNumInt") != null)
				cCampo = new String(request.getParameter("cNumInt").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNumInt(cCampo);

			if (request.getParameter("cColonia") != null)
				cCampo = new String(request.getParameter("cColonia").getBytes(
						"ISO-8859-1"), "UTF-8");
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

			if (request.getParameter("cCiudad") != null)
				cCampo = new String(request.getParameter("cCiudad").getBytes(
						"ISO-8859-1"), "UTF-8");
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

			cCampo = "" + request.getParameter("iCveLocalidad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveLocalidad(iCampo);

			
			if (request.getParameter("cTelefono") != null)
				cCampo = new String(request.getParameter("cTelefono").getBytes(
						"ISO-8859-1"), "UTF-8");
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

			if (request.getParameter("cObservacion") != null)
				cCampo = new String(request.getParameter("cObservacion")
						.getBytes("ISO-8859-1"), "UTF-8");
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

			// DATOS ADICIONALES
			/*
			 * if(request.getParameter("cTelefono2")!= null) cCampo = new
			 * String(
			 * request.getParameter("cTelefono2").getBytes("ISO-8859-1"),"UTF-8"
			 * ); if (cCampo.compareTo("null") == 0) { cCampo = ""; }
			 * vEPICisCita.setCTelefono2(cCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveVivienda"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vEPICisCita.setICveVivienda(iCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveDiscapacidad"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vEPICisCita.setICveDiscapacidad(iCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveGpoEtnico"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vEPICisCita.setICveGpoEtnico(iCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveReligiones"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vEPICisCita.setICveReligion(iCampo);
			 * 
			 * 
			 * cCampo = "" + request.getParameter("iCveNivelSEC"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vEPICisCita.setICveNivelSEC(iCampo);
			 * 
			 * 
			 * cCampo = "" + request.getParameter("iCveParPol"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vEPICisCita.setICveParPOL(iCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveECivil"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vEPICisCita.setICveECivil(iCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveTpoSangre"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vEPICisCita.setICveTpoSangre(iCampo);
			 */

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEPICisCita;
	}

	/**
	 * Validando que el Expediente no esta inhabilitado
	 */
	public int getInhabilitado(String Expediente) {
		TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
		Boolean Inhabilitado = false;
		int Activo = 0;
		try {
			String cCondicion = " M1.iCvePersonal = " + Expediente;
			Inhabilitado = dMEDInhabilita.Inhabilitado(cCondicion);
			if (Inhabilitado) {
				Activo = 1;
			}
		} catch (Exception e) {
			Activo = 0;
			e.printStackTrace();
		}
		return Activo;
	}
	
	
	/**
	 * Validando que el Usuario sea Medico Tercero
	 */
	public boolean getBloqueaGeneraCita(String Usuario, int iCveUsuario) {
		boolean Bloquear = false;
		Usuario = Usuario.toLowerCase();
		TDSEGUsuExp dSEGUsuExp = new TDSEGUsuExp();
		try {
			//System.out.println(Usuario.substring(0, 3));
			if(Usuario.substring(0, 3).toString().equals("ext")){
				Bloquear = true;
			}
			if(Bloquear){
				Bloquear = dSEGUsuExp.FindByEsTercero(iCveUsuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Bloquear;
	}

}