package gob.sct.medprev;

import java.util.*;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;

/**
 * * Clase de configuracion para Listado de TOXCtrolCalibra
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. Gonzï¿½lez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070303060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070103040CFG.png'>
 */
public class pg070103040CFG extends CFGListBase2 {
	public pg070103040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		String cWhere = "";
		String cClave = "";
		String cActualiza = "";
		String cFecha = "";
		TFechas dtFecha = new TFechas();
		int iUser = 0;
		try {
			HashMap hmParams = getParameters();
			TDEPICisCita dEPICisCita = new TDEPICisCita();
			if (request.getParameter("hdBoton").compareTo("Expediente") == 0) {

				if (hmParams.get("iCveUniMed") == null) {
					hmParams.put("iCveUniMed", "-1");
				}

				cWhere = "where EPICisCita.iCveUniMed = "
						+ request.getParameter("hdCveUniMed") + " ";
				cWhere = cWhere + "and EPICisCita.iCveModulo = "
						+ request.getParameter("hdCveModulo") + " ";
				cWhere = cWhere + "and EPICisCita.dtFecha = '"
						+ dtFecha.getDateSQL(request.getParameter("hdFecha"))
						+ "' ";
				cWhere = cWhere + "and EPICisCita.iCveCita = "
						+ request.getParameter("hdCveCita") + " ";

				iUser = Integer.parseInt(request.getParameter("hdUsuario"));

				cActualiza = " where iCveUniMed = "
						+ request.getParameter("hdCveUniMed") + " "
						+ "and dtFecha =  '"
						+ dtFecha.getDateSQL(request.getParameter("hdFecha"))
						+ "' " + "and iCveCita =  "
						+ request.getParameter("hdCveCita") + " "
						+ "and iCveModulo = "
						+ request.getParameter("hdCveModulo") + " ";

				dEPICisCita.AltaPersonal(cWhere, iUser, cActualiza);
				vDespliega = new TDEPICisCita().FindConsultaCitas(hmParams);
				iNumReg = vDespliega.size(); // Despliega todos registros

			} else {
				if (hmParams.get("iCveUniMed") == null) {
					hmParams.put("iCveUniMed", "-1");
				}
				vDespliega = new TDEPICisCita().FindConsultaCitas(hmParams);
				iNumReg = vDespliega.size(); // Despliega todos registros
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo getUniMedsValidas
	 */
	public Vector getUniMedsValidas() {
		Vector vcUMValidas = null;
		try {
			TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true)
					.getAttribute("UsrID");
			vcUMValidas = new TDGRLUMUsuario().getUniMedxUsu(vUsuario
					.getICveusuario());
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcUMValidas;
	}

	/**
	 * Metodo getModulos
	 */
	public Vector getModulos() {
		Vector vcModulos = null;
		try {
			String cTmp = request.getParameter("iCveUniMed");
			if (cTmp != null) {
				vcModulos = new TDGRLModulo().getComboModulos(Integer
						.parseInt(cTmp));
			}
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcModulos;
	}

	/**
	 * Metodo getModulos
	 */
	public Vector getModulos(String cCveUniMed) {
		Vector vcModulos = null;
		try {
			if (cCveUniMed != null) {
				vcModulos = new TDGRLModulo().getComboModulos(Integer
						.parseInt(cCveUniMed));
			}
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcModulos;
	}

	/**
	 * Metodo getHorasDeCitas
	 */
	public Vector getHorasDeCitas() {
		Vector vcHoras = null;
		try {
			HashMap hmParams = getParameters();
			if (hmParams.get("iCveUniMed") == null) {
				hmParams.put("iCveUniMed", "-1");
			}
			vcHoras = new TDEPICisCita().getHorasDeCitas(hmParams);
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcHoras;
	}

	/**
	 * Metodo getHorasDeCitas
	 */
	public Vector getHorasDeCitas(String cCveUniMed, String cCveModulo,
			String cFecha) {
		Vector vcHoras = null;
		try {
			HashMap hmParams = new HashMap();
			hmParams.put("iCveUniMed", cCveUniMed);
			hmParams.put("iCveModulo", cCveModulo);
			hmParams.put("dtFecha", cFecha);
			if (hmParams.get("iCveUniMed") == null) {
				hmParams.put("iCveUniMed", "-1");
			}
			vcHoras = new TDEPICisCita().getHorasDeCitas(hmParams);
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcHoras;
	}

	/**
	 * Metodo getParameters
	 */
	private HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		hmTmp.put("iCveUniMed", request.getParameter("iCveUniMed"));
		hmTmp.put("iCveModulo", request.getParameter("iCveModulo"));
		hmTmp.put("dtFecha", request.getParameter("dtFecha"));
		hmTmp.put("cHora", request.getParameter("cHora"));

		return hmTmp;
	}
public boolean validaCURP2(String cCURP){
		
		if(cCURP.matches(
				"[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
				"(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
				"[HM]{1}" +
				"(AS|BC|BS|CC|CH|CL|CS|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
				"[B-DF-HJ-NP-TV-Z]{3}" +
				"[0-9A-Z]{1}[0-9]{1}$")){//AAAA######AAAAAA##
			
			return true;
		}
		return false;
	}	
	/*
	 * Valida que la CURP de cita deEPICisCita es valida y no se encuentra en PERDATOS 
	 * @author Ing. Andres Esteban Bernal Muñoz
	 * */
	public int checkCURP(String cCveUniMed,String cCveModulo,String cFecha,String cCveCita){
		TDEPICisCita dEPICisCita =new TDEPICisCita();
		TDPERDatos dPERDatos = new TDPERDatos();
		String cCURP="";
		//String aMSG="";
		int count;
		int regresa=0;
		try {
			cCURP=dEPICisCita.FindCURPByCita(cCveUniMed,cCveModulo,cFecha,cCveCita);
			//aMSG="curp: "+cCURP+"\n";
			
			if(!dPERDatos.validaCURP2(cCURP)){
				return 1;
			}
			count=dPERDatos.iCURP(cCURP);
			if(count>0){
				//aMSG+="ya esta en sistema: "+count+"\n";
				return 2;
			}
			
		} catch (DAOException e) {
			System.out.println(e.toString());
		}
		return regresa;
	}

}