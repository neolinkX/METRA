package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;
import com.micper.sql.*;

/**
 * * Clase de configuracion para Listado de TOXCtrolCalibra
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. Gonz�lez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070303060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070103040CFG.png'>
 */
public class pg070203010CFG extends CFGListBase2 {
	public pg070203010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		String cWhere = "";
		String cClave = "";
		String cActualiza = "";
		String cFecha = "";
		int iUser = 0;
		try {
			HashMap hmParams = getParameters();

			TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
			if (request.getParameter("hdICvePersonal") != null) {
				vDespliega = dEXPExamAplica
						.FindByAll("where EXPExamAplica.iCveExpediente = "
								+ request.getParameter("hdICvePersonal")
								+ " and EXPExamAplica.icveproceso = 2 ");
			}
			/*
			 * TDEPICisCita dEPICisCita = new TDEPICisCita(); if
			 * (request.getParameter("hdBoton").compareTo("Expediente") == 0) {
			 * 
			 * if (hmParams.get("iCveUniMed") == null) {
			 * hmParams.put("iCveUniMed", "-1"); }
			 * 
			 * 
			 * cWhere = "where EPICisCita.iCveUniMed = " +
			 * request.getParameter("hdCveUniMed") + " "; cWhere = cWhere +
			 * "and EPICisCita.iCveModulo = " +
			 * request.getParameter("hdCveModulo") + " "; cWhere = cWhere +
			 * "and EPICisCita.dtFecha = '" + request.getParameter("hdFecha") +
			 * "' "; cWhere = cWhere + "and EPICisCita.iCveCita = " +
			 * request.getParameter("hdCveCita") + " ";
			 * 
			 * iUser = Integer.parseInt(request.getParameter("hdUsuario"));
			 * 
			 * cActualiza = " where iCveUniMed = " +
			 * request.getParameter("hdCveUniMed") + " " + "and dtFecha =  '" +
			 * request.getParameter("hdFecha") + "' " + "and iCveCita =  " +
			 * request.getParameter("hdCveCita") + " " + "and iCveModulo = " +
			 * request.getParameter("hdCveModulo") + " ";
			 * 
			 * dEPICisCita.AltaPersonal(cWhere,iUser,cActualiza); vDespliega =
			 * new TDEPICisCita().FindConsultaCitas(hmParams);
			 * 
			 * } else { if (hmParams.get("iCveUniMed") == null) {
			 * hmParams.put("iCveUniMed", "-1"); } vDespliega = new
			 * TDEPICisCita().FindConsultaCitas(hmParams); }
			 */
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo getUniMedsValidas
	 */
	public Vector getUniMedsValidas() {
		Vector vcUMValidas = null;
		try {
			TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true)
					.getAttribute("UsrID");
			vcUMValidas = new TDGRLUMUsuario().getUniMedxUsu(vUsuario
					.getICveusuario());
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcUMValidas;
	}

	/**
	 * Metodo getModulos
	 */
	public Vector getModulos() {
		Vector vcModulos = null;
		try {
			String cTmp = request.getParameter("iCveUniMed");
			if (cTmp != null) {
				vcModulos = new TDGRLModulo().getComboModulos(Integer
						.parseInt(cTmp));
			}
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcModulos;
	}

	/**
	 * Metodo getModulos
	 */
	public Vector getModulos(String cCveUniMed) {
		Vector vcModulos = null;
		try {
			if (cCveUniMed != null) {
				vcModulos = new TDGRLModulo().getComboModulos(Integer
						.parseInt(cCveUniMed));
			}
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcModulos;
	}

	/**
	 * Metodo getHorasDeCitas
	 */
	public Vector getHorasDeCitas() {
		Vector vcHoras = null;
		try {
			HashMap hmParams = getParameters();
			if (hmParams.get("iCveUniMed") == null) {
				hmParams.put("iCveUniMed", "-1");
			}
			vcHoras = new TDEPICisCita().getHorasDeCitas(hmParams);
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcHoras;
	}

	/**
	 * Metodo getHorasDeCitas
	 */
	public Vector getHorasDeCitas(String cCveUniMed, String cCveModulo,
			String cFecha) {
		Vector vcHoras = null;
		try {
			HashMap hmParams = new HashMap();
			hmParams.put("iCveUniMed", cCveUniMed);
			hmParams.put("iCveModulo", cCveModulo);
			hmParams.put("dtFecha", cFecha);
			if (hmParams.get("iCveUniMed") == null) {
				hmParams.put("iCveUniMed", "-1");
			}
			vcHoras = new TDEPICisCita().getHorasDeCitas(hmParams);
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcHoras;
	}

	/**
	 * Metodo getParameters
	 */
	private HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		hmTmp.put("iCveUniMed", request.getParameter("iCveUniMed"));
		hmTmp.put("iCveModulo", request.getParameter("iCveModulo"));
		hmTmp.put("dtFecha", request.getParameter("dtFecha"));
		hmTmp.put("cHora", request.getParameter("cHora"));

		return hmTmp;
	}
}