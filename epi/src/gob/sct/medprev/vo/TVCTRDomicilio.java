package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object CTRDomicilio
 * </p>
 * <p>
 * Description: Control al Transporte - Domicilios de las Empresas
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
public class TVCTRDomicilio implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpresa;
	private int iCveDomicilio;
	private String cCalle;
	private String cColonia;
	private String cCiudad;
	private int iCP;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;
	private String cTel;
	private String cFax;
	private String cCorreoElec;
	private int lActivo;
	private String cNombrePais;
	private String cNombreEntidad;
	private String cNombreMunicipio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpresa);
		pk.add("" + iCveDomicilio);
		return pk;
	}

	public TVCTRDomicilio() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("iCveDomicilio", "" + iCveDomicilio);
		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("cCiudad", cCiudad);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("cTel", cTel);
		hmfieldsTable.put("cFax", cFax);
		hmfieldsTable.put("cCorreoElec", cCorreoElec);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cNombrePais", "" + cNombrePais);
		hmfieldsTable.put("cNombreEntidad", "" + cNombreEntidad);
		hmfieldsTable.put("cNombreMunicipio", "" + cNombreMunicipio);
		return hmfieldsTable;
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public int getICveDomicilio() {
		return iCveDomicilio;
	}

	public void setICveDomicilio(int iCveDomicilio) {
		this.iCveDomicilio = iCveDomicilio;
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

	public String getcNombrePais() {
		return cNombrePais;
	}

	public void setcNombrePais(String cNombrePais) {
		this.cNombrePais = cNombrePais;
	}

	public String getcNombreEntidad() {
		return cNombreEntidad;
	}

	public void setcNombreEntidad(String cNombreEntidad) {
		this.cNombreEntidad = cNombreEntidad;
	}

	public String getcNombreMunicipio() {
		return cNombreMunicipio;
	}

	public void setcNombreMunicipio(String cNombreMunicipio) {
		this.cNombreMunicipio = cNombreMunicipio;
	}

}
