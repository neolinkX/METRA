package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object VEHEtapaXSolic
 * </p>
 * <p>
 * Description: Value Object de Etapas por Solicitud
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
public class TVVEHEtapaXSolic implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveUniMed;
	private String cDscUniMed;
	private int iCveSolicitud;
	private java.sql.Date dtSolicitud;
	private java.sql.Date dtRegistro;
	private int iCveUsuSolicita;
	private String cDscUsuSolicita;
	private int iCveModulo;
	private String cDscModulo;
	private int iCveArea;
	private String cDscArea;
	private int iCveMotivo;
	private String cDscMotivo;
	private int iCveVehiculo;
	private String cPlacas;
	private int iCveEtapaSolic;
	private String cDscEtapaSolic;
	private java.sql.Date dtRevistro;
	private int iCveUsuRegistra;
	private String cDscUsuRegistra;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveUniMed);
		pk.add("" + iCveSolicitud);
		pk.add("" + iCveEtapaSolic);
		return pk;
	}

	public TVVEHEtapaXSolic() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("iCveSolicitud", "" + iCveSolicitud);
		hmfieldsTable.put("dtSolicitud", "" + dtSolicitud);
		hmfieldsTable.put("dtRegistro", "" + dtRegistro);
		hmfieldsTable.put("iCveUsuSolicita", "" + iCveUsuSolicita);
		hmfieldsTable.put("cDscUsuSolicita", cDscUsuSolicita);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("cDscModulo", cDscModulo);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("cDscArea", cDscArea);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("cDscMotivo", cDscMotivo);
		hmfieldsTable.put("iCveVehiculo", "" + iCveVehiculo);
		hmfieldsTable.put("cPlacas", cPlacas);
		hmfieldsTable.put("iCveEtapaSolic", "" + iCveEtapaSolic);
		hmfieldsTable.put("cDscEtapaSolic", cDscEtapaSolic);
		hmfieldsTable.put("dtRevistro", "" + dtRevistro);
		hmfieldsTable.put("iCveUsuRegistra", "" + iCveUsuRegistra);
		hmfieldsTable.put("cDscUsuRegistra", cDscUsuRegistra);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public int getICveSolicitud() {
		return iCveSolicitud;
	}

	public void setICveSolicitud(int iCveSolicitud) {
		this.iCveSolicitud = iCveSolicitud;
	}

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public java.sql.Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(java.sql.Date dtRegistro) {
		this.dtRegistro = dtRegistro;
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

	public int getICveArea() {
		return iCveArea;
	}

	public void setICveArea(int iCveArea) {
		this.iCveArea = iCveArea;
	}

	public String getCDscArea() {
		return cDscArea;
	}

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public int getICveVehiculo() {
		return iCveVehiculo;
	}

	public void setICveVehiculo(int iCveVehiculo) {
		this.iCveVehiculo = iCveVehiculo;
	}

	public String getCPlacas() {
		return cPlacas;
	}

	public void setCPlacas(String cPlacas) {
		this.cPlacas = cPlacas;
	}

	public int getICveEtapaSolic() {
		return iCveEtapaSolic;
	}

	public void setICveEtapaSolic(int iCveEtapaSolic) {
		this.iCveEtapaSolic = iCveEtapaSolic;
	}

	public String getCDscEtapaSolic() {
		return cDscEtapaSolic;
	}

	public void setCDscEtapaSolic(String cDscEtapaSolic) {
		this.cDscEtapaSolic = cDscEtapaSolic;
	}

	public java.sql.Date getDtRevistro() {
		return dtRevistro;
	}

	public void setDtRevistro(java.sql.Date dtRevistro) {
		this.dtRevistro = dtRevistro;
	}

	public int getICveUsuRegistra() {
		return iCveUsuRegistra;
	}

	public void setICveUsuRegistra(int iCveUsuRegistra) {
		this.iCveUsuRegistra = iCveUsuRegistra;
	}

	public String getCDscUsuRegistra() {
		return cDscUsuRegistra;
	}

	public void setCDscUsuRegistra(String cDscUsuRegistra) {
		this.cDscUsuRegistra = cDscUsuRegistra;
	}
}
