package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object PERRubroNoAp
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
public class TVPERRubroNoAp implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMotivoNoAp;
	private int iCveRubroNoAp;
	private String cDscRubroNoAp;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMotivoNoAp);
		pk.add("" + iCveRubroNoAp);
		return pk;
	}

	public TVPERRubroNoAp() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMotivoNoAp", "" + iCveMotivoNoAp);
		hmfieldsTable.put("iCveRubroNoAp", "" + iCveRubroNoAp);
		hmfieldsTable.put("cDscRubroNoAp", cDscRubroNoAp);
		return hmfieldsTable;
	}

	public int getICveMotivoNoAp() {
		return iCveMotivoNoAp;
	}

	public void setICveMotivoNoAp(int iCveMotivoNoAp) {
		this.iCveMotivoNoAp = iCveMotivoNoAp;
	}

	public int getICveRubroNoAp() {
		return iCveRubroNoAp;
	}

	public void setICveRubroNoAp(int iCveRubroNoAp) {
		this.iCveRubroNoAp = iCveRubroNoAp;
	}

	public String getcDscRubroNoAp() {
		return cDscRubroNoAp;
	}

	public void setcDscRubroNoAp(String cDscRubroNoAp) {
		this.cDscRubroNoAp = cDscRubroNoAp;
	}
}
