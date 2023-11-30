package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object INVSituacion
 * </p>
 * <p>
 * Description: Value Object de la Entidad INVSituacion
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVINVSituacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveSituacion;
	private String cDscSituacion;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveSituacion);
		return pk;
	}

	public TVINVSituacion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveSituacion", "" + iCveSituacion);
		hmfieldsTable.put("cDscSituacion", cDscSituacion);
		hmfieldsTable.put("lActivo", "" + lActivo);
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

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
