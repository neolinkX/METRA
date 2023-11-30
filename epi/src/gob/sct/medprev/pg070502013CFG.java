package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Control al Transporte - Cat�logo de los
 * Domicilios de las Empresas
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070502013CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070502013CFG.png'>
 */
public class pg070502013CFG extends CFGCatBase2 {
	public pg070502013CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070502012.jsp||pg070502010.jsp|";
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDCTRDomicilio dCTRDomicilio = new TDCTRDomicilio();
		try {
			cClave = (String) dCTRDomicilio.insert(null, this.getInputs());
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
		TDCTRDomicilio dCTRDomicilio = new TDCTRDomicilio();
		try {
			cClave = (String) dCTRDomicilio.update(null, this.getInputs());
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
		TDCTRDomicilio dCTRDomicilio = new TDCTRDomicilio();
		try {
			cClave = (String) dCTRDomicilio.disable(null, this.getInputs());
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
		TDCTRDomicilio dCTRDomicilio = new TDCTRDomicilio();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveEmpresa") != null
					&& request.getParameter("iCveEmpresa").compareTo("") != 0)
				cWhere += " where CTRDomicilio.iCveEmpresa = "
						+ request.getParameter("iCveEmpresa");
			else if (request.getParameter("hdEmpresa") != null
					&& request.getParameter("hdEmpresa").compareTo("") != 0)
				cWhere += " where CTRDomicilio.iCveEmpresa = "
						+ request.getParameter("hdEmpresa");
			else if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where CTRDomicilio.iCveEmpresa = "
						+ request.getParameter("hdCampoClave1");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += " where " + cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy += cOrden;
			else
				cOrderBy = " order by iCveDomicilio desc";

			if (request.getParameter("iCveEmpresa") != null
					|| request.getParameter("hdEmpresa") != null)
				vDespliega = dCTRDomicilio.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		if (request.getParameter("hdCampoClave1") != null
				&& request.getParameter("hdCampoClave1").compareTo("") != 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
		} else {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0)
				mPk.add(request.getParameter("hdEmpresa"));
			else
				mPk.add(request.getParameter("iCveEmpresa"));
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
		TVCTRDomicilio vCTRDomicilio = new TVCTRDomicilio();
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0)
				cCampo = "" + request.getParameter("hdEmpresa");
			else
				cCampo = "" + request.getParameter("iCveEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRDomicilio.setICveEmpresa(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRDomicilio.setICveDomicilio(iCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRDomicilio.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRDomicilio.setCColonia(cCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRDomicilio.setCCiudad(cCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRDomicilio.setICP(iCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRDomicilio.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRDomicilio.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRDomicilio.setICveMunicipio(iCampo);

			cCampo = "" + request.getParameter("cTel");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRDomicilio.setCTel(cCampo);

			cCampo = "" + request.getParameter("cFax");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRDomicilio.setCFax(cCampo);

			cCampo = "" + request.getParameter("cCorreoElec");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRDomicilio.setCCorreoElec(cCampo);

			if (request.getParameter("chklActivo") == null) {
				vCTRDomicilio.setLActivo(0);
			} else {
				TDCTRDomicilio dCTRDomicilioTemp = new TDCTRDomicilio();
				TVCTRDomicilio vCTRDomicilioTemp = new TVCTRDomicilio();
				Vector vcCTRDomicilioTemp = new Vector();
				vcCTRDomicilioTemp = dCTRDomicilioTemp.FindByAll(
						" where iCveEmpresa = "
								+ request.getParameter("hdEmpresa")
								+ " and lActivo = 1 ", "");
				if (vcCTRDomicilioTemp.size() > 0)
					for (int i = 0; i < vcCTRDomicilioTemp.size(); i++)
						vCTRDomicilioTemp = (TVCTRDomicilio) vcCTRDomicilioTemp
								.get(i);
				vCTRDomicilioTemp.setICveEmpresa(Integer.parseInt(request
						.getParameter("hdEmpresa")));
				vCTRDomicilioTemp.setICveDomicilio(vCTRDomicilioTemp
						.getICveDomicilio());
				vCTRDomicilioTemp.setLActivo(0);
				cClave = (String) dCTRDomicilioTemp.updateActual(null,
						vCTRDomicilioTemp);

				vCTRDomicilio.setLActivo(1);
			}

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vCTRDomicilio;
	}
}