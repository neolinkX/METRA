package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Clase de configuracion para Listado de
 * EXPExamAplica
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>AG SA SANDOVAL
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070105000CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070108010CFG.png'>
 */
public class pg070108010CFG extends CFGListBase2 {
	public pg070108010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			// vDespliega = new TDEXPExamAplica().FindByAll(getParameters());
			int fecha1 = request.getParameter("dtConcluido").length();
			int fecha2 = request.getParameter("dtConcluido2").length();
			// System.out.println(fecha1 +" --FECHAS--   "+fecha2);
			if (fecha1 > 9 && fecha2 > 9) {
				vDespliega = new TDTSEGEXAR().FindByAll(getParameters());
				iNumReg = vDespliega.size();
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo getParameters
	 */
	public HashMap getParameters() {
		HashMap hmTmp = new HashMap();

		String cTmp = request.getParameter("iCveUniMed");
		hmTmp.put("iCveUniMed", cTmp != null ? cTmp : "0");
		cTmp = request.getParameter("iCveModulo");
		hmTmp.put("iCveModulo", cTmp != null ? cTmp : "0");
		cTmp = request.getParameter("iCveProceso");
		hmTmp.put("iCveProceso", cTmp != null ? cTmp : "0");
		cTmp = request.getParameter("dtConcluido");
		java.sql.Date dtTmp = new java.sql.Date(new Date().getTime());
		if (cTmp != null && cTmp.length() != 0)
			dtTmp = new TFechas().getDateSQL(cTmp);
		hmTmp.put("dtConcluido", dtTmp);
		// Fecha dtConcluido2
		cTmp = request.getParameter("dtConcluido2");
		if (cTmp != null && cTmp.length() != 0)
			dtTmp = new TFechas().getDateSQL(cTmp);
		hmTmp.put("dtConcluido2", dtTmp);
		return hmTmp;
	}
}
