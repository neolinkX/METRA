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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070105020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070105000CFG.png'>
 */
public class pg070105020CFG extends CFGListBase2 {
	public pg070105020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		try {
			vDespliega = new TDEXPDictamenServ()
					.getEXPDictamenYNotaMedica(getParameters());
			iNumReg = vDespliega.size();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método getParameters
	 */
	public HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		String cTmp = request.getParameter("hdICveExpediente");
		hmTmp.put("iCveExpediente", cTmp != null ? cTmp : "0");
		cTmp = request.getParameter("hdINumExamen");
		hmTmp.put("iNumExamen", cTmp != null ? cTmp : "0");
		cTmp = request.getParameter("hdINumExamTmp");
		if (cTmp != null && cTmp.length() != 0)
			hmTmp.put("iNumExamen", cTmp);
		return hmTmp;
	}

	/**
	 * Método getUser
	 */
	public TVPERDatos getUser(String cCveExpediente) {
		TVPERDatos vPERDatos = null;
		try {
			vPERDatos = new TDPERDatos()
					.findUserbyExp(cCveExpediente != null ? Integer
							.parseInt(cCveExpediente) : 0);
		} catch (Exception ex) {
			error("getUser", ex);
		}
		return vPERDatos;
	}

	/**
	 * Método getProceso
	 */
	public TVGRLProceso getProceso(String cCveProceso) {
		TVGRLProceso vGRLProceso = null;
		HashMap hmTmp = new HashMap();
		hmTmp.put("iCveProceso", cCveProceso);
		try {
			Vector vcTmp = new TDGRLProceso().FindByAll(hmTmp);
			if (vcTmp.size() > 0)
				vGRLProceso = (TVGRLProceso) vcTmp.get(0);
		} catch (Exception ex) {
			error("getUniMed", ex);
		}
		return vGRLProceso;
	}

	/**
	 * Método getEdad
	 */
	public int getEdad(Date dtFechaNac) {
		Calendar hoy = Calendar.getInstance();
		Calendar nac = Calendar.getInstance();
		nac.setTime(dtFechaNac);
		int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
		if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
				.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE))
			edad--;
		return edad;
	}

	/**
	 * Método setPaginas
	 */
	public void setPaginas(String cPaginas) {
		this.cPaginas = cPaginas;
	}
}