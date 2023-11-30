package gob.sct.medprev;

import java.lang.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.util.TFechas;

/**
 * * Clase de configuracion para CFG Lista de Refrigeradores
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301080CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301080CFG.png'>
 */
public class pg070306030CFG extends CFGListBase2 {
	public pg070306030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		cPaginas = "pg070306031.jsp|";
		TDTOXBaja dTOXBaja = new TDTOXBaja();
		String tipo = "";
		TFechas Fecha = new TFechas();
		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion += " AND " + cCondicion;
			}
			if (request.getParameter("tipo") != null) {
				tipo = request.getParameter("tipo");
				if (tipo.compareTo("reactivo") == 0) {
					// if (request.getParameter("Busqueda1") != null) {
					if (request.getParameter("iCveLaboratorio") != null) {
						cCondicion += " AND R.iCveLaboratorio = "
								+ request.getParameter("iCveLaboratorio");
						// }
					}
					if (request.getParameter("Busqueda3") != null) {
						if (request.getParameter("dtBaja") != null
								&& request.getParameter("dtBaja").toString()
										.length() > 0) {
							cCondicion += " AND R.dtBaja = '"
									+ Fecha.getDateSQL(request
											.getParameter("dtBaja")) + "'";
						} else
							cCondicion += " AND R.dtBaja = '"
									+ Fecha.TodaySQL() + "'";

					}
					if (request.getParameter("iCveReactivo") != null
							&& request.getParameter("iCveReactivo").compareTo(
									"0") != 0) {
						cCondicion += " AND R.ICVEREACTIVO = "
								+ request.getParameter("iCveReactivo");
					}
				} else {
					// if (request.getParameter("Busqueda1") != null) {
					if (request.getParameter("iCveLaboratorio") != null) {
						cCondicion += " AND C.iCveLaboratorio = "
								+ request.getParameter("iCveLaboratorio");
						// }
					}
					if (request.getParameter("Busqueda3") != null) {
						if (request.getParameter("dtBaja") != null
								&& request.getParameter("dtBaja").toString()
										.length() > 0) {
							cCondicion += " AND C.dtBaja = '"
									+ Fecha.getDateSQL(request
											.getParameter("dtBaja")) + "'";
						} else
							cCondicion += " AND C.dtBaja = '"
									+ Fecha.TodaySQL() + "'";

					}
					if (request.getParameter("iCveCtrolCalibra") != null
							&& request.getParameter("iCveCtrolCalibra")
									.compareTo("0") != 0) {
						cCondicion += " AND C.ICVECTROLCALIBRA = "
								+ request.getParameter("iCveCtrolCalibra");
					}
				}
			}

			// System.out.println("cCondicion = " + cCondicion);
			if (cCondicion.compareTo("") != 0
					&& cCondicion.compareTo("null") != 0) {
				vDespliega = dTOXBaja.FindByAllB(cCondicion, tipo);
			} else {
				vDespliega.setSize(0);
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
