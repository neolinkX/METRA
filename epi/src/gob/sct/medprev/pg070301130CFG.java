package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para CFG de TOXAcCorrectiva
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Juan Manuel Vazquez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301130CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301130CFG.png'>
 */
public class pg070301130CFG extends CFGListBase2 {
	public pg070301130CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		NavStatus = "Disabled";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			TDAcCorrectiva dAcCorrectiva = new TDAcCorrectiva();
			if (this.getLPagina("pg070301131.jsp"))
				cPaginas = "pg070301131.jsp|";

			if (cCondicion.compareTo("") != 0)
				cCondicion = " WHERE " + cCondicion;
			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY iCveAcCorrectiva";

			String cAcCorrectiva = cCondicion + " " + cOrden;

			vDespliega = dAcCorrectiva.FindByAll(cAcCorrectiva);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
