/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev;

import gob.sct.medprev.dao.TDSEGUsuario;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.seguridad.dao.CFGListBase2;

/**
 * 
 * @author admin
 */
public class pg070107091CFG extends CFGListBase2 {

	public pg070107091CFG() {
		this.vParametros = new TParametro("07");
		// cPaginas = "pg070104021.jsp"; // Ir a...
	}
	
	
	public void CombpruebaUsuarios() throws DAOException{
		TDSEGUsuario dSEGUsuario = new TDSEGUsuario();
		int respuesta = 0;
		respuesta = dSEGUsuario.insertSincronizaUsuarios(null);
	}
	
	
}
