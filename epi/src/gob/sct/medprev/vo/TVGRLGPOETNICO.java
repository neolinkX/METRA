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

public class TVGRLGPOETNICO implements HashBeanInterface {
	private BeanPK pk;
	private int iCveGpoEtnico;
	private String cGpoEtnico;
	private int iCveFamilia;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveGpoEtnico);
		return pk;
	}

	public TVGRLGPOETNICO() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveGpoEtnico", "" + iCveGpoEtnico);
		hmfieldsTable.put("cGpoEtnico", cGpoEtnico);
		hmfieldsTable.put("iCveFamilia", iCveFamilia);
		return hmfieldsTable;
	}

	public int getICveGpoEtnico() {
		return iCveGpoEtnico;
	}

	public void setICveGpoEtnico(int iCveGpoEtnico) {
		this.iCveGpoEtnico = iCveGpoEtnico;
	}

	public String getCGpoEtnico() {
		return cGpoEtnico;
	}

	public void setCGpoEtnico(String cGpoEtnico) {
		this.cGpoEtnico = cGpoEtnico;
	}

	public int getICveFamilia() {
		return iCveFamilia;
	}

	public void setICveFamilia(int iCveFamilia) {
		this.iCveFamilia = iCveFamilia;
	}

}
