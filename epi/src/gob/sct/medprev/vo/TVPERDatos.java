package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object Datos
 * </p>
 * <p>
 * Description: VO de la Entidad PERDatos (PERPersonal)
 * </p>   
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Su�rez Romero
 * @version 1.0
 */
public class TVPERDatos implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePersonal;
	private int iCveExpediente;
	private String cSexo;
	private String cSexo_DGIS;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private String cRFC;
	private String cHomoclave;
	private String cCURP;
	private java.sql.Date dtNacimiento;
	private java.sql.Date dtSolicitado;
	private java.sql.Date dtIniVig;
	private java.sql.Date dtFinVig;
	private int iCvePaisNac;
	private int iCveEstadoNac;
	private String cExpediente;
	private String cSenasPersonal;
	private String cCorreoElec;
	private int lDonadorOrg;
	private String cPersonaAviso;
	private String cDirecAviso;
	private String cTelAviso;
	private String cCorreoAviso;
	private int iCveDireccion;
	private int iCveFoto;
	private int lNoHuellas;
	private int iCveNumEmpresa;
	private int iCveUsuRegistra;
	// usuario que autorizo Jefe de Unidad Medica
	// AG SA
	private int iCveJUniMed;

	// Domicilio
	private String cCalle;
	private String cNumExt;
	private String cNumInt;
	private String cColonia;
	private int iCP;
	private String cCiudad;
	private String cDscPais;
	private String cDscEstado;
	private String cDscMunicipio;
	private String cDscLocalidad;
	private String cTelefono;

	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;
	private int iCveLocalidad;

	// Descripciones
	private String cDscPaisNac;
	private String cDscEstadoNac;
	private String cDscEmpresa;
	private String cDscEmpresaTmp;// EmpresaTemporal L.A.S. 20 Junio 2011
	private String cDscFecNacimiento;
	private String cDscFecNacimientoNom024;

	private int iNumExamen;

	private String cGpoSang;
	private int lRH;
	private int lUsaLentes;
	private int lHipertension;
	private int lDiabetes;

	private int lAereo;
	private int lContacto;
	private String cGenero;
	private int iCveEmpresa;
	private int lInternacional;

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
	// Faltante para llenado de combos
	private int iCveGrupoD;
	private String iCveSubGrupoD;
	private int iCveGrupoR;
	private int iCveSubGrupoR;
	
	private java.sql.Timestamp tSGenerado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePersonal);
		return pk;
	}

	public TVPERDatos() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("cSexo", cSexo);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cRFC", cRFC);
		hmfieldsTable.put("cHomoclave", cHomoclave);
		hmfieldsTable.put("cCURP", cCURP);
		hmfieldsTable.put("dtNacimiento", "" + dtNacimiento);
		hmfieldsTable.put("dtSolicitado", "" + dtSolicitado);
		hmfieldsTable.put("dtIniVig", "" + dtIniVig);
		hmfieldsTable.put("dtFinVig", "" + dtFinVig);
		hmfieldsTable.put("iCvePaisNac", "" + iCvePaisNac);
		hmfieldsTable.put("iCveEstadoNac", "" + iCveEstadoNac);
		hmfieldsTable.put("cExpediente", cExpediente);
		hmfieldsTable.put("cSenasPersonal", cSenasPersonal);
		hmfieldsTable.put("cCorreoElec", cCorreoElec);
		hmfieldsTable.put("lDonadorOrg", "" + lDonadorOrg);
		hmfieldsTable.put("cPersonaAviso", cPersonaAviso);
		hmfieldsTable.put("cDirecAviso", cDirecAviso);
		hmfieldsTable.put("cTelAviso", cTelAviso);
		hmfieldsTable.put("cCorreoAviso", cCorreoAviso);
		hmfieldsTable.put("iCveDireccion", "" + iCveDireccion);
		hmfieldsTable.put("iCveFoto", "" + iCveFoto);
		hmfieldsTable.put("lNoHuellas", "" + lNoHuellas);
		hmfieldsTable.put("iCveNumEmpresa", "" + iCveNumEmpresa);
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("iCveUsuRegistra", "" + iCveUsuRegistra);

		// Campo agregado para Jefe Unidad Medica
		hmfieldsTable.put("iCveJUniMed", "" + iCveJUniMed);

		hmfieldsTable.put("cNombreCompleto", cNombre + " " + cApPaterno + " "
				+ cApMaterno);

		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cNumExt", cNumExt);
		hmfieldsTable.put("cNumInt", cNumInt);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("cCiudad", cCiudad);
		hmfieldsTable.put("cDscPais", cDscPais);
		hmfieldsTable.put("cDscEstado", cDscEstado);
		hmfieldsTable.put("cDscMunicipio", cDscMunicipio);
		hmfieldsTable.put("cDscLocalidad", cDscLocalidad);
		hmfieldsTable.put("cTelefono", cTelefono);

		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("iCveLocalidad", "" + iCveLocalidad);

		hmfieldsTable.put("cDscPaisNac", cDscPaisNac);
		hmfieldsTable.put("cDscEstadoNac", cDscEstadoNac);
		hmfieldsTable.put("cDscEmpresa", cDscEmpresa);
		hmfieldsTable.put("cDscEmpresaTmp", cDscEmpresaTmp);
		hmfieldsTable.put("cDscFecNacimiento", cDscFecNacimiento);
		hmfieldsTable.put("cDscFecNacimientoNom024", cDscFecNacimientoNom024);

		hmfieldsTable.put("cGpoSang", cGpoSang);
		hmfieldsTable.put("lRH", "" + lRH);
		hmfieldsTable.put("lUsaLentes", "" + lUsaLentes);
		hmfieldsTable.put("lHipertension", "" + lHipertension);
		hmfieldsTable.put("lDiabetes", "" + lDiabetes);

		hmfieldsTable.put("lAereo", "" + lAereo);
		hmfieldsTable.put("lContacto", "" + lContacto);
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("lInternacional", "" + lInternacional);
		hmfieldsTable.put("cLicencia", "" + cLicencia);
		hmfieldsTable.put("cLicenciaInt", "" + cLicenciaInt);

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
		hmfieldsTable.put("iCveGrupoD", "" + iCveGrupoD);
		hmfieldsTable.put("iCveSubGrupoD", "" + iCveSubGrupoD);
		hmfieldsTable.put("iCveGrupoR", "" + iCveGrupoR);
		hmfieldsTable.put("iCveSubGrupoR", "" + iCveSubGrupoR);

		hmfieldsTable.put("cSexo_DGIS", cSexo_DGIS);
		
		hmfieldsTable.put("tSGenerado", tSGenerado);
		
		return hmfieldsTable;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public String getCSexo() {
		return cSexo;
	}

	public void setCSexo(String cSexo) {
		this.cSexo = cSexo;
	}

	
	public String getCSexo_DGIS() {
		return cSexo_DGIS;
	}

	public void setCSexo_DGIS(String cSexo_DGIS) {
		this.cSexo_DGIS = cSexo_DGIS;
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

	public String getCHomoclave() {
		return cHomoclave;
	}

	public void setCHomoclave(String cHomoclave) {
		this.cHomoclave = cHomoclave;
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

	public java.sql.Date getDtSolicitado() {
		return dtSolicitado;
	}

	public void setDtSolicitado(java.sql.Date dtSolicitado) {
		this.dtSolicitado = dtSolicitado;
	}

	public java.sql.Date getDtIniVig() {
		return dtIniVig;
	}

	public void setDtIniVIg(java.sql.Date dtIniVig) {
		this.dtIniVig = dtIniVig;
	}

	public java.sql.Date getDtFinVig() {
		return dtFinVig;
	}

	public void setDtFinVig(java.sql.Date dtFinVig) {
		this.dtFinVig = dtFinVig;
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

	public String getCSenasPersonal() {
		return cSenasPersonal;
	}

	public void setCSenasPersonal(String cSenasPersonal) {
		this.cSenasPersonal = cSenasPersonal;
	}

	public String getCCorreoElec() {
		return cCorreoElec;
	}

	public void setCCorreoElec(String cCorreoElec) {
		this.cCorreoElec = cCorreoElec;
	}

	public int getLDonadorOrg() {
		return lDonadorOrg;
	}

	public void setLDonadorOrg(int lDonadorOrg) {
		this.lDonadorOrg = lDonadorOrg;
	}

	public String getCPersonaAviso() {
		return cPersonaAviso;
	}

	public void setCPersonaAviso(String cPersonaAviso) {
		this.cPersonaAviso = cPersonaAviso;
	}

	public String getCDirecAviso() {
		return cDirecAviso;
	}

	public void setCDirecAviso(String cDirecAviso) {
		this.cDirecAviso = cDirecAviso;
	}

	public String getCTelAviso() {
		return cTelAviso;
	}

	public void setCTelAviso(String cTelAviso) {
		this.cTelAviso = cTelAviso;
	}

	public String getCCorreoAviso() {
		return cCorreoAviso;
	}

	public void setCCorreoAviso(String cCorreoAviso) {
		this.cCorreoAviso = cCorreoAviso;
	}

	public int getICveDireccion() {
		return iCveDireccion;
	}

	public void setICveDireccion(int iCveDireccion) {
		this.iCveDireccion = iCveDireccion;
	}

	public int getICveFoto() {
		return iCveFoto;
	}

	public void setICveFoto(int iCveFoto) {
		this.iCveFoto = iCveFoto;
	}

	public int getLNoHuellas() {
		return lNoHuellas;
	}

	public void setLNoHuellas(int lNoHuellas) {
		this.lNoHuellas = lNoHuellas;
	}

	public int getICveNumEmpresa() {
		return iCveNumEmpresa;
	}

	public void setICveNumEmpresa(int iCveNumEmpresa) {
		this.iCveNumEmpresa = iCveNumEmpresa;
	}

	public String getCNombreCompleto() {
		return cNombre + " " + cApPaterno + " " + cApMaterno;
	}

	public String getCDscEmpresa() {
		return cDscEmpresa;
	}

	public String getCDscEmpresaTmp() {
		return cDscEmpresaTmp;
	}

	public String getCDscEstadoNac() {
		return cDscEstadoNac;
	}

	public String getCDscPaisNac() {
		return cDscPaisNac;
	}

	public void setCDscPaisNac(String cDscPaisNac) {
		this.cDscPaisNac = cDscPaisNac;
	}

	public void setCDscEstadoNac(String cDscEstadoNac) {
		this.cDscEstadoNac = cDscEstadoNac;
	}

	public void setCDscEmpresa(String cDscEmpresa) {
		this.cDscEmpresa = cDscEmpresa;
	}

	public void setCDscEmpresaTmp(String cDscEmpresaTmp) {
		this.cDscEmpresaTmp = cDscEmpresaTmp;
	}

	public String getCCalle() {
		return cCalle;
	}

	public String getCCiudad() {
		return cCiudad;
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

	public void setCLicenciaInt(String cLicenciaInt) {
		this.cLicenciaInt = cLicenciaInt;
	}

	public void setCCalle(String cCalle) {
		this.cCalle = cCalle;
	}

	public void setCCiudad(String cCiudad) {
		this.cCiudad = cCiudad;
	}

	public void setCColonia(String cColonia) {
		this.cColonia = cColonia;
	}

	public String getCColonia() {
		return cColonia;
	}

	public String getCDscMunicipio() {
		return cDscMunicipio;
	}
	
	public String getCDscLocalidad() {
		return cDscLocalidad;
	}	

	public String getCDscPais() {
		return cDscPais;
	}

	public void setCDscMunicipio(String cDscMunicipio) {
		this.cDscMunicipio = cDscMunicipio;
	}

	public void setCDscLocalidad(String cDscLocalidad) {
		this.cDscLocalidad = cDscLocalidad;
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

	public String getCTelefono() {
		return cTelefono;
	}

	public void setCTelefono(String cTelefono) {
		this.cTelefono = cTelefono;
	}

	public int getICP() {
		return iCP;
	}

	public void setICP(int iCP) {
		this.iCP = iCP;
	}

	public String getCNumExt() {
		return cNumExt;
	}

	public String getCNumInt() {
		return cNumInt;
	}

	public void setCNumInt(String cNumInt) {
		this.cNumInt = cNumInt;
	}

	public void setCNumExt(String cNumExt) {
		this.cNumExt = cNumExt;
	}

	public String getCDscFecNacimiento() {
		return cDscFecNacimiento;
	}

	public void setCDscFecNacimiento(String cDscFecNacimiento) {
		this.cDscFecNacimiento = cDscFecNacimiento;
	}

	public String getCDscFecNacimientoNom024() {
		return cDscFecNacimientoNom024;
	}	

	public void setCDscFecNacimientoNom024(String cDscFecNacimientoNom024) {
		this.cDscFecNacimientoNom024 = cDscFecNacimientoNom024;
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

	public int getICveLocalidad() {
		return iCveLocalidad;
	}

	public void setICveMunicipio(int iCveMunicipio) {
		this.iCveMunicipio = iCveMunicipio;
	}
	
	public void setICveLocalidad(int iCveLocalidad) {
		this.iCveLocalidad = iCveLocalidad;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public int getICveUsuRegistra() {
		return iCveUsuRegistra;
	}

	public void setICveUsuRegistra(int iCveUsuRegistra) {
		this.iCveUsuRegistra = iCveUsuRegistra;
	}

	// datos para agregar al Jefe Unidad Medica
	// AG SA

	public int getICveJUniMed() {
		return iCveJUniMed;
	}

	public void setICveJUniMed(int iCveJUniMed) {
		this.iCveJUniMed = iCveJUniMed;
	}

	public String getCGpoSang() {
		return cGpoSang;
	}

	public void setCGpoSang(String cGpoSang) {
		this.cGpoSang = cGpoSang;
	}

	public int getLDiabetes() {
		return lDiabetes;
	}

	public void setLDiabetes(int lDiabetes) {
		this.lDiabetes = lDiabetes;
	}

	public int getLHipertension() {
		return lHipertension;
	}

	public void setLHipertension(int lHipertension) {
		this.lHipertension = lHipertension;
	}

	public int getLRH() {
		return lRH;
	}

	public void setLRH(int lRH) {
		this.lRH = lRH;
	}

	public int getLUsaLentes() {
		return lUsaLentes;
	}

	public void setLUsaLentes(int lUsaLentes) {
		this.lUsaLentes = lUsaLentes;
	}

	public int getLAereo() {
		return lAereo;
	}

	public void setLAereo(int lAereo) {
		this.lAereo = lAereo;
	}

	public void setLContacto(int lContacto) {
		this.lContacto = lContacto;
	}

	public int getLContacto() {
		return lContacto;
	}

	public String getCGenero() {
		if (this.cSexo == null)
			return "";
		if (this.cSexo.equalsIgnoreCase("F"))
			return "FEMENINO";
		else
			return "MASCULINO";
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public int getLInternacional() {
		return lInternacional;
	}

	public void setLInternacional(int lInternacional) {
		this.lInternacional = lInternacional;
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

	public int getICveGrupoD() {
		return iCveGrupoD;
	}

	public void setICveGrupoD(int iCveGrupoD) {
		this.iCveGrupoD = iCveGrupoD;
	}

	public String getICveSubGrupoD() {
		return iCveSubGrupoD;
	}

	public void setICveSubGrupoD(String iCveSubGrupoD) {
		this.iCveSubGrupoD = iCveSubGrupoD;
	}

	public int getICveGrupoR() {
		return iCveGrupoR;
	}

	public void setICveGrupoR(int iCveGrupoR) {
		this.iCveGrupoR = iCveGrupoR;
	}

	public int getICveSubGrupoR() {
		return iCveSubGrupoR;
	}

	public void setICveSubGrupoR(int iCveSubGrupoR) {
		this.iCveSubGrupoR = iCveSubGrupoR;
	}

	public java.sql.Timestamp getTSGenerado() {
		return tSGenerado;
	}

	public void setTSGenerado(java.sql.Timestamp tSGenerado) {
		this.tSGenerado = tSGenerado;
	}
}
