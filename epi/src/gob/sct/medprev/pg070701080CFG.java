package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Control de Veh�culos - Listado de Empresas de
 * Mantenimiento
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070701080.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070701080.png'>
 */
public class pg070701080CFG extends CFGListBase2 {
	public pg070701080CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070701081.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDVEHEmpMantto dVEHEmpMantto = new TDVEHEmpMantto();
		try {
			String cWhere = "";
			String cOrderBy = "";
			if (cCondicion.compareTo("") != 0) {
				cWhere = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			vDespliega = dVEHEmpMantto.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}