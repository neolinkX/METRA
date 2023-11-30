package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TFechas;
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
 *      "JavaScript:alert('Consulte los archivos:\n-pg070702030CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070702030CFG.java.png'>
 */
public class pg070702030CFG extends CFGListBase2 {
	TVVEHMantenimiento vVEHMantProg;
	TVVEHMantenimiento vVEHMantNProg;
	Vector vMantProg = new Vector();
	Vector vMantNProg = new Vector();

	public pg070702030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/*
	 * Metodo para generar el Vector vDespliega.
	 */

	public void vDespliega() {
		TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
		TVVEHVehiculo vVEHVehiculo = new TVVEHVehiculo();
		boolean lWhere = false;
		try {
			if (request.getParameter("iCveTpoVehiculo") != null
					&& request.getParameter("iCveTpoVehiculo").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveTpoVehiculo").toString()
							.compareTo("null") != 0
					&& request.getParameter("iCveTpoVehiculo").toString()
							.compareTo("-1") != 0) {
				cCondicion += " AND VEHVehiculo.iCveTpoVehiculo = "
						+ request.getParameter("iCveTpoVehiculo");
			}

			if (request.getParameter("iCveMarca") != null
					&& request.getParameter("iCveMarca").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveMarca").toString()
							.compareTo("null") != 0
					&& request.getParameter("iCveMarca").toString()
							.compareTo("-1") != 0) {
				cCondicion += " AND VEHVehiculo.iCveMarca = "
						+ request.getParameter("iCveMarca");
			}

			if (request.getParameter("iCveTpoMantto2") != null
					&& request.getParameter("iCveTpoMantto2").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveTpoMantto2").toString()
							.compareTo("null") != 0
					&& request.getParameter("iCveTpoMantto2").toString()
							.compareTo("-1") != 0)
				vVEHVehiculo.setICveTpoMantto(new Integer(request
						.getParameter("iCveTpoMantto2")).intValue());

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("null") != 0
					&& request.getParameter("iCveUniMed").toString()
							.compareTo("-1") != 0)
				vVEHVehiculo.setICveUniMed(new Integer(request
						.getParameter("iCveUniMed")).intValue());
			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY VEHVehiculo.iCveVehiculo ";
			vDespliega = dVEHVehiculo.FindByMant(vVEHVehiculo, cCondicion,
					cOrden);
		} catch (Exception e) {
			error("vDespliega", e);
		}
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			if (cCondicion.compareTo("") != 0)
				cCondicion += "WHERE " + cCondicion
						+ " AND VEHVehiculo.lBaja = 0";
			else
				cCondicion = " WHERE VEHVehiculo.lBaja = 0  ";

			this.vDespliega();
			TVVEHVehiculo vVEHVehiculo = new TVVEHVehiculo();

			TDVEHMantenimiento dVEHMantenimiento = new TDVEHMantenimiento();
			TVVEHMantenimiento vVEHMantenimiento = new TVVEHMantenimiento();

			TVVEHSeguimiento vVEHSeguimiento = new TVVEHSeguimiento();
			TDVEHSeguimiento dVEHSeguimiento = new TDVEHSeguimiento();

