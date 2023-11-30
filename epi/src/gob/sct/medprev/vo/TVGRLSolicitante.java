package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object GRLSolicitante
 * </p>
 * <p>
 * Description: Value Object de la Tabla GRLSolicitante
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
public class TVGRLSolicitante implements HashBeanInterface {
	private BeanPK pk;
	private int iCveSolictante;
	private String cDscSolicitante;
	private int lExterno;
	private int lActivo;

	private String cObservacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveSolictante);
		return pk;
	}

	public TVGRLSolicitante() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveSolictante", "" + iCveSolictante);
		hmfieldsTable.put("lExterno", "" + lExterno);
		hmfieldsTable.put("cDscSolicitante", "" + cDscSolicitante);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getiCveSolictante() {
		return iCveSolictante;
	}

	public void setiCveSolictante(int iCveSolictante) {
		this.iCveSolictante = iCveSolictante;
	}

	public int getlExterno() {
		return lExterno;
	}

	public void setlExterno(int lExterno) {
		this.lExterno = lExterno;
	}

	public String getcDscSolicitante() {
		return cDscSolicitante;
	}

	public void setcDscSolicitante(String cDscSolicitante) {
		this.cDscSolicitante = cDscSolicitante;
	}

	public int getlActivo() {
		return lActivo;
	}

	public void setlActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
