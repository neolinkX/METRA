package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object CTRUMPeriodo
 * </p>
 * <p>
 * Description: Value Object de la Tabla CTRUMPeriodo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrejón Adame.
 * @version 1.0
 */
public class TVCTRUMPeriodo implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCvePeriodPla;
	private int iCveUniMed;
	private int iCveMdoTrans;
	private java.sql.Date dtGeneradas;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCvePeriodPla);
		pk.add("" + iCveUniMed);
		pk.add("" + iCveMdoTrans);
		return pk;
	}

	public TVCTRUMPeriodo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCvePeriodPla", "" + iCvePeriodPla);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("dtGeneradas", "" + dtGeneradas);
		return hmfieldsTable;
	}

	public int getiAnio() {
		return iAnio;
	}

	public void setiAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getiCvePeriodPla() {
		return iCvePeriodPla;
	}

	public void setiCvePeriodPla(int iCvePeriodPla) {
		this.iCvePeriodPla = iCvePeriodPla;
	}

	public int getiCveUniMed() {
		return iCveUniMed;
	}

	public void setiCveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public java.sql.Date getdtGeneradas() {
		return dtGeneradas;
	}

	public void setdtGeneradas(java.sql.Date dtGeneradas) {
		this.dtGeneradas = dtGeneradas;
	}

	public int getiCveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setiCveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}
}
