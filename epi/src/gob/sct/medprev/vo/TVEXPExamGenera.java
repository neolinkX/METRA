package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPExamGenera
 * </p>
 * <p>
 * Description: Value Object de la Entidad EXPExamGenera
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
public class TVEXPExamGenera implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iExamenGenera;
	private int iCveProceso;
	private int iNumExamenGenera;
	private java.sql.Date dtProgramado;
	private java.sql.Date dtPosibleAten;
	private java.sql.Date dtAplicacion;
	private int lAplicado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iExamenGenera);
		return pk;
	}

	public TVEXPExamGenera() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iExamenGenera", "" + iExamenGenera);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iNumExamenGenera", "" + iNumExamenGenera);
		hmfieldsTable.put("dtProgramado", "" + dtProgramado);
		hmfieldsTable.put("dtPosibleAten", "" + dtPosibleAten);
		hmfieldsTable.put("dtAplicacion", "" + dtAplicacion);
		hmfieldsTable.put("lAplicado", "" + lAplicado);
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

	public int getIExamenGenera() {
		return iExamenGenera;
	}

	public void setIExamenGenera(int iExamenGenera) {
		this.iExamenGenera = iExamenGenera;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getINumExamenGenera() {
		return iNumExamenGenera;
	}

	public void setINumExamenGenera(int iNumExamenGenera) {
		this.iNumExamenGenera = iNumExamenGenera;
	}

	public java.sql.Date getDtProgramado() {
		return dtProgramado;
	}

	public void setDtProgramado(java.sql.Date dtProgramado) {
		this.dtProgramado = dtProgramado;
	}

	public java.sql.Date getDtPosibleAten() {
		return dtPosibleAten;
	}

	public void setDtPosibleAten(java.sql.Date dtPosibleAten) {
		this.dtPosibleAten = dtPosibleAten;
	}

	public java.sql.Date getDtAplicacion() {
		return dtAplicacion;
	}

	public void setDtAplicacion(java.sql.Date dtAplicacion) {
		this.dtAplicacion = dtAplicacion;
	}

	public int getLAplicado() {
		return lAplicado;
	}

	public void setLAplicado(int lAplicado) {
		this.lAplicado = lAplicado;
	}
}


