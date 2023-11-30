package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMConcepto
 * </p>
 * <p>
 * Description: VO de ALMConcepto
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Juan Manuel Vazquez
 * @version 1.0
 */
public class TVALMConcepto implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoMovimiento;
	private int iCveConcepto;
	private String cDscConcepto;
	private int lActivo;
	private String cDscMovimiento;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoMovimiento);
		pk.add("" + iCveConcepto);
		return pk;
	}

	public TVALMConcepto() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoMovimiento", "" + iCveTpoMovimiento);
		hmfieldsTable.put("iCveConcepto", "" + iCveConcepto);
		hmfieldsTable.put("cDscConcepto", cDscConcepto);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscMovimiento", "" + cDscMovimiento);
		return hmfieldsTable;
	}

	public int getICveTpoMovimiento() {
		return iCveTpoMovimiento;
	}

	public void setICveTpoMovimiento(int iCveTpoMovimiento) {
		this.iCveTpoMovimiento = iCveTpoMovimiento;
	}

	public int getICveConcepto() {
		return iCveConcepto;
	}

	public void setICveConcepto(int iCveConcepto) {
		this.iCveConcepto = iCveConcepto;
	}

	public String getCDscConcepto() {
		return cDscConcepto;
	}

	public void setCDscConcepto(String cDscConcepto) {
		this.cDscConcepto = cDscConcepto;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getCDscMovimiento() {
		return cDscMovimiento;
	}

	public void setCDscMovimiento(String cDscMovimiento) {
		this.cDscMovimiento = cDscMovimiento;
	}
}
