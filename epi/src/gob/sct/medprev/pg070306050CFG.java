package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070306050CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070306050CFG.png'>
 */
public class pg070306050CFG extends CFGListBase2 {
	public pg070306050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXCtrolCalibra dTOXCtrolCalibra = new TDTOXCtrolCalibra();
		try {
			vDespliega = dTOXCtrolCalibra.FindByAll(getParameters());
			if (vDespliega != null)
				this.iNumReg = vDespliega.size();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo getTipos
	 */
	public Vector getTipos() {
		Vector v = null;
		try {
			v = new TDEmpleoCalib().FindByAll("where lActivo=1");
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		return v;
	}

	/**
	 * Metodo getParameters
	 */
	public HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		hmTmp.put("iCveLaboratorio", request.getParameter("iCveLaboratorio"));
		hmTmp.put("hdLCondicion", request.getParameter("hdLCondicion"));
		hmTmp.put("hdLOrdenar", request.getParameter("hdLOrdenar"));
		if ("1".equals(request.getParameter("cbICveEmpleoCalib")))
			hmTmp.put("iCveEmpleoCalib",
					request.getParameter("iCveEmpleoCalib"));
		if ("1".equals(request.getParameter("cbICveUsuPrepara")))
			hmTmp.put("iCveUsuPrepara", request.getParameter("iCveUsuPrepara"));
		if ("1".equals(request.getParameter("cbDtCaducidad")))
			hmTmp.put("dtCaducidad", request.getParameter("dtCaducidad"));
		if ("1".equals(request.getParameter("cbDtPreparacion")))
			hmTmp.put("dtPreparacion", request.getParameter("dtPreparacion"));
		if ("1".equals(request.getParameter("cbRSituacion")))
			hmTmp.put("rSituacion", request.getParameter("rSituacion"));
		return hmTmp;
	}
}
