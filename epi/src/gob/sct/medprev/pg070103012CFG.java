package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuracion para CFG de la pagina pg070104021
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104021CFG.png'>
 */
public class pg070103012CFG extends CFGListBase2 {
	public pg070103012CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "";
		NavStatus = "Disabled";
	}

	private StringBuffer activeX = new StringBuffer("");

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEXPResultado dEXPResultado = new TDEXPResultado();
		Vector vcReporte = new Vector();
		try {

			// vDespliega =
			// dEXPResultado.FindDsc(request.getParameter("hdiCveExpediente"),
			// request.getParameter("hdiNumExamen"),
			// request.getParameter("hdICveServicio"));
			// iNumReg = vDespliega.size();

			/*
			 * if (request.getParameter("hdReporte") != null){ if
			 * (request.getParameter("hdReporte").compareToIgnoreCase(
			 * "Reporte") == 0) { vcReporte = vDespliega;
			 * 
			 * activeX.append(this.GenRep(vcReporte));
			 * 
			 * } }
			 */

			vDespliega = new Vector();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public StringBuffer GenRep(Object obj) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("pg070104011");
		StringBuffer buffer = new StringBuffer();
		TVEXPResultado vEXPResultado = new TVEXPResultado();
		Vector vcResultado = new Vector();
		TFechas dtFecha = new TFechas("07");
		int iServicioCardio = new Integer(
				vParametros.getPropEspecifica("EPIServicioCardio")).intValue();
		int iRamaCardio = new Integer(
				vParametros.getPropEspecifica("EPIRamaCardio")).intValue();
		int iSintomaCardio = new Integer(
				vParametros.getPropEspecifica("EPISintomaCardio")).intValue();

		try {
			vcResultado = (Vector) obj;
			for (int j = 0; j < vcResultado.size(); j++) {
				vEXPResultado = (TVEXPResultado) vcResultado.get(j);
				if (vEXPResultado.getICveServicio() == iServicioCardio
						&& vEXPResultado.getICveRama() == iRamaCardio
						&& vEXPResultado.getICveSintoma() == iSintomaCardio) {
					xl.comDespliega("C", 8, vEXPResultado.getCCaracter());
					xl.comDespliega("I", 13, request.getParameter("hdCNombre"));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Generar Reporte : " +
			// ex.getMessage());
		}
		buffer = xl.creaActiveX("pg070104011", cNomArchivo, false, false, true);
		return buffer;
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

}