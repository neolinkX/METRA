package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLProfesion
 * </p>
 * <p>
 * Description: VO para la administración de la información de la tabla
 * GRLProfesion
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
public class TVGRLProfesion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveProfesion;
	private String cPrefesion;
	private String cSiglas;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveProfesion);
		return pk;
	}

	public TVGRLProfesion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveProfesion", "" + iCveProfesion);
		hmfieldsTable.put("cPrefesion", cPrefesion);
		hmfieldsTable.put("cSiglas", cSiglas);
		return hmfieldsTable;
	}

	public int getICveProfesion() {
		return iCveProfesion;
	}

	public void setICveProfesion(int iCveProfesion) {
		this.iCveProfesion = iCveProfesion;
	}

	public String getcPrefesion() {
		return cPrefesion;
	}

	public void setcPrefesion(String cPrefesion) {
		this.cPrefesion = cPrefesion;
	}

	public String getCSiglas() {
		return cSiglas;
	}

	public void setCSiglas(String cSiglas) {
		this.cSiglas = cSiglas;
	}
}
