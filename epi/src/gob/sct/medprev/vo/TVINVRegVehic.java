package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object INVRegVehic
 * </p>
 * <p>
 * Description: VO de la entidad INVRegVehic
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suárez Romero
 * @version 1.0
 */
public class TVINVRegVehic implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveMdoTrans;
	private int iIDDGPMPT;
	private int iCveVehiculo;
	private String cMatricula;
	private String cPropietario;
	private int iCveEmpresa;
	private int iCveServPrestado;
	private String cOrigen;
	private String cDestino;
	private int iPerFedInvolucra;
	private int iPerEdoInvolucra;
	private int iPerPartInvolucra;
	private int iCvePaisOr;
	private int iCveEdoOr;
	private int iCvePaisDest;
	private int iCveEdoDest;
	private String cDscServPrestado;
	private String cDscPaisOr;
	private String cDscPaisDest;
	private String cDscEdoOr;
	private String cDscEdoDest;
	private String cDscEmpresa;
	private String cDscMdoTrans;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMdoTrans);
		pk.add("" + iIDDGPMPT);
		pk.add("" + iCveVehiculo);
		return pk;
	}

	public TVINVRegVehic() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iIDDGPMPT", "" + iIDDGPMPT);
		hmfieldsTable.put("iCveVehiculo", "" + iCveVehiculo);
		hmfieldsTable.put("cMatricula", cMatricula);
		hmfieldsTable.put("cPropietario", cPropietario);
		hmfieldsTable.put("iCveEmpresa", "" + iCveEmpresa);
		hmfieldsTable.put("iCveServPrestado", "" + iCveServPrestado);
		hmfieldsTable.put("cOrigen", cOrigen);
		hmfieldsTable.put("cDestino", cDestino);
		hmfieldsTable.put("iPerFedInvolucra", "" + iPerFedInvolucra);
		hmfieldsTable.put("iPerEdoInvolucra", "" + iPerEdoInvolucra);
		hmfieldsTable.put("iPerPartInvolucra", "" + iPerPartInvolucra);
		hmfieldsTable.put("iCvePaisOr", "" + iCvePaisOr);
		hmfieldsTable.put("iCveEdoOr", "" + iCveEdoOr);
		hmfieldsTable.put("iCvePaisDest", "" + iCvePaisDest);
		hmfieldsTable.put("iCveEdoDest", "" + iCveEdoDest);
		hmfieldsTable.put("cDscServPrestado", cDscServPrestado);
		hmfieldsTable.put("cDscPaisOr", cDscPaisOr);
		hmfieldsTable.put("cDscPaisDest", cDscPaisDest);
		hmfieldsTable.put("cDscEdoOr", cDscEdoOr);
		hmfieldsTable.put("cDscEdoDest", cDscEdoDest);
		hmfieldsTable.put("cDscEmpresa", cDscEmpresa);
		hmfieldsTable.put("cDscMdoTrans", cDscMdoTrans);
		hmfieldsTable.put("iTotal", ""
				+ (iPerFedInvolucra + iPerEdoInvolucra + iPerPartInvolucra));

		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getIIDDGPMPT() {
		return iIDDGPMPT;
	}

	public void setIIDDGPMPT(int iIDDGPMPT) {
		this.iIDDGPMPT = iIDDGPMPT;
	}

	public int getICveVehiculo() {
		return iCveVehiculo;
	}

	public void setICveVehiculo(int iCveVehiculo) {
		this.iCveVehiculo = iCveVehiculo;
	}

	public String getCMatricula() {
		return cMatricula;
	}

	public void setCMatricula(String cMatricula) {
		this.cMatricula = cMatricula;
	}

	public String getCPropietario() {
		return cPropietario;
	}

	public void setCPropietario(String cPropietario) {
		this.cPropietario = cPropietario;
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public int getICveServPrestado() {
		return iCveServPrestado;
	}

	public void setICveServPrestado(int iCveServPrestado) {
		this.iCveServPrestado = iCveServPrestado;
	}

	public String getCOrigen() {
		return cOrigen;
	}

	public void setCOrigen(String cOrigen) {
		this.cOrigen = cOrigen;
	}

	public String getCDestino() {
		return cDestino;
	}

	public void setCDestino(String cDestino) {
		this.cDestino = cDestino;
	}

	public int getIPerFedInvolucra() {
		return iPerFedInvolucra;
	}

	public void setIPerFedInvolucra(int iPerFedInvolucra) {
		this.iPerFedInvolucra = iPerFedInvolucra;
	}

	public int getIPerEdoInvolucra() {
		return iPerEdoInvolucra;
	}

	public void setIPerEdoInvolucra(int iPerEdoInvolucra) {
		this.iPerEdoInvolucra = iPerEdoInvolucra;
	}

	public int getIPerPartInvolucra() {
		return iPerPartInvolucra;
	}

	public void setIPerPartInvolucra(int iPerPartInvolucra) {
		this.iPerPartInvolucra = iPerPartInvolucra;
	}

	public int getICvePaisOr() {
		return iCvePaisOr;
	}

	public void setICvePaisOr(int iCvePaisOr) {
		this.iCvePaisOr = iCvePaisOr;
	}

	public int getICveEdoOr() {
		return iCveEdoOr;
	}

	public void setICveEdoOr(int iCveEdoOr) {
		this.iCveEdoOr = iCveEdoOr;
	}

	public int getICvePaisDest() {
		return iCvePaisDest;
	}

	public void setICvePaisDest(int iCvePaisDest) {
		this.iCvePaisDest = iCvePaisDest;
	}

	public int getICveEdoDest() {
		return iCveEdoDest;
	}

	public void setICveEdoDest(int iCveEdoDest) {
		this.iCveEdoDest = iCveEdoDest;
	}

	public String getCDscEdoDest() {
		return cDscEdoDest;
	}

	public String getCDscEdoOr() {
		return cDscEdoOr;
	}

	public String getCDscPaisDest() {
		return cDscPaisDest;
	}

	public String getCDscPaisOr() {
		return cDscPaisOr;
	}

	public String getCDscServPrestado() {
		return cDscServPrestado;
	}

	public void setCDscEdoDest(String cDscEdoDest) {
		this.cDscEdoDest = cDscEdoDest;
	}

	public void setCDscEdoOr(String cDscEdoOr) {
		this.cDscEdoOr = cDscEdoOr;
	}

	public void setCDscPaisDest(String cDscPaisDest) {
		this.cDscPaisDest = cDscPaisDest;
	}

	public void setCDscPaisOr(String cDscPaisOr) {
		this.cDscPaisOr = cDscPaisOr;
	}

	public void setCDscServPrestado(String cDscServPrestado) {
		this.cDscServPrestado = cDscServPrestado;
	}

	public String getCDscEmpresa() {
		return cDscEmpresa;
	}

	public void setCDscEmpresa(String cDscEmpresa) {
		this.cDscEmpresa = cDscEmpresa;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}
}
