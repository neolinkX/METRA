package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLUMUsuario
 * </p>
 * <p>
 * Description: VO para GRLUMUsuario
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
public class TVGRLUMUsuarioMP implements HashBeanInterface {
	private BeanPK pk;
	private int iCveUsuario;
	private int iCveUniMed;
	private int iCveProceso;
	private String cDscUniMed;
	private String cDscProceso;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUsuario);
		pk.add("" + iCveUniMed);
		pk.add("" + iCveProceso);
		return pk;
	}

	public TVGRLUMUsuarioMP() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUsuario", "" + iCveUsuario);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("cDscUniMed", "" + cDscUniMed);
		hmfieldsTable.put("cDscProceso", "" + cDscProceso);
		return hmfieldsTable;
	}

	public int getICveUsuario() {
		return iCveUsuario;
	}

	public void setICveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

}
