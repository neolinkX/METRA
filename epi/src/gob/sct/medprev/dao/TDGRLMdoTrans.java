package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLMdoTrans DAO
 * </p>
 * <p>
 * Description: Data Access Object de GRLMdoTrans
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sanchez
 * @version 1.0
 */

public class TDGRLMdoTrans extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLMdoTrans() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByAll() throws DAOException {
		return findByAll("", "");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByAll(String where) throws DAOException {
		return findByAll(where, "");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByAll(String where, String order) throws DAOException {
		System.out.println("findByAll Transporte"); 
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMdoTrans = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLMdoTrans vGRLMdoTrans;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMdoTrans," + "cDscMdoTrans," + "cDscDocto,"
					+ "lActivo" + " from GRLMdoTrans ";
			if (where != null && where.length() != 0) {
				cSQL += where;
			}
			if (order != null && order.length() != 0) {
				cSQL += order;
			} else {
				cSQL += " order by iCveMdoTrans";
			}

			System.out.println(cSQL); 

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLMdoTrans = new TVGRLMdoTrans();
				vGRLMdoTrans.setICveMdoTrans(rset.getInt(1));
				vGRLMdoTrans.setCDscMdoTrans(rset.getString(2));
				vGRLMdoTrans.setCDscDocto(rset.getString(3));
				vGRLMdoTrans.setLActivo(rset.getInt(4));
				vcGRLMdoTrans.addElement(vGRLMdoTrans);
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
			return vcGRLMdoTrans;
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
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVGRLMdoTrans vGRLMdoTrans = (TVGRLMdoTrans) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLMdoTrans(" + "iCveMdoTrans,"
					+ "cDscMdoTrans," + "cDscDocto," + "lActivo"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveMdoTrans) from GRLMdoTrans";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			if (iMax == 0) {
				iMax = 1;
			} else {
				iMax += 1;
			}
			vGRLMdoTrans.setICveMdoTrans(iMax);
			// ******************************************************************
			pstmt.setInt(1, vGRLMdoTrans.getICveMdoTrans());
			pstmt.setString(2, vGRLMdoTrans.getCDscMdoTrans());
			pstmt.setString(3, vGRLMdoTrans.getCDscDocto());
			pstmt.setInt(4, vGRLMdoTrans.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vGRLMdoTrans.getICveMdoTrans();
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
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
				}
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
			TVGRLMdoTrans vGRLMdoTrans = (TVGRLMdoTrans) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLMdoTrans" + " set cDscMdoTrans= ?, "
					+ "cDscDocto= ?, " + "lActivo= ? "
					+ "where iCveMdoTrans = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vGRLMdoTrans.getCDscMdoTrans());
			pstmt.setString(2, vGRLMdoTrans.getCDscDocto());
			pstmt.setInt(3, vGRLMdoTrans.getLActivo());
			pstmt.setInt(4, vGRLMdoTrans.getICveMdoTrans());
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
			TVGRLMdoTrans vGRLMdoTrans = (TVGRLMdoTrans) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLMdoTrans" + " where iCveMdoTrans = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLMdoTrans.getICveMdoTrans());
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
				warn("delete.closeGRLMdoTrans", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Disable
	 */
	public Object disable(Connection conGral, Object obj) throws DAOException {
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
			TVGRLMdoTrans vGRLMdoTrans = (TVGRLMdoTrans) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLMdoTrans" + " set lActivo= ? "
					+ " where iCveMdoTrans = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vGRLMdoTrans.getICveMdoTrans());
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
				warn("disable.close", ex2);
			}
			return cClave;
		}
	}

}
