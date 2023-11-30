package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.naming.*;
import javax.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.sql.*;
import com.micper.ingsw.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import com.micper.util.logging.*;
import com.micper.util.*;
import com.micper.sql.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.StringTokenizer;

/**
 * * Clase de configuracion para EXAM Aplica
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>L.A.S.
 */
public class pg070107050CFG extends CFGListBase2 {
	public pg070107050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * M�todo Guardar
	 */
	public void Guardar() {

		// //System.out.println("Guardar cfg");
		// //System.out.println("expediente = " +
		// request.getParameter("iCveExpediente"));
		// //System.out.println("examen = " +
		// request.getParameter("hdInumExamen") + " -- "
		// +request.getParameter("inumExamen"));
		// //System.out.println("nueva fecha = " +
		// request.getParameter("hdBuscado"));
		// System.out.println("nueva fecha2 = " +
		// request.getParameter("dtSolicitado"));

		String expediente = "";
		expediente = request.getParameter("iCveExpediente");
		String examen = "";
		examen = request.getParameter("hdInumExamen");
		String fecha = "";
		fecha = request.getParameter("dtSolicitado");

		// System.out.println(fecha);

		String fechaExamen2 = fecha + "/";
		String dia;
		String mes;
		String ano;
		StringTokenizer solDatos = new StringTokenizer(fechaExamen2, "/");
		ano = solDatos.nextToken();
		dia = solDatos.nextToken();
		mes = solDatos.nextToken();
		fecha = "" + mes + "-" + dia + "-" + ano;

		// System.out.println(fecha);

		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		String regresa = "";

		try {
			regresa = dEXPExamAplica.updateDesbloquear(expediente, examen,
					fecha);
		} catch (Exception ex) {
			error("Error al realizar la actualizacion", ex);
		}

		/*
		 * Calcula Fecha Actual
		 */
		/*
		 * java.sql.Date dtCampo; TFechas tfCampo = new TFechas();
		 * java.util.Date today = new java.util.Date(); java.sql.Date defFecha =
		 * new java.sql.Date(today.getTime()); java.sql.Date defaultFecha = new
		 * java.sql.Date(today.getTime()); String dFechaActual = ""; TFechas
		 * dtFecha = new TFechas(); dFechaActual =
		 * dtFecha.getFechaDDMMYYYY(defaultFecha, "/"); String cCampo =
		 * dFechaActual; dtCampo = tfCampo.getDateSQL(cCampo);
		 * 
		 * TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica(); try {
		 * 
		 * 
		 * } catch (Exception ex) { vErrores.acumulaError("", 14, "");
		 * error("Error al insertar el registro", ex); } finally {
		 * super.Guardar(); }
		 */} // M�todo Guardar

	/**
	 * M�todo GuardarA
	 */
	public void GuardarA() {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		// //System.out.println("GuardarA");
		/*
		 * try { cClave = (String) dEXPExamAplica.update(null,
		 * this.getInputs()); } catch (Exception ex) { vErrores.acumulaError("",
		 * 14, ""); error("Error al actualizar el registro", ex); } finally {
		 * super.GuardarA(); }
		 */
	} // M�todo GuardarA

	/**
	 * M�todo BorrarB
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
	} // M�todo BorrarB

	/**
	 * M�todo FillVector
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
	 * M�todo findUser
	 */
	public TVEXPExamAplica findUser() {
		TVEXPExamAplica vEXPExamAplica = null;
		try {
			String cUserId = request.getParameter("iCveExpediente");
			String cNumExa = request.getParameter("iNumExamen");

			// //System.out.println("op1 "
			// +request.getParameter("iCveExpediente"));
			// //System.out.println("op2 " +cUserId);

			if (cUserId == null) {
				cUserId = "0";
			}
			int iCvePersonal = Integer.parseInt(cUserId);

			vEXPExamAplica = new TDEXPExamAplica().FindByAll(iCvePersonal);
			System.out.print("findUser");
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vEXPExamAplica;
	}

	/**
	 * M�todo getEdad
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
	 * M�todo findExpediente
	 */
	public TVEXPExamAplica findExpediente() {
		TVEXPExamAplica vEXPExamAplica = null;
		try {
			String cUserId = request.getParameter("iCveExpediente");

			// //System.out.println("op1 "
			// +request.getParameter("iCveExpediente"));
			// //System.out.println("op2 " +cUserId);

			if (cUserId == null) {
				cUserId = "0";
			}
			int iCvePersonal = Integer.parseInt(cUserId);

			vEXPExamAplica = new TDEXPExamAplica().FindByAll(iCvePersonal);
			System.out.print("findExpediente");
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vEXPExamAplica;
	}

	/**
	 * M�todo FillPK
	 */
	// public void FillPK(){
	// mPk.add(cActual);
	// }
	/**
	 * M�todo getInputs
	 */
	public Object getInputs() throws CFGException {
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

		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}

			vEXPExamAplica.setICveExpediente(iCampo);

			cCampo = "" + request.getParameter("iNumExamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setINumExamen(iCampo);

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

			cCampo = "" + request.getParameter("dtSolicitado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtSolicitado(dtCampo);

			cCampo = "" + request.getParameter("iFolioEs");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setIFolioEs(iCampo);

			cCampo = "" + request.getParameter("dtAplicacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			// vEXPExamAplica.setDtAplicacion(dtCampo);
			vEXPExamAplica.setDtAplicacion(tfCampo.TodaySQL());

			cCampo = "" + request.getParameter("dtConcluido");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtConcluido(dtCampo);

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

			cCampo = "" + request.getParameter("dtArchivado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}

			vEXPExamAplica.setDtDictamen(dtCampo);

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

			cCampo = "" + request.getParameter("iCveNumEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveNumEmpresa(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPExamAplica;
	}
}


