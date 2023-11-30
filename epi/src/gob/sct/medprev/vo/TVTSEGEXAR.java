package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TVTSEGEXAR
 * </p>
 * <p>
 * Description: Value Object de la Entidad TVTSEGEXAR
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author AG SA SANDOVAL
 * @version 1.0
 */
public class TVTSEGEXAR implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveUniMed;
	private int iCveProceso;

	private int iCveModulo;
	private java.sql.Date dtSolicitado;
	private java.sql.Date dtDictamen;
	private int iCveUsuDictamen;
	private int lDictaminado;
	private int NumServC;
	private int Estatus;
	private String cDscServicio;

	private Vector vcEXPExamCat;

	private int lDictamen;
	private int iGenerados;
	private int iDictaminados;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		return pk;
	}

	public TVTSEGEXAR() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);

		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("dtSolicitado", "" + dtSolicitado);
		hmfieldsTable.put("dtDictamen", "" + dtDictamen);
		hmfieldsTable.put("iCveUsuDictamen", "" + iCveUsuDictamen);
		hmfieldsTable.put("lDictamen", "" + lDictamen);
		hmfieldsTable.put("NumServC", "" + NumServC);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("Estatus", "" + Estatus);

		hmfieldsTable.put("vcEXPExamCat", vcEXPExamCat);
		hmfieldsTable.put("lDictamen", "" + lDictamen);

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

	public java.sql.Date getDtDictamen() {
		return dtDictamen;
	}

	public void setDtDictamen(java.sql.Date dtDictamen) {
		this.dtDictamen = dtDictamen;
	}

	public int getLDictamen() {
		return lDictamen;
	}

	public void setLDictamen(int lDictamen) {
		this.lDictamen = lDictamen;
	}

	public int getNumServC() {
		return NumServC;
	}

	public void setNumServC(int NumServC) {
		this.NumServC = NumServC;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public int getEstatus() {
		return Estatus;
	}

	public void setEstatus(int Estatus) {
		this.Estatus = Estatus;
	}

	public int getICveUsuDictamen() {
		return iCveUsuDictamen;
	}

	public void setiCveUsuDictamen(int iCveUsuDictamen) {
		this.iCveUsuDictamen = iCveUsuDictamen;
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

	public int getIGenerados() {
		return iGenerados;
	}

	public void setIGenerados(int iGenerados) {
		this.iGenerados = iGenerados;
	}

	public int getIDictaminados() {
		return iDictaminados;
	}

	public void setIDictaminados(int iDictaminados) {
		this.iDictaminados = iDictaminados;
	}

	public void setICveUsuDictamen(int iCveUsuDictamen) {
		this.iCveUsuDictamen = iCveUsuDictamen;
	}

}