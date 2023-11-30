package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuracion para Listado de Consulta de configuracion de la rama
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101060CFG.png'>
 */
public class pg070101060CFG extends CFGListBase2 {
	public pg070101060CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070101061.jsp";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		String cWhere = "";
		try {
			vDespliega = dMEDSintoma.FindByAll(cWhere + " " + cOrden);

			String lAction = "";

			if (request.getParameter("hdBoton") != null) {
				lAction = request.getParameter("hdBoton");
			} else {
				lAction = "";
			}

			if (lAction.trim().length() == 0) {
				lAction = "Buscar";
			}

			String cCond = "";
			String cOrder = "";
			if (lAction.equalsIgnoreCase("Primero")
					|| lAction.equalsIgnoreCase("Siguiente")
					|| lAction.equalsIgnoreCase("Anterior")
					|| lAction.equalsIgnoreCase("Actual")
					|| lAction.equalsIgnoreCase("Ultimo")
					|| lAction.equalsIgnoreCase("Primero")) {
				cWhere = "";
				cCond = request.getParameter("hdCCondicion");
				cOrder = request.getParameter("hdCOrdenar");
				if (cCond != null) {
					if (cCond.compareTo("null") == 0 || cCond.length() == 0) {
						cCond = "";
					} else {
						cCond = " Where " + cCond;
					}
				} else {
					cCond = "";
				}

				if (cOrder != null) {
					if (cOrder.compareTo("null") == 0) {
						cOrder = "";
					}
				} else {
					cOrder = "";
				}

				if (cCond.trim().length() == 0) {
					lAction = "Buscar";
				} else {
					String xCveServicio = "";
					String xCveRama = "";

					int inCveServicio = 0;
					int inCveRama = 0;

					xCveServicio = "" + request.getParameter("iCveServicio");
					xCveRama = "" + request.getParameter("iCveRama");

					if (xCveServicio.compareTo("null") != 0
							&& xCveServicio.compareTo("") != 0) {
						inCveServicio = Integer.parseInt(xCveServicio, 10);
					} else {
						inCveServicio = 0;
					}

					if (xCveRama.compareTo("null") != 0
							&& xCveRama.compareTo("") != 0) {
						inCveRama = Integer.parseInt(xCveRama, 10);
					} else {
						inCveRama = 0;
					}
					cCond += " And a.iCveServicio = " + inCveServicio
							+ " And a.iCveRama = " + inCveRama;

					cWhere += cCond + " " + cOrder;
					vDespliega = dMEDSintoma.FindByAll(cWhere + " " + cOrden);
				}
			}

			if (lAction.equalsIgnoreCase("Buscar")) {
				String cFiltro = "";
				String cCveServicio = "";
				String cCveRama = "";

				int iCveServicio = 0;
				int iCveRama = 0;

				cCveServicio = "" + request.getParameter("iCveServicio");
				cCveRama = "" + request.getParameter("iCveRama");

				if (cCveServicio.compareTo("null") != 0
						&& cCveServicio.compareTo("") != 0) {
					iCveServicio = Integer.parseInt(cCveServicio, 10);
				} else {
					iCveServicio = 0;
				}

				if (cCveRama.compareTo("null") != 0
						&& cCveRama.compareTo("") != 0) {
					iCveRama = Integer.parseInt(cCveRama, 10);
				} else {
					iCveRama = 0;
				}

				cWhere = " WHERE a.iCveServicio = " + iCveServicio
						+ " And a.iCveRama = " + iCveRama;

				if (cOrden.equals(""))
					vDespliega = dMEDSintoma.FindByAll(cWhere
							+ " order by iOrden ");
				else
					vDespliega = dMEDSintoma.FindByAll(cWhere + " " + cOrden);
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

}