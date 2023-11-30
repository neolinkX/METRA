package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object EMOExamen
 * </p>
 * <p>
 * Description: VO EMOExamen
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
public class TVEMOExamen implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveMomentoAp;
	private String cMatricula;
	private int iCvePaisOr;
	private int iCveEdoOr;
	private String cOrigen;
	private int iCvePaisDes;
	private int iCveEdoDes;
	private String cDestino;
	private int lSinLicencia;
	private String cLicencia;
	private java.sql.Date dtVenceLic;
	private int lLicVencida;
	
	//////////////Nuevos Campos MAGTIC EMO 25 Agosto 2015 - AG SA.	
	private int iCveSubModulo;
	private int iCveCapturaDelExamen;
	private int iCveUsuAplicoEMO;
	private String cMedDic;
	private String cCedula;
	private String iCveFolio;	
	private java.sql.Timestamp tIAplicacion;
	

	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private String cDscPaisOrg;
	private String cDscEstadoOrg;
	private String cDscPaisDes;
	private String cDscEstadoDes;
	
	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		return pk;
	}

	public TVEMOExamen() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveMomentoAp", "" + iCveMomentoAp);
		hmfieldsTable.put("cMatricula", cMatricula);
		hmfieldsTable.put("iCvePaisOr", "" + iCvePaisOr);
		hmfieldsTable.put("iCveEdoOr", "" + iCveEdoOr);
		hmfieldsTable.put("cOrigen", cOrigen);
		hmfieldsTable.put("iCvePaisDes", "" + iCvePaisDes);
		hmfieldsTable.put("iCveEdoDes", "" + iCveEdoDes);
		hmfieldsTable.put("cDestino", cDestino);
		hmfieldsTable.put("lSinLicencia", "" + lSinLicencia);
		hmfieldsTable.put("cLicencia", cLicencia);
		hmfieldsTable.put("dtVenceLic", "" + dtVenceLic);
		hmfieldsTable.put("lLicVencida", "" + lLicVencida);
		
		hmfieldsTable.put("iCveSubModulo", "" + iCveSubModulo);
		hmfieldsTable.put("iCveCapturaDelExamen", "" + iCveCapturaDelExamen);
		hmfieldsTable.put("iCveUsuAplicoEMO", "" + iCveUsuAplicoEMO);
		hmfieldsTable.put("cMedDic", "" + cMedDic);
		hmfieldsTable.put("cCedula", "" + cCedula);
		hmfieldsTable.put("iCveFolio", "" + iCveFolio);
		hmfieldsTable.put("tIAplicacion", "" + tIAplicacion);

		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cDscPaisOrg", cDscPaisOrg);
		hmfieldsTable.put("cDscEstadoOrg", cDscEstadoOrg);
		hmfieldsTable.put("cDscPaisDes", cDscPaisDes);
		hmfieldsTable.put("cDscEstadoDes", cDscEstadoDes);
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

	public int getICveMomentoAp() {
		return iCveMomentoAp;
	}

	public void setICveMomentoAp(int iCveMomentoAp) {
		this.iCveMomentoAp = iCveMomentoAp;
	}

	public String getCMatricula() {
		return cMatricula;
	}

	public void setCMatricula(String cMatricula) {
		this.cMatricula = cMatricula;
	}

	public int getICvePaisOr() {
		return iCvePaisOr;
	}

	public void setICvePaisOr(int iCvePaisOr) {
		this.iCvePaisOr = iCvePaisOr;
	}

	public int getICveEdoOr() {
		return iCveEdoOr;
	}

	public void setICveEdoOr(int iCveEdoOr) {
		this.iCveEdoOr = iCveEdoOr;
	}

	public String getCOrigen() {
		return cOrigen;
	}

	public void setCOrigen(String cOrigen) {
		this.cOrigen = cOrigen;
	}

	public int getICvePaisDes() {
		return iCvePaisDes;
	}

	public void setICvePaisDes(int iCvePaisDes) {
		this.iCvePaisDes = iCvePaisDes;
	}

	public int getICveEdoDes() {
		return iCveEdoDes;
	}

	public void setICveEdoDes(int iCveEdoDes) {
		this.iCveEdoDes = iCveEdoDes;
	}

	public String getCDestino() {
		return cDestino;
	}

	public void setCDestino(String cDestino) {
		this.cDestino = cDestino;
	}

	public int getLSinLicencia() {
		return lSinLicencia;
	}

	public void setLSinLicencia(int lSinLicencia) {
		this.lSinLicencia = lSinLicencia;
	}

	public String getCLicencia() {
		return cLicencia;
	}

	public void setCLicencia(String cLicencia) {
		this.cLicencia = cLicencia;
	}

	public java.sql.Date getDtVenceLic() {
		return dtVenceLic;
	}

	public void setDtVenceLic(java.sql.Date dtVenceLic) {
		this.dtVenceLic = dtVenceLic;
	}

	public int getLLicVencida() {
		return lLicVencida;
	}

	public void setLLicVencida(int lLicVencida) {
		this.lLicVencida = lLicVencida;
	}
	
	
	
	public void setICveSubModulo(int iCveSubModulo) {
		this.iCveSubModulo = iCveSubModulo;
	}

	public int getICveSubModulo() {
		return iCveSubModulo;
	}

	public void setICveCapturaDelExamen(int iCveCapturaDelExamen) {
		this.iCveCapturaDelExamen = iCveCapturaDelExamen;
	}

	public int getICveCapturaDelExamen() {
		return iCveCapturaDelExamen;
	}
	
	public void setICveUsuAplicoEMO(int iCveUsuAplicoEMO) {
		this.iCveUsuAplicoEMO = iCveUsuAplicoEMO;
	}

	public int getICveUsuAplicoEMO() {
		return iCveUsuAplicoEMO;
	}	

	public String getCMedDic() {
		return cMedDic;
	}

	public void setCMedDic(String cMedDic) {
		this.cMedDic = cMedDic;
	}
	
	public String getCCedula() {
		return cCedula;
	}

	public void setCCedula(String cCedula) {
		this.cCedula = cCedula;
	}
	
	public String getICveFolio() {
		return iCveFolio;
	}

	public void setICveFolio(String iCveFolio) {
		this.iCveFolio = iCveFolio;
	}
	

	public java.sql.Timestamp getTIAplicacion() {
		return tIAplicacion;
	}

	public void setTIAplicacion(java.sql.Timestamp tIAplicacion) {
		this.tIAplicacion = tIAplicacion;
	}
	

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCApPaterno() {
		return cApPaterno;
	}

	public void setCApPaterno(String cApPaterno) {
		this.cApPaterno = cApPaterno;
	}

	public String getCApMaterno() {
		return cApMaterno;
	}

	public void setCApMaterno(String cApMaterno) {
		this.cApMaterno = cApMaterno;
	}


	public String getCDscPaisOrg() {
		return cDscPaisOrg;
	}

	public void setCDscPaisOrg(String cDscPaisOrg) {
		this.cDscPaisOrg = cDscPaisOrg;
	}

	public void setCDscEstadoOrg(String cDscEstadoOrg) {
		this.cDscEstadoOrg = cDscEstadoOrg;
	}

	public String getCDscEstadoOrg() {
		return cDscEstadoOrg;
	}
	
	public String getCDscPaisDes() {
		return cDscPaisDes;
	}

	public void setCDscPaisDes(String cDscPaisDes) {
		this.cDscPaisDes = cDscPaisDes;
	}

	public void setCDscEstadoDes(String cDscEstadoDes) {
		this.cDscEstadoDes = cDscEstadoDes;
	}

	public String getCDscEstadoDes() {
		return cDscEstadoDes;
	}
}

