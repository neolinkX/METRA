package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuraci�n para CFG pagina pg070802011
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070802011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070802011CFG.png'>
 */
public class pg070802011CFG extends CFGCatBase2 {
	public pg070802011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070802010.jsp|";
	}

	/**
	 * M�todo Guardar
	 */
	public void Guardar() {
		TDALMArticulo dALMArticulo = new TDALMArticulo();
		try {
			cClave = (String) dALMArticulo.insert(null, this.getInputs());
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
		TDALMArticulo dALMArticulo = new TDALMArticulo();
		try {
			cClave = (String) dALMArticulo.update(null, this.getInputs());
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
		TDALMArticulo dALMArticulo = new TDALMArticulo();
		try {
			cClave = (String) dALMArticulo.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // M�todo Borrar

	/**
	 * M�todo FillVector
	 */
	public void fillVector() {
		TDALMArticulo dALMArticulo = new TDALMArticulo();
		try {
			String cWhere = "";
			String cOrderBy = " ORDER BY ALMArticulo.iCveArticulo ";

			if (cCondicion.compareTo("") != 0) {
				cWhere += " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}
			vDespliega = dALMArticulo.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * M�todo FillPK
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
		TVALMArticulo vALMArticulo = new TVALMArticulo();
		try {
			if (request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Borrar") == 0)
				cCampo = "" + request.getParameter("hdCampoClave");
			else
				cCampo = "" + request.getParameter("iCveArticulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMArticulo.setiCveArticulo(iCampo);

			cCampo = "" + request.getParameter("iCveTpoArticulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMArticulo.setiCveTpoArticulo(iCampo);

			cCampo = "" + request.getParameter("iCveFamilia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMArticulo.setiCveFamilia(iCampo);

			cCampo = "" + request.getParameter("cCveArticulo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMArticulo.setcCveArticulo(cCampo);

			cCampo = "" + request.getParameter("cDscArticulo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMArticulo.setcDscArticulo(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMArticulo.setcDscBreve(cCampo);

			cCampo = "" + request.getParameter("iCveUniAlm");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMArticulo.setiCveUniAlm(iCampo);

			cCampo = "" + request.getParameter("iCveUniSum");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMArticulo.setiCveUniSum(iCampo);
			if (request.getParameter("chklMaxMin") == null) {
				cCampo = "0";
			} else {
				cCampo = "1";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMArticulo.setlMaxMin(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMArticulo.setcObservacion(cCampo);

			if (request.getParameter("chklLote") == null) {
				cCampo = "0";
			} else {
				cCampo = "1";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMArticulo.setlLote(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vALMArticulo;
	}
}