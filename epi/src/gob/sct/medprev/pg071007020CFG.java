package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Control de Veh�culos - Tipos de Mantenimiento
 * por Empresa
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071007020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071007020CFG.png'>
 */
public class pg071007020CFG extends CFGListBase2 {
	public pg071007020CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg071007021.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDSEGUsuario dSEGUsuario = new TDSEGUsuario();
		try {
			String cWhere = "";

			if (cCondicion.compareTo("") != 0)
				cWhere += " where " + cCondicion + " ";

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;

			vDespliega = dSEGUsuario.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}