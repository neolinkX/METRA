package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EQMAsignacion
 * </p>
 * <p>
 * Description: VO para el control de la Información de la tabla EQMAsignacion
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernandez García
 * @version 1.0
 */
public class TVEQMAsignacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEquipo;
	private int iCveAsignacion;
	private int iCveUniMed;
	private int iCveModulo;
	private int iCveArea;
	private int iCveUsuResp;
	private int lActual;
	private java.sql.Date dtAsigna;
	private java.sql.Date dtDesasigna;
	private String cNumSerie;
	private String cInventario;
	private String cDscMarca;
	private String cModelo;
	private String cDscEstadoEQM;
	private String cDscEquipo;
	private String cDscUniMed;
	private String cDscModulo;
	private String cDscArea;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private String cDtAsigna;
	private String cDtDesasigna;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEquipo);
		pk.add("" + iCveAsignacion);
		return pk;
	}

	public TVEQMAsignacion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveAsignacion", "" + iCveAsignacion);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("iCveUsuResp", "" + iCveUsuResp);
		hmfieldsTable.put("lActual", "" + lActual);
		hmfieldsTable.put("dtAsigna", "" + dtAsigna);
		hmfieldsTable.put("dtDesasigna", "" + dtDesasigna);
		hmfieldsTable.put("cNumSerie", "" + cNumSerie);
		hmfieldsTable.put("cInventario", "" + cInventario);
		hmfieldsTable.put("cDscMarca", "" + cDscMarca);
		hmfieldsTable.put("cModelo", "" + cModelo);
		hmfieldsTable.put("cDscEstadoEQM", "" + cDscEstadoEQM);
		hmfieldsTable.put("cDscEquipo", "" + cDscEquipo);
		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cDscModulo", "" + cDscModulo);
		hmfieldsTable.put("cDscArea", "" + cDscArea);
		hmfieldsTable.put("cNombre", "" + cNombre);
		hmfieldsTable.put("cApPaterno", "" + cApPaterno);
		hmfieldsTable.put("cApMaterno", "" + cApMaterno);
		hmfieldsTable.put("cDtAsigna", "" + cDtAsigna);
		hmfieldsTable.put("cDtDesasigna", "" + cDtDesasigna);
		return hmfieldsTable;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public int getICveAsignacion() {
		return iCveAsignacion;
	}

	public void setICveAsignacion(int iCveAsignacion) {
		this.iCveAsignacion = iCveAsignacion;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public int getICveArea() {
		return iCveArea;
	}

	public void setICveArea(int iCveArea) {
		this.iCveArea = iCveArea;
	}

	public int getICveUsuResp() {
		return iCveUsuResp;
	}

	public void setICveUsuResp(int iCveUsuResp) {
		this.iCveUsuResp = iCveUsuResp;
	}

	public int getLActual() {
		return lActual;
	}

	public void setLActual(int lActual) {
		this.lActual = lActual;
	}

	public java.sql.Date getDtAsigna() {
		return dtAsigna;
	}

	public void setDtAsigna(java.sql.Date dtAsigna) {
		this.dtAsigna = dtAsigna;
	}

	public java.sql.Date getDtDesasigna() {
		return dtDesasigna;
	}

	public void setDtDesasigna(java.sql.Date dtDesasigna) {
		this.dtDesasigna = dtDesasigna;
	}

	public String getCNumSerie() {
		return cNumSerie;
	}

	public void setCNumSerie(String cNumSerie) {
		this.cNumSerie = cNumSerie;
	}

	public String getCInventario() {
		return cInventario;
	}

	public void setCInventario(String cInventario) {
		this.cInventario = cInventario;
	}

	public String getCDscMarca() {
		return cDscMarca;
	}

	public void setCDscMarca(String cDscMarca) {
		this.cDscMarca = cDscMarca;
	}

	public String getCModelo() {
		return cModelo;
	}

	public void setCModelo(String cModelo) {
		this.cModelo = cModelo;
	}

	public String getCDscEstadoEQM() {
		return cDscEstadoEQM;
	}

	public void setCDscEstadoEQM(String cDscEstadoEQM) {
		this.cDscEstadoEQM = cDscEstadoEQM;
	}

	public String getCDscEquipo() {
		return cDscEquipo;
	}

	public void setCDscEquipo(String cDscEquipo) {
		this.cDscEquipo = cDscEquipo;
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

	public String getCDscArea() {
		return cDscArea;
	}

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCApPaterno() {
		return cApPaterno;
	}

	public void setCApPaterno(String cApPaterno) {
		this.cApPaterno = cApPaterno;
	}

	public String getCApMaterno() {
		return cApMaterno;
	}

	public void setCApMaterno(String cApMaterno) {
		this.cApMaterno = cApMaterno;
	}

	public String getCDtAsigna() {
		return cDtAsigna;
	}

	public void setCDtAsigna(String cDtAsigna) {
		this.cDtAsigna = cDtAsigna;
	}

	public String getCDtDesasigna() {
		return cDtDesasigna;
	}

	public void setCDtDesasigna(String cDtDesasigna) {
		this.cDtDesasigna = cDtDesasigna;
	}
}
