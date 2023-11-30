package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPRefConcepto
 * </p>
 * <p>
 * Description: Value Object de la entidad EXPRefConcepto
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Gabriela P�rez
 * @version 1.0
 */
public class TVEXPRefConcepto implements HashBeanInterface {
	private BeanPK pk;
	private int iEjercicio;
	private int iCveConcepto;
	private int iEjercicio2;
	private int iCveConcepto2;
	private int iRefNumerica;
	private String cDscConcepto;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iEjercicio);
		pk.add("" + iCveConcepto);
		return pk;
	}

	public TVEXPRefConcepto() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iEjercicio", "" + iEjercicio);
		hmfieldsTable.put("iCveConcepto", "" + iCveConcepto);
		hmfieldsTable.put("iEjercicio2", "" + iEjercicio2);
		hmfieldsTable.put("iCveConcepto2", "" + iCveConcepto2);
		hmfieldsTable.put("iRefNumerica", "" + iRefNumerica);
		hmfieldsTable.put("cDscConcepto", cDscConcepto);
		return hmfieldsTable;
	}

	public int getICveConcepto() {
		return iCveConcepto;
	}

	public void setICveConcepto(int iCveConcepto) {
		this.iCveConcepto = iCveConcepto;
	}

	public int getICveConcepto2() {
		return iCveConcepto2;
	}

	public void setICveConcepto2(int iCveConcepto2) {
		this.iCveConcepto2 = iCveConcepto2;
	}

	public int getIEjercicio() {
		return iEjercicio;
	}

	public void setIEjercicio(int iEjercicio) {
		this.iEjercicio = iEjercicio;
	}

	public int getIEjercicio2() {
		return iEjercicio2;
	}

	public void setIEjercicio2(int iEjercicio2) {
		this.iEjercicio2 = iEjercicio2;
	}

	public int getIRefNumerica() {
		return iRefNumerica;
	}

	public void setIRefNumerica(int iRefNumerica) {
		this.iRefNumerica = iRefNumerica;
	}

	public String getCDscConcepto() {
		return cDscConcepto;
	}

	public void setCDscConcepto(String cDscConcepto) {
		this.cDscConcepto = cDscConcepto;
	}

}
