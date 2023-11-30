package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object EPISituacion
 * </p>
 * <p>
 * Description: VO Tabla EPISituacion
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
public class TVEPISituacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveSituacion;
	private String cDscSituacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveSituacion);
		return pk;
	}

	public TVEPISituacion() {
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
