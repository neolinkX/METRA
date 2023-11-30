package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.sql.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Listado de Investigaciones por Unidad M�dica
 * <p>
 * CFG de Investigaci�n de Personal.
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Gonzalez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070402040CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070402040CFG.png'>
 */
public class pg070402040CFG extends CFGCatBase2 {
	public pg070402040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		String cWhere = "";
		try {

			if (request.getParameter("iAnioSel") != null
					&& request.getParameter("iCveMdoTransSel") != null
					&& request.getParameter("iIDDGPMPT") != null) {
				if (Integer.parseInt(request.getParameter("iAnioSel"), 10) > 0
						&& Integer.parseInt(
								request.getParameter("iCveMdoTransSel"), 10) > 0
						&& Integer.parseInt(request.getParameter("iIDDGPMPT"),
								10) > 0) {
					cWhere = "     a.iAnio        = "
							+ request.getParameter("iAnioSel")
							+ " and a.iCveMdoTrans = "
							+ request.getParameter("iCveMdoTransSel")
							+ " and a.iIDDGPMPT    = "
							+ request.getParameter("iIDDGPMPT");

					if (cCondicion.compareTo("") != 0) {
						cWhere += " and a." + cCondicion;
					}

					vDespliega = new TDINVRegistro().FindByAll(cWhere, "");

				}
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
		mPk.add("iAnio");
		mPk.add("iCveMdoTrans");
		mPk.add("iIDDGPMPT");

	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			new TDINVRegistro().updateStatus(null, getINVRegistros());
			vErrores.acumulaError("", 0,
					"Se guardaron correctamente los datos.");
		} catch (Exception ex) {
			error("Error al actualizar los registros", ex);
		}
	} // Metodo Guardar

	/**
	 * Metodo getMdoTrans
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
	 * Metodo getUniMeds
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
	 * Metodo getINVRegistros
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
