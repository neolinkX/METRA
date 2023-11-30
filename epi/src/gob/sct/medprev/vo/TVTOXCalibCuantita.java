package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;

/**
 * <p>
 * Title: Value Object TOXCalibCuantita
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
public class TVTOXCalibCuantita implements HashBeanInterface {
	private BeanPK pk;
	private Integer iCveLaboratorio;
	private Integer iCveCalib;
	private Integer iPosicion;

	private String cDscCalib;
	private Double dPorcentaje;
	private Integer lControl;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveCalib);
		pk.add("" + iPosicion);
		return pk;
	}

	public TVTOXCalibCuantita() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveCalib", "" + iCveCalib);
		hmfieldsTable.put("iPosicion", "" + iPosicion);
		hmfieldsTable.put("cDscCalib", "" + cDscCalib);
		hmfieldsTable.put("dPorcentaje", "" + dPorcentaje);
		hmfieldsTable.put("lControl", "" + lControl);
		return hmfieldsTable;
	}

	public Integer getiCveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setiCveLaboratorio(Integer iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public Integer getiCveCalib() {
		return iCveCalib;
	}

	public void setiCveCalib(Integer iCveCalib) {
		this.iCveCalib = iCveCalib;
	}

	public Integer getiPosicion() {
		return iPosicion;
	}

	public void setiPosicion(Integer iPosicion) {
		this.iPosicion = iPosicion;
	}

	public Integer getlControl() {
		return lControl;
	}

	public void setlControl(Integer lControl) {
		this.lControl = lControl;
	}

	public Double getdPorcentaje() {
		return dPorcentaje;
	}

	public void setdPorcentaje(Double dPorcentaje) {
		this.dPorcentaje = dPorcentaje;
	}

	public String getcDscCalib() {
		return cDscCalib;
	}

	public void setcDscCalib(String cDscCalib) {
		this.cDscCalib = cDscCalib;
	}

}
