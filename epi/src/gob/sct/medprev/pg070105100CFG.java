package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Clase de configuración para Listado de
 * EXPExamAplica
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070105000CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070105000CFG.png'>
 */
public class pg070105100CFG extends CFGListBase2 {
	public pg070105100CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070105010.jsp";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		try {
			vDespliega = new TDMEDPerfilEspec()
					.getPerfilEspecXDiag(getParameters());
			iNumReg = vDespliega.size();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método getEspecialidades
	 */
	public Vector getEspecialidades() {
		Vector vcMEDExpecialidad = null;
		try {
			vcMEDExpecialidad = new TDMEDEspecialidad()
					.getEspecPerfil(getParameters());
		} catch (Exception ex) {
			error("getEspecialidades", ex);
		}
		return vcMEDExpecialidad;
	}

	/**
	 * Método getParameters
	 */
	public HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		hmTmp.put("lActivo", "1");
		String cTmp = request.getParameter("hdICvePerfil");
		hmTmp.put("iCvePerfil", cTmp != null ? cTmp : "0");
		cTmp = request.getParameter("iCveEspecialidad");
		hmTmp.put("iCveEspecialidad", cTmp != null ? cTmp : "0");
		return hmTmp;
	}
}