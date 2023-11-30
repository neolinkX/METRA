package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuración para CFG de la pagina pg070104021
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070105012CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070105012CFG.png'>
 */
public class pg070105012CFG extends CFGListBase2 {
	public pg070105012CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "";
		NavStatus = "Disabled";
	}

	public void fillVector() {
		StringBuffer cFiltro = new StringBuffer();
		String cOrdenamiento = new String();
		String cCondicion = new String("cDscDiagnostico like '");
		boolean lAnd = false;
		Vector vResultado = new Vector();
		try {
			if ("Buscar".compareToIgnoreCase(this.cAccionOriginal.toString()) == 0) {
				if (request.getParameter("cCIE10") != null
						&& request.getParameter("cCIE10").toString().length() > 0) {
					cFiltro.append(" where cCIE like '")
							.append(request.getParameter("cCIE10").toString())
							.append("%'");
					lAnd = true;
				}
				if (request.getParameter("cDscDiagnostico") != null
						&& request.getParameter("cDscDiagnostico").toString()
								.length() > 0) {
					if (lAnd)
						cFiltro.append(" and ");
					else
						cFiltro.append(" where ");
					if (request.getParameter("cOpDesc") != null
							&& request.getParameter("cOpDesc").toString()
									.length() > 0) {
						if ("Contenga".compareToIgnoreCase(request
								.getParameter("cOpDesc").toString()) == 0)
							cCondicion += "%";
					}
					cFiltro.append(cCondicion)
							.append(request.getParameter("cDscDiagnostico")
									.toString()).append("%'");
				}
				if (request.getParameter("cOrden") != null
						&& request.getParameter("cOrden").toString().length() > 0) {
					cOrdenamiento = "order by "
							+ request.getParameter("cOrden").toString();
				}
				this.vDespliega = new TDMEDDiagnostico().FindByAll(
						cFiltro.toString(), cOrdenamiento);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

}
