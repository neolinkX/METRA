package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Control de Vehículos - Catálogo de Etapas por
 * Proceso
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071005031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071005031CFG.png'>
 */
public class pg071005031CFG extends CFGCatBase2 {
	public pg071005031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071005030.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDGRLEtapa dGRLEtapa = new TDGRLEtapa();
		try {
			cClave = (String) dGRLEtapa.insert(null, this.getInputs());
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
		TDGRLEtapa dGRLEtapa = new TDGRLEtapa();
		try {
			cClave = (String) dGRLEtapa.update(null, this.getInputs());
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
		TDGRLEtapa dGRLEtapa = new TDGRLEtapa();
		try {
			cClave = (String) dGRLEtapa.disable(null, this.getInputs());
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
		TDGRLEtapa dGRLEtapa = new TDGRLEtapa();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where iCveProceso = "
						+ request.getParameter("hdCampoClave1");
			else if (request.getParameter("iCveProceso") != null
					&& request.getParameter("iCveProceso").compareTo("") != 0)
				cWhere += " where iCveProceso = "
						+ request.getParameter("iCveProceso");
			else if (request.getParameter("hdProceso") != null
					&& request.getParameter("hdProceso").compareTo("") != 0)
				cWhere += " where iCveProceso = "
						+ request.getParameter("hdProceso");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by iCveProceso";

			if ((request.getParameter("iCveProceso") != null
					&& request.getParameter("iCveProceso").compareTo("") != 0
					&& request.getParameter("iCveProceso").compareTo("null") != 0 && Integer
					.parseInt(request.getParameter("iCveProceso")) > 0)
					|| (request.getParameter("hdProceso") != null
							&& request.getParameter("hdProceso").compareTo("") != 0
							&& request.getParameter("hdProceso").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdProceso")) > 0)
					|| (request.getParameter("hdCampoClave1") != null
							&& request.getParameter("hdCampoClave1").compareTo(
									"") != 0
							&& request.getParameter("hdCampoClave").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdCampoClave1")) > 0))
				vDespliega = dGRLEtapa.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método fillPK
	 */
	public void fillPK() {
		if (request.getParameter("hdCampoClave1") != null
				&& request.getParameter("hdCampoClave2") != null
				&& request.getParameter("hdCampoClave1").compareTo("") != 0
				&& request.getParameter("hdCampoClave2").compareTo("") != 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
		}
		if (request.getParameter("hdBoton").compareTo("Guardar") == 0
				|| request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
			mPk.add(request.getParameter("hdProceso"));
			mPk.add(cActual);
		}
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
		TVGRLEtapa vGRLEtapa = new TVGRLEtapa();
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0)
				cCampo = "" + request.getParameter("hdProceso");
			else
				cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLEtapa.setiCveProceso(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLEtapa.setiCveEtapa(iCampo);

			cCampo = "" + request.getParameter("cDscEtapa");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEtapa.setcDscEtapa(cCampo);

			cCampo = "" + request.getParameter("cDocumento");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEtapa.setcDocumento(cCampo);

			if (request.getParameter("lActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLEtapa.setlActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLEtapa;
	}
}