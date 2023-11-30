/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * 
 * @author AG SA 26 de enero 2012
 */
public class TVEXPSERVPRef implements HashBeanInterface {
	private BeanPK pk;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int width;
	private int height;
	private int iCveExpediente;
	private int iNumExamen;
	private java.sql.Timestamp tsCaptura;
	private int iOrden;
	private int iCveDocumento;
	private int iCveServicio;
	private int iCveRama;
	private String cDescripcion;

	// Adicionales
	private String cDscServicio;
	private String cDscRama;
	private String cDscheight;
	private String cPregunta;
	private String cDscBreve;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + x1);
		return pk;
	}

	public TVEXPSERVPRef() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("x1", "" + x1);
		hmfieldsTable.put("y1", "" + y1);
		hmfieldsTable.put("x2", "" + x2);
		hmfieldsTable.put("y2", "" + y2);
		hmfieldsTable.put("width", "" + width);
		hmfieldsTable.put("height", "" + height);
		hmfieldsTable.put("iCveExpediente", iCveExpediente);
		hmfieldsTable.put("iNumExamen", iNumExamen);
		hmfieldsTable.put("tsCaptura", "" + tsCaptura);
		hmfieldsTable.put("iOrden", iOrden);
		hmfieldsTable.put("iCveDocumento", iCveDocumento);
		hmfieldsTable.put("iCveServicio", iCveServicio);
		hmfieldsTable.put("iCveRama", iCveRama);
		hmfieldsTable.put("cDescripcion", cDescripcion);
		hmfieldsTable.put("cDscServicio", cDscServicio);
		hmfieldsTable.put("cDscRama", cDscRama);
		hmfieldsTable.put("cDscheight", cDscheight);
		hmfieldsTable.put("cPregunta", cPregunta);
		hmfieldsTable.put("cDscBreve", cDscBreve);

		return hmfieldsTable;
	}

	public int getx1() {
		return x1;
	}

	public void setx1(int x1) {
		this.x1 = x1;
	}

	public int gety1() {
		return y1;
	}

	public void sety1(int y1) {
		this.y1 = y1;
	}

	public int getx2() {
		return x2;
	}

	public void setx2(int x2) {
		this.x2 = x2;
	}

	public int gety2() {
		return y2;
	}

	public void sety2(int y2) {
		this.y2 = y2;
	}

	public int getwidth() {
		return width;
	}

	public void setwidth(int width) {
		this.width = width;
	}

	public int getheight() {
		return height;
	}

	public void setheight(int height) {
		this.height = height;
	}

	public int getiCveExpediente() {
		return iCveExpediente;
	}

	public void setiCveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public int getiNumExamen() {
		return iNumExamen;
	}

	public void setiNumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public java.sql.Timestamp gettsCaptura() {
		return tsCaptura;
	}

	public void settsCaptura(java.sql.Timestamp tsCaptura) {
		this.tsCaptura = tsCaptura;
	}

	public int getiOrden() {
		return iOrden;
	}

	public void setiOrden(int iOrden) {
		this.iOrden = iOrden;
	}

	public int getiCveDocumento() {
		return iCveDocumento;
	}

	public void setiCveDocumento(int iCveDocumento) {
		this.iCveDocumento = iCveDocumento;
	}

	public int getiCveServicio() {
		return iCveServicio;
	}

	public void setiCveServicio(int iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public int getiCveRama() {
		return iCveRama;
	}

	public void setiCveRama(int iCveRama) {
		this.iCveRama = iCveRama;
	}

	public String getcDescripcion() {
		return cDescripcion;
	}

	public void setcDescripcion(String cDescripcion) {
		this.cDescripcion = cDescripcion;
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

	public String getCDscheight() {
		return cDscheight;
	}

	public void setCDscheight(String cDscheight) {
		this.cDscheight = cDscheight;
	}

	public String getCPregunta() {
		return cPregunta;
	}

	public void setCPregunta(String cPregunta) {
		this.cPregunta = cPregunta;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

}
