package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para Generales - Listado de Requisitos
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071006010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071006010CFG.png'>
 */
public class pg071006010CFG extends CFGListBase2 {
	public pg071006010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071006011.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDGRLRequisito dGRLRequisito = new TDGRLRequisito();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (cCondicion.compareTo("") != 0)
				cWhere = " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by iCveRequisito ";

			vDespliega = dGRLRequisito.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}