package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object ALMInventario
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
public class TVALMInventario implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveAlmacen;
	private int iCveInventario;

	private java.sql.Date dtGeneracion;
	private java.sql.Date dtConteo;
	private java.sql.Date dtActualizacion;
	private int iCveUsuGenera;
	private int iCveAutoriza;
	private String cObservacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveInventario);
		return pk;
	}

	public TVALMInventario() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveInventario", "" + iCveInventario);
		hmfieldsTable.put("dtGeneracion", "" + dtGeneracion);
		hmfieldsTable.put("dtConteo", "" + dtConteo);
		hmfieldsTable.put("dtActualizacion", "" + dtActualizacion);
		hmfieldsTable.put("iCveUsuGenera", "" + iCveUsuGenera);
		hmfieldsTable.put("iCveAutoriza", "" + iCveAutoriza);
		hmfieldsTable.put("cObservacion", "" + cObservacion);
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

	public int getiCveUsuGenera() {
		return iCveUsuGenera;
	}

	public void setiCveUsuGenera(int iCveUsuGenera) {
		this.iCveUsuGenera = iCveUsuGenera;
	}

	public int getiCveAutoriza() {
		return iCveAutoriza;
	}

	public void setiCveAutoriza(int iCveAutoriza) {
		this.iCveAutoriza = iCveAutoriza;
	}

	public java.sql.Date getdtGeneracion() {
		return dtGeneracion;
	}

	public void setdtGeneracion(java.sql.Date dtGeneracion) {
		this.dtGeneracion = dtGeneracion;
	}

	public java.sql.Date getdtConteo() {
		return dtConteo;
	}

	public void setdtConteo(java.sql.Date dtConteo) {
		this.dtConteo = dtConteo;
	}

	public java.sql.Date getdtActualizacion() {
		return dtActualizacion;
	}

	public void setdtActualizacion(java.sql.Date dtActualizacion) {
		this.dtActualizacion = dtActualizacion;
	}

	public String getcObservacion() {
		return cObservacion;
	}

	public void setcObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}
}
