package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EQMEstado DAO
 * </p>
 * <p>
 * Description: Calibraci�n de Equipo - Estados del Equipo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */

public class TDEQMEstado extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEQMEstado() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEstado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEstado vEQMEstado;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEstadoEQM," + "cDscEstadoEQM,"
					+ "lInactivaEquipo," + "lActivo" + " from EQMEstado "
					+ cWhere + cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMEstado = new TVEQMEstado();
				vEQMEstado.setiCveEstadoEQM(rset.getInt(1));
				vEQMEstado.setcDscEstadoEQM(rset.getString(2));
				vEQMEstado.setLInactivaEquipo(rset.getInt(3));
				vEQMEstado.setLActivo(rset.getInt(4));
				vcEQMEstado.addElement(vEQMEstado);
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
			return vcEQMEstado;
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
		int iMax = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEQMEstado vEQMEstado = (TVEQMEstado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EQMEstado(" + "iCveEstadoEQM,"
					+ "cDscEstadoEQM," + "lInactivaEquipo," + "lActivo"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveEstadoEQM) from EQMEstado";
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
			vEQMEstado.setiCveEstadoEQM(iMax);
			// ******************************************************************
			pstmt.setInt(1, vEQMEstado.getiCveEstadoEQM());
			pstmt.setString(2, vEQMEstado.getcDscEstadoEQM().toUpperCase()
					.trim());
			pstmt.setInt(3, vEQMEstado.getLInactivaEquipo());
			pstmt.setInt(4, vEQMEstado.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vEQMEstado.getiCveEstadoEQM();
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
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
			TVEQMEstado vEQMEstado = (TVEQMEstado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMEstado" + " set cDscEstadoEQM= ?, "
					+ "lInactivaEquipo= ?, " + "lActivo= ? "
					+ "where iCveEstadoEQM = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vEQMEstado.getcDscEstadoEQM().toUpperCase()
					.trim());
			pstmt.setInt(2, vEQMEstado.getLInactivaEquipo());
			pstmt.setInt(3, vEQMEstado.getLActivo());
			pstmt.setInt(4, vEQMEstado.getiCveEstadoEQM());
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
			TVEQMEstado vEQMEstado = (TVEQMEstado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EQMEstado" + " where iCveEstadoEQM = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMEstado.getiCveEstadoEQM());
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
				warn("delete.closeEQMEstado", ex2);
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
			TVEQMEstado vEQMEstado = (TVEQMEstado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMEstado" + " set lActivo= ? "
					+ "where iCveEstadoEQM = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vEQMEstado.getiCveEstadoEQM());
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
