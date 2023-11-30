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
public class pg070402021CFG extends CFGCatBase2 {

	// private Vector vcMuestrasApi = null;
	// private Vector vcMuestrasCan = null;
	private Vector vcINVPersonal = null;
	private int iNoPersonal = 0;

	public pg070402021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		UpdStatus = "Hidden";
		cPaginas = "pg070402010.jsp|pg070402020.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {

		TDINVEquipoInv dEquipoInv = new TDINVEquipoInv();
		Vector vcAsigna = new Vector();
		Object obElemento;
		int i = 0;
		try {

			vcAsigna = (Vector) this.getInputs();
			if (vcAsigna.size() > 0) {
				for (i = 0; i < vcAsigna.size(); i++) {
					obElemento = vcAsigna.get(i);
					TVINVEquipoInv vEquipoInv = (TVINVEquipoInv) obElemento;
					dEquipoInv.insert(null, vEquipoInv);

				}
				this.GeneraSerRamRes();

			}

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/*
	 * /** Metodo GeneraSerRamRes
	 * 
	 * @author Marco A. Gonz�lez Paz
	 * 
	 * @return Copia del Proceso de Inserci�n de
	 * INVServicio/INVRama/INVResultado
	 */

	public void GeneraSerRamRes() {
		Vector vcMedSintExamen = new Vector();
		Vector vcMedSintExamenServicios = new Vector();
		Vector vcMedRama = new Vector();
		Vector vcMedSintoma = new Vector();

		TDMEDSintExamen dMEDSintExamen = new TDMEDSintExamen();
		TVMEDSintExamen vMEDSintExamen = new TVMEDSintExamen();
		TDINVServicio dINVServicio = new TDINVServicio();
		TDMEDRama dMEDRama = new TDMEDRama();
		TVMEDRama vMEDRama;
		TDINVRama dINVRama = new TDINVRama();
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		TVMEDSintoma vMEDSintoma;
		TDINVResultado dINVResultado = new TDINVResultado();
		Vector vRama = new Vector();
		Vector vResultado = new Vector();

		int iMotivo = 0;
		String cWhere = "";

		// B�squeda del Motivo
		TDINVRegistro dINVRegistro = new TDINVRegistro();

		try {

			cWhere = " iAnio = " + request.getParameter("hdiAnio")
					+ " and iCveMdoTrans = "
					+ request.getParameter("hdiCveMdoTrans")
					+ " and iIDDGPMPT = "
					+ request.getParameter("hdiIdefMedPrev") + " ";

			iMotivo = dINVRegistro.FindMotivo(cWhere);

			vcMedSintExamen = dMEDSintExamen
					.FindByAllDistinct(" Where iCveProceso = "
							+ vParametros.getPropEspecifica("INVProceso")
							+ " And   iCveMotivo  = " + iMotivo
							+ " And   iCveMdoTrans= "
							+ request.getParameter("hdiCveMdoTrans"));

			if (vcMedSintExamen.size() > 0) {

				int iCveServicioAnt = 0;
				int iCveRamaAnt = 0;
				for (int y = 0; y < vcMedSintExamen.size(); y++) {
					vMEDSintExamen = (TVMEDSintExamen) vcMedSintExamen.get(y);

					/**
					 * Paso 1
					 * 
					 * Se inserta en la tabla INVServicio los campos
					 * 
					 * - iAnio - iCveMdoTrans - iIDDGPMPT - iCveServicio -
					 * iCveUsuario
					 * 
					 */

					if (iCveServicioAnt != vMEDSintExamen.getICveServicio()) {
						dINVServicio.insertFromExpExamen(null, Integer
								.parseInt(request.getParameter("hdiAnio"), 10),
								Integer.parseInt(
										request.getParameter("hdiCveMdoTrans"),
										10), Integer.parseInt(
										request.getParameter("hdiIdefMedPrev"),
										10), vMEDSintExamen.getICveServicio(),
								Integer.parseInt(
										request.getParameter("hdUsuario"), 10));
					}

					vcMedRama = dMEDRama.FindByAll(" Where iCveServicio = "
							+ vMEDSintExamen.getICveServicio()
							+ " and lActivo = 1 ");

					if (vcMedRama.size() > 0) {
						for (int a = 0; a < vcMedRama.size(); a++) {
							vMEDRama = (TVMEDRama) vcMedRama.get(a);

							/**
							 * Paso 2
							 * 
							 * Se inserta en la tabla INVRama los campos
							 * 
							 * - iAnio - iCveMdoTrans - iIDDGPMPT - iCveServicio
							 * - iCveRama
							 * 
							 */

							vRama = dINVRama
									.FindCustomWhere(" where iAnio        = "
											+ Integer.parseInt(request
													.getParameter("hdiAnio"),
													10)
											+ "   and iCveMdoTrans = "
											+ Integer.parseInt(
													request.getParameter("hdiCveMdoTrans"),
													10)
											+ "   and iIDDGPMPT    = "
											+ Integer.parseInt(
													request.getParameter("hdiIdefMedPrev"),
													10)
											+ "   and iCveServicio = "
											+ vMEDSintExamen.getICveServicio()
											+ "   and iCveRama     = "
											+ vMEDRama.getICveRama());
							if (vRama.isEmpty())
								dINVRama.insertFromExpExamen(
										null,
										Integer.parseInt(
												request.getParameter("hdiAnio"),
												10),
										Integer.parseInt(
												request.getParameter("hdiCveMdoTrans"),
												10),
										Integer.parseInt(
												request.getParameter("hdiIdefMedPrev"),
												10), vMEDSintExamen
												.getICveServicio(), vMEDRama
												.getICveRama(),
										Integer.parseInt(request
												.getParameter("hdUsuario"), 10));

							/**
							 * Paso 3
							 * 
							 * Se inserta en la tabla INVResultado los campos
							 * 
							 * - iAnio - iCveMdoTrans - iIDDGPMPT - iCveServicio
							 * - iCveRama - iCveSintoma
							 * 
							 */
							vcMedSintoma = dMEDSintoma
									.FindByAll(" Where a.iCveServicio = "
											+ vMEDSintExamen.getICveServicio()
											+ " And  a.iCveRama      = "
											+ vMEDRama.getICveRama());
							if (vcMedSintoma.size() > 0) {
								for (int b = 0; b < vcMedSintoma.size(); b++) {
									vMEDSintoma = (TVMEDSintoma) vcMedSintoma
											.get(b);

									vResultado = dINVResultado
											.FindByAll(" where iAnio        = "
													+ Integer.parseInt(
															request.getParameter("hdiAnio"),
															10)
													+ "   and iCveMdoTrans = "
													+ Integer.parseInt(
															request.getParameter("hdiCveMdoTrans"),
															10)
													+ "   and iIDDGPMPT    = "
													+ Integer.parseInt(
															request.getParameter("hdiIdefMedPrev"),
															10)
													+ "   and iCveServicio = "
													+ vMEDSintExamen
															.getICveServicio()
													+ "   and iCveRama     = "
													+ vMEDRama.getICveRama()
													+ "   and iCveSintoma  = "
													+ vMEDSintoma
															.getICveSintoma());

									if (vResultado.isEmpty()) {
										dINVResultado
												.insertFromExpExamen(
														null,
														Integer.parseInt(
																request.getParameter("hdiAnio"),
																10),
														Integer.parseInt(
																request.getParameter("hdiCveMdoTrans"),
																10),
														Integer.parseInt(
																request.getParameter("hdiIdefMedPrev"),
																10),
														vMEDSintExamen
																.getICveServicio(),
														vMEDRama.getICveRama(),
														vMEDSintoma
																.getICveSintoma());
									}
								}
							}
							iCveRamaAnt = vMEDRama.getICveRama();
						}
					}
					iCveServicioAnt = vMEDSintExamen.getICveServicio();
					// iCveRamaAnt = 0;
				}
			}
		} catch (Exception ex) {
			error("GeneraSerRamRes", ex);
		}

	}

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
		UpdStatus = "Hidden";
		int iUniMed = 0;
		try {

			TDINVEquipoInv dINVEquipoInv = new TDINVEquipoInv();
			TDINVEquipoInv vINVEquipoInv = new TDINVEquipoInv();

			cWhere = " where iAnio = " + request.getParameter("hdiAnio")
					+ " and iCveMdoTrans = "
					+ request.getParameter("hdiCveMdoTrans")
					+ " and iIDDGPMPT = "
					+ request.getParameter("hdiIdefMedPrev") + " ";

			vDespliega = dINVEquipoInv.FindByAll(cWhere);

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
		Vector vcMedico = new Vector();
		Vector vcAsignados = new Vector();
		TVINVRegistro vINVRegistro = new TVINVRegistro();
		try {

			TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
			TDINVEquipoInv dEquipoInv = new TDINVEquipoInv();

			cWhere = " Where GRLUMUsuario.iCveUniMed = "
					+ request.getParameter("hdiCveUniMed")
					+ " and GRLUMUsuario.iCveProceso = "
					+ vParametros.getPropEspecifica("INVProceso") + " ";

			vcMedico = dUMUsuario.FindByAll(cWhere);

			Object obElemento;
			int i;
			int iActivo = 0;
			int iNoActivos = 0;

			for (i = 0; i < vcMedico.size(); i++) {
				obElemento = vcMedico.get(i);
				TVGRLUMUsuario vUMUsuario = (TVGRLUMUsuario) obElemento;
				TVINVEquipoInv vEquipoInv = new TVINVEquipoInv();
				if (request.getParameter("" + vUMUsuario.getICveUsuario()) != null) {
					if (request.getParameter("" + vUMUsuario.getICveUsuario())
							.compareTo("ON") == 0) {

						vEquipoInv.setIAnio(Integer.parseInt(
								request.getParameter("hdiAnio"), 10));
						vEquipoInv.setICveMdoTrans(Integer.parseInt(
								request.getParameter("hdiCveMdoTrans"), 10));
						vEquipoInv.setIIDDGPMPT(Integer.parseInt(
								request.getParameter("hdiIdefMedPrev"), 10));
						vEquipoInv.setICveUsuInv(vUMUsuario.getICveUsuario());
						vcAsignados.addElement(vEquipoInv);
					}
				}
			}

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vcAsignados;
	}
}