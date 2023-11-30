package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object MEDServExamen
 * </p>
 * <p>
 * Description: VO Configuración de los Examenes
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */
public class TVMEDServExamen implements HashBeanInterface {
	private BeanPK pk;
	private int iCveProceso;
	private int iCveMotivo;
	private int iCveServicio;
	private String cDscProceso;
	private String cDscMotivo;
	private String cDscServicio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveProceso);
		pk.add("" + iCveMotivo);
		pk.add("" + iCveServicio);
		return pk;
	}

	public TVMEDServExamen() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("cDscProceso", "" + cDscProceso);
		hmfieldsTable.put("cDscMotivo", "" + cDscMotivo);
		hmfieldsTable.put("cDscServicio", "" + cDscServicio);
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

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public String getCDscProceso() {
		return cDscProceso;
	}

	public void setCDscProceso(String cDscProceso) {
		this.cDscProceso = cDscProceso;
	}

	public String getCDscMotivo() {
		return cDscMotivo;
	}

	public void setCDscMotivo(String cDscMotivo) {
		this.cDscMotivo = cDscMotivo;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}
}
