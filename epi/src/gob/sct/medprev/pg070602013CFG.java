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
 * * Clase de configuracion para CFG Consulta de Mantenimientos
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070602010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070602010CFG.png'>
 */
public class pg070602013CFG extends CFGListBase2 {
	public Vector vEquipo = new Vector();

	public pg070602013CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070602010.jsp|pg070602011.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMMantenimiento dEQMMantto = new TDEQMMantenimiento();
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		String iCveEquipo = "";

		if (request.getParameter("iCveEquipo") != null
				&& !request.getParameter("iCveEquipo").equals(""))
			iCveEquipo = request.getParameter("iCveEquipo").toString();

		try {
			if (!iCveEquipo.equals("")) {
				vEquipo = dEQMEquipo.FindByAllEquipo(iCveEquipo);
				String q = " where EqmMantenimiento.iCveEquipo=" + iCveEquipo;
				if (!cCondicion.equals(""))
					q = q + " and " + cCondicion;
				if (request.getParameter("hdListado") != null
						&& !request.getParameter("hdListado").equals(""))
					q = q + cOrden + " desc ";
				vDespliega = dEQMMantto.FindByAllListaMantto(q);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}