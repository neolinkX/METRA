package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Control de Veh�culos - Listado de Controles
 * por Etapa
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070701090CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070701090CFG.png'>
 */
public class pg070701092CFG extends CFGListBase2 {
	public pg070701092CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070701093.jsp|pg070701090.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDVEHConfControl dVEHConfControl = new TDVEHConfControl();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveEtapaSolic") != null
					&& request.getParameter("iCveEtapaSolic").compareTo("") != 0)
				cWhere += " where VEHConfControl.iCveEtapaSolic = "
						+ request.getParameter("iCveEtapaSolic");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by iCveConfControl ";

			if (request.getParameter("iCveEtapaSolic") != null
					&& request.getParameter("iCveEtapaSolic").compareTo("") != 0
					&& request.getParameter("iCveEtapaSolic").compareTo("null") != 0
					&& Integer.parseInt(request.getParameter("iCveEtapaSolic")) > 0)
				vDespliega = dVEHConfControl.FindDsc(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}