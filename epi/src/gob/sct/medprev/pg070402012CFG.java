package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG del listado de vehículos involucrados en el
 * accidente
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Jaime Enrique Suárez Romero
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070402012CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070402012CFG.png'>
 */
public class pg070402012CFG extends CFGListBase2 {
	public pg070402012CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070402013.jsp|pg070402010.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDINVRegVehic dRegVehic = new TDINVRegVehic();
		try {
			String cBusca = " where INVRegVehic.iAnio = "
					+ request.getParameter("iAnioSel")
					+ " and INVRegVehic.iCveMdoTrans = "
					+ request.getParameter("iCveMdoTransSel")
					+ " and INVRegVehic.iIDDGPMPT = "
					+ request.getParameter("hdiIDDGPMPT");

			if (!cCondicion.equals("")) {
				cBusca = cBusca + " and " + cCondicion;
			}
			if (!cOrden.equals("")) {
				cBusca = cBusca + cOrden;
			}
			vDespliega = dRegVehic.FindByAll(cBusca);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
