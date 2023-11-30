package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object Municipio
 * </p>
 * <p>
 * Description: VO Tabla GRLMunicipio
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. González Paz
 * @version 1.0
 */
public class TVMunicipio implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePais;
	private int iCveEntidadFed;
	private int iCveMunicipio;
	private String cNombre;
	private String cAbreviatura;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePais);
		pk.add("" + iCveEntidadFed);
		pk.add("" + iCveMunicipio);
		return pk;
	}

	public TVMunicipio() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEntidadFed", "" + iCveEntidadFed);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
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

	public int getICveMunicipio() {
		return iCveMunicipio;
	}

	public void setICveMunicipio(int iCveMunicipio) {
		this.iCveMunicipio = iCveMunicipio;
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
