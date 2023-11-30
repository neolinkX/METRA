package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import com.micper.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuración para CFG Catalogo de Representantes Legales de la
 * Empresa
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070502015.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070502015.png'>
 */
public class pg070502015CFG extends CFGCatBase2 {
	public pg070502015CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070502014.jsp|pg070502010.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDCTRRepresentante dCTRRepresentante = new TDCTRRepresentante();
		try {
			cClave = (String) dCTRRepresentante.insert(null, this.getInputs());
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
		TDCTRRepresentante dCTRRepresentante = new TDCTRRepresentante();
		try {
			cClave = (String) dCTRRepresentante.update(null, this.getInputs());
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
		TDCTRRepresentante dCTRRepresentante = new TDCTRRepresentante();
		try {
			cClave = (String) dCTRRepresentante.disable(null, this.getInputs());
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
		TDCTRRepresentante dCTRRepresentante = new TDCTRRepresentante();
		String iEmpresa = "";
		String where = "";

		if (request.getParameter("iCveEmpresa") != null
				&& !request.getParameter("iCveEmpresa").equals(""))
			iEmpresa = request.getParameter("iCveEmpresa").toString();

		try {
			if (!iEmpresa.equals("")) {
				where = " where CTRRepresentante.iCveEmpresa=" + iEmpresa;
				if (!cCondicion.equals(""))
					where = where + " and " + cCondicion;
				if (cOrden.compareToIgnoreCase("") != 0)
					cOrden = cOrden + " desc";
				else
					cOrden = " order by CTRRepresentante.iCveRepresentante desc ";
				vDespliega = dCTRRepresentante.FindByAllRepPaisEdoMcpio(where
						+ cOrden);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método fillPK
	 */
	public void fillPK() {
		mPk.add(cActual);
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
		TVCTRRepresentante vCTRRepresentante = new TVCTRRepresentante();
		try {
			cCampo = "" + request.getParameter("iCveEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRRepresentante.setICveEmpresa(iCampo);

			cCampo = "" + request.getParameter("iCveRepresentante");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRRepresentante.setICveRepresentante(iCampo);

			cCampo = "" + request.getParameter("cRFC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCRFC(cCampo);

			cCampo = "" + request.getParameter("cCURP");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCCURP(cCampo);

			cCampo = "" + request.getParameter("cNombre");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCNombre(cCampo);

			cCampo = "" + request.getParameter("cApPaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCApPaterno(cCampo);

			cCampo = "" + request.getParameter("cApMaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCApMaterno(cCampo);

			cCampo = "" + request.getParameter("cPuesto");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCPuesto(cCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCColonia(cCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRRepresentante.setICP(iCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRRepresentante.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRRepresentante.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRRepresentante.setICveMunicipio(iCampo);

			cCampo = "" + request.getParameter("cTel");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCTel(cCampo);

			cCampo = "" + request.getParameter("cFax");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCFax(cCampo);

			cCampo = "" + request.getParameter("cCorreoElec");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setCCorreoElec(cCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRRepresentante.setLActivo(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRRepresentante.setcObservacion(cCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vCTRRepresentante;
	}
}