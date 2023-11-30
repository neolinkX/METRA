package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TVTSSEGEXAR
 * </p>
 * <p>
 * Description: Value Object de la Entidad TVTSSEGEXAR
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author AG SA SANDOVAL
 * @version 1.0
 */
public class TVTSSEGEXAR implements HashBeanInterface {
	private BeanPK pk;
	private int iCveExpediente;
	private int iNumExamen;
	private int iCveServicio;
	private int iCveUsuAplica;
	private int lConcluido;
	private String cDscServicio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iNumExamen);
		return pk;
	}

	public TVTSSEGEXAR() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);

		hmfieldsTable.put("iCveUsuAplica", "" + iCveUsuAplica);
		hmfieldsTable.put("lConcluido", "" + lConcluido);

		hmfieldsTable.put("cDscServicio", cDscServicio);

		return hmfieldsTable;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getLConcluido() {
		return lConcluido;
	}

	public void setLConcluido(int lConcluido) {
		this.lConcluido = lConcluido;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public int getICveUsuAplica() {
		return iCveUsuAplica;
	}

	public void setICveUsuAplica(int iCveUsuAplica) {
		this.iCveUsuAplica = iCveUsuAplica;
	}

	public boolean equals(Object obj) {
		boolean bRet = true;
		if (obj == null) {
			bRet = false;
		} else if (obj instanceof TVEXPExamAplica) {
			bRet = getPK().equals(((TVEXPExamAplica) obj).getPK());
		} else {
			bRet = false;
		}
		return bRet;
	}

}