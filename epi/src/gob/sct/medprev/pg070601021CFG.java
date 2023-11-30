package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Calibraci�n de Equipo - Cat�logo de Tipos
 * de Equipo
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070601021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070601021CFG.png'>
 */
public class pg070601021CFG extends CFGCatBase2 {
	public pg070601021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070601020.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDEQMTpoEquipo dEQMTpoEquipo = new TDEQMTpoEquipo();
		try {
			cClave = (String) dEQMTpoEquipo.insert(null, this.getInputs());
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
		TDEQMTpoEquipo dEQMTpoEquipo = new TDEQMTpoEquipo();
		try {
			cClave = (String) dEQMTpoEquipo.update(null, this.getInputs());
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
		TDEQMTpoEquipo dEQMTpoEquipo = new TDEQMTpoEquipo();
		try {
			cClave = (String) dEQMTpoEquipo.disable(null, this.getInputs());
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
		TDEQMTpoEquipo dEQMTpoEquipo = new TDEQMTpoEquipo();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where iCveClasificacion = "
						+ request.getParameter("hdCampoClave1");
			else if (request.getParameter("iCveClasificacion") != null
					&& request.getParameter("iCveClasificacion").compareTo("") != 0)
				cWhere += " where iCveClasificacion = "
						+ request.getParameter("iCveClasificacion");
			else if (request.getParameter("hdClasificacion") != null
					&& request.getParameter("hdClasificacion").compareTo("") != 0)
				cWhere += " where iCveClasificacion = "
						+ request.getParameter("hdClasificacion");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}

			if (request.getParameter("iCveClasificacion") != null
					|| request.getParameter("hdClasificacion") != null
					|| (request.getParameter("hdCampoClave1") != null && request
							.getParameter("hdCampoClave1").compareTo("") != 0))
				vDespliega = dEQMTpoEquipo.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		if (request.getParameter("hdCampoClave1") != null
				&& request.getParameter("hdCampoClave1").compareTo("") != 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
		} else {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0)
				mPk.add(request.getParameter("hdClasificacion"));
			else
				mPk.add(request.getParameter("iCveClasificacion"));
			mPk.add(cActual);
		}
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
		TVEQMTpoEquipo vEQMTpoEquipo = new TVEQMTpoEquipo();
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0)
				cCampo = "" + request.getParameter("hdClasificacion");
			else
				cCampo = "" + request.getParameter("iCveClasificacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMTpoEquipo.setICveClasificacion(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMTpoEquipo.setICveTpoEquipo(iCampo);

			cCampo = "" + request.getParameter("cDscTpoEquipo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMTpoEquipo.setCDscTpoEquipo(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMTpoEquipo.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("cCABMS");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMTpoEquipo.setCCABMS(cCampo);

			if (request.getParameter("chklActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMTpoEquipo.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEQMTpoEquipo;
	}
}