package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070502032CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public boolean lActivo = false;
	public boolean lModificar = false;
	int ivEmpresa = 0;
	int ivPlantilla = 0;
	int ivProgramada = 0;
	int ivAnio = 0;
	int ivPeriodo = 0;
	int ivUniMedRec = 0;
	int ivUsrRec = 0;
	int ivCveMedRecep = 0;
	int ivTpoEntrega = 0;
	int ivUniMedSol = 0;
	int ivUsrSolicita = 0;
	int ivModificar = 0;
	java.sql.Date dtRecepcion = null;
	java.sql.Date dtSolicitud = null;
	java.sql.Date dtVencimiento = null;
	public Vector vEmpresas = new Vector();
	public Vector vMdoTrans = new Vector();
	public Vector vPeriodPla = new Vector();
	public Vector vMedRecep = new Vector();
	public Vector vTpoEntrega = new Vector();
	TFechas Fecha = new TFechas();

	public pg070502032CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070502031.jsp|";
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

		if (request.getParameter("TBXProgramada") != null)
			if (request.getParameter("TBXProgramada").toString()
					.compareToIgnoreCase("") != 0)
				ivProgramada = new Integer(
						request.getParameter("TBXProgramada")).intValue();

		if (request.getParameter("SLSAnio") != null)
			if (request.getParameter("SLSAnio").toString()
					.compareToIgnoreCase("") != 0)
				ivAnio = new Integer(request.getParameter("SLSAnio"))
						.intValue();

		if (request.getParameter("SLSPeriodo") != null)
			if (request.getParameter("SLSPeriodo").toString()
					.compareToIgnoreCase("") != 0)
				ivPeriodo = new Integer(request.getParameter("SLSPeriodo"))
						.intValue();

		if (request.getParameter("dtSolicitud") != null)
			if (request.getParameter("dtSolicitud").toString()
					.compareToIgnoreCase("") != 0)
				dtSolicitud = Fecha.getDateSQL(request.getParameter(
						"dtSolicitud").toString());

		if (request.getParameter("dtVencimiento") != null)
			if (request.getParameter("dtVencimiento").toString()
					.compareToIgnoreCase("") != 0)
				dtVencimiento = Fecha.getDateSQL(request.getParameter(
						"dtVencimiento").toString());

		// if (request.getParameter("dtRecepcion") != null)
		// if
		// (request.getParameter("dtRecepcion").toString().compareToIgnoreCase("")
		// != 0)
		// dtRecepcion =
		// Fecha.getDateSQL(request.getParameter("dtRecepcion").toString());

		if (request.getParameter("SLSUniMedSolicita") != null)
			if (request.getParameter("SLSUniMedSolicita").toString()
					.compareToIgnoreCase("") != 0)
				ivUniMedSol = new Integer(
						request.getParameter("SLSUniMedSolicita")).intValue();

		if (request.getParameter("SLSUsrSolicita") != null)
			if (request.getParameter("SLSUsrSolicita").toString()
					.compareToIgnoreCase("") != 0)
				ivUsrSolicita = new Integer(
						request.getParameter("SLSUsrSolicita")).intValue();

		if (request.getParameter("SLSUniMedRecibe") != null)
			if (request.getParameter("SLSUniMedRecibe").toString()
					.compareToIgnoreCase("") != 0)
				ivUniMedRec = new Integer(
						request.getParameter("SLSUniMedRecibe")).intValue();

		if (request.getParameter("SLSUsrRecibe") != null)
			if (request.getParameter("SLSUsrRecibe").toString()
					.compareToIgnoreCase("") != 0)
				ivUsrRec = new Integer(request.getParameter("SLSUsrRecibe"))
						.intValue();

		if (request.getParameter("SLSMedio") != null)
			if (request.getParameter("SLSMedio").toString()
					.compareToIgnoreCase("") != 0)
				ivCveMedRecep = new Integer(request.getParameter("SLSMedio"))
						.intValue();

		if (request.getParameter("SLSTpoEntrega") != null)
			if (request.getParameter("SLSTpoEntrega").toString()
					.compareToIgnoreCase("") != 0)
				ivTpoEntrega = new Integer(
						request.getParameter("SLSTpoEntrega")).intValue();

		if (request.getParameter("hdModificar") != null)
			if (request.getParameter("hdModificar").toString()
					.compareToIgnoreCase("") != 0)
				ivModificar = new Integer(request.getParameter("hdModificar"))
						.intValue();

		if (request.getParameter("hdBoton") != null)
			if (request.getParameter("hdBoton").toString()
					.compareToIgnoreCase("Cerrar") == 0)
				this.Cerrar();
	}

	public void Guardar() {

		if (ivModificar == 1) {
			TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
			TDCTRSeguimiento DCTRSeguimiento = new TDCTRSeguimiento();
			TVCTRPlantilla VCTRPlantilla = new TVCTRPlantilla();
			TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
			Vector vPlantilla = new Vector();
			int ivNoPlantilla = 0;
			int ivEtapa = 0;
			ivEtapa = Integer.parseInt(vParametros
					.getPropEspecifica("CTREtapaInicial"));
			int ivSolictante = 0;
			ivSolictante = Integer.parseInt(vParametros
					.getPropEspecifica("CTRSolicitanteIni"));

			try {
				vPlantilla = DCTRPlantilla.FindByAll(" where iCveEmpresa = "
						+ ivEmpresa + " order by iCvePlantilla");
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			for (int i = 0; i < vPlantilla.size(); i++) {
				TVCTRPlantilla VCTRPlantillaMax = new TVCTRPlantilla();
				VCTRPlantillaMax = (TVCTRPlantilla) vPlantilla.get(i);
				ivNoPlantilla = VCTRPlantillaMax.getiCvePlantilla();
			}
			ivNoPlantilla = ivNoPlantilla + 1;
			VCTRPlantilla.setICveEmpresa(ivEmpresa);
			VCTRPlantilla.setiCvePlantilla(ivNoPlantilla);
			VCTRPlantilla.setiCveTpoEntrega(ivTpoEntrega);
			VCTRPlantilla.setlProgramada(ivProgramada);
			VCTRPlantilla.setiAnio(ivAnio);
			VCTRPlantilla.setiCvePeriodPla(ivPeriodo);
			VCTRPlantilla.setdtSolicitud(dtSolicitud);
			VCTRPlantilla.setdtVencimiento(dtVencimiento);
			VCTRPlantilla.setiCveUsuSolicita(ivUsrSolicita);
			VCTRPlantilla.setiCveUMSolicita(ivUniMedSol);
			VCTRPlantilla.setiCveMedRecep(ivCveMedRecep);

			VCTRSeguimiento.setiCveEmpresa(ivEmpresa);
			VCTRSeguimiento.setiCvePlantilla(ivNoPlantilla);
			VCTRSeguimiento.setiCveMovimiento(0);
			VCTRSeguimiento.setiCveProceso(5);
			VCTRSeguimiento.setiCveEtapa(ivEtapa);
			VCTRSeguimiento.setdtSolicitud(dtSolicitud);
			VCTRSeguimiento.setiCveSolictante(ivSolictante);
			VCTRSeguimiento.setiCveUsuReg(ivUsrSolicita);
			VCTRSeguimiento.setiCveUsuSolicita(ivUsrSolicita);
			VCTRSeguimiento
					.setcObservacion("Agregado desde el Detalle de la Plantilla");

			// Inserción de la Plantilla.
			try {
				DCTRPlantilla.insert(null, VCTRPlantilla);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Inserción del Seguimiento.
			try {
				DCTRSeguimiento.insert(null, VCTRSeguimiento);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			ivPlantilla = ivNoPlantilla;
		} else {
			TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
			TVCTRPlantilla VCTRPlantilla = new TVCTRPlantilla();

			VCTRPlantilla.setICveEmpresa(ivEmpresa);
			VCTRPlantilla.setiCvePlantilla(ivPlantilla);
			VCTRPlantilla.setiCveUMRecibe(ivUniMedRec);
			VCTRPlantilla.setiCveUsuRecibe(ivUsrRec);
			VCTRPlantilla.setiCveMedRecep(ivCveMedRecep);
			VCTRPlantilla.setiCveTpoEntrega(ivTpoEntrega);

			// Actualización sin Cerrar.
			try {
				DCTRPlantilla.updPreCierre(null, VCTRPlantilla);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void Cerrar() {
		TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
		TVCTRPlantilla VCTRPlantilla = new TVCTRPlantilla();
		TDCTRSeguimiento DCTRSeguimiento = new TDCTRSeguimiento();
		TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
		int ivEtapa = 0;
		ivEtapa = Integer.parseInt(vParametros
				.getPropEspecifica("CTREtapaFinal"));
		int ivSolictante = 0;
		ivSolictante = Integer.parseInt(vParametros
				.getPropEspecifica("CTRSolicitanteIni"));
		int ivTipoEntrega = 0;
		ivTipoEntrega = Integer.parseInt(vParametros
				.getPropEspecifica("CTRTipoEntregaIni"));
		java.sql.Date dtRecepcion = null;
		dtRecepcion = Fecha.TodaySQL();

		if (dtRecepcion != null) {

			VCTRPlantilla.setICveEmpresa(ivEmpresa);
			VCTRPlantilla.setiCvePlantilla(ivPlantilla);
			VCTRPlantilla.setdtRecepcion(dtRecepcion);
			VCTRPlantilla.setiCveUMRecibe(ivUniMedRec);
			VCTRPlantilla.setiCveUsuRecibe(ivUsrRec);
			VCTRPlantilla.setiCveMedRecep(ivCveMedRecep);
			VCTRPlantilla.setiCveTpoEntrega(ivTpoEntrega);

			VCTRSeguimiento.setiCveEmpresa(ivEmpresa);
			VCTRSeguimiento.setiCvePlantilla(ivPlantilla);
			VCTRSeguimiento.setiCveProceso(5);
			VCTRSeguimiento.setiCveEtapa(ivEtapa);
			VCTRSeguimiento.setdtSolicitud(dtRecepcion);
			VCTRSeguimiento.setiCveSolictante(ivSolictante);
			VCTRSeguimiento.setiCveUsuReg(ivUsrRec);
			VCTRSeguimiento.setiCveUsuSolicita(ivUsrRec);
			VCTRSeguimiento.setcObservacion("Por el Cierre de Plantilla");

			// Generación del Update de la Plantilla.
			try {
				DCTRPlantilla.updCierre(null, VCTRPlantilla);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Generación de Insert del Seguimiento Final.
			try {
				DCTRSeguimiento.insert(null, VCTRSeguimiento);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	public void fillVector() {

		TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		TDCTRPeriodPla DCTRPeriodPla = new TDCTRPeriodPla();
		TDCTRMedRecep DCTRMedRecep = new TDCTRMedRecep();
		TDCTRTpoEntrega DCTRTpoEntrega = new TDCTRTpoEntrega();
		TFechas Fecha = new TFechas();

		if (ivAnio == 0)
			ivAnio = Fecha.getIntYear(Fecha.TodaySQL());

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
			vPeriodPla = DCTRPeriodPla.FindByAll(" where iAnio = " + ivAnio
					+ " and   lActivo = 1 ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vMedRecep = DCTRMedRecep.FindByAll(" where lActivo = 1 ", "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vTpoEntrega = DCTRTpoEntrega.FindByAll(" where lActivo = 1 ", "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (cCondicion.compareToIgnoreCase("") != 0)
			cCondicion = " and " + cCondicion;

		if (cOrden.compareTo("") != 0) {
			if (cOrden.compareToIgnoreCase("order by iCveEmpresa") == 0)
				cCondicion = cCondicion + " order by iCvePlantilla ";
			else
				cCondicion = cCondicion + cOrden;
		} else
			cCondicion = cCondicion + " order by iCvePlantilla ";

		// Vector del Listado.
		try {
			vDespliega = DCTRPlantilla
					.FindByAll(" where CTRPlantilla.iCveEmpresa   = "
							+ ivEmpresa
							+ "  and  CTRPlantilla.iCvePlantilla = "
							+ ivPlantilla);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		String cAccAnt = "";
		if (request.getParameter("hdBoton") != null)
			if (request.getParameter("hdBoton").toString()
					.compareToIgnoreCase("") != 0)
				cAccAnt = request.getParameter("hdBoton").toString();

		if (!vDespliega.isEmpty()) {
			if (cAccAnt.compareToIgnoreCase("Nuevo") != 0) {
				if (cAccAnt.compareToIgnoreCase("Guardar") == 0) {
					lActivo = true;
					lModificar = false;
					UpdStatus = "AddSaveCancel";
					cImprimir = "Imprimir";
					if (NavStatus.compareToIgnoreCase("Disabled") == 0)
						NavStatus = "Disabled";
				} else {
					lActivo = true;
					lModificar = false;
					UpdStatus = "AddSaveCancel";
					cImprimir = "Imprimir";
					if (NavStatus.compareToIgnoreCase("Disabled") == 0)
						NavStatus = "Disabled";
				}
			} else {
				lActivo = true;
				lModificar = true;
				UpdStatus = "SaveCancelOnly";
				cImprimir = "Imprimir";
				if (NavStatus.compareToIgnoreCase("Disabled") == 0)
					NavStatus = "Disabled";
			}
		} else {
			if (cAccAnt.compareToIgnoreCase("Nuevo") == 0) {
				lActivo = true;
				lModificar = true;
				UpdStatus = "SaveCancelOnly";
				NavStatus = "Disabled";
			} else {
				lActivo = true;
				lModificar = false;
				UpdStatus = "AddOnly";
				NavStatus = "Disabled";
			}
		}
	}
}