package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * 
 * @author AG SA
 */
public class TVMEDRespSint implements HashBeanInterface {
	private BeanPK pk;
	private int iCveServicio;
	private int iCveRama;
	private int iCveSintoma;
	private int iCveTpoResp;
	private int iOrden;
	private int iLogica;
	private String cDescripcion;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveTpoResp);
		return pk;
	}

	public TVMEDRespSint() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveServicio", "" + iCveServicio);
		hmfieldsTable.put("iCveRama", "" + iCveRama);
		hmfieldsTable.put("iCveSintoma", "" + iCveSintoma);
		hmfieldsTable.put("iCveTpoResp", "" + iCveTpoResp);
		hmfieldsTable.put("iOrden", "" + iOrden);
		hmfieldsTable.put("iLogica", iLogica);
		hmfieldsTable.put("cDescripcion", "" + cDescripcion);
		return hmfieldsTable;
	}

	public int getICveTpoResp() {
		return iCveTpoResp;
	}

	public void setICveTpoResp(int iCveTpoResp) {
		this.iCveTpoResp = iCveTpoResp;
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

	public int getIOrden() {
		return iOrden;
	}

	public void setIOrden(int iOrden) {
		this.iOrden = iOrden;
	}

	public int getILogica() {
		return iLogica;
	}

	public void setILogica(int iLogica) {
		this.iLogica = iLogica;
	}

	public String getCDescripcion() {
		return cDescripcion;
	}

	public void setCDescripcion(String cDescripcion) {
		this.cDescripcion = cDescripcion;
	}

}
