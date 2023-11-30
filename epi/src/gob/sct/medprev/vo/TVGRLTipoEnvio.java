package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLTipoEnvio
 * </p>
 * <p>
 * Description: VO para tipo de Envio
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
public class TVGRLTipoEnvio implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTipoEnvio;
	private String cDscTipoEnvio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTipoEnvio);
		return pk;
	}

	public TVGRLTipoEnvio() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTipoEnvio", "" + iCveTipoEnvio);
		hmfieldsTable.put("cDscTipoEnvio", cDscTipoEnvio);
		return hmfieldsTable;
	}

	public int getICveTipoEnvio() {
		return iCveTipoEnvio;
	}

	public void setICveTipoEnvio(int iCveTipoEnvio) {
		this.iCveTipoEnvio = iCveTipoEnvio;
	}

	public String getCDscTipoEnvio() {
		return cDscTipoEnvio;
	}

	public void setCDscTipoEnvio(String cDscTipoEnvio) {
		this.cDscTipoEnvio = cDscTipoEnvio;
	}
}
