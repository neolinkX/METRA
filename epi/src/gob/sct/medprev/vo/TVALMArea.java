package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMArea
 * </p>
 * <p>
 * Description: VO Tabla ALMArea
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
public class TVALMArea implements HashBeanInterface {
	private BeanPK pk;
	private int iCveAlmacen;
	private int iCveArea;
	private String cDscArea;
	private String cDscBreve;
	private int iCveTpoArticulo;
	private String cObservacion;
	private int lActivo;
	private String cDscTpoArticulo;
	private String cDscAlmacen;
	private String cDscBreveTpoArticulo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveArea);
		return pk;
	}

	public TVALMArea() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("cDscArea", cDscArea);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("iCveTpoArticulo", "" + iCveTpoArticulo);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscTpoArticulo", "" + cDscTpoArticulo);
		hmfieldsTable.put("cDscAlmacen", "" + cDscAlmacen);
		hmfieldsTable.put("cDscBreveTpoArticulo", "" + cDscBreveTpoArticulo);
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

	public String getCDscArea() {
		return cDscArea;
	}

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getICveTpoArticulo() {
		return iCveTpoArticulo;
	}

	public void setICveTpoArticulo(int iCveTpoArticulo) {
		this.iCveTpoArticulo = iCveTpoArticulo;
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

	public String getCDscTpoArticulo() {
		return cDscTpoArticulo;
	}

	public void setCDscTpoArticulo(String cDscTpoArticulo) {
		this.cDscTpoArticulo = cDscTpoArticulo;
	}

	public String getCDscAlmacen() {
		return cDscAlmacen;
	}

	public void setCDscAlmacen(String cDscAlmacen) {
		this.cDscAlmacen = cDscAlmacen;
	}

	public String getCDscBreveTpoArticulo() {
		return cDscBreveTpoArticulo;
	}

	public void setCDscBreveTpoArticulo(String cDscBreveTpoArticulo) {
		this.cDscBreveTpoArticulo = cDscBreveTpoArticulo;
	}

}
