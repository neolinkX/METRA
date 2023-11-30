package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object AUXEMO
 * </p>
 * <p>
 * Description: VO Auxiliar Examen Anterior
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. González Paz
 * @version 1.0
 */
public class TVAUXEMO implements HashBeanInterface {
	private BeanPK pk;
	private int iCveNumEmpresa;
	private int iCveCategoria;
	private int iCveMotivo;
	private int iCveGrupo;
	private int iCvePuesto;
	private int iCveMomentoAP;
	private String cLicencia;
	private String cDscEmpresa;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveNumEmpresa);
		pk.add("" + iCveCategoria);
		pk.add("" + iCveMotivo);
		pk.add("" + iCveGrupo);
		pk.add("" + iCvePuesto);
		pk.add("" + iCveMomentoAP);
		pk.add(cLicencia);
		return pk;
	}

	public TVAUXEMO() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveNumEmpresa", "" + iCveNumEmpresa);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("iCveGrupo", "" + iCveGrupo);
		hmfieldsTable.put("iCvePuesto", "" + iCvePuesto);
		hmfieldsTable.put("iCveMomentoAP", "" + iCveMomentoAP);
		hmfieldsTable.put("cLicencia", cLicencia);
		hmfieldsTable.put("cDscEmpresa", cDscEmpresa);
		return hmfieldsTable;
	}

	public int getICveNumEmpresa() {
		return iCveNumEmpresa;
	}

	public void setICveNumEmpresa(int iCveNumEmpresa) {
		this.iCveNumEmpresa = iCveNumEmpresa;
	}

	public int getICveCategoria() {
		return iCveCategoria;
	}

	public void setICveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

	public int getICveMotivo() {
		return iCveMotivo;
	}

	public void setICveMotivo(int iCveMotivo) {
		this.iCveMotivo = iCveMotivo;
	}

	public int getICveGrupo() {
		return iCveGrupo;
	}

	public void setICveGrupo(int iCveGrupo) {
		this.iCveGrupo = iCveGrupo;
	}

	public int getICvePuesto() {
		return iCvePuesto;
	}

	public void setICvePuesto(int iCvePuesto) {
		this.iCvePuesto = iCvePuesto;
	}

	public int getICveMomentoAP() {
		return iCveMomentoAP;
	}

	public void setICveMomentoAP(int iCveMomentoAP) {
		this.iCveMomentoAP = iCveMomentoAP;
	}

	public String getCLicencia() {
		return cLicencia;
	}

	public void setCLicencia(String cLicencia) {
		this.cLicencia = cLicencia;
	}

	public String getCDscEmpresa() {
		return cDscEmpresa;
	}

	public void setCDscEmpresa(String cDscEmpresa) {
		this.cDscEmpresa = cDscEmpresa;
	}
}
