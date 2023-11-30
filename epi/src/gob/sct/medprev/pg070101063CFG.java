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
public class pg070101063CFG extends CFGCatBase2 {
	int iCveRama = 0;
	int iCveServicio = 0;

	public pg070101063CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070101060.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		// TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		System.out.println("======== G U A R D A N D O =========");
		TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
		try {
			cClave = (String) dMEDRespSint.insert(null, this.getInputs());
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
		// TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
	    System.out.println("======== M O D I F I C A N D O =========");
		try {
			String cCode = "";
			List<String> listaRespuestas = new ArrayList<String>();
			List<String> listaRespNum = new ArrayList<String>();
			// System.out.println("Size Datos "+request.getParameter("hdSizeDatos"));
			int cont = Integer
					.parseInt(request.getParameter("hdSizeDatos"), 10);
			for (int i = 0; i < cont; i++) {
				// System.out.println("Orden "+request.getParameter("cOrdenRef"+i));
				// System.out.println("Valor "+request.getParameter("cValRef"+i));
				listaRespNum.add(request.getParameter("cOrdenRef" + i));
				listaRespuestas.add(request.getParameter("cValRef" + i));
			}
			cCode = (String) dMEDRespSint.updateRama(null, this.getInputs(),
					listaRespuestas, listaRespNum);

			/*
			 * StringTokenizer st = new StringTokenizer(cCode, "|"); String
			 * cArray[] = new String[3]; int Cont = 0; while
			 * (st.hasMoreTokens()) { cArray[Cont] = st.nextToken(); Cont++; }
			 * 
			 * cClave = cArray[2]; iCveRama = Integer.parseInt(cArray[1]);
			 * iCveServicio = Integer.parseInt(cArray[0]);
			 */

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}

		// System.out.println("======== F I N   M O D I F I C A N D O =========");
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
		TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
		try {
			String cWhere = "";

			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("iCveSintoma") != null) {
				cActual = request.getParameter("iCveSintoma");
				cAccion = "ReposPK";
			}

			if (request.getParameter("iCveServicio") != null
					&& request.getParameter("iCveRama") != null) {

				cWhere = " where a.iCveServicio =  "
						+ request.getParameter("iCveServicio");
				cWhere = cWhere + " and a.iCveRama = "
						+ request.getParameter("iCveRama");
				if (request.getParameter("iCveSintoma") != null)
					cWhere = cWhere + " and a.iCveSintoma = "
							+ request.getParameter("iCveSintoma");
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
		// TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
		TVMEDRespSint vMEDRespSint = new TVMEDRespSint();
		try {
			cCampo = "" + request.getParameter("hdServicio2");
			// System.out.println(cCampo);
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRespSint.setICveServicio(iCampo);

			cCampo = "" + request.getParameter("iCveRama");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRespSint.setICveRama(iCampo);

			cCampo = "" + request.getParameter("hdSintoma");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRespSint.setICveSintoma(iCampo);

			cCampo = "" + request.getParameter("iCveTpoResp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRespSint.setICveTpoResp(iCampo);
			// System.out.println("tipo respuesta="+cCampo);

			cCampo = "" + request.getParameter("lLogico2");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDRespSint.setILogica(iCampo);

			cCampo = "" + request.getParameter("hdChosen");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDRespSint.setCDescripcion(cCampo);
			// System.out.println("descripcion="+cCampo);
			cCampo = "" + request.getParameter("hdSizeDatos");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			/*
			 * for (int i = 0; i < iCampo; i++) { vMEDRespSint. }
			 */

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDRespSint;
	}
}