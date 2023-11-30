package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object PERDireccion
 * </p>
 * <p>
 * Description: VO Tabla PERDireccion
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
public class TVPERDireccion implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePersonal;
	private int iCveDireccion;
	private String cCalle;
	private String cNumExt;
	private String cNumInt;
	private String cColonia;
	private int iCP;
	private String cCiudad;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;
	private int iCveLocalidad;
	private String cTel;

	// Descripciones
	private String cDscPais;
	private String cDscEstado;
	private String cDscMunicipio;

	// Usuario que hace la alta
	private int iCveUsuario;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePersonal);
		pk.add("" + iCveDireccion);
		return pk;
	}

	public TVPERDireccion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iCveDireccion", "" + iCveDireccion);
		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cNumExt", cNumExt);
		hmfieldsTable.put("cNumInt", cNumInt);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("cCiudad", cCiudad);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("iCveLocalidad", "" + iCveLocalidad);
		hmfieldsTable.put("cTel", cTel);

		hmfieldsTable.put("cDscPais", cDscPais);
		hmfieldsTable.put("cDscEstado", cDscEstado);
		hmfieldsTable.put("cDscMunicipio", cDscMunicipio);
		hmfieldsTable.put("iCveUsuario", "" + iCveUsuario);
		return hmfieldsTable;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getICveDireccion() {
		return iCveDireccion;
	}

	public void setICveDireccion(int iCveDireccion) {
		this.iCveDireccion = iCveDireccion;
	}

	public String getCCalle() {
		return cCalle;
	}

	public void setCCalle(String cCalle) {
		this.cCalle = cCalle;
	}

	public String getCNumExt() {
		return cNumExt;
	}

	public void setCNumExt(String cNumExt) {
		this.cNumExt = cNumExt;
	}

	public String getCNumInt() {
		return cNumInt;
	}

	public void setCNumInt(String cNumInt) {
		this.cNumInt = cNumInt;
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
	
	public int getICveLocalidad() {
		return iCveLocalidad;
	}

	public void setICveLocalidad(int iCveLocalidad) {
		this.iCveLocalidad = iCveLocalidad;
	}	

	public String getCTel() {
		return cTel;
	}

	public void setCTel(String cTel) {
		this.cTel = cTel;
	}

	public String getCDscEstado() {
		return cDscEstado;
	}

	public String getCDscMunicipio() {
		return cDscMunicipio;
	}

	public String getCDscPais() {
		return cDscPais;
	}

	public void setCDscPais(String cDscPais) {
		this.cDscPais = cDscPais;
	}

	public void setCDscMunicipio(String cDscMunicipio) {
		this.cDscMunicipio = cDscMunicipio;
	}

	public void setCDscEstado(String cDscEstado) {
		this.cDscEstado = cDscEstado;
	}

	public int getICveUsuario() {
		return iCveUsuario;
	}

	public void setICveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
	}

}
