package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.sql.*;

public class pg070502033CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public boolean lGuardar = true;
	int ivEmpresa = 0;
	int ivPlantilla = 0;
	int vMdoTransporte = 1;
	public Vector vEmpresas = new Vector();
	public Vector vMdoTrans = new Vector();
	public Vector vPeriodPla = new Vector();
	public Vector vPlantilla = new Vector();
	public Vector vEtapa = new Vector();
	public Vector vSolicitante = new Vector();
	private StringBuffer activeX = new StringBuffer("");

	public pg070502033CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070502032.jsp";
	}

	public void mainBlock() {
		if (request.getParameter("hdIni") != null)
			if (request.getParameter("hdIni").toString()
					.compareToIgnoreCase("") != 0)
				ivEmpresa = new Integer(request.getParameter("hdIni"))
						.intValue();
		if (request.getParameter("hdIni2") != null)
			if (request.getParameter("hdIni2").toString()
					.compareToIgnoreCase("") != 0)
				ivPlantilla = new Integer(request.getParameter("hdIni2"))
						.intValue();

		if (cAccion.compareToIgnoreCase("Generar") == 0)
			this.Documento();

	}

	public void Guardar() {
		TDCTRSeguimiento DCTRSeguimiento = new TDCTRSeguimiento();
		TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
		TFechas Fecha = new TFechas();
		java.sql.Date dtSolicitud;

		VCTRSeguimiento.setiCveEmpresa(ivEmpresa);
		VCTRSeguimiento.setiCvePlantilla(ivPlantilla);
		VCTRSeguimiento.setdtSolicitud(Fecha.TodaySQL());
		VCTRSeguimiento.setiCveProceso(5); // Proceso de Control al Transporte.
		if (request.getParameter("dtSolicitud") != null)
			VCTRSeguimiento.setdtSolicitud(Fecha.getDateSQL(request
					.getParameter("dtSolicitud")));
		if (request.getParameter("SLSSolicitante") != null)
			VCTRSeguimiento.setiCveSolictante(new Integer(request
					.getParameter("SLSSolicitante")).intValue());
		if (request.getParameter("hdExterno") != null) {
			if (request.getParameter("hdExterno").toString()
					.compareToIgnoreCase("1") == 0) {
				if (request.getParameter("TXTSolicitante") != null)
					VCTRSeguimiento.setcSolicitante(request
							.getParameter("TXTSolicitante"));
			}
		}
		if (request.getParameter("SLSEtapa") != null)
			VCTRSeguimiento.setiCveEtapa(new Integer(request
					.getParameter("SLSEtapa")).intValue());
		if (request.getParameter("TXTObservacion") != null)
			VCTRSeguimiento.setcObservacion(request
					.getParameter("TXTObservacion"));
		if (request.getParameter("cNoOficio") != null)
			VCTRSeguimiento.setcNoOficio(request.getParameter("cNoOficio"));
		if (request.getParameter("SLSUsuReg") != null)
			VCTRSeguimiento.setiCveUsuReg(new Integer(request
					.getParameter("SLSUsuReg")).intValue());
		if (request.getParameter("SLSUsrSolicita") != null) {
			if (request.getParameter("SLSUsrSolicita").toString()
					.compareToIgnoreCase("") != 0)
				VCTRSeguimiento.setiCveUsuSolicita(new Integer(request
						.getParameter("SLSUsrSolicita")).intValue());
			else {
				if (request.getParameter("SLSUsuReg") != null)
					VCTRSeguimiento.setiCveUsuSolicita(new Integer(request
							.getParameter("SLSUsuReg")).intValue());
			}
		}

		// Agrega el Seguimiento de la Plantilla.
		try {
			DCTRSeguimiento.insert(null, VCTRSeguimiento);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void fillVector() {

		TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		TDCTRPeriodPla DCTRPeriodPla = new TDCTRPeriodPla();
		TDCTRSeguimiento DCTRSeguimiento = new TDCTRSeguimiento();
		TDGRLEtapa DGRLEtapa = new TDGRLEtapa();
		TDGRLSolicitante DGRLSolicitante = new TDGRLSolicitante();
		Vector vFiltro = new Vector();
		int ivEtapaIni = 0;
		ivEtapaIni = Integer.parseInt(vParametros
				.getPropEspecifica("CTREtapaInicial"));
		int ivEtapa = 0;
		ivEtapa = Integer.parseInt(vParametros
				.getPropEspecifica("CTREtapaFinal"));

		try {
			vEmpresas = DGRLEmpresas.FindByAll(" where iCveEmpresa = "
					+ ivEmpresa);
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
					" where CTRPlantilla.iCveEmpresa   = " + ivEmpresa
							+ "   and CTRPlantilla.iCvePlantilla = "
							+ ivPlantilla, "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			vEtapa = DGRLEtapa.FindByAll(" where iCveProceso  = 5 " +
			// "   and iCveEtapa   <>   " + ivEtapaIni +
			// "   and iCveEtapa   <>   " + ivEtapa +
					"   and lActivo      =1 ");
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
					|| cOrden.compareToIgnoreCase(" order by cIDDGPMPT") == 0)
				cCondicion = cCondicion + " order by iCveMovimiento ";
			else
				cCondicion = cCondicion + cOrden;
		} else
			cCondicion = cCondicion + " order by iCveMovimiento ";

		// Vector del Listado.
		try {
			vDespliega = DCTRSeguimiento.FindByAll(" where iCveEmpresa   =   "
					+ ivEmpresa + "   and iCvePlantilla =   " + ivPlantilla
					+ "   and iCveProceso   = 5 " + cCondicion);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			// Buscando la Etapa de Finalización.
			vFiltro = vDespliega;
			if (!vFiltro.isEmpty()) {
				for (int i = 0; i < vFiltro.size(); i++) {
					TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
					VCTRSeguimiento = (TVCTRSeguimiento) vFiltro.get(i);
					if (VCTRSeguimiento.getiCveEtapa() == ivEtapa) {
						lGuardar = false;
					}
				}
			}

			if (lGuardar)
				UpdStatus = "SaveCancelOnly";
			else
				UpdStatus = "Hidden";
			cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "SaveCancelOnly";
			NavStatus = "Disabled";
		}
	}

	public boolean getlGuardar() {
		return lGuardar;
	}

	public void Documento() {
		TExcel Rep1 = new TExcel("07");
		TDGRLEtapa DGRLEtapa = new TDGRLEtapa();
		TDCTRSeguimiento DCTRSeguimiento = new TDCTRSeguimiento();
		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		TDGRLUniMed DGRLUniMed = new TDGRLUniMed();
		TDCTRDomicilio DCTRDomicilio = new TDCTRDomicilio();
		TDEntidadFed DEntidadFed = new TDEntidadFed();
		Vector vEtapas = new Vector();
		Vector vSeguimiento = new Vector();
		Vector vEmpresas = new Vector();
		Vector vDomicilio = new Vector();
		Vector vUniMed = new Vector();
		Vector vEstado = new Vector();
		TDSEGUsuario DSEGUsuario = new TDSEGUsuario();
		TFechas Fecha = new TFechas("07");
		String cJefeDepto = "";
		Vector vSEGUsuario = new Vector();
		int ivMovimiento = 0;
		String cFecha = "";
		String cLugar = "";
		String cEmpresa = "";
		String cCalle = "";
		String cColonia = "";
		String cCiudad = "";
		String cCiudadUM = "";
		String cCP = "";
		String cAbrevEstado = "";
		String cPlantilla = "";
		String cCveEmpresa = "";
		String cObservaciones = "";
		String cUsuario = "";
		String cOficio = "";
		int ivMdoTransporte = 0;
		int ivEtapa = 0;
		int iviCveUniMed = 0;
		int iviCvePais = 0;
		int iviCveEstado = 0;
		int iviCveUsuario = 0;
		java.sql.Date dtFecha = null;
		String cDirectorGeneral = vParametros.getPropEspecifica(
				"DirectorGeneral").toString();

		if (request.getParameter("hdMovimiento") != null)
			ivMovimiento = new Integer(request.getParameter("hdMovimiento"))
					.intValue();

		try {
			vEtapas = DGRLEtapa
					.FindByAll(" where iCveProceso = 5 and lActivo = 1");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vSeguimiento = DCTRSeguimiento
					.FindByAll(" where CTRSeguimiento.iCveEmpresa    = "
							+ ivEmpresa
							+ "   and CTRSeguimiento.iCvePlantilla  = "
							+ ivPlantilla
							+ "   and CTRSeguimiento.iCveMovimiento = "
							+ ivMovimiento);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vEmpresas = DGRLEmpresas
					.FindByCustomWhere(" join CTRDomicilio on CTRDomicilio.iCveEmpresa = GRLEmpresas.iCveEmpresa "
							+ "                  and CTRDomicilio.lActivo     = 1 "
							+ " where GRLEmpresas.iCveEmpresa = " + ivEmpresa);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vDomicilio = DCTRDomicilio.FindByAll(
					" where CTRDomicilio.iCveEmpresa =    " + ivEmpresa
							+ "   and CTRDomicilio.lActivo     =  1 ", "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Valores del Seguimiento.
		for (int i = 0; i < vSeguimiento.size(); i++) {
			TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
			VCTRSeguimiento = (TVCTRSeguimiento) vSeguimiento.get(i);
			dtFecha = VCTRSeguimiento.getdtSolicitud();
			cFecha = ", a " + Fecha.getIntDay(VCTRSeguimiento.getdtSolicitud())
					+ " de "
					+ Fecha.getMonthName(VCTRSeguimiento.getdtSolicitud())
					+ " de "
					+ Fecha.getIntYear(VCTRSeguimiento.getdtSolicitud());
			ivEtapa = VCTRSeguimiento.getiCveEtapa();
			cObservaciones = VCTRSeguimiento.getcObservacion();
			iviCveUsuario = VCTRSeguimiento.getiCveUsuSolicita();
			cOficio = VCTRSeguimiento.getcNoOficio();
		}

		try {
			if (iviCveUsuario != 0)
				vSEGUsuario = DSEGUsuario.FindByAll(" where iCveUsuario = "
						+ iviCveUsuario);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (int i = 0; i < vSEGUsuario.size(); i++) {
			TVSEGUsuario VSEGUsuario = new TVSEGUsuario();
			VSEGUsuario = (TVSEGUsuario) vSEGUsuario.get(i);
			cUsuario = VSEGUsuario.getCNombre() + " "
					+ VSEGUsuario.getCApPaterno() + " "
					+ VSEGUsuario.getCApMaterno();
		}

		for (int i = 0; i < vEtapas.size(); i++) {
			TVGRLEtapa VGRLEtapa = new TVGRLEtapa();
			VGRLEtapa = (TVGRLEtapa) vEtapas.get(i);
			if (VGRLEtapa.getiCveEtapa() == ivEtapa)
				cPlantilla = VGRLEtapa.getcDocumento();
		}

		// Valores de la Empresa.
		for (int i = 0; i < vEmpresas.size(); i++) {
			TVGRLEmpresas VGRLEmpresas = new TVGRLEmpresas();
			VGRLEmpresas = (TVGRLEmpresas) vEmpresas.get(i);
			cEmpresa = VGRLEmpresas.getCNombreRS();
			ivMdoTransporte = VGRLEmpresas.getICveMdoTrans();
			iviCveUniMed = VGRLEmpresas.getICveUniMed();
			cCveEmpresa = VGRLEmpresas.getcIDDGPMPT();
		}

		try {
			vUniMed = DGRLUniMed.FindByAll(" where iCveUniMed = "
					+ iviCveUniMed, "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Valores de la Unidad Médica.
		for (int i = 0; i < vUniMed.size(); i++) {
			TVGRLUniMed VGRLUniMed = new TVGRLUniMed();
			VGRLUniMed = (TVGRLUniMed) vUniMed.get(i);
			cLugar = VGRLUniMed.getCCiudad() + ",";
			iviCveEstado = VGRLUniMed.getICveEstado();
			iviCvePais = VGRLUniMed.getICvePais();
		}

		try {
			vEstado = DEntidadFed.FindByAll(" where iCvePais       = "
					+ iviCvePais + "   and iCveEntidadFed = " + iviCveEstado);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (int i = 0; i < vEstado.size(); i++) {
			TVEntidadFed VEntidadFed = new TVEntidadFed();
			VEntidadFed = (TVEntidadFed) vEstado.get(i);
			cLugar = cLugar + VEntidadFed.getCAbreviatura();
		}

		// Valores del Domicilio.
		for (int i = 0; i < vDomicilio.size(); i++) {
			TVCTRDomicilio VCTRDomicilio = new TVCTRDomicilio();
			VCTRDomicilio = (TVCTRDomicilio) vDomicilio.get(i);
			cCalle = VCTRDomicilio.getCCalle();
			cColonia = "Col. " + VCTRDomicilio.getCColonia();
			cCiudad = VCTRDomicilio.getCCiudad() + ", ";
			cCP = new Integer(VCTRDomicilio.getICP()).toString() + ",";
			iviCveEstado = VCTRDomicilio.getICveEstado();
			iviCvePais = VCTRDomicilio.getICvePais();
			// cAbrevEstado = "DF";
		}

		try {
			vEstado = DEntidadFed.FindByAll(" where iCvePais       = "
					+ iviCvePais + "   and iCveEntidadFed = " + iviCveEstado);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (int i = 0; i < vEstado.size(); i++) {
			TVEntidadFed VEntidadFed = new TVEntidadFed();
			VEntidadFed = (TVEntidadFed) vEstado.get(i);
			cAbrevEstado = VEntidadFed.getCAbreviatura();
		}

		switch (ivEtapa) {
		case 1: {
			Rep1.comDespliega("E", 9, cOficio);
			Rep1.comDespliega("E", 10, cLugar + cFecha);
			Rep1.comDespliega("B", 13, cEmpresa);
			Rep1.comDespliega("B", 14, cCalle);
			Rep1.comDespliega("B", 15, cColonia);
			Rep1.comDespliega("B", 16, cCP + cCiudad + cAbrevEstado);
			Rep1.comDespliega("B", 55, cDirectorGeneral);
			break;
		}
		case 2: {
			Rep1.comDespliega("K", 10, Fecha.getFechaDDMMYYYY(dtFecha, "/"));
			Rep1.comDespliega("F", 13, cEmpresa);
			Rep1.comDespliega("F", 14, cCveEmpresa);
			Rep1.comDespliega("C", 28, cObservaciones);
			Rep1.comDespliega("G", 42, cUsuario);
			break;
		}
		case 3: {
			Rep1.comDespliega("E", 9, cOficio);
			Rep1.comDespliega("E", 10, cLugar + cFecha);
			Rep1.comDespliega("B", 55, cDirectorGeneral);
			break;
		}
		case 5: {
			Rep1.comDespliega("E", 9, cOficio);
			Rep1.comDespliega("E", 10, cLugar + cFecha);
			Rep1.comDespliega("B", 13, cEmpresa);
			Rep1.comDespliega("B", 14, cCalle);
			Rep1.comDespliega("B", 15, cColonia);
			Rep1.comDespliega("B", 16, cCP + cCiudad + cAbrevEstado);
			Rep1.comDespliega("B", 55, cDirectorGeneral);
			break;
		}
		case 6: {
			Rep1.comDespliega("K", 10, Fecha.getFechaDDMMYYYY(dtFecha, "/"));
			Rep1.comDespliega("F", 13, cEmpresa);
			Rep1.comDespliega("F", 14, cCveEmpresa);
			Rep1.comDespliega("C", 28, cObservaciones);
			Rep1.comDespliega("G", 42, cUsuario);
			break;
		}
		case 7: {
			Rep1.comDespliega("K", 10, Fecha.getFechaDDMMYYYY(dtFecha, "/"));
			Rep1.comDespliega("F", 13, cEmpresa);
			Rep1.comDespliega("F", 14, cCveEmpresa);
			Rep1.comDespliega("C", 28, cObservaciones);
			Rep1.comDespliega("G", 42, cUsuario);
			break;
		}
		}
		StringBuffer buffer = Rep1.creaActiveX(cPlantilla + ivMdoTransporte,
				"CtrTrans-" + ivEmpresa + "-" + ivPlantilla + "-"
						+ ivMovimiento, false, false, true);

		activeX.append(buffer);
	}

	public String getActiveX() {
		return activeX.toString();
	}

}