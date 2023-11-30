package gob.sct.medprev.vo;   

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object GRLCONVIGPUE
 * </p>
 * <p>
 * Description: Value Object de la Entidad GRLCONVIGPUE
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
public class TVGRLCONVIGPUE implements HashBeanInterface {
	private BeanPK pk;
	private int iCveMdoTrans;
	private int iCveCategoria;
	private int iCvePuesto;
	private int iEdMayor;
	private int iEdMenor;
	private int iTmpVigencia;
	private int iCveUsuGenera;
	private java.sql.Time tIGenerado;
	private int lActivo;
	private String cDscConVigPue;
	private int ianosVigencia;
	private int iMesesVigencia;
	private int iOrden;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveMdoTrans);
		pk.add("" + iCveCategoria);
		pk.add("" + iCvePuesto);
		return pk;
	}

	public TVGRLCONVIGPUE() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveMdoTrans", "" + iCveMdoTrans);
		hmfieldsTable.put("iCveCategoria", "" + iCveCategoria);
		hmfieldsTable.put("iCvePuesto", "" + iCvePuesto);
		hmfieldsTable.put("iEdMayor", "" + iEdMayor);
		hmfieldsTable.put("iEdMenor", "" + iEdMenor);
		hmfieldsTable.put("iTmpVigencia", "" + iTmpVigencia);
		hmfieldsTable.put("iCveUsuGenera", "" + iCveUsuGenera);
		hmfieldsTable.put("tIGenerado", "" + tIGenerado);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscConVigPue", "" + cDscConVigPue);
		hmfieldsTable.put("ianosVigencia", "" + ianosVigencia);
		hmfieldsTable.put("iMesesVigencia", "" + iMesesVigencia);
		hmfieldsTable.put("iOrden", "" + iOrden);
		return hmfieldsTable;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public int getICveCategoria() {
		return iCveCategoria;
	}

	public void setICveCategoria(int iCveCategoria) {
		this.iCveCategoria = iCveCategoria;
	}

	public int getICvePuesto() {
		return iCvePuesto;
	}

	public void setICvePuesto(int iCvePuesto) {
		this.iCvePuesto = iCvePuesto;
	}

	public int getIEdMayor() {
		return iEdMayor;
	}

	public void setIEdMayor(int iEdMayor) {
		this.iEdMayor = iEdMayor;
	}

	public int getIEdMenor() {
		return iEdMenor;
	}

	public void setIEdMenor(int iEdMenor) {
		this.iEdMenor = iEdMenor;
	}

	public int getITmpVigencia() {
		return iTmpVigencia;
	}

	public void setITmpVigencia(int iTmpVigencia) {
		this.iTmpVigencia = iTmpVigencia;
	}

	public int getICveUsuGenera() {
		return iCveUsuGenera;
	}

	public void setICveUsuGenera(int iCveUsuGenera) {
		this.iCveUsuGenera = iCveUsuGenera;
	}

	public java.sql.Time getTIGenerado() {
		return tIGenerado;
	}

	public void setTIGenerado(java.sql.Time tIGenerado) {
		this.tIGenerado = tIGenerado;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getCDscConVigPue() {
		return cDscConVigPue;
	}

	public void setCDscConVigPue(String cDscConVigPue) {
		this.cDscConVigPue = cDscConVigPue;
	}

	public int getIanosVigencia() {
		return ianosVigencia;
	}

	public void setIanosVigencia(int iTmpVigencia) {
		ianosVigencia = iTmpVigencia / 12;
		this.ianosVigencia = ianosVigencia;
	}

	public int getIMesesVigencia() {
		return iMesesVigencia;
	}

	public void setIMesesVigencia(int iTmpVigencia) {
		int ano = iTmpVigencia / 12;
		iMesesVigencia = iTmpVigencia - (ano * 12);
		this.iMesesVigencia = iMesesVigencia;
	}

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

}