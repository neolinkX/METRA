package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para CFG de la pagina pg070101021
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101021CFG.png'>
 */
public class pg070101021CFG extends CFGCatBase2 {
	public pg070101021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070101020.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDMEDDiagnostico dMEDDiagnostico = new TDMEDDiagnostico();
		try {
			cClave = (String) dMEDDiagnostico.insert(null, this.getInputs());
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
		TDMEDDiagnostico dMEDDiagnostico = new TDMEDDiagnostico();
		try {
			cClave = (String) dMEDDiagnostico.update(null, this.getInputs());
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
		TDMEDDiagnostico dMEDDiagnostico = new TDMEDDiagnostico();
		try {
			cClave = (String) dMEDDiagnostico.disable(null, this.getInputs());
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
		TDMEDDiagnostico dMEDDiagnostico = new TDMEDDiagnostico();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			if (request.getParameter("iCveEspecialidad") != null
					&& request.getParameter("iCveEspecialidad").compareTo("") != 0)
				cWhere += " where MEDDiagnostico.iCveEspecialidad = "
						+ request.getParameter("iCveEspecialidad");
			else if (request.getParameter("hdEspecialidad") != null
					&& request.getParameter("hdEspecialidad").compareTo("") != 0)
				cWhere += " where MEDDiagnostico.iCveEspecialidad = "
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
				cOrderBy = " order by cCIE";

			if (request.getParameter("iCveEspecialidad") != null
					|| request.getParameter("hdEspecialidad") != null)
				vDespliega = dMEDDiagnostico.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método FillPK
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
				|| request.getParameter("hdBoton").compareTo("GuardarA") == 0) {
			mPk.add(request.getParameter("hdEspecialidad"));
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
		TVMEDDiagnostico vMEDDiagnostico = new TVMEDDiagnostico();
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
			vMEDDiagnostico.setICveEspecialidad(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDDiagnostico.setICveDiagnostico(iCampo);

			cCampo = "" + request.getParameter("cDscDiagnostico");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDDiagnostico.setCDscDiagnostico(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDDiagnostico.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("cCIE");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDDiagnostico.setCCIE(cCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDDiagnostico.setCObservacion(cCampo);

			if (request.getParameter("chklFrecuente") == null) {
				cCampo = "0";
			} else {
				cCampo = "1";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDDiagnostico.setLFrecuente(iCampo);

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
			vMEDDiagnostico.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDDiagnostico;
	}
}