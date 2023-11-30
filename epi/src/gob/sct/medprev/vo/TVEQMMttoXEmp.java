package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EQMMttoXEmp
 * </p>
 * <p>
 * Description: Calibración de Equipo - Tipos de Mantenimiento por Empresa
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
public class TVEQMMttoXEmp implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpMtto;
	private int iCveTpoMantto;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpMtto);
		pk.add("" + iCveTpoMantto);
		return pk;
	}

	public TVEQMMttoXEmp() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpMtto", "" + iCveEmpMtto);
		hmfieldsTable.put("iCveTpoMantto", "" + iCveTpoMantto);
		return hmfieldsTable;
	}

	public int getICveEmpMtto() {
		return iCveEmpMtto;
	}

	public void setICveEmpMtto(int iCveEmpMtto) {
		this.iCveEmpMtto = iCveEmpMtto;
	}

	public int getICveTpoMantto() {
		return iCveTpoMantto;
	}

	public void setICveTpoMantto(int iCveTpoMantto) {
		this.iCveTpoMantto = iCveTpoMantto;
	}
}
