package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.TVUsuario;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Vigencias de Puestos
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author <dd>AG SA L.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071004041CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071004041CFG.png'>
 */
public class pg071004043CFG extends CFGCatBase2 {
	public pg071004043CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071004040.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		// TDGRLPuesto dGRLPuesto = new TDGRLPuesto();
		TDGRLCONVIGPUE dGRLCONVIGPUE = new TDGRLCONVIGPUE();
		try {
			// cClave = (String) dGRLPuesto.insert(null, this.getInputs());
			cClave = (String) dGRLCONVIGPUE.insert(null, this.getInputs());
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
		// TDGRLPuesto dGRLPuesto = new TDGRLPuesto();
		TDGRLCONVIGPUE dGRLCONVIGPUE = new TDGRLCONVIGPUE();
		try {
			// cClave = (String) dGRLPuesto.update(null, this.getInputs());
			cClave = (String) dGRLCONVIGPUE.update(null, this.getInputs());
		} catch (Exception ex) {
			ex.printStackTrace();
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
		// TDGRLPuesto dGRLPuesto = new TDGRLPuesto();
		TDGRLCONVIGPUE dGRLCONVIGPUE = new TDGRLCONVIGPUE();
		try {
			// cClave = (String) dGRLPuesto.delete(null, this.getInputs());
			// cClave = (String) dGRLCONVIGPUE.delete(null, this.getInputs());
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
		// TDGRLPuesto dGRLPuesto = new TDGRLPuesto();
		TDGRLCONVIGPUE dGRLCONVIGPUE = new TDGRLCONVIGPUE();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null) {
				cActual = request.getParameter("hdCampoClave");

			}
			String cWhere = "";
			String cOrderBy = "";
			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0) {
				cWhere += " where iCveMdoTrans = "
						+ request.getParameter("hdCampoClave1")
						+ " and iCveCategoria = "
						+ request.getParameter("hdCampoClave2")
						+ " and iCvePuesto = "
						+ request.getParameter("hdCampoClave3")
						+ " and iOrden = "
						+ request.getParameter("hdCampoClave4");
			} else if (request.getParameter("iCveMdoTrans") != null
					&& request.getParameter("iCveMdoTrans").compareTo("") != 0) {
				cWhere += " where iCveMdoTrans = "
						+ request.getParameter("iCveMdoTrans")
						+ " and iCveCategoria = "
						+ request.getParameter("iCveCategoria")
						+ " and iCvePuesto = "
						+ request.getParameter("iCvePuesto") + " and iOrden = "
						+ request.getParameter("iOrden");
			} else if (request.getParameter("hdMdoTrans") != null
					&& request.getParameter("hdMdoTrans").compareTo("") != 0) {
				cWhere += " where iCveMdoTrans = "
						+ request.getParameter("hdMdoTrans")
						+ " and iCveCategoria = "
						+ request.getParameter("hdCategoria")
						+ " and iCvePuesto = "
						+ request.getParameter("hdPuesto") + " and iOrden = "
						+ request.getParameter("hdOrden");

			}
			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0) {
					cWhere += " and " + cCondicion;
				} else {
					cWhere += cCondicion;
				}
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy += cOrden;
			} else {
				cOrderBy += " order by iOrden ";

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
							.getParameter("hdCampoClave1")) > 0)) {
				vDespliega = dGRLCONVIGPUE.FindByAll(cWhere, cOrderBy);
			}
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
			mPk.add(request.getParameter("hdCampoClave3"));
		}
		if (request.getParameter("hdBoton").compareTo("Guardar") == 0
				|| request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
			mPk.add(request.getParameter("hdMdoTrans"));
			mPk.add(request.getParameter("hdCategoria"));
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
		// TVGRLPuesto vGRLPuesto = new TVGRLPuesto();
		TVGRLCONVIGPUE vGRLCONVIGPUE = new TVGRLCONVIGPUE();
		TVUsuario usuario = (TVUsuario) this.httpReq.getSession().getAttribute(
				"UsrID");
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
				cCampo = "" + request.getParameter("hdMdoTrans");
			} else {
				cCampo = "" + request.getParameter("iCveMdoTrans");
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLCONVIGPUE.setICveMdoTrans(iCampo);

			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
				cCampo = "" + request.getParameter("hdCategoria");
			} else {
				cCampo = "" + request.getParameter("iCveCategoria");
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLCONVIGPUE.setICveCategoria(iCampo);

			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
				cCampo = "" + request.getParameter("hdPuesto");
			} else {
				cCampo = "" + request.getParameter("iCvePuesto");
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLCONVIGPUE.setICvePuesto(iCampo);

			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
				cCampo = "" + request.getParameter("hdOrden");
			} else {
				cCampo = "" + request.getParameter("iOrden");
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLCONVIGPUE.setIOrden(iCampo);

			cCampo = "" + request.getParameter("iEdMayor");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			}
			vGRLCONVIGPUE.setIEdMayor(iCampo);

			cCampo = "" + request.getParameter("iEdMenor");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			}
			vGRLCONVIGPUE.setIEdMenor(iCampo);

			if (request.getParameter("lActivo") == null) {
				cCampo = "0";
			} else {
				cCampo = "1";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLCONVIGPUE.setLActivo(iCampo);

			cCampo = "" + request.getParameter("iTmpVigencia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			}
			vGRLCONVIGPUE.setITmpVigencia(iCampo);

			// Se agrega el usuario el Usuario BEA SYSTEMS 16/10/2006
			if (usuario != null) {
				vGRLCONVIGPUE.setICveUsuGenera(usuario.getICveusuario());
			}
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLCONVIGPUE;
	}
}
