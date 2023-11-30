package com.micper.util;

// por implementar: Saltos de Página | Inserta Imagen | etc.

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 * Forma de llamar:    TExcel vExcel = new TExcel("Nombre_Archivo_Sin_Extensión");
 *                     vExcel.comAbreLibro();
 *                     vExcel.comAlineaRango();
 *                     vExcel.comAlineaRangoVer();
 *                     vExcel.comAltoReng();
 *                     vExcel.comBordeRededor();
 *                     vExcel.comBordeTotal();
 *                     vExcel.comCellFormat();
 *                     vExcel.comCopiaHoja();
 *                     vExcel.comCreaFormula();
 *                     vExcel.comDeleteCols();
 *                     vExcel.comDespliega();
 *                     vExcel.comEligeHoja();
 *                     vExcel.comFillCells();
 *                     vExcel.comFontBold();
 *                     vExcel.comFontColor();
 *                     vExcel.comFontNormal();
 *                     vExcel.comFontSize();
 *                     vExcel.comReplace();
 *                     vExcel.comTamanioFontGral();
 *                     out.println(vExcel.creaActiveX("01_01", "", false, false, true));
 */
import java.util.*;
import com.micper.util.*;
import com.micper.ingsw.*;

public class TExcel {
	private String cClassID = "";
	private String cCodeBase = "";
	private String cRutaOrig = "";
	private String cRutaDest = "";
	private String cRutaServidor;
	private String cDelim = "|";
	private String cNumModulo = "";
	private StringBuffer sbDespliega = null;
	private String AT_HIZQUIERDA = "IZQUIERDA";
	private String AT_HCENTRO = "CENTRO";
	private String AT_HDERECHA = "DERECHA";
	private String AT_HJUSTIFICA = "JUSTIFICA";
	private String AT_HCENTRO_SELECCION = "CENTRO_SELECCION";
	private String AT_COMBINA_CENTRO = "COMBINA_CENTRO";
	private String AT_COMBINA_IZQUIERDA = "COMBINA_IZQUIERDA";
	private String AT_COMBINA_DERECHA = "COMBINA_DERECHA";
	private String AT_DESCOMBINA = "DESCOMBINA";
	private String AT_VSUPERIOR = "SUPERIOR";
	private String AT_VCENTRO = "CENTRO";
	private String AT_VINFERIOR = "INFERIOR";
	private String AT_VJUSTIFICAR = "JUSTIFICAR";
	private String AT_VAJUSTAR = "AJUSTAR";
	private String AT_POSINICIAL = "Inicio";
	private String AT_POSFINAL = "Final";

	public TExcel(String cModulo) {
		this.cNumModulo = cModulo;
		TParametro vParametros = new TParametro(this.cNumModulo);
		this.cClassID = vParametros.getPropEspecifica("ExcelClassID");
		this.cCodeBase = vParametros.getPropEspecifica("ExcelCodeBase");
		this.cRutaOrig = vParametros.getPropEspecifica("ExcelRutaOrig");
		this.cRutaDest = vParametros.getPropEspecifica("ExcelRutaDest");
		this.cRutaServidor = vParametros.getPropEspecifica("ExcelRutaServidor");
		this.sbDespliega = new StringBuffer();
	}

	public StringBuffer creaActiveX(String cArchivoOrig, String cArchivoDest,
			boolean lAutoImprimir, boolean lCerrarAlFinal, boolean lVisible) {
		StringBuffer sbRes = new StringBuffer();
		sbRes.append("<OBJECT");
		sbRes.append(" classid=\"" + cClassID + "\" ");
		sbRes.append(" codebase=\"" + cCodeBase + "\" ");
		sbRes.append(" width=1 height=1 ");
		sbRes.append(" align=center ");
		sbRes.append(" hspace=0 vspace=0 ");
		sbRes.append(">");
		sbRes.append("\n<PARAM NAME=\"CF\" VALUE=\"FILENAME=" + cRutaOrig
				+ cArchivoOrig + ".xls");
		if (lVisible)
			sbRes.append("|1");
		else
			sbRes.append("|0");
		sbRes.append(this.sbDespliega);
		if (cArchivoDest.compareToIgnoreCase("") != 0)
			sbRes.append("\nGUARDARCOMO=" + cRutaDest + cArchivoDest);
		if (lAutoImprimir)
			sbRes.append("\nIMPRIMIR=SI");
		if (lCerrarAlFinal)
			sbRes.append("\nCERRAR=SI");
		sbRes.append("\n\">");
		sbRes.append("\n</OBJECT>");
		return sbRes;
	}

