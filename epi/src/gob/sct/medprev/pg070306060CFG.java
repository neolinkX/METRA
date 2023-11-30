package gob.sct.medprev;

import java.util.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;
import com.micper.sql.*;

/**
 * * Clase de configuracion para CFG de la pagina pg070306060
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070306060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070306060CFG.png'>
 */
public class pg070306060CFG extends CFGListBase2 {
	public pg070306060CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		UpdStatus = "Add";
	}

	public void Nuevo() {
		super.Nuevo();
		UpdStatus = "SaveCancel";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		String cFiltro = "";
		int iTotalReg = 0;
		int iError = 0;
		try {
			TDTOXRefrigerador dRefrigerador = new TDTOXRefrigerador();
			TVTOXRefrigerador vRefrigerador = new TVTOXRefrigerador();
			// Desplegar al inicio
			if (request.getParameter("hdBoton").compareTo("Primero") == 0) {
				if (request.getParameter("iCveArea") != null
						&& request.getParameter("iCveArea").toString()
								.compareTo("") != 0) {
					vRefrigerador.setICveArea(Integer.parseInt(request
							.getParameter("iCveArea")));
				}
				if (request.getParameter("iCveTurnoValida") != null
						&& request.getParameter("iCveTurnoValida").toString()
								.compareTo("") != 0) {
					vRefrigerador.setICveTurnoValida(Integer.parseInt(request
							.getParameter("iCveTurnoValida")));
				}
				if (request.getParameter("dtFechaActual") != null
						&& request.getParameter("dtFechaActual").toString()
								.compareTo("") != 0) {
					StringTokenizer strtkFechaTemp = new StringTokenizer(""
							+ request.getParameter("dtFechaActual"), "/");
					while (strtkFechaTemp.hasMoreTokens()) {
						vRefrigerador.setIDia(Integer.parseInt(strtkFechaTemp
								.nextToken()));
						vRefrigerador.setIMes(Integer.parseInt(strtkFechaTemp
								.nextToken()));
						vRefrigerador.setIAnio(Integer.parseInt(strtkFechaTemp
								.nextToken()));
					}
				}
				if (cCondicion.compareTo("") != 0) {
					cFiltro += " and " + cCondicion;
				}
				if (cOrden.compareTo("") != 0) {
					cFiltro += cOrden;
				}
				vDespliega = dRefrigerador.FindByAll2(vRefrigerador, cFiltro);
				iTotalReg = vDespliega.size();
			}

			// Desplegar en nuevo
			if (request.getParameter("hdBoton").compareTo("Nuevo") == 0) {

				Vector vcRegistrado = new Vector();
				TDTOXTurnoRef dTurnoRef = new TDTOXTurnoRef();
				TVTOXTurnoRef vTurnoRef = new TVTOXTurnoRef();
				/*
				 * GregorianCalendar dtFechaActual = new GregorianCalendar();
				 * vTurnoRef.setIAnio(dtFechaActual.get(Calendar.YEAR));
				 * vTurnoRef.setIMes(dtFechaActual.get(Calendar.MONTH) + 1);
				 * vTurnoRef.setIDia(dtFechaActual.get(Calendar.DAY_OF_MONTH));
				 */

				TFechas dtFechaActual = new TFechas();
				java.sql.Date dtDespliegue;

				dtDespliegue = dtFechaActual.getDateSQL(request
						.getParameter("dtFechaActual"));

				vTurnoRef.setIAnio(dtFechaActual.getIntYear(dtDespliegue));
				vTurnoRef.setIMes(dtFechaActual.getIntMonth(dtDespliegue));
				vTurnoRef.setIDia(dtFechaActual.getIntDay(dtDespliegue));

				String cWhere = " where iAnio = " + vTurnoRef.getIAnio()
						+ " and iCveTurnoValida = "
						+ request.getParameter("iCveTurnoValida")
						+ " and iMes = " + vTurnoRef.getIMes()
						+ " and iDia =  " + vTurnoRef.getIDia();

				vcRegistrado = dTurnoRef.FindByAll(cWhere);
				if (vcRegistrado.size() == 0) {

					Nuevo();
					if (request.getParameter("iCveArea") != null
							&& request.getParameter("iCveArea").toString()
									.compareTo("") != 0) {
						vRefrigerador.setICveArea(Integer.parseInt(request
								.getParameter("iCveArea")));
					}
					if (request.getParameter("iCveTurnoValida") != null
							&& request.getParameter("iCveTurnoValida")
									.toString().compareTo("") != 0) {
						vRefrigerador.setICveTurnoValida(Integer
								.parseInt(request
										.getParameter("iCveTurnoValida")));
					}
					if (cCondicion.compareTo("") != 0) {
						cFiltro += " and " + cCondicion;
					}
					if (cOrden.compareTo("") != 0) {
						cFiltro += cOrden;
					}
					vDespliega = dRefrigerador.FindByAll3(vRefrigerador,
							cFiltro);
					iTotalReg = vDespliega.size();
				} else {
					iError = 1;
				}
			}

			// Insertar registros

			if (request.getParameter("hdBoton").compareTo("no") == 0) {
				Vector vcRegistrado = new Vector();
				String cWhere = "";

				// GregorianCalendar dtFechaActual = new GregorianCalendar();
				TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
						.getAttribute("UsrID");
				TVTOXTurnoRef vTurnoRef = new TVTOXTurnoRef();
				TDTOXTurnoRef dTurnoRef = new TDTOXTurnoRef();

				TFechas dtFechaActual = new TFechas();
				java.sql.Date dtDespliegue;

				dtDespliegue = dtFechaActual.getDateSQL(request
						.getParameter("dtFechaActual"));

				vTurnoRef.setIAnio(dtFechaActual.getIntYear(dtDespliegue));
				vTurnoRef.setIMes(dtFechaActual.getIntMonth(dtDespliegue));
				vTurnoRef.setIDia(dtFechaActual.getIntDay(dtDespliegue));

				vTurnoRef.setICveUsuRespon(vUsuario.getICveusuario());
				vTurnoRef.setICveTurnoValida(Integer.parseInt(request
						.getParameter("iCveTurnoValida")));

				vTurnoRef.setDTempAmbiente(Float.parseFloat(request
						.getParameter("dTempAmbiente")));
				vTurnoRef.setDHumedad(Float.parseFloat(request
						.getParameter("dHumedad")));

				cWhere = " where iAnio = " + vTurnoRef.getIAnio()
						+ " and iCveTurnoValida = "
						+ vTurnoRef.getICveTurnoValida() + " and iMes = "
						+ vTurnoRef.getIMes() + " and iDia =  "
						+ vTurnoRef.getIDia();

				vcRegistrado = dTurnoRef.FindByAll(cWhere);

				if (vcRegistrado.size() == 0) {
					dTurnoRef.insert(null, vTurnoRef);

					TVTOXTemperRefr vTemperRefr = new TVTOXTemperRefr();
					TDTOXTemperRefr dTemperRefr = new TDTOXTemperRefr();

					for (int i = 1; i <= Integer.parseInt(request
							.getParameter("hdTotalReg")); i++) {
						if (request.getParameter("dTemp" + i) != null
								&& request.getParameter("dTemp" + i)
										.compareToIgnoreCase("") != 0) {
							vTemperRefr.setICveTurnoRef(vTurnoRef
									.getICveTurnoRef());
							vTemperRefr.setIAnio(dtFechaActual
									.getIntYear(dtDespliegue));
							vTemperRefr
									.setICveRefrigerador(Integer.parseInt(request
											.getParameter("hdRefrigerador" + i)));
							vTemperRefr.setDTemperatura((new Float(request
									.getParameter("dTemp" + i).toString()))
									.floatValue());
							dTemperRefr.insert(null, vTemperRefr);
						}
					}
				}
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		} finally {
			if (iError == 1) {
				vErrores.acumulaError("Registro Existente: ", 0,
						"El Registro ya ha sido asignado anteriormente.");
			}
			// super.fillVector();
		}

	}

}
