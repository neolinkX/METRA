package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object INVEquipoInv
 * </p>
 * <p>
 * Description: VO Tabla INVEquipoINV
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
public class TVINVEquipoInv implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveMdoTrans;
	private int iIDDGPMPT;
	private int iCveUsuInv;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iIDDGPMPT);
		pk.add("" + iCveUsuInv);
		return pk;
	}

	public TVINVEquipoInv() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iIDDGPMPT", "" + iIDDGPMPT);
		hmfieldsTable.put("iCveUsuInv", "" + iCveUsuInv);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getIIDDGPMPT() {
		return iIDDGPMPT;
	}

	public void setIIDDGPMPT(int iIDDGPMPT) {
		this.iIDDGPMPT = iIDDGPMPT;
	}

	public int getICveUsuInv() {
		return iCveUsuInv;
	}

	public void setICveUsuInv(int iCveUsuInv) {
		this.iCveUsuInv = iCveUsuInv;
	}
}
