package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Listado de Servicio Prestado
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070501050CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070501050CFG.png'>
 */
public class pg070502016CFG extends CFGListBase2 {
	public pg070502016CFG() {
		vParametros = new TParametro("07");
		// UpdStatus = "SaveOnly";
		cPaginas = "pg070502010.jsp|";
		NavStatus = "Disabled";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDCTRServPrestado dCTRServPrestado = new TDCTRServPrestado();
		try {
			String cWhere = "";
			String cOrderBy = "";
			StringTokenizer stCondicion = new StringTokenizer(cCondicion);
			StringTokenizer stOrden = new StringTokenizer(cOrden);
			String tCondicion = "";
			String tOrden = "";
			boolean lEncontro = false;
			boolean lEncontroOrden = false;

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

			if (lEncontro)
				cCondicion = "";

			if (cCondicion.compareTo("") != 0)
				cWhere = " where lActivo = 1 and " + cCondicion;
			else
				cWhere = " where lActivo = 1 ";

			while (stOrden.hasMoreElements()) {
				tOrden = stOrden.nextToken();
				if (tOrden.compareToIgnoreCase("cIDDGPMPT") == 0
						&& !lEncontroOrden) {
					lEncontroOrden = true;
				}
				if (tOrden.compareToIgnoreCase("iIDMdoTrans") == 0
						&& !lEncontroOrden) {
					lEncontroOrden = true;
				}
				if (tOrden.compareToIgnoreCase("cRFC") == 0 && !lEncontroOrden) {
					lEncontroOrden = true;
				}
				if (tOrden.compareToIgnoreCase("cDscEmpresa") == 0
						&& !lEncontroOrden) {
					lEncontroOrden = true;
				}
			}

			if (lEncontroOrden)
				cOrden = "";

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else
				cOrderBy = " order by iCveServPrestado";

			vDespliega = dCTRServPrestado.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}

		if (!vDespliega.isEmpty())
			UpdStatus = "SaveOnly";
		else
			UpdStatus = "Disabled";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			TVCTRServEmpresa vCTRServEmpresa = new TVCTRServEmpresa();
			TDCTRServEmpresa dCTRServEmpresa = new TDCTRServEmpresa();
			Vector vcCTRServEmpresa = new Vector();

			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++)
				if (request.getParameter("chk" + i) != null) {
					vcCTRServEmpresa = dCTRServEmpresa.FindByAll(
							" where iCveEmpresa = "
									+ request.getParameter("iCveEmpresa")
									+ " and iCveServPrestado = "
									+ Integer.parseInt(request
											.getParameter("chk" + i)), "");
					if (vcCTRServEmpresa.size() == 0) {
						vCTRServEmpresa.setICveEmpresa(Integer.parseInt(request
								.getParameter("iCveEmpresa")));
						vCTRServEmpresa.setICveServPrestado(Integer
								.parseInt(request.getParameter("chk" + i)));
						cClave = (String) dCTRServEmpresa.insert(null,
								vCTRServEmpresa);
					}
				} else {
					vcCTRServEmpresa = dCTRServEmpresa.FindByAll(
							" where iCveEmpresa = "
									+ request.getParameter("iCveEmpresa")
									+ " and iCveServPrestado = "
									+ request.getParameter("hdChk" + i), "");
					if (vcCTRServEmpresa.size() > 0) {
						vCTRServEmpresa.setICveEmpresa(Integer.parseInt(request
								.getParameter("iCveEmpresa")));
						vCTRServEmpresa.setICveServPrestado(Integer
								.parseInt(request.getParameter("hdChk" + i)));
						cClave = (String) dCTRServEmpresa.delete(null,
								vCTRServEmpresa);
					}
				}
		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

}
