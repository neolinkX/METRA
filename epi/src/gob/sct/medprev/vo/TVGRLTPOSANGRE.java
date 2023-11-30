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

public class TVGRLTPOSANGRE implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoSangre;
	private String cTpoSangre;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoSangre);
		return pk;
	}

	public TVGRLTPOSANGRE() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoSangre", "" + iCveTpoSangre);
		hmfieldsTable.put("cTpoSangre", cTpoSangre);
		return hmfieldsTable;
	}

	public int getICveTpoSangre() {
		return iCveTpoSangre;
	}

	public void setICveTpoSangre(int iCveTpoSangre) {
		this.iCveTpoSangre = iCveTpoSangre;
	}

	public String getCTpoSangre() {
		return cTpoSangre;
	}

	public void setCTpoSangre(String cTpoSangre) {
		this.cTpoSangre = cTpoSangre;
	}

}
