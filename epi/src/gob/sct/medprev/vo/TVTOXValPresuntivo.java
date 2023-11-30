package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TOXValPresuntivo
 * </p>
 * <p>
 * Description: VO de TOXVALPRESUNTIVO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Juan Manuel Vazquez
 * @version 1.0
 */
public class TVTOXValPresuntivo implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveLaboratorio;
	private int iCveValPres;
	private int iCveCtrolCalibra;
	private int iCveEquipo;
	private int iCveSustancia;
	private java.sql.Date dtEstudio;
	private float dCorte;
	private float dCorteNeg;
	private String cObservacion;
	private int iCveUsuResp;
	private float dCortePost;
	private String cDscUniMed;
	private String cDscCtrolCalibra;
	private String cDscEquipo;
	private String cDscSustancia;
	private String cDtEstudio;
	private String cModelo;
	private String cNumSerie;
	private int lCuantCual;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveValPres);
		return pk;
	}

	public TVTOXValPresuntivo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveValPres", "" + iCveValPres);
		hmfieldsTable.put("iCveCtrolCalibra", "" + iCveCtrolCalibra);
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("dtEstudio", "" + dtEstudio);
		hmfieldsTable.put("dCorte", "" + dCorte);
		hmfieldsTable.put("dCorteNeg", "" + dCorteNeg);
		hmfieldsTable.put("dCortePost", "" + dCortePost);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("iCveUsuResp", "" + iCveUsuResp);

		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cDscCtrolCalibra", "" + cDscCtrolCalibra);
		hmfieldsTable.put("cDscEquipo", "" + cDscEquipo);
		hmfieldsTable.put("cDscSustancia", "" + cDscSustancia);
		hmfieldsTable.put("cDtEstudio", "" + cDtEstudio);
		hmfieldsTable.put("lCuantCual", "" + lCuantCual);

		hmfieldsTable.put("cModelo", cModelo);
		hmfieldsTable.put("cNumSerie", cNumSerie);

		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getICveValPres() {
		return iCveValPres;
	}

	public void setICveValPres(int iCveValPres) {
		this.iCveValPres = iCveValPres;
	}

	public int getICveCtrolCalibra() {
		return iCveCtrolCalibra;
	}

	public void setICveCtrolCalibra(int iCveCtrolCalibra) {
		this.iCveCtrolCalibra = iCveCtrolCalibra;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public int getICveSustancia() {
		return iCveSustancia;
	}

	public void setICveSustancia(int iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public java.sql.Date getDtEstudio() {
		return dtEstudio;
	}

	public void setDtEstudio(java.sql.Date dtEstudio) {
		this.dtEstudio = dtEstudio;
	}

	public float getDCorte() {
		return dCorte;
	}

	public void setDCorte(float dCorte) {
		this.dCorte = dCorte;
	}

	public float getDCorteNeg() {
		return dCorteNeg;
	}

	public void setDCorteNeg(float dCorteNeg) {
		this.dCorteNeg = dCorteNeg;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getICveUsuResp() {
		return iCveUsuResp;
	}

	public void setICveUsuResp(int iCveUsuResp) {
		this.iCveUsuResp = iCveUsuResp;
	}

	public float getDCortePost() {
		return dCortePost;
	}

	public void setDCortePost(float dCortePost) {
		this.dCortePost = dCortePost;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscCtrolCalibra() {
		return cDscCtrolCalibra;
	}

	public void setCDscCtrolCalibra(String cDscCtrolCalibra) {
		this.cDscCtrolCalibra = cDscCtrolCalibra;
	}

	public String getCDscEquipo() {
		return cDscEquipo;
	}

	public void setCDscEquipo(String cDscEquipo) {
		this.cDscEquipo = cDscEquipo;
	}

	public String getCDscSustancia() {
		return cDscSustancia;
	}

	public void setCDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

	public String getCDtEstudio() {
		return cDtEstudio;
	}

	public void setCDtEstudio(String cDtEstudio) {
		this.cDtEstudio = cDtEstudio;
	}

	public int getLCuantCual() {
		return lCuantCual;
	}

	public void setLCuantCual(int lCuantCual) {
		this.lCuantCual = lCuantCual;
	}

	public String getCModelo() {
		return cModelo;
	}

	public String getCNumSerie() {
		return cNumSerie;
	}

	public void setCModelo(String cModelo) {
		this.cModelo = cModelo;
	}

	public void setCNumSerie(String cNumSerie) {
		this.cNumSerie = cNumSerie;
	}
}
