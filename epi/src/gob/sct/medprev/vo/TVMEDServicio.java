package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDServicio
 * </p>
 * <p>
 * Description: VO MedServicio
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
public class TVMEDServicio implements HashBeanInterface {
	private BeanPK pk;
	private int iCveServicio;
	private String cDscServicio;
	private String cObservacion;
	private int lVariosMeds;
	private int lInterConsulta;
	private int lDiagnostico;
	private int lPostDiag;
	private int lActivo;
	private int lReqDiag;
	private int lSUBIRARCHIVOS;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVMEDServicio() {

	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("cObservacion", cObservacion);
		hmfieldsTable.put("lVariosMeds", "" + lVariosMeds);
		hmfieldsTable.put("lInterConsulta", "" + lInterConsulta);
		hmfieldsTable.put("lDiagnostico", "" + lDiagnostico);
		hmfieldsTable.put("lPostDiag", "" + lPostDiag);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("lReqDiag", "" + lReqDiag);
		hmfieldsTable.put("lSUBIRARCHIVOS", "" + lSUBIRARCHIVOS);
		return hmfieldsTable;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public String getCObservacion() {
		return cObservacion;
	}

	public void setCObservacion(String cObservacion) {
		this.cObservacion = cObservacion;
	}

	public int getLVariosMeds() {
		return lVariosMeds;
	}

	public void setLVariosMeds(int lVariosMeds) {
		this.lVariosMeds = lVariosMeds;
	}

	public int getLInterConsulta() {
		return lInterConsulta;
	}

	public void setLInterConsulta(int lInterConsulta) {
		this.lInterConsulta = lInterConsulta;
	}

	public int getLDiagnostico() {
		return lDiagnostico;
	}

	public void setLDiagnostico(int lDiagnostico) {
		this.lDiagnostico = lDiagnostico;
	}

	public int getLPostDiag() {
		return lPostDiag;
	}

	public void setLPostDiag(int lPostDiag) {
		this.lPostDiag = lPostDiag;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public int getLReqDiag() {
		return lReqDiag;
	}

	public void setLReqDiag(int lReqDiag) {
		this.lReqDiag = lReqDiag;
	}

	/**
	 * @return the lSUBIRARCHIVOS
	 */
	public int getlSUBIRARCHIVOS() {
		return lSUBIRARCHIVOS;
	}

	/**
	 * @param lSUBIRARCHIVOS
	 *            the lSUBIRARCHIVOS to set
	 */
	public void setlSUBIRARCHIVOS(int lSUBIRARCHIVOS) {
		this.lSUBIRARCHIVOS = lSUBIRARCHIVOS;
	}
}
