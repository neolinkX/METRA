package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object RTpoPlantilla
 * </p>
 * <p>
 * Description: Control al Transporte - Tipo de Plantilla
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
public class TVCTRTpoPlantilla implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoPlantilla;
	private String cDscTpoPlantilla;
	private String cDscBreve;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoPlantilla);
		return pk;
	}

	public TVCTRTpoPlantilla() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoPlantilla", "" + iCveTpoPlantilla);
		hmfieldsTable.put("cDscTpoPlantilla", cDscTpoPlantilla);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoPlantilla() {
		return iCveTpoPlantilla;
	}

	public void setICveTpoPlantilla(int iCveTpoPlantilla) {
		this.iCveTpoPlantilla = iCveTpoPlantilla;
	}

	public String getCDscTpoPlantilla() {
		return cDscTpoPlantilla;
	}

	public void setCDscTpoPlantilla(String cDscTpoPlantilla) {
		this.cDscTpoPlantilla = cDscTpoPlantilla;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
