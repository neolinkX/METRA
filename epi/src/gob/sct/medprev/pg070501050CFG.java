package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para Listado de Servicio Prestado
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070501050CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070501050CFG.png'>
 */
public class pg070501050CFG extends CFGListBase2 {
	public pg070501050CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070501051.jsp|";
		NavStatus = "Disabled";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDCTRServPrestado dCTRServPrestado = new TDCTRServPrestado();
		try {
			String cWhere = "";
			String cOrderBy = "";
			if (cCondicion.compareTo("") != 0) {
				cWhere = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else
				cOrderBy = "order by iCveServPrestado";
			vDespliega = dCTRServPrestado.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}