package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuraci�n para Generales - Cat�logo de Procesos
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071007031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071007031CFG.png'>
 */
public class pg071007031CFG extends CFGCatBase2 {
	public pg071007031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071007030.jsp|";
	}

	/**
	 * M�todo Guardar
	 */
	public void Guardar() {
		TDEXPRefConcepto dEXPRefConcepto = new TDEXPRefConcepto();
		try {
			cClave = (String) dEXPRefConcepto.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // M�todo Guardar

	/**
	 * M�todo GuardarA
	 */
	public void GuardarA() {
		TDEXPRefConcepto dEXPRefConcepto = new TDEXPRefConcepto();
		try {
			cClave = (String) dEXPRefConcepto.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // M�todo GuardarA

	/**
	 * M�todo Borrar
	 */
	public void Borrar() {
		try {
			cClave = "ERROR";
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			if (cClave.compareTo("ERROR") == 0) {
				vErrores.acumulaError("Funcion No Disponible: ", 0,
						"No es posible borrar ejercicios");
			}
			// super.Borrar();
		}
	} // M�todo Borrar

	/**
	 * M�todo FillVector
	 */
	public void fillVector() {
		TDEXPRefConcepto dEXPRefConcepto = new TDEXPRefConcepto();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (cCondicion.compareTo("") != 0)
				cWhere = " and " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by EXPRefConcepto.iEjercicio desc ";

			vDespliega = dEXPRefConcepto.FindByAllCon(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * M�todo fillPK
	 */
	public void fillPK() {
		mPk.add(cActual);
	}

	/**
	 * M�todo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVEXPRefConcepto vEXPRefConcepto = new TVEXPRefConcepto();
		try {
			cCampo = "" + request.getParameter("iEjercicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPRefConcepto.setIEjercicio(iCampo);

			cCampo = "" + request.getParameter("iEjercicio2");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPRefConcepto.setIEjercicio2(iCampo);

			cCampo = "" + request.getParameter("iCveConcepto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPRefConcepto.setICveConcepto(iCampo);

			cCampo = "" + request.getParameter("iCveConcepto2");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPRefConcepto.setICveConcepto2(iCampo);

			cCampo = "" + request.getParameter("iRefNum");
			System.out.println("iRefNum = " + cCampo);
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPRefConcepto.setIRefNumerica(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPRefConcepto;
	}
}