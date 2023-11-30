package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;

/**
 * <p>
 * Title: Value Object TOXCalibEquipo
 * </p>
 * <p>
 * Description: Lotes Cuantitativos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrejón Adame
 * @version 1.0
 */
public class TVTOXCalibEquipo implements HashBeanInterface {
	private BeanPK pk;
	private Integer iCveEquipo;
	private Integer iCveCalib;
	private java.sql.Date Fecha;
	private Integer iCveUsuCalib;
	private String cObservacion;
	private Integer iCveAcCorrectiva;
	private Integer lCorrecto;
	private String cNomCalibra;
	private String cCveEquipo;
	private String cNumSerie;

	public Vector vParametros;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEquipo);
		pk.add("" + iCveCalib);
		return pk;
	}

	public TVTOXCalibEquipo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveCalib", "" + iCveCalib);
		hmfieldsTable.put("Fecha", Fecha);
		hmfieldsTable.put("iCveUsuCalib", "" + iCveUsuCalib);
		hmfieldsTable.put("cObservacion", "" + cObservacion);
		hmfieldsTable.put("iCveAcCorrectiva", "" + iCveAcCorrectiva);
		hmfieldsTable.put("lCorrecto", lCorrecto);
		hmfieldsTable.put("cCveEquipo", this.cCveEquipo);
		hmfieldsTable.put("cNumSerie", this.cNumSerie);
		hmfieldsTable.put("cNomCalibra", this.cNomCalibra);
		return hmfieldsTable;
	}

	public Integer getiCveEquipo() {
		return iCveEquipo;
	}

	public void setiCveEquipo(Integer iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public Integer getiCveCalib() {
		return iCveCalib;
	}

	public void setiCveCalib(Integer iCveCalib) {
		this.iCveCalib = iCveCalib;
	}

	public void setFecha(java.sql.Date aFecha) {
		this.Fecha = aFecha;
	}

	public java.sql.Date getFecha() {
		return Fecha;
	}

	public Integer getiCveUsuCalib() {
		return iCveUsuCalib;
	}

	public void setiCveUsuCalib(Integer iCveUsuCalib) {
		this.iCveUsuCalib = iCveUsuCalib;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public Integer getiCveAcCorrectiva() {
		return iCveAcCorrectiva;
	}

	public void setiCveAcCorrectiva(Integer iCveAcCorrectiva) {
		this.iCveAcCorrectiva = iCveAcCorrectiva;
	}

	public Integer getlCorrecto() {
		return lCorrecto;
	}

	public void setlCorrecto(Integer lCorrecto) {
		this.lCorrecto = lCorrecto;
	}

	public String getCNomCalibra() {
		return cNomCalibra;
	}

	public void setCNomCalibra(String cNomCalibra) {
		this.cNomCalibra = cNomCalibra;
	}

	public String getCCveEquipo() {
		return cCveEquipo;
	}

	public void setCCveEquipo(String cCveEquipo) {
		this.cCveEquipo = cCveEquipo;
	}

	public String getCNumSerie() {
		return cNumSerie;
	}

	public void setCNumSerie(String cNumSerie) {
		this.cNumSerie = cNumSerie;
	}
}
