package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de INVSituacion DAO
 * </p>
 * <p>
 * Description: Data Access Object para INVSituacion
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */

public class TDINVSituacion extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVSituacion() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		return FindByAll("", "");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrder) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVSituacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVSituacion vINVSituacion;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select " + "iCveSituacion,cDscSituacion,lActivo "
					+ "from INVSituacion";
			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			if (cOrder != null && cOrder.length() != 0) {
				cSQL += cOrder;
			} else {
				cSQL += " order by iCveSituacion";
			}
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVSituacion = new TVINVSituacion();
				vINVSituacion.setICveSituacion(rset.getInt("iCveSituacion"));
				vINVSituacion.setCDscSituacion(rset.getString("cDscSituacion"));
				vINVSituacion.setLActivo(rset.getInt("lActivo"));
				vcINVSituacion.addElement(vINVSituacion);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcINVSituacion;
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, TVINVSituacion vINVSituacion)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		ResultSet rslt = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			stmt = conn.createStatement();
			rslt = stmt
					.executeQuery("select max(iCveSituacion) cta from INVSituacion");
			if (rslt.next())
				vINVSituacion.setICveSituacion(rslt.getInt("cta") + 1);
			else
				vINVSituacion.setICveSituacion(1);
			rslt.close();
			stmt.close();
			if (vINVSituacion.getICveSituacion() > 0) {
				String cSQL = "insert into INVSituacion( "
						+ "iCveSituacion,cDscSituacion,lActivo"
						+ ") values (?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vINVSituacion.getICveSituacion());
				pstmt.setString(2, vINVSituacion.getCDscSituacion());
				pstmt.setInt(3, vINVSituacion.getLActivo());
				pstmt.executeUpdate();
				cClave = "" + vINVSituacion.getICveSituacion();
				if (conGral == null) {
					conn.commit();
				}
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
				if (rslt != null) {
					rslt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo update
	 */
	public Object update(Connection conGral, TVINVSituacion vINVSituacion)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "update INVSituacion "
					+ "set cDscSituacion=?,lActivo=? "
					+ "where iCveSituacion=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vINVSituacion.getCDscSituacion());
			pstmt.setInt(2, vINVSituacion.getLActivo());
			pstmt.setInt(3, vINVSituacion.getICveSituacion());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo disable
	 */
	public Object disable(Connection conGral, TVINVSituacion vINVSituacion)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "update INVSituacion " + "set lActivo=? "
					+ "where iCveSituacion=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVSituacion.getLActivo());
			pstmt.setInt(2, vINVSituacion.getICveSituacion());
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
				warn("disable", ex1);
			}
			warn("disable", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("disable.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo Delete
	 */
	public Object delete(Connection conGral, TVINVSituacion vINVSituacion)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "delete from INVSituacion"
					+ " where iCveSituacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVSituacion.getICveSituacion());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("delete.closeINVSituacion", ex2);
			}
		}
		return cClave;
	}
}