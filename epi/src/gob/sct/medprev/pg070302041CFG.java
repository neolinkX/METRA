package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG del listado de la tabla TOXMuestra
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070302041CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070302041CFG.png'>
 */
public class pg070302041CFG extends CFGListBase2 {
	public pg070302041CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070302040.jsp|pg070302050.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDMuestra dMuestra = new TDMuestra();
		try {
			String cFiltro = "";
			cFiltro = " where TOXMuestra.iAnio      = "
					+ request.getParameter("hdCampoClave")
					+ "   and TOXMuestra.iCveUniMed = "
					+ request.getParameter("hdCampoClave2")
					+ "   and TOXMuestra.iCveEnvio  = "
					+ request.getParameter("hdCampoClave3");
			if (cCondicion.compareTo("") != 0) {
				cFiltro = cFiltro + " and " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cFiltro = cFiltro + cOrden;
			}
			vDespliega = dMuestra.FindByAll3(cFiltro);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public Object Envio() {
		TVTOXEnvio VEnvio = new TVTOXEnvio();
		try {
			VEnvio = (TVTOXEnvio) (new TDTOXEnvio().FindByAll2(
					" where E.iAnio      = "
							+ request.getParameter("hdCampoClave")
							+ "   and E.iCveUniMed = "
							+ request.getParameter("hdCampoClave2")
							+ "   and E.iCveEnvio  = "
							+ request.getParameter("hdCampoClave3"), ""))
					.get(0);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		return VEnvio;
	}

	public String getOtrasSust(TVTOXCuantAnalisis VCAnal) {
		String cTexto = new String();
		try {
			cTexto = new TDTOXCuantAnalisis().getSustPost(VCAnal).toString();
			cTexto += "&nbsp;";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cTexto;
	}

	public String getAutorizada(TVTOXCuantAnalisis VCAnal) {
		String cTexto = new String();
		try {
			cTexto = new TDTOXCuantAnalisis().getAutorizada(VCAnal).toString();
			cTexto += "&nbsp;";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cTexto;
	}

}