			TFechas tFecha = new TFechas();
			String cFiltro = "";
			Vector vTemp = new Vector();
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").toString()
							.compareTo("Guardar") == 0) {
				if (vDespliega.size() > 0) {
					for (int i = 0; i < vDespliega.size(); i++) {
						vVEHVehiculo = (TVVEHVehiculo) vDespliega.get(i);
						if (request.getParameter("check"
								+ vVEHVehiculo.getICveVehiculo()) != null
								&& request
										.getParameter(
												"check"
														+ vVEHVehiculo
																.getICveVehiculo())
										.toString().compareTo("1") == 0) {
							vVEHMantProg = new TVVEHMantenimiento();
							vVEHMantNProg = new TVVEHMantenimiento();
							cFiltro = " WHERE M.iCveVehiculo = "
									+ vVEHVehiculo.getICveVehiculo()
									+ "   AND M.iCveTpoMantto = "
									+ request.getParameter("iCveTpoMantto")
									+ "   AND M.dtProgramado = '"
									+ tFecha.getDateSQL(request
											.getParameter("dtProgramada"))
									+ "'";
							vTemp = dVEHMantenimiento.FindByAll(cFiltro, "");
							if (vTemp.size() <= 0) { // Grabar
								vVEHMantenimiento.setICveVehiculo(vVEHVehiculo
										.getICveVehiculo());
								vVEHMantenimiento.setICveTpoMantto(new Integer(
										request.getParameter("iCveTpoMantto"))
										.intValue());
								vVEHMantenimiento.setICveEmpMantto(0);
								vVEHMantenimiento.setDtSolicitud(tFecha
										.getDateSQL(request
												.getParameter("dtSolicitud")));
								vVEHMantenimiento.setDtProgramado(tFecha
										.getDateSQL(request
												.getParameter("dtProgramada")));
								vVEHMantenimiento
										.setICveUsuSolicita(new Integer(request
												.getParameter("iCveUsuSesion"))
												.intValue());
								vVEHMantenimiento.setICveUsuAutoriza(0);
								vVEHMantenimiento.setICveUsuRecibe(0);
								String cMov = (String) dVEHMantenimiento
										.insert(null, vVEHMantenimiento);
								vVEHSeguimiento
										.setICveMantenimiento(new Integer(cMov)
												.intValue());
								vVEHSeguimiento.setICveVehiculo(vVEHVehiculo
										.getICveVehiculo());
								vVEHSeguimiento.setICveProceso(new Integer(
										request.getParameter("iCveProceso"))
										.intValue());
								vVEHSeguimiento.setICveEtapa(new Integer(
										request.getParameter("iCveEtapa"))
										.intValue());
								vVEHSeguimiento
										.setICveSolicitante(new Integer(
												request.getParameter("iCveSolicitante"))
												.intValue());
								vVEHSeguimiento.setDtSolicitud(tFecha
										.getDateSQL(request
												.getParameter("cToday")));
								vVEHSeguimiento.setICveUsuReg(new Integer(
										request.getParameter("iCveUsuSesion"))
										.intValue());
								vVEHSeguimiento.setICveUsuSolicita(new Integer(
										request.getParameter("iCveUsuSesion"))
										.intValue());
								vVEHSeguimiento.setCSolicitante("");
								vVEHSeguimiento.setCObservacion("");
								dVEHSeguimiento.insert(null, vVEHSeguimiento);
								vVEHMantProg.setICveVehiculo(vVEHVehiculo
										.getICveVehiculo()); // Almacenando
								vVEHMantProg.setCPlacas(vVEHVehiculo
										.getCPlacas());
								vVEHMantProg.setICveMantenimiento(new Integer(
										cMov).intValue());
								vVEHMantProg.setCDscTpoMantto(request
										.getParameter("cDscTpoMantto"));
								vVEHMantProg.setDtProgramado(tFecha
										.getDateSQL(request
												.getParameter("dtProgramada")));
								vMantProg.add(vVEHMantProg);
							} else { // Ya existe un Mantenimiento con los datos
										// proporcionados
								vVEHMantenimiento = (TVVEHMantenimiento) vTemp
										.get(0);
								vVEHMantNProg.setICveVehiculo(vVEHMantenimiento
										.getICveVehiculo());
								vVEHMantNProg
										.setICveMantenimiento(vVEHMantenimiento
												.getICveMantenimiento());
								vVEHMantNProg
										.setCDscTpoMantto(vVEHMantenimiento
												.getCDscTpoMantto());
								vVEHMantNProg.setDtProgramado(vVEHMantenimiento
										.getDtProgramado());
								vVEHMantNProg.setCPlacas(vVEHMantenimiento
										.getCPlacas());
								vMantNProg.add(vVEHMantNProg);
							}
						}
					}
				}
				this.vDespliega();
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public Object getMantProg() {
		return vMantProg;
	}

	public Object getNMantProg() {
		return vMantNProg;
	}
}
