package gob.sct.medprev;

import java.util.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para Handler de acciones para la página pg070101080
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Romeo Sanchez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101080.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101080.jsp.png'>
 */
public class pg070101080CFG extends CFGListBase2 {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private TFechas tf = new TFechas();
	private java.sql.Date d = null;
	private String fechaFormateada = "";
	private String fechaTmp = "";

	public pg070101080CFG() {
		this.vParametros = new TParametro("07");
		cPaginas = "pg070101081.jsp";
	}

	public void fillVector() {
		TDMEDPerfilMC dMEDPerfilMC = new TDMEDPerfilMC();
		String orderBy = " order by p.iCvePerfil";
		String cOrden = "" + request.getParameter("hdLOrdenar");
		String cWhere = "";

		cWhere = "" + request.getParameter("hdLCondicion");
		if (cWhere.compareTo("null") != 0 && cWhere.compareTo("") != 0) {
			cWhere = " and " + cWhere;
		} else
			cWhere = "";

		cOrden = "" + request.getParameter("hdLOrdenar");
		if (cOrden.compareTo("null") != 0 && cOrden.compareTo("") != 0) {
			cOrden = request.getParameter("hdLOrdenar");
		} else
			cOrden = orderBy;

		String trans = request.getParameter("iCveMdoTrans");
		if (trans != null && !trans.equals("")) {
			cWhere += " and p.iCveMdoTrans = " + trans;
		}
		try {
			vDespliega = dMEDPerfilMC.findPerfilGpoMdo(cWhere, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
