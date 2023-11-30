package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXMotBaja
 * </p>
 * <p>
 * Description: VO Tabla TOXMotBaja
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
public class TVTOXMotBaja implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMotBaja;
	private String cDscMotBaja;
	private String cDscBreve;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMotBaja);
		return pk;
	}

	public TVTOXMotBaja() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMotBaja", "" + iCveMotBaja);
		hmfieldsTable.put("cDscMotBaja", cDscMotBaja);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveMotBaja() {
		return iCveMotBaja;
	}

	public void setICveMotBaja(int iCveMotBaja) {
		this.iCveMotBaja = iCveMotBaja;
	}

	public String getCDscMotBaja() {
		return cDscMotBaja;
	}

	public void setCDscMotBaja(String cDscMotBaja) {
		this.cDscMotBaja = cDscMotBaja;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
