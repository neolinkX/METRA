package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import com.micper.util.TFechas;

/**
 * <p>
 * Title: Value Object CTRPersonal
 * </p>
 * <p>
 * Description: VO para la tabla CTRPersonal
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hern�ndez Garc�a
 * @version 1.0
 */
public class TVCTRPersonal implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpresa;
	private int iCvePlantilla;
	private int iNumPersonal;
	private int iCveExpediente;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private String cRFC;
	private String cCURP;
	private java.sql.Date dtNacimiento;
	private int iCvePaisNac;
	private int iCveEstadoNac;
	private int iCveMdoTrans;
	private int iCvePuesto;
	private String cLicencia;
	private String cCalle;
	private String cNumExt;
	private String cNumInt;
	private String cColonia;
	private int iCP;
	private String cCiudad;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;
	private String cTel;
	private String cDscEmpresa;
	private String cDscTpoEntrega;
	private String cDscPaisNac;
	private String cDscEstadoNac;
	private String cDscMdotrans;
	private String cDscPuesto;
	private String cDscPais;
	private String cDscEstado;
	private String cDscMunicipio;
	private java.sql.Date dtRecepcion;
	private java.sql.Date dtLicVencimiento;
	private int lActivo;
	private int lBaseEventual;

	private String cRExaTox; // Resultado del examen toxicologico
	private String cRExaDAlc; // Resultado del examen de deteccion de alcohol

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpresa);
		pk.add("" + iCvePlantilla);
		pk.add("" + iNumPersonal);
		return pk;
	}

	public TVCTRPersonal() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		TFechas dtFecha = new TFechas();

		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("iCvePlantilla", "" + iCvePlantilla);
		hmfieldsTable.put("iNumPersonal", "" + iNumPersonal);
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cRFC", cRFC);
		hmfieldsTable.put("cCURP", cCURP);

		if (dtNacimiento != null
				&& dtNacimiento.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtNacimiento",
					dtFecha.getFechaDDMMYYYY(dtNacimiento, ""));

		hmfieldsTable.put("iCvePaisNac", "" + iCvePaisNac);
		hmfieldsTable.put("iCveEstadoNac", "" + iCveEstadoNac);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCvePuesto", "" + iCvePuesto);
		hmfieldsTable.put("cLicencia", cLicencia);
		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cNumExt", cNumExt);
		hmfieldsTable.put("cNumInt", cNumInt);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("cCiudad", cCiudad);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("cTel", cTel);

		hmfieldsTable.put("cDscEmpresa", cDscEmpresa);
		hmfieldsTable.put("cDscTpoEntrega", cDscTpoEntrega);
		hmfieldsTable.put("cDscPaisNac", cDscPaisNac);
		hmfieldsTable.put("cDscEstadoNac", cDscEstadoNac);
		hmfieldsTable.put("cDscMdotrans", cDscMdotrans);
		hmfieldsTable.put("cDscPuesto", cDscPuesto);
		hmfieldsTable.put("cDscPais", cDscPais);
		hmfieldsTable.put("cDscEstado", cDscEstado);
		hmfieldsTable.put("cDscMunicipio", cDscMunicipio);
		if (dtRecepcion != null
				&& dtRecepcion.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtRecepcion",
					dtFecha.getFechaDDMMYYYY(dtRecepcion, ""));
		if (dtLicVencimiento != null
				&& dtLicVencimiento.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtLicVencimiento",
					dtFecha.getFechaDDMMYYYY(dtLicVencimiento, ""));
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("lBaseEventual", "" + lBaseEventual);

		// Nuevos campos
		hmfieldsTable.put("cRExaTox", "" + cRExaTox);
		hmfieldsTable.put("cRExaDAlc", "" + cRExaDAlc);
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

	public void setICvePlantilla(int iCvePlantilla) {
		this.iCvePlantilla = iCvePlantilla;
	}

	public int getINumPersonal() {
		return iNumPersonal;
	}

	public void setINumPersonal(int iNumPersonal) {
		this.iNumPersonal = iNumPersonal;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
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

	public java.sql.Date getDtNacimiento() {
		return dtNacimiento;
	}

	public void setDtNacimiento(java.sql.Date dtNacimiento) {
		this.dtNacimiento = dtNacimiento;
	}

	public int getICvePaisNac() {
		return iCvePaisNac;
	}

	public void setICvePaisNac(int iCvePaisNac) {
		this.iCvePaisNac = iCvePaisNac;
	}

	public int getICveEstadoNac() {
		return iCveEstadoNac;
	}

	public void setICveEstadoNac(int iCveEstadoNac) {
		this.iCveEstadoNac = iCveEstadoNac;
	}

	public int getiCveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getICvePuesto() {
		return iCvePuesto;
	}

	public void setICvePuesto(int iCvePuesto) {
		this.iCvePuesto = iCvePuesto;
	}

	public String getCLicencia() {
		return cLicencia;
	}

	public void setCLicencia(String cLicencia) {
		this.cLicencia = cLicencia;
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

	public String getCTel() {
		return cTel;
	}

	public void setCTel(String cTel) {
		this.cTel = cTel;
	}

	public String getCDscEmpresa() {
		return cDscEmpresa;
	}

	public void setCDscEmpresa(String cDscEmpresa) {
		this.cDscEmpresa = cDscEmpresa;
	}

	public String getCDscTpoEntrega() {
		return cDscTpoEntrega;
	}

	public void setCDscTpoEntrega(String cDscTpoEntrega) {
		this.cDscTpoEntrega = cDscTpoEntrega;
	}

	public String getCDscPaisNac() {
		return cDscPaisNac;
	}

	public void setCDscPaisNac(String cDscPaisNac) {
		this.cDscPaisNac = cDscPaisNac;
	}

	public String getCDscEstadoNac() {
		return cDscEstadoNac;
	}

	public void setCDscEstadoNac(String cDscEstadoNac) {
		this.cDscEstadoNac = cDscEstadoNac;
	}

	public String getCDscMdotrans() {
		return cDscMdotrans;
	}

	public void setCDscMdotrans(String cDscMdotrans) {
		this.cDscMdotrans = cDscMdotrans;
	}

	public String getCDscPuesto() {
		return cDscPuesto;
	}

	public void setCDscPuesto(String cDscPuesto) {
		this.cDscPuesto = cDscPuesto;
	}

	public String getCDscPais() {
		return cDscPais;
	}

	public void setCDscPais(String cDscPais) {
		this.cDscPais = cDscPais;
	}

	public String getCDscEstado() {
		return cDscEstado;
	}

	public void setCDscEstado(String cDscEstado) {
		this.cDscEstado = cDscEstado;
	}

	public String getCDscMunicipio() {
		return cDscMunicipio;
	}

	public void setCDscMunicipio(String cDscMunicipio) {
		this.cDscMunicipio = cDscMunicipio;
	}

	public java.sql.Date getDtRecepcion() {
		return dtRecepcion;
	}

	public void setDtRecepcion(java.sql.Date dtRecepcion) {
		this.dtRecepcion = dtRecepcion;
	}

	public java.sql.Date getDtLicVencimiento() {
		return dtLicVencimiento;
	}

	public void setDtLicVencimiento(java.sql.Date dtLicVencimiento) {
		this.dtLicVencimiento = dtLicVencimiento;
	}

	public int getlActivo() {
		return lActivo;
	}

	public void setlActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public int getlBaseEventual() {
		return lBaseEventual;
	}

	public void setlBaseEventual(int lBaseEventual) {
		this.lBaseEventual = lBaseEventual;
	}

	// ///////// Nuevos Capmpos /////////////////////////////////////////////
	// Agregados el 2 de abril 2012 por AG SA.
	public String getCRExaTox() {
		return cRExaTox;
	}

	public void setCRExaTox(String cRExaTox) {
		this.cRExaTox = cRExaTox;
	}

	public String getCRExaDAlc() {
		return cRExaDAlc;
	}

	public void setCRExaDAlc(String cRExaDAlc) {
		this.cRExaDAlc = cRExaDAlc;
	}

}
