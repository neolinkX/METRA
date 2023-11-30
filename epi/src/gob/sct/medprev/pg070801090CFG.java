package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para CFG pagina pg070801090
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801090CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801090CFG.png'>
 */
public class pg070801090CFG extends CFGListBase2 {
	public pg070801090CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801091.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDALMPeriodo dALMPeriodo = new TDALMPeriodo();
		TFechas dtFecha = new TFechas();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iAnio") != null)
				cWhere += " where iAnio = " + request.getParameter("iAnio");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}

			if (request.getParameter("iAnio") != null)
				vDespliega = dALMPeriodo.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}