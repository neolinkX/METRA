package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object PERCatMotRubNoAp
 * </p>
 * <p>
 * Description: Motivos de no aptitud
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */
public class TVPERCatMotRubNoAp implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePersonal;
	private int iCveMdoTrans;
	private int iCveCategoria;
	private int iCveCatalogoNoAp;
	private int iCveMotivoNoAp;
	private int iCveRubroNoAp;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePersonal);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iCveCategoria);
		pk.add("" + iCveCatalogoNoAp);
		pk.add("" + iCveMotivoNoAp);
		pk.add("" + iCveRubroNoAp);
		return pk;
	}

	public TVPERCatMotRubNoAp() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("iCveCatalogoNoAp", "" + iCveCatalogoNoAp);
		hmfieldsTable.put("iCveMotivoNoAp", "" + iCveMotivoNoAp);
		hmfieldsTable.put("iCveRubroNoAp", "" + iCveRubroNoAp);
		return hmfieldsTable;
	}

	public int getiCvePersonal() {
		return iCvePersonal;
	}

	public void setiCvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getiCveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setiCveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getiCveCategoria() {
		return iCveCategoria;
	}

	public void setiCveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

	public int getiCveCatalogoNoAp() {
		return iCveCatalogoNoAp;
	}

	public void setiCveCatalogoNoAp(int iCveCatalogoNoAp) {
		this.iCveCatalogoNoAp = iCveCatalogoNoAp;
	}

	public int getICveMotivoNoAp() {
		return iCveMotivoNoAp;
	}

	public void setICveMotivoNoAp(int iCveMotivoNoAp) {
		this.iCveMotivoNoAp = iCveMotivoNoAp;
	}

	public int getICveRubroNoAp() {
		return iCveRubroNoAp;
	}

	public void setICveRubroNoAp(int iCveRubroNoAp) {
		this.iCveRubroNoAp = iCveRubroNoAp;
	}
}
