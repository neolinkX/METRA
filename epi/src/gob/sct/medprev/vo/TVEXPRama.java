package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.text.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object EXPRama
 * </p>
 * <p>
 * Description: Value Object EXPRama
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
public class TVEXPRama implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveServicio;
	private int iCveRama;
	private int iConcluido;
	private java.sql.Date dtInicio;
	private java.sql.Date dtFin;
	private int iCveUsuAplica;
	private String cDscRama;
	private String cDscServicio;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private java.sql.Timestamp tsInicio;
	private java.sql.Timestamp tsFin;
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveServicio);
		pk.add("" + iCveRama);
		return pk;
	}

	public TVEXPRama() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("iConcluido", "" + iConcluido);
		hmfieldsTable.put("dtInicio", "" + dtInicio);
		hmfieldsTable.put("dtFin", "" + dtFin);
		hmfieldsTable.put("iCveUsuAplica", "" + iCveUsuAplica);
		hmfieldsTable.put("cDscRama", cDscRama);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("cNombre", cNombre);
		if (tsInicio != null && tsInicio.toString().compareTo("") != 0
				&& tsInicio.toString().compareTo("null") != 0)
			hmfieldsTable.put("tsInicio", "" + sdf.format(tsInicio));
		if (tsFin != null && tsFin.toString().compareTo("") != 0
				&& tsFin.toString().compareTo("null") != 0)
			hmfieldsTable.put("tsFin", "" + sdf.format(tsFin));
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

	public int getICveRama() {
		return iCveRama;
	}

	public void setICveRama(int iCveRama) {
		this.iCveRama = iCveRama;
	}

	public int getIConcluido() {
		return iConcluido;
	}

	public void setIConcluido(int iConcluido) {
		this.iConcluido = iConcluido;
	}

	public java.sql.Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(java.sql.Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public java.sql.Date getDtFin() {
		return dtFin;
	}

	public void setDtFin(java.sql.Date dtFin) {
		this.dtFin = dtFin;
	}

	public int getICveUsuAplica() {
		return iCveUsuAplica;
	}

	public void setICveUsuAplica(int iCveUsuAplica) {
		this.iCveUsuAplica = iCveUsuAplica;
	}

	public String getCDscRama() {
		return cDscRama;
	}

	public void setCDscRama(String cDscRama) {
		this.cDscRama = cDscRama;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
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

	public java.sql.Timestamp getTsInicio() {
		return tsInicio;
	}

	public void setTsInicio(java.sql.Timestamp tsInicio) {
		this.tsInicio = tsInicio;
	}

	public java.sql.Timestamp getTsFin() {
		return tsFin;
	}

	public void setTsFin(java.sql.Timestamp tsFin) {
		this.tsFin = tsFin;
	}
}
