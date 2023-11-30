package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Generales - Listado de Motivos
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071005020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071005020CFG.png'>
 */
public class pg071005020CFG extends CFGListBase2 {
	public pg071005020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071005021.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLMotivo dGRLMotivo = new TDGRLMotivo();
		try {
			String cWhere = "";

			if (cCondicion.compareTo("") != 0)
				cWhere += " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else
				cWhere += " order by iCveMotivo ";

			vDespliega = dGRLMotivo.FindDsc(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}