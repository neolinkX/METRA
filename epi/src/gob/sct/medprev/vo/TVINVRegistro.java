package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object INVRegistro
 * </p>
 * <p>
 * Description: Value Object de la Entidad INVRegistro
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
public class TVINVRegistro implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveMdoTrans;
	private int iIDDGPMPT;
	private int iIDMdoTrans;
	private java.sql.Date dtAccidente;
	private java.sql.Date dtNotifica;
	private String cDscBreve;
	private int iCveMedInforma;
	private String cLugar;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;
	private int iCveTpoCausa;
	private int iCveCausa;
	private String cObservacion;
	private String cDscAccidente;
	private int iVehFedInvolucra;
	private int iVehEdoInvolucra;
	private int iVehPartInvolucra;
	private int iPerFedInvolucra;
	private int iPerEdoInvolucra;
	private int iPerPartInvolucra;
	private int iCveUniMed;
	private java.sql.Date dtAsigna;
	private int lInvestigado;
	private String cConclusion;
	private int iCveMotivo;
	private int lConcluido;
	private java.sql.Date dtConcluido;
	private int lCancelado;
	private java.sql.Date dtCancelado;
	private int lFinRegistro;
	private String cPais;
	private String cEstado;
	private String cMunicipio;
	private String cDscTpoCausa;
	private String cDscCausa;
	private String cDscMedInforma;
	private String cDscUniMed;
	private String cDscDtAsigna;
	private String cDscDtAccidente;
	private String cDscModulo;
	private int iCveModulo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iIDDGPMPT);
		return pk;
	}

	public TVINVRegistro() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iIDDGPMPT", "" + iIDDGPMPT);
		hmfieldsTable.put("iIDMdoTrans", "" + iIDMdoTrans);
		hmfieldsTable.put("dtAccidente", "" + dtAccidente);
		hmfieldsTable.put("dtNotifica", "" + dtNotifica);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("iCveMedInforma", "" + iCveMedInforma);
		hmfieldsTable.put("cLugar", cLugar);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("iCveTpoCausa", "" + iCveTpoCausa);
		hmfieldsTable.put("iCveCausa", "" + iCveCausa);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("cDscAccidente", cDscAccidente);
		hmfieldsTable.put("iVehFedInvolucra", "" + iVehFedInvolucra);
		hmfieldsTable.put("iVehEdoInvolucra", "" + iVehEdoInvolucra);
		hmfieldsTable.put("iVehPartInvolucra", "" + iVehPartInvolucra);
		hmfieldsTable.put("iPerFedInvolucra", "" + iPerFedInvolucra);
		hmfieldsTable.put("iPerEdoInvolucra", "" + iPerEdoInvolucra);
		hmfieldsTable.put("iPerPartInvolucra", "" + iPerPartInvolucra);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("dtAsigna", "" + dtAsigna);
		hmfieldsTable.put("lInvestigado", "" + lInvestigado);
		hmfieldsTable.put("cConclusion", cConclusion);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("lConcluido", "" + lConcluido);
		hmfieldsTable.put("dtConcluido", "" + dtConcluido);
		hmfieldsTable.put("lCancelado", "" + lCancelado);
		hmfieldsTable.put("dtCancelado", "" + dtCancelado);
		hmfieldsTable.put("cPais", cPais);
		hmfieldsTable.put("cEstado", cEstado);
		hmfieldsTable.put("cMunicipio", cMunicipio);
		hmfieldsTable.put("cDscTpoCausa", cDscTpoCausa);
		hmfieldsTable.put("cDscCausa", cDscCausa);
		hmfieldsTable.put("cDscMedInforma", cDscMedInforma);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("cDscDtAsigna", cDscDtAsigna);
		hmfieldsTable.put("cDscDtAccidente", cDscDtAccidente);
		hmfieldsTable.put("cDscModulo", cDscModulo);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);

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

	public int getIIDMdoTrans() {
		return iIDMdoTrans;
	}

	public void setIIDMdoTrans(int iIDMdoTrans) {
		this.iIDMdoTrans = iIDMdoTrans;
	}

	public java.sql.Date getDtAccidente() {
		return dtAccidente;
	}

	public void setDtAccidente(java.sql.Date dtAccidente) {
		this.dtAccidente = dtAccidente;
	}

	public java.sql.Date getDtNotifica() {
		return dtNotifica;
	}

	public void setDtNotifica(java.sql.Date dtNotifica) {
		this.dtNotifica = dtNotifica;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getICveMedInforma() {
		return iCveMedInforma;
	}

	public void setICveMedInforma(int iCveMedInforma) {
		this.iCveMedInforma = iCveMedInforma;
	}

	public String getCLugar() {
		return cLugar;
	}

	public void setCLugar(String cLugar) {
		this.cLugar = cLugar;
	}

	public int getICvePais() {
		return iCvePais;
	}

	public void setICvePais(int iCvePais) {
		this.iCvePais = iCvePais;
	}

	public int getICveEstado() {
		return iCveEstado;
	}

	public void setICveEstado(int iCveEstado) {
		this.iCveEstado = iCveEstado;
	}

	public int getICveMunicipio() {
		return iCveMunicipio;
	}

	public void setICveMunicipio(int iCveMunicipio) {
		this.iCveMunicipio = iCveMunicipio;
	}

	public int getICveTpoCausa() {
		return iCveTpoCausa;
	}

	public void setICveTpoCausa(int iCveTpoCausa) {
		this.iCveTpoCausa = iCveTpoCausa;
	}

	public int getICveCausa() {
		return iCveCausa;
	}

	public void setICveCausa(int iCveCausa) {
		this.iCveCausa = iCveCausa;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public String getCDscAccidente() {
		return cDscAccidente;
	}

	public void setCDscAccidente(String cDscAccidente) {
		this.cDscAccidente = cDscAccidente;
	}

	public int getIVehFedInvolucra() {
		return iVehFedInvolucra;
	}

	public void setIVehFedInvolucra(int iVehFedInvolucra) {
		this.iVehFedInvolucra = iVehFedInvolucra;
	}

	public int getIVehEdoInvolucra() {
		return iVehEdoInvolucra;
	}

	public void setIVehEdoInvolucra(int iVehEdoInvolucra) {
		this.iVehEdoInvolucra = iVehEdoInvolucra;
	}

	public int getIVehPartInvolucra() {
		return iVehPartInvolucra;
	}

	public void setIVehPartInvolucra(int iVehPartInvolucra) {
		this.iVehPartInvolucra = iVehPartInvolucra;
	}

	public int getIPerFedInvolucra() {
		return iPerFedInvolucra;
	}

	public void setIPerFedInvolucra(int iPerFedInvolucra) {
		this.iPerFedInvolucra = iPerFedInvolucra;
	}

	public int getIPerEdoInvolucra() {
		return iPerEdoInvolucra;
	}

	public void setIPerEdoInvolucra(int iPerEdoInvolucra) {
		this.iPerEdoInvolucra = iPerEdoInvolucra;
	}

	public int getIPerPartInvolucra() {
		return iPerPartInvolucra;
	}

	public void setIPerPartInvolucra(int iPerPartInvolucra) {
		this.iPerPartInvolucra = iPerPartInvolucra;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public java.sql.Date getDtAsigna() {
		return dtAsigna;
	}

	public void setDtAsigna(java.sql.Date dtAsigna) {
		this.dtAsigna = dtAsigna;
	}

	public int getLInvestigado() {
		return lInvestigado;
	}

	public void setLInvestigado(int lInvestigado) {
		this.lInvestigado = lInvestigado;
	}

	public String getCConclusion() {
		return cConclusion;
	}

	public void setCConclusion(String cConclusion) {
		this.cConclusion = cConclusion;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public int getLConcluido() {
		return lConcluido;
	}

	public void setLConcluido(int lConcluido) {
		this.lConcluido = lConcluido;
	}

	public java.sql.Date getDtConcluido() {
		return dtConcluido;
	}

	public void setDtConcluido(java.sql.Date dtConcluido) {
		this.dtConcluido = dtConcluido;
	}

	public int getLCancelado() {
		return lCancelado;
	}

	public void setLCancelado(int lCancelado) {
		this.lCancelado = lCancelado;
	}

	public java.sql.Date getDtCancelado() {
		return dtCancelado;
	}

	public void setDtCancelado(java.sql.Date dtCancelado) {
		this.dtCancelado = dtCancelado;
	}

	public int getLFinRegistro() {
		return lFinRegistro;
	}

	public void setLFinRegistro(int lFinRegistro) {
		this.lFinRegistro = lFinRegistro;
	}

	public String getCPais() {
		return cPais;
	}

	public void setCPais(String cPais) {
		this.cPais = cPais;
	}

	public String getCEstado() {
		return cEstado;
	}

	public void setCEstado(String cEstado) {
		this.cEstado = cEstado;
	}

	public String getCMunicipio() {
		return cMunicipio;
	}

	public void setCMunicipio(String cMunicipio) {
		this.cMunicipio = cMunicipio;
	}

	public String getCDscTpoCausa() {
		return cDscTpoCausa;
	}

	public void setCDscTpoCausa(String cDscTpoCausa) {
		this.cDscTpoCausa = cDscTpoCausa;
	}

	public String getCDscCausa() {
		return cDscCausa;
	}

	public void setCDscCausa(String cDscCausa) {
		this.cDscCausa = cDscCausa;
	}

	public String getCDscMedInforma() {
		return cDscMedInforma;
	}

	public void setCDscMedInforma(String cDscMedInforma) {
		this.cDscMedInforma = cDscMedInforma;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscDtAsigna() {
		return cDscDtAsigna;
	}

	public void setCDscDtAsigna(String cDscDtAsigna) {
		this.cDscDtAsigna = cDscDtAsigna;
	}

	public String getCDscDtAccidente() {
		return cDscDtAccidente;
	}

	public void setCDscDtAccidente(String cDscDtAccidente) {
		this.cDscDtAccidente = cDscDtAccidente;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}
}
