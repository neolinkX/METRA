package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Value Object TOXEnvio
 * </p>
 * <p>
 * Description: VO de la Entidad TOXEnvio
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
public class TVTOXEnvio implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEnvio;
	private int iCveUniMed;
	private int iAnio;
	private int iCveLaboratorio;
	private int iCveUsuEnvia;
	private java.sql.Date dtEnvio;
	private int iCveTipoEnvio;
	private java.sql.Date dtRecepcion;
	private int iCveUsuRec;
	private String cObsEnvio;
	private String cObsRecep;
	private String cDscTipoEnvio;
	private String cdtEnvio;
	private String cDscUniMed;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private int iTotalEnviadas;
	private int iTotalRechazadas;
	private int iTotalRecibidas;
	private String cDscLaboratorio;
	private String cNombreRec;
	private String cApPaternoRec;
	private String cApMaternoRec;
	private int iTotalPendientes;
	private int iTotalPositivos;
	private int lResultado;
	public java.util.Vector vMuestra;
	private int iMaxOrden;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEnvio);
		pk.add("" + iCveUniMed);
		pk.add("" + iAnio);
		return pk;
	}

	public TVTOXEnvio() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		TFechas dtFecha = new TFechas();
		hmfieldsTable.put("iCveEnvio", "" + iCveEnvio);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveUsuEnvia", "" + iCveUsuEnvia);
		hmfieldsTable.put("dtEnvio", "" + dtEnvio);
		if (dtEnvio != null && dtEnvio.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable
					.put("dtEnvioC", dtFecha.getFechaDDMMYYYY(dtEnvio, ""));
		else
			hmfieldsTable.put("dtEnvioC", "null");

		hmfieldsTable.put("iCveTipoEnvio", "" + iCveTipoEnvio);
		if (dtRecepcion != null
				&& dtRecepcion.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtRecepcion",
					dtFecha.getFechaDDMMYYYY(dtRecepcion, ""));
		else
			hmfieldsTable.put("dtRecepcion", "null");

		hmfieldsTable.put("iCveUsuRec", "" + iCveUsuRec);
		hmfieldsTable.put("cObsEnvio", cObsEnvio);
		hmfieldsTable.put("cObsRecep", cObsRecep);
		hmfieldsTable.put("cDscTipoEnvio", "" + cDscTipoEnvio);
		hmfieldsTable.put("cdtEnvio", "" + cdtEnvio);
		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cNombre", "" + cNombre);
		hmfieldsTable.put("cApPaterno", "" + cApPaterno);
		hmfieldsTable.put("cApMaterno", "" + cApMaterno);
		hmfieldsTable.put("iTotalEnviadas", "" + iTotalEnviadas);
		hmfieldsTable.put("iTotalRechazadas", "" + iTotalRechazadas);
		hmfieldsTable.put("iTotalRecibidas", "" + iTotalRecibidas);
		hmfieldsTable.put("iTotalPendientes", "" + iTotalPendientes);
		hmfieldsTable.put("cDscLaboratorio", "" + cDscLaboratorio);

		hmfieldsTable.put("cNombreRec", cNombreRec);
		hmfieldsTable.put("cApPaternoRec", cApPaternoRec);
		hmfieldsTable.put("cApMaternoRec", cApMaternoRec);

		return hmfieldsTable;
	}

	public int getICveEnvio() {
		return iCveEnvio;
	}

	public void setICveEnvio(int iCveEnvio) {
		this.iCveEnvio = iCveEnvio;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getICveUsuEnvia() {
		return iCveUsuEnvia;
	}

	public void setICveUsuEnvia(int iCveUsuEnvia) {
		this.iCveUsuEnvia = iCveUsuEnvia;
	}

	public void setdtEnvio(java.sql.Date adtEnvio) {
		this.dtEnvio = adtEnvio;
	}

	public java.sql.Date getdtEnvio() {
		return dtEnvio;
	}

	public int getICveTipoEnvio() {
		return iCveTipoEnvio;
	}

	public void setICveTipoEnvio(int iCveTipoEnvio) {
		this.iCveTipoEnvio = iCveTipoEnvio;
	}

	public void setdtRecepcion(java.sql.Date adtRecepcion) {
		this.dtRecepcion = adtRecepcion;
	}

	public java.sql.Date getdtRecepcion() {
		return dtRecepcion;
	}

	public int getICveUsuRec() {
		return iCveUsuRec;
	}

	public void setICveUsuRec(int iCveUsuRec) {
		this.iCveUsuRec = iCveUsuRec;
	}

	public String getCObsEnvio() {
		return cObsEnvio;
	}

	public void setCObsEnvio(String cObsEnvio) {
		this.cObsEnvio = cObsEnvio;
	}

	public String getCObsRecep() {
		return cObsRecep;
	}

	public void setCObsRecep(String cObsRecep) {
		this.cObsRecep = cObsRecep;
	}

	public String getCDscTipoEnvio() {
		return cDscTipoEnvio;
	}

	public void setCDscTipoEnvio(String cDscTipoEnvio) {
		this.cDscTipoEnvio = cDscTipoEnvio;
	}

	public String getCdtEnvio() {
		return cdtEnvio;
	}

	public void setCdtEnvio(String cdtEnvio) {
		this.cdtEnvio = cdtEnvio;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
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

	public int getITotalEnviadas() {
		return iTotalEnviadas;
	}

	public void setITotalEnviadas(int iTotalEnviadas) {
		this.iTotalEnviadas = iTotalEnviadas;
	}

	public int getITotalRechazadas() {
		return iTotalRechazadas;
	}

	public void setITotalRechazadas(int iTotalRechazadas) {
		this.iTotalRechazadas = iTotalRechazadas;
	}

	public int getITotalRecibidas() {
		return iTotalRecibidas;
	}

	public void setITotalRecibidas(int iTotalRecibidas) {
		this.iTotalRecibidas = iTotalRecibidas;
	}

	public String getCDscLaboratorio() {
		return cDscLaboratorio;
	}

	public void setCDscLaboratorio(String cDscLaboratorio) {
		this.cDscLaboratorio = cDscLaboratorio;
	}

	public String getCNombreRec() {
		return cNombreRec;
	}

	public void setCNombreRec(String cNombreRec) {
		this.cNombreRec = cNombreRec;
	}

	public String getCApPaternoRec() {
		return cApPaternoRec;
	}

	public void setCApPaternoRec(String cApPaternoRec) {
		this.cApPaternoRec = cApPaternoRec;
	}

	public String getCApMaternoRec() {
		return cApMaternoRec;
	}

	public void setCApMaternoRec(String cApMaternoRec) {
		this.cApMaternoRec = cApMaternoRec;
	}

	public int getITotalPendientes() {
		return iTotalPendientes;
	}

	public void setITotalPendientes(int iTotalPendientes) {
		this.iTotalPendientes = iTotalPendientes;
	}

	public int getITotalPositivos() {
		return iTotalPositivos;
	}

	public void setITotalPositivos(int iTotalPositivos) {
		this.iTotalPositivos = iTotalPositivos;
	}

	public int getLResultado() {
		return lResultado;
	}

	public void setLResultado(int lResultado) {
		this.lResultado = lResultado;
	}

	public int getIMaxOrden() {
		return iMaxOrden;
	}

	public void setIMaxOrden(int iMaxOrden) {
		this.iMaxOrden = iMaxOrden;
	}

}
