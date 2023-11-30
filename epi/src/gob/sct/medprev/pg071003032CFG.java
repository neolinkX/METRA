package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003032CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003032CFG.png'>
 */
public class pg071003032CFG extends CFGListBase2 {
	public pg071003032CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg071003031.jsp|pg071003033.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDGRLArea dGRLArea = new TDGRLArea();
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

			vDespliega = dGRLArea.FindByAll(cWhere, cOrderBy);
			iNumReg = vDespliega.size();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		try {
			TVGRLAreaModulo vGRLAreaModulo = new TVGRLAreaModulo();
			TDGRLAreaModulo dGRLAreaModulo = new TDGRLAreaModulo();
			Vector vcGRLAreaModulo = new Vector();

			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++) {
				if (request.getParameter("chk" + i) != null) {
					vcGRLAreaModulo = dGRLAreaModulo
							.FindByAll(" where iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and iCveModulo = "
									+ request.getParameter("iCveModulo")
									+ " and iCveArea = "
									+ Integer.parseInt(request
											.getParameter("chk" + i)));
					if (vcGRLAreaModulo.size() == 0) {
						// Inserta el Registro
						vGRLAreaModulo.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLAreaModulo.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLAreaModulo.setICveArea(Integer.parseInt(request
								.getParameter("chk" + i)));
						vGRLAreaModulo.setCResponsable(request
								.getParameter("ttxt" + i));
						cClave = (String) dGRLAreaModulo.insert(null,
								vGRLAreaModulo);
					} else {
						// Actualiza el registro
						vGRLAreaModulo.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLAreaModulo.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLAreaModulo.setICveArea(Integer.parseInt(request
								.getParameter("hdChk" + i)));
						cClave = (String) dGRLAreaModulo.delete(null,
								vGRLAreaModulo);

						vGRLAreaModulo.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLAreaModulo.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLAreaModulo.setICveArea(Integer.parseInt(request
								.getParameter("chk" + i)));
						vGRLAreaModulo.setCResponsable(request
								.getParameter("ttxt" + i));
						cClave = (String) dGRLAreaModulo.insert(null,
								vGRLAreaModulo);
					}
				} else {
					vcGRLAreaModulo = dGRLAreaModulo
							.FindByAll(" where iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and iCveModulo = "
									+ request.getParameter("iCveModulo")
									+ " and iCveArea = "
									+ request.getParameter("hdChk" + i));
					if (vcGRLAreaModulo.size() > 0) {
						// Elimina el registro
						vGRLAreaModulo.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLAreaModulo.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLAreaModulo.setICveArea(Integer.parseInt(request
								.getParameter("hdChk" + i)));
						cClave = (String) dGRLAreaModulo.delete(null,
								vGRLAreaModulo);
					}
				}
			}

		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Método Guardar

}