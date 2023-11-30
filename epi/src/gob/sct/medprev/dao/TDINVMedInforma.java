package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de INVMedInforma DAO
 * </p>
 * <p>
 * Description: Data Access Object para INVMedInforma
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

public class TDINVMedInforma extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVMedInforma() {
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
		Vector vcINVMedInforma = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVMedInforma vINVMedInforma;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select " + "iCveMedInforma,cDscMedInforma,lActivo "
					+ "from INVMedInforma";
			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			if (cOrder != null && cOrder.length() != 0) {
				cSQL += cOrder;
			} else {
				cSQL += " order by iCveMedInforma";
			}
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVMedInforma = new TVINVMedInforma();
				vINVMedInforma.setICveMedInforma(rset.getInt("iCveMedInforma"));
				vINVMedInforma.setCDscMedInforma(rset
						.getString("cDscMedInforma"));
				vINVMedInforma.setLActivo(rset.getInt("lActivo"));
				vcINVMedInforma.addElement(vINVMedInforma);
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
		return vcINVMedInforma;
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, TVINVMedInforma vINVMedInforma)
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
					.executeQuery("select max(iCveMedInforma) cta from INVMedInforma");
			if (rslt.next())
				vINVMedInforma.setICveMedInforma(rslt.getInt("cta") + 1);
			else
				vINVMedInforma.setICveMedInforma(1);
			rslt.close();
			stmt.close();
			if (vINVMedInforma.getICveMedInforma() > 0) {
				String cSQL = "insert into INVMedInforma ("
						+ "iCveMedInforma,cDscMedInforma,lActivo"
						+ ") values (?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vINVMedInforma.getICveMedInforma());
				pstmt.setString(2, vINVMedInforma.getCDscMedInforma());
				pstmt.setInt(3, vINVMedInforma.getLActivo());
				pstmt.executeUpdate();
				cClave = "" + vINVMedInforma.getICveMedInforma();
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
	public Object update(Connection conGral, TVINVMedInforma vINVMedInforma)
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
			String cSQL = "update INVMedInforma "
					+ "set cDscMedInforma=?,lActivo=? "
					+ "where iCveMedInforma=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vINVMedInforma.getCDscMedInforma());
			pstmt.setInt(2, vINVMedInforma.getLActivo());
			pstmt.setInt(3, vINVMedInforma.getICveMedInforma());
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
	public Object disable(Connection conGral, TVINVMedInforma vINVMedInforma)
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
			String cSQL = "update INVMedInforma " + "set lActivo=? "
					+ "where iCveMedInforma=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVMedInforma.getLActivo());
			pstmt.setInt(2, vINVMedInforma.getICveMedInforma());
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
	public Object delete(Connection conGral, TVINVMedInforma vINVMedInforma)
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
			String cSQL = "delete from INVMedInforma "
					+ "where iCveMedInforma = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVMedInforma.getICveMedInforma());
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
				warn("delete.closeINVMedInforma", ex2);
			}
		}
		return cClave;
	}
}