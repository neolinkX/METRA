package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EmpleoCalib DAO
 * </p>
 * <p>
 * Description: DAO de TOXEmpleoCalib
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

public class TDEmpleoCalib extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEmpleoCalib() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cEmpleoCalib) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEmpleoCalib = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEmpleoCalib vEmpleoCalib;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEmpleoCalib," + "cDscEmpleoCalib,"
					+ "lActivo" + " from TOXEmpleoCalib " + cEmpleoCalib;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEmpleoCalib = new TVEmpleoCalib();
				vEmpleoCalib.setICveEmpleoCalib(rset.getInt(1));
				vEmpleoCalib.setCDscEmpleoCalib(rset.getString(2));
				vEmpleoCalib.setLActivo(rset.getInt(3));
				vcEmpleoCalib.addElement(vEmpleoCalib);
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
			return vcEmpleoCalib;
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
		PreparedStatement pstmtMax = null;
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
			TVEmpleoCalib vEmpleoCalib = (TVEmpleoCalib) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "Select max(iCveEmpleoCalib) from db2admin.TOXEmpleoCalib ";

			pstmtMax = conn.prepareStatement(cSQL);
			rset = pstmtMax.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1) + 1;
			}

			vEmpleoCalib.setICveEmpleoCalib(iCve);

			cSQL = "";
			cSQL = "insert into TOXEmpleoCalib(" + "iCveEmpleoCalib,"
					+ "cDscEmpleoCalib," + "lActivo" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vEmpleoCalib.getICveEmpleoCalib());
			pstmt.setString(2, vEmpleoCalib.getCDscEmpleoCalib());
			pstmt.setInt(3, vEmpleoCalib.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vEmpleoCalib.getICveEmpleoCalib();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (rset != null) {
					rset.close();
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
			TVEmpleoCalib vEmpleoCalib = (TVEmpleoCalib) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXEmpleoCalib" + " set cDscEmpleoCalib= ?, "
					+ "lActivo= ? " + "where iCveEmpleoCalib = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vEmpleoCalib.getCDscEmpleoCalib());
			pstmt.setInt(2, vEmpleoCalib.getLActivo());
			pstmt.setInt(3, vEmpleoCalib.getICveEmpleoCalib());
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
	 * Metodo DesActivar
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
			TVEmpleoCalib vEmpleoCalib = (TVEmpleoCalib) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXEmpleoCalib"
					+ "    set TOXEmpleoCalib.lActivo = 0 "
					+ "  where TOXEmpleoCalib.iCveEmpleoCalib = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEmpleoCalib.getICveEmpleoCalib());
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
			TVEmpleoCalib vEmpleoCalib = (TVEmpleoCalib) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXEmpleoCalib" + " where iCveEmpleoCalib = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEmpleoCalib.getICveEmpleoCalib());
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
				warn("delete.closeEmpleoCalib", ex2);
			}
			return cClave;
		}
	}
}
