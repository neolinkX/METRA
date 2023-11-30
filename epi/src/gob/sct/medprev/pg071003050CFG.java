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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003050CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003050CFG.png'>
 */
public class pg071003050CFG extends CFGListBase2 {
	public pg071003050CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg071003051.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDMEDServicio dMEDServicio = new TDMEDServicio();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (cCondicion.compareTo("") != 0)
				cWhere += " where lActivo = 1 and " + cCondicion;
			else
				cWhere += " where lActivo = 1 ";

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}

			vDespliega = dMEDServicio.FindByAll(cWhere, cOrderBy);
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
			TVGRLServUM vGRLServUM = new TVGRLServUM();
			TDGRLServUM dGRLServUM = new TDGRLServUM();
			Vector vcGRLServUM = new Vector();

			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++) {
				if (request.getParameter("chk" + i) != null) {
					vcGRLServUM = dGRLServUM
							.FindByAll(" where iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and iCveModulo = "
									+ request.getParameter("iCveModulo")
									+ " and iCveServicio = "
									+ Integer.parseInt(request
											.getParameter("chk" + i)));
					if (vcGRLServUM.size() == 0) {
						// Inserta el Registro
						vGRLServUM.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLServUM.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLServUM.setICveServicio(Integer.parseInt(request
								.getParameter("chk" + i)));
						cClave = (String) dGRLServUM.insert(null, vGRLServUM);
					}
				} else {
					vcGRLServUM = dGRLServUM.FindByAll(" where iCveUniMed = "
							+ request.getParameter("iCveUniMed")
							+ " and iCveModulo = "
							+ request.getParameter("iCveModulo")
							+ " and iCveServicio = "
							+ request.getParameter("hdChk" + i));
					if (vcGRLServUM.size() > 0) {
						// Elimina el registro
						vGRLServUM.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLServUM.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLServUM.setICveServicio(Integer.parseInt(request
								.getParameter("hdChk" + i)));
						cClave = (String) dGRLServUM.delete(null, vGRLServUM);
					}
				}
			}

		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

}