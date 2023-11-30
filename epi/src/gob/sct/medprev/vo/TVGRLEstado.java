package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object GRLEstado
 * </p>
 * <p>
 * Description: VO GRLEstado
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
public class TVGRLEstado implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePais;
	private int iCveEstado;
	private String cDscEstado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePais);
		pk.add("" + iCveEstado);
		return pk;
	}

	public TVGRLEstado() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("cDscEstado", cDscEstado);
		return hmfieldsTable;
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

	public String getCDscEstado() {
		return cDscEstado;
	}

	public void setCDscEstado(String cDscEstado) {
		this.cDscEstado = cDscEstado;
	}
}
