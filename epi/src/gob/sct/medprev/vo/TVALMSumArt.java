package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMSumArt
 * </p>
 * <p>
 * Description: VO para la tabla de ALMSumArt
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernández García
 * @version 1.0
 */
public class TVALMSumArt implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveAlmacen;
	private int iCveSolicSum;
	private int iCveArticulo;
	private int iCveMeta;
	private float dUnidSolicita;
	private float dUnidAutor;
	private String cObservacion;
	private int lAutorizada;
	private int lAnalizada;
	private String cDscBreve;
	private int iCveTpoArticulo;
	private String cDscTpoArticulo;
	private int iCveFamilia;
	private String cDscFamilia;
	private String cDscArticulo;
	private int iCveUniSum;
	private String cDscUnidad;
	private int iNumArt;
	private int lLote;
	private String cLote;
	private java.sql.Date dtCaducidad;
	private float dUnidades;
	private String cCveArticulo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveSolicSum);
		pk.add("" + iCveArticulo);
		pk.add("" + iCveTpoArticulo);
		pk.add("" + cDscTpoArticulo);
		pk.add("" + iCveFamilia);
		pk.add("" + cDscFamilia);
		pk.add("" + cDscArticulo);
		pk.add("" + iCveUniSum);
		pk.add("" + cDscUnidad);
		return pk;
	}

	public TVALMSumArt() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveSolicSum", "" + iCveSolicSum);
		hmfieldsTable.put("iCveArticulo", "" + iCveArticulo);
		hmfieldsTable.put("iCveMeta", "" + iCveMeta);
		hmfieldsTable.put("dUnidSolicita", "" + dUnidSolicita);
		hmfieldsTable.put("dUnidAutor", "" + dUnidAutor);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lAutorizada", "" + lAutorizada);
		hmfieldsTable.put("lAnalizada", "" + lAnalizada);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("iCveTpoArticulo", "" + iCveTpoArticulo);
		hmfieldsTable.put("cDscTpoArticulo", "" + cDscTpoArticulo);
		hmfieldsTable.put("iCveFamilia", "" + iCveFamilia);
		hmfieldsTable.put("cDscFamilia", "" + cDscFamilia);
		hmfieldsTable.put("cDscArticulo", "" + cDscArticulo);
		hmfieldsTable.put("iCveUniSum", "" + iCveUniSum);
		hmfieldsTable.put("cDscUnidad", "" + cDscUnidad);
		hmfieldsTable.put("iNumArt", "" + iNumArt);
		hmfieldsTable.put("lLote", "" + lLote);
		hmfieldsTable.put("cLote", "" + cLote);
		hmfieldsTable.put("dtCaducidad", "" + dtCaducidad);
		hmfieldsTable.put("dUnidades", "" + dUnidades);
		hmfieldsTable.put("cCveArticulo", "" + dUnidades);

		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveAlmacen() {
		return iCveAlmacen;
	}

	public void setICveAlmacen(int iCveAlmacen) {
		this.iCveAlmacen = iCveAlmacen;
	}

	public int getICveSolicSum() {
		return iCveSolicSum;
	}

	public void setICveSolicSum(int iCveSolicSum) {
		this.iCveSolicSum = iCveSolicSum;
	}

	public int getICveArticulo() {
		return iCveArticulo;
	}

	public void setICveArticulo(int iCveArticulo) {
		this.iCveArticulo = iCveArticulo;
	}

	public int getICveMeta() {
		return iCveMeta;
	}

	public void setICveMeta(int iCveMeta) {
		this.iCveMeta = iCveMeta;
	}

	public float getDUnidSolicita() {
		return dUnidSolicita;
	}

	public void setDUnidSolicita(float dUnidSolicita) {
		this.dUnidSolicita = dUnidSolicita;
	}

	public float getDUnidAutor() {
		return dUnidAutor;
	}

	public void setDUnidAutor(float dUnidAutor) {
		this.dUnidAutor = dUnidAutor;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getLAutorizada() {
		return lAutorizada;
	}

	public void setLAutorizada(int lAutorizada) {
		this.lAutorizada = lAutorizada;
	}

	public int getLAnalizada() {
		return lAnalizada;
	}

	public void setLAnalizada(int lAnalizada) {
		this.lAnalizada = lAnalizada;
	}

	public String getcDscBreve() {
		return cDscBreve;
	}

	public void setcDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getICveTpoArticulo() {
		return iCveTpoArticulo;
	}

	public void setICveTpoArticulo(int iCveTpoArticulo) {
		this.iCveTpoArticulo = iCveTpoArticulo;
	}

	public String getCDscTpoArticulo() {
		return cDscTpoArticulo;
	}

	public void setCDscTpoArticulo(String cDscTpoArticulo) {
		this.cDscTpoArticulo = cDscTpoArticulo;
	}

	public int getICveFamilia() {
		return iCveFamilia;
	}

	public void setICveFamilia(int iCveFamilia) {
		this.iCveFamilia = iCveFamilia;
	}

	public String getCDscFamilia() {
		return cDscFamilia;
	}

	public void setCDscFamilia(String cDscFamilia) {
		this.cDscFamilia = cDscFamilia;
	}

	public String getCDscArticulo() {
		return cDscArticulo;
	}

	public void setCDscArticulo(String cDscArticulo) {
		this.cDscArticulo = cDscArticulo;
	}

	public int getICveUniSum() {
		return iCveUniSum;
	}

	public void setICveUniSum(int iCveUniSum) {
		this.iCveUniSum = iCveUniSum;
	}

	public String getCDscUnidad() {
		return cDscUnidad;
	}

	public void setCDscUnidad(String cDscUnidad) {
		this.cDscUnidad = cDscUnidad;
	}

	public int getINumArt() {
		return iNumArt;
	}

	public void setINumArt(int iNumArt) {
		this.iNumArt = iNumArt;
	}

	public int getLLote() {
		return lLote;
	}

	public void setLLote(int lLote) {
		this.lLote = lLote;
	}

	public String getcLote() {
		return cLote;
	}

	public void setcLote(String cLote) {
		this.cLote = cLote;
	}

	public java.sql.Date getdtCaducidad() {
		return dtCaducidad;
	}

	public void setdtCaducidad(java.sql.Date dtCaducidad) {
		this.dtCaducidad = dtCaducidad;
	}

	public float getdUnidades() {
		return dUnidades;
	}

	public void setdUnidades(float dUnidades) {
		this.dUnidades = dUnidades;
	}

	public String getcCveArticulo() {
		return cCveArticulo;
	}

	public void setcCveArticulo(String cCveArticulo) {
		this.cCveArticulo = cCveArticulo;
	}
}
