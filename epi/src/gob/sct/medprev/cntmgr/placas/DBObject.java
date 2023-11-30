/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev.cntmgr.placas;

/**
 *
 * @author Ivan Santiago Méndez
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

public class DBObject {
	private Connection conexion;
	Properties prop;
	String Esquema;
	PreparedStatement stmtX;
	ResultSet rsX = null;

	public DBObject() {
		cargarConfiguraciones();
	}

	public Connection getConexion() {
		try {
			javax.naming.InitialContext initctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) initctx.lookup(prop
					.getProperty("JDBC_DS_NAME"));
			this.conexion = ds.getConnection();
			this.conexion
					.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			this.Esquema = prop.getProperty("Esquema");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexion;
	}

	public synchronized ArrayList executeQuery(String SQL) {
		Connection conn = getConexion();
		ArrayList info = new ArrayList();
		try {
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			this.stmtX = conn.prepareStatement(SQL,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			this.rsX = stmtX.executeQuery();
			int numCol = rsX.getMetaData().getColumnCount();
			System.out.println(SQL);
			while (rsX.next()) {
				String[] datos = new String[numCol];
				for (int x = 1; x <= numCol; x++) {
					datos[x - 1] = rsX.getString(x);
				}
				info.add(datos);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			this.closeConexion();
		}
		return info;
	}

	public void closeConexion() {
		try {
			this.rsX.close();
		} catch (Exception er) {

		}
		try {
			this.stmtX.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			this.conexion.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public int executeInsert(String SQL) {
		Connection conn = getConexion();
		ArrayList info = new ArrayList();
		int res = 0;// Cero que tuvo un error en la insercion se insertaron 0
					// registros
		try {
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			this.stmtX = conn.prepareStatement(SQL,
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			res = this.stmtX.executeUpdate();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			this.closeConexion();
		}
		return res;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public void cargarConfiguraciones() {
		prop = new Properties();
		try {
			prop.load(this
					.getClass()
					.getResource(
							"/gob/sct/medprev/cntmgr/placas/dbparameters.properties")
					.openStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String getValue(String key) {
		return prop.getProperty(key);
	}

}
