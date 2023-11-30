package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.util.TNumeros;

/**
 * <p>
 * Title: Value Object TVTOXLoteCuantDetalle
 * </p>
 * <p>
 * Description: Lotes Cualitativos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Itzia Ma. del C. Sánchez Méndez
 * @version 1.0
 */
public class TVTOXLoteExCualita {

	public TVMuestra VMuestra;
	public TVExamenCualita VECualita;
	private String cUsuAutoriza;
	private String cUsuExam;
	private String cUsuPrepara;
	private java.sql.Date dtGeneracion;
	private String CUsuAutor;
	private String cEquipo;
	private int iAnalizados;
	private int iAutorizados;
	private int iPendientes;
	private int iNumExamen;

	public TVTOXLoteExCualita() {
		VMuestra = new TVMuestra();
		VECualita = new TVExamenCualita();
	}

	public String getCUsuAutoriza() {
		return cUsuAutoriza;
	}

	public void setCUsuAutoriza(String cUsuAutoriza) {
		this.cUsuAutoriza = cUsuAutoriza;
	}

	public String getCUsuExam() {
		return cUsuExam;
	}

	public void setCUsuExam(String cUsuExam) {
		this.cUsuExam = cUsuExam;
	}

	public String getCUsuPrepara() {
		return this.cUsuPrepara;
	}

	public void setCUsuPrepara(String CUsuPrepara) {
		this.cUsuPrepara = CUsuPrepara;
	}

	public java.sql.Date getDtGeneracion() {
		return dtGeneracion;
	}

	public void setDtGeneracion(java.sql.Date dtGeneracion) {
		this.dtGeneracion = dtGeneracion;
	}

	public String getCEquipo() {
		return cEquipo;
	}

	public void setCEquipo(String cEquipo) {
		this.cEquipo = cEquipo;
	}

	public int getIAnalizados() {
		return iAnalizados;
	}

	public void setIAnalizados(int iAnalizados) {
		this.iAnalizados = iAnalizados;
	}

	public int getIAutorizados() {
		return iAutorizados;
	}

	public void setIAutorizados(int iAutorizados) {
		this.iAutorizados = iAutorizados;
	}

	public int getIPendientes() {
		return iPendientes;
	}

	public void setIPendientes(int iPendientes) {
		this.iPendientes = iPendientes;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}
}
