package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Catálogo de Medio de Información
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070404011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070404011CFG.png'>
 */
public class pg070401041CFG extends CFGCatBase2 {
	public pg070401041CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070401040.jsp";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		try {
			new TDINVSituacion().insert(null, this.getInputs());
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
		try {
			new TDINVSituacion().update(null, this.getInputs());
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
		try {
			new TDINVSituacion().disable(null, this.getInputs());
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
		try {
			String cWhere = request.getParameter("hdCCondicion");
			cWhere = cWhere != null ? cWhere : "";
			String cOrder = request.getParameter("hdCOrdenar");
			cOrder = cOrder != null ? cOrder : "";
			vDespliega = new TDINVSituacion().FindByAll(cWhere, cOrder);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método fillPK
	 */
	public void fillPK() {
		mPk.add(cActual);
	}

	/**
	 * Método getInputs
	 */
	public TVINVSituacion getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TVINVSituacion vINVSituacion = new TVINVSituacion();
		try {
			cCampo = request.getParameter("iCveSituacion");
			if (cCampo != null && cCampo.length() != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vINVSituacion.setICveSituacion(iCampo);
			cCampo = request.getParameter("cDscSituacion");
			if (cCampo == null)
				cCampo = "";
			vINVSituacion.setCDscSituacion(cCampo);
			cCampo = request.getParameter("lActivo");
			if (cCampo != null && cCampo.length() != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vINVSituacion.setLActivo(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vINVSituacion;
	}
}