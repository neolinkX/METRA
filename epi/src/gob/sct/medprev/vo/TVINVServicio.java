package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object INVServicio
 * </p>
 * <p>
 * Description: VO INVServicio
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */
public class TVINVServicio implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveMdoTrans;
	private int iIDDGPMPT;
	private int iCveServicio;
	private int lConcluido;
	private java.sql.Date dtInicio;
	private java.sql.Date dtFin;
	private int iCveUsuAplica;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iIDDGPMPT);
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVINVServicio() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iIDDGPMPT", "" + iIDDGPMPT);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("lConcluido", "" + lConcluido);
		hmfieldsTable.put("dtInicio", "" + dtInicio);
		hmfieldsTable.put("dtFin", "" + dtFin);
		hmfieldsTable.put("iCveUsuAplica", "" + iCveUsuAplica);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getIIDDGPMPT() {
		return iIDDGPMPT;
	}

	public void setIIDDGPMPT(int iIDDGPMPT) {
		this.iIDDGPMPT = iIDDGPMPT;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getLConcluido() {
		return lConcluido;
	}

	public void setLConcluido(int lConcluido) {
		this.lConcluido = lConcluido;
	}

	public java.sql.Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(java.sql.Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public java.sql.Date getDtFin() {
		return dtFin;
	}

	public void setDtFin(java.sql.Date dtFin) {
		this.dtFin = dtFin;
	}

	public int getICveUsuAplica() {
		return iCveUsuAplica;
	}

	public void setICveUsuAplica(int iCveUsuAplica) {
		this.iCveUsuAplica = iCveUsuAplica;
	}
}
