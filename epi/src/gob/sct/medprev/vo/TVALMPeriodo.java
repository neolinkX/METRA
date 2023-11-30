package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMPeriodo
 * </p>
 * <p>
 * Description: VO Tabla ALMPeriodo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */
public class TVALMPeriodo implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCvePeriodo;
	private String cDscPeriodo;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCvePeriodo);
		return pk;
	}

	public TVALMPeriodo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCvePeriodo", "" + iCvePeriodo);
		hmfieldsTable.put("cDscPeriodo", cDscPeriodo);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICvePeriodo() {
		return iCvePeriodo;
	}

	public void setICvePeriodo(int iCvePeriodo) {
		this.iCvePeriodo = iCvePeriodo;
	}

	public String getCDscPeriodo() {
		return cDscPeriodo;
	}

	public void setCDscPeriodo(String cDscPeriodo) {
		this.cDscPeriodo = cDscPeriodo;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
