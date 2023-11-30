package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object CTRPeriodPla
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
public class TVCTRPeriodPla implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCvePeriodPla;
	private int iCveTpoPlantilla;
	private String cDscPeriodPla;
	private String cObservacion;
	private java.sql.Date dtGeneracion;
	private java.sql.Date dtVencimiento;
	private int lActivo;
	private String cDscTpoPlantilla;
	private String cdtGeneracion;
	private String cdtVencimiento;
	private String cDscBreve;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCvePeriodPla);
		return pk;
	}

	public TVCTRPeriodPla() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCvePeriodPla", "" + iCvePeriodPla);
		hmfieldsTable.put("iCveTpoPlantilla", "" + iCveTpoPlantilla);
		hmfieldsTable.put("cDscPeriodPla", "" + cDscPeriodPla);
		hmfieldsTable.put("cObservacion", "" + cObservacion);
		hmfieldsTable.put("dtGeneracion", "" + dtGeneracion);
		hmfieldsTable.put("dtVencimiento", "" + dtVencimiento);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscTpoPlantilla", "" + cDscTpoPlantilla);
		hmfieldsTable.put("cdtGeneracion", "" + cdtGeneracion);
		hmfieldsTable.put("cdtVencimiento", "" + cdtVencimiento);
		hmfieldsTable.put("cDscBreve", "" + cDscBreve);
		return hmfieldsTable;
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

	public int getiCveTpoPlantilla() {
		return iCveTpoPlantilla;
	}

	public void setiCveTpoPlantilla(int iCveTpoPlantilla) {
		this.iCveTpoPlantilla = iCveTpoPlantilla;
	}

	public String getcDscPeriodPla() {
		return cDscPeriodPla;
	}

	public void setcDscPeriodPla(String cDscPeriodPla) {
		this.cDscPeriodPla = cDscPeriodPla;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public void setcDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getcDscBreve() {
		return cDscBreve;
	}

	public java.sql.Date getdtGeneracion() {
		return dtGeneracion;
	}

	public void setdtGeneracion(java.sql.Date dtGeneracion) {
		this.dtGeneracion = dtGeneracion;
	}

	public java.sql.Date getdtVencimiento() {
		return dtVencimiento;
	}

	public void setdtVencimiento(java.sql.Date dtVencimiento) {
		this.dtVencimiento = dtVencimiento;
	}

	public int getlActivo() {
		return lActivo;
	}

	public void setlActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getcDscTpoPlantilla() {
		return cDscTpoPlantilla;
	}

	public void setcDscTpoPlantilla(String cDscTpoPlantilla) {
		this.cDscTpoPlantilla = cDscTpoPlantilla;
	}

	public String getcdtGeneracion() {
		return cdtGeneracion;
	}

	public void setcdtGeneracion(String cdtGeneracion) {
		this.cdtGeneracion = cdtGeneracion;
	}

	public String getcdtVencimiento() {
		return cdtVencimiento;
	}

	public void setcdtVencimiento(String cdtVencimiento) {
		this.cdtVencimiento = cdtVencimiento;
	}

}
