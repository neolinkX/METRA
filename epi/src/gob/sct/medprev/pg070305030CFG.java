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
 * @author <dd>Itzia Mar�a del Carmen S�nchez M�ndez.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305030.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305030.png'>
 */
public class pg070305030CFG extends CFGListBase2 {
	public Vector VRegistros = new Vector();

	public pg070305030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDExamenCualita dLote = new TDExamenCualita();
		cPaginas = "pg070305031.jsp|";
		boolean lWhere = false;
		try {

			if (cCondicion.compareToIgnoreCase("") != 0) {
				lWhere = true;
				cCondicion = " Where " + cCondicion;
			}

			if (request.getParameter("iAnio") != null) {
				if (lWhere)
					cCondicion += " And LC.iAnio = "
							+ request.getParameter("iAnio");
				else {
					cCondicion = "  WHERE LC.iAnio = "
							+ request.getParameter("iAnio");
					lWhere = true;
				}
			}

			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.compareTo("0") != 0) {
				if (lWhere)
					cCondicion += " And LC.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
				else {
					cCondicion = " Where LC.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
					lWhere = true;
				}
				if (request.getParameter("iCveLoteCualita") != null
						&& request.getParameter("iCveLoteCualita").toString()
								.length() > 0)
					if (lWhere)
						cCondicion += " And LC.iCveLoteCualita = "
								+ request.getParameter("iCveLoteCualita");
					else {
						cCondicion = " Where LC.iCveLoteCualita = "
								+ request.getParameter("iCveLoteCualita");
						lWhere = true;
					}

				if (request.getParameter("dtFechaI") != null
						&& request.getParameter("dtFechaF") != null
						&& request.getParameter("dtFechaI").toString().length() > 0
						&& request.getParameter("dtFechaF").toString().length() > 0) {
					int i = Integer.parseInt(request.getParameter("RSFecha")
							.toString(), 10);
					String Fecha = "";
					switch (i) {
					case 1:
						Fecha = " LC.dtGeneracion ";
						break;
					}
					TFechas TFecha = new TFechas();

					if (lWhere)
						cCondicion += " And "
								+ Fecha
								+ " between '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaI").toString())
								+ "'"
								+ " And '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaF").toString()) + "'";
					else {
						cCondicion += " Where "
								+ Fecha
								+ " between '"
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
					cOrden = " order by LC.iAnio, LC.iCveLaboratorio, LC.iCveLoteCualita ";
				cCondicion += " " + cOrden;
				vDespliega = dLote.LoteDtlle(cCondicion);
				VRegistros = vDespliega;
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}