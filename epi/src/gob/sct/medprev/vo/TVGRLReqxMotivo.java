package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object GRLReqxMotivo
 * </p>
 * <p>
 * Description: GRLReqxMotivo
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
public class TVGRLReqxMotivo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMotivo;
	private int iCveRequisito;
	private int lObligatorio;
	private int iCopias;
	private String cDscReqBreve;
	private String cDscRequisito;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMotivo);
		pk.add("" + iCveRequisito);
		return pk;
	}

	public TVGRLReqxMotivo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("iCveRequisito", "" + iCveRequisito);
		hmfieldsTable.put("lObligatorio", "" + lObligatorio);
		hmfieldsTable.put("iCopias", "" + iCopias);
		hmfieldsTable.put("cDscReqBreve", cDscReqBreve);
		hmfieldsTable.put("cDscRequisito", cDscRequisito);
		return hmfieldsTable;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public int getICveRequisito() {
		return iCveRequisito;
	}

	public void setICveRequisito(int iCveRequisito) {
		this.iCveRequisito = iCveRequisito;
	}

	public int getLObligatorio() {
		return lObligatorio;
	}

	public void setLObligatorio(int lObligatorio) {
		this.lObligatorio = lObligatorio;
	}

	public int getICopias() {
		return iCopias;
	}

	public void setICopias(int iCopias) {
		this.iCopias = iCopias;
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

}
