package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLAreaModulo
 * </p>
 * <p>
 * Description: VO para la tabla GRLAreaModulo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernández García
 * @version 1.0
 */
public class TVGRLAreaModulo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUniMed;
	private int iCveModulo;
	private int iCveArea;
	private String cDscArea;
	private String cResponsable;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		pk.add("" + iCveModulo);
		pk.add("" + iCveArea);
		return pk;
	}

	public TVGRLAreaModulo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("cDscArea", "" + cDscArea);
		hmfieldsTable.put("cResponsable", "" + cResponsable);
		return hmfieldsTable;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public int getICveArea() {
		return iCveArea;
	}

	public void setICveArea(int iCveArea) {
		this.iCveArea = iCveArea;
	}

	public String getCDscArea() {
		return cDscArea;
	}

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

	public String getCResponsable() {
		return cResponsable;
	}

	public void setCResponsable(String cResponsable) {
		this.cResponsable = cResponsable;
	}
}
