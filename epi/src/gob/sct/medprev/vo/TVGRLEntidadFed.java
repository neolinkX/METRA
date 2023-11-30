package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLEntidadFed
 * </p>
 * <p>
 * Description: Value Object de la Entidad GRLEntidadFed
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVGRLEntidadFed implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePais;
	private int iCveEntidadFed;
	private String cNombre;
	private String cAbreviatura;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePais);
		pk.add("" + iCveEntidadFed);
		return pk;
	}

	public TVGRLEntidadFed() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEntidadFed", "" + iCveEntidadFed);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cAbreviatura", cAbreviatura);
		return hmfieldsTable;
	}

	public int getICvePais() {
		return iCvePais;
	}

	public void setICvePais(int iCvePais) {
		this.iCvePais = iCvePais;
	}

	public int getICveEntidadFed() {
		return iCveEntidadFed;
	}

	public void setICveEntidadFed(int iCveEntidadFed) {
		this.iCveEntidadFed = iCveEntidadFed;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCAbreviatura() {
		return cAbreviatura;
	}

	public void setCAbreviatura(String cAbreviatura) {
		this.cAbreviatura = cAbreviatura;
	}
}
