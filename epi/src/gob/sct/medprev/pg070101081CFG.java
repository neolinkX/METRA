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
 * * Clase de configuracion para Clase que maneja las acciones de la p�gina
 * JSP
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101081CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101081CFG.png'>
 */
public class pg070101081CFG extends CFGCatBase2 {

	private final int NUEVO = 0;
	private final int MODIFICAR = 1;

	public pg070101081CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070101080.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		try {
			Object obj = this.getInputs();
			if (validar(obj, NUEVO)) {
				cClave = (String) dMEDPerfilMC.insert(null, obj);
			} else {
				cClave = request.getParameter("hdCampoClave");
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			cClave = request.getParameter("iCvePerfil");
			// cAccion = "Ultimo";

			super.Guardar();

		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		try {
			Object obj = this.getInputs();
			if (validar(obj, MODIFICAR)) {
				cClave = (String) dMEDPerfilMC.update(null, obj);
			} else {
				cClave = request.getParameter("hdCampoClave");
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			cClave = request.getParameter("iCvePerfil");
			// cAccion = "Cancelar";

			super.Guardar();

		}
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		try {
			cClave = (String) dMEDPerfilMC.delete(null, this.getInputs());
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
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		cClave = request.getParameter("hdCampoClave");
		String accion = request.getParameter("hdBoton");
		String perfil = request.getParameter("hdCampoClave");
		String mdoTrans = request.getParameter("iCveMdoTrans");

		try {

			String where = " WHERE 1=1 ";

			if ((perfil == null || perfil.equals("") || perfil.equals(""))) {
				where += "";
			}
			if (mdoTrans != null && !mdoTrans.equals("")
					&& !mdoTrans.equals("null")) {
				where += " AND iCveMdoTrans = " + mdoTrans;
			}
			if (cCondicion != null && !cCondicion.equals("")
					&& !cCondicion.equals("null")) {
				where += " and " + cCondicion;
			}
			if (cOrden == null || cOrden.equals("") || cOrden.equals("null")) {
				cOrden = "";

			}

			vDespliega = dMEDPerfilMC.findByWhere(where, cOrden);
			if (accion.equalsIgnoreCase("Guardar")) {
				cAccion = "Ultimo";
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
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
		TVMEDPerfilMC vMEDPerfilMC = new TVMEDPerfilMC();
		try {
			cCampo = "" + request.getParameter("iCvePerfil");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilMC.setICvePerfil(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilMC.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCveGrupo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilMC.setICveGrupo(iCampo);

			cCampo = "" + request.getParameter("dtInicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vMEDPerfilMC.setDtInicio(dtCampo);

			cCampo = "" + request.getParameter("dtFin");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vMEDPerfilMC.setDtFin(dtCampo);

			cCampo = "" + request.getParameter("lVigente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDPerfilMC.setLVigente(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDPerfilMC;
	}

	/**
	 * Metodo que efect�a las validaciones necesarias antes de insertar o
	 * modificar un registro.
	 * 
	 * @param obj
	 *            el Value Objet generado durante el request del usuario
	 * @return un boolean indicando si los datos son v�lidos (
	 *         <code>true</code>) o no lo son (<code>false</code>).
	 */
	private boolean validar(Object obj, int modo) {
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		TVMEDPerfilMC pmc = null;
		boolean validacion = true;
		// verificar el tipo de dato antes de hacer el casting
		if (obj instanceof TVMEDPerfilMC) {
			pmc = (TVMEDPerfilMC) obj;
		}
		// validar que para un modo de transporte y un grupo exista un solo
		// registro vigente
		// (lVigente==1)
		String cveMdoTrans = "" + pmc.getICveMdoTrans();
		String cveGrupo = "" + pmc.getICveGrupo();
		int vigente = pmc.getLVigente();
		String where = " where iCveMdoTrans=" + cveMdoTrans + " and iCveGrupo="
				+ cveGrupo + " and lVigente=1";
		String cOrden = "";
		Vector v = null;
		int found = 0;
		try {
			v = dMEDPerfilMC.findByWhere(where, cOrden);
		} catch (DAOException ex) {
			error("validar", ex);
		}

		switch (modo) {
		case NUEVO:
			if (vigente != 0 && v.size() > modo) {
				String err = "Debe tener s�lo un perfil m�dico cient�fico vigente para el perfil seleccionado";

				this.vErrores.acumulaError("", 0, err);
				validacion = false;
			}
			break;
		case MODIFICAR:
			if (vigente != 0 && v.size() >= modo) {
				String err = "Debe tener s�lo un perfil m�dico cient�fico vigente para el perfil seleccionado";

				this.vErrores.acumulaError("", 0, err);
				validacion = false;
			}
		}

		// validar que la fecha de fin de vigencia sea mayor o igual que la de
		// inicio
		// (dtFin >= dtInicio)

		if (pmc.getDtFin().before(pmc.getDtInicio())) {
			String err = "La fecha de fin de vigencia debe ser posterior a la de inicio de vigencia";

			this.vErrores.acumulaError("", 0, err);
			validacion = false;
		}
		return validacion;
	}

}
