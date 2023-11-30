package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Control de Veh�culos - Tipos de Mantenimiento
 * por Empresa
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003041CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003041CFG.png'>
 */
public class pg071003041CFG extends CFGListBase2 {
	public pg071003041CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg071003040.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (cCondicion.compareTo("") != 0)
				cWhere += " where GRLUniMed.lVigente = 1 and GRLUniMed.lVigente = 1 and "
						+ cCondicion;
			else
				cWhere += " where GRLUniMed.lVigente = 1 and GRLUniMed.lVigente = 1 ";

			if (cOrden.compareTo("") != 0)
				cOrderBy += cOrden;
			else
				cOrderBy += " order by GRLUniMed.cDscUniMed ";

			vDespliega = dGRLUniMed.FindByAll(cWhere, cOrderBy);
			iNumReg = vDespliega.size();
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
									+ request.getParameter("hdUniMed" + i)
									+ " and iCveProceso = "
									+ request.getParameter("iCveProceso"));
					if (vcGRLProcesoUM.size() == 0) {
						// Inserta el Registro
						vGRLProcesoUM.setICveUniMed(Integer.parseInt(request
								.getParameter("hdUniMed" + i)));
						vGRLProcesoUM.setICveProceso(Integer.parseInt(request
								.getParameter("iCveProceso")));
						vGRLProcesoUM.setLInterconsulta(Integer
								.parseInt(request.getParameter("chkl2" + i)));
						cClave = (String) dGRLProcesoUM.insert(null,
								vGRLProcesoUM);
					} else {
						// Actualiza el registro
						vGRLProcesoUM.setICveUniMed(Integer.parseInt(request
								.getParameter("hdUniMed" + i)));
						vGRLProcesoUM.setICveProceso(Integer.parseInt(request
								.getParameter("iCveProceso")));
						cClave = (String) dGRLProcesoUM.delete(null,
								vGRLProcesoUM);

						vGRLProcesoUM.setICveUniMed(Integer.parseInt(request
								.getParameter("hdUniMed" + i)));
						vGRLProcesoUM.setICveProceso(Integer.parseInt(request
								.getParameter("iCveProceso")));
						vGRLProcesoUM.setLInterconsulta(Integer
								.parseInt(request.getParameter("chkl2" + i)));
						cClave = (String) dGRLProcesoUM.insert(null,
								vGRLProcesoUM);
					}
				} else {
					vcGRLProcesoUM = dGRLProcesoUM
							.FindByAll(" where iCveUniMed = "
									+ request.getParameter("hdUniMed" + i)
									+ " and iCveProceso = "
									+ request.getParameter("iCveProceso"));
					if (vcGRLProcesoUM.size() > 0) {
						// Elimina el registro
						vGRLProcesoUM.setICveUniMed(Integer.parseInt(request
								.getParameter("hdUniMed" + i)));
						vGRLProcesoUM.setICveProceso(Integer.parseInt(request
								.getParameter("iCveProceso")));
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