package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object VEHTpoMantto
 * </p>
 * <p>
 * Description: Value Object de Tipos de Mantenimiento de VehÝculos
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
public class TVVEHTpoMantto implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoMantto;
	private String cDscTpoMantto;
	private String cDscBreve;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoMantto);
		return pk;
	}

	public TVVEHTpoMantto() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoMantto", "" + iCveTpoMantto);
		hmfieldsTable.put("cDscTpoMantto", cDscTpoMantto);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoMantto() {
		return iCveTpoMantto;
	}

	public void setICveTpoMantto(int iCveTpoMantto) {
		this.iCveTpoMantto = iCveTpoMantto;
	}

	public String getCDscTpoMantto() {
		return cDscTpoMantto;
	}

	public void setCDscTpoMantto(String cDscTpoMantto) {
		this.cDscTpoMantto = cDscTpoMantto;
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
