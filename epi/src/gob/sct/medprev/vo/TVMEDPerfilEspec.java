package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDPerfilEspec
 * </p>
 * <p>
 * Description: VO de la tabla MEDPerfilEspec
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sánchez
 * @version 1.0
 */

public class TVMEDPerfilEspec implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePerfil;
	private int iCveEspecialidad;
	private String cEspecificacion;
	private String cDscEspecialidad;
	private String cDscPerfil;
	private Vector vcMEDPerfilDiag;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePerfil);
		pk.add("" + iCveEspecialidad);
		return pk;
	}

	public int getICvePerfil() {
		return iCvePerfil;
	}

	public void setICvePerfil(int iCvePerfil) {
		this.iCvePerfil = iCvePerfil;
	}

	public int getICveEspecialidad() {
		return iCveEspecialidad;
	}

	public void setICveEspecialidad(int iCveEspecialidad) {
		this.iCveEspecialidad = iCveEspecialidad;
	}

	public String getCEspecificacion() {
		return cEspecificacion;
	}

	public void setCEspecificacion(String cEspecificacion) {
		this.cEspecificacion = cEspecificacion;
	}

	public TVMEDPerfilEspec() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePerfil", "" + iCvePerfil);
		hmfieldsTable.put("iCveEspecialidad", "" + iCveEspecialidad);
		hmfieldsTable.put("cEspecificacion", cEspecificacion);
		hmfieldsTable.put("cDscEspecialidad", cDscEspecialidad);
		hmfieldsTable.put("cDscPerfil", cDscPerfil);
		hmfieldsTable.put("vcMEDPerfilDiag", vcMEDPerfilDiag);
		return hmfieldsTable;
	}

	public String getCDscEspecialidad() {
		return cDscEspecialidad;
	}

	public void setCDscEspecialidad(String cDscEspecialidad) {
		this.cDscEspecialidad = cDscEspecialidad;
	}

	public String getCDscPerfil() {
		return cDscPerfil;
	}

	public void setCDscPerfil(String cDscPerfil) {
		this.cDscPerfil = cDscPerfil;
	}

	public Vector getVcMEDPerfilDiag() {
		return vcMEDPerfilDiag;
	}

	public void setVcMEDPerfilDiag(Vector vcMEDPerfilDiag) {
		this.vcMEDPerfilDiag = vcMEDPerfilDiag;
	}

	public boolean equals(Object obj) {
		boolean bRet = true;
		if (obj == null)
			bRet = false;
		else if (obj instanceof TVMEDPerfilEspec)
			bRet = getPK().equals(((TVMEDPerfilEspec) obj).getPK());
		else
			bRet = false;
		return bRet;
	}
}