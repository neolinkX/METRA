package gob.sct.medprev.vo;

import java.util.*;

import com.micper.seguridad.vo.TVUsuario;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EPICisCita
 * </p>
 * <p>
 * Description: VO Tabla EPICisCita
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonzalez Paz
 * @version 1.0
 */
public class TVEPICisCita implements HashBeanInterface {
	private BeanPK pk;

	private int iCveUniMed;

	private int iCveModulo;

	private java.sql.Date dtFecha;

	private int iCveCita;

	private String cHora;

	private String cApPaterno;

	private String cApMaterno;

	private String cNombre;

	private String cGenero;

	private java.sql.Date dtNacimiento;

	private String cRFC;

	private String cCURP;

	private int iCvePaisNac;

	private int iCveEstadoNac;

	private String cExpediente;

	private int iCvePersonal;

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

	private String cTelefono;

	private int lCambioDir;

	private int iCveMdoTrans;

	private int iCvePuesto;

	private int iCveCategoria;

	private int iCveMotivo;

	private String cObservacion;

	private int iCveSituacion;

	private int iCveUsuCita;

	private int lRealizoExam;

	private int iCveUsuMCIS;

	private int lProdNoConf;

	// Descripciones
	private String cDscUniMed;

	private String cDscPaisNac;

	private String cDscEstadoNac;

	private String cDscPais;

	private String cDscEstado;

	private String cDscMunicipio;

	private String cDscMdoTransporte;

	private String cDscCategoria;

	private String cDscPuesto;

	private String cDscMotivo;

	private String cDscSituacion;

	private String cDscModulo;

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

	// Usuario para dar altas
	private int iCveJUniMed;

	// Licencias
	private String cLicencia;
	private String cLicenciaInt;

	// DATOS ADICIONALES
	private String cTelefono2;
	private int iCveVivienda;
	private int iCveDiscapacidad;
	private int iCveGpoEtnico;
	private int iCveReligion;
	private int iCveNivelSEC;
	private int iCveParPOL;
	private int iCveECivil;
	private int iCveTpoSangre;

	private String cConcepto;
	private String cDcsDiscapacidad;
	private String cGpoEtnico;
	private String cCodDsc;
	private String cNivelSec;
	private String cDscParPol;
	private String cECivil;
	private String cTpoSangre;

	///Nuevo valor para sexo
	private String cSexo_DGIS;
	
	public BeanPK getPK() {

		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		pk.add("" + iCveModulo);
		pk.add(cDscDtFecha);
		pk.add("" + iCveCita);
		return pk;
	}

	public TVEPICisCita() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("dtFecha", "" + dtFecha);
		hmfieldsTable.put("iCveCita", "" + iCveCita);
		hmfieldsTable.put("cHora", cHora);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cGenero", cGenero);
		hmfieldsTable.put("dtNacimiento", "" + dtNacimiento);
		hmfieldsTable.put("cRFC", cRFC);
		hmfieldsTable.put("cCURP", cCURP);
		// Licencias
		hmfieldsTable.put("cLicencia", cLicencia);
		hmfieldsTable.put("cLicenciaInt", cLicenciaInt);

		hmfieldsTable.put("iCvePaisNac", "" + iCvePaisNac);
		hmfieldsTable.put("iCveEstadoNac", "" + iCveEstadoNac);
		hmfieldsTable.put("cExpediente", cExpediente);
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
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
		hmfieldsTable.put("cTelefono", cTelefono);
		hmfieldsTable.put("lCambioDir", "" + lCambioDir);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCvePuesto", "" + iCvePuesto);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("iCveSituacion", "" + iCveSituacion);
		hmfieldsTable.put("iCveUsuCita", "" + iCveUsuCita);
		hmfieldsTable.put("lRealizoExam", "" + lRealizoExam);
		hmfieldsTable.put("iCveUsuMCIS", "" + iCveUsuMCIS);
		hmfieldsTable.put("lProdNoConf", "" + lProdNoConf);

		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("cDscPaisNac", cDscPaisNac);
		hmfieldsTable.put("cDscEstadoNac", cDscEstadoNac);
		hmfieldsTable.put("cDscPais", cDscPais);
		hmfieldsTable.put("cDscEstado", cDscEstado);
		hmfieldsTable.put("cDscMunicipio", cDscMunicipio);
		hmfieldsTable.put("cDscMdoTransporte", cDscMdoTransporte);
		hmfieldsTable.put("cDscPuesto", cDscPuesto);
		hmfieldsTable.put("cDscCategoria", cDscCategoria);
		hmfieldsTable.put("cDscMotivo", cDscMotivo);
		hmfieldsTable.put("cDscSituacion", cDscSituacion);
		hmfieldsTable.put("cDscModulo", cDscModulo);

		hmfieldsTable.put("cDscDtFecha", cDscDtFecha);
		hmfieldsTable.put("cDscDtFechaNac", cDscDtFechaNac);
		hmfieldsTable.put("cDscDtFechaNacNom024", cDscDtFechaNacNom024);

