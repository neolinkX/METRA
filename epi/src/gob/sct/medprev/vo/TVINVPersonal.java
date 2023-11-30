package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object INVPersonal
 * </p>
 * <p>
 * Description: VO Tabla INVPersonal
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. González Paz
 * @version 1.0
 */
public class TVINVPersonal implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveMdoTrans;
	private int iIDDGPMPT;
	private int iCveVehiculo;
	private int iCveInvPers;
	private int iCvePuesto;
	private java.sql.Date dtVigencia;
	private int iCveSituacion;
	private int iCvePersonal;
	private int iNumExamen;
	private int iNumExamINV;
	private int iNumExamTOX;
	private int lSinLicencia;
	private String cLicencia;
	private String cDscPuesto;
	private String cDscSituacion;
	private String cDscVehiculo;
	private String cDscDtVigencia;
	private String cNombreCompleto;
	private int iCveExpediente;

	private String cDscUniMed;
	private String cDscProceso;
	private java.sql.Date dtAplicacion;
	private java.sql.Date dtConcluido;
	private String cDscMdoTrans;
	private int iCveCategoria;
	private int lDictamen;
	private String cDscDtAplicacion;
	private String cDscDtConcluido;

	/*
	 * "GRLUniMed.cDscUniMed, " + "GRLProceso.cDscProceso, " +
	 * "EXPExamAplica.dtAplicacion, " + "EXPExamAplica.dtConcluido, " +
	 * "GRLMdoTrans.cDscMdoTrans, " + "EXPExamCat.iCveCategoria, " +
	 * "EXPExamCat.lDictamen " +
	 */

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iIDDGPMPT);
		pk.add("" + iCveVehiculo);
		pk.add("" + iCveInvPers);
		return pk;
	}

	public TVINVPersonal() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iIDDGPMPT", "" + iIDDGPMPT);
		hmfieldsTable.put("iCveVehiculo", "" + iCveVehiculo);
		hmfieldsTable.put("iCveInvPers", "" + iCveInvPers);
		hmfieldsTable.put("iCvePuesto", "" + iCvePuesto);
		hmfieldsTable.put("dtVigencia", "" + dtVigencia);
		hmfieldsTable.put("iCveSituacion", "" + iCveSituacion);
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("lSinLicencia", "" + lSinLicencia);
		hmfieldsTable.put("cLicencia", cLicencia);
		hmfieldsTable.put("cDscPuesto", cDscPuesto);
		hmfieldsTable.put("cDscSituacion", cDscSituacion);
		hmfieldsTable.put("cDscVehiculo", cDscVehiculo);
		hmfieldsTable.put("cDscDtVigencia", cDscDtVigencia);
		hmfieldsTable.put("cNombreCompleto", cNombreCompleto);
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamINV", "" + iNumExamINV);
		hmfieldsTable.put("iNumExamTOX", "" + iNumExamTOX);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("cDscProceso", cDscProceso);
		hmfieldsTable.put("dtAplicacion", "" + dtAplicacion);
		hmfieldsTable.put("dtConcluido", "" + dtConcluido);
		hmfieldsTable.put("cDscMdoTrans", cDscMdoTrans);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("lDictamen", "" + lDictamen);
		hmfieldsTable.put("cDscDtAplicacion", cDscDtAplicacion);
		hmfieldsTable.put("cDscDtConcluido", cDscDtConcluido);

		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getIIDDGPMPT() {
		return iIDDGPMPT;
	}

	public void setIIDDGPMPT(int iIDDGPMPT) {
		this.iIDDGPMPT = iIDDGPMPT;
	}

	public int getICveVehiculo() {
		return iCveVehiculo;
	}

	public void setICveVehiculo(int iCveVehiculo) {
		this.iCveVehiculo = iCveVehiculo;
	}

	public int getICveInvPers() {
		return iCveInvPers;
	}

	public void setICveInvPers(int iCveInvPers) {
		this.iCveInvPers = iCveInvPers;
	}

	public int getICvePuesto() {
		return iCvePuesto;
	}

	public void setICvePuesto(int iCvePuesto) {
		this.iCvePuesto = iCvePuesto;
	}

	public java.sql.Date getDtVigencia() {
		return dtVigencia;
	}

	public void setDtVigencia(java.sql.Date dtVigencia) {
		this.dtVigencia = dtVigencia;
	}

	public int getICveSituacion() {
		return iCveSituacion;
	}

	public void setICveSituacion(int iCveSituacion) {
		this.iCveSituacion = iCveSituacion;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public int getLSinLicencia() {
		return lSinLicencia;
	}

	public void setLSinLicencia(int lSinLicencia) {
		this.lSinLicencia = lSinLicencia;
	}

	public String getCLicencia() {
		return cLicencia;
	}

	public void setCLicencia(String cLicencia) {
		this.cLicencia = cLicencia;
	}

	public String getCDscDtVigencia() {
		return cDscDtVigencia;
	}

	public String getCDscPuesto() {
		return cDscPuesto;
	}

	public String getCDscSituacion() {
		return cDscSituacion;
	}

	public String getCDscVehiculo() {
		return cDscVehiculo;
	}

	public void setCDscVehiculo(String cDscVehiculo) {
		this.cDscVehiculo = cDscVehiculo;
	}

	public void setCDscSituacion(String cDscSituacion) {
		this.cDscSituacion = cDscSituacion;
	}

	public void setCDscPuesto(String cDscPuesto) {
		this.cDscPuesto = cDscPuesto;
	}

	public void setCDscDtVigencia(String cDscDtVigencia) {
		this.cDscDtVigencia = cDscDtVigencia;
	}

	public String getCNombreCompleto() {
		return cNombreCompleto;
	}

	public void setCNombreCompleto(String cNombreCompleto) {
		this.cNombreCompleto = cNombreCompleto;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public int getINumExamINV() {
		return iNumExamINV;
	}

	public void setINumExamINV(int iNumExamINV) {
		this.iNumExamINV = iNumExamINV;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public java.sql.Date getDtAplicacion() {
		return dtAplicacion;
	}

	public void setDtAplicacion(java.sql.Date dtAplicacion) {
		this.dtAplicacion = dtAplicacion;
	}

	public java.sql.Date getDtConcluido() {
		return dtConcluido;
	}

	public void setDtConcluido(java.sql.Date dtConcluido) {
		this.dtConcluido = dtConcluido;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public int getICveCategoria() {
		return iCveCategoria;
	}

	public void setICveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

	public int getLDictamen() {
		return lDictamen;
	}

	public void setLDictamen(int lDictamen) {
		this.lDictamen = lDictamen;
	}

	public String getCDscDtAplicacion() {
		return cDscDtAplicacion;
	}

	public void setCDscDtAplicacion(String cDscDtAplicacion) {
		this.cDscDtAplicacion = cDscDtAplicacion;
	}

	public String getCDscDtConcluido() {
		return cDscDtConcluido;
	}

	public void setCDscDtConcluido(String cDscDtConcluido) {
		this.cDscDtConcluido = cDscDtConcluido;
	}

	public int getINumExamTOX() {
		return iNumExamTOX;
	}

	public void setINumExamTOX(int iNumExamTOX) {
		this.iNumExamTOX = iNumExamTOX;
	}
}


