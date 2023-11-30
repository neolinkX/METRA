package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPExamAplica
 * </p>
 * <p>
 * Description: Value Object de la Entidad EXPExamAplica
 * </p>
 * <p>  
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVEXPExamAplica implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveUniMed;
	private int iCveProceso;
	private int iCvePersonal;
	private int iCveModulo;
	private java.sql.Date dtSolicitado;
	private int iFolioEs;
	private java.sql.Date dtAplicacion;
	private java.sql.Date dtConcluido;
	private int lIniciado;
	private int lDictaminado;
	private int lInterConsulta;
	private int lInterConcluye;
	private int lConcluido;
	private java.sql.Date dtDictamen;
	private java.sql.Date dtEntregaRes;
	private java.sql.Date dtArchivado;
	private int iCveNumEmpresa;
	private int iCveUsuSolicita;
	private String cRFC;
	private String cDscSituacion;
	private String cDscUniMed;
	private String cDscModulo;
	private String cDscProceso;
	private Vector vcEXPExamCat;
	private int lPrivada;
	private java.sql.Time tInicio;
	private java.sql.Time tConcluido;
	private java.sql.Timestamp tIniExa;

	private int iGenerados;
	private int iDictaminados;
	private int iNoDictaminados;
	private int iCveUsuDictamen;
	private int iDictamenes;
	private String cDictaminador;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	// Campo candado Dictamen
	private java.sql.Date dtBlqDictamen;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		return pk;
	}

	public TVEXPExamAplica() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("dtSolicitado", "" + dtSolicitado);
		hmfieldsTable.put("iFolioEs", "" + iFolioEs);
		hmfieldsTable.put("dtAplicacion", "" + dtAplicacion);
		hmfieldsTable.put("dtConcluido", "" + dtConcluido);
		hmfieldsTable.put("lIniciado", "" + lIniciado);
		hmfieldsTable.put("lDictaminado", "" + lDictaminado);
		hmfieldsTable.put("lInterConsulta", "" + lInterConsulta);
		hmfieldsTable.put("lInterConcluye", "" + lInterConcluye);
		hmfieldsTable.put("lConcluido", "" + lConcluido);
		hmfieldsTable.put("dtDictamen", "" + dtDictamen);
		hmfieldsTable.put("dtEntregaRes", "" + dtEntregaRes);
		hmfieldsTable.put("dtArchivado", "" + dtArchivado);
		hmfieldsTable.put("iCveNumEmpresa", "" + iCveNumEmpresa);
		hmfieldsTable.put("iCveUsuSolicita", "" + iCveUsuSolicita);
		hmfieldsTable.put("cRFC", "" + cRFC);
		hmfieldsTable.put("cDscSituacion", cDscSituacion);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("cDscModulo", cDscModulo);
		hmfieldsTable.put("cDscProceso", cDscProceso);
		hmfieldsTable.put("vcEXPExamCat", vcEXPExamCat);
		hmfieldsTable.put("lPrivada", "" + lPrivada);
		hmfieldsTable.put("tInicio", "" + tInicio);
		hmfieldsTable.put("tConcluido", "" + tConcluido);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("tIniExa", "" + tIniExa);
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

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public java.sql.Date getDtSolicitado() {
		return dtSolicitado;
	}

	public void setDtSolicitado(java.sql.Date dtSolicitado) {
		this.dtSolicitado = dtSolicitado;
	}

	public int getIFolioEs() {
		return iFolioEs;
	}

	public void setIFolioEs(int iFolioEs) {
		this.iFolioEs = iFolioEs;
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

	public int getLIniciado() {
		return lIniciado;
	}

	public void setLIniciado(int lIniciado) {
		this.lIniciado = lIniciado;
	}

	public int getLDictaminado() {
		return lDictaminado;
	}

	public void setLDictaminado(int lDictaminado) {
		this.lDictaminado = lDictaminado;
	}

	public int getLInterConsulta() {
		return lInterConsulta;
	}

	public void setLInterConsulta(int lInterConsulta) {
		this.lInterConsulta = lInterConsulta;
	}

	public int getLInterConcluye() {
		return lInterConcluye;
	}

	public void setLInterConcluye(int lInterConcluye) {
		this.lInterConcluye = lInterConcluye;
	}

	public int getLConcluido() {
		return lConcluido;
	}

	public void setLConcluido(int lConcluido) {
		this.lConcluido = lConcluido;
	}

	public java.sql.Date getDtDictamen() {
		return dtDictamen;
	}

	public void setDtDictamen(java.sql.Date dtDictamen) {
		this.dtDictamen = dtDictamen;
	}

	public java.sql.Date getDtEntregaRes() {
		return dtEntregaRes;
	}

	public void setDtEntregaRes(java.sql.Date dtEntregaRes) {
		this.dtEntregaRes = dtEntregaRes;
	}

	public java.sql.Date getDtArchivado() {
		return dtArchivado;
	}

	public void setDtArchivado(java.sql.Date dtArchivado) {
		this.dtArchivado = dtArchivado;
	}

	public int getICveNumEmpresa() {
		return iCveNumEmpresa;
	}

	public void setICveNumEmpresa(int iCveNumEmpresa) {
		this.iCveNumEmpresa = iCveNumEmpresa;
	}

	public int getICveUsuSolicita() {
		return iCveUsuSolicita;
	}

	public void setICveUsuSolicita(int iCveUsuSolicita) {
		this.iCveUsuSolicita = iCveUsuSolicita;
	}

	public String getCRFC() {
		return cRFC;
	}

	public void setCRFC(String cRFC) {
		this.cRFC = cRFC;
	}

	public String getCDscSituacion() {
		return cDscSituacion;
	}

	public void setCDscSituacion(String cDscSituacion) {
		this.cDscSituacion = cDscSituacion;
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

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public Vector getVcEXPExamCat() {
		return vcEXPExamCat;
	}

	public void setVcEXPExamCat(Vector vcEXPExamCat) {
		this.vcEXPExamCat = vcEXPExamCat;
	}

	public boolean equals(Object obj) {
		boolean bRet = true;
		if (obj == null) {
			bRet = false;
		} else if (obj instanceof TVEXPExamAplica) {
			bRet = getPK().equals(((TVEXPExamAplica) obj).getPK());
		} else {
			bRet = false;
		}
		return bRet;
	}

	public int getLPrivada() {
		return lPrivada;
	}

	public void setLPrivada(int lPrivada) {
		this.lPrivada = lPrivada;
	}

	public java.sql.Time getTConcluido() {
		return tConcluido;
	}

	public java.sql.Time getTInicio() {
		return tInicio;
	}

	public void setTConcluido(java.sql.Time tConcluido) {
		this.tConcluido = tConcluido;
	}

	public void setTInicio(java.sql.Time tInicio) {
		this.tInicio = tInicio;
	}

	public int getIGenerados() {
		return iGenerados;
	}

	public void setIGenerados(int iGenerados) {
		this.iGenerados = iGenerados;
	}

	public int getINoDictaminados() {
		return iNoDictaminados;
	}

	public void setINoDictaminados(int iNoDictaminados) {
		this.iNoDictaminados = iNoDictaminados;
	}

	public int getIDictaminados() {
		return iDictaminados;
	}

	public void setIDictaminados(int iDictaminados) {
		this.iDictaminados = iDictaminados;
	}

	public String getCDictaminador() {
		return cDictaminador;
	}

	public void setCDictaminador(String cDictaminador) {
		this.cDictaminador = cDictaminador;
	}

	public int getICveUsuDictamen() {
		return iCveUsuDictamen;
	}

	public void setICveUsuDictamen(int iCveUsuDictamen) {
		this.iCveUsuDictamen = iCveUsuDictamen;
	}

	public int getIDictamenes() {
		return iDictamenes;
	}

	public void setIDictamenes(int iDictamenes) {
		this.iDictamenes = iDictamenes;
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

	public java.sql.Date getDtBlqDictamen() {
		return dtBlqDictamen;
	}

	// Candado Dictamen
	public void setDtBlqDictamen(java.sql.Date dtBlqDictamen) {
		this.dtBlqDictamen = dtBlqDictamen;
	}

	public java.sql.Timestamp getTIniExa() {
		return tIniExa;
	}

	public void setTIniExa(java.sql.Timestamp tIniExa) {
		this.tIniExa = tIniExa;
	}

	
}

