package gob.sct.medprev;

import java.util.*;

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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070401031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070401031CFG.png'>
 */
public class pg070401031CFG extends CFGCatBase2 {
	public pg070401031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070401030.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			new TDINVCausa().insert(null, this.getInputs());
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
			new TDINVCausa().update(null, this.getInputs());
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
			new TDINVCausa().disable(null, this.getInputs());
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
			String cCveTpoCausa = request.getParameter("hdICveTpoCausa");
			if (cCveTpoCausa != null) {
				cWhere = cWhere.length() != 0 ? cWhere + " and " : "";
				cWhere += " iCveTpoCausa=" + cCveTpoCausa;
			}
			vDespliega = new TDINVCausa().FindByAll(cWhere, cOrder);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(request.getParameter("hdICveTpoCausa"));
		mPk.add(request.getParameter("hdICveCausa"));
	}

	/**
	 * Metodo getInputs
	 */
	public TVINVCausa getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TVINVCausa vINVCausa = new TVINVCausa();
		try {
			cCampo = request.getParameter("hdICveTpoCausa");
			if (cCampo != null && cCampo.length() != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vINVCausa.setICveTpoCausa(iCampo);
			cCampo = request.getParameter("hdICveCausa");
			if (cCampo != null && cCampo.length() != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vINVCausa.setICveCausa(iCampo);
			cCampo = request.getParameter("cDscCausa");
			if (cCampo == null)
				cCampo = "";
			vINVCausa.setCDscCausa(cCampo);
			cCampo = request.getParameter("lActivo");
			if (cCampo != null && cCampo.length() != 0)
				iCampo = Integer.parseInt(cCampo, 10);
			else
				iCampo = 0;
			vINVCausa.setLActivo(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vINVCausa;
	}

	/**
	 * Metodo getTpoCausa
	 */
	public Vector getTpoCausa() {
		Vector vcINVTpoCausa = null;
		try {
			vcINVTpoCausa = new TDINVTpoCausa().FindByAll("",
					" order by cDscTpoCausa");
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		return vcINVTpoCausa;
	}
}