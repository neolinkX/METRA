package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Calibraci�n de Equipo - Listado de Cat�logo
 * de Tipo de Mantenimiento
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070601061CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070601061CFG.png'>
 */
public class pg070601061CFG extends CFGCatBase2 {
	public pg070601061CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070601060.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDEQMTpoMantto dEQMTpoMantto = new TDEQMTpoMantto();
		try {
			cClave = (String) dEQMTpoMantto.insert(null, this.getInputs());
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
		TDEQMTpoMantto dEQMTpoMantto = new TDEQMTpoMantto();
		try {
			cClave = (String) dEQMTpoMantto.update(null, this.getInputs());
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
		TDEQMTpoMantto dEQMTpoMantto = new TDEQMTpoMantto();
		try {
			cClave = (String) dEQMTpoMantto.disable(null, this.getInputs());
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
		TDEQMTpoMantto dEQMTpoMantto = new TDEQMTpoMantto();
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
			vDespliega = dEQMTpoMantto.FindByAll(cWhere, cOrderBy);
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
		TVEQMTpoMantto vEQMTpoMantto = new TVEQMTpoMantto();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMTpoMantto.setICveTpoMantto(iCampo);

			cCampo = "" + request.getParameter("cDscTpoMantto");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMTpoMantto.setCDscTpoMantto(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMTpoMantto.setCDscBreve(cCampo);

			if (request.getParameter("chklActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMTpoMantto.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEQMTpoMantto;
	}
}