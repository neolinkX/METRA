package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para Control de Vehículos - Listado de Etapas por
 * Proceso
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071005030CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071005030CFG.png'>
 */
public class pg071005030CFG extends CFGListBase2 {
	public pg071005030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071005031.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDGRLEtapa dGRLEtapa = new TDGRLEtapa();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveProceso") != null
					&& request.getParameter("iCveProceso").compareTo("") != 0)
				cWhere += " where iCveProceso = "
						+ request.getParameter("iCveProceso");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by iCveProceso";

			if (request.getParameter("iCveProceso") != null
					&& request.getParameter("iCveProceso").compareTo("") != 0
					&& request.getParameter("iCveProceso").compareTo("null") != 0
					&& Integer.parseInt(request.getParameter("iCveProceso")) > 0)
				vDespliega = dGRLEtapa.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}