package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de MEDTpoResp DAO
 * </p>
 * <p>
 * Description: Catalogo de tipos de respuesta
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */

public class TDMEDTpoResp extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDTpoResp() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		return FindByAll("", " order by iCveTpoResp");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrder) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDTpoResp = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVMEDTpoResp vMEDTpoResp;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select " + "iCveTpoResp," + "cDscTpoResp,"
					+ "cCampo," + "lActivo" + " from MEDTpoResp" + cWhere
					+ cOrder;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDTpoResp = new TVMEDTpoResp();
				vMEDTpoResp.setICveTpoResp(rset.getInt(1));
				vMEDTpoResp.setCDscTpoResp(rset.getString(2));
				vMEDTpoResp.setCCampo(rset.getString(3));
				vMEDTpoResp.setLActivo(rset.getInt(4));
				vcMEDTpoResp.addElement(vMEDTpoResp);
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
		}
		return vcMEDTpoResp;
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
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			TVMEDTpoResp vMEDTpoResp = (TVMEDTpoResp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			stmt = conn.createStatement();
			rslt = stmt
					.executeQuery("select max(iCveTpoResp)+1 cta from MEDTpoResp");
			if (rslt.next()) {
				vMEDTpoResp.setICveTpoResp(rslt.getInt("cta"));
			} else {
				vMEDTpoResp.setICveTpoResp(0);
			}
			rslt.close();
			stmt.close();
			rslt = null;
			stmt = null;
			if (vMEDTpoResp.getICveTpoResp() > 0) {
				String cSQL = "insert into MEDTpoResp(" + "iCveTpoResp,"
						+ "cDscTpoResp," + "cCampo," + "lActivo"
						+ ")values(?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vMEDTpoResp.getICveTpoResp());
				pstmt.setString(2, vMEDTpoResp.getCDscTpoResp());
				pstmt.setString(3, vMEDTpoResp.getCCampo());
				pstmt.setInt(4, vMEDTpoResp.getLActivo());
				pstmt.executeUpdate();
				cClave = "" + vMEDTpoResp.getICveTpoResp();
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
		}
		return cClave;
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
			TVMEDTpoResp vMEDTpoResp = (TVMEDTpoResp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "update MEDTpoResp" + " set cDscTpoResp= ?, "
					+ "cCampo= ?, " + "lActivo= ? " + "where iCveTpoResp = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vMEDTpoResp.getCDscTpoResp());
			pstmt.setString(2, vMEDTpoResp.getCCampo());
			pstmt.setInt(3, vMEDTpoResp.getLActivo());
			pstmt.setInt(4, vMEDTpoResp.getICveTpoResp());
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
		}
		return cClave;
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
			TVMEDTpoResp vMEDTpoResp = (TVMEDTpoResp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDTpoResp" + " where iCveTpoResp = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDTpoResp.getICveTpoResp());
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
				warn("delete.closeMEDTpoResp", ex2);
			}
		}
		return cClave;
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
			TVMEDTpoResp vMEDTpoResp = (TVMEDTpoResp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			pstmt = conn
					.prepareStatement("update MEDTpoResp set lActivo=0 where iCveTpoResp = ?");
			pstmt.setInt(1, vMEDTpoResp.getICveTpoResp());
			pstmt.executeUpdate();
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
		}
		return cClave;
	}
}
