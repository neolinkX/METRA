package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object MEDPerfilSintoma
 * </p>
 * <p>
 * Description: VO de la tabla MEDPerfilSintoma
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sánchez
 * @version 1.0
 */

public class TVMEDPerfilSintoma implements HashBeanInterface {
	private BeanPK pk;
	private int iCvePerfil;
	private int iCveServicio;
	private int iCveRama;
	private int iCveSintoma;
	private float dValorI;
	private float dValorF;
	private int lLogico;
	private String cCaracter;
	private String cPregunta;
	private String cDscTpoResp;
	private int iCveTpoResp;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCvePerfil);
		pk.add("" + iCveServicio);
		pk.add("" + iCveRama);
		pk.add("" + iCveSintoma);
		return pk;
	}

	public int getICvePerfil() {
		return iCvePerfil;
	}

	public void setICvePerfil(int iCvePerfil) {
		this.iCvePerfil = iCvePerfil;
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

	public float getDValorI() {
		return dValorI;
	}

	public void setDValorI(float dValorI) {
		this.dValorI = dValorI;
	}

	public float getDValorF() {
		return dValorF;
	}

	public void setDValorF(float dValorF) {
		this.dValorF = dValorF;
	}

	public int getLLogico() {
		return lLogico;
	}

	public void setLLogico(int lLogico) {
		this.lLogico = lLogico;
	}

	public String getCCaracter() {
		return cCaracter;
	}

	public void setCCaracter(String cCaracter) {
		this.cCaracter = cCaracter;
	}

	public TVMEDPerfilSintoma() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCvePerfil", "" + iCvePerfil);
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("iCveSintoma", "" + iCveSintoma);
		hmfieldsTable.put("dValorI", "" + dValorI);
		hmfieldsTable.put("dValorF", "" + dValorF);
		hmfieldsTable.put("lLogico", "" + lLogico);
		hmfieldsTable.put("cCaracter", cCaracter);
		hmfieldsTable.put("cPregunta", cPregunta);
		hmfieldsTable.put("iCveTpoResp", "" + iCveTpoResp);
		hmfieldsTable.put("cDscTpoResp", cDscTpoResp);
		return hmfieldsTable;
	}

	public String getCPregunta() {
		return cPregunta;
	}

	public void setCPregunta(String cPregunta) {
		this.cPregunta = cPregunta;
	}

	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
	}

	public String getCDscTpoResp() {
		return cDscTpoResp;
	}

	public void setCDscTpoResp(String cDscTpoResp) {
		this.cDscTpoResp = cDscTpoResp;
	}
}