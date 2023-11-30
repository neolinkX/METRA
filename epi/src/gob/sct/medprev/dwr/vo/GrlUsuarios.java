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
public class GrlUsuarios extends PagedRequest implements Serializable {
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

	private int ICVEUSUARIO;
	private String CUSUARIO;
	private String CNOMBRE;
	private String CAPPATERNO;
	private String CAPMATERNO;

	/**
	 * @return the ICVEUSUARIO
	 */
	public int getICVEUSUARIO() {
		return ICVEUSUARIO;
	}

	/**
	 * @param ICVEUSUARIO
	 *            the ICVEUSUARIO to set
	 */
	public void setICVEUSUARIO(int ICVEUSUARIO) {
		this.ICVEUSUARIO = ICVEUSUARIO;
	}

	/**
	 * @return the CUSUARIO
	 */
	public String getCUSUARIO() {
		return CUSUARIO;
	}

	/**
	 * @param CUSUARIO
	 *            the CUSUARIO to set
	 */
	public void setCUSUARIO(String CUSUARIO) {
		this.CUSUARIO = CUSUARIO;
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
