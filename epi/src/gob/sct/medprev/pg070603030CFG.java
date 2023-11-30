package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.util.TFechas;

/**
 * * Clase de configuración para Clase para listar la Información de la tabla
 * EQMMantenimiento
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hernandez García
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg070603030CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070603030CFG.java.png'>
 */
public class pg070603030CFG extends CFGListBase2 {
	private TFechas pFecha = new TFechas("01");

	public pg070603030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDEQMMantenimiento dEQMMantenimiento = new TDEQMMantenimiento();
		boolean lWhere = false;
		String cTempDe, cTempA;
		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}

			if (request.getParameter("lSolicitados") != null
					&& request.getParameter("lSolicitados").compareTo("1") == 0) {
				if (request.getParameter("dtDeSol") != null
						&& request.getParameter("dtDeSol").compareTo("null") != 0
						&& request.getParameter("dtDeSol").compareTo("") != 0
						&& request.getParameter("dtASol") != null
						&& request.getParameter("dtASol").compareTo("null") != 0
						&& request.getParameter("dtASol").compareTo("") != 0) {
					cTempDe = pFecha.getFechaYYYYMMDD(
							pFecha.getDateSQL(request.getParameter("dtDeSol")),
							"-");
					cTempA = pFecha.getFechaYYYYMMDD(
							pFecha.getDateSQL(request.getParameter("dtASol")),
							"-");
					if (lWhere)
						cCondicion += " AND (EQMMantenimiento.dtSolicitud >= '"
								+ cTempDe
								+ "' AND EQMMantenimiento.dtSolicitud <= '"
								+ cTempA + "') ";
					else {
						cCondicion = " WHERE (EQMMantenimiento.dtSolicitud >= '"
								+ cTempDe
								+ "' AND EQMMantenimiento.dtSolicitud <= '"
								+ cTempA + "') ";
						lWhere = true;
					}
				}
			}

			if (request.getParameter("lProgramados") != null
					&& request.getParameter("lProgramados").compareTo("1") == 0) {
				if (request.getParameter("dtDePro") != null
						&& request.getParameter("dtDePro").compareTo("null") != 0
						&& request.getParameter("dtDePro").compareTo("") != 0
						&& request.getParameter("dtAPro") != null
						&& request.getParameter("dtAPro").compareTo("null") != 0
						&& request.getParameter("dtAPro").compareTo("") != 0) {
					cTempDe = pFecha.getFechaYYYYMMDD(
							pFecha.getDateSQL(request.getParameter("dtDeSol")),
							"-");
					cTempA = pFecha.getFechaYYYYMMDD(
							pFecha.getDateSQL(request.getParameter("dtASol")),
							"-");
					if (lWhere)
						cCondicion += " AND (EQMMantenimiento.dtProgramado >= '"
								+ cTempDe
								+ "' AND EQMMantenimiento.dtProgramado <= '"
								+ cTempA + "') ";
					else {
						cCondicion = " WHERE (EQMMantenimiento.dtProgramado >= '"
								+ cTempDe
								+ "' AND EQMMantenimiento.dtProgramado <= '"
								+ cTempA + "') ";
						lWhere = true;
					}
				}
			}

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("") != 0
					&& request.getParameter("iCveUniMed").compareTo("-1") != 0
					&& request.getParameter("iCveUniMed").compareTo("0") != 0) {
				if (lWhere)
					cCondicion += " AND EQMAsignacion.iCveUniMed = "
							+ request.getParameter("iCveUniMed");
				else {
					cCondicion = " WHERE EQMAsignacion.iCveUniMed = "
							+ request.getParameter("iCveUniMed");
					lWhere = true;
				}
			}

			if (request.getParameter("iCveModulo") != null
					&& request.getParameter("iCveModulo").compareTo("") != 0
					&& request.getParameter("iCveModulo").compareTo("-1") != 0
					&& request.getParameter("iCveModulo").compareTo("0") != 0) {
				if (lWhere)
					cCondicion += " AND EQMAsignacion.iCveModulo = "
							+ request.getParameter("iCveModulo");
				else {
					cCondicion = " WHERE EQMAsignacion.iCveModulo = "
							+ request.getParameter("iCveModulo");
					lWhere = true;
				}
			}

			if (request.getParameter("iCveArea") != null
					&& request.getParameter("iCveArea").compareTo("") != 0
					&& request.getParameter("iCveArea").compareTo("-1") != 0
					&& request.getParameter("iCveArea").compareTo("0") != 0) {
				if (lWhere)
					cCondicion += " AND EQMAsignacion.iCveArea = "
							+ request.getParameter("iCveArea");
				else {
					cCondicion = " WHERE EQMAsignacion.iCveArea = "
							+ request.getParameter("iCveArea");
					lWhere = true;
				}
			}
			if (cOrden.compareTo("") != 0)
				cOrden = " ORDER BY EQMMantenimiento.iCveMantenimiento";
			vDespliega = dEQMMantenimiento.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
