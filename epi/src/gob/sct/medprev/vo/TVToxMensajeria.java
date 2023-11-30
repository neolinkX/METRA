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
public class TVToxMensajeria implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMensajeria;
	private String cDscMensajeria;
	private String SitioOficial;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMensajeria);
		return pk;
	}

	public TVToxMensajeria() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMensajeria", "" + iCveMensajeria);
		hmfieldsTable.put("cDscMensajeria", cDscMensajeria);
		hmfieldsTable.put("SitioOficial", SitioOficial);
		return hmfieldsTable;
	}

	public int getICveMensajeria() {
		return iCveMensajeria;
	}

	public void setICveMensajeria(int iCveMensajeria) {
		this.iCveMensajeria = iCveMensajeria;
	}

	public String getCDscMensajeria() {
		return cDscMensajeria;
	}

	public void setCDscMensajeria(String cDscMensajeria) {
		this.cDscMensajeria = cDscMensajeria;
	}
	
	public String getSitioOficial() {
		return SitioOficial;
	}

	public void setSitioOficial(String SitioOficial) {
		this.SitioOficial = SitioOficial;
	}
}
