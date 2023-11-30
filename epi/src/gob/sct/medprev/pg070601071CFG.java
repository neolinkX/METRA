package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Calibraci�n de Equipo - Cat�logo de
 * Empresas de Mantenimiento
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070601071CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070601071CFG.png'>
 */
public class pg070601071CFG extends CFGCatBase2 {
	public pg070601071CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070601070.jsp|pg070601072.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDEQMEmpMtto dEQMEmpMtto = new TDEQMEmpMtto();
		try {
			cClave = (String) dEQMEmpMtto.insert(null, this.getInputs());
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
		TDEQMEmpMtto dEQMEmpMtto = new TDEQMEmpMtto();
		try {
			cClave = (String) dEQMEmpMtto.update(null, this.getInputs());
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
		TDEQMEmpMtto dEQMEmpMtto = new TDEQMEmpMtto();
		try {
			cClave = (String) dEQMEmpMtto.disable(null, this.getInputs());
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
		TDEQMEmpMtto dEQMEmpMtto = new TDEQMEmpMtto();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";
			if (cCondicion.compareTo("") != 0) {
				cWhere = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			vDespliega = dEQMEmpMtto.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(cActual);
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
		TVEQMEmpMtto vEQMEmpMtto = new TVEQMEmpMtto();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEmpMtto.setICveEmpMtto(iCampo);

			cCampo = "" + request.getParameter("cRFC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCRFC(cCampo);

			cCampo = "" + request.getParameter("cCURP");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCCURP(cCampo);

			cCampo = "" + request.getParameter("cTpoPersona");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCTpoPersona(cCampo);

			cCampo = "" + request.getParameter("cApPaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCApPaterno(cCampo);

			cCampo = "" + request.getParameter("cApMaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCApMaterno(cCampo);

			cCampo = "" + request.getParameter("cNombreRS");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCNombreRS(cCampo);

			if (request.getParameter("hdBoton").compareTo("Borrar") == 0)
				vEQMEmpMtto.setCDscEmpMtto("");
			else {
				if (request.getParameter("cTpoPersona") != null
						&& request.getParameter("cTpoPersona").compareTo("F") == 0
						&& request.getParameter("cApPaterno") != null
						&& request.getParameter("cApMaterno") != null)
					cCampo = request.getParameter("cNombreRS") + " "
							+ request.getParameter("cApPaterno") + " "
							+ request.getParameter("cApMaterno");
				else
					cCampo = request.getParameter("cNombreRS");
				if (cCampo.compareTo("null") == 0) {
					cCampo = "";
				}
				vEQMEmpMtto.setCDscEmpMtto(cCampo);
			}

			cCampo = "" + request.getParameter("cContacto");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCContacto(cCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCColonia(cCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCCiudad(cCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEmpMtto.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEmpMtto.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEmpMtto.setICveMunicipio(iCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEmpMtto.setICP(iCampo);

			cCampo = "" + request.getParameter("cTel");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCTel(cCampo);

			cCampo = "" + request.getParameter("cFax");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCFax(cCampo);

			cCampo = "" + request.getParameter("cCorreoElec");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEmpMtto.setCCorreoElec(cCampo);

			if (request.getParameter("lActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEmpMtto.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEQMEmpMtto;
	}
}