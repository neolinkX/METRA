package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Calibraci�n de Equipo - Listado de
 * Clasificaci�n
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070601010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070601010CFG.png'>
 */
public class pg070601010CFG extends CFGListBase2 {
	public pg070601010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070601011.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMClasificacion dEQMClasificacion = new TDEQMClasificacion();
		try {
			String cWhere = "";
			String cOrderBy = "";
			if (cCondicion.compareTo("") != 0) {
				cWhere = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			vDespliega = dEQMClasificacion.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}