package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Control al Transporte - Periodos/Pla
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070501071CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070501071CFG.png'>
 */
public class pg070501071CFG extends CFGCatBase2 {
	public pg070501071CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070501070.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDCTRPeriodPla dCTRPeriodPla = new TDCTRPeriodPla();
		try {
			cClave = (String) dCTRPeriodPla.insert(null, this.getInputs());
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
		TDCTRPeriodPla dCTRPeriodPla = new TDCTRPeriodPla();
		try {
			cClave = (String) dCTRPeriodPla.update(null, this.getInputs());
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
		TDCTRPeriodPla dCTRPeriodPla = new TDCTRPeriodPla();
		try {
			cClave = (String) dCTRPeriodPla.disable(null, this.getInputs());
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
		TDCTRPeriodPla dCTRPeriodPla = new TDCTRPeriodPla();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iAnio") != null)
				cWhere += " where iAnio = " + request.getParameter("iAnio")
						+ " ";
			else if (request.getParameter("hdIAnio") != null)
				cWhere += " where iAnio = " + request.getParameter("hdIAnio")
						+ " ";

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion + " ";
				else
					cWhere += cCondicion + " ";
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else
				cOrderBy = "order by iCvePeriodPla";

			if (request.getParameter("iAnio") != null
					|| request.getParameter("hdIAnio") != null)
				vDespliega = dCTRPeriodPla.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		if (request.getParameter("hdCampoClave1") != null
				&& request.getParameter("hdCampoClave2") != null
				&& request.getParameter("hdCampoClave1").compareTo("") != 0
				&& request.getParameter("hdCampoClave2").compareTo("") != 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
		}
		if (request.getParameter("hdBoton").compareTo("Guardar") == 0) {
			mPk.add(request.getParameter("hdIAnio"));
			mPk.add(cActual);
		}
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
		TVCTRPeriodPla vCTRPeriodPla = new TVCTRPeriodPla();
		try {

			if (request.getParameter("hdBoton").compareTo("Borrar") == 0)
				cCampo = "" + request.getParameter("iAnio");
			else
				cCampo = "" + request.getParameter("hdIAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPeriodPla.setiAnio(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPeriodPla.setiCvePeriodPla(iCampo);

			cCampo = "" + request.getParameter("iCveTpoPlantilla");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPeriodPla.setiCveTpoPlantilla(iCampo);

			cCampo = "" + request.getParameter("cDscPeriodPla");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPeriodPla.setcDscPeriodPla(cCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPeriodPla.setcObservacion(cCampo);

			cCampo = "" + request.getParameter("dtGeneracion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCTRPeriodPla.setdtGeneracion(dtCampo);

			cCampo = "" + request.getParameter("dtVencimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCTRPeriodPla.setdtVencimiento(dtCampo);

			if (request.getParameter("chklActivo") == null) {
				cCampo = "0";
			} else {
				cCampo = "1";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPeriodPla.setlActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vCTRPeriodPla;
	}
}