/**
 * @author AG SA.
 * @version 1.0
 */

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
import com.micper.seguridad.vo.TVUsuario;

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
 * @author <dd>AG SA
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
public class pg070101100CFG extends CFGListBase2 {
	public pg070101100CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070101061.jsp";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		// TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		TDMEDREGSIN dMEDREGSIN = new TDMEDREGSIN();
		TDMEDAReg dMEDAREG = new TDMEDAReg();
		String cWhere = "";
		try {
			// vDespliega = dMEDREGSIN.FindByAll(cWhere + " " + cOrden);

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
					String xCveSintoma = "";

					int inCveServicio = 0;
					int inCveRama = 0;
					int inCveSintoma = 0;

					xCveServicio = "" + request.getParameter("iCveServicio");
					xCveRama = "" + request.getParameter("iCveRama");
					xCveSintoma = "" + request.getParameter("iCveSintoma");

					// /////////
					if (xCveServicio.compareTo("null") != 0
							&& xCveServicio.compareTo("") != 0) {
						inCveServicio = Integer.parseInt(xCveServicio, 10);
					} else {
						inCveServicio = 0;
					}

					// /////////
					if (xCveRama.compareTo("null") != 0
							&& xCveRama.compareTo("") != 0) {
						inCveRama = Integer.parseInt(xCveRama, 10);
					} else {
						inCveRama = 0;
					}

					// /////////
					if (xCveSintoma.compareTo("null") != 0
							&& xCveSintoma.compareTo("") != 0) {
						inCveSintoma = Integer.parseInt(xCveSintoma, 10);
					} else {
						inCveSintoma = 0;
					}
					cCond += " Where M.iCveServicio = " + inCveServicio
							+ " And M.iCveRama = " + inCveRama
							+ " And M.iCveSintoma = " + inCveSintoma
							+ " And M.RDACCION = 1  ";

					cWhere += cCond + " ";
					vDespliega = dMEDREGSIN.FindByAll(cWhere);
				}
			}

			if (lAction.equalsIgnoreCase("Buscar")) {
				String cFiltro = "";
				String cCveServicio = "";
				String cCveRama = "";
				String cCveSintoma = "";

				int iCveServicio = 0;
				int iCveRama = 0;
				int iCveSintoma = 0;

				cCveServicio = "" + request.getParameter("iCveServicio");
				cCveRama = "" + request.getParameter("iCveRama");
				cCveSintoma = "" + request.getParameter("iCveSintoma");

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

				if (cCveSintoma.compareTo("null") != 0
						&& cCveSintoma.compareTo("") != 0) {
					iCveSintoma = Integer.parseInt(cCveSintoma, 10);
				} else {
					iCveSintoma = 0;
				}

				cWhere = " WHERE M.iCveServicio = " + iCveServicio
						+ " And M.iCveRama = " + iCveRama
						+ " And M.iCveSintoma = " + iCveSintoma
						+ " And M.RDACCION = 1  ";

				vDespliega = dMEDREGSIN.FindByAll(cWhere);

			}

			if (lAction.equalsIgnoreCase("BorrarB")) {
				String cFiltro = "";
				String cCveServicio = "";
				String cCveRama = "";
				String cCveSintoma = "";

				int iCveServicio = 0;
				int iCveRama = 0;
				int iCveSintoma = 0;

				cCveServicio = "" + request.getParameter("iCveServicio");
				cCveRama = "" + request.getParameter("iCveRama");
				cCveSintoma = "" + request.getParameter("iCveSintoma");

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

				if (cCveSintoma.compareTo("null") != 0
						&& cCveSintoma.compareTo("") != 0) {
					iCveSintoma = Integer.parseInt(cCveSintoma, 10);
				} else {
					iCveSintoma = 0;
				}

				TVUsuario usuario = (TVUsuario) this.httpReq.getSession().getAttribute(
						"UsrID");
				dMEDREGSIN.delete(null, iCveServicio, iCveRama, iCveSintoma,
						" and RdAccion = 1", usuario.getICveusuario());
				
				dMEDAREG.delete(null, iCveServicio, iCveRama, iCveSintoma,
						" and SintomaDep IS NULL",  usuario.getICveusuario());
				vDespliega = dMEDREGSIN.FindByAll(cWhere);
			}// fin BorrarB

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

}