package gob.sct.medprev;

import java.util.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import com.micper.util.caching.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para CFG Catalogo de las Empresas
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070502011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070502011CFG.png'>
 */
public class pg070502011CFG extends CFGCatBase2 {

	public TDGRLMdoTrans dMdoTrans = new TDGRLMdoTrans();
	public TDCTROrigenInf dOrigenInf = new TDCTROrigenInf();
	public TDCTRGrupoEmp dGpoEmp = new TDCTRGrupoEmp();

	public TVGRLMdoTrans tvMdoTrans = new TVGRLMdoTrans();
	public TVCTROrigenInf tvOrigInf = new TVCTROrigenInf();

	public Vector vLabs = new Vector();
	public Vector vMdoTrans = new Vector();
	public Vector vMdoTransT = new Vector();
	public Vector vOrigenInf = new Vector();
	public Vector vGpoEmp = new Vector();
	String antCve = "";

	public pg070502011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070502010.jsp|pg070502030.jsp|";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
		try {
			cClave = (String) dGRLEmpresas.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Método Guardar

	/**
	 * Método GuardarA
	 */
	public void GuardarA() {
		TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
		try {
			cClave = (String) dGRLEmpresas.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Método GuardarA

	/**
	 * Método Borrar
	 */
	public void Borrar() {
		TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
		try {
			cClave = (String) dGRLEmpresas.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Método Borrar

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();

		try {
			vLabs = (Vector) AppCacheManager.getColl("GRLUniMed", "");
			tvMdoTrans.setCDscMdoTrans("Todos");
			tvMdoTrans.setICveMdoTrans(0);
			vMdoTrans = dMdoTrans.findByAll("");
			vMdoTransT = dMdoTrans.findByAll("");
			vMdoTransT.add(tvMdoTrans);
			vGpoEmp = dGpoEmp.FindByAll(" where lActivo=1 ", "");
			vOrigenInf = dOrigenInf.FindByAll("", "");

			// Filtros
			String cUniMed = "0";
			String iMdoTrans = "0";
			String cWhereMdo = "";
			String cWhereUni = "";
			String where = "";

			if (request.getParameter("iCveMdoTrans") != null
					&& !request.getParameter("iCveMdoTrans").equals("")) {
				iMdoTrans = request.getParameter("iCveMdoTrans").toString();
			}
			if (request.getParameter("iCveUniMed") != null
					&& !request.getParameter("iCveUniMed").equals("")) {
				cUniMed = request.getParameter("iCveUniMed").toString();
			}

			// Modo de transporte
			if (iMdoTrans.equals("0")) {
				cWhereMdo = "";
			} else {
				cWhereMdo = "iCveMdoTrans=" + iMdoTrans;
			}

			// Unidad Médica
			if (cUniMed.equals("0")) {
				cWhereUni = "iCveUniMed=" + cUniMed;
			} else {
				cWhereUni = "iCveUniMed=" + cUniMed;
			}
			if (!cWhereMdo.equals("")) {
				where += cWhereMdo;
				if (!cWhereUni.equals(""))
					where = where + " and " + cWhereUni;
			} else {
				if (!cWhereUni.equals("")) {
					where += cWhereUni;
				} else {
					where = " ";
				}
			}
			if (!where.equals("")) {
				if (!where.equals(" "))
					where = " where " + where;
				if (!where.equals(" ") && !where.equals("")
						&& !cCondicion.equals("")) {
					where = where + " and " + cCondicion;
				} else if (!cCondicion.equals("")) {
					where = " where " + cCondicion;
				}

				if (cOrden.compareTo("") == 0)
					cOrden = " order by cIDDGPMPT";

				vDespliega = dGRLEmpresas.FindByAllGpoOrgInf(where + cOrden);

				if (request.getParameter("hdBoton").equals("Cancelar"))
					this.Cancelar();
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método fillPK
	 */
	public void fillPK() {
		mPk.add(cActual);
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
		try {
			cCampo = "" + request.getParameter("iCveEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLEmpresas.setICveEmpresa(iCampo);

			cCampo = "" + request.getParameter("iCveGrupoEmp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLEmpresas.setICveGrupoEmp(iCampo);

			cCampo = "" + request.getParameter("cIDDGPMPT");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEmpresas.setcIDDGPMPT(cCampo);

			cCampo = "" + request.getParameter("iIDMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLEmpresas.setIIDMdoTrans(iCampo);

			cCampo = "" + request.getParameter("cRFC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEmpresas.setCRFC(cCampo);

			cCampo = "" + request.getParameter("cCURP");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEmpresas.setCCurp(cCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLEmpresas.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLEmpresas.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("cTpoPersona");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEmpresas.setCTpoPersona(cCampo);

			cCampo = "" + request.getParameter("cApPaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEmpresas.setCApPaterno(cCampo);

			cCampo = "" + request.getParameter("cApMaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEmpresas.setCApMaterno(cCampo);

			cCampo = "" + request.getParameter("cNombreRS");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEmpresas.setCNombreRS(cCampo);

			cCampo = "" + request.getParameter("cDenominacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEmpresas.setCDenominacion(cCampo);

			cCampo = "" + request.getParameter("dtRegistro");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vGRLEmpresas.setDtRegistro(dtCampo);

			cCampo = "" + request.getParameter("cCveRPA");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLEmpresas.setCCveRPA(cCampo);

			cCampo = "" + request.getParameter("iCveOrigenInf");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLEmpresas.setICveOringenInf(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLEmpresas;
	}
}