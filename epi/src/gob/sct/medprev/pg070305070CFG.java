package gob.sct.medprev;

import java.util.*;
import java.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.util.TFechas;
import com.micper.util.TExcel;
import com.micper.util.TNumeros;
import gob.sct.medprev.vo.TVTOXLoteCuantita;
import gob.sct.medprev.vo.TVMuestra;
import gob.sct.medprev.vo.TVTOXSustancia;
import com.micper.seguridad.vo.TVDinRep02;

/**
 * * Clase de configuracion para CFG de la Consulta de Positivos
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305070.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305070.png'>
 */
public class pg070305070CFG extends CFGListBase2 {
	public Vector VRegistros = new Vector();
	private StringBuffer activeX = new StringBuffer("");
	private StringBuffer activeY = new StringBuffer("");

	public pg070305070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDExamenCualita dLote = new TDExamenCualita();
		cPaginas = "pg070305031.jsp|";
		boolean lWhere = false;
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").toString().compareTo("-1") != 0) {
				cCondicion = "  where M.iAnio = "
						+ request.getParameter("iAnio");
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
					Fecha = " M.dtRecoleccion";
					break;
				case 2:
					Fecha = " M.dtCaptura ";
					break;
				}
				TFechas TFecha = new TFechas();
				cCondicion += lWhere ? " and " : " where ";
				cCondicion += Fecha
						+ " between '"
						+ TFecha.getDateSQL(request.getParameter("dtFechaI")
								.toString())
						+ "'"
						+ " and '"
						+ TFecha.getDateSQL(request.getParameter("dtFechaF")
								.toString()) + "'";
				lWhere = true;
			}
			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("-1") != 0) {
				cCondicion += "   and M.iCveUniMed = "
						+ request.getParameter("iCveUniMed").toString();
				if (request.getParameter("iCveModulo") != null
						&& request.getParameter("iCveModulo").toString()
								.compareTo("-1") != 0) {
					cCondicion += "   and M.iCveModulo = "
							+ request.getParameter("iCveModulo").toString();
				}
			}
			// Generar el Reporte de las Positivas
			if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
				cCondicion += " order by M.iAnio, M.iCveMuestra";
				// Realizar la b�squeda
				vDespliega = new TDTOXMuestra().findInfoGeneral(cCondicion);
				VRegistros = vDespliega;
				this.iNumReg = VRegistros.size();
				this.activeX.append(this.GenReporte());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	} // fillVector()

	public String getOtrasSust(Vector vSustancias) {
		StringBuffer cTexto = new StringBuffer();
		TVTOXLoteCuantita VAnalisis;
		for (int i = 0; i < vSustancias.size(); i++) {
			VAnalisis = new TVTOXLoteCuantita();
			VAnalisis = (TVTOXLoteCuantita) vSustancias.get(i);
			if (i > 0)
				cTexto.append("<br>");
			cTexto.append(VAnalisis.VSustancia.getCDscSustancia())
					.append(" - ");
			cTexto.append(VAnalisis.getiAnio()).append("/")
					.append(VAnalisis.getiCveLoteCuantita());
		}
		return cTexto.toString();
	}

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getActiveY() {
		return this.activeY.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}

	public StringBuffer GenReporte() {
		TExcel Rep = new TExcel("07");
		TVMuestra VMuestra;
		TFechas Fecha = new TFechas();
		TNumeros Numero = new TNumeros();
		StringBuffer buffer = new StringBuffer();
		String cNomArchivo = "InfoGralMuestras_";
		int iReng = 2, iRengI;
		try {
			// Verificar que existan registros a Desplegar
			if (this.VRegistros.size() > 0) {
				// Buscar las sustancias a Presentar
				Vector vSustancias = new TDTOXSustancia()
						.FindByAll(
								" where lActivo = 1 and lAnalizada = 1 and lPositiva = 1 ",
								" order by iCveSustancia ");
				char Cel = 'A', CelS;
				String cColumnaI = "A";
				String cCelda, cCeldaA, cCeldaI, cCeldaIC;
				cCeldaA = cCeldaIC = cCeldaI = cCelda = cColumnaI + Cel;
				HashMap hCeldaSust = new HashMap();
				StringBuffer cLote;
				for (int i = 1; i <= 2; i++) {
					// Generar columnas para las sustancias An�lisis
					// confirmatorio a Desplegar
					for (int s = 0; s < vSustancias.size(); s++) {
						cCelda = cColumnaI + String.valueOf(Cel);
						if (s == 0)
							cCeldaIC = cCelda;
						if (i == 1 && s == 0)
							Rep.comDespliega(cCeldaIC, iReng - 1,
									"Informaci�n del An�lisis Presuntivo ");
						if (i == 2 && s == 0)
							Rep.comDespliega(cCeldaIC, iReng - 1,
									"Informaci�n del An�lisis Confirmatorio ");
						hCeldaSust.put(
								String.valueOf(((TVTOXSustancia) vSustancias
										.get(s)).getiCveSustancia()) + "_" + i,
								cCelda);
						Rep.comDespliega(cCelda, iReng,
								((TVTOXSustancia) vSustancias.get(s))
										.getcDscSustancia());
						Rep.comDespliega(cCelda, iReng + 1, "An�lisis");
						CelS = (char) (Cel + 1);
						if (CelS > 'Z') {
							cColumnaI = String.valueOf((char) (cColumnaI
									.charAt(0) + 1));
							CelS = 'A';
						}
						cCeldaA = cColumnaI + String.valueOf(CelS);
						Rep.comDespliega(cCeldaA, iReng + 1, "Resultado");
						CelS = (char) (Cel + 2);
						if (CelS > 'Z') {
							cColumnaI = String.valueOf((char) (cColumnaI
									.charAt(0) + 1));
							CelS = 'A';
						}
						cCeldaA = cColumnaI + String.valueOf(CelS);
						Rep.comDespliega(cCeldaA, iReng + 1, "Concentraci�n");
						Rep.comAlineaRango(cCelda, iReng, cCeldaA, iReng,
								Rep.getAT_COMBINA_CENTRO());
						Cel = (char) (CelS + 1);
						if (Cel > 'Z') {
							cColumnaI = String.valueOf((char) (cColumnaI
									.charAt(0) + 1));
							Cel = 'A';
						}
					}
					Rep.comAlineaRango(cCeldaIC, iReng - 1, cCeldaA, iReng - 1,
							Rep.getAT_COMBINA_CENTRO());
				} // Columnas de sustancias para dos tipos de an�lisis
				Rep.comFillCells(cCeldaI, iReng - 1, cCeldaA, iReng + 1, 15);
				Rep.comFontBold(cCeldaI, iReng - 1, cCeldaA, iReng + 1);
				Rep.comBordeTotal(cCeldaI, iReng - 1, cCeldaA, iReng + 1, 1);

				iRengI = iReng = iReng += 2;
				// Desplegar la informaci�n de las muestras
				for (int i = 0; i < this.VRegistros.size(); i++) {
					VMuestra = new TVMuestra();
					VMuestra = (TVMuestra) this.VRegistros.get(i);
					// Presentar la informaci�n de la Muestra
					Rep.comDespliega("A", iReng, String.valueOf(i + 1));
					Rep.comDespliega("B", iReng,
							String.valueOf(VMuestra.getIAnio()));
					Rep.comDespliega("C", iReng,
							String.valueOf(VMuestra.getICveMuestra()));

					Rep.comDespliega("D", iReng, VMuestra.getCDscUM());
					Rep.comDespliega("E", iReng, VMuestra.getCDscModulo());
					Rep.comDespliega("F", iReng, VMuestra.getCDscProceso());
					Rep.comDespliega("G", iReng, VMuestra.getCDscMotivo());
					Rep.comDespliega(
							"H",
							iReng,
							"'"
									+ Fecha.getFechaDDMMYYYY(
											VMuestra.getDtRecoleccion(), "/"));
					Rep.comDespliega("I", iReng, VMuestra.getCDscUsuRecolec());
					Rep.comDespliega("J", iReng,
							VMuestra.getLTemperaC() == 1 ? "S�" : "No");
					Rep.comDespliega("K", iReng,
							VMuestra.getLAlteracion() == 1 ? "S�" : "No");
					Rep.comDespliega("L", iReng,
							VMuestra.getLBajoObserva() == 1 ? "S�" : "No");
					Rep.comDespliega("M", iReng, VMuestra.getCDscMdoTrans());
					Rep.comDespliega("N", iReng, VMuestra.getCDscEmpresa());
					Rep.comDespliega("O", iReng,
							String.valueOf(VMuestra.getICvePersonal()));
					Rep.comDespliega("P", iReng,
							String.valueOf(VMuestra.getIEdad()));
					Rep.comDespliega(
							"Q",
							iReng,
							"'"
									+ Fecha.getFechaDDMMYYYY(
											VMuestra.getDtCaptura(), "/"));
					Rep.comDespliega("R", iReng, VMuestra.getCDscUsuCaptura());
					Rep.comDespliega("S", iReng, VMuestra.getCDscSituacion());
					Rep.comDespliega("T", iReng, VMuestra.getCDscTipoRecep());
					Rep.comDespliega("U", iReng, VMuestra.getCDscMotRecep());
					// Muestra informaci�n de las muestras que fueron
					// analizadas
					if (VMuestra.getICveAnalisis() > 0) {
						Rep.comDespliega("V", iReng, VMuestra.getCAnalisis());
						Rep.comDespliega("W", iReng, VMuestra.getCResultado());
						Rep.comDespliega("X", iReng, VMuestra
								.getLPresuntoPost() == 1 ? "S�" : "No");

						// Presentar informaci�n del an�lisis presuntivo
						if (VMuestra.vResultPresuntivo.size() > 0) {
							for (int iAnPres = 0; iAnPres < VMuestra.vResultPresuntivo
									.size(); iAnPres++) {
								TVDinRep02 vDatos = (TVDinRep02) VMuestra.vResultPresuntivo
										.get(iAnPres);
								if (hCeldaSust.containsKey(vDatos
										.getString("iCveSustancia") + "_1")) {
									Cel = hCeldaSust
											.get(vDatos
													.getString("iCveSustancia")
													+ "_1").toString()
											.toCharArray()[1];
									cColumnaI = String.valueOf(hCeldaSust
											.get(vDatos
													.getString("iCveSustancia")
													+ "_1").toString()
											.toCharArray()[0]);
									cCeldaI = hCeldaSust.get(
											vDatos.getString("iCveSustancia")
													+ "_1").toString();
									if (vDatos.get("dtProcesado") != null)
										Rep.comDespliega(
												cCeldaI,
												iReng,
												Fecha.getFechaDDMMYYYY(
														vDatos.getDate("dtProcesado"),
														"/"));
									Cel = (char) ++Cel;
									if (Cel > 'Z') {
										cColumnaI = String
												.valueOf((char) (cColumnaI
														.charAt(0) + 1));
										Cel = 'A';
									}
									cCeldaI = cColumnaI + String.valueOf(Cel);
									Rep.comDespliega(
											cCeldaI,
											iReng,
											vDatos.getInt("lPositivo") == 1 ? "Positivo"
													: "Negativo");
									if (vDatos.getInt("lPositivo") == 1)
										Rep.comFontBold(cCeldaI, iReng,
												cCeldaI, iReng);
									Cel = (char) ++Cel;
									if (Cel > 'Z') {
										cColumnaI = String
												.valueOf((char) (cColumnaI
														.charAt(0) + 1));
										Cel = 'A';
									}
									cCeldaI = cColumnaI + String.valueOf(Cel);
									Rep.comDespliega(cCeldaI, iReng, vDatos
											.get("dResultado").toString());
								} // Presentar sustancia

							} // Para cada an�lisis
						} // Hay informaci�n del an�lisis

						// Verificar si fue presunto positivo
						if (VMuestra.getLPresuntoPost() == 1) {
							Rep.comDespliega("Y", iReng,
									String.valueOf(VMuestra.getISustPost()));
							Rep.comDespliega("Z", iReng,
									String.valueOf(VMuestra.getISustConf()));

							// Presentar las drogas por positivo
							TVTOXLoteCuantita VAnalisis;
							for (int iRes = 0; iRes < VMuestra.vResultado
									.size(); iRes++) {
								VAnalisis = (TVTOXLoteCuantita) VMuestra.vResultado
										.get(iRes);
								cLote = new StringBuffer();
								// Verificar que la sustancia se vaya a
								// presentar en el reporte
								if (hCeldaSust.containsKey(String
										.valueOf(VAnalisis.getiCveSustancia()
												+ "_2"))) {
									Cel = hCeldaSust
											.get(String.valueOf(VAnalisis
													.getiCveSustancia()) + "_2")
											.toString().toCharArray()[1];
									cColumnaI = String
											.valueOf(hCeldaSust
													.get(String.valueOf(VAnalisis
															.getiCveSustancia())
															+ "_2").toString()
													.toCharArray()[0]);
									cCeldaI = hCeldaSust
											.get(String.valueOf(VAnalisis
													.getiCveSustancia()) + "_2")
											.toString();
									Rep.comDespliega(cCeldaI, iReng, Fecha
											.getFechaDDMMYYYY(
													VAnalisis.getdtAnalisis(),
													"/"));
									Cel = (char) ++Cel;
									if (Cel > 'Z') {
										cColumnaI = String
												.valueOf((char) (cColumnaI
														.charAt(0) + 1));
										Cel = 'A';
									}
									cCeldaI = cColumnaI + String.valueOf(Cel);
									Rep.comDespliega(
											cCeldaI,
											iReng,
											VAnalisis.VCuantAn.getlResultado()
													.intValue() == 1 ? "Positivo"
													: "Negativo");
									Cel = (char) ++Cel;
									if (Cel > 'Z') {
										cColumnaI = String
												.valueOf((char) (cColumnaI
														.charAt(0) + 1));
										Cel = 'A';
									}
									cCeldaI = cColumnaI + String.valueOf(Cel);
									Rep.comDespliega(cCeldaI, iReng,
											VAnalisis.VCuantAn.getdResultado()
													.toString());
								}
							} // Para cada sustancia positiva
						} // La muestra fue presunta positiva
					} // La muestra fue analizada
					iReng++;
				} // Recorrer muestras
					// Generar archivo
				Rep.comBordeTotal("A", iRengI, cCeldaA, iReng - 1, 1);
				cNomArchivo += Fecha.getFechaMMDDYYYYSinSep(Fecha.TodaySQL());
				buffer = Rep.creaActiveX("pg070305070", cNomArchivo, false,
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
