package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Catálogo de Familia
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801021CFG.png'>
 */
public class pg070801021CFG extends CFGCatBase2 {
	public pg070801021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801020.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDALMFamilia dALMFamilia = new TDALMFamilia();
		try {
			cClave = (String) dALMFamilia.insert(null, this.getInputs());
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
		TDALMFamilia dALMFamilia = new TDALMFamilia();
		try {
			cClave = (String) dALMFamilia.update(null, this.getInputs());
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
		TDALMFamilia dALMFamilia = new TDALMFamilia();
		try {
			cClave = (String) dALMFamilia.disable(null, this.getInputs());
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
		TDALMFamilia dALMFamilia = new TDALMFamilia();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where F.iCveTpoArticulo = "
						+ request.getParameter("hdCampoClave1");
			else if (request.getParameter("iCveTpoArticulo") != null
					&& request.getParameter("iCveTpoArticulo").compareTo("") != 0)
				cWhere += " where F.iCveTpoArticulo = "
						+ request.getParameter("iCveTpoArticulo");
			else if (request.getParameter("hdTpoArticulo") != null
					&& request.getParameter("hdTpoArticulo").compareTo("") != 0)
				cWhere += " where F.iCveTpoArticulo = "
						+ request.getParameter("hdTpoArticulo");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			if ((request.getParameter("iCveTpoArticulo") != null
					&& request.getParameter("iCveTpoArticulo").compareTo("") != 0
					&& request.getParameter("iCveTpoArticulo")
							.compareTo("null") != 0 && Integer.parseInt(request
					.getParameter("iCveTpoArticulo")) > 0)
					|| (request.getParameter("hdTpoArticulo") != null
							&& request.getParameter("hdTpoArticulo").compareTo(
									"") != 0
							&& request.getParameter("hdTpoArticulo").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdTpoArticulo")) > 0)
					|| (request.getParameter("hdCampoClave1") != null
							&& request.getParameter("hdCampoClave1").compareTo(
									"") != 0
							&& request.getParameter("hdCampoClave").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdCampoClave1")) > 0))
				vDespliega = dALMFamilia.FindByAll(cWhere, cOrderBy);
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
		if (request.getParameter("hdBoton").compareTo("Guardar") == 0) {
			mPk.add(request.getParameter("hdTpoArticulo"));
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
		TVALMFamilia vTDALMFamilia = new TVALMFamilia();
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0)
				cCampo = "" + request.getParameter("hdTpoArticulo");
			else
				cCampo = "" + request.getParameter("iCveTpoArticulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTDALMFamilia.setiCveTpoArticulo(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTDALMFamilia.setiCveFamilia(iCampo);

			cCampo = "" + request.getParameter("cDscFamilia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTDALMFamilia.setcDscFamilia(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTDALMFamilia.setcDscBreve(cCampo);

			if (request.getParameter("lActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTDALMFamilia.setlActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTDALMFamilia;
	}
}