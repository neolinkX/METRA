package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TOXReactivo
 * </p>
 * <p>
 * Description: Value Object de la Entidad TOXReactivo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suárez Romero
 * @version 1.1
 */
public class TVTOXReactivo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveLaboratorio;
	private int iCveReactivo;
	private int iCveTpoReact;
	private int iAnio;
	private String iCodigo;
	private String cDscBreve;
	private String cDscReactivo;
	private int iCveMarcaSust;
	private String cPreparadoDe;
	private float dVolumen;
	private int iViales;
	private java.sql.Date dtPreparacion;
	private int iCveUsuPrepara;
	private java.sql.Date dtCaducidad;
	private java.sql.Date dtAutoriza;
	private int iCveUsuAutoriza;
	private int lAgotado;
	private java.sql.Date dtAgotado;
	private int lBaja;
	private java.sql.Date dtBaja;
	private int iCveBaja;
	private String cObservacion;
	private int lCuantCual;
	private float dCorteNeg;
	private float dCortePost;
	private String cDscTpoReact;
	private String cPreparacion;
	private String cDscMarcaSust;
	private String cNomCompletoPrepara;
	private String cNomCompletoAutoriza;
	private int lCual;

	private String cComboBaja;
	private int iCveSustancia;
	private String cNumLote;
	private String cNumCatalogo;
	private String cProveedor;
	private String cObservaAmp;
	private int iCveEquipoE1;
	private double dConcentExper1;
	private double dConcentracion;
	private java.sql.Date dtValoracion1;
	private int iCveEquipoE2;
	private double dConcentExper2;
	private java.sql.Date dtValoracion2;
	private java.sql.Date dtCaducAmp;
	private double dConcentTeor;
	// atributos de composición
	// Agregados por Rafael Alcocer Caldera 25/Sep/2006
	private String cCveEquipo1;
	private String cDscEquipo1;
	private String cNumSerie1;
	private String cModelo1;
	private String cCveEquipo2;
	private String cDscEquipo2;
	private String cNumSerie2;
	private String cModelo2;
	private String cDscSustancia;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveReactivo);
		return pk;
	}

	public TVTOXReactivo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveReactivo", "" + iCveReactivo);
		hmfieldsTable.put("iCveTpoReact", "" + iCveTpoReact);
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCodigo", iCodigo);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cDscReactivo", cDscReactivo);
		hmfieldsTable.put("iCveMarcaSust", "" + iCveMarcaSust);
		hmfieldsTable.put("cPreparadoDe", cPreparadoDe);
		hmfieldsTable.put("dVolumen", "" + dVolumen);
		hmfieldsTable.put("iViales", "" + iViales);
		hmfieldsTable.put("dtPreparacion", "" + dtPreparacion);
		hmfieldsTable.put("iCveUsuPrepara", "" + iCveUsuPrepara);
		hmfieldsTable.put("dtCaducidad", "" + dtCaducidad);
		hmfieldsTable.put("dtAutoriza", "" + dtAutoriza);
		hmfieldsTable.put("iCveUsuAutoriza", "" + iCveUsuAutoriza);
		hmfieldsTable.put("lAgotado", "" + lAgotado);
		hmfieldsTable.put("dtAgotado", "" + dtAgotado);
		hmfieldsTable.put("lBaja", "" + lBaja);
		hmfieldsTable.put("dtBaja", "" + dtBaja);
		hmfieldsTable.put("iCveBaja", "" + iCveBaja);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("cDscTpoReact", cDscTpoReact);
		hmfieldsTable.put("cPreparacion", cPreparacion);
		hmfieldsTable.put("dCorteNeg", "" + dCorteNeg);
		hmfieldsTable.put("dCortePost", "" + dCortePost);
		hmfieldsTable.put("cDscMarcaSust", cDscMarcaSust);
		hmfieldsTable.put("cNomCompletoPrepara", cNomCompletoPrepara);
		hmfieldsTable.put("cNomCompletoAutoriza", cNomCompletoAutoriza);
		hmfieldsTable.put("lCuantCual", "" + lCuantCual);
		hmfieldsTable.put("lCual", "" + lCual);
		hmfieldsTable.put("cComboBaja", cComboBaja);
		hmfieldsTable.put("dConcentTeor", String.valueOf(this.dConcentTeor)); // Rafael
																				// Alcocer
																				// Caldera
																				// Corregido
																				// 05/Oct/2006
																				// dConcetTeor
																				// -->
																				// dConcentTeor
		hmfieldsTable.put("iCveSustancia", String.valueOf(this.iCveSustancia));
		hmfieldsTable.put("cNumLote", this.cNumLote);
		hmfieldsTable.put("cNumCatalogo", this.cNumCatalogo);
		hmfieldsTable
				.put("dConcentracion", String.valueOf(this.dConcentracion)); // Rafael
																				// Alcocer
																				// Caldera
																				// Corregido
																				// 05/Oct/2006
																				// dConcetracion
																				// -->
																				// dConcentracion
		hmfieldsTable.put("dtCaducAmp", this.dtCaducAmp);
		hmfieldsTable.put("cProveedor", this.cProveedor);
		hmfieldsTable.put("cObservaAmp", this.cObservaAmp);
		hmfieldsTable.put("iCveEquipoE1", String.valueOf(this.iCveEquipoE1));
		hmfieldsTable
				.put("dConcentExper1", String.valueOf(this.dConcentExper1)); // Rafael
																				// Alcocer
																				// Caldera
																				// Corregido
																				// 05/Oct/2006
																				// dConcetExper1
																				// -->
																				// dConcentExper1
		hmfieldsTable.put("dtValoracion1", this.dtValoracion1);
		hmfieldsTable.put("iCveEquipoE2", String.valueOf(this.iCveEquipoE2));
		hmfieldsTable
				.put("dConcentExper2", String.valueOf(this.dConcentExper2)); // Rafael
																				// Alcocer
																				// Caldera
																				// Corregido
																				// 05/Oct/2006
																				// dConcetExper2
																				// -->
																				// dConcentExper2
		hmfieldsTable.put("dtValoracion2", this.dtValoracion2);
		// Agregados por Rafael Alcocer Caldera 25/Sep/2006
		hmfieldsTable.put("cCveEquipo1", cCveEquipo1);
		hmfieldsTable.put("cDscEquipo1", cDscEquipo1);
		hmfieldsTable.put("cNumSerie1", cNumSerie1);
		hmfieldsTable.put("cModelo1", cModelo1);
		hmfieldsTable.put("cCveEquipo2", cCveEquipo2);
		hmfieldsTable.put("cDscEquipo2", cDscEquipo2);
		hmfieldsTable.put("cNumSerie2", cNumSerie2);
		hmfieldsTable.put("cModelo2", cModelo2);
		hmfieldsTable.put("cDscSustancia", cDscSustancia);

		return hmfieldsTable;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getICveReactivo() {
		return iCveReactivo;
	}

	public void setICveReactivo(int iCveReactivo) {
		this.iCveReactivo = iCveReactivo;
	}

	public int getICveTpoReact() {
		return iCveTpoReact;
	}

	public void setICveTpoReact(int iCveTpoReact) {
		this.iCveTpoReact = iCveTpoReact;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public String getICodigo() {
		return iCodigo;
	}

	public void setICodigo(String iCodigo) {
		this.iCodigo = iCodigo;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCDscReactivo() {
		return cDscReactivo;
	}

	public void setCDscReactivo(String cDscReactivo) {
		this.cDscReactivo = cDscReactivo;
	}

	public int getICveMarcaSust() {
		return iCveMarcaSust;
	}

	public void setICveMarcaSust(int iCveMarcaSust) {
		this.iCveMarcaSust = iCveMarcaSust;
	}

	public String getCPreparadoDe() {
		return cPreparadoDe;
	}

	public void setCPreparadoDe(String cPreparadoDe) {
		this.cPreparadoDe = cPreparadoDe;
	}

	public float getDVolumen() {
		return dVolumen;
	}

	public void setDVolumen(float dVolumen) {
		this.dVolumen = dVolumen;
	}

	public int getIViales() {
		return iViales;
	}

	public void setIViales(int iViales) {
		this.iViales = iViales;
	}

	public java.sql.Date getDtPreparacion() {
		return dtPreparacion;
	}

	public void setDtPreparacion(java.sql.Date dtPreparacion) {
		this.dtPreparacion = dtPreparacion;
	}

	public String getCPreparacion() {
		return cPreparacion;
	}

	public void setCPreparacion(String cPreparacion) {
		this.cPreparacion = cPreparacion;
	}

	public int getICveUsuPrepara() {
		return iCveUsuPrepara;
	}

	public void setICveUsuPrepara(int iCveUsuPrepara) {
		this.iCveUsuPrepara = iCveUsuPrepara;
	}

	public java.sql.Date getDtCaducidad() {
		return dtCaducidad;
	}

	public void setDtCaducidad(java.sql.Date dtCaducidad) {
		this.dtCaducidad = dtCaducidad;
	}

	public java.sql.Date getDtAutoriza() {
		return dtAutoriza;
	}

	public void setDtAutoriza(java.sql.Date dtAutoriza) {
		this.dtAutoriza = dtAutoriza;
	}

	public int getICveUsuAutoriza() {
		return iCveUsuAutoriza;
	}

	public void setICveUsuAutoriza(int iCveUsuAutoriza) {
		this.iCveUsuAutoriza = iCveUsuAutoriza;
	}

	public int getLAgotado() {
		return lAgotado;
	}

	public void setLAgotado(int lAgotado) {
		this.lAgotado = lAgotado;
	}

	public java.sql.Date getDtAgotado() {
		return dtAgotado;
	}

	public void setDtAgotado(java.sql.Date dtAgotado) {
		this.dtAgotado = dtAgotado;
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

	public int getICveBaja() {
		return iCveBaja;
	}

	public void setICveBaja(int iCveBaja) {
		this.iCveBaja = iCveBaja;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getLCuantCual() {
		return lCuantCual;
	}

	public void setLCuantCual(int lCuantCual) {
		this.lCuantCual = lCuantCual;
	}

	public float getDCorteNeg() {
		return dCorteNeg;
	}

	public float getDCortePost() {
		return dCortePost;
	}

	public void setDCortePost(float dCortePost) {
		this.dCortePost = dCortePost;
	}

	public void setDCorteNeg(float dCorteNeg) {
		this.dCorteNeg = dCorteNeg;
	}

	public void setCDscTpoReact(String cDscTpoReact) {
		this.cDscTpoReact = cDscTpoReact;
	}

	public String getCDscTpoReact(String cDscTpoReact) {
		return cDscTpoReact;
	}

	public String getCDscMarcaSust() {
		return cDscMarcaSust;
	}

	public void setCDscMarcaSust(String cDscMarcaSust) {
		this.cDscMarcaSust = cDscMarcaSust;
	}

	public String getCNomCompletoPrepara() {
		return cNomCompletoPrepara;
	}

	public void setCNomCompletoPrepara(String cNomCompletoPrepara) {
		this.cNomCompletoPrepara = cNomCompletoPrepara;
	}

	public String getCNomCompletoAutoriza() {
		return cNomCompletoAutoriza;
	}

	public void setCNomCompletoAutoriza(String cNomCompletoAutoriza) {
		this.cNomCompletoAutoriza = cNomCompletoAutoriza;
	}

	public int getLCual() {
		return lCual;
	}

	public void setLCual(int lCual) {
		this.lCual = lCual;
	}

	public String getCComboBaja() {
		return cComboBaja;
	}

	public void setCComboBaja(String cComboBaja) {
		this.cComboBaja = cComboBaja;
	}

	public int getICveSustancia() {
		return iCveSustancia;
	}

	public void setICveSustancia(int iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public String getCNumLote() {
		return cNumLote;
	}

	public void setCNumLote(String cNumLote) {
		this.cNumLote = cNumLote;
	}

	public String getCNumCatalogo() {
		return cNumCatalogo;
	}

	public void setCNumCatalogo(String cNumCatalogo) {
		this.cNumCatalogo = cNumCatalogo;
	}

	public double getDConcentracion() {
		return dConcentracion;
	}

	public void setDConcentracion(double dConcentracion) {
		this.dConcentracion = dConcentracion;
	}

	public String getCProveedor() {
		return cProveedor;
	}

	public void setCProveedor(String cProveedor) {
		this.cProveedor = cProveedor;
	}

	public String getCObservaAmp() {
		return cObservaAmp;
	}

	public void setCObservaAmp(String cObservaAmp) {
		this.cObservaAmp = cObservaAmp;
	}

	public int getICveEquipoE1() {
		return iCveEquipoE1;
	}

	public void setICveEquipoE1(int iCveEquipoE1) {
		this.iCveEquipoE1 = iCveEquipoE1;
	}

	public double getDConcentExper1() {
		return dConcentExper1;
	}

	public void setDConcentExper1(double dConcentExper1) {
		this.dConcentExper1 = dConcentExper1;
	}

	public java.sql.Date getDtValoracion1() {
		return dtValoracion1;
	}

	public void setDtValoracion1(java.sql.Date dtValoracion1) {
		this.dtValoracion1 = dtValoracion1;
	}

	public int getICveEquipoE2() {
		return iCveEquipoE2;
	}

	public void setICveEquipoE2(int iCveEquipoE2) {
		this.iCveEquipoE2 = iCveEquipoE2;
	}

	public double getDConcentExper2() {
		return dConcentExper2;
	}

	public void setDConcentExper2(double dConcentExper2) {
		this.dConcentExper2 = dConcentExper2;
	}

	public java.sql.Date getDtValoracion2() {
		return dtValoracion2;
	}

	public void setDtValoracion2(java.sql.Date dtValoracion2) {
		this.dtValoracion2 = dtValoracion2;
	}

	public java.sql.Date getDtCaducAmp() {
		return dtCaducAmp;
	}

	public void setDtCaducAmp(java.sql.Date dtCaducAmp) {
		this.dtCaducAmp = dtCaducAmp;
	}

	public double getDConcentTeor() {
		return dConcentTeor;
	}

	public void setDConcentTeor(double dConcentTeor) {
		this.dConcentTeor = dConcentTeor;
	}

	public String getCCveEquipo1() {
		return cCveEquipo1;
	}

	public void setCCveEquipo1(String cCveEquipo1) {
		this.cCveEquipo1 = cCveEquipo1;
	}

	public String getCDscEquipo1() {
		return cDscEquipo1;
	}

	public void setCDscEquipo1(String cDscEquipo1) {
		this.cDscEquipo1 = cDscEquipo1;
	}

	public String getCNumSerie1() {
		return cNumSerie1;
	}

	public void setCNumSerie1(String cNumSerie1) {
		this.cNumSerie1 = cNumSerie1;
	}

	public String getCModelo1() {
		return cModelo1;
	}

	public void setCModelo1(String cModelo1) {
		this.cModelo1 = cModelo1;
	}

	public String getCCveEquipo2() {
		return cCveEquipo2;
	}

	public void setCCveEquipo2(String cCveEquipo2) {
		this.cCveEquipo2 = cCveEquipo2;
	}

	public String getCDscEquipo2() {
		return cDscEquipo2;
	}

	public void setCDscEquipo2(String cDscEquipo2) {
		this.cDscEquipo2 = cDscEquipo2;
	}

	public String getCNumSerie2() {
		return cNumSerie2;
	}

	public void setCNumSerie2(String cNumSerie2) {
		this.cNumSerie2 = cNumSerie2;
	}

	public String getCModelo2() {
		return cModelo2;
	}

	public void setCModelo2(String cModelo2) {
		this.cModelo2 = cModelo2;
	}

	public String getCDscSustancia() {
		return cDscSustancia;
	}

	public void setCDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

}
