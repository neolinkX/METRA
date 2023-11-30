package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;
import java.util.Vector;

/**
 * * Clase de configuracion para Clase para el despliegue da la Informaci�n en
 * la Tabla VEHVehiculo
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
 *      "JavaScript:alert('Consulte los archivos:\n-pg070704020CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070704020CFG.java.png'>
 */
public class pg070704020CFG extends CFGListBase2 {
	private StringBuffer sbReporte = new StringBuffer();

	public pg070704020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			if (request.getParameter("hdBoton") != null
					&& (request.getParameter("hdBoton").equals("Buscar") || request
							.getParameter("hdBoton").equals("Generar Reporte"))) {
				String cFecha = "";
				String cDesde = "";
				String cHasta = "";

				if (request.getParameter("iFiltro") != null) {

					cFecha = " WHERE ";
					cDesde = ""
							+ new TFechas().getDateSQL(request
									.getParameter("dtDesde"));
					cHasta = ""
							+ new TFechas().getDateSQL(request
									.getParameter("dtHasta"));

					if (Integer.parseInt(request.getParameter("iFiltro")) == 1) {
						cFecha += " dtSolicitud ";
					} else if (Integer
							.parseInt(request.getParameter("iFiltro")) == 2) {
						cFecha += " dtProgramado ";
					} else if (Integer
							.parseInt(request.getParameter("iFiltro")) == 3) {
						cFecha += " dtInicia ";
					} else if (Integer
							.parseInt(request.getParameter("iFiltro")) == 4) {
						cFecha += " dtRecepcion ";
					} else if (Integer
							.parseInt(request.getParameter("iFiltro")) == 5) {
						cFecha += " dtcancelacion ";
					}
					cFecha += " BETWEEN '" + cDesde + "' AND '" + cHasta + "' ";
				}

				if (request.getParameter("iCveVehiculo") != null
						&& Integer.parseInt(request
								.getParameter("iCveVehiculo")) > 0)
					cFecha += " AND m.iCveVehiculo = "
							+ request.getParameter("iCveVehiculo");

				if (request.getParameter("iCveEmpMantto") != null
						&& Integer.parseInt(request
								.getParameter("iCveEmpMantto")) > 0)
					cFecha += " AND m.iCveEmpMantto = "
							+ request.getParameter("iCveEmpMantto");

				if (request.getParameter("iCveTpoMantto") != null
						&& Integer.parseInt(request
								.getParameter("iCveTpoMantto")) > 0)
					cFecha += " AND m.iCveTpoMantto = "
							+ request.getParameter("iCveTpoMantto");

				if (cOrden.compareTo("") == 0)
					cOrden = " ORDER BY VEHVehiculo.iCveVehiculo ";

				vDespliega = new TDVEHEmpMantto().buscaMantXEmp(cFecha);

				if (request.getParameter("hdBoton").equals("Generar Reporte")
						&& vDespliega != null && vDespliega.size() > 0) {
					this.setExcel(vDespliega);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void setExcel(Vector vA) {
		try {
			TExcel repExcel = new TExcel("07");
			int iRow = 11;
			for (int i = 0; i < vA.size(); i++) {

				String cEmpresa = "";
				String cFSolicitud = "";
				String cFProgramado = "";
				String cFInicia = "";
				String cFRecepcion = "";
				String cVehiculo = "";
				String cMarca = "";
				String cModelo = "";
				String cPlacas = "";
				String cKilometraje = "";
				String cConcluido = "";
				String cTpoMantto = "";
				String cObservaciones = "";
				String cCancelado = "";
				String cFCancelacion = "";

				TVVEHEmpMantto tvEmpMantto = (TVVEHEmpMantto) vA.get(i);

				if (tvEmpMantto.getCDscEmpMantto() != null)
					cEmpresa = tvEmpMantto.getCDscEmpMantto();
				if (tvEmpMantto.getDtSolicitud() != null)
					cFSolicitud += tvEmpMantto.getDtSolicitud();
				if (tvEmpMantto.getDtProgramado() != null)
					cFProgramado += tvEmpMantto.getDtProgramado();
				if (tvEmpMantto.getDtInicia() != null)
					cFInicia += tvEmpMantto.getDtInicia();
				if (tvEmpMantto.getDtRecepcion() != null)
					cFRecepcion += tvEmpMantto.getDtRecepcion();
				if (tvEmpMantto.getCDscTpoVehiculo() != null)
					cVehiculo = tvEmpMantto.getCDscTpoVehiculo();
				if (tvEmpMantto.getCDscMarca() != null)
					cMarca = tvEmpMantto.getCDscMarca();
				if (tvEmpMantto.getCDscModelo() != null)
					cModelo = tvEmpMantto.getCDscModelo();
				if (tvEmpMantto.getCPlacas() != null)
					cPlacas = tvEmpMantto.getCPlacas();
				if (tvEmpMantto.getIKilometraje() != 0)
					cKilometraje += tvEmpMantto.getIKilometraje();
				if (tvEmpMantto.getLConcluido() != 0)
					cConcluido = "SI";
				else
					cConcluido = "NO";
				if (tvEmpMantto.getCDscTpoMantto() != null)
					cTpoMantto = tvEmpMantto.getCDscTpoMantto();
				if (tvEmpMantto.getCObservaciones() != null)
					cObservaciones = tvEmpMantto.getCObservaciones();
				if (tvEmpMantto.getLCancelado() != 0)
					cCancelado = "SI";
				else
					cCancelado = "NO";
				if (tvEmpMantto.getDtCancelacion() != null)
					cFCancelacion += tvEmpMantto.getDtCancelacion();

				repExcel.comDespliega("A", iRow, cEmpresa);
				repExcel.comDespliega("B", iRow, cFSolicitud);
				repExcel.comDespliega("C", iRow, cFProgramado);
				repExcel.comDespliega("D", iRow, cFInicia);
				repExcel.comDespliega("E", iRow, cFRecepcion);
				repExcel.comDespliega("F", iRow, cVehiculo);
				repExcel.comDespliega("G", iRow, cMarca);
				repExcel.comDespliega("H", iRow, cModelo);
				repExcel.comDespliega("I", iRow, cPlacas);
				repExcel.comDespliega("J", iRow, cKilometraje);
				repExcel.comDespliega("K", iRow, cConcluido);
				repExcel.comDespliega("L", iRow, cTpoMantto);
				repExcel.comDespliega("M", iRow, cObservaciones);
				repExcel.comDespliega("N", iRow, cCancelado);
				repExcel.comDespliega("O", iRow, cFCancelacion);
				iRow++;
			}
			sbReporte.append(repExcel
					.creaActiveX("pg070704020",
							"Consulta de Mantenimiento por Empresa", false,
							false, true));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public StringBuffer getSbReporte() {
		return sbReporte;
	}

}
