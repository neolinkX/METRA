package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG del Listado de Usuarios y Unidades Médicas
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071001020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071001020CFG.png'>
 */
public class pg071001020CFG extends CFGListBase2 {
	public pg071001020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071001021.jsp|";
	}

	public void fillVector() {
		TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
		try {
			vDespliega = dGRLUMUsuario.FindByAll("");
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}