package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para CFG del listado de la tabla TOXEnvio
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
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070302050.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070302050.png'>
 */
public class pg070302050CFG extends CFGListBase2 {
	public pg070302050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXEnvio dTOXEnvio = new TDTOXEnvio();
		cPaginas = "pg070302041.jsp|";
		boolean lWhere = false;
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed") != null) {
				if (cCondicion.compareToIgnoreCase("") != 0) {
					lWhere = true;
					cCondicion = " Where " + cCondicion;
				}
				if (request.getParameter("iAnio") != null) {
					if (lWhere)
						cCondicion += " And E.iAnio = "
								+ request.getParameter("iAnio");
					else {
						cCondicion = " Where E.iAnio = "
								+ request.getParameter("iAnio");
						lWhere = true;
					}
				}

				if (request.getParameter("iCveUniMed") != null
						&& request.getParameter("iCveUniMed").toString()
								.compareTo("-1") != 0) {
					if (lWhere)
						cCondicion += " And E.iCveUniMed = "
								+ request.getParameter("iCveUniMed");
					else {
						cCondicion = " Where E.iCveUniMed = "
								+ request.getParameter("iCveUniMed");
						lWhere = true;
					}
				}
				if (request.getParameter("iCveLaboratorio") != null
						&& request.getParameter("iCveLaboratorio").toString()
								.compareTo("-1") != 0) {
					if (lWhere)
						cCondicion += " And E.iCveLaboratorio = "
								+ request.getParameter("iCveLaboratorio");
					else {
						cCondicion = " Where E.iCveLaboratorio = "
								+ request.getParameter("iCveLaboratorio");
						lWhere = true;
					}
				}
				if (cOrden.compareTo("") == 0)
					cOrden = " order by E.iCveEnvio desc ";
				else
					cOrden += " desc";

				vDespliega = dTOXEnvio.FindByAll2(cCondicion, cOrden);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}