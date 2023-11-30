package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Cat�logo de Unidades M�dicas
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003011CFG.png'>
 */
public class pg071003011CFG extends CFGCatBase2 {
	public pg071003011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071003010.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
		try {
			cClave = (String) dGRLUniMed.insert(null, this.getInputs());
			this.getInputs();
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
		TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
		try {
			cClave = (String) dGRLUniMed.update(null, this.getInputs());
			this.getInputs();
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
		TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
		try {
			cClave = (String) dGRLUniMed.disable(null, this.getInputs());
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
		TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null) {
				cActual = request.getParameter("hdRowNum");
			}

			if (cCondicion.compareTo("") != 0)
				cWhere = " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by iCveUniMed ";

			vDespliega = dGRLUniMed.FindByAll(cWhere, cOrderBy);
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
		TVGRLUniMed vGRLUniMed = new TVGRLUniMed();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("cDscUniMed");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLUniMed.setCDscUniMed(cCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setICveMunicipio(iCampo);

			if (request.getParameter("lCis").compareTo("Si") == 0)
				cCampo = "1";
			else
				cCampo = "0";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setLCis(iCampo);

			if (request.getParameter("lPago").compareTo("Si") == 0)
				cCampo = "1";
			else
				cCampo = "0";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setLPago(iCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLUniMed.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLUniMed.setCColonia(cCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setICP(iCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLUniMed.setCCiudad(cCampo);

			cCampo = "" + request.getParameter("cTel");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLUniMed.setCTel(cCampo);

			cCampo = "" + request.getParameter("cCorreoE");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLUniMed.setCCorreoE(cCampo);

			cCampo = "" + request.getParameter("iCveUsuResp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setICveUsuResp(iCampo);

			if (request.getParameter("lPrivada").compareTo("Si") == 0)
				cCampo = "1";
			else
				cCampo = "0";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setLPrivada(iCampo);

			if (request.getParameter("lVigente").compareTo("Si") == 0)
				cCampo = "1";
			else
				cCampo = "0";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUniMed.setLVigente(iCampo);

			cCampo = "" + request.getParameter("iCveUddAdmiva");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				cCampo = "0";
			} else {
				cCampo = "0";
			}
			vGRLUniMed.setICveUddAdmiva(cCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLUniMed;
	}
}
