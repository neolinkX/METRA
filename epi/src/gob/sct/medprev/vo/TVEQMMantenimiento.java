package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;
import com.micper.util.TFechas;

/**
 * <p>
 * Title: Value Object EQMMantenimiento
 * </p>
 * <p>
 * Description: Value Object Tabla EQMMantenimiento
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
public class TVEQMMantenimiento implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEquipo;
	private int iCveMantenimiento;
	private java.sql.Date dtSolicitud;
	private java.sql.Date dtProgramado;
	private int iCveTpoMantto;
	private int iCveMotivo;
	private String cAccesorios;
	private String cAnalisisOper;
	private int iCveEmpMtto;
	private String cNombre;
	private String cResultado;
	private String cObservaciones;
	private int lConcluido;
	private java.sql.Date dtRecepcion;
	private int iCveUsuSolicita;
	private int iCveUsuAutoriza;
	private int iCveUsuRecibe;
	private int lCancelado;
	private java.sql.Date dtCancelacion;
	private String cDscBreveClasif;
	private String cDscBreveTpoEquipo;
	private String cDscBreveMarca;
	private String cDscTpoMantto;
	private String cDscMotivo;
	private String cDscEquipo;
	private String cModelo;
	private String cNumSerie;
	private String cInventario;
	private String cDscUniMed;
	private String cDscModulo;
	private String cDscArea;
	private String cDscTpoEquipo;
	private String cDscUsuSolicita;
	private String cDscUsuAutoriza;
	private String cDscUsuRecibe;
	private String cDscEmpMtto;
	private String cDtProgramado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEquipo);
		pk.add("" + iCveMantenimiento);
		return pk;
	}

	public TVEQMMantenimiento() {
	}

	public HashMap toHashMap() {
		TFechas dtFecha = new TFechas();
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveMantenimiento", "" + iCveMantenimiento);

		if (dtSolicitud != null
				&& dtSolicitud.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtSolicitud",
					dtFecha.getFechaDDMMYYYY(dtSolicitud, ""));

		if (dtProgramado != null
				&& dtProgramado.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtProgramado",
					dtFecha.getFechaDDMMYYYY(dtProgramado, ""));

		if (dtRecepcion != null
				&& dtRecepcion.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtRecepcion",
					dtFecha.getFechaDDMMYYYY(dtRecepcion, ""));

		if (dtCancelacion != null
				&& dtCancelacion.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtCancelacion",
					dtFecha.getFechaDDMMYYYY(dtCancelacion, ""));

		hmfieldsTable.put("iCveTpoMantto", "" + iCveTpoMantto);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("cAccesorios", cAccesorios);
		hmfieldsTable.put("cAnalisisOper", cAnalisisOper);
		hmfieldsTable.put("iCveEmpMtto", "" + iCveEmpMtto);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cResultado", cResultado);
		hmfieldsTable.put("cObservaciones", cObservaciones);
		hmfieldsTable.put("lConcluido", "" + lConcluido);
		hmfieldsTable.put("iCveUsuSolicita", "" + iCveUsuSolicita);
		hmfieldsTable.put("iCveUsuAutoriza", "" + iCveUsuAutoriza);
		hmfieldsTable.put("iCveUsuRecibe", "" + iCveUsuRecibe);
		hmfieldsTable.put("lCancelado", "" + lCancelado);
		hmfieldsTable.put("cDscBreveClasif", "" + cDscBreveClasif);
		hmfieldsTable.put("cDscBreveTpoEquipo", "" + cDscBreveTpoEquipo);
		hmfieldsTable.put("cDscBreveMarca", "" + cDscBreveMarca);
		hmfieldsTable.put("cDscTpoMantto", "" + cDscTpoMantto);
		hmfieldsTable.put("cDscMotivo", "" + cDscMotivo);
		hmfieldsTable.put("cDscEquipo", "" + cDscEquipo);
		hmfieldsTable.put("cNumSerie", "" + cNumSerie);
		hmfieldsTable.put("cInventario", "" + cInventario);
		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cDscModulo", "" + cDscModulo);
		hmfieldsTable.put("cDscArea", "" + cDscArea);
		hmfieldsTable.put("cDscTpoEquipo", "" + cDscTpoEquipo);
		hmfieldsTable.put("cDscUsuSolicita", "" + cDscUsuSolicita);
		hmfieldsTable.put("cDscUsuAutoriza", "" + cDscUsuAutoriza);
		hmfieldsTable.put("cDscUsuRecibe", "" + cDscUsuRecibe);
		hmfieldsTable.put("cDscEmpMtto", "" + cDscEmpMtto);
		hmfieldsTable.put("cDtProgramado", "" + cDtProgramado);
		hmfieldsTable.put("cModelo", "" + cModelo);
		return hmfieldsTable;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public int getICveMantenimiento() {
		return iCveMantenimiento;
	}

	public void setICveMantenimiento(int iCveMantenimiento) {
		this.iCveMantenimiento = iCveMantenimiento;
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

	public int getICveTpoMantto() {
		return iCveTpoMantto;
	}

	public void setICveTpoMantto(int iCveTpoMantto) {
		this.iCveTpoMantto = iCveTpoMantto;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public String getCAccesorios() {
		return cAccesorios;
	}

	public void setCAccesorios(String cAccesorios) {
		this.cAccesorios = cAccesorios;
	}

	public String getCAnalisisOper() {
		return cAnalisisOper;
	}

	public void setCAnalisisOper(String cAnalisisOper) {
		this.cAnalisisOper = cAnalisisOper;
	}

	public int getICveEmpMtto() {
		return iCveEmpMtto;
	}

	public void setICveEmpMtto(int iCveEmpMtto) {
		this.iCveEmpMtto = iCveEmpMtto;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
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

	public int getICveUsuAutoriza() {
		return iCveUsuAutoriza;
	}

	public void setICveUsuAutoriza(int iCveUsuAutoriza) {
		this.iCveUsuAutoriza = iCveUsuAutoriza;
	}

	public int getICveUsuRecibe() {
		return iCveUsuRecibe;
	}

	public void setICveUsuRecibe(int iCveUsuRecibe) {
		this.iCveUsuRecibe = iCveUsuRecibe;
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

	public String getCDscBreveClasif() {
		return cDscBreveClasif;
	}

	public void setCDscBreveClasif(String cDscBreveClasif) {
		this.cDscBreveClasif = cDscBreveClasif;
	}

	public String getCDscBreveTpoEquipo() {
		return cDscBreveTpoEquipo;
	}

	public void setCDscBreveTpoEquipo(String cDscBreveTpoEquipo) {
		this.cDscBreveTpoEquipo = cDscBreveTpoEquipo;
	}

	public String getCDscBreveMarca() {
		return cDscBreveMarca;
	}

	public void setCDscBreveMarca(String cDscBreveMarca) {
		this.cDscBreveMarca = cDscBreveMarca;
	}

	public String getCDscTpoMantto() {
		return cDscTpoMantto;
	}

	public void setCDscTpoMantto(String cDscTpoMantto) {
		this.cDscTpoMantto = cDscTpoMantto;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public String getCDscEquipo() {
		return cDscEquipo;
	}

	public void setCDscEquipo(String cDscEquipo) {
		this.cDscEquipo = cDscEquipo;
	}

	public String getCNumSerie() {
		return cNumSerie;
	}

	public void setCNumSerie(String cNumSerie) {
		this.cNumSerie = cNumSerie;
	}

	public String getCInventario() {
		return cInventario;
	}

	public void setCInventario(String cInventario) {
		this.cInventario = cInventario;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}

	public String getCDscArea() {
		return cDscArea;
	}

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

	public String getCDscTpoEquipo() {
		return cDscTpoEquipo;
	}

	public void setCDscTpoEquipo(String cDscTpoEquipo) {
		this.cDscTpoEquipo = cDscTpoEquipo;
	}

	public String getCDscUsuSolicita() {
		return cDscUsuSolicita;
	}

	public void setCDscUsuSolicita(String cDscUsuSolicita) {
		this.cDscUsuSolicita = cDscUsuSolicita;
	}

	public String getCDscUsuAutoriza() {
		return cDscUsuAutoriza;
	}

	public void setCDscUsuAutoriza(String cDscUsuAutoriza) {
		this.cDscUsuAutoriza = cDscUsuAutoriza;
	}

	public String getCDscUsuRecibe() {
		return cDscUsuRecibe;
	}

	public void setCDscUsuRecibe(String cDscUsuRecibe) {
		this.cDscUsuRecibe = cDscUsuRecibe;
	}

	public String getCDscEmpMtto() {
		return cDscEmpMtto;
	}

	public void setCDscEmpMtto(String cDscEmpMtto) {
		this.cDscEmpMtto = cDscEmpMtto;
	}

	public String getCDtProgramado() {
		return cDtProgramado;
	}

	public void setCDtProgramado(String cDtProgramado) {
		this.cDtProgramado = cDtProgramado;
	}

	public String getCModelo() {
		return cModelo;
	}

	public void setCModelo(String cModelo) {
		this.cModelo = cModelo;
	}

}
