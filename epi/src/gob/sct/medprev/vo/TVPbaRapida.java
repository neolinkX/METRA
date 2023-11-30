package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object PbaRapida
 * </p>
 * <p>
 * Description: Datos Tabla TOXPbaRapida
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
public class TVPbaRapida implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCvePbaRapida;
	private java.sql.Date dtCaducidad;
	private java.sql.Date dtAsignacion;
	private int iCveUniMed;
	private int iCveProceso;
	private java.sql.Date dtAplicacion;
	private int iCvePersonal;
	private int iCveUsuAplica;
	private java.sql.Date dtCaptura;
	private int iCveUsuCaptura;
	private int iCveMotivo;
	private int lPosiblePost;
	private String cDscUM;
	private String cDscProceso;
	private String cDscMotivo;
	private String cDscUsuAplica;
	private String cDtCaducidad;
	private String cDtAsignacion;
	private String cDtAplicacion;
	private String cDtCaptura;

	private int iCveModulo;
	private int iCveMdoTrans;
	private String cDscModulo;
	private String cDscMdoTrans;

	//Drogas
	private int lAnfetaminas;
	private int lCannabis;
	private int lCocaina;
	private int lOpiaceos;
	private int lFenciclidina;
	private int lMetanfetaminas;
	
	
	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCvePbaRapida);
		return pk;
	}

	public TVPbaRapida() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCvePbaRapida", "" + iCvePbaRapida);
		hmfieldsTable.put("dtCaducidad", "" + dtCaducidad);
		hmfieldsTable.put("dtAsignacion", "" + dtAsignacion);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("dtAplicacion", "" + dtAplicacion);
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iCveUsuAplica", "" + iCveUsuAplica);
		hmfieldsTable.put("dtCaptura", "" + dtCaptura);
		hmfieldsTable.put("iCveUsuCaptura", "" + iCveUsuCaptura);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("lPosiblePost", "" + lPosiblePost);
		hmfieldsTable.put("cDscUM", cDscUM);
		hmfieldsTable.put("cDscProceso", cDscProceso);
		hmfieldsTable.put("cDscMotivo", cDscMotivo);
		hmfieldsTable.put("cDscUsuAplica", cDscUsuAplica);
		hmfieldsTable.put("cDtCaducidad", cDtCaducidad);
		hmfieldsTable.put("cDtAsignacion", cDtAsignacion);
		hmfieldsTable.put("cDtAplicacion", cDtAplicacion);
		hmfieldsTable.put("cDtCaptura", cDtCaptura);

		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("cDscModulo", cDscModulo);
		hmfieldsTable.put("cDscMdoTrans", cDscMdoTrans);
		
		//Drogas
		hmfieldsTable.put("lAnfetaminas", "" + lAnfetaminas);
		hmfieldsTable.put("lCannabis", "" + lCannabis);
		hmfieldsTable.put("lCocaina", "" + lCocaina);
		hmfieldsTable.put("lOpiaceos", "" + lOpiaceos);
		hmfieldsTable.put("lFenciclidina", "" + lFenciclidina);
		hmfieldsTable.put("lMetanfetaminas", "" + lMetanfetaminas);

		return hmfieldsTable;
	}

	public String getCDscUsuAplica() {
		return cDscUsuAplica;
	}

	public void setCDscUsuAplica(String cDscUsuAplica) {
		this.cDscUsuAplica = cDscUsuAplica;
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

	public String getCDscUM() {
		return cDscUM;
	}

	public void setCDscUM(String cDscUM) {
		this.cDscUM = cDscUM;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICvePbaRapida() {
		return iCvePbaRapida;
	}

	public void setICvePbaRapida(int iCvePbaRapida) {
		this.iCvePbaRapida = iCvePbaRapida;
	}

	public java.sql.Date getDtCaducidad() {
		return dtCaducidad;
	}

	public void setDtCaducidad(java.sql.Date dtCaducidad) {
		this.dtCaducidad = dtCaducidad;
	}

	public java.sql.Date getDtAsignacion() {
		return dtAsignacion;
	}

	public void setDtAsignacion(java.sql.Date dtAsignacion) {
		this.dtAsignacion = dtAsignacion;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public java.sql.Date getDtAplicacion() {
		return dtAplicacion;
	}

	public void setDtAplicacion(java.sql.Date dtAplicacion) {
		this.dtAplicacion = dtAplicacion;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getICveUsuAplica() {
		return iCveUsuAplica;
	}

	public void setICveUsuAplica(int iCveUsuAplica) {
		this.iCveUsuAplica = iCveUsuAplica;
	}

	public java.sql.Date getDtCaptura() {
		return dtCaptura;
	}

	public void setDtCaptura(java.sql.Date dtCaptura) {
		this.dtCaptura = dtCaptura;
	}

	public int getICveUsuCaptura() {
		return iCveUsuCaptura;
	}

	public void setICveUsuCaptura(int iCveUsuCaptura) {
		this.iCveUsuCaptura = iCveUsuCaptura;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public int getLPosiblePost() {
		return lPosiblePost;
	}

	public void setLPosiblePost(int lPosiblePost) {
		this.lPosiblePost = lPosiblePost;
	}

	public String getCDtAplicacion() {
		return cDtAplicacion;
	}

	public String getCDtAsignacion() {
		return cDtAsignacion;
	}

	public String getCDtCaducidad() {
		return cDtCaducidad;
	}

	public String getCDtCaptura() {
		return cDtCaptura;
	}

	public void setCDtCaptura(String cDtCaptura) {
		this.cDtCaptura = cDtCaptura;
	}

	public void setCDtCaducidad(String cDtCaducidad) {
		this.cDtCaducidad = cDtCaducidad;
	}

	public void setCDtAsignacion(String cDtAsignacion) {
		this.cDtAsignacion = cDtAsignacion;
	}

	public void setCDtAplicacion(String cDtAplicacion) {
		this.cDtAplicacion = cDtAplicacion;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}
	
	//Drogas

	public int getLAnfetaminas() {
		return lAnfetaminas;
	}

	public void setLAnfetaminas(int lAnfetaminas) {
		this.lAnfetaminas = lAnfetaminas;
	}
	

	public int getLCanabis() {
		return lCannabis;
	}

	public void setLCanabis(int lCannabis) {
		this.lCannabis = lCannabis;
	}
	

	public int getLCocaina() {
		return lCocaina;
	}

	public void setLCocaina(int lCocaina) {
		this.lCocaina = lCocaina;
	}
	

	public int getLOpiaceos() {
		return lOpiaceos;
	}

	public void setLOpiaceos(int lOpiaceos) {
		this.lOpiaceos = lOpiaceos;
	}
	

	public int getLFenciclidina() {
		return lFenciclidina;
	}

	public void setLFenciclidina(int lFenciclidina) {
		this.lFenciclidina = lFenciclidina;
	}
	

	public int getLMetanfetaminas() {
		return lMetanfetaminas;
	}

	public void setLMetanfetaminas(int lMetanfetaminas) {
		this.lMetanfetaminas = lMetanfetaminas;
	}
	
	
	
	
}


