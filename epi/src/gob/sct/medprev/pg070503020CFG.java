package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Clase para el Listado de la tabla CTRPlantilla
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
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070502050.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070502050.java.png'>
 */
public class pg070503020CFG extends CFGListBase2 {
	public Vector vEtapas = new Vector();
	public String cValSQL = "";

	public pg070503020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		// cPaginas = "pg070803031.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		String cRFC = "";
		String cRazonSocial = "";
		String cCveEtapa = "";
		String cIDDGPMPT = "";
		String cdtSolicitud = "";
		String cdtVencimiento = "";
		String cdtRecepcion = "";

		try {

			if (cOrden.compareTo("") == 0)
				cOrden = cOrden + " order by GRLEmpresas.cRFC";

			if (cCondicion.compareToIgnoreCase("") != 0) {
				cCondicion = " where " + cCondicion;
			}

			if (request.getParameter("cRFC") != null) {
				if (request.getParameter("cRFC").toString()
						.compareToIgnoreCase("") != 0) {
					cRFC = " GRLEmpresas.cRFC like '"
							+ request.getParameter("cRFC").toString() + "%'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cRFC;
					else
						cCondicion = " where " + cRFC;
				}
			}

			if (request.getParameter("cRazonSocial") != null) {
				if (request.getParameter("cRazonSocial").toString()
						.compareToIgnoreCase("") != 0) {
					cRazonSocial = " GRLEmpresas.cDscEmpresa like '%"
							+ request.getParameter("cRazonSocial").toString()
							+ "%'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cRazonSocial;
					else
						cCondicion = " where " + cRazonSocial;
				}
			}

			if (request.getParameter("SLSEtapa") != null) {
				if (new Integer(request.getParameter("SLSEtapa").toString())
						.intValue() >= 0) {
					cCveEtapa = " GRLEtapa.iCveEtapa = "
							+ request.getParameter("SLSEtapa").toString() + "";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cCveEtapa;
					else
						cCondicion = " where " + cCveEtapa;
				}
			}

			if (request.getParameter("cIDDGPMPT") != null) {
				if (request.getParameter("cIDDGPMPT").toString()
						.compareToIgnoreCase("") != 0) {
					cIDDGPMPT = " GRLEmpresas.cIDDGPMPT LIKE '%"
							+ request.getParameter("cIDDGPMPT").toString()
							+ "%'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cIDDGPMPT;
					else
						cCondicion = " where " + cIDDGPMPT;
				}
			}

			if (request.getParameter("dtSolicitud") != null) {
				if (request.getParameter("dtSolicitud").toString()
						.compareToIgnoreCase("") != 0) {
					cdtSolicitud = " CTRPlantilla.dtSolicitud = '"
							+ request.getParameter("dtSolicitud").toString()
							+ "'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cdtSolicitud;
					else
						cCondicion = " where " + cdtSolicitud;
				}
			}

			if (request.getParameter("dtVencimiento") != null) {
				if (request.getParameter("dtVencimiento").toString()
						.compareToIgnoreCase("") != 0) {
					cdtVencimiento = " CTRPlantilla.dtVencimiento = '"
							+ request.getParameter("dtVencimiento").toString()
							+ "'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cdtVencimiento;
					else
						cCondicion = " where " + cdtVencimiento;
				}
			}

			if (request.getParameter("dtRecepcion") != null) {
				if (request.getParameter("dtRecepcion").toString()
						.compareToIgnoreCase("") != 0) {
					cdtRecepcion = " CTRPlantilla.dtRecepcion = '"
							+ request.getParameter("dtRecepcion").toString()
							+ "'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cdtRecepcion;
					else
						cCondicion = " where " + cdtRecepcion;
				}
			}

			if (cCondicion.compareToIgnoreCase("where") == 0)
				cCondicion = "";

			vDespliega = DGRLEmpresas.FindByAllSituacion(cCondicion, cOrden);

			cValSQL = DGRLEmpresas.cValSQL;

		} catch (Exception ex) {
			error("FillVector", ex);
		}

		if (!vDespliega.isEmpty()) {
			if (NavStatus.compareToIgnoreCase("Hidden") == 0)
				NavStatus = "FirstRecord";
		} else
			NavStatus = "Hidden";

	}

	public Vector getEtapas() {
		TDGRLEtapa DGRLEtapa = new TDGRLEtapa();

		try {
			vEtapas = DGRLEtapa.FindByAll(" where iCveProceso = 5 ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return vEtapas;

	}

	public Vector getPlantilla(int iviCveEmpresa, int iviCvePlantilla) {
		TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
		Vector vCTRPlantilla = new Vector();

		try {
			vCTRPlantilla = DCTRPlantilla
					.FindByAll(" where CTRPlantilla.iCveEmpresa = "
							+ iviCveEmpresa
							+ "   and CTRPlantilla.iCvePlantilla = "
							+ iviCvePlantilla);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vCTRPlantilla;
	}

	public Vector getSeguimiento(int iviCveEmpresa, int iviCvePlantilla,
			int iviCveMovimiento) {
		TDCTRSeguimiento DCTRSeguimiento = new TDCTRSeguimiento();
		Vector vSeguimiento = new Vector();

		try {
			vSeguimiento = DCTRSeguimiento
					.FindByAll(" where CTRSeguimiento.iCveEmpresa    = "
							+ iviCveEmpresa
							+ "   and CTRSeguimiento.iCvePlantilla  = "
							+ iviCvePlantilla
							+ "   and CTRSeguimiento.iCveMovimiento = "
							+ iviCveMovimiento);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vSeguimiento;
	}
}
