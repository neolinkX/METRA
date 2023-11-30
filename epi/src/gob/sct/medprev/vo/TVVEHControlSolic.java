package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object VEHControlSolic
 * </p>
 * <p>
 * Description: VO VEHControlSolic
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */
public class TVVEHControlSolic implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveUniMed;
	private int iCveSolicitud;
	private int iCveEtapaSolic;
	private int iCveConfControl;
	private float dValorIni;
	private int lLogico;
	private String cCaracter;
	private float dValorFin;
	private java.sql.Date dtRegistro;
	private String cDscEtapaSolic;
	private int iOrden;
	private String cDscTpoResp;
	private int iCveTpoResp;
	private String cEtiqueta;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveUniMed);
		pk.add("" + iCveSolicitud);
		pk.add("" + iCveEtapaSolic);
		pk.add("" + iCveConfControl);
		return pk;
	}

	public TVVEHControlSolic() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveSolicitud", "" + iCveSolicitud);
		hmfieldsTable.put("iCveEtapaSolic", "" + iCveEtapaSolic);
		hmfieldsTable.put("iCveConfControl", "" + iCveConfControl);
		hmfieldsTable.put("dValorIni", "" + dValorIni);
		hmfieldsTable.put("lLogico", "" + lLogico);
		hmfieldsTable.put("cCaracter", cCaracter);
		hmfieldsTable.put("dValorFin", "" + dValorFin);
		hmfieldsTable.put("dtRegistro", "" + dtRegistro);
		hmfieldsTable.put("cDscEtapaSolic", cDscEtapaSolic);
		hmfieldsTable.put("iOrden", "" + iOrden);
		hmfieldsTable.put("cDscTpoResp", cDscTpoResp);
		hmfieldsTable.put("iCveTpoResp", "" + iCveTpoResp);
		hmfieldsTable.put("cEtiqueta", cEtiqueta);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveSolicitud() {
		return iCveSolicitud;
	}

	public void setICveSolicitud(int iCveSolicitud) {
		this.iCveSolicitud = iCveSolicitud;
	}

	public int getICveEtapaSolic() {
		return iCveEtapaSolic;
	}

	public void setICveEtapaSolic(int iCveEtapaSolic) {
		this.iCveEtapaSolic = iCveEtapaSolic;
	}

	public int getICveConfControl() {
		return iCveConfControl;
	}

	public void setICveConfControl(int iCveConfControl) {
		this.iCveConfControl = iCveConfControl;
	}

	public float getDValorIni() {
		return dValorIni;
	}

	public void setDValorIni(float dValorIni) {
		this.dValorIni = dValorIni;
	}

	public int getLLogico() {
		return lLogico;
	}

	public void setLLogico(int lLogico) {
		this.lLogico = lLogico;
	}

	public String getCCaracter() {
		return cCaracter;
	}

	public void setCCaracter(String cCaracter) {
		this.cCaracter = cCaracter;
	}

	public float getDValorFin() {
		return dValorFin;
	}

	public void setDValorFin(float dValorFin) {
		this.dValorFin = dValorFin;
	}

	public java.sql.Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(java.sql.Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getCDscEtapaSolic() {
		return cDscEtapaSolic;
	}

	public void setCDscEtapaSolic(String cDscEtapaSolic) {
		this.cDscEtapaSolic = cDscEtapaSolic;
	}

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

	public String getCDscTpoResp() {
		return cDscTpoResp;
	}

	public void setCDscTpoResp(String cDscTpoResp) {
		this.cDscTpoResp = cDscTpoResp;
	}

	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
	}

	public String getCEtiqueta() {
		return cEtiqueta;
	}

	public void setCEtiqueta(String cEtiqueta) {
		this.cEtiqueta = cEtiqueta;
	}
}
