package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070502030CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	int ivUniMed = 0;
	int ivMdoTransporte = 0;
	int ivEmpresa = 0;
	public Vector vMdoTrans = new Vector();

	public pg070502030CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070502031.jsp|";
		NavStatus = "Disabled";
	}

	public void mainBlock() {
		if (request.getParameter("SLSUniMed") != null)
			if (request.getParameter("SLSUniMed").toString()
					.compareToIgnoreCase("") != 0)
				ivUniMed = new Integer(request.getParameter("SLSUniMed"))
						.intValue();

		if (request.getParameter("SLSMdoTransporte") != null)
			if (request.getParameter("SLSMdoTransporte").toString()
					.compareToIgnoreCase("") != 0)
				ivMdoTransporte = new Integer(
						request.getParameter("SLSMdoTransporte")).intValue();

		if (request.getParameter("SLSEmpresas") != null)
			if (request.getParameter("SLSEmpresas").toString()
					.compareToIgnoreCase("") != 0)
				ivEmpresa = new Integer(request.getParameter("SLSEmpresas"))
						.intValue();
	}

	public void fillVector() {

		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		String cCond2 = "";

		// Obtención del Modo de Transporte
		try {
			TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
			VGRLMdoTrans.setICveMdoTrans(0);
			VGRLMdoTrans.setCDscMdoTrans("Todos");
			vMdoTrans = DGRLMdoTrans.findByAll("");
			vMdoTrans.addElement(VGRLMdoTrans);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		cPaginas = "pg070502030.jsp|";

		if (ivMdoTransporte > 0) {
			cCond2 = " and iCveMdoTrans = " + ivMdoTransporte;
		}

		if (cCondicion.compareToIgnoreCase("") != 0)
			cCondicion = " and " + cCondicion;

		if (cOrden.compareTo("") != 0)
			cCondicion = cCondicion + cOrden;
		else
			cCondicion = cCondicion + " order by cIDDGPMPT ";

		try {
			if (ivUniMed > 0)
				vDespliega = DGRLEmpresas.FindByAll(" where iCveUniMed = "
						+ ivUniMed + cCond2 + cCondicion);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "Hidden";
			cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}
	}
}