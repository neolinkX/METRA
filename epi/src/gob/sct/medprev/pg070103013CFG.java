package gob.sct.medprev;

import javax.servlet.http.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.seguridad.vo.*;

import java.util.*;

/**
 * * Clase de configuracion para CFG Registro de Citas
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. Gonz�lez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070102020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070103013CFG.png'>
 */
public class pg070103013CFG extends CFGCatBase2 {

	private String cCita;
	private String cModulo;
	private String cFecha;
	private boolean lValidar = false;
	private boolean lGenerar = false;
	private Object Obj = null;
	private java.util.Vector vResValida;
	private int iCvePersonalA;

	public pg070103013CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070103040.jsp";
	}

	public void mainBlock() {

		/*
		 * cModulo = request.getParameter("iCveModulo"); cFecha =
		 * request.getParameter("dtFecha"); cCita =
		 * request.getParameter("iCveCita");
		 */
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
			cClave = (String) dEPICisCita.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * 
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
			cClave = (String) dEPICisCita.updateAlta(null, this.getInputs());
			lValidar = true;
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	}

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
			cClave = (String) dEPICisCita.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		TVEPICisCita vEPICisCita = new TVEPICisCita();
		try {
			vDespliega = dEPICisCita.FindByAll(getParameters());
			if (vDespliega.size() > 0) {
				vEPICisCita = (TVEPICisCita) vDespliega.get(0);
				iCvePersonalA = vEPICisCita.getICvePersonal();
				if (vEPICisCita.getICvePersonal() > 0)
					UpdStatus = "Hidden";
				if (lValidar)
					Obj = vEPICisCita;
			}
			if (request.getParameter("hdBoton").compareTo("Expediente") == 0) {
				// Se verifica que se realice la verificaci�n del Expediente
				if (request.getParameter("lValidado").compareToIgnoreCase(
						"false") == 0) {
					lValidar = true;
					Obj = this.getInputs();
				} else {
					lValidar = false;
					Obj = this.getInputs();
				}
			}
			// Validar la generaci�n de la alta
			if (lValidar) {
				// Llamado a la funci�n de la b�squeda
				TDPERDatos dPDatos = new TDPERDatos();
				vResValida = dPDatos.validarAlta(Obj);
			}
			if (request.getParameter("hdBoton").compareTo("Expediente") == 0
					&& !lValidar) {
				// System.out.println(request.getParameter("hdBoton").compareTo("Expediente")+" valor expediente");
				lGenerar = true;
				// Se genera el expediente
				int iCvePersonalS = Integer.parseInt(request.getParameter(
						"iCvePersonalS").toString());

				if (iCvePersonalS == 0) {
					// Generar expediente nuevo
					TFechas dtFecha = new TFechas();
					StringBuffer cWhere = new StringBuffer(), cActualiza = new StringBuffer();
					int iCveUsuario = ((TVUsuario) (((HttpServletRequest) request)
							.getSession(true).getAttribute("UsrID")))
							.getICveusuario();
					// AG SA
					// Campo Jefe Unidad Medica
					int iCveJUniMed = 0;
					String cCampo = "" + request.getParameter("iCveJUniMed");
					if (cCampo.compareTo("null") != 0
							&& cCampo.compareTo("") != 0) {
						iCveJUniMed = Integer.parseInt(cCampo.trim(), 10);
					} else {
						iCveJUniMed = 0;
					}

					HashMap hmParametros = new HashMap();
					hmParametros = this.getParameters();
					cWhere.append("where EPICisCita.iCveUniMed = ")
							.append(hmParametros.get("iCveUniMed").toString())
							.append("  and EPICisCita.dtFecha    = '")
							.append(dtFecha.getDateSQL(hmParametros.get(
									"dtFecha").toString())).append("' ")
							.append("  and EPICisCita.iCveCita   = ")
							.append(hmParametros.get("iCveCita").toString())
							.append("  and EPICisCita.iCveModulo = ")
							.append(hmParametros.get("iCveModulo").toString());

					cActualiza
							.append("where iCveUniMed = ")
							.append(hmParametros.get("iCveUniMed").toString())
							.append("  and dtFecha    = '")
							.append(dtFecha.getDateSQL(hmParametros.get(
									"dtFecha").toString())).append("' ")
							.append("  and iCveCita   = ")
							.append(hmParametros.get("iCveCita").toString())
							.append("  and iCveModulo = ")
							.append(hmParametros.get("iCveModulo").toString());

					// Obj =
					// dEPICisCita.AltaPersonal2(cWhere.toString(),iCveUsuario,
					// iCveJUniMed, cActualiza.toString());
					Obj = dEPICisCita.AltaPersonal(cWhere.toString(),
							iCveUsuario, cActualiza.toString());
				} else {
					// Asignar expediente anterior
					((TVEPICisCita) Obj).setICvePersonal(iCvePersonalS);
					Obj = dEPICisCita.updateCveExpediente(null, Obj);
				}
				vDespliega = dEPICisCita.FindByAll(getParameters());
				if (vDespliega.size() > 0) {
					vEPICisCita = (TVEPICisCita) vDespliega.get(0);
					iCvePersonalA = vEPICisCita.getICvePersonal();
					if (vEPICisCita.getICvePersonal() > 0)
						UpdStatus = "Hidden";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {

		mPk.add(request.getParameter("hdCveUniMed"));
		mPk.add(request.getParameter("hdCveModulo"));
		mPk.add(request.getParameter("hdFecha"));
		mPk.add(request.getParameter("hdCveCita"));

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
		TVEPICisCita vEPICisCita = new TVEPICisCita();
		TVUsuario usuario = (TVUsuario) this.httpReq.getSession().getAttribute(
				"UsrID"); // BEA SYSTEMS 16/10/2006
		try {
			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUniMedA(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveModuloA(iCampo);

			cCampo = "" + request.getParameter("dtFecha");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo.trim());
			} else {
				dtCampo = null;
			}
			vEPICisCita.setDtFechaA(dtCampo);

			cCampo = "" + request.getParameter("hdCveCita");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveCita(iCampo);

			cCampo = "" + request.getParameter("cHora");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCHora(cCampo.trim());

			cCampo = "" + request.getParameter("cApPaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCApPaterno(cCampo.trim());

			cCampo = "" + request.getParameter("cApMaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCApMaterno(cCampo.trim());

			cCampo = "" + request.getParameter("cNombre");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNombre(cCampo.trim());

			cCampo = "" + request.getParameter("cGenero");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCGenero(cCampo.trim());

			cCampo = "" + request.getParameter("dtNacimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo.trim());
			} else {
				dtCampo = null;
			}
			vEPICisCita.setDtNacimiento(dtCampo);

			cCampo = "" + request.getParameter("cRFC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCRFC(cCampo.trim());

			cCampo = "" + request.getParameter("cCURP");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCURP(cCampo.trim());

			// Licencias
			cCampo = "" + request.getParameter("cLicencia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCLicencia(cCampo.trim());

			cCampo = "" + request.getParameter("cLicenciaInt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCLiceniaInt(cCampo.trim());

			cCampo = "" + request.getParameter("iCvePaisNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePaisNac(iCampo);

			cCampo = "" + request.getParameter("iCveEstadoNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveEstadoNac(iCampo);

			cCampo = "" + request.getParameter("cExpediente");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCExpediente(cCampo.trim());

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCalle(cCampo.trim());

			cCampo = "" + request.getParameter("cNumExt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNumExt(cCampo.trim());

			cCampo = "" + request.getParameter("cNumInt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNumInt(cCampo.trim());

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCColonia(cCampo.trim());

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICP(iCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCiudad(cCampo.trim());

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveMunicipio(iCampo);

			cCampo = "" + request.getParameter("cTelefono");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCTelefono(cCampo.trim());

			cCampo = "" + request.getParameter("lCambioDir");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setLCambioDir(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCvePuesto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePuesto(iCampo);

			cCampo = "" + request.getParameter("iCveCategoria");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveCategoria(iCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveMotivo(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCObservacion(cCampo.trim());

			cCampo = "" + request.getParameter("iCveSituacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveSituacion(iCampo);

			cCampo = "" + request.getParameter("iCveUsuCita");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUsuCita(iCampo);

			cCampo = "" + request.getParameter("lRealizoExam");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setLRealizoExam(iCampo);

			cCampo = "" + request.getParameter("iCveUsuMCIS");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUsuMCIS(iCampo);

			cCampo = "" + request.getParameter("lProdNoConf");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setLProdNoConf(iCampo);

			cCampo = "" + request.getParameter("iCveJUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo.trim(), 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setiCveJUniMed(iCampo);

			// Se agrega el usuario BEA SYSTEMS 16/10/2006
			if (usuario != null) {
				vEPICisCita.setICveUsuCita(usuario.getICveusuario());
			}

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEPICisCita;
	}

	/**
	 * Metodo getParameters
	 */
	private HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		hmTmp.put("iCveUniMed", request.getParameter("hdCveUniMed"));
		hmTmp.put("iCveModulo", request.getParameter("hdCveModulo"));
		hmTmp.put("dtFecha", request.getParameter("hdFecha"));
		hmTmp.put("iCveCita", request.getParameter("hdCveCita"));
		return hmTmp;
	}

	public java.util.Vector getVResValida() {
		return vResValida;
	}

	public boolean getlValidar() {
		return lValidar;
	}

	public int getICvePersonalA() {
		return iCvePersonalA;
	}

	public boolean getlGenerar() {
		return lGenerar;
	}

}
