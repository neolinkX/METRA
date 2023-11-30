package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070304050CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vLoteCuantita = new Vector();
	public Vector vCtrolCalibra = new Vector();
	public Vector vEQMEquipo = new Vector();
	public Vector vDespliega2 = new Vector();
	public Vector vTOXCalibEquipo = new Vector();
	public TVTOXLoteCalibra VInfoCalibrador = new TVTOXLoteCalibra();
	public TVTOXLoteCuantita VLote = null;
	public boolean lModifica = false;
	public boolean lModificaA = false;
	public boolean lIntegraCalib = false;
	String cvAnio = "";
	int ivSustancia = 0;
	int ivCveLaboratorio = 0;
	int ivLoteCuantita = 0;
	int ivLoteCualita = 0;
	int ivEquipo = 0;
	int ivValidacion = 0;
	java.sql.Date dtvFechaInicial;
	StringBuffer cFiltro = new StringBuffer(), cFiltroC = new StringBuffer();

	// TVUsuario vUsuario = (TVUsuario)
	// httpReq.getSession().getAttribute("UsrID");

	public pg070304050CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {
		TFechas Fecha = new TFechas();
		TDTOXLoteCuantita dLoteCuantita = new TDTOXLoteCuantita();
		int ivDias = new Integer(
				vParametros.getPropEspecifica("TOXDiasCalibracion")).intValue()
				* (-1);

		dtvFechaInicial = Fecha.aumentaDisminuyeDias(Fecha.TodaySQL(), ivDias);

		if (request.getParameter("SLSAnio") != null)
			cvAnio = request.getParameter("SLSAnio");

		if (cvAnio.compareToIgnoreCase("") == 0)
			cvAnio = "2004";

		if (request.getParameter("iCveUniMed") != null)
			ivCveLaboratorio = new Integer(request.getParameter("iCveUniMed"))
					.intValue();

		if (request.getParameter("SLSSustancia") != null)
			ivSustancia = new Integer(request.getParameter("SLSSustancia")
					.toString()).intValue();

		if (request.getParameter("SLSLoteConfirmativo") != null)
			ivLoteCuantita = new Integer(request.getParameter(
					"SLSLoteConfirmativo").toString()).intValue();

		if (request.getParameter("SLSEqControl") != null)
			ivEquipo = new Integer(request.getParameter("SLSEqControl")
					.toString()).intValue();

		if (request.getParameter("TBXValidacion") != null)
			ivValidacion = new Integer(request.getParameter("TBXValidacion")
					.toString()).intValue();

		try {

			vLoteCuantita = dLoteCuantita
					.FindByAll(" where TOXLoteCuantita.iAnio           =           "
							+ cvAnio
							+ "   and TOXLoteCuantita.iCveLaboratorio =           "
							+ ivCveLaboratorio
							+ "   and TOXLoteCuantita.iCveSustancia   =           "
							+ ivSustancia
							+ "   and TOXLoteCuantita.dtGeneracion    is not null "
							+
							// "   and TOXLoteCuantita.dtAnalisis      is null     "
							// +
							// "   and TOXLoteCuantita.dtAutorizacion  is null     "
							// +
							// "   and TOXLoteCuantita.dtCalibracion   is null     "
							// +
							// "   and TOXLoteCuantita.lValidaCalib    is null     "
							// +
							" order by TOXLoteCuantita.iCveLoteCuantita desc");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void Guardar() {
		Vector VDatos = new Vector();
		TVTOXCuantAnalisis VAnalisis;
		TDTOXCuantAnalisis DCuantAnalisis = new TDTOXCuantAnalisis();
		try {
			// Buscar el vector a guardar
			this.fillVector();
			VDatos = this.vDespliega2;
			// Verificar que se va a guardar
			if (((TVTOXCuantAnalisis) VDatos.get(0)).getlAutorizado() == null)
				for (int i = 0; i < VDatos.size(); i++) {
					VAnalisis = new TVTOXCuantAnalisis();
					VAnalisis = (TVTOXCuantAnalisis) VDatos.get(i);
					String cValor = "FILConc"
							+ VAnalisis.getiCveAnalisis().toString();
					if (request.getParameter(cValor) != null) {
						VAnalisis.setdResultadoDil(new Double(request
								.getParameter(cValor).toString()));
						if (request.getParameter("FILTmpRetenc"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDTmpRetenc(new Double(request
									.getParameter(
											"FILTmpRetenc"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis
									.setDTmpRetenc(new Double(0).doubleValue());
						if (request.getParameter("FILTmpRetencD"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDTmpRetencD(new Double(request
									.getParameter(
											"FILTmpRetencD"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDTmpRetencD(new Double(0)
									.doubleValue());
						if (request.getParameter("FILIon01"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon01(new Double(request
									.getParameter(
											"FILIon01"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon01(new Double(0).doubleValue());
						if (request.getParameter("FILIon02"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon02(new Double(request
									.getParameter(
											"FILIon02"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon02(new Double(0).doubleValue());
						if (request.getParameter("FILIon03"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon03(new Double(request
									.getParameter(
											"FILIon03"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon03(new Double(0).doubleValue());
						if (request.getParameter("FILIon04"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon04(new Double(request
									.getParameter(
											"FILIon04"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon04(new Double(0).doubleValue());
						if (request.getParameter("FILIon05"
								+ VAnalisis.getiCveAnalisis().toString()) != null)
							VAnalisis.setDIon05(new Double(request
									.getParameter(
											"FILIon05"
													+ VAnalisis
															.getiCveAnalisis()
															.toString())
									.toString()).doubleValue());
						else
							VAnalisis.setDIon05(new Double(0).doubleValue());

						VAnalisis.setLRegistrado(new Integer(1));
						VAnalisis.setdResultado(new Double(VAnalisis
								.getdResultadoDil().doubleValue()
								* VAnalisis.getiDilusion().intValue()));
					}
					DCuantAnalisis.updCalibracion(VAnalisis);
				}
			else {
				// Guardar la información del Lote
				if (request.getParameter("cObservacion") != null)
					this.VLote.setcObservacion(request.getParameter(
							"cObservacion").toString());
				if (request.getParameter("iCveAccCorrectiva") != null)
					this.VLote.setiCveAcCorrectiva(new Integer(request
							.getParameter("iCveAccCorrectiva").toString()));
				TDTOXLoteCuantita DLoteCuantita = new TDTOXLoteCuantita();
				DLoteCuantita.updCalibracion(this.VLote);
				this.VLote = null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void fillCalibra() {
		TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();
		TDTOXLoteCuantita DLote = new TDTOXLoteCuantita();
		try {
			vDespliega = DTOXCuantAnalisis.FindCalibra(cFiltro.toString());
			vDespliega2 = vDespliega;
			if (vDespliega2.size() > 0) {
				lModifica = ((TVTOXCuantAnalisis) vDespliega2.get(0))
						.getlAutorizado() == null ? true : false;
				cFiltroC = new StringBuffer();
				cFiltroC.append("where CA.iAnio = ").append(cvAnio)
						.append("   and CA.iCveLaboratorio  = ")
						.append(ivCveLaboratorio)
						.append("   and CA.iCveSustancia    = ")
						.append(ivSustancia)
						.append("   and CA.iCveLoteCuantita = ")
						.append(ivLoteCuantita)
						.append("   and CA.iPosicion        = 1");
				// Llenar la calibración
				VInfoCalibrador = (TVTOXLoteCalibra) DTOXCuantAnalisis
						.FindCalibrador(cFiltroC.toString()).get(0);
				if (!lModifica)
					// Traer la información del Lote para la acción correctiva y
					// la observación.
					if (((TVTOXCuantAnalisis) vDespliega2.get(0))
							.getlAutorizado().equals(new Integer(0))) {
						StringBuffer cFiltroL = new StringBuffer();
						cFiltroL.append(" where iAnio = ").append(cvAnio)
								.append("   and iCveLaboratorio  = ")
								.append(ivCveLaboratorio)
								.append("   and iCveSustancia    = ")
								.append(ivSustancia)
								.append("   and iCveLoteCuantita = ")
								.append(ivLoteCuantita);
						VLote = (TVTOXLoteCuantita) DLote.FindByAll(
								cFiltroL.toString()).get(0);
						if (VLote.getiCveAcCorrectiva().equals(new Integer(0)))
							this.lModificaA = true;
					}
			}
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}
	}

	public void fillVector() {
		cPaginas = "pg070304051.jsp|";
		TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();
		TDTOXLoteCuantita DLote = new TDTOXLoteCuantita();
		TVTOXLoteCalibra VLoteCuant = new TVTOXLoteCalibra(), VCalibrador = new TVTOXLoteCalibra();
		Vector VAnalisis = new Vector();
		try {
			cFiltro = new StringBuffer();
			cFiltro.append("where L.iAnio = ").append(cvAnio)
					.append("  and L.iCveLaboratorio  = ")
					.append(ivCveLaboratorio)
					.append("  and L.iCveSustancia    = ").append(ivSustancia)
					.append("  and L.iCveLoteCuantita = ")
					.append(ivLoteCuantita);
			cFiltroC = new StringBuffer();
			cFiltroC.append("where CA.iAnio = ").append(cvAnio)
					.append("   and CA.iCveLaboratorio  = ")
					.append(ivCveLaboratorio)
					.append("   and CA.iCveSustancia    = ")
					.append(ivSustancia)
					.append("   and CA.iCveLoteCuantita = ")
					.append(ivLoteCuantita)
					.append("   and CA.iPosicion        = 1");
			lIntegraCalib = DTOXCuantAnalisis.findCtrolCalibra(
					Integer.parseInt(cvAnio), ivCveLaboratorio, ivSustancia,
					ivLoteCuantita);
			// Verificar si fue Validar
			this.fillCalibra();
			if (request.getParameter("hdBoton") != null)
				if (request.getParameter("hdBoton").toString()
						.equals("Validar")) {

					// Obtener las Sustancias a Unificar
					String cUnifica = "";
					ArrayList aSustancias = new ArrayList();
					try {
						Vector vSustancia = new TDTOXSustancia().FindByAll(
								" where iCveSustancia = " + ivSustancia, "");
						if (vSustancia.size() > 0) {
							cUnifica = ((TVTOXSustancia) (vSustancia.get(0)))
									.getCSustUnifica();
						}
						if (cUnifica == null || cUnifica.equalsIgnoreCase(""))
							cUnifica = String.valueOf(ivSustancia);
						// Obtener un ArrayList con las Sustancias a Unificar
						StringTokenizer stSustancias = new StringTokenizer(
								cUnifica, ",");
						while (stSustancias.hasMoreTokens()) {
							aSustancias.add(stSustancias.nextToken());
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					// Proceso de Validación.
					StringBuffer cFiltroS = new StringBuffer(), cFiltroSC = new StringBuffer();
					int iTotal = 1;
					int iValida = 1;
					// Validar por cada Sustancia que integra el lote
					for (int s = 0; s < aSustancias.size(); s++) {
						iValida = 1;
						// Buscar la sustancia
						TVTOXSustancia vSustancia = (TVTOXSustancia) (new TDTOXSustancia()
								.FindByAll(" where iCveSustancia = "
										+ aSustancias.get(s), "")).get(0);
						cFiltroS = new StringBuffer();
						cFiltroS.append("where L.iAnio = ").append(cvAnio)
								.append("  and L.iCveLaboratorio  = ")
								.append(ivCveLaboratorio)
								.append("  and L.iCveSustancia    = ")
								.append(aSustancias.get(s))
								.append("  and L.iCveLoteCuantita = ")
								.append(ivLoteCuantita);
						if (vSustancia.getLValidaCtrol() == 0) {
							cFiltroS.append(" and A.iCveAnalisis in (")
									.append(this.vParametros.getPropEspecifica(
											"TOXPosCalibCtrol").toString())
									.append(")");
						}
						cFiltroSC = new StringBuffer();
						cFiltroSC.append("where CA.iAnio = ").append(cvAnio)
								.append("   and CA.iCveLaboratorio  = ")
								.append(ivCveLaboratorio)
								.append("   and CA.iCveSustancia    = ")
								.append(aSustancias.get(s))
								.append("   and CA.iCveLoteCuantita = ")
								.append(ivLoteCuantita)
								.append("   and CA.iPosicion        = 1");
						Vector VResultado = new Vector(), vCalibracion = new Vector();
						VResultado = DTOXCuantAnalisis.FindValCalibra(cFiltroS
								.toString());
						vCalibracion = DTOXCuantAnalisis
								.FindCalibrador(cFiltroSC.toString());
						if (vCalibracion.size() > 0) {
							if (VResultado.size() > 0) {
								VCalibrador = (TVTOXLoteCalibra) vCalibracion
										.get(0);
								for (int i = 0; i < VResultado.size(); i++) {
									// System.out.println("Tamaño * " +
									// VResultado.size());
									VLoteCuant = (TVTOXLoteCalibra) VResultado
											.get(i);
									VLoteCuant.VAnalisis
											.setLCorrecto(new Integer(1));
									if (VLoteCuant.VAnalisis.getlControl()
											.intValue() == 1) { // Controles
										if (VLoteCuant.VAnalisis
												.getdConcentracion()
												.doubleValue() == 0
												&& VLoteCuant.VAnalisis
														.getdResultado()
														.doubleValue() > 0) {
											VLoteCuant.VAnalisis
													.setLCorrecto(new Integer(
															DTOXCuantAnalisis
																	.ValidaMuestra(
																			VCalibrador,
																			VLoteCuant.VAnalisis)));
											if (VLoteCuant.VAnalisis
													.getLCorrecto().intValue() == 1) {
												VLoteCuant.VAnalisis
														.setLCorrecto(new Integer(
																0));
												iValida = 0;
											} else
												VLoteCuant.VAnalisis
														.setLCorrecto(new Integer(
																1));
										} else {
											if (VLoteCuant.VAnalisis
													.getdResultado()
													.doubleValue() < VLoteCuant
													.getDLimInferior()
													|| VLoteCuant.VAnalisis
															.getdResultado()
															.doubleValue() > VLoteCuant
															.getDLimSuperior()) {
												VLoteCuant.VAnalisis
														.setLCorrecto(new Integer(
																0));
												iValida = 0;
											} else {
												// Validar la muestra con su
												// relación de Iones y Tiempo de
												// Retención
												VLoteCuant.VAnalisis
														.setLCorrecto(new Integer(
																DTOXCuantAnalisis
																		.ValidaMuestra(
																				VCalibrador,
																				VLoteCuant.VAnalisis)));
												iValida = VLoteCuant.VAnalisis
														.getLCorrecto()
														.intValue() == 0 ? VLoteCuant.VAnalisis
														.getLCorrecto()
														.intValue() : iValida;
											}
										} // Controles que no son negativos
									} // Calibrador
									else {
										if (VLoteCuant.VAnalisis
												.getdResultado().doubleValue() < VCalibrador
												.getDLimInferior()
												|| VLoteCuant.VAnalisis
														.getdResultado()
														.doubleValue() > VCalibrador
														.getDLimSuperior()) {
											VLoteCuant.VAnalisis
													.setLCorrecto(new Integer(0));
											iValida = 0;
										}
									}
									VAnalisis.add(VLoteCuant.VAnalisis);
								} // Resultados de la calibración
							} // Tiene registros.
							VLoteCuant.VLote.setlValidaCalib(new Integer(
									iValida));
							TFechas Fecha = new TFechas();
							VLoteCuant.VLote.setdtCalibracion(new TFechas()
									.TodaySQL());
							try {
								// La Calibración es incorrecta.
								if (iValida == 0) {
									TDTOXExamResult DExamResult = new TDTOXExamResult();
									// Condición para la Liberación de las
									// muestras.
									String cCondicUpd = " update TOXExamResult "
											+ " set TOXExamResult.iAsignado = 0 "
											+ " where TOXExamResult.iAnio = "
											+ cvAnio
											+ " and   TOXExamResult.iCveLaboratorio = "
											+ ivCveLaboratorio
											+ " and   TOXExamResult.iCveLoteCualita in (select distinct TOXAnalisis.iCveLoteCualita "
											+ "                                         from TOXLoteCuantita "
											+ "                                         join TOXCuantAnalisis "
											+ "                                         on   TOXCuantAnalisis.iAnio            = TOXLoteCuantita.iAnio "
											+ "                                         and  TOXCuantAnalisis.iCveSustancia    = TOXLoteCuantita.iCveSustancia "
											+ "                                         and  TOXCuantAnalisis.iCveLoteCuantita = TOXLoteCuantita.iCveLoteCuantita "
											+ "                                         and  TOXCuantAnalisis.iCveLaboratorio  = TOXLoteCuantita.iCveLaboratorio "
											+ "                                         join TOXAnalisis  "
											+ "                                         on   TOXAnalisis.iAnio           = TOXCuantAnalisis.iAnio "
											+ "                                         and  TOXAnalisis.iCveLaboratorio = TOXCuantAnalisis.iCveLaboratorio "
											+ "                                         and  TOXAnalisis.iCveAnalisis    = TOXCuantAnalisis.iCveAnalisis "
											+ "                                         where TOXLoteCuantita.iAnio = "
											+ cvAnio
											+ "                                         and   TOXLoteCuantita.iCveLaboratorio = "
											+ ivCveLaboratorio
											+ "                                         and   TOXLoteCuantita.iCveSustancia in ("
											+ cUnifica
											+ ") "
											+ "                                         and   TOXLoteCuantita.iCveLoteCuantita = "
											+ ivLoteCuantita
											+ " ) "
											+ " and   TOXExamResult.iCveSustancia in ("
											+ cUnifica
											+ ") "
											+ " and   TOXExamResult.iCveAnalisis  in (select TOXCuantAnalisis.iCveAnalisis "
											+ "                                       from   TOXCuantAnalisis "
											+ "                                       where  TOXCuantAnalisis.iAnio            = "
											+ cvAnio
											+ "                                         and  TOXCuantAnalisis.iCveSustancia    in ("
											+ cUnifica
											+ ") "
											+ "                                         and  TOXCuantAnalisis.iCveLoteCuantita = "
											+ ivLoteCuantita
											+ "                                         and  TOXCuantAnalisis.iCveLaboratorio  = "
											+ ivCveLaboratorio
											+ " ) "
											+ " and   TOXExamResult.iAsignado     = 1 "
											+ "";

									// Activación de las Muestras del Lote, para
									// poder ser asignadas a otro.
									DExamResult.ActivarMuestras(cCondicUpd);
								}
								TDTOXLoteCuantita DLoteCuantita = new TDTOXLoteCuantita();
								// Modificar la validación por registro
								DTOXCuantAnalisis.updCorrecto(VAnalisis);
								// System.out.println("Antes Actualización " +
								// VLoteCuant.VLote.getiAnio());
								DLoteCuantita.updCalibracion(VLoteCuant.VLote);
								lModifica = false;
								vErrores.acumulaError(
										"La Calibración de la Sustancia "
												+ vSustancia.getcDscSustancia()
												+ " es "
												+ (iValida == 0 ? "Incorrecta"
														: "Correcta"), 0, "");
								this.fillCalibra();
								iTotal *= iValida;

							} catch (Exception ex) {
								ex.printStackTrace();

							} // actualizar los lotes
						} // Existe la calibración
						else
							vErrores.acumulaError(
									"La Calibración de la Sustancia "
											+ aSustancias.get(s)
											+ " no ha sido cargada.", 0, "");

					} // Para cada sustancia.
						// al final de la validación. realizar la actualización
						// de todas las calibraciones
					if (iTotal == 0) {
						TDTOXLoteCuantita DLoteCuantita = new TDTOXLoteCuantita();
						TVTOXLoteCuantita vLote = new TVTOXLoteCuantita();
						vLote.setiAnio(new Integer(cvAnio));
						vLote.setiCveLaboratorio(new Integer(ivCveLaboratorio));
						vLote.setiCveSustancia(new Integer(ivSustancia));
						vLote.setiCveLoteCuantita(new Integer(ivLoteCuantita));
						boolean lRealizado = DLoteCuantita
								.updCalibracionVarias(vLote, cUnifica);
					}
				} // Validar
		} catch (Exception e) {
			e.printStackTrace();
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}
		if (!vDespliega.isEmpty()) {
			if (lModifica) {
				UpdStatus = "SaveCancelOnly";
				NavStatus = "Hidden";
				cImprimir = "Imprimir";
			} else {
				UpdStatus = "Hidden";
				NavStatus = "Hidden";
				cImprimir = "Imprimir";
			}
			if (lModificaA) {
				UpdStatus = "SaveCancelOnly";
				NavStatus = "Hidden";
				cImprimir = "Imprimir";
			}

		} else {
			UpdStatus = "Hidden";
			NavStatus = "Hidden";
			cImprimir = "Imprimir";
		}

	}
}
