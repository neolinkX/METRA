package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para CFG Envio de Laboratorio
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. Gonz�lez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070302030CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070302030CFG.png'>
 */
public class pg070402014CFG extends CFGCatBase2 {

	// private Vector vcMuestrasApi = null;
	// private Vector vcMuestrasCan = null;
	private Vector vcINVPersonal = null;
	private int iNoPersonal = 0;

	public pg070402014CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070402010.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDINVPersonal dINVPersonal = new TDINVPersonal();
		TDEXPExamAplica dExamAplica = new TDEXPExamAplica();
		TDEXPExamGenera dExamGenera = new TDEXPExamGenera();
		TDPERDatos dPERDatos = new TDPERDatos();
		TFechas dtFecha = new TFechas();
		Vector vcInsertar = new Vector();
		Vector vcPersona = new Vector();
		String cExamen = "";
		int iEnvio = 0;
		int i = 0;
		try {
			vcInsertar = (Vector) this.getInputs();

			if (vcInsertar.size() > 0) {
				for (i = 0; i < vcInsertar.size(); i++) {
					TVINVPersonal vINVPersonal = new TVINVPersonal();
					TVEXPExamAplica vExamAplica = new TVEXPExamAplica();
					TVEXPExamGenera vExamGenera = new TVEXPExamGenera();
					TVPERDatos vPERDatos = new TVPERDatos();
					// INVPersonal
					vINVPersonal = (TVINVPersonal) vcInsertar.get(i);
					dINVPersonal.insert(null, vINVPersonal);

					// Genera ExamAplica
					vcPersona = dPERDatos.FindByPersona(""
							+ vINVPersonal.getICvePersonal());
					vPERDatos = (TVPERDatos) vcPersona.get(0);

					vExamAplica
							.setICveExpediente(vPERDatos.getICveExpediente());
					vExamAplica.setICvePersonal(vINVPersonal.getICvePersonal());
					vExamAplica.setICveProceso(Integer.parseInt(
							request.getParameter("hdINVProceso"), 10));
					vExamAplica.setDtSolicitado(dtFecha.TodaySQL());
					vExamAplica.setICveUniMed(Integer.parseInt(
							request.getParameter("iCveUniMedSel"), 10));
					vExamAplica.setICveModulo(Integer.parseInt(
							request.getParameter("iCveModulo"), 10));
					vExamAplica.setIFolioEs(0);
					vExamAplica.setLIniciado(0);
					vExamAplica.setLDictaminado(0);
					vExamAplica.setLInterConsulta(0);
					vExamAplica.setLInterConcluye(0);
					vExamAplica.setLConcluido(0);
					vExamAplica.setICveUsuSolicita(Integer.parseInt(
							request.getParameter("hdUsuario"), 10));

					// cExamen = (String) dExamAplica.insert(null, vExamAplica);
					cExamen = (String) new TDGenExamenes().genera(vExamAplica,
							Integer.parseInt(request
									.getParameter("iCvePuesto0")), Integer
									.parseInt(request
											.getParameter("hdiCveMdoTrans")),
							vPERDatos.getCSexo());

					// Actualizacion de INumExamINV en INVPersonal
					vINVPersonal.setINumExamINV(Integer.parseInt(cExamen, 10));
					dINVPersonal.updateExamen(null, vINVPersonal);

					// Genera ExamGenera

					vExamGenera
							.setICveExpediente(vPERDatos.getICveExpediente());
					vExamGenera.setINumExamen(Integer.parseInt(cExamen, 10));
					vExamGenera.setICveProceso(Integer.parseInt(
							request.getParameter("hdTOXProceso"), 10));
					vExamGenera.setDtProgramado(dtFecha.TodaySQL());
					vExamGenera.setDtPosibleAten(dtFecha.TodaySQL());
					vExamGenera.setDtAplicacion(dtFecha.TodaySQL());
					vExamGenera.setINumExamenGenera(0);
					vExamGenera.setLAplicado(0);

					dExamGenera.insert(null, vExamGenera);

					if (vINVPersonal.getICveSituacion() != Integer.parseInt(
							request.getParameter("hdFallecido"), 10)) {

						vExamGenera.setICveProceso(Integer.parseInt(
								request.getParameter("hdEPIProceso"), 10));

						dExamGenera.insert(null, vExamGenera);
					}
				}
			}

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
		TDEnvio dEnvio = new TDEnvio();
		try {
			cClave = (String) dEnvio.update(null, this.getInputs());
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
		TDEnvio dEnvio = new TDEnvio();
		try {
			cClave = (String) dEnvio.delete(null, this.getInputs());
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
		TDEnvio dEnvio = new TDEnvio();
		String cWhere = "";
		Vector vcInvRegistro = new Vector();
		Vector vcInvPersonal = new Vector();
		try {

			TDINVRegistro dINVRegistro = new TDINVRegistro();
			TVINVRegistro vINVRegistro = new TVINVRegistro();

			cWhere = " a.iAnio = " + request.getParameter("hdiAnio")
					+ " and a.iCveMdoTrans = "
					+ request.getParameter("hdiCveMdoTrans")
					+ " and a.iIDDGPMPT = "
					+ request.getParameter("hdiIdefMedPrev");
			vcInvRegistro = dINVRegistro.FindByAll(cWhere, "");
			if (vcInvRegistro.size() > 0) {
				vINVRegistro = (TVINVRegistro) vcInvRegistro.get(0);
				iNoPersonal = vINVRegistro.getIPerFedInvolucra();
				TDINVPersonal dINVPersonal = new TDINVPersonal(true);
				TVINVPersonal vINVPersonal = new TVINVPersonal();
				vcInvPersonal = dINVPersonal.FindPersonal(
						request.getParameter("hdiAnio"),
						request.getParameter("hdiCveMdoTrans"),
						request.getParameter("hdiIdefMedPrev"));

				if (vINVRegistro.getIPerFedInvolucra() == vcInvPersonal.size()) {
					vDespliega = vcInvPersonal;
				}

			}

			/*
			 * if (request.getParameter("hdBoton").compareTo("Enviar") == 0 ){
			 * vDespliega = dEnvio.FindByEnvio(this.getInputs()); } else { if
			 * (request.getParameter("hdBoton").compareTo("Guardar") == 0 &&
			 * request.getParameter("iCveEnvio").compareTo("0") == 0) {
			 * vDespliega = dEnvio.FindByNuevo(this.getInputs()); } else
			 * vDespliega = dEnvio.FindByAll(this.getInputs()); }
			 */
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
		String cWhere = "";
		TFechas tfCampo = new TFechas();
		Vector vcPersonal = new Vector();
		try {

			TDINVRegistro dINVRegistro = new TDINVRegistro();
			TVINVRegistro vINVRegistro = new TVINVRegistro();

			cWhere = " a.iAnio = " + request.getParameter("hdiAnio")
					+ " and a.iCveMdoTrans = "
					+ request.getParameter("hdiCveMdoTrans")
					+ " and a.iIDDGPMPT = "
					+ request.getParameter("hdiIdefMedPrev");
			vcPersonal = dINVRegistro.FindByAll(cWhere, "");
			if (vcPersonal.size() > 0) {
				vINVRegistro = (TVINVRegistro) vcPersonal.get(0);
				iNoPersonal = vINVRegistro.getIPerFedInvolucra();
			}

			// Vector Personal Involucrado
			vcINVPersonal = new Vector();

			Object obElemento;
			int i;
			int iAcumulado = 0;
			int iFlag;

			for (i = 0; i < iNoPersonal; i++) {
				TVINVPersonal vINVPersonal = new TVINVPersonal();
				// Campos Llave
				vINVPersonal.setIAnio(Integer.parseInt(
						request.getParameter("hdiAnio"), 10));
				vINVPersonal.setICveMdoTrans(Integer.parseInt(
						request.getParameter("hdiCveMdoTrans"), 10));
				vINVPersonal.setIIDDGPMPT(Integer.parseInt(
						request.getParameter("hdiIdefMedPrev"), 10));

				// Consecutivo del Personal
				vINVPersonal.setICveInvPers(i + 1);

				// Clave Personal
				cCampo = "" + request.getParameter("txtPersonal" + i);
				if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
					iCampo = Integer.parseInt(cCampo, 10);
				} else {
					iCampo = 0;
				}
				vINVPersonal.setICvePersonal(iCampo);

				// Clave Puesto
				cCampo = "" + request.getParameter("iCvePuesto" + i);
				if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
					iCampo = Integer.parseInt(cCampo, 10);
				} else {
					iCampo = 0;
				}
				vINVPersonal.setICvePuesto(iCampo);

				// Fecha de Vigencia
				cCampo = "" + request.getParameter("dtFecha" + i);
				if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
					dtCampo = tfCampo.getDateSQL(cCampo);
				} else {
					dtCampo = null;
				}
				vINVPersonal.setDtVigencia(dtCampo);

				// Clave Situaci�n
				cCampo = "" + request.getParameter("iCveSituacion" + i);
				if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
					iCampo = Integer.parseInt(cCampo, 10);
				} else {
					iCampo = 0;
				}
				vINVPersonal.setICveSituacion(iCampo);

				// Clave Vehiculo
				cCampo = "" + request.getParameter("iCveVehiculo" + i);
				if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
					iCampo = Integer.parseInt(cCampo, 10);
				} else {
					iCampo = 0;
				}
				vINVPersonal.setICveVehiculo(iCampo);

				vcINVPersonal.addElement(vINVPersonal);

			}
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vcINVPersonal;
	}
}



