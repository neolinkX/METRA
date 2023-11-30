package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.excepciones.*;
import com.micper.util.*;
import com.micper.sql.*;
import java.sql.*;

/**
 * * Clase de configuracion para CFG Seguimiento del Mantenimiento
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070602010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070602010CFG.png'>
 */
public class pg070703023CFG extends CFGListBase2 {
	public TVVEHVehiculo tvVehiculo = null;
	public TVVEHSolicitud tvSolicitud = null;
	public Vector vEtapas = new Vector();
	public Vector vEtapaAGuadar = new Vector();
	public int siguienteEtapa;
	public int grabaPrimeraEtapa;
	public int grabaUltimaEtapa;
	public int iCveVehiculo;
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private BeanScroller BeanSC;

	public pg070703023CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cOrden = "";
		cCondicion = "";
		siguienteEtapa = -1;
		grabaPrimeraEtapa = -1;
		iCveVehiculo = 0;
		grabaUltimaEtapa = 0;
		cPaginas = "pg070703020.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDVEHSolicitud dSolic = new TDVEHSolicitud();
		TVVEHSolicitud tvSolicitud = new TVVEHSolicitud();
		int iAnio = 0;
		int iCveUniMed = 0;
		int iCveSolicitud = 0;
		int iCveEtapaSolic = 0;
		int iCveConfControl = 0;
		int iCveUsuReg = 0;
		int num_reg = 0;
		int iCveVehiculo = 0;
		int iKmInicial = 0;
		int iKmFinal = 0;
		try {
			DbConnection dbConn = new DbConnection(dataSourceName);
			Connection conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			if (request.getParameter("iAnio") != null
					&& !request.getParameter("iAnio").equals(""))
				iAnio = Integer.parseInt(request.getParameter("iAnio")
						.toString());
			if (request.getParameter("iCveUniMed") != null
					&& !request.getParameter("iCveUniMed").equals(""))
				iCveUniMed = Integer.parseInt(request
						.getParameter("iCveUniMed").toString());
			if (request.getParameter("iCveSolicitud") != null
					&& !request.getParameter("iCveSolicitud").equals(""))
				iCveSolicitud = Integer.parseInt(request.getParameter(
						"iCveSolicitud").toString());
			if (request.getParameter("iKmInicial") != null
					&& !request.getParameter("iKmInicial").equals(""))
				iKmInicial = Integer.parseInt(request
						.getParameter("iKmInicial").toString());
			if (request.getParameter("iKmFinal") != null
					&& !request.getParameter("iKmFinal").equals(""))
				iKmFinal = Integer.parseInt(request.getParameter("iKmFinal")
						.toString());

			// Actualizaci�n del Kilometraje
			tvSolicitud.setIAnio(iAnio);
			tvSolicitud.setICveUniMed(iCveUniMed);
			tvSolicitud.setICveSolicitud(iCveSolicitud);
			tvSolicitud.setIKmInicial(iKmInicial);
			tvSolicitud.setIKmFinal(iKmFinal);
			dSolic.updateKilimentraje(conn, tvSolicitud);
			conn.commit();
			conn.close();
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al Actualizar el registro", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDVEHVehiculo dVehiculo = new TDVEHVehiculo();
		TDVEHSolicitud dSolicitud = new TDVEHSolicitud();
		TDVEHControlSolic dCtrol = new TDVEHControlSolic();
		Vector vVeh = new Vector();
		Vector vSol = new Vector();

		String iAnio = "";
		String iCveUniMed = "";
		String iCveSolicitud = "";

		if (request.getParameter("iAnio") != null
				&& !request.getParameter("iAnio").equals(""))
			iAnio = request.getParameter("iAnio").toString();
		if (request.getParameter("iCveUniMed") != null
				&& !request.getParameter("iCveUniMed").equals(""))
			iCveUniMed = request.getParameter("iCveUniMed").toString();
		if (request.getParameter("iCveSolicitud") != null
				&& !request.getParameter("iCveSolicitud").equals(""))
			iCveSolicitud = request.getParameter("iCveSolicitud").toString();
		if (request.getParameter("iCveVehiculo") != null
				&& !request.getParameter("iCveVehiculo").equals(""))
			iCveVehiculo = Integer.parseInt(request
					.getParameter("iCveVehiculo").toString());

		try {
			if (!iAnio.equals("") && !iCveUniMed.equals("")
					&& !iCveSolicitud.equals("")) {
				String w = " where VehSolicitud.iAnio=" + iAnio
						+ " and VehSolicitud.iCveUniMed=" + iCveUniMed
						+ " and VehSolicitud.iCveSolicitud=" + iCveSolicitud;
				String sWhe = "";
				String sWhereqEgxS = "Where VehControlSolic.iAnio=" + iAnio
						+ " and VehControlSolic.iCveUniMed=" + iCveUniMed
						+ " and VehControlSolic.iCveSolicitud=" + iCveSolicitud;

				// Tabla de Datos del Solicitante y Vehiculo
				vSol = dSolicitud.FindDatosSolic(w, "");
				if (vSol.size() > 0) {
					tvSolicitud = (TVVEHSolicitud) vSol.get(0);
					vVeh = dVehiculo
							.FindByAllDetalleM(" Where VehVehiculo.iCveVehiculo="
									+ tvSolicitud.getICveVehiculo());
					iCveVehiculo = tvSolicitud.getICveVehiculo();

					if (vVeh.size() > 0) {
						tvVehiculo = new TVVEHVehiculo();
						tvVehiculo = (TVVEHVehiculo) vVeh.get(0);
					}
				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public BeanScroller getBeanSC() {
		return BeanSC;
	}
}
