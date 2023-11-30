package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Control de Veh�culos - Cat�logo de Empresas
 * de Mantenimiento
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070701081CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070701081CFG.png'>
 */
public class pg070701081CFG extends CFGCatBase2 {
	public pg070701081CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070701080.jsp|pg070701082.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDVEHEmpMantto dVEHEmpMantto = new TDVEHEmpMantto();
		try {
			cClave = (String) dVEHEmpMantto.insert(null, this.getInputs());
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
		TDVEHEmpMantto dVEHEmpMantto = new TDVEHEmpMantto();
		try {
			cClave = (String) dVEHEmpMantto.update(null, this.getInputs());
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
		TDVEHEmpMantto dVEHEmpMantto = new TDVEHEmpMantto();
		try {
			cClave = (String) dVEHEmpMantto.disable(null, this.getInputs());
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
		TDVEHEmpMantto dVEHEmpMantto = new TDVEHEmpMantto();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");
			else if (request.getParameter("hdEmpMantto") != null
					&& request.getParameter("hdEmpMantto").compareTo("") != 0
					&& request.getParameter("hdEmpMantto").compareTo("null") != 0) {
				cActual = request.getParameter("hdEmpMantto");
				cAccion = "ReposPk";
			}

			String cWhere = "";
			String cOrderBy = "";
			if (cCondicion.compareTo("") != 0) {
				cWhere = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			vDespliega = dVEHEmpMantto.FindByAll(cWhere, cOrderBy);
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
		TVVEHEmpMantto vVEHEmpMantto = new TVVEHEmpMantto();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHEmpMantto.setICveEmpMantto(iCampo);

			cCampo = "" + request.getParameter("cRFC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCRFC(cCampo);

			cCampo = "" + request.getParameter("cCURP");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCCURP(cCampo);

			cCampo = "" + request.getParameter("cTpoPersona");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCTpoPersona(cCampo);

			cCampo = "" + request.getParameter("cApPaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCApPaterno(cCampo);

			cCampo = "" + request.getParameter("cApMaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCApMaterno(cCampo);

			cCampo = "" + request.getParameter("cNombreRS");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCNombreRS(cCampo);

			if (request.getParameter("cTpoPersona") != null
					&& request.getParameter("cTpoPersona").compareTo("F") == 0
					&& request.getParameter("cApPaterno") != null
					&& request.getParameter("cApMaterno") != null)
				cCampo = "" + request.getParameter("cNombreRS") + " "
						+ request.getParameter("cApPaterno") + " "
						+ request.getParameter("cApMaterno");
			else if (request.getParameter("cNombreRS") != null)
				cCampo = "" + request.getParameter("cNombreRS");
			else
				cCampo = "";
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCDscEmpMantto(cCampo);

			cCampo = "" + request.getParameter("cContacto");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCContacto(cCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCColonia(cCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCCiudad(cCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHEmpMantto.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHEmpMantto.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHEmpMantto.setICveMunicipio(iCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHEmpMantto.setICP(iCampo);

			cCampo = "" + request.getParameter("cTel");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCTel(cCampo);

			cCampo = "" + request.getParameter("cFax");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCFax(cCampo);

			cCampo = "" + request.getParameter("cCorreoElec");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHEmpMantto.setCCorreoElec(cCampo);

			if (request.getParameter("lActivo") == null)
				cCampo = "0";
			else
				cCampo = "1";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHEmpMantto.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vVEHEmpMantto;
	}
}