package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TFechas;
import java.util.*;

/**
 * * Clase de configuracion para Control de Veh�culos - Solicitudes Vigentes
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070703020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070703020CFG.png'>
 */
public class pg070703020CFG extends CFGListBase2 {
	public pg070703020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDVEHSolicitud dVEHSolicitud = new TDVEHSolicitud();
		try {
			String cWhere = "";
			String cOrderBy = "";
			String cVEHEtapaSolFin = vParametros
					.getPropEspecifica("VEHEtapaSolFin");
			TFechas TFecha = new TFechas();
			if (request.getParameter("dtSolicitudDe") != null
					&& request.getParameter("dtSolicitudA") != null
					&& request.getParameter("iCveUniMed") != null) {
				cWhere += " where VEHSolicitud.dtSolicitud >= '"
						+ TFecha.getDateSQL(request.getParameter(
								"dtSolicitudDe").toString())
						+ "' and VEHSolicitud.dtSolicitud <= '"
						+ TFecha.getDateSQL(request
								.getParameter("dtSolicitudA").toString())
						+ "' and  VEHSolicitud.iCveUniMed = "
						+ request.getParameter("iCveUniMed")
						+ " and VEHSolicitud.dtCancelacion is null ";

				if (cCondicion.compareTo("") != 0)
					cWhere += " and " + cCondicion;

				cOrderBy += cOrden;

				vDespliega = dVEHSolicitud.FindSolicVig2(cWhere, cOrderBy,
						cVEHEtapaSolFin);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}