package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object PERMotivoNoAp
 * </p>
 * <p>
 * Description: Motivos de no aptitud
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
public class TVPERMotivoNoAp implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMotivoNoAp;
	private String cDscMotivoNoAp;
	private int iPeriodo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMotivoNoAp);
		return pk;
	}

	public TVPERMotivoNoAp() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMotivoNoAp", "" + iCveMotivoNoAp);
		hmfieldsTable.put("cDscMotivoNoAp", cDscMotivoNoAp);
		hmfieldsTable.put("iPeriodo", "" + iPeriodo);
		return hmfieldsTable;
	}

	public int getICveMotivoNoAp() {
		return iCveMotivoNoAp;
	}

	public void setICveMotivoNoAp(int iCveMotivoNoAp) {
		this.iCveMotivoNoAp = iCveMotivoNoAp;
	}

	public String getCDscMotivoNoAp() {
		return cDscMotivoNoAp;
	}

	public void setCDscMotivoNoAp(String cDscMotivoNoAp) {
		this.cDscMotivoNoAp = cDscMotivoNoAp;
	}

	public int getIPeriodo() {
		return iPeriodo;
	}

	public void setIPeriodo(int iPeriodo) {
		this.iPeriodo = iPeriodo;
	}
}
