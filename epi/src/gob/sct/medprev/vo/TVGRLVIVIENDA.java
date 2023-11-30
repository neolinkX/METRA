/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * 
 * @author AG SA
 */

public class TVGRLVIVIENDA implements HashBeanInterface {
	private BeanPK pk;
	private int iCveVivienda;
	private String cConcepto;
	private String cDefinicion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveVivienda);
		return pk;
	}

	public TVGRLVIVIENDA() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveVivienda", "" + iCveVivienda);
		hmfieldsTable.put("cConcepto", cConcepto);
		hmfieldsTable.put("cDefinicion", cDefinicion);
		return hmfieldsTable;
	}

	public int getICveVivienda() {
		return iCveVivienda;
	}

	public void setICveVivienda(int iCveVivienda) {
		this.iCveVivienda = iCveVivienda;
	}

	public String getCConcepto() {
		return cConcepto;
	}

	public void setCConcepto(String cConcepto) {
		this.cConcepto = cConcepto;
	}

	public String getCDefinicion() {
		return cDefinicion;
	}

	public void setCDefinicion(String cDefinicion) {
		this.cDefinicion = cDefinicion;
	}
}
