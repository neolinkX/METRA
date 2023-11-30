package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuraci�n para CFG Investigaciones por unidad Medica
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070302030CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070302030CFG.png'>
 */
public class pg070402030CFG extends CFGCatBase2 {

	private Vector vcMuestrasApi = null;
	private Vector vcMuestrasCan = null;

	public pg070402030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * M�todo Guardar
	 */
	public void Guardar() {
		TDEnvio dEnvio = new TDEnvio();
		int iEnvio = 0;
		try {
			if (request.getParameter("iCveEnvio") != null) {
				iEnvio = Integer.parseInt(request.getParameter("iCveEnvio"));
			}
			cClave = (String) dEnvio.insert(null, this.getInputs(),
					vcMuestrasApi, vcMuestrasCan, iEnvio);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // M�todo Guardar

	/**
	 * M�todo GuardarA
	 */
	public void GuardarA() {
		TDEnvio dEnvio = new TDEnvio();
		try {
			cClave = (String) dEnvio.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // M�todo GuardarA

	/**
	 * M�todo Borrar
	 */
	public void Borrar() {
		TDEnvio dEnvio = new TDEnvio();
		try {
			cClave = (String) dEnvio.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // M�todo Borrar

	/**
	 * M�todo FillVector
	 */
	public void fillVector() {
		TDEnvio dEnvio = new TDEnvio();
		try {
			if (request.getParameter("hdBoton").compareTo("Enviar") == 0) {
				vDespliega = dEnvio.FindByEnvio(this.getInputs());
				/*
				 * && request.getParameter("hdBoton").compareTo("Guardar") != 0
				 * && request.getParameter("iCveEnvio").compareTo("0") != 0) {
				 */
			} else {
				if (request.getParameter("hdBoton").compareTo("Guardar") == 0
						&& request.getParameter("iCveEnvio").compareTo("0") == 0) {
					vDespliega = dEnvio.FindByNuevo(this.getInputs());
				} else
					vDespliega = dEnvio.FindByAll(this.getInputs());

			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * M�todo iBuscar
	 */
	public void iBuscar() {
		TDEnvio dEnvio = new TDEnvio();
		try {
			if (request.getParameter("hdBoton").compareTo("Enviar") == 0) {
				vDespliega = dEnvio.FindByEnvio(this.getInputs());
				/*
				 * && request.getParameter("hdBoton").compareTo("Guardar") != 0
				 * && request.getParameter("iCveEnvio").compareTo("0") != 0) {
				 */
			} else {
				if (request.getParameter("hdBoton").compareTo("Guardar") == 0
						&& request.getParameter("iCveEnvio").compareTo("0") == 0) {
					vDespliega = dEnvio.FindByNuevo(this.getInputs());
				} else
					vDespliega = dEnvio.FindByAll(this.getInputs());

			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * M�todo FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
	}

	/**
	 * M�todo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVEnvio vEnvio = new TVEnvio();
		try {
			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEnvio.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEnvio.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveEnvio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEnvio.setICveEnvio(iCampo);

			cCampo = "" + request.getParameter("iCveLaboratorio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEnvio.setICveLaboratorio(iCampo);

			TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
					.getAttribute("UsrID");
			iCampo = vUsuario.getICveusuario();
			/*
			 * cCampo = "" + request.getParameter("iCveUsuEnvia"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 */
			vEnvio.setICveUsuEnvia(iCampo);

			/*
			 * cCampo = "" + request.getParameter("dtEnvio"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * dtCampo = tfCampo.getDateSQL(cCampo); } else { dtCampo = null; }
			 */
			dtCampo = tfCampo.TodaySQL();
			vEnvio.setDtEnvio(dtCampo);

			cCampo = "" + request.getParameter("iCveTipoEnvio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEnvio.setICveTipoEnvio(iCampo);

			cCampo = "" + request.getParameter("dtRecepcion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEnvio.setDtRecepcion(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuRec");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEnvio.setICveUsuRec(iCampo);

			cCampo = "" + request.getParameter("cObsEnvio");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEnvio.setCObsEnvio(cCampo);

			cCampo = "" + request.getParameter("cObsRecep");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEnvio.setCObsRecep(cCampo);

			// Vector de Muestras
			vcMuestrasApi = new Vector();
			vcMuestrasCan = new Vector();
			Vector vcMuestra = null;
			TDMuestra dMuestra = new TDMuestra();
			vcMuestra = dMuestra.FindMuestraNE(vEnvio.getIAnio(),
					vEnvio.getICveUniMed(), vEnvio.getICveEnvio(), "");
			Object obElemento;
			int i;
			int iAcumulado = 0;
			int iFlag;

			for (i = 0; i < vcMuestra.size(); i++) {
				obElemento = vcMuestra.get(i);
				TVMuestra vMuestra = (TVMuestra) obElemento;
				iFlag = 0;

				if (request.getParameter("S" + vMuestra.getICveMuestra()) == null) {
					cCampo = "false";
				} else {
					cCampo = request.getParameter("S"
							+ vMuestra.getICveMuestra());
				}
				if (cCampo.compareTo("ON") == 0) {
					vcMuestrasApi.addElement(vMuestra);
					iFlag = 1;
				}

				if (iFlag == 0) {
					if (request.getParameter("D" + vMuestra.getICveMuestra()) == null) {
						cCampo = "false";
					} else {
						cCampo = request.getParameter("D"
								+ vMuestra.getICveMuestra());
					}
					if (cCampo.compareTo("ON") == 0) {
						vcMuestrasCan.addElement(vMuestra);
					}
				}
			}
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEnvio;
	}
}