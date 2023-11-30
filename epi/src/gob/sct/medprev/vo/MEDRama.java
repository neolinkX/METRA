package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object DRama
 * </p>
 * <p>
 * Description: VO MEDRama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */
public class MEDRama implements HashBeanInterface {
	private BeanPK pk;
	private int iCveServicio;
	private int iCveRama;
	private String cObservacion;
	private int lEstudio;
	private int iOrden;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveServicio);
		pk.add("" + iCveRama);
		return pk;
	}

	public MEDRama() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lEstudio", "" + lEstudio);
		hmfieldsTable.put("iOrden", "" + iOrden);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
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
}
