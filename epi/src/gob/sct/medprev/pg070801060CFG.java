package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG List ALMTpoMovimiento
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Juan Manuel Vazquez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801060CFG.png'>
 */
public class pg070801060CFG extends CFGListBase2 {
	public pg070801060CFG() {
		cPaginas = "pg070801061.jsp|";
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDALMTpoMovimiento dALMTpoMovimiento = new TDALMTpoMovimiento();
		try {
			String cFiltro = "" + request.getParameter("hdLCondicion");
			String cOrden = "" + request.getParameter("hdLOrdenar");

			if (cFiltro.compareToIgnoreCase("null") != 0
					&& cFiltro.compareTo("") != 0)
				cFiltro = " where " + cFiltro;
			else
				cFiltro = "";

			if (cOrden.compareToIgnoreCase("null") != 0
					&& cOrden.compareTo("") != 0)
				cOrden = cOrden;
			else
				cOrden = " ORDER BY iCveTpoMovimiento ";

			vDespliega = dALMTpoMovimiento.FindByAll(cFiltro, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
