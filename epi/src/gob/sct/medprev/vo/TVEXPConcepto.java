package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPConcepto
 * </p>
 * <p>
 * Description: Value Object de la entidad EXPConcepto
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Gabriela Pérez
 * @version 1.0
 */
public class TVEXPConcepto implements HashBeanInterface {
	private BeanPK pk;
	private int iCveConcepto;
	private String cDscConcepto;
	private int lAdicional;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveConcepto);
		return pk;
	}

	public TVEXPConcepto() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveConcepto", "" + iCveConcepto);
		hmfieldsTable.put("cDscConcepto", cDscConcepto);
		hmfieldsTable.put("lAdicional", "" + lAdicional);
		return hmfieldsTable;
	}

	public int getICveConcepto() {
		return iCveConcepto;
	}

	public void setICveConcepto(int iCveConcepto) {
		this.iCveConcepto = iCveConcepto;
	}

	public String getCDscConcepto() {
		return cDscConcepto;
	}

	public void setCDscConcepto(String cDscConcepto) {
		this.cDscConcepto = cDscConcepto;
	}

	public int getLAdicional() {
		return lAdicional;
	}

	public void setLAdicional(int lAdicional) {
		this.lAdicional = lAdicional;
	}

}
