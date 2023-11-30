package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import java.util.Vector;

/**
 * * Clase de configuracion para CFG del listado de la tabla TOXMuestra
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305041CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305041CFG.png'>
 */
public class pg070305041CFG extends CFGListBase2 {
	public Vector VRegistros = new Vector();
	public Vector VLote = new Vector();

	public pg070305041CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070305040.jsp|";
	}

	private StringBuffer activeX = new StringBuffer("");
	private StringBuffer activeY = new StringBuffer("");

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXCuantAnalisis dTOXAnalsis = new TDTOXCuantAnalisis();
		StringBuffer cFiltro = new StringBuffer();
		String cAnio, cLaboratorio, cSustancia, cLote;
		try {
			cAnio = request.getParameter("hdCampoClave");
			cLaboratorio = request.getParameter("hdCampoClave2");
			cSustancia = request.getParameter("hdCampoClave3");
			cLote = request.getParameter("hdRowNum");
			cFiltro.append(" where CA.iAnio            = ").append(cAnio)
					.append("   and CA.iCveLaboratorio  = ")
					.append(cLaboratorio)
					.append("   and CA.iCveSustancia    = ").append(cSustancia)
					.append("   and CA.iCveLoteCuantita = ").append(cLote);
			if (this.cCondicion.compareToIgnoreCase("") > 0)
				cFiltro.append(" and ").append(cCondicion);
			if (this.cOrden.compareToIgnoreCase("") > 0)
				cFiltro.append(" ").append(cOrden);
			else
				cFiltro.append(" order by CA.iAnio, CA.iCveLaboratorio, ")
						.append("          CA.iCveSustancia, CA.iCveLoteCuantita, CA.iPosicion ");

			VRegistros = vDespliega = dTOXAnalsis
					.FindResult(cFiltro.toString());
			cFiltro = new StringBuffer();
			cFiltro.append(" where LC.iAnio            = ").append(cAnio)
					.append("   and LC.iCveLaboratorio  = ")
					.append(cLaboratorio)
					.append("   and LC.iCveSustancia    = ").append(cSustancia)
					.append("   and LC.iCveLoteCuantita = ").append(cLote);
			VLote = (new TDTOXLoteCuantita()).DetalleLote(cFiltro.toString());

			if (request.getParameter("hdBoton").compareToIgnoreCase(
					"Generar Reporte") == 0) {
				activeX.append(this.Report());
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public String getActiveY() {
		return activeY.toString();
	}

	public StringBuffer Report() {
		TExcel Rep = new TExcel("07");
		TDTOXEnvio dTOXEnvio = new TDTOXEnvio();
		TDTOXAnalisis DAnalisis = new TDTOXAnalisis();
		TDTOXLoteCuantita DCuantita = new TDTOXLoteCuantita();
		TVTOXEnvio vTOXEnvio = new TVTOXEnvio();
		TVTOXLoteCuantita vTOXCuantita = new TVTOXLoteCuantita();
		TVMuestra vMuestra;
		TVTOXLoteCuantita vInfoLote = new TVTOXLoteCuantita();

		Vector cCeldas = new Vector();
		Vector vLotes = new Vector();
		String cNomArchivo = "TarjConf_";
		TFechas tFecha = new TFechas("07");
		int iReng = 8, iHoja = 2;
		boolean lWhere = false;
		cCeldas.add("B");
		cCeldas.add("C");
		cCeldas.add("D");
		cCeldas.add("E");
		cCeldas.add("F");
		cCeldas.add("G");
		cCeldas.add("H");
		cCeldas.add("I");
		cCeldas.add("J");
		try {
			Vector VReporte = new Vector();
			Vector VTOXCuantita = new Vector();
			TVTOXAnalisis VFiltro = new TVTOXAnalisis();
			vInfoLote.setiAnio(new Integer(request.getParameter("hdCampoClave")
					.toString()));
			vInfoLote.setiCveLaboratorio(new Integer(request.getParameter(
					"hdCampoClave2").toString()));
			vInfoLote.setiCveSustancia(new Integer(request.getParameter(
					"hdCampoClave3").toString()));
			vInfoLote.setiCveLoteCuantita(new Integer(request.getParameter(
					"hdRowNum").toString()));
			vLotes = dTOXEnvio.RepTarjPost(vInfoLote);
			// Desglozar la informaci�n de los env�os
			cNomArchivo += vInfoLote.getiAnio() + "_"
					+ vInfoLote.getiCveLaboratorio() + "_"
					+ vInfoLote.getiCveSustancia() + "_"
					+ vInfoLote.getiCveLoteCuantita();
			if (vLotes.size() > 0) {
				for (int i = 0; i < vLotes.size(); i++) {
					vTOXEnvio = new TVTOXEnvio();
					vTOXEnvio = (TVTOXEnvio) vLotes.get(i);
					// Recorrer las muestras
					// System.out.println("vTOXEnvio.vMuestra.size() = "+vTOXEnvio.vMuestra.size());
					for (int iNumMuest = 0; iNumMuest < vTOXEnvio.vMuestra
							.size(); iNumMuest++) {
						if (vTOXEnvio.getLResultado() == 1 || iNumMuest == 0) {
							// Generar tarjeta
							iReng = 8;
							Rep.comCopiaHoja(
									1,
									Rep.getAT_POSFINAL(),
									vTOXEnvio.getICveUniMed() + "_"
											+ vTOXEnvio.getICveEnvio() + "_"
											+ (iHoja - 1));
							Rep.comEligeHoja(iHoja++);
							// Imprimir encabezado
							String cFecha = "México D.F., a "
									+ tFecha.getIntDay(tFecha.TodaySQL())
									+ " de "
									+ tFecha.getMonthName(tFecha.TodaySQL())
									+ " de "
									+ tFecha.getIntYear(tFecha.TodaySQL());
							// Presentar la informaci�n del Env�o
							Rep.comDespliega("G", iReng, cFecha);
							iReng += 4;
							Rep.comDespliega("A", iReng,
									"DR(A). " + vTOXEnvio.getCNombreRec());
							iReng += 2;
							Rep.comDespliega("A", iReng, "JEFE DE LA U. M. "
									+ vTOXEnvio.getCDscUniMed());
							iReng += 8;
							Rep.comDespliega("C", iReng,
									vTOXEnvio.getCDscUniMed());
							Rep.comDespliega("C", ++iReng,
									String.valueOf(vTOXEnvio.getIAnio()));
							Rep.comDespliega(
									"H",
									iReng,
									tFecha.getFechaDDMMMMMYYYY(
											vTOXEnvio.getdtEnvio(), " de "));
							Rep.comDespliega("C", ++iReng,
									String.valueOf(vTOXEnvio.getICveEnvio()));
							Rep.comDespliega(
									"H",
									iReng,
									tFecha.getFechaDDMMMMMYYYY(
											vTOXEnvio.getdtRecepcion(), " de "));
							// Presentar resumen de la recepci�n del env�o
							iReng += 6;
							Rep.comDespliega("C", iReng, String
									.valueOf(vTOXEnvio.getITotalEnviadas()));
							Rep.comDespliega("C", iReng + 1, String
									.valueOf(vTOXEnvio.getITotalRechazadas()));
							Rep.comDespliega("C", iReng + 2, String
									.valueOf(vTOXEnvio.getITotalRecibidas()));
							Rep.comDespliega(
									"H",
									iReng,
									String.valueOf(vTOXEnvio
											.getITotalRecibidas()
											- (vTOXEnvio.getITotalPendientes() + vTOXEnvio
													.getITotalPositivos())));
							Rep.comDespliega("H", iReng + 1, String
									.valueOf(vTOXEnvio.getITotalPendientes()));
							iReng = 36;
						}
						iReng++;
						vMuestra = new TVMuestra();
						vMuestra = (TVMuestra) vTOXEnvio.vMuestra
								.get(iNumMuest);
						// Enviar informaci�n de las muestras
						Rep.comDespliega("B", iReng,
								String.valueOf(vMuestra.getCMuestra()));
						Rep.comAlineaRango("B", iReng, "C", iReng,
								Rep.getAT_HCENTRO());
						Rep.comDespliega("D", iReng, vMuestra.getCDscProceso());
						Rep.comAlineaRango("D", iReng, "F", iReng,
								Rep.getAT_HCENTRO());
						Rep.comDespliega("G", iReng,
								vMuestra.getLResultado() == 0 ? "NEGATIVO"
										: "POSITIVO");
						Rep.comAlineaRango("G", iReng, "I", iReng,
								Rep.getAT_HCENTRO());
						Rep.comBordeTotal("B", iReng, "I", iReng, 1);
						// Enviar informaci�n de las sustancias a las cuales
						// es positiva la muestra
						if (vMuestra.getLResultado() == 1) {
							// Encabezado
							iReng += 2;
							Rep.comDespliega("B", iReng,
									"SUSTANCIA PSICOTRÓPICA");
							Rep.comDespliega("G", iReng,
									"FECHA DE CONFIRMACIÓN");
							Rep.comAlineaRango("B", iReng, "F", iReng,
									Rep.getAT_COMBINA_CENTRO());
							Rep.comAlineaRango("G", iReng, "I", iReng,
									Rep.getAT_COMBINA_CENTRO());
							Rep.comFillCells("B", iReng, "I", iReng, 15);
							Rep.comFontBold("B", iReng, "I", iReng);
							Rep.comBordeTotal("B", iReng, "I", iReng, 1);
							// Detalle de las sustancias
							// System.out.println("vMuestra.vResultado.size() = "+vMuestra.vResultado.size());
							String Muestra = "";
							String Sustancia = "";

							for (int iNumSust = 0; iNumSust < vMuestra.vResultado
									.size(); iNumSust++) {
								vInfoLote = new TVTOXLoteCuantita();
								vInfoLote = (TVTOXLoteCuantita) vMuestra.vResultado
										.get(iNumSust);
								iReng++;
								if (vMuestra.vResultado.size() > 1) {
									if (!Sustancia.equals(vInfoLote.VSustancia
											.getCTitRepConf())) {
										Rep.comDespliega("B", iReng,
												vInfoLote.VSustancia
														.getCTitRepConf());
										// System.out.println("vInfoLote.VSustancia.getCTitRepConf() = "+vInfoLote.VSustancia.getCTitRepConf());
										// Rep.comDespliega("G", iReng,
										// tFecha.getFechaDDMMMMMYYYY(vInfoLote.getdtAnalisis(),
										// " de "));
										// Obteniendo fecha de autorizacion
										String cAnio, cLaboratorio, cSustancia, cLote;
										cAnio = request
												.getParameter("hdCampoClave");
										cLaboratorio = request
												.getParameter("hdCampoClave2");
										cSustancia = request
												.getParameter("hdCampoClave3");
										cLote = request
												.getParameter("hdRowNum");
										String cClave = " where iAnio = "
												+ cAnio
												+ " and  iCveSustancia = "
												+ cSustancia
												+ " and iCveLoteCuantita = "
												+ cLote + "";
										VTOXCuantita = DCuantita
												.FindByAll(cClave);
										for (int a = 0; a < VTOXCuantita.size(); a++) {
											vTOXCuantita = (TVTOXLoteCuantita) VTOXCuantita
													.get(i);
											// System.out.println(vTOXCuantita.getdtAutorizacion());
											Rep.comDespliega(
													"G",
													iReng,
													tFecha.getFechaDDMMMMMYYYY(
															vTOXCuantita
																	.getdtAutorizacion(),
															" de "));
										}
										Rep.comAlineaRango("B", iReng, "F",
												iReng,
												Rep.getAT_COMBINA_CENTRO());
										Rep.comAlineaRango("G", iReng, "I",
												iReng,
												Rep.getAT_COMBINA_CENTRO());
										Rep.comBordeTotal("B", iReng, "I",
												iReng, 1);
									}
								} else {
									Rep.comDespliega("B", iReng,
											vInfoLote.VSustancia
													.getCTitRepConf());
									// System.out.println("vInfoLote.VSustancia.getCTitRepConf() = "+vInfoLote.VSustancia.getCTitRepConf());
									// Rep.comDespliega("G", iReng,
									// tFecha.getFechaDDMMMMMYYYY(vInfoLote.getdtAnalisis(),
									// " de "));
									// Obteniendo fecha de autorizacion
									String cAnio, cLaboratorio, cSustancia, cLote;
									cAnio = request
											.getParameter("hdCampoClave");
									cLaboratorio = request
											.getParameter("hdCampoClave2");
									cSustancia = request
											.getParameter("hdCampoClave3");
									cLote = request.getParameter("hdRowNum");
									String cClave = " where iAnio = " + cAnio
											+ " and  iCveSustancia = "
											+ cSustancia
											+ " and iCveLoteCuantita = "
											+ cLote + "";
									VTOXCuantita = DCuantita.FindByAll(cClave);
									for (int a = 0; a < VTOXCuantita.size(); a++) {
										vTOXCuantita = (TVTOXLoteCuantita) VTOXCuantita
												.get(i);
										// System.out.println(vTOXCuantita.getdtAutorizacion());
										Rep.comDespliega(
												"G",
												iReng,
												tFecha.getFechaDDMMMMMYYYY(
														vTOXCuantita
																.getdtAutorizacion(),
														" de "));
									}
									Rep.comAlineaRango("B", iReng, "F", iReng,
											Rep.getAT_COMBINA_CENTRO());
									Rep.comAlineaRango("G", iReng, "I", iReng,
											Rep.getAT_COMBINA_CENTRO());
									Rep.comBordeTotal("B", iReng, "I", iReng, 1);

								}

								Muestra = String
										.valueOf(vMuestra.getCMuestra());
								Sustancia = vInfoLote.VSustancia
										.getCTitRepConf() + "";
							}
						}
						// Datos de la firma
						if (vTOXEnvio.getLResultado() == 1
								|| iNumMuest == (vTOXEnvio.vMuestra.size() - 1)) {
							iReng += 5;
							Rep.comFontBold("A", iReng, "F", iReng + 8);
							Rep.comDespliega("A", iReng, "ATENTAMENTE");
							iReng += 4;
							Rep.comDespliega("A", iReng, this.vParametros
									.getPropEspecifica("TOXPuestoFirma"));
							Rep.comDespliega("F", iReng, this.vParametros
									.getPropEspecifica("TOXPuestoFirmaD"));
							Rep.comDespliega("A", ++iReng, this.vParametros
									.getPropEspecifica("TOXNombreFirma"));
							Rep.comDespliega("F", iReng, this.vParametros
									.getPropEspecifica("TOXNombreFirmaD"));
							iReng += 5;
							Rep.comDespliega("A", iReng, this.vParametros
									.getPropEspecifica("TOXCCPDPyO"));
						}
					}
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		StringBuffer buffer = Rep.creaActiveX("pg070305041", cNomArchivo,
				false, false, true);
		return buffer;
	}

	public String getOtrasSust(TVTOXCuantAnalisis VCAnal,
			String iCveLoteCuantita) {
		String cTexto = new String();
		String cLaboratorio, cSustancia, cLote;
		try {
			cLaboratorio = request.getParameter("hdCampoClave2");
			cSustancia = request.getParameter("hdCampoClave3");
			cLote = request.getParameter("hdRowNum");
			String query = " and LC.iCveLaboratorio  = " + cLaboratorio
					+ " and LC.iCveSustancia    = " + cSustancia
					+ " and LC.iCveLoteCuantita = " + cLote;
			cTexto = new TDTOXCuantAnalisis().getOtrasSust2(VCAnal).toString();
			cTexto += "&nbsp;";
			// System.out.println("cTexto = "+cTexto);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cTexto;
	}

}
