package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPDiagnostico
 * </p>
 * <p>
 * Description: Value Object para EXPDiagnostico
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
public class TVEXPDiagnostico implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveServicio;
	private int iCveEspecialidad;
	private int iCveDiagnostico;
	private int iCvePerfil;
	private int iCveMdoTrans;
	private int iCveGrupo;
	private String cCIE;
	private String cDscDiagnostico;
	private String cDscGrupo;
	private int lAlarma;
	private String cDscEspecialidad;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveServicio);
		pk.add("" + iCveEspecialidad);
		pk.add("" + iCveDiagnostico);
		return pk;
	}

	public TVEXPDiagnostico() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveEspecialidad", "" + iCveEspecialidad);
		hmfieldsTable.put("iCveDiagnostico", "" + iCveDiagnostico);
		hmfieldsTable.put("iCvePerfil", "" + iCvePerfil);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveGrupo", "" + iCveGrupo);
		hmfieldsTable.put("cCIE", cCIE);
		hmfieldsTable.put("cDscDiagnostico", cDscDiagnostico);
		hmfieldsTable.put("cDscGrupo", cDscGrupo);
		hmfieldsTable.put("lAlarma", "" + lAlarma);
		hmfieldsTable.put("cDscEspecialidad", cDscEspecialidad);
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

	public int getICveEspecialidad() {
		return iCveEspecialidad;
	}

	public void setICveEspecialidad(int iCveEspecialidad) {
		this.iCveEspecialidad = iCveEspecialidad;
	}

	public int getICveDiagnostico() {
		return iCveDiagnostico;
	}

	public void setICveDiagnostico(int iCveDiagnostico) {
		this.iCveDiagnostico = iCveDiagnostico;
	}

	public int getICvePerfil() {
		return iCvePerfil;
	}

	public void setICvePerfil(int iCvePerfil) {
		this.iCvePerfil = iCvePerfil;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getICveGrupo() {
		return iCveGrupo;
	}

	public void setICveGrupo(int iCveGrupo) {
		this.iCveGrupo = iCveGrupo;
	}

	public String getCCIE() {
		return cCIE;
	}

	public void setCCIE(String cCIE) {
		this.cCIE = cCIE;
	}

	public String getCDscDiagnostico() {
		return cDscDiagnostico;
	}

	public void setCDscDiagnostico(String cDscDiagnostico) {
		this.cDscDiagnostico = cDscDiagnostico;
	}

	public String getCDscGrupo() {
		return cDscGrupo;
	}

	public void setCDscGrupo(String cDscGrupo) {
		this.cDscGrupo = cDscGrupo;
	}

	public int getLAlarma() {
		return lAlarma;
	}

	public void setLAlarma(int lAlarma) {
		this.lAlarma = lAlarma;
	}

	public String getCDscEspecialidad() {
		return cDscEspecialidad;
	}

	public void setCDscEspecialidad(String cDscEspecialidad) {
		this.cDscEspecialidad = cDscEspecialidad;
	}

}
