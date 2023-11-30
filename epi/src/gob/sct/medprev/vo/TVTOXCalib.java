package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;

/**
 * <p>
 * Title: Value Object TOXCalib
 * </p>
 * <p>
 * Description: Lotes Cuantitativos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrejón Adame
 * @version 1.0
 */
public class TVTOXCalib implements HashBeanInterface {
	private BeanPK pk;
	private Integer iCveEquipo;
	private Integer iCveCalib;
	private Integer iCveLaboratorio;
	private Integer iCveParamCalib;

	private Double dValor;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEquipo);
		pk.add("" + iCveCalib);
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveParamCalib);
		return pk;
	}

	public TVTOXCalib() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveCalib", "" + iCveCalib);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveParamCalib", "" + iCveParamCalib);
		hmfieldsTable.put("dValor", "" + dValor);
		return hmfieldsTable;
	}

	public Integer getiCveEquipo() {
		return iCveEquipo;
	}

	public void setiCveEquipo(Integer iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public Integer getiCveCalib() {
		return iCveCalib;
	}

	public void setiCveCalib(Integer iCveCalib) {
		this.iCveCalib = iCveCalib;
	}

	public Integer getiCveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setiCveLaboratorio(Integer iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public Integer getiCveParamCalib() {
		return iCveParamCalib;
	}

	public void setiCveParamCalib(Integer iCveParamCalib) {
		this.iCveParamCalib = iCveParamCalib;
	}

	public Double getdValor() {
		return dValor;
	}

	public void setdValor(Double dValor) {
		this.dValor = dValor;
	}
}
