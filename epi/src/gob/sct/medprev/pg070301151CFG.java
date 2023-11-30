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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301151CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301151CFG.png'>
 */
public class pg070301151CFG extends CFGCatBase2 {

	public pg070301151CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDTOXParamCalib dParam = new TDTOXParamCalib();
		try {
			cClave = (String) dParam.insert(null, this.getInputs());
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
		TDTOXParamCalib dParam = new TDTOXParamCalib();
		try {
			cClave = (String) dParam.update(null, this.getInputs());
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
		TDTOXParamCalib dParam = new TDTOXParamCalib();
		try {
			cClave = (String) dParam.deshabilitar(null, this.getInputs());
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
		TDTOXParamCalib dParam = new TDTOXParamCalib();
		cPaginas = "pg070301150.jsp|"; /* Liga al JSP de Catálogo */
		try {
			int iCveLaboratorio = 0;
			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.length() > 0)
				iCveLaboratorio = Integer.parseInt(request.getParameter(
						"iCveLaboratorio").toString());
			String cWhere = " where iCveLaboratorio =" + iCveLaboratorio;
			if (cCondicion.compareTo("") != 0)
				cCondicion = " and " + cCondicion;
			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY iCveParamCalib";
			vDespliega = dParam.FindByAll(cCondicion + cOrden);
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	/**
	 * Método FillPK
	 */
	public void fillPK() {
		debug("FillPK");
		mPk.add(request.getParameter("iCveLaboratorio").toString());
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
		TVTOXParamCalib vParam = new TVTOXParamCalib();
		try {
			cCampo = "" + request.getParameter("hdCampoClave"); // iCveParamCalib
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vParam.setiCveParamCalib(new Integer(iCampo));

			cCampo = "" + request.getParameter("iCveLaboratorio"); // iCveLaboratorio
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vParam.setiCveLaboratorio(new Integer(iCampo));

			cCampo = "" + request.getParameter("cDscParam");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vParam.setcDscParam(cCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vParam.setlActivo(new Integer(iCampo));

			cCampo = "" + request.getParameter("dValorMin");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vParam.setdValorMin(new Double(fCampo));
			cCampo = "" + request.getParameter("dValorMax");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vParam.setDValorMax(new Double(fCampo));

		} catch (Exception ex) {
			error("getInputs", ex);
		}
		return vParam;
	}
}
