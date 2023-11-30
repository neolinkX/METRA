package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object EMOMomentoAP
 * </p>
 * <p>
 * Description: VO EMOMomentoAP
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */
public class TVEMOMomentoAP implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMomentoAP;
	private String cDscMomentoAP;
	private String cDscBreve;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMomentoAP);
		return pk;
	}

	public TVEMOMomentoAP() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMomentoAP", "" + iCveMomentoAP);
		hmfieldsTable.put("cDscMomentoAP", cDscMomentoAP);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveMomentoAP() {
		return iCveMomentoAP;
	}

	public void setICveMomentoAP(int iCveMomentoAP) {
		this.iCveMomentoAP = iCveMomentoAP;
	}

	public String getCDscMomentoAP() {
		return cDscMomentoAP;
	}

	public void setCDscMomentoAP(String cDscMomentoAP) {
		this.cDscMomentoAP = cDscMomentoAP;
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
