package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXAbsorMuestra
 * </p>
 * <p>
 * Description: VO para el manejo de captura de muestras
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */
public class TVTOXAbsorMuestra implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveAbsorvancia;
	private int iCveMAbsorvancia;
	private int iCveSustancia;
	private float dConcentracion;
	private float dPorcentaje;
	private int iCarrusel;
	private int iPosicion;
	private float dResultado;
	private String cDscSustancia;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveAbsorvancia);
		pk.add("" + iCveMAbsorvancia);
		return pk;
	}

	public TVTOXAbsorMuestra() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveAbsorvancia", "" + iCveAbsorvancia);
		hmfieldsTable.put("iCveMAbsorvancia", "" + iCveMAbsorvancia);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("dConcentracion", "" + dConcentracion);
		hmfieldsTable.put("dPorcentaje", "" + dPorcentaje);
		hmfieldsTable.put("iCarrusel", "" + iCarrusel);
		hmfieldsTable.put("iPosicion", "" + iPosicion);
		hmfieldsTable.put("cDscSustancia", "" + cDscSustancia);
		hmfieldsTable.put("dResultado", "" + dResultado);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveAbsorvancia() {
		return iCveAbsorvancia;
	}

	public void setICveAbsorvancia(int iCveAbsorvancia) {
		this.iCveAbsorvancia = iCveAbsorvancia;
	}

	public int getICveMAbsorvancia() {
		return iCveMAbsorvancia;
	}

	public void setICveMAbsorvancia(int iCveMAbsorvancia) {
		this.iCveMAbsorvancia = iCveMAbsorvancia;
	}

	public int getICveSustancia() {
		return iCveSustancia;
	}

	public void setICveSustancia(int iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public float getDConcentracion() {
		return dConcentracion;
	}

	public void setDConcentracion(float dConcentracion) {
		this.dConcentracion = dConcentracion;
	}

	public float getDPorcentaje() {
		return dPorcentaje;
	}

	public void setDPorcentaje(float dPorcentaje) {
		this.dPorcentaje = dPorcentaje;
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

	public float getDResultado() {
		return dResultado;
	}

	public void setDResultado(float dResultado) {
		this.dResultado = dResultado;
	}

	public String getCDscSustancia() {
		return cDscSustancia;
	}

	public void setCDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

}
