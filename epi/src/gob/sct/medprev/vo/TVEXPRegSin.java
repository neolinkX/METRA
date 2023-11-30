package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * 
 * @author AG SA junio 2011
 * 
 */
public class TVEXPRegSin implements HashBeanInterface {
	private BeanPK pk;
	private int iCveServicio;
	private int iCveRama;
	private int iCveSintoma;
	private int iCveRegla;
	private int RdInfo;
	private int RdAccion;
	private int lActivo;
	private double iMayorA;
	private double iIgualA;
	private double iMenorA;
	private int Logica;
	private int lDictamenS;
	private int lDictamenF;
	private int iCveUsugenera;
	private java.sql.Timestamp dtGenerado;
	private String cAlerta;
	private String cdscRegla;
	private int lDependiente;
	private String cPregunta;
	private String cDscBreve;
	private int iCveMdoTrans;
	private int iCveCategoria;

	// Adicionales
	private String cMdoTrans;
	private String cCategorias;
	private String cServicios;
	private String cRama;
	private int iCveTpoResp;
	private int iCveUsuario;
	private int sintomaDep;
	private int iCveExpediente;
	private int iNumExamen;
	
	private double iDvalorIni;
	private int LogicaR;
	private String cCaracterR;
	
	private String cUsuario;
	

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVEXPRegSin() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("iCveSintoma", "" + iCveSintoma);
		hmfieldsTable.put("iCveRegla", "" + iCveRegla);
		hmfieldsTable.put("RdInfo", "" + RdInfo);
		hmfieldsTable.put("RdAccion", RdAccion);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("iMayorA", "" + iMayorA);
		hmfieldsTable.put("iIgualA", "" + iIgualA);
		hmfieldsTable.put("iMenorA", iMenorA);
		hmfieldsTable.put("Logica", "" + Logica);
		hmfieldsTable.put("lDictamenS", "" + lDictamenS);
		hmfieldsTable.put("lDictamenF", "" + lDictamenF);
		hmfieldsTable.put("iCveUsugenera", iCveUsugenera);
		hmfieldsTable.put("dtGenerado", "" + dtGenerado);
		hmfieldsTable.put("cAlerta", "" + cAlerta);
		hmfieldsTable.put("cdscRegla", "" + cdscRegla);
		hmfieldsTable.put("lDependiente", lDependiente);
		hmfieldsTable.put("cPregunta", cPregunta);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cMdoTrans", cMdoTrans);
		hmfieldsTable.put("cCategorias", cCategorias);
		hmfieldsTable.put("cServicios", cServicios);
		hmfieldsTable.put("cRama", cRama);
		hmfieldsTable.put("iCveTpoResp", iCveTpoResp);
		hmfieldsTable.put("iCveUsuario", iCveUsuario);
		hmfieldsTable.put("iCveMdoTrans", iCveMdoTrans);
		hmfieldsTable.put("iCveCategoria", iCveCategoria);
		hmfieldsTable.put("sintomaDep", sintomaDep);
		hmfieldsTable.put("iCveExpediente", iCveExpediente);
		hmfieldsTable.put("iNumExamen", iNumExamen);
		hmfieldsTable.put("iDvalorIni", iDvalorIni);
		hmfieldsTable.put("LogicaR", "" + LogicaR);
		hmfieldsTable.put("cCaracterR", cCaracterR);
		hmfieldsTable.put("cUsuario", cUsuario);
		return hmfieldsTable;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getICveRama() {
		return iCveRama;
	}

	public void setICveRama(int iCveRama) {
		this.iCveRama = iCveRama;
	}

	public int getICveSintoma() {
		return iCveSintoma;
	}

	public void setICveSintoma(int iCveSintoma) {
		this.iCveSintoma = iCveSintoma;
	}

	public int getICveRegla() {
		return iCveRegla;
	}

	public void setICveRegla(int iCveRegla) {
		this.iCveRegla = iCveRegla;
	}

