package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import java.text.*;

/**
 * * Clase de configuracion para Clase para el control de la tabla de
 * ALMArticulo
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hern�ndez Garc�a
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg070803012CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803012CFG.java.png'>
 */
public class pg070803012CFG extends CFGListBase2 {
	TDALMArticulo dALMArticulo = new TDALMArticulo();
	TVALMArticulo vALMArticulo = new TVALMArticulo();
	boolean lCondicion = false;

	public pg070803012CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	private StringBuffer activeX = new StringBuffer("");

	public String getActiveX() {
		return activeX.toString();
	}

	public void Despliega() {
		boolean lWhere = false;
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").toString().compareTo("") != 0
					&& request.getParameter("iCveAlmacen") != null
					&& request.getParameter("iCveAlmacen").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveSolicSum") != null
					&& request.getParameter("iCveSolicSum").toString()
							.compareTo("") != 0) {

				vALMArticulo
						.setIAnio(new Integer(request.getParameter("iAnio"))
								.intValue());
				vALMArticulo.setICveAlmacen(new Integer(request
						.getParameter("iCveAlmacen")).intValue());
				vALMArticulo.setICveSolicSum(new Integer(request
						.getParameter("iCveSolicSum")).intValue());
				if (!lCondicion) {
					if (cCondicion.compareTo("") != 0) {
						cCondicion = " WHERE " + cCondicion;
						lWhere = true;
					}
					lCondicion = true;
				}
				if (cOrden.compareTo("") == 0)
					cOrden = " ORDER BY ALMArticulo.iCveArticulo ";
				vDespliega = dALMArticulo.FindBySumArt2(vALMArticulo,
						cCondicion, cOrden, " inner ", 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StringBuffer Report(Vector cResultado) {
		TExcel Rep1 = new TExcel("07");
		TFechas tFecha = new TFechas("07");
		int j = 19;
		String cNomArch = "";
		try {
			if (cResultado.size() > 0) { // Se recorre el Vector de los
											// Articulos desplegados
				vALMArticulo = (TVALMArticulo) cResultado.get(0);

				// Almac�n
				Rep1.comDespliega("B", 12, "" + vALMArticulo.getCDscAlmacen());
				// Unidad M�dica
				Rep1.comDespliega("B", 13, "" + vALMArticulo.getCDscUniMed());
				// M�dulo
				Rep1.comDespliega("B", 14, "" + vALMArticulo.getCDscModulo());
				// Area
				Rep1.comDespliega("B", 15, "" + vALMArticulo.getCDscArea());

				// Fecha Reporte
				Rep1.comDespliega("D", 12,
						tFecha.getFechaDDMMYYYY(tFecha.TodaySQL(), "/")); // FECHA
																			// DE
																			// HOY
				// A�o
				Rep1.comDespliega("D", 13, "" + vALMArticulo.getIAnio());
				cNomArch = "SSum_" + vALMArticulo.getIAnio();
				// No. Solicitud
				DecimalFormat df = new DecimalFormat("000");
				Rep1.comDespliega(
						"D",
						14,
						""
								+ df.format(Double.parseDouble(""
										+ vALMArticulo.getICveSolicSum())));
				cNomArch += "_"
						+ df.format(Double.parseDouble(""
								+ vALMArticulo.getICveSolicSum()));
				// Fecha Solicitud
				Rep1.comDespliega("D", 15, tFecha.getFechaDDMMYYYY(
						vALMArticulo.getDtSolicitud(), "/"));

				for (int i = 0; i < vDespliega.size(); i++) {
					vALMArticulo = (TVALMArticulo) cResultado.get(i);
					Rep1.comDespliega("A", j,
							"" + vALMArticulo.getcCveArticulo());
					Rep1.comAlineaRango("A", j, "A", j, Rep1.getAT_HCENTRO());
					Rep1.comDespliega("B", j, "" + vALMArticulo.getcDscBreve());
					Rep1.comAlineaRango("B", j, "B", j, Rep1.getAT_HJUSTIFICA());
					Rep1.comAlineaRangoVer("B", j, "B", j,
							Rep1.getAT_VAJUSTAR());
					if (vALMArticulo.getcDscUniSum() != null
							&& !vALMArticulo.getcDscUniSum().equalsIgnoreCase(
									"null"))
						Rep1.comDespliega("C", j,
								"" + vALMArticulo.getcDscUniSum());
					else
						Rep1.comDespliega("C", j, "");
					Rep1.comAlineaRango("C", j, "C", j, Rep1.getAT_HIZQUIERDA());
					Rep1.comDespliega("D", j,
							"" + vALMArticulo.getDUnidSolicita());
					Rep1.comAlineaRango("D", j, "D", j, Rep1.getAT_HDERECHA());
					Rep1.comCellFormat("D", j, "D", j, "#,##0");
					Rep1.comBordeTotal("A", j, "D", j, 1);
					j++;
				}

				j++;
				Rep1.comDespliega("A", j, "JEFE DEL AREA SOLICITANTE");
				Rep1.comAlineaRango("A", j, "A", j, Rep1.getAT_HIZQUIERDA());
				Rep1.comDespliega("D", j,
						"RECIBIDO POR EL ALMACEN DE BIENES DE CONSUMO");
				Rep1.comAlineaRango("D", j, "D", j, Rep1.getAT_HDERECHA());
				j++;
				String cResponsable = "______________________________________";
				if (vALMArticulo.getCRespArea() != null
						&& !cResponsable.equalsIgnoreCase("null"))
					cResponsable = vALMArticulo.getCRespArea();
				Rep1.comDespliega("A", j, "NOMBRE: " + cResponsable);
				Rep1.comAlineaRango("A", j, "A", j, Rep1.getAT_HIZQUIERDA());
				Rep1.comDespliega("D", j,
						"NOMBRE: " + vALMArticulo.getCDscUsuResp());
				Rep1.comAlineaRango("D", j, "D", j, Rep1.getAT_HDERECHA());
				j += 3;
				Rep1.comDespliega("A", j,
						"_____________________________________________");
				Rep1.comAlineaRango("A", j, "A", j, Rep1.getAT_HIZQUIERDA());
				Rep1.comDespliega("D", j,
						"_____________________________________________");
				Rep1.comAlineaRango("D", j, "D", j, Rep1.getAT_HDERECHA());
				j++;
				Rep1.comDespliega("A", j, "                              FIRMA");
				Rep1.comAlineaRango("A", j, "A", j, Rep1.getAT_HIZQUIERDA());
				Rep1.comDespliega("D", j, "FIRMA                              ");
				Rep1.comAlineaRango("D", j, "D", j, Rep1.getAT_HDERECHA());

				Rep1.comBordeRededor("A", j - 5, "D", j, 2, 1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		StringBuffer buffer = Rep1.creaActiveX("pg070803012", cNomArch, false,
				false, true);
		return buffer;
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		cPaginas = "pg070803010.jsp|pg070803011.jsp|";
		TDALMSumArt dALTSumArt = new TDALMSumArt();
		TVALMSumArt vALTSumArt = new TVALMSumArt();
		Vector vSumArt = new Vector();
		int iCveArticulo = 0;
		String cFiltro = "";
		boolean lExiste = false;
		int lEliminar = 0;

		float dUnidSolicita = 0; // Valor de BD
		float dUnidSolicitaP = 0; // Valor de PANTALLA
		int iCveMeta = 0; // Valor de BD
		int iCveMetaP = 0; // Valor de PANTALLA

		try {
			this.Despliega();
			if (request.getParameter("hdBoton").toString().compareTo("Guardar") == 0) {
				if (vDespliega.size() > 0) { // Se recorre el Vector de los
												// Articulos desplegados
					for (int i = 0; i < vDespliega.size(); i++) {
						vALMArticulo = (TVALMArticulo) vDespliega.get(i);
						iCveArticulo = vALMArticulo.getiCveArticulo(); // Se
																		// obtiene
																		// la
																		// clave
																		// del
																		// articulo

						if (request.getParameter("lEliminar" + iCveArticulo) != null)
							lEliminar = new Integer(request.getParameter(
									"lEliminar" + iCveArticulo).toString())
									.intValue();
						else
							lEliminar = 0;

						if (lEliminar == 1) { // SE ELIMINA
							vALTSumArt.setIAnio(new Integer(request
									.getParameter("iAnio")).intValue());
							vALTSumArt.setICveAlmacen(new Integer(request
									.getParameter("iCveAlmacen")).intValue());
							vALTSumArt.setICveSolicSum(new Integer(request
									.getParameter("iCveSolicSum")).intValue());
							vALTSumArt.setICveArticulo(iCveArticulo);
							dALTSumArt.delete(null, vALTSumArt);
						}
					}
				}
				this.Despliega();
			} else if (request.getParameter("hdBoton").toString()
					.compareTo("Autorizar") == 0) { // AUTORIZACI�N DE LOS
													// ARTICULOS
				TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
						.getAttribute("UsrID");
				TDALMSolicSumin dSolicSumin = new TDALMSolicSumin();
				TVALMSolicSumin vSolicSumin = new TVALMSolicSumin();
				vSolicSumin.setIAnio(new Integer(request.getParameter("iAnio"))
						.intValue());
				vSolicSumin.setICveAlmacen(new Integer(request
						.getParameter("iCveAlmacen")).intValue());
				vSolicSumin.setICveSolicSum(new Integer(request
						.getParameter("iCveSolicSum")).intValue());
				vSolicSumin.setICveUsuAutoriza(vUsuario.getICveusuario());
				dSolicSumin.update2(null, vSolicSumin); // SE ACTUALIZA
														// ALMSOLICSUMIN.LAUTORIZADA
														// = 1
				this.Despliega();
			} else if (request.getParameter("hdBoton").compareToIgnoreCase(
					"Generar Reporte") == 0) {
				activeX.append(this.Report(vDespliega));
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
