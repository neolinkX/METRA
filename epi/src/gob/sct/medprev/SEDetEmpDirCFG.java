package gob.sct.medprev;

import java.util.Vector;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
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
 *      <img src='pg070102020CFG.png'>
 */
public class SEDetEmpDirCFG extends CFGCatBase2 {

	public SEDetEmpDirCFG() {
		vParametros = new TParametro("07");
		// DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDPERDatos dPERDatos = new TDPERDatos();
		TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
		try {
			vDespliega = dGRLEmpresas.DetalleDirEmpresa(Integer
					.parseInt(request.getParameter("hdiCveEmpresa")));
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

}