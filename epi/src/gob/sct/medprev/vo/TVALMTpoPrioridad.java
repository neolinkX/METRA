package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMTpoPrioridad
 * </p>
 * <p>
 * Description: VO Tabla ALMTpoPrioridad
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
public class TVALMTpoPrioridad implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePrioridad;
	private String cDscPrioridad;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePrioridad);
		return pk;
	}

	public TVALMTpoPrioridad() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePrioridad", "" + iCvePrioridad);
		hmfieldsTable.put("cDscPrioridad", cDscPrioridad);
		return hmfieldsTable;
	}

	public int getICvePrioridad() {
		return iCvePrioridad;
	}

	public void setICvePrioridad(int iCvePrioridad) {
		this.iCvePrioridad = iCvePrioridad;
	}

	public String getCDscPrioridad() {
		return cDscPrioridad;
	}

	public void setCDscPrioridad(String cDscPrioridad) {
		this.cDscPrioridad = cDscPrioridad;
	}
}
