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

public class TVGRLECIVIL implements HashBeanInterface {
	private BeanPK pk;
	private int iCveECivil;
	private String cECivil;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveECivil);
		return pk;
	}

	public TVGRLECIVIL() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveECivil", "" + iCveECivil);
		hmfieldsTable.put("cECivil", cECivil);
		return hmfieldsTable;
	}

	public int getICveECivil() {
		return iCveECivil;
	}

	public void setICveECivil(int iCveECivil) {
		this.iCveECivil = iCveECivil;
	}

	public String getCECivil() {
		return cECivil;
	}

	public void setCECivil(String cECivil) {
		this.cECivil = cECivil;
	}

}
