package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Listado de Tipo de Mantenimiento por Empresa
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070501050CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070501050CFG.png'>
 */
public class pg071003040CFG extends CFGListBase2 {
	public pg071003040CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg071003041.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLProceso dGRLProceso = new TDGRLProceso();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (cCondicion.compareTo("") != 0)
				cWhere = " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by iCveProceso ";

			vDespliega = dGRLProceso.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			TVGRLProcesoUM vGRLProcesoUM = new TVGRLProcesoUM();
			TDGRLProcesoUM dGRLProcesoUM = new TDGRLProcesoUM();
			Vector vcGRLProcesoUM = new Vector();
			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++) {
				if (request.getParameter("chk" + i) != null) {
					vcGRLProcesoUM = dGRLProcesoUM
							.FindByAll(" where iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and iCveProceso = "
									+ Integer.parseInt(request
											.getParameter("chk" + i)));
					if (vcGRLProcesoUM.size() == 0) {
						// Insertar
						vGRLProcesoUM.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLProcesoUM.setICveProceso(Integer.parseInt(request
								.getParameter("chk" + i)));
						if (request.getParameter("ttxt" + i) != null)
							vGRLProcesoUM.setLInterconsulta(Integer
									.parseInt(request.getParameter("txt" + i)));
						if (request.getParameter("chkO" + i) != null)
							vGRLProcesoUM.setLInterconsulta(1);
						cClave = (String) dGRLProcesoUM.insert(null,
								vGRLProcesoUM);
					} else {
						// Modificar
						vGRLProcesoUM.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLProcesoUM.setICveProceso(Integer.parseInt(request
								.getParameter("hdChk" + i)));
						cClave = (String) dGRLProcesoUM.delete(null,
								vGRLProcesoUM);

						vGRLProcesoUM.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLProcesoUM.setICveProceso(Integer.parseInt(request
								.getParameter("chk" + i)));
						if (request.getParameter("ttxt" + i) != null)
							vGRLProcesoUM.setLInterconsulta(Integer
									.parseInt(request.getParameter("txt" + i)));
						if (request.getParameter("chkO" + i) != null)
							vGRLProcesoUM.setLInterconsulta(1);
						cClave = (String) dGRLProcesoUM.insert(null,
								vGRLProcesoUM);
					}
				} else {
					// Eliminar
					vcGRLProcesoUM = dGRLProcesoUM
							.FindByAll(" where iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and iCveProceso = "
									+ request.getParameter("hdChk" + i));
					if (vcGRLProcesoUM.size() > 0) {
						vGRLProcesoUM.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLProcesoUM.setICveProceso(Integer.parseInt(request
								.getParameter("hdChk" + i)));
						cClave = (String) dGRLProcesoUM.delete(null,
								vGRLProcesoUM);
					}
				}
			}
		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

}
