package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object ALMDtlleInve
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
public class TVALMDtlleInve implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveAlmacen;
	private int iCveInventario;
	private int iCveArticulo;
	private int iCveLote;
	private double dELogica;
	private double dEFisica;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveInventario);
		pk.add("" + iCveArticulo);
		pk.add("" + iCveLote);
		return pk;
	}

	public TVALMDtlleInve() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveInventario", "" + iCveInventario);
		hmfieldsTable.put("iCveArticulo", "" + iCveArticulo);
		hmfieldsTable.put("iCveLote", "" + iCveLote);
		hmfieldsTable.put("dELogica", "" + dELogica);
		hmfieldsTable.put("dEFisica", "" + dEFisica);
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

	public int getiCveInventario() {
		return iCveInventario;
	}

	public void setiCveInventario(int iCveInventario) {
		this.iCveInventario = iCveInventario;
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

	public double getdELogica() {
		return dELogica;
	}

	public void setdELogica(double dELogica) {
		this.dELogica = dELogica;
	}

	public double getdEFisica() {
		return dEFisica;
	}

	public void setdEFisica(double dEFisica) {
		this.dEFisica = dEFisica;
	}
}
