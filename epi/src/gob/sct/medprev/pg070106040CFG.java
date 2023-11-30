package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.util.*;

/**
 * * Clase de configuracion para Control de Veh�culos - Listado de Modelos por
 * Marca
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070701030CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070701030CFG.png'>
 */
public class pg070106040CFG extends CFGListBase2 {
	public pg070106040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070106041.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDPERCatalogoNoAp dPERCatalogoNoAp = new TDPERCatalogoNoAp();
		TFechas Fecha = new TFechas();

		try {
			String cWhere = "";
			String ClavePer = "";
			String VigenT = "";
			String cOrderBy = "";

			if (request.getParameter("iCvePersonal") != null
					&& request.getParameter("iCvePersonal").compareTo("null") != 0
					&& request.getParameter("iCvePersonal").compareTo("") != 0) {
				// cWhere += " where PERCatalogoNoAp.iCvePersonal = " +
				// request.getParameter("iCvePersonal");
				ClavePer += " where EA.ICVEEXPEDIENTE =  "
						+ request.getParameter("iCvePersonal") + " ";
				// System.out.println("TIENE CLAVE PERSONAL");
			} else {

				if (request.getParameter("dtFechaDe") != null
						&& request.getParameter("dtFechaDe").compareTo("") != 0
						&& request.getParameter("dtFechaA") != null
						&& request.getParameter("dtFechaA").compareTo("") != 0)
					cWhere += " WHERE EA.DTSOLICITADO >= '"
							+ Fecha.getFechaYYYYMMDD(Fecha.getDateSQL(request
									.getParameter("dtFechaDe")), "-")
							+ "' AND EA.DTSOLICITADO <='"
							+ Fecha.getFechaYYYYMMDD(Fecha.getDateSQL(request
									.getParameter("dtFechaA")), "-") + "' ";

				if (request.getParameter("lVigente") != null
						&& request.getParameter("lVigente").toString()
								.compareTo("on") == 0) {
					VigenT += "  PCN.lVigente = 1 and";
					// System.out.println("VIGENTE");
				}/*
				 * else // System.out.println("NO VIGENTE"); //cWhere +=
				 * " and PERCatalogoNoAp.lVigente = 0 ";
				 */
				if (cCondicion.compareTo("") != 0) {
					if (cWhere.compareTo("") != 0) {
						cWhere += " AND EA." + cCondicion;
						// System.out.println("TIene condicion");
					} else {
						cWhere += " where EA." + cCondicion;
						// System.out.println("NO TIENE CONDICION");
					}
				}

				if (cOrden.compareTo("") != 0) {
					cOrderBy = cOrden;
				}
			}

			// PARA AGREGAR LAS CONDICIONES AL WHERE
			cWhere += " " + ClavePer + "AND "
					+ " EA.ICVEEXPEDIENTE = PE.ICVEEXPEDIENTE  AND"
					+ " EA.ICVEEXPEDIENTE = EC.ICVEEXPEDIENTE   AND"
					+ " EA.ICVEEXPEDIENTE = EXP.ICVEEXPEDIENTE   AND"
					+ " EA.INUMEXAMEN = EC.INUMEXAMEN    AND"
					+ " EA.INUMEXAMEN = EXP.INUMEXAMEN    AND"
					+ " EC.ICVEMOTIVO = GM.ICVEMOTIVO    AND"
					+ " EA.ICVEPROCESO = GM.ICVEPROCESO   AND"
					+ " EC.ICVEMDOTRANS = GT.ICVEMDOTRANS  AND"
					+ " EC.ICVEMDOTRANS = GC.ICVEMDOTRANS  AND"
					+ " EC.ICVECATEGORIA = GC.ICVECATEGORIA  AND"
					+ " EA.ICVEUNIMED = GU.ICVEUNIMED   AND"
					+ " EXP.ICVEESPECIALIDAD = MD.ICVEESPECIALIDAD AND"
					+ " EXP.ICVEDIAGNOSTICO = MD.ICVEDIAGNOSTICO AND"
					+ " EXP.ICVESERVICIO = 31    AND"
					+ " EA.ICVEEXPEDIENTE > 1    AND"
					+ " EA.LDICTAMINADO = 1    AND" + " EC.LDICTAMEN = 0 AND"
					+ VigenT + " EA.ICVEEXPEDIENTE = PCN.ICVEPERSONAL  AND"
					+ " EC.ICVEMDOTRANS = PCN.ICVEMDOTRANS  " + " GROUP BY"
					+ " EXP.TIDIAGNOSTICO," + " EA.ICVEEXPEDIENTE,"
					+ " EA.INUMEXAMEN," + " PE.CNOMBRE," + " PE.CAPPATERNO,"
					+ " PE.CAPMATERNO," + " EA.DTSOLICITADO,"
					+ " GU.CDSCUNIMED," + " EA.DTDICTAMEN," + " GM.CDSCMOTIVO,"
					+ " GT.CDSCMDOTRANS," + " GC.CDSCCATEGORIA,"
					+ " MD.CDSCDIAGNOSTICO, " + " GC.iCveCategoria, "
					+ " GC.iCveMdoTrans";
			/*
			 * AND" + " EXP.TIDIAGNOSTICO = " +
			 * "     (SELECT MIN(TIDIAGNOSTICO)" +
			 * "      FROM     EXPDIAGNOSTICO AS EXP2" + "      WHERE" +
			 * "         EXP.ICVEEXPEDIENTE = EXP2.ICVEEXPEDIENTE  AND" +
			 * "         EXP.INUMEXAMEN = EXP2.INUMEXAMEN  AND" +
			 * "         EXP2.ICVESERVICIO = 31)";
			 */

			if (request.getParameter("dtFechaDe") != null
					&& request.getParameter("dtFechaDe").compareTo("") != 0
					&& request.getParameter("dtFechaA") != null
					&& request.getParameter("dtFechaA").compareTo("") != 0)
				vDespliega = dPERCatalogoNoAp.FindDsc(cWhere, cOrderBy);

			if (vDespliega != null) {
				if (vDespliega.isEmpty())
					NavStatus = "Disabled";
				else
					NavStatus = "FirstRecord";
			} else
				NavStatus = "Disabled";

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}