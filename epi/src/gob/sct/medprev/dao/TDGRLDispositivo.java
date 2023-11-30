package gob.sct.medprev.dao;

import gob.sct.medprev.vo.TVGRLDispositivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

public class TDGRLDispositivo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLDispositivo() {
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author Lic. AG SA L
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLDispositivo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLDispositivo vGRLDispositivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT " +
					"ICVEDISPOSITIVO," +
					"CNOMBREDISPOSITIVO," +
					"CFABRICANTE," +
					"CMODELO," +
					"CPUERTODEINTERFAZ," +
					"CSOCOMPATIBLE," +
					"LACTIVO " +
					"FROM GRLDISPOSITIVO WHERE LACTIVO = 1";
			
			//System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLDispositivo = new TVGRLDispositivo();
				vGRLDispositivo.setICveDispositivos(rset.getInt(1));
				vGRLDispositivo.setCNombreDispositivo(rset.getString(2));
				vGRLDispositivo.setCFabricante(rset.getString(3));
				vGRLDispositivo.setCModelo(rset.getString(4));
				vGRLDispositivo.setCPuertoDeInterfaz(rset.getString(5));
				vGRLDispositivo.setCSOCompatible(rset.getString(6));
				vGRLDispositivo.setIActivo(rset.getInt(7));
				vcGRLDispositivo.addElement(vGRLDispositivo);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcGRLDispositivo;
		}
	}
	
	/**
	 * Metodo Find By All
	 * 
	 * @author Lic. AG SA L
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLDispositivo vGRLDispositivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT " +
					"ICVEDISPOSITIVO," +
					"CNOMBREDISPOSITIVO," +
					"CFABRICANTE," +
					"CMODELO," +
					"CPUERTODEINTERFAZ," +
					"CSOCOMPATIBLE," +
					"LACTIVO " +
					"FROM GRLDISPOSITIVO " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLDispositivo = new TVGRLDispositivo();
				vGRLDispositivo = new TVGRLDispositivo();
				vGRLDispositivo.setICveDispositivos(rset.getInt(1));
				vGRLDispositivo.setCNombreDispositivo(rset.getString(2));
				vGRLDispositivo.setCFabricante(rset.getString(3));
				vGRLDispositivo.setCModelo(rset.getString(4));
				vGRLDispositivo.setCPuertoDeInterfaz(rset.getString(5));
				vGRLDispositivo.setCSOCompatible(rset.getString(6));
				vGRLDispositivo.setIActivo(rset.getInt(8));
				vcGRLModulo.addElement(vGRLDispositivo);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcGRLModulo;
		}
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author Lic. AG SA L
	 */
	public String  FindByNombreDisp(String cDispositivo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String  cNombreDispositivo = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLDispositivo vGRLDispositivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT  " +
					"CNOMBREDISPOSITIVO  " +
					"FROM GRLDISPOSITIVO WHERE ICVEDISPOSITIVO ="+cDispositivo;
			
			//System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				cNombreDispositivo =	rset.getString(1);
			}
		} catch (Exception ex) {
			warn("FindByNombreDisp", ex);
			throw new DAOException("FindByNombreDisp");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByNombreDisp.close", ex2);
			}
			return cNombreDispositivo;
		}
	}
	
	
}