	public int getRdInfo() {
		return RdInfo;
	}

	public void setRdInfo(int RdInfo) {
		this.RdInfo = RdInfo;
	}

	public int getRdAccion() {
		return RdAccion;
	}

	public void setRdAccion(int RdAccion) {
		this.RdAccion = RdAccion;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public double getIMayorA() {
		return iMayorA;
	}

	public void setIMayorA(double iMayorA) {
		this.iMayorA = iMayorA;
	}

	public double getIIgualA() {
		return iIgualA;
	}

	public void setIIgualA(double iIgualA) {
		this.iIgualA = iIgualA;
	}

	public double getIMenorA() {
		return iMenorA;
	}

	public void setIMenorA(double iMenorA) {
		this.iMenorA = iMenorA;
	}

	public double getIDvalorIni() {
		return iDvalorIni;
	}

	public void setIDvalorIni(double iDvalorIni) {
		this.iDvalorIni = iDvalorIni;
	}
	
	public int getLogica() {
		return Logica;
	}

	public void setLogica(int Logica) {
		this.Logica = Logica;
	}

	public int getLDictamenS() {
		return lDictamenS;
	}

	public void setLDictamenS(int lDictamenS) {
		this.lDictamenS = lDictamenS;
	}

	public int getLDictamenF() {
		return lDictamenF;
	}

	public void setLDictamenF(int lDictamenF) {
		this.lDictamenF = lDictamenF;
	}

	public int getICveUsugenera() {
		return iCveUsugenera;
	}

	public void setICveUsugenera(int iCveUsugenera) {
		this.iCveUsugenera = iCveUsugenera;
	}

	public java.sql.Timestamp getDtGenerado() {
		return dtGenerado;
	}

	public void setDtGenerado(java.sql.Timestamp dtGenerado) {
		this.dtGenerado = dtGenerado;
	}

	public String getCAlerta() {
		return cAlerta;
	}

	public void setCAlerta(String cAlerta) {
		this.cAlerta = cAlerta;
	}

	public String getCdscRegla() {
		return cdscRegla;
	}

	public void setCdscRegla(String cdscRegla) {
		this.cdscRegla = cdscRegla;
	}

	public int getLDependiente() {
		return lDependiente;
	}

	public void setLDependiente(int lDependiente) {
		this.lDependiente = lDependiente;
	}

	public String getCPregunta() {
		return cPregunta;
	}

	public void setCPregunta(String cPregunta) {
		this.cPregunta = cPregunta;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCMdoTrans() {
		return cMdoTrans;
	}

	public void setCMdoTrans(String cMdoTrans) {
		this.cMdoTrans = cMdoTrans;
	}

	public String getCCategorias() {
		return cCategorias;
	}

	public void setCCategorias(String cCategorias) {
		this.cCategorias = cCategorias;
	}

	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
	}

	public int getICveusuario() {
		return iCveUsuario;
	}

	public void setICveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
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

	public String getCServicios() {
		return cServicios;
	}

	public void setCServicios(String cServicios) {
		this.cServicios = cServicios;
	}

	public String getCRama() {
		return cRama;
	}

	public void setCRama(String cRama) {
		this.cRama = cRama;
	}	
	
	public int getSintomaDep() {
		return sintomaDep;
	}

	public void setSintomaDep(int sintomaDep) {
		this.sintomaDep = sintomaDep;
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
	
	
	public int getLogicaR() {
		return LogicaR;
	}

	public void setLogicaR(int LogicaR) {
		this.LogicaR = LogicaR;
	}

	public String getCCaracterR() {
		return cCaracterR;
	}

	public void setCCaracterR(String cCaracterR) {
		this.cCaracterR = cCaracterR;
	}


	public String getCUsuario() {
		return cUsuario;
	}

	public void setCUsuario(String cUsuario) {
		this.cUsuario = cUsuario;
	}
	
	
}
