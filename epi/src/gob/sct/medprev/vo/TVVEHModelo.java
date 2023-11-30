package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object VEHModelo
 * </p>
 * <p>
 * Description: Value Object de Modelos de Vehículos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 */
public class TVVEHModelo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMarca;
	private int iCveModelo;
	private String cDscMarca;
	private String cDscModelo;
	private String cDscBreve;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMarca);
		pk.add("" + iCveModelo);
		return pk;
	}

	public TVVEHModelo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMarca", "" + iCveMarca);
		hmfieldsTable.put("iCveModelo", "" + iCveModelo);
		hmfieldsTable.put("cDscMarca", cDscMarca);
		hmfieldsTable.put("cDscModelo", cDscModelo);
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

	public int getICveModelo() {
		return iCveModelo;
	}

	public void setICveModelo(int iCveModelo) {
		this.iCveModelo = iCveModelo;
	}

	public String getCDscMarca() {
		return cDscMarca;
	}

	public void setCDscMarca(String cDscMarca) {
		this.cDscMarca = cDscMarca;
	}

	public String getCDscModelo() {
		return cDscModelo;
	}

	public void setCDscModelo(String cDscModelo) {
		this.cDscModelo = cDscModelo;
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