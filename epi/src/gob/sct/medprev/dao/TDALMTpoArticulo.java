package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMTpoArticulo DAO
 * </p>
 * <p>
 * Description: TD ALMTpoArticulo
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

public class TDALMTpoArticulo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMTpoArticulo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMTpoArticulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMTpoArticulo vALMTpoArticulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveTpoArticulo," + "cDscTpoArticulo,"
					+ "cDscBreve," + "iIDPartida," + "lActivo"
					+ " from ALMTpoArticulo " + cFiltro + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMTpoArticulo = new TVALMTpoArticulo();
				vALMTpoArticulo.setICveTpoArticulo(rset.getInt(1));
				vALMTpoArticulo.setCDscTpoArticulo(rset.getString(2));
				vALMTpoArticulo.setCDscBreve(rset.getString(3));
				vALMTpoArticulo.setIIDPartida(rset.getInt(4));
				vALMTpoArticulo.setLActivo(rset.getInt(5));
				vcALMTpoArticulo.addElement(vALMTpoArticulo);
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
			return vcALMTpoArticulo;
		}
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMTpoArticulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMTpoArticulo vALMTpoArticulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select distinct " + " ALMTpoArticulo.iCveTpoArticulo,  "
					+ " ALMTpoArticulo.cDscTpoArticulo,   "
					+ " ALMTpoArticulo.cDscBreve, "
					+ " ALMTpoArticulo.iiDPartida,     "
					+ " ALMTpoArticulo.lActivo  " + " from ALMTpoArticulo "
					+ cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMTpoArticulo = new TVALMTpoArticulo();
				vALMTpoArticulo.setICveTpoArticulo(rset.getInt(1));
				vALMTpoArticulo.setCDscTpoArticulo(rset.getString(2));
				vALMTpoArticulo.setCDscBreve(rset.getString(3));
				vALMTpoArticulo.setIIDPartida(rset.getInt(4));
				vALMTpoArticulo.setLActivo(rset.getInt(5));
				vcALMTpoArticulo.addElement(vALMTpoArticulo);
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
			return vcALMTpoArticulo;
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
			TVALMTpoArticulo vALMTpoArticulo = (TVALMTpoArticulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "";
			cSQL = "Select Max(iCveTpoArticulo) from ALMTpoArticulo";
			pstmt2 = conn.prepareStatement(cSQL);
			rset = pstmt2.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1) + 1;
			}
			rset.close();
			pstmt2.close();

			vALMTpoArticulo.setICveTpoArticulo(iCve);

			cSQL = "insert into ALMTpoArticulo(" + "iCveTpoArticulo,"
					+ "cDscTpoArticulo," + "cDscBreve," + "iIDPartida,"
					+ "lActivo" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vALMTpoArticulo.getICveTpoArticulo());
			pstmt.setString(2, vALMTpoArticulo.getCDscTpoArticulo()
					.toUpperCase().trim());
			pstmt.setString(3, vALMTpoArticulo.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setInt(4, vALMTpoArticulo.getIIDPartida());
			pstmt.setInt(5, vALMTpoArticulo.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vALMTpoArticulo.getICveTpoArticulo();
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
			TVALMTpoArticulo vALMTpoArticulo = (TVALMTpoArticulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMTpoArticulo" + " set cDscTpoArticulo= ?, "
					+ "cDscBreve= ?, " + "iIDPartida= ?, " + "lActivo= ? "
					+ "where iCveTpoArticulo = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vALMTpoArticulo.getCDscTpoArticulo()
					.toUpperCase().trim());
			pstmt.setString(2, vALMTpoArticulo.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setInt(3, vALMTpoArticulo.getIIDPartida());
			pstmt.setInt(4, vALMTpoArticulo.getLActivo());
			pstmt.setInt(5, vALMTpoArticulo.getICveTpoArticulo());
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

	/* UpDate Para Dar De Baja Logica */

	/**
	 * Metodo disable2
	 */
	public Object disable2(Connection conGral, Object obj) throws DAOException {
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
			TVALMTpoArticulo vALMTpoArticulo = (TVALMTpoArticulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMTpoArticulo" + " set lActivo= 0 "
					+ "where iCveTpoArticulo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMTpoArticulo.getICveTpoArticulo());
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
			TVALMTpoArticulo vALMTpoArticulo = (TVALMTpoArticulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMTpoArticulo" + " where iCveTpoArticulo = ?"
					+ " and  = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMTpoArticulo.getICveTpoArticulo());
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
				warn("delete.closeALMTpoArticulo", ex2);
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
			TVALMTpoArticulo vALMTpoArticulo = (TVALMTpoArticulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMTpoArticulo" + " set lActivo= ?, "
					+ "where iCveTpoArticulo = ?" + " and  = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
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
