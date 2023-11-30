package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object ALMDetalleMov
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
public class TVALMDetalleMov implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveAlmacen;
	private int iCveMovimiento;
	private int iCveLote;
	private double dUnidades;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveMovimiento);
		pk.add("" + iCveLote);
		return pk;
	}

	public TVALMDetalleMov() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveMovimiento", "" + iCveMovimiento);
		hmfieldsTable.put("iCveLote", "" + iCveLote);
		hmfieldsTable.put("dUnidades", "" + dUnidades);
		return hmfieldsTable;
	}

	public int getiAnio() {
		return iAnio;
	}

	public void setiAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getiCveAlmacen() {
		return iCveAlmacen;
	}

	public void setiCveAlmacen(int iCveAlmacen) {
		this.iCveAlmacen = iCveAlmacen;
	}

	public int getiCveMovimiento() {
		return iCveMovimiento;
	}

	public void setiCveMovimiento(int iCveMovimiento) {
		this.iCveMovimiento = iCveMovimiento;
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
}
