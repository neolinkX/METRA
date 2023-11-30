package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para CFG Cat MEDServicio
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101041CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101041CFG.png'>
 */
public class pg070101041CFG extends CFGCatBase2 {
	public pg070101041CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDMEDServicio dMEDServicio = new TDMEDServicio();
		try {
			cClave = (String) dMEDServicio.insert(null, this.getInputs());
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
		TDMEDServicio dMEDServicio = new TDMEDServicio();
		try {
			cClave = (String) dMEDServicio.update(null, this.getInputs());
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
		TDMEDServicio dMEDServicio = new TDMEDServicio();
		try {
			cClave = (String) dMEDServicio.updatebaja(null, this.getInputs());
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
		TDMEDServicio dMEDServicio = new TDMEDServicio();
		cPaginas = "pg070101040.jsp|";

		try {

			String cFiltro = "" + request.getParameter("hdCCondicion");
			String cOrden = "" + request.getParameter("hdCOrdenar");

			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null) {
				cActual = request.getParameter("hdCampoClave");

			}
			if (cFiltro.compareToIgnoreCase("null") != 0
					&& cFiltro.compareTo("") != 0) {
				cFiltro = " where " + cFiltro;
			} else {
				cFiltro = "";

			}
			if (cOrden.compareToIgnoreCase("null") != 0
					&& cOrden.compareTo("") != 0) {
				cOrden = cOrden;
			} else {
				cOrden = " order by iCveServicio ";

			}
			vDespliega = dMEDServicio.FindByAll(cFiltro, cOrden);
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
		TFechas tfCampo = new TFechas();
		TVMEDServicio vMEDServicio = new TVMEDServicio();
		try {

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDServicio.setICveServicio(iCampo);

			cCampo = "" + request.getParameter("cDscServicio");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDServicio.setCDscServicio(cCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDServicio.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("lVariosMeds");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDServicio.setLVariosMeds(iCampo);

			// Se Inicializan Valores
			vMEDServicio.setLPostDiag(0);
			vMEDServicio.setLInterConsulta(0);
			vMEDServicio.setLDiagnostico(0);

			// Asigna el Valor de Acuerdo al Radio Seleccionado
			cCampo = "" + request.getParameter("lPostDiag");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
				switch (iCampo) {
				case 0:
					vMEDServicio.setLPostDiag(1);
					break;
				case 1:
					vMEDServicio.setLInterConsulta(1);
					break;
				case 2:
					vMEDServicio.setLDiagnostico(0);
					break;
				case 3:
					vMEDServicio.setLDiagnostico(1);
					break;
				}

			}

			cCampo = "" + request.getParameter("lSUBIRARCHIVOS");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDServicio.setlSUBIRARCHIVOS(iCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDServicio.setLActivo(iCampo);

			cCampo = "" + request.getParameter("lReqDiag");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDServicio.setLReqDiag(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDServicio;
	}
}
