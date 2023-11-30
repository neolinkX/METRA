package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Generales - Categorias
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071004021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071004021CFG.png'>
 */
public class pg071004021CFG extends CFGCatBase2 {
	public pg071004021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071004020.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDGRLCategoria dGRLCategoria = new TDGRLCategoria();
		try {
			cClave = (String) dGRLCategoria.insert(null, this.getInputs());
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
		TDGRLCategoria dGRLCategoria = new TDGRLCategoria();
		try {
			cClave = (String) dGRLCategoria.update(null, this.getInputs());
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
		TDGRLCategoria dGRLCategoria = new TDGRLCategoria();
		try {
			cClave = (String) dGRLCategoria.disable(null, this.getInputs());
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
		TDGRLCategoria dGRLCategoria = new TDGRLCategoria();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where iCveMdoTrans = "
						+ request.getParameter("hdCampoClave1");
			else if (request.getParameter("iCveMdoTrans") != null
					&& request.getParameter("iCveMdoTrans").compareTo("") != 0)
				cWhere += " where iCveMdoTrans = "
						+ request.getParameter("iCveMdoTrans");
			else if (request.getParameter("hdMdoTrans") != null
					&& request.getParameter("hdMdoTrans").compareTo("") != 0)
				cWhere += " where iCveMdoTrans = "
						+ request.getParameter("hdMdoTrans");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy += cOrden;
			}
			if ((request.getParameter("iCveMdoTrans") != null
					&& request.getParameter("iCveMdoTrans").compareTo("") != 0
					&& request.getParameter("iCveMdoTrans").compareTo("null") != 0 && Integer
					.parseInt(request.getParameter("iCveMdoTrans")) > 0)
					|| (request.getParameter("hdMdoTrans") != null
							&& request.getParameter("hdMdoTrans").compareTo("") != 0
							&& request.getParameter("hdMdoTrans").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdMdoTrans")) > 0)
					|| (request.getParameter("hdCampoClave1") != null
							&& request.getParameter("hdCampoClave1").compareTo(
									"") != 0
							&& request.getParameter("hdCampoClave").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdCampoClave1")) > 0))
				vDespliega = dGRLCategoria.FindByAll(cWhere, cOrderBy);
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
			mPk.add(request.getParameter("hdMdoTrans"));
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
		TVGRLCategoria vGRLCategoria = new TVGRLCategoria();
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0)
				cCampo = "" + request.getParameter("hdMdoTrans");
			else
				cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLCategoria.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLCategoria.setICveCategoria(iCampo);

			cCampo = "" + request.getParameter("cDscCategoria");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLCategoria.setCDscCategoria(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLCategoria.setCDscBreve(cCampo);

			if (request.getParameter("iTmpoReexp") != null)
				cCampo = request.getParameter("iTmpoReexp");
			else
				cCampo = "0";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLCategoria.setITmpoReexp(iCampo);

			if (request.getParameter("lActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLCategoria.setLActivo(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLCategoria;
	}
}