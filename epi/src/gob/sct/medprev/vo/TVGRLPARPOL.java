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

public class TVGRLPARPOL implements HashBeanInterface {
	private BeanPK pk;
	private int iCveParPol;
	private String cDscParPol;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveParPol);
		return pk;
	}

	public TVGRLPARPOL() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveParPol", "" + iCveParPol);
		hmfieldsTable.put("cDscParPol", cDscParPol);
		return hmfieldsTable;
	}

	public int getICveParPol() {
		return iCveParPol;
	}

	public void setICveParPol(int iCveParPol) {
		this.iCveParPol = iCveParPol;
	}

	public String getCDscParPol() {
		return cDscParPol;
	}

	public void setCDscParPol(String cDscParPol) {
		this.cDscParPol = cDscParPol;
	}

}
