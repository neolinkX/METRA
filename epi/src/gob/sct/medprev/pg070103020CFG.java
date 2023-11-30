package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;

/**
 * <p>
 * Clase de configuración para Digitalización.
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Jaime Enrique Suárez Romero
 */
public class pg070103020CFG extends CFGListBase2 {
	public pg070103020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}
}