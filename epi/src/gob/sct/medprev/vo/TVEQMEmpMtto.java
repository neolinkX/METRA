package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EQMEmpMtto
 * </p>
 * <p>
 * Description: Calibración de Equipo - Empresas de Mantenimiento
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */
public class TVEQMEmpMtto implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpMtto;
	private String cRFC;
	private String cCURP;
	private String cTpoPersona;
	private String cApPaterno;
	private String cApMaterno;
	private String cNombreRS;
	private String cDscEmpMtto;
	private String cContacto;
	private String cCalle;
	private String cColonia;
	private String cCiudad;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;
	private int iCP;
	private String cTel;
	private String cFax;
	private String cCorreoElec;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpMtto);
		return pk;
	}

	public TVEQMEmpMtto() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpMtto", "" + iCveEmpMtto);
		hmfieldsTable.put("cRFC", cRFC);
		hmfieldsTable.put("cCURP", cCURP);
		hmfieldsTable.put("cTpoPersona", cTpoPersona);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cNombreRS", cNombreRS);
		hmfieldsTable.put("cDscEmpMtto", cDscEmpMtto);
		hmfieldsTable.put("cContacto", cContacto);
		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("cCiudad", cCiudad);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("cTel", cTel);
		hmfieldsTable.put("cFax", cFax);
		hmfieldsTable.put("cCorreoElec", cCorreoElec);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveEmpMtto() {
		return iCveEmpMtto;
	}

	public void setICveEmpMtto(int iCveEmpMtto) {
		this.iCveEmpMtto = iCveEmpMtto;
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

	public String getCTpoPersona() {
		return cTpoPersona;
	}

	public void setCTpoPersona(String cTpoPersona) {
		this.cTpoPersona = cTpoPersona;
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

	public String getCNombreRS() {
		return cNombreRS;
	}

	public void setCNombreRS(String cNombreRS) {
		this.cNombreRS = cNombreRS;
	}

	public String getCDscEmpMtto() {
		return cDscEmpMtto;
	}

	public void setCDscEmpMtto(String cDscEmpMtto) {
		this.cDscEmpMtto = cDscEmpMtto;
	}

	public String getCContacto() {
		return cContacto;
	}

	public void setCContacto(String cContacto) {
		this.cContacto = cContacto;
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

	public String getCCiudad() {
		return cCiudad;
	}

	public void setCCiudad(String cCiudad) {
		this.cCiudad = cCiudad;
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

	public int getICP() {
		return iCP;
	}

	public void setICP(int iCP) {
		this.iCP = iCP;
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
}
