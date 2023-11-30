package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object VEHConfControl
 * </p>
 * <p>
 * Description: Value Object de Configuración de Controles
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 */
public class TVVEHConfControl implements HashBeanInterface {
	private BeanPK pk;
	private int iCveEtapaSolic;
	private String cDscEtapaSolic;
	private int iCveConfControl;
	private String cDscTpoResp;
	private String cDscBreve;
	private String cEtiqueta;
	private int iCveTpoResp;
	private String cCampo;
	private int lObligatorio;
	private int lActivo;
	private int iOrden;
	private String cDscTpoResp2;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveEtapaSolic);
		pk.add("" + iCveConfControl);
		return pk;
	}

	public TVVEHConfControl() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveEtapaSolic", "" + iCveEtapaSolic);
		hmfieldsTable.put("cDscEtapaSolic", cDscEtapaSolic);
		hmfieldsTable.put("iCveConfControl", "" + iCveConfControl);
		hmfieldsTable.put("cDscTpoResp", cDscTpoResp);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cEtiqueta", cEtiqueta);
		hmfieldsTable.put("iCveTpoResp", "" + iCveTpoResp);
		hmfieldsTable.put("cDscTpoResp", cDscTpoResp);
		hmfieldsTable.put("cCampo", cCampo);
		hmfieldsTable.put("lObligatorio", "" + lObligatorio);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("iOrden", "" + iOrden);
		hmfieldsTable.put("cDscTpoResp2", cDscTpoResp2);

		return hmfieldsTable;
	}

	public int getICveEtapaSolic() {
		return iCveEtapaSolic;
	}

	public void setICveEtapaSolic(int iCveEtapaSolic) {
		this.iCveEtapaSolic = iCveEtapaSolic;
	}

	public String getCDscEtapaSolic() {
		return cDscEtapaSolic;
	}

	public void setCDscEtapaSolic(String cDscEtapaSolic) {
		this.cDscEtapaSolic = cDscEtapaSolic;
	}

	public int getICveConfControl() {
		return iCveConfControl;
	}

	public void setICveConfControl(int iCveConfControl) {
		this.iCveConfControl = iCveConfControl;
	}

	public String getCDscTpoResp() {
		return cDscTpoResp;
	}

	public void setCDscTpoResp(String cDscTpoResp) {
		this.cDscTpoResp = cDscTpoResp;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCEtiqueta() {
		return cEtiqueta;
	}

	public void setCEtiqueta(String cEtiqueta) {
		this.cEtiqueta = cEtiqueta;
	}

	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
	}

	public String getCCampo() {
		return cCampo;
	}

	public void setCCampo(String cCampo) {
		this.cCampo = cCampo;
	}

	public int getLObligatorio() {
		return lObligatorio;
	}

	public void setLObligatorio(int lObligatorio) {
		this.lObligatorio = lObligatorio;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

	public String getCDscTpoResp2() {
		return cDscTpoResp2;
	}

	public void setCDscTpoResp2(String cDscTpoResp2) {
		this.cDscTpoResp2 = cDscTpoResp2;
	}

}
