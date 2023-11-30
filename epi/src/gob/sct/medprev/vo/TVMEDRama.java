package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDRama
 * </p>
 * <p>
 * Description: VO de la tabla MEDRama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sánchez
 * @version 1.0
 */

public class TVMEDRama implements HashBeanInterface {
	private BeanPK pk;
	private int iCveServicio;
	private int iCveRama;
	private String cObservacion;
	private int lEstudio;
	private int iOrden;
	private int lActivo;
	private String cDscRama;
	private int LOrden;
	private String cDscServicio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveServicio);
		pk.add("" + iCveRama);
		return pk;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getICveRama() {
		return iCveRama;
	}

	public void setICveRama(int iCveRama) {
		this.iCveRama = iCveRama;
	}

	public String getCDscRama() {
		return cDscRama;
	}

	public void setCDscRama(String cDscRama) {
		this.cDscRama = cDscRama;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getLEstudio() {
		return lEstudio;
	}

	public void setLEstudio(int lEstudio) {
		this.lEstudio = lEstudio;
	}

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public TVMEDRama() {
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("cDscRama", cDscRama);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lEstudio", "" + lEstudio);
		hmfieldsTable.put("iOrden", "" + iOrden);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		return hmfieldsTable;
	}
}