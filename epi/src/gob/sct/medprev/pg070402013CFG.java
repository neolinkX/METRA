package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG del cat�logo de veh�culos involucrados
 * en el accidente
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Jaime Enrique Su�rez Romero
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070402013CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070402013CFG.png'>
 */
public class pg070402013CFG extends CFGCatBase2 {
	String cAnio = "", cCveMdoTrans = "", cIDDGPMPT = "", cCveVehiculo = "";

	public pg070402013CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070402012.jsp";
	}

	public void mainBlock() {
		cAnio = "" + request.getParameter("iAnioSel");
		cCveMdoTrans = "" + request.getParameter("hdiCveMdoTrans");
		cIDDGPMPT = "" + request.getParameter("hdiIDDGPMPT");
		cCveVehiculo = "" + request.getParameter("hdiCveVehiculo");
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDINVRegVehic dINVRegVehic = new TDINVRegVehic();
		try {
			TVINVRegVehic vINVRegVehic = (TVINVRegVehic) dINVRegVehic.insert(
					null, this.getInputs());
			cAnio = "" + vINVRegVehic.getIAnio();
			cCveMdoTrans = "" + vINVRegVehic.getICveMdoTrans();
			cIDDGPMPT = "" + vINVRegVehic.getIIDDGPMPT();
			cCveVehiculo = "" + vINVRegVehic.getICveVehiculo();
			if (vINVRegVehic.getICveVehiculo() == -1) {
				vErrores.acumulaError("", 0,
						"No es posible exceder el n�mero de veh�culos registrados!");
			}
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
		TDINVRegVehic dINVRegVehic = new TDINVRegVehic();
		try {
			cClave = (String) dINVRegVehic.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDINVRegVehic dINVRegVehic = new TDINVRegVehic();
		try {
			cClave = (String) dINVRegVehic.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDINVRegVehic dINVRegVehic = new TDINVRegVehic();
		try {
			String cBusca = " where INVRegVehic.iAnio = "
					+ request.getParameter("iAnioSel")
					+ " and INVRegVehic.iCveMdoTrans = "
					+ request.getParameter("iCveMdoTransSel")
					+ " and INVRegVehic.iIDDGPMPT = "
					+ request.getParameter("hdiIDDGPMPT");

			if (!cCondicion.equals("")) {
				cBusca = cBusca + " and " + cCondicion;
			}
			if (!cOrden.equals("")) {
				cBusca = cBusca + cOrden;
			}

			vDespliega = dINVRegVehic.FindByAll(cBusca);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(cAnio);
		mPk.add(cCveMdoTrans);
		mPk.add(cIDDGPMPT);
		mPk.add(cCveVehiculo);
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
		TVINVRegVehic vINVRegVehic = new TVINVRegVehic();
		try {
			cCampo = "" + request.getParameter("iAnioSel");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTransSel");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("hdiIDDGPMPT");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setIIDDGPMPT(iCampo);

			cCampo = "" + request.getParameter("hdiCveVehiculo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setICveVehiculo(iCampo);

			cCampo = "" + request.getParameter("cMatricula");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vINVRegVehic.setCMatricula(cCampo);

			cCampo = "" + request.getParameter("cPropietario");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vINVRegVehic.setCPropietario(cCampo);

			cCampo = "" + request.getParameter("iCveEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setICveEmpresa(iCampo);

			cCampo = "" + request.getParameter("iCveServPrestado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setICveServPrestado(iCampo);

			cCampo = "" + request.getParameter("cOrigen");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vINVRegVehic.setCOrigen(cCampo);

			cCampo = "" + request.getParameter("cDestino");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vINVRegVehic.setCDestino(cCampo);

			cCampo = "" + request.getParameter("iPerFedInvolucra");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setIPerFedInvolucra(iCampo);

			cCampo = "" + request.getParameter("iPerEdoInvolucra");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setIPerEdoInvolucra(iCampo);

			cCampo = "" + request.getParameter("iPerPartInvolucra");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setIPerPartInvolucra(iCampo);

			cCampo = "" + request.getParameter("iCvePaisOr");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setICvePaisOr(iCampo);

			cCampo = "" + request.getParameter("iCveEdoOr");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setICveEdoOr(iCampo);

			cCampo = "" + request.getParameter("iCvePaisDest");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setICvePaisDest(iCampo);

			cCampo = "" + request.getParameter("iCveEdoDest");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegVehic.setICveEdoDest(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vINVRegVehic;
	}
}