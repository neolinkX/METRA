package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para CFG de TOXEmpleoCalib
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301100.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301100.jsp.png'>
 */
public class pg070301100CFG extends CFGListBase2 {
	public pg070301100CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		NavStatus = "Disabled";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEmpleoCalib dEmpleoCalib = new TDEmpleoCalib();
		if (this.getLPagina("pg070301101.jsp"))
			cPaginas = "pg070301101.jsp|";

		if (cCondicion.compareTo("") != 0)
			cCondicion = " WHERE " + cCondicion;
		if (cOrden.compareTo("") == 0)
			cOrden = " ORDER BY iCveEmpleoCalib";

		String cEmpleoCalib = cCondicion + " " + cOrden;

		try {
			vDespliega = dEmpleoCalib.FindByAll(cEmpleoCalib);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
