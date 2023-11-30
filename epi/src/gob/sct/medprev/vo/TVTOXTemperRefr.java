package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXTemperRefr
 * </p>
 * <p>
 * Description: VO de la tabla TOXTemperRefr
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
public class TVTOXTemperRefr implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveRefrigerador;
	private int iCveTurnoRef;
	private float dTemperatura;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveRefrigerador);
		pk.add("" + iCveTurnoRef);
		return pk;
	}

	public TVTOXTemperRefr() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveRefrigerador", "" + iCveRefrigerador);
		hmfieldsTable.put("iCveTurnoRef", "" + iCveTurnoRef);
		hmfieldsTable.put("dTemperatura", "" + dTemperatura);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveRefrigerador() {
		return iCveRefrigerador;
	}

	public void setICveRefrigerador(int iCveRefrigerador) {
		this.iCveRefrigerador = iCveRefrigerador;
	}

	public int getICveTurnoRef() {
		return iCveTurnoRef;
	}

	public void setICveTurnoRef(int iCveTurnoRef) {
		this.iCveTurnoRef = iCveTurnoRef;
	}

	public float getDTemperatura() {
		return dTemperatura;
	}

	public void setDTemperatura(float dTemperatura) {
		this.dTemperatura = dTemperatura;
	}
}
