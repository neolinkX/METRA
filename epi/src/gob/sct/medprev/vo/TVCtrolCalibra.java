package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object CtrolCalibra
 * </p>
 * <p>
 * Description: VO de TOXCtrolCalibra
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
public class TVCtrolCalibra implements HashBeanInterface {
	private BeanPK pk;
	private int iCveLaboratorio;
	private int iCveCtrolCalibra;
	private int iAnio;
	private String cLote;
	private String cDscBreve;
	private int iCveSustancia;
	private float dVolumen;
	private float dConcentracion;
	private int iCveEmpleoCalib;
	private int lCuantCual;
	private int iViales;
	private java.sql.Date dtPreparacion;
	private java.sql.Date dtCaducidad;
	private java.sql.Date dtAutoriza;
	private int iCveUsuAutoriza;
	private int lAgotado;
	private java.sql.Date dtAgotado;
	private int lBaja;
	private java.sql.Date dtBaja;
	private int iAnioBaja;
	private int iCveBaja;
	private int iCveMarcaSust;
	private String cObservacion;
	private int iCveReactivo;
	private int iCveUsuPrepara;
	private String cDscCtrolCalibra;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveCtrolCalibra);
		return pk;
	}

	public TVCtrolCalibra() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveCtrolCalibra", "" + iCveCtrolCalibra);
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("cLote", cLote);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cDscCtrolCalibra", cDscCtrolCalibra);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("dVolumen", "" + dVolumen);
		hmfieldsTable.put("dConcentracion", "" + dConcentracion);
		hmfieldsTable.put("iCveEmpleoCalib", "" + iCveEmpleoCalib);
		hmfieldsTable.put("lCuantCual", "" + lCuantCual);
		hmfieldsTable.put("iViales", "" + iViales);
		hmfieldsTable.put("dtPreparacion", "" + dtPreparacion);
		hmfieldsTable.put("dtCaducidad", "" + dtCaducidad);
		hmfieldsTable.put("dtAutoriza", "" + dtAutoriza);
		hmfieldsTable.put("iCveUsuAutoriza", "" + iCveUsuAutoriza);
		hmfieldsTable.put("lAgotado", "" + lAgotado);
		hmfieldsTable.put("dtAgotado", "" + dtAgotado);
		hmfieldsTable.put("lBaja", "" + lBaja);
		hmfieldsTable.put("dtBaja", "" + dtBaja);
		hmfieldsTable.put("iAnioBaja", "" + iAnioBaja);
		hmfieldsTable.put("iCveBaja", "" + iCveBaja);
		hmfieldsTable.put("iCveMarcaSust", "" + iCveMarcaSust);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("iCveReactivo", "" + iCveReactivo);
		hmfieldsTable.put("iCveUsuPrepara", "" + iCveUsuPrepara);

		return hmfieldsTable;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getICveCtrolCalibra() {
		return iCveCtrolCalibra;
	}

	public void setICveCtrolCalibra(int iCveCtrolCalibra) {
		this.iCveCtrolCalibra = iCveCtrolCalibra;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public String getCLote() {
		return cLote;
	}

	public void setCLote(String cLote) {
		this.cLote = cLote;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getICveSustancia() {
		return iCveSustancia;
	}

	public void setICveSustancia(int iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public float getDVolumen() {
		return dVolumen;
	}

	public void setDVolumen(float dVolumen) {
		this.dVolumen = dVolumen;
	}

	public float getDConcentracion() {
		return dConcentracion;
	}

	public void setDConcentracion(float dConcentracion) {
		this.dConcentracion = dConcentracion;
	}

	public int getICveEmpleoCalib() {
		return iCveEmpleoCalib;
	}

	public void setICveEmpleoCalib(int iCveEmpleoCalib) {
		this.iCveEmpleoCalib = iCveEmpleoCalib;
	}

	public int getLCuantCual() {
		return lCuantCual;
	}

	public void setLCuantCual(int lCuantCual) {
		this.lCuantCual = lCuantCual;
	}

	public int getIViales() {
		return iViales;
	}

	public void setIViales(int iViales) {
		this.iViales = iViales;
	}

	public java.sql.Date getDtPreparacion() {
		return dtPreparacion;
	}

	public void setDtPreparacion(java.sql.Date dtPreparacion) {
		this.dtPreparacion = dtPreparacion;
	}

	public java.sql.Date getDtCaducidad() {
		return dtCaducidad;
	}

	public void setDtCaducidad(java.sql.Date dtCaducidad) {
		this.dtCaducidad = dtCaducidad;
	}

	public java.sql.Date getDtAutoriza() {
		return dtAutoriza;
	}

	public void setDtAutoriza(java.sql.Date dtAutoriza) {
		this.dtAutoriza = dtAutoriza;
	}

	public int getICveUsuAutoriza() {
		return iCveUsuAutoriza;
	}

	public void setICveUsuAutoriza(int iCveUsuAutoriza) {
		this.iCveUsuAutoriza = iCveUsuAutoriza;
	}

	public int getLAgotado() {
		return lAgotado;
	}

	public void setLAgotado(int lAgotado) {
		this.lAgotado = lAgotado;
	}

	public java.sql.Date getDtAgotado() {
		return dtAgotado;
	}

	public void setDtAgotado(java.sql.Date dtAgotado) {
		this.dtAgotado = dtAgotado;
	}

	public int getLBaja() {
		return lBaja;
	}

	public void setLBaja(int lBaja) {
		this.lBaja = lBaja;
	}

	public java.sql.Date getDtBaja() {
		return dtBaja;
	}

	public void setDtBaja(java.sql.Date dtBaja) {
		this.dtBaja = dtBaja;
	}

	public int getIAnioBaja() {
		return iAnioBaja;
	}

	public void setIAnioBaja(int iAnioBaja) {
		this.iAnioBaja = iAnioBaja;
	}

	public int getICveBaja() {
		return iCveBaja;
	}

	public void setICveBaja(int iCveBaja) {
		this.iCveBaja = iCveBaja;
	}

	public int getICveMarcaSust() {
		return iCveMarcaSust;
	}

	public void setICveMarcaSust(int iCveMarcaSust) {
		this.iCveMarcaSust = iCveMarcaSust;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getICveReactivo() {
		return iCveReactivo;
	}

	public void setICveReactivo(int iCveReactivo) {
		this.iCveReactivo = iCveReactivo;
	}

	public int getICveUsuPrepara() {
		return iCveUsuPrepara;
	}

	public void setICveUsuPrepara(int iCveUsuPrepara) {
		this.iCveUsuPrepara = iCveUsuPrepara;
	}

	public String getCDscCtrolCalibra() {
		return cDscCtrolCalibra;
	}

	public void setCDscCtrolCalibra(String cDscCtrolCalibra) {
		this.cDscCtrolCalibra = cDscCtrolCalibra;
	}
}