	public StringBuffer creaActiveX(String cArchivoOrig, String cArchivoDest,
			boolean lAutoImprimir, int iNumCopias, int iTiempoEspera,
			boolean lCerrarAlFinal, boolean lVisible) {
		StringBuffer sbRes = new StringBuffer();
		sbRes.append("<OBJECT");
		sbRes.append(" classid=\"" + cClassID + "\" ");
		sbRes.append(" codebase=\"" + cCodeBase + "\" ");
		sbRes.append(" width=1 height=1 ");
		sbRes.append(" align=center ");
		sbRes.append(" hspace=0 vspace=0 ");
		sbRes.append(">");
		sbRes.append("\n<PARAM NAME=\"CF\" VALUE=\"FILENAME=" + cRutaOrig
				+ cArchivoOrig + ".xls");
		sbRes.append((lVisible ? "|1" : "|0"));
		sbRes.append(this.sbDespliega);
		if (cArchivoDest.compareToIgnoreCase("") != 0)
			sbRes.append("\nGUARDARCOMO=" + cRutaDest + cArchivoDest);
		if (lAutoImprimir) {
			if (iNumCopias < 1)
				iNumCopias = 1;
			if (iTiempoEspera < 0)
				iTiempoEspera = 0;
			for (int i = 1; i <= iNumCopias; i++) {
				sbRes.append("\nIMPRIMIR=SI");
				if (iTiempoEspera > 0)
					sbRes.append("\nDELAY=" + iTiempoEspera);
			}
		}
		if (lCerrarAlFinal)
			sbRes.append("\nCERRAR=SI");
		sbRes.append("\n\">");
		sbRes.append("\n</OBJECT>");
		return sbRes;
	}

	/**
	 * Agregada por Rafael Alcocer Caldera 29/Ago/2006
	 * 
	 * @param cArchivoOrig
	 *            String
	 * @param cArchivoDest
	 *            String
	 * @param lAutoImprimir
	 *            boolean
	 * @param lCerrarAlFinal
	 *            boolean
	 * @param lVisible
	 *            boolean
	 * @return StringBuffer
	 */
	public StringBuffer creaActiveX(
			// String cArchivoOrig,
			String cArchivoDest, boolean lAutoImprimir, boolean lCerrarAlFinal,
			boolean lVisible) {
		StringBuffer sbRes = new StringBuffer();
		sbRes.append("<OBJECT");
		sbRes.append(" classid=\"" + cClassID + "\" ");
		sbRes.append(" codebase=\"" + cCodeBase + "\" ");
		sbRes.append(" width=1 height=1 ");
		sbRes.append(" align=center ");
		sbRes.append(" hspace=0 vspace=0 ");
		sbRes.append(">");
		sbRes.append("\n<PARAM NAME=\"CF\" VALUE=\"FILENAME=" + cRutaServidor
				+ cArchivoDest + ".xls");
		if (lVisible)
			sbRes.append("|1");
		else
			sbRes.append("|0");
		sbRes.append(this.sbDespliega);
		if (cArchivoDest.compareToIgnoreCase("") != 0)
			sbRes.append("\nGUARDARCOMO=" + cRutaDest + cArchivoDest);
		if (lAutoImprimir)
			sbRes.append("\nIMPRIMIR=SI");
		if (lCerrarAlFinal)
			sbRes.append("\nCERRAR=SI");
		sbRes.append("\n\">");
		sbRes.append("\n</OBJECT>");
		return sbRes;
	}

