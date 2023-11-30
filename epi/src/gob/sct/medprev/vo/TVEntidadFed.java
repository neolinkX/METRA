package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;

/**
 * <p>
 * Title: Value Object EntidadFed
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */
public class TVEntidadFed implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePais;
	private int iCveEntidadFed;
	private String cNombre;
	private String cAbreviatura;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePais);
		return pk;
	}

	public TVEntidadFed() {
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

	public int getICveEntidadFed() {
		return iCveEntidadFed;
	}

	public void setICveEntidadFed(int iCveEntidadFed) {
		this.iCveEntidadFed = iCveEntidadFed;
	}
}
