package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object MEDConsulta
 * </p>
 * <p>
 * Description: VO Tabla MEDConsulta
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
public class TVMEDConsulta implements HashBeanInterface {
	private BeanPK pk;
	private int iCveServicio;
	private int iCveServCons;
	private String cDscServCons;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveServicio);
		pk.add("" + iCveServCons);
		return pk;
	}

	public TVMEDConsulta() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveServCons", "" + iCveServCons);
		hmfieldsTable.put("cDscServCons", cDscServCons);
		return hmfieldsTable;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getICveServCons() {
		return iCveServCons;
	}

	public void setICveServCons(int iCveServCons) {
		this.iCveServCons = iCveServCons;
	}

	public String getCDscServCons() {
		return cDscServCons;
	}

	public void setCDscServCons(String cDscServCons) {
		this.cDscServCons = cDscServCons;
	}
}
