package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.TVUsuario;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

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
 * @author <dd>Marco A. Gonzalez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070102020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070102033CFG.png'>
 */
public class pg070102033CFG extends CFGCatBase2 {

	private String cCita;
	private String cModulo;
	private String cFecha;

	public pg070102033CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070103050.jsp";
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
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
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
			
			cClave = (String) dEPICisCita.updateNombre(null, this.getInputs());
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
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		try {
			cClave = (String) dEPICisCita.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		TVEPICisCita vEPICisCita = new TVEPICisCita();
		try {

			vDespliega = dEPICisCita.FindByAll2(getParameters());
			if (vDespliega.size() > 0) {
				vEPICisCita = (TVEPICisCita) vDespliega.get(0);
				if (vEPICisCita.getICvePersonal() > 0)
					UpdStatus = "Hidden";
			}

		} catch (Exception ex) {
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
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUniMedA(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveModuloA(iCampo);

			cCampo = "" + request.getParameter("dtFecha");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisCita.setDtFechaA(dtCampo);

			cCampo = "" + request.getParameter("hdCveCita");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveCita(iCampo);

			cCampo = "" + request.getParameter("cHora");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCHora(cCampo);

			cCampo = "" + request.getParameter("cApPaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCApPaterno(cCampo);

			cCampo = "" + request.getParameter("cApMaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCApMaterno(cCampo);

			cCampo = "" + request.getParameter("cNombre");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNombre(cCampo);

			cCampo = "" + request.getParameter("cGenero");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCGenero(cCampo);

			if(cCampo.equals("M")){
				cCampo = "H";
			}
			if(cCampo.equals("F")){
				cCampo = "M";
			}
			vEPICisCita.setCSexo_DGIS(cCampo);
			
			
			
			
			cCampo = "" + request.getParameter("dtNacimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				cCampo = cCampo.charAt(6)+""+cCampo.charAt(7)+"/"+cCampo.charAt(4)+""+cCampo.charAt(5)+"/"+cCampo.charAt(0)+""+cCampo.charAt(1)+""+cCampo.charAt(2)+""+cCampo.charAt(3);
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisCita.setDtNacimiento(dtCampo);
/*
			cCampo = "" + request.getParameter("dtNacimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisCita.setDtNacimiento(dtCampo);*/			
			

			cCampo = "" + request.getParameter("cRFC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCRFC(cCampo);

			cCampo = "" + request.getParameter("cCURP");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCURP(cCampo);

			cCampo = "" + request.getParameter("iCvePaisNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePaisNac(iCampo);

			cCampo = "" + request.getParameter("iCveEstadoNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveEstadoNac(iCampo);

			cCampo = "" + request.getParameter("cExpediente");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCExpediente(cCampo);

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cNumExt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNumExt(cCampo);

			cCampo = "" + request.getParameter("cNumInt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCNumInt(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCColonia(cCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICP(iCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCCiudad(cCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveMunicipio(iCampo);

			cCampo = "" + request.getParameter("cTelefono");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCTelefono(cCampo);

			cCampo = "" + request.getParameter("lCambioDir");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setLCambioDir(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCvePuesto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICvePuesto(iCampo);

			cCampo = "" + request.getParameter("iCveCategoria");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveCategoria(iCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveMotivo(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisCita.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("iCveSituacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveSituacion(iCampo);

			cCampo = "" + request.getParameter("iCveUsuCita");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUsuCita(iCampo);

			cCampo = "" + request.getParameter("lRealizoExam");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setLRealizoExam(iCampo);

			cCampo = "" + request.getParameter("iCveUsuMCIS");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setICveUsuMCIS(iCampo);

			cCampo = "" + request.getParameter("lProdNoConf");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisCita.setLProdNoConf(iCampo);

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

}