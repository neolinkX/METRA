package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object CTROrigenInf
 * </p>
 * <p>
 * Description: Control al Transporte - Origen de la Información
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
public class TVCTROrigenInf implements HashBeanInterface {
	private BeanPK pk;
	private int iCveOrigenInf;
	private String cDscOrigenInf;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveOrigenInf);
		return pk;
	}

	public TVCTROrigenInf() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveOrigenInf", "" + iCveOrigenInf);
		hmfieldsTable.put("cDscOrigenInf", cDscOrigenInf);
		return hmfieldsTable;
	}

	public int getICveOrigenInf() {
		return iCveOrigenInf;
	}

	public void setICveOrigenInf(int iCveOrigenInf) {
		this.iCveOrigenInf = iCveOrigenInf;
	}

	public String getCDscOrigenInf() {
		return cDscOrigenInf;
	}

	public void setCDscOrigenInf(String cDscOrigenInf) {
		this.cDscOrigenInf = cDscOrigenInf;
	}
}
