package gob.sct.medprev.vo;

import java.util.HashMap;

import com.micper.sql.BeanPK;
import com.micper.sql.HashBeanInterface;

/**
 * <p>
 * Title: Value Object EXPMultas
 * </p>
 * <p>
 * Description: VO Tabla EXPMultas
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Company: Tecnologia Aplicada
 * </p>
 * 
 * @author AG SA L
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TVEXPMulta implements HashBeanInterface {
	private BeanPK pk;

	private int iCveUniMed;

	private int iCveModulo;

	private java.sql.Date dtFecha;

	private int iCveMulta;

	private String cHora;

	private String cApPaterno;

	private String cApMaterno;

	private String cNombre;

	private String cGenero;

	private java.sql.Date dtNacimiento;

	private String cRFC;

	private String cCURP;


	private int iCveUsuCita;

	private String cDscModulo;

	private String cDscUniMed;

	private int iCvePersonal;
	
	// Formato Fechas

	private String cDscDtFecha;

	private String cDscDtFechaNac;
	private String cDscDtFechaNacNom024;

	// Horas (hh:mm)
	private int iHora;

	private int iMinutos;

	// Acutalizar
	private int iCveUniMedA;

	private int iCveModuloA;

	private java.sql.Date dtFechaA;

	// Licencias
	private int iCantidad;
	private int iTarifa;
	
	private java.sql.Timestamp tRealizoPago;
	
	private java.sql.Timestamp tRegistroPago;
	
	private String cSexo_DGIS;
	
	public BeanPK getPK() {

		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		pk.add("" + iCveModulo);
		pk.add(cDscDtFecha);
		pk.add("" + iCveMulta);
		return pk;
	}

	public TVEXPMulta() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("dtFecha", "" + dtFecha);
		hmfieldsTable.put("iCveCita", "" + iCveMulta);
		hmfieldsTable.put("cHora", cHora);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cGenero", cGenero);
		hmfieldsTable.put("dtNacimiento", "" + dtNacimiento);
		hmfieldsTable.put("cRFC", cRFC);
		hmfieldsTable.put("cCURP", cCURP);
		// Licencias
		hmfieldsTable.put("cLicencia", iCantidad);
		hmfieldsTable.put("cLicenciaInt", iTarifa);

		hmfieldsTable.put("cDscModulo", cDscModulo);

		hmfieldsTable.put("cDscDtFecha", cDscDtFecha);
		hmfieldsTable.put("cDscDtFechaNac", cDscDtFechaNac);
		hmfieldsTable.put("cDscDtFechaNacNom024", cDscDtFechaNacNom024);

		hmfieldsTable.put("iHora", "" + iHora);
		hmfieldsTable.put("iMinutos", "" + iMinutos);

		hmfieldsTable.put("iCveUniMedA", "" + iCveUniMedA);
		hmfieldsTable.put("iCveModuloA", "" + iCveModuloA);
		hmfieldsTable.put("dtFechaA", "" + dtFechaA);
		hmfieldsTable.put("tRealizoPago", "" + tRealizoPago);
		hmfieldsTable.put("tRegistroPago", "" + tRegistroPago);
		
		return hmfieldsTable;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}


	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public java.sql.Date getDtFecha() {
		return dtFecha;
	}

	public void setDtFecha(java.sql.Date dtFecha) {
		this.dtFecha = dtFecha;
	}


	public String getCHora() {
		return cHora;
	}

	public void setCHora(String cHora) {
		this.cHora = cHora;
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

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCGenero() {
		return cGenero;
	}

	public void setCGenero(String cGenero) {
		this.cGenero = cGenero;
	}

	public java.sql.Date getDtNacimiento() {
		return dtNacimiento;
	}

	public void setDtNacimiento(java.sql.Date dtNacimiento) {
		this.dtNacimiento = dtNacimiento;
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

	public int getICantidad() {
		return iCantidad;
	}

	public void setICantidad(int iCantidad) {
		this.iCantidad = iCantidad;
	}

	public int getITarifa() {
		return iTarifa;
	}

	public void setITarifa(int iTarifa) {
		this.iTarifa = iTarifa;
	}

	public int getICveUsuCita() {
		return iCveUsuCita;
	}

	public void setICveUsuCita(int iCveUsuCita) {
		this.iCveUsuCita = iCveUsuCita;
	}


	public String getCDscDtFecha() {
		return cDscDtFecha;
	}

	public void setCDscDtFecha(String cDscDtFecha) {
		this.cDscDtFecha = cDscDtFecha;
	}

	public String getCDscDtFechaNac() {
		return cDscDtFechaNac;
	}
	
	public String getCDscDtFechaNacNom024() {
		return cDscDtFechaNacNom024;
	}


	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public int getIHora() {
		return iHora;
	}

	public int getIMinutos() {
		return iMinutos;
	}

	public void setIMinutos(int iMinutos) {
		this.iMinutos = iMinutos;
	}

	public void setIHora(int iHora) {
		this.iHora = iHora;
	}

	public int getICveUniMedA() {
		return iCveUniMedA;
	}

	public void setICveUniMedA(int iCveUniMedA) {
		this.iCveUniMedA = iCveUniMedA;
	}

	public java.sql.Date getDtFechaA() {
		return dtFechaA;
	}

	public void setDtFechaA(java.sql.Date dtFechaA) {
		this.dtFechaA = dtFechaA;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}

	public int getICveModuloA() {
		return iCveModuloA;
	}

	public void setICveModuloA(int iCveModuloA) {
		this.iCveModuloA = iCveModuloA;
	}


	public String getCSexo_DGIS() {
		return cSexo_DGIS;
	}

	public void setCSexo_DGIS(String cSexo_DGIS) {
		this.cSexo_DGIS = cSexo_DGIS;
	}
	

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}
	

	public int getICveMulta() {
		return iCveMulta;
	}

	public void setICveMulta(int iCveMulta) {
		this.iCveMulta = iCveMulta;
	}
	

	public java.sql.Timestamp getTRealizoPago() {
		return tRealizoPago;
	}

	public void setTRealizoPago(java.sql.Timestamp tRealizoPago) {
		this.tRealizoPago = tRealizoPago;
	}


	public java.sql.Timestamp getTRegistroPago() {
		return tRegistroPago;
	}

	public void setTRegistroPago(java.sql.Timestamp tRegistroPago) {
		this.tRegistroPago = tRegistroPago;
	}
	
}
