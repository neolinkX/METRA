package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG Cat ALMTpoMovimiento
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Juan Manuel Vazquez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801061CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801061CFG.png'>
 */
public class pg070801061CFG extends CFGCatBase2 {
	public pg070801061CFG() {
		cPaginas = "pg070801060.jsp|";

		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDALMTpoMovimiento dALMTpoMovimiento = new TDALMTpoMovimiento();
		try {
			cClave = (String) dALMTpoMovimiento.insert(null, this.getInputs());
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
		TDALMTpoMovimiento dALMTpoMovimiento = new TDALMTpoMovimiento();
		try {
			cClave = (String) dALMTpoMovimiento.update(null, this.getInputs());
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
		TDALMTpoMovimiento dALMTpoMovimiento = new TDALMTpoMovimiento();
		try {
			cClave = (String) dALMTpoMovimiento.disable(null, this.getInputs());
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
		TDALMTpoMovimiento dALMTpoMovimiento = new TDALMTpoMovimiento();
		try {
			String cFiltro = "" + request.getParameter("hdCCondicion");
			String cOrden = "" + request.getParameter("hdCOrdenar");

			if (cFiltro.compareToIgnoreCase("null") != 0
					&& cFiltro.compareTo("") != 0)
				cFiltro = " where " + cFiltro;
			else
				cFiltro = "";

			if (cOrden.compareToIgnoreCase("null") != 0
					&& cOrden.compareTo("") != 0)
				cOrden = cOrden;
			else
				cOrden = " ORDER BY iCveTpoMovimiento ";

			vDespliega = dALMTpoMovimiento.FindByAll(cFiltro, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVALMTpoMovimiento vALMTpoMovimiento = new TVALMTpoMovimiento();
		try {
			cCampo = "" + request.getParameter("hdCampoClave"); // iCveTpoMovimiento
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMTpoMovimiento.setICveTpoMovimiento(iCampo);

			cCampo = "" + request.getParameter("cDscTpoMovimiento");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMTpoMovimiento.setCDscTpoMovimiento(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMTpoMovimiento.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("lSuma");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMTpoMovimiento.setLSuma(iCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMTpoMovimiento.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vALMTpoMovimiento;
	}
}
