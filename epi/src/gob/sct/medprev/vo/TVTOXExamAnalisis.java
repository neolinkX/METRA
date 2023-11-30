package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TOXExamAnalisis
 * </p>
 * <p>
 * Description: VO para la tabla TOXExamAnalisis
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
public class TVTOXExamAnalisis implements HashBeanInterface {
	private BeanPK pk;
	private int iCveLaboratorio;
	private int iAnio;
	private int iCveLoteCualita;
	private int iCveExamCualita;
	private int iCveAnalisis;
	private int iCarrusel;
	private int iPosicion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveLaboratorio);
		pk.add("" + iAnio);
		pk.add("" + iCveLoteCualita);
		pk.add("" + iCveExamCualita);
		pk.add("" + iCveAnalisis);
		return pk;
	}

	public TVTOXExamAnalisis() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLoteCualita", "" + iCveLoteCualita);
		hmfieldsTable.put("iCveExamCualita", "" + iCveExamCualita);
		hmfieldsTable.put("iCveAnalisis", "" + iCveAnalisis);
		hmfieldsTable.put("iCarrusel", "" + iCarrusel);
		hmfieldsTable.put("iPosicion", "" + iPosicion);
		return hmfieldsTable;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveLoteCualita() {
		return iCveLoteCualita;
	}

	public void setICveLoteCualita(int iCveLoteCualita) {
		this.iCveLoteCualita = iCveLoteCualita;
	}

	public int getICveExamCualita() {
		return iCveExamCualita;
	}

	public void setICveExamCualita(int iCveExamCualita) {
		this.iCveExamCualita = iCveExamCualita;
	}

	public int getICveAnalisis() {
		return iCveAnalisis;
	}

	public void setICveAnalisis(int iCveAnalisis) {
		this.iCveAnalisis = iCveAnalisis;
	}

	public int getICarrusel() {
		return iCarrusel;
	}

	public void setICarrusel(int iCarrusel) {
		this.iCarrusel = iCarrusel;
	}

	public int getIPosicion() {
		return iPosicion;
	}

	public void setIPosicion(int iPosicion) {
		this.iPosicion = iPosicion;
	}
}
