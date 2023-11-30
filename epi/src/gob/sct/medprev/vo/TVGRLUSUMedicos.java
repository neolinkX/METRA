package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;

/**
 * <p>
 * Title: Value Object GRLUSUMedicos
 * </p>
 * <p>
 * Description: VO de la entidad GRLUSUMedicos
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
public class TVGRLUSUMedicos implements HashBeanInterface, Serializable {
	private BeanPK pk;
	private int iCveUsuario;
	private String cNombreCompleto;
	private int iCveUniMed;
	private String cDscUniMed;
	private int iCveProceso;
	private String cDscProceso;
	private int iCveModulo;
	private String cDscModulo;
	private int iCveServicio;
	private String cDscServicio;
	private int iCveRama;
	private String cDscRama;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUsuario);
		pk.add("" + iCveUniMed);
		pk.add("" + iCveProceso);
		pk.add("" + iCveModulo);
		pk.add("" + iCveServicio);
		pk.add("" + iCveRama);
		return pk;
	}

	public TVGRLUSUMedicos() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveUsuario", "" + iCveUsuario);
		hmfieldsTable.put("cNombreCompleto", cNombreCompleto);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("cDscProceso", cDscProceso);
		hmfieldsTable.put("iCveModulo", "" + iCveModulo);
		hmfieldsTable.put("cDscModulo", cDscModulo);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("cDscServicio", "" + cDscServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("cDscRama", "" + cDscRama);
		return hmfieldsTable;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

	public int getICveUsuario() {
		return iCveUsuario;
	}

	public void setICveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getICveProceso() {
		return iCveProceso;
	}

	public void setICveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getICveModulo() {
		return iCveModulo;
	}

	public void setICveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getICveRama() {
		return iCveRama;
	}

	public void setICveRama(int iCveRama) {
		this.iCveRama = iCveRama;
	}

	public String getCDscRama() {
		return cDscRama;
	}

	public void setCDscRama(String cDscRama) {
		this.cDscRama = cDscRama;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public String getCDscModulo() {
		return cDscModulo;
	}

	public void setCDscModulo(String cDscModulo) {
		this.cDscModulo = cDscModulo;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public String getCNombreCompleto() {
		return cNombreCompleto;
	}

	public void setCNombreCompleto(String cNombreCompleto) {
		this.cNombreCompleto = cNombreCompleto;
	}
}
