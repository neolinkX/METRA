package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuración para Control al Transporte - Listado de los
 * Domicilios de las Empresas
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070502012CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070502012CFG.png'>
 */
public class pg070502012CFG extends CFGListBase2 {
	public pg070502012CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070502013.jsp|pg070502010.jsp|";
		DeleteAction = "Borrar";
		NavStatus = "Disabled";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDCTRDomicilio dCTRDomicilio = new TDCTRDomicilio();
		try {
			String cWhere = "";
			String cOrderBy = "";
			StringTokenizer stCondicion = new StringTokenizer(cCondicion);
			String tCondicion = "";
			boolean lEncontro = false;

			while (stCondicion.hasMoreElements()) {
				tCondicion = stCondicion.nextToken();
				if (tCondicion.compareToIgnoreCase("cIDDGPMPT") == 0
						&& !lEncontro) {
					lEncontro = true;
				}
				if (tCondicion.compareToIgnoreCase("iIDMdoTrans") == 0
						&& !lEncontro) {
					lEncontro = true;
				}
				if (tCondicion.compareToIgnoreCase("cRFC") == 0 && !lEncontro) {
					lEncontro = true;
				}
				if (tCondicion.compareToIgnoreCase("cDscEmpresa") == 0
						&& !lEncontro) {
					lEncontro = true;
				}
			}

			if (request.getParameter("iCveEmpresa") != null
					&& request.getParameter("iCveEmpresa").compareTo("") != 0)
				cWhere += " where CTRDomicilio.iCveEmpresa = "
						+ request.getParameter("iCveEmpresa");
			else if (request.getParameter("hdEmpresa") != null
					&& request.getParameter("hdEmpresa").compareTo("") != 0)
				cWhere += " where CTRDomicilio.iCveEmpresa = "
						+ request.getParameter("hdEmpresa");

			if (lEncontro)
				cCondicion = "";

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += " where " + cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				if (cOrden.compareTo(" order by cIDDGPMPT") == 0
						|| cOrden.compareTo(" order by iIDMdoTrans") == 0
						|| cOrden.compareTo(" order by cRFC") == 0
						|| cOrden.compareTo(" order by cDscEmpresa") == 0)
					cOrderBy = " order by iCveDomicilio desc ";
				else
					cOrderBy += cOrden + " desc ";
			} else
				cOrderBy += " order by iCveDomicilio desc ";

			if (request.getParameter("iCveEmpresa") != null
					|| request.getParameter("hdEmpresa") != null) {
				vDespliega = dCTRDomicilio.FindByAll(cWhere, cOrderBy);
				if (vDespliega.size() > 0)
					UpdStatus = "SaveOnly";
				else
					UpdStatus = "Hidden";
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDCTRDomicilio dCTRDomicilio = new TDCTRDomicilio();
		TVCTRDomicilio vCTRDomicilio = new TVCTRDomicilio();
		Vector vcCTRDomicilio = new Vector();
		try {
			if (request.getParameter("iCveEmpresa") != null
					&& request.getParameter("hdSel") != null
					&& request.getParameter("hdSel").compareTo("") != 0) {
				vcCTRDomicilio = dCTRDomicilio.FindByAll(
						" where iCveEmpresa = "
								+ request.getParameter("iCveEmpresa")
								+ " and lActivo = 1 ", "");
				if (vcCTRDomicilio.size() > 0)
					for (int i = 0; i < vcCTRDomicilio.size(); i++)
						vCTRDomicilio = (TVCTRDomicilio) vcCTRDomicilio.get(i);
				vCTRDomicilio.setICveEmpresa(Integer.parseInt(request
						.getParameter("iCveEmpresa")));
				vCTRDomicilio
						.setICveDomicilio(vCTRDomicilio.getICveDomicilio());
				vCTRDomicilio.setLActivo(0);
				cClave = (String) dCTRDomicilio.updateActual(null,
						vCTRDomicilio);

				vcCTRDomicilio = dCTRDomicilio.FindByAll(
						" where iCveEmpresa = "
								+ request.getParameter("iCveEmpresa")
								+ " and iCveDomicilio = "
								+ request.getParameter("hdSel"), "");
				if (vcCTRDomicilio.size() > 0)
					for (int i = 0; i < vcCTRDomicilio.size(); i++)
						vCTRDomicilio = (TVCTRDomicilio) vcCTRDomicilio.get(i);
				vCTRDomicilio.setICveEmpresa(Integer.parseInt(request
						.getParameter("iCveEmpresa")));
				vCTRDomicilio.setICveDomicilio(Integer.parseInt(request
						.getParameter("hdSel")));
				vCTRDomicilio.setLActivo(1);
				cClave = (String) dCTRDomicilio.updateActual(null,
						vCTRDomicilio);
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Método Guardar

}