package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Cat�logo de MotivoNoAps
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070106061CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070106061CFG.png'>
 */
public class pg070106061CFG extends CFGCatBase2 {
	public pg070106061CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070106060.jsp|pg071003033.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDPERMotivoNoAp dPERMotivoNoAp = new TDPERMotivoNoAp();
		try {
			cClave = (String) dPERMotivoNoAp.insert(null, this.getInputs());
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
		TDPERMotivoNoAp dPERMotivoNoAp = new TDPERMotivoNoAp();
		try {
			cClave = (String) dPERMotivoNoAp.update(null, this.getInputs());
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
		TDPERMotivoNoAp dPERMotivoNoAp = new TDPERMotivoNoAp();
		try {
			// cClave = (String) dPERMotivoNoAp.disable(null, this.getInputs());
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
		TDPERMotivoNoAp dPERMotivoNoAp = new TDPERMotivoNoAp();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			if (cCondicion.compareTo("") != 0)
				cWhere += " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;

			vDespliega = dPERMotivoNoAp.FindByAll(cWhere);
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
		TVPERMotivoNoAp vPERMotivoNoAp = new TVPERMotivoNoAp();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERMotivoNoAp.setICveMotivoNoAp(iCampo);

			cCampo = "" + request.getParameter("cDscMotivoNoAp");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERMotivoNoAp.setCDscMotivoNoAp(cCampo);

			cCampo = "" + request.getParameter("iPeriodo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERMotivoNoAp.setIPeriodo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vPERMotivoNoAp;
	}
}