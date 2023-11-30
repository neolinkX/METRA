package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDTpoResp
 * </p>
 * <p>
 * Description: Tipos de Respuesta
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */
public class TVMEDTpoResp implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoResp;
	private String cDscTpoResp;
	private String cCampo;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoResp);
		return pk;
	}

	public TVMEDTpoResp() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoResp", "" + iCveTpoResp);
		hmfieldsTable.put("cDscTpoResp", cDscTpoResp);
		hmfieldsTable.put("cCampo", cCampo);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
	}

	public String getCDscTpoResp() {
		return cDscTpoResp;
	}

	public void setCDscTpoResp(String cDscTpoResp) {
		this.cDscTpoResp = cDscTpoResp;
	}

	public String getCCampo() {
		return cCampo;
	}

	public void setCCampo(String cCampo) {
		this.cCampo = cCampo;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
