package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object MEDSintExamen
 * </p>
 * <p>
 * Description: VO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Oscar Castrej�n Adame.
 * @version 1.0
 */
public class TVMEDSintExamen implements HashBeanInterface {
	private BeanPK pk;
	private int iCveProceso;
	private int iCveMotivo;
	private int iCveServicio;
	private int iCveRama;
	private int iCveSintoma;
	private int iCveMdoTrans;
	private String cValRef;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveProceso);
		pk.add("" + iCveMotivo);
		pk.add("" + iCveServicio);
		pk.add("" + iCveRama);
		pk.add("" + iCveSintoma);
		pk.add("" + iCveMdoTrans);
		return pk;
	}

	public TVMEDSintExamen() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveProceso", "" + iCveProceso);
		hmfieldsTable.put("iCveMotivo", "" + iCveMotivo);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("iCveSintoma", "" + iCveSintoma);
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
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

	public int getICveRama() {
		return iCveRama;
	}

	public void setICveRama(int iCveRama) {
		this.iCveRama = iCveRama;
	}

	public int getICveSintoma() {
		return iCveSintoma;
	}

	public void setICveSintoma(int iCveSintoma) {
		this.iCveSintoma = iCveSintoma;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public String getCValRef() {
		return cValRef;
	}

	public void setCValRef(String cValRef) {
		this.cValRef = cValRef;
	}
}
