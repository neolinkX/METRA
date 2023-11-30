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
public class pg070703022CFG extends CFGListBase2 {
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

	public pg070703022CFG() {
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
		TVVEHControlSolic tvCtrol = new TVVEHControlSolic();
		TDVEHControlSolic dCtrol = new TDVEHControlSolic();
		TDVEHVehiculo dVeh = new TDVEHVehiculo();
		TVVEHVehiculo tvVehiculo = new TVVEHVehiculo();
		TDVEHSolicitud dSolic = new TDVEHSolicitud();
		TVVEHSolicitud tvSolicitud = new TVVEHSolicitud();
		TDVEHEtapaXSolic dEtapXSol = new TDVEHEtapaXSolic();
		TVVEHEtapaXSolic tvEtapaxSol = new TVVEHEtapaXSolic();
		int iAnio = 0;
		int iCveUniMed = 0;
		int iCveSolicitud = 0;
		int iCveEtapaSolic = 0;
		int iCveConfControl = 0;
		int iCveUsuReg = 0;
		int dValorIni = 0;
		int dValorFin = 0;
		int lLogico = 0;
		int num_reg = 0;
		int iCveVehiculo = 0;
		int iKmInicial = 0;
		String cCaracter = "";
		TFechas hoy = new TFechas();
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
			if (request.getParameter("iCveEtapaSolic") != null
					&& !request.getParameter("iCveEtapaSolic").equals(""))
				iCveEtapaSolic = Integer.parseInt(request.getParameter(
						"iCveEtapaSolic").toString());
			if (request.getParameter("num_reg") != null
					&& !request.getParameter("num_reg").equals(""))
				num_reg = Integer.parseInt(request.getParameter("num_reg")
						.toString());
			if (request.getParameter("iCveVehiculo") != null
					&& !request.getParameter("iCveVehiculo").equals(""))
				iCveVehiculo = Integer.parseInt(request.getParameter(
						"iCveVehiculo").toString());
			if (request.getParameter("iKmInicial") != null
					&& !request.getParameter("iKmInicial").equals(""))
				iKmInicial = Integer.parseInt(request
						.getParameter("iKmInicial").toString());
			if (request.getParameter("iCveUsuReg") != null
					&& !request.getParameter("iCveUsuReg").equals(""))
				iCveUsuReg = Integer.parseInt(request
						.getParameter("iCveUsuReg").toString());

			tvCtrol.setIAnio(iAnio);
			tvCtrol.setICveUniMed(iCveUniMed);
			tvCtrol.setICveSolicitud(iCveSolicitud);
			tvCtrol.setICveEtapaSolic(iCveEtapaSolic);
			String tipo = "";

			// Actualizacion EtapaXSolic
			tvEtapaxSol.setIAnio(iAnio);
			tvEtapaxSol.setICveUniMed(iCveUniMed);
			tvEtapaxSol.setICveSolicitud(iCveSolicitud);
			tvEtapaxSol.setICveEtapaSolic(iCveEtapaSolic);
			tvEtapaxSol.setDtRegistro(hoy.TodaySQL());
			tvEtapaxSol.setICveUsuRegistra(iCveUsuReg);
			dEtapXSol.insert(conn, tvEtapaxSol);

			// Actualizacion ControlSolic
			for (int i = 0; i < num_reg; i++) {
				tipo = request.getParameter("campo" + i);
				if (request.getParameter("iCveConfControl" + i) != null
						&& !request.getParameter("iCveConfControl" + i).equals(
								""))
					iCveConfControl = Integer.parseInt(request.getParameter(
							"iCveConfControl" + i).toString());
				tvCtrol.setICveConfControl(iCveConfControl);

				if (tipo.equals("1")) {
					if (request.getParameter("control" + i) != null
							&& !request.getParameter("control" + i).equals(""))
						tvCtrol.setLLogico(Integer.parseInt(request
								.getParameter("control" + i)));
				}
				if (tipo.equals("2")) {
					if (request.getParameter("control" + i) != null
							&& !request.getParameter("control" + i).equals(""))
						tvCtrol.setDValorIni(Float.parseFloat(request
								.getParameter("control" + i)));
				}
				if (tipo.equals("3")) {
					if (request.getParameter("dRango1") != null
							&& !request.getParameter("dRango1").equals(""))
						tvCtrol.setDValorIni(Float.parseFloat(request
								.getParameter("dRango1")));
					if (request.getParameter("dRango2") != null
							&& !request.getParameter("dRango2").equals(""))
						tvCtrol.setDValorFin(Float.parseFloat(request
								.getParameter("dRango2")));
				}
				if (tipo.equals("4")) {
					if (request.getParameter("control" + i) != null
							&& !request.getParameter("control" + i).equals(""))
						tvCtrol.setCCaracter(request
								.getParameter("control" + i));
				}
				dCtrol.insert(conn, tvCtrol);
			}

			// Actualizaci�n del Kilometraje
			if (request.getParameter("grabaPrimeraEtapa") != null
					&& request.getParameter("grabaPrimeraEtapa").equals("1")) {
				tvVehiculo.setICveVehiculo(iCveVehiculo);
				tvVehiculo.setIKmFinal(iKmInicial);
				dVeh.updateRevision(conn, tvVehiculo);

				tvSolicitud.setIAnio(iAnio);
				tvSolicitud.setICveUniMed(iCveUniMed);
				tvSolicitud.setICveSolicitud(iCveSolicitud);
				tvSolicitud.setIKmInicial(iKmInicial);
				dSolic.updateRevisionInicial(conn, tvSolicitud);
			}
			if (request.getParameter("grabaUltimaEtapa") != null
					&& request.getParameter("grabaUltimaEtapa").equals("1")) {
				tvVehiculo.setICveVehiculo(iCveVehiculo);
				tvVehiculo.setIKmFinal(iKmInicial);
				dVeh.updateRevision(conn, tvVehiculo);

				tvSolicitud.setIAnio(iAnio);
				tvSolicitud.setICveUniMed(iCveUniMed);
				tvSolicitud.setICveSolicitud(iCveSolicitud);
				tvSolicitud.setIKmFinal(iKmInicial);
				tvSolicitud.setDtEntrega(hoy.TodaySQL());
				dSolic.updateRevisionFinal(conn, tvSolicitud);
			}
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

				// Combo de Etapas
				vEtapas = dCtrol.FindByAllEtapasxSol();

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

				// Datos Para la Siguiente Etapa
				vEtapaAGuadar = dCtrol.FindEtapaSiguiente(sWhereqEgxS);
				siguienteEtapa = dCtrol.siguienteEtapa;
				grabaPrimeraEtapa = dCtrol.grabaPrimeraEtapa;
				grabaUltimaEtapa = dCtrol.grabaUltimaEtapa;
				sWhe = "Where VehControlSolic.iAnio=" + iAnio
						+ " and VehControlSolic.iCveUniMed=" + iCveUniMed
						+ " and VehControlSolic.iCveSolicitud=" + iCveSolicitud;
				vDespliega = dCtrol.FindByAllEtapasAnteriores(sWhe);
				if ((vDespliega != null) && (!vDespliega.isEmpty())) {
					this.BeanSC = new BeanScroller(vDespliega);
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