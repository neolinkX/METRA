package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Cat�logo de M�dulos
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003021CFG.png'>
 */
public class pg071001041CFG extends CFGCatBase2 {
	public pg071001041CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071001040.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDGRLNacionalidad dGRLNacionalidad = new TDGRLNacionalidad();
		try {
			cClave = (String) dGRLNacionalidad.insert(null, this.getInputs());
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
		TDGRLNacionalidad dGRLNacionalidad = new TDGRLNacionalidad();
		try {
			cClave = (String) dGRLNacionalidad.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLNacionalidad dGRLNacionalidad = new TDGRLNacionalidad();
		try {

			String cWhere = "";
			if (cCondicion.compareTo("") != 0)
				cWhere = " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else
				cWhere += " order by iCvePais ";

			vDespliega = dGRLNacionalidad.FindByAll(cWhere);
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
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVGRLNacionalidad vGRLNacionalidad = new TVGRLNacionalidad();
		try {
			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLNacionalidad.setICvePais(iCampo);

			cCampo = "" + request.getParameter("cDscNacional");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLNacionalidad.setCDscNacional(cCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLNacionalidad;
	}
}