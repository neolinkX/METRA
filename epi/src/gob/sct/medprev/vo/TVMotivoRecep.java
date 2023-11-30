package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MotivoRecep
 * </p>
 * <p>
 * Description: VO para la tabla TOXMotivoRecep
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
public class TVMotivoRecep implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMotRecep;
	private int iCveTipoRecep;
	private String cDscMotRecep;
	private String cDscTipoRecep;
	private String cDscLong;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMotRecep);
		return pk;
	}

	public TVMotivoRecep() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMotRecep", "" + iCveMotRecep);
		hmfieldsTable.put("iCveTipoRecep", "" + iCveTipoRecep);
		hmfieldsTable.put("cDscTipoRecep", "" + cDscTipoRecep);
		hmfieldsTable.put("cDscMotRecep", cDscMotRecep);
		hmfieldsTable.put("cDscLong", cDscLong);

		return hmfieldsTable;
	}

	public int getICveMotRecep() {
		return iCveMotRecep;
	}

	public void setICveMotRecep(int iCveMotRecep) {
		this.iCveMotRecep = iCveMotRecep;
	}

	public int getICveTipoRecep() {
		return iCveTipoRecep;
	}

	public void setICveTipoRecep(int iCveTipoRecep) {
		this.iCveTipoRecep = iCveTipoRecep;
	}

	public String getCDscMotRecep() {
		return cDscMotRecep;
	}

	public void setCDscMotRecep(String cDscMotRecep) {
		this.cDscMotRecep = cDscMotRecep;
	}

	public String getCDscTipoRecep() {
		return cDscTipoRecep;
	}

	public void setCDscTipoRecep(String cDscTipoRecep) {
		this.cDscTipoRecep = cDscTipoRecep;
	}

	public String getCDscLong() {
		return cDscLong;
	}

	public void setCDscLong(String cDscLong) {
		this.cDscLong = cDscLong;
	}
}
