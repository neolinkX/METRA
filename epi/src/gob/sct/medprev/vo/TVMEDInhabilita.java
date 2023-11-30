package gob.sct.medprev.vo;

import java.util.*;

import com.micper.seguridad.vo.TVUsuario;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EPICisCita
 * </p>
 * <p>
 * Description: VO Tabla EPICisCita
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 */
public class TVMEDInhabilita implements HashBeanInterface {
	private BeanPK pk;

	private int iCvePersonal;
	private int iCveMotivo;
	private String cDscMotivo;
	java.sql.Date InicioInh;
	java.sql.Date FinInh;
	private String cObservacion;
	private String cDscInicioInh;
	private String cDscInicioInhNac;
	// Usuario para dar altas
	private int iCveUsuInh;
	private String cMedico;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add(cDscInicioInh);
		return pk;
	}

	public TVMEDInhabilita() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("InicioInh", "" + InicioInh);
		hmfieldsTable.put("FinInh", "" + FinInh);
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("cDscMotivo", cDscMotivo);
		hmfieldsTable.put("cDscInicioInh", cDscInicioInh);
		hmfieldsTable.put("cDscInicioInhNac", cDscInicioInhNac);
		hmfieldsTable.put("iCveUsuInh", "" + iCveUsuInh);
		hmfieldsTable.put("cMedico", "" + cMedico);
		return hmfieldsTable;
	}

	public java.sql.Date getInicioInh() {
		return InicioInh;
	}

	public void setInicioInh(java.sql.Date InicioInh) {
		this.InicioInh = InicioInh;
	}

	public java.sql.Date getFinInh() {
		return FinInh;
	}

	public void setFinInh(java.sql.Date FinInh) {
		this.FinInh = FinInh;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public String getCDscInicioInh() {
		return cDscInicioInh;
	}

	public void setCDscInicioInh(String cDscInicioInh) {
		this.cDscInicioInh = cDscInicioInh;
	}

	public String getCDscInicioInhNac() {
		return cDscInicioInhNac;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscInicioInhNac(String cDscInicioInhNac) {
		this.cDscInicioInhNac = cDscInicioInhNac;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public int getiCveUsuInh() {
		return iCveUsuInh;
	}

	public void setiCveUsuInh(int iCveUsuInh) {
		this.iCveUsuInh = iCveUsuInh;
	}

	public String getCMedico() {
		return cMedico;
	}

	public void setCMedico(String cMedico) {
		this.cMedico = cMedico;
	}

}
