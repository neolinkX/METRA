package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de AcCorrectiva DAO
 * </p>
 * <p>
 * Description: DAO de TOXAcCorrectiva
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Juan Manuel Vazquez
 * @version 1.0
 */

public class TDAcCorrectiva extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDAcCorrectiva() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cAcCorrectiva) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcAcCorrectiva = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVAcCorrectiva vAcCorrectiva;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveAcCorrectiva," + "cDscAcCorrectiva,"
					+ "lActivo" + " from TOXAcCorrectiva " + cAcCorrectiva;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vAcCorrectiva = new TVAcCorrectiva();
				vAcCorrectiva.setICveAcCorrectiva(rset.getInt(1));
				vAcCorrectiva.setCDscAcCorrectiva(rset.getString(2));
				vAcCorrectiva.setLActivo(rset.getInt(3));
				vcAcCorrectiva.addElement(vAcCorrectiva);
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
			return vcAcCorrectiva;
		}
	}

	/**
	 * 
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		ResultSet rset = null;
		int iCve = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVAcCorrectiva vAcCorrectiva = (TVAcCorrectiva) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "Select max(iCveAcCorrectiva) from TOXAcCorrectiva ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1) + 1;
			}

			vAcCorrectiva.setICveAcCorrectiva(iCve);

			cSQL = "";
			cSQL = "insert into TOXAcCorrectiva(" + "iCveAcCorrectiva,"
					+ "cDscAcCorrectiva," + "lActivo" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vAcCorrectiva.getICveAcCorrectiva());
			pstmt.setString(2, vAcCorrectiva.getCDscAcCorrectiva());
			pstmt.setInt(3, vAcCorrectiva.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vAcCorrectiva.getICveAcCorrectiva();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insert", ex1);
			}
			warn("insert", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo update
	 */
	public Object update(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVAcCorrectiva vAcCorrectiva = (TVAcCorrectiva) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXAcCorrectiva" + " set cDscAcCorrectiva= ?, "
					+ "lActivo= ? " + "where iCveAcCorrectiva = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vAcCorrectiva.getCDscAcCorrectiva());
			pstmt.setInt(2, vAcCorrectiva.getLActivo());
			pstmt.setInt(3, vAcCorrectiva.getICveAcCorrectiva());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("update", ex1);
			}
			warn("update", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo update
	 */
	public Object deshabilitar(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVAcCorrectiva vAcCorrectiva = (TVAcCorrectiva) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXAcCorrectiva "
					+ "   set TOXAcCorrectiva.lActivo = 0 "
					+ " where TOXAcCorrectiva.iCveAcCorrectiva = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vAcCorrectiva.getICveAcCorrectiva());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("deshabilitar", ex1);
			}
			warn("deshabilitar", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("deshabilitar.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Delete
	 */
	public Object delete(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iEntidades = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVAcCorrectiva vAcCorrectiva = (TVAcCorrectiva) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXAcCorrectiva"
					+ " where iCveAcCorrectiva = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vAcCorrectiva.getICveAcCorrectiva());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("delete", ex1);
			}
			warn("delete", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeAcCorrectiva ", ex2);
			}
			return cClave;
		}
	}
}