		hmfieldsTable.put("iHora", "" + iHora);
		hmfieldsTable.put("iMinutos", "" + iMinutos);

		hmfieldsTable.put("iCveUniMedA", "" + iCveUniMedA);
		hmfieldsTable.put("iCveModuloA", "" + iCveModuloA);
		hmfieldsTable.put("dtFechaA", "" + dtFechaA);

		hmfieldsTable.put("iCveJUniMed", "" + iCveJUniMed);

		// DATOS ADICIONALES
		hmfieldsTable.put("cTelefono2", cTelefono2);
		hmfieldsTable.put("iCveVivienda", "" + iCveVivienda);
		hmfieldsTable.put("iCveDiscapacidad", "" + iCveDiscapacidad);
		hmfieldsTable.put("iCveGpoEtnico", "" + iCveGpoEtnico);
		hmfieldsTable.put("iCveReligion", "" + iCveReligion);
		hmfieldsTable.put("iCveNivelSEC", "" + iCveNivelSEC);
		hmfieldsTable.put("iCveParPOL", "" + iCveParPOL);
		hmfieldsTable.put("iCveECivil", "" + iCveECivil);
		hmfieldsTable.put("cConcepto", "" + cConcepto);
		hmfieldsTable.put("cDcsDiscapacidad", "" + cDcsDiscapacidad);
		hmfieldsTable.put("cGpoEtnico", "" + cGpoEtnico);
		hmfieldsTable.put("cCodDsc", "" + cCodDsc);
		hmfieldsTable.put("cNivelSec", "" + cNivelSec);
		hmfieldsTable.put("cDscParPol", "" + cDscParPol);
		hmfieldsTable.put("cECivil", "" + cECivil);
		hmfieldsTable.put("iCveTpoSangre", "" + iCveTpoSangre);
		hmfieldsTable.put("cTpoSangre", "" + cTpoSangre);
		hmfieldsTable.put("cSexo_DGIS", cSexo_DGIS);
		
