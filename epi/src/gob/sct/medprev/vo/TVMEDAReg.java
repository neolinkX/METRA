package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * 
 * @author AG SA 26 de enero 2012
 */
public class TVMEDAReg implements HashBeanInterface {
	private BeanPK pk;
	private int iCveServicio;
	private int iCveRama;
	private int iCveSintoma;
	private int iCveRegla;
	private int iCveAccion;
	private int ServInterCon;
	private int iCveUsugenera;
	private java.sql.Timestamp dtGenerado;

	// Adicionales
	private String cDscServicio;
	private String cDscRama;
	private String cDscServInterCon;
	private String cPregunta;
	private String cDscBreve;

	// Otros
	private int iCveSintomaDep;
	private int iIgualA;
	private int iMayorA;
	private int iMenorA;
	private float fIgualA;
	private float fMayorA;
	private float fMenorA;
	private int iCveTpoResp;
	
	private int SintomaDep;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVMEDAReg() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("iCveSintoma", "" + iCveSintoma);
		hmfieldsTable.put("iCveRegla", "" + iCveRegla);
		hmfieldsTable.put("iCveAccion", "" + iCveAccion);
		hmfieldsTable.put("ServInterCon", "" + ServInterCon);
		hmfieldsTable.put("iCveUsugenera", iCveUsugenera);
		hmfieldsTable.put("dtGenerado", "" + dtGenerado);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("cDscRama", cDscRama);
		hmfieldsTable.put("cDscServInterCon", cDscServInterCon);
		hmfieldsTable.put("cPregunta", cPregunta);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("iCveSintomaDep", iCveSintomaDep);
		hmfieldsTable.put("iIgualA", iIgualA);
		hmfieldsTable.put("iIgualA", iMenorA);
		hmfieldsTable.put("iIgualA", iMayorA);
		hmfieldsTable.put("iCveTpoResp", iCveTpoResp);
		hmfieldsTable.put("SintomaDep", "" + SintomaDep);

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

	public int getICveAccion() {
		return iCveAccion;
	}

	public void setICveAccion(int iCveAccion) {
		this.iCveAccion = iCveAccion;
	}

	public int getServInterCon() {
		return ServInterCon;
	}

	public void setServInterCon(int ServInterCon) {
		this.ServInterCon = ServInterCon;
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

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public String getCDscRama() {
		return cDscRama;
	}

	public void setCDscRama(String cDscRama) {
		this.cDscRama = cDscRama;
	}

	public String getCDscServInterCon() {
		return cDscServInterCon;
	}

	public void setCDscServInterCon(String cDscServInterCon) {
		this.cDscServInterCon = cDscServInterCon;
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

	public int getICveSintomaDep() {
		return iCveSintomaDep;
	}

	public void setICveSintomaDep(int iCveSintomaDep) {
		this.iCveSintomaDep = iCveSintomaDep;
	}

	public int getIIgualA() {
		return iIgualA;
	}

	public void setIIgualA(int iIgualA) {
		this.iIgualA = iIgualA;
	}
	public int getIMayorA() {
		return iMayorA;
	}

	public void setIMayorA(int iMayorA) {
		this.iMayorA = iMayorA;
	}
	public int getIMenorA() {
		return iMenorA;
	}

	public void setIMenorA(int iMenorA) {
		this.iMenorA = iMenorA;
	}


	public float getFIgualA() {
		return fIgualA;
	}

	public void setFIgualA(float dIgualA) {
		this.fIgualA = dIgualA;
	}
	public float getFMayorA() {
		return fMayorA;
	}

	public void setFMayorA(float fMayorA) {
		this.fMayorA = fMayorA;
	}
	
	
	public float getFMenorA() {
		return fMenorA;
	}

	public void setFMenorA(float fMenorA) {
		this.fMenorA = fMenorA;
	}
	
	
	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
	}
}
