package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para CFG Cat ALMTpoArticulo
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070801011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070801011CFG.png'>
 */
public class pg070801011CFG extends CFGCatBase2 {
	public pg070801011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDALMTpoArticulo dALMTpoArticulo = new TDALMTpoArticulo();
		TVALMTpoArticulo vTpoArticulo;
		Vector vDatos;
		boolean lInsertar = true;
		try {
			vTpoArticulo = (TVALMTpoArticulo) this.getInputs();
			if (vTpoArticulo.getIIDPartida() > 0) {
				vDatos = dALMTpoArticulo
						.FindByCustomWhere(" WHERE ALMTpoArticulo.iiDPartida="
								+ vTpoArticulo.getIIDPartida());
				if (vDatos != null && vDatos.size() > 0)
					lInsertar = false;
			}
			if (lInsertar)
				cClave = (String) dALMTpoArticulo
						.insert(null, this.getInputs());
			else
				vErrores.acumulaError("Ya existe la partida proporcionada", 0,
						"No se guard� la informaci�n.");
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
		TDALMTpoArticulo dALMTpoArticulo = new TDALMTpoArticulo();
		TVALMTpoArticulo vTpoArticulo;
		Vector vDatos;
		boolean lInsertar = true;
		try {
			vTpoArticulo = (TVALMTpoArticulo) this.getInputs();
			if (vTpoArticulo.getIIDPartida() > 0) {
				vDatos = dALMTpoArticulo
						.FindByCustomWhere(" WHERE ALMTpoArticulo.iiDPartida="
								+ vTpoArticulo.getIIDPartida());
				if (vDatos != null && vDatos.size() > 0)
					lInsertar = false;
			}
			if (lInsertar)
				cClave = (String) dALMTpoArticulo
						.update(null, this.getInputs());
			else
				vErrores.acumulaError("Ya existe la partida proporcionada.", 0,
						"No se guard� la informaci�n.");
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
		TDALMTpoArticulo dALMTpoArticulo = new TDALMTpoArticulo();
		try {
			cClave = (String) dALMTpoArticulo.disable2(null, this.getInputs());
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
		cPaginas = "pg070801010.jsp|";
		TDALMTpoArticulo dALMTpoArticulo = new TDALMTpoArticulo();
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
				cOrden = " ORDER BY iCveTpoArticulo ";

			vDespliega = dALMTpoArticulo.FindByAll(cFiltro, cOrden);
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
		TVALMTpoArticulo vALMTpoArticulo = new TVALMTpoArticulo();
		try {

			cCampo = "" + request.getParameter("hdCampoClave"); // iCveTpoArticulo
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMTpoArticulo.setICveTpoArticulo(iCampo);

			cCampo = "" + request.getParameter("cDscTpoArticulo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMTpoArticulo.setCDscTpoArticulo(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vALMTpoArticulo.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("iIDPartida");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMTpoArticulo.setIIDPartida(iCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vALMTpoArticulo.setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vALMTpoArticulo;
	}
}
