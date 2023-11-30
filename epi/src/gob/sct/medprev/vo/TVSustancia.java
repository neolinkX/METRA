package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object Sustancia
 * </p>
 * <p>
 * Description: Value Object TOXSustancia
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
public class TVSustancia implements HashBeanInterface {
	private BeanPK pk;
	private int iCveSustancia;
	private String cDscSustancia;
	private String cTitRepConf;
	private String cPrefLoteConf;
	private String cAbrvEq;
	private int lActivo;
	private int lAnalizada;
	private int lPositiva;
	private double dIon01;
	private double dIon02;
	private double dIon03;
	private double dIon04;
	private double dIon05;
	private int iMuestrasLC;
	private String cSustUnifica;
	private String cAbrevEqAC;
	private int lValidaCtrol;
	private int lValConcentracion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveSustancia);
		return pk;
	}

	public TVSustancia() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("cDscSustancia", cDscSustancia);
		hmfieldsTable.put("cTitRepConf", cTitRepConf);
		hmfieldsTable.put("cPrefLoteConf", cPrefLoteConf);
		hmfieldsTable.put("cAbrvEq", cAbrvEq);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("lAnalizada", "" + lAnalizada);
		hmfieldsTable.put("lPositiva", "" + lPositiva);
		hmfieldsTable.put("dIon01", "" + dIon01);
		hmfieldsTable.put("dIon02", "" + dIon02);
		hmfieldsTable.put("dIon03", "" + dIon03);
		hmfieldsTable.put("dIon04", "" + dIon04);
		hmfieldsTable.put("dIon05", "" + dIon05);
		hmfieldsTable.put("iMuestrasLC", String.valueOf(this.iMuestrasLC));
		hmfieldsTable.put("cSustUnifica", cSustUnifica);
		hmfieldsTable.put("cAbrevEqAC", cAbrevEqAC);
		hmfieldsTable.put("lValidaCtrol", "" + this.lValidaCtrol);
		hmfieldsTable.put("lValConcentracion", "" + this.lValConcentracion);
		return hmfieldsTable;
	}

	public int getICveSustancia() {
		return iCveSustancia;
	}

	public void setICveSustancia(int iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public String getCDscSustancia() {
		return cDscSustancia;
	}

	public void setCDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

	public String getCTitRepConf() {
		return cTitRepConf;
	}

	public void setCTitRepConf(String cTitRepConf) {
		this.cTitRepConf = cTitRepConf;
	}

	public String getCAbrvEq() {
		return this.cAbrvEq;
	}

	public void setCAbrvEq(String cAbrvEq) {
		this.cAbrvEq = cAbrvEq;
	}

	public String getCPrefLoteConf() {
		return cPrefLoteConf;
	}

	public void setCPrefLoteConf(String cPrefLoteConf) {
		this.cPrefLoteConf = cPrefLoteConf;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public int getLAnalizada() {
		return lAnalizada;
	}

	public void setLAnalizada(int lAnalizada) {
		this.lAnalizada = lAnalizada;
	}

	public int getLPositiva() {
		return lPositiva;
	}

	public void setLPositiva(int lPositiva) {
		this.lPositiva = lPositiva;
	}

	public double getDIon01() {
		return dIon01;
	}

	public void setDIon01(double dIon01) {
		this.dIon01 = dIon01;
	}

	public double getDIon02() {
		return dIon02;
	}

	public void setDIon02(double dIon01) {
		this.dIon02 = dIon02;
	}

	public double getDIon03() {
		return dIon03;
	}

	public void setDIon03(double dIon03) {
		this.dIon03 = dIon03;
	}

	public double getDIon04() {
		return dIon04;
	}

	public void setDIon04(double dIon04) {
		this.dIon04 = dIon04;
	}

	public double getDIon05() {
		return dIon05;
	}

	public void setDIon05(double dIon05) {
		this.dIon05 = dIon05;
	}

	public int getIMuestrasLC() {
		return iMuestrasLC;
	}

	public void setIMuestrasLC(int iMuestrasLC) {
		this.iMuestrasLC = iMuestrasLC;
	}

	public String getCSustUnifica() {
		return cSustUnifica;
	}

	public void setCSustUnifica(String cSustUnifica) {
		this.cSustUnifica = cSustUnifica;
	}

	public String getCAbrevEqAC() {
		return cAbrevEqAC;
	}

	public void setCAbrevEqAC(String cAbrevEqAC) {
		this.cAbrevEqAC = cAbrevEqAC;
	}

	public int getLValidaCtrol() {
		return lValidaCtrol;
	}

	public void setLValidaCtrol(int lValidaCtrol) {
		this.lValidaCtrol = lValidaCtrol;
	}

	public int getLValConcentracion() {
		return lValConcentracion;
	}

	public void setLValConcentracion(int lValConcentracion) {
		this.lValConcentracion = lValConcentracion;
	}

}
