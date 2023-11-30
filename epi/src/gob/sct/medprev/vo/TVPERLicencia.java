package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object PERLicencia
 * </p>
 * <p>
 * Description: VO PERTpoLicencia
 * </p>
 * <p> 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. González Paz
 * @version 1.0
 */
public class TVPERLicencia implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePersonal;
	private int iCveMdoTrans;
	private int iCveCategoria;
	private String cLicencia;
	private int lDictamen;
	private String cConstancia;
	private int iNumExamen;
	private java.sql.Date dtIniVigencia;
	private java.sql.Date dtFinVigencia;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePersonal);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iCveCategoria);
		return pk;
	}

	public TVPERLicencia() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("cLicencia", cLicencia);
		hmfieldsTable.put("lDictamen", "" + lDictamen);
		hmfieldsTable.put("cConstancia", cConstancia);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("dtIniVigencia", "" + dtIniVigencia);
		hmfieldsTable.put("dtFinVigencia", "" + dtFinVigencia);

		return hmfieldsTable;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getICveCategoria() {
		return iCveCategoria;
	}

	public void setICveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

	public String getCLicencia() {
		return cLicencia;
	}

	public void setCLicencia(String cLicencia) {
		this.cLicencia = cLicencia;
	}

	public int getLDictamen() {
		return lDictamen;
	}

	public void setLDictamen(int lDictamen) {
		this.lDictamen = lDictamen;
	}

	public String getCConstancia() {
		return cConstancia;
	}

	public void setCConstancia(String cConstancia) {
		this.cConstancia = cConstancia;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public java.sql.Date getDtFinVigencia() {
		return dtFinVigencia;
	}

	public java.sql.Date getDtIniVigencia() {
		return dtIniVigencia;
	}

	public void setDtFinVigencia(java.sql.Date dtFinVigencia) {
		this.dtFinVigencia = dtFinVigencia;
	}

	public void setDtIniVigencia(java.sql.Date dtIniVigencia) {
		this.dtIniVigencia = dtIniVigencia;
	}
}
