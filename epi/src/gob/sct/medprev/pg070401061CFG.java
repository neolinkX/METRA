package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Cat�logo de TpoCausas
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003021CFG.png'>
 */
public class pg070401061CFG extends CFGCatBase2 {
	public pg070401061CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070401060.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDINVTpoCausa dINVTpoCausa = new TDINVTpoCausa();
		try {
			cClave = (String) dINVTpoCausa.insert(null,
					(TVINVTpoCausa) this.getInputs());
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
		TDINVTpoCausa dINVTpoCausa = new TDINVTpoCausa();
		try {
			cClave = (String) dINVTpoCausa.update(null,
					(TVINVTpoCausa) this.getInputs());
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
		TDINVTpoCausa dINVTpoCausa = new TDINVTpoCausa();
		try {
			cClave = (String) dINVTpoCausa.disable(null,
					(TVINVTpoCausa) this.getInputs());
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
		TDINVTpoCausa dINVTpoCausa = new TDINVTpoCausa();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";
			if (cCondicion.compareTo("") != 0) {
				cWhere = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			vDespliega = dINVTpoCausa.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVINVTpoCausa vINVTpoCausa = new TVINVTpoCausa();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVTpoCausa.setICveTpoCausa(iCampo);

			cCampo = "" + request.getParameter("cDscTpoCausa");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vINVTpoCausa.setCDscTpoCausa(cCampo);

			if (request.getParameter("lActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVTpoCausa.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vINVTpoCausa;
	}
}