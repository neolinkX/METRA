package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object GRLEmpresas
 * </p>
 * <p>
 * Description: Value Object de la Tabla GRLEmpresas
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
public class TVCTRPlantilla implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpresa;
	private int iCvePlantilla;
	private int iCveTpoEntrega;
	private int lProgramada;
	private int iAnio;
	private int iCvePeriodPla;
	private java.sql.Date dtSolicitud;
	private java.sql.Date dtVencimiento;
	private java.sql.Date dtRecepcion;
	private int iCveUsuSolicita;
	private int iCveUMSolicita;
	private int iCveUsuRecibe;
	private int iCveUMRecibe;
	private int iCveMedRecep;
	private String cDscEmpresa;
	private String cIDDGPMPT;
	private int iIDMdoTrans;
	private String cRFC;
	private String cDscPeriodPla;
	private String cTpoPersona;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpresa);
		pk.add("" + iCvePlantilla);
		return pk;
	}

	public TVCTRPlantilla() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("iCvePlantilla", "" + iCvePlantilla);
		hmfieldsTable.put("iCveTpoEntrega", "" + iCveTpoEntrega);
		hmfieldsTable.put("lProgramada", "" + lProgramada);
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCvePeriodPla", "" + iCvePeriodPla);
		hmfieldsTable.put("dtSolicitud", "" + dtSolicitud);
		hmfieldsTable.put("dtVencimiento", "" + dtVencimiento);
		hmfieldsTable.put("dtRecepcion", "" + dtRecepcion);
		hmfieldsTable.put("iCveUsuSolicita", "" + iCveUsuSolicita);
		hmfieldsTable.put("iCveUMSolicita", "" + iCveUMSolicita);
		hmfieldsTable.put("iCveUsuRecibe", "" + iCveUsuRecibe);
		hmfieldsTable.put("iCveUMRecibe", "" + iCveUMRecibe);
		hmfieldsTable.put("iCveMedRecep", "" + iCveMedRecep);
		hmfieldsTable.put("cDscEmpresa", "" + cDscEmpresa);
		hmfieldsTable.put("cIDDGPMPT", "" + cIDDGPMPT);
		hmfieldsTable.put("iIDMdoTrans", "" + iIDMdoTrans);
		hmfieldsTable.put("cRFC", "" + cRFC);
		hmfieldsTable.put("cDscPeriodPla", "" + cDscPeriodPla);
		hmfieldsTable.put("cTpoPersona", "" + cTpoPersona);

		return hmfieldsTable;
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public int getiCvePlantilla() {
		return iCvePlantilla;
	}

	public void setiCvePlantilla(int iCvePlantilla) {
		this.iCvePlantilla = iCvePlantilla;
	}

	public int getiCveTpoEntrega() {
		return iCveTpoEntrega;
	}

	public void setiCveTpoEntrega(int iCveTpoEntrega) {
		this.iCveTpoEntrega = iCveTpoEntrega;
	}

	public int getlProgramada() {
		return lProgramada;
	}

	public void setlProgramada(int lProgramada) {
		this.lProgramada = lProgramada;
	}

	public int getiAnio() {
		return iAnio;
	}

	public void setiAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getiCvePeriodPla() {
		return iCvePeriodPla;
	}

	public void setiCvePeriodPla(int iCvePeriodPla) {
		this.iCvePeriodPla = iCvePeriodPla;
	}

	public java.sql.Date getdtSolicitud() {
		return dtSolicitud;
	}

	public void setdtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public java.sql.Date getdtVencimiento() {
		return dtVencimiento;
	}

	public void setdtVencimiento(java.sql.Date dtVencimiento) {
		this.dtVencimiento = dtVencimiento;
	}

	public java.sql.Date getdtRecepcion() {
		return dtRecepcion;
	}

	public void setdtRecepcion(java.sql.Date dtRecepcion) {
		this.dtRecepcion = dtRecepcion;
	}

	public int getiCveUsuSolicita() {
		return iCveUsuSolicita;
	}

	public void setiCveUsuSolicita(int iCveUsuSolicita) {
		this.iCveUsuSolicita = iCveUsuSolicita;
	}

	public int getiCveUMSolicita() {
		return iCveUMSolicita;
	}

	public void setiCveUMSolicita(int iCveUMSolicita) {
		this.iCveUMSolicita = iCveUMSolicita;
	}

	public int getiCveUsuRecibe() {
		return iCveUsuRecibe;
	}

	public void setiCveUsuRecibe(int iCveUsuRecibe) {
		this.iCveUsuRecibe = iCveUsuRecibe;
	}

	public int getiCveUMRecibe() {
		return iCveUMRecibe;
	}

	public void setiCveUMRecibe(int iCveUMRecibe) {
		this.iCveUMRecibe = iCveUMRecibe;
	}

	public int getiCveMedRecep() {
		return iCveMedRecep;
	}

	public void setiCveMedRecep(int iCveMedRecep) {
		this.iCveMedRecep = iCveMedRecep;
	}

	public String getCDscEmpresa() {
		return cDscEmpresa;
	}

	public void setCDscEmpresa(String cDscEmpresa) {
		this.cDscEmpresa = cDscEmpresa;
	}

	public String getcIDDGPMPT() {
		return cIDDGPMPT;
	}

	public void setcIDDGPMPT(String cIDDGPMPT) {
		this.cIDDGPMPT = cIDDGPMPT;
	}

	public int getIIDMdoTrans() {
		return iIDMdoTrans;
	}

	public void setIIDMdoTrans(int iIDMdoTrans) {
		this.iIDMdoTrans = iIDMdoTrans;
	}

	public String getCRFC() {
		return cRFC;
	}

	public void setCRFC(String cRFC) {
		this.cRFC = cRFC;
	}

	public String getCDscPeriodPla() {
		return cDscPeriodPla;
	}

	public void setCDscPeriodPla(String cDscPeriodPla) {
		this.cDscPeriodPla = cDscPeriodPla;
	}

	public String getCTpoPersona() {
		return cTpoPersona;
	}

	public void setCTpoPersona(String cTpoPersona) {
		this.cTpoPersona = cTpoPersona;
	}
}
