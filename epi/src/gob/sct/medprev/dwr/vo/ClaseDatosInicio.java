/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dwr.vo;

import gob.sct.medprev.dwr.framework.PagedRequest;
import java.io.Serializable;

/**
 * 
 * @author w3r0SCT
 */
public class ClaseDatosInicio extends PagedRequest implements Serializable {
	/**
     * 
     */
	private static long serialVersionUID = 1L;
	private String nombreCompleto;
	private String nombre;
	private String cappaterno;
	private String capmaterno;
	private String cedula;
	private String especialidad;
	private String rfc;
	private String domLaboral;
	private String clues;
	

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClues() {
		return clues;
	}

	public void setClues(String clues) {
		this.clues = clues;
	}

	public String getDomLaboral() {
		return domLaboral;
	}

	public void setDomLaboral(String domLaboral) {
		this.domLaboral = domLaboral;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApPaterno() {
		return cappaterno;
	}

	public void setApPaterno(String cappaterno) {
		this.cappaterno = cappaterno;
	}

	public String getApMaterno() {
		return nombreCompleto;
	}

	public void setApMaterno(String capmaterno) {
		this.capmaterno = capmaterno;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

}
