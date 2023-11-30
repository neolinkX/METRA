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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003033CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003033CFG.png'>
 */
public class pg071003033CFG extends CFGListBase2 {
	public pg071003033CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg071003020.jsp|pg071003021.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLModulo dGRLModulo = new TDGRLModulo();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (cCondicion.compareTo("") != 0)
				cWhere += " where GRLUniMed.lVigente = 1 and GRLModulo.lVigente = 1 and "
						+ cCondicion;
			else
				cWhere += " where GRLUniMed.lVigente = 1 and GRLModulo.lVigente = 1 ";

			if (cOrden.compareTo("") != 0)
				cOrderBy += cOrden;
			else
				cOrderBy += " order by GRLUniMed.cDscUniMed ";

			vDespliega = dGRLModulo.FindDsc(cWhere, cOrderBy);
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

			TVGRLAreaModulo vGRLAreaModulo = new TVGRLAreaModulo();
			TDGRLAreaModulo dGRLAreaModulo = new TDGRLAreaModulo();
			Vector vcGRLAreaModulo = new Vector();

			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++) {
				if (request.getParameter("chk" + i) != null) {
					vcGRLAreaModulo = dGRLAreaModulo
							.FindByAll(" where iCveUniMed = "
									+ request.getParameter("hdUniMed" + i)
									+ " and iCveModulo = "
									+ request.getParameter("hdModulo" + i)
									+ " and iCveArea = "
									+ request.getParameter("iCveArea"));
					if (vcGRLAreaModulo.size() == 0) {
						// Inserta el Registro
						vGRLAreaModulo.setICveUniMed(Integer.parseInt(request
								.getParameter("hdUniMed" + i)));
						vGRLAreaModulo.setICveModulo(Integer.parseInt(request
								.getParameter("hdModulo" + i)));
						vGRLAreaModulo.setICveArea(Integer.parseInt(request
								.getParameter("iCveArea")));
						vGRLAreaModulo.setCResponsable(request
								.getParameter("txt" + i));
						cClave = (String) dGRLAreaModulo.insert(null,
								vGRLAreaModulo);
					} else {
						// Actualiza el registro
						vGRLAreaModulo.setICveUniMed(Integer.parseInt(request
								.getParameter("hdUniMed" + i)));
						vGRLAreaModulo.setICveModulo(Integer.parseInt(request
								.getParameter("hdModulo" + i)));
						vGRLAreaModulo.setICveArea(Integer.parseInt(request
								.getParameter("iCveArea")));
						cClave = (String) dGRLAreaModulo.delete(null,
								vGRLAreaModulo);

						vGRLAreaModulo.setICveUniMed(Integer.parseInt(request
								.getParameter("hdUniMed" + i)));
						vGRLAreaModulo.setICveModulo(Integer.parseInt(request
								.getParameter("hdModulo" + i)));
						vGRLAreaModulo.setICveArea(Integer.parseInt(request
								.getParameter("iCveArea")));
						vGRLAreaModulo.setCResponsable(request
								.getParameter("txt" + i));
						cClave = (String) dGRLAreaModulo.insert(null,
								vGRLAreaModulo);
					}
				} else {
					vcGRLAreaModulo = dGRLAreaModulo
							.FindByAll(" where iCveUniMed = "
									+ request.getParameter("hdUniMed" + i)
									+ " and iCveModulo = "
									+ request.getParameter("hdModulo" + i)
									+ " and iCveArea = "
									+ request.getParameter("iCveArea"));
					if (vcGRLAreaModulo.size() > 0) {
						// Elimina el registro
						vGRLAreaModulo.setICveUniMed(Integer.parseInt(request
								.getParameter("hdUniMed" + i)));
						vGRLAreaModulo.setICveModulo(Integer.parseInt(request
								.getParameter("hdModulo" + i)));
						vGRLAreaModulo.setICveArea(Integer.parseInt(request
								.getParameter("iCveArea")));
						cClave = (String) dGRLAreaModulo.delete(null,
								vGRLAreaModulo);
					}
				}
			}

		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

}