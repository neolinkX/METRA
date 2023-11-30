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
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305020.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305020.png'>
 */
public class pg070305020CFG extends CFGListBase2 {
	public pg070305020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXEnvio dTOXEnvio = new TDTOXEnvio();
		cPaginas = "pg070305021.jsp|";
		boolean lWhere = false;
		try {
			/*
			 * } if (request.getParameter("iAnio")!=null &&
			 * request.getParameter("iCveUniMed")!=null){ if
			 * (cCondicion.compareToIgnoreCase("")!=0) cCondicion = " Where " +
			 * cCondicion + " and "; else cCondicion = " Where "; cCondicion =
			 * cCondicion + "    TOXEnvio.iAnio = " +
			 * request.getParameter("iAnio") + "   and TOXEnvio.iCveUniMed = " +
			 * request.getParameter("iCveUniMed"); if (cOrden.compareTo("") ==
			 * 0) cOrden = " order by TOXEnvio.iCveEnvio desc"; vDespliega =
			 * dTOXEnvio.FindByAll3(cCondicion,cOrden);
			 */
			if (cCondicion.compareToIgnoreCase("") != 0) {
				lWhere = true;
				cCondicion = " Where " + cCondicion;
			}

			if (request.getParameter("iAnio") != null) {
				if (lWhere)
					cCondicion += " And TOXEnvio.iAnio = "
							+ request.getParameter("iAnio");
				else {
					cCondicion = " Where TOXEnvio.iAnio = "
							+ request.getParameter("iAnio");
					lWhere = true;
				}
			}

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("-1") != 0) {
				if (lWhere)
					cCondicion += " And TOXEnvio.iCveUniMed = "
							+ request.getParameter("iCveUniMed");
				else {
					cCondicion = " Where TOXEnvio.iCveUniMed = "
							+ request.getParameter("iCveUniMed");
					lWhere = true;
				}
			}

			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.compareTo("-1") != 0) {
				if (lWhere)
					cCondicion += " And TOXEnvio.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
				else {
					cCondicion = " Where TOXEnvio.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
					lWhere = true;
				}
			}

			if (cOrden.compareTo("") == 0)
				cOrden = " order by TOXEnvio.iCveEnvio desc ";

			vDespliega = dTOXEnvio.FindByAll3(cCondicion, cOrden);

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}