package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.excepciones.*;
import com.micper.util.*;

/**
 * * Clase de configuración para CFG Consulta de Mantenimientos
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070602010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070602010CFG.png'>
 */
public class pg070702041CFG extends CFGListBase2 {
	public TVVEHVehiculo tvVehiculo = null;
	public Vector vUsuarios = new Vector();
	public Vector vEmpMantto = new Vector();
	public StringBuffer Reporte = new StringBuffer("");

	public pg070702041CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cOrden = "";
		cCondicion = "";
		cPaginas = "pg070702042.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDVEHMantenimiento dMan = new TDVEHMantenimiento();
		TDVEHSeguimiento dSeg = new TDVEHSeguimiento();
		TVVEHSeguimiento tvSeg = new TVVEHSeguimiento();

		int iCveMantenimiento = 0;
		int iCveVehiculo = 0;
		int iCveProceso = 0;
		int iCveEtapaCa = 0;
		int iCveEtapaCo = 0;
		int iCveSolicitante = 0;
		int iCveUsuReg = 0;
		TFechas hoy = new TFechas();
		try {
			if (request.getParameter("iCveMantenimiento") != null
					&& !request.getParameter("iCveMantenimiento").equals("")
					&& request.getParameter("iCveVehiculo") != null
					&& !request.getParameter("iCveVehiculo").equals("")) {
				iCveVehiculo = Integer.parseInt(request.getParameter(
						"iCveVehiculo").toString());
				iCveMantenimiento = Integer.parseInt(request.getParameter(
						"iCveMantenimiento").toString());
			}
			if (request.getParameter("iCveProceso") != null
					&& !request.getParameter("iCveProceso").equals(""))
				iCveProceso = Integer.parseInt(request.getParameter(
						"iCveProceso").toString());
			if (request.getParameter("iCveEtapaCa") != null
					&& !request.getParameter("iCveEtapaCa").equals(""))
				iCveEtapaCa = Integer.parseInt(request.getParameter(
						"iCveEtapaCa").toString());
			if (request.getParameter("iCveEtapaCo") != null
					&& !request.getParameter("iCveEtapaCo").equals(""))
				iCveEtapaCo = Integer.parseInt(request.getParameter(
						"iCveEtapaCo").toString());
			if (request.getParameter("iCveSolicitante") != null
					&& !request.getParameter("iCveSolicitante").equals(""))
				iCveSolicitante = Integer.parseInt(request.getParameter(
						"iCveSolicitante").toString());
			if (request.getParameter("iCveUsuReg") != null
					&& !request.getParameter("iCveUsuReg").equals(""))
				iCveUsuReg = Integer.parseInt(request
						.getParameter("iCveUsuReg").toString());

			tvSeg.setICveMantenimiento(iCveMantenimiento);
			tvSeg.setICveVehiculo(iCveVehiculo);
			tvSeg.setICveProceso(iCveProceso);
			if (request.getParameter("lCancelado") != null
					&& request.getParameter("lCancelado").equals("1"))
				tvSeg.setICveEtapa(iCveEtapaCa);
			else if (request.getParameter("lConcluido") != null
					&& request.getParameter("lConcluido").equals("1"))
				tvSeg.setICveEtapa(iCveEtapaCo);
			tvSeg.setICveSolicitante(iCveSolicitante);
			tvSeg.setDtSolicitud(hoy.TodaySQL());
			tvSeg.setICveUsuReg(iCveUsuReg);
			tvSeg.setICveUsuSolicita(iCveUsuReg);
			tvSeg.setCSolicitante("");
			tvSeg.setCObservacion("");

			dSeg.insert(null, tvSeg);
			dMan.updateDetMan(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al Actualizar el registro", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDVEHVehiculo dVehiculo = new TDVEHVehiculo();
		TDVEHMantenimiento dMantto = new TDVEHMantenimiento();
		TDVEHEmpMantto dEmpMantto = new TDVEHEmpMantto();
		Vector vVeh = new Vector();
		String iCveVehiculo = "";
		String iCveProceso = "";
		String iCveMantenimiento = "";
		String sWhere = "";

		if (request.getParameter("iCveVehiculo") != null
				&& !request.getParameter("iCveVehiculo").equals(""))
			iCveVehiculo = request.getParameter("iCveVehiculo").toString();
		if (request.getParameter("iCveMantenimiento") != null
				&& !request.getParameter("iCveMantenimiento").equals(""))
			iCveMantenimiento = request.getParameter("iCveMantenimiento")
					.toString();
		iCveProceso = vParametros.getPropEspecifica("CtrlVeh");
		if (cAccion.compareToIgnoreCase("Generar Reporte") == 0) {
			Reporte();
		}

		try {
			if (!iCveVehiculo.equals("") && !iCveMantenimiento.equals("")
					&& !iCveProceso.equals("")) {
				vVeh = dVehiculo
						.FindByAllDetalleM(" Where VehVehiculo.iCveVehiculo="
								+ iCveVehiculo);
				vUsuarios = dMantto
						.FindByAllUsuarios(" Where GrlUmUsuario.iCveProceso="
								+ iCveProceso);
				vEmpMantto = dEmpMantto.FindByAll(" Where lActivo=1 ",
						" order by cDscBreve");

				if (vVeh.size() > 0) {
					tvVehiculo = new TVVEHVehiculo();
					tvVehiculo = (TVVEHVehiculo) vVeh.get(0);
				}
				sWhere = " Where VehMantenimiento.iCveVehiculo=" + iCveVehiculo
						+ " and VehMantenimiento.iCveMantenimiento="
						+ iCveMantenimiento;
				vDespliega = dMantto.FindByAllDetalle(sWhere);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
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
		TVVEHMantenimiento vVEHMantenimiento = new TVVEHMantenimiento();
		try {
			cCampo = "" + request.getParameter("iCveVehiculo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveVehiculo(iCampo);

			cCampo = "" + request.getParameter("iCveMantenimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveMantenimiento(iCampo);

			cCampo = "" + request.getParameter("iCveEmpMantto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveEmpMantto(iCampo);

			cCampo = "" + request.getParameter("iKilometraje");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setIKilometraje(iCampo);

			cCampo = "" + request.getParameter("iCveUsuAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveUsuAutoriza(iCampo);

			cCampo = "" + request.getParameter("cAccesorios");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHMantenimiento.setCAccesorios(cCampo);

			cCampo = "" + request.getParameter("cResultado");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHMantenimiento.setCResultado(cCampo);

			cCampo = "" + request.getParameter("cObservaciones");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHMantenimiento.setCObservaciones(cCampo);

			cCampo = "" + request.getParameter("dtRecepcion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHMantenimiento.setDtRecepcion(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuRecibe");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setICveUsuRecibe(iCampo);

			cCampo = "" + request.getParameter("lConcluido");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setLConcluido(iCampo);

			cCampo = "" + request.getParameter("lCancelado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHMantenimiento.setLCancelado(iCampo);

			cCampo = "" + request.getParameter("dtCancelacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHMantenimiento.setDtCancelacion(dtCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vVEHMantenimiento;
	}

	public void Reporte() {
		String cAutorizaOrdenServ = vParametros
				.getPropEspecifica("VEHOrdenAutoriza");
		String cVoBoPuestoOrdenServ = vParametros
				.getPropEspecifica("VEHOrdenVoBoPuesto");
		String cVoBoNombreOrdenServ = vParametros
				.getPropEspecifica("VEHOrdenVoBoNombre");
		if (cAutorizaOrdenServ == null
				|| cAutorizaOrdenServ.equalsIgnoreCase("null"))
			cAutorizaOrdenServ = "NOMBRE:______________________________";
		if (cVoBoPuestoOrdenServ == null
				|| cVoBoPuestoOrdenServ.equalsIgnoreCase("null"))
			cVoBoPuestoOrdenServ = "";
		if (cVoBoNombreOrdenServ == null
				|| cVoBoNombreOrdenServ.equalsIgnoreCase("null"))
			cVoBoNombreOrdenServ = "";
		TExcel tRep = new TExcel("07");
		TFechas tFecha = new TFechas("07");

		TDVEHVehiculo dVehiculo = new TDVEHVehiculo();
		TDVEHMantenimiento dMantto = new TDVEHMantenimiento();
		TDVEHEmpMantto dEmpMantto = new TDVEHEmpMantto();
		TDVEHSeguimiento dSeguimiento = new TDVEHSeguimiento();
		TVVEHVehiculo VVehiculo = null;
		TVVEHMantenimiento VMantto = null;
		TVVEHEmpMantto VEmpresa = null;
		TVVEHSeguimiento VSeguimie = null;

		Vector vVehiculo = new Vector();
		Vector vMantto = new Vector();
		Vector vEmpMant = new Vector();
		Vector vSeguimie = new Vector();

		String iCveVehiculo = "";
		String iCveProceso = "";
		String iCveMantenimiento = "";

		if (request.getParameter("iCveVehiculo") != null
				&& !request.getParameter("iCveVehiculo").equals(""))
			iCveVehiculo = request.getParameter("iCveVehiculo").toString();
		if (request.getParameter("iCveMantenimiento") != null
				&& !request.getParameter("iCveMantenimiento").equals(""))
			iCveMantenimiento = request.getParameter("iCveMantenimiento")
					.toString();
		iCveProceso = vParametros.getPropEspecifica("CtrlVeh");

		try {
			if (!iCveVehiculo.equals("") && !iCveMantenimiento.equals("")
					&& !iCveProceso.equals("")) {
				vVehiculo = dVehiculo
						.FindByAllDetalleM(" Where VehVehiculo.iCveVehiculo="
								+ iCveVehiculo);
				if (vVehiculo != null && vVehiculo.size() > 0) {
					VVehiculo = (TVVEHVehiculo) vVehiculo.get(0);
					// Datos de vehículo
					tRep.comDespliega("B", 12, VVehiculo.getICveVehiculo() + "");
					tRep.comDespliega("E", 12, VVehiculo.getCDscTpoVehiculo());
					tRep.comDespliega("H", 12, VVehiculo.getCPlacas());
					tRep.comDespliega("B", 13, VVehiculo.getCDscMarca());
					tRep.comDespliega("E", 13, VVehiculo.getCDscModelo());
					tRep.comDespliega("H", 13, VVehiculo.getIAnioVeh() + "");
					tRep.comDespliega("B", 14, VVehiculo.getCNumSerie());
					tRep.comDespliega("B", 15, VVehiculo.getCNumMotor());
					tRep.comDespliega("B", 16, VVehiculo.getCInventario());
					vMantto = dMantto
							.FindByAllDetalle(" WHERE VEHMantenimiento.iCveVehiculo = "
									+ iCveVehiculo
									+ " AND VEHMantenimiento.iCveMantenimiento = "
									+ iCveMantenimiento);
					if (vMantto != null && vMantto.size() > 0) {
						VMantto = (TVVEHMantenimiento) vMantto.get(0);
						// Datos de Mantenimiento
						tRep.comDespliega("B", 19,
								VMantto.getICveMantenimiento() + "");
						tRep.comDespliega("E", 19, VMantto.getCDscTpoMantto());
						tRep.comDespliega(
								"H",
								19,
								"'"
										+ tFecha.getFechaDDMMMYYYY(
												VMantto.getDtSolicitud(), "/"));
						tRep.comDespliega(
								"B",
								20,
								"'"
										+ tFecha.getFechaDDMMMYYYY(
												VMantto.getDtInicia(), "/"));
						tRep.comDespliega(
								"E",
								20,
								"'"
										+ tFecha.getFechaDDMMMYYYY(
												VMantto.getDtProgramado(), "/"));
						tRep.comDespliega("H", 20, VMantto.getIKilometraje()
								+ "");
						if (!VMantto.getCDscUsuSolicita().equalsIgnoreCase(
								"null"))
							tRep.comDespliega("B", 21,
									VMantto.getCDscUsuSolicita());
						if (!VMantto.getCDscUsuAutoriza().equalsIgnoreCase(
								"null"))
							tRep.comDespliega("B", 22,
									VMantto.getCDscUsuAutoriza());
						// Datos de Empresa de Mantenimiento
						if (VMantto.getICveEmpMantto() > 0) {
							vEmpMant = dEmpMantto.FindByAll(
									" WHERE iCveEmpMantto = "
											+ VMantto.getICveEmpMantto(), " ");
							if (vEmpMant != null && vEmpMant.size() > 0) {
								VEmpresa = (TVVEHEmpMantto) vEmpMant.get(0);
								tRep.comDespliega("B", 25,
										VEmpresa.getCNombreRS());
								tRep.comDespliega("B", 26, VEmpresa.getCRFC());
								tRep.comDespliega("E", 26, VEmpresa.getCTel());
								tRep.comDespliega("H", 26, VEmpresa.getCFax());
								tRep.comDespliega("B", 27, VEmpresa.getCCalle());
								tRep.comDespliega(
										"B",
										28,
										"Col. " + VEmpresa.getCColonia()
												+ " Ciudad. "
												+ VEmpresa.getCCiudad()
												+ " C.P. " + VEmpresa.getICP());
								if (VEmpresa.getCDscMunicipio() != null
										&& !VEmpresa.getCDscMunicipio()
												.equalsIgnoreCase("null")
										&& VEmpresa.getCDscEstado() != null
										&& !VEmpresa.getCDscEstado()
												.equalsIgnoreCase("null")
										&& VEmpresa.getCDscPais() != null
										&& !VEmpresa.getCDscPais()
												.equalsIgnoreCase("null"))
									tRep.comDespliega(
											"B",
											29,
											VEmpresa.getCDscMunicipio() + ", "
													+ VEmpresa.getCDscEstado()
													+ ", "
													+ VEmpresa.getCDscPais());
								else
									tRep.comDespliega("B", 29, "");
								tRep.comDespliega("B", 30,
										VEmpresa.getCContacto());
							}
						}
						// Datos de Accesorios y Observaciones
						tRep.comDespliega("A", 33, VMantto.getCAccesorios());
						tRep.comAlineaRangoVer("A", 33, "A", 33,
								tRep.getAT_VSUPERIOR());
						tRep.comDespliega("A", 38, VMantto.getCObservaciones());
						tRep.comAlineaRangoVer("A", 38, "A", 38,
								tRep.getAT_VSUPERIOR());
						// Datos de recepción
						tRep.comDespliega("B", 49, VMantto.getCDscUsuRecibe());
						if (VMantto.getDtRecepcion() != null)
							tRep.comDespliega(
									"B",
									50,
									tFecha.getFechaDDMMMYYYY(
											VMantto.getDtRecepcion(), "/"));
						tRep.comDespliega("B", 51, VMantto.getCResultado());
						tRep.comAlineaRangoVer("B", 51, "B", 51,
								tRep.getAT_VSUPERIOR());
						// Datos de Firmas
						if (!cAutorizaOrdenServ.equals(""))
							tRep.comDespliega("A", 65, cAutorizaOrdenServ);
						if (!cVoBoPuestoOrdenServ.equals("")
								&& !cVoBoNombreOrdenServ.equals("")) {
							tRep.comDespliega("F", 59, "Vo. Bo.");
							tRep.comDespliega("F", 60, cVoBoPuestoOrdenServ);
							tRep.comDespliega("F", 64, cVoBoNombreOrdenServ);
							// tRep.comBorde("F", 64, "F", 64, 3, 1, 3, 1);
						}
						// Seguimiento del mantenimiento
						vSeguimie = dSeguimiento
								.FindByAll(
										" WHERE VEHSeguimiento.iCveVehiculo = "
												+ iCveVehiculo
												+ " AND VEHSeguimiento.iCveMantenimiento = "
												+ iCveMantenimiento, "");
						if (vSeguimie != null && vSeguimie.size() > 0) {
							int iRengIni = 68;
							for (int i = 0; i < vSeguimie.size(); i++) {
								VSeguimie = (TVVEHSeguimiento) vSeguimie.get(i);
								tRep.comDespliega(
										"A",
										iRengIni,
										"'"
												+ tFecha.getFechaDDMMMYYYY(
														VSeguimie
																.getDtSolicitud(),
														"/"));
								tRep.comAlineaRango("A", iRengIni, "A",
										iRengIni, tRep.getAT_HCENTRO());
								tRep.comFontSize("A", iRengIni, "A", iRengIni,
										(float) 9);
								tRep.comFontSize("B", iRengIni, "I", iRengIni,
										(float) 7);
								if (VSeguimie.getCDscEtapa() != null
										&& !VSeguimie.getCDscEtapa()
												.equalsIgnoreCase("null"))
									tRep.comDespliega("B", iRengIni,
											VSeguimie.getCDscEtapa());
								tRep.comAlineaRango("B", iRengIni, "C",
										iRengIni,
										tRep.getAT_COMBINA_IZQUIERDA());
								if (VSeguimie.getCSolicitante() != null
										&& !VSeguimie.getCSolicitante().equals(
												""))
									tRep.comDespliega(
											"D",
											iRengIni,
											VSeguimie.getCDscSolicitante()
													+ " - "
													+ VSeguimie
															.getCSolicitante());
								else
									tRep.comDespliega("D", iRengIni,
											VSeguimie.getCDscSolicitante());
								tRep.comAlineaRango("D", iRengIni, "E",
										iRengIni,
										tRep.getAT_COMBINA_IZQUIERDA());
								tRep.comDespliega("F", iRengIni,
										VSeguimie.getCObservacion());
								tRep.comAlineaRango("F", iRengIni, "I",
										iRengIni,
										tRep.getAT_COMBINA_IZQUIERDA());
								tRep.comAlineaRangoVer("A", iRengIni, "I",
										iRengIni, tRep.getAT_VAJUSTAR());
								tRep.comBordeTotal("A", iRengIni, "I",
										iRengIni, 1);
								iRengIni++;
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			error("Reporte", ex);
		}
		Reporte = tRep.creaActiveX("pg070702041", "SolServ_" + iCveVehiculo
				+ "_" + iCveMantenimiento, false, false, true);
	}

	public String getReporte() {
		return Reporte.toString();
	}
}
