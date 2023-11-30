package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object ALMLote
 * </p>
 * <p>
 * Description: VO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Oscar Castrejón Adame.
 * @version 1.0
 */
public class TVALMLote implements HashBeanInterface {
	private BeanPK pk;
	private int iCveAlmacen;
	private int iCveArticulo;
	private int iCveLote;

	private double dUnidades;
	private int iCveUsuario;
	private java.sql.Date dtIngreso;
	private java.sql.Date dtCaducidad;
	private int iCveTpoMovimiento;
	private int iCveConcepto;
	private String cObservacion;
	private java.sql.Date dtAgotado;
	private String cLote;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveArticulo);
		pk.add("" + iCveLote);
		return pk;
	}

	public TVALMLote() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveArticulo", "" + iCveArticulo);
		hmfieldsTable.put("iCveLote", "" + iCveLote);
		hmfieldsTable.put("dUnidades", "" + dUnidades);
		hmfieldsTable.put("iCveUsuario", "" + iCveUsuario);
		hmfieldsTable.put("dtIngreso", "" + dtIngreso);
		hmfieldsTable.put("dtCaducidad", "" + dtCaducidad);
		hmfieldsTable.put("iCveTpoMovimiento", "" + iCveTpoMovimiento);
		hmfieldsTable.put("iCveConcepto", "" + iCveConcepto);
		hmfieldsTable.put("cObservacion", "" + cObservacion);
		hmfieldsTable.put("dtAgotado", "" + dtAgotado);
		hmfieldsTable.put("cLote", "" + cLote);
		return hmfieldsTable;
	}

	public int getiCveAlmacen() {
		return iCveAlmacen;
	}

	public void setiCveAlmacen(int iCveAlmacen) {
		this.iCveAlmacen = iCveAlmacen;
	}

	public int getiCveArticulo() {
		return iCveArticulo;
	}

	public void setiCveArticulo(int iCveArticulo) {
		this.iCveArticulo = iCveArticulo;
	}

	public int getiCveLote() {
		return iCveLote;
	}

	public void setiCveLote(int iCveLote) {
		this.iCveLote = iCveLote;
	}

	public double getdUnidades() {
		return dUnidades;
	}

	public void setdUnidades(double dUnidades) {
		this.dUnidades = dUnidades;
	}

	public int getiCveUsuario() {
		return iCveUsuario;
	}

	public void setiCveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
	}

	public java.sql.Date getdtIngreso() {
		return dtIngreso;
	}

	public void setdtIngreso(java.sql.Date dtIngreso) {
		this.dtIngreso = dtIngreso;
	}

	public java.sql.Date getdtCaducidad() {
		return dtCaducidad;
	}

	public void setdtCaducidad(java.sql.Date dtCaducidad) {
		this.dtCaducidad = dtCaducidad;
	}

	public int getiCveTpoMovimiento() {
		return iCveTpoMovimiento;
	}

	public void setiCveTpoMovimiento(int iCveTpoMovimiento) {
		this.iCveTpoMovimiento = iCveTpoMovimiento;
	}

	public int getiCveConcepto() {
		return iCveConcepto;
	}

	public void setiCveConcepto(int iCveConcepto) {
		this.iCveConcepto = iCveConcepto;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public java.sql.Date getdtAgotado() {
		return dtAgotado;
	}

	public void setdtAgotado(java.sql.Date dtAgotado) {
		this.dtAgotado = dtAgotado;
	}

	public String getcLote() {
		return cLote;
	}

	public void setcLote(String cLote) {
		this.cLote = cLote;
	}
}
