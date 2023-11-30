package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object GRLServUM
 * </p>
 * <p>
 * Description: Value Object para la tabla GRLServUM
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sanchez
 * @version 1.0
 */
public class TVGRLServUM implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUniMed;
	private int iCveModulo;
	private int iCveServicio;
	private String cDscUniMed;
	private String cDscModulo;
	private String cDscServicio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		pk.add("" + iCveModulo);
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVGRLServUM() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("cDscModulo", cDscModulo);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		return hmfieldsTable;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}
}
