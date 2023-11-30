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
public class pg070601072CFG extends CFGListBase2 {
	public pg070601072CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg070601070.jsp|pg070601071.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMTpoMantto dEQMTpoMantto = new TDEQMTpoMantto();
		try {
			String cWhere = "";
			String cOrderBy = "";
			if (cCondicion.compareTo("") != 0)
				cWhere = " where lActivo = 1 and " + cCondicion;
			else
				cWhere = " where lActivo = 1 ";

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			vDespliega = dEQMTpoMantto.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			TVEQMMttoXEmp vEQMMttoXEmp = new TVEQMMttoXEmp();
			TDEQMMttoXEmp dEQMMttoXEmp = new TDEQMMttoXEmp();
			Vector vcEQMMttoXEmp = new Vector();

			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++)
				if (request.getParameter("chk" + i) != null) {
					vcEQMMttoXEmp = dEQMMttoXEmp.FindByAll(
							" where iCveEmpMtto = "
									+ request.getParameter("iCveEmpMtto")
									+ " and iCveTpoMantto = "
									+ Integer.parseInt(request
											.getParameter("chk" + i)), "");
					if (vcEQMMttoXEmp.size() == 0) {
						vEQMMttoXEmp.setICveEmpMtto(Integer.parseInt(request
								.getParameter("iCveEmpMtto")));
						vEQMMttoXEmp.setICveTpoMantto(Integer.parseInt(request
								.getParameter("chk" + i)));
						cClave = (String) dEQMMttoXEmp.insert(null,
								vEQMMttoXEmp);
					}
				} else {
					vcEQMMttoXEmp = dEQMMttoXEmp.FindByAll(
							" where iCveEmpMtto = "
									+ request.getParameter("iCveEmpMtto")
									+ " and iCveTpoMantto = "
									+ request.getParameter("hdChk" + i), "");
					if (vcEQMMttoXEmp.size() > 0) {
						vEQMMttoXEmp.setICveEmpMtto(Integer.parseInt(request
								.getParameter("iCveEmpMtto")));
						vEQMMttoXEmp.setICveTpoMantto(Integer.parseInt(request
								.getParameter("hdChk" + i)));
						cClave = (String) dEQMMttoXEmp.delete(null,
								vEQMMttoXEmp);
					}
				}
		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

}
