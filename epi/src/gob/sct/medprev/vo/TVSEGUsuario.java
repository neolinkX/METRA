package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object SEGUsuario
 * </p>
 * <p>
 * Description: VO de la entidad SEGUsuario que es Replica de ADMSEG (Solo
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
public class TVSEGUsuario implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUsuario;
	private java.sql.Date dtRegistro;
	private String cUsuario;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private String cCalle;
	private String cColonia;
	private int iCvePais;
	private String cDscPais;
	private int iCveEntidadFed;
	private String cDscEntidadFed;
	private int iCveMunicipio;
	private String cDscMunicipio;
	private int iCodigoPostal;
	private String cTelefono;
	private int lBloqueado;
	private String cPassword;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUsuario);
		return pk;
	}

	public TVSEGUsuario() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUsuario", "" + iCveUsuario);
		hmfieldsTable.put("dtRegistro", "" + dtRegistro);
		hmfieldsTable.put("cUsuario", cUsuario);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		hmfieldsTable.put("cCalle", cCalle);
		hmfieldsTable.put("cColonia", cColonia);
		hmfieldsTable.put("iCvePais", "" + iCvePais);
		hmfieldsTable.put("cDscPais", "" + cDscPais);
		hmfieldsTable.put("iCveEntidadFed", "" + iCveEntidadFed);
		hmfieldsTable.put("cDscEntidadFed", "" + cDscEntidadFed);
		hmfieldsTable.put("iCveMunicipio", "" + iCveMunicipio);
		hmfieldsTable.put("cDscMunicipio", "" + cDscMunicipio);
		hmfieldsTable.put("iCodigoPostal", "" + iCodigoPostal);
		hmfieldsTable.put("cTelefono", cTelefono);
		hmfieldsTable.put("lBloqueado", lBloqueado == 0 ? "No" : "Si");
		hmfieldsTable.put("cNomCompleto", cNombre + " " + cApPaterno + " "
				+ cApMaterno);
		return hmfieldsTable;
	}

	public int getICveUsuario() {
		return iCveUsuario;
	}

	public void setICveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
	}

	public java.sql.Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(java.sql.Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getCUsuario() {
		return cUsuario;
	}

	public void setCUsuario(String cUsuario) {
		this.cUsuario = cUsuario;
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

	public String getCCalle() {
		return cCalle;
	}

	public void setCCalle(String cCalle) {
		this.cCalle = cCalle;
	}

	public String getCColonia() {
		return cColonia;
	}

	public void setCColonia(String cColonia) {
		this.cColonia = cColonia;
	}

	public int getICvePais() {
		return iCvePais;
	}

	public void setICvePais(int iCvePais) {
		this.iCvePais = iCvePais;
	}

	public int getICveEntidadFed() {
		return iCveEntidadFed;
	}

	public void setICveEntidadFed(int iCveEntidadFed) {
		this.iCveEntidadFed = iCveEntidadFed;
	}

	public int getICveMunicipio() {
		return iCveMunicipio;
	}

	public void setICveMunicipio(int iCveMunicipio) {
		this.iCveMunicipio = iCveMunicipio;
	}

	public int getICodigoPostal() {
		return iCodigoPostal;
	}

	public void setICodigoPostal(int iCodigoPostal) {
		this.iCodigoPostal = iCodigoPostal;
	}

	public String getCTelefono() {
		return cTelefono;
	}

	public void setCTelefono(String cTelefono) {
		this.cTelefono = cTelefono;
	}

	public int getLBloqueado() {
		return lBloqueado;
	}

	public void setLBloqueado(int lBloqueado) {
		this.lBloqueado = lBloqueado;
	}

	public String getCDscEntidadFed() {
		return cDscEntidadFed;
	}

	public String getCDscMunicipio() {
		return cDscMunicipio;
	}

	public String getCDscPais() {
		return cDscPais;
	}

	public void setCDscEntidadFed(String cDscEntidadFed) {
		this.cDscEntidadFed = cDscEntidadFed;
	}

	public void setCDscMunicipio(String cDscMunicipio) {
		this.cDscMunicipio = cDscMunicipio;
	}

	public void setCDscPais(String cDscPais) {
		this.cDscPais = cDscPais;
	}

	public String getCPassword() {
		return cPassword;
	}

	public void setCPassword(String cPassword) {
		this.cPassword = cPassword;
	}
}
