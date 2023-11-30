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
import com.micper.util.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuracion para CFG de EQMEquipo
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301070CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301070CFG.png'>
 */
public class pg070306040CFG extends CFGListBase2 {
	public pg070306040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXReactivo dTOXReact = new TDTOXReactivo();
		TFechas Fecha = new TFechas();
		String cFiltro = "";
		cPaginas = "pg070306011.jsp|";
		try {
			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.compareTo("") != 0) {
				cFiltro = " and iCveLaboratorio="
						+ request.getParameter("iCveLaboratorio");

			}
			if (request.getParameter("chkfprep") != null
					&& request.getParameter("chkfprep").equals("1")) {
				cFiltro += " and dtPreparacion='"
						+ Fecha.getDateSQL(request
								.getParameter("dtPreparacion")) + "'";

			}
			if (request.getParameter("chkfcad") != null
					&& request.getParameter("chkfcad").equals("1")) {
				cFiltro += " and dtCaducidad='"
						+ Fecha.getDateSQL(request.getParameter("dtCaducidad"))
						+ "'";

			}
			if (request.getParameter("chkPreparadoPor") != null
					&& request.getParameter("chkPreparadoPor").equals("1")) {
				cFiltro += " and iCveUsuPrepara="
						+ request.getParameter("iCveUsuPrepara");

			}
			if (request.getParameter("chkMarca") != null
					&& request.getParameter("chkMarca").equals("1")) {
				cFiltro += " and iCveMarcaSust="
						+ request.getParameter("iCveMarcaSust");

			}
			if (request.getParameter("chkSituacion") != null
					&& request.getParameter("chkSituacion").equals("1")) {
				String s = request.getParameter("situacion");
				if (s.equals("enuso")) {
					cFiltro += " and lAgotado=0 and lBaja=0";
				} else if (s.equals("agotado")) {
					cFiltro += " and lAgotado=1";
				} else if (s.equals("baja")) {
					cFiltro += " and lBaja=1";
				}
			}
			if (cFiltro.compareTo("") == 0)
				cFiltro = " and iCveLaboratorio = -1 ";
			vDespliega = dTOXReact.FindByAllTpoReact(cFiltro,
					" order by R.dtPreparacion ");
			if (vDespliega.size() > 0)
				this.iNumReg = vDespliega.size();
			else
				this.iNumReg = 0;

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
