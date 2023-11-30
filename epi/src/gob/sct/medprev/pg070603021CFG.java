package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;

/**
 * * Clase de configuracion para Clase para la tabla EQMMantenimiento
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
 *      "JavaScript:alert('Consulte los archivos:\n-pg070603021CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070603021CFG.java.png'>
 */
public class pg070603021CFG extends CFGCatBase2 {
	TVEQMSeguimiento vEQMSeguimiento = new TVEQMSeguimiento();
	TFechas tfCampo = new TFechas();
	private StringBuffer activeX = new StringBuffer("");

	public pg070603021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070603020.jsp|";
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public StringBuffer Report() {
		StringBuffer sbRes = new StringBuffer("");
		TDEQMMantenimiento DEqmMantto = new TDEQMMantenimiento();
		TVEQMMantenimiento VEqmMantto;
		try {
			VEqmMantto = (TVEQMMantenimiento) this.getInputs();
			if (VEqmMantto != null && VEqmMantto.getICveEquipo() > 0
					&& VEqmMantto.getICveMantenimiento() > 0)
				sbRes = DEqmMantto.ReportSolicMantto(
						VEqmMantto.getICveEquipo(),
						VEqmMantto.getICveMantenimiento());
			else
				vErrores.acumulaError(
						"Debe existir un equipo y mantenimiento seleccionados para imprimir el reporte.",
						0, "");
		} catch (Exception ex) {
			sbRes = new StringBuffer("");
		}
		return sbRes;
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDEQMMantenimiento dEQMMantenimiento = new TDEQMMantenimiento();
		TDEQMSeguimiento dEQMSeguimiento = new TDEQMSeguimiento();
		String cFiltro = "";
		Vector vTemp = new Vector();
		try {
			cFiltro = " WHERE EQMMantenimiento.iCveEquipo = "
					+ request.getParameter("hdCampoClave")
					+ "   AND EQMMantenimiento.iCveTpoMantto = "
					+ request.getParameter("iCveTpoMantto")
					+ "   AND EQMMantenimiento.dtProgramado = '"
					+ request.getParameter("dtProgramado") + "'"
					+ "   AND EQMMantenimiento.lConcluido = 0 "
					+ "   AND EQMMantenimiento.lCancelado = 0 ";
			vTemp = dEQMMantenimiento.FindByAll(cFiltro, cOrden);
			if (vTemp.size() <= 0) {
				cClave = (String) dEQMMantenimiento.insert(null,
						this.getInputs());
				vEQMSeguimiento.setICveEquipo(new Integer(request
						.getParameter("hdCampoClave")).intValue());
				vEQMSeguimiento.setICveMantenimiento(new Integer(cClave)
						.intValue());
				vEQMSeguimiento.setICveProceso(new Integer(request
						.getParameter("iProceso")).intValue());
				vEQMSeguimiento.setICveEtapa(new Integer(request
						.getParameter("EQMEtapaInicio")).intValue());
				vEQMSeguimiento.setDtSolicitud(tfCampo.getDateSQL(request
						.getParameter("Today")));
				vEQMSeguimiento.setICveSolicitante(new Integer(request
						.getParameter("EQMSolicitaInicio")).intValue());
				vEQMSeguimiento.setICveUsuReg(new Integer(request
						.getParameter("Usuario")).intValue());
				vEQMSeguimiento.setICveUsuSolicita(new Integer(request
						.getParameter("Usuario")).intValue());
				dEQMSeguimiento.insert(null, vEQMSeguimiento);
			} else
				vErrores.acumulaError("", 0,
						" Ya existe un Mantenimiento con las caracteristicas especificadas!");
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDEQMMantenimiento dEQMMantenimiento = new TDEQMMantenimiento();
		try {
			cClave = (String) dEQMMantenimiento.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	}

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDEQMMantenimiento dEQMMantenimiento = new TDEQMMantenimiento();
		try {
			cClave = (String) dEQMMantenimiento.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMMantenimiento dEQMMantenimiento = new TDEQMMantenimiento();
		TVEQMMantenimiento vEQMMantenimiento = new TVEQMMantenimiento();
		boolean lWhere = false;
		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}

			if (request.getParameter("hdCampoClave") != null
					&& request.getParameter("hdCampoClave").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND EQMMantenimiento.iCveEquipo = "
							+ request.getParameter("hdCampoClave");
				else {
					lWhere = true;
					cCondicion = " WHERE EQMMantenimiento.iCveEquipo = "
							+ request.getParameter("hdCampoClave");
				}
			}

			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY EQMMantenimiento.iCveEquipo, EQMMantenimiento.iCveMantenimiento  ";
			vDespliega = dEQMMantenimiento.FindByAll(cCondicion, cOrden);

			// Llamado para generar reporte de solicitud de servicio
			if (request.getParameter("hdBoton").compareToIgnoreCase(
					"Generar Reporte") == 0) {
				activeX.append(this.Report());
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(request.getParameter("hdCampoClave"));
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
		TVEQMMantenimiento vEQMMantenimiento = new TVEQMMantenimiento();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setICveEquipo(iCampo);

			cCampo = "" + request.getParameter("iCveMantenimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setICveMantenimiento(iCampo);

			cCampo = "" + request.getParameter("dtSolicitud");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				dtCampo = tfCampo.getDateSQL(cCampo);
			else
				dtCampo = null;
			vEQMMantenimiento.setDtSolicitud(dtCampo);

			cCampo = "" + request.getParameter("dtProgramado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				dtCampo = tfCampo.getDateSQL(cCampo);
			else
				dtCampo = null;
			vEQMMantenimiento.setDtProgramado(dtCampo);

			cCampo = "" + request.getParameter("iCveTpoMantto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setICveTpoMantto(iCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setICveMotivo(iCampo);

			cCampo = "" + request.getParameter("cAccesorios");
			if (cCampo.compareTo("null") == 0)
				cCampo = "";
			vEQMMantenimiento.setCAccesorios(cCampo);

			cCampo = "" + request.getParameter("cAnalisisOper");
			if (cCampo.compareTo("null") == 0)
				cCampo = "";
			vEQMMantenimiento.setCAnalisisOper(cCampo);

			cCampo = "" + request.getParameter("iCveEmpMtto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setICveEmpMtto(iCampo);

			cCampo = "" + request.getParameter("cNombre");
			if (cCampo.compareTo("null") == 0)
				cCampo = "";
			vEQMMantenimiento.setCNombre(cCampo);

			cCampo = "" + request.getParameter("cResultado");
			if (cCampo.compareTo("null") == 0)
				cCampo = "";
			vEQMMantenimiento.setCResultado(cCampo);

			cCampo = "" + request.getParameter("cObservaciones");
			if (cCampo.compareTo("null") == 0)
				cCampo = "";
			vEQMMantenimiento.setCObservaciones(cCampo);

			cCampo = "" + request.getParameter("lConcluido");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setLConcluido(iCampo);

			cCampo = "" + request.getParameter("dtRecepcion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				dtCampo = tfCampo.getDateSQL(cCampo);
			else
				dtCampo = null;
			vEQMMantenimiento.setDtRecepcion(dtCampo);

			cCampo = "" + request.getParameter("Usuario");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setICveUsuSolicita(iCampo);

			cCampo = "" + request.getParameter("iCveUsuAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setICveUsuAutoriza(iCampo);

			cCampo = "" + request.getParameter("iCveUsuRecibe");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setICveUsuRecibe(iCampo);

			cCampo = "" + request.getParameter("lCancelado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vEQMMantenimiento.setLCancelado(iCampo);

			cCampo = "" + request.getParameter("Today"); // La Fecha de
															// Cancelaci�n
															// debe ser el TODAY
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0)
				dtCampo = tfCampo.getDateSQL(cCampo);
			else
				dtCampo = null;
			vEQMMantenimiento.setDtCancelacion(dtCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEQMMantenimiento;
	}
}
