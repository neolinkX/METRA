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
 * * Clase de configuracion para CFG de la pagina pg070104011
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104011CFG.png'>
 */
public class pg070104011CFG extends CFGListBase2 {
	public pg070104011CFG() {
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
			vDespliega = dEXPResultado.FindDsc(
					request.getParameter("hdiCveExpediente"),
					request.getParameter("hdiNumExamen"),
					request.getParameter("hdICveServicio"),
					request.getParameter("hdICveRama"));
			iNumReg = vDespliega.size();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public StringBuffer GenRep(Object obj) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("pg070306031");
		int iRenglon = 0;
		int iFilasxPag = 65;
		int iPag = 0;
		int iPagAnt = 0;
		int iRenIni = 0;
		int iRenFin = 0;
		int iRenxPag = 4;
		int iCvePersonal = 0;
		int iCveUniMed = 0;
		int iCveProceso = 0;
		String cPersonal = "";
		String cCveUniMed = "";
		String cCveProceso = "";
		String pName = "";
		String pEmpresa = "";
		String Doctor = "";
		StringBuffer buffer = new StringBuffer();
		java.sql.Date dtConcluido;
		String dFinalExamen = "";
		int lDictamen = 1;
		TVEXPResultado vEXPResultado = new TVEXPResultado();
		Vector vcResultado = new Vector();
		TFechas dtFecha = new TFechas("07");

		try {
			vcResultado = (Vector) obj;

			if (vcResultado.size() > 0) {
				vEXPResultado = (TVEXPResultado) vcResultado.get(0);
			}

			xl.comDespliega("C", 8, "Diagnostico");
			xl.comDespliega("I", 13, "Doctor");
			// xl.comDespliega("B", 34, vTOXBaja.getCInactiva());
			// xl.comDespliega("B", 38, vTOXBaja.getCObservacion());
		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Generar Reporte : " +
			// ex.getMessage());
		}
		buffer = xl.creaActiveX("pg070306031", cNomArchivo, false, false, true);
		return buffer;
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

}