package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Generales - Listado de TipoEnvios
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301150CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301150CFG.png'>
 */
public class pg070301150CFG extends CFGListBase2 {
	public pg070301150CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070301151.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		int iCveLaboratorio = 0;
		TDTOXParamCalib dParam = new TDTOXParamCalib();
		try {
			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.length() > 0)
				iCveLaboratorio = Integer.parseInt(request.getParameter(
						"iCveLaboratorio").toString());
			String cWhere = " where iCveLaboratorio =" + iCveLaboratorio;

			if (cCondicion.compareTo("") != 0)
				cWhere = " and " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else
				cWhere += " order by iCveParamCalib ";

			vDespliega = dParam.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
