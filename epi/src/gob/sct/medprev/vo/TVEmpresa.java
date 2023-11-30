package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object Empresa
 * </p>
 * <p>
 * Description: VO Tabla GRLEmpresa
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
public class TVEmpresa implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpresa;
	private String cDscEmpresa;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpresa);
		return pk;
	}

	public TVEmpresa() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("cDscEmpresa", cDscEmpresa);
		return hmfieldsTable;
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public String getCDscEmpresa() {
		return cDscEmpresa;
	}

	public void setCDscEmpresa(String cDscEmpresa) {
		this.cDscEmpresa = cDscEmpresa;
	}
}
