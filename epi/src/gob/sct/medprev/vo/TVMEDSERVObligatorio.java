package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TVMEDSERVObligatorio
 * </p>
 * 
 * @author AG SA L 01/10/2013
 */
public class TVMEDSERVObligatorio implements HashBeanInterface {
	private BeanPK pk;
	private int iCveProceso;
	private int iCveMotivo;
	private int iCveMdoTrans;
	private int iCveServicio;
	private int iCveUsuregistra;
	private java.sql.Timestamp tRegistro;
	private String cSexo;

	private String cDscServicio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveProceso);
		pk.add("" + iCveMotivo);
		return pk;
	}

	public TVMEDSERVObligatorio() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveUsuregistra", "" + iCveUsuregistra);

		hmfieldsTable.put("tRegistro", "" + tRegistro);
		hmfieldsTable.put("cSexo", cSexo);

		hmfieldsTable.put("cDscServicio", cDscServicio);

		return hmfieldsTable;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getICveUsuregistra() {
		return iCveUsuregistra;
	}

	public void setTRegistro(java.sql.Timestamp tRegistro) {
		this.tRegistro = tRegistro;
	}

	public String getCSexo() {
		return cSexo;
	}

	public void setCSexo(String cSexo) {
		this.cSexo = cSexo;
	}

	public String getCDcsServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

}