package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLMetaProceso
 * </p>
 * <p>
 * Description: VO para la tabla GRLMetaProceso
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
public class TVGRLMetaProceso implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveMeta;
	private String cDscMeta;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMeta);
		return pk;
	}

	public TVGRLMetaProceso() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveMeta", "" + iCveMeta);
		hmfieldsTable.put("cDscMeta", cDscMeta);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveMeta() {
		return iCveMeta;
	}

	public void setICveMeta(int iCveMeta) {
		this.iCveMeta = iCveMeta;
	}

	public String getCDscMeta() {
		return cDscMeta;
	}

	public void setCDscMeta(String cDscMeta) {
		this.cDscMeta = cDscMeta;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
