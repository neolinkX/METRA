package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;
import com.micper.util.TFechas;

/**
 * <p>
 * Title: Value Object VEHMantenimiento
 * </p>
 * <p>
 * Description: Value Object de mantenimiento de vehículos
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
public class TVVEHMantenimiento implements HashBeanInterface {
	private BeanPK pk;
	private int iCveVehiculo;
	private int iCveMantenimiento;
	private int iCveTpoMantto;
	private String cDscTpoMantto;
	private java.sql.Date dtSolicitud;
	private java.sql.Date dtProgramado;
	private java.sql.Date dtInicia;
	private int iCveEmpMantto;
	private String cNombreEmpMantto;
	private String cDscEmpMantto;
	private String cDscBreveEmpMantto;
	private String cAccesorios;
	private String cResultado;
	private String cObservaciones;
	private int lConcluido;
	private int iKilometraje;
	private java.sql.Date dtRecepcion;
	private int iCveUsuSolicita;
	private String cDscUsuSolicita;
	private int iCveUsuAutoriza;
	private String cDscUsuAutoriza;
	private int iCveUsuRecibe;
	private String cDscUsuRecibe;
	private int lCancelado;
	private java.sql.Date dtCancelacion;
	private String cDscTpoVehiculo;
	private String cPlacas;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveVehiculo);
		pk.add("" + iCveMantenimiento);
		return pk;
	}

	public TVVEHMantenimiento() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		TFechas dtFecha = new TFechas();

		if (dtSolicitud != null
				&& dtSolicitud.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtSolicitud",
					dtFecha.getFechaDDMMYYYY(dtSolicitud, ""));

		if (dtProgramado != null
				&& dtProgramado.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtProgramado",
					dtFecha.getFechaDDMMYYYY(dtProgramado, ""));

		if (dtInicia != null
				&& dtInicia.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtInicia",
					dtFecha.getFechaDDMMYYYY(dtInicia, ""));

		if (dtRecepcion != null
				&& dtRecepcion.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtRecepcion",
					dtFecha.getFechaDDMMYYYY(dtRecepcion, ""));

		if (dtCancelacion != null
				&& dtCancelacion.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtCancelacion",
					dtFecha.getFechaDDMMYYYY(dtCancelacion, ""));

		hmfieldsTable.put("iCveVehiculo", "" + iCveVehiculo);
		hmfieldsTable.put("iCveMantenimiento", "" + iCveMantenimiento);
		hmfieldsTable.put("iCveTpoMantto", "" + iCveTpoMantto);
		hmfieldsTable.put("cDscTpoMantto", cDscTpoMantto);
		hmfieldsTable.put("iCveEmpMantto", "" + iCveEmpMantto);
		hmfieldsTable.put("cNombreEmpMantto", cNombreEmpMantto);
		hmfieldsTable.put("cDscEmpMantto", cDscEmpMantto);
		hmfieldsTable.put("cDscBreveEmpMantto", cDscBreveEmpMantto);
		hmfieldsTable.put("cAccesorios", cAccesorios);
		hmfieldsTable.put("cResultado", cResultado);
		hmfieldsTable.put("cObservaciones", cObservaciones);
		hmfieldsTable.put("lConcluido", "" + lConcluido);
		hmfieldsTable.put("iKilometraje", "" + iKilometraje);
		hmfieldsTable.put("iCveUsuSolicita", "" + iCveUsuSolicita);
		hmfieldsTable.put("cDscUsuSolicita", cDscUsuSolicita);
		hmfieldsTable.put("iCveUsuAutoriza", "" + iCveUsuAutoriza);
		hmfieldsTable.put("cDscUsuAutoriza", cDscUsuAutoriza);
		hmfieldsTable.put("iCveUsuRecibe", "" + iCveUsuRecibe);
		hmfieldsTable.put("cDscUsuRecibe", cDscUsuRecibe);
		hmfieldsTable.put("lCancelado", "" + lCancelado);
		hmfieldsTable.put("cDscTpoVehiculo", "" + cDscTpoVehiculo);
		hmfieldsTable.put("cPlacas", cPlacas);
		return hmfieldsTable;
	}

	public int getICveVehiculo() {
		return iCveVehiculo;
	}

	public void setICveVehiculo(int iCveVehiculo) {
		this.iCveVehiculo = iCveVehiculo;
	}

	public int getICveMantenimiento() {
		return iCveMantenimiento;
	}

	public void setICveMantenimiento(int iCveMantenimiento) {
		this.iCveMantenimiento = iCveMantenimiento;
	}

	public int getICveTpoMantto() {
		return iCveTpoMantto;
	}

	public void setICveTpoMantto(int iCveTpoMantto) {
		this.iCveTpoMantto = iCveTpoMantto;
	}

	public String getCDscTpoMantto() {
		return cDscTpoMantto;
	}

	public void setCDscTpoMantto(String cDscTpoMantto) {
		this.cDscTpoMantto = cDscTpoMantto;
	}

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public java.sql.Date getDtProgramado() {
		return dtProgramado;
	}

	public void setDtProgramado(java.sql.Date dtProgramado) {
		this.dtProgramado = dtProgramado;
	}

	public java.sql.Date getDtInicia() {
		return dtInicia;
	}

	public void setDtInicia(java.sql.Date dtInicia) {
		this.dtInicia = dtInicia;
	}

	public int getICveEmpMantto() {
		return iCveEmpMantto;
	}

	public void setICveEmpMantto(int iCveEmpMantto) {
		this.iCveEmpMantto = iCveEmpMantto;
	}

	public String getCNombreEmpMantto() {
		return cNombreEmpMantto;
	}

	public void setCNombreEmpMantto(String cNombreEmpMantto) {
		this.cNombreEmpMantto = cNombreEmpMantto;
	}

	public String getCDscEmpMantto() {
		return cDscEmpMantto;
	}

	public void setCDscEmpMantto(String cDscEmpMantto) {
		this.cDscEmpMantto = cDscEmpMantto;
	}

	public String getCDscBreveEmpMantto() {
		return cDscBreveEmpMantto;
	}

	public void setCDscBreveEmpMantto(String cDscBreveEmpMantto) {
		this.cDscBreveEmpMantto = cDscBreveEmpMantto;
	}

	public String getCAccesorios() {
		return cAccesorios;
	}

	public void setCAccesorios(String cAccesorios) {
		this.cAccesorios = cAccesorios;
	}

	public String getCResultado() {
		return cResultado;
	}

	public void setCResultado(String cResultado) {
		this.cResultado = cResultado;
	}

	public String getCObservaciones() {
		return cObservaciones;
	}

	public void setCObservaciones(String cObservaciones) {
		this.cObservaciones = cObservaciones;
	}

	public int getLConcluido() {
		return lConcluido;
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

	public java.sql.Date getDtRecepcion() {
		return dtRecepcion;
	}

	public void setDtRecepcion(java.sql.Date dtRecepcion) {
		this.dtRecepcion = dtRecepcion;
	}

	public int getICveUsuSolicita() {
		return iCveUsuSolicita;
	}

	public void setICveUsuSolicita(int iCveUsuSolicita) {
		this.iCveUsuSolicita = iCveUsuSolicita;
	}

	public String getCDscUsuSolicita() {
		return cDscUsuSolicita;
	}

	public void setCDscUsuSolicita(String cDscUsuSolicita) {
		this.cDscUsuSolicita = cDscUsuSolicita;
	}

	public int getICveUsuAutoriza() {
		return iCveUsuAutoriza;
	}

	public void setICveUsuAutoriza(int iCveUsuAutoriza) {
		this.iCveUsuAutoriza = iCveUsuAutoriza;
	}

	public String getCDscUsuAutoriza() {
		return cDscUsuAutoriza;
	}

	public void setCDscUsuAutoriza(String cDscUsuAutoriza) {
		this.cDscUsuAutoriza = cDscUsuAutoriza;
	}

	public int getICveUsuRecibe() {
		return iCveUsuRecibe;
	}

	public void setICveUsuRecibe(int iCveUsuRecibe) {
		this.iCveUsuRecibe = iCveUsuRecibe;
	}

	public String getCDscUsuRecibe() {
		return cDscUsuRecibe;
	}

	public void setCDscUsuRecibe(String cDscUsuRecibe) {
		this.cDscUsuRecibe = cDscUsuRecibe;
	}

	public int getLCancelado() {
		return lCancelado;
	}

	public void setLCancelado(int lCancelado) {
		this.lCancelado = lCancelado;
	}

	public java.sql.Date getDtCancelacion() {
		return dtCancelacion;
	}

	public void setDtCancelacion(java.sql.Date dtCancelacion) {
		this.dtCancelacion = dtCancelacion;
	}

	public String getCDscTpoVehiculo() {
		return cDscTpoVehiculo;
	}

	public void setCDscTpoVehiculo(String cDscTpoVehiculo) {
		this.cDscTpoVehiculo = cDscTpoVehiculo;
	}

	public String getCPlacas() {
		return cPlacas;
	}

	public void setCPlacas(String cPlacas) {
		this.cPlacas = cPlacas;
	}
}
