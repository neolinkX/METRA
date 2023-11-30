package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para Clase para el Catálogo de TOXCorteXSust
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco antonio Hernández García
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301221.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301221.png'>
 */
public class pg070301220CFG extends CFGListBase2 {
	public pg070301220CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		NavStatus = "Disabled";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDTOXCorteXSust dTOXCorteXSust = new TDTOXCorteXSust();
		try {
			if (this.getLPagina("pg070301221.jsp"))
				cPaginas = "pg070301221.jsp|";
			if (this.getLPagina("pg070301221.jsp"))
				if (cPaginas.compareTo("") != 0)
					cPaginas += "pg070301120.jsp|";
				else
					cPaginas = "pg070301120.jsp|";

			if (cCondicion != null && cCondicion.compareTo("") != 0)
				cCondicion = " WHERE " + cCondicion;
			if (request.getParameter("iCveSustancia") != null
					&& request.getParameter("iCveSustancia").compareTo("") != 0) {
				if (cCondicion.compareTo("") != 0
						&& request.getParameter("iCveSustancia") != null)
					cCondicion += " AND TOXCorteXSust.iCveSustancia = "
							+ request.getParameter("iCveSustancia");
				else if (request.getParameter("iCveSustancia") != null)
					cCondicion += " WHERE TOXCorteXSust.iCveSustancia = "
							+ request.getParameter("iCveSustancia");
			}

			vDespliega = dTOXCorteXSust.FindByAll(cCondicion);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
