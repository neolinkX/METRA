package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object EXPAudiomet
 * </p>
 * <p>
 * Description: VO Tabla EXPAudiomet
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
public class TVEXPAudiomet implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iOido;
	private int iTipo;
	private int iX;
	private int iY;
	private int iCveServicio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iOido);
		pk.add("" + iTipo);
		pk.add("" + iX);
		pk.add("" + iY);
		return pk;
	}

	public TVEXPAudiomet() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iOido", "" + iOido);
		hmfieldsTable.put("iTipo", "" + iTipo);
		hmfieldsTable.put("iX", "" + iX);
		hmfieldsTable.put("iY", "" + iY);
		return hmfieldsTable;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public int getIOido() {
		return iOido;
	}

	public void setIOido(int iOido) {
		this.iOido = iOido;
	}

	public int getITipo() {
		return iTipo;
	}

	public void setITipo(int iTipo) {
		this.iTipo = iTipo;
	}

	public int getIX() {
		return iX;
	}

	public void setIX(int iX) {
		this.iX = iX;
	}

	public int getIY() {
		return iY;
	}

	public void setIY(int iY) {
		this.iY = iY;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}
}
