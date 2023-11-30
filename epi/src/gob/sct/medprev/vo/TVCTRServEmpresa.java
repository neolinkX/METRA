package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object CTRServEmpresa
 * </p>
 * <p>
 * Description: Control al Transporte - Servicio Prestado por Empresa
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */
public class TVCTRServEmpresa implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpresa;
	private int iCveServPrestado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpresa);
		pk.add("" + iCveServPrestado);
		return pk;
	}

	public TVCTRServEmpresa() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("iCveServPrestado", "" + iCveServPrestado);
		return hmfieldsTable;
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public int getICveServPrestado() {
		return iCveServPrestado;
	}

	public void setICveServPrestado(int iCveServPrestado) {
		this.iCveServPrestado = iCveServPrestado;
	}
}
