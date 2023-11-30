package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;

/**
 * <p>
 * Title: Value Object TOXParamCalib
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
public class TVTOXParamCalib implements HashBeanInterface {
	private BeanPK pk;
	private Integer iCveLaboratorio;
	private Integer iCveParamCalib;

	private String cDscParam;
	private Integer lActivo;
	private Double dValorMin;
	private Double dValorMax;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveLaboratorio.intValue());
		pk.add("" + iCveParamCalib.intValue());
		return pk;
	}

	public TVTOXParamCalib() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveParamCalib", "" + iCveParamCalib);
		hmfieldsTable.put("cDscParam", "" + cDscParam);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("dValorMin", "" + dValorMin);
		hmfieldsTable.put("dValorMax", "" + dValorMax);
		return hmfieldsTable;
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

	public String getcDscParam() {
		return cDscParam;
	}

	public void setcDscParam(String cDscParam) {
		this.cDscParam = cDscParam;
	}

	public Integer getlActivo() {
		return lActivo;
	}

	public void setlActivo(Integer lActivo) {
		this.lActivo = lActivo;
	}

	public Double getdValorMin() {
		return dValorMin;
	}

	public void setdValorMin(Double dValorMin) {
		this.dValorMin = dValorMin;
	}

	public Double getDValorMax() {
		return dValorMax;
	}

	public void setDValorMax(Double dValorMax) {
		this.dValorMax = dValorMax;
	}
}
