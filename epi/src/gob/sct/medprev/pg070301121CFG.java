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
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;
import com.micper.util.*;

/**
 * * Clase de configuracion para CFG Catalogo
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301121CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301121CFG.png'>
 */
public class pg070301121CFG extends CFGCatBase2 {
	public pg070301121CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDSustancia dSustancia = new TDSustancia();
		try {
			cClave = (String) dSustancia.insert(null, this.getInputs());
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
		TDSustancia dSustancia = new TDSustancia();
		try {
			cClave = (String) dSustancia.update(null, this.getInputs());
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
		TDSustancia dSustancia = new TDSustancia();
		try {
			cClave = (String) dSustancia.disable(null, this.getInputs());
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
		TDSustancia dSustancia = new TDSustancia();
		String cFiltro = "";
		if (this.getLPagina("pg070301120.jsp"))
			cPaginas = "pg070301120.jsp|";
		try {
			if (cCondicion.compareTo("") != 0) {
				cFiltro = " WHERE " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cFiltro = cFiltro + cOrden;
			}
			vDespliega = dSustancia.FindByAll(cFiltro);
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
		TVSustancia vSustancia = new TVSustancia();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSustancia.setICveSustancia(iCampo);

			cCampo = "" + request.getParameter("cDscSustancia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vSustancia.setCDscSustancia(cCampo);

			cCampo = "" + request.getParameter("cTitRepConf");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vSustancia.setCTitRepConf(cCampo);

			cCampo = "" + request.getParameter("cPrefLoteConf");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vSustancia.setCPrefLoteConf(cCampo);

			cCampo = "" + request.getParameter("cAbrvEq");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vSustancia.setCAbrvEq(cCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSustancia.setLActivo(iCampo);

			cCampo = "" + request.getParameter("lAnalizada");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSustancia.setLAnalizada(iCampo);

			//

			cCampo = "" + request.getParameter("lPositiva");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSustancia.setLPositiva(iCampo);

			cCampo = "" + request.getParameter("iMuestrasLC");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSustancia.setIMuestrasLC(iCampo);

			cCampo = "" + request.getParameter("cSustUnifica");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vSustancia.setCSustUnifica(cCampo);

			cCampo = "" + request.getParameter("cAbrevEqAC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vSustancia.setCAbrevEqAC(cCampo);
			cCampo = "" + request.getParameter("lValidaCtrol");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vSustancia.setLValidaCtrol(iCampo);

			cCampo = "" + request.getParameter("lValConcentracion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			// System.out.println("lValConcentracion" + iCampo);
			vSustancia.setLValConcentracion(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vSustancia;
	}
}
