package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLUniMed
 * </p>
 * <p>
 * Description: Value Object para GRLUniMed
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVGRLAyudaDGPMPT implements HashBeanInterface {
	private BeanPK pk;
	private int iCveAyuda;
	private String cDsAyuda;
	private String cDscDescripcion;
	private int lManual;
	private int lSoftware;
	private int lGuia;
	private String cUrl;
	private int lVigente;
	private int lAdmin;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveAyuda);
		return pk;
	}

	public TVGRLAyudaDGPMPT() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveAyuda", "" + iCveAyuda);
		hmfieldsTable.put("cDsAyuda", cDsAyuda);
		hmfieldsTable.put("cDscDescripcion", "" + cDscDescripcion);
		hmfieldsTable.put("lManual", "" + lManual);
		hmfieldsTable.put("lSoftware", "" + lSoftware);
		hmfieldsTable.put("lGuia", "" + lGuia);
		hmfieldsTable.put("cUrl", "" + cUrl);
		hmfieldsTable.put("lVigente", lVigente);
		hmfieldsTable.put("lAdmin", lAdmin);

		return hmfieldsTable;
	}

	public int getICveAyuda() {
		return iCveAyuda;
	}

	public void setICveAyuda(int iCveAyuda) {
		this.iCveAyuda = iCveAyuda;
	}

	public String getCDsAyuda() {
		return cDsAyuda;
	}

	public void setCDsAyuda(String cDsAyuda) {
		this.cDsAyuda = cDsAyuda;
	}

	public String getCDscDescripcion() {
		return cDscDescripcion;
	}

	public void setCDscDescripcion(String cDscDescripcion) {
		this.cDscDescripcion = cDscDescripcion;
	}

	public int getLManual() {
		return lManual;
	}

	public void setLManual(int lManual) {
		this.lManual = lManual;
	}

	public int getLSoftware() {
		return lSoftware;
	}

	public void setLSoftware(int lSoftware) {
		this.lSoftware = lSoftware;
	}

	public int getLGuia() {
		return lGuia;
	}

	public void setLGuia(int lGuia) {
		this.lGuia = lGuia;
	}

	public String getCUrl() {
		return cUrl;
	}

	public void setCUrl(String cUrl) {
		this.cUrl = cUrl;
	}

	public int getLVigente() {
		return lVigente;
	}

	public void setLVigente(int lVigente) {
		this.lVigente = lVigente;
	}

	public int getLAdmin() {
		return lAdmin;
	}

	public void setLAdmin(int lAdmin) {
		this.lAdmin = lAdmin;
	}

}
