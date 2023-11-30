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

public class TVGRLNIVELSEC implements HashBeanInterface {
	private BeanPK pk;
	private int iCveNivelSEC;
	private String cNivelSEC;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveNivelSEC);
		return pk;
	}

	public TVGRLNIVELSEC() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveNivelSEC", "" + iCveNivelSEC);
		hmfieldsTable.put("cNivelSEC", cNivelSEC);
		return hmfieldsTable;
	}

	public int getICveNivelSEC() {
		return iCveNivelSEC;
	}

	public void setICveNivelSEC(int iCveNivelSEC) {
		this.iCveNivelSEC = iCveNivelSEC;
	}

	public String getCNivelSEC() {
		return cNivelSEC;
	}

	public void setCNivelSEC(String cNivelSEC) {
		this.cNivelSEC = cNivelSEC;
	}

}
