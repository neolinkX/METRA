package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object VEHColor
 * </p>
 * <p>
 * Description: ValueObject de Color de Vehículos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 */
public class TVVEHColor implements HashBeanInterface {
	private BeanPK pk;
	private int iCveColor;
	private String cDscColor;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveColor);
		return pk;
	}

	public TVVEHColor() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveColor", "" + iCveColor);
		hmfieldsTable.put("cDscColor", cDscColor);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveColor() {
		return iCveColor;
	}

	public void setICveColor(int iCveColor) {
		this.iCveColor = iCveColor;
	}

	public String getCDscColor() {
		return cDscColor;
	}

	public void setCDscColor(String cDscColor) {
		this.cDscColor = cDscColor;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
