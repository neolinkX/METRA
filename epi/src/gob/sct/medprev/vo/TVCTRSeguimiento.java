package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object CTRSeguimiento
 * </p>
 * <p>
 * Description: Value Object de la Tabla CTRSeguimiento
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */
public class TVCTRSeguimiento implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpresa;
	private int iCvePlantilla;
	private int iCveMovimiento;
	private int iCveProceso;
	private int iCveEtapa;
	private java.sql.Date dtSolicitud;
	private int iCveSolictante;
	private int iCveUsuReg;
	private int iCveUsuSolicita;
	private String cSolicitante;
	private String cObservacion;
	private String cNoOficio;
	private int iNoRegularizados;
	private int iRegularizados;
	private int iMultados;
	private int iMultaPagada;
	private int iAltas;
	private int iBajas;
	private int iOperadores;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpresa);
		pk.add("" + iCvePlantilla);
		pk.add("" + iCveMovimiento);
		return pk;
	}

	public TVCTRSeguimiento() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("iCvePlantilla", "" + iCvePlantilla);
		hmfieldsTable.put("iCveMovimiento", "" + iCveMovimiento);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iCveEtapa", "" + iCveEtapa);
		hmfieldsTable.put("dtSolicitud", "" + dtSolicitud);
		hmfieldsTable.put("iCveSolictante", "" + iCveSolictante);
		hmfieldsTable.put("iCveUsuReg", "" + iCveUsuReg);
		hmfieldsTable.put("iCveUsuSolicita", "" + iCveUsuSolicita);
		hmfieldsTable.put("cSolicitante", "" + cSolicitante);
		hmfieldsTable.put("cObservacion", "" + cObservacion);
		hmfieldsTable.put("cNoOficio", "" + cNoOficio);
		return hmfieldsTable;
	}

	public int getiCveEmpresa() {
		return iCveEmpresa;
	}

	public void setiCveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public int getiCvePlantilla() {
		return iCvePlantilla;
	}

	public void setiCvePlantilla(int iCvePlantilla) {
		this.iCvePlantilla = iCvePlantilla;
	}

	public int getiCveMovimiento() {
		return iCveMovimiento;
	}

	public void setiCveMovimiento(int iCveMovimiento) {
		this.iCveMovimiento = iCveMovimiento;
	}

	public int getiCveProceso() {
		return iCveProceso;
	}

	public void setiCveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getiCveEtapa() {
		return iCveEtapa;
	}

	public void setiCveEtapa(int iCveEtapa) {
		this.iCveEtapa = iCveEtapa;
	}

	public java.sql.Date getdtSolicitud() {
		return dtSolicitud;
	}

	public void setdtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public int getiCveSolictante() {
		return iCveSolictante;
	}

	public void setiCveSolictante(int iCveSolictante) {
		this.iCveSolictante = iCveSolictante;
	}

	public int getiCveUsuReg() {
		return iCveUsuReg;
	}

	public void setiCveUsuReg(int iCveUsuReg) {
		this.iCveUsuReg = iCveUsuReg;
	}

	public int getiCveUsuSolicita() {
		return iCveUsuSolicita;
	}

	public void setiCveUsuSolicita(int iCveUsuSolicita) {
		this.iCveUsuSolicita = iCveUsuSolicita;
	}

	public String getcSolicitante() {
		return cSolicitante;
	}

	public void setcSolicitante(String cSolicitante) {
		this.cSolicitante = cSolicitante;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public String getcNoOficio() {
		return cNoOficio;
	}

	public void setcNoOficio(String cNoOficio) {
		this.cNoOficio = cNoOficio;
	}
}
