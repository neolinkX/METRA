package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Value Object ALMSolicSumin
 * </p>
 * <p>
 * Description: VO para la tabla ALMSolicSumin
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernández García
 * @version 1.0
 */
public class TVALMSolicSumin implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveAlmacen;
	private int iCveSolicSum;
	private int iCveUniMed;
	private int iCveModulo;
	private int iCveArea;
	private int iCveUsuSolicita;
	private int iCveUsuAutoriza;
	private java.sql.Date dtSolicitud;
	private java.sql.Date dtSuministro;
	private int lProgramada;
	private int iCvePrioridad;
	private int iCvePeriodo;
	private int lAutorizada;
	private int lRevisada;
	private int lSuministrada;
	private String cDscAlmacen;
	private String cDscUniMed;
	private String cDscModulo;
	private String cDscArea;
	private String cDscUsuSolicita;
	private String cDscUsuAutoriza;
	private String cDscPrioridad;
	private String cDscPeriodo;
	private int iCveArticulo;
	private String cDscArticulo;
	private String cDscTpoArticulo;
	private String cDscFamilia;
	private float dUnidSolicita;
	private float dExistencia;
	private String cDscUniSum;
	private int iCveTpoArticulo;
	private int iCveFamilia;
	private float dUnidAutor;
	private int lAnalizada;
	private String cDscMeta;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private String cDscUnidad;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveSolicSum);
		return pk;
	}

	public TVALMSolicSumin() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		TFechas dtFecha = new TFechas();

		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveSolicSum", "" + iCveSolicSum);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("iCveUsuSolicita", "" + iCveUsuSolicita);
		hmfieldsTable.put("iCveUsuAutoriza", "" + iCveUsuAutoriza);

		if (dtSolicitud != null
				&& dtSolicitud.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtSolicitud",
					dtFecha.getFechaDDMMYYYY(dtSolicitud, ""));

		if (dtSuministro != null
				&& dtSuministro.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtSuministro",
					dtFecha.getFechaDDMMYYYY(dtSuministro, ""));

		hmfieldsTable.put("lProgramada", "" + lProgramada);
		hmfieldsTable.put("iCvePrioridad", "" + iCvePrioridad);
		hmfieldsTable.put("iCvePeriodo", "" + iCvePeriodo);
		hmfieldsTable.put("lAutorizada", "" + lAutorizada);
		hmfieldsTable.put("lRevisada", "" + lRevisada);
		hmfieldsTable.put("lSuministrada", "" + lSuministrada);
		hmfieldsTable.put("cDscAlmacen", cDscAlmacen);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("cDscModulo", cDscModulo);
		hmfieldsTable.put("cDscArea", cDscArea);
		hmfieldsTable.put("cDscUsuSolicita", cDscUsuSolicita);
		hmfieldsTable.put("cDscUsuAutoriza", cDscUsuAutoriza);
		hmfieldsTable.put("cDscPrioridad", cDscPrioridad);
		hmfieldsTable.put("cDscPeriodo", cDscPeriodo);

		hmfieldsTable.put("iCveArticulo", "" + iCveArticulo);
		hmfieldsTable.put("cDscArticulo", cDscArticulo);
		hmfieldsTable.put("cDscTpoArticulo", cDscTpoArticulo);
		hmfieldsTable.put("cDscFamilia", cDscFamilia);
		hmfieldsTable.put("dUnidSolicita", "" + dUnidSolicita);
		hmfieldsTable.put("dExistencia", "" + dExistencia);
		hmfieldsTable.put("cDscUniSum", "" + cDscUniSum);
		hmfieldsTable.put("iCveTpoArticulo", "" + iCveTpoArticulo);
		hmfieldsTable.put("iCveFamilia", "" + iCveFamilia);
		hmfieldsTable.put("dUnidAutor", "" + dUnidAutor);
		hmfieldsTable.put("lAnalizada", "" + lAnalizada);
		hmfieldsTable.put("cDscMeta", cDscMeta);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cDscUnidad", cDscUnidad);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveAlmacen() {
		return iCveAlmacen;
	}

	public void setICveAlmacen(int iCveAlmacen) {
		this.iCveAlmacen = iCveAlmacen;
	}

	public int getICveSolicSum() {
		return iCveSolicSum;
	}

	public void setICveSolicSum(int iCveSolicSum) {
		this.iCveSolicSum = iCveSolicSum;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public int getICveArea() {
		return iCveArea;
	}

	public void setICveArea(int iCveArea) {
		this.iCveArea = iCveArea;
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

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public java.sql.Date getDtSuministro() {
		return dtSuministro;
	}

	public void setDtSuministro(java.sql.Date dtSuministro) {
		this.dtSuministro = dtSuministro;
	}

	public int getLProgramada() {
		return lProgramada;
	}

	public void setLProgramada(int lProgramada) {
		this.lProgramada = lProgramada;
	}

	public int getICvePrioridad() {
		return iCvePrioridad;
	}

	public void setICvePrioridad(int iCvePrioridad) {
		this.iCvePrioridad = iCvePrioridad;
	}

	public int getICvePeriodo() {
		return iCvePeriodo;
	}

	public void setICvePeriodo(int iCvePeriodo) {
		this.iCvePeriodo = iCvePeriodo;
	}

	public int getLAutorizada() {
		return lAutorizada;
	}

	public void setLAutorizada(int lAutorizada) {
		this.lAutorizada = lAutorizada;
	}

	public int getLRevisada() {
		return lRevisada;
	}

	public void setLRevisada(int lRevisada) {
		this.lRevisada = lRevisada;
	}

	public int getLSuministrada() {
		return lSuministrada;
	}

	public void setLSuministrada(int lSuministrada) {
		this.lSuministrada = lSuministrada;
	}

	public String getCDscAlmacen() {
		return cDscAlmacen;
	}

	public void setCDscAlmacen(String cDscAlmacen) {
		this.cDscAlmacen = cDscAlmacen;
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

	public String getCDscPrioridad() {
		return cDscPrioridad;
	}

	public void setCDscPrioridad(String cDscPrioridad) {
		this.cDscPrioridad = cDscPrioridad;
	}

	public String getCDscPeriodo() {
		return cDscPeriodo;
	}

	public void setCDscPeriodo(String cDscPeriodo) {
		this.cDscPeriodo = cDscPeriodo;
	}

	public int getICveArticulo() {
		return iCveArticulo;
	}

	public void setICveArticulo(int iCveArticulo) {
		this.iCveArticulo = iCveArticulo;
	}

	public String getCDscArticulo() {
		return cDscArticulo;
	}

	public void setCDscArticulo(String cDscArticulo) {
		this.cDscArticulo = cDscArticulo;
	}

	public String getCDscTpoArticulo() {
		return cDscTpoArticulo;
	}

	public void setCDscTpoArticulo(String cDscTpoArticulo) {
		this.cDscTpoArticulo = cDscTpoArticulo;
	}

	public String getCDscFamilia() {
		return cDscFamilia;
	}

	public void setCDscFamilia(String cDscFamilia) {
		this.cDscFamilia = cDscFamilia;
	}

	public float getDUnidSolicita() {
		return dUnidSolicita;
	}

	public void setDUnidSolicita(float dUnidSolicita) {
		this.dUnidSolicita = dUnidSolicita;
	}

	public float getDExistencia() {
		return dExistencia;
	}

	public void setDExistencia(float dExistencia) {
		this.dExistencia = dExistencia;
	}

	public String getCDscUniSum() {
		return cDscUniSum;
	}

	public void setCDscUniSum(String cDscUniSum) {
		this.cDscUniSum = cDscUniSum;
	}

	public int getICveTpoArticulo() {
		return iCveTpoArticulo;
	}

	public void setICveTpoArticulo(int iCveTpoArticulo) {
		this.iCveTpoArticulo = iCveTpoArticulo;
	}

	public int getICveFamilia() {
		return iCveFamilia;
	}

	public void setICveFamilia(int iCveFamilia) {
		this.iCveFamilia = iCveFamilia;
	}

	public float getDUnidAutor() {
		return dUnidAutor;
	}

	public void setDUnidAutor(float dUnidAutor) {
		this.dUnidAutor = dUnidAutor;
	}

	public int getLAnalizada() {
		return lAnalizada;
	}

	public void setLAnalizada(int lAnalizada) {
		this.lAnalizada = lAnalizada;
	}

	public String getCDscMeta() {
		return cDscMeta;
	}

	public void setCDscMeta(String cDscMeta) {
		this.cDscMeta = cDscMeta;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCApMaterno() {
		return cApMaterno;
	}

	public String getCApPaterno() {
		return cApPaterno;
	}

	public void setCApMaterno(String cApMaterno) {
		this.cApMaterno = cApMaterno;
	}

	public void setCApPaterno(String cApPaterno) {
		this.cApPaterno = cApPaterno;
	}

	public String getCDscUnidad() {
		return cDscUnidad;
	}

	public void setCDscUnidad(String cDscUnidad) {
		this.cDscUnidad = cDscUnidad;
	}
}
