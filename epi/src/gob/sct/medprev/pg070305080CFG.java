package gob.sct.medprev;

import java.util.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.util.TFechas;
import com.micper.util.TExcel;
import com.micper.util.TNumeros;
import com.micper.seguridad.vo.TVDinRep02;

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
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305020.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305080.png'>
 */
public class pg070305080CFG extends CFGListBase2 {
	public Vector VRegistros = new Vector();
	private StringBuffer activeX = new StringBuffer("");

	public pg070305080CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXLoteCuantita dLote = new TDTOXLoteCuantita();
		cPaginas = "";
		boolean lWhere = false;
		try {

			if (cCondicion.compareToIgnoreCase("") != 0) {
				lWhere = true;
				cCondicion = " Where " + cCondicion;
			}

			if (request.getParameter("iAnio") != null) {
				if (lWhere)
					cCondicion += " And LC.iAnio = "
							+ request.getParameter("iAnio");
				else {
					cCondicion = "  WHERE LC.iAnio = "
							+ request.getParameter("iAnio");
					lWhere = true;
				}
			}

			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.compareTo("0") != 0) {
				if (lWhere)
					cCondicion += " And LC.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
				else {
					cCondicion = " Where LC.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
					lWhere = true;
				}
				if (request.getParameter("SLSSustancia") != null
						&& request.getParameter("SLSSustancia").toString()
								.compareTo("-1") != 0)
					if (lWhere)
						cCondicion += " And LC.iCveSustancia = "
								+ request.getParameter("SLSSustancia");
					else {
						cCondicion = " Where LC.iCveSustancia = "
								+ request.getParameter("SLSSustancia");
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
						Fecha = "LC.dtGeneracion ";
						break;
					case 2:
						Fecha = "LC.dtAnalisis ";
						break;
					case 3:
						Fecha = "LC.dtAutorizacion ";
						break;
					}
					TFechas TFecha = new TFechas();

					if (lWhere)
						cCondicion += " And "
								+ Fecha
								+ "between '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaI").toString())
								+ "'"
								+ " And '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaF").toString()) + "'";
					else {
						cCondicion += " Where "
								+ Fecha
								+ "between '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaI").toString())
								+ "'"
								+ " And '"
								+ TFecha.getDateSQL(request.getParameter(
										"dtFechaF").toString()) + "'";
						lWhere = true;
					}
				}
				cCondicion += (!lWhere ? " where " : " and ")
						+ " LC.dtCalibracion is not null";
				if (cOrden.compareTo("") == 0)
					cOrden = " order by LC.iCveLoteCuantita ";
				cCondicion += " " + cOrden;
			}
			if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
				// Realizar la b�squeda
				ArrayList vConsultas = new TDTOXLoteCuantita()
						.findGlobal(cCondicion);
				this.activeX.append(this.GenReporte(vConsultas));
			}

		} catch (Exception ex) {
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
		TVDinRep02 vResultado;
		TFechas Fecha = new TFechas();
		TNumeros Numero = new TNumeros();
		StringBuffer buffer = new StringBuffer();
		String cNomArchivo = "InfoLoteCuantita_";
		int iReng01 = 1, iReng02;
		try {
			// Verificar que existan registros a Desplegar
			if (vInfo.size() > 0 && ((ArrayList) vInfo.get(0)).size() > 0) {
				// Rep.comDespliega("A", iReng01,
				// "Global de Lotes confirmatorio");
				iReng01 = iReng02 = 4;
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
									+ vResultado.getString("cPrefLoteConf")
									+ "-"
									+ Numero.getNumeroSinSeparador(
											new Integer(
													vResultado
															.getString("iCveLoteCuantita")),
											4));
					Rep.comDespliega("C", iReng02,
							vResultado.getString("cDscSustancia"));
					Rep.comDespliega(
							"D",
							iReng02,
							Fecha.getFechaDDMMYYYY(
									vResultado.getDate("dtGeneracion"), "/"));
					if (vResultado.getDate("dtAnalisis") != null)
						Rep.comDespliega(
								"E",
								iReng02,
								Fecha.getFechaDDMMYYYY(
										vResultado.getDate("dtAnalisis"), "/"));
					else
						Rep.comDespliega("E", iReng02, Fecha.getFechaDDMMYYYY(
								vResultado.getDate("dtCalibracion"), "/"));
					Rep.comDespliega(
							"F",
							iReng02,
							vResultado.get("cUsuAnalisa") != null ? vResultado
									.getString("cUsuAnalisa") : "");
					Rep.comDespliega("G", iReng02,
							vResultado.getString("cCveEquipo"));
					Rep.comDespliega("H", iReng02, vResultado
							.getInt("lValidaCalib") == 1 ? "Correcta"
							: "Incorrecta");
					Rep.comDespliega("I", iReng02,
							vResultado.getString("dCalibrador"));
					Rep.comDespliega("J", iReng02,
							vResultado.getString("dNegativo"));
					Rep.comDespliega("K", iReng02,
							vResultado.getString("dControl1"));
					Rep.comDespliega("L", iReng02,
							vResultado.getString("dControl2"));
					Rep.comDespliega("M", iReng02,
							vResultado.getString("dControl3"));
					Rep.comDespliega("N", iReng02,
							vResultado.getString("iAnalizados"));
					Rep.comDespliega("O", iReng02,
							vResultado.getString("iAutorizados"));
					Rep.comDespliega("P", iReng02, "=N" + iReng02 + "-O"
							+ iReng02);
					Rep.comDespliega("Q", iReng02,
							vResultado.getString("iPositivos"));
					Rep.comDespliega("R", iReng02,
							vResultado.getString("iNegativos"));
				}
				Rep.comBordeTotal("A", iReng01, "R", iReng02, 1);
				// Generar archivo
				cNomArchivo += Fecha.getFechaDDMMYYYY(Fecha.TodaySQL(), "_");
				buffer = Rep.creaActiveX("pg070305080", cNomArchivo, false,
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
