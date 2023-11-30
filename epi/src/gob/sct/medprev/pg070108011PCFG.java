package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

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
public class pg070108011PCFG extends CFGListBase2 {
	public pg070108011PCFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			// vDespliega = new TDEXPExamAplica().FindByAll(getParameters());
			vDespliega = new TDTSEGEXAR().FindByAll(getParameters());
			iNumReg = vDespliega.size();
		} catch (Exception ex) {
			// error("FillVector", ex);
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
		return hmTmp;
	}

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
	 * Metodo getProceso
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
	 * Metodo getUniMed
	 */
	public TVGRLUniMed getUniMed(String cCveUniMed) {
		TVGRLUniMed vGRLUniMed = null;
		HashMap hmTmp = new HashMap();
		hmTmp.put("iCveUniMed", cCveUniMed);
		try {
			Vector vcTmp = new TDGRLUniMed().FindByAll(hmTmp);
			if (vcTmp.size() > 0)
				vGRLUniMed = (TVGRLUniMed) vcTmp.get(0);
		} catch (Exception ex) {
			error("getUniMed", ex);
		}
		return vGRLUniMed;
	}

	/**
	 * Metodo getModulo
	 */
	public TVGRLModulo getModulo(String cCveUniMed, String cCveModulo) {
		TVGRLModulo vGRLModulo = null;
		HashMap hmTmp = new HashMap();
		hmTmp.put("iCveUniMed", cCveUniMed);
		hmTmp.put("iCveModulo", cCveModulo);
		try {
			Vector vcTmp = new TDGRLModulo().FindByAll(hmTmp);
			if (vcTmp.size() > 0)
				vGRLModulo = (TVGRLModulo) vcTmp.get(0);
		} catch (Exception ex) {
			error("getModulo", ex);
		}
		return vGRLModulo;
	}

}
