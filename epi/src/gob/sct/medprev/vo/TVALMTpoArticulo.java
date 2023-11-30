package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMTpoArticulo
 * </p>
 * <p>
 * Description: TV de ALMTpoArticulo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Juan Manuel Vazquez
 * @version 1.0
 */
public class TVALMTpoArticulo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTpoArticulo;
	private String cDscTpoArticulo;
	private String cDscBreve;
	private int iIDPartida;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoArticulo);
		return pk;
	}

	public TVALMTpoArticulo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTpoArticulo", "" + iCveTpoArticulo);
		hmfieldsTable.put("cDscTpoArticulo", cDscTpoArticulo);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("iIDPartida", "" + iIDPartida);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveTpoArticulo() {
		return iCveTpoArticulo;
	}

	public void setICveTpoArticulo(int iCveTpoArticulo) {
		this.iCveTpoArticulo = iCveTpoArticulo;
	}

	public String getCDscTpoArticulo() {
		return cDscTpoArticulo;
	}

	public void setCDscTpoArticulo(String cDscTpoArticulo) {
		this.cDscTpoArticulo = cDscTpoArticulo;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getIIDPartida() {
		return iIDPartida;
	}

	public void setIIDPartida(int iIDPartida) {
		this.iIDPartida = iIDPartida;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
