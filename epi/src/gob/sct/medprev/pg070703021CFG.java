package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.TFechas;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;

/**
 * * Clase de configuración para Clase para listar la información de la tabla
 * VEHVehiculo
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hernández García
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070703021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070703021CFG.png'>
 */
public class pg070703021CFG extends CFGListBase2 {
	TVVEHSolicitud vVEHSolicitud = new TVVEHSolicitud();
	TDVEHSolicitud dVEHSolicitud = new TDVEHSolicitud();
	Vector vAlarmas = new Vector();
	int iVehiculo = 0;
	int iTipo = 0;
	int iMarca = 0;
	int iModelo = 0;

	public pg070703021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070703020.jsp|";
	}

	public void Validar() {
		try {
			int iCveVehiculo = new Integer(request.getParameter("RadioVEH"))
					.intValue();
			TDVEHMantenimiento dVEHMantenimiento = new TDVEHMantenimiento();
			Vector vTemp = new Vector();
			String cFiltro = "";
			cFiltro = " WHERE M.iCveVehiculo = " + iCveVehiculo
					+ "   AND M.lConcluido = 0 " + "   AND M.lCancelado = 0 ";
			vTemp = dVEHMantenimiento.FindByAll(cFiltro, "");
			if (vTemp.size() > 0) {
				vAlarmas.add("- El Vehiculo tiene Mantenimientos programados sin Cancelar y sin Concluir");
			}
		} catch (Exception e) {
			error("Validar", e);
		}
	}

	public void Solicitud() {
		try {
			Vector vSolicitud = new Vector();
			String cFiltro = " WHERE VEHSolicitud.iAnio = "
					+ request.getParameter("iAnio")
					+ "   AND VEHSolicitud.iCveUniMed = "
					+ request.getParameter("iCveUniMed")
					+ "   AND VEHSolicitud.iCveSolicitud = "
					+ request.getParameter("iCveSolicitud");
			// String cOrden = "";
			vSolicitud = dVEHSolicitud.FindSolicVig(cFiltro, cOrden);
			if (vSolicitud.size() > 0) {
				vVEHSolicitud = (TVVEHSolicitud) vSolicitud.get(0);
				iTipo = vVEHSolicitud.getICveTpoVehiculoVEH();
				iMarca = vVEHSolicitud.getICveMarca();
				iModelo = vVEHSolicitud.getICveModelo();
				iVehiculo = vVEHSolicitud.getICveVehiculo();
			}
		} catch (Exception e) {
			error("Solicitud", e);
		}
	}

	public Object getSolicitud() {
		return vVEHSolicitud;
	}

	public Object getAlarmas() {
		return vAlarmas;
	}

	public void Desplegar() {
		TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
		try {
			if (request.getParameter("iCveTpoVehiculo") != null
					&& request.getParameter("iCveTpoVehiculo").compareTo("") != 0)
				iTipo = new Integer(request.getParameter("iCveTpoVehiculo")
						.toString()).intValue();

			if (iTipo > 0)
				cCondicion += " AND VEHVehiculo.iCveTpoVehiculo = " + iTipo;

			if (request.getParameter("iCveMarca") != null
					&& request.getParameter("iCveMarca").compareTo("") != 0)
				iMarca = new Integer(request.getParameter("iCveMarca")
						.toString()).intValue();

			if (iMarca > 0)
				cCondicion += " AND VEHVehiculo.iCveMarca = " + iMarca;

			if (request.getParameter("iCveModelo") != null
					&& request.getParameter("iCveModelo").compareTo("") != 0)
				iModelo = new Integer(request.getParameter("iCveModelo")
						.toString()).intValue();

			if (iModelo > 0)
				cCondicion += " AND VEHVehiculo.iCveModelo = " + iModelo;
			vDespliega = dVEHVehiculo.FindByAll(cCondicion, cOrden);
		} catch (Exception e) {
			error("Desplegar", e);
		}
	}

	public void Actualizar() {
		try {
			TFechas tFecha = new TFechas();
			TDVEHSolicitud dVEHSolicitud = new TDVEHSolicitud();
			TVVEHSolicitud vVEHSolicitud = new TVVEHSolicitud();
			vVEHSolicitud.setIAnio(new Integer(request.getParameter("iAnio"))
					.intValue());
			vVEHSolicitud.setICveUniMed(new Integer(request
					.getParameter("iCveUniMed")).intValue());
			vVEHSolicitud.setICveSolicitud(new Integer(request
					.getParameter("iCveSolicitud")).intValue());
			vVEHSolicitud.setICveVehiculo(new Integer(request
					.getParameter("RadioVEH")).intValue());
			vVEHSolicitud.setICveTpoVehiculo(new Integer(request
					.getParameter("iCveTpoVehiculo"
							+ request.getParameter("RadioVEH"))).intValue());
			vVEHSolicitud.setICveUsuAsigna(new Integer(request
					.getParameter("iCveUsuSesion")).intValue());
			vVEHSolicitud.setLAsignado(1);
			vVEHSolicitud.setDtAsignado(tFecha.getDateSQL(request
					.getParameter("cToday")));
			dVEHSolicitud.updateVEH(null, vVEHSolicitud);
		} catch (Exception ex) {
			error("Actualizar", ex);
		}
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		try {
			String cVEHUso = "";
			if (cAccion.equals(""))
				cAccion = "Primero";
			cVEHUso = vParametros.getPropEspecifica("VEHEnUso");
			if (cCondicion.compareTo("") != 0)
				cCondicion = " WHERE "
						+ cCondicion
						+ " AND VEHVehiculo.lBaja = 0 AND VEHVehiculo.iCveEstadoVeh IN ("
						+ cVEHUso + ")";
			else
				cCondicion = " WHERE VEHVehiculo.lBaja = 0 AND VEHVehiculo.iCveEstadoVeh IN ("
						+ cVEHUso + ")";
			if (request.getParameter("SLSOrden") == null
					|| request.getParameter("SLSOrden").compareTo(
							"VEHSolicitud.tsSolicitud") == 0
					|| request.getParameter("SLSOrden").compareTo(
							"SEGUsuario.cNombre") == 0
					|| request.getParameter("SLSOrden").compareTo(
							"VEHTpoVehiculo.cDscBreve") == 0)
				cOrden = " ORDER BY VEHVehiculo.iCveVehiculo ";
			this.Solicitud();
			this.Desplegar();
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").toString()
							.compareTo("Validar") == 0) {
				this.Validar();
				this.Desplegar();
			} else if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").toString()
							.compareTo("Guardar") == 0) {
				this.Actualizar();
				this.Solicitud();
				this.Desplegar();
			}
			if (cAccion.compareToIgnoreCase("Validar") == 0) {
				cAccion = "Guardar";
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

}
