package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG Cat ALMUnidad
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Juan Manuel Vazquez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801101CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801101CFG.png'>
 */
public class pg070801101CFG extends CFGCatBase2 {
	public pg070801101CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDALMUnidad dALMUnidad = new TDALMUnidad();
		try {
			cClave = (String) dALMUnidad.insert(null, this.getInputs());
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
		TDALMUnidad dALMUnidad = new TDALMUnidad();
		try {
			cClave = (String) dALMUnidad.update(null, this.getInputs());
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
		TDALMUnidad dALMUnidad = new TDALMUnidad();
		try {
			cClave = (String) dALMUnidad.disable(null, this.getInputs());
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
		cPaginas = "pg070801100.jsp|";
		TDALMUnidad dALMUnidad = new TDALMUnidad();
		try {
			String cFiltro = "" + request.getParameter("hdCCondicion");
			String cOrden = "" + request.getParameter("hdCOrdenar");

			if (cFiltro.compareToIgnoreCase("null") != 0
					&& cFiltro.compareTo("") != 0)
				cFiltro = " where " + cFiltro;
			else
				cFiltro = "";

			if (cOrden.compareToIgnoreCase("null") != 0
					&& cOrden.compareTo("") != 0)
				cOrden = cOrden;
			else
				cOrden = "";

			vDespliega = dALMUnidad.FindByAll(cFiltro, cOrden);
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
		TVALMUnidad vALMUnidad = new TVALMUnidad();
		try {
			cCampo = "" + request.getParameter("hdCampoClave"); // iCveTpoArticulo
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMUnidad.setICveUnidad(iCampo);

			cCampo = "" + request.getParameter("cDscUnidad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMUnidad.setCDscUnidad(cCampo);

			cCampo = "" + request.getParameter("cAbreviatura");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMUnidad.setCAbreviatura(cCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMUnidad.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vALMUnidad;
	}
}
