package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Clase para el control de la Informaci�n de la
 * tabla VEHSolicitud
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
 *      "JavaScript:alert('Consulte los archivos:\n-pg070703010CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070703010CFG.java.png'>
 */
public class pg070703010CFG extends CFGCatBase2 {
	private int iCveUsrSesion;

	public pg070703010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDVEHSolicitud dVEHSolicitud = new TDVEHSolicitud();
		try {
			cClave = (String) dVEHSolicitud.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDVEHSolicitud dVEHSolicitud = new TDVEHSolicitud();
		try {
			TVVEHSolicitud VSolic, vVEHSolicitud = (TVVEHSolicitud) this
					.getInputs();
			Vector vSolic = dVEHSolicitud.FindSolicXUsr2(
					" WHERE VEHSolicitud.iAnio = " + vVEHSolicitud.getIAnio()
							+ "  AND VEHSolicitud.iCveUniMed = "
							+ vVEHSolicitud.getICveUniMed()
							+ "  AND VEHSolicitud.iCveSolicitud = "
							+ vVEHSolicitud.getICveSolicitud(), "");
			if (vSolic != null && vSolic.size() > 0) {
				VSolic = (TVVEHSolicitud) vSolic.get(0);
				VSolic.setICveUsuConductor(vVEHSolicitud.getICveUsuConductor());
				VSolic.setCLicencia(vVEHSolicitud.getCLicencia());
				VSolic.setLLicPermanente(vVEHSolicitud.getLLicPermanente());
				VSolic.setDtVenceLic(vVEHSolicitud.getDtVenceLic());
				VSolic.setICveMotivo(vVEHSolicitud.getICveMotivo());
				VSolic.setCDestino(vVEHSolicitud.getCDestino());
				VSolic.setICveTpoVehiculo(vVEHSolicitud.getICveTpoVehiculo());
				VSolic.setITmpAsignado(vVEHSolicitud.getITmpAsignado());
				VSolic.setDtSolicitud(vVEHSolicitud.getDtSolicitud());
				VSolic.setTsSolicitud(vVEHSolicitud.getTsSolicitud());
			} else
				VSolic = vVEHSolicitud;
			cClave = (String) dVEHSolicitud.update(null, VSolic);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo Cancelar
	 */
	public void Borrar() {
		TDVEHSolicitud dVEHSolicitud = new TDVEHSolicitud();
		try {
			cClave = (String) dVEHSolicitud.cancel(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al cancelar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDVEHSolicitud dVEHSolicitud = new TDVEHSolicitud();
		try {
			if (cClave.compareTo("") == 0 || cClave.compareTo("null") == 0)
				cClave = "0";
			if (cCondicion.compareTo("") != 0)
				cCondicion = " WHERE " + cCondicion
						+ " AND VEHSolicitud.iCveSolicitud = "
						+ new Integer(cClave).intValue();
			// else
			// cCondicion = " WHERE VEHSolicitud.iCveSolicitud = " + new
			// Integer(cClave).intValue();
			cCondicion += cCondicion.equals("") ? " WHERE " : " AND ";
			cCondicion += " VEHSolicitud.iCveUsuSolic = " + iCveUsrSesion + " ";
			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY VEHSolicitud.iAnio,VEHSolicitud.iCveSolicitud ";
			vDespliega = dVEHSolicitud.FindSolicXUsr2(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(request.getParameter("iAnio"));
		mPk.add(request.getParameter("iCveUniMed"));
		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVVEHSolicitud vVEHSolicitud = new TVVEHSolicitud();
		try {
			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveSolicitud");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveSolicitud(iCampo);

			cCampo = "" + request.getParameter("dtRegistro");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHSolicitud.setDtRegistro(dtCampo);
			int idia = 0, imes = 0, ianio = 0;
			cCampo = "" + request.getParameter("dtSolicitud");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
				idia = tfCampo.getIntDay(dtCampo);
				imes = tfCampo.getIntMonth(dtCampo);
				ianio = tfCampo.getIntYear(dtCampo);
			} else {
				dtCampo = null;
			}
			vVEHSolicitud.setDtSolicitud(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuSolic");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveUsuSolic(iCampo);

			cCampo = "" + request.getParameter("iCveUsuConductor");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveUsuConductor(iCampo);
			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveModulo(iCampo);

			cCampo = "" + request.getParameter("iCveArea");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveArea(iCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveMotivo(iCampo);

			cCampo = "" + request.getParameter("cDestino");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHSolicitud.setCDestino(cCampo);

			cCampo = "" + request.getParameter("cLicencia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHSolicitud.setCLicencia(cCampo);
			cCampo = "" + request.getParameter("lLicPermanente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setLLicPermanente(iCampo);

			cCampo = "" + request.getParameter("dtVenceLic");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHSolicitud.setDtVenceLic(dtCampo);

			int idias = 0;
			int ihoras = 0;
			int itotalhrs = 0;
			if (request.getParameter("idias") != null
					&& request.getParameter("idias").toString().compareTo("") != 0
					&& request.getParameter("idias").toString()
							.compareTo("null") != 0)
				idias = new Integer(request.getParameter("idias").toString())
						.intValue();

			if (request.getParameter("ihoras") != null
					&& request.getParameter("ihoras").toString().compareTo("") != 0
					&& request.getParameter("ihoras").toString()
							.compareTo("null") != 0)
				ihoras = new Integer(request.getParameter("ihoras").toString())
						.intValue();

			itotalhrs = (idias * 24) + ihoras;

			vVEHSolicitud.setITmpAsignado(itotalhrs);

			cCampo = "" + request.getParameter("iCveTpoVehiculo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveTpoVehiculo(iCampo);

			cCampo = "" + request.getParameter("dtAsignado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHSolicitud.setDtAsignado(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuAsigna");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveUsuAsigna(iCampo);

			cCampo = "" + request.getParameter("lAsignado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setLAsignado(iCampo);

			cCampo = "" + request.getParameter("dtEntrega");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHSolicitud.setDtEntrega(dtCampo);

			cCampo = "" + request.getParameter("dtCancelacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHSolicitud.setDtCancelacion(dtCampo);

			cCampo = "" + request.getParameter("iCveVehiculo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setICveVehiculo(iCampo);

			cCampo = "" + request.getParameter("iKmInicial");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setIKmInicial(iCampo);

			cCampo = "" + request.getParameter("iKmFinal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHSolicitud.setIKmFinal(iCampo);

			GregorianCalendar gTime = new GregorianCalendar();
			String cTmp = request.getParameter("iMinutos") != null ? request
					.getParameter("iMinutos") : "";
			int imin = cTmp.length() != 0 ? Integer.parseInt(cTmp) : 0;
			cTmp = request.getParameter("iHoras") != null ? request
					.getParameter("iHoras") : "";
			int ihora = cTmp.length() != 0 ? Integer.parseInt(cTmp) : 0;
			gTime.set(ianio, imes - 1, idia, ihora, imin, 0);
			vVEHSolicitud.setTsSolicitud(new java.sql.Timestamp(gTime
					.getTimeInMillis()));
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vVEHSolicitud;
	}

	public void setICveUsrSesion(int iCveUsrSesion) {
		this.iCveUsrSesion = iCveUsrSesion;
	}
}
