package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070502040CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	int ivUniMed = 0;
	int ivUniMedCtr = 0;
	int ivMdoTransporte = 0;
	int ivAnio = 2004;
	int ivPeriodo = 1;
	int ivProgramada = 0;
	int ivUsuario = 0;
	int ivCantidad = 0;
	String cdtSolicitud = "";
	String cdtVencimiento = "";
	public Vector vMdoTrans = new Vector();
	public Vector vPeriodPla = new Vector();

	public pg070502040CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {
		if (request.getParameter("SLSUniMed") != null)
			if (request.getParameter("SLSUniMed").toString()
					.compareToIgnoreCase("") != 0)
				ivUniMed = new Integer(request.getParameter("SLSUniMed"))
						.intValue();

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

		if (request.getParameter("SLSUniMedCtr") != null)
			if (request.getParameter("SLSUniMedCtr").toString()
					.compareToIgnoreCase("") != 0)
				ivUniMedCtr = new Integer(request.getParameter("SLSUniMedCtr"))
						.intValue();

		if (request.getParameter("SLSMdoTransporte") != null)
			if (request.getParameter("SLSMdoTransporte").toString()
					.compareToIgnoreCase("") != 0)
				ivMdoTransporte = new Integer(
						request.getParameter("SLSMdoTransporte")).intValue();

		if (request.getParameter("lProgramada") != null)
			if (request.getParameter("lProgramada").toString()
					.compareToIgnoreCase("") != 0)
				ivProgramada = new Integer(request.getParameter("lProgramada"))
						.intValue();

		if (request.getParameter("dtSolicitud") != null)
			if (request.getParameter("dtSolicitud").toString()
					.compareToIgnoreCase("") != 0)
				cdtSolicitud = request.getParameter("dtSolicitud");

		if (request.getParameter("dtVencimiento") != null)
			if (request.getParameter("dtVencimiento").toString()
					.compareToIgnoreCase("") != 0)
				cdtVencimiento = request.getParameter("dtVencimiento");

		if (request.getParameter("SLSUsuario") != null)
			if (request.getParameter("SLSUsuario").toString()
					.compareToIgnoreCase("") != 0)
				ivUsuario = new Integer(request.getParameter("SLSUsuario"))
						.intValue();

		if (request.getParameter("hdBoton") != null)
			if (request.getParameter("hdBoton").toString()
					.compareToIgnoreCase("Generar") == 0)
				this.Generar();

	}

	public void Generar() {
		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		TDCTRUMPeriodo DCTRUMPeriodo = new TDCTRUMPeriodo();
		TDCTRSeguimiento DCTRSeguimiento = new TDCTRSeguimiento();
		TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
		TDCTRPeriodPla DCTRPeriodPla = new TDCTRPeriodPla();
		java.sql.Date dtVencimiento = null;
		TFechas Fecha = new TFechas();
		Vector vEmpresas = new Vector();
		Vector vUMPeriodo = new Vector();
		Vector vPlantilla = new Vector();
		int ivPlantilla = 0;
		int ivEtapa = 0;
		ivEtapa = Integer.parseInt(vParametros
				.getPropEspecifica("CTREtapaInicial"));
		int ivSolictante = 0;
		ivSolictante = Integer.parseInt(vParametros
				.getPropEspecifica("CTRSolicitanteIni"));
		int ivTipoEntrega = 0;
		ivTipoEntrega = Integer.parseInt(vParametros
				.getPropEspecifica("CTRTipoEntregaIni"));

		if (ivAnio > 0 && ivPeriodo > 0) {

			try {
				vPeriodPla = DCTRPeriodPla.FindByAll(" where iAnio         = "
						+ ivAnio + "   and iCvePeriodPla = " + ivPeriodo
						+ "   and lActivo = 1 ");
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Obtención de la Fecha de Vencimiento para las Plantillas
			// Programadas.
			if (!vPeriodPla.isEmpty()) {
				for (int i = 0; i < vPeriodPla.size(); i++) {
					TVCTRPeriodPla VCTRPeriodPla = new TVCTRPeriodPla();
					VCTRPeriodPla = (TVCTRPeriodPla) vPeriodPla.get(i);
					if (VCTRPeriodPla.getdtVencimiento() != null)
						dtVencimiento = VCTRPeriodPla.getdtVencimiento();
					else
						dtVencimiento = Fecha.TodaySQL();
				}
			}

			try {
				vUMPeriodo = DCTRUMPeriodo.FindByAll(" where iAnio         = "
						+ ivAnio + "   and iCvePeriodPla = " + ivPeriodo
						+ "   and iCveUniMed    = " + ivUniMed
						+ "   and iCveMdoTrans  = " + ivMdoTransporte);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (vUMPeriodo != null)
				if (vUMPeriodo.isEmpty()) {

					// Selección de las Empresas que ya se van a Procesar.
					try {
						vEmpresas = DGRLEmpresas
								.FindByAll(" where iCveUniMed   = "
										+ ivUniMedCtr
										+ "   and iCveMdoTrans = "
										+ ivMdoTransporte);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					if (!vEmpresas.isEmpty()) {
						for (int i = 0; i < vEmpresas.size(); i++) {
							TVGRLEmpresas VGRLEmpresas = new TVGRLEmpresas();
							VGRLEmpresas = (TVGRLEmpresas) vEmpresas.get(i);

							try {
								vPlantilla = DCTRPlantilla
										.FindByAll(" where CTRPlantilla.iCveEmpresa = "
												+ VGRLEmpresas.getICveEmpresa());
							} catch (Exception ex) {
								ex.printStackTrace();
							}

							if (!vPlantilla.isEmpty()) {
								ivPlantilla = vPlantilla.size() + 1;
							} else
								ivPlantilla = 1;

							// Datos de la Plantilla.
							TVCTRPlantilla VCTRPlantilla = new TVCTRPlantilla();
							VCTRPlantilla.setICveEmpresa(VGRLEmpresas
									.getICveEmpresa());
							VCTRPlantilla.setiCvePlantilla(ivPlantilla);
							VCTRPlantilla.setiCveTpoEntrega(ivTipoEntrega);
							VCTRPlantilla.setlProgramada(ivProgramada);
							VCTRPlantilla.setdtSolicitud(Fecha
									.getDateSQL(cdtSolicitud));
							if (ivProgramada == 0)
								VCTRPlantilla.setdtVencimiento(Fecha
										.getDateSQL(cdtVencimiento));
							else
								// VCTRPlantilla.setdtVencimiento(Fecha.getDateSQL(Fecha.getFechaDDMMYYYY(dtVencimiento,"/")));
								VCTRPlantilla.setdtVencimiento(dtVencimiento);
							VCTRPlantilla.setiAnio(ivAnio);
							VCTRPlantilla.setiCvePeriodPla(ivPeriodo);
							VCTRPlantilla.setiCveUMSolicita(ivUniMed);
							VCTRPlantilla.setiCveUsuSolicita(ivUsuario);

							// Datos del Seguimiento.
							TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
							VCTRSeguimiento.setiCveEmpresa(VGRLEmpresas
									.getICveEmpresa());
							VCTRSeguimiento.setiCvePlantilla(ivPlantilla);
							VCTRSeguimiento.setiCveMovimiento(1);
							VCTRSeguimiento.setiCveProceso(5);
							VCTRSeguimiento.setiCveEtapa(ivEtapa);
							VCTRSeguimiento.setdtSolicitud(Fecha
									.getDateSQL(cdtSolicitud));
							VCTRSeguimiento.setiCveSolictante(ivSolictante);
							VCTRSeguimiento.setiCveUsuSolicita(ivUsuario);
							VCTRSeguimiento.setiCveUsuReg(ivUsuario);
							VCTRSeguimiento
									.setcObservacion("Por la Generación de Plantillas");

							// Se insertan los Datos de la Plantilla
							try {
								DCTRPlantilla.insert(null, VCTRPlantilla);
							} catch (Exception ex) {
								ex.printStackTrace();
							}

							// Se Ingresan los Datos del Seguimiento.
							try {
								DCTRSeguimiento.insert(null, VCTRSeguimiento);
							} catch (Exception ex) {
								ex.printStackTrace();
							}

							ivCantidad = ivCantidad + 1;
						}
					}

					// Generación del Movimiento para la Plantilla con Año,
					// Periodo, Unidad Medica y Modo de Transporte
					TVCTRUMPeriodo VCTRUMPeriodo = new TVCTRUMPeriodo();
					VCTRUMPeriodo.setiAnio(ivAnio);
					VCTRUMPeriodo.setiCvePeriodPla(ivPeriodo);
					VCTRUMPeriodo.setiCveUniMed(ivUniMed);
					VCTRUMPeriodo.setiCveMdoTrans(ivMdoTransporte);
					VCTRUMPeriodo.setdtGeneradas(Fecha.TodaySQL());
					try {
						DCTRUMPeriodo.insert(null, VCTRUMPeriodo);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
		}
	}

	public int getCantidad() {
		return ivCantidad;
	}

	public void fillVector() {

		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		TDCTRPeriodPla DCTRPeriodPla = new TDCTRPeriodPla();
		String cCond2 = "";

		try {
			vPeriodPla = DCTRPeriodPla.FindByAll(" where iAnio = " + ivAnio
					+ "   and lActivo = 1 ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Obtención del Modo de Transporte
		try {
			vMdoTrans = DGRLMdoTrans.findByAll("");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		cPaginas = "pg070502040.jsp|";

		if (ivMdoTransporte > 0) {
			cCond2 = " and iCveMdoTrans = " + ivMdoTransporte;
		}

		if (cCondicion.compareToIgnoreCase("") != 0)
			cCondicion = " and " + cCondicion;

		if (cOrden.compareTo("") != 0)
			cCondicion = cCondicion + cOrden;
		else
			cCondicion = cCondicion + " order by iCveEmpresa ";

		try {
			// if (ivUniMed > 0)
			// vDespliega = DGRLEmpresas.FindByAll(" where iCveUniMed = " +
			// ivUniMed +
			// cCond2 +
			// cCondicion);
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