	public void comImprimir(int iNumCopias, int iTiempoEspera) {
		if (iNumCopias < 1)
			iNumCopias = 1;
		for (int i = 1; i <= iNumCopias; i++) {
			this.sbDespliega.append("\nIMPRIMIR=SI");
			if (iTiempoEspera > 0)
				this.comDelay(iTiempoEspera);
		}
	}

	public void comImprimir(int iNumCopias) {
		this.comImprimir(iNumCopias, 0);
	}

	public void comDelay(int iTiempoEspera) {
		if (iTiempoEspera < 0)
			iTiempoEspera = 0;
		if (iTiempoEspera > 0)
			this.sbDespliega.append("\nDELAY=" + iTiempoEspera);
	}

	public void comDespliega(String cCol, int iReng, String cValor) {
		this.sbDespliega.append("\nDESPLIEGA=" + cCol + cDelim + iReng + cDelim
				+ cValor);
	}

	public void comAlineaRango(String cColIni, int iRengIni, String cColFin,
			int iRengFin, String cAlineacion) {
		// IZQUIERDA|CENTRO|DERECHA|JUSTIFICA|CENTRO_SELECCION|DESCOMBINA|
		// COMBINA_CENTRO|COMBINA_IZQUIERDA|COMBINA_DERECHA|COMBINA_JUSTIFICA
		this.sbDespliega.append("\nALINEARANGO=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin + cDelim + cAlineacion);
	}

	public void comAlineaRangoVer(String cColIni, int iRengIni, String cColFin,
			int iRengFin, String cAlineacion) {
		// SUPERIOR|CENTRO|INFERIOR|JUSTIFICAR|AJUSTAR
		this.sbDespliega.append("\nALINEARANGOVER=" + cColIni + cDelim
				+ iRengIni + cDelim + cColFin + cDelim + iRengFin + cDelim
				+ cAlineacion);
	}

	public void comReplace(String cValBuscar, String cValAsignar) {
		this.sbDespliega.append("\nREPLACE=" + cValBuscar + cDelim
				+ cValAsignar);
	}

	public void comAltoReng(String cCol, int iReng, float fAlto) {
		// this.sbDespliega.append("\nALTORENG=" + cCol + cDelim + iReng +
		// cDelim + fAlto);
	}

	public void comBordeRededor(String cColIni, int iRengIni, String cColFin,
			int iRengFin, int iTipo, int iColor) {
		this.sbDespliega.append("\nBORDEREDEDOR=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin + cDelim + iTipo
				+ cDelim + iColor);
	}

	public void comBorde(String cColIni, int iRengIni, String cColFin,
			int iRengFin, int iPosicion, int iEstiloLinea, int iPeso, int iColor) {
		// this.sbDespliega.append("\nBORDES=" + cColIni + cDelim + iRengIni +
		// cDelim + cColFin + cDelim + iRengFin + cDelim + iPosicion + cDelim +
		// iEstiloLinea + cDelim + iPeso + cDelim + iColor );
	}

	public void comBordeTotal(String cColIni, int iRengIni, String cColFin,
			int iRengFin, int iTipo) {
		this.sbDespliega.append("\nBORDETOTAL=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin + cDelim + iTipo);
	}

	public void comCellFormat(String cColIni, int iRengIni, String cColFin,
			int iRengFin, String cFormato) {
		this.sbDespliega.append("\nCELLFORMAT=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin + cDelim + cFormato);
	}

	public void comCreaFormula(String cCol, int iReng, String cFormula) {
		this.sbDespliega.append("\nCREAFORMULA=" + cCol + cDelim + iReng
				+ cDelim + cFormula);
	}

	public void comDeleteCols(String cColIni, int iRengIni, String cColFin,
			int iRengFin) {
		this.sbDespliega.append("\nDELETECELDAS=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin);
	}

	public void comAbreLibro(String cArchivo, boolean lVisible) {
		this.sbDespliega.append("\nABRELIBRO=" + cArchivo);
		if (lVisible)
			this.sbDespliega.append(cDelim + "1");
		else
			this.sbDespliega.append(cDelim + "0");
	}

	public void comCopiaHoja(int iNumHoja, String cPosicion, String cNombre) {
		this.sbDespliega.append("\nCOPIAHOJA=" + iNumHoja + cDelim + cPosicion
				+ cDelim + cNombre);
	}

	public void comEligeHoja(int iNumHoja) {
		this.sbDespliega.append("\nELIGEHOJA=" + iNumHoja);
	}

	public void comTamanioFontGral(int iTamanio) {
		this.sbDespliega.append("\nTAMANIOFONTGRAL=" + iTamanio);
	}

	public void comFillCells(String cColIni, int iRengIni, String cColFin,
			int iRengFin, int iColor) {
		this.sbDespliega.append("\nFILLCELLS=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin + cDelim + iColor);
	}

	public void comFontBold(String cColIni, int iRengIni, String cColFin,
			int iRengFin) {
		this.sbDespliega.append("\nFONTBOLD=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin);
	}

	public void comFontColor(String cColIni, int iRengIni, String cColFin,
			int iRengFin, int iColor) {
		this.sbDespliega.append("\nFONTCOLOR=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin + cDelim + iColor);
	}

	public void comFontNormal(String cColIni, int iRengIni, String cColFin,
			int iRengFin) {
		this.sbDespliega.append("\nFONTNORMAL=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin);
	}

	public void comFontSize(String cColIni, int iRengIni, String cColFin,
			int iRengFin, float dTamanio) {
		this.sbDespliega.append("\nFONTSIZE=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin + cDelim
				+ String.valueOf(dTamanio));
	}

	public void comRotar(String cColIni, int iRengIni, String cColFin,
			int iRengFin, int iGrados) {
		this.sbDespliega.append("\nROTAR=" + cColIni + cDelim + iRengIni
				+ cDelim + cColFin + cDelim + iRengFin + cDelim + iGrados);
	}

	public void comSaltosPagina(String cSaltosPag) {
		String cCadena = cSaltosPag.replaceAll(",", cDelim);
		this.sbDespliega.append("\nSALTOSPAGINA=" + cCadena);
	}

	public String getAT_COMBINA_CENTRO() {
		return AT_COMBINA_CENTRO;
	}

	public String getAT_COMBINA_DERECHA() {
		return AT_COMBINA_DERECHA;
	}

	public String getAT_COMBINA_IZQUIERDA() {
		return AT_COMBINA_IZQUIERDA;
	}

	public String getAT_DESCOMBINA() {
		return AT_DESCOMBINA;
	}

	public String getAT_HCENTRO() {
		return AT_HCENTRO;
	}

	public String getAT_HDERECHA() {
		return AT_HDERECHA;
	}

	public String getAT_HIZQUIERDA() {
		return AT_HIZQUIERDA;
	}

	public String getAT_HJUSTIFICA() {
		return AT_HJUSTIFICA;
	}

	public String getAT_HCENTRO_SELECCION() {
		return AT_HCENTRO_SELECCION;
	}

	public String getAT_VCENTRO() {
		return AT_VCENTRO;
	}

	public String getAT_VSUPERIOR() {
		return AT_VSUPERIOR;
	}

	public String getAT_VINFERIOR() {
		return AT_VINFERIOR;
	}

	public String getAT_VAJUSTAR() {
		return AT_VAJUSTAR;
	}

	public String getAT_VJUSTIFICAR() {
		return AT_VJUSTIFICAR;
	}

	public String getAT_POSFINAL() {
		return AT_POSFINAL;
	}

	public String getAT_POSINICIAL() {
		return AT_POSINICIAL;
	}

	public StringBuffer getSbDespliega() {
		StringBuffer sbTemp = new StringBuffer(sbDespliega.toString());
		sbDespliega = new StringBuffer("");
		return sbTemp;
	}

	public void setSbDespliega(StringBuffer sbValor) {
		this.sbDespliega = sbValor;
	}
}
