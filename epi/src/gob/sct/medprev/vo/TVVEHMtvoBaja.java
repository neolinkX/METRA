package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object VEHMtvoBaja
 * </p>
 * <p>
 * Description: Value Object de Motivos de Baja
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
public class TVVEHMtvoBaja implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMtvoBaja;
	private String cDscMtvoBaja;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMtvoBaja);
		return pk;
	}

	public TVVEHMtvoBaja() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMtvoBaja", "" + iCveMtvoBaja);
		hmfieldsTable.put("cDscMtvoBaja", cDscMtvoBaja);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveMtvoBaja() {
		return iCveMtvoBaja;
	}

	public void setICveMtvoBaja(int iCveMtvoBaja) {
		this.iCveMtvoBaja = iCveMtvoBaja;
	}

	public String getCDscMtvoBaja() {
		return cDscMtvoBaja;
	}

	public void setCDscMtvoBaja(String cDscMtvoBaja) {
		this.cDscMtvoBaja = cDscMtvoBaja;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
