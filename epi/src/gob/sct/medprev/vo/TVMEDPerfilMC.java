package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDPerfilMC
 * </p>
 * <p>
 * Description: Value Object de la tabla MEDPerfilMC
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

public class TVMEDPerfilMC implements HashBeanInterface {
	private int iCvePerfil;
	private int iCveMdoTrans;
	private int iCveGrupo;
	private java.sql.Date dtInicio;
	private java.sql.Date dtFin;
	private BeanPK pk;
	private int lVigente;
	private int LVigente;
	private String cDscGrupo;
	private String cDscMdoTrans;

	public TVMEDPerfilMC() {
	}

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePerfil);
		return pk;
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePerfil", "" + iCvePerfil);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveGrupo", "" + iCveGrupo);
		hmfieldsTable.put("dtInicio", "" + dtInicio);
		hmfieldsTable.put("dtFin", "" + "" + dtFin);
		hmfieldsTable.put("lVigente", "" + lVigente);
		hmfieldsTable.put("cDscGrupo", "" + cDscGrupo);
		hmfieldsTable.put("cDscMdoTrans", "" + cDscMdoTrans);
		return hmfieldsTable;
	}

	public int getICvePerfil() {
		return iCvePerfil;
	}

	public void setICvePerfil(int iCvePerfil) {
		this.iCvePerfil = iCvePerfil;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getICveGrupo() {
		return iCveGrupo;
	}

	public void setICveGrupo(int iCveGrupo) {
		this.iCveGrupo = iCveGrupo;
	}

	public java.sql.Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(java.sql.Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public java.sql.Date getDtFin() {
		return dtFin;
	}

	public void setDtFin(java.sql.Date dtFin) {
		this.dtFin = dtFin;
	}

	public int getLVigente() {
		return lVigente;
	}

	public void setLVigente(int lVigente) {
		this.lVigente = lVigente;
	}

	public String toString() {
		return this.toHashMap().toString();
	}

	public String getCDscGrupo() {
		return cDscGrupo;
	}

	public void setCDscGrupo(String cDscGrupo) {
		this.cDscGrupo = cDscGrupo;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}
}