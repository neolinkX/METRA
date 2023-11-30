package gob.sct.medprev;

import java.lang.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.TFechas;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.dao.*;
import java.util.Vector;

/**
 * * Clase de configuración para
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305010.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305010.png'>
 */
public class pg070305010CFG extends CFGListBase2 {
	public pg070305010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDMuestra dMuestra = new TDMuestra();
		TFechas Fecha = new TFechas();
		try {
			String cFiltro = "";

			if (request.getParameter("iAnio") != null)
				cFiltro = " where a.iAnio = " + request.getParameter("iAnio");
			if (request.getParameter("iCveUniMed") != null
					&& "-1".compareToIgnoreCase(request.getParameter(
							"iCveUniMed").toString()) != 0)
				cFiltro += " and iCveUniMed = "
						+ request.getParameter("iCveUniMed");
			if (request.getParameter("dtPeriodoDe") != null
					&& request.getParameter("dtPeriodoA") != null)
				if (request.getParameter("dtPeriodoDe").toString()
						.compareTo("") != 0
						&& request.getParameter("dtPeriodoA").toString()
								.compareTo("") != 0)
					cFiltro += " and a.dtRecoleccion >= '"
							+ Fecha.getDateSQL(request
									.getParameter("dtPeriodoDe"))
							+ "' and a.dtRecoleccion <= '"
							+ Fecha.getDateSQL(request
									.getParameter("dtPeriodoA")) + "' ";

			if (request.getParameter("iClaveDe") != null
					&& request.getParameter("iClaveA") != null)
				if (request.getParameter("iClaveDe").toString().compareTo("") != 0
						&& request.getParameter("iClaveA").toString()
								.compareTo("") != 0)
					cFiltro += " and a.iCveMuestra >= "
							+ request.getParameter("iClaveDe")
							+ " and a.iCveMuestra <= "
							+ request.getParameter("iClaveA") + " ";

			if (request.getParameter("iCveSituacion") != null
					&& request.getParameter("iCveSituacion").compareTo("-1") != 0)
				cFiltro += " and a.iCveSituacion = "
						+ request.getParameter("iCveSituacion");

			if (cCondicion.compareToIgnoreCase("") != 0) {
				cFiltro += " and " + cCondicion;
			}

			if (cFiltro.length() > 0) {
				cFiltro += cOrden;
				vDespliega = (Vector) (new TDTOXAnalisis().FindByAll6(cFiltro));
			}
		} catch (Exception ex) {
			error("FillVector", ex);
			ex.printStackTrace();
		}
	}

	public String getOtrasSust(TVTOXCuantAnalisis VCAnal) {
		String cTexto = new String();
		try {
			cTexto = new TDTOXCuantAnalisis().getOtrasSust(VCAnal).toString();
			cTexto += "&nbsp;";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cTexto;
	}

}
