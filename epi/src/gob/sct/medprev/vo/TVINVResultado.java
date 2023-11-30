package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object INVResultado
 * </p>
 * <p>
 * Description: INVResultado
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
public class TVINVResultado implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveMdoTrans;
	private int iIDDGPMPT;
	private int iCveServicio;
	private int iCveRama;
	private int iCveSintoma;
	private float dValorIni;
	private int lLogico;
	private String cCaracter;
	private float dValorFin;
	private String cDscRama;
	private String cPregunta;
	private int iCveTpoResp;
	private int lObligatorio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iIDDGPMPT);
		pk.add("" + iCveServicio);
		pk.add("" + iCveRama);
		pk.add("" + iCveSintoma);
		return pk;
	}

	public TVINVResultado() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iIDDGPMPT", "" + iIDDGPMPT);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("iCveSintoma", "" + iCveSintoma);
		hmfieldsTable.put("dValorIni", "" + dValorIni);
		hmfieldsTable.put("lLogico", "" + lLogico);
		hmfieldsTable.put("cCaracter", cCaracter);
		hmfieldsTable.put("dValorFin", "" + dValorFin);
		hmfieldsTable.put("cDscRama", cDscRama);
		hmfieldsTable.put("cPregunta", cPregunta);
		hmfieldsTable.put("iCveTpoResp", "" + iCveTpoResp);
		hmfieldsTable.put("lObligatorio", "" + lObligatorio);

		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getIIDDGPMPT() {
		return iIDDGPMPT;
	}

	public void setIIDDGPMPT(int iIDDGPMPT) {
		this.iIDDGPMPT = iIDDGPMPT;
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

	public float getDValorIni() {
		return dValorIni;
	}

	public void setDValorIni(float dValorIni) {
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

	public void setCDscRama(String cDscRama) {
		this.cDscRama = cDscRama;
	}

	public String getCDscRama() {
		return cDscRama;
	}

	public void setCPregunta(String cPregunta) {
		this.cPregunta = cPregunta;
	}

	public String getCPregunta() {
		return cPregunta;
	}

	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
	}

	public int getLObligatorio() {
		return lObligatorio;
	}

	public void setLObligatorio(int lObligatorio) {
		this.lObligatorio = lObligatorio;
	}
}
