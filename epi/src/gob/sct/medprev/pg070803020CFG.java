package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Clase para la tabla ALMSolicSumin
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hernández García
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg070803020CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803020CFG.java.png'>
 */
public class pg070803020CFG extends CFGListBase2 {
	public pg070803020CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070803021.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();
		try {
			String cWhere = "";
			String cOrderBy = "";
			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveModulo") != null
					&& request.getParameter("iCveArea") != null
					&& request.getParameter("iAnio") != null
					&& request.getParameter("iCvePeriodo") != null) {
				cWhere += "where ALMSolicSumin.iCveUniMed = "
						+ request.getParameter("iCveUniMed")
						+ " and ALMSolicSumin.iCveModulo = "
						+ request.getParameter("iCveModulo");

				if (Integer.parseInt(request.getParameter("iCveArea")
						.toString()) > 0)
					cWhere += " and ALMSolicSumin.iCveArea = "
							+ request.getParameter("iCveArea");
				cWhere += " and ALMSolicSumin.iAnio = "
						+ request.getParameter("iAnio");

				if (Integer.parseInt(request.getParameter("iCvePeriodo")
						.toString()) > 0)
					cWhere += " and ALMSolicSumin.iCvePeriodo = "
							+ request.getParameter("iCvePeriodo");
				if (request.getParameter("chkProgExt") != null
						&& request.getParameter("chkProgExt").compareTo(
								"Programada") == 0)
					cWhere += " and lProgramada = 1";
				else
					cWhere += " and lProgramada = 0";

				cWhere += " ";
			} else
				cWhere += " where 1=0 ";

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") == 0)
					cWhere += " where " + cCondicion;
				else
					cWhere += " and " + cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy += cOrden;
			else
				cOrderBy += " order by iCveSolicSum ";

			// if(request.getParameter("iCveUniMed")!=null &&
			// request.getParameter("iCveModulo")!=null &&
			// request.getParameter("iCveArea")!=null &&
			// request.getParameter("iAnio")!=null &&
			// request.getParameter("iCvePeriodo")!=null)
			vDespliega = dALMSolicSumin.FindByAll3(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}

		if (vDespliega.isEmpty())
			NavStatus = "Disabled";
	}
}
