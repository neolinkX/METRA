package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Generales - Catálogo de Huellas
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071006031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071006031CFG.png'>
 */
public class pg071006031CFG extends CFGCatBase2 {
	public pg071006031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071006030.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDGRLHuellas dGRLHuella = new TDGRLHuellas();
		try {
			cClave = (String) dGRLHuella.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Método Guardar

	/**
	 * Método GuardarA
	 */
	public void GuardarA() {
		TDGRLHuellas dGRLHuella = new TDGRLHuellas();
		try {
			cClave = (String) dGRLHuella.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Método GuardarA

	/**
	 * Método Borrar
	 */
	public void Borrar() {
		TDGRLHuellas dGRLHuella = new TDGRLHuellas();
		try {
			cClave = (String) dGRLHuella.disable(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Método Borrar

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDGRLHuellas dGRLHuella = new TDGRLHuellas();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";

			if (cCondicion.compareTo("") != 0)
				cWhere += " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else
				cWhere += " order by iCveHuella ";

			vDespliega = dGRLHuella.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método fillPK
	 */
	public void fillPK() {
		mPk.add(cActual);
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVGRLHuellas vGRLHuella = new TVGRLHuellas();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLHuella.setICveHuella(iCampo);

			cCampo = "" + request.getParameter("cDscHuella");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLHuella.setCDscHuella(cCampo);

			if (request.getParameter("lObligatoria") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLHuella.setLObligatoria(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLHuella;
	}
}