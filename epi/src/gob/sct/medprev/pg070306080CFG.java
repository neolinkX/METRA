package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuracion para PKG Validacion
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg07030680CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg07030680CFG.png'>
 */
public class pg070306080CFG extends CFGCatBase2 {
	public pg070306080CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDTOXAbsorvancia dTOXAbsorvancia = new TDTOXAbsorvancia();
		try {
			cClave = (String) dTOXAbsorvancia.insert(null, this.getInputs());

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	public void aGenerar() {

	}

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDTOXAbsorvancia dTOXAbsorvancia = new TDTOXAbsorvancia();
		try {
			cClave = (String) dTOXAbsorvancia.update(null, this.getInputs());

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo BorrarB
	 */
	public void BorrarB() {
		TDTOXAbsorvancia dTOXAbsorvancia = new TDTOXAbsorvancia();
		try {
			// cClave = (String) dTOXAbsorvancia.disable(null,
			// this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.BorrarB();
		}
	} // Metodo BorrarB

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXAbsorvancia dTOXAbsorvancia = new TDTOXAbsorvancia();
		cPaginas = "pg070306081.jsp|"; // Aqui va la pagina de origen
		String cWhere = "";
		String cParam1 = "";
		String aBoton = "";
		try {
			vDespliega = dTOXAbsorvancia.FindByAll("");
			NavStatus = "FirstRecord";
			String lAction = request.getParameter("hdBoton");
			if (lAction.equalsIgnoreCase("Modificar")) {
				cParam1 = request.getParameter("iCveAbsorvancia");
				cWhere = " Where iCveAbsorvancia = " + cParam1;
				vDespliega = dTOXAbsorvancia.FindByAll(cWhere);

			}
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
		TFechas tfCampo = new TFechas();
		TVTOXAbsorvancia vTOXAbsorvancia = new TVTOXAbsorvancia();
		try {
			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXAbsorvancia.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveAbsorvancia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXAbsorvancia.setICveAbsorvancia(iCampo);

			cCampo = "" + request.getParameter("iCveEquipo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXAbsorvancia.setICveEquipo(iCampo);

			cCampo = "" + request.getParameter("dtEstudio");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vTOXAbsorvancia.setDtEstudio(dtCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXAbsorvancia.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("iCveUsuResp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXAbsorvancia.setICveUsuResp(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXAbsorvancia;
	}
}
