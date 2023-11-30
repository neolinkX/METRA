package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Control de Veh�culos - Cat�logo de Modelos
 * por Marca
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070701031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070701031CFG.png'>
 */
public class pg070701031CFG extends CFGCatBase2 {
	public pg070701031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070701030.jsp|pg070701020.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDVEHModelo dVEHModelo = new TDVEHModelo();
		try {
			cClave = (String) dVEHModelo.insert(null, this.getInputs());
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
		TDVEHModelo dVEHModelo = new TDVEHModelo();
		try {
			cClave = (String) dVEHModelo.update(null, this.getInputs());
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
		TDVEHModelo dVEHModelo = new TDVEHModelo();
		try {
			cClave = (String) dVEHModelo.disable(null, this.getInputs());
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
		TDVEHModelo dVEHModelo = new TDVEHModelo();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where iCveMarca = "
						+ request.getParameter("hdCampoClave1");
			else if (request.getParameter("iCveMarca") != null
					&& request.getParameter("iCveMarca").compareTo("") != 0)
				cWhere += " where iCveMarca = "
						+ request.getParameter("iCveMarca");
			else if (request.getParameter("hdMarca") != null
					&& request.getParameter("hdMarca").compareTo("") != 0)
				cWhere += " where iCveMarca = "
						+ request.getParameter("hdMarca");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			if ((request.getParameter("iCveMarca") != null
					&& request.getParameter("iCveMarca").compareTo("") != 0
					&& request.getParameter("iCveMarca").compareTo("null") != 0 && Integer
					.parseInt(request.getParameter("iCveMarca")) > 0)
					|| (request.getParameter("hdMarca") != null
							&& request.getParameter("hdMarca").compareTo("") != 0
							&& request.getParameter("hdMarca")
									.compareTo("null") != 0 && Integer
							.parseInt(request.getParameter("hdMarca")) > 0)
					|| (request.getParameter("hdCampoClave1") != null
							&& request.getParameter("hdCampoClave1").compareTo(
									"") != 0
							&& request.getParameter("hdCampoClave").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdCampoClave1")) > 0))
				vDespliega = dVEHModelo.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
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
			mPk.add(request.getParameter("hdMarca"));
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
		TVVEHModelo vVEHModelo = new TVVEHModelo();
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0)
				cCampo = "" + request.getParameter("hdMarca");
			else
				cCampo = "" + request.getParameter("iCveMarca");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHModelo.setICveMarca(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHModelo.setICveModelo(iCampo);

			cCampo = "" + request.getParameter("cDscModelo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHModelo.setCDscModelo(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHModelo.setCDscBreve(cCampo);

			if (request.getParameter("lActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHModelo.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vVEHModelo;
	}
}