package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TOXEquipo
 * </p>
 * <p>
 * Description: VO para la tabla TOXEquipo
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
public class TVTOXEquipo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEquipo;
	private int lCuantCual;
	private int iCarrucel;
	private int iPosiciones;
	private String cDscEquipo;
	private String cNumSerie;
	private String cModelo;
	private String cCveEquipo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEquipo);
		return pk;
	}

	public TVTOXEquipo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("lCuantCual", "" + lCuantCual);
		hmfieldsTable.put("iCarrucel", "" + iCarrucel);
		hmfieldsTable.put("iPosiciones", "" + iPosiciones);
		hmfieldsTable.put("cDscEquipo", "" + cDscEquipo);
		hmfieldsTable.put("cNumSerie", "" + cNumSerie);
		hmfieldsTable.put("cModelo", "" + cModelo);
		hmfieldsTable.put("cCveEquipo", "" + cCveEquipo);
		return hmfieldsTable;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public int getLCuantCual() {
		return lCuantCual;
	}

	public void setLCuantCual(int lCuantCual) {
		this.lCuantCual = lCuantCual;
	}

	public int getICarrucel() {
		return iCarrucel;
	}

	public void setICarrucel(int iCarrucel) {
		this.iCarrucel = iCarrucel;
	}

	public int getIPosiciones() {
		return iPosiciones;
	}

	public void setIPosiciones(int iPosiciones) {
		this.iPosiciones = iPosiciones;
	}

	public String getCDscEquipo() {
		return cDscEquipo;
	}

	public void setCDscEquipo(String cDscEquipo) {
		this.cDscEquipo = cDscEquipo;
	}

	public String getCNumSerie() {
		return cNumSerie;
	}

	public void setCNumSerie(String cNumSerie) {
		this.cNumSerie = cNumSerie;
	}

	public String getCModelo() {
		return cModelo;
	}

	public void setCModelo(String cModelo) {
		this.cModelo = cModelo;
	}

	public String getCCveEquipo() {
		return cCveEquipo;
	}

	public void setCCveEquipo(String cCveEquipo) {
		this.cCveEquipo = cCveEquipo;
	}

}
