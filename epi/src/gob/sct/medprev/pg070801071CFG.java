package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Control de Almac�n - Cat�logo de Conceptos
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel POpoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801071CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801071CFG.png'>
 */
public class pg070801071CFG extends CFGCatBase2 {
	public pg070801071CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801070.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDALMConcepto dALMConcepto = new TDALMConcepto();
		try {
			cClave = (String) dALMConcepto.insert(null, this.getInputs());
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
		TDALMConcepto dALMConcepto = new TDALMConcepto();
		try {
			cClave = (String) dALMConcepto.update(null, this.getInputs());
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
		TDALMConcepto dALMConcepto = new TDALMConcepto();
		try {
			cClave = (String) dALMConcepto.disable(null, this.getInputs());
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
		TDALMConcepto dALMConcepto = new TDALMConcepto();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where C.iCveTpoMovimiento = "
						+ request.getParameter("hdCampoClave1");
			else if (request.getParameter("iCveTpoMovimiento") != null
					&& request.getParameter("iCveTpoMovimiento").compareTo("") != 0)
				cWhere += " where C.iCveTpoMovimiento = "
						+ request.getParameter("iCveTpoMovimiento");
			else if (request.getParameter("hdTpoMovimiento") != null
					&& request.getParameter("hdTpoMovimiento").compareTo("") != 0)
				cWhere += " where C.iCveTpoMovimiento = "
						+ request.getParameter("hdTpoMovimiento");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			if ((request.getParameter("iCveTpoMovimiento") != null
					&& request.getParameter("iCveTpoMovimiento").compareTo("") != 0
					&& request.getParameter("iCveTpoMovimiento").compareTo(
							"null") != 0 && Integer.parseInt(request
					.getParameter("iCveTpoMovimiento")) > 0)
					|| (request.getParameter("hdTpoMovimiento") != null
							&& request.getParameter("hdTpoMovimiento")
									.compareTo("") != 0
							&& request.getParameter("hdTpoMovimiento")
									.compareTo("null") != 0 && Integer
							.parseInt(request.getParameter("hdTpoMovimiento")) > 0)
					|| (request.getParameter("hdCampoClave1") != null
							&& request.getParameter("hdCampoClave1").compareTo(
									"") != 0
							&& request.getParameter("hdCampoClave").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdCampoClave1")) > 0))
				vDespliega = dALMConcepto.FindByAll(cWhere, cOrderBy);
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
			mPk.add(request.getParameter("hdTpoMovimiento"));
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
		TVALMConcepto vALMConcepto = new TVALMConcepto();
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0)
				cCampo = "" + request.getParameter("hdTpoMovimiento");
			else
				cCampo = "" + request.getParameter("iCveTpoMovimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMConcepto.setICveTpoMovimiento(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMConcepto.setICveConcepto(iCampo);

			cCampo = "" + request.getParameter("cDscConcepto");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMConcepto.setCDscConcepto(cCampo);

			if (request.getParameter("lActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMConcepto.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vALMConcepto;
	}
}