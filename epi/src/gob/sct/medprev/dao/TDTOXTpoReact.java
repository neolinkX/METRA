package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXTpoReact DAO
 * </p>
 * <p>
 * Description: DAO de la entidad TOXTpoReact
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

public class TDTOXTpoReact extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXTpoReact() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXTpoReact = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXTpoReact vTOXTpoReact;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveTpoReact," + "cDscTpoReact," + "lActivo"
					+ " from TOXTpoReact " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXTpoReact = new TVTOXTpoReact();
				vTOXTpoReact.setICveTpoReact(rset.getInt(1));
				vTOXTpoReact.setCDscTpoReact(rset.getString(2));
				vTOXTpoReact.setLActivo(rset.getInt(3));
				vcTOXTpoReact.addElement(vTOXTpoReact);
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
			return vcTOXTpoReact;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltra, String cOrdena) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXTpoReact = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXTpoReact vTOXTpoReact;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveTpoReact," + "cDscTpoReact," + "lActivo"
					+ " from TOXTpoReact" + cFiltra + " " + cOrdena;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXTpoReact = new TVTOXTpoReact();
				vTOXTpoReact.setICveTpoReact(rset.getInt(1));
				vTOXTpoReact.setCDscTpoReact(rset.getString(2));
				vTOXTpoReact.setLActivo(rset.getInt(3));
				vcTOXTpoReact.addElement(vTOXTpoReact);
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
			return vcTOXTpoReact;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		ResultSet rslt = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			TVTOXTpoReact vTOXTpoReact = (TVTOXTpoReact) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			stmt = conn.createStatement();
			rslt = stmt
					.executeQuery("select max(iCveTpoReact) cta from TOXTpoReact");
			if (rslt.next()) {
				iMax = rslt.getInt("cta");
			}
			iMax++;
			vTOXTpoReact.setICveTpoReact(iMax);
			rslt.close();
			stmt.close();
			rslt = null;
			stmt = null;
			if (vTOXTpoReact.getICveTpoReact() > 0) {
				String cSQL = "insert into TOXTpoReact(" + "iCveTpoReact,"
						+ "cDscTpoReact," + "lActivo" + ")values(?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vTOXTpoReact.getICveTpoReact());
				pstmt.setString(2, vTOXTpoReact.getCDscTpoReact());
				pstmt.setInt(3, vTOXTpoReact.getLActivo());
				pstmt.executeUpdate();
				cClave = "" + vTOXTpoReact.getICveTpoReact();
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
			TVTOXTpoReact vTOXTpoReact = (TVTOXTpoReact) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXTpoReact" + " set cDscTpoReact= ?, "
					+ "lActivo= ? " + "where iCveTpoReact = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vTOXTpoReact.getCDscTpoReact());
			pstmt.setInt(2, vTOXTpoReact.getLActivo());
			pstmt.setInt(3, vTOXTpoReact.getICveTpoReact());
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
			TVTOXTpoReact vTOXTpoReact = (TVTOXTpoReact) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXTpoReact" + "    set TOXTpoReact.lActivo = 0 "
					+ "  where TOXTpoReact.iCveTpoReact = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXTpoReact.getICveTpoReact());
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
			TVTOXTpoReact vTOXTpoReact = (TVTOXTpoReact) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXTpoReact" + " where iCveTpoReact = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXTpoReact.getICveTpoReact());
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
				warn("delete.closeTOXTpoReact", ex2);
			}
			return cClave;
		}
	}
}
