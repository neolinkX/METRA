package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TipoEnvio
 * </p>
 * <p>
 * Description: VO Tabla GRLTipoEnvio
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. González Paz
 * @version 1.0
 */
public class TVTipoEnvio implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTipoEnvio;
	private String cDscTipoEnvio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTipoEnvio);
		return pk;
	}

	public TVTipoEnvio() {
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
