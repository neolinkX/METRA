/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev.dwr.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author admin
 */
public class ExpBitMod implements Serializable {
	/**
     * 
     */
	private static long serialVersionUID = 1L;
	private String iCveExpediente;
	private String iNumExamen;
	private String operacion;
	private String dtRealizado;
	private String descripcion;
	private String iCveUsuarioRealiza;
	private String iCveServicio;
	private String iCveRama;
	private String iCveSintoma;
	private String lDictamen;
	private String ipAddress;
	private String macAddress;
	private String computerName;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDtRealizado() {
		return dtRealizado;
	}

	public void setDtRealizado(String dtRealizado) {
		this.dtRealizado = dtRealizado;
	}

	public String getiCveExpediente() {
		return iCveExpediente;
	}

	public void setiCveExpediente(String iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public String getiCveRama() {
		return iCveRama;
	}

	public void setiCveRama(String iCveRama) {
		this.iCveRama = iCveRama;
	}

	public String getiCveServicio() {
		return iCveServicio;
	}

	public void setiCveServicio(String iCveServicio) {
		this.iCveServicio = iCveServicio;
	}

	public String getiCveSintoma() {
		return iCveSintoma;
	}

	public void setiCveSintoma(String iCveSintoma) {
		this.iCveSintoma = iCveSintoma;
	}

	public String getiCveUsuarioRealiza() {
		return iCveUsuarioRealiza;
	}

	public void setiCveUsuarioRealiza(String iCveUsuarioRealiza) {
		this.iCveUsuarioRealiza = iCveUsuarioRealiza;
	}

	public String getiNumExamen() {
		return iNumExamen;
	}

	public void setiNumExamen(String iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public String getlDictamen() {
		return lDictamen;
	}

	public void setlDictamen(String lDictamen) {
		this.lDictamen = lDictamen;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * @param macAddress
	 *            the macAddress to set
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * @return the computerName
	 */
	public String getComputerName() {
		return computerName;
	}

	/**
	 * @param computerName
	 *            the computerName to set
	 */
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

}
