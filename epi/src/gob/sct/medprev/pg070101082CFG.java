package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * Clase de configuracion para Clase manejadora de eventos
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Romeo S�nchez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101082CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101082CFG.png'>
 */
public class pg070101082CFG extends CFGCatBase2 {

	private String cActual2 = ""; // campo que almacenar� el segundo valor de
									// la llave

	public pg070101082CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070101080.jsp|pg070101081.jsp|pg070101085.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMEDPerfilEspec dMEDPerfilEspec = new TDMEDPerfilEspec();
		try {
			Object mpe = this.getInputs();
			String foo = (String) dMEDPerfilEspec.insert(null, mpe);
			// setPK(cClave);
		} catch (DAOException e) {

			vErrores.acumulaError("", 0, e.getMessage());
			error("Error al insertar el registro", e);
		} catch (Exception ex) {

			vErrores.acumulaError("", 0, "");
			error("Error al insertar el registro", ex);
		} catch (Throwable t) {
			;
		} finally {
			cActual2 = request.getParameter("iCvePerfil");
			cClave = request.getParameter("iCveEspecialidad");

			super.Guardar();

		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDMEDPerfilEspec dMEDPerfilEspec = new TDMEDPerfilEspec();
		try {
			String foo = (String) dMEDPerfilEspec
					.update(null, this.getInputs());

			cActual2 = request.getParameter("iCvePerfil");
			cClave = request.getParameter("iCveEspecialidad");
			// setPK(cClave);
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
		TDMEDPerfilEspec dMEDPerfilEspec = new TDMEDPerfilEspec();
		cActual2 = request.getParameter("iCvePerfil");
		try {
			cClave = (String) dMEDPerfilEspec.delete(null, this.getInputs());
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
		TDMEDPerfilEspec dMEDPerfilEspec = new TDMEDPerfilEspec();
		String where = " WHERE 1=1 ";
		String accion = request.getParameter("hdBoton");

		try {

			String perfil = request.getParameter("iCvePerfil"); // iCvePerfil
			String clave = request.getParameter("hdCampoClave"); // iCvePerfil
			String especial = request.getParameter("iCveEspecialidad"); // iCveEspecialidad
			String mdoTrans = request.getParameter("iCveMdoTrans");

			cActual2 = perfil == null ? "" : perfil;
			cClave = especial == null ? "" : especial;

			// si la acci�n es "Guardar", la llave que se utiliz� para
			// insertar, debe ser
			// usada para mostrar el registro reci�n creado
			if (accion.startsWith("Guardar")) {
			}

			/*
			 * if ( (perfil != null && !perfil.equals("null") &&
			 * !perfil.equals("")) && (especial != null &&
			 * !especial.equals("null") && !especial.equals(""))) { where +=
			 * " AND iCveEspecialidad = " + especial + " and iCvePerfil = " +
			 * perfil; } else
			 */

			if (accion.equals("Primero") || accion.equals("Anterior")
					|| accion.equals("Siguiente") || accion.equals("Ultimo")) {
				perfil = clave;
			}
			if ((perfil != null && !perfil.equals("null") && !perfil.equals(""))
					&& cOrden.length() == 0) { // &&
			// (especial == null || especial.equals("null") ||
			// especial.equals(""))) {
				where += " AND iCvePerfil = " + perfil;
			} else if ((perfil == null || perfil.equals("null") || perfil
					.equals(""))
					&& (especial == null || especial.equals("null") || especial
							.equals(""))) {
				where += "";
			}
			if (cCondicion != null && !cCondicion.equals("null")
					&& !cCondicion.trim().equals("")) {
				where += " and " + cCondicion;
			}

			vDespliega = dMEDPerfilEspec.findByWhere(where, cOrden);

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void fillPK() {
		mPk.add(cActual2);// request.getParameter("iCveEspecialidad")
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
		TVMEDPerfilEspec vMEDPerfilEspec = new TVMEDPerfilEspec();
		try {
			cCampo = "" + request.getParameter("iCvePerfil");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilEspec.setICvePerfil(iCampo);

			cCampo = "" + request.getParameter("iCveEspecialidad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilEspec.setICveEspecialidad(iCampo);

			cCampo = "" + request.getParameter("cEspecificacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDPerfilEspec.setCEspecificacion(cCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDPerfilEspec;
	}

	/**
	 * Metodo que recibe en un String la llave compuesta por dos campos
	 * separados por comas (,) y los asigna a las variables correspondientes a
	 * la llave primaria.
	 * 
	 * @param cClave
	 *            la llave compuesta recibida en la inserci�n o
	 *            actualizaci�n
	 * @author Romeo S�nchez
	 */
	private void setPK(String cClave) {

		String[] key = new String[2];
		StringTokenizer st = new StringTokenizer(cClave, ",");
		int idx = 0;
		while (st.hasMoreElements()) {
			key[idx] = (String) st.nextElement();

			idx++;
		}
		// deben ser 2: perfil y especialidad
		cActual = key[0];
		cActual2 = key[1];

	}

}
