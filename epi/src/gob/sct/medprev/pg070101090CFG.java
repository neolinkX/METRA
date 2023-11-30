package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Clase de configuracion para Listado de
 * MEDTpoResp
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101090CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101090CFG.png'>
 */
public class pg070101090CFG extends CFGListBase2 {
	public pg070101090CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070101091.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			String cWhere = request.getParameter("hdLCondicion");
			if (cWhere != null && cWhere.length() != 0)
				cWhere = " where " + cWhere;
			else
				cWhere = "";
			String cOrder = request.getParameter("hdLOrdenar");
			if (cOrder == null || cOrder.length() == 0)
				cOrder = " order by iCveTpoResp";
			vDespliega = new TDMEDTpoResp().FindByAll(cWhere, cOrder);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
