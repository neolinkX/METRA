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
 * * Clase de configuracion para CFG Lista de Refrigeradores
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301080CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301080CFG.png'>
 */
public class pg070301080CFG extends CFGListBase2 {
	public pg070301080CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		NavStatus = "Disabled";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXRefrigerador dTOXRefrigerador = new TDTOXRefrigerador();
		String cFiltro = "";
		if (this.getLPagina("pg070301081.jsp"))
			cPaginas = "pg070301081.jsp|";

		if (cOrden.compareToIgnoreCase("") == 0)
			cOrden = " order by iCveRefrigerador ";

		try {
			if (cCondicion.compareTo("") != 0) {
				cFiltro = " and " + cCondicion + " " + cOrden;
			} else {
				cFiltro = cOrden;
			}
			vDespliega = dTOXRefrigerador.FindByAll(cFiltro);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}