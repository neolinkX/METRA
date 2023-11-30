package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070304090CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vLoteCuantita = new Vector();
	public Vector vCtrolCalibra = new Vector();
	public Vector vEQMEquipo = new Vector();
	public Vector vDespliega2 = new Vector();
	public Vector vTOXCalibEquipo = new Vector();
	public boolean lModifica = false;
	String cvAnio = "";
	int ivSustancia = 0;
	int ivCveLaboratorio = 0;
	int ivLoteCuantita = 0;
	int ivLoteCualita = 0;
	int ivEquipo = 0;
	int ivValidacion = 0;
	java.sql.Date dtvFechaInicial;

	// TVUsuario vUsuario = (TVUsuario)
	// httpReq.getSession().getAttribute("UsrID");

	public pg070304090CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {
		TFechas Fecha = new TFechas();
		TDTOXLoteCuantita dLoteCuantita = new TDTOXLoteCuantita();
		int ivDias = new Integer(
				vParametros.getPropEspecifica("TOXDiasCalibracion")).intValue()
				* (-1);

		dtvFechaInicial = Fecha.aumentaDisminuyeDias(Fecha.TodaySQL(), ivDias);

		if (request.getParameter("SLSAnio") != null)
			cvAnio = request.getParameter("SLSAnio");

		if (cvAnio.compareToIgnoreCase("") == 0)
			cvAnio = "2004";

		if (request.getParameter("iCveUniMed") != null)
			ivCveLaboratorio = new Integer(request.getParameter("iCveUniMed"))
					.intValue();

		if (request.getParameter("SLSSustancia") != null)
			ivSustancia = new Integer(request.getParameter("SLSSustancia")
					.toString()).intValue();

		if (request.getParameter("SLSLoteConfirmativo") != null)
			ivLoteCuantita = new Integer(request.getParameter(
					"SLSLoteConfirmativo").toString()).intValue();

		if (request.getParameter("SLSEqControl") != null)
			ivEquipo = new Integer(request.getParameter("SLSEqControl")
					.toString()).intValue();

		if (request.getParameter("TBXValidacion") != null)
			ivValidacion = new Integer(request.getParameter("TBXValidacion")
					.toString()).intValue();

		try {

			vLoteCuantita = dLoteCuantita
					.FindByAll(" where TOXLoteCuantita.iAnio           =           "
							+ cvAnio
							+ "   and TOXLoteCuantita.iCveLaboratorio =           "
							+ ivCveLaboratorio
							+ "   and TOXLoteCuantita.iCveSustancia   =           "
							+ ivSustancia
							+ "   and TOXLoteCuantita.dtGeneracion    is not null "
							+
							// "   and TOXLoteCuantita.dtAnalisis      is null     "
							// +
							// "   and TOXLoteCuantita.dtAutorizacion  is null     "
							// +
							// "   and TOXLoteCuantita.dtCalibracion   is null     "
							// +
							// "   and TOXLoteCuantita.lValidaCalib    is null     "
							// +
							" ");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void Guardar() {
		Vector VDatos = new Vector();
		TVTOXCuantAnalisis VAnalisis;
		TDTOXCuantAnalisis DCuantAnalisis = new TDTOXCuantAnalisis();
		try {
			// Buscar el vector a guardar
			this.fillVector();
			VDatos = this.vDespliega2;
			for (int i = 0; i < VDatos.size(); i++) {
				VAnalisis = new TVTOXCuantAnalisis();
				VAnalisis = (TVTOXCuantAnalisis) VDatos.get(i);
				String cValor = "iCveCtrol"
						+ VAnalisis.getiCveAnalisis().toString();
				if (request.getParameter(cValor) != null) {
					VAnalisis.setiCveCtrolCalibra(new Integer(request
							.getParameter(cValor).toString()));
				}
				DCuantAnalisis.updCalibracion(VAnalisis);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void fillVector() {
		cPaginas = "pg070304051.jsp|";
		TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();
		try {
			vDespliega = DTOXCuantAnalisis
					.FindCalibra2(" where L.iAnio            = " + cvAnio
							+ "   and L.iCveLaboratorio  = " + ivCveLaboratorio
							+ "   and L.iCveSustancia    = " + ivSustancia
							+ "   and L.iCveLoteCuantita = " + ivLoteCuantita);
			vDespliega2 = vDespliega;
			if (vDespliega2.size() > 0)
				lModifica = ((TVTOXCuantAnalisis) vDespliega2.get(0))
						.getlAutorizado() == null ? true : false;
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			if (lModifica) {
				UpdStatus = "SaveCancelOnly";
				NavStatus = "Disabled";
				cImprimir = "Imprimir";
			} else {
				UpdStatus = "Hidden";
				NavStatus = "Disabled";
				cImprimir = "Imprimir";
			}
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
			cImprimir = "Imprimir";
		}

	}
}