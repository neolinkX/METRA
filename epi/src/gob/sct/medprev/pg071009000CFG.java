package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Cat�logo de Unidades M�dicas
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003011CFG.png'>
 */
public class pg071009000CFG extends CFGCatBase2 {
	public pg071009000CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071009000.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDGRLAyudaDGPMPT dGRLAyudaDGPMPT = new TDGRLAyudaDGPMPT();
		try {
			cClave = (String) dGRLAyudaDGPMPT.insert(null, this.getInputs());
			this.getInputs();
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
		TDGRLAyudaDGPMPT dGRLAyudaDGPMPT = new TDGRLAyudaDGPMPT();
		try {
			cClave = (String) dGRLAyudaDGPMPT.update(null, this.getInputs());
			this.getInputs();
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDGRLAyudaDGPMPT dGRLAyudaDGPMPT = new TDGRLAyudaDGPMPT();
		try {
			cClave = (String) dGRLAyudaDGPMPT.disable(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLAyudaDGPMPT dGRLAyudaDGPMPT = new TDGRLAyudaDGPMPT();
		TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null) {
				cActual = request.getParameter("hdRowNum");
			}

			if (cCondicion.compareTo("") != 0)
				cWhere = " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by ICVEAYUDA ";

			vDespliega = dGRLAyudaDGPMPT.FindByAll(cWhere, cOrderBy);
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
		// TVGRLUniMed vGRLUniMed = new TVGRLUniMed();
		TVGRLAyudaDGPMPT vGRLAyudaDGPMPT = new TVGRLAyudaDGPMPT();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLAyudaDGPMPT.setICveAyuda(iCampo);

			cCampo = "" + request.getParameter("cDsAyuda");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLAyudaDGPMPT.setCDsAyuda(cCampo);

			cCampo = "" + request.getParameter("cDscDescripcion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLAyudaDGPMPT.setCDscDescripcion(cCampo);

			cCampo = "" + request.getParameter("cUrl");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLAyudaDGPMPT.setCUrl(cCampo);

			cCampo = "" + request.getParameter("lManual");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLAyudaDGPMPT.setLManual(iCampo);

			cCampo = "" + request.getParameter("lSoftware");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLAyudaDGPMPT.setLSoftware(iCampo);

			cCampo = "" + request.getParameter("lGuia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLAyudaDGPMPT.setLGuia(iCampo);

			if (request.getParameter("lAdmin").compareTo("Si") == 0)
				cCampo = "1";
			else
				cCampo = "0";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLAyudaDGPMPT.setLAdmin(iCampo);

			if (request.getParameter("lVigente").compareTo("Si") == 0)
				cCampo = "1";
			else
				cCampo = "0";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLAyudaDGPMPT.setLVigente(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLAyudaDGPMPT;
	}
}
