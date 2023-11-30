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
public class TVTOXCtrolCalibra implements HashBeanInterface {
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
	private String cDscUniMed;
	private String cDscSustancia;
	private String cDscEmpleoCalib;
	private String cDscMarcaSust;
	private String cDscReactivo;
	private String cDscTpoReact;
	private int iCveTpoReact;
	private String cDscUsuPrepara;
	private String cDscUsuAutoriza;
	private String cDtPreparacion;
	private String cDtCaducidad;
	private String cDtAutoriza;
	private String cDtAgotado;
	private String cDtBaja;
	private int lCual;

	private String cComboBaja;
	private float dConcentExper;
	private int iCveEquipo;
	private java.sql.Date dtValoracion;
	private float dVolUtilizado;

	// Agregado Rafael Alcocer Caldera 31/Ago/2006
	private String cDscEquipo;
	private String cNumSerie;
	private String cModelo;
	private String cCveEquipo;
	private String iCodigo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveCtrolCalibra);
		return pk;
	}

	public TVTOXCtrolCalibra() {
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
		hmfieldsTable.put("lCual", "" + lCual);
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

		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cDscSustancia", "" + cDscSustancia);
		hmfieldsTable.put("cDscEmpleoCalib", "" + cDscEmpleoCalib);
		hmfieldsTable.put("cDscMarcaSust", "" + cDscMarcaSust);
		hmfieldsTable.put("cDscReactivo", "" + cDscReactivo);
		hmfieldsTable.put("cDscTpoReact", "" + cDscTpoReact);
		hmfieldsTable.put("iCveTpoReact", "" + iCveTpoReact);
		hmfieldsTable.put("cDscUsuPrepara", "" + cDscUsuPrepara);
		hmfieldsTable.put("cDscUsuAutoriza", "" + cDscUsuAutoriza);

		hmfieldsTable.put("cDtPreparacion", cDtPreparacion);
		hmfieldsTable.put("cDtCaducidad", cDtCaducidad);
		hmfieldsTable.put("cDtAutoriza", cDtAutoriza);
		hmfieldsTable.put("cDtAgotado", cDtAgotado);
		hmfieldsTable.put("cDtBaja", cDtBaja);

		hmfieldsTable.put("cComboBaja", cComboBaja);

		hmfieldsTable.put("dConcentExper", "" + dConcentExper);
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("dtValoracion", dtValoracion);
		hmfieldsTable.put("dVolUtilizado", "" + dVolUtilizado);

		hmfieldsTable.put("cDscEquipo", "" + cDscEquipo);
		hmfieldsTable.put("cNumSerie", "" + cNumSerie);
		hmfieldsTable.put("cModelo", "" + cModelo);
		hmfieldsTable.put("cCveEquipo", "" + cCveEquipo);
		hmfieldsTable.put("iCodigo", "" + iCodigo);

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

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscSustancia() {
		return cDscSustancia;
	}

	public void setCDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

	public String getCDscEmpleoCalib() {
		return cDscEmpleoCalib;
	}

	public void setCDscEmpleoCalib(String cDscEmpleoCalib) {
		this.cDscEmpleoCalib = cDscEmpleoCalib;
	}

	public String getCDscMarcaSust() {
		return cDscMarcaSust;
	}

	public void setCDscMarcaSust(String cDscMarcaSust) {
		this.cDscMarcaSust = cDscMarcaSust;
	}

	public String getCDscReactivo() {
		return cDscReactivo;
	}

	public void setCDscReactivo(String cDscReactivo) {
		this.cDscReactivo = cDscReactivo;
	}

	public String getCDscTpoReact() {
		return cDscTpoReact;
	}

	public void setCDscTpoReact(String cDscTpoReact) {
		this.cDscTpoReact = cDscTpoReact;
	}

	public int getICveTpoReact() {
		return iCveTpoReact;
	}

	public void setICveTpoReact(int iCveTpoReact) {
		this.iCveTpoReact = iCveTpoReact;
	}

	public String getCDscUsuPrepara() {
		return cDscUsuPrepara;
	}

	public void setCDscUsuPrepara(String cDscUsuPrepara) {
		this.cDscUsuPrepara = cDscUsuPrepara;
	}

	public String getCDscUsuAutoriza() {
		return cDscUsuAutoriza;
	}

	public void setCDscUsuAutoriza(String cDscUsuAutoriza) {
		this.cDscUsuAutoriza = cDscUsuAutoriza;
	}

	public String getCDtPreparacion() {
		return cDtPreparacion;
	}

	public void setCDtPreparacion(String cDtPreparacion) {
		this.cDtPreparacion = cDtPreparacion;
	}

	public String getCDtCaducidad() {
		return cDtCaducidad;
	}

	public void setCDtCaducidad(String cDtCaducidad) {
		this.cDtCaducidad = cDtCaducidad;
	}

	public String getCDtAutoriza() {
		return cDtAutoriza;
	}

	public void setCDtAutoriza(String cDtAutoriza) {
		this.cDtAutoriza = cDtAutoriza;
	}

	public String getCDtAgotado() {
		return cDtAgotado;
	}

	public void setCDtAgotado(String cDtAgotado) {
		this.cDtAgotado = cDtAgotado;
	}

	public String getCDtBaja() {
		return cDtBaja;
	}

	public void setCDtBaja(String cDtBaja) {
		this.cDtBaja = cDtBaja;
	}

	public int getLCual() {
		return lCual;
	}

	public void setLCual(int lCual) {
		this.lCual = lCual;
	}

	public String getCComboBaja() {
		return cComboBaja;
	}

	public void setCComboBaja(String CComboBaja) {
		this.cComboBaja = CComboBaja;
	}

	public float getDConcentExper() {
		return dConcentExper;
	}

	public void setDConcentExper(float dConcentExper) {
		this.dConcentExper = dConcentExper;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public java.sql.Date getDtValoracion() {
		return dtValoracion;
	}

	public void setDtValoracion(java.sql.Date dtValoracion) {
		this.dtValoracion = dtValoracion;
	}

	public float getDVolUtilizado() {
		return dVolUtilizado;
	}

	public void setDVolUtilizado(float dVolUtilizado) {
		this.dVolUtilizado = dVolUtilizado;
	}

	public String getCDscEquipo() {
		return cDscEquipo;
	}

	public void setCDscEquipo(String cDscEquipo) {
		this.cDscEquipo = cDscEquipo;
	}

	public String getCNumSerie() {
		return cNumSerie;
	}

	public void setCNumSerie(String cNumSerie) {
		this.cNumSerie = cNumSerie;
	}

	public String getCModelo() {
		return cModelo;
	}

	public void setCModelo(String cModelo) {
		this.cModelo = cModelo;
	}

	public String getCCveEquipo() {
		return cCveEquipo;
	}

	public void setCCveEquipo(String cCveEquipo) {
		this.cCveEquipo = cCveEquipo;
	}

	public String getICodigo() {
		return iCodigo;
	}

	public void setICodigo(String iCodigo) {
		this.iCodigo = iCodigo;
	}

}
