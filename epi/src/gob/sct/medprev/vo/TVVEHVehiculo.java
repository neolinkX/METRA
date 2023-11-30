package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;
import com.micper.util.TFechas;

/**
 * <p>
 * Title: Value Object VEHVehiculo
 * </p>
 * <p>
 * Description: VO para la administración de la información de la tabla
 * VEHVehiculo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hernández García
 * @version 1.0
 */
public class TVVEHVehiculo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveVehiculo;
	private int iCveTpoVehiculo;
	private int iCveMarca;
	private int iCveModelo;
	private int iAnioVeh;
	private String cNumSerie;
	private String cNumMotor;
	private String cInventario;
	private int iCveColor;
	private String cPlacas;
	private int iKmMantto;
	private int iMesMantto;
	private String cPoliza;
	private java.sql.Date dtInicioVig;
	private java.sql.Date dtFinVig;
	private java.sql.Date dtAlta;
	private int iCveEstadoVEH;
	private int lActivo;
	private int lBaja;
	private java.sql.Date dtBaja;
	private int iCveMtvoBaja;
	private String cDscTpoVehiculo;
	private String cDscMarca;
	private String cDscModelo;
	private String cDscMtvoBaja;
	private String cDscEstadoVEH;
	private String cDscColor;
	private String cDscUniMed;
	private int iCveTpoMantto;
	private int iCveUniMed;
	private int iKmFinal;
	private java.sql.Date dtIniMantto;
	private int iCveMantenimiento;
	private int iKilometraje;
	private java.sql.Date dtInicia;
	private int iKmGarantia;
	private int iMesGarantia;
	private String cCobertura;
	private String cAseguradora;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveVehiculo);
		return pk;
	}

	public TVVEHVehiculo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();

		TFechas dtFecha = new TFechas();

		if (dtInicioVig != null
				&& dtInicioVig.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtInicioVig",
					dtFecha.getFechaDDMMYYYY(dtInicioVig, ""));

		if (dtFinVig != null
				&& dtFinVig.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtFinVig",
					dtFecha.getFechaDDMMYYYY(dtFinVig, ""));

		if (dtAlta != null && dtAlta.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtAlta", dtFecha.getFechaDDMMYYYY(dtAlta, ""));

		if (dtBaja != null && dtBaja.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtBaja", dtFecha.getFechaDDMMYYYY(dtBaja, ""));

		if (dtIniMantto != null
				&& dtIniMantto.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtIniMantto",
					dtFecha.getFechaDDMMYYYY(dtIniMantto, ""));

		hmfieldsTable.put("iCveVehiculo", "" + iCveVehiculo);
		hmfieldsTable.put("iCveTpoVehiculo", "" + iCveTpoVehiculo);
		hmfieldsTable.put("iCveMarca", "" + iCveMarca);
		hmfieldsTable.put("iCveModelo", "" + iCveModelo);
		hmfieldsTable.put("iAnioVeh", "" + iAnioVeh);
		hmfieldsTable.put("cNumSerie", cNumSerie);
		hmfieldsTable.put("cNumMotor", cNumMotor);
		hmfieldsTable.put("cInventario", cInventario);
		hmfieldsTable.put("iCveColor", "" + iCveColor);
		hmfieldsTable.put("cPlacas", cPlacas);
		hmfieldsTable.put("iKmMantto", "" + iKmMantto);
		hmfieldsTable.put("iMesMantto", "" + iMesMantto);
		hmfieldsTable.put("cPoliza", cPoliza);
		hmfieldsTable.put("iCveEstadoVEH", "" + iCveEstadoVEH);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("lBaja", "" + lBaja);
		hmfieldsTable.put("iCveMtvoBaja", "" + iCveMtvoBaja);
		hmfieldsTable.put("cDscTpoVehiculo", cDscTpoVehiculo);
		hmfieldsTable.put("cDscMarca", cDscMarca);
		hmfieldsTable.put("cDscModelo", cDscModelo);
		hmfieldsTable.put("cDscMtvoBaja", cDscMtvoBaja);
		hmfieldsTable.put("cDscEstadoVEH", cDscEstadoVEH);
		hmfieldsTable.put("cDscColor", cDscColor);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("iCveTpoMantto", "" + iCveTpoMantto);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iKmFinal", "" + iKmFinal);
		// Datos del Mantenimiento:
		hmfieldsTable.put("iCveMantenimiento", "" + iCveMantenimiento);
		hmfieldsTable.put("iKilometraje", "" + iKilometraje);

		if (dtInicia != null
				&& dtInicia.toString().compareToIgnoreCase("") != 0)
			hmfieldsTable.put("dtInicia",
					dtFecha.getFechaDDMMYYYY(dtInicia, ""));

		hmfieldsTable.put("iKmGarantia", "" + iKmGarantia);
		hmfieldsTable.put("iMesGarantia", "" + iMesGarantia);
		hmfieldsTable.put("cCobertura", "" + cCobertura);
		hmfieldsTable.put("cAseguradora", "" + cAseguradora);

		return hmfieldsTable;
	}

	public int getICveVehiculo() {
		return iCveVehiculo;
	}

	public void setICveVehiculo(int iCveVehiculo) {
		this.iCveVehiculo = iCveVehiculo;
	}

	public int getICveTpoVehiculo() {
		return iCveTpoVehiculo;
	}

	public void setICveTpoVehiculo(int iCveTpoVehiculo) {
		this.iCveTpoVehiculo = iCveTpoVehiculo;
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

	public int getIAnioVeh() {
		return iAnioVeh;
	}

	public void setIAnioVeh(int iAnioVeh) {
		this.iAnioVeh = iAnioVeh;
	}

	public String getCNumSerie() {
		return cNumSerie;
	}

	public void setCNumSerie(String cNumSerie) {
		this.cNumSerie = cNumSerie;
	}

	public String getCNumMotor() {
		return cNumMotor;
	}

	public void setCNumMotor(String cNumMotor) {
		this.cNumMotor = cNumMotor;
	}

	public String getCInventario() {
		return cInventario;
	}

	public void setCInventario(String cInventario) {
		this.cInventario = cInventario;
	}

	public int getICveColor() {
		return iCveColor;
	}

	public void setICveColor(int iCveColor) {
		this.iCveColor = iCveColor;
	}

	public String getCPlacas() {
		return cPlacas;
	}

	public void setCPlacas(String cPlacas) {
		this.cPlacas = cPlacas;
	}

	public int getIKmMantto() {
		return iKmMantto;
	}

	public void setIKmMantto(int iKmMantto) {
		this.iKmMantto = iKmMantto;
	}

	public int getIMesMantto() {
		return iMesMantto;
	}

	public void setIMesMantto(int iMesMantto) {
		this.iMesMantto = iMesMantto;
	}

	public String getCPoliza() {
		return cPoliza;
	}

	public void setCPoliza(String cPoliza) {
		this.cPoliza = cPoliza;
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

	public java.sql.Date getDtAlta() {
		return dtAlta;
	}

	public void setDtAlta(java.sql.Date dtAlta) {
		this.dtAlta = dtAlta;
	}

	public int getICveEstadoVEH() {
		return iCveEstadoVEH;
	}

	public void setICveEstadoVEH(int iCveEstadoVEH) {
		this.iCveEstadoVEH = iCveEstadoVEH;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public int getLBaja() {
		return lBaja;
	}

	public void setLBaja(int lBaja) {
		this.lBaja = lBaja;
	}

	public java.sql.Date getDtBaja() {
		return dtBaja;
	}

	public void setDtBaja(java.sql.Date dtBaja) {
		this.dtBaja = dtBaja;
	}

	public int getICveMtvoBaja() {
		return iCveMtvoBaja;
	}

	public void setICveMtvoBaja(int iCveMtvoBaja) {
		this.iCveMtvoBaja = iCveMtvoBaja;
	}

	public String getCDscTpoVehiculo() {
		return cDscTpoVehiculo;
	}

	public void setCDscTpoVehiculo(String cDscTpoVehiculo) {
		this.cDscTpoVehiculo = cDscTpoVehiculo;
	}

	public String getCDscMarca() {
		return cDscMarca;
	}

	public void setCDscMarca(String cDscMarca) {
		this.cDscMarca = cDscMarca;
	}

	public String getCDscModelo() {
		return cDscModelo;
	}

	public void setCDscModelo(String cDscModelo) {
		this.cDscModelo = cDscModelo;
	}

	public String getCDscMtvoBaja() {
		return cDscMtvoBaja;
	}

	public void setCDscMtvoBaja(String cDscMtvoBaja) {
		this.cDscMtvoBaja = cDscMtvoBaja;
	}

	public String getCDscEstadoVEH() {
		return cDscEstadoVEH;
	}

	public void setCDscEstadoVEH(String cDscEstadoVEH) {
		this.cDscEstadoVEH = cDscEstadoVEH;
	}

	public String getCDscColor() {
		return cDscColor;
	}

	public void setCDscColor(String cDscColor) {
		this.cDscColor = cDscColor;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public int getICveTpoMantto() {
		return iCveTpoMantto;
	}

	public void setICveTpoMantto(int iCveTpoMantto) {
		this.iCveTpoMantto = iCveTpoMantto;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getIKmFinal() {
		return iKmFinal;
	}

	public void setIKmFinal(int iKmFinal) {
		this.iKmFinal = iKmFinal;
	}

	public java.sql.Date getDtIniMantto() {
		return dtIniMantto;
	}

	public void setDtIniMantto(java.sql.Date dtIniMantto) {
		this.dtIniMantto = dtIniMantto;
	}

	public int getICveMantenimiento() {
		return iCveMantenimiento;
	}

	public void setICveMantenimiento(int iCveMantenimiento) {
		this.iCveMantenimiento = iCveMantenimiento;
	}

	public int getIKilometraje() {
		return iKilometraje;
	}

	public void setIKilometraje(int iKilometraje) {
		this.iKilometraje = iKilometraje;
	}

	public java.sql.Date getDtInicia() {
		return dtInicia;
	}

	public void setDtInicia(java.sql.Date dtInicia) {
		this.dtInicia = dtInicia;
	}

	public int getIKmGarantia() {
		return iKmGarantia;
	}

	public void setIKmGarantia(int iKmGarantia) {
		this.iKmGarantia = iKmGarantia;
	}

	public int getIMesGarantia() {
		return iMesGarantia;
	}

	public void setIMesGarantia(int iMesGarantia) {
		this.iMesGarantia = iMesGarantia;
	}

	public String getCCobertura() {
		return cCobertura;
	}

	public void setCCobertura(String cCobertura) {
		this.cCobertura = cCobertura;
	}

	public String getCAseguradora() {
		return cAseguradora;
	}

	public void setCAseguradora(String cAseguradora) {
		this.cAseguradora = cAseguradora;
	}
}
