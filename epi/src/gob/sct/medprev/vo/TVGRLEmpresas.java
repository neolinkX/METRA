package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object GRLEmpresas
 * </p>
 * <p>
 * Description: Value Object de la Tabla GRLEmpresas
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
public class TVGRLEmpresas implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpresa;
	private int iCveGrupoEmp;
	private String cIDDGPMPT;
	private int iIDMdoTrans;
	private String cRFC;
	private String cCurp;
	private int iCveUniMed;
	private int iCveMdoTrans;
	private String cTpoPersona;
	private String cApPaterno;
	private String cApMaterno;
	private String cNombreRS;
	private String cDenominacion;
	private java.sql.Date dtRegistro;
	private String cCveRPA;
	private int iCveOringenInf;
	private String cDscMdoTrans;
	private String cDscBreve;
	private String cDscOrigenInf;
	private String cCalle;
	private String cColonia;
	private String cCiudad;
	private String cDscEmpresa;
	private int iCvePlantilla;
	private int iCveMovimiento;
	private int iCveEtapa;
	private java.sql.Date dtSolicitud;
	private java.sql.Date dtVencimiento;
	private java.sql.Date dtRecepcion;
	private int iCveDomicilio;
	private String cDscEntidadFed;
	private String cDscMunicipio;
	private int iCP;
	private String cTelefono;
	private int iCveEntidadFed;
	private int iCveMunicipio;
	private int iCvePais;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpresa);
		return pk;
	}

	public TVGRLEmpresas() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("iCveGrupoEmp", "" + iCveGrupoEmp);
		hmfieldsTable.put("cIDDGPMPT", "" + cIDDGPMPT);
		hmfieldsTable.put("iIDMdoTrans", "" + iIDMdoTrans);
		hmfieldsTable.put("cRFC", cRFC);
		hmfieldsTable.put("cCurp", cCurp);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("cTpoPersona", cTpoPersona);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cNombreRS", cNombreRS);
		hmfieldsTable.put("cDenominacion", cDenominacion);
		hmfieldsTable.put("dtRegistro", "" + dtRegistro);
		hmfieldsTable.put("cCveRPA", cCveRPA);
		hmfieldsTable.put("iCveOrigenInf", "" + iCveOringenInf);
		hmfieldsTable.put("cDscMdoTrans", "" + cDscMdoTrans);
		hmfieldsTable.put("cDscBreve", "" + cDscBreve);
		hmfieldsTable.put("cDscOrigenInf", "" + cDscOrigenInf);
		hmfieldsTable.put("cCalle", "" + cCalle);
		hmfieldsTable.put("cColonia", "" + cColonia);
		hmfieldsTable.put("cCiudad", "" + cCiudad);
		hmfieldsTable.put("cNombreCompleto", this.getCNombreCompleto());
		hmfieldsTable.put("cDscEmpresa", cDscEmpresa);
		hmfieldsTable.put("iCvePlantilla", "" + iCvePlantilla);
		hmfieldsTable.put("iCveMovimiento", "" + iCveMovimiento);
		hmfieldsTable.put("iCveEtapa", "" + iCveEtapa);
		hmfieldsTable.put("dtSolicitud", "" + dtSolicitud);
		hmfieldsTable.put("dtVencimiento", "" + dtVencimiento);
		hmfieldsTable.put("dtRecepcion", "" + dtRecepcion);
		hmfieldsTable.put("iCveDomicilio", "" + iCveDomicilio);
		hmfieldsTable.put("cDscEntidaFed", cDscEntidadFed);
		hmfieldsTable.put("cDscMunicipio", cDscMunicipio);
		hmfieldsTable.put("cTelefono", cTelefono);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEntidadFed", "" + iCveEntidadFed);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		return hmfieldsTable;
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public int getICveGrupoEmp() {
		return iCveGrupoEmp;
	}

	public void setICveGrupoEmp(int iCveGrupoEmp) {
		this.iCveGrupoEmp = iCveGrupoEmp;
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

	public String getCRFC() {
		return cRFC;
	}

	public void setCRFC(String cRFC) {
		this.cRFC = cRFC;
	}

	public String getCCurp() {
		return cCurp;
	}

	public void setCCurp(String cCurp) {
		this.cCurp = cCurp;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
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

	public String getCDenominacion() {
		return cDenominacion;
	}

	public void setCDenominacion(String cDenominacion) {
		this.cDenominacion = cDenominacion;
	}

	public java.sql.Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(java.sql.Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getCCveRPA() {
		return cCveRPA;
	}

	public void setCCveRPA(String cCveRPA) {
		this.cCveRPA = cCveRPA;
	}

	public int getICveOringenInf() {
		return iCveOringenInf;
	}

	public void setICveOringenInf(int iCveOringenInf) {
		this.iCveOringenInf = iCveOringenInf;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCDscOrigenInf() {
		return cDscOrigenInf;
	}

	public void setCDscOrigenInf(String cDscOrigenInf) {
		this.cDscOrigenInf = cDscOrigenInf;
	}

	public String getcCalle() {
		return cCalle;
	}

	public void setcCalle(String cCalle) {
		this.cCalle = cCalle;
	}

	public String getcColonia() {
		return cColonia;
	}

	public void setcColonia(String cColonia) {
		this.cColonia = cColonia;
	}

	public String getcCiudad() {
		return cCiudad;
	}

	public void setcCiudad(String cCiudad) {
		this.cCiudad = cCiudad;
	}

	public String getCNombreCompleto() {
		return cNombreRS + ' ' + cApPaterno + ' ' + cApMaterno;
	}

	public String getcDscEmpresa() {
		return cDscEmpresa;
	}

	public void setcDscEmpresa(String cDscEmpresa) {
		this.cDscEmpresa = cDscEmpresa;
	}

	public int getiCvePlantilla() {
		return iCvePlantilla;
	}

	public void setiCvePlantilla(int iCvePlantilla) {
		this.iCvePlantilla = iCvePlantilla;
	}

	public int getiCveMovimiento() {
		return iCveMovimiento;
	}

	public void setiCveMovimiento(int iCveMovimiento) {
		this.iCveMovimiento = iCveMovimiento;
	}

	public int getiCveEtapa() {
		return iCveEtapa;
	}

	public void setiCveEtapa(int iCveEtapa) {
		this.iCveEtapa = iCveEtapa;
	}

	public java.sql.Date getdtSolicitud() {
		return dtSolicitud;
	}

	public void setdtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public java.sql.Date getdtVencimiento() {
		return dtVencimiento;
	}

	public void setdtVencimiento(java.sql.Date dtVencimiento) {
		this.dtVencimiento = dtVencimiento;
	}

	public java.sql.Date getdtRecepcion() {
		return dtRecepcion;
	}

	public void setdtRecepcion(java.sql.Date dtRecepcion) {
		this.dtRecepcion = dtRecepcion;
	}

	public int getiCveDomicilio() {
		return iCveDomicilio;
	}

	public void setiCveDomicilio(int iCveDomicilio) {
		this.iCveDomicilio = iCveDomicilio;
	}

	public String getCDscEntidadFed() {
		return cDscEntidadFed;
	}

	public void setCDscEntidadFed(String cDscEntidadFed) {
		this.cDscEntidadFed = cDscEntidadFed;
	}

	public String getCDscMunicipio() {
		return cDscMunicipio;
	}

	public void setCDscMunicipio(String cDscMunicipio) {
		this.cDscMunicipio = cDscMunicipio;
	}

	public int getICP() {
		return iCP;
	}

	public void setICP(int iCP) {
		this.iCP = iCP;
	}

	public String getCTelefono() {
		return cTelefono;
	}

	public void setCTelefono(String cTelefono) {
		this.cTelefono = cTelefono;
	}

	public int getICveEntidadFed() {
		return iCveEntidadFed;
	}

	public void setICveEntidadFed(int iCveEntidadFed) {
		this.iCveEntidadFed = iCveEntidadFed;
	}

	public int getICveMunicipio() {
		return iCveMunicipio;
	}

	public void setICveMunicipio(int iCveMunicipio) {
		this.iCveMunicipio = iCveMunicipio;
	}

	public int getICvePais() {
		return iCvePais;
	}

	public void setICvePais(int iCvePais) {
		this.iCvePais = iCvePais;
	}
}
