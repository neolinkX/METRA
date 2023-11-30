package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import java.text.DecimalFormat;

/**
 * <p>
 * Title: Value Object TOXExamResult
 * </p>
 * <p>
 * Description: Resultado del Exámen Toxicológico
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrejón Adame
 * @version 1.0
 */
public class TVTOXExamResult implements HashBeanInterface {
	private BeanPK pk;
	private Integer iAnio;
	private Integer iCveLaboratorio;
	private Integer iCveLoteCualita;
	private Integer iCveExamCualita;
	private Integer iCveAnalisis;
	private Integer iCveSustancia;
	private Integer lPositivo;
	private Double dResultado;
	private Double dAbsorbancia;
	private Double dDilucion;
	private Integer lAsignado;
	private Integer lDudoso;
	private String cDscSustancia;
	private String cNoCtrol;
	private String cDscCtrolCalibra;
	private Integer lAutorizado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveLoteCualita);
		pk.add("" + iCveExamCualita);
		pk.add("" + iCveAnalisis);
		pk.add("" + iCveSustancia);
		return pk;
	}

	public TVTOXExamResult() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveLoteCualita", "" + iCveLoteCualita);
		hmfieldsTable.put("iCveExamCualita", "" + iCveExamCualita);
		hmfieldsTable.put("iCveAnalisis", "" + iCveAnalisis);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("dResultado", "" + dResultado);
		hmfieldsTable.put("dAbsorbancia", "" + dAbsorbancia);
		hmfieldsTable.put("dDilucion", "" + dDilucion);
		hmfieldsTable.put("lAsignado", "" + lAsignado);
		hmfieldsTable.put("lDudoso", "" + lDudoso);
		hmfieldsTable.put("cDscSustancia", "" + cDscSustancia);
		hmfieldsTable.put("lPositivo", "" + lPositivo);
		hmfieldsTable.put("cNoCtrol", cNoCtrol);
		hmfieldsTable.put("cDscCtrolCalibra", cDscCtrolCalibra);
		return hmfieldsTable;
	}

	public Integer getiAnio() {
		return iAnio;
	}

	public void setiAnio(Integer iAnio) {
		this.iAnio = iAnio;
	}

	public Integer getiCveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setiCveLaboratorio(Integer iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public Integer getiCveLoteCualita() {
		return iCveLoteCualita;
	}

	public void setiCveLoteCualita(Integer iCveLoteCualita) {
		this.iCveLoteCualita = iCveLoteCualita;
	}

	public Integer getiCveExamCualita() {
		return iCveExamCualita;
	}

	public void setiCveExamCualita(Integer iCveExamCualita) {
		this.iCveExamCualita = iCveExamCualita;
	}

	public Integer getiCveAnalisis() {
		return iCveAnalisis;
	}

	public void setiCveAnalisis(Integer iCveAnalisis) {
		this.iCveAnalisis = iCveAnalisis;
	}

	public Integer getiCveSustancia() {
		return iCveSustancia;
	}

	public void setiCveSustancia(Integer iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public Double getdResultado() {
		return dResultado;
	}

	public void setdResultado(Double dResultado) {
		this.dResultado = dResultado;
	}

	public Double getdAbsobancia() {
		return dAbsorbancia;
	}

	public void setdAbsorbancia(Double dAbsorbancia) {
		this.dAbsorbancia = dAbsorbancia;
	}

	public Double getdDilucion() {
		return dDilucion;
	}

	public void setdDilucion(Double dDilucion) {
		this.dDilucion = dDilucion;
	}

	public Integer getlAsignado() {
		return lAsignado;
	}

	public void setlAsignado(Integer lAsignado) {
		this.lAsignado = lAsignado;
	}

	public Integer getlAutorizado() {
		return lAutorizado;
	}

	public void setlAutorizado(Integer lAutorizado) {
		this.lAutorizado = lAutorizado;
	}

	public String getcDscSustancia() {
		return cDscSustancia;
	}

	public void setcDscSustancia(String cDscSustancia) {
		this.cDscSustancia = cDscSustancia;
	}

	public Integer getLPositivo() {
		return lPositivo;
	}

	public void setLPositivo(Integer lPositivo) {
		this.lPositivo = lPositivo;
	}

	public Integer getLDudoso() {
		return lDudoso;
	}

	public void setLDudoso(Integer lDudoso) {
		this.lDudoso = lDudoso;
	}

	public String getCNoCtrol() {
		return cNoCtrol;
	}

	public void setCNoCtrol(String cNoCtrol) {
		this.cNoCtrol = cNoCtrol;
	}

	public String getCDscCtrolCalibra() {
		return cDscCtrolCalibra;
	}

	public void setCDscCtrolCalibra(String cDscCtrolCalibra) {
		this.cDscCtrolCalibra = cDscCtrolCalibra;
	}

}
