package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object Situacion
 * </p>
 * <p>
 * Description: Value Object para la tabla TOXSituacion
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

public class TVSituacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveSituacion;
	private String cDscSituacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveSituacion);
		return pk;
	}

	public TVSituacion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveSituacion", "" + iCveSituacion);
		hmfieldsTable.put("cDscSituacion", cDscSituacion);
		return hmfieldsTable;
	}

	public int getICveSituacion() {
		return iCveSituacion;
	}

	public void setICveSituacion(int iCveSituacion) {
		this.iCveSituacion = iCveSituacion;
	}

	public String getCDscSituacion() {
		return cDscSituacion;
	}

	public void setCDscSituacion(String cDscSituacion) {
		this.cDscSituacion = cDscSituacion;
	}
}
