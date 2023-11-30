package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;
import com.micper.util.TNumeros;

/**
 * <p>
 * Title: Value Object TVTOXLoteCalibra
 * </p>
 * <p>
 * Description: VO para la tabla TVTOXLoteCalibra
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

public class TVTOXLoteCalibra {
	public TVTOXCuantAnalisis VAnalisis;
	public TVTOXCtrolCalibra VCtrol;
	public TVTOXLoteCuantita VLote;
	private double dLimInferior;
	private double dLimSuperior;
	private Double dTmpRetencInf;
	private Double dTmpRetencSup;
	private Double dTmpRetencDInf;
	private Double dTmpRetencDSup;
	private Double dIon02Inf;
	private Double dIon03Inf;
	private Double dIon04;
	private Double dIon05Inf;
	private Double dIon01;
	private Double dIon02Sup;
	private Double dIon03Sup;
	private Double dIon05Sup;
	private int lValConcentracion;

	public TVTOXLoteCalibra() {
		VCtrol = new TVTOXCtrolCalibra();
		VAnalisis = new TVTOXCuantAnalisis();
		VLote = new TVTOXLoteCuantita();
	}

	public double getDLimInferior() {
		return dLimInferior;
	}

	public void setDLimInferior(double dLimInferior) {
		this.dLimInferior = dLimInferior;
	}

	public double getDLimSuperior() {
		return dLimSuperior;
	}

	public void setDLimSuperior(double dLimSuperior) {
		this.dLimSuperior = dLimSuperior;
	}

	public Double getDTmpRetencInf() {
		return dTmpRetencInf;
	}

	public void setDTmpRetencInf(Double dTmpRetencInf) {
		this.dTmpRetencInf = dTmpRetencInf;
	}

	public Double getDTmpRetencSup() {
		return dTmpRetencSup;
	}

	public void setDTmpRetencSup(Double dTmpRetencSup) {
		this.dTmpRetencSup = dTmpRetencSup;
	}

	public Double getDTmpRetencDInf() {
		return dTmpRetencDInf;
	}

	public void setDTmpRetencDInf(Double dTmpRetencDInf) {
		this.dTmpRetencDInf = dTmpRetencDInf;
	}

	public Double getDTmpRetencDSup() {
		return dTmpRetencDSup;
	}

	public void setDTmpRetencDSup(Double dTmpRetencDSup) {
		this.dTmpRetencDSup = dTmpRetencDSup;
	}

	public Double getDIon02Inf() {
		return dIon02Inf;
	}

	public void setDIon02Inf(Double dIon02Inf) {
		this.dIon02Inf = dIon02Inf;
	}

	public Double getDIon03Inf() {
		return dIon03Inf;
	}

	public void setDIon03Inf(Double dIon03Inf) {
		this.dIon03Inf = dIon03Inf;
	}

	public Double getDIon04() {
		return dIon04;
	}

	public void setDIon04(Double dIon04) {
		this.dIon04 = dIon04;
	}

	public Double getDIon05Inf() {
		return dIon05Inf;
	}

	public void setDIon05Inf(Double dIon05Inf) {
		this.dIon05Inf = dIon05Inf;
	}

	public Double getDIon01() {
		return dIon01;
	}

	public void setDIon01(Double dIon01) {
		this.dIon01 = dIon01;
	}

	public Double getDIon02Sup() {
		return dIon02Sup;
	}

	public void setDIon02Sup(Double dIon02Sup) {
		this.dIon02Sup = dIon02Sup;
	}

	public Double getDIon03Sup() {
		return dIon03Sup;
	}

	public void setDIon03Sup(Double dIon03Sup) {
		this.dIon03Sup = dIon03Sup;
	}

	public Double getDIon05Sup() {
		return dIon05Sup;
	}

	public void setDIon05Sup(Double dIon05Sup) {
		this.dIon05Sup = dIon05Sup;
	}

	public int getLValConcentracion() {
		return lValConcentracion;
	}

	public void setLValConcentracion(int lValConcentracion) {
		this.lValConcentracion = lValConcentracion;
	}

}
