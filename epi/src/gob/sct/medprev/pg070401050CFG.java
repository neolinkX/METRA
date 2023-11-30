package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Listado de Recomendaci�n
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070401050CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070401050CFG.png'>
 */
public class pg070401050CFG extends CFGListBase2 {
	public pg070401050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070401051.jsp";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			String cWhere = request.getParameter("hdLCondicion");
			cWhere = cWhere != null ? cWhere : "";
			String cOrder = request.getParameter("hdLOrdenar");
			cOrder = cOrder != null ? cOrder : "";
			vDespliega = new TDINVRecomendacion().FindByAll(cWhere, cOrder);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}