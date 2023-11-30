package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para CFG Cat TOXEmpleoCalib
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301101CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301101CFG.png'>
 */
public class pg070301101CFG extends CFGCatBase2 {
	public pg070301101CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDEmpleoCalib dEmpleoCalib = new TDEmpleoCalib();
		try {
			cClave = (String) dEmpleoCalib.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Método Guardar

	/**
	 * Método GuardarA
	 */
	public void GuardarA() {
		TDEmpleoCalib dEmpleoCalib = new TDEmpleoCalib();
		try {
			cClave = (String) dEmpleoCalib.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Método GuardarA

	/**
	 * Método Borrar
	 */
	public void Borrar() {
		TDEmpleoCalib dEmpleoCalib = new TDEmpleoCalib();
		try {
			cClave = (String) dEmpleoCalib.deshabilitar(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Método Borrar

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDEmpleoCalib dEmpleoCalib = new TDEmpleoCalib();

		if (this.getLPagina("pg070301100.jsp"))
			cPaginas = "pg070301100.jsp|";

		if (cCondicion.compareTo("") != 0)
			cCondicion = " WHERE " + cCondicion;
		if (cOrden.compareTo("") == 0)
			cOrden = " ORDER BY iCveEmpleoCalib";

		String cEmpleoCalib = cCondicion + " " + cOrden;

		try {
			vDespliega = dEmpleoCalib.FindByAll(cEmpleoCalib);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVEmpleoCalib vEmpleoCalib = new TVEmpleoCalib();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEmpleoCalib.setICveEmpleoCalib(iCampo);

			cCampo = "" + request.getParameter("cDscEmpleoCalib");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEmpleoCalib.setCDscEmpleoCalib(cCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEmpleoCalib.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEmpleoCalib;
	}
}
