package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.util.TNumeros;

/**
 * <p>
 * Title: Value Object TOXExamResult
 * </p>
 * <p>
 * Description: Resultado del Exámen Toxicológico
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
public class TVTOXCuantAnalisis implements HashBeanInterface {
	private BeanPK pk;
	private Integer iAnio;
	private Integer iCveSustancia;
	private Integer iCveLoteCuantita;
	private Integer iCveAnalisis;
	private Integer iCveLaboratorio;

	private Integer iCveCtrolCalibra;
	private Integer lControl;
	private Integer iPosicion;
	private Integer iDilusion;
	private Integer lResultado;
	private Double dResultado;
	private Double dResultadoDil;
	private Integer lAutorizado;
	private Double dConcentracion;
	private String cObservacion;
	private String cDscCalib;
	private Double porcentaje;
	private Double dMargenError;
	private double dTmpRetenc;
	private double dIon01;
	private double dIon02;
	private double dIon03;
	private double dTmpRetencD;
	private double dIon04;
	private double dIon05;
	private Integer lCorrecto;
	private Integer lRegistrado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveSustancia);
		pk.add("" + iCveLoteCuantita);
		pk.add("" + iCveAnalisis);
		pk.add("" + iCveLaboratorio);
		return pk;
	}

	public TVTOXCuantAnalisis() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("iCveLoteCuantita", "" + iCveLoteCuantita);
		hmfieldsTable.put("iCveAnalisis", "" + iCveAnalisis);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveCtrolCalibra", "" + iCveCtrolCalibra);
		hmfieldsTable.put("lControl", "" + lControl);
		hmfieldsTable.put("iPosicion", "" + iPosicion);
		hmfieldsTable.put("iDilusion", "" + iDilusion);
		hmfieldsTable.put("lResultado", "" + lResultado);
		hmfieldsTable.put("dResultado", "" + dResultado);
		hmfieldsTable.put("dResultadoDil", "" + dResultadoDil);
		hmfieldsTable.put("lAutorizado", "" + lAutorizado);
		hmfieldsTable.put("dConcentracion", "" + dConcentracion);
		hmfieldsTable.put("cObservacion", "" + cObservacion);
		hmfieldsTable.put("dTmpRetenc", "" + this.dTmpRetenc);
		hmfieldsTable.put("dIon01", "" + this.dIon01);
		hmfieldsTable.put("dIon02", "" + this.dIon02);
		hmfieldsTable.put("dIon03", "" + this.dIon03);
		hmfieldsTable.put("dTmpRetencD", "" + this.dTmpRetencD);
		hmfieldsTable.put("dIon04", "" + this.dIon04);
		hmfieldsTable.put("dIon05", "" + this.dIon05);
		hmfieldsTable.put("lCorrecto", "" + this.lCorrecto);
		hmfieldsTable.put("cDscCalib", this.cDscCalib);
		return hmfieldsTable;
	}

	public Integer getiAnio() {
		return iAnio;
	}

	public void setiAnio(Integer iAnio) {
		this.iAnio = iAnio;
	}

	public Integer getiCveSustancia() {
		return iCveSustancia;
	}

	public void setiCveSustancia(Integer iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public Integer getiCveLoteCuantita() {
		return iCveLoteCuantita;
	}

	public void setiCveLoteCuantita(Integer iCveLoteCuantita) {
		this.iCveLoteCuantita = iCveLoteCuantita;
	}

	public Integer getiCveAnalisis() {
		return iCveAnalisis;
	}

	public void setiCveAnalisis(Integer iCveAnalisis) {
		this.iCveAnalisis = iCveAnalisis;
	}

	public Integer getiCveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setiCveLaboratorio(Integer iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public Integer getiCveCtrolCalibra() {
		return iCveCtrolCalibra;
	}

	public void setiCveCtrolCalibra(Integer iCveCtrolCalibra) {
		this.iCveCtrolCalibra = iCveCtrolCalibra;
	}

	public Integer getlControl() {
		return lControl;
	}

	public void setlControl(Integer lControl) {
		this.lControl = lControl;
	}

	public Integer getiPosicion() {
		return iPosicion;
	}

	public void setiPosicion(Integer iPosicion) {
		this.iPosicion = iPosicion;
	}

	public Integer getiDilusion() {
		return iDilusion;
	}

	public void setiDilusion(Integer iDilusion) {
		this.iDilusion = iDilusion;
	}

	public Integer getlResultado() {
		return lResultado;
	}

	public void setlResultado(Integer lResultado) {
		this.lResultado = lResultado;
	}

	public Double getdResultado() {
		return dResultado;
	}

	public void setdResultado(Double dResultado) {
		this.dResultado = dResultado;
	}

	public Double getdResultadoDil() {
		return dResultadoDil;
	}

	public void setdResultadoDil(Double dResultadoDil) {
		this.dResultadoDil = dResultadoDil;
	}

	public Integer getlAutorizado() {
		return lAutorizado;
	}

	public void setlAutorizado(Integer lAutorizado) {
		this.lAutorizado = lAutorizado;
	}

	public Double getdConcentracion() {
		return dConcentracion;
	}

	public void setdConcentracion(Double dConcentracion) {
		this.dConcentracion = dConcentracion;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public String getCAnio() {
		return String.valueOf(this.getiAnio()).substring(2);
	}

	public String getCAnalisis() {
		TNumeros Numeros = new TNumeros();
		return (this.getCAnio() + Numeros.getNumeroSinSeparador(
				this.getiCveAnalisis(), 6));
	}

	public String getCDscCalib() {
		return cDscCalib;
	}

	public void setCDscCalib(String cDscCalib) {
		this.cDscCalib = cDscCalib;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Double getDMargenError() {
		return dMargenError;
	}

	public void setDMargenError(Double dMargenError) {
		this.dMargenError = dMargenError;
	}

	public double getDTmpRetenc() {
		return dTmpRetenc;
	}

	public void setDTmpRetenc(double dTmpRetenc) {
		this.dTmpRetenc = dTmpRetenc;
	}

	public double getDIon01() {
		return dIon01;
	}

	public void setDIon01(double dIon01) {
		this.dIon01 = dIon01;
	}

	public double getDIon02() {
		return dIon02;
	}

	public void setDIon02(double dIon02) {
		this.dIon02 = dIon02;
	}

	public double getDIon03() {
		return dIon03;
	}

	public void setDIon03(double dIon03) {
		this.dIon03 = dIon03;
	}

	public double getDTmpRetencD() {
		return dTmpRetencD;
	}

	public void setDTmpRetencD(double dTmpRetencD) {
		this.dTmpRetencD = dTmpRetencD;
	}

	public double getDIon04() {
		return dIon04;
	}

	public void setDIon04(double dIon04) {
		this.dIon04 = dIon04;
	}

	public double getDIon05() {
		return dIon05;
	}

	public void setDIon05(double dIon05) {
		this.dIon05 = dIon05;
	}

	public Integer getLCorrecto() {
		return lCorrecto;
	}

	public void setLCorrecto(Integer lCorrecto) {
		this.lCorrecto = lCorrecto;
	}

	public Integer getLRegistrado() {
		return lRegistrado;
	}

	public void setLRegistrado(Integer lRegistrado) {
		this.lRegistrado = lRegistrado;
	}

}
