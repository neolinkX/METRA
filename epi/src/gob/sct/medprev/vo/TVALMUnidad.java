package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMUnidad
 * </p>
 * <p>
 * Description: Value Object de ALMUnidad
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
public class TVALMUnidad implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUnidad;
	private String cDscUnidad;
	private String cAbreviatura;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUnidad);
		return pk;
	}

	public TVALMUnidad() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUnidad", "" + iCveUnidad);
		hmfieldsTable.put("cDscUnidad", cDscUnidad);
		hmfieldsTable.put("cAbreviatura", cAbreviatura);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveUnidad() {
		return iCveUnidad;
	}

	public void setICveUnidad(int iCveUnidad) {
		this.iCveUnidad = iCveUnidad;
	}

	public String getCDscUnidad() {
		return cDscUnidad;
	}

	public void setCDscUnidad(String cDscUnidad) {
		this.cDscUnidad = cDscUnidad;
	}

	public String getCAbreviatura() {
		return cAbreviatura;
	}

	public void setCAbreviatura(String cAbreviatura) {
		this.cAbreviatura = cAbreviatura;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
