package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.text.*;

public class pg070802040CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";

	public Vector vAlmacenes = new Vector();
	public Vector vALMTpoPrioridad = new Vector();
	public Vector vALMPeriodo = new Vector();

	public Vector vFamilias = new Vector();
	public Vector vAlmxArt = new Vector();
	public Vector vUbicacion = new Vector();

	public Vector vSolicSumin = new Vector();
	public Vector vSumArt = new Vector();

	private StringBuffer activeX = new StringBuffer("");
	private String cRespDeptoAlmacen = "";
	private int iCveUsuario;
	private boolean bFlag;

	String cvAnio = "";

	// int ivAlmacen = 0;
	// int ivTipoArticulo = 0;
	// int ivFamilia = 0;

	public pg070802040CFG() {
		vParametros = new TParametro("07");
		cRespDeptoAlmacen = vParametros
				.getPropEspecifica("ALMRespDeptoAlmacen");
		if (cRespDeptoAlmacen == null
				|| cRespDeptoAlmacen.equalsIgnoreCase("null"))
			cRespDeptoAlmacen = "NOMBRE:______________________________";
	}

	public void mainBlock() {

		TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
		TDALMTpoPrioridad dALMTpoPrioridad = new TDALMTpoPrioridad();
		TDALMPeriodo dALMPeriodo = new TDALMPeriodo();

		// Vector de los Almacenes.
		try {
			vAlmacenes = DALMAlmacen.FindByCustomWhere(" where lActivo = 1");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Tipos de Prioridad
		try {
			vALMTpoPrioridad = dALMTpoPrioridad.FindByAll("",
					" Order By iCvePrioridad");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Periodos
		try {
			TFechas dtFecha = new TFechas();
			int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

			vALMPeriodo = dALMPeriodo.FindByAll(
					" where lActivo = 1 and iAnio = " + iAnio,
					" Order by iCvePeriodo");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (cAccion.compareToIgnoreCase("Suministrar") == 0) {
			Guardar();
		}

		if (cAccion.compareToIgnoreCase("Comprobante") == 0) {
			this.Comprobante();
		}

		if (cAccion.compareToIgnoreCase("Vale") == 0) {
			this.Vale();
		}

	}

	public void Comprobante() {
		TExcel Rep1 = new TExcel("07");
		TFechas tFecha = new TFechas("07");
		TDALMSolicSumin DALMSolicSumin = new TDALMSolicSumin();
		TDALMSumArt DALMSumArt = new TDALMSumArt();
		TDSEGUsuario DSEGUsuario = new TDSEGUsuario();
		TFechas Fecha = new TFechas();
		String cJefeDepto = "";
		String cArea = "";
		Vector vSEGUsuario = new Vector();

		int ivAnio = 0;
		int ivAlmacen = 0;
		int ivNumSolic = 0;
		int ivReng = 18; // Valor del Renglon Inicial en la Hoja Excel.
		int ivRengIni = ivReng; // Valor del Renglon Inicial en la Hoja Excel.

		if (request.getParameter("hdCampoClave") != null)
			ivAnio = Integer.parseInt(request.getParameter("hdCampoClave"));
		if (request.getParameter("hdCampoClave2") != null)
			ivAlmacen = Integer.parseInt(request.getParameter("hdCampoClave2"));
		if (request.getParameter("hdCampoClave3") != null)
			ivNumSolic = Integer
					.parseInt(request.getParameter("hdCampoClave3"));

		try {
			String Condicion = " where ALMSolicSumin.iAnio = " + ivAnio
					+ "   and AlmSolicSumin.iCveAlmacen = " + ivAlmacen
					+ "   and ALMSolicSumin.iCveSolicSum = " + ivNumSolic;
			// // System.out.println("Condicion 1: "+Condicion);
			vSolicSumin = DALMSolicSumin.FindByAll(Condicion, "");
			Condicion = " where ALMSumArt.iAnio = " + ivAnio
					+ "   and ALMSumArt.iCveAlmacen = " + ivAlmacen
					+ "   and ALMSumArt.iCveSolicSum = " + ivNumSolic;
			// // System.out.println("Condicion 2: "+Condicion);
			vSumArt = DALMSumArt.FindByAllSolicSumin2(Condicion, "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (vSolicSumin != null && vSolicSumin.size() > 0) {
			// // System.out.println("1");
			for (int i = 0; i < vSolicSumin.size(); i++) {
				TVALMSolicSumin VALMSolicSumin = new TVALMSolicSumin();
				VALMSolicSumin = (TVALMSolicSumin) vSolicSumin.get(i);
				// Nombre de almacén
				Rep1.comDespliega(
						"B",
						11,
						(VALMSolicSumin.getCDscAlmacen() != null ? VALMSolicSumin
								.getCDscAlmacen() : ""));
				// Nombre de Unidad Médica
				Rep1.comDespliega(
						"B",
						12,
						(VALMSolicSumin.getCDscUniMed() != null ? VALMSolicSumin
								.getCDscUniMed() : ""));
				// Nombre de Módulo
				Rep1.comDespliega(
						"B",
						13,
						(VALMSolicSumin.getCDscModulo() != null ? VALMSolicSumin
								.getCDscModulo() : ""));
				// Nombre de Area
				cArea = VALMSolicSumin.getCDscArea();
				if (cArea.equalsIgnoreCase("null"))
					cArea = "___________________________________________";
				Rep1.comDespliega("B", 14, cArea);

				// Fecha de Suministro
				Rep1.comDespliega("F", 11, Fecha.getFechaDDMMYYYY(
						VALMSolicSumin.getDtSuministro(), "/"));
				// Año
				Rep1.comDespliega("F", 12, "" + ivAnio);
				// No. Solicitud
				DecimalFormat df = new DecimalFormat("000");
				Rep1.comDespliega("F", 13,
						"" + df.format(Double.parseDouble("" + ivNumSolic)));
				// Fecha Solicitud
				Rep1.comDespliega("F", 14, tFecha.getFechaDDMMYYYY(
						VALMSolicSumin.getDtSolicitud(), "/"));
			}
		}

		if (vSumArt != null && vSumArt.size() > 0) {
			// // System.out.println("2");
			for (int i = 0; i < vSumArt.size(); i++) {
				TVALMSumArt VALMSumArt = new TVALMSumArt();
				VALMSumArt = (TVALMSumArt) vSumArt.get(i);
				// Unidades
				float dUnidadesDesplegar = (float) 0.0;
				if (VALMSumArt.getdUnidades() <= 0.0)
					dUnidadesDesplegar = VALMSumArt.getDUnidAutor();
				else
					dUnidadesDesplegar = VALMSumArt.getdUnidades();
				if (dUnidadesDesplegar > 0) {
					Rep1.comDespliega("A", ivReng,
							new Float(dUnidadesDesplegar).toString());
					Rep1.comAlineaRango("A", ivReng, "A", ivReng,
							Rep1.getAT_HCENTRO());
					// Descripción de unidad de empaque
					Rep1.comDespliega("B", ivReng, VALMSumArt.getCDscUnidad());
					Rep1.comAlineaRango("B", ivReng, "B", ivReng,
							Rep1.getAT_HIZQUIERDA());
					// Descripción del artículo
					Rep1.comDespliega("C", ivReng, VALMSumArt.getcDscBreve());
					Rep1.comAlineaRango("C", ivReng, "C", ivReng,
							Rep1.getAT_HJUSTIFICA());
					Rep1.comAlineaRangoVer("C", ivReng, "C", ivReng,
							Rep1.getAT_VAJUSTAR());
					// Clave del artículo
					Rep1.comDespliega("D", ivReng, VALMSumArt.getcCveArticulo());
					Rep1.comAlineaRango("D", ivReng, "D", ivReng,
							Rep1.getAT_HCENTRO());
					if (VALMSumArt.getLLote() == 1) {
						// Lote del artículo
						Rep1.comDespliega("E", ivReng,
								"'" + VALMSumArt.getcLote());
						Rep1.comAlineaRango("E", ivReng, "E", ivReng,
								Rep1.getAT_HIZQUIERDA());
						// Rep1.comCellFormat("E", ivReng, "E", ivReng, "0");
						// Caducidad del lote del artículo
						if (VALMSumArt.getdtCaducidad() != null)
							Rep1.comDespliega(
									"F",
									ivReng,
									tFecha.getFechaDDMMYYYY(
											VALMSumArt.getdtCaducidad(), "/"));
						else
							Rep1.comDespliega("F", ivReng, "--");
						Rep1.comAlineaRango("F", ivReng, "F", ivReng,
								Rep1.getAT_HCENTRO());
					}
					ivReng++;
				}
			}
		}
		Rep1.comBordeTotal("A", ivRengIni, "F", ivReng - 1, 1);

		// Firma del Jefe de Departamento.
		ivReng++;
		int ivRengFirmasIni = ivReng;
		Rep1.comDespliega("A", ivReng, "                    Vo. Bo.");
		Rep1.comAlineaRango("A", ivReng, "A", ivReng, Rep1.getAT_HIZQUIERDA());
		Rep1.comDespliega("C", ivReng,
				"CONFORME                                        ");
		Rep1.comAlineaRango("C", ivReng, "C", ivReng, Rep1.getAT_HDERECHA());
		Rep1.comDespliega("E", ivReng, "RECIBIDO POR EL AREA");
		Rep1.comAlineaRango("E", ivReng, "E", ivReng, Rep1.getAT_HCENTRO());

		ivReng++;
		Rep1.comDespliega("A", ivReng, "     EL JEFE DEL DEPTO.");
		Rep1.comAlineaRango("A", ivReng, "A", ivReng, Rep1.getAT_HIZQUIERDA());
		Rep1.comDespliega("C", ivReng,
				"EL JEFE DE ALMACÉN                              ");
		Rep1.comAlineaRango("C", ivReng, "C", ivReng, Rep1.getAT_HDERECHA());
		Rep1.comDespliega("F", ivReng, cArea);
		Rep1.comAlineaRango("F", ivReng, "F", ivReng, Rep1.getAT_HDERECHA());

		int iRespAlmacen = 0;
		String cDscRespAlmacen = "";
		if (!vAlmacenes.isEmpty()) {
			for (int i = 0; i < vAlmacenes.size(); i++) {
				TVALMAlmacen VALMAlmacen = new TVALMAlmacen();
				VALMAlmacen = (TVALMAlmacen) vAlmacenes.get(i);
				if (VALMAlmacen.getICveAlmacen() == ivAlmacen)
					iRespAlmacen = VALMAlmacen.getICveUsuResp();
			}
		}

		try {
			vSEGUsuario = DSEGUsuario
					.FindByAll(" WHERE SEGUsuario.iCveUsuario = "
							+ iRespAlmacen);
			if (vSEGUsuario != null && !vSEGUsuario.isEmpty()) {
				TVSEGUsuario VSEGUsuario = new TVSEGUsuario();
				VSEGUsuario = (TVSEGUsuario) vSEGUsuario.get(0);
				cDscRespAlmacen = VSEGUsuario.getCNombre() != null ? VSEGUsuario
						.getCNombre() : "";
				cDscRespAlmacen += VSEGUsuario.getCApPaterno() != null ? " "
						+ VSEGUsuario.getCApPaterno() : "";
				cDscRespAlmacen += VSEGUsuario.getCApMaterno() != null ? " "
						+ VSEGUsuario.getCApMaterno() : "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ivReng++;
		Rep1.comDespliega("A", ivReng, "   " + cRespDeptoAlmacen);
		Rep1.comAlineaRango("A", ivReng, "A", ivReng, Rep1.getAT_HIZQUIERDA());
		Rep1.comDespliega("C", ivReng, cDscRespAlmacen + "                 ");
		Rep1.comAlineaRango("C", ivReng, "C", ivReng, Rep1.getAT_HDERECHA());
		Rep1.comDespliega("F", ivReng,
				"NOMBRE:______________________________________");
		Rep1.comAlineaRango("F", ivReng, "F", ivReng, Rep1.getAT_HDERECHA());

		ivReng += 2;
		Rep1.comDespliega("A", ivReng, "____________________________________");
		Rep1.comAlineaRango("A", ivReng, "A", ivReng, Rep1.getAT_HIZQUIERDA());
		Rep1.comDespliega("C", ivReng,
				"____________________________________     ");
		Rep1.comAlineaRango("C", ivReng, "C", ivReng, Rep1.getAT_HDERECHA());
		Rep1.comDespliega("E", ivReng, "____________________________________");
		Rep1.comAlineaRango("E", ivReng, "E", ivReng, Rep1.getAT_HCENTRO());

		ivReng++;
		Rep1.comDespliega("A", ivReng, "                    FIRMA");
		Rep1.comAlineaRango("A", ivReng, "A", ivReng, Rep1.getAT_HIZQUIERDA());
		Rep1.comDespliega("C", ivReng,
				"FIRMA                                             ");
		Rep1.comAlineaRango("C", ivReng, "C", ivReng, Rep1.getAT_HDERECHA());
		Rep1.comDespliega("E", ivReng, "FIRMA");
		Rep1.comAlineaRango("E", ivReng, "E", ivReng, Rep1.getAT_HCENTRO());

		Rep1.comBordeRededor("A", ivRengFirmasIni, "F", ivReng, 1, 1);

		StringBuffer buffer = Rep1.creaActiveX("pg070802040-1", "ComSum_"
				+ ivAnio + "_" + ivAlmacen + "_" + +ivNumSolic, false, false,
				true);

		activeX.append(buffer);
	}

	public void Vale() {
		TExcel Rep1 = new TExcel("07");
		TFechas tFecha = new TFechas("07");
		TDALMSolicSumin DALMSolicSumin = new TDALMSolicSumin();
		TDALMSumArt DALMSumArt = new TDALMSumArt();
		TDSEGUsuario DSEGUsuario = new TDSEGUsuario();
		TFechas Fecha = new TFechas();
		String cJefeDepto = "";
		String cArea = "";
		Vector vSEGUsuario = new Vector();

		int ivAnio = 0;
		int ivAlmacen = 0;
		int ivNumSolic = 0;

		if (request.getParameter("hdCampoClave") != null)
			ivAnio = Integer.parseInt(request.getParameter("hdCampoClave"));
		if (request.getParameter("hdCampoClave2") != null)
			ivAlmacen = Integer.parseInt(request.getParameter("hdCampoClave2"));
		if (request.getParameter("hdCampoClave3") != null)
			ivNumSolic = Integer
					.parseInt(request.getParameter("hdCampoClave3"));

		try {
			vSolicSumin = DALMSolicSumin.FindByAll(
					" where ALMSolicSumin.iAnio = " + ivAnio
							+ "   and AlmSolicSumin.iCveAlmacen = " + ivAlmacen
							+ "   and ALMSolicSumin.iCveSolicSum = "
							+ ivNumSolic, "");
			vSumArt = DALMSumArt.FindByAllSolicSumin2(
					" where ALMSumArt.iAnio = " + ivAnio
							+ "   and ALMSumArt.iCveAlmacen = " + ivAlmacen
							+ "   and ALMSumArt.iCveSolicSum = " + ivNumSolic,
					"");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!vSolicSumin.isEmpty()) {
			for (int i = 0; i < vSolicSumin.size(); i++) {
				TVALMSolicSumin VALMSolicSumin = new TVALMSolicSumin();
				VALMSolicSumin = (TVALMSolicSumin) vSolicSumin.get(i);
				// Nombre de almacén
				Rep1.comDespliega("B", 11, VALMSolicSumin.getCDscAlmacen());
				// Nombre de Unidad Médica
				Rep1.comDespliega("C", 13, VALMSolicSumin.getCDscUniMed());
				// Nombre de Módulo
				Rep1.comDespliega("C", 14, VALMSolicSumin.getCDscModulo());
				// Nombre de Area
				cArea = VALMSolicSumin.getCDscArea();
				if (cArea.equalsIgnoreCase("null"))
					cArea = "___________________________________________";
				Rep1.comDespliega("C", 15, cArea);

				// Fecha de Suministro
				Rep1.comDespliega("G", 11,
						Fecha.getFechaDDMMYYYY(tFecha.TodaySQL(), "/"));
				// Año
				Rep1.comDespliega("G", 13, "" + ivAnio);
				// No. Solicitud
				DecimalFormat df = new DecimalFormat("000");
				Rep1.comDespliega("G", 14,
						"" + df.format(Double.parseDouble("" + ivNumSolic)));
				// Fecha Solicitud
				Rep1.comDespliega("G", 15, tFecha.getFechaDDMMYYYY(
						VALMSolicSumin.getDtSolicitud(), "/"));
			}
		}

		int iRespAlmacen = 0;
		String cDscRespAlmacen = "";
		if (!vAlmacenes.isEmpty()) {
			for (int i = 0; i < vAlmacenes.size(); i++) {
				TVALMAlmacen VALMAlmacen = new TVALMAlmacen();
				VALMAlmacen = (TVALMAlmacen) vAlmacenes.get(i);
				if (VALMAlmacen.getICveAlmacen() == ivAlmacen)
					iRespAlmacen = VALMAlmacen.getICveUsuResp();
			}
		}

		try {
			vSEGUsuario = DSEGUsuario
					.FindByAll(" WHERE SEGUsuario.iCveUsuario = "
							+ iRespAlmacen);
			if (vSEGUsuario != null && !vSEGUsuario.isEmpty()) {
				TVSEGUsuario VSEGUsuario = new TVSEGUsuario();
				VSEGUsuario = (TVSEGUsuario) vSEGUsuario.get(0);
				cDscRespAlmacen = VSEGUsuario.getCNombre() != null ? VSEGUsuario
						.getCNombre() : "";
				cDscRespAlmacen += VSEGUsuario.getCApPaterno() != null ? " "
						+ VSEGUsuario.getCApPaterno() : "";
				cDscRespAlmacen += VSEGUsuario.getCApMaterno() != null ? " "
						+ VSEGUsuario.getCApMaterno() : "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Rep1.comDespliega("D", 36, cDscRespAlmacen);
		Rep1.comDespliega("F", 36, cRespDeptoAlmacen);

		StringBuffer buffer = Rep1.creaActiveX("pg070802040-2", "ValSalida_"
				+ ivAnio + "_" + ivAlmacen + "_" + +ivNumSolic, false, false,
				true);

		activeX.append(buffer);
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public void Guardar() {
		try {
			int iAnio = 0;
			int iAlmacen = 0;
			int iNumSolic = 0;
			boolean bUpdate = false;

			if (request.getParameter("hdCampoClave") != null
					&& request.getParameter("hdCampoClave").trim().length() > 0
					&& request.getParameter("hdCampoClave2") != null
					&& request.getParameter("hdCampoClave2").trim().length() > 0
					&& request.getParameter("hdCampoClave3") != null
					&& request.getParameter("hdCampoClave3").trim().length() > 0) {

				iAnio = Integer.parseInt(request.getParameter("hdCampoClave"));
				iAlmacen = Integer.parseInt(request
						.getParameter("hdCampoClave2"));
				iNumSolic = Integer.parseInt(request
						.getParameter("hdCampoClave3"));

				bUpdate = new TDALMDetalleMov().MakeTransaction(iAlmacen,
						iAnio, iNumSolic, this.iCveUsuario);
				if (bUpdate)
					this.setBFlag(bUpdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}/*
		 * 
		 * TDALMSolicSumin DALMSolicSumin = new TDALMSolicSumin(); TDALMSumArt
		 * DALMSumArt = new TDALMSumArt(); TDALMMovimiento DALMMovimiento = new
		 * TDALMMovimiento(); TDALMDetalleMov DALMDetalleMov = new
		 * TDALMDetalleMov(); TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
		 * TDALMLote DALMLote = new TDALMLote();
		 * 
		 * Vector vGuardar = new Vector(); Vector vMovimientos = new Vector();
		 * Vector vExistencias = new Vector(); Vector vExisLotes = new Vector();
		 * Vector vLotesAct = new Vector(); Vector vDetalleMovIns = new
		 * Vector(); TFechas Fecha = new TFechas(); int ivAnio = 0; int
		 * ivAlmacen = 0; int ivNumSolic = 0; int ivMovimiento = 0; int ivLote =
		 * 0; double dvExistencia = 0;
		 * 
		 * try{ if (request.getParameter("hdCampoClave") != null) ivAnio =
		 * Integer.parseInt(request.getParameter("hdCampoClave"));
		 * 
		 * if (request.getParameter("hdCampoClave2") != null) ivAlmacen =
		 * Integer.parseInt(request.getParameter("hdCampoClave2"));
		 * 
		 * if (request.getParameter("hdCampoClave3") != null) ivNumSolic =
		 * Integer.parseInt(request.getParameter("hdCampoClave3"));
		 * 
		 * vExistencias = DALMArtxAlm.FindByAll(" where iCveAlmacen = " +
		 * ivAlmacen); vExisLotes = DALMLote.FindByAll(" where iCveAlmacen = " +
		 * ivAlmacen + "   and dtCaducidad >= '" + Fecha.TodaySQL() + "'" +
		 * "   and dtAgotado   is null " +
		 * " order by iCveAlmacen, iCveArticulo, dtCaducidad asc ");
		 * vMovimientos = DALMMovimiento.FindByAll(" where iAnio       = " +
		 * ivAnio + " and   iCveAlmacen = " + ivAlmacen +
		 * "  ORDER BY iAnio, iCveAlmacen, iCvemovimiento DESC ");
		 * 
		 * if (!vMovimientos.isEmpty()) ivMovimiento =
		 * ((TVALMMovimiento)vMovimientos.get(0)).getiCveMovimiento() + 1; else
		 * ivMovimiento = 1;
		 * 
		 * vGuardar = DALMSumArt.FindByAll(" where ALMSumArt.iAnio = " + ivAnio
		 * + "   and ALMSumArt.iCveAlmacen = " + ivAlmacen +
		 * "   and ALMSumArt.iCveSolicSum = " + ivNumSolic , ""); } catch
		 * (Exception ex){ ex.printStackTrace(); }
		 * 
		 * if (!vGuardar.isEmpty()){ for (int i=0;i<vGuardar.size();i++){
		 * TVALMSumArt VALMSumArt = new TVALMSumArt(); VALMSumArt =
		 * (TVALMSumArt) vGuardar.get(i);
		 * 
		 * // Datos del Movimiento de Salida del Almacén. TVALMMovimiento
		 * VALMMovimiento = new TVALMMovimiento();
		 * VALMMovimiento.setiAnio(ivAnio);
		 * VALMMovimiento.setiCveAlmacen(ivAlmacen);
		 * VALMMovimiento.setiCveMovimiento(ivMovimiento);
		 * VALMMovimiento.setiCveSolicSum(ivNumSolic);
		 * VALMMovimiento.setiCveArticulo(VALMSumArt.getICveArticulo());
		 * VALMMovimiento.setiCveTpoMovimiento(2); // Movimiento de Salida
		 * VALMMovimiento.setiCveConcepto(1);
		 * VALMMovimiento.setdUnidades(VALMSumArt.getDUnidAutor());
		 * VALMMovimiento.setdtMovimiento(Fecha.TodaySQL());
		 * VALMMovimiento.setiCveUsuario(this.iCveUsuario);
		 * VALMMovimiento.setcObservacion(VALMSumArt.getCObservacion());
		 * VALMMovimiento.setlPNC(0); // Producto no Conforme. // Valores de las
		 * Existencias Generales. TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
		 * VALMArtxAlm.setiCveAlmacen(ivAlmacen);
		 * VALMArtxAlm.setiCveArticulo(VALMSumArt.getICveArticulo()); if
		 * (!vExistencias.isEmpty()){ for(int j=0;j<vExistencias.size();j++){
		 * TVALMArtxAlm VALMArtxAlmExistencia = new TVALMArtxAlm();
		 * VALMArtxAlmExistencia = (TVALMArtxAlm) vExistencias.get(j); if
		 * (VALMArtxAlmExistencia.getiCveArticulo() ==
		 * VALMSumArt.getICveArticulo()) dvExistencia =
		 * VALMArtxAlmExistencia.getdExistencia(); } }
		 * VALMArtxAlm.setdExistencia(dvExistencia -
		 * VALMSumArt.getDUnidAutor());
		 * 
		 * // Valores de las Existencias de los Lotes. if
		 * (!vExisLotes.isEmpty()){ double dvAcumula = 0; for (int
		 * j=0;j<vExisLotes.size();j++){ TVALMLote VALMLoteExistencia = new
		 * TVALMLote(); VALMLoteExistencia = (TVALMLote) vExisLotes.get(j); if
		 * (VALMLoteExistencia.getiCveArticulo() ==
		 * VALMSumArt.getICveArticulo()){ if (dvAcumula <
		 * VALMSumArt.getDUnidAutor()){ double dvFaltan =
		 * VALMSumArt.getDUnidAutor() - dvAcumula; double dvAplica = 0; double
		 * dvMovimiento = 0; if (dvFaltan >= VALMLoteExistencia.getdUnidades()){
		 * dvAcumula = dvAcumula + VALMLoteExistencia.getdUnidades();
		 * dvMovimiento = VALMLoteExistencia.getdUnidades(); dvAplica = 0; } if
		 * (dvFaltan < VALMLoteExistencia.getdUnidades()){ dvAcumula = dvAcumula
		 * + dvFaltan; dvMovimiento = dvFaltan; dvAplica =
		 * VALMLoteExistencia.getdUnidades() - dvFaltan; }
		 * 
		 * TVALMLote VALMLote = new TVALMLote();
		 * VALMLote.setiCveAlmacen(ivAlmacen);
		 * VALMLote.setiCveArticulo(VALMSumArt.getICveArticulo());
		 * VALMLote.setiCveLote(VALMLoteExistencia.getiCveLote());
		 * VALMLote.setdUnidades(dvAplica); if (dvAplica == 0)
		 * VALMLote.setdtAgotado(Fecha.TodaySQL()); vLotesAct.add(VALMLote);
		 * 
		 * //Datos del Movimiento de Salida del Almacen con Lote.
		 * TVALMDetalleMov VALMDetalleMov = new TVALMDetalleMov();
		 * VALMDetalleMov.setiAnio(ivAnio);
		 * VALMDetalleMov.setiCveAlmacen(ivAlmacen);
		 * VALMDetalleMov.setiCveMovimiento(ivMovimiento);
		 * VALMDetalleMov.setiCveLote(VALMLoteExistencia.getiCveLote()); // Lote
		 * del Articulo. VALMDetalleMov.setdUnidades(dvMovimiento);
		 * vDetalleMovIns.add(VALMDetalleMov); } } } }
		 * 
		 * // Inserción del Movimiento de Salida del Almacén. try{
		 * DALMMovimiento.insert(null,VALMMovimiento); ivMovimiento =
		 * ivMovimiento + 1; // Actualización de Existencias Generales.
		 * DALMArtxAlm.updExistencias(null,VALMArtxAlm); } catch (Exception ex){
		 * ex.printStackTrace(); }
		 * 
		 * // Actualización de Movimientos con Lotes. if
		 * (!vDetalleMovIns.isEmpty()){ for (int
		 * iii=0;iii<vDetalleMovIns.size();iii++){ TVALMDetalleMov
		 * VALMDetalleMov = new TVALMDetalleMov(); VALMDetalleMov =
		 * (TVALMDetalleMov) vDetalleMovIns.get(iii); try {
		 * DALMDetalleMov.insert(null,VALMDetalleMov); } catch (Exception ex) {
		 * ex.printStackTrace(); } } }
		 * 
		 * if (!vLotesAct.isEmpty()){ for (int ii=0;ii<vLotesAct.size();ii++){
		 * TVALMLote VALMLote = new TVALMLote(); VALMLote = (TVALMLote)
		 * vLotesAct.get(ii); try { DALMLote.updExistencias(null,VALMLote); }
		 * catch (Exception ex) { ex.printStackTrace(); } } } } // Valores para
		 * la Modificación de la Situación de la Solicitud. TVALMSolicSumin
		 * VALMSolicSumin = new TVALMSolicSumin();
		 * VALMSolicSumin.setIAnio(ivAnio);
		 * VALMSolicSumin.setICveAlmacen(ivAlmacen);
		 * VALMSolicSumin.setICveSolicSum(ivNumSolic);
		 * VALMSolicSumin.setDtSuministro(Fecha.TodaySQL());
		 * VALMSolicSumin.setLSuministrada(1); // Solicitud Suministrada. //
		 * Modificación de la Situación de la Solicitud. try{
		 * DALMSolicSumin.updSumistrada(null,VALMSolicSumin); } catch (Exception
		 * ex){ ex.printStackTrace(); } }
		 */
	}

	public void fillVector() {
		TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();

		cPaginas = "pg070802040.jsp|";

		// Vector de la Ubicación de los Artículos en el Almacen.

		try {

			TFechas dtFecha = new TFechas();
			int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

			String cFiltro = "";
			String cICveAlmacen = "" + request.getParameter("iCveAlmacen");
			String cICvePrioridad = ""
					+ request.getParameter("iCveTpoPrioridad");
			String cICvePeriodo = "" + request.getParameter("iCvePeriodo");
			String cProgramada = "" + request.getParameter("lProgramada");

			if (cICveAlmacen.compareTo("null") != 0
					&& cICveAlmacen.compareTo("") != 0
					&& cICveAlmacen.compareTo("0") != 0) {
				cFiltro = " Where ALMSolicSumin.iCveAlmacen = " + cICveAlmacen;

				if (cICvePrioridad.compareTo("null") != 0
						&& cICvePrioridad.compareTo("") != 0
						&& cICvePrioridad.compareTo("0") != 0) {
					cFiltro += " and  almtpoprioridad.iCvePrioridad = "
							+ cICvePrioridad;
				}

				if (cICvePeriodo.compareTo("null") != 0
						&& cICvePeriodo.compareTo("") != 0
						&& cICvePeriodo.compareTo("0") != 0) {
					cFiltro += " and  ALMSolicSumin.iCvePeriodo = "
							+ cICvePeriodo;
				}
				cFiltro += " and ALMSolicSumin.iAnio = " + iAnio;

				if (cProgramada.compareTo("null") != 0
						&& cProgramada.compareTo("") != 0
						&& cProgramada.compareToIgnoreCase("null") != 0) {
					cFiltro += " and  lProgramada = " + cProgramada;
				}
				cFiltro += " and ALMSolicSumin.iAnio = " + iAnio
						+ " and lAutorizada = 1 and lRevisada = 1";
			}

			if (cFiltro.compareToIgnoreCase("") != 0) {
				if (cCondicion.compareToIgnoreCase("") != 0)
					cFiltro = cFiltro + " and " + cCondicion;
			} else {
				if (cCondicion.compareToIgnoreCase("") != 0)
					cFiltro = " where " + cCondicion;
			}

			if (cFiltro.compareTo("null") != 0 && cFiltro.compareTo("") != 0)
				vDespliega = dALMSolicSumin.FindByAll(cFiltro, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
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

	public void setICveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
	}

	public boolean isBFlag() {
		return bFlag;
	}

	private void setBFlag(boolean bFlag) {
		this.bFlag = bFlag;
	}
}
