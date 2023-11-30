package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPExamCat
 * </p>
 * <p>
 * Description: Value Object de la Entidad EXPExamCat
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
public class TVEXPExamCat implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveMdoTrans;
	private int iCveCategoria;
	private int iCveMotivo;
	private int lDictamen;
	private String cRefAlfanum;
	private java.sql.Date dtMovIngreso;
	private int iVigMeses;
	private java.sql.Date dtInicioVig;
	private java.sql.Date dtFinVig;
	private String cDscMdoTrans;
	private String cDscMdoTransIng;
	private String cDscCategoria;
	private String cDscCategoriaIng;
	private String cDscMotivo;
	private int lPago;
	private int iCvePuesto;
	private int iCveMdoTransInicial;
	private int iCvePuestoInicial;
	private int iCveMotivoInicial;
	private int iCveCategoriaInicial;
	private int iRefNumerica;
	private int lDitamem;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iCveCategoria);
		return pk;
	}

	public TVEXPExamCat() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("lDictamen", "" + lDictamen);
		hmfieldsTable.put("cRefAlfanum", cRefAlfanum);
		hmfieldsTable.put("dtMovIngreso", "" + dtMovIngreso);
		hmfieldsTable.put("iVigMeses", "" + iVigMeses);
		hmfieldsTable.put("dtInicioVig", "" + dtInicioVig);
		hmfieldsTable.put("dtFinVig", "" + dtFinVig);
		hmfieldsTable.put("cDscMdoTrans", cDscMdoTrans);
		hmfieldsTable.put("cDscMdoTransIng", cDscMdoTransIng);
		hmfieldsTable.put("cDscCategoria", cDscCategoria);
		hmfieldsTable.put("cDscCategoriaIng", cDscCategoriaIng);
		hmfieldsTable.put("cDscMotivo", cDscMotivo);
		hmfieldsTable.put("lPago", "" + lPago);
		hmfieldsTable.put("iRefNumerica", "" + iRefNumerica);
		hmfieldsTable.put("lDitamem", "" + lDitamem);
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

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getICveCategoria() {
		return iCveCategoria;
	}

	public void setICveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public int getLDictamen() {
		return lDictamen;
	}

	public void setLDictamen(int lDictamen) {
		this.lDictamen = lDictamen;
	}

	public String getCRefAlfanum() {
		return cRefAlfanum;
	}

	public void setCRefAlfanum(String cRefAlfanum) {
		this.cRefAlfanum = cRefAlfanum;
	}

	public java.sql.Date getDtMovIngreso() {
		return dtMovIngreso;
	}

	public void setDtMovIngreso(java.sql.Date dtMovIngreso) {
		this.dtMovIngreso = dtMovIngreso;
	}

	public int getIVigMeses() {
		return iVigMeses;
	}

	public void setIVigMeses(int iVigMeses) {
		this.iVigMeses = iVigMeses;
	}

	public java.sql.Date getDtInicioVig() {
		return dtInicioVig;
	}

	public void setDtInicioVig(java.sql.Date dtInicioVig) {
		this.dtInicioVig = dtInicioVig;
	}

	public java.sql.Date getDtFinVig() {
		return dtFinVig;
	}

	public void setDtFinVig(java.sql.Date dtFinVig) {
		this.dtFinVig = dtFinVig;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}
	
	public String getCDscMdoTransIng() {
		return cDscMdoTransIng;
	}

	public void setCDscMdoTransIng(String cDscMdoTransIng) {
		this.cDscMdoTransIng = cDscMdoTransIng;
	}	

	public String getCDscCategoria() {
		return cDscCategoria;
	}

	public void setCDscCategoria(String cDscCategoria) {
		this.cDscCategoria = cDscCategoria;
	}

	public String getCDscCategoriaIng() {
		return cDscCategoriaIng;
	}

	public void setCDscCategoriaIng(String cDscCategoriaIng) {
		this.cDscCategoriaIng = cDscCategoriaIng;
	}
	
	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public boolean equals(Object obj) {
		boolean bRet = true;
		if (obj == null)
			bRet = false;
		else if (obj instanceof TVEXPExamCat)
			bRet = getPK().equals(((TVEXPExamCat) obj).getPK());
		else
			bRet = false;
		return bRet;
	}

	public int getLPago() {
		return lPago;
	}

	public void setLPago(int lPago) {
		this.lPago = lPago;
	}

	public int getICvePuesto() {
		return iCvePuesto;
	}

	public void setICvePuesto(int iCvePuesto) {
		this.iCvePuesto = iCvePuesto;
	}

	public int getICveCategoriaInicial() {
		return iCveCategoriaInicial;
	}

	public void setICveCategoriaInicial(int iCveCategoriaInicial) {
		this.iCveCategoriaInicial = iCveCategoriaInicial;
	}

	public int getICveMdoTransInicial() {
		return iCveMdoTransInicial;
	}

	public void setICveMdoTransInicial(int iCveMdoTransInicial) {
		this.iCveMdoTransInicial = iCveMdoTransInicial;
	}

	public int getICveMotivoInicial() {
		return iCveMotivoInicial;
	}

	public void setICveMotivoInicial(int iCveMotivoInicial) {
		this.iCveMotivoInicial = iCveMotivoInicial;
	}

	public int getICvePuestoInicial() {
		return iCvePuestoInicial;
	}

	public void setICvePuestoInicial(int iCvePuestoInicial) {
		this.iCvePuestoInicial = iCvePuestoInicial;
	}

	public int getIRefNumerica() {
		return iRefNumerica;
	}

	public void setIRefNumerica(int iRefNumerica) {
		this.iRefNumerica = iRefNumerica;
	}

	public int getLDitamem() {
		return lDitamem;
	}

	public void setLDitamem(int lDitamem) {
		this.lDitamem = lDitamem;
	}
}
