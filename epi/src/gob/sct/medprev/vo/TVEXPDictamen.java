package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;
import com.micper.util.TNumeros;

/**
 * <p>
 * Title: Value Object TOXExamenCualita
 * </p>
 * <p>
 * Description: VO para la tabla TOXExamenCualita
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Itzia María del C. Sánchez Méndez
 * @version 1.0
 */

public class TVEXPDictamen {
	public TVPERDatos VPerDatos;
	public TVEXPExamCat VExamCat;
	public TVGRLUniMed VUniMed;
	public TVGRLUsuario vUsuario;
	public TVGRLModulo vModulo;
	private java.sql.Date dtDictamen;
	private java.sql.Date dtSolicitado;

	private String cDictaminador;
	private String cDscProceso;

	public TVEXPDictamen() {
		VPerDatos = new TVPERDatos();
		VExamCat = new TVEXPExamCat();
		VUniMed = new TVGRLUniMed();
		vUsuario = new TVGRLUsuario();
		vModulo = new TVGRLModulo();
	}

	public java.sql.Date getDtDictamen() {
		return dtDictamen;
	}

	public void setDtDictamen(java.sql.Date dtDictamen) {
		this.dtDictamen = dtDictamen;
	}

	public String getCDictaminador() {
		return cDictaminador;
	}

	public void setCDictaminador(String cDictaminador) {
		this.cDictaminador = cDictaminador;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public java.sql.Date getDtSolicitado() {
		return dtSolicitado;
	}

	public void setDtSolicitado(java.sql.Date dtSolicitado) {
		this.dtSolicitado = dtSolicitado;
	}

}
