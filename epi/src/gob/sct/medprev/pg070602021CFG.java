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
public class pg070602021CFG extends CFGListBase2 {
	public Vector vEquipo = new Vector();
	public Vector vServxEq = new Vector();
	public Vector vServxUniMed = new Vector();
	public TVEQMAsignacion tvAsignacion = new TVEQMAsignacion();

	public pg070602021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070602012.jsp|pg070602020.jsp|";
		cOrden = "";
		cCondicion = "";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDEQMServXEq dServxEq = new TDEQMServXEq();
		try {
			int num_serv = 0;
			Vector vServ = new Vector();
			TVEQMServXEq tvServ;
			if (request.getParameter("num_serv") != null
					&& !request.getParameter("num_serv").equals(""))
				num_serv = Integer.parseInt(request.getParameter("num_serv")
						.toString());

			for (int i = 0; i < num_serv; i++) {
				tvServ = new TVEQMServXEq();
				if (request.getParameter("iCveServicio" + i) != null
						&& !request.getParameter("iCveServicio" + i).equals("")
						&& request.getParameter("hdCampoClave") != null
						&& !request.getParameter("hdCampoClave").equals("")
						&& request.getParameter("iCveAsignacion") != null
						&& !request.getParameter("iCveAsignacion").equals("")) {
					String iCveEquipo = request.getParameter("hdCampoClave");
					String iCveAsignacion = request
							.getParameter("iCveAsignacion");
					String asignar = request.getParameter("iCveAsignar" + i) == null ? ""
							: request.getParameter("iCveAsignar" + i);
					String existe = request.getParameter("iCveExiste" + i) == null ? ""
							: request.getParameter("iCveExiste" + i);
					tvServ.setICveServicio(Integer.parseInt(request
							.getParameter("iCveServicio" + i).toString()));
					tvServ.setICveEquipo(Integer.parseInt(iCveEquipo));
					tvServ.setICveAsignacion(Integer.parseInt(iCveAsignacion));
					// Se usara el campo lActual para saber si es insertar o
					// borrar 1 = Insertar, 0 = Borrar
					if (!asignar.equals("") && existe.equals("0")) {
						tvServ.setLActual(1);
						vServ.add(tvServ);
					} else if (asignar.equals("") && existe.equals("1")) {
						tvServ.setLActual(0);
						vServ.add(tvServ);
					}
				}
			}
			if (vServ.size() > 0)
				dServxEq.updateServiciosxEq(null, vServ);
			// cClave = (String) dALMArticulo.insert(null, this.getInputs());
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
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		TDEQMServXEq dServEq = new TDEQMServXEq();
		TDMEDServicio dServUM = new TDMEDServicio();
		TDEQMAsignacion dAsigna = new TDEQMAsignacion();
		Vector vAsigna = new Vector();

		TVMEDServicio tvServxE = new TVMEDServicio();
		TVMEDServicio tvMedServ = new TVMEDServicio();

		String iCveEquipo = "";
		String iCveAsignacion = "";

		if (request.getParameter("hdCampoClave") != null
				&& !request.getParameter("hdCampoClave").equals(""))
			iCveEquipo = request.getParameter("hdCampoClave").toString();
		if (request.getParameter("iCveAsignacion") != null
				&& !request.getParameter("iCveAsignacion").equals(""))
			iCveAsignacion = request.getParameter("iCveAsignacion").toString();

		try {
			if (!iCveEquipo.equals("") && !iCveAsignacion.equals("")) {
				String qxe = " where EqmServXEq.iCveEquipo=" + iCveEquipo
						+ " and EqmServXEq.iCveAsignacion=" + iCveAsignacion;
				String qxum = " where EqmAsignacion.iCveEquipo=" + iCveEquipo
						+ " and EqmAsignacion.iCveAsignacion=" + iCveAsignacion;
				String qxumma = " where EqmAsignacion.iCveEquipo=" + iCveEquipo
						+ " and EqmAsignacion.iCveAsignacion=" + iCveAsignacion;
				String where = "";

				if (!cOrden.equals(" order by E.iCveEquipo")
						&& !cCondicion.equals(" order by E.iCveEquipo")) {
					if (!cCondicion.equals("")) {
						where = " and " + cCondicion + " ";
					}
					where = where + cOrden;
				}

				vAsigna = dAsigna.FindByAllUniMedModArea(qxumma);
				vEquipo = dEQMEquipo.FindByAllEquipo(iCveEquipo);
				vServxEq = dServEq.FindByAllServXEq(qxe);
				vServxUniMed = dServUM.FindByAllSerxUM(qxum + where);

				if (vAsigna.size() > 0)
					tvAsignacion = (TVEQMAsignacion) vAsigna.get(0);
				else {
					tvAsignacion.setCDscUniMed("");
					tvAsignacion.setCDscModulo("");
					tvAsignacion.setCDscArea("");
				}

				for (int i = 0; i < vServxUniMed.size(); i++) {
					boolean agregada = false;
					tvServxE = (TVMEDServicio) vServxUniMed.get(i);
					for (int j = 0; j < vServxEq.size(); j++) {
						tvMedServ = (TVMEDServicio) vServxEq.get(j);
						if (tvServxE.getICveServicio() == tvMedServ
								.getICveServicio()) {
							vDespliega.add(tvMedServ);
							agregada = true;
							break;
						}
					}
					if (!agregada) {
						vDespliega.add(tvServxE);
					}
				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}