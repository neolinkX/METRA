package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLPuesto
 * </p>
 * <p>
 * Description: Vlue Object de la Entidad GRLPuesto
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suárez Romero
 * @version 1.0
 */
public class TVGRLPuesto implements HashBeanInterface {
	private BeanPK pk;
	private int iCveModoTrans;
	private int iCvePuesto;
	private int iCveCategoria;
	private String cDscCategoria;
	private String cDscPuesto;
	private int iCveGrupo;
	private int iEdadMinima;
	private String cDscGrupo;
	private String cDscMdoTrans;
	private int lActivo;
	private int iExamen;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveModoTrans);
		pk.add("" + iCveCategoria);
		pk.add("" + iCvePuesto);
		return pk;
	}

	public TVGRLPuesto() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveModoTrans", "" + iCveModoTrans);
		hmfieldsTable.put("iCvePuesto", "" + iCvePuesto);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("cDscCategoria", cDscCategoria);
		hmfieldsTable.put("cDscPuesto", cDscPuesto);
		hmfieldsTable.put("iCveGrupo", "" + iCveGrupo);
		hmfieldsTable.put("iEdadMinima", "" + iEdadMinima);
		hmfieldsTable.put("cDscGrupo", "" + cDscGrupo);
		hmfieldsTable.put("cDscMdoTrans", "" + cDscMdoTrans);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("iExamen", "" + iExamen);

		return hmfieldsTable;
	}

	public int getICveModoTrans() {
		return iCveModoTrans;
	}

	public void setICveModoTrans(int iCveModoTrans) {
		this.iCveModoTrans = iCveModoTrans;
	}

	public int getICvePuesto() {
		return iCvePuesto;
	}

	public void setICvePuesto(int iCvePuesto) {
		this.iCvePuesto = iCvePuesto;
	}

	public int getICveCategoria() {
		return iCveCategoria;
	}

	public void setICveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

	public String getCDscCategoria() {
		return cDscCategoria;
	}

	public void setCDscCategoria(String cDscCategoria) {
		this.cDscCategoria = cDscCategoria;
	}

	public String getCDscPuesto() {
		return cDscPuesto;
	}

	public void setCDscPuesto(String cDscPuesto) {
		this.cDscPuesto = cDscPuesto;
	}

	public int getICveGrupo() {
		return iCveGrupo;
	}

	public void setICveGrupo(int iCveGrupo) {
		this.iCveGrupo = iCveGrupo;
	}

	public int getIEdadMinima() {
		return iEdadMinima;
	}

	public void setIEdadMinima(int iEdadMinima) {
		this.iEdadMinima = iEdadMinima;
	}

	public String getCDscGrupo() {
		return cDscGrupo;
	}

	public void setCDscGrupo(String cDscGrupo) {
		this.cDscGrupo = cDscGrupo;
	}

	public String getCDscMdoTrans() {
		return cDscMdoTrans;
	}

	public void setCDscMdoTrans(String cDscMdoTrans) {
		this.cDscMdoTrans = cDscMdoTrans;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public int getIExamen() {
		return iExamen;
	}

	public void setIExamen(int iExamen) {
		this.iExamen = iExamen;
	}

}