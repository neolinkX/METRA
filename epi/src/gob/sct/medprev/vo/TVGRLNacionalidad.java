package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLNacionalidad
 * </p>
 * <p>
 * Description: VO para GRLNacionalidad
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonal Popoca G.
 * @version 1.0
 */
public class TVGRLNacionalidad implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePais;
	private String cDscNacional;
	private String cNombre;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePais);
		return pk;
	}

	public TVGRLNacionalidad() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("cDscNacional", cDscNacional);
		hmfieldsTable.put("cNombre", cNombre);
		return hmfieldsTable;
	}

	public int getICvePais() {
		return iCvePais;
	}

	public void setICvePais(int iCvePais) {
		this.iCvePais = iCvePais;
	}

	public String getCDscNacional() {
		return cDscNacional;
	}

	public void setCDscNacional(String cDscNacional) {
		this.cDscNacional = cDscNacional;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}
}
