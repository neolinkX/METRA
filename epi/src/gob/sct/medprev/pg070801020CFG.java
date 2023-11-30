package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Listado de Familia
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801020CFG.png'>
 */
public class pg070801020CFG extends CFGListBase2 {
	public pg070801020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801021.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDALMFamilia dALMFamilia = new TDALMFamilia();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveTpoArticulo") != null
					&& request.getParameter("iCveTpoArticulo").compareTo("") != 0)
				cWhere += " where F.iCveTpoArticulo = "
						+ request.getParameter("iCveTpoArticulo");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}

			if (request.getParameter("iCveTpoArticulo") != null
					&& request.getParameter("iCveTpoArticulo").compareTo("") != 0
					&& request.getParameter("iCveTpoArticulo")
							.compareTo("null") != 0
					&& Integer
							.parseInt(request.getParameter("iCveTpoArticulo")) > 0)
				vDespliega = dALMFamilia.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}