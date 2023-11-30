package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para DFG para listado de la tabla TOXMotivoRecep
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301030.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301030.png'>
 */
public class pg070301030CFG extends CFGListBase2 {
	public pg070301030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		NavStatus = "Disabled";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDMotivoRecep dMotivoRecep = new TDMotivoRecep();
		if (this.getLPagina("pg070301031.jsp"))
			cPaginas = "pg070301031.jsp|";

		try {
			String cFiltro = "";
			if (cCondicion.compareTo("") != 0) {
				cFiltro = cFiltro + " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cFiltro = cFiltro + cOrden;
			}
			vDespliega = dMotivoRecep.FindByAll(cFiltro);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}