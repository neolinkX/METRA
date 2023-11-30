package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuraci�n para Listado de Origen de la Infromaci�n
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070501020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070501020CFG.png'>
 */
public class pg070501020CFG extends CFGListBase2 {
	public pg070501020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070501021.jsp|";
		NavStatus = "Disabled";
	}

	/**
	 * M�todo FillVector
	 */
	public void fillVector() {
		TDCTROrigenInf dCTROrigenInf = new TDCTROrigenInf();
		try {
			String cWhere = "";
			String cOrderBy = "";
			if (cCondicion.compareTo("") != 0) {
				cWhere = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else
				cOrderBy = "order by iCveOrigenInf";
			vDespliega = dCTROrigenInf.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}