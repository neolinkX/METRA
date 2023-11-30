package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para CFG Cat de TOXAcCorrectiva
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301131CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301131CFG.png'>
 */
public class pg070301131CFG extends CFGCatBase2 {

	public pg070301131CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDAcCorrectiva dAcCorrectiva = new TDAcCorrectiva();
		try {
			cClave = (String) dAcCorrectiva.insert(null, this.getInputs());
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
		TDAcCorrectiva dAcCorrectiva = new TDAcCorrectiva();
		try {
			cClave = (String) dAcCorrectiva.update(null, this.getInputs());
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
		TDAcCorrectiva dAcCorrectiva = new TDAcCorrectiva();
		try {
			cClave = (String) dAcCorrectiva
					.deshabilitar(null, this.getInputs());
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
		TDAcCorrectiva dAcCorrectiva = new TDAcCorrectiva();
		if (this.getLPagina("pg070301130.jsp"))
			cPaginas = "pg070301130.jsp|"; /* Liga al JSP de Catálogo */
		try {
			if (cCondicion.compareTo("") != 0)
				cCondicion = " WHERE " + cCondicion;
			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY iCveAcCorrectiva";
			vDespliega = dAcCorrectiva.FindByAll(cCondicion + cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método FillPK
	 */
	public void FillPK() {
		debug("FillPK");
		mPk.add(cActual);
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs() {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVAcCorrectiva vAcCorrectiva = new TVAcCorrectiva();
		try {
			cCampo = "" + request.getParameter("hdCampoClave"); // iCveAcCorrectiva
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vAcCorrectiva.setICveAcCorrectiva(iCampo);

			cCampo = "" + request.getParameter("cDscAcCorrectiva");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vAcCorrectiva.setCDscAcCorrectiva(cCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vAcCorrectiva.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
		}
		return vAcCorrectiva;
	}
}