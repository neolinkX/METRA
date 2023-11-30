package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXTurnoRef
 * </p>
 * <p>
 * Description: VO de la tabla TOXTurnoRef
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
public class TVTOXTurnoRef implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveTurnoRef;
	private int iCveTurnoValida;
	private int iCveUsuRespon;
	private int iMes;
	private int iDia;
	private float dTempAmbiente;
	private float dHumedad;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveTurnoRef);
		return pk;
	}

	public TVTOXTurnoRef() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveTurnoRef", "" + iCveTurnoRef);
		hmfieldsTable.put("iCveTurnoValida", "" + iCveTurnoValida);
		hmfieldsTable.put("iCveUsuRespon", "" + iCveUsuRespon);
		hmfieldsTable.put("iMes", "" + iMes);
		hmfieldsTable.put("iDia", "" + iDia);
		hmfieldsTable.put("dTempAmbiente", "" + dTempAmbiente);
		hmfieldsTable.put("dHumedad", "" + dHumedad);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveTurnoRef() {
		return iCveTurnoRef;
	}

	public void setICveTurnoRef(int iCveTurnoRef) {
		this.iCveTurnoRef = iCveTurnoRef;
	}

	public int getICveTurnoValida() {
		return iCveTurnoValida;
	}

	public void setICveTurnoValida(int iCveTurnoValida) {
		this.iCveTurnoValida = iCveTurnoValida;
	}

	public int getICveUsuRespon() {
		return iCveUsuRespon;
	}

	public void setICveUsuRespon(int iCveUsuRespon) {
		this.iCveUsuRespon = iCveUsuRespon;
	}

	public int getIMes() {
		return iMes;
	}

	public void setIMes(int iMes) {
		this.iMes = iMes;
	}

	public int getIDia() {
		return iDia;
	}

	public void setIDia(int iDia) {
		this.iDia = iDia;
	}

	public float getDTempAmbiente() {
		return dTempAmbiente;
	}

	public void setDTempAmbiente(float dTempAmbiente) {
		this.dTempAmbiente = dTempAmbiente;
	}

	public float getDHumedad() {
		return dHumedad;
	}

	public void setDHumedad(float dHumedad) {
		this.dHumedad = dHumedad;
	}
}
