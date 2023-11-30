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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070701082CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070701082CFG.png'>
 */
public class pg070701082CFG extends CFGListBase2 {
	public pg070701082CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg070701080.jsp|pg070701081.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDVEHTpoMantto dVEHTpoMantto = new TDVEHTpoMantto();
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
			vDespliega = dVEHTpoMantto.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		try {
			TVVEHManttoXEmp vVEHManttoXEmp = new TVVEHManttoXEmp();
			TDVEHManttoXEmp dVEHManttoXEmp = new TDVEHManttoXEmp();
			Vector vcVEHManttoXEmp = new Vector();

			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++)
				if (request.getParameter("chk" + i) != null) {
					vcVEHManttoXEmp = dVEHManttoXEmp.FindByAll(
							" where iCveEmpMantto = "
									+ request.getParameter("iCveEmpMantto")
									+ " and iCveTpoMantto = "
									+ Integer.parseInt(request
											.getParameter("chk" + i)), "");
					if (vcVEHManttoXEmp.size() == 0) {
						vVEHManttoXEmp
								.setICveEmpMantto(Integer.parseInt(request
										.getParameter("iCveEmpMantto")));
						vVEHManttoXEmp.setICveTpoMantto(Integer
								.parseInt(request.getParameter("chk" + i)));
						cClave = (String) dVEHManttoXEmp.insert(null,
								vVEHManttoXEmp);
					}
				} else {
					vcVEHManttoXEmp = dVEHManttoXEmp.FindByAll(
							" where iCveEmpMantto = "
									+ request.getParameter("iCveEmpMantto")
									+ " and iCveTpoMantto = "
									+ request.getParameter("hdChk" + i), "");
					if (vcVEHManttoXEmp.size() > 0) {
						vVEHManttoXEmp
								.setICveEmpMantto(Integer.parseInt(request
										.getParameter("iCveEmpMantto")));
						vVEHManttoXEmp.setICveTpoMantto(Integer
								.parseInt(request.getParameter("hdChk" + i)));
						cClave = (String) dVEHManttoXEmp.delete(null,
								vVEHManttoXEmp);
					}
				}
		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Método Guardar

}