package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;
import java.text.*;

/**
 * * Clase de configuracion para Calibraci�n de Equipo - Consultas de
 * Asignaciones del Equipo
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070603010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070603010CFG.png'>
 */
public class pg070603010CFG extends CFGListBase2 {
	public pg070603010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("lMostrarDatos") != null
					&& request.getParameter("lMostrarDatos").compareTo(
							"verdadero") == 0) {

				if (request.getParameter("chkClasificacion") != null)
					cWhere += " where EQMEquipo.iCveClasificacion = "
							+ request.getParameter("iCveClasificacion");

				if (request.getParameter("chkTpoEquipo") != null)
					cWhere += " and EQMEquipo.iCveTpoEquipo = "
							+ request.getParameter("iCveTpoEquipo");

				if (request.getParameter("chkMarca") != null) {
					if (cWhere.compareTo("") == 0)
						cWhere += " where EQMEquipo.iCveMarca = "
								+ request.getParameter("iCveMarca");
					else
						cWhere += " and EQMEquipo.iCveMarca = "
								+ request.getParameter("iCveMarca");
				}

				if (request.getParameter("chkUniMed") != null) {
					if (cWhere.compareTo("") == 0)
						cWhere += " where GRLUniMed.iCveUniMed = "
								+ request.getParameter("iCveUniMed");
					else
						cWhere += " and GRLUniMed.iCveUniMed = "
								+ request.getParameter("iCveUniMed");
				}

				if (request.getParameter("chkModulo") != null)
					cWhere += " and GRLModulo.iCveModulo = "
							+ request.getParameter("iCveModulo");

				if (request.getParameter("chkArea") != null)
					cWhere += " and GRLArea.iCveArea = "
							+ request.getParameter("iCveArea");

				if (cCondicion.compareTo("") != 0)
					cWhere += " and " + cCondicion;

				cWhere += " and lActual = 1 ";

				if (cOrden.compareTo("") != 0)
					cOrderBy = cOrden;

				if (request.getParameter("lProgramar") != null
						&& request.getParameter("lProgramar").compareTo(
								"verdadero") == 0) {
					GregorianCalendar dtHoy = new GregorianCalendar();
					java.sql.Date dtSolicitud = null;
					dtSolicitud = dtSolicitud.valueOf(dtHoy.get(Calendar.YEAR)
							+ "-" + (dtHoy.get(Calendar.MONTH) + 1) + "-"
							+ dtHoy.get(Calendar.DAY_OF_MONTH));

					java.sql.Date dtProgramado = null;
					StringTokenizer sTk = new StringTokenizer(
							request.getParameter("dtProgramado"), "/");
					String cDia = sTk.nextToken();
					String cMes = sTk.nextToken();
					String cAnio = sTk.nextToken();
					dtProgramado = dtProgramado.valueOf(cAnio + "-" + cMes
							+ "-" + cDia);

					String cDtFechaTmp = "";
					SimpleDateFormat formato = new SimpleDateFormat(
							"dd/MM/yyyy");
					cDtFechaTmp = formato.format(dtProgramado);

					for (int i = 1; i < Integer.parseInt(request
							.getParameter("hdTotalReg")); i++)
						if (request.getParameter("chkProg" + i) != null) {
							TDEQMMantenimiento dEQMMantenimiento = new TDEQMMantenimiento();
							Vector vcEQMMantenimiento = new Vector();
							vcEQMMantenimiento = dEQMMantenimiento
									.FindByAll(" where EQMMantenimiento.iCveEquipo = "
											+ request.getParameter("iCveEquipo"
													+ i)
											+ " and EQMMantenimiento.iCveTpoMantto = "
											+ request
													.getParameter("iCveTpoMantto")
											+ " and EQMMantenimiento.iCveMotivo = "
											+ request
													.getParameter("iCveMotivo")
											+ " and EQMMantenimiento.dtProgramado = '"
											+ cDtFechaTmp + "' ");

							if (vcEQMMantenimiento.size() == 0) {
								TVEQMMantenimiento vEQMMantenimiento = new TVEQMMantenimiento();
								vEQMMantenimiento
										.setICveTpoMantto(Integer.parseInt(request
												.getParameter("iCveTpoMantto")));
								vEQMMantenimiento.setICveMotivo(Integer
										.parseInt(request
												.getParameter("iCveMotivo")));
								vEQMMantenimiento
										.setICveEquipo(Integer.parseInt(request
												.getParameter("iCveEquipo" + i)));
								vEQMMantenimiento.setDtSolicitud(dtSolicitud);
								vEQMMantenimiento.setDtProgramado(dtProgramado);
								vEQMMantenimiento.setICveUsuSolicita(Integer
										.parseInt(request
												.getParameter("hdUser")));
								cClave = (String) dEQMMantenimiento.insert(
										null, vEQMMantenimiento);
							}
						}
				}

				vDespliega = dEQMEquipo.FindDsc(cWhere, cOrderBy);

			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

}