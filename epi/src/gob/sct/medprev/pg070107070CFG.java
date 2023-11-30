package gob.sct.medprev;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.TVUsuario;
import com.micper.util.*;

import gob.sct.medprev.dao.*;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG Registro de Citas
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070102020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070102020CFG.png'>
 */
public class pg070107070CFG extends CFGCatBase2 {

	private String cCita;
	private String cModulo;
	private String cFecha;

	public pg070107070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public void mainBlock() {
		cModulo = request.getParameter("iCveModulo");
		cFecha = request.getParameter("dtFecha");
		cCita = "";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
		try {
			ExpBitMod mod = new ExpBitMod();
			mod.setIpAddress(request.getParameter("hdIpAddress"));
			mod.setMacAddress(request.getParameter("hdMacAddress"));
			mod.setComputerName(request.getParameter("hdComputerName"));
			cClave = (String) dMEDInhabilita.insertIpMacName(null,
					this.getInputs(), mod);

			cCita = cClave;
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			if (cClave.compareTo("ERROR") == 0) {
				vErrores.acumulaError("Registro Existente: ", 0,
						"No es posible registrar varias citas para la misma persona el mismo d�a");
			}
			super.Guardar();
			cActual = request.getParameter("iCveUniMed");
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
			// cClave = (String) dMEDInhabilita.update(null, this.getInputs());
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
		TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
		try {
			// cClave = (String) dEPICisCita.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			// super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo fillVector
	 */
	public void fillVector() {
		TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		String cWhere;
		try {
			/*
			 * cWhere = " INICIOINH = '" +
			 * tfCampo.getDateSQL(request.getParameter
			 * ("dtFecha"))+" 00:00:00.0'";
			 */

			String cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {

				// cWhere = " iCvePersonal = "+
				// request.getParameter("iCvePersonal");

				vDespliega = dMEDInhabilita.FindByAllMax(cCampo);
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {

		mPk.add(cActual);
		mPk.add(cModulo);
		mPk.add(cFecha);
		mPk.add(cCita);

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
		TVMEDInhabilita vMEDInhabilita = new TVMEDInhabilita();

		// System.out.println("Bajando el usuario de la sesion");
		TVUsuario usuario = (TVUsuario) this.httpReq.getSession().getAttribute(
				"UsrID"); // BEA SYSTEMS 16/10/2006
		try {

			cCampo = "" + request.getParameter("dtFecha");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vMEDInhabilita.setInicioInh(dtCampo);
			// System.out.println(cCampo);

			cCampo = "" + request.getParameter("dtFecha2");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vMEDInhabilita.setFinInh(dtCampo);
			// System.out.println(cCampo);

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDInhabilita.setICvePersonal(iCampo);
			// System.out.println(cCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDInhabilita.setICveMotivo(iCampo);
			// System.out.println(cCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDInhabilita.setCObservacion(cCampo);
			// System.out.println(cCampo);

			// Se agrega el usuario
			if (usuario != null) {
				vMEDInhabilita.setiCveUsuInh(usuario.getICveusuario());
			}
			// System.out.println(usuario.getICveusuario());

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDInhabilita;
	}

	/**
	 * Validando que el Expediente no esta inhabilitado
	 */
	public int getInhabilitado(String Expediente) {
		TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
		Vector vcInhabilita = new Vector();
		TVMEDInhabilita vMEDInhabilita;
		TFechas dtFecha = new TFechas();
		// int Inhabilitado = 0;
		Boolean Inhabilitado = false;
		String fecha1 = "";
		String fecha2 = "";
		int Activo = 0;

		try {
			String cCondicion = " M1.iCvePersonal = " + Expediente;
			Inhabilitado = dMEDInhabilita.Inhabilitado(cCondicion);
			if (Inhabilitado) {
				Activo = 1;
			}
		} catch (Exception e) {
			vcInhabilita = new Vector();
			e.printStackTrace();
		}

		return Activo;
	}

}