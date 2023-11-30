package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLRequisito
 * </p>
 * <p>
 * Description: VO GRLRequisito
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */
public class TVGRLRequisito implements HashBeanInterface {
	private BeanPK pk;
	private int iCveRequisito;
	private String cDscReqBreve;
	private String cDscRequisito;
	private String cObservacion;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveRequisito);
		return pk;
	}

	public TVGRLRequisito() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveRequisito", "" + iCveRequisito);
		hmfieldsTable.put("cDscReqBreve", cDscReqBreve);
		hmfieldsTable.put("cDscRequisito", cDscRequisito);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveRequisito() {
		return iCveRequisito;
	}

	public void setICveRequisito(int iCveRequisito) {
		this.iCveRequisito = iCveRequisito;
	}

	public String getCDscReqBreve() {
		return cDscReqBreve;
	}

	public void setCDscReqBreve(String cDscReqBreve) {
		this.cDscReqBreve = cDscReqBreve;
	}

	public String getCDscRequisito() {
		return cDscRequisito;
	}

	public void setCDscRequisito(String cDscRequisito) {
		this.cDscRequisito = cDscRequisito;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
