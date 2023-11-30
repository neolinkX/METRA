package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object EXPResultado
 * </p>
 * <p>
 * Description: VO EXP Resultado
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */
public class TVPEREXAMENNoAp implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveServicio;
	private int iCveRama;
	private int iCveSintoma;

	// private float dValorIni; //FCS : Se cambio el Tipo De Dato, de float a
	// Double,
	// ya que formateaba a 4 posiciones en la parte decimal y le resta 1,
	// ver linea siguiente.
	private double dValorIni;

	private int lLogico;
	private String cCaracter;
	private float dValorFin;
	private String cDscBreve;
	private String cPregunta;
	private String cGenero;
	private int lEstudio;
	private int iCveTpoResp;
	private String cEtiqueta;
	private int lObligatorio;
	private int lEvalAuto;
	private String cDscServicio;
	private String cDscRama;
	private String cCampo;
	private int lConcluido;
	private String cValRef;
	private String cUsuServ;
	private String cUsuRama;
	private int lVariosMeds;
	private int iOrden;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveServicio);
		pk.add("" + iCveRama);
		pk.add("" + iCveSintoma);
		return pk;
	}

	public TVPEREXAMENNoAp() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("iCveSintoma", "" + iCveSintoma);
		hmfieldsTable.put("dValorIni", "" + dValorIni);
		hmfieldsTable.put("lLogico", "" + lLogico);
		hmfieldsTable.put("cCaracter", cCaracter);
		hmfieldsTable.put("dValorFin", "" + dValorFin);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cPregunta", cPregunta);
		hmfieldsTable.put("cGenero", cGenero);
		hmfieldsTable.put("lEstudio", "" + lEstudio);
		hmfieldsTable.put("iCveTpoResp", "" + iCveTpoResp);
		hmfieldsTable.put("cEtiqueta", cEtiqueta);
		hmfieldsTable.put("lObligatorio", "" + lObligatorio);
		hmfieldsTable.put("lEvalAuto", "" + lEvalAuto);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("cDscRama", cDscRama);
		hmfieldsTable.put("cCampo", cCampo);
		hmfieldsTable.put("lConcluido", "" + lConcluido);
		hmfieldsTable.put("cValRef", cValRef);
		hmfieldsTable.put("iOrden", iOrden);

		return hmfieldsTable;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getICveRama() {
		return iCveRama;
	}

	public void setICveRama(int iCveRama) {
		this.iCveRama = iCveRama;
	}

	public int getICveSintoma() {
		return iCveSintoma;
	}

	public void setICveSintoma(int iCveSintoma) {
		this.iCveSintoma = iCveSintoma;
	}

	// public float getDValorIni() { //FCS : Se cambio el Tipo De Dato, de float
	// a Double,
	// ya que formateaba a 4 posiciones en la parte decimal y le resta 1,
	// ver linea siguiente.
	public double getDValorIni() {
		return dValorIni;
	}

	public int getIValorIni() {
		return (int) dValorIni;
	}

	// public void setDValorIni(float dValorIni) { //FCS : Se cambio el Tipo De
	// Dato, de float a Double,
	// ya que formateaba a 4 posiciones en la parte decimal y le resta 1,
	// ver linea siguiente.
	public void setDValorIni(double dValorIni) {
		this.dValorIni = dValorIni;
	}

	public int getLLogico() {
		return lLogico;
	}

	public void setLLogico(int lLogico) {
		this.lLogico = lLogico;
	}

	public String getCCaracter() {
		return cCaracter;
	}

	public void setCCaracter(String cCaracter) {
		this.cCaracter = cCaracter;
	}

	public float getDValorFin() {
		return dValorFin;
	}

	public void setDValorFin(float dValorFin) {
		this.dValorFin = dValorFin;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCPregunta() {
		return cPregunta;
	}

	public void setCPregunta(String cPregunta) {
		this.cPregunta = cPregunta;
	}

	public String getCGenero() {
		return cGenero;
	}

	public void setCGenero(String cGenero) {
		this.cGenero = cGenero;
	}

	public int getLEstudio() {
		return lEstudio;
	}

	public void setLEstudio(int lEstudio) {
		this.lEstudio = lEstudio;
	}

	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
	}

	public String getCEtiqueta() {
		return cEtiqueta;
	}

	public void setCEtiqueta(String cEtiqueta) {
		this.cEtiqueta = cEtiqueta;
	}

	public int getLObligatorio() {
		return lObligatorio;
	}

	public void setLObligatorio(int lObligatorio) {
		this.lObligatorio = lObligatorio;
	}

	public int getLEvalAuto() {
		return lEvalAuto;
	}

	public void setLEvalAuto(int lEvalAuto) {
		this.lEvalAuto = lEvalAuto;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public String getCDscRama() {
		return cDscRama;
	}

	public void setCDscRama(String cDscRama) {
		this.cDscRama = cDscRama;
	}

	public String getCCampo() {
		return cCampo;
	}

	public void setCCampo(String cCampo) {
		this.cCampo = cCampo;
	}

	public int getLConcluido() {
		return lConcluido;
	}

	public void setLConcluido(int lConcluido) {
		this.lConcluido = lConcluido;
	}

	public String getCValRef() {
		return cValRef;
	}

	public void setCValRef(String cValRef) {
		this.cValRef = cValRef;
	}

	public String getCUsuServ() {
		return cUsuServ;
	}

	public void setCUsuServ(String cUsuServ) {
		this.cUsuServ = cUsuServ;
	}

	public String getCUsuRama() {
		return cUsuRama;
	}

	public void setCUsuRama(String cUsuRama) {
		this.cUsuRama = cUsuRama;
	}

	public int getLVariosMeds() {
		return lVariosMeds;
	}

	public void setLVariosMeds(int lVariosMeds) {
		this.lVariosMeds = lVariosMeds;
	}
	
	public int getIOrden() {
		return iOrden;
	}
	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}
	
}