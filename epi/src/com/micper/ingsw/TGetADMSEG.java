package com.micper.ingsw;

import com.micper.util.*;
import java.util.*;
import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;

/**
 * Clase encargada de obtener los valores del archivo globales.properties.
 * <p>
 * En esta clase se conservar�n los par�metros de configuraci�n de cada m�dulo
 * en un solo archivo de propiedades.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>Jos� AG L�pez Hern�ndez
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-*.jsp\n-pgXXXXXXXCFG.java\n-Archivos de ingenieria de software com.micper.ingsw.*.java');"
 *      >Click para mas informaci�n</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TParametro.png">
 */

public class TGetADMSEG {
	/** Variable que contendr� todos los atributos del archivo de propiedades. */
	private TFileProperties vPropiedades = null;
	/** Variable que contiene el nombre del archivo de propiedades a emplear. */
	private final String APP_CONFIG_FILE = "globales";

	/**
	 * Constructor que se encarga de cargar los par�metros de configuraci�n del
	 * archivo globales.properties.
	 * 
	 * @param cId
	 *            Identificador que se asignar� al objeto que contiene los
	 *            par�metros cargados.
	 */
	public TGetADMSEG() {
		try {
			vPropiedades = new TFileProperties(APP_CONFIG_FILE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Este m�todo se encarga de obtener el valor asignado a un par�metro en el
	 * archivo de propiedades. <br>
	 * En este caso se obtendr� el valor del par�metro que tenga el prefijo que
	 * se estableci� en el identificador.
	 * 
	 * @param cProp
	 *            Nombre del par�metro cuyo valor se desea obtener.
	 * @return Valor del par�metro deseado (correspondiente al que tenga el
	 *         prefijo).
	 */
	public String getPropEspecifica(String cProp) {
		String cValor = "";
		try {
			cValor = vPropiedades.getProperty(cProp);
		} catch (Exception e) {
			// System.out.println("TGetADMSEG.La Propiedad Espec�fica: " + cProp
			// + " No Fue Encontrada!");
		}
		return cValor;
	}

	private String getPropEspecificaNoError(String cProp) {
		String cValor = "";
		try {
			cValor = vPropiedades.getProperty(cProp);
		} catch (Exception e) {
			cValor = "";
		}
		return cValor;
	}

	public HashMap getPropiedades(int iId, String cDataSource)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		HashMap hmPropiedades = new HashMap();
		try {
			dbConn = new DbConnection(cDataSource);
			conn = dbConn.getConnection();
			String lSQL = "", cKey, cProp, cTemp;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			Vector lCiudadanos = new Vector();
			int count;

			lSQL = "select * " + "from  segpropiedad "
					+ "where segpropiedad.icvesistema = ? " + "";

			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.setInt(1, iId);

			lRSet = lPStmt.executeQuery();
			while (lRSet.next()) {
				cTemp = lRSet.getString("cPropiedad");
				cKey = lRSet.getString("icvesistema") + "." + cTemp;
				cProp = lRSet.getString("cValor");
				if (!getPropEspecificaNoError(cTemp).equals("")) {
					cProp = getPropEspecificaNoError(cTemp);
					hmPropiedades.put(cKey, cProp);
				} else
					hmPropiedades.put(cKey, cProp);
			}
		} catch (Exception ex) {
			TLogger.log(this, TLogger.WARN,
					"No se efectu� la B�squeda por RFC", ex);
			throw new DAOException("Error en DAO Ciudadano");
		} finally {
			try {
				lRSet.close();
				lPStmt.close();
				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				System.out.print("TGetADMSEG.getPropiedades");
				ex2.printStackTrace();
			}
			return hmPropiedades;
		}
	}

}