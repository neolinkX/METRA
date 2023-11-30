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
public class pg071006020CFG extends CFGListBase2 {
	public pg071006020CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg070601070.jsp|pg070601071.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLRequisito dGRLRequisito = new TDGRLRequisito();
		try {
			String cWhere = "";
			String cOrderBy = "";
			if (cCondicion.compareTo("") != 0)
				cWhere = " where lActivo = 1 and " + cCondicion;
			else
				cWhere = " where lActivo = 1 ";

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by cDscRequisito ";
			vDespliega = dGRLRequisito.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			TVGRLReqxMotivo vGRLReqxMotivo = new TVGRLReqxMotivo();
			TDGRLReqxMotivo dGRLReqxMotivo = new TDGRLReqxMotivo();
			Vector vcGRLReqXMotivo = new Vector();
			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++) {
				if (request.getParameter("chk" + i) != null) {
					vcGRLReqXMotivo = dGRLReqxMotivo.FindByAll(
							" where iCveMotivo = "
									+ request.getParameter("iCveMotivo")
									+ " and iCveRequisito = "
									+ Integer.parseInt(request
											.getParameter("chk" + i)), "");
					if (vcGRLReqXMotivo.size() == 0) {
						vGRLReqxMotivo.setICveMotivo(Integer.parseInt(request
								.getParameter("iCveMotivo")));
						vGRLReqxMotivo.setICveRequisito(Integer
								.parseInt(request.getParameter("chk" + i)));
						if (request.getParameter("ttxt" + i) != null)
							vGRLReqxMotivo.setICopias(Integer.parseInt(request
									.getParameter("txt" + i)));
						if (request.getParameter("chkO" + i) != null)
							vGRLReqxMotivo.setLObligatorio(1);
						cClave = (String) dGRLReqxMotivo.insert(null,
								vGRLReqxMotivo);
					} else {
						vGRLReqxMotivo.setICveMotivo(Integer.parseInt(request
								.getParameter("iCveMotivo")));
						vGRLReqxMotivo.setICveRequisito(Integer
								.parseInt(request.getParameter("hdChk" + i)));
						cClave = (String) dGRLReqxMotivo.delete(null,
								vGRLReqxMotivo);

						vGRLReqxMotivo.setICveMotivo(Integer.parseInt(request
								.getParameter("iCveMotivo")));
						vGRLReqxMotivo.setICveRequisito(Integer
								.parseInt(request.getParameter("chk" + i)));
						if (request.getParameter("ttxt" + i) != null)
							vGRLReqxMotivo.setICopias(Integer.parseInt(request
									.getParameter("txt" + i)));
						if (request.getParameter("chkO" + i) != null)
							vGRLReqxMotivo.setLObligatorio(1);
						cClave = (String) dGRLReqxMotivo.insert(null,
								vGRLReqxMotivo);
					}
				} else {
					vcGRLReqXMotivo = dGRLReqxMotivo.FindByAll(
							" where iCveMotivo = "
									+ request.getParameter("iCveMotivo")
									+ " and iCveRequisito = "
									+ request.getParameter("hdChk" + i), "");
					if (vcGRLReqXMotivo.size() > 0) {
						vGRLReqxMotivo.setICveMotivo(Integer.parseInt(request
								.getParameter("iCveMotivo")));
						vGRLReqxMotivo.setICveRequisito(Integer
								.parseInt(request.getParameter("hdChk" + i)));
						cClave = (String) dGRLReqxMotivo.delete(null,
								vGRLReqxMotivo);
					}
				}
			}
		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

}
