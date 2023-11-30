package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object GRLModulo
 * </p>
 * <p>
 * Description: VO Tabla GRLModulo
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
public class TVGRLSUBModulo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUniMed;
	private int iCveModulo;
	private int iCveSubModulo;
	private String cDscModulo;
	private String cCalle;
	private String cColonia;
	private int iCP;
	private String cCiudad;
	private int iCvePais;
	private int iCveEstado;
	private int iCveMunicipio;
	private String cTel;
	private String cCorreoe;
	private int linterconsulta;
	private int lVigente;
	private String cDscUniMed;
	private int iCveUddAdmiva;
	private int iCveOficina;
	private String cClues;
	private int lValida;
	private String cDispSentencia;
	private String cDispositivos;
	private String cDispValor;
	private String cDispValorAnterior;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		pk.add("" + iCveModulo);
		return pk;
	}

	public TVGRLSUBModulo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveSubModulo", "" + iCveSubModulo);
		hmfieldsTable.put("cDscModulo", cDscModulo);
		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("iCP", "" + iCP);
		hmfieldsTable.put("cCiudad", cCiudad);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("iCveEstado", "" + iCveEstado);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("cTel", cTel);
		hmfieldsTable.put("cCorreoe", cCorreoe);
		hmfieldsTable.put("linterconsulta", "" + linterconsulta);
		hmfieldsTable.put("lVigente", "" + lVigente);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("cClues", cClues);

		hmfieldsTable.put("lValida", getlValida());
		hmfieldsTable.put("cDispositivos", cDispositivos);
		hmfieldsTable.put("cDispSentencia", cDispSentencia);
		hmfieldsTable.put("cDispValor", cDispValor);
		return hmfieldsTable;
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


	public int getICveSubModulo() {
		return iCveSubModulo;
	}

	public void setICveSubModulo(int iCveSubModulo) {
		this.iCveSubModulo = iCveSubModulo;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}

	public String getCCalle() {
		return cCalle;
	}

	public void setCCalle(String cCalle) {
		this.cCalle = cCalle;
	}

	public String getCColonia() {
		return cColonia;
	}

	public void setCColonia(String cColonia) {
		this.cColonia = cColonia;
	}

	public int getICP() {
		return iCP;
	}

	public void setICP(int iCP) {
		this.iCP = iCP;
	}

	public String getCCiudad() {
		return cCiudad;
	}

	public void setCCiudad(String cCiudad) {
		this.cCiudad = cCiudad;
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

	public String getCTel() {
		return cTel;
	}

	public void setCTel(String cTel) {
		this.cTel = cTel;
	}

	public String getCCorreoe() {
		return cCorreoe;
	}

	public void setCCorreoe(String cCorreoe) {
		this.cCorreoe = cCorreoe;
	}

	public int getLinterconsulta() {
		return linterconsulta;
	}

	public void setLinterconsulta(int linterconsulta) {
		this.linterconsulta = linterconsulta;
	}

	public int getLVigente() {
		return lVigente;
	}

	public void setLVigente(int lVigente) {
		this.lVigente = lVigente;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public int getICveUddAdmiva() {
		return iCveUddAdmiva;
	}

	public void setICveUddAdmiva(int iCveUddAdmiva) {
		this.iCveUddAdmiva = iCveUddAdmiva;
	}

	public int getICveOficina() {
		return iCveOficina;
	}

	public void setICveOficina(int iCveOficina) {
		this.iCveOficina = iCveOficina;
	}

	public String getCClues() {
		return cClues;
	}

	public void setCClues(String cClues) {
		this.cClues = cClues;
	}

	/**
	 * @return the lValida
	 */
	public int getlValida() {
		return lValida;
	}

	/**
	 * @param lValida
	 *            the lValida to set
	 */
	public void setlValida(int lValida) {
		this.lValida = lValida;
	}
	

	public String getCDispositivos() {
		return cDispositivos;
	}

	public void setCDispositivos(String cDispositivos) {
		this.cDispositivos = cDispositivos;
	}

	public String getCDispSentencia() {
		return cDispSentencia;
	}

	public void setCDispSentencia(String cDispSentencia) {
		this.cDispSentencia = cDispSentencia;
	}

	public String getCDispValor() {
		return cDispValor;
	}

	public void setCDispValor(String cDispValor) {
		this.cDispValor = cDispValor;
	}
	public String getCDispValorAnt() {
		return cDispValorAnterior;
	}

	public void setCDispValorAnt(String cDispValorAnterior) {
		this.cDispValorAnterior = cDispValorAnterior;
	}
}
