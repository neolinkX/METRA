package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object MEDSintoma
 * </p>
 * <p>
 * Description: VO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */
public class TVMEDSintoma implements HashBeanInterface {
	private BeanPK pk;
	private int iCveServicio;
	private int iCveRama;
	private int iCveSintoma;
	private String cDscBreve;
	private String cPregunta;
	private String cGenero;
	private int lEstudio;
	private int iCveTpoResp;
	private String cEtiqueta;
	private int lCPersonal;
	private int lObligatorio;
	private int lEvalAuto;
	private int lActivo;
	private String cDscTpoResp;
	private String cDscServicio;
	private String cDscRama;
	private int iOrden;
	private String cValRef;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveServicio);
		pk.add("" + iCveRama);
		pk.add("" + iCveSintoma);
		return pk;
	}

	public TVMEDSintoma() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("iCveSintoma", "" + iCveSintoma);
		hmfieldsTable.put("cDscBreve", cDscBreve);
		hmfieldsTable.put("cPregunta", cPregunta);
		hmfieldsTable.put("cGenero", cGenero);
		hmfieldsTable.put("lEstudio", "" + lEstudio);
		hmfieldsTable.put("iCveTpoResp", "" + iCveTpoResp);
		hmfieldsTable.put("cEtiqueta", cEtiqueta);
		hmfieldsTable.put("lCPersonal", "" + lCPersonal);
		hmfieldsTable.put("lObligatorio", "" + lObligatorio);
		hmfieldsTable.put("lEvalAuto", "" + lEvalAuto);
		hmfieldsTable.put("lActivo", "" + lActivo);
		hmfieldsTable.put("cDscTpoResp", cDscTpoResp);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("cDscRama", cDscRama);
		hmfieldsTable.put("iOrden", "" + iOrden);
		hmfieldsTable.put("cValRef", cValRef);

		return hmfieldsTable;
	}

	public int getICveServicio() {
		return iCveServicio;
	}

	public void setICveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getICveRama() {
		return iCveRama;
	}

	public void setICveRama(int iCveRama) {
		this.iCveRama = iCveRama;
	}

	public int getICveSintoma() {
		return iCveSintoma;
	}

	public void setICveSintoma(int iCveSintoma) {
		this.iCveSintoma = iCveSintoma;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCPregunta() {
		return cPregunta;
	}

	public void setCPregunta(String cPregunta) {
		this.cPregunta = cPregunta;
	}

	public String getCGenero() {
		return cGenero;
	}

	public void setCGenero(String cGenero) {
		this.cGenero = cGenero;
	}

	public int getLEstudio() {
		return lEstudio;
	}

	public void setLEstudio(int lEstudio) {
		this.lEstudio = lEstudio;
	}

	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
	}

	public String getCEtiqueta() {
		return cEtiqueta;
	}

	public void setCEtiqueta(String cEtiqueta) {
		this.cEtiqueta = cEtiqueta;
	}

	public int getLCPersonal() {
		return lCPersonal;
	}

	public void setLCPersonal(int lCPersonal) {
		this.lCPersonal = lCPersonal;
	}

	public int getLObligatorio() {
		return lObligatorio;
	}

	public void setLObligatorio(int lObligatorio) {
		this.lObligatorio = lObligatorio;
	}

	public int getLEvalAuto() {
		return lEvalAuto;
	}

	public void setLEvalAuto(int lEvalAuto) {
		this.lEvalAuto = lEvalAuto;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}

	public String getCDscTpoResp() {
		return cDscTpoResp;
	}

	public void setCDscTpoResp(String cDscTpoResp) {
		this.cDscTpoResp = cDscTpoResp;
	}

	public String getCDscServicio() {
		return cDscServicio;
	}

	public void setCDscServicio(String cDscServicio) {
		this.cDscServicio = cDscServicio;
	}

	public String getCDscRama() {
		return cDscRama;
	}

	public void setCDscRama(String cDscRama) {
		this.cDscRama = cDscRama;
	}

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

	public String getCValRef() {
		return cValRef;
	}

	public void setCValRef(String cValRef) {
		this.cValRef = cValRef;
	}

}
