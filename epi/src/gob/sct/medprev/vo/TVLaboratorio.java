package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object Laboratorio
 * </p>
 * <p>
 * Description: VO Laboratorio
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
public class TVLaboratorio implements HashBeanInterface {
	private BeanPK pk;
	private int iCveLaboratorio;
	private String cDscLaboratorio;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveLaboratorio);
		return pk;
	}

	public TVLaboratorio() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("cDscLaboratorio", cDscLaboratorio);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		return hmfieldsTable;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public String getCDscLaboratorio() {
		return cDscLaboratorio;
	}

	public void setCDscLaboratorio(String cDscLaboratorio) {
		this.cDscLaboratorio = cDscLaboratorio;
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
