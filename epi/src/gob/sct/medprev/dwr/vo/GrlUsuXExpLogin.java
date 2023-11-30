/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev.dwr.vo;

import gob.sct.medprev.dwr.framework.PagedRequest;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author admin
 */
public class GrlUsuXExpLogin extends PagedRequest implements Serializable {
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
	private int ICVEEXPEDIENTE;
	private int INODOCTO;
	private Timestamp TSCAPTURA;
	private int IDEDO;
	private int ICVEUSUARIOREGISTRO;
	private String CUSUARIO;
	private String CNOMBRE;
	private String CAPPATERNO;
	private String CAPMATERNO;
	private String CCVEUSUARIOREGISTRO;
	private int LLICENCIAS;
	private int ICVEMDOTRANS;

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
	 * @return the INODOCTO
	 */
	public int getINODOCTO() {
		return INODOCTO;
	}

	/**
	 * @param INODOCTO
	 *            the INODOCTO to set
	 */
	public void setINODOCTO(int INODOCTO) {
		this.INODOCTO = INODOCTO;
	}

	/**
	 * @return the TSCAPTURA
	 */
	public Timestamp getTSCAPTURA() {
		return TSCAPTURA;
	}

	/**
	 * @param TSCAPTURA
	 *            the TSCAPTURA to set
	 */
	public void setTSCAPTURA(Timestamp TSCAPTURA) {
		this.TSCAPTURA = TSCAPTURA;
	}

	/**
	 * @return the IDEDO
	 */
	public int getIDEDO() {
		return IDEDO;
	}

	/**
	 * @param IDEDO
	 *            the IDEDO to set
	 */
	public void setIDEDO(int IDEDO) {
		this.IDEDO = IDEDO;
	}

	/**
	 * @return the ICVEUSUARIOREGISTRO
	 */
	public int getICVEUSUARIOREGISTRO() {
		return ICVEUSUARIOREGISTRO;
	}

	/**
	 * @param ICVEUSUARIOREGISTRO
	 *            the ICVEUSUARIOREGISTRO to set
	 */
	public void setICVEUSUARIOREGISTRO(int ICVEUSUARIOREGISTRO) {
		this.ICVEUSUARIOREGISTRO = ICVEUSUARIOREGISTRO;
	}

	/**
	 * @return the CCVEUSUARIOREGISTRO
	 */
	public String getCCVEUSUARIOREGISTRO() {
		return CCVEUSUARIOREGISTRO;
	}

	/**
	 * @param CCVEUSUARIOREGISTRO
	 *            the CCVEUSUARIOREGISTRO to set
	 */
	public void setCCVEUSUARIOREGISTRO(String CCVEUSUARIOREGISTRO) {
		this.CCVEUSUARIOREGISTRO = CCVEUSUARIOREGISTRO;
	}
	
	/**
	 * @return the LLICENCIAS
	 */
	public int getLLICENCIAS() {
		return LLICENCIAS;
	}
	

	/**
	 * @param LLICENCIAS
	 *            the LLICENCIAS to set
	 */
	public void setLLICENCIAS(int LLICENCIAS) {
		this.LLICENCIAS = LLICENCIAS;
	}
	
	/**
	 * @return the ICVEMDOTRANS
	 */
	public int getICVEMDOTRANS() {
		return ICVEMDOTRANS;
	}
	

	/**
	 * @param ICVEMDOTRANS
	 *            the ICVEMDOTRANS to set
	 */
	public void setICVEMDOTRANS(int ICVEMDOTRANS) {
		this.ICVEMDOTRANS = ICVEMDOTRANS;
	}

}
