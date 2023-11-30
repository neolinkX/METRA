/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev.dwr.vo;

import gob.sct.medprev.dwr.framework.PagedRequest;
import java.io.Serializable;

/**
 * 
 * @author admin
 */
public class GrlExpedientes extends PagedRequest implements Serializable {
	/**
     * 
     */
	private static long serialVersionUID = 1L;

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @param aSerialVersionUID
	 *            the serialVersionUID to set
	 */
	public static void setSerialVersionUID(long aSerialVersionUID) {
		serialVersionUID = aSerialVersionUID;
	}

	private int ICVEPERSONAL;
	private int ICVEEXPEDIENTE;
	private String CNOMBRE;
	private String CAPPATERNO;
	private String CAPMATERNO;

	/**
	 * @return the ICVEPERSONAL
	 */
	public int getICVEPERSONAL() {
		return ICVEPERSONAL;
	}

	/**
	 * @param ICVEPERSONAL
	 *            the ICVEPERSONAL to set
	 */
	public void setICVEPERSONAL(int ICVEPERSONAL) {
		this.ICVEPERSONAL = ICVEPERSONAL;
	}

	/**
	 * @return the ICVEEXPEDIENTE
	 */
	public int getICVEEXPEDIENTE() {
		return ICVEEXPEDIENTE;
	}

	/**
	 * @param ICVEEXPEDIENTE
	 *            the ICVEEXPEDIENTE to set
	 */
	public void setICVEEXPEDIENTE(int ICVEEXPEDIENTE) {
		this.ICVEEXPEDIENTE = ICVEEXPEDIENTE;
	}

	/**
	 * @return the CNOMBRE
	 */
	public String getCNOMBRE() {
		return CNOMBRE;
	}

	/**
	 * @param CNOMBRE
	 *            the CNOMBRE to set
	 */
	public void setCNOMBRE(String CNOMBRE) {
		this.CNOMBRE = CNOMBRE;
	}

	/**
	 * @return the CAPPATERNO
	 */
	public String getCAPPATERNO() {
		return CAPPATERNO;
	}

	/**
	 * @param CAPPATERNO
	 *            the CAPPATERNO to set
	 */
	public void setCAPPATERNO(String CAPPATERNO) {
		this.CAPPATERNO = CAPPATERNO;
	}

	/**
	 * @return the CAPMATERNO
	 */
	public String getCAPMATERNO() {
		return CAPMATERNO;
	}

	/**
	 * @param CAPMATERNO
	 *            the CAPMATERNO to set
	 */
	public void setCAPMATERNO(String CAPMATERNO) {
		this.CAPMATERNO = CAPMATERNO;
	}

}
