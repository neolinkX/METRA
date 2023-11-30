package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object INVCausa
 * </p>
 * <p>
 * Description: Value Object de la Entidad INVCausa
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
public class TVINVCausa implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoCausa;
	private int iCveCausa;
	private String cDscCausa;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoCausa);
		pk.add("" + iCveCausa);
		return pk;
	}

	public TVINVCausa() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoCausa", "" + iCveTpoCausa);
		hmfieldsTable.put("iCveCausa", "" + iCveCausa);
		hmfieldsTable.put("cDscCausa", cDscCausa);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoCausa() {
		return iCveTpoCausa;
	}

	public void setICveTpoCausa(int iCveTpoCausa) {
		this.iCveTpoCausa = iCveTpoCausa;
	}

	public int getICveCausa() {
		return iCveCausa;
	}

	public void setICveCausa(int iCveCausa) {
		this.iCveCausa = iCveCausa;
	}

	public String getCDscCausa() {
		return cDscCausa;
	}

	public void setCDscCausa(String cDscCausa) {
		this.cDscCausa = cDscCausa;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
