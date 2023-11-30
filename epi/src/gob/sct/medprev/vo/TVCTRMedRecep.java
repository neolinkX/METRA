package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object CTRMedRecep
 * </p>
 * <p>
 * Description: Control al Transporte - Medio de Recepción
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
public class TVCTRMedRecep implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMedRecep;
	private String cDscMedRecep;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMedRecep);
		return pk;
	}

	public TVCTRMedRecep() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMedRecep", "" + iCveMedRecep);
		hmfieldsTable.put("cDscMedRecep", cDscMedRecep);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveMedRecep() {
		return iCveMedRecep;
	}

	public void setICveMedRecep(int iCveMedRecep) {
		this.iCveMedRecep = iCveMedRecep;
	}

	public String getCDscMedRecep() {
		return cDscMedRecep;
	}

	public void setCDscMedRecep(String cDscMedRecep) {
		this.cDscMedRecep = cDscMedRecep;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
