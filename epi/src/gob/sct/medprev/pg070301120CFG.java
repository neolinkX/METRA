package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG Listado de las Sustancias
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301120CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301120CFG.png'>
 */
public class pg070301120CFG extends CFGListBase2 {
	public pg070301120CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		NavStatus = "Disabled";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		try {
			TDSustancia dSustancia = new TDSustancia();
			if (this.getLPagina("pg070301121.jsp")) {
				cPaginas = "pg070301121.jsp|";

			}
			if (this.getLPagina("pg070301220.jsp")) {
				if (cPaginas.compareTo("") != 0) {
					cPaginas += "pg070301220.jsp|";
				} else {
					cPaginas = "pg070301220.jsp|";
				}
			}

			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
			}
			if (cOrden.compareTo("") == 0) {
				cOrden = " ORDER BY iCveSustancia";
			}
			String cFiltro = cCondicion + "  " + cOrden;
			vDespliega = dSustancia.FindByAll(cFiltro);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}