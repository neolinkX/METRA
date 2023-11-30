package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object INVTpoCausa
 * </p>
 * <p>
 * Description: Value Object de la Entidad INVTpoCausa
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVINVTpoCausa implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoCausa;
	private String cDscTpoCausa;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoCausa);
		return pk;
	}

	public TVINVTpoCausa() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoCausa", "" + iCveTpoCausa);
		hmfieldsTable.put("cDscTpoCausa", cDscTpoCausa);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoCausa() {
		return iCveTpoCausa;
	}

	public void setICveTpoCausa(int iCveTpoCausa) {
		this.iCveTpoCausa = iCveTpoCausa;
	}

	public String getCDscTpoCausa() {
		return cDscTpoCausa;
	}

	public void setCDscTpoCausa(String cDscTpoCausa) {
		this.cDscTpoCausa = cDscTpoCausa;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
