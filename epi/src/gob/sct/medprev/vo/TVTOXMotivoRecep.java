package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object MotivoRecep
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */
public class TVTOXMotivoRecep implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMotRecep;
	private int iCveTipoRecep;
	private String cDscMotRecep;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMotRecep);
		return pk;
	}

	public TVTOXMotivoRecep() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMotRecep", "" + iCveMotRecep);
		hmfieldsTable.put("iCveTipoRecep", "" + iCveTipoRecep);
		hmfieldsTable.put("cDscMotRecep", cDscMotRecep);
		return hmfieldsTable;
	}

	public int getICveMotRecep() {
		return iCveMotRecep;
	}

	public void setICveMotRecep(int iCveMotRecep) {
		this.iCveMotRecep = iCveMotRecep;
	}

	public int getICveTipoRecep() {
		return iCveTipoRecep;
	}

	public void setICveTipoRecep(int iCveTipoRecep) {
		this.iCveTipoRecep = iCveTipoRecep;
	}

	public String getCDscMotRecep() {
		return cDscMotRecep;
	}

	public void setCDscMotRecep(String cDscMotRecep) {
		this.cDscMotRecep = cDscMotRecep;
	}
}
