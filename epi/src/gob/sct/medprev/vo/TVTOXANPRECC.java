package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;

import java.text.*;

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
 * @author LCI. Oscar Castrej�n Adame
 * @version 1.0
 */
public class TVTOXANPRECC implements HashBeanInterface {
	private BeanPK pk;
	private Integer iCveSustancia;
	private Integer iCveCorte;

	private Integer lActivo;
	private java.sql.Date dtInicio;
	private java.sql.Date dtFin;
	private Integer iCveUsuAutoriza;
	private Double dCorte;
	private Double dMargenError;
	private Double dMargenCC;
	private String cDscSustancia;
	private String cDscUsuAutoriza;
	private java.sql.Timestamp tsCorte;
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveSustancia);
		pk.add("" + iCveCorte);
		return pk;
	}

	public TVTOXANPRECC() {
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
		hmfieldsTable.put("dMargenError", "" + dMargenError);
		hmfieldsTable.put("dMargenCC", "" + dMargenCC);
		hmfieldsTable.put("cDscSustancia", "" + cDscSustancia);
		hmfieldsTable.put("cDscUsuAutoriza", "" + cDscUsuAutoriza);
		if (tsCorte != null && tsCorte.toString().compareTo("") != 0
				&& tsCorte.toString().compareTo("null") != 0)
			hmfieldsTable.put("tsCorte", "" + sdf.format(tsCorte));
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

	public Double getdMargenError() {
		return dMargenError;
	}

	public void setdMargenError(Double dMargenError) {
		this.dMargenError = dMargenError;
	}

	// SE CALCULA EL MARGEN CERCANO AL CORTE
	public Double getdMargenCC() {
		dMargenCC = dCorte - ((dCorte * dMargenError) / 100);
		return dMargenCC;
	}

	public void setdMargenCC() {
		this.dMargenCC = dMargenError;
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

	public java.sql.Timestamp getTsCorte() {
		return tsCorte;
	}

	public void setTsCorte(java.sql.Timestamp tsCorte) {
		this.tsCorte = tsCorte;
	}
}
