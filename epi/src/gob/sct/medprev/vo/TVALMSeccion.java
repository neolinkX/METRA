package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMSeccion
 * </p>
 * <p>
 * Description: VO Tabla ALMSeccion
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */
public class TVALMSeccion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveAlmacen;
	private int iCveArea;
	private int iCveSeccion;
	private String cDscSeccion;
	private String cObservacion;
	private int lActivo;
	private String cDscAlmacen;
	private String cDscArea;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveArea);
		pk.add("" + iCveSeccion);
		return pk;
	}

	public TVALMSeccion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("iCveSeccion", "" + iCveSeccion);
		hmfieldsTable.put("cDscSeccion", cDscSeccion);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscAlmacen", cDscAlmacen);
		hmfieldsTable.put("cDscArea", cDscArea);
		return hmfieldsTable;
	}

	public int getICveAlmacen() {
		return iCveAlmacen;
	}

	public void setICveAlmacen(int iCveAlmacen) {
		this.iCveAlmacen = iCveAlmacen;
	}

	public int getICveArea() {
		return iCveArea;
	}

	public void setICveArea(int iCveArea) {
		this.iCveArea = iCveArea;
	}

	public int getICveSeccion() {
		return iCveSeccion;
	}

	public void setICveSeccion(int iCveSeccion) {
		this.iCveSeccion = iCveSeccion;
	}

	public String getCDscSeccion() {
		return cDscSeccion;
	}

	public void setCDscSeccion(String cDscSeccion) {
		this.cDscSeccion = cDscSeccion;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getCDscAlmacen() {
		return cDscAlmacen;
	}

	public void setCDscAlmacen(String cDscAlmacen) {
		this.cDscAlmacen = cDscAlmacen;
	}

	public String getCDscArea() {
		return cDscArea;
	}

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

}
