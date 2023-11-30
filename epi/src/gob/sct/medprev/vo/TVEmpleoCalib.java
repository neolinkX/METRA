package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object EmpleoCalib
 * </p>
 * <p>
 * Description: VO de TOXEmpleoCalib
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Juan Manuel Vazquez
 * @version 1.0
 */
public class TVEmpleoCalib implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEmpleoCalib;
	private String cDscEmpleoCalib;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEmpleoCalib);
		return pk;
	}

	public TVEmpleoCalib() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEmpleoCalib", "" + iCveEmpleoCalib);
		hmfieldsTable.put("cDscEmpleoCalib", cDscEmpleoCalib);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveEmpleoCalib() {
		return iCveEmpleoCalib;
	}

	public void setICveEmpleoCalib(int iCveEmpleoCalib) {
		this.iCveEmpleoCalib = iCveEmpleoCalib;
	}

	public String getCDscEmpleoCalib() {
		return cDscEmpleoCalib;
	}

	public void setCDscEmpleoCalib(String cDscEmpleoCalib) {
		this.cDscEmpleoCalib = cDscEmpleoCalib;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
