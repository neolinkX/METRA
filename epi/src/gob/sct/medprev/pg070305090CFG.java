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
 * * Clase de configuración para CFG de la Consulta de Positivos
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Itzia María del Carmen Sánchez Méndez.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305090.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305090.png'>
 */
public class pg070305090CFG extends CFGListBase2 {
	public Vector VRegistros = new Vector();
	private StringBuffer activeX = new StringBuffer("");
	String cTitulo = new String();

	public pg070305090CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDExamenCualita dLote = new TDExamenCualita();
		cPaginas = "";
		boolean lWhere = false;
		try {

			if (request.getParameter("dtFechaI") != null
					&& request.getParameter("dtFechaF") != null
					&& request.getParameter("dtFechaI").toString().length() > 0
					&& request.getParameter("dtFechaF").toString().length() > 0) {
				String Fecha = "";
				TFechas TFecha = new TFechas("07");
				cCondicion = "'"
						+ TFecha.getDateSQL(request.getParameter("dtFechaI")
								.toString())
						+ "'"
						+ " and '"
						+ TFecha.getDateSQL(request.getParameter("dtFechaF")
								.toString()) + "'";
				cTitulo = " DEL "
						+ TFecha.getFechaDDMMMYYYY(
								TFecha.getDateSQL(request.getParameter(
										"dtFechaI").toString()), " DE ")
								.toUpperCase()
						+ " AL  "
						+ TFecha.getFechaDDMMMYYYY(
								TFecha.getDateSQL(request.getParameter(
										"dtFechaF").toString()), " DE ")
								.toUpperCase();
			}
			// Generar el Reporte de las Positivas
			if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
				// Realizar la búsqueda
				ArrayList vConsultas = new TDTOXMuestra()
						.findInformeAnalisis(cCondicion);
				this.activeX.append(this.GenReporte(vConsultas));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	} // fillVector()

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}

	public StringBuffer GenReporte(ArrayList vInfo) {
		TExcel Rep = new TExcel("07");
		TVDinRep02 vResultado;
		TFechas Fecha = new TFechas();
		TNumeros Numero = new TNumeros();
		StringBuffer buffer = new StringBuffer();
		String cNomArchivo = "InfoAnalisis_";
		int iReng01 = 7, iReng02;
		try {
			// Verificar que existan registros a Desplegar
			if (vInfo.size() > 0) {
				Rep.comDespliega("A", iReng01, cTitulo);
				// Desplegar la información de las muestras
				for (int iInformacion = 0; iInformacion < 8; iInformacion++)
					if (vInfo.get(iInformacion) != null && vInfo.size() > 0) {
						vResultado = (TVDinRep02) ((ArrayList) (vInfo
								.get(iInformacion))).get(0);
						switch (iInformacion) {
						case 0: // EPI - Examen
							iReng01 = 13;
							break;
						case 1: // EPI - Revaloracion
							iReng01 = 14;
							break;
						case 2: // EMO - En Módulo
							iReng01 = 16;
							break;
						case 3: // EMO - Operativo
							iReng01 = 17;
							break;
						case 4: // TOX - Seguimiento
							iReng01 = 19;
							break;
						case 5: // TOX - Otros
							iReng01 = 21;
							break;
						case 6: // INV - Accidente
							iReng01 = 22;
							break;
						case 7: // INV - Incidente
							iReng01 = 23;
							break;
						}
						Rep.comDespliega("C", iReng01,
								String.valueOf(vResultado.getString("CDI")));
						Rep.comDespliega("D", iReng01,
								String.valueOf(vResultado.getString("CENMA")));
						Rep.comDespliega("E", iReng01,
								String.valueOf(vResultado.getString("UMF")));
						Rep.comDespliega("G", iReng01,
								String.valueOf(vResultado.getString("AEREO")));
						Rep.comDespliega("H", iReng01,
								String.valueOf(vResultado
										.getString("AUTOTRANSPORTE")));
						Rep.comDespliega("I", iReng01, String
								.valueOf(vResultado.getString("FERROVIARIO")));
						Rep.comDespliega("J", iReng01, String
								.valueOf(vResultado.getString("MARITIMO")));
						Rep.comDespliega("K", iReng01,
								String.valueOf(vResultado.getString("OTROS")));
						Rep.comDespliega("C", iReng01 + 18,
								String.valueOf(vResultado.getString("MUESTRA")));
						/*
						 * Rep.comDespliega("D", iReng01 + 18,
						 * String.valueOf(vResultado
						 * .getString("MUESTRA_CANN"))); Rep.comDespliega("E",
						 * iReng01 + 18,
						 * String.valueOf(vResultado.getString("MUESTRA_COC")));
						 * Rep.comDespliega("F", iReng01 + 18,
						 * String.valueOf(vResultado
						 * .getString("MUESTRA_OPIO"))); Rep.comDespliega("G",
						 * iReng01 + 18,
						 * String.valueOf(vResultado.getString("MUESTRA_PCP")));
						 * Rep.comDespliega("H", iReng01 + 18,
						 * String.valueOf(vResultado
						 * .getString("MUESTRA_META")));
						 */

						Rep.comDespliega(
								"C",
								iReng01 + 36,
								"="
										+ String.valueOf(vResultado
												.getString("ANALISIS_ANF"))
										+ "+"
										+ String.valueOf(vResultado
												.getString("ANALISIS_CONF_ANF")));
						Rep.comDespliega(
								"D",
								iReng01 + 36,
								"="
										+ String.valueOf(vResultado
												.getString("ANALISIS_CANN"))
										+ "+"
										+ String.valueOf(vResultado
												.getString("ANALISIS_CONF_CANN")));
						Rep.comDespliega(
								"E",
								iReng01 + 36,
								"="
										+ String.valueOf(vResultado
												.getString("ANALISIS_COC"))
										+ "+"
										+ String.valueOf(vResultado
												.getString("ANALISIS_CONF_COC")));
						Rep.comDespliega(
								"F",
								iReng01 + 36,
								"="
										+ String.valueOf(vResultado
												.getString("ANALISIS_OPIO"))
										+ "+"
										+ String.valueOf(vResultado
												.getString("ANALISIS_CONF_OPIO")));
						Rep.comDespliega(
								"G",
								iReng01 + 36,
								"="
										+ String.valueOf(vResultado
												.getString("ANALISIS_PCP"))
										+ "+"
										+ String.valueOf(vResultado
												.getString("ANALISIS_CONF_PCP")));
						Rep.comDespliega(
								"H",
								iReng01 + 36,
								"="
										+ String.valueOf(vResultado
												.getString("ANALISIS_META"))
										+ "+"
										+ String.valueOf(vResultado
												.getString("ANALISIS_CONF_META")));

					} // Tiene información la búsqueda
				// Firmas
				iReng01 = 75;
				Rep.comDespliega("H", iReng01++,
						this.vParametros.getPropEspecifica("TOXPuestoFirma"));
				Rep.comDespliega("H", iReng01,
						this.vParametros.getPropEspecifica("TOXNombreFirma"));
				// Generar archivo
				cNomArchivo += Fecha.getFechaDDMMYYYY(Fecha.TodaySQL(), "_");
				buffer = Rep.creaActiveX("pg070305090", cNomArchivo, false,
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
