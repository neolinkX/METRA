package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de INVCausa DAO
 * </p>
 * <p>
 * Description: Data Access Object para INVCausa
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

public class TDINVCausa extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVCausa() {
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
		Vector vcINVCausa = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVCausa vINVCausa;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iCveTpoCausa,iCveCausa,cDscCausa,lActivo "
					+ "from INVCausa";
			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			if (cOrder != null && cOrder.length() != 0) {
				cSQL += cOrder;
			} else {
				cSQL += " order by iCveTpoCausa,iCveCausa";
			}
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVCausa = new TVINVCausa();
				vINVCausa.setICveTpoCausa(rset.getInt("iCveTpoCausa"));
				vINVCausa.setICveCausa(rset.getInt("iCveCausa"));
				vINVCausa.setCDscCausa(rset.getString("cDscCausa"));
				vINVCausa.setLActivo(rset.getInt("lActivo"));
				vcINVCausa.addElement(vINVCausa);
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
		return vcINVCausa;
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, TVINVCausa vINVCausa)
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
					.executeQuery("select max(iCveCausa) cta from INVCausa where iCveTpoCausa="
							+ vINVCausa.getICveTpoCausa());
			if (rslt.next())
				vINVCausa.setICveCausa(rslt.getInt("cta") + 1);
			else
				vINVCausa.setICveCausa(1);
			rslt.close();
			stmt.close();
			if (vINVCausa.getICveCausa() > 0) {
				String cSQL = "insert into INVCausa ("
						+ "iCveTpoCausa,iCveCausa,cDscCausa,lActivo"
						+ ") values (?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vINVCausa.getICveTpoCausa());
				pstmt.setInt(2, vINVCausa.getICveCausa());
				pstmt.setString(3, vINVCausa.getCDscCausa());
				pstmt.setInt(4, vINVCausa.getLActivo());
				pstmt.executeUpdate();
				cClave = "" + vINVCausa.getICveTpoCausa();
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
	public Object update(Connection conGral, TVINVCausa vINVCausa)
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
			String cSQL = "update INVCausa " + "set cDscCausa=?,lActivo= ? "
					+ "where iCveTpoCausa=? and iCveCausa=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vINVCausa.getCDscCausa());
			pstmt.setInt(2, vINVCausa.getLActivo());
			pstmt.setInt(3, vINVCausa.getICveTpoCausa());
			pstmt.setInt(4, vINVCausa.getICveCausa());
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
	public Object disable(Connection conGral, TVINVCausa vINVCausa)
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
			String cSQL = "update INVCausa " + "set lActivo=? "
					+ "where iCveTpoCausa=? and iCveCausa=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVCausa.getLActivo());
			pstmt.setInt(2, vINVCausa.getICveTpoCausa());
			pstmt.setInt(3, vINVCausa.getICveCausa());
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
	public Object delete(Connection conGral, TVINVCausa vINVCausa)
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
			String cSQL = "delete from INVCausa"
					+ " where iCveTpoCausa=? and iCveCausa = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVCausa.getICveTpoCausa());
			pstmt.setInt(2, vINVCausa.getICveCausa());
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
				warn("delete.closeINVCausa", ex2);
			}
		}
		return cClave;
	}
}