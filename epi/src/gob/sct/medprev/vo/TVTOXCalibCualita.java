package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object CalibCualita
 * </p>
 * <p>
 * Description: VO para el manejo de CalibCualita
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
public class TVTOXCalibCualita implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveLaboratorio;
	private int iCveExamCualita;
	private int iCveLoteCualita;
	private int iCveSustancia;
	private int iCveCalibCualita;
	private int iCveReactivo;
	private int iCveCtrolCalibra;
	private java.sql.Date dtCalibracion;
	private int iCveUsuRealiza;
	private float dCorte;
	private float dCorteNeg;
	private float dCortePost;
	private int iCveAcCorrectiva;
	private String cObservacion;
	private int lAutorizado;
	private int iCveUsuAutoriza;
	private int lActualizado;
	private String cDscReactivo;
	private String cDscCtrolCalibra;
	private String cDscAcCorrectiva;
	private String cDscSustancia;
	private float dCorte_cs;
	private float dCorteNeg_cs;
	private float dCortePost_cs;
	private float dCorte_ca;
	private float dCorteNeg_ca;
	private float dCortePost_ca;
	private float dCorteNeg_r;
	private float dCortePost_r;
	private String iCodigo;
	private String cLote;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveExamCualita);
		pk.add("" + iCveLoteCualita);
		pk.add("" + iCveSustancia);
		pk.add("" + iCveCalibCualita);
		return pk;
	}

	public TVTOXCalibCualita() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveExamCualita", "" + iCveExamCualita);
		hmfieldsTable.put("iCveLoteCualita", "" + iCveLoteCualita);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("iCveCalibCualita", "" + iCveCalibCualita);
		hmfieldsTable.put("iCveReactivo", "" + iCveReactivo);
		hmfieldsTable.put("iCveCtrolCalibra", "" + iCveCtrolCalibra);
		hmfieldsTable.put("dtCalibracion", "" + dtCalibracion);
		hmfieldsTable.put("iCveUsuRealiza", "" + iCveUsuRealiza);
		hmfieldsTable.put("dCorte", "" + dCorte);
		hmfieldsTable.put("dCorteNeg", "" + dCorteNeg);
		hmfieldsTable.put("dCortePost", "" + dCortePost);
		hmfieldsTable.put("iCveAcCorrectiva", "" + iCveAcCorrectiva);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lAutorizado", "" + lAutorizado);
		hmfieldsTable.put("iCveUsuAutoriza", "" + iCveUsuAutoriza);
		hmfieldsTable.put("cDscReactivo", "" + cDscReactivo);
		hmfieldsTable.put("cDscCtrolCalibra", "" + cDscCtrolCalibra);
		hmfieldsTable.put("cDscAcCorrectiva", "" + cDscAcCorrectiva);
		hmfieldsTable.put("cDscSustancia", cDscSustancia);
		hmfieldsTable.put("dCorte_cs", "" + dCorte_cs);
		hmfieldsTable.put("dCorteNeg_cs", "" + dCorteNeg_cs);
		hmfieldsTable.put("dCortePost_cs", "" + dCortePost_cs);
		hmfieldsTable.put("dCorte_ca", "" + dCorte_ca);
		hmfieldsTable.put("dCorteNeg_ca", "" + dCorteNeg_ca);
		hmfieldsTable.put("dCortePost_ca", "" + dCortePost_ca);
		hmfieldsTable.put("dCorteNeg_r", "" + dCorteNeg_r);
		hmfieldsTable.put("dCortePost_r", "" + dCortePost_r);
		hmfieldsTable.put("lActualizado", "" + lActualizado);
		hmfieldsTable.put("iCodigo", "" + this.iCodigo);
		hmfieldsTable.put("cLote", this.cLote);
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

	public int getICveExamCualita() {
		return iCveExamCualita;
	}

	public void setICveExamCualita(int iCveExamCualita) {
		this.iCveExamCualita = iCveExamCualita;
	}

	public int getICveLoteCualita() {
		return iCveLoteCualita;
	}

	public void setICveLoteCualita(int iCveLoteCualita) {
		this.iCveLoteCualita = iCveLoteCualita;
	}

	public int getICveSustancia() {
		return iCveSustancia;
	}

	public void setICveSustancia(int iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public int getICveCalibCualita() {
		return iCveCalibCualita;
	}

	public void setICveCalibCualita(int iCveCalibCualita) {
		this.iCveCalibCualita = iCveCalibCualita;
	}

	public int getICveReactivo() {
		return iCveReactivo;
	}

	public void setICveReactivo(int iCveReactivo) {
		this.iCveReactivo = iCveReactivo;
	}

	public int getICveControlCalibra() {
		return iCveCtrolCalibra;
	}

	public void setICveCtrolCalibra(int iCveCtrolCalibra) {
		this.iCveCtrolCalibra = iCveCtrolCalibra;
	}

	public java.sql.Date getDtCalibracion() {
		return dtCalibracion;
	}

	public void setDtCalibracion(java.sql.Date dtCalibracion) {
		this.dtCalibracion = dtCalibracion;
	}

	public int getICveUsuRealiza() {
		return iCveUsuRealiza;
	}

	public void setICveUsuRealiza(int iCveUsuRealiza) {
		this.iCveUsuRealiza = iCveUsuRealiza;
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

	public float getDCortePost() {
		return dCortePost;
	}

	public void setDCortePost(float dCortePost) {
		this.dCortePost = dCortePost;
	}

	public int getICveAcCorrectiva() {
		return iCveAcCorrectiva;
	}

	public void setICveAcCorrectiva(int iCveAcCorrectiva) {
		this.iCveAcCorrectiva = iCveAcCorrectiva;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getLAutorizado() {
		return lAutorizado;
	}

	public void setLAutorizado(int lAutorizado) {
		this.lAutorizado = lAutorizado;
	}

	public int getICveUsuAutoriza() {
		return iCveUsuAutoriza;
	}

	public void setICveUsuAutoriza(int iCveUsuAutoriza) {
		this.iCveUsuAutoriza = iCveUsuAutoriza;
	}

	public String getCDscReactivo() {
		return cDscReactivo;
	}

	public void setCDscReactivo(String cDscReactivo) {
		this.cDscReactivo = cDscReactivo;
	}

	public String getCDscCtrolCalibra() {
		return cDscCtrolCalibra;
	}

	public void setCDscCtrolCalibra(String cDscCtrolCalibra) {
		this.cDscCtrolCalibra = cDscCtrolCalibra;
	}

	public String getCDscAcCorrectiva() {
		return cDscAcCorrectiva;
	}

	public void setCDscAcCorrectiva(String cDscAcCorrectiva) {
		this.cDscAcCorrectiva = cDscAcCorrectiva;
	}

	public String getCDscSustancia() {
		return cDscSustancia;
	}

	public void setCDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

	public float getDCorte_cs() {
		return dCorte_cs;
	}

	public void setDCorte_cs(float dCorte_cs) {
		this.dCorte_cs = dCorte_cs;
	}

	public float getDCorteNeg_cs() {
		return dCorteNeg_cs;
	}

	public void setDCorteNeg_cs(float dCorteNeg_cs) {
		this.dCorteNeg_cs = dCorteNeg_cs;
	}

	public float getDCortePost_cs() {
		return dCortePost_cs;
	}

	public void setDCortePost_cs(float dCortePost_cs) {
		this.dCortePost_cs = dCortePost_cs;
	}

	public float getDCorte_ca() {
		return dCorte_ca;
	}

	public void setDCorte_ca(float dCorte_ca) {
		this.dCorte_ca = dCorte_ca;
	}

	public float getDCorteNeg_ca() {
		return dCorteNeg_ca;
	}

	public void setDCorteNeg_ca(float dCorteNeg_ca) {
		this.dCorteNeg_ca = dCorteNeg_ca;
	}

	public float getDCortePost_ca() {
		return dCortePost_ca;
	}

	public void setDCortePost_ca(float dCortePost_ca) {
		this.dCortePost_ca = dCortePost_ca;
	}

	public float getDCorteNeg_r() {
		return dCorteNeg_r;
	}

	public void setDCorteNeg_r(float dCorteNeg_r) {
		this.dCorteNeg_r = dCorteNeg_r;
	}

	public float getDCortePost_r() {
		return dCortePost_r;
	}

	public void setDCortePost_r(float dCortePost_r) {
		this.dCortePost_r = dCortePost_r;
	}

	public int getLActulizado() {
		return lActualizado;
	}

	public void setLActualizado(int lActualizado) {
		this.lActualizado = lActualizado;
	}

	public String getICodigo() {
		return iCodigo;
	}

	public void setICodigo(String iCodigo) {
		this.iCodigo = iCodigo;
	}

	public String getCLote() {
		return cLote;
	}

	public void setCLote(String cLote) {
		this.cLote = cLote;
	}

}
