package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG del listado de la tabla TOXEnvio
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070302040.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070302040.png'>
 */
public class pg070302040CFG extends CFGListBase2 {
	public pg070302040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDTOXEnvio dTOXEnvio = new TDTOXEnvio();
		cPaginas = "pg070302041.jsp|";
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iCveUniMed") != null) {
				if (cCondicion.compareToIgnoreCase("") != 0)
					cCondicion = " Where " + cCondicion + " and ";
				else
					cCondicion = " Where ";
				cCondicion = cCondicion + "    E.iAnio = "
						+ request.getParameter("iAnio")
						+ "   and E.iCveUniMed = "
						+ request.getParameter("iCveUniMed");
				if (cOrden.compareTo("") == 0)
					cOrden = " order by E.iCveEnvio ";
				cOrden = cOrden.concat(" desc");
				vDespliega = dTOXEnvio.FindByAll2(cCondicion, cOrden);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}