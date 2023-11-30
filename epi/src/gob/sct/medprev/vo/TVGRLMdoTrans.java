package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLMdoTrans
 * </p>
 * <p>
 * Description: Value Object de la tabla GRLMdoTrans
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo S�nchez
 * @version 1.0
 */

public class TVGRLMdoTrans implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMdoTrans;
	private String cDscMdoTrans;
	private String cDscDocto;
	private int lActivo;
	private String ciCveMdoTrans;

	public TVGRLMdoTrans() {
	}

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMdoTrans);
		return pk;
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("cDscMdoTrans", "" + cDscMdoTrans);
		hmfieldsTable.put("cDscDocto", "" + cDscDocto);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("ciCveMdoTrans", "" + ciCveMdoTrans);

		return hmfieldsTable;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public String getCDscDocto() {
		return cDscDocto;
	}

	public void setCDscDocto(String cDscDocto) {
		this.cDscDocto = cDscDocto;
	}

	public String toString() {
		return this.toHashMap().toString();
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getCICveMdoTrans() {
		this.ciCveMdoTrans = String.valueOf(iCveMdoTrans);
		return ciCveMdoTrans;
	}

}