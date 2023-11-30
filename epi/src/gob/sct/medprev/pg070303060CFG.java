package gob.sct.medprev;

import java.util.*;
import java.text.DecimalFormat;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Listado de TOXCtrolCalibra
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070303060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070303060CFG.png'>
 */
public class pg070303060CFG extends CFGListBase2 {
	public pg070303060CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	private StringBuffer activeX = new StringBuffer("");

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		Vector vcRegistros = new Vector();
		TDTOXExamResult dTDTOXExamResult = new TDTOXExamResult();
		try {
			for (Enumeration e = request.getParameterNames(); e
					.hasMoreElements();) {
				String cParam = (String) e.nextElement();
				String cValue = request.getParameter(cParam);
				if (cParam.startsWith("dResultado|") && cValue != null
						&& cValue.length() > 0) {
					TVTOXExamResult dTVTOXExamResult = new TVTOXExamResult();
					StringTokenizer st = new StringTokenizer(cParam, "|");
					st.nextToken(); // inicio del campo
					dTVTOXExamResult.setiCveAnalisis(Integer.valueOf(st
							.nextToken()));// iCveAnalisis
					dTVTOXExamResult.setiCveLaboratorio(Integer.valueOf(st
							.nextToken()));// iCveLaboratorio
					dTVTOXExamResult.setiAnio(Integer.valueOf(st.nextToken()));// iAnio
					dTVTOXExamResult.setiCveLoteCualita(Integer.valueOf(st
							.nextToken()));// iCveLoteCualita
					dTVTOXExamResult.setiCveExamCualita(Integer.valueOf(st
							.nextToken()));// iCveExamCualita
					dTVTOXExamResult.setiCveSustancia(Integer.valueOf(st
							.nextToken()));// iCveSustancia
					dTVTOXExamResult.setdResultado(Double.valueOf(cValue));// dResultado
					dTVTOXExamResult.setLPositivo(esPositivo(dTVTOXExamResult));// lPositivo
					vcRegistros.add(dTVTOXExamResult);
				}
			}
			dTDTOXExamResult.insert(null, vcRegistros);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar los registros", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		Vector vcRegistros = new Vector();
		TDTOXExamAnalisis dTDTOXExamAnalisis = new TDTOXExamAnalisis();
		TDTOXExamResult dTDTOXExamResult = new TDTOXExamResult();
		TDTOXCualtSust dTDTOXCualtSust = new TDTOXCualtSust();
		DecimalFormat dfNumber = new DecimalFormat("##,###,##0.000");
		try {
			HashMap hmFiltros = getParameters();
			Vector vcAnalisis = dTDTOXExamAnalisis.FindByAll(hmFiltros);
			Vector vcResultados = dTDTOXExamResult.FindByAll(hmFiltros);
			Vector vcSustancias = dTDTOXCualtSust.FindByAll(hmFiltros);
			for (Enumeration eAnalisis = vcAnalisis.elements(); eAnalisis
					.hasMoreElements();) {
				TVTOXExamAnalisis vAnalisis = (TVTOXExamAnalisis) eAnalisis
						.nextElement();
				TVDinamico vRegistro = new TVDinamico();
				vRegistro.put("iCveAnalisis", vAnalisis.getICveAnalisis());
				vRegistro
						.put("iCveLaboratorio", vAnalisis.getICveLaboratorio());
				vRegistro.put("iAnio", vAnalisis.getIAnio());
				vRegistro
						.put("iCveLoteCualita", vAnalisis.getICveLoteCualita());
				vRegistro
						.put("iCveExamCualita", vAnalisis.getICveExamCualita());
				for (Enumeration eSustancias = vcSustancias.elements(); eSustancias
						.hasMoreElements();) {
					TVTOXCualtSust vSustancia = (TVTOXCualtSust) eSustancias
							.nextElement();
					int iCveSustancia = vSustancia.getICveSustancia();
					vRegistro.put("dResultadoParaIdSustancia" + iCveSustancia,
							"");
				}
				vcRegistros.add(vRegistro);
			}
			HashMap hmRegistros = buildHashMap(vcRegistros);
			for (Enumeration eResultados = vcResultados.elements(); eResultados
					.hasMoreElements();) {
				TVTOXExamResult vResultados = (TVTOXExamResult) eResultados
						.nextElement();
				TVDinamico vRegistro = (TVDinamico) hmRegistros.get("K"
						+ (Integer) vResultados.getiCveAnalisis());
				if (vRegistro == null)
					continue;
				String cElem = "dResultadoParaIdSustancia"
						+ (Integer) vResultados.getiCveSustancia();
				if (vRegistro.containsKey(cElem))
					vRegistro.put(cElem, dfNumber.format(vResultados
							.getdResultado() != null ? vResultados
							.getdResultado() : Double.valueOf("0")));
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		vDespliega = vcRegistros;
		if (request.getParameter("hdBoton").compareToIgnoreCase(
				"Generar Reporte") == 0) {
			activeX.append(this.GenRep());
		}

	}

	/**
	 * Metodo getSubstancias
	 */
	public Vector getSubstancias() {
		try {
			return new TDTOXCualtSust().FindByAll(getParameters());
		} catch (Exception ex) {
			error("FillVector", ex);
			return new Vector();
		}
	}

	/**
	 * Metodo verificaSustancias
	 */
	public String verificaSustancias() {
		String cRet = "";
		try {
			Vector vcSustancias = new TDTOXCualtSust()
					.verificaSustancias(getParameters());
			for (Enumeration eSustancias = vcSustancias.elements(); eSustancias
					.hasMoreElements();) {
				TVDinamico vSustancia = (TVDinamico) eSustancias.nextElement();
				int iCveSust1 = ((Integer) vSustancia.get("iCveSust1"))
						.intValue();
				int iCveSust2 = ((Integer) vSustancia.get("iCveSust2"))
						.intValue();
				if (iCveSust1 != iCveSust2)
					cRet += " -" + vSustancia.get("cDscSustancia") + "\\n";
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		return cRet;
	}

	/**
	 * Metodo getParameters
	 */
	private HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		hmTmp.put("iAnio", request.getParameter("iAnio"));
		hmTmp.put("iCveLaboratorio", request.getParameter("iCveUniMed"));// ojo!!!
																			// iCveLaboratorio=iCveUniMed
		hmTmp.put("iCveLoteCualita", request.getParameter("iCveLoteCualita"));
		hmTmp.put("iCveExamCualita", request.getParameter("iCveExamCualita"));
		return hmTmp;
	}

	/**
	 * Metodo buildHashMap
	 */
	private HashMap buildHashMap(Vector vcRegistros) {
		HashMap hmTmp = new HashMap();
		for (Enumeration eRegistros = vcRegistros.elements(); eRegistros
				.hasMoreElements();) {
			TVDinamico vRegistro = (TVDinamico) eRegistros.nextElement();
			hmTmp.put("K" + (Integer) vRegistro.get("iCveAnalisis"), vRegistro);
		}
		return hmTmp;
	}

	/**
	 * Metodo esPositivo
	 */
	private Integer esPositivo(TVTOXExamResult vRegistro) {
		TDTOXCalibCualita dTDTOXCualtSust = new TDTOXCalibCualita();
		int iRes = 0;
		try {
			HashMap hmFiltro = new HashMap();
			hmFiltro.put("iAnio", vRegistro.getiAnio());
			hmFiltro.put("iCveLaboratorio", vRegistro.getiCveLaboratorio());
			hmFiltro.put("iCveLoteCualita", vRegistro.getiCveLoteCualita());
			hmFiltro.put("iCveExamCualita", vRegistro.getiCveExamCualita());
			hmFiltro.put("iCveSustancia", vRegistro.getiCveSustancia());

			TVTOXCalibCualita dTVTOXCalibCualita = new TDTOXCalibCualita()
					.find(hmFiltro);
			double dResultado = vRegistro.getdResultado().doubleValue();
			// System.out.println(dResultado);
			iRes = (dResultado > dTVTOXCalibCualita.getDCorte()) ? 1 : 0;
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		return new Integer(iRes);
	}

	/**
	 * Metodo getUpdStatus
	 */
	public String getUpdStatus(Vector vcSubstancias) {
		if (bs == null)
			return "Hidden";
		for (Enumeration e = bs.getPageVector().elements(); e.hasMoreElements();) {
			TVDinamico dDinamico = (TVDinamico) e.nextElement();
			for (Enumeration e2 = vcSubstancias.elements(); e2
					.hasMoreElements();) {
				TVTOXCualtSust dTOXCualtSust = (TVTOXCualtSust) e2
						.nextElement();
				Object oResultado = dDinamico.get("dResultadoParaIdSustancia"
						+ dTOXCualtSust.getICveSustancia());
				if (oResultado.toString().length() == 0)
					return "SaveCancelOnly";
			}
		}
		return "Hidden";
	}

	public StringBuffer GenRep() {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		// String cNomArchivo = new String("F-RMD-02");
		String cNomArchivo = "F-RMD-02E", cPlantilla = "F-RMD-02E";
		String cFiltroRep = "";
		int iRenglon = 0;
		int iFilasxPag = 65;
		int iPag = 0;
		int iPagAnt = 0;
		int iRengI = 22;
		int iRengF = 0;
		int iRenxPag = 4;
		int iNumDisco = 0;
		int iRengC = 0, iColumna, iRengA;
		StringBuffer buffer = new StringBuffer();
		Vector cCeldas = new Vector();
		try {
			// Informaci�n de las celdas a utilizar en el reporte
			cCeldas.add("C");
			cCeldas.add("D");
			cCeldas.add("E");
			cCeldas.add("F");
			cCeldas.add("G");
			cCeldas.add("H");
			cCeldas.add("I");
			cCeldas.add("J");
			cCeldas.add("K");

			TDTOXExamenCualita dTOXExamenCualita = new TDTOXExamenCualita();
			Vector vReporte = new Vector();
			cFiltroRep = " where toxexamencualita.ianio = "
					+ request.getParameter("iAnio")
					+ " and toxexamencualita.icvelaboratorio = "
					+ request.getParameter("iCveUniMed")
					+ " and toxexamencualita.iCveLoteCualita = "
					+ request.getParameter("iCveLoteCualita")
					+ " and toxexamencualita.iCveExamCualita = "
					+ request.getParameter("iCveExamCualita");
			vReporte = dTOXExamenCualita.FindByAllRep2(cFiltroRep);
			if (vReporte.size() == 0) {
				vErrores.acumulaError("", 15, "");
				return buffer;
			}

			iColumna = iRengA = 0;
			for (int i = 0; i < vReporte.size(); i++) {
				TVTOXExamenCualita vRenglon = (TVTOXExamenCualita) vReporte
						.get(i);
				// Imprimir la informaci�n general del lote.
				if (i == 0) {
					String cLote = vRenglon.getCLote();
					// escribe el Lote
					iRenglon = 10;
					xl.comDespliega("E", iRenglon,
							vRenglon.VEquipo.getCDscEquipo());
					xl.comDespliega("J", iRenglon, vRenglon.getCLote());
					cLote = cLote.replace('/', '-');
					cNomArchivo = cNomArchivo.concat("-").concat(cLote);
					// escribe la fecha
					iRenglon++;
					xl.comDespliega("E", iRenglon,
							vRenglon.VEquipo.getCModelo());
					xl.comDespliega(
							"J",
							iRenglon,
							"'"
									+ pFecha.getFechaDDMMYYYY(
											vRenglon.getDtEntrega(), "/"));
					iRenglon++;
					xl.comDespliega("E", iRenglon,
							"'" + vRenglon.VEquipo.getCNumSerie());
					if (vRenglon.VEquipo.getICveEquipo() == 92)
						cPlantilla = "F-RMD-02VT";
					cNomArchivo = cPlantilla;
					xl.comDespliega(
							"J",
							iRenglon,
							"'"
									+ pFecha.getFechaDDMMYYYY(
											vRenglon.getDtProcesado(), "/"));
					iRenglon += 4;
					iRengC = (int) (vRenglon.VEquipo.getILimiteUso() % 3) == 0 ? (int) (vRenglon.VEquipo
							.getILimiteUso() / 3) : (int) (vRenglon.VEquipo
							.getILimiteUso() / 3) + 1;
					// Obtener informaci�n de la calibraci�n y enviarla a
					// imprimir
					TVTOXCualtSust vTOXCualtSust;
					Vector VCalibracion = new TDTOXCualtSust()
							.FindByAllAnalisisPresuntivo(Integer.parseInt(
									request.getParameter("iAnio"), 10), Integer
									.parseInt(
											request.getParameter("iCveUniMed"),
											10),
									Integer.parseInt(request
											.getParameter("iCveLoteCualita"),
											10), Integer.parseInt(request
											.getParameter("iCveExamCualita"),
											10));
					if (VCalibracion.size() > 0) {
						for (int c = 0; c < VCalibracion.size(); c++) {
							vTOXCualtSust = new TVTOXCualtSust();
							vTOXCualtSust = (TVTOXCualtSust) VCalibracion
									.get(c);
							xl.comDespliega("A", iRenglon,
									vTOXCualtSust.getcDscSustancia());
							xl.comFontBold("A", iRenglon, "A", iRenglon);
							xl.comDespliega("E", iRenglon, String
									.valueOf((vTOXCualtSust.getDCorteNeg_ca())));
							xl.comDespliega("F", iRenglon, String
									.valueOf((vTOXCualtSust.getDCorte_ca())));
							xl.comDespliega("H", iRenglon, String
									.valueOf(vTOXCualtSust.getDCortePost_ca()));
							xl.comDespliega("J", iRenglon,
									vTOXCualtSust.getCDscReactivo());
							// xl.comCellFormat("D", iRenglon, "I", iRenglon,
							// "#0.000");
							xl.comAlineaRango("A", iRenglon, "C", iRenglon,
									xl.getAT_COMBINA_IZQUIERDA());
							xl.comAlineaRango("D", iRenglon, "E", iRenglon,
									xl.getAT_COMBINA_DERECHA());
							xl.comAlineaRango("F", iRenglon, "G", iRenglon,
									xl.getAT_COMBINA_DERECHA());
							xl.comAlineaRango("H", iRenglon, "I", iRenglon,
									xl.getAT_COMBINA_DERECHA());
							xl.comAlineaRango("J", iRenglon, "K", iRenglon,
									xl.getAT_COMBINA_IZQUIERDA());
							xl.comBordeTotal("A", iRenglon, "K", iRenglon, 1);
							iRenglon++;
						}
						iRenglon += 3;
					}

				}
				// Impresi�n de la informaci�n del carrusel
				if (vRenglon.getICarrusel() != iNumDisco) {
					// Poner la cuadr�cula
					if (iNumDisco > 0) {
						xl.comBordeTotal((String) cCeldas.get(1), iRengI,
								(String) cCeldas.get(2), iRengI + iRengC - 1, 1);
						xl.comBordeTotal((String) cCeldas.get(4), iRengI,
								(String) cCeldas.get(5), iRengI + iRengC - 1, 1);
						xl.comBordeTotal((String) cCeldas.get(7), iRengI,
								(String) cCeldas.get(8), iRengI + iRengC - 1, 1);
						iRenglon = iRengI + iRengC;
					}
					iRenglon += 3;
					iNumDisco = vRenglon.getICarrusel();
					xl.comDespliega("H", iRenglon, "DISCO:");
					xl.comFontBold("H", iRenglon, "H", iRenglon);
					xl.comDespliega("I", iRenglon, "" + vRenglon.getICarrusel());
					xl.comAlineaRango("I", iRenglon, "J", iRenglon,
							"COMBINA_CENTRO");
					xl.comBordeTotal("I", iRenglon, "J", iRenglon, 1);
					iRenglon += 2;
					xl.comDespliega("D", iRenglon, "An�lisis");
					xl.comDespliega("E", iRenglon, "Resultado");
					xl.comDespliega("G", iRenglon, "An�lisis");
					xl.comDespliega("H", iRenglon, "Resultado");
					xl.comDespliega("J", iRenglon, "An�lisis");
					xl.comDespliega("K", iRenglon, "Resultado");
					xl.comFontBold("D", iRenglon, "K", iRenglon);
					iRengI = iRenglon;
					iRengI++;

					iRengA = 0;
					iColumna = 0;

				}
				// Impresi�n de la informaci�n de las muestras
				// escribe el analisis y el resultado en la posicion
				if (iRengA >= iRengC) {
					iRengA = 0;
					iColumna += 3;
				}

				xl.comDespliega((String) cCeldas.get(iColumna),
						iRengI + iRengA,
						String.valueOf(vRenglon.getIPosicion()));
				xl.comDespliega((String) cCeldas.get(iColumna + 1), iRengI
						+ iRengA, vRenglon.getCAnalisis());
				xl.comDespliega((String) cCeldas.get(iColumna + 2), iRengI
						+ iRengA, "" + vRenglon.getcResultado());
				if (!vRenglon.getcResultado().equalsIgnoreCase("NEGATIVO"))
					xl.comFontBold((String) cCeldas.get(iColumna + 2), iRengI
							+ iRengA, (String) cCeldas.get(iColumna + 2),
							iRengI + iRengA);
				iRengA++;
			} // / for
				// Poner la cuadr�cula
			xl.comBordeTotal((String) cCeldas.get(1), iRengI,
					(String) cCeldas.get(2), iRengI + iRengC - 1, 1);
			xl.comBordeTotal((String) cCeldas.get(4), iRengI,
					(String) cCeldas.get(5), iRengI + iRengC - 1, 1);
			xl.comBordeTotal((String) cCeldas.get(7), iRengI,
					(String) cCeldas.get(8), iRengI + iRengC - 1, 1);

		} // / try
		catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Generar Reporte : " +
			// ex.getMessage());
		}

		buffer = xl.creaActiveX(cPlantilla, cNomArchivo, false, false, true);
		return buffer;
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

}
