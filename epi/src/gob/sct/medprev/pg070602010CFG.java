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
 * * Clase de configuracion para CFG Listado de Equipos
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070602010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070602010CFG.png'>
 */
public class pg070602010CFG extends CFGListBase2 {
	public Vector vTpoEquipo = new Vector();
	public Vector vClasif = new Vector();

	public pg070602010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070602011.jsp|";

	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		TDEQMClasificacion dClasif = new TDEQMClasificacion();
		TVEQMClasificacion tvClasif = new TVEQMClasificacion();

		String iCveClasif = "";
		String iCveTpoEq = "";

		if (request.getParameter("iCveClasificacion") != null
				&& !request.getParameter("iCveClasificacion").equals(""))
			iCveClasif = request.getParameter("iCveClasificacion").toString();
		if (request.getParameter("iCveTpoEquipo") != null
				&& !request.getParameter("iCveTpoEquipo").equals(""))
			iCveTpoEq = request.getParameter("iCveTpoEquipo").toString();

		try {
			vClasif = dClasif.FindByAll(" where lActivo=1 ",
					" order by iCveClasificacion ");
			tvClasif.setCDscClasificacion("Seleccione...");
			tvClasif.setICveClasificacion(0);
			vClasif.insertElementAt(tvClasif, 0);
			String q = " where 1=1 ";
			if (!iCveClasif.equals("") && !iCveClasif.equals("0"))
				q += " and EqmEquipo.iCveClasificacion=" + iCveClasif;
			if (!iCveTpoEq.equals("") && !iCveTpoEq.equals("0"))
				q += " and EQMEquipo.iCveTpoEquipo=" + iCveTpoEq;
			if (!cCondicion.equals(""))
				q += " and " + cCondicion + " ";
			vDespliega = dEQMEquipo.FindByAllDesc(q + cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}