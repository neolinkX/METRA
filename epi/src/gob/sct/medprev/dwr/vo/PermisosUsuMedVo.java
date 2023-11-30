package gob.sct.medprev.dwr.vo;

import java.io.Serializable;

public class PermisosUsuMedVo implements Serializable {

	/**
     * 
     */
	private static long serialVersionUID = 1L;
	private int iCveUsuario;
	private int iCveUniMed;
	private int iCveProceso;
	private int iCveModulo;
	private int iCveServicio;
	private int iCveRama;

	public int getiCveUsuario() {
		return iCveUsuario;
	}

	public void setiCveUsuario(int iCveUsuario) {
		this.iCveUsuario = iCveUsuario;
	}

	public int getiCveUniMed() {
		return iCveUniMed;
	}

	public void setiCveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public int getiCveProceso() {
		return iCveProceso;
	}

	public void setiCveProceso(int iCveProceso) {
		this.iCveProceso = iCveProceso;
	}

	public int getiCveModulo() {
		return iCveModulo;
	}

	public void setiCveModulo(int iCveModulo) {
		this.iCveModulo = iCveModulo;
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
}
