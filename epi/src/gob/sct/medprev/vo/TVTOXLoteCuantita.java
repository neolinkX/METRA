package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.util.TNumeros;

/**
 * <p>
 * Title: Value Object TOXLoteCuantita
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
 * @author LCI. Oscar Castrej�n Adame
 * @version 1.0
 */
public class TVTOXLoteCuantita implements HashBeanInterface {
	private BeanPK pk;
	private Integer iAnio;
	private Integer iCveLaboratorio;
	private Integer iCveSustancia;
	private Integer iCveLoteCuantita;

	private java.sql.Date dtGeneracion;
	private Integer iCveUsuGenera;
	private java.sql.Date dtAnalisis;
	private java.sql.Date dtAutorizacion;
	private Double dCorte;
	private Double dCorteNeg;
	private Double dCortePost;
	private Integer iCveEquipo;
	private Integer iCveUsuAnalista;
	private Integer iCveUsuEncarga;
	private Integer iCveCorte;
	private Integer iCveCalib;
	private Integer iCveAcCorrectiva;
	private String cObservacion;
	private java.sql.Date dtCalibracion;
	private Integer lValidaCalib;
	public TVSustancia VSustancia;
	public TVEQMEquipo VEquipo;
	public TVTOXCuantAnalisis VCuantAn;
	private int iNumMuestras;
	private boolean lValidacion;
	private String cDscSustancia;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveSustancia);
		pk.add("" + iCveLoteCuantita);
		return pk;
	}

	public TVTOXLoteCuantita() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("iCveLoteCuantita", "" + iCveLoteCuantita);
		hmfieldsTable.put("dtGeneracion", "" + dtGeneracion);
		hmfieldsTable.put("iCveUsuGenera", "" + iCveUsuGenera);
		hmfieldsTable.put("dtAnalisis", "" + dtAnalisis);
		hmfieldsTable.put("dtAutorizacion", "" + dtAutorizacion);
		hmfieldsTable.put("dCorte", "" + dCorte);
		hmfieldsTable.put("dCorteNeg", "" + dCorteNeg);
		hmfieldsTable.put("dCortePost", "" + dCortePost);
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveUsuAnalista", "" + iCveUsuAnalista);
		hmfieldsTable.put("iCveUsuEncarga", "" + iCveUsuEncarga);
		hmfieldsTable.put("iCveCorte", "" + iCveCorte);
		hmfieldsTable.put("iCveCalib", "" + iCveCalib);

		hmfieldsTable.put("dtCalibracion", "" + dtCalibracion);
		hmfieldsTable.put("lValidaCalib", "" + lValidaCalib);
		// hmfieldsTable.put("cDscSustancia", "" + cDscSustancia);
		return hmfieldsTable;
	}

	public Integer getiAnio() {
		return iAnio;
	}

	public void setiAnio(Integer iAnio) {
		this.iAnio = iAnio;
	}

	public Integer getiCveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setiCveLaboratorio(Integer iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
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

	public void setdtGeneracion(java.sql.Date adtGeneracion) {
		this.dtGeneracion = adtGeneracion;
	}

	public java.sql.Date getdtGeneracion() {
		return dtGeneracion;
	}

	public Integer getiCveUsuGenera() {
		return iCveUsuGenera;
	}

	public void setiCveUsuGenera(Integer iCveUsuGenera) {
		this.iCveUsuGenera = iCveUsuGenera;
	}

	public void setdtAnalisis(java.sql.Date adtAnalisis) {
		this.dtAnalisis = adtAnalisis;
	}

	public java.sql.Date getdtAnalisis() {
		return dtAnalisis;
	}

	public void setdtAutorizacion(java.sql.Date adtAutorizacion) {
		this.dtAutorizacion = adtAutorizacion;
	}

	public java.sql.Date getdtAutorizacion() {
		return dtAutorizacion;
	}

	public Integer getiCveEquipo() {
		return iCveEquipo;
	}

	public void setiCveEquipo(Integer iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public Double getdCorte() {
		return dCorte;
	}

	public void setdCorte(Double dCorte) {
		this.dCorte = dCorte;
	}

	public Double getdCorteNeg() {
		return dCorteNeg;
	}

	public void setdCorteNeg(Double dCorteNeg) {
		this.dCorteNeg = dCorteNeg;
	}

	public Double getdCortePost() {
		return dCortePost;
	}

	public void setdCortePost(Double dCortePost) {
		this.dCortePost = dCortePost;
	}

	public Integer getiCveUsuAnalista() {
		return iCveUsuAnalista;
	}

	public void setiCveUsuAnalista(Integer iCveUsuAnalista) {
		this.iCveUsuAnalista = iCveUsuAnalista;
	}

	public Integer getiCveUsuEncarga() {
		return iCveUsuEncarga;
	}

	public void setiCveUsuEncarga(Integer iCveUsuEncarga) {
		this.iCveUsuEncarga = iCveUsuEncarga;
	}

	public Integer getiCveCorte() {
		return iCveCorte;
	}

	public void setiCveCorte(Integer iCveCorte) {
		this.iCveCorte = iCveCorte;
	}

	public Integer getiCveCalib() {
		return iCveCalib;
	}

	public void setiCveCalib(Integer iCveCalib) {
		this.iCveCalib = iCveCalib;
	}

	public Integer getiCveAcCorrectiva() {
		return iCveAcCorrectiva;
	}

	public void setiCveAcCorrectiva(Integer iCveAcCorrectiva) {
		this.iCveAcCorrectiva = iCveAcCorrectiva;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public void setdtCalibracion(java.sql.Date adtCalibracion) {
		this.dtCalibracion = adtCalibracion;
	}

	public java.sql.Date getdtCalibracion() {
		return dtCalibracion;
	}

	public Integer getlValidaCalib() {
		return lValidaCalib;
	}

	public boolean getlValidacion() {
		return this.lValidacion;
	}

	public void setlValidaCalib(Integer lValidaCalib) {
		this.lValidaCalib = lValidaCalib;
	}

	public String getCLote() {
		StringBuffer cLote = new StringBuffer();
		TNumeros Numeros = new TNumeros();
		cLote.append(Numeros.getNumeroSinSeparador(this.getiAnio(), 4))
				.append("/")
				.append(this.VSustancia.getCPrefLoteConf())
				.append("-")
				.append(Numeros.getNumeroSinSeparador(
						this.getiCveLoteCuantita(), 4));
		return cLote.toString();
	}

	public int getINumMuestras() {
		return iNumMuestras;
	}

	public void setINumMuestras(int iNumMuestras) {
		this.iNumMuestras = iNumMuestras;
	}

	public boolean isLValidacion() {
		return lValidacion;
	}

	public void setLValidacion(boolean lValidacion) {
		this.lValidacion = lValidacion;
	}

	public String getcDscSustancia() {
		return cDscSustancia;
	}

	public void setcDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

}
