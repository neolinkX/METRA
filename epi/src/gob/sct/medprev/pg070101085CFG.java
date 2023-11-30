package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuración para Manejador de eventos de la página JSP
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Romeo Sánchez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101085CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101085CFG.png'>
 */
public class pg070101085CFG extends CFGListBase2 {
	public pg070101085CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070101080.jsp|pg070101081.jsp";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDMEDPerfilEspec dMEDPerfilEspec = new TDMEDPerfilEspec();
		String perfil = request.getParameter("iCvePerfil");
		String where = " WHERE 1=1 ";
		if ((perfil != null && !perfil.equals("null") && !perfil.equals(""))) {
			where += " AND p.iCvePerfil = " + perfil;
		}
		if (cCondicion != null && !cCondicion.equals("")) {
			where += " AND " + cCondicion;
		}

		try {
			vDespliega = dMEDPerfilEspec.findPerfilEspec(where, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
