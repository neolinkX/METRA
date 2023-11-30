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
public class pg070604010CFG extends CFGListBase2 {
	public Vector vTpoMantto = new Vector();
	public Vector vMeses = new Vector();

	public pg070604010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070603020.jsp|";
		vMeses.add("1|Enero");
		vMeses.add("2|Febero");
		vMeses.add("3|Marzo");
		vMeses.add("4|Abril");
		vMeses.add("5|Mayo");
		vMeses.add("6|Junio");
		vMeses.add("7|Julio");
		vMeses.add("8|Agosto");
		vMeses.add("9|Septiembre");
		vMeses.add("10|Octubre");
		vMeses.add("11|Noviembre");
		vMeses.add("12|Diciembre");
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMMantenimiento dEQMMantto = new TDEQMMantenimiento();
		TDEQMTpoMantto dEQMTpoMantto = new TDEQMTpoMantto();
		String sWhere = "";
		String iCveTpoMantto = "";
		String xMesRango = "";
		String xUniMed = "";
		String xModulo = "";
		String xArea = "";
		String iAnio = "";
		String iMes = "";
		String dtInicio = "";
		String dtFin = "";
		String iCveUniMed = "";
		String iCveModulo = "";
		String iCveArea = "";

		// Requests
		if (request.getParameter("iCveTpoMantto") != null
				&& !request.getParameter("iCveTpoMantto").equals(""))
			iCveTpoMantto = request.getParameter("iCveTpoMantto").toString();
		if (request.getParameter("xMesRango") != null
				&& !request.getParameter("xMesRango").equals(""))
			xMesRango = request.getParameter("xMesRango").toString();
		if (request.getParameter("xUniMed") != null
				&& !request.getParameter("xUniMed").equals(""))
			xUniMed = request.getParameter("xUniMed").toString();
		if (request.getParameter("xModulo") != null
				&& !request.getParameter("xModulo").equals(""))
			xModulo = request.getParameter("xModulo").toString();
		if (request.getParameter("xArea") != null
				&& !request.getParameter("xArea").equals(""))
			xArea = request.getParameter("xArea").toString();
		if (request.getParameter("iAnio") != null
				&& !request.getParameter("iAnio").equals(""))
			iAnio = request.getParameter("iAnio").toString();
		if (request.getParameter("iMes") != null
				&& !request.getParameter("iMes").equals(""))
			iMes = request.getParameter("iMes").toString();
		if (request.getParameter("dtInicio") != null
				&& !request.getParameter("dtInicio").equals(""))
			dtInicio = request.getParameter("dtInicio").toString();
		if (request.getParameter("dtFin") != null
				&& !request.getParameter("dtFin").equals(""))
			dtFin = request.getParameter("dtFin").toString();
		if (request.getParameter("iCveUniMed") != null
				&& !request.getParameter("iCveUniMed").equals(""))
			iCveUniMed = request.getParameter("iCveUniMed").toString();
		if (request.getParameter("iCveModulo") != null
				&& !request.getParameter("iCveModulo").equals(""))
			iCveModulo = request.getParameter("iCveModulo").toString();
		if (request.getParameter("iCveArea") != null
				&& !request.getParameter("iCveArea").equals(""))
			iCveArea = request.getParameter("iCveArea").toString();

		try {
			vTpoMantto = dEQMTpoMantto.FindByAll(" where lActivo=1 ",
					" order by iCveTpoMantto ");

			// Where
			if (xMesRango.equals("R")) {
				sWhere += " and EqmMantenimiento.dtProgramado >='" + dtInicio
						+ "'" + " and EqmMantenimiento.dtProgramado <= '"
						+ dtFin + "'";
			}
			if (xUniMed.equals("1")) {
				sWhere += " and EqmAsignacion.iCveUniMed=" + iCveUniMed;
			}
			if (xModulo.equals("1")) {
				sWhere += " and EqmAsignacion.iCveModulo=" + iCveModulo;
			}
			if (xArea.equals("1")) {
				sWhere += " and EqmAsignacion.iCveArea=" + iCveArea;
			}
			if (!iCveTpoMantto.equals("")) {
				String q = " where EqmMantenimiento.iCveTpoMantto="
						+ iCveTpoMantto + sWhere;
				if (!cCondicion.equals(""))
					q = q + " and " + cCondicion;
				vDespliega = dEQMMantto.FindByAllConsProgr(q + cOrden);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}