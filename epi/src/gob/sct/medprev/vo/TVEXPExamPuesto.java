package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPExamPuesto
 * </p>
 * <p>
 * Description: Value Object de la Entidad EXPExamPuesto
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
public class TVEXPExamPuesto implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveMdoTrans;
	private int iCvePuesto;
	private String cDscMdoTrans;
	private String cDscPuesto;
	private String cDscCategoria;
	private String cDscGrupo;
	private int iCvePerfil;

	private int iCveCategoria;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iCvePuesto);
		return pk;
	}

	public TVEXPExamPuesto() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCvePuesto", "" + iCvePuesto);
		hmfieldsTable.put("cDscMdoTrans", "" + cDscMdoTrans);
		hmfieldsTable.put("cDscPuesto", "" + cDscPuesto);
		hmfieldsTable.put("cDscCategoria", "" + cDscCategoria);
		hmfieldsTable.put("cDscGrupo", "" + cDscGrupo);
		hmfieldsTable.put("iCvePerfil", "" + iCvePerfil);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);

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

	public int getICvePuesto() {
		return iCvePuesto;
	}

	public void setICvePuesto(int iCvePuesto) {
		this.iCvePuesto = iCvePuesto;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public String getCDscPuesto() {
		return cDscPuesto;
	}

	public void setCDscPuesto(String cDscPuesto) {
		this.cDscPuesto = cDscPuesto;
	}

	public String getCDscCategoria() {
		return cDscCategoria;
	}

	public void setCDscCategoria(String cDscCategoria) {
		this.cDscCategoria = cDscCategoria;
	}

	public String getCDscGrupo() {
		return cDscGrupo;
	}

	public void setCDscGrupo(String cDscGrupo) {
		this.cDscGrupo = cDscGrupo;
	}

	public int getICvePerfil() {
		return iCvePerfil;
	}

	public void setICvePerfil(int iCvePerfil) {
		this.iCvePerfil = iCvePerfil;
	}

	public int getICveCategoria() {
		return iCveCategoria;
	}

	public void setICveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

}