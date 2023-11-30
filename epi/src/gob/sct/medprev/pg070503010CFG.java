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
public class pg070503010CFG extends CFGListBase2 {
	public String cValSQL = "";

	public pg070503010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		// cPaginas = "pg070803031.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDCTRPersonal DCTRPersonal = new TDCTRPersonal();
		TVCTRPersonal VCTRPersonal = new TVCTRPersonal();
		String cRFC = "";
		String cLicencia = "";
		String cNombre = "";
		String cApPaterno = "";
		String cApMaterno = "";
		String cModoTransporte = "";

		try {

			if (cOrden.compareTo("") == 0)
				cOrden = cOrden + " order by CTRPersonal.cRFC";

			if (cCondicion.compareToIgnoreCase("") != 0) {
				cCondicion = " where " + cCondicion;
			}

			if (request.getParameter("cRFC") != null) {
				if (request.getParameter("cRFC").toString()
						.compareToIgnoreCase("") != 0) {
					cRFC = " CTRPersonal.cRFC like '"
							+ request.getParameter("cRFC").toString() + "%'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cRFC;
					else
						cCondicion = "where " + cRFC;
				}
			}

			if (request.getParameter("cLicencia") != null) {
				if (request.getParameter("cLicencia").toString()
						.compareToIgnoreCase("") != 0) {
					cLicencia = " CTRPersonal.cLicencia like '"
							+ request.getParameter("cLicencia").toString()
							+ "%'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cLicencia;
					else
						cCondicion = "where " + cLicencia;
				}
			}

			if (request.getParameter("cNombre") != null) {
				if (request.getParameter("cNombre").toString()
						.compareToIgnoreCase("") != 0) {
					cNombre = " CTRPersonal.cNombre like '%"
							+ request.getParameter("cNombre").toString() + "%'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cNombre;
					else
						cCondicion = "where " + cNombre;
				}
			}

			if (request.getParameter("cApPaterno") != null) {
				if (request.getParameter("cApPaterno").toString()
						.compareToIgnoreCase("") != 0) {
					cApPaterno = " CTRPersonal.cApPaterno like '%"
							+ request.getParameter("cApPaterno").toString()
							+ "%'";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cApPaterno;
					else
						cCondicion = "where " + cApPaterno;
				}
			}

			if (request.getParameter("SLSModoTransporte") != null) {
				if (request.getParameter("SLSModoTransporte").toString()
						.compareToIgnoreCase("-1") != 0) {
					cModoTransporte = " CTRPersonal.iCveMdoTrans = "
							+ request.getParameter("SLSModoTransporte")
									.toString() + "";
					if (cCondicion.compareToIgnoreCase("") != 0)
						cCondicion = cCondicion + " and " + cModoTransporte;
					else
						cCondicion = "where " + cModoTransporte;
				}
			}

			if (cCondicion.compareToIgnoreCase("where") == 0)
				cCondicion = "";

			vDespliega = DCTRPersonal.FindByAll(cCondicion, cOrden);

			cValSQL = DCTRPersonal.cValSQL;

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
