package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object PERHuella
 * </p>
 * <p>
 * Description: VO de la entidad PERHuella
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
public class TVPERHuella implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePersonal;
	private int iCveHuella;
	private java.sql.Date dtCapturada;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePersonal);
		pk.add("" + iCveHuella);
		return pk;
	}

	public TVPERHuella() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iCveHuella", "" + iCveHuella);
		hmfieldsTable.put("dtCapturada", "" + dtCapturada);
		return hmfieldsTable;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getICveHuella() {
		return iCveHuella;
	}

	public void setICveHuella(int iCveHuella) {
		this.iCveHuella = iCveHuella;
	}

	public java.sql.Date getDtCapturada() {
		return dtCapturada;
	}

	public void setDtCapturada(java.sql.Date dtCapturada) {
		this.dtCapturada = dtCapturada;
	}
}
