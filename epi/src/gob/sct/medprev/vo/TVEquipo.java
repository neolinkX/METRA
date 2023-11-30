package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object Equipo
 * </p>
 * <p>
 * Description: EQM Equipo
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
public class TVEquipo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEquipo;
	private String cDscEquipo;
	private String cNumSerie;
	private String cModelo;
	private int iCveTpoEquipo;
	private int iCveMarca;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEquipo);
		return pk;
	}

	public TVEquipo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("cDscEquipo", cDscEquipo);
		hmfieldsTable.put("cNumSerie", cNumSerie);
		hmfieldsTable.put("cModelo", cModelo);
		hmfieldsTable.put("iCveTpoEquipo", "" + iCveTpoEquipo);
		hmfieldsTable.put("iCveMarca", "" + iCveMarca);
		return hmfieldsTable;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public String getCDscEquipo() {
		return cDscEquipo;
	}

	public void setCDscEquipo(String cDscEquipo) {
		this.cDscEquipo = cDscEquipo;
	}

	public String getCNumSerie() {
		return cNumSerie;
	}

	public void setCNumSerie(String cNumSerie) {
		this.cNumSerie = cNumSerie;
	}

	public String getCModelo() {
		return cModelo;
	}

	public void setCModelo(String cModelo) {
		this.cModelo = cModelo;
	}

	public int getICveTpoEquipo() {
		return iCveTpoEquipo;
	}

	public void setICveTpoEquipo(int iCveTpoEquipo) {
		this.iCveTpoEquipo = iCveTpoEquipo;
	}

	public int getICveMarca() {
		return iCveMarca;
	}

	public void setICveMarca(int iCveMarca) {
		this.iCveMarca = iCveMarca;
	}
}
