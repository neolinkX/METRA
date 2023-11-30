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
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.excepciones.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuración para Registro de Documentos
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070103012CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070103012CFG.png'>
 */
public class pg070103030CFG extends CFGListBase2 {
	public pg070103030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDEXPRequisitos dEXPRequisitos = new TDEXPRequisitos();
		try {
			this.BorrarB();
			int iCveMotivo = 0;
			if (vParametros.getPropEspecifica("EPIProceso") != null) {
				iCveMotivo = Integer.parseInt(vParametros
						.getPropEspecifica("EPIProceso"));
			}
			TDGRLReqxMotivo dTDRqxMt = new TDGRLReqxMotivo();
			Vector vRqxMt = new Vector();
			vRqxMt = dTDRqxMt.FindByAllWithReq(" Where a.iCveMotivo = "
					+ iCveMotivo + " And lActivo = 1");

			if (vRqxMt.size() > 0) {
				for (int w = 0; w < vRqxMt.size(); w++) {
					if (request.getParameter("iCveRequisito" + w) != null) {
						int iCveRequisito = Integer.parseInt(request
								.getParameter("iCveRequisito" + w).toString());
						String cObservacion = request
								.getParameter("cObservacion" + w);
						if (cObservacion == null) {
							cObservacion = "";
						}
						cClave = (String) dEXPRequisitos.insert(null,
								this.getInputs(), iCveRequisito, cObservacion);
					}
				}
			}

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
		TDEXPRequisitos dEXPRequisitos = new TDEXPRequisitos();
		try {
			cClave = (String) dEXPRequisitos.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Método GuardarA

	/**
	 * Método BorrarB
	 */
	public void BorrarB() {
		TVGRLReqxMotivo vTVReqxMotivo = new TVGRLReqxMotivo();
		Vector vReqxMotivo = new Vector();
		TDEXPRequisitos dEXPRequisitos = new TDEXPRequisitos();
		try {
			int iCveMotivo = 0;
			if (vParametros.getPropEspecifica("EPIProceso") != null) {
				iCveMotivo = Integer.parseInt(vParametros
						.getPropEspecifica("EPIProceso"));
			}
			TDGRLReqxMotivo dTDRqxMt = new TDGRLReqxMotivo();
			Vector vRqxMt = new Vector();
			vRqxMt = dTDRqxMt.FindByAllWithReq(" Where a.iCveMotivo = "
					+ iCveMotivo + " And lActivo = 1");
			if (vRqxMt.size() > 0) {
				for (int w = 0; w < vRqxMt.size(); w++) {
					vTVReqxMotivo = (TVGRLReqxMotivo) vRqxMt.get(w);
					cClave = (String) dEXPRequisitos.delete(null,
							this.getInputs(), vTVReqxMotivo.getICveRequisito());
				}
			}

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.BorrarB();
		}
	} // Método BorrarB

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDEXPRequisitos dEXPRequisitos = new TDEXPRequisitos();
		try {
			String lAction = request.getParameter("hdBoton");
			String cCveExpediente = request.getParameter("iCveExpediente");
			String cNumExamen = request.getParameter("iNumExamen");
			int iCveExpediente = 0;
			int iNumExamen = 0;
			if (cCveExpediente != null) {
				iCveExpediente = Integer.parseInt(cCveExpediente);
			} else {
				iCveExpediente = 0;
			}
			if (cNumExamen != null) {
				iNumExamen = Integer.parseInt(cNumExamen);
			} else {
				iNumExamen = 0;
			}

			// System.out.println("Ultima Accion ==>> "+lAction);
			vDespliega = dEXPRequisitos.FindByAll("Where iCveExpediente="
					+ iCveExpediente + " And iNumExamen=" + iNumExamen);
			// System.out.println("Where iCveExpediente="+iCveExpediente+" And iNumExamen="+iNumExamen);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método findUser
	 */
	public TVPERDatos findUser() {
		TVPERDatos vPerDat = null;
		try {
			String cUserId = request.getParameter("hdICvePersonal");

			if (cUserId == null || cUserId.length() == 0) {
				cUserId = "0";
			}
			// System.out.println("cUserId==>> "+cUserId);
			int iCvePersonal = Integer.parseInt(cUserId);
			vPerDat = new TDPERDatos().findUser(iCvePersonal);
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vPerDat;
	}

	/**
	 * Método getEdad
	 */
	public int getEdad(java.sql.Date dtFechaNac) {
		Calendar hoy = Calendar.getInstance();
		Calendar nac = Calendar.getInstance();
		nac.setTime(dtFechaNac);
		int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
		if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
				.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE))
			edad--;
		return edad;
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		// TFechas tfCampo = new TFechas();
		TVEXPRequisitos vEXPRequisitos = new TVEXPRequisitos();
		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPRequisitos.setICveExpediente(iCampo);

			cCampo = "" + request.getParameter("iNumExamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPRequisitos.setINumExamen(iCampo);

			cCampo = "" + request.getParameter("iCveRequisito");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPRequisitos.setICveRequisito(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEXPRequisitos.setCObservacion(cCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPRequisitos;
	}

}