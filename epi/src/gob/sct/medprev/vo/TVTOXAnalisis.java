package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import com.micper.util.TNumeros;
import java.io.*;

/**
 * <p>
 * Title: Value Object TOXAnalisis
 * </p>
 * <p>
 * Description: Resultado del Exámen Toxicológico
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrejón Adame
 * @version 1.0
 */
public class TVTOXAnalisis implements HashBeanInterface {
	private BeanPK pk;
	private Integer iAnio;
	private Integer iCveLaboratorio;
	private Integer iCveAnalisis;
	private int iCveMuestra;
	private Integer iCveLoteCualita;
	private Integer lControl;
	private Integer iCveCtrolCalibra;
	private Integer lResultado;
	private Integer lPresuntoPost;
	private Integer lAutorizado;
	private java.sql.Date dtAutorizacion;
	private Integer iCveUsuAutoriza;
	private Integer iCveExamCualita;
	private Integer iCarrusel;
	private Integer iPosicion;
	private Integer iCveUniMed;
	private Integer iSustPost;
	private Integer iSustConf;
	private Integer iCveEnvio;
	private Integer iCveSustancia;
	private String cDscSustancia;
	private String cDscTipoRecep;
	private String cDscMotivo;
	private Integer iCveTipoRecep;
	private Integer iCveSituacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveAnalisis);
		return pk;
	}

	public TVTOXAnalisis() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveAnalisis", "" + iCveAnalisis);
		hmfieldsTable.put("iCveMuestra", "" + iCveMuestra);
		hmfieldsTable.put("iCveLoteCualita", "" + iCveLoteCualita);
		hmfieldsTable.put("lControl", "" + lControl);
		hmfieldsTable.put("iCveCtrolCalibra", "" + iCveCtrolCalibra);
		hmfieldsTable.put("lResultado", "" + lResultado);
		hmfieldsTable.put("lPresuntoPost", "" + lPresuntoPost);
		hmfieldsTable.put("lAutorizado", "" + lAutorizado);
		hmfieldsTable.put("dtAutorizacion", "" + dtAutorizacion);
		hmfieldsTable.put("iCveUsuAutoriza", "" + iCveUsuAutoriza);
		hmfieldsTable.put("iCveExamCaulita", "" + iCveExamCualita);
		hmfieldsTable.put("iCarrusel", "" + iCarrusel);
		hmfieldsTable.put("iPosicion", "" + iPosicion);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iSustPost", "" + iSustPost);
		hmfieldsTable.put("iSustConf", "" + iSustConf);
		hmfieldsTable.put("iCveEnvio", "" + iCveEnvio);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("cDscSustancia", "" + cDscSustancia);
		hmfieldsTable.put("cDscTipoRecep", "" + cDscTipoRecep);
		hmfieldsTable.put("cDscMotivo", "" + cDscMotivo);
		hmfieldsTable.put("iCveTipoRecep", "" + iCveTipoRecep);
		hmfieldsTable.put("iCveSituacion", "" + iCveSituacion);
		hmfieldsTable.put("cCveAnalisis", this.getCAnalisis());
		hmfieldsTable.put("cCveMuestra", this.getCMuestra());

		return hmfieldsTable;
	}

	public Integer getiAnio() {
		return iAnio;
	}

	public void setiAnio(Integer iAnio) {
		this.iAnio = iAnio;
	}

	public Integer getiCveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setiCveLaboratorio(Integer iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public Integer getiCveAnalisis() {
		return iCveAnalisis;
	}

	public void setiCveAnalisis(Integer iCveAnalisis) {
		this.iCveAnalisis = iCveAnalisis;
	}

	public int getiCveMuestra() {
		return iCveMuestra;
	}

	public void setiCveMuestra(int iCveMuestra) {
		this.iCveMuestra = iCveMuestra;
	}

	public Integer getiCveLoteCualita() {
		return iCveLoteCualita;
	}

	public void setiCveLoteCualita(Integer iCveLoteCualita) {
		this.iCveLoteCualita = iCveLoteCualita;
	}

	public Integer getlControl() {
		return lControl;
	}

	public void setlControl(Integer lControl) {
		this.lControl = lControl;
	}

	public Integer getiCveCtrolCalibra() {
		return iCveCtrolCalibra;
	}

	public void setiCveCtrolCalibra(Integer iCveCtrolCalibra) {
		this.iCveCtrolCalibra = iCveCtrolCalibra;
	}

	public Integer getlResultado() {
		return lResultado;
	}

	public void setlResultado(Integer lResultado) {
		this.lResultado = lResultado;
	}

	public Integer getlPresuntoPost() {
		return lPresuntoPost;
	}

	public void setlPresuntoPost(Integer lPresuntoPost) {
		this.lPresuntoPost = lPresuntoPost;
	}

	public Integer getlAutorizado() {
		return lAutorizado;
	}

	public void setlAutorizado(Integer lAutorizado) {
		this.lAutorizado = lAutorizado;
	}

	public void setdtAutorizacion(java.sql.Date adtAutorizacion) {
		this.dtAutorizacion = adtAutorizacion;
	}

	public java.sql.Date getdtAutorizacion() {
		return dtAutorizacion;
	}

	public Integer getiCveUsuAutoriza() {
		return iCveUsuAutoriza;
	}

	public void setiCveUsuAutoriza(Integer iCveUsuAutoriza) {
		this.iCveUsuAutoriza = iCveUsuAutoriza;
	}

	public Integer getiCveExamCualita() {
		return iCveExamCualita;
	}

	public void setiCveExamCualita(Integer iCveExamCualita) {
		this.iCveExamCualita = iCveExamCualita;
	}

	public Integer getiCarrusel() {
		return iCarrusel;
	}

	public void setiCarrusel(Integer iCarrusel) {
		this.iCarrusel = iCarrusel;
	}

	public Integer getiPosicion() {
		return iPosicion;
	}

	public void setiPosicion(Integer iPosicion) {
		this.iPosicion = iPosicion;
	}

	public Integer getiCveUniMed() {
		return iCveUniMed;
	}

	public void setiCveUniMed(Integer iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public Integer getISustPost() {
		return iSustPost;
	}

	public void setISustPost(Integer iSustPost) {
		this.iSustPost = iSustPost;
	}

	public Integer getISustConf() {
		return iSustConf;
	}

	public void setISustConf(Integer iSustConf) {
		this.iSustConf = iSustConf;
	}

	public Integer getICveEnvio() {
		return iCveEnvio;
	}

	public void setICveEnvio(Integer iCveEnvio) {
		this.iCveEnvio = iCveEnvio;
	}

	public Integer getICveSustancia() {
		return iCveSustancia;
	}

	public void setICveSustancia(Integer iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public String getCDscSustancia() {
		return cDscSustancia;
	}

	public void setCDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

	public String getCDscTipoRecep() {
		return cDscTipoRecep;
	}

	public void setCDscTipoRecep(String cDscTipoRecep) {
		this.cDscTipoRecep = cDscTipoRecep;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public Integer getICveTipoRecep() {
		return iCveTipoRecep;
	}

	public void setICveTipoRecep(Integer iCveTipoRecep) {
		this.iCveTipoRecep = iCveTipoRecep;
	}

	public Integer getICveSituacion() {
		return iCveSituacion;
	}

	public void setICveSituacion(Integer iCveSituacion) {
		this.iCveSituacion = iCveSituacion;
	}

	public String getCAnio() {
		return this.getiAnio().toString().substring(2);
	}

	public String getCAnalisis() {
		TNumeros Numeros = new TNumeros();
		return (this.getCAnio() + Numeros.getNumeroSinSeparador(
				this.getiCveAnalisis(), 6));
	}

	public String getCMuestra() {
		TNumeros Numeros = new TNumeros();
		String cValor = String.valueOf(this.iCveMuestra);
		/*
		 * if(cValor.length() > 6){ Integer iAnio =
		 * Integer.valueOf(cValor.substring(0,1)); Integer iMuestra =
		 * Integer.valueOf(cValor.substring(1)); return
		 * (Numeros.getNumeroSinSeparador(iAnio,2) + " " +
		 * Numeros.getNumeroSinSeparador(iMuestra,6)); } else return
		 * (this.getCAnio() + " " +
		 * Numeros.getNumeroSinSeparador(Integer.valueOf( String.valueOf(
		 * this.getiCveMuestra())) ,6));
		 */
		if (cValor.length() > 6) {
			Integer iAnio = Integer.valueOf(cValor.substring(0, 1));
			Integer iMuestra = Integer.valueOf(cValor.substring(1));

			if (cValor.length() == 7) {
				if (iAnio == 1) {
					// System.out.println("Es igual a 1");
					return (Numeros.getNumeroSinSeparador(iAnio, 1) + "0" + " " + Numeros
							.getNumeroSinSeparador(iMuestra, 5));
				} else {
					return (Numeros.getNumeroSinSeparador(iAnio, 2) + " " + Numeros
							.getNumeroSinSeparador(iMuestra, 6));
				}
			} else {
				// System.out.println("AÑO = " + iAnio + "    MUESTRA = " +
				// iMuestra);
				String CadenaM = "" + cValor.charAt(0) + cValor.charAt(1) + " "
						+ cValor.charAt(2) + cValor.charAt(3)
						+ cValor.charAt(4) + cValor.charAt(5)
						+ cValor.charAt(6) + cValor.charAt(7) + "";

				return (CadenaM);
				// return (Numeros.getNumeroSinSeparador(iAnio,2) + " " +
				// Numeros.getNumeroSinSeparador(iMuestra,6));
			}
		} else
			return (this.getCAnio() + " " + Numeros.getNumeroSinSeparador(
					Integer.valueOf(String.valueOf(this.getiCveMuestra())), 6));

	}

}
