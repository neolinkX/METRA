package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXCualtSust
 * </p>
 * <p>
 * Description: Value Object de la Tabla TOXCualtSust
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
public class TVTOXCualtSust implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveLoteCualita;
	private int iCveExamCualita;
	private int iCveLaboratorio;
	private int iCveSustancia;
	private int iCveCorte;
	private String cDscSustancia;
	private float dCorte_co;
	private float dCorteNeg_co;
	private float dCortePost_co;
	private String cDscReactivo;
	private float dCorteNeg_r;
	private float dCortePost_r;
	private String cDscCtrolCalibra;
	private float dCorte_ca;
	private float dCorteNeg_ca;
	private float dCortePost_ca;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveLoteCualita);
		pk.add("" + iCveExamCualita);
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveSustancia);
		return pk;
	}

	public TVTOXCualtSust() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLoteCualita", "" + iCveLoteCualita);
		hmfieldsTable.put("iCveExamCualita", "" + iCveExamCualita);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("iCveCorte", "" + iCveCorte);
		hmfieldsTable.put("cDscSustancia", cDscSustancia);
		hmfieldsTable.put("dCorte_co", "" + dCorte_co);
		hmfieldsTable.put("dCorteNeg_co", "" + dCorteNeg_co);
		hmfieldsTable.put("dCortePost_co", "" + dCortePost_co);
		hmfieldsTable.put("cDscReactivo", cDscReactivo);
		hmfieldsTable.put("dCorteNeg_r", "" + dCorteNeg_r);
		hmfieldsTable.put("dCortePost_r", "" + dCortePost_r);
		hmfieldsTable.put("cDscCtrolCalibra", cDscCtrolCalibra);
		hmfieldsTable.put("dCorte_ca", "" + dCorte_ca);
		hmfieldsTable.put("dCorteNeg_ca", "" + dCorteNeg_ca);
		hmfieldsTable.put("dCortePost_ca", "" + dCortePost_ca);

		return hmfieldsTable;
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

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getICveSustancia() {
		return iCveSustancia;
	}

	public void setICveSustancia(int iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public int getICveCorte() {
		return iCveCorte;
	}

	public void setICveCorte(int iCveCorte) {
		this.iCveCorte = iCveCorte;
	}

	public String getcDscSustancia() {
		return cDscSustancia;
	}

	public void setcDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

	public float getDCorte_co() {
		return dCorte_co;
	}

	public void setDCorte_co(float dCorte_co) {
		this.dCorte_co = dCorte_co;
	}

	public float getDCorteNeg_co() {
		return dCorteNeg_co;
	}

	public void setDCorteNeg_co(float dCorteNeg_co) {
		this.dCorteNeg_co = dCorteNeg_co;
	}

	public float getDCortePost_co() {
		return dCortePost_co;
	}

	public void setDCortePost_co(float dCortePost_co) {
		this.dCortePost_co = dCortePost_co;
	}

	public String getCDscReactivo() {
		return cDscReactivo;
	}

	public void setCDscReactivo(String cDscReactivo) {
		this.cDscReactivo = cDscReactivo;
	}

	public float getDCorteNeg_r() {
		return dCorteNeg_r;
	}

	public float getDCortePost_r() {
		return dCortePost_r;
	}

	public void setDCortePost_r(float dCortePost_r) {
		this.dCortePost_r = dCortePost_r;
	}

	public void setDCorteNeg_r(float dCorteNeg_r) {
		this.dCorteNeg_r = dCorteNeg_r;
	}

	public String getCDscCtrolCalibra() {
		return cDscCtrolCalibra;
	}

	public void setCDscCtrolCalibra(String cDscCtrolCalibra) {
		this.cDscCtrolCalibra = cDscCtrolCalibra;
	}

	public float getDCorte_ca() {
		return dCorte_ca;
	}

	public void setDCorte_ca(float dCorte_ca) {
		this.dCorte_ca = dCorte_ca;
	}

	public float getDCorteNeg_ca() {
		return dCorteNeg_ca;
	}

	public void setDCorteNeg_ca(float dCorteNeg_ca) {
		this.dCorteNeg_ca = dCorteNeg_ca;
	}

	public float getDCortePost_ca() {
		return dCortePost_ca;
	}

	public void setDCortePost_ca(float dCortePost_ca) {
		this.dCortePost_ca = dCortePost_ca;
	}

}
