package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMFamilia
 * </p>
 * <p>
 * Description: VO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Oscar Castrejón Adame.
 * @version 1.0
 */
public class TVALMFamilia implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoArticulo;
	private int iCveFamilia;
	private String cDscFamilia;
	private String cDscBreve;
	private int lActivo;
	private String cDscArticulo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoArticulo);
		pk.add("" + iCveFamilia);
		return pk;
	}

	public TVALMFamilia() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoArticulo", "" + iCveTpoArticulo);
		hmfieldsTable.put("iCveFamilia", "" + iCveFamilia);
		hmfieldsTable.put("cDscFamilia", "" + cDscFamilia);
		hmfieldsTable.put("cDscBreve", "" + cDscBreve);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscArticulo", "" + cDscArticulo);
		return hmfieldsTable;
	}

	public int getiCveTpoArticulo() {
		return iCveTpoArticulo;
	}

	public void setiCveTpoArticulo(int iCveTpoArticulo) {
		this.iCveTpoArticulo = iCveTpoArticulo;
	}

	public int getiCveFamilia() {
		return iCveFamilia;
	}

	public void setiCveFamilia(int iCveFamilia) {
		this.iCveFamilia = iCveFamilia;
	}

	public int getlActivo() {
		return lActivo;
	}

	public void setlActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getcDscFamilia() {
		return cDscFamilia;
	}

	public void setcDscFamilia(String cDscFamilia) {
		this.cDscFamilia = cDscFamilia;
	}

	public String getcDscBreve() {
		return cDscBreve;
	}

	public void setcDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCDscArticulo() {
		return cDscArticulo;
	}

	public void setCDscArticulo(String cDscArticulo) {
		this.cDscArticulo = cDscArticulo;
	}

}
