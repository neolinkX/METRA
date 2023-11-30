package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para CFG pagina pg070801030
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801030CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801030CFG.png'>
 */
public class pg070801030CFG extends CFGListBase2 {
	public pg070801030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801031.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDALMAlmacen dALMAlmacen = new TDALMAlmacen();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("") != 0
					&& Integer.parseInt(request.getParameter("iCveUniMed")
							.toString()) > 0)
				cWhere += " where ALMAlmacen.iCveUniMed = "
						+ request.getParameter("iCveUniMed");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("") != 0
					&& Integer.parseInt(request.getParameter("iCveUniMed")
							.toString()) > 0)
				vDespliega = dALMAlmacen.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}