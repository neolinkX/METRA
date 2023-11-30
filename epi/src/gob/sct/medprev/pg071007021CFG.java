package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Control de Vehículos - Tipos de Mantenimiento
 * por Empresa
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071007021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071007021CFG.png'>
 */
public class pg071007021CFG extends CFGListBase2 {
	public pg071007021CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg071007020.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDGRLUsuario dGRLUsuario = new TDGRLUsuario();
		try {
			String cWhere = "";
			cWhere += " where U.iCveUsuario = "
					+ request.getParameter("hdCampoClave1");
			vDespliega = dGRLUsuario.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		try {
			TDGRLUsuario dGRLUsuario = new TDGRLUsuario();
			TVGRLUsuario vGRLUsuario = new TVGRLUsuario();
			vGRLUsuario.setICveUsuario(Integer.parseInt(request
					.getParameter("hdCampoClave1")));
			vGRLUsuario.setCApPaterno(request.getParameter("hdCampoClave2"));
			vGRLUsuario.setCApMaterno(request.getParameter("hdCampoClave3"));
			vGRLUsuario.setCNombre(request.getParameter("hdCampoClave4"));
			vGRLUsuario.setCRFC(request.getParameter("cRFC"));
			vGRLUsuario.setCCedula(request.getParameter("cCedula"));
			vGRLUsuario.setICveProfesion(Integer.parseInt(request
					.getParameter("iCveProfesion")));
			vGRLUsuario.setCSiglasProf(request.getParameter("cSiglasProf"));

			if ("SI".compareTo(request.getParameter("Nuevo").toString()) == 0)
				dGRLUsuario.insert(null, vGRLUsuario);
			else
				dGRLUsuario.update(null, vGRLUsuario);
		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Método Guardar

}
