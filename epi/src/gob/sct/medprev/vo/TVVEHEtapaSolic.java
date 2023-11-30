package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object VEHEtapaSolic
 * </p>
 * <p>
 * Description: Value Object de Etapas de Solicitud de Vehículos
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
public class TVVEHEtapaSolic implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEtapaSolic;
	private String cDscEtapaSolic;
	private String cDscBreve;
	private int iOrden;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEtapaSolic);
		return pk;
	}

	public TVVEHEtapaSolic() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEtapaSolic", "" + iCveEtapaSolic);
		hmfieldsTable.put("cDscEtapaSolic", cDscEtapaSolic);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("iOrden", "" + iOrden);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveEtapaSolic() {
		return iCveEtapaSolic;
	}

	public void setICveEtapaSolic(int iCveEtapaSolic) {
		this.iCveEtapaSolic = iCveEtapaSolic;
	}

	public String getCDscEtapaSolic() {
		return cDscEtapaSolic;
	}

	public void setCDscEtapaSolic(String cDscEtapaSolic) {
		this.cDscEtapaSolic = cDscEtapaSolic;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
