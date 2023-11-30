package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object UniMed
 * </p>
 * <p>
 * Description: VO de Unidades Medicas
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernández García
 * @version 1.0
 */
public class TVUniMed implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUniMed;
	private String cDscUniMed;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		return pk;
	}

	public TVUniMed() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		return hmfieldsTable;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public int getICvePais() {
		return iCvePais;
	}

	public void setICvePais(int iCvePais) {
		this.iCvePais = iCvePais;
	}

	public int getICveEstado() {
		return iCveEstado;
	}

	public void setICveEstado(int iCveEstado) {
		this.iCveEstado = iCveEstado;
	}

	public int getICveMunicipio() {
		return iCveMunicipio;
	}

	public void setICveMunicipio(int iCveMunicipio) {
		this.iCveMunicipio = iCveMunicipio;
	}
}
