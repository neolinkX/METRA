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
public class TVGRLUniMed implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUniMed;
	private String cDscUniMed;
	private String cDscUMPais;
	private String cDscUMEstado;
	private String cDscUMMunicipio;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;
	private int lCis;
	private int lPago;
	private String cCalle;
	private String cColonia;
	private int iCP;
	private String cCiudad;
	private String cTel;
	private String cCorreoE;
	private int lPrivada;
	private int lVigente;
	private int iCveUsuResp;
	private String iCveUddAdmiva;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		return pk;
	}

	public TVGRLUniMed() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("lCis", "" + lCis);
		hmfieldsTable.put("lPago", "" + lPago);
		hmfieldsTable.put("cDscUMPais", cDscUMPais);
		hmfieldsTable.put("cDscUMEstado", cDscUMEstado);
		hmfieldsTable.put("cDscUMMunicipio", cDscUMMunicipio);
		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("cCorreoE", cCorreoE);
		hmfieldsTable.put("lPrivada", "" + lPrivada);
		hmfieldsTable.put("lVigente", "" + lVigente);
		hmfieldsTable.put("cCiudad", "" + cCiudad);
		hmfieldsTable.put("cTel", "" + cTel);
		hmfieldsTable.put("iCveUsuResp", "" + iCveUsuResp);
		hmfieldsTable.put("iCveUddAdmiva", iCveUddAdmiva);

		return hmfieldsTable;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscUMPais() {
		return cDscUMPais;
	}

	public void setCDscUMPais(String cDscUMPais) {
		this.cDscUMPais = cDscUMPais;
	}

	public String getCDscUMEstado() {
		return cDscUMEstado;
	}

	public void setCDscUMEstado(String cDscUMEstado) {
		this.cDscUMEstado = cDscUMEstado;
	}

	public String getCDscUMMunicipio() {
		return cDscUMMunicipio;
	}

	public void setCDscUMMunicipio(String cDscUMMunicipio) {
		this.cDscUMMunicipio = cDscUMMunicipio;
	}

	public int getICvePais() {
		return iCvePais;
	}

	public void setICvePais(int iCvePais) {
		this.iCvePais = iCvePais;
	}

	public int getICveEstado() {
		return iCveEstado;
	}

	public void setICveEstado(int iCveEstado) {
		this.iCveEstado = iCveEstado;
	}

	public int getICveMunicipio() {
		return iCveMunicipio;
	}

	public void setICveMunicipio(int iCveMunicipio) {
		this.iCveMunicipio = iCveMunicipio;
	}

	public int getLCis() {
		return lCis;
	}

	public void setLCis(int lCis) {
		this.lCis = lCis;
	}

	public int getLPago() {
		return lPago;
	}

	public void setLPago(int lPago) {
		this.lPago = lPago;
	}

	public String getCCalle() {
		return cCalle;
	}

	public void setCCalle(String cCalle) {
		this.cCalle = cCalle;
	}

	public String getCColonia() {
		return cColonia;
	}

	public void setCColonia(String cColonia) {
		this.cColonia = cColonia;
	}

	public int getICP() {
		return iCP;
	}

	public void setICP(int iCP) {
		this.iCP = iCP;
	}

	public String getCCiudad() {
		return cCiudad;
	}

	public void setCCiudad(String cCiudad) {
		this.cCiudad = cCiudad;
	}

	public String getCTel() {
		return cTel;
	}

	public void setCTel(String cTel) {
		this.cTel = cTel;
	}

	public String getCCorreoE() {
		return cCorreoE;
	}

	public void setCCorreoE(String cCorreoE) {
		this.cCorreoE = cCorreoE;
	}

	public int getLPrivada() {
		return lPrivada;
	}

	public void setLPrivada(int lPrivada) {
		this.lPrivada = lPrivada;
	}

	public int getLVigente() {
		return lVigente;
	}

	public void setLVigente(int lVigente) {
		this.lVigente = lVigente;
	}

	public int getICveUsuResp() {
		return iCveUsuResp;
	}

	public void setICveUsuResp(int iCveUsuResp) {
		this.iCveUsuResp = iCveUsuResp;
	}

	public String getICveUddAdmiva() {
		return iCveUddAdmiva;
	}

	public void setICveUddAdmiva(String iCveUddAdmiva) {
		this.iCveUddAdmiva = iCveUddAdmiva;
	}

}
