package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Listado de TOXCtrolCalibra
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070303060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070303060CFG.png'>
 */
public class pg070102032CFG extends CFGListBase2 {
	public pg070102032CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			HashMap hmParams = getParameters();
			if (hmParams.get("iCveUniMed") == null)
				hmParams.put("iCveUniMed", "-1");
			vDespliega = new TDEPICisCita().FindConsultaCitas(hmParams);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo getUniMedsValidas
	 */
	public Vector getUniMedsValidas() {
		Vector vcUMValidas = null;
		try {
			TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true)
					.getAttribute("UsrID");
			vcUMValidas = new TDGRLUMUsuario().getUniMedxUsu(vUsuario
					.getICveusuario());
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcUMValidas;
	}

	/**
	 * Metodo getModulos
	 */
	public Vector getModulos() {
		Vector vcModulos = null;
		try {
			String cTmp = request.getParameter("iCveUniMed");
			if (cTmp != null)
				vcModulos = new TDGRLModulo().getComboModulos(Integer
						.parseInt(cTmp));
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcModulos;
	}

	/**
	 * Metodo getModulos
	 */
	public Vector getModulos(String cCveUniMed) {
		Vector vcModulos = null;
		try {
			if (cCveUniMed != null)
				vcModulos = new TDGRLModulo().getComboModulos(Integer
						.parseInt(cCveUniMed));
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcModulos;
	}

	/**
	 * Metodo getHorasDeCitas
	 */
	public Vector getHorasDeCitas() {
		Vector vcHoras = null;
		try {
			HashMap hmParams = getParameters();
			if (hmParams.get("iCveUniMed") == null)
				hmParams.put("iCveUniMed", "-1");
			vcHoras = new TDEPICisCita().getHorasDeCitas(hmParams);
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcHoras;
	}

	/**
	 * Metodo getHorasDeCitas
	 */
	public Vector getHorasDeCitas(String cCveUniMed, String cCveModulo,
			String cFecha) {
		Vector vcHoras = null;
		try {
			HashMap hmParams = new HashMap();
			hmParams.put("iCveUniMed", cCveUniMed);
			hmParams.put("iCveModulo", cCveModulo);
			hmParams.put("dtFecha", cFecha);
			if (hmParams.get("iCveUniMed") == null)
				hmParams.put("iCveUniMed", "-1");
			vcHoras = new TDEPICisCita().getHorasDeCitas(hmParams);
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcHoras;
	}

	/**
	 * Metodo getParameters
	 */
	private HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		hmTmp.put("iCveUniMed", request.getParameter("iCveUniMed"));
		hmTmp.put("iCveModulo", request.getParameter("iCveModulo"));
		hmTmp.put("dtFecha", request.getParameter("dtFecha"));
		hmTmp.put("cHora", request.getParameter("cHora"));
		return hmTmp;
	}
}