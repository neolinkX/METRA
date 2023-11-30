package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;
import com.micper.excepciones.*;

/**
 * * Clase de configuracion para Muestras
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301110CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301110CFG.png'>
 */
public class pg070303011CFG extends CFGListBase2 {
	public pg070303011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXMuestra dTOXMuestra = new TDTOXMuestra();
		if (this.getLPagina("pg070303010.jsp"))
			cPaginas = "pg070303010.jsp|";
		if (this.getLPagina("pg070303012.jsp"))
			cPaginas += "pg070303012.jsp|";
		String xCampo = "";
		String cAnio = "";
		String cUnidad = "";
		String cWhere = "";
		int zCampo = 0;
		try {
			xCampo = "" + request.getParameter("iCveEnvio");
			cAnio = "" + request.getParameter("hdCampoClave");
			cUnidad = "" + request.getParameter("hdCPropiedad");
			if (xCampo.compareTo("null") != 0 && xCampo.compareTo("") != 0) {
				zCampo = Integer.parseInt(xCampo, 10);
				cWhere = " Where a.iAnio      = " + cAnio
						+ "   and a.iCveUniMed = " + cUnidad
						+ "   and a.iCveEnvio  = " + zCampo;
			} else {
				zCampo = 0;
			}
			if (this.cCondicion != null && !cCondicion.equalsIgnoreCase(""))
				cWhere += " and " + this.cCondicion;
			if (this.cOrden == null || cOrden.length() < 1)
				cWhere += " order by a.iOrden";
			else
				cWhere += " " + this.cOrden;
			vDespliega = dTOXMuestra.FindByAll2(cWhere);

			String lAction = request.getParameter("hdBoton");
			String cvNombre = "";
			String cvTR = "";
			String cvMR = "";
			String cParam = "";
			String tTipoR;
			String tTipoM;

			if (lAction.equalsIgnoreCase("Guardar")) {

				if (!vDespliega.isEmpty()) {
					for (int i = 0; i < vDespliega.size(); i++) {

						TVTOXMuestra vTOXMuestraItera = new TVTOXMuestra();
						vTOXMuestraItera = (TVTOXMuestra) vDespliega.get(i);
						int iSituacion = 3;
						cvNombre = "lActivo"
								+ vTOXMuestraItera.getICveMuestra();
						cvTR = "iCveTipoRecep"
								+ vTOXMuestraItera.getICveMuestra();
						cvMR = "iCveMotRecep"
								+ vTOXMuestraItera.getICveMuestra();

						if (request.getParameter(cvNombre) != null) {
							if (request.getParameter(cvNombre)
									.compareToIgnoreCase("1") == 0) {

								tTipoR = request.getParameter(cvTR);

								tTipoM = request.getParameter(cvMR);
								if (tTipoR.compareTo("2") == 0)
									iSituacion = Integer.parseInt(vParametros
											.getPropEspecifica("TOXRechazo"),
											10);
								dTOXMuestra.update(null, tTipoR, tTipoM,
										iSituacion,
										vTOXMuestraItera.getICveMuestra(),
										vTOXMuestraItera.getIAnio());
							}
						}
					}
				} else {
					;
				}
				vDespliega = dTOXMuestra.FindByAll2(cWhere);
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public Object getInputs() throws CFGException {
		String cCampo;
		String cCampob;
		int iCampo;
		int iCuenta;
		float fCampo;
		java.sql.Date dtCampo;
		TVTOXMuestra vTOXMuestra = new TVTOXMuestra();

		try {

			cCampo = "" + request.getParameter("iCveMuestra");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXMuestra.setICveMuestra(Integer.parseInt(cCampo));

			cCampo = "" + request.getParameter("iCveTipoRecep");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXMuestra.setICveTipoRecep(iCampo);

			cCampo = "" + request.getParameter("iCveMotRecep");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXMuestra.setICveMotRecep(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXMuestra;
	}

}
