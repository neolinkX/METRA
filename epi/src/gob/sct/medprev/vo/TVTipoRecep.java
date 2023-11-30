package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TipoRecep
 * </p>
 * <p>
 * Description: Value Object para la tabla TOXTipoRecep
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

public class TVTipoRecep implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTipoRecep;
	private String cDscTipoRecep;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTipoRecep);
		return pk;
	}

	public TVTipoRecep() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTipoRecep", "" + iCveTipoRecep);
		hmfieldsTable.put("cDscTipoRecep", cDscTipoRecep);
		return hmfieldsTable;
	}

	public int getICveTipoRecep() {
		return iCveTipoRecep;
	}

	public void setICveTipoRecep(int iCveTipoRecep) {
		this.iCveTipoRecep = iCveTipoRecep;
	}

	public String getCDscTipoRecep() {
		return cDscTipoRecep;
	}

	public void setCDscTipoRecep(String cDscTipoRecep) {
		this.cDscTipoRecep = cDscTipoRecep;
	}
}
