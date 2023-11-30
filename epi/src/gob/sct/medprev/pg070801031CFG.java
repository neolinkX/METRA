package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;
import javax.servlet.http.*;
import com.micper.sql.*;

/**
 * * Clase de configuracion para CFG de la pagina pg070801031
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801030CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801030CFG.png'>
 */
public class pg070801031CFG extends CFGCatBase2 {
	public pg070801031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070801030.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDALMAlmacen dALMAlmacen = new TDALMAlmacen();
		try {
			cClave = (String) dALMAlmacen.insert(null, this.getInputs());
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
		TDALMAlmacen dALMAlmacen = new TDALMAlmacen();
		try {
			cClave = (String) dALMAlmacen.update(null, this.getInputs());
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
		TDALMAlmacen dALMAlmacen = new TDALMAlmacen();
		try {
			cClave = (String) dALMAlmacen.disable(null, this.getInputs());
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
		TDALMAlmacen dALMAlmacen = new TDALMAlmacen();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("") != 0)
				cWhere += " where ALMAlmacen.iCveUniMed = "
						+ request.getParameter("iCveUniMed");
			else if (request.getParameter("hdUniMed") != null
					&& request.getParameter("hdUniMed").compareTo("") != 0)
				cWhere += " where ALMAlmacen.iCveUniMed = "
						+ request.getParameter("hdUniMed");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			vDespliega = dALMAlmacen.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
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
			mPk.add(request.getParameter("hdUniMed"));
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
		TVALMAlmacen vALMAlmacen = new TVALMAlmacen();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMAlmacen.setICveAlmacen(iCampo);

			if (request.getParameter("hdBoton").compareTo("Borrar") == 0)
				cCampo = "" + request.getParameter("iCveUnimed");
			else
				cCampo = "" + request.getParameter("hdUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMAlmacen.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("cDscAlmacen");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMAlmacen.setCDscAlmacen(cCampo);

			cCampo = "" + request.getParameter("iCveUsuResp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMAlmacen.setICveUsuResp(iCampo);

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
			vALMAlmacen.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vALMAlmacen;
	}
}