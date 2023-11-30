package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG pagina pg070801050
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801050CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801050CFG.png'>
 */
public class pg070801050CFG extends CFGListBase2 {
	public pg070801050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801051.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDALMSeccion dALMSeccion = new TDALMSeccion();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveAlmacen") != null
					&& request.getParameter("iCveArea") != null) {

				if (request.getParameter("iCveAlmacen").compareTo("") != 0)
					cWhere += " where ALMSeccion.iCveAlmacen = "
							+ request.getParameter("iCveAlmacen");

				if (request.getParameter("iCveArea").compareTo("") != 0) {
					if (cWhere.compareTo("") != 0)
						cWhere += " and ALMSeccion.iCveArea = "
								+ request.getParameter("iCveArea");
					else
						cWhere += " where ALMSeccion.iCveArea = "
								+ request.getParameter("iCveArea");
				}

				if (cCondicion.compareTo("") != 0) {
					if (cWhere.compareTo("") != 0)
						cWhere += " and " + cCondicion;
					else
						cWhere += cCondicion;
				}
			} else
				cWhere += " where 1=0 ";

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else
				cOrderBy = " order by cDscSeccion ";

			vDespliega = dALMSeccion.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
