package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.sql.*;

public class pg070603032CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public boolean lGuardar = true;
	int ivEquipo = 0;
	int ivMantenimiento = 0;
	public Vector vEquipos = new Vector();
	public Vector vMdoTrans = new Vector();
	public Vector vPeriodPla = new Vector();
	public Vector vPlantilla = new Vector();
	public Vector vEtapa = new Vector();
	public Vector vSolicitante = new Vector();

	public pg070603032CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070602013.jsp|pg070603021.jsp|pg070603030.jsp|pg070603031.jsp|pg070604010.jsp|";
	}

	public void mainBlock() {
		if (request.getParameter("hdIni") != null)
			if (request.getParameter("hdIni").toString()
					.compareToIgnoreCase("") != 0)
				ivEquipo = new Integer(request.getParameter("hdIni"))
						.intValue();
		if (request.getParameter("hdIni2") != null)
			if (request.getParameter("hdIni2").toString()
					.compareToIgnoreCase("") != 0)
				ivMantenimiento = new Integer(request.getParameter("hdIni2"))
						.intValue();
	}

	public void Guardar() {
		TDEQMSeguimiento DEQMSeguimiento = new TDEQMSeguimiento();
		TVEQMSeguimiento VEQMSeguimiento = new TVEQMSeguimiento();
		TFechas Fecha = new TFechas();
		java.sql.Date dtSolicitud;

		VEQMSeguimiento.setICveEquipo(ivEquipo);
		VEQMSeguimiento.setICveMantenimiento(ivMantenimiento);
		VEQMSeguimiento.setDtSolicitud(Fecha.TodaySQL());
		VEQMSeguimiento.setICveProceso(6); // Proceso de Calibración de Equipo.
		if (request.getParameter("dtSolicitud") != null)
			VEQMSeguimiento.setDtSolicitud(Fecha.getDateSQL(request
					.getParameter("dtSolicitud")));
		if (request.getParameter("SLSSolicitante") != null)
			VEQMSeguimiento.setICveSolicitante(new Integer(request
					.getParameter("SLSSolicitante")).intValue());
		if (request.getParameter("hdExterno") != null) {
			if (request.getParameter("hdExterno").toString()
					.compareToIgnoreCase("1") == 0) {
				if (request.getParameter("TXTSolicitante") != null)
					VEQMSeguimiento.setCSolicitante(request
							.getParameter("TXTSolicitante"));
			}
		}
		if (request.getParameter("SLSEtapa") != null)
			VEQMSeguimiento.setICveEtapa(new Integer(request
					.getParameter("SLSEtapa")).intValue());
		if (request.getParameter("TXTObservacion") != null)
			VEQMSeguimiento.setCObservacion(request
					.getParameter("TXTObservacion"));
		if (request.getParameter("SLSUsuReg") != null)
			VEQMSeguimiento.setICveUsuReg(new Integer(request
					.getParameter("SLSUsuReg")).intValue());
		if (request.getParameter("SLSUsrSolicita") != null) {
			if (request.getParameter("SLSUsrSolicita").toString()
					.compareToIgnoreCase("") != 0)
				VEQMSeguimiento.setICveUsuSolicita(new Integer(request
						.getParameter("SLSUsrSolicita")).intValue());
			else {
				if (request.getParameter("SLSUsuReg") != null)
					VEQMSeguimiento.setICveUsuSolicita(new Integer(request
							.getParameter("SLSUsuReg")).intValue());
			}
		}

		// Agrega el Seguimiento de la Plantilla.
		try {
			DEQMSeguimiento.insert(null, VEQMSeguimiento);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void fillVector() {

		TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
		TDEQMEquipo DEQMEquipo = new TDEQMEquipo();
		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		TDCTRPeriodPla DCTRPeriodPla = new TDCTRPeriodPla();
		TDEQMSeguimiento DEQMSeguimiento = new TDEQMSeguimiento();
		TDGRLEtapa DGRLEtapa = new TDGRLEtapa();
		TDGRLSolicitante DGRLSolicitante = new TDGRLSolicitante();
		Vector vFiltro = new Vector();
		int ivEtapaIni = 0;
		ivEtapaIni = Integer.parseInt(vParametros
				.getPropEspecifica("EQMEtapaInicio"));
		int ivEtapa = 0;
		ivEtapa = Integer.parseInt(vParametros
				.getPropEspecifica("EQMEtapaFinal"));

		try {
			vEquipos = DEQMEquipo.FindByAll(" where iCveEquipo = " + ivEquipo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vMdoTrans = DGRLMdoTrans.findByAll("");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vPeriodPla = DCTRPeriodPla.FindByAll("");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vPlantilla = DCTRPlantilla.FindByAll(
					" where CTRPlantilla.iCveEmpresa   = " + ivEquipo
							+ "   and CTRPlantilla.iCvePlantilla = "
							+ ivMantenimiento, "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			vEtapa = DGRLEtapa.FindByAll(" where iCveProceso  = 6 "
					+ "   and iCveEtapa   <>   " + ivEtapaIni
					+ "   and iCveEtapa   <>   " + ivEtapa
					+ "   and lActivo      =1 ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vSolicitante = DGRLSolicitante
					.FindByAll(" where lActivo = 1 order by cDscSolicitante");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (cCondicion.compareToIgnoreCase("") != 0)
			cCondicion = " and " + cCondicion;

		if (cOrden.compareTo("") != 0) {
			if (cOrden.compareToIgnoreCase("order by iCveEmpresa") == 0
					|| cOrden
							.compareToIgnoreCase(" order by CTRPlantilla.iCvePlantilla") == 0
					|| cOrden.compareToIgnoreCase(" order by cModelo") == 0)
				cCondicion = cCondicion + " order by iCveMovimiento ";
			else
				cCondicion = cCondicion + cOrden;
		} else
			cCondicion = cCondicion + " order by iCveMovimiento ";

		// Vector del Listado.
		try {
			vDespliega = DEQMSeguimiento.FindByAll(" where iCveEquipo    =   "
					+ ivEquipo + "   and iCveMantenimiento =   "
					+ ivMantenimiento + "   and iCveProceso   = 6 "
					+ cCondicion);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			// Buscando la Etapa de Finalización.
			vFiltro = vDespliega;
			if (!vFiltro.isEmpty()) {
				for (int i = 0; i < vFiltro.size(); i++) {
					TVEQMSeguimiento VEQMSeguimiento = new TVEQMSeguimiento();
					VEQMSeguimiento = (TVEQMSeguimiento) vFiltro.get(i);
					if (VEQMSeguimiento.getICveEtapa() == ivEtapa) {
						lGuardar = false;
					}
				}
			}

			if (lGuardar)
				UpdStatus = "SaveOnly";
			else
				UpdStatus = "Hidden";
			cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "SaveOnly";
			NavStatus = "Disabled";
		}
	}

	public boolean getlGuardar() {
		return lGuardar;
	}
}