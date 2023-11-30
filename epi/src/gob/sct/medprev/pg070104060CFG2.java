package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuraci�n para CFG de la pagina pg070104060
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104060CFG.png'>
 */
public class pg070104060CFG2 extends CFGListBase2 {
	public pg070104060CFG2() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "";
		NavStatus = "Disabled";
	}

	/**
	 * M�todo FillVector
	 */
	public void fillVector() {
		TDEXPRama dEXPRama = new TDEXPRama();
		try {
			if (request.getParameter("hdiCveExpediente") != null
					&& request.getParameter("hdiCveExpediente").compareTo(
							"null") != 0
					&& request.getParameter("hdiCveExpediente").compareTo("") != 0
					&& request.getParameter("hdiNumExamen") != null
					&& request.getParameter("hdiNumExamen").compareTo("null") != 0
					&& request.getParameter("hdiNumExamen").compareTo("") != 0)
				vDespliega = dEXPRama.FindRec(
						request.getParameter("hdiCveExpediente"),
						request.getParameter("hdiNumExamen"));
			iNumReg = vDespliega.size();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}