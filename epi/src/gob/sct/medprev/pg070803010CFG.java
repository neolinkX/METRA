package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TExcel;
import com.micper.util.TFechas;

/**
 * * Clase de configuracion para Clase para la tabla ALMSolicSumin
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hern�ndez Garc�a
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg070803010CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803010CFG.java.png'>
 */
public class pg070803010CFG extends CFGCatBase2 {
	public pg070803010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();
		try {
			cClave = (String) dALMSolicSumin.insert(null, this.getInputs());
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
		TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();
		try {
			cClave = (String) dALMSolicSumin.update(null, this.getInputs());
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
		TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();
		try {
			cClave = (String) dALMSolicSumin.delete(null, this.getInputs());
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
		TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();
		cPaginas = "pg070803011.jsp|pg070803012.jsp|";
		boolean lWhere = false;
		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}
			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY ALMSolicSumin.iCveSolicSum ";
			TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
					.getAttribute("UsrID");
			String cUsuario = "";
			int iUsuario = 0;
			if (vUsuario != null) {
				cUsuario = vUsuario.getCNombre() + " "
						+ vUsuario.getCApPaterno() + " "
						+ vUsuario.getCApMaterno();
				iUsuario = vUsuario.getICveusuario();
			}
			if (iUsuario > 0)
				if (!cCondicion.equals(""))
					cCondicion += " and ALMSolicSumin.iCveUsuSolicita = "
							+ iUsuario + " ";
				else
					cCondicion = " WHERE ALMSolicSumin.iCveUsuSolicita = "
							+ iUsuario + " ";
			vDespliega = dALMSolicSumin.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		if (request.getParameter("iAnio") != null
				&& request.getParameter("iAnio").toString().compareTo("") != 0)
			mPk.add(request.getParameter("iAnio").toString());
		if (request.getParameter("iCveAlmacen") != null
				&& request.getParameter("iCveAlmacen").toString().compareTo("") != 0)
			mPk.add(request.getParameter("iCveAlmacen").toString());
		mPk.add(cClave);
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
		TVALMSolicSumin vALMSolicSumin = new TVALMSolicSumin();
		try {
			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveAlmacen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setICveAlmacen(iCampo);

			cCampo = "" + request.getParameter("iCveSolicSum");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setICveSolicSum(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setICveModulo(iCampo);

			cCampo = "" + request.getParameter("iCveArea");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setICveArea(iCampo);

			cCampo = "" + request.getParameter("iCveUsuSolicita");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setICveUsuSolicita(iCampo);

			cCampo = "" + request.getParameter("iCveUsuAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setICveUsuAutoriza(iCampo);

			cCampo = "" + request.getParameter("dtSolicitud");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vALMSolicSumin.setDtSolicitud(dtCampo);

			cCampo = "" + request.getParameter("dtSuministro");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vALMSolicSumin.setDtSuministro(dtCampo);

			cCampo = "" + request.getParameter("lProgramada");
			if (!cCampo.equalsIgnoreCase("null")
					&& !cCampo.equalsIgnoreCase("")) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setLProgramada(iCampo);

			cCampo = "" + request.getParameter("iCvePrioridad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setICvePrioridad(iCampo);

			cCampo = "" + request.getParameter("iCvePeriodo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setICvePeriodo(iCampo);

			cCampo = "" + request.getParameter("lAutorizada");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setLAutorizada(iCampo);

			cCampo = "" + request.getParameter("lRevisada");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setLRevisada(iCampo);

			cCampo = "" + request.getParameter("lSuministrada");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMSolicSumin.setLSuministrada(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vALMSolicSumin;
	}
}