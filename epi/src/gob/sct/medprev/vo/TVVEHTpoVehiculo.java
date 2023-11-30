package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object VEHTpoVehiculo
 * </p>
 * <p>
 * Description: VO para la administración de la información de la tabla
 * VEHTpoVehiculo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernández García
 * @version 1.0
 */
public class TVVEHTpoVehiculo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoVehiculo;
	private String cDscTpoVehiculo;
	private String cDscBreve;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoVehiculo);
		return pk;
	}

	public TVVEHTpoVehiculo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoVehiculo", "" + iCveTpoVehiculo);
		hmfieldsTable.put("cDscTpoVehiculo", cDscTpoVehiculo);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoVehiculo() {
		return iCveTpoVehiculo;
	}

	public void setICveTpoVehiculo(int iCveTpoVehiculo) {
		this.iCveTpoVehiculo = iCveTpoVehiculo;
	}

	public String getCDscTpoVehiculo() {
		return cDscTpoVehiculo;
	}

	public void setCDscTpoVehiculo(String cDscTpoVehiculo) {
		this.cDscTpoVehiculo = cDscTpoVehiculo;
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
