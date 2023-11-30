package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Calibraci�n de Equipo - Consultas de
 * Asignaciones del Equipo
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070602012CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070602012CFG.png'>
 */
public class pg070602012CFG extends CFGListBase2 {
	public pg070602012CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070602010.jsp|pg070602011.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMAsignacion dEQMAsignacion = new TDEQMAsignacion();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveEquipo") != null
					&& request.getParameter("iCveEquipo").compareTo("") != 0)
				cWhere += " where EQMAsignacion.iCveEquipo = "
						+ request.getParameter("iCveEquipo");
			else {
				TDEQMAsignacion dEQMAsignacionT = new TDEQMAsignacion();
				TVEQMAsignacion vEQMAsignacionT = new TVEQMAsignacion();
				Vector vcEQMAsignacionT = new Vector();
				vcEQMAsignacionT = dEQMAsignacionT.FindDsc("", "");
				vEQMAsignacionT = (TVEQMAsignacion) vcEQMAsignacionT.get(0);
				cWhere += " where EQMAsignacion.iCveEquipo = "
						+ vEQMAsignacionT.getICveEquipo();
			}

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				if (cOrden.compareTo(" order by cModelo") == 0)
					cOrderBy = " order by iCveAsignacion ";
				else
					cOrderBy = cOrden;
			}

			vDespliega = dEQMAsignacion.FindDsc(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

}