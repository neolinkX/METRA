package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Generales - Catalogo de Mensajeria
 * 
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p> 
 * @author <dd>Ing. Andres Esteban Bernal Munoz 01/07/2014
 *          
 */

public class pg071001033CFG extends CFGListBase2 {
	
	public pg071001033CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";		
		cPaginas = "pg071001034.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {		
		TDToxMensajeria dToxMensajeria = new TDToxMensajeria();
		
		try {
			String cWhere = "";
			if (cCondicion.compareTo("") != 0)
				cWhere = " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else				
				cWhere += " order by iCveMensajeria ";
		
			vDespliega = dToxMensajeria.FindByAll();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}