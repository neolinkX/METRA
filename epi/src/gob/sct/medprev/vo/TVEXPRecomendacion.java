package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EXPRecomendacion
 * </p>
 * <p>
 * Description: Value Object para EXPRecomendacion
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVEXPRecomendacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveServicio;
	private int iCveEspecialidad;
	private int iCveRecomendacion;
	private String cDetalle;
	private int iCveUsuRecom;
	private String cIdentificador;
	private String cDscBreve;
	private String cDscEspecialidad;
	private String cDscRecomendacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		pk.add("" + iCveServicio);
		pk.add("" + iCveEspecialidad);
		pk.add("" + iCveRecomendacion);
		return pk;
	}

	public TVEXPRecomendacion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveEspecialidad", "" + iCveEspecialidad);
		hmfieldsTable.put("iCveRecomendacion", "" + iCveRecomendacion);
		hmfieldsTable.put("cDetalle", cDetalle);
		hmfieldsTable.put("iCveUsuRecom", "" + iCveUsuRecom);
		hmfieldsTable.put("cIdentificador", cIdentificador);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cDscEspecialidad", cDscEspecialidad);
		hmfieldsTable.put("cDscRecomendacion", cDscRecomendacion);
		return hmfieldsTable;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
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

	public String getCDetalle() {
		return cDetalle;
	}

	public void setCDetalle(String cDetalle) {
		this.cDetalle = cDetalle;
	}

	public int getICveUsuRecom() {
		return iCveUsuRecom;
	}

	public void setICveUsuRecom(int iCveUsuRecom) {
		this.iCveUsuRecom = iCveUsuRecom;
	}

	public String getCIdentificador() {
		return cIdentificador;
	}

	public void setCIdentificador(String cIdentificador) {
		this.cIdentificador = cIdentificador;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCDscEspecialidad() {
		return cDscEspecialidad;
	}

	public void setCDscEspecialidad(String cDscEspecialidad) {
		this.cDscEspecialidad = cDscEspecialidad;
	}

	public String getCDscRecomendacion() {
		return cDscRecomendacion;
	}

	public void setCDscRecomendacion(String cDscRecomendacion) {
		this.cDscRecomendacion = cDscRecomendacion;
	}

}
