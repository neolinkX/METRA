package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Catalogo de Marcas de sustancias
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301111CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301111CFG.png'>
 */
public class pg070301111CFG extends CFGCatBase2 {
	public pg070301111CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		NavStatus = "Disabled";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDTOXMarcaSust dTOXMarcaSust = new TDTOXMarcaSust();
		try {
			cClave = (String) dTOXMarcaSust.insert(null, this.getInputs());
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
		TDTOXMarcaSust dTOXMarcaSust = new TDTOXMarcaSust();
		try {
			cClave = (String) dTOXMarcaSust.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Método GuardarA

	/**
	 * Método BorrarB
	 */
	public void BorrarB() {
		TDTOXMarcaSust dTOXMarcaSust = new TDTOXMarcaSust();
		try {
			cClave = (String) dTOXMarcaSust.disable(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.BorrarB();
		}
	} // Método BorrarB

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDTOXMarcaSust dTOXMarcaSust = new TDTOXMarcaSust();
		if (this.getLPagina("pg070301110.jsp"))
			cPaginas = "pg070301110.jsp|";
		try {

			if (cCondicion.compareTo("") != 0)
				cCondicion = " WHERE " + cCondicion;
			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY iCveMarcaSust ";
			String cMarcaSust = cCondicion + " " + cOrden;

			vDespliega = dTOXMarcaSust.FindByAll(cMarcaSust);
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
		// TFechas tfCampo = new TFechas();
		TVTOXMarcaSust vTOXMarcaSust = new TVTOXMarcaSust();
		try {
			cCampo = "" + request.getParameter("iCveMarcaSust");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXMarcaSust.setICveMarcaSust(iCampo);

			cCampo = "" + request.getParameter("cDscMarcaSust");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXMarcaSust.setCDscMarcaSust(cCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXMarcaSust.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXMarcaSust;
	}
}