package gob.sct.medprev;

import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG de la pagina pg070104080CFG
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104080CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104080CFG.png'>
 */
public class pg070104080CFG extends CFGCatBase2 {
	public pg070104080CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {

		try {

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al Guardar la informaci�n", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		if (request.getParameter("hdGenerarNumAnalisis") != null) {
			if ((request.getParameter("hdGenerarNumAnalisis")
					.compareToIgnoreCase("GenerarNumAnalisis") == 0)) {

				if (request.getParameter("hdNumAnalisis") != null) {
					try {
						TDEXPCtrolLab dExpCtrolLab = new TDEXPCtrolLab();
						TFechas t = new TFechas();
						int y = t.getIntYear(t.TodaySQL());

						TVExpCtrolLab vExpCtrolLab = new TVExpCtrolLab();
						// vExpCtrolLab.setIConsAnalisis(dExpCtrolLab.getINextConsecutivo());
						vExpCtrolLab.setIAnio(y);
						vExpCtrolLab.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vExpCtrolLab.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vExpCtrolLab.setICveAnalisis(Integer.parseInt(request
								.getParameter("hdNumAnalisis")));
						vExpCtrolLab.setICveExpediente(Integer.parseInt(request
								.getParameter("iCveExpediente")));
						vExpCtrolLab.setCUsuaAplicado("");
						vExpCtrolLab.setCEstudios("");
						vExpCtrolLab.setCMotivo("");
						vExpCtrolLab.setDtSolicitud(t.TodaySQL());

						dExpCtrolLab.insertExpCtrolLab(null, vExpCtrolLab);

						// System.out.println("Inserted: " +
						// dExpCtrolLab.getIInserted());
					} catch (DAOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}

		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();

		try {
			// Buscar el Examen del Operador
			if ("Actual".compareToIgnoreCase(this.getAccionOriginal()) == 0) {
				vDespliega = dEXPExamAplica.findByUniMedModExp((HashMap) this
						.getInputs());

				if (vDespliega != null && vDespliega.size() > 0) {
					// Buscar el Servicio del Expediente

				} else {
					vErrores.acumulaError(
							"El expediente no fue encontrado o no tiene un examen vigente",
							0, "");
					this.UpdStatus = "";
				}
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
		HashMap vDatos = new HashMap();
		TFechas Fecha = new TFechas();
		try {
			cCampo = request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").toString().length() > 0 ? request
					.getParameter("iCveUniMed").toString() : "";
			vDatos.put("iCveUniMed", cCampo);
			cCampo = request.getParameter("iCveModulo") != null
					&& request.getParameter("iCveModulo").toString().length() > 0 ? request
					.getParameter("iCveModulo").toString() : "";
			vDatos.put("iCveModulo", cCampo);
			cCampo = request.getParameter("iCveProceso") != null
					&& request.getParameter("iCveProceso").toString().length() > 0 ? request
					.getParameter("iCveProceso").toString() : "";
			vDatos.put("iCveProceso", cCampo);
			cCampo = request.getParameter("iCveExpediente") != null
					&& request.getParameter("iCveExpediente").toString()
							.length() > 0 ? request.getParameter(
					"iCveExpediente").toString() : "";
			vDatos.put("iCveExpediente", cCampo);
			vDatos.put("iCvePersonal", cCampo);
			cCampo = request.getParameter("iNumExamen") != null
					&& request.getParameter("iNumExamen").toString().length() > 0 ? request
					.getParameter("iNumExamen").toString() : "";
			vDatos.put("iNumExamen", cCampo);
			cCampo = request.getParameter("iCveServicio") != null
					&& request.getParameter("iCveServicio").toString().length() > 0 ? request
					.getParameter("iCveServicio").toString() : "";
			vDatos.put("iCveServicio", cCampo);
			vDatos.put("dtFecha", Fecha.TodaySQL());
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vDatos;
	}

}
