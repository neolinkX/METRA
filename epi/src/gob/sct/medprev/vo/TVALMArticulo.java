package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMArticulo
 * </p>
 * <p>
 * Description: VO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Oscar Castrejón Adame.
 * @version 1.0
 */
public class TVALMArticulo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveArticulo;
	private int iCveTpoArticulo;
	private int iPartida;
	private int iCveFamilia;
	private String cCveArticulo;
	private String cDscArticulo;
	private String cDscBreve;
	private int iCveUniAlm;
	private int iCveUniSum;
	private int lMaxMin;
	private String cObservacion;
	private int lLote;
	private String cDscTpoArticulo;
	private String cDscFamilia;
	private String cDscUnidad;
	private String cDscUniAlm;
	private String cDscUniSum;
	private int iCveMeta;
	private float dUnidSolicita;
	private int iAnio;
	private int iCveAlmacen;
	private int iCveSolicSum;
	private int iCveLote;
	private String cLote;
	private java.sql.Date dtCaducidad;
	private double dUnidades;
	private String cDscMeta;
	private String cDscArea;
	private String cDscAlmacen;
	private String cDscUsuResp;
	private String cDscUsuSolicita;
	private String cDscUniMed;
	private String cDscModulo;
	private java.sql.Date dtSolicitud;
	private String cRespArea;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveArticulo);
		return pk;
	}

	public TVALMArticulo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveArticulo", "" + iCveArticulo);
		hmfieldsTable.put("iCveTpoArticulo", "" + iCveTpoArticulo);
		hmfieldsTable.put("iPartida", "" + iPartida);
		hmfieldsTable.put("iCveFamilia", "" + iCveFamilia);
		hmfieldsTable.put("cCveArticulo", "" + cCveArticulo);
		hmfieldsTable.put("cDscArticulo", "" + cDscArticulo);
		hmfieldsTable.put("cDscBreve", "" + cDscBreve);
		hmfieldsTable.put("iCveUniAlm", "" + iCveUniAlm);
		hmfieldsTable.put("iCveUniSum", "" + iCveUniSum);
		hmfieldsTable.put("lMaxMin", "" + lMaxMin);
		hmfieldsTable.put("cObservacion", "" + cObservacion);
		hmfieldsTable.put("lLote", "" + lLote);
		hmfieldsTable.put("cDscTpoArticulo", "" + cDscTpoArticulo);
		hmfieldsTable.put("cDscFamilia", "" + cDscFamilia);
		hmfieldsTable.put("cDscUnidad", "" + cDscUnidad);
		hmfieldsTable.put("cDscUniAlm", "" + cDscUniAlm);
		hmfieldsTable.put("cDscUniSum", "" + cDscUniSum);
		hmfieldsTable.put("iCveMeta", "" + iCveMeta);
		hmfieldsTable.put("dUnidSolicita", "" + dUnidSolicita);
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveSolicSum", "" + iCveSolicSum);
		hmfieldsTable.put("iCveLote", "" + iCveLote);
		hmfieldsTable.put("cLote", "" + cLote);
		hmfieldsTable.put("dUnidades", "" + dUnidades);
		hmfieldsTable.put("cDscMeta", "" + cDscMeta);
		hmfieldsTable.put("cDscArea", "" + cDscArea);
		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cDscModulo", "" + cDscModulo);
		hmfieldsTable.put("dtSolicitud", "" + dtSolicitud);
		hmfieldsTable.put("cRespArea", "" + cRespArea);
		hmfieldsTable.put("dtCaducidad", "" + dtCaducidad);
		return hmfieldsTable;
	}

	public int getiCveArticulo() {
		return iCveArticulo;
	}

	public void setiCveArticulo(int iCveArticulo) {
		this.iCveArticulo = iCveArticulo;
	}

	public int getiCveTpoArticulo() {
		return iCveTpoArticulo;
	}

	public void setiCveTpoArticulo(int iCveTpoArticulo) {
		this.iCveTpoArticulo = iCveTpoArticulo;
	}

	public int getIPartida() {
		return iPartida;
	}

	public void setIPartida(int iPartida) {
		this.iPartida = iPartida;
	}

	public int getiCveFamilia() {
		return iCveFamilia;
	}

	public void setiCveFamilia(int iCveFamilia) {
		this.iCveFamilia = iCveFamilia;
	}

	public String getcCveArticulo() {
		return cCveArticulo;
	}

	public void setcCveArticulo(String cCveArticulo) {
		this.cCveArticulo = cCveArticulo;
	}

	public String getcDscArticulo() {
		return cDscArticulo;
	}

	public void setcDscArticulo(String cDscArticulo) {
		this.cDscArticulo = cDscArticulo;
	}

	public String getcDscBreve() {
		return cDscBreve;
	}

	public void setcDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getiCveUniAlm() {
		return iCveUniAlm;
	}

	public void setiCveUniAlm(int iCveUniAlm) {
		this.iCveUniAlm = iCveUniAlm;
	}

	public int getiCveUniSum() {
		return iCveUniSum;
	}

	public void setiCveUniSum(int iCveUniSum) {
		this.iCveUniSum = iCveUniSum;
	}

	public int getlMaxMin() {
		return lMaxMin;
	}

	public void setlMaxMin(int lMaxMin) {
		this.lMaxMin = lMaxMin;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getlLote() {
		return lLote;
	}

	public void setlLote(int lLote) {
		this.lLote = lLote;
	}

	public String getcDscTpoArticulo() {
		return cDscTpoArticulo;
	}

	public void setcDscTpoArticulo(String cDscTpoArticulo) {
		this.cDscTpoArticulo = cDscTpoArticulo;
	}

	public String getcDscFamilia() {
		return cDscFamilia;
	}

	public void setcDscFamilia(String cDscFamilia) {
		this.cDscFamilia = cDscFamilia;
	}

	public String getcDscUnidad() {
		return cDscUnidad;
	}

	public void setcDscUnidad(String cDscUnidad) {
		this.cDscUnidad = cDscUnidad;
	}

	public String getcDscUniAlm() {
		return cDscUniAlm;
	}

	public void setcDscUniAlm(String cDscUniAlm) {
		this.cDscUniAlm = cDscUniAlm;
	}

	public String getcDscUniSum() {
		return cDscUniSum;
	}

	public void setcDscUniSum(String cDscUniSum) {
		this.cDscUniSum = cDscUniSum;
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

	public int getiCveLote() {
		return iCveLote;
	}

	public void setiCveLote(int iCveLote) {
		this.iCveLote = iCveLote;
	}

	public String getcLote() {
		return cLote;
	}

	public void setcLote(String cLote) {
		this.cLote = cLote;
	}

	public double getdUnidades() {
		return dUnidades;
	}

	public void setdUnidades(double dUnidades) {
		this.dUnidades = dUnidades;
	}

	public String getCDscMeta() {
		return cDscMeta;
	}

	public void setCDscMeta(String cDscMeta) {
		this.cDscMeta = cDscMeta;
	}

	public String getCDscArea() {
		return cDscArea;
	}

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

	public String getCDscAlmacen() {
		return cDscAlmacen;
	}

	public void setCDscAlmacen(String cDscAlmacen) {
		this.cDscAlmacen = cDscAlmacen;
	}

	public String getCDscUsuResp() {
		return cDscUsuResp;
	}

	public void setCDscUsuResp(String cDscUsuResp) {
		this.cDscUsuResp = cDscUsuResp;
	}

	public String getCDscUsuSolicita() {
		return cDscUsuSolicita;
	}

	public void setCDscUsuSolicita(String cDscUsuSolicita) {
		this.cDscUsuSolicita = cDscUsuSolicita;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public String getCRespArea() {
		return cRespArea;
	}

	public void setCRespArea(String cRespArea) {
		this.cRespArea = cRespArea;
	}

	public java.sql.Date getDtCaducidad() {
		return dtCaducidad;
	}

	public void setDtCaducidad(java.sql.Date dtCaducidad) {
		this.dtCaducidad = dtCaducidad;
	}
}
