package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TOXSustancia
 * </p>
 * <p>
 * Description: Value Object de la entidad TOXSustancia
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suárez Romero
 * @version 1.0
 */
public class TVTOXSustancia implements HashBeanInterface {
	private BeanPK pk;
	private int iCveSustancia;
	private String cDscSustancia;
	private String cTitRepConf;
	private String cPrefLoteConf;
	private int lActivo;
	private int lAnalizada;
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

	public TVTOXSustancia() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("cDscSustancia", cDscSustancia);
		hmfieldsTable.put("cTitRepConf", cTitRepConf);
		hmfieldsTable.put("cPrefLoteConf", cPrefLoteConf);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("lAnalizada", "" + lAnalizada);
		hmfieldsTable.put("iMuestrasLC", String.valueOf(iMuestrasLC));
		hmfieldsTable.put("cSustUnifica", cSustUnifica);
		hmfieldsTable.put("cAbrevEqAC", cAbrevEqAC);
		hmfieldsTable.put("lValidaCtrol", "" + this.lValidaCtrol);
		hmfieldsTable.put("lValConcentracion", "" + this.lValConcentracion);

		return hmfieldsTable;
	}

	public int getiCveSustancia() {
		return iCveSustancia;
	}

	public void setiCveSustancia(int iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public String getcDscSustancia() {
		return cDscSustancia;
	}

	public void setcDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

	public String getcTitRepConf() {
		return cTitRepConf;
	}

	public void setcTitRepConf(String cTitRepConf) {
		this.cTitRepConf = cTitRepConf;
	}

	public String getcPrefLoteConf() {
		return cPrefLoteConf;
	}

	public void setcPrefLoteConf(String cPrefLoteConf) {
		this.cPrefLoteConf = cPrefLoteConf;
	}

	public int getlActivo() {
		return lActivo;
	}

	public void setlActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public int getlAnalizada() {
		return lAnalizada;
	}

	public void setlAnalizada(int lAnalizada) {
		this.lAnalizada = lAnalizada;
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
