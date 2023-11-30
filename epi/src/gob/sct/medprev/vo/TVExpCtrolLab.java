package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ExpCtrolLab
 * </p>
 * <p>
 * Description: VO de la tabla TVExpCtrolLab
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Ing. Rafael Alcocer Caldera
 * @version 1.0
 */
public class TVExpCtrolLab implements HashBeanInterface {

	private BeanPK pk;
	private int iConsAnalisis;
	private int iAnio;
	private int iCveUniMed;
	private int iCveModulo;
	private int iCveAnalisis;
	private int iCveExpediente;
	private String cUsuaAplicado;
	private String cEstudios;
	private String cMotivo;
	private java.sql.Date dtSolicitud;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iConsAnalisis);

		return pk;
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iConsAnalisis", "" + iConsAnalisis);
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveAnalisis", "" + iCveAnalisis);
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("cUsuaAplicado", "" + cUsuaAplicado);
		hmfieldsTable.put("cEstudios", "" + cEstudios);
		hmfieldsTable.put("cMotivo", "" + cMotivo);
		hmfieldsTable.put("dtSolicitud", "" + dtSolicitud);

		return hmfieldsTable;
	}

	public int getIConsAnalisis() {
		return iConsAnalisis;
	}

	public void setIConsAnalisis(int iConsAnalisis) {
		this.iConsAnalisis = iConsAnalisis;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public int getICveAnalisis() {
		return iCveAnalisis;
	}

	public void setICveAnalisis(int iCveAnalisis) {
		this.iCveAnalisis = iCveAnalisis;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public String getCUsuaAplicado() {
		return cUsuaAplicado;
	}

	public void setCUsuaAplicado(String cUsuaAplicado) {
		this.cUsuaAplicado = cUsuaAplicado;
	}

	public String getCEstudios() {
		return cEstudios;
	}

	public void setCEstudios(String cEstudios) {
		this.cEstudios = cEstudios;
	}

	public String getCMotivo() {
		return cMotivo;
	}

	public void setCMotivo(String cMotivo) {
		this.cMotivo = cMotivo;
	}

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}
}
