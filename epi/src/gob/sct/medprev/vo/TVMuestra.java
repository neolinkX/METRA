package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Value Object Muestra
 * </p>
 * <p>
 * Description: VO Tabla TOXMuestra
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernández García & Marco A. González Paz
 * @version 1.0
 */
public class TVMuestra implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveMuestra;
	private int iCvePbaRapida;
	private int iCveUniMed;
	private int iCveEnvio;
	private int iCveProceso;
	private int iCveMotivo;
	private java.sql.Date dtRecoleccion;
	private int iCveTipoRecep;
	private int lIntegraLote;
	private int iCveSituacion;
	private String cDscSituacion;
	private int lTemperaC;
	private int lAlteracion;
	private int lBajoObserva;
	private int iCveEmpresa;
	private String cOrdenar;
	private String cFiltrar;
	private java.sql.Date dtCaptura;
	private int iCveUsuCaptura;
	private int iCveUsuRecolec;
	private int iCvePersonal;
	private int iCveMotRecep;
	private int iCveAnalisis;
	private int lResultado;
	
	private int iCveHielera;
	private int iCveMensajeria;

	private String cObsMuestra;
	private String cDscUM;
	private String cDscProceso;
	private String cDscMotivo;
	private String cDscUsuRecolec;
	private String cDscUsuCaptura;
	private String cDscEmpresa;
	private String cDtRecoleccion;
	private String cDtCaptura;
	private String cDscMdoTrans;
	private String cNombreCompleto;
	private String cDscModulo;
	private String cResultado;

	TFechas dtFecha = new TFechas();
	private int iCveMdoTrans;
	private int iCveModulo;
	private int iEdad;
	public java.util.Vector vResultado;
	private int iCveLaboratorio;
	private int iSustPost;
	private int iSustConf;
	private String cDscTipoRecep;
	private String cDscMotRecep;
	private int lPresuntoPost;
	private java.sql.Date dtDeshecho;

	
	
	public ArrayList vResultPresuntivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMuestra);
		return pk;
	}

	public TVMuestra() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveMuestra", "" + iCveMuestra);
		hmfieldsTable.put("iCvePbaRapida", "" + iCvePbaRapida);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveEnvio", "" + iCveEnvio);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		if (dtRecoleccion != null
				&& dtRecoleccion.toString().compareToIgnoreCase("") != 0) {
			hmfieldsTable.put("dtRecoleccion",
					dtFecha.getFechaDDMMYYYY(dtRecoleccion, ""));
		}
		hmfieldsTable.put("iCveTipoRecep", "" + iCveTipoRecep);
		hmfieldsTable.put("lIntegraLote", "" + lIntegraLote);
		hmfieldsTable.put("iCveSituacion", "" + iCveSituacion);
		hmfieldsTable.put("cDscSituacion", "" + cDscSituacion);
		hmfieldsTable.put("lTemperaC", "" + lTemperaC);
		hmfieldsTable.put("lAlteracion", "" + lAlteracion);
		hmfieldsTable.put("lBajoObserva", "" + lBajoObserva);
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("cOrdenar", cOrdenar);
		hmfieldsTable.put("dtCaptura", "" + dtCaptura);
		hmfieldsTable.put("cFiltrar", cFiltrar);
		hmfieldsTable.put("iCveUsuCaptura", "" + iCveUsuCaptura);
		hmfieldsTable.put("iCveUsuRecolec", "" + iCveUsuRecolec);
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("cObsMuestra", "" + cObsMuestra);
		hmfieldsTable.put("cDscUM", cDscUM);
		hmfieldsTable.put("cDscProceso", cDscProceso);
		hmfieldsTable.put("cDscMotivo", cDscMotivo);
		hmfieldsTable.put("iCveMotRecep", "" + iCveMotRecep);
		hmfieldsTable.put("iCveAnalisis", "" + iCveAnalisis);
		hmfieldsTable.put("lResultado", "" + lResultado);
		hmfieldsTable.put("cDscUsuRecolec", cDscUsuRecolec);
		hmfieldsTable.put("cDscEmpresa", cDscEmpresa);
		hmfieldsTable.put("cDtRecoleccion", cDtRecoleccion);
		hmfieldsTable.put("cDtCaptura", cDtCaptura);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("cDscMdoTrans", cDscMdoTrans);
		hmfieldsTable.put("cNombreCompleto", cNombreCompleto);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("cDscModulo", cDscModulo);
		hmfieldsTable.put("iEdad", "" + iEdad);
		hmfieldsTable.put("cResultado", "" + cResultado);
		hmfieldsTable.put("cCveAnalisis", this.getCAnalisis());

		hmfieldsTable.put("cCveMuestra", this.getCMuestra());

		hmfieldsTable.put("iCveHielera", iCveHielera);
		hmfieldsTable.put("iCveMensajeria", iCveMensajeria);
		
		return hmfieldsTable;
	}

	public String getCOrdenar() {
		return cOrdenar;
	}

	public void setCOrdenar(String cOrdenar) {
		this.cOrdenar = cOrdenar;
	}

	public String getCFiltrar() {
		return cFiltrar;
	}

	public void setCFiltrar(String cFiltrar) {
		this.cFiltrar = cFiltrar;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveMuestra() {
		return iCveMuestra;
	}

	public void setICveMuestra(int iCveMuestra) {
		this.iCveMuestra = iCveMuestra;
	}

	public int getICvePbaRapida() {
		return iCvePbaRapida;
	}

	public void setICvePbaRapida(int iCvePbaRapida) {
		this.iCvePbaRapida = iCvePbaRapida;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveEnvio() {
		return iCveEnvio;
	}

	public void setICveEnvio(int iCveEnvio) {
		this.iCveEnvio = iCveEnvio;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public java.sql.Date getDtRecoleccion() {
		return dtRecoleccion;
	}

	public void setDtRecoleccion(java.sql.Date dtRecoleccion) {
		this.dtRecoleccion = dtRecoleccion;
	}

	public int getICveTipoRecep() {
		return iCveTipoRecep;
	}

	public void setICveTipoRecep(int iCveTipoRecep) {
		this.iCveTipoRecep = iCveTipoRecep;
	}

	public int getICveMotRecep() {
		return iCveMotRecep;
	}

	public void setICveMotRecep(int iCveMotRecep) {
		this.iCveMotRecep = iCveMotRecep;
	}

	public int getLIntegraLote() {
		return lIntegraLote;
	}

	public void setLIntegraLote(int lIntegraLote) {
		this.lIntegraLote = lIntegraLote;
	}

	public int getICveSituacion() {
		return iCveSituacion;
	}

	public void setICveSituacion(int iCveSituacion) {
		this.iCveSituacion = iCveSituacion;
	}

	public String getCDscSituacion() {
		return cDscSituacion;
	}

	public void setCDscSituacion(String cDscSituacion) {
		this.cDscSituacion = cDscSituacion;
	}

	public int getLTemperaC() {
		return lTemperaC;
	}

	public void setLTemperaC(int lTemperaC) {
		this.lTemperaC = lTemperaC;
	}

	public int getLAlteracion() {
		return lAlteracion;
	}

	public void setLAlteracion(int lAlteracion) {
		this.lAlteracion = lAlteracion;
	}

	public int getLBajoObserva() {
		return lBajoObserva;
	}

	public void setLBajoObserva(int lBajoObserva) {
		this.lBajoObserva = lBajoObserva;
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public java.sql.Date getDtCaptura() {
		return dtCaptura;
	}

	public void setDtCaptura(java.sql.Date dtCaptura) {
		this.dtCaptura = dtCaptura;
	}

	public String getCObsMuestra() {
		return cObsMuestra;
	}

	public void setCObsMuestra(String cObsMuestra) {
		this.cObsMuestra = cObsMuestra;
	}

	public int getICveUsuCaptura() {
		return iCveUsuCaptura;
	}

	public void setICveUsuCaptura(int iCveUsuCaptura) {
		this.iCveUsuCaptura = iCveUsuCaptura;
	}

	public void setICveUsuRecolec(int iCveUsuRecolec) {
		this.iCveUsuRecolec = iCveUsuRecolec;
	}

	public int getICveUsuRecolec() {
		return iCveUsuRecolec;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public String getCDscUsuRecolec() {
		return cDscUsuRecolec;
	}

	public void setCDscUsuRecolec(String cDscUsuRecolec) {
		this.cDscUsuRecolec = cDscUsuRecolec;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public void setdtRecoleccion(java.sql.Date dtRecoleccion) {
		this.dtRecoleccion = dtRecoleccion;
	}

	public java.sql.Date getdtRecoleccion() {
		return dtRecoleccion;
	}

	public int getICveAnalisis() {
		return iCveAnalisis;
	}

	public void setICveAnalisis(int iCveAnalisis) {
		this.iCveAnalisis = iCveAnalisis;
	}

	public int getLResultado() {
		return lResultado;
	}

	public void setLResultado(int lResultado) {
		this.lResultado = lResultado;
	}

	public String getCDscUM() {
		return cDscUM;
	}

	public void setCDscUM(String cDscUM) {
		this.cDscUM = cDscUM;
	}

	public String getCDscEmpresa() {
		return cDscEmpresa;
	}

	public void setCDscEmpresa(String cDscEmpresa) {
		this.cDscEmpresa = cDscEmpresa;
	}

	public String getCDtCaptura() {
		return cDtCaptura;
	}

	public String getCDtRecoleccion() {
		return cDtRecoleccion;
	}

	public void setCDtRecoleccion(String cDtRecoleccion) {
		this.cDtRecoleccion = cDtRecoleccion;
	}

	public void setCDtCaptura(String cDtCaptura) {
		this.cDtCaptura = cDtCaptura;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public String getCNombreCompleto() {
		return cNombreCompleto;
	}

	public void setCNombreCompleto(String cNombreCompleto) {
		this.cNombreCompleto = cNombreCompleto;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}

	public int getIEdad() {
		return iEdad;
	}

	public void setIEdad(int iEdad) {
		this.iEdad = iEdad;
	}

	public String getCResultado() {
		return cResultado;
	}

	public void setCResultado(String cResultado) {
		this.cResultado = cResultado;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public String getCAnio() {
		if (this.getIAnio() > 0)
			return String.valueOf(this.getIAnio()).substring(2);
		else
			return "";
	}

	public String getCAnalisis() {
		TNumeros Numeros = new TNumeros();
		return (this.getCAnio() + Numeros.getNumeroSinSeparador(new Integer(
				this.getICveAnalisis()), 6));
	}

	public int getISustPost() {
		return iSustPost;
	}

	public void setISustPost(int iSustPost) {
		this.iSustPost = iSustPost;
	}

	public int getISustConf() {
		return iSustConf;
	}

	public void setISustConf(int iSustConf) {
		this.iSustConf = iSustConf;
	}

	public String getCDscUsuCaptura() {
		return cDscUsuCaptura;
	}

	public void setCDscUsuCaptura(String cUsuCaptura) {
		this.cDscUsuCaptura = cUsuCaptura;
	}

	public String getCDscTipoRecep() {
		return cDscTipoRecep;
	}

	public void setCDscTipoRecep(String cDscTipoRecep) {
		this.cDscTipoRecep = cDscTipoRecep;
	}

	public String getCDscMotRecep() {
		return cDscMotRecep;
	}

	public void setCDscMotRecep(String cDscMotRecep) {
		this.cDscMotRecep = cDscMotRecep;
	}

	public int getLPresuntoPost() {
		return lPresuntoPost;
	}

	public void setLPresuntoPost(int lPresuntoPost) {
		this.lPresuntoPost = lPresuntoPost;
	}

	public String getCMuestra() {
		TNumeros Numeros = new TNumeros();
		String cValor = String.valueOf(this.iCveMuestra);
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
					Integer.valueOf(String.valueOf(this.getICveMuestra())), 6));
	}

	public java.sql.Date getDtDeshecho() {
		return dtDeshecho;
	}

	public void setDtDeshecho(java.sql.Date dtDeshecho) {
		this.dtDeshecho = dtDeshecho;
	}


	public int getICveHielera() {
		return iCveHielera;
	}

	public void setICveHielera(int iCveHielera) {
		this.iCveHielera = iCveHielera;
	}	


	public int getICveMensajeria() {
		return iCveMensajeria;
	}

	public void setICveMensajeria(int iCveMensajeria) {
		this.iCveMensajeria = iCveMensajeria;
	}	
	
	
}
