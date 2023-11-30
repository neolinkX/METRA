package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG de la página pg070101020
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101020CFG.png'>
 */
public class pg070101020CFG extends CFGListBase2 {
	public pg070101020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070101021.jsp|";
		NavStatus = "Disabled";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDMEDDiagnostico dMEDDiagnostico = new TDMEDDiagnostico();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where MEDDiagnostico.iCveEspecialidad = "
						+ request.getParameter("hdCampoClave1");
			else if (request.getParameter("iCveEspecialidad") != null
					&& request.getParameter("iCveEspecialidad").compareTo("") != 0)
				cWhere += " where MEDDiagnostico.iCveEspecialidad = "
						+ request.getParameter("iCveEspecialidad");
			else if (request.getParameter("hdEspecialidad") != null
					&& request.getParameter("hdEspecialidad").compareTo("") != 0)
				cWhere += " where MEDDiagnostico.iCveEspecialidad = "
						+ request.getParameter("hdEspecialidad");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by cCIE";

			if ((request.getParameter("iCveEspecialidad") != null && request
					.getParameter("iCveEspecialidad").compareTo("") != 0)
					|| (request.getParameter("hdEspecialidad") != null && request
							.getParameter("hdEspecialidad").compareTo("") != 0)
					|| (request.getParameter("hdCampoClave1") != null)
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				vDespliega = dMEDDiagnostico.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}