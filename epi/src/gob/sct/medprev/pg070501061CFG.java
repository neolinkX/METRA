package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Cat�logo de Tipo de Plantilla
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070501061CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070501061CFG.png'>
 */
public class pg070501061CFG extends CFGCatBase2 {
	public pg070501061CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070501060.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDCTRTpoPlantilla dCTRTpoPlantilla = new TDCTRTpoPlantilla();
		try {
			cClave = (String) dCTRTpoPlantilla.insert(null, this.getInputs());
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
		TDCTRTpoPlantilla dCTRTpoPlantilla = new TDCTRTpoPlantilla();
		try {
			cClave = (String) dCTRTpoPlantilla.update(null, this.getInputs());
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
		TDCTRTpoPlantilla dCTRTpoPlantilla = new TDCTRTpoPlantilla();
		try {
			cClave = (String) dCTRTpoPlantilla.disable(null, this.getInputs());
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
		TDCTRTpoPlantilla dCTRTpoPlantilla = new TDCTRTpoPlantilla();
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
			} else
				cOrderBy = "order by iCveTpoPlantilla";
			vDespliega = dCTRTpoPlantilla.FindByAll(cWhere, cOrderBy);
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
		TVCTRTpoPlantilla vCTRTpoPlantilla = new TVCTRTpoPlantilla();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRTpoPlantilla.setICveTpoPlantilla(iCampo);

			cCampo = "" + request.getParameter("cDscTpoPlantilla");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRTpoPlantilla.setCDscTpoPlantilla(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRTpoPlantilla.setCDscBreve(cCampo);

			if (request.getParameter("chklActivo") == null) {
				cCampo = "0";
			} else {
				cCampo = "1";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRTpoPlantilla.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vCTRTpoPlantilla;
	}
}