package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object AreaAlmacen
 * </p>
 * <p>
 * Description: VO para la tabla ALMAreaAlmacen
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
public class TVALMAreaAlmacen implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUniMed;
	private int iCveModulo;
	private int iCveArea;
	private int iCveAlmacen;
	private String cDscAlmacen;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		pk.add("" + iCveModulo);
		pk.add("" + iCveArea);
		pk.add("" + iCveAlmacen);
		return pk;
	}

	public TVALMAreaAlmacen() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("cDscAlmacen", "" + cDscAlmacen);
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

	public int getICveAlmacen() {
		return iCveAlmacen;
	}

	public void setICveAlmacen(int iCveAlmacen) {
		this.iCveAlmacen = iCveAlmacen;
	}

	public String getCDscAlmacen() {
		return cDscAlmacen;
	}

	public void setCDscAlmacen(String cDscAlmacen) {
		this.cDscAlmacen = cDscAlmacen;
	}
}
