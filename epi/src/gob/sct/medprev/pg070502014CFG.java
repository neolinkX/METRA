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
import com.micper.excepciones.*;

/**
 * * Clase de configuracion para CFG Listado de Representantes Legales de la
 * Empresa
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070502014CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070502014CFG.png'>
 */
public class pg070502014CFG extends CFGListBase2 {
	public pg070502014CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070502015.jsp|pg070502010.jsp|";
		NavStatus = "Disabled";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDCTRRepresentante dCTRRepresentante = new TDCTRRepresentante();
		String where = "";
		int iEmp = 0;
		String iEmpresa = "";
		int iRepres = 0;
		String guardar = "";
		StringTokenizer stCondicion = new StringTokenizer(cCondicion);
		String tCondicion = "";
		boolean lEncontro = false;

		try {

			// Requests
			if (request.getParameter("iCveEmpresa") != null
					&& !request.getParameter("iCveEmpresa").equals("")) {
				iEmp = Integer.parseInt(request.getParameter("iCveEmpresa")
						.toString());
				iEmpresa = request.getParameter("iCveEmpresa").toString();
			}
			if (request.getParameter("lActivo") != null
					&& !request.getParameter("lActivo").equals("")) {
				iRepres = Integer.parseInt(request.getParameter("lActivo")
						.toString());
			}
			if (request.getParameter("GuardaRepresentante") != null
					&& !request.getParameter("GuardaRepresentante").equals("")) {
				guardar = request.getParameter("GuardaRepresentante")
						.toString();
			}
			// Ver si es cambio de Representante
			if (guardar.equals("S"))
				dCTRRepresentante.cambiarRepresentante(null, iEmp, iRepres);

			while (stCondicion.hasMoreElements()) {
				tCondicion = stCondicion.nextToken();
				if (tCondicion.compareToIgnoreCase("cIDDGPMPT") == 0
						&& !lEncontro) {
					lEncontro = true;
				}
				if (tCondicion.compareToIgnoreCase("iIDMdoTrans") == 0
						&& !lEncontro) {
					lEncontro = true;
				}
				if (tCondicion.compareToIgnoreCase("cRFC") == 0 && !lEncontro) {
					lEncontro = true;
				}
				if (tCondicion.compareToIgnoreCase("cDscEmpresa") == 0
						&& !lEncontro) {
					lEncontro = true;
				}
			}

			if (lEncontro)
				cCondicion = "";

			if (cOrden.compareTo("") != 0) {
				if (cOrden.compareTo(" order by cIDDGPMPT") == 0
						|| cOrden.compareTo(" order by iIDMdoTrans") == 0
						|| cOrden.compareTo(" order by cRFC") == 0
						|| cOrden.compareTo(" order by cDscEmpresa") == 0)
					cOrden = " order by iCveRepresentante ";
			} else
				cOrden += " order by iCveRepresentante ";

			// Bean Scroller
			if (!iEmpresa.equals("")) {
				where = " where CTRRepresentante.iCveEmpresa=" + iEmpresa;
				if (!cCondicion.equals(""))
					where = where + " and " + cCondicion;
				if (cOrden.compareToIgnoreCase("") != 0)
					where = where + cOrden + " desc ";
				else
					where = where
							+ " order by CTRRepresentante.iCveRepresentante desc ";
				vDespliega = dCTRRepresentante.FindByAllRepPaisEdoMcpio(where);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "SaveOnly";
		} else
			UpdStatus = "Disabled";
	}
}