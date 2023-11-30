package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMTpoMovimiento
 * </p>
 * <p>
 * Description: TV de ALMTpoMovimiento
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
public class TVALMTpoMovimiento implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoMovimiento;
	private String cDscTpoMovimiento;
	private String cDscBreve;
	private int lSuma;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoMovimiento);
		return pk;
	}

	public TVALMTpoMovimiento() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoMovimiento", "" + iCveTpoMovimiento);
		hmfieldsTable.put("cDscTpoMovimiento", cDscTpoMovimiento);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("lSuma", "" + lSuma);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoMovimiento() {
		return iCveTpoMovimiento;
	}

	public void setICveTpoMovimiento(int iCveTpoMovimiento) {
		this.iCveTpoMovimiento = iCveTpoMovimiento;
	}

	public String getCDscTpoMovimiento() {
		return cDscTpoMovimiento;
	}

	public void setCDscTpoMovimiento(String cDscTpoMovimiento) {
		this.cDscTpoMovimiento = cDscTpoMovimiento;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getLSuma() {
		return lSuma;
	}

	public void setLSuma(int lSuma) {
		this.lSuma = lSuma;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
