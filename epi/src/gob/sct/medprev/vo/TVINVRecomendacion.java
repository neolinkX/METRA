package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object INVRecomendacion
 * </p>
 * <p>
 * Description: Value Object de la Entidad INVRecomendacion
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVINVRecomendacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveRecomendacion;
	private String cDscRecomendacion;
	private String cCveInterna;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveRecomendacion);
		return pk;
	}

	public TVINVRecomendacion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveRecomendacion", "" + iCveRecomendacion);
		hmfieldsTable.put("cDscRecomendacion", cDscRecomendacion);
		hmfieldsTable.put("cCveInterna", cCveInterna);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveRecomendacion() {
		return iCveRecomendacion;
	}

	public void setICveRecomendacion(int iCveRecomendacion) {
		this.iCveRecomendacion = iCveRecomendacion;
	}

	public String getCDscRecomendacion() {
		return cDscRecomendacion;
	}

	public void setCDscRecomendacion(String cDscRecomendacion) {
		this.cDscRecomendacion = cDscRecomendacion;
	}

	public String getCCveInterna() {
		return cCveInterna;
	}

	public void setCCveInterna(String cCveInterna) {
		this.cCveInterna = cCveInterna;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
