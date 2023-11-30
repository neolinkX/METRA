package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Cetegorias
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071004020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071004020CFG.png'>
 */
public class pg071004020CFG extends CFGListBase2 {
	public pg071004020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071004021.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLCategoria dGRLCategoria = new TDGRLCategoria();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveMdoTrans") != null
					&& request.getParameter("iCveMdoTrans").compareTo("") != 0)
				cWhere += " where iCveMdoTrans = "
						+ request.getParameter("iCveMdoTrans");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy += cOrden;
			}

			if (request.getParameter("iCveMdoTrans") != null
					&& request.getParameter("iCveMdoTrans").compareTo("") != 0
					&& request.getParameter("iCveMdoTrans").compareTo("null") != 0
					&& Integer.parseInt(request.getParameter("iCveMdoTrans")) > 0)
				vDespliega = dGRLCategoria.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

}