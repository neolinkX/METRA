package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MuestraLote
 * </p>
 * <p>
 * Description: VO para TOXMuestraLote
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco antonio Hernández García
 * @version 1.0
 */
public class TVMuestraLote implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveMuestra;
	private int iCveLaboratorio;
	private int iCveLoteCualita;
	private String cFiltrar;
	private String cOrdenar;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveMuestra);
		return pk;
	}

	public TVMuestraLote() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveMuestra", "" + iCveMuestra);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveLoteCualita", "" + iCveLoteCualita);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveMuestra() {
		return iCveMuestra;
	}

	public void setICveMuestra(int iCveMuestra) {
		this.iCveMuestra = iCveMuestra;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getICveLoteCualita() {
		return iCveLoteCualita;
	}

	public void setICveLoteCualita(int iCveLoteCualita) {
		this.iCveLoteCualita = iCveLoteCualita;
	}

	public String getCFiltrar() {
		return cFiltrar;
	}

	public void setCFiltrar(String cFiltrar) {
		this.cFiltrar = cFiltrar;
	}

	public String getCOrdenar() {
		return cOrdenar;
	}

	public void setCOrdenar(String cOrdenar) {
		this.cOrdenar = cOrdenar;
	}
}
