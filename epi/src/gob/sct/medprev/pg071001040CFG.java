package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para Generales - Listado de Nacionals
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071001040CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071001040CFG.png'>
 */
public class pg071001040CFG extends CFGListBase2 {
	public pg071001040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071001041.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDGRLNacionalidad dGRLNacionalidad = new TDGRLNacionalidad();
		try {
			String cWhere = "";
			if (cCondicion.compareTo("") != 0)
				cWhere = " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else
				cWhere += " order by iCvePais ";

			vDespliega = dGRLNacionalidad.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}