package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Value Object VEHEmpMantto
 * </p>
 * <p>
 * Description: Value Object de Empresas que Proporcionan Mantenimiento a
 * Vehículos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 */
public class TVVEHEmpMantto implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpMantto;
	private String cRFC;
	private String cCURP;
	private String cTpoPersona;
	private String cApPaterno;
	private String cApMaterno;
	private String cNombreRS;
	private String cDscEmpMantto;
	private String cContacto;
	private String cCalle;
	private String cColonia;
	private String cCiudad;
	private int iCvePais;
	private String cDscPais;
	private int iCveEstado;
	private String cDscEstado;
	private int iCveMunicipio;
	private String cDscMunicipio;
	private String cDscBreve;
	private int iCP;
	private String cTel;
	private String cFax;
	private String cCorreoElec;
	private int lActivo;

	private java.sql.Date dtSolicitud;
	private java.sql.Date dtProgramado;
	private java.sql.Date dtInicia;
	private java.sql.Date dtRecepcion;
	private java.sql.Date dtCancelacion;

	private String cResultado;
	private String cObservaciones;
	private String cDscMarca;
	private String cDscTpoVehiculo;
	private String cDscTpoMantto;
	private String cDscModelo;
	private int lConcluido;
	private int iKilometraje;
	private int lCancelado;
	private String cPlacas;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpMantto);
		return pk;
	}

	public TVVEHEmpMantto() {
	}

	public HashMap toHashMap() {
		TFechas tFechas = new TFechas();
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpMantto", "" + iCveEmpMantto);
		hmfieldsTable.put("cRFC", cRFC);
		hmfieldsTable.put("cCURP", cCURP);
		hmfieldsTable.put("cTpoPersona", cTpoPersona);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cNombreRS", cNombreRS);
		hmfieldsTable.put("cDscEmpMantto", cDscEmpMantto);
		hmfieldsTable.put("cContacto", cContacto);
		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("cCiudad", cCiudad);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("cDscPais", cDscPais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("cDscEstado", cDscEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("cDscMunicipio", cDscMunicipio);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("cTel", cTel);
		hmfieldsTable.put("cFax", cFax);
		hmfieldsTable.put("cCorreoElec", cCorreoElec);
		hmfieldsTable.put("lActivo", "" + lActivo);

		if (dtSolicitud != null)
			hmfieldsTable.put("dtSolicitud",
					tFechas.getFechaDDMMYYYY(dtSolicitud, ""));
		if (dtProgramado != null)
			hmfieldsTable.put("dtProgramado",
					tFechas.getFechaDDMMYYYY(dtProgramado, ""));
		if (dtInicia != null)
			hmfieldsTable.put("dtInicia",
					tFechas.getFechaDDMMYYYY(dtInicia, ""));
		if (dtRecepcion != null)
			hmfieldsTable.put("dtRecepcion",
					tFechas.getFechaDDMMYYYY(dtRecepcion, ""));
		if (dtCancelacion != null)
			hmfieldsTable.put("dtCancelacion",
					tFechas.getFechaDDMMYYYY(dtCancelacion, ""));

		hmfieldsTable.put("cResultado", cResultado);
		hmfieldsTable.put("cObservaciones", cObservaciones);
		hmfieldsTable.put("cDscMarca", cDscMarca);
		hmfieldsTable.put("cDscTpoVehiculo", cDscTpoVehiculo);
		hmfieldsTable.put("cDscTpoMantto", cDscTpoMantto);
		hmfieldsTable.put("cDscModelo", cDscModelo);
		hmfieldsTable.put("lConcluido", "" + lConcluido);
		hmfieldsTable.put("iKilometraje", "" + iKilometraje);
		hmfieldsTable.put("lCancelado", "" + lCancelado);
		hmfieldsTable.put("cPlacas", cPlacas);

		return hmfieldsTable;
	}

	public int getICveEmpMantto() {
		return iCveEmpMantto;
	}

	public void setICveEmpMantto(int iCveEmpMantto) {
		this.iCveEmpMantto = iCveEmpMantto;
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

	public String getCDscEmpMantto() {
		return cDscEmpMantto;
	}

	public void setCDscEmpMantto(String cDscEmpMantto) {
		this.cDscEmpMantto = cDscEmpMantto;
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

	public String getCDscPais() {
		return cDscPais;
	}

	public void setCDscPais(String cDscPais) {
		this.cDscPais = cDscPais;
	}

	public int getICveEstado() {
		return iCveEstado;
	}

	public void setICveEstado(int iCveEstado) {
		this.iCveEstado = iCveEstado;
	}

	public String getCDscEstado() {
		return cDscEstado;
	}

	public void setCDscEstado(String cDscEstado) {
		this.cDscEstado = cDscEstado;
	}

	public int getICveMunicipio() {
		return iCveMunicipio;
	}

	public void setICveMunicipio(int iCveMunicipio) {
		this.iCveMunicipio = iCveMunicipio;
	}

	public String getCDscMunicipio() {
		return cDscMunicipio;
	}

	public void setCDscMunicipio(String cDscMunicipio) {
		this.cDscMunicipio = cDscMunicipio;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
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

	public String getCDscMarca() {
		return cDscMarca;
	}

	public void setCDscMarca(String cDscMarca) {
		this.cDscMarca = cDscMarca;
	}

	public String getCDscTpoVehiculo() {
		return cDscTpoVehiculo;
	}

	public void setCDscTpoVehiculo(String cDscTpoVehiculo) {
		this.cDscTpoVehiculo = cDscTpoVehiculo;
	}

	public String getCObservaciones() {
		return cObservaciones;
	}

	public void setCObservaciones(String cObservaciones) {
		this.cObservaciones = cObservaciones;
	}

	public String getCResultado() {
		return cResultado;
	}

	public void setCResultado(String cResultado) {
		this.cResultado = cResultado;
	}

	public java.sql.Date getDtProgramado() {
		return dtProgramado;
	}

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtProgramado(java.sql.Date dtProgramado) {
		this.dtProgramado = dtProgramado;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public String getCDscTpoMantto() {
		return cDscTpoMantto;
	}

	public void setCDscTpoMantto(String cDscTpoMantto) {
		this.cDscTpoMantto = cDscTpoMantto;
	}

	public String getCDscModelo() {
		return cDscModelo;
	}

	public void setCDscModelo(String cDscModelo) {
		this.cDscModelo = cDscModelo;
	}

	public java.sql.Date getDtCancelacion() {
		return dtCancelacion;
	}

	public void setDtCancelacion(java.sql.Date dtCancelacion) {
		this.dtCancelacion = dtCancelacion;
	}

	public java.sql.Date getDtInicia() {
		return dtInicia;
	}

	public void setDtInicia(java.sql.Date dtInicia) {
		this.dtInicia = dtInicia;
	}

	public java.sql.Date getDtRecepcion() {
		return dtRecepcion;
	}

	public void setDtRecepcion(java.sql.Date dtRecepcion) {
		this.dtRecepcion = dtRecepcion;
	}

	public int getLCancelado() {
		return lCancelado;
	}

	public int getLConcluido() {
		return lConcluido;
	}

	public void setLCancelado(int lCancelado) {
		this.lCancelado = lCancelado;
	}

	public void setLConcluido(int lConcluido) {
		this.lConcluido = lConcluido;
	}

	public int getIKilometraje() {
		return iKilometraje;
	}

	public void setIKilometraje(int iKilometraje) {
		this.iKilometraje = iKilometraje;
	}

	public String getCPlacas() {
		return cPlacas;
	}

	public void setCPlacas(String cPlacas) {
		this.cPlacas = cPlacas;
	}
}
