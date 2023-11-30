package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para CFG de la página pg070101020
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101020CFG.png'>
 */
public class pg071002010CFG extends CFGListBase2 {
	public pg071002010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TVGRLReportes vGRLReportes = new TVGRLReportes();
		try {
			vGRLReportes.setCNombre("" + request.getParameter("hdNameRep"));
			vGRLReportes.setCQuery("" + request.getParameter("cSQL"));
			vGRLReportes.setCTitulo("" + request.getParameter("cTitulo"));
			vGRLReportes.setCCampos("" + request.getParameter("cCampos"));
			new TDGRLReportes().insert(null, vGRLReportes);
		} catch (Exception e) {
			vErrores.acumulaError("", 14, "");
		}
	}

	/**
	 * Método GuardarA
	 */
	public void GuardarA() {
		TVGRLReportes vGRLReportes = new TVGRLReportes();
		try {
			vGRLReportes.setCNombre("" + request.getParameter("hdNameRep"));
			vGRLReportes.setCQuery("" + request.getParameter("cSQL"));
			vGRLReportes.setCTitulo("" + request.getParameter("cTitulo"));
			vGRLReportes.setCCampos("" + request.getParameter("cCampos"));
			new TDGRLReportes().update(null, vGRLReportes);
		} catch (Exception e) {
			vErrores.acumulaError("", 14, "");
		}
	}

	/**
	 * Método Borrar
	 */
	public void Borrar() {
		TVGRLReportes vGRLReportes = new TVGRLReportes();
		try {
			vGRLReportes.setCNombre("" + request.getParameter("cReportes"));
			new TDGRLReportes().delete(null, vGRLReportes);
		} catch (Exception e) {
			vErrores.acumulaError("", 14, "");
		}
	}

}