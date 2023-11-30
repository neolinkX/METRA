package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Control de Almacen - Listado de Concepto
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801070CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801070CFG.png'>
 */
public class pg070801070CFG extends CFGListBase2 {
	public pg070801070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801071.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDALMConcepto dALMConcepto = new TDALMConcepto();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveTpoMovimiento") != null
					&& request.getParameter("iCveTpoMovimiento").compareTo("") != 0)
				cWhere += " where C.iCveTpoMovimiento = "
						+ request.getParameter("iCveTpoMovimiento");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}

			if (request.getParameter("iCveTpoMovimiento") != null
					&& request.getParameter("iCveTpoMovimiento").compareTo("") != 0
					&& request.getParameter("iCveTpoMovimiento").compareTo(
							"null") != 0
					&& Integer.parseInt(request
							.getParameter("iCveTpoMovimiento")) > 0)
				vDespliega = dALMConcepto.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}