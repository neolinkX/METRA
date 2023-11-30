package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object CTRServPrestado
 * </p>
 * <p>
 * Description: Control al Transporte - Servicio Prestado
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
public class TVCTRServPrestado implements HashBeanInterface {
	private BeanPK pk;
	private int iCveServPrestado;
	private String cDscServPrestado;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveServPrestado);
		return pk;
	}

	public TVCTRServPrestado() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveServPrestado", "" + iCveServPrestado);
		hmfieldsTable.put("cDscServPrestado", cDscServPrestado);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveServPrestado() {
		return iCveServPrestado;
	}

	public void setICveServPrestado(int iCveServPrestado) {
		this.iCveServPrestado = iCveServPrestado;
	}

	public String getCDscServPrestado() {
		return cDscServPrestado;
	}

	public void setCDscServPrestado(String cDscServPrestado) {
		this.cDscServPrestado = cDscServPrestado;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
