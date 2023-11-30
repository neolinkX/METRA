package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object CTRTpoEntrega
 * </p>
 * <p>
 * Description: Control al Transporte - Tipo de Entrega
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
public class TVCTRTpoEntrega implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoEntrega;
	private String cDscTpoEntrega;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoEntrega);
		return pk;
	}

	public TVCTRTpoEntrega() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoEntrega", "" + iCveTpoEntrega);
		hmfieldsTable.put("cDscTpoEntrega", cDscTpoEntrega);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoEntrega() {
		return iCveTpoEntrega;
	}

	public void setICveTpoEntrega(int iCveTpoEntrega) {
		this.iCveTpoEntrega = iCveTpoEntrega;
	}

	public String getCDscTpoEntrega() {
		return cDscTpoEntrega;
	}

	public void setCDscTpoEntrega(String cDscTpoEntrega) {
		this.cDscTpoEntrega = cDscTpoEntrega;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
