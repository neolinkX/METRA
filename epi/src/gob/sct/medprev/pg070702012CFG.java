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

/**
 * * Clase de configuración para Clase de configuración de la página de
 * ubicación de vehículos
 * <p>
 * AMódulo de Control de Vehículos.
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070702012.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070702012.png'>
 */
public class pg070702012CFG extends CFGListBase2 {
	/** Clave del vehículo a buscar datos generales y ubicacines. */
	private int iCveVehiculo = 0;
	/** Filtro que se incluirá en la búsqueda de ubicaciones. */
	private String cFiltro = "";
	/** Filtro que se incluirá en la búsqueda de datos de vehículo. */
	private String cFiltroVeh = "";
	/** Vector que contendrá en la primer posición los datos del vehículo. */
	private Vector vVehiculo;

	/**
	 * Constructor por omisión.
	 */
	public pg070702012CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		super.NavStatus = "Hidden";
		cPaginas = "pg070702010.jsp|pg070702011.jsp|";
	}

	/**
	 * Método FillVector, realiza búsqueda de datos del vehículo y sus
	 * diferentes ubicaciones.
	 */
	public void fillVector() {
		TDVEHUbicacion dVEHUbicacion = new TDVEHUbicacion();
		TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
		try {
			if (request.getParameter("hdVehiculo") != null) {
				if (!request.getParameter("hdVehiculo").equals(""))
					iCveVehiculo = Integer.parseInt(request
							.getParameter("hdVehiculo"));
			} else {
				if (request.getParameter("hdCampoClave") != null)
					if (!request.getParameter("hdCampoClave").equals(""))
						iCveVehiculo = Integer.parseInt(request
								.getParameter("hdCampoClave"));
			}
			if (iCveVehiculo != 0) {
				cActual = cClave = String.valueOf(iCveVehiculo);
				iNumReg = iCveVehiculo;
				cFiltro = " WHERE VEHUbicacion.iCveVehiculo = " + iCveVehiculo;
				cFiltroVeh = " WHERE VEHVehiculo.iCveVehiculo = "
						+ iCveVehiculo;
				if (!cCondicion.equals(""))
					cFiltro += " AND " + cCondicion;
				vVehiculo = dVEHVehiculo.FindByAll(cFiltroVeh, "");
				vDespliega = dVEHUbicacion
						.FindByAll(cFiltro,
								"ORDER BY VEHUbicacion.iCveVehiculo, VEHUbicacion.iCveUbicacion DESC ");
			}
			if (vDespliega == null || vDespliega.size() == 0)
				vDespliega = new Vector();
		} catch (Exception ex) {
			super.vErrores.acumulaError(
					"No se encontró la información solicitada", 0, "");
			error("FillVector", ex);
		}
	}

	/**
	 * Método encargado de devolver el valor del vector con los datos del
	 * vehículo.
	 * 
	 * @return Vector cuyo primer elemento tiene los datos del vehículo.
	 */
	public Vector getVVehiculo() {
		if (vVehiculo == null || vVehiculo.size() == 0)
			vVehiculo = new Vector();
		return vVehiculo;
	}
}