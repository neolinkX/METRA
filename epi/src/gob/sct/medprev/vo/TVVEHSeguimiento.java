package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object VEHSeguimiento
 * </p>
 * <p>
 * Description: Value Object de Seguimiento de Mantenimiento de Vehículos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 */
public class TVVEHSeguimiento implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMantenimiento;
	private int iCveMovimiento;
	private int iCveVehiculo;
	private int iCveProceso;
	private String cDscProceso;
	private int iCveEtapa;
	private String cDscEtapa;
	private int iCveSolicitante;
	private String cDscSolicitante;
	private java.sql.Date dtSolicitud;
	private int iCveUsuReg;
	private String cDscUsuReg;
	private int iCveUsuSolicita;
	private String cDscUsuSolicita;
	private String cSolicitante;
	private String cObservacion;
	private int lConcluido;
	private int lCancelado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMantenimiento);
		pk.add("" + iCveMovimiento);
		pk.add("" + iCveVehiculo);
		return pk;
	}

	public TVVEHSeguimiento() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMantenimiento", "" + iCveMantenimiento);
		hmfieldsTable.put("iCveMovimiento", "" + iCveMovimiento);
		hmfieldsTable.put("iCveVehiculo", "" + iCveVehiculo);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("cDscProceso", cDscProceso);
		hmfieldsTable.put("iCveEtapa", "" + iCveEtapa);
		hmfieldsTable.put("cDscEtapa", cDscEtapa);
		hmfieldsTable.put("iCveSolicitante", "" + iCveSolicitante);
		hmfieldsTable.put("cDscSolicitante", cDscSolicitante);
		hmfieldsTable.put("dtSolicitud", "" + dtSolicitud);
		hmfieldsTable.put("iCveUsuReg", "" + iCveUsuReg);
		hmfieldsTable.put("cDscUsuReg", cDscUsuReg);
		hmfieldsTable.put("iCveUsuSolicita", "" + iCveUsuSolicita);
		hmfieldsTable.put("cDscUsuSolicita", cDscUsuSolicita);
		hmfieldsTable.put("cSolicitante", cSolicitante);
		hmfieldsTable.put("cObservacion", cObservacion);
		return hmfieldsTable;
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

	public int getICveVehiculo() {
		return iCveVehiculo;
	}

	public void setICveVehiculo(int iCveVehiculo) {
		this.iCveVehiculo = iCveVehiculo;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public int getICveEtapa() {
		return iCveEtapa;
	}

	public void setICveEtapa(int iCveEtapa) {
		this.iCveEtapa = iCveEtapa;
	}

	public String getCDscEtapa() {
		return cDscEtapa;
	}

	public void setCDscEtapa(String cDscEtapa) {
		this.cDscEtapa = cDscEtapa;
	}

	public int getICveSolicitante() {
		return iCveSolicitante;
	}

	public void setICveSolicitante(int iCveSolicitante) {
		this.iCveSolicitante = iCveSolicitante;
	}

	public String getCDscSolicitante() {
		return cDscSolicitante;
	}

	public void setCDscSolicitante(String cDscSolicitante) {
		this.cDscSolicitante = cDscSolicitante;
	}

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public int getICveUsuReg() {
		return iCveUsuReg;
	}

	public void setICveUsuReg(int iCveUsuReg) {
		this.iCveUsuReg = iCveUsuReg;
	}

	public String getCDscUsuReg() {
		return cDscUsuReg;
	}

	public void setCDscUsuReg(String cDscUsuReg) {
		this.cDscUsuReg = cDscUsuReg;
	}

	public int getICveUsuSolicita() {
		return iCveUsuSolicita;
	}

	public void setICveUsuSolicita(int iCveUsuSolicita) {
		this.iCveUsuSolicita = iCveUsuSolicita;
	}

	public String getCDscUsuSolicita() {
		return cDscUsuSolicita;
	}

	public void setCDscUsuSolicita(String cDscUsuSolicita) {
		this.cDscUsuSolicita = cDscUsuSolicita;
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

	public int getLConcluido() {
		return lConcluido;
	}

	public void setLConcluido(int lConcluido) {
		this.lConcluido = lConcluido;
	}

	public int getLCancelado() {
		return lCancelado;
	}

	public void setLCancelado(int lCancelado) {
		this.lCancelado = lCancelado;
	}
}
