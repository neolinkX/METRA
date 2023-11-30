package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuración para CFG de pg071003064.jsp
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003064.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003064.jsp.png'>
 */
public class pg071003064CFG extends CFGListBase2 {
	public pg071003064CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071003060.jsp|";
		UpdStatus = "SaveOnly";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDMEDRama dMEDRama = new TDMEDRama();
		try {
			String cWhere = "";

			if (request.getParameter("iCveServicio") != null
					&& request.getParameter("iCveServicio").compareTo("null") != 0
					&& request.getParameter("iCveServicio").compareTo("") != 0)
				cWhere += " where iCveServicio = "
						+ request.getParameter("iCveServicio");

			if (cWhere.compareTo("") == 0 && cCondicion.compareTo("") != 0)
				cWhere += " where " + cCondicion;
			else if (cCondicion.compareTo("") != 0)
				cWhere += " and " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else
				cWhere += " order by iCveRama ";
			if (request.getParameter("iCveServicio") != null
					&& request.getParameter("iCveServicio").compareTo("null") != 0
					&& request.getParameter("iCveServicio").compareTo("") != 0
					&& Integer.parseInt(request.getParameter("iCveServicio")) > 0)
				vDespliega = dMEDRama.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		try {
			TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
			TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
			Vector vcGRLUSUMedicos = new Vector();
			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++) {
				if (request.getParameter("chk" + i) != null) {
					vcGRLUSUMedicos = dGRLUSUMedicos
							.FindByAllSimple(" where iCveUsuario = "
									+ request.getParameter("iCveUsuario")
									+ " and iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and iCveProceso = "
									+ request.getParameter("iCveProceso")
									+ " and iCveModulo = "
									+ request.getParameter("iCveModulo")
									+ " and iCveServicio = "
									+ request.getParameter("iCveServicio")
									+ " and iCveRama = "
									+ request.getParameter("chk" + i));
					if (vcGRLUSUMedicos.size() == 0) {
						// Inserta el Registro
						vGRLUSUMedicos.setICveUsuario(Integer.parseInt(request
								.getParameter("iCveUsuario")));
						vGRLUSUMedicos.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLUSUMedicos.setICveProceso(Integer.parseInt(request
								.getParameter("iCveProceso")));
						vGRLUSUMedicos.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLUSUMedicos.setICveServicio(Integer.parseInt(request
								.getParameter("iCveServicio")));
						vGRLUSUMedicos.setICveRama(Integer.parseInt(request
								.getParameter("chk" + i)));
						cClave = (String) dGRLUSUMedicos.insert2(null,
								vGRLUSUMedicos);
					}
				} else {
					vcGRLUSUMedicos = dGRLUSUMedicos
							.FindByAllSimple(" where iCveUsuario = "
									+ request.getParameter("iCveUsuario")
									+ " and iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and iCveProceso = "
									+ request.getParameter("iCveProceso")
									+ " and iCveModulo = "
									+ request.getParameter("iCveModulo")
									+ " and iCveServicio = "
									+ request.getParameter("iCveServicio")
									+ " and iCveRama = "
									+ request.getParameter("hdChk" + i));
					if (vcGRLUSUMedicos.size() > 0) {
						// Elimina el registro
						vGRLUSUMedicos.setICveUsuario(Integer.parseInt(request
								.getParameter("iCveUsuario")));
						vGRLUSUMedicos.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLUSUMedicos.setICveProceso(Integer.parseInt(request
								.getParameter("iCveProceso")));
						vGRLUSUMedicos.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLUSUMedicos.setICveServicio(Integer.parseInt(request
								.getParameter("iCveServicio")));
						vGRLUSUMedicos.setICveRama(Integer.parseInt(request
								.getParameter("hdChk" + i)));
						cClave = (String) dGRLUSUMedicos.delete(null,
								vGRLUSUMedicos);
					}
				}
			}

		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Método Guardar

}
