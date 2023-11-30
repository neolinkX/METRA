package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXAbasorvancia
 * </p>
 * <p>
 * Description: VO para catalogo de absorvancia
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */
public class TVTOXAbsorvancia implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveAbsorvancia;
	private int iCveEquipo;
	private java.sql.Date dtEstudio;
	private String cObservacion;
	private int iCveUsuResp;
	private String cDscEquipo;
	private String cFechaFormat;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveAbsorvancia);
		return pk;
	}

	public TVTOXAbsorvancia() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveAbsorvancia", "" + iCveAbsorvancia);
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("dtEstudio", "" + dtEstudio);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("iCveUsuResp", "" + iCveUsuResp);
		hmfieldsTable.put("cDscEquipo", cDscEquipo);
		hmfieldsTable.put("cFechaFormat", cFechaFormat);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveAbsorvancia() {
		return iCveAbsorvancia;
	}

	public void setICveAbsorvancia(int iCveAbsorvancia) {
		this.iCveAbsorvancia = iCveAbsorvancia;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public java.sql.Date getDtEstudio() {
		return dtEstudio;
	}

	public void setDtEstudio(java.sql.Date dtEstudio) {
		this.dtEstudio = dtEstudio;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getICveUsuResp() {
		return iCveUsuResp;
	}

	public void setICveUsuResp(int iCveUsuResp) {
		this.iCveUsuResp = iCveUsuResp;
	}

	public String getCDscEquipo() {
		return cDscEquipo;
	}

	public void setCDscEquipo(String cDscEquipo) {
		this.cDscEquipo = cDscEquipo;
	}

	public String getCFechaFormat() {
		return cFechaFormat;
	}

	public void setCFechaFormat(String cFechaFormat) {
		this.cFechaFormat = cFechaFormat;
	}

}
