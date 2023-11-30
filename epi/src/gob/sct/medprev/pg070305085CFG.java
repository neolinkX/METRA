package gob.sct.medprev;

import java.util.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.util.TFechas;
import com.micper.util.TExcel;
import com.micper.util.TNumeros;
import com.micper.seguridad.vo.TVDinRep02;
import gob.sct.medprev.vo.TVTOXSustancia;

/**
 * * Clase de configuracion para CFG del listado de la tabla TOXEnvio
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Itzia Mar�a del Carmen S�nchez M�ndez.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305085.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305085.png'>
 */
public class pg070305085CFG extends CFGListBase2 {

	public Vector VRegistros = new Vector();
	private StringBuffer activeX = new StringBuffer("");

	public pg070305085CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDExamenCualita dLote = new TDExamenCualita();
		boolean lWhere = false;
		try {

			if (cCondicion.compareToIgnoreCase("") != 0) {
				lWhere = true;
				cCondicion = " " + cCondicion;
			}

			if (request.getParameter("iAnio") != null) {
				if (lWhere)
					cCondicion += " and EC.iAnio = "
							+ request.getParameter("iAnio");
				else {
					cCondicion = "  EC.iAnio = "
							+ request.getParameter("iAnio");
					lWhere = true;
				}
			}

			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.compareTo("0") != 0) {
				if (lWhere)
					cCondicion += " and EC.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
				else {
					cCondicion = "  EC.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
					lWhere = true;
				}
				if (request.getParameter("iCveLoteCualita") != null
						&& request.getParameter("iCveLoteCualita").toString()
								.length() > 0)
					if (lWhere)
						cCondicion += " And EC.iCveLoteCualita = "
								+ request.getParameter("iCveLoteCualita");
					else {
						cCondicion = "  EC.iCveLoteCualita = "
								+ request.getParameter("iCveLoteCualita");
						lWhere = true;
					}

				if (request.getParameter("dtFechaI") != null
						&& request.getParameter("dtFechaF") != null
						&& request.getParameter("dtFechaI").toString().length() > 0
						&& request.getParameter("dtFechaF").toString().length() > 0) {
					int i = Integer.parseInt(request.getParameter("RSFecha")
							.toString(), 10);
					String Fecha = "";
					switch (i) {
					case 1:
						Fecha = "EC.dtEntrega ";
						break;
					case 2:
						Fecha = "EC.dtProcesado ";
						break;
					case 3:
						Fecha = "EC.dtAutorizado ";
						break;
					}
					TFechas TFecha = new TFechas();

					if (lWhere)
						cCondicion += " and "
								+ Fecha
								+ "between '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaI").toString())
								+ "' And '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaF").toString()) + "'";
					else {
						cCondicion += " "
								+ Fecha
								+ "between '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaI").toString())
								+ "'  And '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaF").toString()) + "'";
						lWhere = true;
					}
				}
				if (cOrden.compareTo("") == 0)
					cOrden = " order by EC.iAnio, EC.iCveLaboratorio, EC.iCveLoteCualita ";
				cCondicion += " " + cOrden;

			}
			// Informaci�n para el reporte
			if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
				// Realizar la b�squeda
				ArrayList vConsultas = new TDExamenCualita()
						.findGlobal(cCondicion);
				this.activeX.append(this.GenReporte(vConsultas));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}

	public StringBuffer GenReporte(ArrayList vInfo) {
		TExcel Rep = new TExcel("07");
		TVDinRep02 vResultado, vCalibracion;
		TFechas Fecha = new TFechas();
		TNumeros Numero = new TNumeros();
		StringBuffer buffer = new StringBuffer();
		String cNomArchivo = "InfoLoteCualita_";
		int iReng01 = 1, iReng02;
		ArrayList aCalibracion = new ArrayList();
		TDExamenCualita dExamen = new TDExamenCualita();
		try {
			// Verificar que existan registros a Desplegar
			if (vInfo.size() > 0 && ((ArrayList) vInfo.get(0)).size() > 0) {
				// Rep.comDespliega("A", iReng01,
				// "Global de Lotes confirmatorio");
				iReng01 = iReng02 = 3;
				// Generar celdas para las sustancias
				Vector vSustancias = new TDTOXSustancia()
						.FindByAll(
								" where lActivo = 1 and lAnalizada = 1 and lPositiva = 1 or iCveSustancia = 6 ",
								" order by iCveSustancia ");
				char Cel = 'M', CelS = 'M';
				String cColumnaI = " ";
				String cCelda, cCeldaA, cCeldaI, cCeldaIC;
				cCeldaA = cCeldaIC = cCeldaI = cCelda = cColumnaI + Cel;
				HashMap hCeldaSust = new HashMap();
				StringBuffer cLote = new StringBuffer();
				for (int i = 1; i <= 2; i++) {
					// Generar columnas para las sustancias An�lisis
					// confirmatorio a Desplegar
					for (int s = 0; s < vSustancias.size(); s++) {
						cCelda = cColumnaI + String.valueOf(Cel);
						if (s == 0)
							cCeldaIC = cCelda;
						if (i == 1 && s == 0)
							Rep.comDespliega(cCeldaIC, iReng01 - 1,
									"Calibraci�n por Sustancia");
						if (i == 2 && s == 0)
							Rep.comDespliega(cCeldaIC, iReng01 - 1,
									"Controles Internos");
						hCeldaSust.put(
								String.valueOf(((TVTOXSustancia) vSustancias
										.get(s)).getiCveSustancia()) + "_" + i,
								cCelda);
						Rep.comDespliega(cCelda, iReng01,
								((TVTOXSustancia) vSustancias.get(s))
										.getcDscSustancia());
						if (i == 1)
							Rep.comDespliega(cCelda, iReng01 + 1, "Reactivo");
						else
							Rep.comAlineaRango(cCelda, iReng01, cCelda,
									iReng01 + 1, Rep.getAT_COMBINA_CENTRO());
						// campos para la calibraci�n
						if (i == 1) {
							CelS = (char) (Cel + 1);
							if (CelS > 'Z') {
								if (cColumnaI.equalsIgnoreCase(" "))
									cColumnaI = "A";
								else
									cColumnaI = String
											.valueOf((char) (cColumnaI
													.charAt(0) + 1));
								CelS = 'A';
							}
							cCeldaA = cColumnaI + String.valueOf(CelS);
							Rep.comDespliega(cCeldaA, iReng01 + 1, "Valor 01");

							CelS = (char) (CelS + 1);
							if (CelS > 'Z') {
								if (cColumnaI.equalsIgnoreCase(" "))
									cColumnaI = "A";
								else
									cColumnaI = String
											.valueOf((char) (cColumnaI
													.charAt(0) + 1));
								CelS = 'A';
							}
							cCeldaA = cColumnaI + String.valueOf(CelS);
							Rep.comDespliega(cCeldaA, iReng01 + 1, "Valor 02");
							CelS = (char) (CelS + 1);
							if (CelS > 'Z') {
								if (cColumnaI.equalsIgnoreCase(" "))
									cColumnaI = "A";
								else
									cColumnaI = String
											.valueOf((char) (cColumnaI
													.charAt(0) + 1));
								CelS = 'A';
							}
							cCeldaA = cColumnaI + String.valueOf(CelS);
							Rep.comDespliega(cCeldaA, iReng01 + 1, "Valor 03");
							Rep.comAlineaRango(cCelda, iReng01, cCeldaA,
									iReng01, Rep.getAT_COMBINA_CENTRO());
						} else
							CelS = (Cel);
						cCeldaA = cColumnaI + String.valueOf(CelS);
						Cel = (char) (CelS + 1);
						if (Cel > 'Z') {
							if (cColumnaI.equalsIgnoreCase(" "))
								cColumnaI = "A";
							else
								cColumnaI = String.valueOf((char) (cColumnaI
										.charAt(0) + 1));
							Cel = 'A';
						}
					} // Barrer cada sustancia
					if (i == 1)
						Rep.comFillCells(cCeldaIC, iReng01 - 1, cCeldaA,
								iReng01 + 1, 15);
					else
						Rep.comFillCells(cCeldaIC, iReng01 - 1, cCeldaA,
								iReng01 + 1, 8);
					Rep.comAlineaRango(cCeldaIC, iReng01 - 1, cCelda,
							iReng01 - 1, Rep.getAT_COMBINA_CENTRO());
				} // Columnas de sustancias para dos tipos de an�lisis

				Rep.comFontBold(cCeldaI, iReng01 - 1, cCeldaA, iReng01 + 1);
				Rep.comBordeTotal(cCeldaI, iReng01 - 1, cCeldaA, iReng01 + 1, 1);
				iReng02 = iReng01 = 4;
				// Desplegar la informaci�n de las muestras
				for (int i = 0; i < ((ArrayList) vInfo.get(0)).size(); i++) {
					iReng02++;
					vResultado = (TVDinRep02) ((ArrayList) vInfo.get(0)).get(i);
					Rep.comDespliega("A", iReng02, String.valueOf(i + 1));
					Rep.comDespliega(
							"B",
							iReng02,
							Numero.getNumeroSinSeparador(
									new Integer(vResultado.getString("iAnio")),
									4)
									+ "/"
									+ Numero.getNumeroSinSeparador(
											new Integer(
													vResultado
															.getString("iCveLoteCualita")),
											4)
									+ "-"
									+ Numero.getNumeroSinSeparador(
											new Integer(
													vResultado
															.getString("iCveExamCualita")),
											2));
					Rep.comDespliega(
							"C",
							iReng02,
							Fecha.getFechaDDMMYYYY(
									vResultado.getDate("dtEntrega"), "/"));
					if (vResultado.getDate("dtProcesado") != null)
						Rep.comDespliega(
								"D",
								iReng02,
								Fecha.getFechaDDMMYYYY(
										vResultado.getDate("dtProcesado"), "/"));
					Rep.comDespliega(
							"E",
							iReng02,
							vResultado.get("cNomAnalisa") != null ? vResultado
									.getString("cNomAnalisa") : "");
					if (vResultado.getDate("dtAutorizado") != null)
						Rep.comDespliega("F", iReng02, Fecha.getFechaDDMMYYYY(
								vResultado.getDate("dtAutorizado"), "/"));
					Rep.comDespliega("G", iReng02,
							vResultado.getString("cCveEquipo")); // Identificador
																	// del
																	// Equipo
					Rep.comDespliega("H", iReng02,
							vResultado.getString("iMuestras"));
					Rep.comDespliega("I", iReng02,
							vResultado.getString("iMuestrasPost"));
					Rep.comDespliega("J", iReng02, "=H" + iReng02 + "-I"
							+ iReng02);
					Rep.comDespliega("K", iReng02,
							vResultado.getString("iMuestrasErroneas"));
					Rep.comDespliega("L", iReng02,
							vResultado.getString("iCtrolExt"));
					// Obtener informaci�n de la calibraci�n
					cLote = new StringBuffer();
					cLote.append("where CC.iAnio           = ")
							.append(vResultado.getString("iAnio"))
							.append("  and CC.iCveLaboratorio = ")
							.append(vResultado.getString("iCveLaboratorio"))
							.append("  and CC.iCveLoteCualita = ")
							.append(vResultado.getString("iCveLoteCualita"))
							.append("  and CC.iCveExamCualita = ")
							.append(vResultado.getString("iCveExamCualita"));

					aCalibracion = dExamen.findCalibracion(cLote.toString());
					// Desplegar la informaci�n de la calibraci�n.
					if (aCalibracion.size() > 0
							&& ((ArrayList) (aCalibracion.get(0))).size() > 0) {
						for (int iCalibra = 0; iCalibra < ((ArrayList) (aCalibracion
								.get(0))).size(); iCalibra++) {
							vCalibracion = (TVDinRep02) ((ArrayList) aCalibracion
									.get(0)).get(iCalibra);
							// La sustancia est� desplegada
							if (hCeldaSust.containsKey(vCalibracion
									.getString("iCveSustancia") + "_1")) {
								Cel = hCeldaSust
										.get(vCalibracion
												.getString("iCveSustancia")
												+ "_1").toString()
										.toCharArray()[1];
								cColumnaI = String.valueOf(hCeldaSust
										.get(vCalibracion
												.getString("iCveSustancia")
												+ "_1").toString()
										.toCharArray()[0]);
								cCeldaI = hCeldaSust.get(
										vCalibracion.getString("iCveSustancia")
												+ "_1").toString();
								if (vCalibracion.get("cCtrolCalibra") != null)
									Rep.comDespliega(cCeldaI, iReng02,
											vCalibracion
													.getString("cCtrolCalibra"));
								Cel = (char) ++Cel;
								if (Cel > 'Z') {
									if (cColumnaI.equalsIgnoreCase(" "))
										cColumnaI = "A";
									else
										cColumnaI = String
												.valueOf((char) (cColumnaI
														.charAt(0) + 1));
									Cel = 'A';
								}
								cCeldaI = cColumnaI + String.valueOf(Cel);
								if (vCalibracion.get("dCorteNeg") != null)
									Rep.comDespliega(cCeldaI, iReng02,
											vCalibracion.getString("dCorteNeg"));
								Cel = (char) ++Cel;
								if (Cel > 'Z') {
									if (cColumnaI.equalsIgnoreCase(" "))
										cColumnaI = "A";
									else
										cColumnaI = String
												.valueOf((char) (cColumnaI
														.charAt(0) + 1));
									Cel = 'A';
								}

								cCeldaI = cColumnaI + String.valueOf(Cel);
								if (vCalibracion.get("dCorte") != null)
									Rep.comDespliega(cCeldaI, iReng02,
											vCalibracion.getString("dCorte"));
								Cel = (char) ++Cel;
								if (Cel > 'Z') {
									if (cColumnaI.equalsIgnoreCase(" "))
										cColumnaI = "A";
									else
										cColumnaI = String
												.valueOf((char) (cColumnaI
														.charAt(0) + 1));
									Cel = 'A';
								}
								cCeldaI = cColumnaI + String.valueOf(Cel);
								if (vCalibracion.get("dCortePost") != null)
									Rep.comDespliega(cCeldaI, iReng02,
											vCalibracion
													.getString("dCortePost"));
							} // Informaci�n de la celda para controles
							if (hCeldaSust.containsKey(vCalibracion
									.getString("iCveSustancia") + "_2")) {
								cCeldaI = hCeldaSust.get(
										vCalibracion.getString("iCveSustancia")
												+ "_2").toString();
								Rep.comDespliega(cCeldaI, iReng02,
										vCalibracion.getString("iNumCtrol"));
							} // Celda para los controles
						} // Barrer los resultados
					}// Existe informaci�n de la calibraci�n
				}// Recorrer los resultados
				Rep.comBordeTotal("A", iReng01, cCeldaA, iReng02, 1);
				// Generar archivo
				cNomArchivo += Fecha.getFechaDDMMYYYY(Fecha.TodaySQL(), "_");
				buffer = Rep.creaActiveX("pg070305085", cNomArchivo, false,
						false, true);
			} // Existen registros
			else
				this.vErrores.acumulaError(
						"No existen registros para generar el Reporte.", 0, "");
		} // try
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return buffer;
	}

}
