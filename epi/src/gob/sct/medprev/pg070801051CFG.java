package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG pagina pg070801051
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801051CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801051CFG.png'>
 */
public class pg070801051CFG extends CFGCatBase2 {
	public pg070801051CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801050.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDALMSeccion dALMSeccion = new TDALMSeccion();
		try {
			cClave = (String) dALMSeccion.insert(null, this.getInputs());
		} catch (Exception ex) {
			ex.printStackTrace();
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
		TDALMSeccion dALMSeccion = new TDALMSeccion();
		try {
			cClave = (String) dALMSeccion.update(null, this.getInputs());
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
		TDALMSeccion dALMSeccion = new TDALMSeccion();
		try {
			cClave = (String) dALMSeccion.disable(null, this.getInputs());
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
		TDALMSeccion dALMSeccion = new TDALMSeccion();
		try {
			String cWhere = "";
			String cOrderBy = "";
			if (request.getParameter("iCveAlmacen") != null
					&& request.getParameter("iCveArea") != null) {
				if (request.getParameter("iCveAlmacen").compareTo("") != 0)
					cWhere += " where ALMSeccion.iCveAlmacen = "
							+ request.getParameter("iCveAlmacen");
				if (request.getParameter("iCveArea").compareTo("") != 0) {
					if (cWhere.compareTo("") != 0)
						cWhere += " and ALMSeccion.iCveArea = "
								+ request.getParameter("iCveArea");
					else
						cWhere += " where ALMSeccion.iCveArea = "
								+ request.getParameter("iCveArea");
				}
				if (cCondicion.compareTo("") != 0) {
					if (cWhere.compareTo("") != 0)
						cWhere += " and " + cCondicion;
					else
						cWhere += cCondicion;
				}
			} else if (request.getParameter("hdAlmacen") != null
					&& request.getParameter("hdArea") != null
					&& request.getParameter("hdAlmacen").compareTo("null") != 0
					&& request.getParameter("hdArea").compareTo("null") != 0
					&& Integer.parseInt(request.getParameter("hdAlmacen")) > 0
					&& Integer.parseInt(request.getParameter("hdArea")) > 0) {
				if (request.getParameter("hdAlmacen").compareTo("") != 0)
					cWhere += " where ALMSeccion.iCveAlmacen = "
							+ request.getParameter("hdAlmacen");
				if (request.getParameter("hdArea").compareTo("") != 0) {
					if (cWhere.compareTo("") != 0)
						cWhere += " and ALMSeccion.iCveArea = "
								+ request.getParameter("hdArea");
					else
						cWhere += " where ALMSeccion.iCveArea = "
								+ request.getParameter("hdArea");
				}
				if (cCondicion.compareTo("") != 0) {
					if (cWhere.compareTo("") != 0)
						cWhere += " and " + cCondicion;
					else
						cWhere += cCondicion;
				}
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			if (request.getParameter("iCveArea") != null
					|| request.getParameter("hdArea") != null)
				vDespliega = dALMSeccion.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void fillPK() {
		if (request.getParameter("hdCampoClave1") != null
				&& request.getParameter("hdCampoClave2") != null
				&& request.getParameter("hdCampoClave3") != null
				&& request.getParameter("hdCampoClave1").compareTo("") != 0
				&& request.getParameter("hdCampoClave2").compareTo("") != 0
				&& request.getParameter("hdCampoClave3").compareTo("") != 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
			mPk.add(request.getParameter("hdCampoClave3"));
		}
		if (request.getParameter("hdBoton").compareTo("Guardar") == 0
				|| request.getParameter("hdBoton").compareTo("GuardarA") == 0) {
			mPk.add(request.getParameter("hdAlmacen"));
			mPk.add(request.getParameter("hdArea"));
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
		TVALMSeccion vALMSeccion = new TVALMSeccion();
		try {

			if (request.getParameter("hdBoton").compareTo("Borrar") == 0)
				cCampo = "" + request.getParameter("iCveAlmacen");
			else
				cCampo = "" + request.getParameter("hdAlmacen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSeccion.setICveAlmacen(iCampo);

			if (request.getParameter("hdBoton").compareTo("Borrar") == 0)
				cCampo = "" + request.getParameter("iCveArea");
			else
				cCampo = "" + request.getParameter("hdArea");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSeccion.setICveArea(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSeccion.setICveSeccion(iCampo);

			cCampo = "" + request.getParameter("cDscSeccion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMSeccion.setCDscSeccion(cCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMSeccion.setCObservacion(cCampo);

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
			vALMSeccion.setLActivo(iCampo);

		} catch (Exception ex) {
			ex.printStackTrace();
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vALMSeccion;
	}
}