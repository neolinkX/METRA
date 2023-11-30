package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;
import java.util.Vector;

/**
 * * Clase de configuracion para Control de Vehiculos - Solicitudes de Vehiculo
 * por Usuario
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070704010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070704010CFG.png'>
 */
public class pg070704010CFG extends CFGListBase2 {
	String cFechaIni = "";
	String cFechaFin = "";
	int iMes = 0;
	int iAnio = 0;
	public StringBuffer Reporte = new StringBuffer("");

	public pg070704010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDVEHSolicitud dVEHSolicitud = new TDVEHSolicitud();
		try {
			String cWhere = "";
			String cOrderBy = "";
			int iClaveTemp;
			if (request.getParameter("iCveUniMed") != null) {
				cWhere = " where VEHSolicitud.iCveUniMed = "
						+ request.getParameter("iCveUniMed");
				if (request.getParameter("iCveModulo") != null) {
					iClaveTemp = Integer.parseInt(
							request.getParameter("iCveModulo"), 10);
					if (iClaveTemp > 0)
						cWhere += " and VEHSolicitud.iCveModulo = "
								+ iClaveTemp;
				}
				if (request.getParameter("iCveArea") != null) {
					iClaveTemp = Integer.parseInt(
							request.getParameter("iCveArea"), 10);
					if (iClaveTemp > 0)
						cWhere += " and VEHSolicitud.iCveArea = " + iClaveTemp;
				}
				if (request.getParameter("iAnio") != null) {
					if (!request.getParameter("iAnio").equals("-1")) {
						try {
							iAnio = Integer.parseInt(
									request.getParameter("iAnio"), 10);
						} catch (Exception ex) {
							iAnio = 0;
						}
						if (iAnio > 0) {
							cWhere += " and VEHSolicitud.iAnio = " + iAnio;
							if (request.getParameter("iMes") != null) {
								iMes = request.getParameter("iMes") != null ? Integer
										.parseInt(request.getParameter("iMes"),
												10) : 0;
								iMes = Integer.parseInt(
										request.getParameter("iMes"), 10);
								if (iMes > 0) {
									fObtenerRangoFechas();
									cWhere += " and VEHSolicitud.dtSolicitud >= '"
											+ cFechaIni
											+ "'"
											+ " and VEHSolicitud.dtSolicitud <= '"
											+ cFechaFin + "'";
								}
							}
						}
					}
				}
				if (request.getParameter("iCveUsuSolic") != null) {
					iClaveTemp = Integer.parseInt(
							request.getParameter("iCveUsuSolic"), 10);
					if (iClaveTemp > 0)
						cWhere += " and VEHSolicitud.iCveUsuSolic = "
								+ iClaveTemp;
				}
				if (request.getParameter("iCveVehiculo") != null) {
					iClaveTemp = Integer.parseInt(
							request.getParameter("iCveVehiculo"), 10);
					if (iClaveTemp > 0)
						cWhere += "  and VEHVehiculo.iCveVehiculo = "
								+ iClaveTemp;
				}
				if (cCondicion.compareTo("") != 0)
					cWhere += " and " + cCondicion;

				cOrderBy = cOrden;
				vDespliega = dVEHSolicitud.FindSolicXUsr(cWhere, cOrderBy);
			}
			if (cAccion.compareToIgnoreCase("Generar Reporte") == 0) {
				Reporte(vDespliega);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public void Reporte(Vector vDatos) {
		TExcel tRep = new TExcel("07");
		TFechas tFecha = new TFechas("07");
		TVVEHSolicitud VSolicitud;
		int iReng = 15;
		int iAsignados = 0, iEntregados = 0, iCancelados = 0;
		long iKilometros = 0, iKmTemp = 0;

		try {
			if (vDatos != null && vDatos.size() > 0) {
				VSolicitud = (TVVEHSolicitud) vDatos.get(0);
				tRep.comDespliega("C", 12, VSolicitud.getCDscUniMed());
				tRep.comDespliega("K", 12, VSolicitud.getIAnio() + "");
				String cMes = (iMes > 0) ? (tFecha.getVNombresMes().size() == 12) ? tFecha
						.getVNombresMes().get(iMes - 1).toString()
						: "Nombres de mes no registrados"
						: "Todos";
				tRep.comDespliega("O", 12, cMes);

				// Recorrido de registros
				for (int i = 0; i < vDatos.size(); i++) {
					VSolicitud = (TVVEHSolicitud) vDatos.get(i);
					tRep.comDespliega("A", iReng, VSolicitud.getICveSolicitud()
							+ "");
					tRep.comDespliega(
							"B",
							iReng,
							"'"
									+ tFecha.getFechaDDMMMYYYY(
											VSolicitud.getDtSolicitud(), "/"));
					String cHora = VSolicitud.getTsSolicitud().toString();
					if (cHora.compareTo("") != 0)
						cHora = cHora.substring(11, 16);
					else
						cHora = "";
					tRep.comDespliega("C", iReng, "'" + cHora);
					tRep.comDespliega("D", iReng, VSolicitud.getCDscModulo());
					tRep.comDespliega("E", iReng, VSolicitud.getCDscArea());
					String cUsuSolic = "";
					if (VSolicitud.getCNombre() != null)
						cUsuSolic += VSolicitud.getCNombre();
					if (VSolicitud.getCApPaterno() != null)
						cUsuSolic += " " + VSolicitud.getCApPaterno();
					if (VSolicitud.getCApMaterno() != null)
						cUsuSolic += " " + VSolicitud.getCApMaterno();
					tRep.comDespliega("F", iReng, cUsuSolic);
					tRep.comDespliega("G", iReng, VSolicitud.getCDscMotivo());
					tRep.comDespliega("H", iReng, VSolicitud.getCDestino());
					if (VSolicitud.getCDscBreveTpoVeh() != null
							&& !VSolicitud.getCDscBreveTpoVeh()
									.equalsIgnoreCase("null"))
						tRep.comDespliega("I", iReng,
								VSolicitud.getCDscBreveTpoVeh());
					if (VSolicitud.getDtAsignado() != null) {
						tRep.comDespliega(
								"J",
								iReng,
								"'"
										+ tFecha.getFechaDDMMMYYYY(
												VSolicitud.getDtAsignado(), "/"));
						iAsignados++;
					}
					if (VSolicitud.getDtEntrega() != null) {
						tRep.comDespliega(
								"K",
								iReng,
								"'"
										+ tFecha.getFechaDDMMMYYYY(
												VSolicitud.getDtEntrega(), "/"));
						iEntregados++;
					}
					if (VSolicitud.getDtCancelacion() != null) {
						tRep.comDespliega(
								"L",
								iReng,
								"'"
										+ tFecha.getFechaDDMMMYYYY(
												VSolicitud.getDtCancelacion(),
												"/"));
						iCancelados++;
					}
					if (VSolicitud.getDtAsignado() != null
							&& VSolicitud.getLAsignado() == 1) {
						tRep.comDespliega("M", iReng, VSolicitud.getCDscMarca()
								+ " - " + VSolicitud.getCDscModelo() + " - "
								+ VSolicitud.getIAnioVeh());
						tRep.comDespliega("N", iReng, VSolicitud.getCPlacas());
						tRep.comDespliega("O", iReng,
								VSolicitud.getIKmInicial() + "");
						if (VSolicitud.getDtEntrega() != null) {
							tRep.comDespliega("P", iReng,
									VSolicitud.getIKmFinal() + "");
							iKmTemp = VSolicitud.getIKmFinal()
									- VSolicitud.getIKmInicial();
							tRep.comDespliega("Q", iReng, iKmTemp + "");
							iKilometros += iKmTemp;
						}
					}
					tRep.comAlineaRango("A", iReng, "C", iReng,
							tRep.getAT_HCENTRO());
					tRep.comAlineaRango("D", iReng, "I", iReng,
							tRep.getAT_HJUSTIFICA());
					tRep.comAlineaRango("J", iReng, "L", iReng,
							tRep.getAT_HCENTRO());
					tRep.comAlineaRango("M", iReng, "M", iReng,
							tRep.getAT_HJUSTIFICA());
					tRep.comAlineaRango("N", iReng, "N", iReng,
							tRep.getAT_HCENTRO());
					tRep.comAlineaRango("O", iReng, "Q", iReng,
							tRep.getAT_HDERECHA());
					tRep.comCellFormat("O", iReng, "Q", iReng, "#,##0");
					tRep.comAlineaRangoVer("A", iReng, "Q", iReng,
							tRep.getAT_VSUPERIOR());
					tRep.comAlineaRangoVer("A", iReng, "Q", iReng,
							tRep.getAT_VAJUSTAR());
					tRep.comBordeTotal("A", iReng, "Q", iReng, 1);
					iReng++;
				}
				// total de vehiculos
				tRep.comDespliega("H", iReng, "Total de Veh�culos:");
				tRep.comDespliega("J", iReng, iAsignados + "");
				tRep.comDespliega("K", iReng, iEntregados + "");
				tRep.comDespliega("L", iReng, iCancelados + "");
				tRep.comAlineaRango("H", iReng, "I", iReng,
						tRep.getAT_COMBINA_DERECHA());
				tRep.comFontBold("H", iReng, "L", iReng);
				tRep.comFontColor("H", iReng, "L", iReng, 2);
				tRep.comBordeTotal("H", iReng, "L", iReng, 1);
				tRep.comFillCells("H", iReng, "L", iReng, 48);
				// kilometraje total
				tRep.comDespliega("N", iReng, "Total de Kil�metros:");
				tRep.comDespliega("Q", iReng, iKilometros + "");
				tRep.comAlineaRango("N", iReng, "P", iReng,
						tRep.getAT_COMBINA_DERECHA());
				tRep.comFontBold("N", iReng, "Q", iReng);
				tRep.comFontColor("N", iReng, "Q", iReng, 2);
				tRep.comBordeTotal("N", iReng, "Q", iReng, 1);
				tRep.comFillCells("N", iReng, "Q", iReng, 48);
			}
		} catch (Exception ex) {
			error("Reporte", ex);
		}
		Reporte = tRep.creaActiveX("pg070704010",
				"ConsSol_" + tFecha.getFechaDDMMYYYY(tFecha.TodaySQL(), "-"),
				false, false, true);
	}

	private void fObtenerRangoFechas() {
		TFechas pFecha = new TFechas("07");
		Integer iAnio = new Integer(request.getParameter("iAnio"));
		cFechaIni = pFecha.getFechaYYYYMMDD(
				pFecha.getDateSQL(new Integer(1), new Integer(iMes), iAnio),
				"-");

		cFechaFin = "31";
		if (iMes == 2)
			cFechaFin = (iAnio.intValue() % 4 == 0) ? "29" : "28";
		else if (iMes == 4 || iMes == 6 || iMes == 9 || iMes == 11)
			cFechaFin = "30";

		// cFechaFin += "/" + iMes + "/" + iAnio;
		cFechaFin = pFecha.getFechaYYYYMMDD(pFecha.getDateSQL(new Integer(
				cFechaFin), new Integer(iMes), iAnio), "-");
	}

	public String getReporte() {
		return Reporte.toString();
	}

}
