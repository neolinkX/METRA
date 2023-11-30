package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMTpoMovimiento DAO
 * </p>
 * <p>
 * Description: TD de ALMTpoMovimiento
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

public class TDALMTpoMovimiento extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMTpoMovimiento() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMTpoMovimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMTpoMovimiento vALMTpoMovimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveTpoMovimiento," + "cDscTpoMovimiento,"
					+ "cDscBreve," + "lSuma," + "lActivo"
					+ " from ALMTpoMovimiento " + cFiltro + cOrden;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMTpoMovimiento = new TVALMTpoMovimiento();
				vALMTpoMovimiento.setICveTpoMovimiento(rset.getInt(1));
				vALMTpoMovimiento.setCDscTpoMovimiento(rset.getString(2));
				vALMTpoMovimiento.setCDscBreve(rset.getString(3));
				vALMTpoMovimiento.setLSuma(rset.getInt(4));
				vALMTpoMovimiento.setLActivo(rset.getInt(5));
				vcALMTpoMovimiento.addElement(vALMTpoMovimiento);
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
			return vcALMTpoMovimiento;
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
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		int iCve = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVALMTpoMovimiento vALMTpoMovimiento = (TVALMTpoMovimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "";
			cSQL = "Select max(iCveTpoMovimiento) " + "from ALMTpoMovimiento ";
			pstmt2 = conn.prepareStatement(cSQL);
			rset = pstmt2.executeQuery();

			if (rset.next())
				iCve = rset.getInt(1) + 1;

			rset.close();
			pstmt2.close();

			vALMTpoMovimiento.setICveTpoMovimiento(iCve);

			cSQL = "insert into ALMTpoMovimiento(" + "iCveTpoMovimiento,"
					+ "cDscTpoMovimiento," + "cDscBreve," + "lSuma,"
					+ "lActivo" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			/*
			 * vALMTpoMovimiento.setICveTpoMovimiento(TablaSecuencia.getSecuencia
			 * (conn, "15"));
			 */

			pstmt.setInt(1, vALMTpoMovimiento.getICveTpoMovimiento());
			pstmt.setString(2, vALMTpoMovimiento.getCDscTpoMovimiento()
					.toUpperCase().trim());
			pstmt.setString(3, vALMTpoMovimiento.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setInt(4, vALMTpoMovimiento.getLSuma());
			pstmt.setInt(5, vALMTpoMovimiento.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vALMTpoMovimiento.getICveTpoMovimiento();
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
			TVALMTpoMovimiento vALMTpoMovimiento = (TVALMTpoMovimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMTpoMovimiento" + " set cDscTpoMovimiento= ?, "
					+ "cDscBreve= ?, " + "lSuma= ?, " + "lActivo= ? "
					+ "where iCveTpoMovimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vALMTpoMovimiento.getCDscTpoMovimiento()
					.toUpperCase().trim());
			pstmt.setString(2, vALMTpoMovimiento.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setInt(3, vALMTpoMovimiento.getLSuma());
			pstmt.setInt(4, vALMTpoMovimiento.getLActivo());
			pstmt.setInt(5, vALMTpoMovimiento.getICveTpoMovimiento());
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
			TVALMTpoMovimiento vALMTpoMovimiento = (TVALMTpoMovimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMTpoMovimiento"
					+ " where iCveTpoMovimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMTpoMovimiento.getICveTpoMovimiento());
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
				warn("delete.closeALMTpoMovimiento", ex2);
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
			TVALMTpoMovimiento vALMTpoMovimiento = (TVALMTpoMovimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMTpoMovimiento" + " set lActivo= 0 "
					+ "where iCveTpoMovimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMTpoMovimiento.getICveTpoMovimiento());
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
