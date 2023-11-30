package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object VEHEstado
 * </p>
 * <p>
 * Description: Value Object de Estado de Vehículos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 */
public class TVVEHEstado implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEstadoVeh;
	private String cDscEstadoVeh;
	private int lInactivaVeh;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEstadoVeh);
		return pk;
	}

	public TVVEHEstado() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEstadoVeh", "" + iCveEstadoVeh);
		hmfieldsTable.put("cDscEstadoVeh", cDscEstadoVeh);
		hmfieldsTable.put("lInactivaVeh", "" + lInactivaVeh);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveEstadoVeh() {
		return iCveEstadoVeh;
	}

	public void setICveEstadoVeh(int iCveEstadoVeh) {
		this.iCveEstadoVeh = iCveEstadoVeh;
	}

	public String getCDscEstadoVeh() {
		return cDscEstadoVeh;
	}

	public void setCDscEstadoVeh(String cDscEstadoVeh) {
		this.cDscEstadoVeh = cDscEstadoVeh;
	}

	public int getLInactivaVeh() {
		return lInactivaVeh;
	}

	public void setLInactivaVeh(int lInactivaVeh) {
		this.lInactivaVeh = lInactivaVeh;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
