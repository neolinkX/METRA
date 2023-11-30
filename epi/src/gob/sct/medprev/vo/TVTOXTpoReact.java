package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TOXTpoReact
 * </p>
 * <p>
 * Description: VO de la entidad TOXTpoReact
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
public class TVTOXTpoReact implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoReact;
	private String cDscTpoReact;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoReact);
		return pk;
	}

	public TVTOXTpoReact() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoReact", "" + iCveTpoReact);
		hmfieldsTable.put("cDscTpoReact", cDscTpoReact);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoReact() {
		return iCveTpoReact;
	}

	public void setICveTpoReact(int iCveTpoReact) {
		this.iCveTpoReact = iCveTpoReact;
	}

	public String getCDscTpoReact() {
		return cDscTpoReact;
	}

	public void setCDscTpoReact(String cDscTpoReact) {
		this.cDscTpoReact = cDscTpoReact;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
