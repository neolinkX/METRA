package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Calibraci�n de Equipo - Listado de Tipo de
 * Equipo
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070601020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070601020CFG.png'>
 */
public class pg070601020CFG extends CFGListBase2 {
	public pg070601020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070601021.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMTpoEquipo dEQMTpoEquipo = new TDEQMTpoEquipo();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveClasificacion") != null
					&& request.getParameter("iCveClasificacion").compareTo("") != 0)
				cWhere += " where iCveClasificacion = "
						+ request.getParameter("iCveClasificacion");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}

			if (request.getParameter("iCveClasificacion") != null)
				vDespliega = dEQMTpoEquipo.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}