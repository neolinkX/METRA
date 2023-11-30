package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuracion para Configuracion del examen
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101070CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101070CFG.png'>
 */
public class pg070101070CFG extends CFGCatBase2 {
	public pg070101070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMEDServExamen dMEDServExamen = new TDMEDServExamen();
		try {

			String cProceso = "";

			cProceso = request.getParameter("iCveProceso");

			TVMEDServicio vTVS = new TVMEDServicio();
			TDMEDServicio dMedSrv = new TDMEDServicio();
			Vector vSrvs = new Vector();
			vSrvs = dMedSrv.FindByAll(" Where lActivo = 1 ");

			TVGRLMotivo vTVMots = new TVGRLMotivo();
			TDGRLMotivo dGrlMots = new TDGRLMotivo();
			Vector vMots = new Vector();
			if (cProceso.compareTo("null") != 0 && cProceso.compareTo("") != 0)
				vMots = dGrlMots.FindByAll(" Where iCveProceso = " + cProceso
						+ " and lActivo = 1");
			else
				vMots = dGrlMots
						.FindByAll(" Where iCveProceso = 1 and lActivo = 1");
			int iProceso = 0;
			int iServicio = 0;
			int iMotivo = 0;

			dMEDServExamen.delete(null, this.getInputs());

			for (int j = 0; j < vSrvs.size(); j++) {
				vTVS = (TVMEDServicio) vSrvs.get(j);
				if (request.getParameter("General" + j) != null) {
					// dMEDServExamen.update(null,this.getInputs(),Integer.parseInt((String)request.getParameter("iCveServicio"+j)),0);
					if (vMots.size() > 0) {
						for (int k = 0; k < vMots.size(); k++) {
							vTVMots = (TVGRLMotivo) vMots.get(k);
							dMEDServExamen.update(null, this.getInputs(),
									Integer.parseInt((String) request
											.getParameter("iCveServicio" + j)),
									vTVMots.getICveMotivo());
						}
					} else {
						dMEDServExamen.update(null, this.getInputs(), Integer
								.parseInt((String) request
										.getParameter("iCveServicio" + j)), 0);
					}
				}
				for (int k = 0; k < vMots.size(); k++) {
					vTVMots = (TVGRLMotivo) vMots.get(k);
					if (request.getParameter(vTVMots.getCDscMotivo().replace(
							' ', '_')
							+ j) != null) {
						dMEDServExamen.update(null, this.getInputs(), Integer
								.parseInt((String) request
										.getParameter("iCveServicio" + j)),
								Integer.parseInt((String) request
										.getParameter(vTVMots.getCDscMotivo()
												.replace(' ', '_') + j)));
					}
				}
			}

			// cClave = (String) dMEDServExamen.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDMEDServExamen dMEDServExamen = new TDMEDServExamen();
		try {
			String cProceso = "";

			cProceso = request.getParameter("iCveProceso");

			TVMEDServicio vTVS = new TVMEDServicio();
			TDMEDServicio dMedSrv = new TDMEDServicio();
			Vector vSrvs = new Vector();
			vSrvs = dMedSrv.FindByAll(" Where lActivo = 1 ");

			TVGRLMotivo vTVMots = new TVGRLMotivo();
			TDGRLMotivo dGrlMots = new TDGRLMotivo();
			Vector vMots = new Vector();

			if (cProceso.compareTo("null") != 0 && cProceso.compareTo("") != 0)
				vMots = dGrlMots.FindByAll(" Where iCveProceso = " + cProceso
						+ " and lActivo = 1");
			else
				vMots = dGrlMots
						.FindByAll(" Where iCveProceso = 1 and lActivo = 1");

			int iProceso = 0;
			int iServicio = 0;
			int iMotivo = 0;

			dMEDServExamen.delete(null, this.getInputs());

			for (int j = 0; j < vSrvs.size(); j++) {
				vTVS = (TVMEDServicio) vSrvs.get(j);
				if (request.getParameter("General" + j) != null) {
					// dMEDServExamen.update(null,this.getInputs(),Integer.parseInt((String)request.getParameter("iCveServicio"+j)),0);
					if (vMots.size() > 0) {
						for (int k = 0; k < vMots.size(); k++) {
							vTVMots = (TVGRLMotivo) vMots.get(k);
							dMEDServExamen.update(null, this.getInputs(),
									Integer.parseInt((String) request
											.getParameter("iCveServicio" + j)),
									vTVMots.getICveMotivo());
						}
					} else {
						dMEDServExamen.update(null, this.getInputs(), Integer
								.parseInt((String) request
										.getParameter("iCveServicio" + j)), 0);
					}
				}
				for (int k = 0; k < vMots.size(); k++) {
					vTVMots = (TVGRLMotivo) vMots.get(k);
					if (request.getParameter(vTVMots.getCDscMotivo().replace(
							' ', '_')
							+ j) != null) {
						dMEDServExamen.update(null, this.getInputs(), Integer
								.parseInt((String) request
										.getParameter("iCveServicio" + j)),
								Integer.parseInt((String) request
										.getParameter(vTVMots.getCDscMotivo()
												.replace(' ', '_') + j)));
					}
				}
			}

			// cClave = (String) dMEDServExamen.update(null, this.getInputs());
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
		TDMEDServExamen dMEDServExamen = new TDMEDServExamen();
		try {
			// cClave = (String) dMEDServExamen.disable(null, this.getInputs());
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
		TDMEDServExamen dMEDServExamen = new TDMEDServExamen();
		try {

			String cWhere = "";
			String cCond = "";
			String cOrder = "";
			String lAction = request.getParameter("hdBoton");
			vDespliega = dMEDServExamen.FindByAll("");

			if (lAction.equalsIgnoreCase("Primero")) {
				cCond = request.getParameter("hdCCondicion");
				cOrder = "";// request.getParameter("hdCOrdenar");
				if (cCond != null) {
					if (cCond.compareTo("null") == 0 || cCond.length() == 0) {
						cCond = "";
					} else {
						cCond = " Where " + cCond;
					}
				} else {
					cCond = "";
				}

				if (cOrder != null) {
					// if (cOrder.compareTo("null") == 0) {
					// cOrder = "";
					// }
				} else {
					cOrder = "";
				}
				cWhere += cCond + " " + cOrder;
				vDespliega = dMEDServExamen.FindByAll(cWhere);
			}
			if (lAction.equalsIgnoreCase("Buscar")
					|| lAction.equalsIgnoreCase("Cancelar")) {
				cCond = request.getParameter("iCveProceso");
				if (cCond != null) {
					if (cCond.compareTo("null") == 0 || cCond.length() == 0) {
						cCond = "0";
					} else {
						cCond = " Where a.iCveProceso = " + cCond;
					}
				} else {
					cCond = "";
				}

				if (cOrder != null) {
					// if (cOrder.compareTo("null") == 0) {
					// cOrder = "";
					// }
				} else {
					cOrder = "";
				}
				cWhere += cCond + " " + cOrder;
				vDespliega = dMEDServExamen.FindByAll(cWhere);
			}

			if (lAction.equalsIgnoreCase("Modificar")
					|| lAction.equalsIgnoreCase("GuardarA")
					|| lAction.equalsIgnoreCase("Guardar")) {
				cCond = request.getParameter("iCveProceso");
				if (cCond != null) {
					if (cCond.compareTo("null") == 0 || cCond.length() == 0) {
						cCond = "0";
					} else {
						cCond = " Where a.iCveProceso = " + cCond;
					}
				} else {
					cCond = "";
				}

				if (cOrder != null) {
					// if (cOrder.compareTo("null") == 0) {
					// cOrder = "";
					// }
				} else {
					cOrder = "";
				}
				cWhere += cCond + " " + cOrder;
				vDespliega = dMEDServExamen.FindByAll(cWhere);
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
		TVMEDServExamen vMEDServExamen = new TVMEDServExamen();
		try {
			cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDServExamen.setICveProceso(iCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDServExamen.setICveMotivo(iCampo);

			cCampo = "" + request.getParameter("iCveServicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDServExamen.setICveServicio(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDServExamen;
	}
}