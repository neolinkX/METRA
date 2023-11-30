package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import java.util.Vector;

/**
 * * Clase de configuración para CFG del listado de la tabla TOXMuestra
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305021CFG.png'>
 */
public class pg070305021CFG extends CFGListBase2 {
	public pg070305021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070305020.jsp|";
	}

	private StringBuffer activeX = new StringBuffer("");
	private StringBuffer activeY = new StringBuffer("");

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDTOXAnalisis dTOXAnalsis = new TDTOXAnalisis();
		TVTOXAnalisis vTOXAnalsis = new TVTOXAnalisis();
		try {
			vTOXAnalsis.setiAnio(new Integer(request
					.getParameter("hdCampoClave")));
			vTOXAnalsis.setiCveLaboratorio(new Integer(request
					.getParameter("hdCampoClave2")));
			vTOXAnalsis.setICveEnvio(new Integer(request
					.getParameter("hdCampoClave3")));
			vDespliega = dTOXAnalsis.FindByAll5(vTOXAnalsis);

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
		TVTOXEnvio vTOXEnvio = new TVTOXEnvio();
		TVTOXAnalisis vAnalisis = new TVTOXAnalisis();
		Vector cCeldas = new Vector();
		String cNomArchivo = "Resultado_";
		TFechas tFecha = new TFechas("07");
		int iReng = 8;
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
			TVTOXAnalisis VFiltro = new TVTOXAnalisis();
			VFiltro.setiAnio(new Integer(request.getParameter("hdCampoClave")
					.toString()));
			VFiltro.setiCveUniMed(new Integer(request.getParameter(
					"hdCampoClave2").toString()));
			VFiltro.setICveEnvio(new Integer(request.getParameter(
					"hdCampoClave3").toString()));
			StringBuffer cFiltro = new StringBuffer();
			cFiltro.append(" where TOXEnvio.iAnio      = ")
					.append(VFiltro.getiAnio())
					.append("   and TOXEnvio.iCveUniMed = ")
					.append(VFiltro.getiCveUniMed())
					.append("   and TOXEnvio.iCveEnvio  = ")
					.append(VFiltro.getICveEnvio());
			// Información del Envío
			vTOXEnvio = (TVTOXEnvio) (dTOXEnvio.FindByAll3(cFiltro.toString(),
					"")).get(0);
			String cFecha = "México D.F., a "
					+ tFecha.getIntDay(tFecha.TodaySQL()) + " de "
					+ tFecha.getMonthName(tFecha.TodaySQL()) + " de "
					+ tFecha.getIntYear(tFecha.TodaySQL());
			// Presentar la información del Envío
			Rep.comDespliega("G", iReng, cFecha);
			iReng += 4;
			Rep.comDespliega("A", iReng, "DR(A). " + vTOXEnvio.getCNombreRec());
			iReng += 2;
			Rep.comDespliega("A", iReng,
					"JEFE DE LA U. M. " + vTOXEnvio.getCDscUniMed());
			iReng += 8;
			Rep.comDespliega("C", iReng, vTOXEnvio.getCDscUniMed());
			Rep.comDespliega("C", ++iReng, String.valueOf(vTOXEnvio.getIAnio()));
			Rep.comDespliega("H", iReng,
					tFecha.getFechaDDMMMMMYYYY(vTOXEnvio.getdtEnvio(), " de "));
			Rep.comDespliega("C", ++iReng,
					String.valueOf(vTOXEnvio.getICveEnvio()));
			Rep.comDespliega("H", iReng, tFecha.getFechaDDMMMMMYYYY(
					vTOXEnvio.getdtRecepcion(), " de "));
			// Presentar resumen de la recepción del envío
			iReng += 6;
			Rep.comDespliega("C", iReng,
					String.valueOf(vTOXEnvio.getITotalEnviadas()));
			Rep.comDespliega("C", iReng + 1,
					String.valueOf(vTOXEnvio.getITotalRechazadas()));
			Rep.comDespliega("C", iReng + 2,
					String.valueOf(vTOXEnvio.getITotalRecibidas()));

			Rep.comDespliega("H", iReng + 1,
					String.valueOf(vTOXEnvio.getITotalPendientes()));
			// Rep.comDespliega("H", iReng + 2,
			// String.valueOf(vTOXEnvio.getITotalPositivos()));

			VReporte = DAnalisis.RepTarjeta(VFiltro);
			int iRengI = 35;
			int iRengC = 0;
			if (((Vector) (VReporte.get(1))).size() > 0) {
				// Título de Negativas
				Rep.comFontBold("A", iRengI, "A", iRengI);
				Rep.comDespliega("A", iRengI++,
						"DETALLE DE LAS MUESTRAS NEGATIVAS");
				Rep.comDespliega("B", iRengI, "NIM");
				Rep.comDespliega("C", iRengI, "PROCESO");
				Rep.comAlineaRango("C", iRengI, "E", iRengI,
						Rep.getAT_COMBINA_CENTRO());
				Rep.comFillCells("B", iRengI, "E", iRengI, 15);
				Rep.comDespliega("G", iRengI, "NIM");
				Rep.comDespliega("H", iRengI, "PROCESO");
				Rep.comAlineaRango("H", iRengI, "J", iRengI,
						Rep.getAT_COMBINA_CENTRO());
				Rep.comFillCells("G", iRengI, "J", iRengI, 15);
				Rep.comFontBold("B", iRengI, "J", iRengI);
				Rep.comAlineaRango("B", iRengI, "J", iRengI,
						Rep.getAT_HCENTRO());
				Rep.comBordeTotal("B", iRengI, "E", iRengI, 1);
				Rep.comBordeTotal("G", iRengI, "J", iRengI, 1);
				iRengI++;
				// Información de las Negativas
				Rep.comDespliega("H", iReng,
						String.valueOf(((Vector) (VReporte.get(1))).size()));
				Vector VNegativo = new Vector();
				iRengC = (int) (((Vector) (VReporte.get(1))).size() % 2) == 0 ? (int) (((Vector) (VReporte
						.get(1))).size() / 2) : (int) (((Vector) (VReporte
						.get(1))).size() / 2) + 1;
				int iColumna = 0;
				int iRengA = 0;
				for (int i = 0; i < ((Vector) VReporte.get(1)).size(); i++) {
					// Desplegar negativos
					vAnalisis = new TVTOXAnalisis();
					vAnalisis = (TVTOXAnalisis) ((Vector) VReporte.get(1))
							.get(i);
					if (iRengA >= iRengC) {
						iRengA = 0;
						iColumna += 5;
					}
					Rep.comDespliega(cCeldas.get(iColumna).toString(), iRengI
							+ iRengA, vAnalisis.getCMuestra());
					Rep.comDespliega(cCeldas.get(iColumna + 1).toString(),
							iRengI + iRengA, vAnalisis.getCDscMotivo());
					Rep.comAlineaRango(cCeldas.get(iColumna + 1).toString(),
							iRengI + iRengA, cCeldas.get(iColumna + 3)
									.toString(), iRengI + iRengA, Rep
									.getAT_COMBINA_IZQUIERDA());
					Rep.comAlineaRangoVer(cCeldas.get(iColumna + 1).toString(),
							iRengI + iRengA, cCeldas.get(iColumna + 3)
									.toString(), iRengI + iRengA, Rep
									.getAT_VJUSTIFICAR());
					Rep.comAlineaRangoVer(cCeldas.get(iColumna + 1).toString(),
							iRengI + iRengA, cCeldas.get(iColumna + 3)
									.toString(), iRengI + iRengA, Rep
									.getAT_VAJUSTAR());
					Rep.comBordeRededor(cCeldas.get(iColumna).toString(),
							iRengI + iRengA, cCeldas.get(iColumna).toString(),
							iRengI + iRengA, 1, 0);
					Rep.comBordeRededor(cCeldas.get(iColumna + 1).toString(),
							iRengI + iRengA, cCeldas.get(iColumna + 3)
									.toString(), iRengI + iRengA, 1, 0);
					iRengA++;
				}
			}
			// Presentar la información de las rechazadas
			// Título de Rechazadas
			if (((Vector) VReporte.get(0)).size() > 0) {
				iRengI += iRengC + 2;
				Rep.comFontBold("A", iRengI, "A", iRengI);
				Rep.comDespliega("A", iRengI++,
						"DETALLE DE LAS MUESTRAS RECHAZADAS");
				Rep.comDespliega("B", iRengI, "MOTIVO");
				Rep.comAlineaRango("B", iRengI, "E", iRengI,
						Rep.getAT_COMBINA_CENTRO());
				Rep.comDespliega("F", iRengI, "NIM");
				Rep.comDespliega("G", iRengI, "NIM");
				Rep.comDespliega("H", iRengI, "NIM");
				Rep.comDespliega("I", iRengI, "NÚMERO");
				Rep.comDespliega("J", iRengI, "%");
				Rep.comFillCells("B", iRengI, "J", iRengI, 15);
				Rep.comBordeTotal("B", iRengI, "J", iRengI, 1);
				Rep.comFontBold("B", iRengI, "J", iRengI);
				Rep.comAlineaRango("B", iRengI, "J", iRengI,
						Rep.getAT_HCENTRO());
				iRengC = 1;
				for (int i = 0; i < ((Vector) VReporte.get(0)).size(); i++) {
					iRengI += iRengC;
					Vector VNegativo = new Vector();
					VNegativo = ((Vector) ((Vector) (VReporte.get(0))).get(i));
					iRengC = VNegativo.size() % 3 == 0 ? (int) (VNegativo
							.size() / 3) : ((int) (VNegativo.size() / 3) + 1);
					int iColumna = 4;
					int iRengA = 0;
					for (int j = 0; j < VNegativo.size(); j++) {
						vAnalisis = new TVTOXAnalisis();
						vAnalisis = (TVTOXAnalisis) VNegativo.get(j);
						if (j == 0) {
							Rep.comDespliega("B", iRengI, vAnalisis
									.getCDscTipoRecep() == null ? ""
									: vAnalisis.getCDscTipoRecep());
							Rep.comAlineaRango("B", iRengI, "E", iRengI,
									Rep.getAT_COMBINA_IZQUIERDA());
							Rep.comAlineaRangoVer("B", iRengI, "E", iRengI,
									Rep.getAT_VJUSTIFICAR());
							Rep.comAlineaRangoVer("B", iRengI, "E", iRengI,
									Rep.getAT_VAJUSTAR());
							Rep.comDespliega("B", iRengI + 1,
									vAnalisis.getCDscMotivo() == null ? ""
											: vAnalisis.getCDscMotivo());
							Rep.comAlineaRango("B", iRengI + 1, "E", iRengI
									+ iRengC, Rep.getAT_COMBINA_IZQUIERDA());
							Rep.comAlineaRangoVer("B", iRengI + 1, "E", iRengI
									+ iRengC, Rep.getAT_VJUSTIFICAR());
							Rep.comAlineaRangoVer("B", iRengI + 1, "E", iRengI
									+ iRengC, Rep.getAT_VAJUSTAR());
							Rep.comBordeRededor("B", iRengI, "E", iRengI
									+ iRengC, 1, 0);
							Rep.comBordeRededor("F", iRengI, "F", iRengI
									+ iRengC, 1, 0);
							Rep.comBordeRededor("G", iRengI, "G", iRengI
									+ iRengC, 1, 0);
							Rep.comBordeRededor("H", iRengI, "H", iRengI
									+ iRengC, 1, 0);
							Rep.comBordeRededor("I", iRengI, "I", iRengI
									+ iRengC, 1, 0);
							Rep.comBordeRededor("J", iRengI, "J", iRengI
									+ iRengC, 1, 0);
							Rep.comDespliega("I", iRengI,
									String.valueOf(VNegativo.size()));
							Rep.comDespliega("J", iRengI, "=(I" + iRengI + "/C"
									+ (iReng + 1) + ")*100");
							iRengI++;
						}
						if (iRengA >= iRengC) {
							iRengA = 0;
							iColumna++;
						}
						Rep.comDespliega((String) cCeldas.get(iColumna), iRengI
								+ iRengA, vAnalisis.getCMuestra());
						iRengA++;
					}
				} // Desplegar información de las Rechazadas
			}
			// Datos de la firma
			iRengI += iRengC + 3;
			Rep.comFontBold("A", iRengI, "F", iRengI + 8);
			Rep.comDespliega("A", iRengI, "ATENTAMENTE");
			iRengI += 4;
			Rep.comDespliega("A", iRengI,
					this.vParametros.getPropEspecifica("TOXPuestoFirma"));
			Rep.comDespliega("F", iRengI,
					this.vParametros.getPropEspecifica("TOXPuestoFirmaD"));
			Rep.comDespliega("A", ++iRengI,
					this.vParametros.getPropEspecifica("TOXNombreFirma"));
			Rep.comDespliega("F", iRengI,
					this.vParametros.getPropEspecifica("TOXNombreFirmaD"));

			cNomArchivo += vTOXEnvio.getCDscUniMed() + "_"
					+ vTOXEnvio.getICveEnvio() + "_" + vTOXEnvio.getIAnio();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		StringBuffer buffer = Rep.creaActiveX("pg070305021", cNomArchivo,
				false, false, true);
		return buffer;
	}

	public String getOtrasSust(TVTOXCuantAnalisis VCAnal) {
		String cTexto = new String();
		try {
			cTexto = new TDTOXCuantAnalisis().getOtrasSust(VCAnal).toString();
			cTexto += "&nbsp;";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cTexto;
	}

}
