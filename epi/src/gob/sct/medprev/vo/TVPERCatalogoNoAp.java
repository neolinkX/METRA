package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>   
 * Title: Value Object PERCatalogoNoAp
 * </p>
 * <p>
 * Description: VO Tabla PERCatalogoNoAp
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
public class TVPERCatalogoNoAp implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUniMed;
	private int iCveUsuAgrega;
	private int iCveUsuBaja;
	private int iCvePersonal;
	private int iCveMdoTrans;
	private int iCveCategoria;
	private int iCveCatalogoNoAp;
	private int lVigente;
	private java.sql.Date dtInicio;
	private java.sql.Date dtFin;
	private int iPeriodo;
	private int iCveMotivoNoAp;
	private int iCveRubroNoAp;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private String cDscMotivo;
	private String cDtInicio;
	private String cDtFin;
	private String cDscMdoTrans;
	private String cDscCategoria;
	private String cDscUniMed;
	private String cDscMotivoNoAp;
	private String cDscRubroNoAp;
	
	/////Numero de Examen, cabe aclarar que EXAMAPLICA y PERCATALOGONOAP llevan un consecutivo de examenes independiente
	private int iNumExamen;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePersonal);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iCveCategoria);
		pk.add("" + iCveCatalogoNoAp);
		return pk;
	}

	public TVPERCatalogoNoAp() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveUsuAgrega", "" + iCveUsuAgrega);
		hmfieldsTable.put("iCveUsuBaja", "" + iCveUsuBaja);
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("iCveCatalogoNoAp", "" + iCveCatalogoNoAp);
		hmfieldsTable.put("lVigente", "" + lVigente);
		hmfieldsTable.put("dtInicio", "" + dtInicio);
		hmfieldsTable.put("dtFin", "" + dtFin);
		hmfieldsTable.put("iPeriodo", "" + iPeriodo);
		hmfieldsTable.put("iCveMotivoNoAp", "" + iCveMotivoNoAp);
		hmfieldsTable.put("iCveRubroNoAp", "" + iCveRubroNoAp);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cDscMotivo", cDscMotivo);
		hmfieldsTable.put("cDtInicio", cDtInicio);
		hmfieldsTable.put("cDtFin", cDtFin);
		hmfieldsTable.put("cDscMdoTrans", cDscMdoTrans);
		hmfieldsTable.put("cDscCategoria", cDscCategoria);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("cDscRubroNoAp", cDscRubroNoAp);
		
		hmfieldsTable.put("iNumExamen", iNumExamen);
		
		return hmfieldsTable;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveUsuAgrega() {
		return iCveUsuAgrega;
	}

	public void setICveUsuAgrega(int iCveUsuAgrega) {
		this.iCveUsuAgrega = iCveUsuAgrega;
	}

	public int getICveUsuBaja() {
		return iCveUsuBaja;
	}

	public void setICveUsuBaja(int iCveUsuBaja) {
		this.iCveUsuBaja = iCveUsuBaja;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getICveCategoria() {
		return iCveCategoria;
	}

	public void setICveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

	public int getICveCatalogoNoAp() {
		return iCveCatalogoNoAp;
	}

	public void setICveCatalogoNoAp(int iCveCatalogoNoAp) {
		this.iCveCatalogoNoAp = iCveCatalogoNoAp;
	}

	public int getLVigente() {
		return lVigente;
	}

	public void setLVigente(int lVigente) {
		this.lVigente = lVigente;
	}

	public java.sql.Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(java.sql.Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public java.sql.Date getDtFin() {
		return dtFin;
	}

	public void setDtFin(java.sql.Date dtFin) {
		this.dtFin = dtFin;
	}

	public int getIPeriodo() {
		return iPeriodo;
	}

	public void setIPeriodo(int iPeriodo) {
		this.iPeriodo = iPeriodo;
	}

	public int getICveMotivoNoAp() {
		return iCveMotivoNoAp;
	}

	public void setICveMotivoNoAp(int iCveMotivoNoAp) {
		this.iCveMotivoNoAp = iCveMotivoNoAp;
	}

	public int getICveRubroNoAp() {
		return iCveRubroNoAp;
	}

	public void setICveRubroNoAp(int iCveRubroNoAp) {
		this.iCveRubroNoAp = iCveRubroNoAp;
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

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public String getCDtInicio() {
		return cDtInicio;
	}

	public void setCDtInicio(String cDtInicio) {
		this.cDtInicio = cDtInicio;
	}

	public String getCDtFin() {
		return cDtFin;
	}

	public void setCDtFin(String cDtFin) {
		this.cDtFin = cDtFin;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public String getCDscCategoria() {
		return cDscCategoria;
	}

	public void setCDscCategoria(String cDscCategoria) {
		this.cDscCategoria = cDscCategoria;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscMotivoNoAp() {
		return cDscMotivoNoAp;
	}

	public void setCDscMotivoNoAp(String cDscMotivoNoAp) {
		this.cDscMotivoNoAp = cDscMotivoNoAp;
	}

	public String getCDscRubroNoAp() {
		return cDscRubroNoAp;
	}

	public void setCDscRubroNoAp(String cDscRubroNoAp) {
		this.cDscRubroNoAp = cDscRubroNoAp;
	}
	
	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}
	
}
