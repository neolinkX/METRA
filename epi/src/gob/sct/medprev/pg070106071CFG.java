package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Cat�logo de Rubros de No Aptitud
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Oscar Castrej�n Adame
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070106071CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070106071CFG.png'>
 */
public class pg070106071CFG extends CFGCatBase2 {
	public pg070106071CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070106070.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDPERRubroNoAp DPERRubroNoAp = new TDPERRubroNoAp();
		try {
			cClave = (String) DPERRubroNoAp.insert(null, this.getInputs());
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
		TDPERRubroNoAp DPERRubroNoAp = new TDPERRubroNoAp();
		try {
			cClave = (String) DPERRubroNoAp.update(null, this.getInputs());
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
		TDPERRubroNoAp DPERRubroNoAp = new TDPERRubroNoAp();
		try {
			cClave = (String) DPERRubroNoAp.delete(null, this.getInputs());
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
		TDPERRubroNoAp DPERRubroNoAp = new TDPERRubroNoAp();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			// if(request.getParameter("hdCampoClave1")!=null &&
			// request.getParameter("hdCampoClave1").compareTo("")!=0)
			if (request.getParameter("iCveMotivoNoAp") != null
					&& request.getParameter("iCveMotivoNoAp").compareTo("") != 0)
				cWhere += " where iCveMotivoNoAp = "
						+ request.getParameter("iCveMotivoNoAp");
			// else if(request.getParameter("iCveMotivoNoAp")!=null &&
			// request.getParameter("iCveMotivoNoAp").compareTo("")!=0)
			// cWhere += " where iCveMotivoNoAp = " +
			// request.getParameter("iCveMotivoNoAp");
			// else if(request.getParameter("hdiCveMotivoNoAp")!=null &&
			// request.getParameter("hdiCveMotivoNoAp").compareTo("")!=0)
			// cWhere += " where iCveMotivoNoAp = " +
			// request.getParameter("hdiCveMotivoNoAp");
			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by iCveRubroNoAp ";

			if ((request.getParameter("iCveMotivoNoAp") != null
					&& request.getParameter("iCveMotivoNoAp").compareTo("") != 0
					&& request.getParameter("iCveMotivoNoAp").compareTo("null") != 0 && Integer
					.parseInt(request.getParameter("iCveMotivoNoAp")) > 0)
					|| (request.getParameter("hdiCveMotivoNoAp") != null
							&& request.getParameter("hdiCveMotivoNoAp")
									.compareTo("") != 0
							&& request.getParameter("hdiCveMotivoNoAp")
									.compareTo("null") != 0 && Integer
							.parseInt(request.getParameter("hdiCveMotivoNoAp")) > 0)
			// ||(request.getParameter("hdCampoClave1")!=null &&
			// request.getParameter("hdCampoClave1").compareTo("")!=0 &&
			// request.getParameter("hdCampoClave").compareTo("null")!=0 &&
			// Integer.parseInt(request.getParameter("hdCampoClave1"))>0)
			)
				vDespliega = DPERRubroNoAp.FindByAll(cWhere + cOrderBy);
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
		if (request.getParameter("hdBoton").compareTo("Guardar") == 0
				|| request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
			mPk.add(request.getParameter("hdiCveMotivoNoAp"));
			mPk.add(cActual);
		}
	}

	/**
	 * Metodo getInputs que regresa un objeto con los datos de la pantalla.
	 * 
	 * @return Objeto con los Datos de la Pantalla.
	 * @throws CFGException
	 *             Valor de la Excepci�n
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVPERRubroNoAp VPERRubroNoAp = new TVPERRubroNoAp();
		try {

			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0)
				cCampo = "" + request.getParameter("iCveMotivoNoAp");
			// cCampo = "" + request.getParameter("hdiCveMotivoNoAp");
			else
				cCampo = "" + request.getParameter("iCveMotivoNoAp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			VPERRubroNoAp.setICveMotivoNoAp(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			VPERRubroNoAp.setICveRubroNoAp(iCampo);

			cCampo = "" + request.getParameter("cDscRubroNoAp");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			VPERRubroNoAp.setcDscRubroNoAp(cCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return VPERRubroNoAp;
	}
}