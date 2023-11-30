package gob.sct.medprev;

import java.util.*;
import java.text.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.sql.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import gob.sct.ingresosws.ws.ConUsrIng.*;
import gob.sct.ingresosws.ws.ConAreaRec.TVConceptoII;
import gob.sct.ingresos.TEncriptaDatos;
import com.micper.util.TNumeros;
import com.micper.util.TDIngresos;
import com.micper.util.TFechas;

/**
 * * Clase de configuracion para Listado de TOXCtrolCalibra
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070103010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070103010CFG.png'>
 */
public class pg070103014CFG {

	private String cParametros;
	private TVDinRep02 vInfoIng;
	private TParametro vParametros;

	public pg070103014CFG() {
		vParametros = new TParametro("07");
	}

	/**
	 * Metodo guardarA
	 */

	public void GuardarA(TVDinRep02 vInfoEnv) {
		DbConnection dbConn = null;
		java.sql.Connection conn = null;
		try {
			dbConn = new DbConnection(
					vParametros.getPropEspecifica("ConDBModulo"));
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(java.sql.Connection.TRANSACTION_READ_COMMITTED);
			// Informaci�n del Expdiente
			// System.out.println("cInfoCat =" +
			// vInfoEnv.getString("cInfoCat"));

			StringTokenizer stReferencias = new StringTokenizer(
					vInfoEnv.getString("cInfoCat"), "|");
			String[] cEntradas = new String[stReferencias.countTokens()];
			int iCont = 0;
			while (stReferencias.hasMoreElements()) {
				cEntradas[iCont++] = stReferencias.nextToken();
			}

			String[] cRefAlfaNum = vInfoEnv.getString("cRefAlfaNum").split(",");
			String[] cRefNumerica = vInfoEnv.getString("cRefNumerica").split(
					",");

			// Obtener informaci�n
			Vector vReferencias = new Vector();
			String cDatos = cEntradas[0];
			if (cEntradas[0].length() > 0) {
				TVEXPExamCat vExamCat = new TVEXPExamCat();
				String[] cIdentif = cEntradas[0].toString().split(",");
				// System.out.println("Existen las claves" +
				// Integer.parseInt(cIdentif[0]) + " - " +
				// Integer.parseInt(cIdentif[1]));

				if (Integer.parseInt(cIdentif[0]) > 0
						&& Integer.parseInt(cIdentif[1]) > 0) {
					int iCveExpediente = Integer.parseInt(cIdentif[0]), iNumExamen = Integer
							.parseInt(cIdentif[1]);

					for (int i = 1; i < cEntradas.length; i++) {
						String[] cCateg = cEntradas[i].split(",");
						vExamCat = new TVEXPExamCat();
						vExamCat.setICveExpediente(iCveExpediente);
						vExamCat.setINumExamen(iNumExamen);
						vExamCat.setICveMdoTrans(Integer.parseInt(cCateg[0]));
						vExamCat.setICveCategoria(Integer.parseInt(cCateg[1]));
						if (cRefAlfaNum.length == 2 && i > 1) {
							vExamCat.setCRefAlfanum(cRefAlfaNum[1].substring(5));
							vExamCat.setIRefNumerica(Integer
									.parseInt(cRefNumerica[1]));
							// System.out.println("MdoTrans/Cat" +cCateg[0] +
							// "-" + cCateg[1] + "Datos actualizar = " +
							// cRefAlfaNum[1].substring(5) + "-" +
							// cRefNumerica[1]);
						} else {
							vExamCat.setCRefAlfanum(cRefAlfaNum[0].substring(5));
							vExamCat.setIRefNumerica(Integer
									.parseInt(cRefNumerica[0]));
							// System.out.println("MdoTrans/Cat" +cCateg[0] +
							// "-" + cCateg[1] + "Datos actualizar = " +
							// cRefAlfaNum[0].substring(5) + "-" +
							// cRefNumerica[0]);
						}
						vReferencias.add(vExamCat);
					} // Barrer las entradas

					TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
					String cClave = (String) dEXPExamCat.updateRef(conn,
							vReferencias);
				} // Se enviaron las claves para expediente y n�mero de examen
			} // Se enviaron los datos adicionales
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
			}
		}
	} // Metodo GuardarA

}
