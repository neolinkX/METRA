package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDDiagnostico
 * </p>
 * <p>
 * Description: VO de la tabla MEDDiagnostico
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

public class TVMEDDiagnostico implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEspecialidad;
	private int iCveDiagnostico;
	private String cDscDiagnostico;
	private String cDscBreve;
	private String cCIE;
	private String cObservacion;
	private int lActivo;
	private int lFrecuente;
	private String cDscEspecialidad;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEspecialidad);
		pk.add("" + iCveDiagnostico);
		return pk;
	}

	public int getICveEspecialidad() {
		return iCveEspecialidad;
	}

	public void setICveEspecialidad(int iCveEspecialidad) {
		this.iCveEspecialidad = iCveEspecialidad;
	}

	public int getICveDiagnostico() {
		return iCveDiagnostico;
	}

	public void setICveDiagnostico(int iCveDiagnostico) {
		this.iCveDiagnostico = iCveDiagnostico;
	}

	public String getCDscDiagnostico() {
		return cDscDiagnostico;
	}

	public void setCDscDiagnostico(String cDscDiagnostico) {
		this.cDscDiagnostico = cDscDiagnostico;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCCIE() {
		return cCIE;
	}

	public void setCCIE(String cCIE) {
		this.cCIE = cCIE;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getLFrecuente() {
		return lFrecuente;
	}

	public void setLFrecuente(int lFrecuente) {
		this.lFrecuente = lFrecuente;
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

	public TVMEDDiagnostico() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEspecialidad", "" + iCveEspecialidad);
		hmfieldsTable.put("iCveDiagnostico", "" + iCveDiagnostico);
		hmfieldsTable.put("cDscDiagnostico", cDscDiagnostico);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cCIE", cCIE);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lFrecuente", "" + lFrecuente);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscEspecialidad", cDscEspecialidad);
		return hmfieldsTable;
	}
}