package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object TOXTurnoValida
 * </p>
 * <p>
 * Description: VO para la trabla TOXTurnoValida
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVTOXTurnoValida implements HashBeanInterface {
	private BeanPK pk;
	private int iCveTurnoValida;
	private String cDscTurno;
	private String cDscBreve;
	private int iCveUsuRespon;
	private int iCveArea;
	private String cDscArea;
	private String cDscUsuResponsable;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTurnoValida);
		return pk;
	}

	public TVTOXTurnoValida() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveTurnoValida", "" + iCveTurnoValida);
		hmfieldsTable.put("cDscTurno", cDscTurno);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("iCveUsuRespon", "" + iCveUsuRespon);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("cDscArea", cDscArea);
		hmfieldsTable.put("cDscUsuResponsable", cDscUsuResponsable);

		return hmfieldsTable;
	}

	public int getICveTurnoValida() {
		return iCveTurnoValida;
	}

	public void setICveTurnoValida(int iCveTurnoValida) {
		this.iCveTurnoValida = iCveTurnoValida;
	}

	public String getCDscTurno() {
		return cDscTurno;
	}

	public void setCDscTurno(String cDscTurno) {
		this.cDscTurno = cDscTurno;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public int getICveUsuRespon() {
		return iCveUsuRespon;
	}

	public void setICveUsuRespon(int iCveUsuRespon) {
		this.iCveUsuRespon = iCveUsuRespon;
	}

	public int getICveArea() {
		return iCveArea;
	}

	public void setICveArea(int iCveArea) {
		this.iCveArea = iCveArea;
	}

	public String getCDscArea() {
		return cDscArea;
	}

	public void setCDscArea(String cDscArea) {
		this.cDscArea = cDscArea;
	}

	public String getCDscUsuResponsable() {
		return cDscUsuResponsable;
	}

	public void setCDscUsuResponsable(String cDscUsuResponsable) {
		this.cDscUsuResponsable = cDscUsuResponsable;
	}

}
