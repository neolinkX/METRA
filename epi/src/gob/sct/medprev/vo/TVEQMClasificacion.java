package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EQMClasificacion
 * </p>
 * <p>
 * Description: Calibración de Equipo - Clasificación
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
public class TVEQMClasificacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveClasificacion;
	private String cDscClasificacion;
	private String cDscBreve;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveClasificacion);
		return pk;
	}

	public TVEQMClasificacion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveClasificacion", "" + iCveClasificacion);
		hmfieldsTable.put("cDscClasificacion", cDscClasificacion);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveClasificacion() {
		return iCveClasificacion;
	}

	public void setICveClasificacion(int iCveClasificacion) {
		this.iCveClasificacion = iCveClasificacion;
	}

	public String getCDscClasificacion() {
		return cDscClasificacion;
	}

	public void setCDscClasificacion(String cDscClasificacion) {
		this.cDscClasificacion = cDscClasificacion;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
