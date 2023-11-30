package gob.sct.medprev.vo;

import java.util.*;
import java.text.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPServicio
 * </p>
 * <p>
 * Description: EXPServicio
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */
public class TVEXPServicio implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveServicio;
	private int iOrden;
	private String cNotaMedica;
	private int iCveUsuNotaMed;
	private int lConcluido;
	private java.sql.Date dtInicio;
	private java.sql.Date dtFin;
	private int iCveUsuAplica;
	private String cDscServicio;
	private String cApPaterno;
	private String cApMaterno;
	private String cNombre;
	private String cRFC;
	private java.sql.Timestamp tsInicio;
	private java.sql.Timestamp tsFin;
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVEXPServicio() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iOrden", "" + iOrden);
		hmfieldsTable.put("cNotaMedica", cNotaMedica);
		hmfieldsTable.put("iCveUsuNotaMed", "" + iCveUsuNotaMed);
		hmfieldsTable.put("lConcluido", "" + lConcluido);
		hmfieldsTable.put("dtInicio", "" + dtInicio);
		hmfieldsTable.put("dtFin", "" + dtFin);
		hmfieldsTable.put("iCveUsuAplica", "" + iCveUsuAplica);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cRFC", cRFC);
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

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

	public String getCNotaMedica() {
		return cNotaMedica;
	}

	public void setCNotaMedica(String cNotaMedica) {
		this.cNotaMedica = cNotaMedica;
	}

	public int getICveUsuNotaMed() {
		return iCveUsuNotaMed;
	}

	public void setICveUsuNotaMed(int iCveUsuNotaMed) {
		this.iCveUsuNotaMed = iCveUsuNotaMed;
	}

	public int getLConcluido() {
		return lConcluido;
	}

	public void setLConcluido(int lConcluido) {
		this.lConcluido = lConcluido;
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

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
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

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCRFC() {
		return cRFC;
	}

	public void setCRFC(String cRFC) {
		this.cRFC = cRFC;
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
