package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object Envio
 * </p>
 * <p>
 * Description: VO Tabla TOXEnvio
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. González Paz
 * @version 1.0
 */
public class TVEnvio implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveUniMed;
	private int iCveEnvio;
	private int iCveLaboratorio;
	private int iCveUsuEnvia;
	private java.sql.Date dtEnvio;
	private int iCveTipoEnvio;
	private java.sql.Date dtRecepcion;
	private int iCveUsuRec;
	private String cObsEnvio;
	private String cObsRecep;
	private String cDscTipoEnvio;
	private String cDscLaboratorio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveUniMed);
		pk.add("" + iCveEnvio);
		return pk;
	}

	public TVEnvio() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveEnvio", "" + iCveEnvio);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveUsuEnvia", "" + iCveUsuEnvia);
		hmfieldsTable.put("dtEnvio", "" + dtEnvio);
		hmfieldsTable.put("iCveTipoEnvio", "" + iCveTipoEnvio);
		hmfieldsTable.put("dtRecepcion", "" + dtRecepcion);
		hmfieldsTable.put("iCveUsuRec", "" + iCveUsuRec);
		hmfieldsTable.put("cObsEnvio", cObsEnvio);
		hmfieldsTable.put("cObsRecep", cObsRecep);
		hmfieldsTable.put("cDscTipoEnvio", cDscTipoEnvio);
		hmfieldsTable.put("cDscLaboratorio", cDscLaboratorio);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveEnvio() {
		return iCveEnvio;
	}

	public void setICveEnvio(int iCveEnvio) {
		this.iCveEnvio = iCveEnvio;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getICveUsuEnvia() {
		return iCveUsuEnvia;
	}

	public void setICveUsuEnvia(int iCveUsuEnvia) {
		this.iCveUsuEnvia = iCveUsuEnvia;
	}

	public java.sql.Date getDtEnvio() {
		return dtEnvio;
	}

	public void setDtEnvio(java.sql.Date dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public int getICveTipoEnvio() {
		return iCveTipoEnvio;
	}

	public void setICveTipoEnvio(int iCveTipoEnvio) {
		this.iCveTipoEnvio = iCveTipoEnvio;
	}

	public java.sql.Date getDtRecepcion() {
		return dtRecepcion;
	}

	public void setDtRecepcion(java.sql.Date dtRecepcion) {
		this.dtRecepcion = dtRecepcion;
	}

	public int getICveUsuRec() {
		return iCveUsuRec;
	}

	public void setICveUsuRec(int iCveUsuRec) {
		this.iCveUsuRec = iCveUsuRec;
	}

	public String getCObsEnvio() {
		return cObsEnvio;
	}

	public void setCObsEnvio(String cObsEnvio) {
		this.cObsEnvio = cObsEnvio;
	}

	public String getCObsRecep() {
		return cObsRecep;
	}

	public void setCObsRecep(String cObsRecep) {
		this.cObsRecep = cObsRecep;
	}

	public String getCDscTipoEnvio() {
		return cDscTipoEnvio;
	}

	public void setCDscTipoEnvio(String cDscTipoEnvio) {
		this.cDscTipoEnvio = cDscTipoEnvio;
	}

	public String getCDscLaboratorio() {
		return cDscLaboratorio;
	}

	public void setCDscLaboratorio(String cDscLaboratorio) {
		this.cDscLaboratorio = cDscLaboratorio;
	}
}
