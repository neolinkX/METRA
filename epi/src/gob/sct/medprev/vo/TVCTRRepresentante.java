package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object CTRRepresentante
 * </p>
 * <p>
 * Description: Value Object Tabla CTRRepresentante
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
public class TVCTRRepresentante implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpresa;
	private int iCveRepresentante;
	private String cRFC;
	private String cCURP;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private String cPuesto;
	private String cCalle;
	private String cColonia;
	private int iCP;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;
	private String cTel;
	private String cFax;
	private String cCorreoElec;
	private int lActivo;
	private String cPais;
	private String cDscEstado;
	private String cMunicipio;
	private String cIDDGPMPT;
	private int iIDMdoTrans;
	private String cDscMdoTrans;
	private String cObservacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		// pk.add("" + iCveEmpresa);
		pk.add("" + iCveRepresentante);
		return pk;
	}

	public TVCTRRepresentante() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("iCveRepresentante", "" + iCveRepresentante);
		hmfieldsTable.put("cRFC", cRFC);
		hmfieldsTable.put("cCURP", cCURP);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cPuesto", cPuesto);
		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("cTel", cTel);
		hmfieldsTable.put("cFax", cFax);
		hmfieldsTable.put("cCorreoElec", cCorreoElec);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cPais", cPais);
		hmfieldsTable.put("cDscEstado", cDscEstado);
		hmfieldsTable.put("cMunicipio", cMunicipio);
		hmfieldsTable.put("cIDDGPMPT", "" + cIDDGPMPT);
		hmfieldsTable.put("iIDMdoTrans", "" + iIDMdoTrans);
		hmfieldsTable.put("cDscMdoTrans", cDscMdoTrans);
		hmfieldsTable.put("cObservacion", cObservacion);
		return hmfieldsTable;
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public int getICveRepresentante() {
		return iCveRepresentante;
	}

	public void setICveRepresentante(int iCveRepresentante) {
		this.iCveRepresentante = iCveRepresentante;
	}

	public String getCRFC() {
		return cRFC;
	}

	public void setCRFC(String cRFC) {
		this.cRFC = cRFC;
	}

	public String getCCURP() {
		return cCURP;
	}

	public void setCCURP(String cCURP) {
		this.cCURP = cCURP;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCApPaterno() {
		return cApPaterno;
	}

	public void setCApPaterno(String cApPaterno) {
		this.cApPaterno = cApPaterno;
	}

	public String getCApMaterno() {
		return cApMaterno;
	}

	public void setCApMaterno(String cApMaterno) {
		this.cApMaterno = cApMaterno;
	}

	public String getCPuesto() {
		return cPuesto;
	}

	public void setCPuesto(String cPuesto) {
		this.cPuesto = cPuesto;
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

	public String getCTel() {
		return cTel;
	}

	public void setCTel(String cTel) {
		this.cTel = cTel;
	}

	public String getCFax() {
		return cFax;
	}

	public void setCFax(String cFax) {
		this.cFax = cFax;
	}

	public String getCCorreoElec() {
		return cCorreoElec;
	}

	public void setCCorreoElec(String cCorreoElec) {
		this.cCorreoElec = cCorreoElec;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getCPais() {
		return cPais;
	}

	public void setCPais(String cPais) {
		this.cPais = cPais;
	}

	public String getCDscEstado() {
		return cDscEstado;
	}

	public void setCDscEstado(String cDscEstado) {
		this.cDscEstado = cDscEstado;
	}

	public String getCMunicipio() {
		return cMunicipio;
	}

	public void setCMunicipio(String cMunicipio) {
		this.cMunicipio = cMunicipio;
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

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}
}
