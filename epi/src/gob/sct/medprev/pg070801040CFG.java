package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG pagina pg070801040
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801040CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801040CFG.png'>
 */
public class pg070801040CFG extends CFGListBase2 {
	public pg070801040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801041.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDALMArea dALMArea = new TDALMArea();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveAlmacen") != null
					&& request.getParameter("iCveAlmacen").compareTo("") != 0)
				cWhere += " where ALMArea.iCveAlmacen = "
						+ request.getParameter("iCveAlmacen");
			else if (request.getParameter("hdAlmacen") != null
					&& request.getParameter("hdAlmacen").compareTo("") != 0)
				cWhere += " where ALMArea.iCveAlmacen = "
						+ request.getParameter("hdAlmacen");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else
				cOrderBy = " ORDER BY iCveArea ";

			if (request.getParameter("iCveAlmacen") != null
					|| request.getParameter("hdAlmacen") != null)
				vDespliega = dALMArea.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}