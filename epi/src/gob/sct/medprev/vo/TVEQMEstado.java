package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object EQMEstado
 * </p>
 * <p>
 * Description: Calibración de Equipo - Estados del Equipo
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
public class TVEQMEstado implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEstadoEQM;
	private String cDscEstadoEQM;
	private int lInactivaEquipo;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEstadoEQM);
		return pk;
	}

	public TVEQMEstado() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEstadoEQM", "" + iCveEstadoEQM);
		hmfieldsTable.put("cDscEstadoEQM", cDscEstadoEQM);
		hmfieldsTable.put("lInactivaEquipo", "" + lInactivaEquipo);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getiCveEstadoEQM() {
		return iCveEstadoEQM;
	}

	public void setiCveEstadoEQM(int iCveEstadoEQM) {
		this.iCveEstadoEQM = iCveEstadoEQM;
	}

	public String getcDscEstadoEQM() {
		return cDscEstadoEQM;
	}

	public void setcDscEstadoEQM(String cDscEstadoEQM) {
		this.cDscEstadoEQM = cDscEstadoEQM;
	}

	public int getLInactivaEquipo() {
		return lInactivaEquipo;
	}

	public void setLInactivaEquipo(int lInactivaEquipo) {
		this.lInactivaEquipo = lInactivaEquipo;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
