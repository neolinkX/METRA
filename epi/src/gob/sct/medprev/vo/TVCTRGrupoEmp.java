package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object CTRGrupoEmp
 * </p>
 * <p>
 * Description: Control al Transporte - Grupo de Empresas
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
public class TVCTRGrupoEmp implements HashBeanInterface {
	private BeanPK pk;
	private int iCveGrupoEmp;
	private String cDscGrupoEmp;
	private String cDscBreve;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveGrupoEmp);
		return pk;
	}

	public TVCTRGrupoEmp() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveGrupoEmp", "" + iCveGrupoEmp);
		hmfieldsTable.put("cDscGrupoEmp", cDscGrupoEmp);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveGrupoEmp() {
		return iCveGrupoEmp;
	}

	public void setICveGrupoEmp(int iCveGrupoEmp) {
		this.iCveGrupoEmp = iCveGrupoEmp;
	}

	public String getCDscGrupoEmp() {
		return cDscGrupoEmp;
	}

	public void setCDscGrupoEmp(String cDscGrupoEmp) {
		this.cDscGrupoEmp = cDscGrupoEmp;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
