package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EQMMarca
 * </p>
 * <p>
 * Description: Calibraci�n de Equipo - Marca
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
public class TVEQMMarca implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMarca;
	private String cDscMarca;
	private String cDscBreve;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMarca);
		return pk;
	}

	public TVEQMMarca() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMarca", "" + iCveMarca);
		hmfieldsTable.put("cDscMarca", cDscMarca);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveMarca() {
		return iCveMarca;
	}

	public void setICveMarca(int iCveMarca) {
		this.iCveMarca = iCveMarca;
	}

	public String getCDscMarca() {
		return cDscMarca;
	}

	public void setCDscMarca(String cDscMarca) {
		this.cDscMarca = cDscMarca;
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
