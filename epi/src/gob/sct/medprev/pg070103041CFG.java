package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

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
 *      <img src='pg070102010CFG.png'>
 */
public class pg070103041CFG extends CFGCatBase2 {
	public pg070103041CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070106050.jsp";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDPERDatos dPERDatos = new TDPERDatos();
		try {
			vDespliega = dPERDatos.FindByPersona(request
					.getParameter("hdICvePersonal"));
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
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDPERDatos dPERDatos = new TDPERDatos();
		try {
			cClave = (String) dPERDatos.insert(null, this.getInputs());
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
		TDPERDatos dPERDatos = new TDPERDatos();
		TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true).getAttribute(
				"UsrID");
		try {
			int iCampo = 0;
			String cCampo = "" + request.getParameter("iCveNumEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			}
			cClave = (String) dPERDatos.update2(null, this.getInputs());
			if (iCampo == 0)
				cClave = (String) dPERDatos.UPerEmpresa(null, this.getInputs(),
						vUsuario.getICveusuario());
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
		TDPERDatos dPERDatos = new TDPERDatos();
		try {
			cClave = (String) dPERDatos.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVPERDatos vPERDatos = new TVPERDatos();
		try {

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDatos.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDatos.setICveExpediente(iCampo);

			cCampo = "" + request.getParameter("cSexo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCSexo(cCampo);
			
			if(cCampo.equals("M")){
				cCampo = "H";
			}
			if(cCampo.equals("F")){
				cCampo = "M";
			}
			vPERDatos.setCSexo_DGIS(cCampo);
			
			

			cCampo = "" + request.getParameter("cNombre");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCNombre(cCampo);

			cCampo = "" + request.getParameter("cApPaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCApPaterno(cCampo);

			cCampo = "" + request.getParameter("cApMaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCApMaterno(cCampo);

			cCampo = "" + request.getParameter("cRFC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCRFC(cCampo);

			cCampo = "" + request.getParameter("cHomoclave");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCHomoclave(cCampo);

			cCampo = "" + request.getParameter("cCURP");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCCURP(cCampo);

			cCampo = "" + request.getParameter("cLicencia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCLicencia(cCampo);

			cCampo = "" + request.getParameter("cLicenciaInt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCLicenciaInt(cCampo);

			cCampo = "" + request.getParameter("dtNacimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vPERDatos.setDtNacimiento(dtCampo);

			cCampo = "" + request.getParameter("iCvePaisNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDatos.setICvePaisNac(iCampo);

			cCampo = "" + request.getParameter("iCveEstadoNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDatos.setICveEstadoNac(iCampo);

			cCampo = "" + request.getParameter("cExpediente");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCExpediente(cCampo);

			cCampo = "" + request.getParameter("cSenasPersonal");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			} else {
				cCampo = cCampo.replace("'", "");
				cCampo = cCampo.replace("\n", "");
				cCampo = cCampo.replace("/n", "");
			}
			vPERDatos.setCSenasPersonal(cCampo);

			cCampo = "" + request.getParameter("cCorreoElec");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCCorreoElec(cCampo);

			/*
			 * cCampo = "" + request.getParameter("lDonadorOrg"); if
			 * (cCampo.compareTo("ON") == 0) { iCampo = 1; } else { iCampo = 0;
			 * } vPERDatos.setLDonadorOrg(iCampo);
			 */
			vPERDatos.setLDonadorOrg(0);

			cCampo = "" + request.getParameter("cPersonaAviso");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			} else {
				iCampo = 0;
			}
			vPERDatos.setCPersonaAviso(cCampo);

			cCampo = "" + request.getParameter("cDirecAviso");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCDirecAviso(cCampo);

			cCampo = "" + request.getParameter("cTelAviso");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCTelAviso(cCampo);

			cCampo = "" + request.getParameter("cCorreoAviso");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCCorreoAviso(cCampo);

			// Empresa

			cCampo = "" + request.getParameter("iCveNumEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERDatos.setICveNumEmpresa(iCampo);

			if (iCampo == 0) {
				cCampo = "" + request.getParameter("cDscEmpresa2");
				if (cCampo.compareTo("null") == 0) {
					cCampo = "";
				}
				vPERDatos.setCDscEmpresaTmp(cCampo);
			}

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vPERDatos;
	}

	/**
	 * Metodo FillVector
	 */
	public String NombreTemp(String NE, String Exp) {
		TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
		String EmpTmp = "";
		try {
			EmpTmp = dGRLEmpresas
					.FindByAllR("SELECT T.CDSCEMPRESA FROM PEREMPRESATMP AS T WHERE T.ICVEPERSONAL = "
							+ Exp
							+ " AND 0 =  (SELECT ICVEEMPRESA FROM GRLEMPRESAS AS G WHERE G.CDSCEMPRESA='"
							+ NE + "')");
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		return EmpTmp;
	}

	/**
	 * Metodo FillVector
	 */
	public String Temporal() {
		TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
		String EmpTmp = "";
		try {
			EmpTmp = dGRLEmpresas
					.FindByAllR("SELECT CDSCEMPRESA FROM GRLEMPRESAS WHERE ICVEEMPRESA = 0");
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		return EmpTmp;
	}
	


	/**
	 * Validando que el Usuario sea Medico Tercero
	 */
	public boolean getBloqueaGeneraCita(String Usuario, int iCveUsuario) {
		boolean Bloquear = false;
		Usuario = Usuario.toLowerCase();
		TDSEGUsuExp dSEGUsuExp = new TDSEGUsuExp();
		try {
			//System.out.println(Usuario.substring(0, 3));
			if(Usuario.substring(0, 3).toString().equals("ext")){
				Bloquear = true;
			}
			if(Bloquear){
				Bloquear = dSEGUsuExp.FindByEsTercero(iCveUsuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Bloquear;
	}
}