package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para CFG Envio de Laboratorio
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. Gonz�lez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070302030CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070302030CFG.png'>
 */
public class pg070402020CFG extends CFGCatBase2 {

	// private Vector vcMuestrasApi = null;
	// private Vector vcMuestrasCan = null;
	private Vector vcINVPersonal = null;
	private int iNoPersonal = 0;

	public pg070402020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		UpdStatus = "Hidden";
		cPaginas = "pg070402010.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {

		TDINVRegistro dRegistro = new TDINVRegistro();

		try {
			dRegistro.updateUniMed(null, (TVINVRegistro) this.getInputs());

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
		TDEnvio dEnvio = new TDEnvio();
		try {
			cClave = (String) dEnvio.update(null, this.getInputs());
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
		TDEnvio dEnvio = new TDEnvio();
		try {
			cClave = (String) dEnvio.delete(null, this.getInputs());
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
		TDEnvio dEnvio = new TDEnvio();
		String cWhere = "";
		String cWhereA = "";
		Vector vcInvRegistro = new Vector();
		UpdStatus = "Hidden";
		int iUniMed = 0;
		try {

			TDINVRegistro dINVRegistro = new TDINVRegistro();
			TVINVRegistro vINVRegistro = new TVINVRegistro();

			cWhereA = " a.iAnio = " + request.getParameter("hdiAnio")
					+ " and a.iCveMdoTrans = "
					+ request.getParameter("hdiCveMdoTrans")
					+ " and a.iIDDGPMPT = "
					+ request.getParameter("hdiIdefMedPrev");

			cWhere = " iAnio = " + request.getParameter("hdiAnio")
					+ " and iCveMdoTrans = "
					+ request.getParameter("hdiCveMdoTrans")
					+ " and iIDDGPMPT = "
					+ request.getParameter("hdiIdefMedPrev");

			iUniMed = dINVRegistro.FindUniMed(cWhere);
			if (iUniMed > 0) {
				UpdStatus = "Hidden";
				vDespliega = dINVRegistro.FindByAll(cWhereA, "");

			} else
				UpdStatus = "SaveOnly";

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void FillPK() {
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
		String cWhere = "";
		TFechas tfCampo = new TFechas();
		Vector vcPersonal = new Vector();
		TVINVRegistro vINVRegistro = new TVINVRegistro();
		try {

			vINVRegistro.setIAnio(Integer.parseInt(
					request.getParameter("hdiAnio"), 10));
			vINVRegistro.setICveMdoTrans(Integer.parseInt(
					request.getParameter("hdiCveMdoTrans"), 10));
			vINVRegistro.setIIDDGPMPT(Integer.parseInt(
					request.getParameter("hdiIdefMedPrev"), 10));

			// Unidad M�dica
			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegistro.setICveUniMed(iCampo);

			// Unidad M�dulo
			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRegistro.setICveModulo(iCampo);

			// Fecha de Asiganaci�n

			// Fecha de Vigencia
			cCampo = "" + request.getParameter("dtAsigna");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vINVRegistro.setDtAsigna(dtCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vINVRegistro;
	}
}