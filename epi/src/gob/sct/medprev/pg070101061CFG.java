package gob.sct.medprev;

import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG Catalogo de configuracion de la Rama
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101061CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101061CFG.png'>
 */
public class pg070101061CFG extends CFGCatBase2 {
	int iCveRama = 0;
	int iCveServicio = 0;

	public pg070101061CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070101060.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		try {
			cClave = (String) dMEDSintoma.insert(null, this.getInputs());
			cAccion = "Ultimo";
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
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		try {
			String cCode = "";
			cCode = (String) dMEDSintoma.update(null, this.getInputs());

			StringTokenizer st = new StringTokenizer(cCode, "|");
			String cArray[] = new String[3];
			int Cont = 0;
			while (st.hasMoreTokens()) {
				cArray[Cont] = st.nextToken();
				Cont++;
			}

			cClave = cArray[2];
			iCveRama = Integer.parseInt(cArray[1]);
			iCveServicio = Integer.parseInt(cArray[0]);

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
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		try {
			cClave = (String) dMEDSintoma.disable(null, this.getInputs());

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
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		try {
			String cWhere = "";

			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdSintoma") != null) {
				cActual = request.getParameter("hdSintoma");
				cAccion = "ReposPK";
			}

			if (request.getParameter("iCveServicio") != null
					&& request.getParameter("iCveRama") != null) {

				cWhere = " where a.iCveServicio =  "
						+ request.getParameter("iCveServicio");
				cWhere = cWhere + " and a.iCveRama = "
						+ request.getParameter("iCveRama");
				if (cOrden != null && cOrden.compareTo("null") != 0) {
					cWhere = cWhere + " " + cOrden;
				}
				vDespliega = dMEDSintoma.FindByAll(cWhere);
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void fillPK() {
		mPk.add("" + request.getParameter("iCveServicio"));
		mPk.add("" + request.getParameter("iCveRama"));
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
		// TFechas tfCampo = new TFechas();
		TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
		try {
			cCampo = "" + request.getParameter("iCveServicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDSintoma.setICveServicio(iCampo);

			cCampo = "" + request.getParameter("iCveRama");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDSintoma.setICveRama(iCampo);

			cCampo = "" + request.getParameter("iCveSintoma");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDSintoma.setICveSintoma(iCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDSintoma.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("cPregunta");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDSintoma.setCPregunta(cCampo);

			cCampo = "" + request.getParameter("cGenero");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDSintoma.setCGenero(cCampo);

			cCampo = "" + request.getParameter("lEstudio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDSintoma.setLEstudio(iCampo);

			cCampo = "" + request.getParameter("iCveTpoResp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDSintoma.setICveTpoResp(iCampo);

			cCampo = "" + request.getParameter("cEtiqueta");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDSintoma.setCEtiqueta(cCampo);

			cCampo = "" + request.getParameter("lCPersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDSintoma.setLCPersonal(iCampo);

			cCampo = "" + request.getParameter("lObligatorio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDSintoma.setLObligatorio(iCampo);

			cCampo = "" + request.getParameter("lEvalAuto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDSintoma.setLEvalAuto(iCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDSintoma.setLActivo(iCampo);

			cCampo = "" + request.getParameter("cValRef");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDSintoma.setCValRef(cCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDSintoma;
	}
}