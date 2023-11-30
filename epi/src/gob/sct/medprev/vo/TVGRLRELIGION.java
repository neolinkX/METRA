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

public class TVGRLRELIGION implements HashBeanInterface {
	private BeanPK pk;
	private int iCveReligion;
	private int iCveGrupo;
	private int iCveSubGrupo;
	private String cGrupo;
	private String cSubGrupo;
	private String cCodDsc;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveReligion);
		return pk;
	}

	public TVGRLRELIGION() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveReligion", "" + iCveReligion);
		hmfieldsTable.put("iCveGrupo", "" + iCveGrupo);
		hmfieldsTable.put("iCveSubGrupo", iCveSubGrupo);
		hmfieldsTable.put("cGrupo", cGrupo);
		hmfieldsTable.put("cSubGrupo", cSubGrupo);
		hmfieldsTable.put("cCodDsc", cCodDsc);

		return hmfieldsTable;
	}

	public int getICveReligion() {
		return iCveReligion;
	}

	public void setICveReligion(int iCveReligion) {
		this.iCveReligion = iCveReligion;
	}

	public int getICveGrupo() {
		return iCveGrupo;
	}

	public void setICveGrupo(int iCveGrupo) {
		this.iCveGrupo = iCveGrupo;
	}

	public int getICveSubGrupo() {
		return iCveSubGrupo;
	}

	public void setICveSubGrupo(int iCveSubGrupo) {
		this.iCveSubGrupo = iCveSubGrupo;
	}

	public String getCGrupo() {
		return cGrupo;
	}

	public void setCGrupo(String cGrupo) {
		this.cGrupo = cGrupo;
	}

	public String getCSubGrupo() {
		return cSubGrupo;
	}

	public void setCSubGrupoD(String cSubGrupo) {
		this.cSubGrupo = cSubGrupo;
	}

	public String getCCodDsc() {
		return cCodDsc;
	}

	public void setCCodDsc(String cCodDsc) {
		this.cCodDsc = cCodDsc;
	}

}
