package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG del Listado de usuarios de la DGPMPT
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Jaime Enrique Suárez Romero
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071001011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071001011CFG.png'>
 */
public class pg071001011CFG extends CFGListBase2 {
	public pg071001011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDSEGUsuario dSEGUsuario = new TDSEGUsuario();
		String cBuscar = "";
		try {
			if (cCondicion.compareTo("") != 0) {
				cBuscar = "where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cBuscar = cBuscar + cOrden;
			}
			vDespliega = dSEGUsuario.FindByAll(cBuscar);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}