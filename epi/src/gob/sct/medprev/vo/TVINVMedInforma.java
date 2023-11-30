package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object INVMedInforma
 * </p>
 * <p>
 * Description: Value Object de la Entidad INVMedInforma
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVINVMedInforma implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMedInforma;
	private String cDscMedInforma;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMedInforma);
		return pk;
	}

	public TVINVMedInforma() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMedInforma", "" + iCveMedInforma);
		hmfieldsTable.put("cDscMedInforma", cDscMedInforma);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveMedInforma() {
		return iCveMedInforma;
	}

	public void setICveMedInforma(int iCveMedInforma) {
		this.iCveMedInforma = iCveMedInforma;
	}

	public String getCDscMedInforma() {
		return cDscMedInforma;
	}

	public void setCDscMedInforma(String cDscMedInforma) {
		this.cDscMedInforma = cDscMedInforma;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
