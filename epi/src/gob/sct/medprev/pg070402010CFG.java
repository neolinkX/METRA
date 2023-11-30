package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.sql.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para Listado de Investigaciones por Unidad Médica
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070402010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070402010CFG.png'>
 */
public class pg070402010CFG extends CFGListBase2 {
	public pg070402010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070402011.jsp";
		NavStatus = "Disabled";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		try {
			String cWhere = request.getParameter("hdLCondicion");
			cWhere = cWhere != null ? cWhere : "";
			String cOrder = request.getParameter("hdLOrdenar");
			cOrder = cOrder != null ? cOrder : "";
			String cIAnio = request.getParameter("iAnioSel");
			cIAnio = cIAnio != null ? cIAnio : "0";
			String cICveMdoTrans = request.getParameter("iCveMdoTransSel");
			cICveMdoTrans = cICveMdoTrans != null ? cICveMdoTrans : "0";
			String cICveUniMed = request.getParameter("iCveUniMedSel");
			cICveUniMed = cICveUniMed != null ? cICveUniMed : "0";
			if (cWhere.length() != 0)
				cWhere += " and ";
			cWhere += " a.iAnio=" + cIAnio + " and a.iCveMdoTrans="
					+ cICveMdoTrans + " and a.iCveUniMed=" + cICveUniMed;

			vDespliega = new TDINVRegistro().FindByAll(cWhere, cOrder);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		try {
			new TDINVRegistro().updateStatus(null, getINVRegistros());
			vErrores.acumulaError("", 0,
					"Se guardaron correctamente los datos.");
		} catch (Exception ex) {
			error("Error al actualizar los registros", ex);
		}
	} // Método Guardar

	/**
	 * Método getMdoTrans
	 */
	public Vector getMdoTrans() {
		Vector vcTmp = null;
		try {
			vcTmp = new TDGRLMdoTrans().findByAll("", " order by cDscMdoTrans");
		} catch (Exception ex) {
			error("getMdoTrans", ex);
		}
		return vcTmp;
	}

	/**
	 * Método getUniMeds
	 */
	public Vector getUniMeds() {
		Vector vcTmp = null;
		try {
			vcTmp = new TDGRLUniMed().FindByAll(" order by cDscUniMed");
		} catch (Exception ex) {
			error("getUniMed", ex);
		}
		return vcTmp;
	}

	/**
	 * Método getINVRegistros
	 */
	private Vector getINVRegistros() {
		Vector vcTmp = new Vector();
		for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			String cParam = (String) e.nextElement();
			if (cParam.startsWith("rbStatus|")) {
				int iStatus = Integer.parseInt(request.getParameter(cParam));
				if (iStatus != 0) {
					String[] cTmp = cParam.split("\\|");
					TVINVRegistro vINVRegistro = new TVINVRegistro();
					vINVRegistro.setIAnio(Integer.parseInt(cTmp[1]));
					vINVRegistro.setICveMdoTrans(Integer.parseInt(cTmp[2]));
					vINVRegistro.setIIDDGPMPT(Integer.parseInt(cTmp[3]));
					vINVRegistro.setLInvestigado(1);
					switch (iStatus) {
					case 1:
						vINVRegistro.setLCancelado(0);
						break;
					case 2:
						vINVRegistro.setLCancelado(1);
						break;
					}
					vcTmp.add(vINVRegistro);
				}
			}
		}
		return vcTmp;
	}
}