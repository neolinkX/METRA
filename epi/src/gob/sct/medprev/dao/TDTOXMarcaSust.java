package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXMarcaSust DAO
 * </p>
 * <p>
 * Description: DAO de la entidad TOXMarcaSust
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Su�rez Romero
 * @version 1.0
 */

public class TDTOXMarcaSust extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXMarcaSust() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXMarcaSust = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXMarcaSust vTOXMarcaSust;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMarcaSust," + "cDscMarcaSust," + "lActivo"
					+ " from TOXMarcaSust " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXMarcaSust = new TVTOXMarcaSust();
				vTOXMarcaSust.setICveMarcaSust(rset.getInt(1));
				vTOXMarcaSust.setCDscMarcaSust(rset.getString(2));
				vTOXMarcaSust.setLActivo(rset.getInt(3));
				vcTOXMarcaSust.addElement(vTOXMarcaSust);
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
			return vcTOXMarcaSust;
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
			TVTOXMarcaSust vTOXMarcaSust = (TVTOXMarcaSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "Select max(iCveMarcaSust) from TOXMarcaSust ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1);
			}
			iCve++;
			vTOXMarcaSust.setICveMarcaSust(iCve);

			cSQL = "";
			pstmt.close();

			cSQL = "insert into TOXMarcaSust(" + "iCveMarcaSust,"
					+ "cDscMarcaSust," + "lActivo" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vTOXMarcaSust.getICveMarcaSust());
			pstmt.setString(2, vTOXMarcaSust.getCDscMarcaSust());
			pstmt.setInt(3, vTOXMarcaSust.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vTOXMarcaSust.getICveMarcaSust();
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
			TVTOXMarcaSust vTOXMarcaSust = (TVTOXMarcaSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXMarcaSust" + " set cDscMarcaSust= ?, "
					+ "lActivo= ? " + "where iCveMarcaSust = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vTOXMarcaSust.getCDscMarcaSust());
			pstmt.setInt(2, vTOXMarcaSust.getLActivo());
			pstmt.setInt(3, vTOXMarcaSust.getICveMarcaSust());
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
	 * Metodo disable
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
			TVTOXMarcaSust vTOXMarcaSust = (TVTOXMarcaSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXMarcaSust" + " set " + "lActivo= 0 "
					+ "where iCveMarcaSust = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXMarcaSust.getICveMarcaSust());
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
			TVTOXMarcaSust vTOXMarcaSust = (TVTOXMarcaSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXMarcaSust" + " where iCveMarcaSust = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXMarcaSust.getICveMarcaSust());
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
				warn("delete.closeTOXMarcaSust", ex2);
			}
			return cClave;
		}
	}
}
