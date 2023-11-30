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
 * Title: Data Acces Object de TOXMotBaja DAO
 * </p>
 * <p>
 * Description: DAO Tabla TOXMotBaja
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez PAz
 * @version 1.0
 */

public class TDTOXMotBaja extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXMotBaja() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXMotBaja = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXMotBaja vTOXMotBaja;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMotBaja," + "cDscMotBaja," + "cDscBreve,"
					+ "lActivo" + " from TOXMotBaja  " + cWhere + " " + cOrden;
			// order by iCveMotBaja";
			// System.out.println("SQL : " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXMotBaja = new TVTOXMotBaja();
				vTOXMotBaja.setICveMotBaja(rset.getInt(1));
				vTOXMotBaja.setCDscMotBaja(rset.getString(2));
				vTOXMotBaja.setCDscBreve(rset.getString(3));
				vTOXMotBaja.setLActivo(rset.getInt(4));
				vcTOXMotBaja.addElement(vTOXMotBaja);
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
			return vcTOXMotBaja;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
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
			TVTOXMotBaja vTOXMotBaja = (TVTOXMotBaja) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXMotBaja(" + "iCveMotBaja," + "cDscMotBaja,"
					+ "cDscBreve," + "lActivo" + ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			String sqlNewCve = "select max(iCveMotBaja) FROM TOXMotBaja";
			PreparedStatement psNewCve = conn.prepareStatement(sqlNewCve);
			int iNewCve = 0;
			ResultSet rsNewCve = psNewCve.executeQuery();

			if (rsNewCve.next()) {
				iNewCve = rsNewCve.getInt(1) + 1;
			}
			rsNewCve.close();
			psNewCve.close();

			pstmt.setInt(1, iNewCve);
			pstmt.setString(2, vTOXMotBaja.getCDscMotBaja());
			pstmt.setString(3, vTOXMotBaja.getCDscBreve());
			pstmt.setInt(4, vTOXMotBaja.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + iNewCve;
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
			TVTOXMotBaja vTOXMotBaja = (TVTOXMotBaja) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXMotBaja" + " set cDscMotBaja= ?, "
					+ "cDscBreve= ?, " + "lActivo= ? "
					+ "where iCveMotBaja = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vTOXMotBaja.getCDscMotBaja());
			pstmt.setString(2, vTOXMotBaja.getCDscBreve());
			pstmt.setInt(3, vTOXMotBaja.getLActivo());
			pstmt.setInt(4, vTOXMotBaja.getICveMotBaja());

			/*
			 * System.out.println("Vals: " + vTOXMotBaja.getCDscMotBaja() +
			 * " ** " + vTOXMotBaja.getCDscBreve() + " ** " +
			 * vTOXMotBaja.getLActivo() + " ** " +
			 * vTOXMotBaja.getICveMotBaja());
			 */
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
			TVTOXMotBaja vTOXMotBaja = (TVTOXMotBaja) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXMotBaja" + " set lActivo= 0 "
					+ "where iCveMotBaja = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXMotBaja.getICveMotBaja());
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
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("disable.close", ex2);
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
			TVTOXMotBaja vTOXMotBaja = (TVTOXMotBaja) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXMotBaja" + " where iCveMotBaja = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXMotBaja.getICveMotBaja());
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
				warn("delete.closeTOXMotBaja", ex2);
			}
			return cClave;
		}
	}
}
