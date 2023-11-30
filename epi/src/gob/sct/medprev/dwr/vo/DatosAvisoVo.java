/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dwr.vo;

import java.io.Serializable;

/**
 * 
 * @author w3r0SCT
 */
public class DatosAvisoVo implements Serializable {
	/**
     * 
     */
	private static long serialVersionUID = 1L;
	private String avisoConfigBody;
	private String avisoConfigTit;
	private String mostrarAvisoConfig;
	private String avisoConfigTitLiga;
	private String avisoConfigLiga;

	public String getAvisoConfigBody() {
		return avisoConfigBody;
	}

	public void setAvisoConfigBody(String AvisoConfigBody) {
		this.avisoConfigBody = AvisoConfigBody;
	}

	public String getAvisoConfigLiga() {
		return avisoConfigLiga;
	}

	public void setAvisoConfigLiga(String AvisoConfigLiga) {
		this.avisoConfigLiga = AvisoConfigLiga;
	}

	public String getAvisoConfigTit() {
		return avisoConfigTit;
	}

	public void setAvisoConfigTit(String AvisoConfigTit) {
		this.avisoConfigTit = AvisoConfigTit;
	}

	public String getAvisoConfigTitLiga() {
		return avisoConfigTitLiga;
	}

	public void setAvisoConfigTitLiga(String AvisoConfigTitLiga) {
		this.avisoConfigTitLiga = AvisoConfigTitLiga;
	}

	public String getMostrarAvisoConfig() {
		return mostrarAvisoConfig;
	}

	public void setMostrarAvisoConfig(String MostrarAvisoConfig) {
		this.mostrarAvisoConfig = MostrarAvisoConfig;
	}
}
