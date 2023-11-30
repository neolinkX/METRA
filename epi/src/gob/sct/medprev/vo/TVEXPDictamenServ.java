package gob.sct.medprev.vo;

import java.util.*; 

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPDictamenServ   
 * </p>
 * <p>
 * Description: Value Object para EXPDictamenServ
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 *  
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVEXPDictamenServ implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveMdoTrans;
	private int iCveCategoria;
	private int iCveServicio;
	private int lDictamen;
	private int iCveMotivo;
	private int iCveGrupo;
	private int iCvePuesto;
	private int iCveEspecialidad;
	private int iCveRecomendacion;
	private int iCveDiagnostico;
	private java.sql.Time tIDiagnostico;
	private String cNotaMedica;
	private String cObserRes;
	private String cDscServicio;
	private String cDscCategoria;
	private String cDscMdoTrans;
	private String cDscGrupo;
	private String cDscMotivo;
	private String cDscPuesto;
	private String cDscRecomendacion;
	private String cDscDiagnostico;
	private String cMotivacion;

	private String cGpoSang;
	private int lRH;
	private int lUsaLentes;
	private int lHipertension;
	private int lDiabetes;

	private int lAereo;
	private int lContacto;

	private int iCveUsuDictamen;
	private java.sql.Date dtInicioVig;
	private java.sql.Date dtFinVig;
	private int lDictamEm;
	private java.sql.Date dtDictaminado;
	private int iTmpoReexp;
	
	private int iCveProceso;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iCveCategoria);
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVEXPDictamenServ() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("lDictamen", "" + lDictamen);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("iCveGrupo", "" + iCveGrupo);
		hmfieldsTable.put("iCvePuesto", "" + iCvePuesto);
		hmfieldsTable.put("iCveEspecialidad", "" + iCveEspecialidad);
		hmfieldsTable.put("iCveRecomendacion", "" + iCveRecomendacion);
		hmfieldsTable.put("iCveDiagnostico", "" + iCveDiagnostico);
		hmfieldsTable.put("tIDiagnostico", "" + tIDiagnostico);
		hmfieldsTable.put("cNotaMedica", cNotaMedica);
		hmfieldsTable.put("cObserRes", cObserRes);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("cDscCategoria", cDscCategoria);
		hmfieldsTable.put("cDscMdoTrans", cDscMdoTrans);
		hmfieldsTable.put("cDscGrupo", cDscGrupo);
		hmfieldsTable.put("cDscMotivo", cDscMotivo);
		hmfieldsTable.put("cDscPuesto", cDscPuesto);
		hmfieldsTable.put("cDscRecomendacion", cDscRecomendacion);
		hmfieldsTable.put("cDscDiagnostico", cDscDiagnostico);
		hmfieldsTable.put("cMotivacion", cMotivacion);

		hmfieldsTable.put("cGpoSang", cGpoSang);
		hmfieldsTable.put("lRH", "" + lRH);
		hmfieldsTable.put("lUsaLentes", "" + lUsaLentes);
		hmfieldsTable.put("lHipertension", "" + lHipertension);
		hmfieldsTable.put("lDiabees", "" + lDiabetes);

		hmfieldsTable.put("lAereo", "" + lAereo);
		hmfieldsTable.put("lContacto", "" + lContacto);

		hmfieldsTable.put("iCveUsuDictamen", "" + iCveUsuDictamen);
		
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);

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

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getICveEspecialidad() {
		return iCveEspecialidad;
	}

	public void setICveEspecialidad(int iCveEspecialidad) {
		this.iCveEspecialidad = iCveEspecialidad;
	}

	public int getICveRecomendacion() {
		return iCveRecomendacion;
	}

	public void setICveRecomendacion(int iCveRecomendacion) {
		this.iCveRecomendacion = iCveRecomendacion;
	}

	public int getICveDiagnostico() {
		return iCveDiagnostico;
	}

	public void setICveDiagnostico(int iCveDiagnostico) {
		this.iCveDiagnostico = iCveDiagnostico;
	}

	public java.sql.Time getTIDiagnostico() {
		return tIDiagnostico;
	}

	public void setTIDiagnostico(java.sql.Time tIDiagnostico) {
		this.tIDiagnostico = tIDiagnostico;
	}

	public int getLDictamen() {
		return lDictamen;
	}

	public void setLDictamen(int lDictamen) {
		this.lDictamen = lDictamen;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public int getICveGrupo() {
		return iCveGrupo;
	}

	public void setICveGrupo(int iCveGrupo) {
		this.iCveGrupo = iCveGrupo;
	}

	public int getICvePuesto() {
		return iCvePuesto;
	}

	public void setICvePuesto(int iCvePuesto) {
		this.iCvePuesto = iCvePuesto;
	}

	public String getCNotaMedica() {
		return cNotaMedica;
	}

	public void setCNotaMedica(String cNotaMedica) {
		this.cNotaMedica = cNotaMedica;
	}

	public String getCObserRes() {
		return cObserRes;
	}

	public void setCObserRes(String cObserRes) {
		this.cObserRes = cObserRes;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public String getCDscCategoria() {
		return cDscCategoria;
	}

	public void setCDscCategoria(String cDscCategoria) {
		this.cDscCategoria = cDscCategoria;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public String getCDscGrupo() {
		return cDscGrupo;
	}

	public void setCDscGrupo(String cDscGrupo) {
		this.cDscGrupo = cDscGrupo;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public String getCDscPuesto() {
		return cDscPuesto;
	}

	public void setCDscPuesto(String cDscPuesto) {
		this.cDscPuesto = cDscPuesto;
	}

	public String getCDscRecomendacion() {
		return cDscRecomendacion;
	}

	public void setCDscRecomendacion(String cDscRecomendacion) {
		this.cDscRecomendacion = cDscRecomendacion;
	}

	public String getCDscDiagnostico() {
		return cDscDiagnostico;
	}

	public void setCDscDiagnostico(String cDscDiagnostico) {
		this.cDscDiagnostico = cDscDiagnostico;
	}

	public String getCMotivacion() {
		return cMotivacion;
	}

	public void setCMotivacion(String cMotivacion) {
		this.cMotivacion = cMotivacion;
	}

	public String getCGpoSang() {
		return cGpoSang;
	}

	public void setCGpoSang(String cGpoSang) {
		this.cGpoSang = cGpoSang;
	}

	public int getLDiabetes() {
		return lDiabetes;
	}

	public void setLDiabetes(int lDiabetes) {
		this.lDiabetes = lDiabetes;
	}

	public int getLHipertension() {
		return lHipertension;
	}

	public void setLHipertension(int lHipertension) {
		this.lHipertension = lHipertension;
	}

	public int getLRH() {
		return lRH;
	}

	public void setLRH(int lRH) {
		this.lRH = lRH;
	}

	public int getLUsaLentes() {
		return lUsaLentes;
	}

	public void setLUsaLentes(int lUsaLentes) {
		this.lUsaLentes = lUsaLentes;
	}

	public int getLAereo() {
		return lAereo;
	}

	public int getLContacto() {
		return lContacto;
	}

	public void setLContacto(int lContacto) {
		this.lContacto = lContacto;
	}

	public void setLAereo(int lAereo) {
		this.lAereo = lAereo;
	}

	public int getICveUsuDictamen() {
		return iCveUsuDictamen;
	}

	public void setICveUsuDictamen(int iCveUsuDictamen) {
		this.iCveUsuDictamen = iCveUsuDictamen;
	}

	public java.sql.Date getDtInicioVig() {
		return dtInicioVig;
	}

	public void setDtInicioVig(java.sql.Date dtInicioVig) {
		this.dtInicioVig = dtInicioVig;
	}

	public java.sql.Date getDtFinVig() {
		return dtFinVig;
	}

	public void setDtFinVig(java.sql.Date dtFinVig) {
		this.dtFinVig = dtFinVig;
	}

	public int getLDictamEm() {
		return lDictamEm;
	}

	public void setLDictamEm(int lDictamEm) {
		this.lDictamEm = lDictamEm;
	}

	public java.sql.Date getDtDictaminado() {
		return dtDictaminado;
	}

	public void setDtDictaminado(java.sql.Date dtDictaminado) {
		this.dtDictaminado = dtDictaminado;
	}

	public int getITmpoReexp() {
		return iTmpoReexp;
	}

	public void setITmpoReexp(int iTmpoReexp) {
		this.iTmpoReexp = iTmpoReexp;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}
	
}
