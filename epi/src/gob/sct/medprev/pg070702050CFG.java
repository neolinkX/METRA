package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Clase para el listado de los Equipos
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
 *      "JavaScript:alert('Consulte los archivos:\n-pg070702050CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070702050CFG.java.png'>
 */
public class pg070702050CFG extends CFGListBase2 {
	public pg070702050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDVEHUbicacion dVEHUbicacion = new TDVEHUbicacion();
		try {
			if (cCondicion.compareTo("") != 0)
				cCondicion = " WHERE " + cCondicion
						+ " AND VEHUbicacion.lActivo = 1";
			else
				cCondicion = " WHERE VEHUbicacion.lActivo = 1 ";

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("null") != 0
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("-1") != 0
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("0") != 0) {
				cCondicion += " AND VEHUbicacion.iCveUniMed = "
						+ request.getParameter("iCveUniMed");
			}

			if (request.getParameter("cPlacas") != null
					&& request.getParameter("cPlacas").toString()
							.compareTo("null") != 0
					&& request.getParameter("cPlacas").toString().compareTo("") != 0) {
				cCondicion += " AND VEHVehiculo.cPlacas = '"
						+ request.getParameter("cPlacas") + "' ";
			}

			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY VEHUbicacion.iCveVehiculo ";
			vDespliega = dVEHUbicacion.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
