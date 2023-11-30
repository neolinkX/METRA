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
public class TVDICTAMEN implements HashBeanInterface {
	private BeanPK pk;

	private java.sql.Date dtSolicitado;
	private String cDscProceso;
	private int iCveProceso;
	private String cDscMotivo;
	private int lDictamen;

	private int iCveExpediente;
	private int iNumExamen;
	private int iCveMdoTrans;
	private int iCveCategoria;
	private int iCveMotivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iCveCategoria);
		return pk;
	}

	public TVDICTAMEN() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
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

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public java.sql.Date getDTSolicitado() {
		return dtSolicitado;
	}

	public void setDTSolicitado(java.sql.Date dtSolicitado) {
		this.dtSolicitado = dtSolicitado;
	}

	public String getCDSProceso() {
		return cDscProceso;
	}

	public void setCDSProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
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

}
