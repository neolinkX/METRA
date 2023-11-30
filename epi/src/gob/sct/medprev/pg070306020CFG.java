package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG List de TOXCtrolCalibra
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Juan Manuel Vazquez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070306020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070306020CFG.png'>
 */
public class pg070306020CFG extends CFGListBase2 {
	public pg070306020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDTOXCtrolCalibra dCtrolCalibra = new TDTOXCtrolCalibra();

		cPaginas = "pg070306021.jsp|";

		String cWhere = "";
		String cCond = "" + request.getParameter("hdLCondicion");
		String cAnio = "" + request.getParameter("iAnio");
		String cUnMed = "" + request.getParameter("iCveUniMed");

		if (cCond.compareTo("null") != 0 && cCond.compareTo("") != 0) {
			cWhere = "C." + cCond;

			if (cAnio.compareTo("null") != 0 && cAnio.compareTo("") != 0) {
				cWhere += " and C.iAnio = " + cAnio;
			}
			if (cUnMed.compareTo("null") != 0 && cUnMed.compareTo("") != 0) {
				cWhere += " and C.iCveLaboratorio = " + cUnMed;
			}
		} else {
			if (cAnio.compareTo("null") != 0 && cAnio.compareTo("") != 0) {

				cWhere = " C.iAnio = " + cAnio;
				if (cUnMed.compareTo("null") != 0 && cUnMed.compareTo("") != 0) {
					cWhere += " and C.iCveLaboratorio = " + cUnMed;
				}
			} else {
				if (cUnMed.compareTo("null") != 0 && cUnMed.compareTo("") != 0) {
					cWhere += " C.iCveLaboratorio = " + cUnMed;
				}
			}

		}

		if (cWhere.compareTo("null") != 0 && cWhere.compareTo("") != 0) {
			cWhere = " where " + cWhere;
		} else
			cWhere = "";

		String cOrden = "" + request.getParameter("hdLOrdenar");

		if (cOrden.compareTo("null") != 0 && cOrden.compareTo("") != 0) {
			cOrden = request.getParameter("hdLOrdenar");
		} else
			cOrden = " Order By C.iCveLaboratorio, C.iCveCtrolCalibra";

		try {
			if (cWhere.compareTo("") != 0 && cWhere.compareTo("null") != 0) {
				vDespliega = dCtrolCalibra.FindByAll(cWhere, cOrden);
			} else
				vDespliega = dCtrolCalibra.FindByAll(
						" where C.iCveLaboratorio = 0 ", cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
