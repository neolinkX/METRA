package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para DFG para listado de la tabla TOXMotivoRecep
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301031.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301031.png'>
 */
public class pg070301031CFG extends CFGCatBase2 {
	public pg070301031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMotivoRecep dMotivoRecep = new TDMotivoRecep();
		try {
			cClave = (String) dMotivoRecep.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDMotivoRecep dMotivoRecep = new TDMotivoRecep();
		try {
			cClave = (String) dMotivoRecep.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDMotivoRecep dMotivoRecep = new TDMotivoRecep();
		try {
			cClave = (String) dMotivoRecep.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDMotivoRecep dMotivoRecep = new TDMotivoRecep();
		if (this.getLPagina("pg070301030.jsp"))
			cPaginas = "pg070301030.jsp|";

		try {
			String cFiltro = "";
			if (cCondicion.compareTo("") != 0) {
				cFiltro = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cFiltro = cFiltro + cOrden;
			}
			vDespliega = dMotivoRecep.FindByAll(cFiltro);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() {
		String cCampo;
		int iCampo;
		float fCampo;
		TVMotivoRecep vMotivoRecep = new TVMotivoRecep();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMotivoRecep.setICveMotRecep(iCampo);

			cCampo = "" + request.getParameter("iCveTipoRecep");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMotivoRecep.setICveTipoRecep(iCampo);

			cCampo = "" + request.getParameter("cDscMotRecep");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMotivoRecep.setCDscMotRecep(cCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMotivoRecep.setCDscLong(cCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
		}
		return vMotivoRecep;
	}
}