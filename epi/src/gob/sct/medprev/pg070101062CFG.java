package gob.sct.medprev;

import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Orden de Servicio por Rama
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101062CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101062CFG.png'>
 */
public class pg070101062CFG extends CFGCatBase2 {
	public pg070101062CFG() {
		cPaginas = "pg070101060.jsp|";
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		Vector vcSintoma = new Vector();
		int num_preguntas = 0;
		try {
			if (request.getParameter("num_preguntas") != null
					&& !request.getParameter("num_preguntas").equals("")) {
				num_preguntas = Integer.parseInt(request.getParameter(
						"num_preguntas").toString());
				for (int i = 1; i < num_preguntas; i++) {
					TVMEDSintoma vSintoma = new TVMEDSintoma();
					vSintoma.setICveServicio(Integer.parseInt(request
							.getParameter("iCveServicio").toString()));
					vSintoma.setICveRama(Integer.parseInt(request.getParameter(
							"iCveRama").toString()));
					vSintoma.setICveSintoma(Integer.parseInt(request
							.getParameter("iCvePregunta" + i).toString()));
					vSintoma.setIOrden(Integer.parseInt(request
							.getParameter("iOrden" + i)));
					vcSintoma.add(vSintoma);
				}
			}
			cClave = (String) dMEDSintoma.updateOrdenPreguntas(null, vcSintoma);
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
		TDMEDRama dMEDRama = new TDMEDRama();
		try {
			// cClave = (String) dMEDRama.update(null, this.getInputs());
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
		TDMEDRama dMEDRama = new TDMEDRama();
		try {
			cClave = (String) dMEDRama.delete(null, this.getInputs());
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
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		String filtro = "";
		try {
			if (request.getParameter("iCveServicio") != null
					&& request.getParameter("iCveServicio").compareTo("") != 0
					&& request.getParameter("iCveRama") != null
					&& request.getParameter("iCveRama").compareTo("") != 0
					&& Integer.parseInt(request.getParameter("iCveServicio")
							.toString()) > 0
					&& Integer.parseInt(request.getParameter("iCveRama")
							.toString()) > 0) {
				vDespliega = dMEDSintoma.FindByAll(" where a.iCveServicio = "
						+ request.getParameter("iCveServicio")
						+ " and a.iCveRama = "
						+ request.getParameter("iCveRama")
						+ " and a.lActivo = 1 order by a.iOrden ");
			}
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