package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object VEHUbicacion
 * </p>
 * <p>
 * Description: Value Object de ubicación de vehículos
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
public class TVVEHUbicacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveVehiculo;
	private int iCveUbicacion;
	private int iCveUniMed;
	private String cDscUniMed;
	private java.sql.Date dtAsignacion;
	private java.sql.Date dtDesasigna;
	private int lActivo;
	private String cNumSerie;
	private String cInventario;
	private String cPlacas;
	private String cDscTpoVehiculo;
	private String cDscMarca;
	private String cDscModelo;
	private String cDscMtvoBaja;
	private String cDscEstadoVeh;
	private String cDscColor;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveVehiculo);
		pk.add("" + iCveUbicacion);
		return pk;
	}

	public TVVEHUbicacion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveVehiculo", "" + iCveVehiculo);
		hmfieldsTable.put("iCveUbicacion", "" + iCveUbicacion);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("dtAsignacion", "" + dtAsignacion);
		hmfieldsTable.put("dtDesasigna", "" + dtDesasigna);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cNumSerie", "" + cNumSerie);
		hmfieldsTable.put("cInventario", "" + cInventario);
		hmfieldsTable.put("cPlacas", "" + cPlacas);
		hmfieldsTable.put("cDscTpoVehiculo", "" + cDscTpoVehiculo);
		hmfieldsTable.put("cDscMarca", "" + cDscMarca);
		hmfieldsTable.put("cDscModelo", "" + cDscModelo);
		hmfieldsTable.put("cDscMtvoBaja", "" + cDscMtvoBaja);
		hmfieldsTable.put("cDscEstadoVeh", "" + cDscEstadoVeh);
		hmfieldsTable.put("cDscColor", "" + cDscColor);

		return hmfieldsTable;
	}

	public int getICveVehiculo() {
		return iCveVehiculo;
	}

	public void setICveVehiculo(int iCveVehiculo) {
		this.iCveVehiculo = iCveVehiculo;
	}

	public int getICveUbicacion() {
		return iCveUbicacion;
	}

	public void setICveUbicacion(int iCveUbicacion) {
		this.iCveUbicacion = iCveUbicacion;
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

	public java.sql.Date getDtAsignacion() {
		return dtAsignacion;
	}

	public void setDtAsignacion(java.sql.Date dtAsignacion) {
		this.dtAsignacion = dtAsignacion;
	}

	public java.sql.Date getDtDesasigna() {
		return dtDesasigna;
	}

	public void setDtDesasigna(java.sql.Date dtDesasigna) {
		this.dtDesasigna = dtDesasigna;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
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

	public String getCPlacas() {
		return cPlacas;
	}

	public void setCPlacas(String cPlacas) {
		this.cPlacas = cPlacas;
	}

	public String getCDscTpoVehiculo() {
		return cDscTpoVehiculo;
	}

	public void setCDscTpoVehiculo(String cDscTpoVehiculo) {
		this.cDscTpoVehiculo = cDscTpoVehiculo;
	}

	public String getCDscMarca() {
		return cDscMarca;
	}

	public void setCDscMarca(String cDscMarca) {
		this.cDscMarca = cDscMarca;
	}

	public String getCDscModelo() {
		return cDscModelo;
	}

	public void setCDscModelo(String cDscModelo) {
		this.cDscModelo = cDscModelo;
	}

	public String getCDscMtvoBaja() {
		return cDscMtvoBaja;
	}

	public void setCDscMtvoBaja(String cDscMtvoBaja) {
		this.cDscMtvoBaja = cDscMtvoBaja;
	}

	public String getcDscEstadoVeh() {
		return cDscEstadoVeh;
	}

	public void setcDscEstadoVeh(String cDscEstadoVeh) {
		this.cDscEstadoVeh = cDscEstadoVeh;
	}

	public String getCDscColor() {
		return cDscColor;
	}

	public void setCDscColor(String cDscColor) {
		this.cDscColor = cDscColor;
	}
}
