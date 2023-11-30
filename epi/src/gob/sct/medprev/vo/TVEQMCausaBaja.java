package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EQMCausaBaja
 * </p>
 * <p>
 * Description: Calibración de Equipo - Causa de Baja
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
public class TVEQMCausaBaja implements HashBeanInterface {
	private BeanPK pk;
	private int iCveCausaBaja;
	private String cDscCausaBaja;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveCausaBaja);
		return pk;
	}

	public TVEQMCausaBaja() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveCausaBaja", "" + iCveCausaBaja);
		hmfieldsTable.put("cDscCausaBaja", cDscCausaBaja);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveCausaBaja() {
		return iCveCausaBaja;
	}

	public void setICveCausaBaja(int iCveCausaBaja) {
		this.iCveCausaBaja = iCveCausaBaja;
	}

	public String getCDscCausaBaja() {
		return cDscCausaBaja;
	}

	public void setCDscCausaBaja(String cDscCausaBaja) {
		this.cDscCausaBaja = cDscCausaBaja;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
