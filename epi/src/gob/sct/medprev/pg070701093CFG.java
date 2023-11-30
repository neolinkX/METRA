package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Control de Vehículos - Catálogo de Controles
 * por Etapa
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070701093CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070701093CFG.png'>
 */
public class pg070701093CFG extends CFGCatBase2 {
	public pg070701093CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070701092.jsp|pg070701090.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDVEHConfControl dVEHConfControl = new TDVEHConfControl();
		try {
			cClave = (String) dVEHConfControl.insert(null, this.getInputs());
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
		TDVEHConfControl dVEHConfControl = new TDVEHConfControl();
		try {
			cClave = (String) dVEHConfControl.update(null, this.getInputs());
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
		TDVEHConfControl dVEHConfControl = new TDVEHConfControl();
		try {
			cClave = (String) dVEHConfControl.disable(null, this.getInputs());
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
		TDVEHConfControl dVEHConfControl = new TDVEHConfControl();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where VEHConfControl.iCveEtapaSolic = "
						+ request.getParameter("hdCampoClave1");
			else if (request.getParameter("iCveEtapaSolic") != null
					&& request.getParameter("iCveEtapaSolic").compareTo("") != 0)
				cWhere += " where VEHConfControl.iCveEtapaSolic = "
						+ request.getParameter("iCveEtapaSolic");
			else if (request.getParameter("hdEtapaSolic") != null
					&& request.getParameter("hdEtapaSolic").compareTo("") != 0)
				cWhere += " where VEHConfControl.iCveEtapaSolic = "
						+ request.getParameter("hdEtapaSolic");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by iCveConfControl ";

			if ((request.getParameter("iCveEtapaSolic") != null
					&& request.getParameter("iCveEtapaSolic").compareTo("") != 0
					&& request.getParameter("iCveEtapaSolic").compareTo("null") != 0 && Integer
					.parseInt(request.getParameter("iCveEtapaSolic")) > 0)
					|| (request.getParameter("hdEtapaSolic") != null
							&& request.getParameter("hdEtapaSolic").compareTo(
									"") != 0
							&& request.getParameter("hdEtapaSolic").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdEtapaSolic")) > 0)
					|| (request.getParameter("hdCampoClave1") != null
							&& request.getParameter("hdCampoClave1").compareTo(
									"") != 0
							&& request.getParameter("hdCampoClave").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdCampoClave1")) > 0))
				vDespliega = dVEHConfControl.FindDsc(cWhere, cOrderBy);
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
			mPk.add(request.getParameter("hdEtapaSolic"));
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
		TVVEHConfControl vVEHConfControl = new TVVEHConfControl();
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0)
				cCampo = "" + request.getParameter("hdEtapaSolic");
			else
				cCampo = "" + request.getParameter("iCveEtapaSolic");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHConfControl.setICveEtapaSolic(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHConfControl.setICveConfControl(iCampo);

			cCampo = "" + request.getParameter("cDscTpoResp2");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHConfControl.setCDscTpoResp(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHConfControl.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("cEtiqueta");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHConfControl.setCEtiqueta(cCampo);

			cCampo = "" + request.getParameter("iCveTpoResp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHConfControl.setICveTpoResp(iCampo);

			if (request.getParameter("lObligatorio") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHConfControl.setLObligatorio(iCampo);

			if (request.getParameter("lActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHConfControl.setLActivo(iCampo);

			cCampo = request.getParameter("iOrden");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHConfControl.setIOrden(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vVEHConfControl;
	}
}