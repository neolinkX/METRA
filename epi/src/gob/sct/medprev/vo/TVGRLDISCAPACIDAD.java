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

public class TVGRLDISCAPACIDAD implements HashBeanInterface {
	private BeanPK pk;
	private int iCveDiscapacidad;
	private int iCveGrupoD;
	private String iCveSubGrupoD;
	private String cDscGrupoD;
	private String cDscSubGrupoD;
	private String cDscDiscapacidad;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveDiscapacidad);
		return pk;
	}

	public TVGRLDISCAPACIDAD() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveDiscapacidad", "" + iCveDiscapacidad);
		hmfieldsTable.put("iCveGrupoD", "" + iCveGrupoD);
		hmfieldsTable.put("iCveSubGrupoD", iCveSubGrupoD);
		hmfieldsTable.put("cDscGrupoD", cDscGrupoD);
		hmfieldsTable.put("cDscSubGrupoD", cDscSubGrupoD);
		hmfieldsTable.put("cDscDiscapacidad", cDscDiscapacidad);

		return hmfieldsTable;
	}

	public int getICveDiscapacidad() {
		return iCveDiscapacidad;
	}

	public void setICveDiscapacidad(int iCveDiscapacidad) {
		this.iCveDiscapacidad = iCveDiscapacidad;
	}

	public int getICveGrupoD() {
		return iCveGrupoD;
	}

	public void setICveGrupoD(int iCveGrupoD) {
		this.iCveGrupoD = iCveGrupoD;
	}

	public String getICveSubGrupoD() {
		return iCveSubGrupoD;
	}

	public void setICveSubGrupoD(String iCveSubGrupoD) {
		this.iCveSubGrupoD = iCveSubGrupoD;
	}

	public String getCDscGrupoD() {
		return cDscGrupoD;
	}

	public void setCDscGrupoD(String cDscGrupoD) {
		this.cDscGrupoD = cDscGrupoD;
	}

	public String getCDscSubGrupoD() {
		return cDscSubGrupoD;
	}

	public void setCDscSubGrupoD(String cDscSubGrupoD) {
		this.cDscSubGrupoD = cDscSubGrupoD;
	}

	public String getCDscDiscapacidad() {
		return cDscDiscapacidad;
	}

	public void setCDscDiscapacidad(String cDscDiscapacidad) {
		this.cDscDiscapacidad = cDscDiscapacidad;
	}

}
