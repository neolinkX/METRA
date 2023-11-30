package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import java.util.*;

/**
 * * Clase de configuracion para Clase para el Listado de la tabla CTRPersonal
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hern�ndez Garc�a
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070502034.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070502034.java.png'>
 */
public class pg070502034CFG extends CFGListBase2 {
	public Vector vAptitud = new Vector();

	public pg070502034CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070502035.jsp|pg070502032.jsp|pg070502050.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDCTRPersonal dCTRPersonal = new TDCTRPersonal();
		boolean lWhere = false;

		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}

			if (request.getParameter("hdIni") != null
					&& request.getParameter("hdIni").compareTo("null") != 0
					&& request.getParameter("hdIni").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND CTRPersonal.iCveEmpresa = "
							+ request.getParameter("hdIni");
				else
					cCondicion = " WHERE CTRPersonal.iCveEmpresa = "
							+ request.getParameter("hdIni");
			}

			if (request.getParameter("hdIni2") != null
					&& request.getParameter("hdIni2").compareTo("null") != 0
					&& request.getParameter("hdIni2").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND CTRPersonal.iCvePlantilla = "
							+ request.getParameter("hdIni2");
				else
					cCondicion += " AND CTRPersonal.iCvePlantilla = "
							+ request.getParameter("hdIni2");
			}

			vDespliega = dCTRPersonal.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public Vector fillAptitud(int iCveEmpresa, int iPlantilla, int iNumPersonal) {
		TDCTRPersonal dCTRPersonal = new TDCTRPersonal();
		boolean lWhere = false;
		vAptitud = null;
		String cCond2 = "";

		try {
			vAptitud = dCTRPersonal.FindWhereAptitud(iCveEmpresa, iPlantilla,
					iNumPersonal);
		} catch (Exception ex) {
			error("FillAptitud", ex);
		}
		return vAptitud;
	}
}
