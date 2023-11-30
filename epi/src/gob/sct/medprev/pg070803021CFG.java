package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para Detalle de la Solicitud
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070803021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803021CFG.png'>
 */
public class pg070803021CFG extends CFGListBase2 {
	public pg070803021CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070803020.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDALMSumArt dALMSumArt = new TDALMSumArt();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdIAnio") != null
					&& request.getParameter("hdIAnio").compareTo("null") != 0
					&& request.getParameter("hdIAnio").compareTo("") != 0)
				cWhere += " where ALMSumArt.iAnio = "
						+ request.getParameter("hdIAnio")
						+ " and ALMSumArt.iCveAlmacen = "
						+ request.getParameter("hdICveAlmacen")
						+ " and ALMSumArt.iCveSolicSum =  "
						+ request.getParameter("hdICveSolicSum") + " ";

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") == 0)
					cWhere = " where " + cCondicion;
				else
					cWhere = " and " + cCondicion;
			}

			if (cOrden.compareTo(" order by ALMSumArt.iCveArticulo") == 0)
				cOrderBy = " order by ALMTpoArticulo.iCveTpoArticulo, ALMFamilia.iCveFamilia, ALMSumArt.iCveArticulo ";
			else if (cOrden.compareTo(" order by ALMArticulo.cDscBreve") == 0)
				cOrderBy = " order by ALMTpoArticulo.iCveTpoArticulo, ALMFamilia.iCveFamilia, ALMArticulo.cDscBreve ";
			else
				cOrderBy = " order by ALMTpoArticulo.iCveTpoArticulo, ALMFamilia.iCveFamilia, ALMSumArt.iCveArticulo ";

			vDespliega = dALMSumArt.FindByAll2(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}