package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Clase para el listado de los Equipos
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hern�ndez Garc�a
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg070603020CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070603020CFG.java.png'>
 */
public class pg070603020CFG extends CFGListBase2 {
	public pg070603020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMAsignacion dEQMAsignacion = new TDEQMAsignacion();
		try {
			if (cCondicion.compareTo("") != 0)
				cCondicion = " WHERE " + cCondicion
						+ " AND EQMAsignacion.lActual = 1";
			else
				cCondicion = " WHERE EQMAsignacion.lActual = 1 ";

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("null") != 0
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("-1") != 0
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("0") != 0) {
				cCondicion += " AND EQMAsignacion.iCveUniMed = "
						+ request.getParameter("iCveUniMed");
			}

			if (request.getParameter("iCveModulo") != null
					&& request.getParameter("iCveModulo").toString()
							.compareTo("null") != 0
					&& request.getParameter("iCveModulo").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveModulo").toString()
							.compareTo("-1") != 0
					&& request.getParameter("iCveModulo").toString()
							.compareTo("0") != 0) {
				cCondicion += " AND EQMAsignacion.iCveModulo = "
						+ request.getParameter("iCveModulo");
			}

			if (request.getParameter("iCveArea") != null
					&& request.getParameter("iCveArea").toString()
							.compareTo("null") != 0
					&& request.getParameter("iCveArea").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveArea").toString()
							.compareTo("-1") != 0
					&& request.getParameter("iCveArea").toString()
							.compareTo("0") != 0) {
				cCondicion += " AND EQMAsignacion.iCveArea = "
						+ request.getParameter("iCveArea");
			}
			if (cOrden.compareTo("") != 0)
				cOrden = " ORDER BY EQMAsignacion.iCveEquipo ";

			vDespliega = dEQMAsignacion.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
