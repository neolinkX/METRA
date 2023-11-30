package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Generales - Listado de MotivoNoAps
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070106060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070106060CFG.png'>
 */
public class pg070106060CFG extends CFGListBase2 {
	public pg070106060CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070106061.jsp|pg071003033.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDPERMotivoNoAp dPERMotivoNoAp = new TDPERMotivoNoAp();
		try {
			String cWhere = "";

			if (cCondicion.compareTo("") != 0)
				cWhere = " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;

			vDespliega = dPERMotivoNoAp.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}