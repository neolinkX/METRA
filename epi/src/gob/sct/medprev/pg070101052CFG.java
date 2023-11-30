package gob.sct.medprev;

import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Orden de Servicio por Rama
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101052CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101052CFG.png'>
 */
public class pg070101052CFG extends CFGCatBase2 {
	public pg070101052CFG() {
		cPaginas = "pg070101050.jsp|pg070101051.jsp|";
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMEDRama dMEDRama = new TDMEDRama();
		Vector ramas = new Vector();
		int num_ramas = 0;
		try {
			if (request.getParameter("num_ramas") != null
					&& !request.getParameter("num_ramas").equals("")) {
				num_ramas = Integer.parseInt(request.getParameter("num_ramas")
						.toString());

				for (int i = 1; i < num_ramas; i++) {
					TVMEDRama vRama = new TVMEDRama();

					vRama.setICveServicio(Integer.parseInt(request
							.getParameter("iCveServicio").toString()));
					vRama.setICveRama(Integer.parseInt(request.getParameter(
							"iCveRama" + i).toString()));
					vRama.setIOrden(Integer.parseInt(request
							.getParameter("iOrden" + i)));
					ramas.add(vRama);
				}
			}
			cClave = (String) dMEDRama.updateOrdenRamas(null, ramas);
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
		TDMEDRama dMEDRama = new TDMEDRama();
		try {
			// cClave = (String) dMEDRama.update(null, this.getInputs());
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
		TDMEDRama dMEDRama = new TDMEDRama();
		try {
			cClave = (String) dMEDRama.delete(null, this.getInputs());
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
		TDMEDRama dMEDRama = new TDMEDRama();
		String filtro = "";
		try {
			if (request.getParameter("iCveServicio") != null
					&& !request.getParameter("iCveServicio").equals("")) {
				filtro = " and R.lActivo=1 and R.iCveServicio="
						+ request.getParameter("iCveServicio") + " ";
				vDespliega = dMEDRama.FindByAllCatalogoRamas(filtro,
						" order by iOrden ");
			}
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
		TVMEDRama vMEDRama = new TVMEDRama();
		try {
			cCampo = "" + request.getParameter("iCveServicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRama.setICveServicio(iCampo);

			cCampo = "" + request.getParameter("iCveRama");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRama.setICveRama(iCampo);

			cCampo = "" + request.getParameter("cDscRama");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDRama.setCDscRama(cCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDRama.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("lEstudio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRama.setLEstudio(iCampo);

			cCampo = "" + request.getParameter("iOrden");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRama.setIOrden(iCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRama.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDRama;
	}
}