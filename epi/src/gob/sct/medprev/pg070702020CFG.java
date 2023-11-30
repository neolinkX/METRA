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
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;
import com.micper.util.*;

/**
 * * Clase de configuración para CFG Consulta de Mantenimientos
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
public class pg070702020CFG extends CFGListBase2 {
	public Vector vEquipo = new Vector();
	public Vector vServxEq = new Vector();
	public Vector vServxUniMed = new Vector();
	public TVEQMAsignacion tvAsignacion = new TVEQMAsignacion();

	public pg070702020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cOrden = "";
		cCondicion = "";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDVEHUbicacion dHub = new TDVEHUbicacion();
		try {
			int num_veh = 0;
			Vector vHub = new Vector();
			TFechas hoy = new TFechas();
			TVVEHUbicacion tvHub;
			if (request.getParameter("num_veh") != null
					&& !request.getParameter("num_veh").equals(""))
				num_veh = Integer.parseInt(request.getParameter("num_veh")
						.toString());

			for (int i = 0; i < num_veh; i++) {
				tvHub = new TVVEHUbicacion();
				String iCveVehiculo = request.getParameter("iCveExiste" + i);
				String accion = request.getParameter("accion");
				String ubicacion = request.getParameter("iCveUbicacion" + i) == null ? ""
						: request.getParameter("iCveUbicacion" + i);
				String iCveAsignar = request.getParameter("iCveAsignar" + i) == null ? ""
						: request.getParameter("iCveAsignar" + i);
				String iCveUniMed = request.getParameter("iCveUniMed");

				tvHub.setICveVehiculo(Integer.parseInt(iCveVehiculo));
				// Se usara accion para saber si es insertar o update D =
				// Insertar, A = Update
				if (accion.equals("D") && !iCveAsignar.equals("")
						&& !iCveUniMed.equals("")) {
					tvHub.setICveUniMed(Integer.parseInt(iCveUniMed));
					tvHub.setDtAsignacion(hoy.TodaySQL());
					tvHub.setLActivo(1);
					dHub.insertWithSequence(null, tvHub);
				} else if (accion.equals("A") && iCveAsignar.equals("")
						&& !ubicacion.equals("")) {
					tvHub.setLActivo(0);
					tvHub.setICveUbicacion(Integer.parseInt(ubicacion));
					tvHub.setDtDesasigna(hoy.TodaySQL());
					dHub.updateUbicacion(null, tvHub);
				}
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDVEHUbicacion dVehUBic = new TDVEHUbicacion();
		String iCveUniMed = "";
		String RSTVehic = "D";
		String sWhere = "";

		if (request.getParameter("iCveUniMed") != null
				&& !request.getParameter("iCveUniMed").equals(""))
			iCveUniMed = request.getParameter("iCveUniMed").toString();
		if (request.getParameter("RSTVehic") != null
				&& !request.getParameter("RSTVehic").equals(""))
			RSTVehic = request.getParameter("RSTVehic").toString();

		try {
			if (!RSTVehic.equals("")) {
				if (RSTVehic.equals("D")) {
					sWhere = " and VehUbicacion.iCveUbicacion is null";
				} else if (RSTVehic.equals("A") && !iCveUniMed.equals("")) {
					sWhere = " and VehUbicacion.iCveUbicacion is not null ";
					sWhere = sWhere + "and VehUbicacion.iCveUniMed="
							+ iCveUniMed;
				}
				if (!sWhere.equals("") && !cCondicion.equals("")) {
					sWhere = sWhere + " and " + cCondicion;
				}
				if (cOrden.equals(""))
					cOrden = "order by VehVehiculo.cNumSerie";
				sWhere = sWhere + " " + cOrden;
			}
			if (!sWhere.equals(""))
				vDespliega = dVehUBic.FindByAllUbicacion(sWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}