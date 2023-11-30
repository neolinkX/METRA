package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;

/**
 * <p>
 * Title: Value Object TOXCorteXSust
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
public class TVTOXCorteXSust implements HashBeanInterface {
	private BeanPK pk;
	private Integer iCveSustancia;
	private Integer iCveCorte;

	private Integer lActivo;
	private java.sql.Date dtInicio;
	private java.sql.Date dtFin;
	private Integer iCveUsuAutoriza;
	private Double dCorte;
	private Double dCorteNeg;
	private Double dCortePost;
	private Double dMargenError;
	private String cDscSustancia;
	private String cDscUsuAutoriza;
	private int lCuantCual;
	private String cControles;
	private double dMargConcCal;
	private double dMargTmpRet;
	private double dMargRelacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveSustancia);
		pk.add("" + iCveCorte);
		return pk;
	}

	public TVTOXCorteXSust() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("iCveCorte", "" + iCveCorte);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("dtInicio", "" + dtInicio);
		hmfieldsTable.put("dtFin", "" + dtFin);
		hmfieldsTable.put("iCveUsuAutoriza", "" + iCveUsuAutoriza);
		hmfieldsTable.put("dCorte", "" + dCorte);
		hmfieldsTable.put("dCorteNeg", "" + dCorteNeg);
		hmfieldsTable.put("dCortePost", "" + dCortePost);
		hmfieldsTable.put("dMargenError", "" + dMargenError);
		hmfieldsTable.put("cDscSustancia", "" + cDscSustancia);
		hmfieldsTable.put("cDscUsuAutoriza", "" + cDscUsuAutoriza);
		hmfieldsTable.put("lCuantCual", "" + lCuantCual);
		hmfieldsTable.put("cControles", "" + cControles);
		hmfieldsTable.put("dMargConcCal", String.valueOf(this.dMargConcCal));
		hmfieldsTable.put("dMargTmpRet", String.valueOf(this.dMargTmpRet));
		hmfieldsTable.put("dMargRelacion", String.valueOf(this.dMargRelacion));
		return hmfieldsTable;
	}

	public Integer getiCveSustancia() {
		return iCveSustancia;
	}

	public void setiCveSustancia(Integer iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public Integer getiCveCorte() {
		return iCveCorte;
	}

	public void setiCveCorte(Integer iCveCorte) {
		this.iCveCorte = iCveCorte;
	}

	public Integer getlActivo() {
		return lActivo;
	}

	public void setlActivo(Integer lActivo) {
		this.lActivo = lActivo;
	}

	public void setdtInicio(java.sql.Date adtInicio) {
		this.dtInicio = adtInicio;
	}

	public java.sql.Date getdtInicio() {
		return dtInicio;
	}

	public void setdtFin(java.sql.Date adtFin) {
		this.dtFin = adtFin;
	}

	public java.sql.Date getdtFin() {
		return dtFin;
	}

	public Integer getiCveUsuAutoriza() {
		return iCveUsuAutoriza;
	}

	public void setiCveUsuAutoriza(Integer iCveUsuAutoriza) {
		this.iCveUsuAutoriza = iCveUsuAutoriza;
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

	public Double getdMargenError() {
		return dMargenError;
	}

	public void setdMargenError(Double dMargenError) {
		this.dMargenError = dMargenError;
	}

	public String getCDscSustancia() {
		return cDscSustancia;
	}

	public void setCDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

	public String getCDscUsuAutoriza() {
		return cDscUsuAutoriza;
	}

	public void setCDscUsuAutoriza(String cDscUsuAutoriza) {
		this.cDscUsuAutoriza = cDscUsuAutoriza;
	}

	public int getLCuantCual() {
		return lCuantCual;
	}

	public void setLCuantCual(int lCuantCual) {
		this.lCuantCual = lCuantCual;
	}

	public String getCControles() {
		return cControles;
	}

	public void setCControles(String cControles) {
		this.cControles = cControles;
	}

	public double getDMargConcCal() {
		return dMargConcCal;
	}

	public void setDMargConcCal(double dMargConcCal) {
		this.dMargConcCal = dMargConcCal;
	}

	public double getDMargTmpRet() {
		return dMargTmpRet;
	}

	public void setDMargTmpRet(double dMargTmpRet) {
		this.dMargTmpRet = dMargTmpRet;
	}

	public double getDMargRelacion() {
		return dMargRelacion;
	}

	public void setDMargRelacion(double dMargRelacion) {
		this.dMargRelacion = dMargRelacion;
	}
}
