package gob.sct.medprev.dao;

import java.util.Properties;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Enumeration;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Connection;
import com.micper.sql.DbConnection;

/**
 * 
 * @author Soporte
 */
public class Seg {

	private int ctaErrores = 0;
	private java.util.Date FchHrs = new Date();
	private Statement statement = null;
	private ResultSet resultSet = null;
	protected Connection conn;
	Properties prop = new Properties();
	String dataSourceName = "";
	protected DbConnection dbConn;

	public Statement AbrirBD() {
		dbConn = null;
		conn = null;

		// -------------------------------------
		// Obt los Datos del GLOBALES.PROPERTIES
		try {
			prop = loadProperties("globales");
		} catch (IOException cne) {
			ctaErrores += 1;
			// System.out.println("\n\n***** Hubo un Problema al Leer el 'Globales.properties' ("
			// +FchHrs+ ") 1*****\n");
			cne.printStackTrace();
		}

		dataSourceName = (String) prop.get("ConDBModulo");
		// Obt los Datos del GLOBALES.PROPERTIES
		// -------------------------------------

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			statement = conn.createStatement();
		} catch (Exception e) {
			// System.out.println("\n\n***** Error al Abrir la Conexion  ("
			// +FchHrs+ ") *****\n");
		}

		return statement;
	}

	/**
	 * Método que se encarga de cerrar una conexión previamente establecida.
	 * 
	 * @return lSuccess que valida si se realizó con éxito el cierre de la
	 *         conexión.
	 */
	public void CerrarBD() {
		try {
			if (resultSet != null)
				resultSet.close();

			if (statement != null)
				statement.close();

			if (conn != null)
				conn.close();
		} catch (SQLException sqle) {
			ctaErrores += 1;
			// System.out.println("\n\n***** Error al Cerrar la Conexion  ("
			// +FchHrs+ ") *****\n");
			sqle.printStackTrace();
		}

		finally {
			try {
				if (resultSet != null)
					resultSet.close();

				if (statement != null)
					statement.close();

				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				// System.out.println("\n***** Hubo un Problema en el QUERY2 DatosPago ("
				// +FchHrs+ ") *****");
				ex.printStackTrace();
			}
		}// finally
	}

	private static Properties loadProperties(String file) throws IOException {
		Properties prop = new Properties();
		ResourceBundle bundle = ResourceBundle.getBundle(file);
		Enumeration enumeracion = bundle.getKeys();
		String key = null;

		while (enumeracion.hasMoreElements()) {
			key = (String) enumeracion.nextElement();
			prop.put(key, bundle.getObject(key));
		}
		return prop;
	}

	public Statement AbrirBDAdm() {
		dbConn = null;
		conn = null;

		// -------------------------------------
		// Obt los Datos del GLOBALES.PROPERTIES
		try {
			prop = loadProperties("globales");
		} catch (IOException cne) {
			ctaErrores += 1;
			// System.out.println("\n\n***** Hubo un Problema al Leer el 'Globales.properties' ("
			// +FchHrs+ ") 1*****\n");
			cne.printStackTrace();
		}

		dataSourceName = (String) prop.get("ConDB");
		// Obt los Datos del GLOBALES.PROPERTIES
		// -------------------------------------

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			statement = conn.createStatement();
		} catch (Exception e) {
			// System.out.println("\n\n***** Error al Abrir la Conexion  ("
			// +FchHrs+ ") *****\n");
		}

		return statement;
	}

} // class
