package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.seguridad.vo.*;

/**
 * * Clase de configuracion para EXAM Aplica
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 * @fecha 3 de marzo 2015
 * @author AG SA

 *      
 */
public class pg071003034CFG extends CFGListBase2 {
	public pg071003034CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}


	public boolean  LiberaBiometrico() {
		//TVPERDatos datos = null;
		boolean respuesta = false;
		try {
			TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
					.getAttribute("UsrID");
			String cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				respuesta = new AdminNAS().EliminaBiometricos(cCampo);
			} 
			
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return respuesta;
	}
	

	/**
	 * Metodo FillVector
	 */
	public boolean CargaModulosYServiciosAdmin(){
		boolean resultado = false;
		TDGRLUSUMedicos dUSUMedicos = new TDGRLUSUMedicos();
		try{
			resultado = dUSUMedicos.InsertAdministradores(null);
		}catch(Exception e){
			resultado = false;
		}
		return resultado;
	}

	
}
