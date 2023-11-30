package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object ALMMovimiento
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
public class TVALMMovimiento implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveAlmacen;
	private int iCveMovimiento;
	private int iCveSolicSum;
	private int iCveArticulo;
	private int iCveTpoMovimiento;
	private int iCveConcepto;
	private double dUnidades;
	private int iCveUsuario;
	private java.sql.Date dtMovimiento;
	private String cObservacion;
	private int lPNC;
	private String cDscBreve;
	private String cDscConcepto;
	private String cUsuario;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveMovimiento);
		return pk;
	}

	public TVALMMovimiento() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveMovimiento", "" + iCveMovimiento);
		hmfieldsTable.put("iCveSolicSum", "" + iCveSolicSum);
		hmfieldsTable.put("iCveArticulo", "" + iCveArticulo);
		hmfieldsTable.put("iCveTpoMovimiento", "" + iCveTpoMovimiento);
		hmfieldsTable.put("iCveConcepto", "" + iCveConcepto);
		hmfieldsTable.put("dUnidades", "" + dUnidades);
		hmfieldsTable.put("iCveUsuario", "" + iCveUsuario);
		hmfieldsTable.put("dtMovimiento", "" + dtMovimiento);
		hmfieldsTable.put("cObservacion", "" + cObservacion);
		hmfieldsTable.put("lPNC", "" + lPNC);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cDscConcepto", cDscConcepto);
		hmfieldsTable.put("cUsuario", cUsuario);
		return hmfieldsTable;
	}

	public int getiAnio() {
		return iAnio;
	}

	public void setiAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getiCveAlmacen() {
		return iCveAlmacen;
	}

	public void setiCveAlmacen(int iCveAlmacen) {
		this.iCveAlmacen = iCveAlmacen;
	}

	public int getiCveMovimiento() {
		return iCveMovimiento;
	}

	public void setiCveMovimiento(int iCveMovimiento) {
		this.iCveMovimiento = iCveMovimiento;
	}

	public int getiCveSolicSum() {
		return iCveSolicSum;
	}

	public void setiCveSolicSum(int iCveSolicSum) {
		this.iCveSolicSum = iCveSolicSum;
	}

	public int getiCveArticulo() {
		return iCveArticulo;
	}

	public void setiCveArticulo(int iCveArticulo) {
		this.iCveArticulo = iCveArticulo;
	}

	public int getiCveTpoMovimiento() {
		return iCveTpoMovimiento;
	}

	public void setiCveTpoMovimiento(int iCveTpoMovimiento) {
		this.iCveTpoMovimiento = iCveTpoMovimiento;
	}

	public int getiCveConcepto() {
		return iCveConcepto;
	}

	public void setiCveConcepto(int iCveConcepto) {
		this.iCveConcepto = iCveConcepto;
	}

	public double getdUnidades() {
		return dUnidades;
	}

	public void setdUnidades(double dUnidades) {
		this.dUnidades = dUnidades;
	}

	public int getiCveUsuario() {
		return iCveUsuario;
	}

	public void setiCveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
	}

	public java.sql.Date getdtMovimiento() {
		return dtMovimiento;
	}

	public void setdtMovimiento(java.sql.Date dtMovimiento) {
		this.dtMovimiento = dtMovimiento;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getlPNC() {
		return lPNC;
	}

	public void setlPNC(int lPNC) {
		this.lPNC = lPNC;
	}

	public String getcDscBreve() {
		return cDscBreve;
	}

	public void setcDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getcDscConcepto() {
		return cDscConcepto;
	}

	public void setcDscConcepto(String cDscConcepto) {
		this.cDscConcepto = cDscConcepto;
	}

	public String getcUsuario() {
		return cUsuario;
	}

	public void setcUsuario(String cUsuario) {
		this.cUsuario = cUsuario;
	}
}
