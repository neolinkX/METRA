package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXRefrigerador
 * </p>
 * <p>
 * Description: Value Object de TOXRefrigerador
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */
public class TVTOXRefrigerador implements HashBeanInterface {
	private BeanPK pk;
	private int iCveRefrigerador;
	private int iCveEquipo;
	private String cDscEquipo;
	private String cNumSerie;
	private int iCveArea;
	private String cDscArea;
	private int iCveTurnoValida;
	private float dTemperatura;
	private int iAnio;
	private int iMes;
	private int iDia;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveRefrigerador);
		return pk;
	}

	public TVTOXRefrigerador() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveRefrigerador", "" + iCveRefrigerador);
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("cDscEquipo", cDscEquipo);
		hmfieldsTable.put("cNumSerie", cNumSerie);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("iCveTurnoValida", "" + iCveTurnoValida);
		hmfieldsTable.put("dTemperatura", "" + dTemperatura);
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iMes", "" + iMes);
		hmfieldsTable.put("iDia", "" + iDia);
		hmfieldsTable.put("cDscArea", cDscArea);
		return hmfieldsTable;
	}

	public int getICveRefrigerador() {
		return iCveRefrigerador;
	}

	public void setICveRefrigerador(int iCveRefrigerador) {
		this.iCveRefrigerador = iCveRefrigerador;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public void setCDscEquipo(String cDscEquipo) {
		this.cDscEquipo = cDscEquipo;
	}

	public String getCDscEquipo() {
		return cDscEquipo;
	}

	public void setCNumSerie(String cNumSerie) {
		this.cNumSerie = cNumSerie;
	}

	public String getCNumSerie() {
		return cNumSerie;
	}

	public int getICveArea() {
		return iCveArea;
	}

	public void setICveArea(int iCveArea) {
		this.iCveArea = iCveArea;
	}

	public int getICveTurnoValida() {
		return iCveTurnoValida;
	}

	public void setICveTurnoValida(int iCveTurnoValida) {
		this.iCveTurnoValida = iCveTurnoValida;
	}

	public float getDTemperatura() {
		return dTemperatura;
	}

	public void setDTemperatura(float dTemperatura) {
		this.dTemperatura = dTemperatura;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
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

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

	public String getCDscArea() {
		return cDscArea;
	}
}
