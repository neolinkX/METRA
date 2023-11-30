package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLProcesoUM
 * </p>
 * <p>
 * Description: VO para GRLProcesoUM
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
public class TVGRLProcesoUM implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUniMed;
	private int iCveProceso;
	private int lInterconsulta;
	private String cDscUniMed;
	private String cDscProceso;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		pk.add("" + iCveProceso);
		return pk;
	}

	public TVGRLProcesoUM() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("lInterconsulta", "" + lInterconsulta);
		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cDscProceso", "" + cDscProceso);
		return hmfieldsTable;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getLInterconsulta() {
		return lInterconsulta;
	}

	public void setLInterconsulta(int lInterconsulta) {
		this.lInterconsulta = lInterconsulta;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

}
