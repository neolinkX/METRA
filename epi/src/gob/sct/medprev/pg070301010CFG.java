package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG para la tabla TOXSituacion
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301010.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301010.png'>
 */
public class pg070301010CFG extends CFGListBase2 {
	public pg070301010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		NavStatus = "Disabled";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDSituacion dSituacion = new TDSituacion();
		if (this.getLPagina("pg070301011.jsp"))
			cPaginas = "pg070301011.jsp|";

		try {
			String cFiltro = "";
			if (cCondicion.compareTo("") != 0) {
				cFiltro = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cFiltro = cFiltro + cOrden;
			}
			vDespliega = dSituacion.FindByAll(cFiltro);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() {
		String cCampo;
		int iCampo;
		TVSituacion vSituacion = new TVSituacion();
		try {
			cCampo = "" + request.getParameter("iCveSituacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSituacion.setICveSituacion(iCampo);

			cCampo = "" + request.getParameter("cDscSituacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vSituacion.setCDscSituacion(cCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
		}
		return vSituacion;
	}
}