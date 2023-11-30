package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;

/**
 * * Clase de configuracion para Clase para la Administraci�n de la
 * Informaci�n de la tabla VEHMantenimiento
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
 *      "JavaScript:alert('Consulte los archivos:\n-pg070702051CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070702051CFG.java.png'>
 */
public class pg070702051CFG extends CFGCatBase2 {
	public pg070702051CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070702050.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDVEHMantenimiento dVEHMantenimiento = new TDVEHMantenimiento();
		TDVEHSeguimiento dVEHSeguimiento = new TDVEHSeguimiento();
		TVVEHSeguimiento vVEHSeguimiento = new TVVEHSeguimiento();
		String cFiltro = "";
		Vector vTemp = new Vector();
		TFechas tfCampo = new TFechas();
		try {
			cFiltro = " WHERE M.iCveVehiculo  = "
					+ request.getParameter("hdCampoClave")
					+ "   AND M.iCveTpoMantto = "
					+ request.getParameter("iCveTpoMantto")
					+ "   AND M.dtProgramado  = '"
					+ tfCampo.getDateSQL(request.getParameter("dtProgramado"))
					+ "'" + "   AND M.lConcluido    = 0 "
					+ "   AND M.lCancelado    = 0 ";
			vTemp = dVEHMantenimiento.FindByAll(cFiltro, cOrden);
			if (vTemp.size() <= 0) {
				cClave = (String) dVEHMantenimiento.insertWithSequence(null,
						this.getInputs());
				vVEHSeguimiento.setICveVehiculo(new Integer(request
						.getParameter("hdCampoClave")).intValue());
				vVEHSeguimiento.setICveMantenimiento(new Integer(cClave)
						.intValue());
				vVEHSeguimiento.setICveProceso(new Integer(request
						.getParameter("iProceso")).intValue());
				vVEHSeguimiento.setICveEtapa(new Integer(request
						.getParameter("VEHEtapaInicio")).intValue());
				vVEHSeguimiento.setDtSolicitud(tfCampo.getDateSQL(request
						.getParameter("Today")));
				vVEHSeguimiento.setICveSolicitante(new Integer(request
						.getParameter("VEHSolicitaInicio")).intValue());
				vVEHSeguimiento.setICveUsuReg(new Integer(request
						.getParameter("Usuario")).intValue());
				vVEHSeguimiento.setICveUsuSolicita(new Integer(request
						.getParameter("Usuario")).intValue());
				vVEHSeguimiento.setCSolicitante("");
				vVEHSeguimiento.setCObservacion("");
				cClave = (String) dVEHSeguimiento.insert(null, vVEHSeguimiento);
			} else
				vErrores.acumulaError("", 0,
						" Ya existe un Mantenimiento con las caracteristicas especificadas!");
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
		TDVEHMantenimiento dVEHMantenimiento = new TDVEHMantenimiento();
		try {
			cClave = (String) dVEHMantenimiento.update(null, this.getInputs());
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
		TDVEHMantenimiento dVEHMantenimiento = new TDVEHMantenimiento();
		try {
			cClave = (String) dVEHMantenimiento.delete(null, this.getInputs());
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
		TDVEHMantenimiento dVEHMantenimiento = new TDVEHMantenimiento();
		boolean lWhere = false;
		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}

			if (request.getParameter("hdCampoClave") != null
					&& request.getParameter("hdCampoClave").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND M.iCveVehiculo = "
							+ request.getParameter("hdCampoClave");
				else {
					lWhere = true;
					cCondicion = " WHERE M.iCveVehiculo = "
							+ request.getParameter("hdCampoClave");
				}
			}

			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY M.iCveVehiculo, M.iCveMantenimiento  ";

			vDespliega = dVEHMantenimiento.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(request.getParameter("hdCampoClave"));
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
		TVVEHMantenimiento vVEHMantenimiento = new TVVEHMantenimiento();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveVehiculo(iCampo);

			cCampo = "" + request.getParameter("iCveMantenimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveMantenimiento(iCampo);

			cCampo = "" + request.getParameter("iCveTpoMantto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveTpoMantto(iCampo);

			cCampo = "" + request.getParameter("dtSolicitud");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHMantenimiento.setDtSolicitud(dtCampo);

			cCampo = "" + request.getParameter("dtProgramado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHMantenimiento.setDtProgramado(dtCampo);

			cCampo = "" + request.getParameter("dtInicia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHMantenimiento.setDtInicia(dtCampo);

			cCampo = "" + request.getParameter("iCveEmpMantto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveEmpMantto(iCampo);

			cCampo = "" + request.getParameter("cAccesorios");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHMantenimiento.setCAccesorios(cCampo);

			cCampo = "" + request.getParameter("cResultado");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHMantenimiento.setCResultado(cCampo);

			cCampo = "" + request.getParameter("cObservaciones");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHMantenimiento.setCObservaciones(cCampo);

			cCampo = "" + request.getParameter("lConcluido");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setLConcluido(iCampo);

			cCampo = "" + request.getParameter("iKilometraje");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setIKilometraje(iCampo);

			cCampo = "" + request.getParameter("dtRecepcion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHMantenimiento.setDtRecepcion(dtCampo);

			cCampo = "" + request.getParameter("Usuario");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveUsuSolicita(iCampo);

			cCampo = "" + request.getParameter("iCveUsuAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveUsuAutoriza(iCampo);

			cCampo = "" + request.getParameter("iCveUsuRecibe");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveUsuRecibe(iCampo);

			cCampo = "" + request.getParameter("lCancelado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setLCancelado(iCampo);

			cCampo = "" + request.getParameter("dtCancelacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHMantenimiento.setDtCancelacion(dtCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vVEHMantenimiento;
	}
}
