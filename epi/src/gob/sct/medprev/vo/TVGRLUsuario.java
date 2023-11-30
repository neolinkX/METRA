package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLUsuario
 * </p>
 * <p>
 * Description: VO de la entidad GRLUsuario que es Replica de ADMSEG (Solo
 * Lectura)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suárez Romero
 * @version 1.0
 */
public class TVGRLUsuario implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUsuario;
	private int iCveProfesion;
	private String cRFC;
	private String cSiglasProf;
	private String cCedula;
	private String cApPaterno;
	private String cApMaterno;
	private String cNombre;
	private String cSiglas;
	public TVSEGUsuario vUsuario;
	private String cProfesion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUsuario);
		return pk;
	}

	public TVGRLUsuario() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUsuario", String.valueOf(iCveUsuario));
		hmfieldsTable.put("iCveProfesion", String.valueOf(iCveProfesion));
		hmfieldsTable.put("cRFC", cRFC);
		hmfieldsTable.put("cSiglasProf", cSiglasProf);
		hmfieldsTable.put("cCedula", cCedula);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cSiglas", cSiglas);
		hmfieldsTable.put("vUsuario", this.vUsuario);
		return hmfieldsTable;
	}

	public int getICveUsuario() {
		return iCveUsuario;
	}

	public void setICveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
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

	public String getCRFC() {
		return cRFC;
	}

	public void setCRFC(String cRFC) {
		this.cRFC = cRFC;
	}

	public String getCSiglasProf() {
		return cSiglasProf;
	}

	public void setCSiglasProf(String cSiglasProf) {
		this.cSiglasProf = cSiglasProf;
	}

	public int getICveProfesion() {
		return iCveProfesion;
	}

	public void setICveProfesion(int iCveProfesion) {
		this.iCveProfesion = iCveProfesion;
	}

	public String getCCedula() {
		return cCedula;
	}

	public void setCCedula(String cCedula) {
		this.cCedula = cCedula;
	}

	public String getCSiglas() {
		return cSiglas;
	}

	public void setCSiglas(String cSiglas) {
		this.cSiglas = cSiglas;
	}

	public TVSEGUsuario getVUsuario() {
		return vUsuario;
	}

	public void setVUsuario(TVSEGUsuario vUsuario) {
		this.vUsuario = vUsuario;
	}

	public String getCProfesion() {
		return cProfesion;
	}

	public void setCProfesion(String cProfesion) {
		this.cProfesion = cProfesion;
	}

}
