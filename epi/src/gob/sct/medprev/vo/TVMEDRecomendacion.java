package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDRecomendacion
 * </p>
 * <p>
 * Description: VO de la tabla MEDRecomendacion
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
public class TVMEDRecomendacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEspecialidad;
	private int iCveRecomendacion;
	private String cDscRecomendacion;
	private String cDscBreve;
	private String cIdentificador;
	private int lActivo;
	private String cDscEspecialidad;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEspecialidad);
		pk.add("" + iCveRecomendacion);
		return pk;
	}

	public TVMEDRecomendacion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEspecialidad", "" + iCveEspecialidad);
		hmfieldsTable.put("iCveRecomendacion", "" + iCveRecomendacion);
		hmfieldsTable.put("cDscRecomendacion", cDscRecomendacion);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cIdentificador", cIdentificador);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscEspecialidad", cDscEspecialidad);
		return hmfieldsTable;
	}

	public int getICveEspecialidad() {
		return iCveEspecialidad;
	}

	public void setICveEspecialidad(int iCveEspecialidad) {
		this.iCveEspecialidad = iCveEspecialidad;
	}

	public int getICveRecomendacion() {
		return iCveRecomendacion;
	}

	public void setICveRecomendacion(int iCveRecomendacion) {
		this.iCveRecomendacion = iCveRecomendacion;
	}

	public String getCDscRecomendacion() {
		return cDscRecomendacion;
	}

	public void setCDscRecomendacion(String cDscRecomendacion) {
		this.cDscRecomendacion = cDscRecomendacion;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCIdentificador() {
		return cIdentificador;
	}

	public void setCIdentificador(String cIdentificador) {
		this.cIdentificador = cIdentificador;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getCDscEspecialidad() {
		return cDscEspecialidad;
	}

	public void setCDscEspecialidad(String cDscEspecialidad) {
		this.cDscEspecialidad = cDscEspecialidad;
	}

}
