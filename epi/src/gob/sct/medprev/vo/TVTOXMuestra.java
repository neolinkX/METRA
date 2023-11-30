package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Value Object TOXMuestra
 * </p>
 * <p>
 * Description: Value object de la pantalla de muestras
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
public class TVTOXMuestra implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iAnioRec;
	private int iCveMuestra;
	private int iCveUniMed;
	private int iCveEnvio;
	private java.sql.Date dtRecoleccion;
	private int iCveProceso;
	private int iCveMotivo;
	private int iCveModTrans;
	private int iCveTipoRecep;
	private int iCveMotRecep;
	private String cDscProceso;
	private String cDscTipoRecep;
	private String cDscMotRecep;
	private String cdtRecoleccion;
	private int iOrden;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMuestra);
		return pk;
	}

	public TVTOXMuestra() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);

		// Nuevo campo
		hmfieldsTable.put("iAnioRec", "" + iAnioRec);
		hmfieldsTable.put("cCveMuestra", this.getCMuestra());

		hmfieldsTable.put("iCveMuestra", "" + iCveMuestra);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveEnvio", "" + iCveEnvio);
		hmfieldsTable.put("dtRecoleccion", "" + dtRecoleccion);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("iCveModTrans", "" + iCveModTrans);
		hmfieldsTable.put("iCveTipoRecep", "" + iCveTipoRecep);
		hmfieldsTable.put("iCveMotRecep", "" + iCveMotRecep);
		hmfieldsTable.put("cDscProceso", "" + cDscProceso);
		hmfieldsTable.put("cDscTipoRecep", "" + cDscTipoRecep);
		hmfieldsTable.put("cDscMotRecep", "" + cDscMotRecep);
		hmfieldsTable.put("cdtRecoleccion", "" + cdtRecoleccion);
		hmfieldsTable.put("iOrden", "" + iOrden);

		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveMuestra() {
		return iCveMuestra;
	}

	public void setICveMuestra(int iCveMuestra) {
		this.iCveMuestra = iCveMuestra;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveEnvio() {
		return iCveEnvio;
	}

	public void setICveEnvio(int iCveEnvio) {
		this.iCveEnvio = iCveEnvio;
	}

	public java.sql.Date getDtRecoleccion() {
		return dtRecoleccion;
	}

	public void setDtRecoleccion(java.sql.Date dtRecoleccion) {
		this.dtRecoleccion = dtRecoleccion;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public int getICveModTrans() {
		return iCveModTrans;
	}

	public void setICveModTrans(int iCveModTrans) {
		this.iCveModTrans = iCveModTrans;
	}

	public int getICveTipoRecep() {
		return iCveTipoRecep;
	}

	public void setICveTipoRecep(int iCveTipoRecep) {
		this.iCveTipoRecep = iCveTipoRecep;
	}

	public int getICveMotRecep() {
		return iCveMotRecep;
	}

	public void setICveMotRecep(int iCveMotRecep) {
		this.iCveMotRecep = iCveMotRecep;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public String getCDscMotRecep() {
		return cDscMotRecep;
	}

	public void setCDscMotRecep(String cDscMotRecep) {
		this.cDscMotRecep = cDscMotRecep;
	}

	public String getCDscTipoRecep() {
		return cDscTipoRecep;
	}

	public void setCDscTipoRecep(String cDscTipoRecep) {
		this.cDscTipoRecep = cDscTipoRecep;
	}

	public String getCdtRecoleccion() {
		return cdtRecoleccion;
	}

	public void setCdtRecoleccion(String cdtRecoleccion) {
		this.cdtRecoleccion = cdtRecoleccion;
	}

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

	public String getCAnio() {
		if (this.getIAnioRec() > 0)
			return String.valueOf(this.getIAnioRec()).substring(2);
		else
			return "";
	}

	public String getCMuestra() {
		TNumeros Numeros = new TNumeros();
		String cValor = String.valueOf(this.iCveMuestra);
		if (cValor.length() > 6) {

			// System.out.println("mayor de 6");
			Integer iAnio = Integer.valueOf(cValor.substring(0, 1));
			Integer iMuestra = Integer.valueOf(cValor.substring(1));

			if (cValor.length() == 7) {
				if (iAnio == 1) {
					// System.out.println("Es igual a 1");
					return (Numeros.getNumeroSinSeparador(iAnio, 1) + "0" + " " + Numeros
							.getNumeroSinSeparador(iMuestra, 5));
				} else {
					return (Numeros.getNumeroSinSeparador(iAnio, 2) + " " + Numeros
							.getNumeroSinSeparador(iMuestra, 6));
				}
			} else {
				// System.out.println("AÑO = " + iAnio + "    MUESTRA = " +
				// iMuestra);
				String CadenaM = "" + cValor.charAt(0) + cValor.charAt(1) + " "
						+ cValor.charAt(2) + cValor.charAt(3)
						+ cValor.charAt(4) + cValor.charAt(5)
						+ cValor.charAt(6) + cValor.charAt(7) + "";

				return (CadenaM);
				// return (Numeros.getNumeroSinSeparador(iAnio,2) + " " +
				// Numeros.getNumeroSinSeparador(iMuestra,6));
			}
		} else
			return (this.getCAnio() + " " + Numeros.getNumeroSinSeparador(
					Integer.valueOf(String.valueOf(this.getICveMuestra())), 6));
		// System.out.println("menor de 6");
	}

	// Campo Nuevo
	public int getIAnioRec() {
		return iAnioRec;
	}

	public void setIAnioRec(int iAnioRec) {
		this.iAnioRec = iAnioRec;
	}

}
