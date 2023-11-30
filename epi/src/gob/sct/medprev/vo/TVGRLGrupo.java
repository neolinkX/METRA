package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLGrupo
 * </p>
 * <p>
 * Description: Value Object de la tabla GRLGrupo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sánchez
 * @version 1.0
 */

public class TVGRLGrupo implements HashBeanInterface {
	private int iCveMdoTrans;
	private int iCveGrupo;
	private String cDscGrupo;
	private BeanPK pk;
	private int lActivo;
	private String cDscBreve;

	public TVGRLGrupo() {
	}

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMdoTrans);
		pk.add("" + iCveGrupo);
		return pk;
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveGrupo", "" + iCveGrupo);
		hmfieldsTable.put("cDscGrupo", cDscGrupo);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		return hmfieldsTable;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getICveGrupo() {
		return iCveGrupo;
	}

	public void setICveGrupo(int iCveGrupo) {
		this.iCveGrupo = iCveGrupo;
	}

	public String getCDscGrupo() {
		return cDscGrupo;
	}

	public void setCDscGrupo(String cDscGrupo) {
		this.cDscGrupo = cDscGrupo;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String toString() {
		return this.toHashMap().toString();
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

}