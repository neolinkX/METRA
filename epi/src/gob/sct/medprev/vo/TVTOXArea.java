package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXArea
 * </p>
 * <p>
 * Description: VO de la Tabla TOXArea
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
public class TVTOXArea implements HashBeanInterface {
	private BeanPK pk;
	private int iCveArea;
	private String cDscArea;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveArea);
		return pk;
	}

	public TVTOXArea() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("cDscArea", cDscArea);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
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

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
