package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object EQMServXEq
 * </p>
 * <p>
 * Description: VO Tabla EQMServXEq
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
public class TVEQMServXEq implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEquipo;
	private int iCveAsignacion;
	private int iCveServicio;
	private String cDscServicio;
	private int lActual;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVEQMServXEq() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveAsignacion", "" + iCveAsignacion);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("lActual", "" + lActual);

		return hmfieldsTable;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public int getICveAsignacion() {
		return iCveAsignacion;
	}

	public void setICveAsignacion(int iCveAsignacion) {
		this.iCveAsignacion = iCveAsignacion;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public int getLActual() {
		return lActual;
	}

	public void setLActual(int lActual) {
		this.lActual = lActual;
	}
}
