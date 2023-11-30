package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object ALMArtxAlm
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
public class TVALMArtxAlm implements HashBeanInterface {
	private BeanPK pk;
	private int iCveAlmacen;
	private int iCveArticulo;
	private double dExistencia;
	private double dMaximo;
	private double dMinimo;
	private String cDscArticulo;
	private String cDscBreve;
	private int lLote;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveArticulo);
		return pk;
	}

	public TVALMArtxAlm() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveArticulo", "" + iCveArticulo);
		hmfieldsTable.put("dExistencia", "" + dExistencia);
		hmfieldsTable.put("dMaximo", "" + dMaximo);
		hmfieldsTable.put("dMinimo", "" + dMinimo);
		hmfieldsTable.put("cDscArticulo", "" + cDscArticulo);
		hmfieldsTable.put("cDscBreve", "" + cDscBreve);
		hmfieldsTable.put("lLote", "" + lLote);
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

	public double getdExistencia() {
		return dExistencia;
	}

	public void setdExistencia(double dExistencia) {
		this.dExistencia = dExistencia;
	}

	public double getdMaximo() {
		return dMaximo;
	}

	public void setdMaximo(double dMaximo) {
		this.dMaximo = dMaximo;
	}

	public double getdMinimo() {
		return dMinimo;
	}

	public void setdMinimo(double dMinimo) {
		this.dMinimo = dMinimo;
	}

	public String getcDscArticulo() {
		return cDscArticulo;
	}

	public void setcDscArticulo(String cDscArticulo) {
		this.cDscArticulo = cDscArticulo;
	}

	public String getcDscBreve() {
		return cDscBreve;
	}

	public void setcDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public void setlLote(int lLote) {
		this.lLote = lLote;
	}

	public int getlLote() {
		return lLote;
	}
}
