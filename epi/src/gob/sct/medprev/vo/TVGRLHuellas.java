package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLHuellas
 * </p>
 * <p>
 * Description: VO de la entidad GRLHuellas
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
public class TVGRLHuellas implements HashBeanInterface {
	private BeanPK pk;
	private int iCveHuella;
	private String cDscHuella;
	private int lObligatoria;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveHuella);
		return pk;
	}

	public TVGRLHuellas() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveHuella", "" + iCveHuella);
		hmfieldsTable.put("cDscHuella", cDscHuella);
		hmfieldsTable.put("lObligatoria", lObligatoria == 0 ? "No" : "Si");
		return hmfieldsTable;
	}

	public int getICveHuella() {
		return iCveHuella;
	}

	public void setICveHuella(int iCveHuella) {
		this.iCveHuella = iCveHuella;
	}

	public String getCDscHuella() {
		return cDscHuella;
	}

	public void setCDscHuella(String cDscHuella) {
		this.cDscHuella = cDscHuella;
	}

	public int getLObligatoria() {
		return lObligatoria;
	}

	public void setLObligatoria(int lObligatoria) {
		this.lObligatoria = lObligatoria;
	}
}
