package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object LMotivo
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
public class GRLMotivo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMotivo;
	private int iCveProceso;
	private String cDscMotivo;
	private int lCita;
	private int lPago;
	private int lConstancia;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMotivo);
		pk.add("" + iCveProceso);
		pk.add(cDscMotivo);
		pk.add("" + lCita);
		pk.add("" + lPago);
		pk.add("" + lConstancia);
		pk.add("" + lActivo);
		return pk;
	}

	public GRLMotivo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("cDscMotivo", cDscMotivo);
		hmfieldsTable.put("lCita", "" + lCita);
		hmfieldsTable.put("lPago", "" + lPago);
		hmfieldsTable.put("lConstancia", "" + lConstancia);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public int getLCita() {
		return lCita;
	}

	public void setLCita(int lCita) {
		this.lCita = lCita;
	}

	public int getLPago() {
		return lPago;
	}

	public void setLPago(int lPago) {
		this.lPago = lPago;
	}

	public int getLConstancia() {
		return lConstancia;
	}

	public void setLConstancia(int lConstancia) {
		this.lConstancia = lConstancia;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
