package gob.sct.medprev.vo;

import java.text.*;
import java.util.*;

import com.micper.sql.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Value Object VEHSolicitud
 * </p>
 * <p>
 * Description: Control de Vehículos - Solicitud
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */
public class TVVEHSolicitud implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveUniMed;
	private int iCveSolicitud;
	private java.sql.Date dtRegistro;
	private java.sql.Date dtSolicitud;
	private java.sql.Timestamp tsSolicitud;
	private int iCveUsuSolic;
	private int iCveModulo;
	private int iCveArea;
	private int iCveMotivo;
	private String cDestino;
	private String cLicencia;
	private java.sql.Date dtVenceLic;
	private int iTmpAsignado;
	private int iCveTpoVehiculo;
	private java.sql.Date dtAsignado;
	private int iCveUsuAsigna;
	private int lAsignado;
	private java.sql.Date dtEntrega;
	private java.sql.Date dtCancelacion;
	private int iCveVehiculo;
	private int iKmInicial;
	private int iKmFinal;
	private String cDscUniMed;
	private String cDscModulo;
	private String cDscArea;
	private String cDscBreveTpoVeh;
	private int iCveEtapaSolic;
	private int lLicPermanente;
	private String cDscMotivo;
	private String cDscUsuSolic;
	private String cDscUsuAsigna;
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private int iCveMarca;
	private int iCveModelo;
	private int iCveTpoVehiculoVEH;
	private int iCveUsuConductor;
	private String cDscUsuConductor;
	private String cDscMarca;
	private String cDscModelo;
	private int iAnioVeh;
	private String cPlacas;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveUniMed);
		pk.add("" + iCveSolicitud);
		return pk;
	}

	public TVVEHSolicitud() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		TFechas dtFecha = new TFechas();
		if (dtRegistro != null
				&& dtRegistro.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtRegistro",
					dtFecha.getFechaDDMMYYYY(dtRegistro, ""));

		if (dtSolicitud != null
				&& dtSolicitud.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtSolicitud",
					dtFecha.getFechaDDMMYYYY(dtSolicitud, ""));

		if (dtVenceLic != null
				&& dtVenceLic.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtVenceLic",
					dtFecha.getFechaDDMMYYYY(dtVenceLic, ""));

		if (dtAsignado != null
				&& dtAsignado.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtAsignado",
					dtFecha.getFechaDDMMYYYY(dtAsignado, ""));

		if (dtEntrega != null
				&& dtEntrega.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtEntrega",
					dtFecha.getFechaDDMMYYYY(dtEntrega, ""));

		if (dtCancelacion != null
				&& dtCancelacion.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtCancelacion",
					dtFecha.getFechaDDMMYYYY(dtCancelacion, ""));

		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveSolicitud", "" + iCveSolicitud);
		if (tsSolicitud != null && tsSolicitud.toString().compareTo("") != 0
				&& tsSolicitud.toString().compareTo("null") != 0)
			hmfieldsTable.put("tsSolicitud", "" + sdf.format(tsSolicitud));
		hmfieldsTable.put("iCveUsuSolic", "" + iCveUsuSolic);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("cDestino", cDestino);
		hmfieldsTable.put("cLicencia", cLicencia);
		hmfieldsTable.put("iTmpAsignado", "" + iTmpAsignado);
		hmfieldsTable.put("iCveTpoVehiculo", "" + iCveTpoVehiculo);
		hmfieldsTable.put("iCveUsuAsigna", "" + iCveUsuAsigna);
		hmfieldsTable.put("lAsignado", "" + lAsignado);
		hmfieldsTable.put("iCveVehiculo", "" + iCveVehiculo);
		hmfieldsTable.put("iKmInicial", "" + iKmInicial);
		hmfieldsTable.put("iKmFinal", "" + iKmFinal);
		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cDscModulo", "" + cDscModulo);
		hmfieldsTable.put("cDscArea", "" + cDscArea);
		hmfieldsTable.put("cDscBreveTpoVeh", "" + cDscBreveTpoVeh);
		hmfieldsTable.put("iCveEtapaSolic", "" + iCveEtapaSolic);
		hmfieldsTable.put("lLicPermanente", "" + lLicPermanente);
		hmfieldsTable.put("cDscMotivo", "" + cDscMotivo);
		cDscUsuSolic = cNombre != null ? cNombre.equals("null") ? "" : cNombre
				: "";
		cDscUsuSolic += cApPaterno != null ? cApPaterno.equals("null") ? ""
				: " " + cApPaterno : "";
		cDscUsuSolic += cApMaterno != null ? cApMaterno.equals("null") ? ""
				: " " + cApMaterno : "";
		hmfieldsTable.put("cDscUsuSolic", "" + cDscUsuSolic);
		hmfieldsTable.put("cDscUsuAsigna", "" + cDscUsuAsigna);
		hmfieldsTable.put("cNombre", "" + cNombre);
		hmfieldsTable.put("cApPaterno", "" + cApPaterno);
		hmfieldsTable.put("cApMaterno", "" + cApMaterno);
		hmfieldsTable.put("iCveMarca", "" + iCveMarca);
		hmfieldsTable.put("iCveModelo", "" + iCveModelo);
		hmfieldsTable.put("iCveTpoVehiculoVEH", "" + iCveTpoVehiculoVEH);
		hmfieldsTable.put("iCveUsuConductor", "" + iCveUsuConductor);
		hmfieldsTable.put("cDscUsuConductor", "" + cDscUsuConductor);
		hmfieldsTable.put("cDscMarca", "" + cDscMarca);
		hmfieldsTable.put("cDscModelo", "" + cDscModelo);
		hmfieldsTable.put("iAnioVeh", "" + iAnioVeh);
		hmfieldsTable.put("cPlacas", "" + cPlacas);

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

	public java.sql.Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(java.sql.Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public java.sql.Date getDtSolicitud() {
		return dtSolicitud;
	}

	public void setDtSolicitud(java.sql.Date dtSolicitud) {
		this.dtSolicitud = dtSolicitud;
	}

	public java.sql.Timestamp getTsSolicitud() {
		return tsSolicitud;
	}

	public void setTsSolicitud(java.sql.Timestamp tsSolicitud) {
		this.tsSolicitud = tsSolicitud;
	}

	public int getICveUsuSolic() {
		return iCveUsuSolic;
	}

	public void setICveUsuSolic(int iCveUsuSolic) {
		this.iCveUsuSolic = iCveUsuSolic;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public int getICveArea() {
		return iCveArea;
	}

	public void setICveArea(int iCveArea) {
		this.iCveArea = iCveArea;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public String getCDestino() {
		return cDestino;
	}

	public void setCDestino(String cDestino) {
		this.cDestino = cDestino;
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

	public int getITmpAsignado() {
		return iTmpAsignado;
	}

	public void setITmpAsignado(int iTmpAsignado) {
		this.iTmpAsignado = iTmpAsignado;
	}

	public int getICveTpoVehiculo() {
		return iCveTpoVehiculo;
	}

	public void setICveTpoVehiculo(int iCveTpoVehiculo) {
		this.iCveTpoVehiculo = iCveTpoVehiculo;
	}

	public java.sql.Date getDtAsignado() {
		return dtAsignado;
	}

	public void setDtAsignado(java.sql.Date dtAsignado) {
		this.dtAsignado = dtAsignado;
	}

	public int getICveUsuAsigna() {
		return iCveUsuAsigna;
	}

	public void setICveUsuAsigna(int iCveUsuAsigna) {
		this.iCveUsuAsigna = iCveUsuAsigna;
	}

	public int getLAsignado() {
		return lAsignado;
	}

	public void setLAsignado(int lAsignado) {
		this.lAsignado = lAsignado;
	}

	public java.sql.Date getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(java.sql.Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public java.sql.Date getDtCancelacion() {
		return dtCancelacion;
	}

	public void setDtCancelacion(java.sql.Date dtCancelacion) {
		this.dtCancelacion = dtCancelacion;
	}

	public int getICveVehiculo() {
		return iCveVehiculo;
	}

	public void setICveVehiculo(int iCveVehiculo) {
		this.iCveVehiculo = iCveVehiculo;
	}

	public int getIKmInicial() {
		return iKmInicial;
	}

	public void setIKmInicial(int iKmInicial) {
		this.iKmInicial = iKmInicial;
	}

	public int getIKmFinal() {
		return iKmFinal;
	}

	public void setIKmFinal(int iKmFinal) {
		this.iKmFinal = iKmFinal;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}

	public String getCDscArea() {
		return cDscArea;
	}

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

	public String getCDscBreveTpoVeh() {
		return cDscBreveTpoVeh;
	}

	public void setCDscBreveTpoVeh(String cDscBreveTpoVeh) {
		this.cDscBreveTpoVeh = cDscBreveTpoVeh;
	}

	public int getICveEtapaSolic() {
		return iCveEtapaSolic;
	}

	public void setICveEtapaSolic(int iCveEtapaSolic) {
		this.iCveEtapaSolic = iCveEtapaSolic;
	}

	public int getLLicPermanente() {
		return lLicPermanente;
	}

	public void setLLicPermanente(int lLicPermanente) {
		this.lLicPermanente = lLicPermanente;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public String getCDscUsuSolic() {
		return cDscUsuSolic;
	}

	public void setCDscUsuSolic(String cDscUsuSolic) {
		this.cDscUsuSolic = cDscUsuSolic;
	}

	public String getCDscUsuAsigna() {
		return cDscUsuAsigna;
	}

	public void setCDscUsuAsigna(String cDscUsuAsigna) {
		this.cDscUsuAsigna = cDscUsuAsigna;
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

	public int getICveMarca() {
		return iCveMarca;
	}

	public void setICveMarca(int iCveMarca) {
		this.iCveMarca = iCveMarca;
	}

	public int getICveModelo() {
		return iCveModelo;
	}

	public void setICveModelo(int iCveModelo) {
		this.iCveModelo = iCveModelo;
	}

	public int getICveTpoVehiculoVEH() {
		return iCveTpoVehiculoVEH;
	}

	public void setICveTpoVehiculoVEH(int iCveTpoVehiculoVEH) {
		this.iCveTpoVehiculoVEH = iCveTpoVehiculoVEH;
	}

	public int getICveUsuConductor() {
		return iCveUsuConductor;
	}

	public void setICveUsuConductor(int iCveUsuConductor) {
		this.iCveUsuConductor = iCveUsuConductor;
	}

	public String getCDscUsuConductor() {
		return cDscUsuConductor;
	}

	public void setCDscUsuConductor(String cDscUsuConductor) {
		this.cDscUsuConductor = cDscUsuConductor;
	}

	public String getCDscMarca() {
		return cDscMarca;
	}

	public String getCDscModelo() {
		return cDscModelo;
	}

	public void setCDscMarca(String cDscMarca) {
		this.cDscMarca = cDscMarca;
	}

	public void setCDscModelo(String cDscModelo) {
		this.cDscModelo = cDscModelo;
	}

	public String getCPlacas() {
		return cPlacas;
	}

	public void setCPlacas(String cPlacas) {
		this.cPlacas = cPlacas;
	}

	public int getIAnioVeh() {
		return iAnioVeh;
	}

	public void setIAnioVeh(int iAnioVeh) {
		this.iAnioVeh = iAnioVeh;
	}

}
