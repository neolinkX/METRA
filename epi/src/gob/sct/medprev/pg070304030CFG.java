package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070304030CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vLoteCuantita = new Vector();
	public Vector vCalibCuantita = new Vector();
	private StringBuffer activeX = new StringBuffer("");
	String cvAnio = "";
	int ivSustancia = 0;
	int ivCveLaboratorio = 0;
	int ivLoteCuantita = 0;
	int ivEquipo = 0;

	// TVUsuario vUsuario = (TVUsuario)
	// httpReq.getSession().getAttribute("UsrID");

	public pg070304030CFG() {
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

		if (request.getParameter("SLSLoteConfirmativo1") != null)
			ivLoteCuantita = new Integer(request.getParameter(
					"SLSLoteConfirmativo1").toString()).intValue();

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
							+
							// "   and TOXLoteCuantita.dtAnalisis      is null     "
							// +
							// "   and TOXLoteCuantita.dtAutorizacion  is null     "
							// +
							// "   and TOXLoteCuantita.dtCalibracion   is null     "
							// +
							" ");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {

			vCalibCuantita = dCalibCuantita
					.FindByAll(" where icvesustancia = "
							+ ivSustancia
							+ "   and iCveLaboratorio = "
							+ ivCveLaboratorio
							+ "   and iCveCalib = (select max(iCveCalib) from TOXCalibCuantita "
							+ "                     where iCveLaboratorio = "
							+ ivCveLaboratorio + " )");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void fillVector() {

		cPaginas = "pg070304031.jsp|";

		TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();

		if (cCondicion.compareTo("") != 0) {
			cCondicion = " where " + cCondicion;
		}

		cCondicion = " where iAnio = " + cvAnio + " and iCveSustancia = "
				+ ivSustancia + " and iCveLoteCuantita = " + ivLoteCuantita
				+ " and iCveLaboratorio = " + ivCveLaboratorio + "";
		// Generaci�n del Reporte
		if (request.getParameter("hdBoton").compareToIgnoreCase(
				"Generar Reporte") == 0)
			activeX.append(this.GenRep(" where CA.iAnio = " + cvAnio
					+ "   and CA.iCveLaboratorio = " + ivCveLaboratorio
					+ "   and CA.iCveSustancia = " + ivSustancia
					+ "   and CA.iCveLoteCuantita = " + ivLoteCuantita));

		if (cOrden.compareToIgnoreCase("") == 0)
			cOrden = " order by iCveAnalisis ";

		if (cOrden.compareTo("") != 0) {
			cCondicion = cCondicion + cOrden;
		}

		try {
			vDespliega = DTOXCuantAnalisis.FindByAll(cCondicion);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "Hidden";
			cImprimir = "Reporte";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}

	}

	public StringBuffer GenRep(String cCondicion) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas("07");
		String cNomArchivo = new String("Lote_");
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
			// Presentar informaci�n del Lote
			xl.comDespliega("H", iRengI++, vLote.getCLote());
			xl.comDespliega("H", iRengI++,
					pFecha.getFechaDDMMMMMYYYY(vLote.getdtGeneracion(), " de "));
			xl.comDespliega("B", iRengI++, "CONFIRMACI�N CG/EM A "
					+ vLote.VSustancia.getCTitRepConf());
			iRengI += 3;
			xl.comCreaFormula("B", iRengI++, vLote.VEquipo.getCDscEquipo());
			xl.comDespliega("C", iRengI++, vLote.VEquipo.getCModelo());
			xl.comDespliega("C", iRengI++, vLote.VEquipo.getCNumSerie());
			cNomArchivo += vLote.getCLote().replace('/', '-');
			cNomArchivo.replaceAll("/", "-");

			// Presentar informaci�n de los an�lisis del lote.
			iRengI = 20;
			int iReng = 0, iColumna = 0;
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

			int iRengC = (int) ((vReporte.size() - 1) % 2) == 0 ? (int) ((vReporte
					.size() - 1) / 2) : (int) ((vReporte.size() - 1) / 2) + 1;
			for (int i = 1; i < vReporte.size(); i++) {
				if (iReng >= iRengC) {
					xl.comBordeTotal(cCeldas.get(iColumna + 1).toString(),
							iRengI, cCeldas.get(iColumna + 3).toString(),
							iRengI + iReng - 1, 1);
					iColumna += 5;
					iReng = 0;
				}
				vAnalisis = new TVTOXCuantAnalisis();
				vAnalisis = (TVTOXCuantAnalisis) vReporte.get(i);
				xl.comDespliega(cCeldas.get(iColumna).toString(), iRengI
						+ iReng, String.valueOf(vAnalisis.getiPosicion()));
				if (vAnalisis.getlControl().equals(new Integer(0))
						&& vAnalisis.getCDscCalib() == null) {
					if (vAnalisis.getiCveSustancia().intValue() > 1)
						xl.comDespliega(cCeldas.get(iColumna + 1).toString(),
								iRengI + iReng, vAnalisis.getCAnalisis()
										+ " - M�ltiple");
					else
						xl.comDespliega(cCeldas.get(iColumna + 1).toString(),
								iRengI + iReng, vAnalisis.getCAnalisis());
				} else
					xl.comDespliega(cCeldas.get(iColumna + 1).toString(),
							iRengI + iReng, vAnalisis.getCDscCalib());

				xl.comAlineaRango(cCeldas.get(iColumna + 1).toString(), iRengI
						+ iReng, cCeldas.get(iColumna + 2).toString(), iRengI
						+ iReng, xl.getAT_COMBINA_IZQUIERDA());
				xl.comDespliega(cCeldas.get(iColumna + 3).toString(), iRengI
						+ iReng,
						vAnalisis.getiDilusion().intValue() > 1 ? "'1:"
								+ String.valueOf(vAnalisis.getiDilusion())
								: "'1:D");
				iReng++;
			}
			xl.comBordeTotal(cCeldas.get(iColumna + 1).toString(), iRengI,
					cCeldas.get(iColumna + 3).toString(), iRengI + iReng - 1, 1);
			iReng = iRengI + iRengC;
			// Presentar final del reporte
			iRengI = iReng + 2;
			xl.comDespliega("A", iRengI++,
					"CADENA DE CUSTODIA DE LAS ALICUOTAS");
			iReng = ++iRengI;
			xl.comDespliega("B", iReng, "FECHA");
			xl.comAlineaRango("B", iReng, "C", iReng, xl.getAT_COMBINA_CENTRO());
			xl.comDespliega("D", iReng, "LIBERADA POR ");
			xl.comAlineaRango("D", iReng, "E", iReng, xl.getAT_COMBINA_CENTRO());
			xl.comDespliega("F", iReng, "RECIBIDA POR ");
			xl.comAlineaRango("F", iReng, "G", iReng, xl.getAT_COMBINA_CENTRO());
			xl.comDespliega("H", iReng, "MOTIVO DEL CAMBIO");
			xl.comAlineaRango("H", iReng, "I", iReng, xl.getAT_COMBINA_CENTRO());
			xl.comFillCells("B", iRengI, "I", iReng, 15);
			xl.comFontBold("B", iReng, "I", iReng);
			for (int i = 1; i <= 7; i++) {
				iReng++;
				xl.comAlineaRango("B", iReng, "C", iReng,
						xl.getAT_COMBINA_CENTRO());
				xl.comAlineaRango("D", iReng, "E", iReng,
						xl.getAT_COMBINA_CENTRO());
				xl.comAlineaRango("F", iReng, "G", iReng,
						xl.getAT_COMBINA_CENTRO());
				xl.comAlineaRango("H", iReng, "I", iReng,
						xl.getAT_COMBINA_CENTRO());
			}
			xl.comBordeTotal("B", iRengI, "I", iReng, 1);
			/*
			 * iReng += 2; xl.comDespliega("D", iReng, "FECHA:");
			 * xl.comAlineaRango("D", iReng, "D", iReng, xl.getAT_HDERECHA());
			 * xl.comFontBold("D", iReng, "D", iReng++); xl.comDespliega("D",
			 * iReng, "LOTE REVISADO POR:"); xl.comAlineaRango("D", iReng, "D",
			 * iReng, xl.getAT_HDERECHA()); xl.comFontBold("D", iReng, "D",
			 * iReng++);
			 */

			iReng += 2;
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
		buffer = xl.creaActiveX("pg070304030", cNomArchivo, false, false, true);
		return buffer;
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

	public String getActiveX() {
		return activeX.toString();
	}

}