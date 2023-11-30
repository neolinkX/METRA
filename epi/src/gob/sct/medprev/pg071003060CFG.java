package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para CFG de pg071003060.jsp
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003060.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003060.jsp.png'>
 */
public class pg071003060CFG extends CFGListBase2 {
	public pg071003060CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDSEGUsuario dSEGUsuario = new TDSEGUsuario();
		try {
			String cWhere = "";

			if (cCondicion.compareTo("") != 0)
				cWhere += " where lBloqueado = 0 and " + cCondicion;
			else
				cWhere += " where lBloqueado = 0 ";

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else
				cWhere += " order by iCveUsuario ";

			vDespliega = dSEGUsuario.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}