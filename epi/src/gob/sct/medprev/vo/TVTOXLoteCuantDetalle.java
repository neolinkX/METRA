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
 * Description: Lotes Cuantitativos
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
public class TVTOXLoteCuantDetalle {

	public TVTOXLoteCuantita VLote;
	private String cUsuAnalista;
	private String cUsuGenera;
	private String cUsuAutoriza;
	private int iAnalizados;
	private int iAutorizados;
	private int iNegativos;
	private int iPositivos;

	public TVTOXLoteCuantDetalle() {
		VLote = new TVTOXLoteCuantita();
	}

	public String getCUsuAnalista() {
		return cUsuAnalista;
	}

	public void setCUsuAnalista(String cUsuAnalista) {
		this.cUsuAnalista = cUsuAnalista;
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

	public int getINegativos() {
		return iNegativos;
	}

	public void setINegativos(int iNegativos) {
		this.iNegativos = iNegativos;
	}

	public int getIPositivos() {
		return iPositivos;
	}

	public void setIPositivos(int iPositivos) {
		this.iPositivos = iPositivos;
	}

	public String getCUsuGenera() {
		return cUsuGenera;
	}

	public void setCUsuGenera(String cUsuGenera) {
		this.cUsuGenera = cUsuGenera;
	}

	public String getCUsuAutoriza() {
		return cUsuAutoriza;
	}

	public void setCUsuAutoriza(String cUsuAutoriza) {
		this.cUsuAutoriza = cUsuAutoriza;
	}

}
