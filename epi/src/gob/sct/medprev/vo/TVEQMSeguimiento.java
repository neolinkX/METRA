package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EQMSeguimiento
 * </p>
 * <p>
 * Description: VO para la administración de la Información de la tabla
 * EQMSeguimiento
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
public class TVEQMSeguimiento implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEquipo;
	private int iCveMantenimiento;
	private int iCveMovimiento;
	private int iCveProceso;
	private int iCveEtapa;
	private java.sql.Date dtSolicitud;
	private int iCveSolicitante;
	private int iCveUsuReg;
	private int iCveUsuSolicita;
	private String cSolicitante;
	private String cObservacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEquipo);
		pk.add("" + iCveMantenimiento);
		pk.add("" + iCveMovimiento);
		return pk;
	}

	public TVEQMSeguimiento() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveMantenimiento", "" + iCveMantenimiento);
		hmfieldsTable.put("iCveMovimiento", "" + iCveMovimiento);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iCveEtapa", "" + iCveEtapa);
		hmfieldsTable.put("dtSolicitud", "" + dtSolicitud);
		hmfieldsTable.put("iCveSolicitante", "" + iCveSolicitante);
		hmfieldsTable.put("iCveUsuReg", "" + iCveUsuReg);
		hmfieldsTable.put("iCveUsuSolicita", "" + iCveUsuSolicita);
		hmfieldsTable.put("cSolicitante", cSolicitante);
		hmfieldsTable.put("cObservacion", cObservacion);
		return hmfieldsTable;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public int getICveMantenimiento() {
		return iCveMantenimiento;
	}

	public void setICveMantenimiento(int iCveMantenimiento) {
		this.iCveMantenimiento = iCveMantenimiento;
	}

	public int getICveMovimiento() {
		return iCveMovimiento;
	}

	public void setICveMovimiento(int iCveMovimiento) {
		this.iCveMovimiento = iCveMovimiento;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getICveEtapa() {
		return iCveEtapa;
	}

	public void setICveEtapa(int iCveEtapa) {
		this.iCveEtapa = iCveEtapa;
	}

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public int getICveSolicitante() {
		return iCveSolicitante;
	}

	public void setICveSolicitante(int iCveSolicitante) {
		this.iCveSolicitante = iCveSolicitante;
	}

	public int getICveUsuReg() {
		return iCveUsuReg;
	}

	public void setICveUsuReg(int iCveUsuReg) {
		this.iCveUsuReg = iCveUsuReg;
	}

	public int getICveUsuSolicita() {
		return iCveUsuSolicita;
	}

	public void setICveUsuSolicita(int iCveUsuSolicita) {
		this.iCveUsuSolicita = iCveUsuSolicita;
	}

	public String getCSolicitante() {
		return cSolicitante;
	}

	public void setCSolicitante(String cSolicitante) {
		this.cSolicitante = cSolicitante;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}
}
