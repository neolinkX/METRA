package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object Proceso
 * </p>
 * <p>
 * Description: Datos Tabla GRLProceso
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. González Paz
 * @version 1.0
 */
public class TVProceso implements HashBeanInterface {
	private BeanPK pk;
	private int iCveProceso;
	private String cDscProceso;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveProceso);
		return pk;
	}

	public TVProceso() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("cDscProceso", cDscProceso);
		return hmfieldsTable;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}
}
