package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para CFG de pg071003061.jsp
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003061.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003061.jsp.png'>
 */
public class pg071003065CFG extends CFGListBase2 {
	public pg071003065CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071003060.jsp|";
		UpdStatus = "SaveOnly";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLProcesoUM dGRLProcesoUM = new TDGRLProcesoUM();
		try {
			String cWhere = "";

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("null") != 0
					&& request.getParameter("iCveUniMed").compareTo("") != 0)
				cWhere += " where GRLProcesoUM.iCveUniMed = "
						+ request.getParameter("iCveUniMed");

			if (cWhere.compareTo("") == 0 && cCondicion.compareTo("") != 0)
				cWhere += " where " + cCondicion;
			else if (cCondicion.compareTo("") != 0)
				cWhere += " and " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else
				cWhere += " order by iCveProceso";
			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("null") != 0
					&& request.getParameter("iCveUniMed").compareTo("") != 0
					&& Integer.parseInt(request.getParameter("iCveUniMed")) > 0)
				vDespliega = dGRLProcesoUM.FindDsc(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			TVGRLUMUsuarioMP vGRLUMUsuario = new TVGRLUMUsuarioMP();
			TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
			Vector vcGRLUMUsuario = new Vector();

			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++) {
				if (request.getParameter("chk" + i) != null) {
					vcGRLUMUsuario = dGRLUMUsuario
							.FindByAllSimple(" where iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and iCveUsuario = "
									+ request.getParameter("iCveUsuario")
									+ " and iCveProceso = "
									+ Integer.parseInt(request
											.getParameter("chk" + i)));
					if (vcGRLUMUsuario.size() == 0) {
						// Inserta el Registro
						vGRLUMUsuario.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLUMUsuario.setICveUsuario(Integer.parseInt(request
								.getParameter("iCveUsuario")));
						vGRLUMUsuario.setICveProceso(Integer.parseInt(request
								.getParameter("chk" + i)));
						cClave = (String) dGRLUMUsuario.insert2(null,
								vGRLUMUsuario);
					}
				} else {
					vcGRLUMUsuario = dGRLUMUsuario
							.FindByAllSimple(" where iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and iCveUsuario = "
									+ request.getParameter("iCveUsuario")
									+ " and iCveProceso = "
									+ request.getParameter("hdChk" + i));
					if (vcGRLUMUsuario.size() > 0) {
						// Elimina el registro
						vGRLUMUsuario.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLUMUsuario.setICveUsuario(Integer.parseInt(request
								.getParameter("iCveUsuario")));
						vGRLUMUsuario.setICveProceso(Integer.parseInt(request
								.getParameter("hdChk" + i)));
						cClave = (String) dGRLUMUsuario.delete2(null,
								vGRLUMUsuario);
					}
				}
			}

		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

}
