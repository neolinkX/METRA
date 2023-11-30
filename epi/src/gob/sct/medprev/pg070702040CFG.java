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
import com.micper.util.*;
import com.micper.seguridad.vo.*;

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
public class pg070702040CFG extends CFGListBase2 {
	public Vector vEquipo = new Vector();
	public Vector vServxEq = new Vector();
	public Vector vServxUniMed = new Vector();
	public TVEQMAsignacion tvAsignacion = new TVEQMAsignacion();

	public pg070702040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cOrden = "";
		cCondicion = "";
		cPaginas = "pg070702041.jsp|pg070702042.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDVEHMantenimiento dMan = new TDVEHMantenimiento();
		try {
			int num_man = 0;
			TFechas hoy = new TFechas();
			TVVEHMantenimiento tvMan;
			if (request.getParameter("num_man") != null
					&& !request.getParameter("num_man").equals(""))
				num_man = Integer.parseInt(request.getParameter("num_man")
						.toString());

			for (int i = 0; i < num_man; i++) {
				tvMan = new TVVEHMantenimiento();
				String iCveVehiculo = request.getParameter("iCveVehiculo" + i) == null ? "0"
						: request.getParameter("iCveVehiculo" + i);
				String iCveExiste = request.getParameter("iCveExiste" + i) == null ? ""
						: request.getParameter("iCveExiste" + i);
				String iCveIniciar = request.getParameter("iCveIniciar" + i) == null ? ""
						: request.getParameter("iCveIniciar" + i);
				String iCveUsr = request.getParameter("iCveUsr") == null ? ""
						: request.getParameter("iCveUsr");
				tvMan.setICveVehiculo(Integer.parseInt(iCveVehiculo));
				if (!iCveIniciar.equals("") && !iCveExiste.equals("")
						&& !iCveUsr.equals("")) {
					tvMan.setICveMantenimiento(Integer.parseInt(iCveExiste));
					tvMan.setDtInicia(hoy.TodaySQL());
					tvMan.setICveUsuAutoriza(Integer.parseInt(iCveUsr));
					dMan.updateSolicitudes(null, tvMan);
				}
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDVEHMantenimiento dMantto = new TDVEHMantenimiento();
		String dtInicio = "";
		String dtFin = "";
		String iCveUniMed = "";
		String sWhere = "";
		TFechas dtFecha = new TFechas();

		if (request.getParameter("dtInicio") != null
				&& !request.getParameter("dtInicio").equals(""))
			dtInicio = request.getParameter("dtInicio").toString();
		if (request.getParameter("dtFin") != null
				&& !request.getParameter("dtFin").equals(""))
			dtFin = request.getParameter("dtFin").toString();
		if (request.getParameter("iCveUniMed") != null
				&& !request.getParameter("iCveUniMed").equals(""))
			iCveUniMed = request.getParameter("iCveUniMed").toString();

		try {
			if (!dtInicio.equals("") && !dtFin.equals("")
					&& !iCveUniMed.equals("")) {
				switch (Integer.parseInt(request.getParameter("RSConcluido"))) {
				case 0:
					sWhere = " and VehMantenimiento.lConcluido = 0 ";
					break;
				case 1:
					sWhere = " and VehMantenimiento.lConcluido = 1 ";
					break;
				case 2:
					break;
				}
				sWhere += " and VehMantenimiento.dtSolicitud >='"
						+ dtFecha.getDateSQL(dtInicio) + "'"
						+ " and VehMantenimiento.dtSolicitud <='"
						+ dtFecha.getDateSQL(dtFin) + "'"
						+ " and VehUbicacion.iCveUniMed=" + iCveUniMed;
				if (!cCondicion.equals("")) {
					sWhere = sWhere + " and " + cCondicion;
				}
				sWhere = sWhere + " " + cOrden;
			}
			if (!sWhere.equals(""))
				vDespliega = dMantto.FindByAllConsSol(sWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
