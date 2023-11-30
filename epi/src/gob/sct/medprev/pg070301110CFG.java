package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Listado de la tabla TOXTpoReact
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301110CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301110CFG.png'>
 */
public class pg070301110CFG extends CFGListBase2 {
	public pg070301110CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		NavStatus = "Disabled";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			TDTOXMarcaSust dTOXMarcaSust = new TDTOXMarcaSust();
			if (this.getLPagina("pg070301111.jsp"))
				cPaginas = "pg070301111.jsp|";

			if (cCondicion.compareTo("") != 0)
				cCondicion = " WHERE " + cCondicion;
			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY iCveMarcaSust";
			String cMarcaSust = cCondicion + " " + cOrden;

			vDespliega = dTOXMarcaSust.FindByAll(cMarcaSust);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
