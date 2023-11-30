package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Clase para listar la informaci�n de la tabla
 * VEHVehiculo
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
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070702010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070702010CFG.png'>
 */
public class pg070702010CFG extends CFGListBase2 {
	public pg070702010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070702011.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
		boolean lWhere = false;
		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}

			if (request.getParameter("iCveTpoVehiculo") != null
					&& request.getParameter("iCveTpoVehiculo").compareTo("") != 0
					&& request.getParameter("iCveTpoVehiculo").compareTo("-1") != 0
					&& request.getParameter("iCveTpoVehiculo").compareTo("0") != 0) {
				if (lWhere)
					cCondicion += " AND VEHVehiculo.iCveTpoVehiculo = "
							+ request.getParameter("iCveTpoVehiculo");
				else {
					cCondicion = " WHERE VEHVehiculo.iCveTpoVehiculo = "
							+ request.getParameter("iCveTpoVehiculo");
					lWhere = true;
				}
			}

			if (request.getParameter("iCveMarca") != null
					&& request.getParameter("iCveMarca").compareTo("") != 0
					&& request.getParameter("iCveMarca").compareTo("-1") != 0
					&& request.getParameter("iCveMarca").compareTo("0") != 0) {
				if (lWhere)
					cCondicion += " AND VEHVehiculo.iCveMarca = "
							+ request.getParameter("iCveMarca");
				else {
					cCondicion = " WHERE VEHVehiculo.iCveMarca = "
							+ request.getParameter("iCveMarca");
					lWhere = true;
				}
			}

			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY VEHVehiculo.iCveVehiculo ";
			vDespliega = dVEHVehiculo.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
