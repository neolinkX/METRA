package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para CFG Para Listado de Ramas
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101050CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101050CFG.png'>
 */
public class pg070101050CFG extends CFGListBase2 {
	public pg070101050CFG() {
		cPaginas = "pg070101051.jsp|";
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDMEDRama dMEDRama = new TDMEDRama();
		String filtro = "";
		try {
			if (request.getParameter("iCveServicio") != null
					&& !request.getParameter("iCveServicio").equals(""))
				filtro = " and R.iCveServicio="
						+ request.getParameter("iCveServicio").toString() + " ";
			else if (request.getParameter("hdCampoClave") != null
					&& !request.getParameter("hdCampoClave").equals(""))
				filtro = " and R.iCveServicio="
						+ request.getParameter("hdCampoClave").toString() + " ";
			if (cCondicion != null && !cCondicion.equals("")) {
				filtro += " and " + cCondicion;
			}

			if (cOrden.compareTo("") == 0)
				cOrden = " order by cDscRama";

			if ((request.getParameter("iCveServicio") != null && !request
					.getParameter("iCveServicio").equals(""))
					|| (request.getParameter("hdCampoClave") != null && !request
							.getParameter("hdCampoClave").equals("")))
				vDespliega = dMEDRama.FindByAllCatalogoRamas(filtro, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}