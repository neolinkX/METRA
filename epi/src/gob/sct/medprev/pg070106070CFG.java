package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Generales - Listado de Rubros de No Aptitud
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Oscar Castrej�n Adame.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070106070CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070106070CFG.png'>
 */
public class pg070106070CFG extends CFGListBase2 {
	public pg070106070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070106071.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDPERRubroNoAp DPERRubroNoAp = new TDPERRubroNoAp();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveMotivoNoAp") != null
					&& request.getParameter("iCveMotivoNoAp").compareTo("") != 0)
				cWhere += " where iCveMotivoNoAp = "
						+ request.getParameter("iCveMotivoNoAp");
			else if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where iCveMotivoNoAp = "
						+ request.getParameter("iCveMotivoNoAp");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy += cOrden;
			else
				cOrderBy = " order by iCveRubroNoAp ";

			if (request.getParameter("iCveMotivoNoAp") != null
					&& request.getParameter("iCveMotivoNoAp").compareTo("") != 0
					&& request.getParameter("iCveMotivoNoAp").compareTo("null") != 0
					&& Integer.parseInt(request.getParameter("iCveMotivoNoAp")) > 0)
				vDespliega = DPERRubroNoAp.FindByAll(cWhere + cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}