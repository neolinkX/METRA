package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG de la pagina pg070101031CFG
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101031CFG.png'>
 */
public class pg070101031CFG extends CFGCatBase2 {
	public pg070101031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070101030.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMEDRecomendacion dMEDRecomendacion = new TDMEDRecomendacion();
		try {
			cClave = (String) dMEDRecomendacion.insert(null, this.getInputs());
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
		TDMEDRecomendacion dMEDRecomendacion = new TDMEDRecomendacion();
		try {
			cClave = (String) dMEDRecomendacion.update(null, this.getInputs());
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
		TDMEDRecomendacion dMEDRecomendacion = new TDMEDRecomendacion();
		try {
			cClave = (String) dMEDRecomendacion.disable(null, this.getInputs());
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
		TDMEDRecomendacion dMEDRecomendacion = new TDMEDRecomendacion();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null) {
				cActual = request.getParameter("hdCampoClave");
				cAccion = "ReposPK";
			}

			if (request.getParameter("iCveEspecialidad") != null
					&& request.getParameter("iCveEspecialidad").compareTo("") != 0)
				cWhere += " where MEDRecomendacion.iCveEspecialidad = "
						+ request.getParameter("iCveEspecialidad");
			else if (request.getParameter("hdEspecialidad") != null
					&& request.getParameter("hdEspecialidad").compareTo("") != 0)
				cWhere += " where MEDRecomendacion.iCveEspecialidad = "
						+ request.getParameter("hdEspecialidad");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by cIdentificador";

			if (request.getParameter("iCveEspecialidad") != null
					|| request.getParameter("hdEspecialidad") != null)
				vDespliega = dMEDRecomendacion.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
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
				|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
				|| request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
			mPk.add(request.getParameter("hdEspecialidad"));
			mPk.add(cActual);
		}
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo = "";
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVMEDRecomendacion vMEDRecomendacion = new TVMEDRecomendacion();
		try {

			if (request.getParameter("hdBoton").compareTo("Borrar") == 0)
				cCampo = "" + request.getParameter("iCveEspecialidad");
			else
				cCampo = "" + request.getParameter("hdEspecialidad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRecomendacion.setICveEspecialidad(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRecomendacion.setICveRecomendacion(iCampo);

			cCampo = "" + request.getParameter("cDscRecomendacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDRecomendacion.setCDscRecomendacion(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDRecomendacion.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("cIdentificador");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDRecomendacion.setCIdentificador(cCampo);

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
			vMEDRecomendacion.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDRecomendacion;
	}
}