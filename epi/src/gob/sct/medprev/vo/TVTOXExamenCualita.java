package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;
import com.micper.util.TNumeros;

/**
 * <p>
 * Title: Value Object TOXExamenCualita
 * </p>
 * <p>
 * Description: VO para la tabla TOXExamenCualita
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernández García
 * @version 1.0
 */
public class TVTOXExamenCualita implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveLoteCualita;
	private int iCveExamCualita;
	private int iCveLaboratorio;
	private java.sql.Date dtEntrega;
	private java.sql.Date dtProcesado;
	private java.sql.Date dtAutorizado;
	private int lAutorizado;
	private int lReanalisis;
	private int iCveEquipo;
	private int iCveUsuPrepara;
	private int iCveUsuExam;
	private int iCveUsuAutoriza;
	private String cLote;

	private int iCveAnalisis;
	private int iCarrusel;
	private int iPosicion;
	private String cResultado;
	public TVEQMEquipo VEquipo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveLoteCualita);
		pk.add("" + iCveExamCualita);
		pk.add("" + iCveLaboratorio);
		return pk;
	}

	public TVTOXExamenCualita() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLoteCualita", "" + iCveLoteCualita);
		hmfieldsTable.put("iCveExamCualita", "" + iCveExamCualita);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("dtEntrega", "" + dtEntrega);
		hmfieldsTable.put("dtProcesado", "" + dtProcesado);
		hmfieldsTable.put("dtAutorizado", "" + dtAutorizado);
		hmfieldsTable.put("lAutorizado", "" + lAutorizado);
		hmfieldsTable.put("lReanalisis", "" + lReanalisis);
		hmfieldsTable.put("iCveEquipo", "" + iCveEquipo);
		hmfieldsTable.put("iCveUsuPrepara", "" + iCveUsuPrepara);
		hmfieldsTable.put("iCveUsuExam", "" + iCveUsuExam);
		hmfieldsTable.put("iCveUsuAutoriza", "" + iCveUsuAutoriza);
		hmfieldsTable.put("cLote", "" + this.getCLote());
		hmfieldsTable.put("iCveAnalisis", "" + iCveAnalisis);
		hmfieldsTable.put("iCarrusel", "" + iCarrusel);
		hmfieldsTable.put("iPosicion", "" + iPosicion);
		hmfieldsTable.put("cResultado", cResultado);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveLoteCualita() {
		return iCveLoteCualita;
	}

	public void setICveLoteCualita(int iCveLoteCualita) {
		this.iCveLoteCualita = iCveLoteCualita;
	}

	public int getICveExamCualita() {
		return iCveExamCualita;
	}

	public void setICveExamCualita(int iCveExamCualita) {
		this.iCveExamCualita = iCveExamCualita;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public java.sql.Date getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(java.sql.Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public java.sql.Date getDtProcesado() {
		return dtProcesado;
	}

	public void setDtProcesado(java.sql.Date dtProcesado) {
		this.dtProcesado = dtProcesado;
	}

	public java.sql.Date getDtAutorizado() {
		return dtAutorizado;
	}

	public void setDtAutorizado(java.sql.Date dtAutorizado) {
		this.dtAutorizado = dtAutorizado;
	}

	public int getLAutorizado() {
		return lAutorizado;
	}

	public void setLAutorizado(int lAutorizado) {
		this.lAutorizado = lAutorizado;
	}

	public int getLReanalisis() {
		return lReanalisis;
	}

	public void setLReanalisis(int lReanalisis) {
		this.lReanalisis = lReanalisis;
	}

	public int getICveEquipo() {
		return iCveEquipo;
	}

	public void setICveEquipo(int iCveEquipo) {
		this.iCveEquipo = iCveEquipo;
	}

	public int getICveUsuPrepara() {
		return iCveUsuPrepara;
	}

	public void setICveUsuPrepara(int iCveUsuPrepara) {
		this.iCveUsuPrepara = iCveUsuPrepara;
	}

	public int getICveUsuExam() {
		return iCveUsuExam;
	}

	public void setICveUsuExam(int iCveUsuExam) {
		this.iCveUsuExam = iCveUsuExam;
	}

	public int getICveUsuAutoriza() {
		return iCveUsuAutoriza;
	}

	public void setICveUsuAutoriza(int iCveUsuAutoriza) {
		this.iCveUsuAutoriza = iCveUsuAutoriza;
	}

	public String getCLote() {
		TNumeros Numeros = new TNumeros();
		cLote = ""
				+ Numeros
						.getNumeroSinSeparador(new Integer(this.getIAnio()), 4)
				+ "/"
				+ Numeros.getNumeroSinSeparador(
						new Integer(this.getICveLoteCualita()), 5)
				+ "-"
				+ Numeros.getNumeroSinSeparador(
						new Integer(this.getICveExamCualita()), 2);
		return cLote;
	}

	public int getICveAnalisis() {
		return iCveAnalisis;
	}

	public void setICveAnalisis(int iCveAnalisis) {
		this.iCveAnalisis = iCveAnalisis;
	}

	public int getICarrusel() {
		return iCarrusel;
	}

	public void setICarrusel(int iCarrusel) {
		this.iCarrusel = iCarrusel;
	}

	public int getIPosicion() {
		return iPosicion;
	}

	public void setIPosicion(int iPosicion) {
		this.iPosicion = iPosicion;
	}

	public String getcResultado() {
		return cResultado;
	}

	public void setCResultado(String cResultado) {
		this.cResultado = cResultado;
	}

	public void setVEquipo(TVEQMEquipo Equipo) {
		this.VEquipo = Equipo;
	}

	public TVEQMEquipo getVEquipo() {
		return this.VEquipo;
	}

	public String getCAnio() {
		return String.valueOf(this.getIAnio()).substring(2);
	}

	public String getCAnalisis() {
		TNumeros Numeros = new TNumeros();
		return (this.getCAnio() + Numeros.getNumeroSinSeparador(new Integer(
				this.getICveAnalisis()), 6));
	}

}
