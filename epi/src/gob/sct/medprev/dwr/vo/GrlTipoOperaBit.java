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
public class GrlTipoOperaBit extends PagedRequest implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private int iOperacion;
	private String cDescripcion;

	public String getcDescripcion() {
		return cDescripcion;
	}

	public void setcDescripcion(String cDescripcion) {
		this.cDescripcion = cDescripcion;
	}

	public int getiOperacion() {
		return iOperacion;
	}

	public void setiOperacion(int iOperacion) {
		this.iOperacion = iOperacion;
	}
}
