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
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.TVUsuario;

import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;
import com.micper.util.*;

/**
 * * Clase de configuracion para CFG Cat�lgo Direcciones
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. Gonz�lez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070103042CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070103042CFG.png'>
 */
public class pg070103042CFG extends CFGCatBase2 {
	public pg070103042CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070103045.jsp|pg070103040.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDPERDireccion dPERDireccion = new TDPERDireccion();
		try {
			cClave = (String) dPERDireccion.insert(null, this.getInputs());
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
		TDPERDireccion dPERDireccion = new TDPERDireccion();
		try {
			cClave = (String) dPERDireccion.update(null, this.getInputs());
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
		TDPERDireccion dPERDireccion = new TDPERDireccion();
		try {
			cClave = (String) dPERDireccion.delete(null, this.getInputs());
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
		TDPERDireccion dPERDireccion = new TDPERDireccion();
		try {
			vDespliega = dPERDireccion.FindByPersona(request
					.getParameter("hdICvePersonal"));
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void FillPK() {
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
		TVUsuario usuario = (TVUsuario) this.httpReq.getSession().getAttribute(
				"UsrID");
		TVPERDireccion vPERDireccion = new TVPERDireccion();
		try {
			cCampo = "" + request.getParameter("hdICvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDireccion.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave2");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDireccion.setICveDireccion(iCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDireccion.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cNumExt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDireccion.setCNumExt(cCampo);

			cCampo = "" + request.getParameter("cNumInt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDireccion.setCNumInt(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDireccion.setCColonia(cCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDireccion.setICP(iCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDireccion.setCCiudad(cCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDireccion.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDireccion.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDireccion.setICveMunicipio(iCampo);
			
			cCampo = "" + request.getParameter("iCveLocalidad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDireccion.setICveLocalidad(iCampo);

			cCampo = "" + request.getParameter("cTel");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDireccion.setCTel(cCampo);

			vPERDireccion.setICveUsuario(usuario.getICveusuario());

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vPERDireccion;
	}

}