		return hmfieldsTable;
	}

	public int getICveUniMed() {
		return iCveUniMed;
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

	public int getICveCita() {
		return iCveCita;
	}

	public void setICveCita(int iCveCita) {
		this.iCveCita = iCveCita;
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

	public String getCLicencia() {
		return cLicencia;
	}

	public void setCLicencia(String cLicencia) {
		this.cLicencia = cLicencia;
	}

	public String getCLicenciaInt() {
		return cLicenciaInt;
	}

	public void setCLiceniaInt(String cLicenciaInt) {
		this.cLicenciaInt = cLicenciaInt;
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

	public String getCExpediente() {
		return cExpediente;
	}

	public void setCExpediente(String cExpediente) {
		this.cExpediente = cExpediente;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
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
	
	public String getCTelefono() {
		return cTelefono;
	}

	public void setCTelefono(String cTelefono) {
		this.cTelefono = cTelefono;
	}

	public int getLCambioDir() {
		return lCambioDir;
	}

	public void setLCambioDir(int lCambioDir) {
		this.lCambioDir = lCambioDir;
	}

	public int getICveMdoTrans() {
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

	public int getICveCategoria() {
		return iCveCategoria;
	}

	public void setICveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getICveSituacion() {
		return iCveSituacion;
	}

	public void setICveSituacion(int iCveSituacion) {
		this.iCveSituacion = iCveSituacion;
	}

	public int getICveUsuCita() {
		return iCveUsuCita;
	}

	public void setICveUsuCita(int iCveUsuCita) {
		this.iCveUsuCita = iCveUsuCita;
	}

	public int getLRealizoExam() {
		return lRealizoExam;
	}

	public void setLRealizoExam(int lRealizoExam) {
		this.lRealizoExam = lRealizoExam;
	}

	public int getICveUsuMCIS() {
		return iCveUsuMCIS;
	}

	public void setICveUsuMCIS(int iCveUsuMCIS) {
		this.iCveUsuMCIS = iCveUsuMCIS;
	}

	public int getLProdNoConf() {
		return lProdNoConf;
	}

	public void setLProdNoConf(int lProdNoConf) {
		this.lProdNoConf = lProdNoConf;
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

	public String getCDscEstado() {
		return cDscEstado;
	}

	public String getCDscEstadoNac() {
		return cDscEstadoNac;
	}

	public String getCDscMdoTransporte() {
		return cDscMdoTransporte;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public String getCDscMunicipio() {
		return cDscMunicipio;
	}

	public String getCDscPais() {
		return cDscPais;
	}

	public String getCDscPaisNac() {
		return cDscPaisNac;
	}

	public String getCDscPuesto() {
		return cDscPuesto;
	}

	public String getCDscSituacion() {
		return cDscSituacion;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscDtFechaNac(String cDscDtFechaNac) {
		this.cDscDtFechaNac = cDscDtFechaNac;
	}
	
	public void setCDscDtFechaNacNom024(String cDscDtFechaNacNom024) {
		this.cDscDtFechaNacNom024 = cDscDtFechaNacNom024;
	}

	public void setCDscEstado(String cDscEstado) {
		this.cDscEstado = cDscEstado;
	}

	public void setCDscEstadoNac(String cDscEstadoNac) {
		this.cDscEstadoNac = cDscEstadoNac;
	}

	public void setCDscMdoTransporte(String cDscMdoTransporte) {
		this.cDscMdoTransporte = cDscMdoTransporte;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public void setCDscMunicipio(String cDscMunicipio) {
		this.cDscMunicipio = cDscMunicipio;
	}

	public void setCDscPais(String cDscPais) {
		this.cDscPais = cDscPais;
	}

	public void setCDscPaisNac(String cDscPaisNac) {
		this.cDscPaisNac = cDscPaisNac;
	}

	public void setCDscPuesto(String cDscPuesto) {
		this.cDscPuesto = cDscPuesto;
	}

	public void setCDscSituacion(String cDscSituacion) {
		this.cDscSituacion = cDscSituacion;
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

	public String getCDscCategoria() {
		return cDscCategoria;
	}

	public void setCDscCategoria(String cDscCategoria) {
		this.cDscCategoria = cDscCategoria;
	}

	public int getiCveJUniMed() {
		return iCveJUniMed;
	}

	public void setiCveJUniMed(int iCveJUniMed) {
		this.iCveJUniMed = iCveJUniMed;
	}

	// DATOS ADICIONALES
	public String getCTelefono2() {
		return cTelefono2;
	}

	public void setCTelefono2(String cTelefono2) {
		this.cTelefono2 = cTelefono2;
	}

	public int getICveVivienda() {
		return iCveVivienda;
	}

	public void setICveVivienda(int iCveVivienda) {
		this.iCveVivienda = iCveVivienda;
	}

	public int getICveDiscapacidad() {
		return iCveDiscapacidad;
	}

	public void setICveDiscapacidad(int iCveDiscapacidad) {
		this.iCveDiscapacidad = iCveDiscapacidad;
	}

	public int getICveGpoEtnico() {
		return iCveGpoEtnico;
	}

	public void setICveGpoEtnico(int iCveGpoEtnico) {
		this.iCveGpoEtnico = iCveGpoEtnico;
	}

	public int getICveReligion() {
		return iCveReligion;
	}

	public void setICveReligion(int iCveReligion) {
		this.iCveReligion = iCveReligion;
	}

	public int getICveNivelSEC() {
		return iCveNivelSEC;
	}

	public void setICveNivelSEC(int iCveNivelSEC) {
		this.iCveNivelSEC = iCveNivelSEC;
	}

	public int getICveParPOL() {
		return iCveParPOL;
	}

	public void setICveParPOL(int iCveParPOL) {
		this.iCveParPOL = iCveParPOL;
	}

	public int getICveECivil() {
		return iCveECivil;
	}

	public void setICveECivil(int iCveECivil) {
		this.iCveECivil = iCveECivil;
	}

	public int getICveTpoSangre() {
		return iCveTpoSangre;
	}

	public void setICveTpoSangre(int iCveTpoSangre) {
		this.iCveTpoSangre = iCveTpoSangre;
	}

	public String getCConcepto() {
		return cConcepto;
	}

	public void setCConcepto(String cConcepto) {
		this.cConcepto = cConcepto;
	}

	public String getCDcsDiscapacidad() {
		return cDcsDiscapacidad;
	}

	public void setCDcsDiscapacidad(String cDcsDiscapacidad) {
		this.cDcsDiscapacidad = cDcsDiscapacidad;
	}

	public String getCGpoEtnico() {
		return cGpoEtnico;
	}

	public void setCGpoEtnico(String cGpoEtnico) {
		this.cGpoEtnico = cGpoEtnico;
	}

	public String getCCodDsc() {
		return cCodDsc;
	}

	public void setCCodDsc(String cCodDsc) {
		this.cCodDsc = cCodDsc;
	}

	public String getCNivelSec() {
		return cNivelSec;
	}

	public void setCNivelSec(String cNivelSec) {
		this.cNivelSec = cNivelSec;
	}

	public String getCDscParPol() {
		return cDscParPol;
	}

	public void setCDscParPol(String cDscParPol) {
		this.cDscParPol = cDscParPol;
	}

	public String getCECivil() {
		return cECivil;
	}

	public void setCECivil(String cECivil) {
		this.cECivil = cECivil;
	}

	public String getCTpoSangre() {
		return cTpoSangre;
	}

	public void setCTpoSangre(String cTpoSangre) {
		this.cTpoSangre = cTpoSangre;
	}

	public String getCSexo_DGIS() {
		return cSexo_DGIS;
	}

	public void setCSexo_DGIS(String cSexo_DGIS) {
		this.cSexo_DGIS = cSexo_DGIS;
	}
	
	
	
}
