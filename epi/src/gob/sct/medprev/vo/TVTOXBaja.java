package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Value Object TOXBaja
 * </p>
 * <p>
 * Description: VO Tabla TOXBaja
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
public class TVTOXBaja implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveLaboratorio;
	private int iCveBaja;
	private java.sql.Date dtDesechado;
	private String cDesechado;
	private String cOrganoInterno;
	private int iCveUsuBaja;
	private String cObservacion;
	private String cInactiva;
	private int iCodigo;
	private String cLote;
	private String cDscBreve;
	private java.sql.Date dtBaja;
	private String cBaja;
	private int iCveMotBaja;
	private String cDscLaboratorio;
	private String cNombreUsuario;
	private String cDscMotBaja;
	private String cComboReactivo;
	private String cComboCalibra;
	private String cDtDesechado;
	private String cFechaCompleta;

	private int iCveCtrolCalibra;
	private int iCveReactivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveBaja);
		return pk;
	}

	public TVTOXBaja() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveBaja", "" + iCveBaja);
		hmfieldsTable.put("dtDesechado", "" + dtDesechado);
		hmfieldsTable.put("cOrganoInterno", cOrganoInterno);
		hmfieldsTable.put("iCveUsuBaja", "" + iCveUsuBaja);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("cInactiva", cInactiva);
		hmfieldsTable.put("iCodigo", "" + iCodigo);
		hmfieldsTable.put("cLote", cLote);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("dtBaja", dtBaja);
		hmfieldsTable.put("cBaja", cBaja);
		hmfieldsTable.put("cDesechado", cDesechado);
		hmfieldsTable.put("iCveMotBaja", "" + iCveMotBaja);
		hmfieldsTable.put("cDscLaboratorio", cDscLaboratorio);
		hmfieldsTable.put("cNombreUsuario", cNombreUsuario);
		hmfieldsTable.put("cDscMotBaja", cDscMotBaja);
		hmfieldsTable.put("cComboReactivo", cComboReactivo);
		hmfieldsTable.put("cComboCalibra", cComboCalibra);
		hmfieldsTable.put("cDtDesechado", cDtDesechado);

		hmfieldsTable.put("iCveCtrolCalibra", "" + iCveCtrolCalibra);
		hmfieldsTable.put("iCveReactivo", "" + iCveReactivo);
		hmfieldsTable.put("cFechaCompleta", cFechaCompleta);

		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getICveBaja() {
		return iCveBaja;
	}

	public void setICveBaja(int iCveBaja) {
		this.iCveBaja = iCveBaja;
	}

	public java.sql.Date getDtDesechado() {
		return dtDesechado;
	}

	public void setDtDesechado(java.sql.Date dtDesechado) {
		this.dtDesechado = dtDesechado;
	}

	public String getCDesechado() {
		return cDesechado;
	}

	public void setCDesechado(String cDesechado) {
		this.cDesechado = cDesechado;
	}

	public String getCOrganoInterno() {
		return cOrganoInterno;
	}

	public void setCOrganoInterno(String cOrganoInterno) {
		this.cOrganoInterno = cOrganoInterno;
	}

	public int getICveUsuBaja() {
		return iCveUsuBaja;
	}

	public void setICveUsuBaja(int iCveUsuBaja) {
		this.iCveUsuBaja = iCveUsuBaja;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public String getCInactiva() {
		return cInactiva;
	}

	public void setCInactiva(String cInactiva) {
		this.cInactiva = cInactiva;
	}

	public int getICodigo() {
		return iCodigo;
	}

	public void setICodigo(int iCodigo) {
		this.iCodigo = iCodigo;
	}

	public String getCLote() {
		return cLote;
	}

	public void setCLote(String cLote) {
		this.cLote = cLote;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public java.sql.Date getDtBaja() {
		return dtBaja;
	}

	public void setDtBaja(java.sql.Date dtBaja) {
		this.dtBaja = dtBaja;
	}

	public String getCBaja() {
		return cBaja;
	}

	public void setCBaja(String cBaja) {
		this.cBaja = cBaja;
	}

	public int getICveMotBaja() {
		return iCveMotBaja;
	}

	public void setICveMotBaja(int iCveMotBaja) {
		this.iCveMotBaja = iCveMotBaja;
	}

	public String getCDscLaboratorio() {
		return cDscLaboratorio;
	}

	public void setCDscLaboratorio(String cDscLaboratorio) {
		this.cDscLaboratorio = cDscLaboratorio;
	}

	public String getCNombreUsuario() {
		return cNombreUsuario;
	}

	public void setCNombreUsuario(String cNombreUsuario) {
		this.cNombreUsuario = cNombreUsuario;
	}

	public String getCDscMotBaja() {
		return cDscMotBaja;
	}

	public void setCDscMotBaja(String cDscMotBaja) {
		this.cDscMotBaja = cDscMotBaja;
	}

	public String getCComboReactivo() {
		return cComboReactivo;
	}

	public String getCComboCalibra() {
		return cComboCalibra;
	}

	public void setCComboCalibra(String cComboCalibra) {
		this.cComboCalibra = cComboCalibra;
	}

	public void setCComboReactivo(String cComboReactivo) {
		this.cComboReactivo = cComboReactivo;
	}

	public String getCDtDesechado() {
		return cDtDesechado;
	}

	public void setCDtDesechado(String cDtDesechado) {
		this.cDtDesechado = cDtDesechado;
	}

	public int getICveReactivo() {
		return iCveReactivo;
	}

	public void setICveReactivo(int iCveReactivo) {
		this.iCveReactivo = iCveReactivo;
	}

	public int getICveCtrolCalibra() {
		return iCveCtrolCalibra;
	}

	public void setICveCtrolCalibra(int iCveCtrolCalibra) {
		this.iCveCtrolCalibra = iCveCtrolCalibra;
	}

	public String getCFechaCompleta() {
		return cFechaCompleta;
	}

	public void setCFechaCompleta(String cFechaCompleta) {
		this.cFechaCompleta = cFechaCompleta;
	}

}
