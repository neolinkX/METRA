package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object EXPInterconsulta
 * </p>
 * <p>
 * Description: Value Object de la tabla EXPInterconsulta
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sanchez
 * @version 1.0
 */
public class TVEXPInterconsulta implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveServicio;
	private int iCveUniMed;
	private int iCveModulo;
	private java.sql.Date dtSolicitud;
	private java.sql.Date dtPropAtencion;
	private String cMtvoSolicitud;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVEXPInterconsulta() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("dtSolicitud", "" + dtSolicitud);
		hmfieldsTable.put("dtPropAtencion", "" + dtPropAtencion);
		hmfieldsTable.put("cMtvoSolicitud", cMtvoSolicitud);
		return hmfieldsTable;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
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

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public java.sql.Date getDtPropAtencion() {
		return dtPropAtencion;
	}

	public void setDtPropAtencion(java.sql.Date dtPropAtencion) {
		this.dtPropAtencion = dtPropAtencion;
	}

	public String getCMtvoSolicitud() {
		return cMtvoSolicitud;
	}

	public void setCMtvoSolicitud(String cMtvoSolicitud) {
		this.cMtvoSolicitud = cMtvoSolicitud;
	}
}
