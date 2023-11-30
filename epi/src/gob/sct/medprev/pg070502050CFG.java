package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

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
public class pg070502050CFG extends CFGListBase2 {
	public pg070502050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		// cPaginas = "pg070803031.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDCTRPlantilla dCTRPlantilla = new TDCTRPlantilla();
		TVCTRPlantilla vCTRPlantilla = new TVCTRPlantilla();
		boolean lWhere = false;
		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}
			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo(
							"iCveUniMed") != 0
					&& request.getParameter("iCveUniMed").compareTo("") != 0
					&& new Integer(request.getParameter("iCveUniMed"))
							.intValue() > 0) {
				if (lWhere)
					cCondicion += " AND GRLEmpresas.iCveUniMed = "
							+ request.getParameter("iCveUniMed");
				else {
					cCondicion = " WHERE GRLEmpresas.iCveUniMed = "
							+ request.getParameter("iCveUniMed");
					lWhere = true;
				}
			} else {
				cCondicion = " WHERE GRLEmpresas.iCveUniMed = 0 ";
				lWhere = true;
			}
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").compareTo("null") != 0
					&& request.getParameter("iAnio").compareTo("") != 0
					&& new Integer(request.getParameter("iAnio")).intValue() > 0) {
				if (lWhere)
					cCondicion += " AND CTRPlantilla.iAnio = "
							+ request.getParameter("iAnio");
				else {
					cCondicion = " WHERE CTRPlantilla.iAnio = "
							+ request.getParameter("iAnio");
					lWhere = true;
				}
			}
			if (request.getParameter("iCvePeriodPla") != null
					&& request.getParameter("iCvePeriodPla").compareTo("null") != 0
					&& request.getParameter("iCvePeriodPla").compareTo("") != 0
					&& new Integer(request.getParameter("iCvePeriodPla"))
							.intValue() > 0) {
				if (lWhere)
					cCondicion += " AND CTRPlantilla.iCvePeriodPla = "
							+ request.getParameter("iCvePeriodPla");
				else {
					cCondicion = " WHERE CTRPlantilla.iCvePeriodPla = "
							+ request.getParameter("iCvePeriodPla");
					lWhere = true;
				}
			}
			if (request.getParameter("dtSolicitud") != null
					&& request.getParameter("dtSolicitud").compareTo("null") != 0
					&& request.getParameter("dtSolicitud").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND CTRPlantilla.dtSolicitud = '"
							+ request.getParameter("dtSolicitud") + "'";
				else {
					cCondicion = " WHERE CTRPlantilla.dtSolicitud = '"
							+ request.getParameter("dtSolicitud") + "'";
					lWhere = true;
				}
			}
			if (request.getParameter("iCvePais") != null
					&& request.getParameter("iCvePais").compareTo("null") != 0
					&& request.getParameter("iCvePais").compareTo("") != 0
					&& new Integer(request.getParameter("iCvePais")).intValue() > 0) {
				if (lWhere)
					cCondicion += " AND CTRDomicilio.iCvePais = "
							+ request.getParameter("iCvePais");
				else {
					cCondicion = " WHERE CTRDomicilio.iCvePais = "
							+ request.getParameter("iCvePais");
					lWhere = true;
				}
			}
			if (request.getParameter("iCveEstado") != null
					&& request.getParameter("iCveEstado").compareTo("null") != 0
					&& request.getParameter("iCveEstado").compareTo("") != 0
					&& new Integer(request.getParameter("iCveEstado"))
							.intValue() > 0) {
				if (lWhere)
					cCondicion += " AND CTRDomicilio.iCveEstado = "
							+ request.getParameter("iCveEstado");
				else {
					cCondicion = " WHERE CTRDomicilio.iCveEstado = "
							+ request.getParameter("iCveEstado");
					lWhere = true;
				}
			}
			if (request.getParameter("iCveMunicipio") != null
					&& request.getParameter("iCveMunicipio").compareTo("null") != 0
					&& request.getParameter("iCveMunicipio").compareTo("") != 0
					&& new Integer(request.getParameter("iCveMunicipio"))
							.intValue() > 0) {
				if (lWhere)
					cCondicion += " AND CTRDomicilio.iCveMunicipio = "
							+ request.getParameter("iCveMunicipio");
				else {
					cCondicion = " WHERE CTRDomicilio.iCveMunicipio = "
							+ request.getParameter("iCveMunicipio");
					lWhere = true;
				}
			}
			if (request.getParameter("cRFC") != null
					&& request.getParameter("cRFC").compareTo("null") != 0
					&& request.getParameter("cRFC").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND GRLEmpresas.cRFC = '"
							+ request.getParameter("cRFC") + "' ";
				else {
					cCondicion = " WHERE GRLEmpresas.cRFC = '"
							+ request.getParameter("cRFC") + "' ";
					lWhere = true;
				}
			}
			if (request.getParameter("cDenominacion") != null
					&& request.getParameter("cDenominacion").compareTo("null") != 0
					&& request.getParameter("cDenominacion").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND GRLEmpresas.cDscEmpresa like '%"
							+ request.getParameter("cDenominacion") + "%' ";
				else {
					cCondicion = " WHERE GRLEmpresas.cDScEmpresa like '%"
							+ request.getParameter("cDenominacion") + "%' ";
					lWhere = true;
				}
			}

			if (request.getParameter("cIDDGPMPT") != null
					&& request.getParameter("cIDDGPMPT").compareTo("null") != 0
					&& request.getParameter("cIDDGPMPT").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND GRLEmpresas.cIDDGPMPT = '"
							+ request.getParameter("cIDDGPMPT") + "' ";
				else {
					cCondicion = " WHERE GRLEmpresas.cIDDGPMPT = '"
							+ request.getParameter("cIDDGPMPT") + "' ";
					lWhere = true;
				}
			}

			vDespliega = dCTRPlantilla.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}

		if (!vDespliega.isEmpty()) {
			if (NavStatus.compareToIgnoreCase("Hidden") == 0)
				NavStatus = "FirstRecord";
		} else
			NavStatus = "Hidden";

	}
}
