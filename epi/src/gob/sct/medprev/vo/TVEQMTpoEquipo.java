package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EQMTpoEquipo
 * </p>
 * <p>
 * Description: Calibración de Equipo - Tipos de Equipo
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
public class TVEQMTpoEquipo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveClasificacion;
	private int iCveTpoEquipo;
	private String cDscTpoEquipo;
	private String cDscBreve;
	private String cCABMS;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveClasificacion);
		pk.add("" + iCveTpoEquipo);
		return pk;
	}

	public TVEQMTpoEquipo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveClasificacion", "" + iCveClasificacion);
		hmfieldsTable.put("iCveTpoEquipo", "" + iCveTpoEquipo);
		hmfieldsTable.put("cDscTpoEquipo", cDscTpoEquipo);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cCABMS", cCABMS);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveClasificacion() {
		return iCveClasificacion;
	}

	public void setICveClasificacion(int iCveClasificacion) {
		this.iCveClasificacion = iCveClasificacion;
	}

	public int getICveTpoEquipo() {
		return iCveTpoEquipo;
	}

	public void setICveTpoEquipo(int iCveTpoEquipo) {
		this.iCveTpoEquipo = iCveTpoEquipo;
	}

	public String getCDscTpoEquipo() {
		return cDscTpoEquipo;
	}

	public void setCDscTpoEquipo(String cDscTpoEquipo) {
		this.cDscTpoEquipo = cDscTpoEquipo;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCCABMS() {
		return cCABMS;
	}

	public void setCCABMS(String cCABMS) {
		this.cCABMS = cCABMS;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
