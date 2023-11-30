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
import com.micper.util.caching.*;

/**
 * * Clase de configuracion para CFG para Listado de Empresas
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070502010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070502010CFG.png'>
 */
public class pg070502010CFG extends CFGListBase2 {
	public Vector labs = new Vector();
	public TVGRLUniMed tvUniMed = new TVGRLUniMed();

	public pg070502010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070502011.jsp";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLEmpresas dEmpresas = new TDGRLEmpresas();
		String cUniMed = "0";
		String iMdoTrans = "0";
		String cWhereMdo = "";
		String cWhereUni = "";
		String where = "";
		labs = (Vector) AppCacheManager.getColl("GRLUniMed", "");
		tvUniMed.setCDscUniMed("Sin Asignar");
		tvUniMed.setICveUniMed(0);
		labs.add(tvUniMed);

		if (request.getParameter("iCveMdoTrans") != null
				&& !request.getParameter("iCveMdoTrans").equals("")) {
			iMdoTrans = request.getParameter("iCveMdoTrans").toString();
		}
		if (request.getParameter("iCveUniMed") != null
				&& !request.getParameter("iCveUniMed").equals("")) {
			cUniMed = request.getParameter("iCveUniMed").toString();
		}

		// Modo de transporte
		if (iMdoTrans.equals("0")) {
			cWhereMdo = "";
		} else {
			cWhereMdo = "iCveMdoTrans=" + iMdoTrans;
		}

		// Unidad M�dica
		if (cUniMed.equals("0")) {
			cWhereUni = "iCveUniMed=" + cUniMed;
		} else {
			cWhereUni = "iCveUniMed=" + cUniMed;
		}

		// Bean Scroller
		try {
			if (!cWhereMdo.equals("")) {
				where += cWhereMdo;
				if (!cWhereUni.equals(""))
					where = where + " and " + cWhereUni;
			} else {
				if (!cWhereUni.equals("")) {
					where += cWhereUni;
				} else {
					where = " ";
				}
			}
			if (!where.equals("")) {
				if (!where.equals(" "))
					where = " where " + where;
				if (!where.equals(" ") && !where.equals("")
						&& !cCondicion.equals("")) {
					where = where + " and " + cCondicion;
				} else if (!cCondicion.equals("")) {
					where = " where " + cCondicion;
				}

				if (cOrden.compareTo("") == 0)
					cOrden = " order by cIDDGPMPT";
				vDespliega = dEmpresas.FindByAll(where + cOrden);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}