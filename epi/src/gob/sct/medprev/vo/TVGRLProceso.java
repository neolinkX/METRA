package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLProceso
 * </p>
 * <p>
 * Description: Value Object de la entidad GRLProceso
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suárez Romero
 * @version 1.0
 */
public class TVGRLProceso implements HashBeanInterface {
	private BeanPK pk;
	private int iCveProceso;
	private String cDscProceso;
	private int iRefNum;
	private int lActivo;
	private int iRefAdicional;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveProceso);
		return pk;
	}

	public TVGRLProceso() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("cDscProceso", cDscProceso);
		hmfieldsTable.put("iRefNum", "" + iRefNum);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("iRefAdicional", "" + iRefAdicional);
		return hmfieldsTable;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public int getIRefNum() {
		return iRefNum;
	}

	public void setIRefNum(int iRefNum) {
		this.iRefNum = iRefNum;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public int getIRefAdicional() {
		return iRefAdicional;
	}

	public void setIRefAdicional(int iRefAdicional) {
		this.iRefAdicional = iRefAdicional;
	}

}
