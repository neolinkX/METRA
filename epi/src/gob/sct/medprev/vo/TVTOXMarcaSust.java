package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TOXMarcaSust
 * </p>
 * <p>
 * Description: VO de la Entidad TOXMarcaSust
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suárez Romero
 * @version 1.0
 */
public class TVTOXMarcaSust implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMarcaSust;
	private String cDscMarcaSust;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMarcaSust);
		return pk;
	}

	public TVTOXMarcaSust() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMarcaSust", "" + iCveMarcaSust);
		hmfieldsTable.put("cDscMarcaSust", cDscMarcaSust);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveMarcaSust() {
		return iCveMarcaSust;
	}

	public void setICveMarcaSust(int iCveMarcaSust) {
		this.iCveMarcaSust = iCveMarcaSust;
	}

	public String getCDscMarcaSust() {
		return cDscMarcaSust;
	}

	public void setCDscMarcaSust(String cDscMarcaSust) {
		this.cDscMarcaSust = cDscMarcaSust;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
