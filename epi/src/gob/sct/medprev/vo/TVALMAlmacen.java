package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object ALMAlmacen
 * </p>
 * <p>
 * Description: VO Tabla ALMAlmacen
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */
public class TVALMAlmacen implements HashBeanInterface {
	private BeanPK pk;
	private int iCveAlmacen;
	private int iCveUniMed;
	private String cDscAlmacen;
	private int iCveUsuResp;
	private int lActivo;
	private String cDscUniMed;
	private int iCveUsuario;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveUniMed);
		pk.add("" + iCveAlmacen);
		return pk;
	}

	public TVALMAlmacen() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveUniMed", "" + iCveUniMed);
		hmfieldsTable.put("cDscAlmacen", cDscAlmacen);
		hmfieldsTable.put("iCveUsuResp", "" + iCveUsuResp);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscUniMed", cDscUniMed);
		hmfieldsTable.put("iCveUsuario", "" + iCveUsuario);
		hmfieldsTable.put("cNombre", cNombre);
		hmfieldsTable.put("cApPaterno", cApPaterno);
		hmfieldsTable.put("cApMaterno", cApMaterno);
		return hmfieldsTable;
	}

	public int getICveAlmacen() {
		return iCveAlmacen;
	}

	public void setICveAlmacen(int iCveAlmacen) {
		this.iCveAlmacen = iCveAlmacen;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public String getCDscAlmacen() {
		return cDscAlmacen;
	}

	public void setCDscAlmacen(String cDscAlmacen) {
		this.cDscAlmacen = cDscAlmacen;
	}

	public int getICveUsuResp() {
		return iCveUsuResp;
	}

	public void setICveUsuResp(int iCveUsuResp) {
		this.iCveUsuResp = iCveUsuResp;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getCDscUniMed() {
		return cDscUniMed;
	}

	public void setCDscUniMed(String cDscUniMed) {
		this.cDscUniMed = cDscUniMed;
	}

	public int getICveUsuario() {
		return iCveUsuario;
	}

	public void setICveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCApPaterno() {
		return cApPaterno;
	}

	public void setCApPaterno(String cApPaterno) {
		this.cApPaterno = cApPaterno;
	}

	public String getCApMaterno() {
		return cApMaterno;
	}

	public void setCApMaterno(String cApMaterno) {
		this.cApMaterno = cApMaterno;
	}

}
