package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Cat�logo de Medio de Informaci�n
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070401011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070401011CFG.png'>
 */
public class pg070401011CFG extends CFGCatBase2 {
	public pg070401011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070401010.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			new TDINVMedInforma().insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		try {
			new TDINVMedInforma().update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo BorrarB
	 */
	public void BorrarB() {
		try {
			new TDINVMedInforma().disable(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.BorrarB();
		}
	} // Metodo BorrarB

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			String cWhere = request.getParameter("hdCCondicion");
			cWhere = cWhere != null ? cWhere : "";
			String cOrder = request.getParameter("hdCOrdenar");
			cOrder = cOrder != null ? cOrder : "";
			vDespliega = new TDINVMedInforma().FindByAll(cWhere, cOrder);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public TVINVMedInforma getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TVINVMedInforma vINVMedInforma = new TVINVMedInforma();
		try {
			cCampo = request.getParameter("iCveMedInforma");
			if (cCampo != null && cCampo.length() != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vINVMedInforma.setICveMedInforma(iCampo);
			cCampo = request.getParameter("cDscMedInforma");
			if (cCampo == null)
				cCampo = "";
			vINVMedInforma.setCDscMedInforma(cCampo);
			cCampo = request.getParameter("lActivo");
			if (cCampo != null && cCampo.length() != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vINVMedInforma.setLActivo(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vINVMedInforma;
	}
}