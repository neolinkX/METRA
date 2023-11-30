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
 * * Clase de configuracion para Clase de configuracion de la p�gina de
 * ubicaci�n de veh�culos
 * <p>
 * AM�dulo de Control de Veh�culos.
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
public class pg070702013CFG extends CFGListBase2 {
	/** Clave del veh�culo a buscar datos generales y ubicacines. */
	private int iCveVehiculo = 0;
	/** Filtro que se incluir� en la b�squeda de ubicaciones. */
	private String cFiltro = "";
	/** Filtro que se incluir� en la b�squeda de datos de veh�culo. */
	private String cFiltroVeh = "";
	/** Vector que contendr� en la primer posici�n los datos del veh�culo. */
	private Vector vVehiculo;

	/**
	 * Constructor por omisi�n.
	 */
	public pg070702013CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		super.NavStatus = "Hidden";
		cPaginas = "pg070702010.jsp|pg070702011.jsp|";
	}

	/**
	 * Metodo FillVector, realiza b�squeda de datos del veh�culo y sus
	 * diferentes ubicaciones.
	 */
	public void fillVector() {
		TDVEHMantenimiento dVEHMantto = new TDVEHMantenimiento();
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
				cFiltro = " WHERE M.iCveVehiculo = " + iCveVehiculo;
				cFiltroVeh = " WHERE VEHVehiculo.iCveVehiculo = "
						+ iCveVehiculo;
				if (!cCondicion.equals(""))
					cFiltro += " AND " + cCondicion;
				vVehiculo = dVEHVehiculo.FindByAll(cFiltroVeh, "");
				vDespliega = dVEHMantto.FindByAll(cFiltro,
						"ORDER BY M.iCveVehiculo, M.iCveMantenimiento DESC ");
			}
			if (vDespliega == null || vDespliega.size() == 0)
				vDespliega = new Vector();
		} catch (Exception ex) {
			super.vErrores.acumulaError(
					"No se encontr� la informaci�n solicitada", 0, "");
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo encargado de devolver el valor del vector con los datos del
	 * veh�culo.
	 * 
	 * @return Vector cuyo primer elemento tiene los datos del veh�culo.
	 */
	public Vector getVVehiculo() {
		if (vVehiculo == null || vVehiculo.size() == 0)
			vVehiculo = new Vector();
		return vVehiculo;
	}
}