package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Clase de configuración para catalogo de
 * MEDTpoResp
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101091CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101091CFG.png'>
 */
public class pg070101091CFG extends CFGCatBase2 {
	public pg070101091CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070101090.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDMEDTpoResp dMEDTpoResp = new TDMEDTpoResp();
		try {
			cClave = (String) dMEDTpoResp.insert(null, this.getInputs());
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
			cClave = (String) new TDMEDTpoResp().update(null, this.getInputs());
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
			cClave = (String) new TDMEDTpoResp()
					.disable(null, this.getInputs());
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
			if (cWhere != null && cWhere.length() != 0)
				cWhere = " where " + cWhere;
			else
				cWhere = "";
			String cOrder = request.getParameter("hdCOrdenar");
			if (cOrder == null || cOrder.length() == 0)
				cOrder = " order by iCveTpoResp";
			vDespliega = new TDMEDTpoResp().FindByAll(cWhere, cOrder);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método FillPK
	 */
	public void fillPK() {
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
		TVMEDTpoResp vMEDTpoResp = new TVMEDTpoResp();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDTpoResp.setICveTpoResp(iCampo);

			cCampo = "" + request.getParameter("cDscTpoResp");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDTpoResp.setCDscTpoResp(cCampo);

			cCampo = "" + request.getParameter("cCampo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDTpoResp.setCCampo(cCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDTpoResp.setLActivo(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDTpoResp;
	}
}