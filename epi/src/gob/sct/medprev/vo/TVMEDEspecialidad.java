package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDEspecialidad
 * </p>
 * <p>
 * Description: VO de la pagina pg070101010
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author leonel Popoca G.
 * @version 1.0
 */
public class TVMEDEspecialidad implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEspecialidad;
	private String cDscEspecialidad;
	private String cObservacion;
	private int lActivo;
	private int iCvePerfil;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEspecialidad);
		return pk;
	}

	public TVMEDEspecialidad() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEspecialidad", "" + iCveEspecialidad);
		hmfieldsTable.put("cDscEspecialidad", cDscEspecialidad);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("iCvePerfil", "" + iCvePerfil);
		return hmfieldsTable;
	}

	public int getICveEspecialidad() {
		return iCveEspecialidad;
	}

	public void setICveEspecialidad(int iCveEspecialidad) {
		this.iCveEspecialidad = iCveEspecialidad;
	}

	public String getCDscEspecialidad() {
		return cDscEspecialidad;
	}

	public void setCDscEspecialidad(String cDscEspecialidad) {
		this.cDscEspecialidad = cDscEspecialidad;
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

	public int getICvePerfil() {
		return iCvePerfil;
	}

	public void setICvePerfil(int iCvePerfil) {
		this.iCvePerfil = iCvePerfil;
	}
}
