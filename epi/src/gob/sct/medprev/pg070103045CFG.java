package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.dwr.vo.*;

/**
 * * Clase de configuracion para CFG Registro de Citas
 * 
 * @author <dd>AG SA.
 */
public class pg070103045CFG extends CFGCatBase2 {
	public pg070103045CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070103050.jsp";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDPERDatos dPERDatos = new TDPERDatos();
		try {
			vDespliega = dPERDatos.FindByPersona2(request
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
	 * */
	public void GuardarA() {
		TDPERDatos dPERDatos = new TDPERDatos();
		TDPERAdicional dPERAdicional = new TDPERAdicional();
		ExpBitMod mod = new ExpBitMod();
		TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true).getAttribute(
				"UsrID");
		try {
			int iCampo = 0;
			String cCampo = "" + request.getParameter("iCveNumEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			}
			int iCURP=0;
			if(!this.validaCURP() && request.getParameter("cCURP").toString().length() == 18){
				vErrores.acumulaError("", 24, "");
				iCURP++;
			}
			if(!this.validaCURP2(request.getParameter("cCURP"))){
				vErrores.acumulaError("", 25, "");
				iCURP++;
			}			
			if(iCURP==0){
				cClave = (String) dPERDatos.update(null, this.getInputs());
				System.out.println(cClave.toString());
				if (iCampo == 0)
					cClave = (String) dPERDatos.UPerEmpresa(null, this.getInputs(),
							vUsuario.getICveusuario());
				// dPERAdicional.FicId(null, this.getInputs());
				
				
				mod.setiCveExpediente(request.getParameter("cExpediente2"));
				mod.setiNumExamen("0");
				mod.setOperacion("17");
				mod.setDescripcion("Los datos previos a la modificación eran Nombre: "
						+ request.getParameter("cApPaterno2")
						+ " "
						+ request.getParameter("cApMaterno2")
						+ " "
						+ request.getParameter("cNombre2")
						+ ", "
						+ "RFC: "
						+ request.getParameter("cRFC2")
						+ ", CURP: "
						+ request.getParameter("cCURP2") + " ");
				mod.setiCveUsuarioRealiza(String.valueOf(vUsuario.getICveusuario()));
				mod.setIpAddress(request.getParameter("hdIpAddress"));
				mod.setMacAddress(request.getParameter("hdMacAddress"));
				mod.setComputerName(request.getParameter("hdComputerName"));
	
				// mod.setiCveServicio(cServicio);
				int insert = new TDEXPBITMOD().insertServiciosIpMacName(null, mod);
			}

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

			if (request.getParameter("cSexo") != null)
				cCampo = new String(request.getParameter("cSexo").getBytes(
						"ISO-8859-1"), "UTF-8");
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
			
			
			if (request.getParameter("cNombre") != null)
				cCampo = new String(request.getParameter("cNombre").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCNombre(cCampo);
			
			

			if (request.getParameter("cApPaterno") != null)
				cCampo = new String(request.getParameter("cApPaterno")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCApPaterno(cCampo);

			if (request.getParameter("cApMaterno") != null)
				cCampo = new String(request.getParameter("cApMaterno")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCApMaterno(cCampo);

			if (request.getParameter("cRFC") != null)
				cCampo = new String(request.getParameter("cRFC").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCRFC(cCampo);

			if (request.getParameter("cHomoclave") != null)
				cCampo = new String(request.getParameter("cHomoclave")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCHomoclave(cCampo);

			if (request.getParameter("cCURP") != null)
				cCampo = new String(request.getParameter("cCURP").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCCURP(cCampo);

			if (request.getParameter("cLicencia") != null)
				cCampo = new String(request.getParameter("cLicencia").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCLicencia(cCampo);

			if (request.getParameter("cLicenciaInt") != null)
				cCampo = new String(request.getParameter("cLicenciaInt")
						.getBytes("ISO-8859-1"), "UTF-8");
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

			if (request.getParameter("cExpediente") != null)
				cCampo = new String(request.getParameter("cExpediente")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCExpediente(cCampo);

			if (request.getParameter("cSenasPersonal") != null)
				cCampo = new String(request.getParameter("cSenasPersonal")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			} else {
				cCampo = cCampo.replace("'", "");
				cCampo = cCampo.replace("\n", "");
				cCampo = cCampo.replace("/n", "");
			}
			vPERDatos.setCSenasPersonal(cCampo);

			if (request.getParameter("cCorreoElec") != null)
				cCampo = new String(request.getParameter("cCorreoElec")
						.getBytes("ISO-8859-1"), "UTF-8");
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

			if (request.getParameter("cPersonaAviso") != null)
				cCampo = new String(request.getParameter("cPersonaAviso")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			} else {
				iCampo = 0;
			}
			vPERDatos.setCPersonaAviso(cCampo);

			if (request.getParameter("cDirecAviso") != null)
				cCampo = new String(request.getParameter("cDirecAviso")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCDirecAviso(cCampo);

			if (request.getParameter("cTelAviso") != null)
				cCampo = new String(request.getParameter("cTelAviso").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vPERDatos.setCTelAviso(cCampo);

			if (request.getParameter("cCorreoAviso") != null)
				cCampo = new String(request.getParameter("cCorreoAviso")
						.getBytes("ISO-8859-1"), "UTF-8");
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
				if (request.getParameter("cDscEmpresa2") != null)
					cCampo = new String(request.getParameter("cDscEmpresa2")
							.getBytes("ISO-8859-1"), "UTF-8");
				if (cCampo.compareTo("null") == 0) {
					cCampo = "";
				}
				vPERDatos.setCDscEmpresaTmp(cCampo);
			}

			// DATOS ADICIONALES
			/*
			 * if(request.getParameter("cTel")!= null) cCampo = new
			 * String(request
			 * .getParameter("cTel").getBytes("ISO-8859-1"),"UTF-8"); if
			 * (cCampo.compareTo("null") == 0) { cCampo = ""; }
			 * vPERDatos.setCTelefono2(cCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveVivienda"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vPERDatos.setICveVivienda(iCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveDiscapacidad"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vPERDatos.setICveDiscapacidad(iCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveGpoEtnico"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vPERDatos.setICveGpoEtnico(iCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveReligiones"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vPERDatos.setICveReligion(iCampo);
			 * 
			 * 
			 * cCampo = "" + request.getParameter("iCveNivelSEC"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vPERDatos.setICveNivelSEC(iCampo);
			 * 
			 * 
			 * cCampo = "" + request.getParameter("iCveParPol"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vPERDatos.setICveParPOL(iCampo);
			 * 
			 * cCampo = "" + request.getParameter("iCveECivil"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vPERDatos.setICveECivil(iCampo);
			 * 
			 * 
			 * cCampo = "" + request.getParameter("iCveTpoSangre"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vPERDatos.setICveTpoSangre(iCampo);
			 */

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
	/* Valida Inexistencia de CURP */
	public boolean validaCURP(){
		TDPERDatos dPERDatos = new TDPERDatos();
				
		try{
			if(dPERDatos.ValidaCURP2(request.getParameter("cCURP"),request.getParameter("cExpediente2"))== 0){
				return true;
			}	
			return false;
			
		}catch(Exception ex){
			error("Error al obtener CURP", ex);			
		}
		
		return false;
	}
	/**
	 * Valida nomenclatura de CURP 
	 * */
	public boolean validaCURP2(String cCURP){
		
		if(cCURP.matches(
				"[A-Z]{4}[0-9]{2}" +
				"(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
				"[HM]{1}" +
				"(AS|BC|BS|CC|CH|CL|CS|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
				"[B-DF-HJ-NP-TV-Z]{3}" +
				"[0-9A-Z]{1}[0-9]{1}$")){//AAAA######AAAAAA##
			
			return true;
		}
		return false;
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