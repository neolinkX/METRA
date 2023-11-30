package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.excepciones.*;
import com.micper.util.*;

/**
 * * Clase de configuración para CFG Seguimiento del Mantenimiento
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070602010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070602010CFG.png'>
 */
public class pg070702042CFG extends CFGListBase2 {
	public TVVEHVehiculo tvVehiculo = null;
	public Vector vSolicitantes = new Vector();
	public Vector vEmpMantto = new Vector();
	public Vector vEtapas = new Vector();
	public Vector vUsuarios = new Vector();
	public boolean lRegNuevo = true;

	public pg070702042CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cOrden = "";
		cCondicion = "";
		cPaginas = "pg070702040.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDVEHSeguimiento dSeg = new TDVEHSeguimiento();

		int iCveMantenimiento = 0;
		int iCveVehiculo = 0;
		int iCveProceso = 0;
		int iCveEtapa = 0;
		int iCveSolicitante = 0;
		int iCveUsuReg = 0;
		TFechas hoy = new TFechas();
		try {
			if (request.getParameter("iCveMantenimiento") != null
					&& !request.getParameter("iCveMantenimiento").equals("")
					&& request.getParameter("iCveVehiculo") != null
					&& !request.getParameter("iCveVehiculo").equals("")) {
				dSeg.insert(null, this.getInputs());
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al Actualizar el registro", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDVEHVehiculo dVehiculo = new TDVEHVehiculo();
		TDVEHSeguimiento dSeg = new TDVEHSeguimiento();
		TDGRLSolicitante dSol = new TDGRLSolicitante();
		TDVEHMantenimiento dMantto = new TDVEHMantenimiento();
		TDGRLEtapa dEtapa = new TDGRLEtapa();
		Vector vVeh = new Vector();
		String iCveVehiculo = "";
		String iCveMantenimiento = "";
		String sWhere = "";

		if (request.getParameter("iCveVehiculo") != null
				&& !request.getParameter("iCveVehiculo").equals(""))
			iCveVehiculo = request.getParameter("iCveVehiculo").toString();
		if (request.getParameter("iCveMantenimiento") != null
				&& !request.getParameter("iCveMantenimiento").equals(""))
			iCveMantenimiento = request.getParameter("iCveMantenimiento")
					.toString();

		try {
			if (!iCveVehiculo.equals("") && !iCveMantenimiento.equals("")) {
				vVeh = dVehiculo
						.FindByAllDetalleM(" Where VehVehiculo.iCveVehiculo="
								+ iCveVehiculo);
				vSolicitantes = dSol.FindByAll(" Where lActivo = 1 ");
				vEtapas = dEtapa.FindByAll(" Where lActivo=1 and iCveProceso="
						+ vParametros.getPropEspecifica("CtrlVeh"));
				vUsuarios = dMantto
						.FindByAllUsuarios(" Where GrlUmUsuario.iCveProceso="
								+ vParametros.getPropEspecifica("CtrlVeh"));
				if (vVeh.size() > 0) {
					tvVehiculo = new TVVEHVehiculo();
					tvVehiculo = (TVVEHVehiculo) vVeh.get(0);
				}
				sWhere = " Where VehSeguimiento.iCveVehiculo=" + iCveVehiculo
						+ " and VehSeguimiento.iCveMantenimiento="
						+ iCveMantenimiento;

				if (cOrden.equals(" order by VehMantenimiento.dtProgramado")
						|| cOrden.equals(" order by VehVehiculo.cPlacas")
						|| cOrden.equals("")) {
					cOrden = " order by VehSeguimiento.dtSolicitud ";
				}
				vDespliega = dSeg.FindByAllSegxVeh(sWhere + cOrden);
				if (vDespliega != null && vDespliega.size() > 0) {
					if (((TVVEHSeguimiento) vDespliega.get(0)).getLConcluido() == 1
							|| ((TVVEHSeguimiento) vDespliega.get(0))
									.getLCancelado() == 1) {
						this.UpdStatus = "Hidden";
						this.lRegNuevo = false;
					}
				}
				if (vDespliega.size() > iNumReg) {
					NavStatus = "FirstRecord";
				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
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
		TVVEHSeguimiento vSeg = new TVVEHSeguimiento();
		try {
			cCampo = "" + request.getParameter("iCveMantenimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSeg.setICveMantenimiento(iCampo);

			cCampo = "" + request.getParameter("iCveVehiculo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSeg.setICveVehiculo(iCampo);

			cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSeg.setICveProceso(iCampo);

			cCampo = "" + request.getParameter("dtSolicitud");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vSeg.setDtSolicitud(dtCampo);

			cCampo = "" + request.getParameter("iCveEtapa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSeg.setICveEtapa(iCampo);

			cCampo = "" + request.getParameter("iCveSolicitante");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSeg.setICveSolicitante(iCampo);

			cCampo = "" + request.getParameter("cSolicitante");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vSeg.setCSolicitante(cCampo);

			cCampo = "" + request.getParameter("iCveUsuReg");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSeg.setICveUsuReg(iCampo);

			cCampo = "" + request.getParameter("iCveUsuSolicita");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSeg.setICveUsuSolicita(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vSeg.setCObservacion(cCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vSeg;
	}
}
