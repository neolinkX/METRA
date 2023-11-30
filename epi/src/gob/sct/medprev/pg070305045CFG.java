package gob.sct.medprev;

import java.util.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.util.TFechas;

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
 *      <img src='pg070305045.png'>
 */
public class pg070305045CFG extends CFGListBase2 {
	public Vector VRegistros = new Vector();

	public Vector vDetalleC = new Vector();

	public pg070305045CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXCalibEquipo dCalib = new TDTOXCalibEquipo();
		boolean lWhere = false;
		try {

			if (cCondicion.compareToIgnoreCase("") != 0) {
				lWhere = true;
				cCondicion = " Where " + cCondicion;
			}

			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.compareTo("0") != 0) {
				/*
				 * if (lWhere) cCondicion += " And CE.iCveLaboratorio = " +
				 * request.getParameter("iCveLaboratorio"); else { cCondicion =
				 * " Where CE.iCveLaboratorio = " +
				 * request.getParameter("iCveLaboratorio"); lWhere = true; }
				 */
				if (request.getParameter("SLSEquipo") != null
						&& request.getParameter("SLSEquipo").toString()
								.compareTo("-1") != 0)
					if (lWhere)
						cCondicion += " And CE.iCveEquipo = "
								+ request.getParameter("SLSEquipo");
					else {
						cCondicion = " Where CE.iCveEquipo = "
								+ request.getParameter("SLSEquipo");
						lWhere = true;
					}

				if (request.getParameter("dtFechaI") != null
						&& request.getParameter("dtFechaF") != null
						&& request.getParameter("dtFechaI").toString().length() > 0
						&& request.getParameter("dtFechaF").toString().length() > 0) {
					TFechas TFecha = new TFechas();

					if (lWhere)
						cCondicion += " And CE.Fecha between '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaI").toString())
								+ "'"
								+ " And '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaF").toString()) + "'";
					else {
						cCondicion += " Where CE.Fecha between '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaI").toString())
								+ "'"
								+ " And '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaF").toString()) + "'";
						lWhere = true;
					}
				}
				if (cOrden.compareTo("") == 0)
					cOrden = " order by CE.iCveEquipo, CE.Fecha ASC ";
				cCondicion += " " + cOrden;
				vDespliega = dCalib.findCalibracion(cCondicion);
				VRegistros = vDespliega;
			}
			// Consultar el detalle de la calibraci�n
			if (this.cAccion.equalsIgnoreCase("Detalle")
					&& request.getParameter("iCveEquipo") != null
					&& request.getParameter("iCveCalib") != null) {
				cCondicion = " where C.iCveEquipo = "
						+ request.getParameter("iCveEquipo").toString()
						+ "   and C.iCveCalib  = "
						+ request.getParameter("iCveCalib").toString();
				this.vDetalleC = dCalib.findDetalleCalib(cCondicion);
				// System.out.println("Registros =" + this.vDetalleC.size());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}
}
