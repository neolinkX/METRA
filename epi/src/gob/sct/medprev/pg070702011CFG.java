package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Clase para la Administraci�n de la
 * Informaci�n de la tabla VEHVehiculo
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
 *      "JavaScript:alert('Consulte los archivos:\n-pg070702011CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070702011CFG.java.png'>
 */
public class pg070702011CFG extends CFGCatBase2 {
	public pg070702011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070702010.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
		try {
			cClave = (String) dVEHVehiculo.insert(null, this.getInputs());
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
		TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
		try {
			cClave = (String) dVEHVehiculo.update(null, this.getInputs());
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
		TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
		try {
			cClave = (String) dVEHVehiculo.delete(null, this.getInputs());
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
		TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
		boolean lWhere = false;
		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}
			if (request.getParameter("hdVehiculo") != null
					&& request.getParameter("hdVehiculo").toString()
							.compareTo("null") != 0
					&& request.getParameter("hdVehiculo").toString()
							.compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND VEHVehiculo.iCveVehiculo = "
							+ request.getParameter("hdVehiculo");
				else
					cCondicion = " WHERE VEHVehiculo.iCveVehiculo = "
							+ request.getParameter("hdVehiculo");
			}

			if (cOrden.compareTo("") == 0)
				cOrden = " ORDER BY VEHVehiculo.iCveVehiculo";
			vDespliega = dVEHVehiculo.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
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
		TVVEHVehiculo vVEHVehiculo = new TVVEHVehiculo();
		try {
			cCampo = "" + request.getParameter("iCveVehiculo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setICveVehiculo(iCampo);

			cCampo = "" + request.getParameter("iCveTpoVehiculo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setICveTpoVehiculo(iCampo);

			cCampo = "" + request.getParameter("iCveMarca");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setICveMarca(iCampo);

			cCampo = "" + request.getParameter("iCveModelo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setICveModelo(iCampo);

			cCampo = "" + request.getParameter("iAnioVeh");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setIAnioVeh(iCampo);

			cCampo = "" + request.getParameter("cNumSerie");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHVehiculo.setCNumSerie(cCampo);

			cCampo = "" + request.getParameter("cNumMotor");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHVehiculo.setCNumMotor(cCampo);

			cCampo = "" + request.getParameter("cInventario");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHVehiculo.setCInventario(cCampo);

			cCampo = "" + request.getParameter("iCveColor");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setICveColor(iCampo);

			cCampo = "" + request.getParameter("cPlacas");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHVehiculo.setCPlacas(cCampo);

			cCampo = "" + request.getParameter("iKmMantto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setIKmMantto(iCampo);

			cCampo = "" + request.getParameter("iKmFinal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setIKmFinal(iCampo);

			cCampo = "" + request.getParameter("iMesMantto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setIMesMantto(iCampo);

			cCampo = "" + request.getParameter("cPoliza");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHVehiculo.setCPoliza(cCampo);

			cCampo = "" + request.getParameter("dtInicioVig");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHVehiculo.setDtInicioVig(dtCampo);

			cCampo = "" + request.getParameter("dtFinVig");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHVehiculo.setDtFinVig(dtCampo);

			cCampo = "" + request.getParameter("dtAlta");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHVehiculo.setDtAlta(dtCampo);

			cCampo = "" + request.getParameter("dtIniMantto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHVehiculo.setDtIniMantto(dtCampo);

			cCampo = "" + request.getParameter("iCveEstadoVEH");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setICveEstadoVEH(iCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setLActivo(iCampo);

			cCampo = "" + request.getParameter("lBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setLBaja(iCampo);

			cCampo = "" + request.getParameter("dtBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vVEHVehiculo.setDtBaja(dtCampo);

			cCampo = "" + request.getParameter("iCveMtvoBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setICveMtvoBaja(iCampo);

			cCampo = "" + request.getParameter("iKmGarantia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setIKmGarantia(iCampo);

			cCampo = "" + request.getParameter("iMesGarantia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vVEHVehiculo.setIMesGarantia(iCampo);

			cCampo = "" + request.getParameter("cCobertura");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHVehiculo.setCCobertura(cCampo);

			cCampo = "" + request.getParameter("cAseguradora");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vVEHVehiculo.setCAseguradora(cCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vVEHVehiculo;
	}
}
