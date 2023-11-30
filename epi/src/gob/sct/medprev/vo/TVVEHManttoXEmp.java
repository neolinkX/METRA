package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object VEHManttoXEmp
 * </p>
 * <p>
 * Description: Value Object de Tipos de Mantenimiento por Empresa
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
public class TVVEHManttoXEmp implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpMantto;
	private String cNombreEmpMantto;
	private String cDscEmpMantto;
	private String cDscBreveEmpMantto;
	private int iCveTpoMantto;
	private String cDscTpoMantto;
	private String cDscBreveTpoMantto;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpMantto);
		pk.add("" + iCveTpoMantto);
		return pk;
	}

	public TVVEHManttoXEmp() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpMantto", "" + iCveEmpMantto);
		hmfieldsTable.put("cNombreEmpMantto", cNombreEmpMantto);
		hmfieldsTable.put("cDscEmpMantto", cDscEmpMantto);
		hmfieldsTable.put("cDscBreveEmpMantto", cDscBreveEmpMantto);
		hmfieldsTable.put("iCveTpoMantto", "" + iCveTpoMantto);
		hmfieldsTable.put("cDscTpoMantto", cDscTpoMantto);
		hmfieldsTable.put("cDscBreveTpoMantto", cDscBreveTpoMantto);
		return hmfieldsTable;
	}

	public int getICveEmpMantto() {
		return iCveEmpMantto;
	}

	public void setICveEmpMantto(int iCveEmpMantto) {
		this.iCveEmpMantto = iCveEmpMantto;
	}

	public String getCNombreEmpMantto() {
		return cNombreEmpMantto;
	}

	public void setCNombreEmpMantto(String cNombreEmpMantto) {
		this.cNombreEmpMantto = cNombreEmpMantto;
	}

	public String getCDscEmpMantto() {
		return cDscEmpMantto;
	}

	public void setCDscEmpMantto(String cDscEmpMantto) {
		this.cDscEmpMantto = cDscEmpMantto;
	}

	public String getCDscBreveEmpMantto() {
		return cDscBreveEmpMantto;
	}

	public void setCDscBreveEmpMantto(String cDscBreveEmpMantto) {
		this.cDscBreveEmpMantto = cDscBreveEmpMantto;
	}

	public int getICveTpoMantto() {
		return iCveTpoMantto;
	}

	public void setICveTpoMantto(int iCveTpoMantto) {
		this.iCveTpoMantto = iCveTpoMantto;
	}

	public String getCDscTpoMantto() {
		return cDscTpoMantto;
	}

	public void setCDscTpoMantto(String cDscTpoMantto) {
		this.cDscTpoMantto = cDscTpoMantto;
	}

	public String getCDscBreveTpoMantto() {
		return cDscBreveTpoMantto;
	}

	public void setCDscBreveTpoMantto(String cDscBreveTpoMantto) {
		this.cDscBreveTpoMantto = cDscBreveTpoMantto;
	}
}
