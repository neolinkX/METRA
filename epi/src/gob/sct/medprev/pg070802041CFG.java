package gob.sct.medprev;

import java.util.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.excepciones.*;

public class pg070802041CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";

	public Vector vAlmacenes = new Vector();
	public Vector vALMTpoPrioridad = new Vector();
	public Vector vALMPeriodo = new Vector();
	public Vector vALMSolciSum = new Vector();

	public TVALMAlmacen tvAlmacen = new TVALMAlmacen();
	public TVALMTpoPrioridad tvPrioridad = new TVALMTpoPrioridad();
	public TVALMPeriodo tvPeriodo = new TVALMPeriodo();
	public TVALMSolicSumin tvSolicSum = new TVALMSolicSumin();

	String cvAnio = "";
	String ivAlmacen = "";
	String ivPrioridad = "";
	String ivPeriodo = "";
	String ivSolicSum = "";

	public pg070802041CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {

		TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
		TDALMTpoPrioridad dALMTpoPrioridad = new TDALMTpoPrioridad();
		TDALMPeriodo dALMPeriodo = new TDALMPeriodo();
		TDALMSolicSumin dALMSolicSum = new TDALMSolicSumin();

		if (request.getParameter("iCveAlmacen") != null
				&& !request.getParameter("iCveAlmacen").equals(""))
			ivAlmacen = request.getParameter("iCveAlmacen").toString();
		if (request.getParameter("iCveTpoPrioridad") != null
				&& !request.getParameter("iCveTpoPrioridad").equals(""))
			ivPrioridad = request.getParameter("iCveTpoPrioridad").toString();
		if (request.getParameter("iCvePeriodo") != null
				&& !request.getParameter("iCvePeriodo").equals(""))
			ivPeriodo = request.getParameter("iCvePeriodo").toString();
		if (request.getParameter("hdCampoClave3") != null
				&& !request.getParameter("hdCampoClave3").equals(""))
			ivSolicSum = request.getParameter("hdCampoClave3").toString();
		if (request.getParameter("hdCampoClave") != null
				&& !request.getParameter("hdCampoClave").equals(""))
			cvAnio = request.getParameter("hdCampoClave").toString();

		// Si no se selecciono prioridad ni periodo traerlos de la tabla
		if (ivPrioridad.equals("") || ivPrioridad.equals("0")
				|| ivPeriodo.equals("") || ivPeriodo.equals("0")) {
			if (request.getParameter("hdCampoClave") != null
					&& !request.getParameter("hdCampoClave").equals("")
					&& request.getParameter("hdCampoClave2") != null
					&& !request.getParameter("hdCampoClave2").equals("")
					&& request.getParameter("hdCampoClave3") != null
					&& !request.getParameter("hdCampoClave3").equals("")) {
				String q = " where S.iAnio="
						+ request.getParameter("hdCampoClave")
						+ " and S.iCveAlmacen="
						+ request.getParameter("hdCampoClave2")
						+ " and S.iCveSolicSum="
						+ request.getParameter("hdCampoClave3");
				try {
					vALMSolciSum = dALMSolicSum.FindByAllCustom(q, "");
					if (vALMSolciSum.size() > 0) {
						tvSolicSum = (TVALMSolicSumin) vALMSolciSum.get(0);
						ivPrioridad = "" + tvSolicSum.getICvePrioridad();
						ivPeriodo = "" + tvSolicSum.getICvePeriodo();
					}
				} catch (DAOException ex1) {
					ex1.printStackTrace();
				}
			}
		}

		// Vector y tv de los Almacenes.
		try {
			vAlmacenes = DALMAlmacen
					.FindByCustomWhere(" where lActivo = 1 and ALMAlmacen.iCveAlmacen="
							+ ivAlmacen);
			if (vAlmacenes.size() > 0)
				tvAlmacen = (TVALMAlmacen) vAlmacenes.get(0);
			else
				tvAlmacen.setCDscAlmacen("&nbsp;");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector y tv de los Tipos de Prioridad
		try {
			vALMTpoPrioridad = dALMTpoPrioridad.FindByAll(
					" where iCvePrioridad=" + ivPrioridad, "");
			if (vALMTpoPrioridad.size() > 0)
				tvPrioridad = (TVALMTpoPrioridad) vALMTpoPrioridad.get(0);
			else
				tvPrioridad.setCDscPrioridad("&nbsp;");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector y tv de los Periodos
		try {
			vALMPeriodo = dALMPeriodo.FindByAll(
					" where lActivo = 1 and iAnio = " + cvAnio
							+ " and iCvePeriodo=" + ivPeriodo,
					" Order by iCvePeriodo");
			if (vALMPeriodo.size() > 0)
				tvPeriodo = (TVALMPeriodo) vALMPeriodo.get(0);
			else
				tvPeriodo.setCDscPeriodo("&nbsp;");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void fillVector() {
		TDALMSumArt dALMSumArt = new TDALMSumArt();

		cPaginas = "pg070802040.jsp|";

		try {
			String cFiltro = "";
			if (!cvAnio.equals("") && !ivAlmacen.equals("")
					&& !ivSolicSum.equals("")) {
				cFiltro = " where AlmSumArt.iAnio=" + cvAnio
						+ " and AlmSumArt.iCveAlmacen=" + ivAlmacen
						+ " and AlmSumArt.iCveSolicSum=" + ivSolicSum;
				if (!cCondicion.equals(""))
					cFiltro += " and " + cCondicion;
			}
			if (!cFiltro.equals("")) {
				vDespliega = dALMSumArt.FindByAllSolicSumin(cFiltro, cOrden);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		if (!vDespliega.isEmpty()) {
			UpdStatus = "Hidden";
			cImprimir = "Imprimir";
			if (vDespliega.size() > iNumReg)
				NavStatus = "FirstRecord";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0) {
				NavStatus = "FirstRecord";
			}
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}
	}
}