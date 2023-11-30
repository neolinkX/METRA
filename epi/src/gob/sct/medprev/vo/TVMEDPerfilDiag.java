package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDPerfilDiag
 * </p>
 * <p>
 * Description: VO de la tabla MEDPerfilDiag
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

public class TVMEDPerfilDiag implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePerfil;
	private int iCveEspecialidad;
	private int iCveDiagnostico;
	private int lAlarma;
	private String cDscDiagnostico;
	private String cDscBreve;
	private String cCIE;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePerfil);
		pk.add("" + iCveEspecialidad);
		pk.add("" + iCveDiagnostico);
		return pk;
	}

	public int getICvePerfil() {
		return iCvePerfil;
	}

	public void setICvePerfil(int iCvePerfil) {
		this.iCvePerfil = iCvePerfil;
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

	public int getLAlarma() {
		return lAlarma;
	}

	public void setLAlarma(int lAlarma) {
		this.lAlarma = lAlarma;
	}

	public TVMEDPerfilDiag() {
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

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePerfil", "" + iCvePerfil);
		hmfieldsTable.put("iCveEspecialidad", "" + iCveEspecialidad);
		hmfieldsTable.put("iCveDiagnostico", "" + iCveDiagnostico);
		hmfieldsTable.put("lAlarma", "" + lAlarma);
		hmfieldsTable.put("cDscDiagnostico", cDscDiagnostico);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cCIE", cCIE);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

}