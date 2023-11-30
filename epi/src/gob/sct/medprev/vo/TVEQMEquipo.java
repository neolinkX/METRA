package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object Equipo
 * </p>
 * <p>
 * Description: VO para la tabla EQMEquipo
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
public class TVEQMEquipo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEquipo;
	private int iCveClasificacion;
	private String cDscEquipo;
	private String cNumSerie;
	private String cModelo;
	private int iCveTpoEquipo;
	private int iCveMarca;
	private String cCveEquipo;
	private String cInventario;
	private java.sql.Date dtAdquisicion;
	private int iCveUsuRegistra;
	private int iCveEstadoEQM;
	private int lActivo;
	private int iPerManttoPrev;
	private int iLimiteUso;
	private int iPerCalibracion;
	private String cObservacion;
	private int lBaja;
	private java.sql.Date dtBaja;
	private int iCveCausaBaja;
	private String cDscBreveTpoEquipo;
	private String cDscBreveClasif;
	private String cDscBreveMarca;
	private String cDscBreveEstado;
	private String cDscBreveCausaBaja;
	private String cDscBreveClasificacion;
	private String cDscUniMed;
	private String cDscModulo;
	private String cDscArea;
	private int lActual;
	private int iCveUniMed;
	private int iCveModulo;
	private int iCveArea;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEquipo);
		return pk;
	}

	public TVEQMEquipo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveClasificacion", "" + iCveClasificacion);
		hmfieldsTable.put("cDscEquipo", cDscEquipo);
		hmfieldsTable.put("cDscEquipoC", cCveEquipo + " / " + cDscEquipo
				+ " NS: " + cNumSerie);
		hmfieldsTable.put("cNumSerie", cNumSerie);
		hmfieldsTable.put("cModelo", cModelo);
		hmfieldsTable.put("iCveTpoEquipo", "" + iCveTpoEquipo);
		hmfieldsTable.put("iCveMarca", "" + iCveMarca);
		hmfieldsTable.put("cCveEquipo", cCveEquipo);
		hmfieldsTable.put("cInventario", cInventario);
		hmfieldsTable.put("dtAdquisicion", "" + dtAdquisicion);
		hmfieldsTable.put("iCveUsuRegistra", "" + iCveUsuRegistra);
		hmfieldsTable.put("iCveEstadoEQM", "" + iCveEstadoEQM);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("iPerManttoPrev", "" + iPerManttoPrev);
		hmfieldsTable.put("iLimiteUso", "" + iLimiteUso);
		hmfieldsTable.put("iPerCalibracion", "" + iPerCalibracion);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lBaja", "" + lBaja);
		hmfieldsTable.put("dtBaja", "" + dtBaja);
		hmfieldsTable.put("iCveCausaBaja", "" + iCveCausaBaja);
		hmfieldsTable.put("cDscBreveTpoEquipo", "" + cDscBreveTpoEquipo);
		hmfieldsTable.put("cDscBreveClasif", "" + cDscBreveClasif);
		hmfieldsTable.put("cDscBreveMarca", "" + cDscBreveMarca);
		hmfieldsTable.put("cDscBreveEstado", "" + cDscBreveEstado);
		hmfieldsTable.put("cDscBreveCausaBaja", "" + cDscBreveCausaBaja);
		hmfieldsTable
				.put("cDscBreveClasificacion", "" + cDscBreveClasificacion);
		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cDscModulo", "" + cDscModulo);
		hmfieldsTable.put("cDscArea", "" + cDscArea);
		hmfieldsTable.put("lActual", "" + lActual);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		return hmfieldsTable;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public int getICveClasificacion() {
		return iCveClasificacion;
	}

	public void setICveClasificacion(int iCveClasificacion) {
		this.iCveClasificacion = iCveClasificacion;
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

	public int getICveTpoEquipo() {
		return iCveTpoEquipo;
	}

	public void setICveTpoEquipo(int iCveTpoEquipo) {
		this.iCveTpoEquipo = iCveTpoEquipo;
	}

	public int getICveMarca() {
		return iCveMarca;
	}

	public void setICveMarca(int iCveMarca) {
		this.iCveMarca = iCveMarca;
	}

	public String getCCveEquipo() {
		return cCveEquipo;
	}

	public void setCCveEquipo(String cCveEquipo) {
		this.cCveEquipo = cCveEquipo;
	}

	public String getCInventario() {
		return cInventario;
	}

	public void setCInventario(String cInventario) {
		this.cInventario = cInventario;
	}

	public java.sql.Date getDtAdquisicion() {
		return dtAdquisicion;
	}

	public void setDtAdquisicion(java.sql.Date dtAdquisicion) {
		this.dtAdquisicion = dtAdquisicion;
	}

	public int getICveUsuRegistra() {
		return iCveUsuRegistra;
	}

	public void setICveUsuRegistra(int iCveUsuRegistra) {
		this.iCveUsuRegistra = iCveUsuRegistra;
	}

	public int getICveEstadoEQM() {
		return iCveEstadoEQM;
	}

	public void setICveEstadoEQM(int iCveEstadoEQM) {
		this.iCveEstadoEQM = iCveEstadoEQM;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public int getIPerManttoPrev() {
		return iPerManttoPrev;
	}

	public void setIPerManttoPrev(int iPerManttoPrev) {
		this.iPerManttoPrev = iPerManttoPrev;
	}

	public int getILimiteUso() {
		return iLimiteUso;
	}

	public void setILimiteUso(int iLimiteUso) {
		this.iLimiteUso = iLimiteUso;
	}

	public int getIPerCalibracion() {
		return iPerCalibracion;
	}

	public void setIPerCalibracion(int iPerCalibracion) {
		this.iPerCalibracion = iPerCalibracion;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
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

	public int getICveCausaBaja() {
		return iCveCausaBaja;
	}

	public void setICveCausaBaja(int iCveCausaBaja) {
		this.iCveCausaBaja = iCveCausaBaja;
	}

	public String getCDscBreveTpoEquipo() {
		return cDscBreveTpoEquipo;
	}

	public void setCDscBreveTpoEquipo(String cDscBreveTpoEquipo) {
		this.cDscBreveTpoEquipo = cDscBreveTpoEquipo;
	}

	public String getCDscBreveClasif() {
		return cDscBreveClasif;
	}

	public void setCDscBreveClasif(String cDscBreveClasif) {
		this.cDscBreveClasif = cDscBreveClasif;
	}

	public String getCDscBreveMarca() {
		return cDscBreveMarca;
	}

	public void setCDscBreveMarca(String cDscBreveMarca) {
		this.cDscBreveMarca = cDscBreveMarca;
	}

	public String getCDscBreveEstado() {
		return cDscBreveEstado;
	}

	public void setCDscBreveEstado(String cDscBreveEstado) {
		this.cDscBreveEstado = cDscBreveEstado;
	}

	public String getCDscBreveCausaBaja() {
		return cDscBreveCausaBaja;
	}

	public void setCDscBreveCausaBaja(String cDscBreveCausaBaja) {
		this.cDscBreveCausaBaja = cDscBreveCausaBaja;
	}

	public String getCDscBreveClasificacion() {
		return cDscBreveClasificacion;
	}

	public void setCDscBreveClasificacion(String cDscBreveClasificacion) {
		this.cDscBreveClasificacion = cDscBreveClasificacion;
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

	public int getLActual() {
		return lActual;
	}

	public void setLActual(int lActual) {
		this.lActual = lActual;
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

}
