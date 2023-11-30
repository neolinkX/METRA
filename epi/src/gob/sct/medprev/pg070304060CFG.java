package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070304060CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vLoteCuantita = new Vector();
	public Vector vCalibCuantita = new Vector();
	public Vector vDespliegaD = new Vector();
	String cvAnio = "";
	int ivSustancia = 0;
	int ivCveLaboratorio = 0;
	int ivLoteCuantita = 0;
	int ivEquipo = 0;
	TVUsuario vUsuario;
	private StringBuffer activeX = new StringBuffer("");

	public pg070304060CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {

		TDTOXLoteCuantita dLoteCuantita = new TDTOXLoteCuantita();
		TDTOXCalibCuantita dCalibCuantita = new TDTOXCalibCuantita();

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

		if (request.getParameter("SLSLoteConfirmativo2") != null)
			ivLoteCuantita = new Integer(request.getParameter(
					"SLSLoteConfirmativo2").toString()).intValue();

		try {

			vLoteCuantita = dLoteCuantita
					.FindByAll(" where TOXLoteCuantita.iAnio           =           "
							+ cvAnio
							+ "   and TOXLoteCuantita.iCveLaboratorio =           "
							+ ivCveLaboratorio
							+ "   and TOXLoteCuantita.iCveSustancia   =           "
							+ ivSustancia
							+ "   and TOXLoteCuantita.dtGeneracion    is not null "
							+ "   and TOXLoteCuantita.iCveUsuGenera   is not null "
							+ "   and TOXLoteCuantita.dtCalibracion   is not null "
							+ "   and TOXLoteCuantita.lValidaCalib = 1            "
							+
							// "   and TOXLoteCuantita.dtAnalisis      is null     "
							// +
							// "   and TOXLoteCuantita.dtAutorizacion  is null     "
							// +

							" ");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {

			vCalibCuantita = dCalibCuantita
					.FindByAll(" where iCveLaboratorio = "
							+ ivCveLaboratorio
							+ "   and iCveCalib = (select max(iCveCalib) from TOXCalibCuantita "
							+ "                     where iCveLaboratorio = "
							+ ivCveLaboratorio + " )");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Guardar() {

		TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();
		TFechas Fecha = new TFechas();
		Vector vTOXCuantAnalisis = new Vector();
		String cvNomDensidad = "";
		String cCond = "";
		StringBuffer filtro = new StringBuffer("");
		Vector vCorte = new Vector();
		TVTOXCorteXSust Corte = new TVTOXCorteXSust();

		// Buscar el corte para evaluar los positivos y los negativos.
		filtro.append("where a.iAnio = ").append(cvAnio)
				.append("  and a.iCveLaboratorio  = ").append(ivCveLaboratorio)
				.append("  and a.iCveSustancia    = ").append(ivSustancia)
				.append("  and a.iCveLoteCuantita = ").append(ivLoteCuantita);

		cCond = " where iAnio = " + cvAnio + " and iCveLaboratorio = "
				+ ivCveLaboratorio + " and iCveSustancia = " + ivSustancia
				+ " and iCveLoteCuantita = " + ivLoteCuantita
				+ " and iCveAnalisis    > 0 " + "";

		try {
			vCorte = DTOXCuantAnalisis.FindCorte(filtro.toString());
			vTOXCuantAnalisis = DTOXCuantAnalisis.FindByAll(cCond);
			if (!vCorte.isEmpty())
				Corte = (TVTOXCorteXSust) vCorte.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!vTOXCuantAnalisis.isEmpty()) {
			for (int i = 0; i < vTOXCuantAnalisis.size(); i++) {
				TVTOXCuantAnalisis VAnalisis = new TVTOXCuantAnalisis();
				VAnalisis = (TVTOXCuantAnalisis) vTOXCuantAnalisis.get(i);
				cvNomDensidad = "FILConc"
						+ VAnalisis.getiCveAnalisis().toString();
				if (request.getParameter(cvNomDensidad) != null) {
					if (request.getParameter(cvNomDensidad).toString()
							.compareToIgnoreCase("") != 0) {
						double dvResultado = new Double(
								request.getParameter(cvNomDensidad))
								.doubleValue()
								* VAnalisis.getiDilusion().intValue();
						VAnalisis.setdResultado(new Double(dvResultado));
						VAnalisis.setdResultadoDil(new Double(request
								.getParameter(cvNomDensidad)));
						if (request.getParameter("FILTmpRetenc"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDTmpRetenc(new Double(request
									.getParameter(
											"FILTmpRetenc"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis
									.setDTmpRetenc(new Double(0).doubleValue());
						if (request.getParameter("FILTmpRetencD"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDTmpRetencD(new Double(request
									.getParameter(
											"FILTmpRetencD"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDTmpRetencD(new Double(0)
									.doubleValue());
						if (request.getParameter("FILIon01"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon01(new Double(request
									.getParameter(
											"FILIon01"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon01(new Double(0).doubleValue());
						if (request.getParameter("FILIon02"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon02(new Double(request
									.getParameter(
											"FILIon02"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon02(new Double(0).doubleValue());
						if (request.getParameter("FILIon03"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon03(new Double(request
									.getParameter(
											"FILIon03"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon03(new Double(0).doubleValue());
						if (request.getParameter("FILIon04"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon04(new Double(request
									.getParameter(
											"FILIon04"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon04(new Double(0).doubleValue());
						if (request.getParameter("FILIon05"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon05(new Double(request
									.getParameter(
											"FILIon05"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon05(new Double(0).doubleValue());

						if (VAnalisis.getdResultado().doubleValue() >= Corte
								.getdCorte().doubleValue())
							VAnalisis.setlResultado(new Integer(1));
						else
							VAnalisis.setlResultado(new Integer(0));
						if (VAnalisis.getDTmpRetenc() > 0)
							VAnalisis.setLRegistrado(new Integer(1));
						else
							VAnalisis.setLRegistrado(new Integer(0));
						// Actualizando el Valor de la Densidad en la Tabla.
						try {
							DTOXCuantAnalisis.updResultado(VAnalisis);

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}

		try {
			vTOXCuantAnalisis = DTOXCuantAnalisis.CountByAll(cCond
					+ " and lRegistrado = 0 ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!vTOXCuantAnalisis.isEmpty()) {
			for (int i = 0; i < vTOXCuantAnalisis.size(); i++) {
				TVTOXCuantAnalisis VTOXCuantAnalisis = new TVTOXCuantAnalisis();
				VTOXCuantAnalisis = (TVTOXCuantAnalisis) vTOXCuantAnalisis
						.get(i);

				if (VTOXCuantAnalisis.getiAnio().intValue() == 0) {
					TDTOXLoteCuantita DTOXLoteCuantita = new TDTOXLoteCuantita();
					TVTOXLoteCuantita VTOXLoteCuantita = new TVTOXLoteCuantita();

					VTOXLoteCuantita.setiAnio(new Integer(cvAnio));
					VTOXLoteCuantita.setiCveLaboratorio(new Integer(
							ivCveLaboratorio));
					VTOXLoteCuantita.setiCveSustancia(new Integer(ivSustancia));
					VTOXLoteCuantita.setiCveLoteCuantita(new Integer(
							ivLoteCuantita));
					VTOXLoteCuantita.setdtAnalisis(Fecha.TodaySQL());
					vUsuario = (TVUsuario) httpReq.getSession().getAttribute(
							"UsrID");
					VTOXLoteCuantita.setiCveUsuAnalista(new Integer(vUsuario
							.getICveusuario()));

					try {
						DTOXLoteCuantita.updResultado(VTOXLoteCuantita);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}

	}

	public void fillVector() {

		cPaginas = "pg070304061.jsp|";

		TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();

		if (cCondicion.compareTo("") != 0) {
			cCondicion = " where " + cCondicion;
		}

		cCondicion = " where iAnio = " + cvAnio + " and iCveSustancia = "
				+ ivSustancia + " and iCveLoteCuantita = " + ivLoteCuantita
				+ " and iCveLaboratorio = " + ivCveLaboratorio
				+ " and iCveAnalisis    > 0 " + "";
		// Generación del Reporte
		if (request.getParameter("hdBoton").compareToIgnoreCase(
				"Generar Reporte") == 0)
			activeX.append(this.GenRep(" where CA.iAnio = " + cvAnio
					+ "   and CA.iCveLaboratorio = " + ivCveLaboratorio
					+ "   and CA.iCveSustancia = " + ivSustancia
					+ "   and CA.iCveLoteCuantita = " + ivLoteCuantita));

		if (cOrden.compareTo("") == 0)
			cOrden = " order by ianio, iCveSustancia, iCveLoteCuantita, iCveAnalisis, iCveLaboratorio  ";

		if (cOrden.compareTo("") != 0) {
			cCondicion = cCondicion + cOrden;
		}

		try {
			vDespliega = DTOXCuantAnalisis.FindByAll(cCondicion);
			vDespliegaD = vDespliega;
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			// Validar si se coloca el panel de actualización
			try {
				String cCond = " where iAnio = " + cvAnio
						+ " and iCveLaboratorio = " + ivCveLaboratorio
						+ " and iCveSustancia = " + ivSustancia
						+ " and iCveLoteCuantita = " + ivLoteCuantita
						+ " and iCveAnalisis    > 0 ";
				Vector vTOXCuantAnalisis = DTOXCuantAnalisis.CountByAll(cCond
						+ " and lRegistrado = 0 ");
				if (!vTOXCuantAnalisis.isEmpty())
					UpdStatus = "SaveCancelOnly";
				else
					UpdStatus = "Hidden";
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			cImprimir = "Reporte";
		} else
			UpdStatus = "Hidden";

	}

	public StringBuffer GenRep(String cCondicion) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas("07");
		String cNomArchivo = new String("Resultado_");
		int iRengI = 8;
		StringBuffer buffer = new StringBuffer();
		try {
			TDTOXCuantAnalisis DCuantA = new TDTOXCuantAnalisis();
			Vector vReporte = new Vector();
			vReporte = DCuantA.FindReporte(cCondicion);
			if (vReporte.size() == 0) {
				vErrores.acumulaError("", 15, "");
				return buffer;
			}
			TVTOXLoteCuantita vLote = new TVTOXLoteCuantita();
			vLote = (TVTOXLoteCuantita) vReporte.get(0);
			// Presentar información del Lote
			xl.comDespliega("H", iRengI++, vLote.getCLote());
			xl.comDespliega("H", iRengI++,
					pFecha.getFechaDDMMMMMYYYY(vLote.getdtGeneracion(), " de "));
			xl.comDespliega("B", iRengI++, "CONFIRMACIÓN CG/EM A "
					+ vLote.VSustancia.getCTitRepConf());
			xl.comDespliega("H", iRengI++,
					pFecha.getFechaDDMMMMMYYYY(vLote.getdtAnalisis(), " de "));
			iRengI += 2;
			xl.comCreaFormula("B", iRengI++, vLote.VEquipo.getCCveEquipo());
			xl.comDespliega("C", iRengI++, vLote.VEquipo.getCModelo());
			xl.comDespliega("C", iRengI++, vLote.VEquipo.getCNumSerie());
			cNomArchivo += vLote.getCLote().replace('/', '-');
			cNomArchivo.replaceAll("/", "-");

			// Presentar información de los análisis del lote.
			iRengI = 20;
			int iReng = 20, iColumna = 0;
			TVTOXCuantAnalisis vAnalisis;
			Vector cCeldas = new Vector();
			cCeldas.add("A");
			cCeldas.add("B");
			cCeldas.add("C");
			cCeldas.add("D");
			cCeldas.add("E");
			cCeldas.add("F");
			cCeldas.add("G");
			cCeldas.add("H");
			cCeldas.add("I");

			for (int i = 1; i < vReporte.size(); i++) {
				vAnalisis = new TVTOXCuantAnalisis();
				vAnalisis = (TVTOXCuantAnalisis) vReporte.get(i);
				xl.comDespliega("A", iReng,
						String.valueOf(vAnalisis.getiPosicion()));
				if (vAnalisis.getlControl().equals(new Integer(0))
						&& vAnalisis.getCDscCalib() == null) {
					if (vAnalisis.getiCveSustancia().intValue() > 1)
						xl.comDespliega("B", iReng, vAnalisis.getCAnalisis()
								+ " - Múltiple");
					else
						xl.comDespliega("B", iReng, vAnalisis.getCAnalisis());
					xl.comDespliega("H", iReng, vAnalisis.getlResultado()
							.intValue() == 0 ? "NEGATIVO" : "POSITIVO");
				} else
					xl.comDespliega("B", iReng, vAnalisis.getCDscCalib());

				xl.comDespliega("I", iReng,
						vAnalisis.getLCorrecto().intValue() == 0 ? "INCORRECTO"
								: "CORRECTO");
				xl.comAlineaRango("B", iReng, "C", iReng,
						xl.getAT_COMBINA_IZQUIERDA());

				xl.comDespliega("F", iReng, vAnalisis.getdResultado()
						.toString());

				if (vAnalisis.getlResultado().intValue() == 1)
					xl.comFontBold("H", iReng, "H", iReng);
				if (vAnalisis.getLCorrecto().intValue() == 0)
					xl.comFontBold("I", iReng, "I", iReng);

				// xl.comAlineaRango("H", iReng, "I", iReng,
				// xl.getAT_COMBINA_IZQUIERDA());
				xl.comDespliega("E", iReng,
						vAnalisis.getiDilusion().intValue() > 1 ? "'1:"
								+ String.valueOf(vAnalisis.getiDilusion())
								: "'1:D");
				iReng++;
			}
			xl.comBordeTotal("B", iRengI, "I", iReng - 1, 1);

			// Presentar final del reporte
			iReng += 2;
			xl.comDespliega("D", iReng, "FECHA DE ENTREGA:");
			xl.comAlineaRango("D", iReng, "D", iReng, xl.getAT_HDERECHA());
			xl.comFontBold("D", iReng, "D", iReng++);
			xl.comDespliega("D", iReng, "POR:");
			xl.comAlineaRango("D", iReng, "D", iReng, xl.getAT_HDERECHA());
			xl.comFontBold("D", iReng, "D", iReng++);

			iReng += 5;

			xl.comDespliega("D", iReng, "FECHA DE REVISIÓN:");
			xl.comAlineaRango("D", iReng, "D", iReng, xl.getAT_HDERECHA());
			xl.comFontBold("D", iReng, "D", iReng++);
			xl.comDespliega("D", iReng, "POR:");
			xl.comAlineaRango("D", iReng, "D", iReng, xl.getAT_HDERECHA());
			xl.comFontBold("D", iReng, "D", iReng++);

			iReng += 5;
			xl.comDespliega("B", iReng, "OBSERVACIONES:");
			xl.comAlineaRango("B", iReng, "I", iReng + 4,
					xl.getAT_COMBINA_IZQUIERDA());
			xl.comAlineaRangoVer("B", iReng, "I", iReng + 4,
					xl.getAT_VSUPERIOR());

			xl.comBordeTotal("B", iReng, "I", iReng + 4, 1);

		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Generar Reporte : " +
			// ex.getMessage());
		}
		buffer = xl.creaActiveX("pg070304060", cNomArchivo, false, false, true);
		return buffer;
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

}
