package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object GRLEtapa
 * </p>
 * <p>
 * Description: Value Object de la Tabla GRLEtapa
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */
public class TVGRLEtapa implements HashBeanInterface {
	private BeanPK pk;
	private int iCveProceso;
	private int iCveEtapa;
	private String cDscEtapa;
	private int lActivo;
	private String cDocumento;

	private String cObservacion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveProceso);
		pk.add("" + iCveEtapa);
		return pk;
	}

	public TVGRLEtapa() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iCveEtapa", "" + iCveEtapa);
		hmfieldsTable.put("cDscEtapa", "" + cDscEtapa);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDocumento", "" + cDocumento);
		return hmfieldsTable;
	}

	public int getiCveProceso() {
		return iCveProceso;
	}

	public void setiCveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getiCveEtapa() {
		return iCveEtapa;
	}

	public void setiCveEtapa(int iCveEtapa) {
		this.iCveEtapa = iCveEtapa;
	}

	public String getcDscEtapa() {
		return cDscEtapa;
	}

	public void setcDscEtapa(String cDscEtapa) {
		this.cDscEtapa = cDscEtapa;
	}

	public int getlActivo() {
		return lActivo;
	}

	public void setlActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getcDocumento() {
		return cDocumento;
	}

	public void setcDocumento(String cDocumento) {
		this.cDocumento = cDocumento;
	}

}
