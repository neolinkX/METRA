package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EQMTpoEquipo DAO
 * </p>
 * <p>
 * Description: Calibraci�n de Equipo - Tipos de Equipo
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

public class TDEQMTpoEquipo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEQMTpoEquipo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMTpoEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMTpoEquipo vEQMTpoEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveClasificacion," + "iCveTpoEquipo,"
					+ "cDscTpoEquipo," + "cDscBreve," + "cCABMS," + "lActivo"
					+ " from EQMTpoEquipo " + cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMTpoEquipo = new TVEQMTpoEquipo();
				vEQMTpoEquipo.setICveClasificacion(rset.getInt(1));
				vEQMTpoEquipo.setICveTpoEquipo(rset.getInt(2));
				vEQMTpoEquipo.setCDscTpoEquipo(rset.getString(3));
				vEQMTpoEquipo.setCDscBreve(rset.getString(4));
				vEQMTpoEquipo.setCCABMS(rset.getString(5));
				vEQMTpoEquipo.setLActivo(rset.getInt(6));
				vcEQMTpoEquipo.addElement(vEQMTpoEquipo);
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
			return vcEQMTpoEquipo;
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
			TVEQMTpoEquipo vEQMTpoEquipo = (TVEQMTpoEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EQMTpoEquipo(" + "iCveClasificacion,"
					+ "iCveTpoEquipo," + "cDscTpoEquipo," + "cDscBreve,"
					+ "cCABMS," + "lActivo" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveTpoEquipo) from EQMTpoEquipo where iCveClasificacion = "
					+ vEQMTpoEquipo.getICveClasificacion();
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
			vEQMTpoEquipo.setICveTpoEquipo(iMax);
			// ******************************************************************
			pstmt.setInt(1, vEQMTpoEquipo.getICveClasificacion());
			pstmt.setInt(2, vEQMTpoEquipo.getICveTpoEquipo());
			pstmt.setString(3, vEQMTpoEquipo.getCDscTpoEquipo().toUpperCase()
					.trim());
			pstmt.setString(4, vEQMTpoEquipo.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setString(5, vEQMTpoEquipo.getCCABMS().toUpperCase().trim());
			pstmt.setInt(6, vEQMTpoEquipo.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vEQMTpoEquipo.getICveTpoEquipo();
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
			TVEQMTpoEquipo vEQMTpoEquipo = (TVEQMTpoEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMTpoEquipo" + " set cDscTpoEquipo= ?, "
					+ "cDscBreve= ?, " + "cCABMS= ?, " + "lActivo= ? "
					+ "where iCveClasificacion = ? " + " and iCveTpoEquipo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vEQMTpoEquipo.getCDscTpoEquipo().toUpperCase()
					.trim());
			pstmt.setString(2, vEQMTpoEquipo.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setString(3, vEQMTpoEquipo.getCCABMS().toUpperCase().trim());
			pstmt.setInt(4, vEQMTpoEquipo.getLActivo());
			pstmt.setInt(5, vEQMTpoEquipo.getICveClasificacion());
			pstmt.setInt(6, vEQMTpoEquipo.getICveTpoEquipo());
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
		PreparedStatement pstmtEnt = null;
		ResultSet rset = null;
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
			String cSQLEnt = "";
			TVEQMTpoEquipo vEQMTpoEquipo = (TVEQMTpoEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQLEnt = "select count(iCveClasificacion) from  where iCveClasificacion = ?  and iCveTpoEquipo = ?";
			pstmtEnt = conn.prepareStatement(cSQLEnt);
			pstmtEnt.setInt(1, vEQMTpoEquipo.getICveClasificacion());
			pstmtEnt.setInt(2, vEQMTpoEquipo.getICveTpoEquipo());
			rset = pstmtEnt.executeQuery();
			while (rset.next()) {
				iEntidades = rset.getInt(1);
			}
			if (iEntidades == 0) {
				cSQL = "delete from EQMTpoEquipo"
						+ " where iCveClasificacion = ?"
						+ " and iCveTpoEquipo = ?";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEQMTpoEquipo.getICveClasificacion());
				pstmt.setInt(2, vEQMTpoEquipo.getICveTpoEquipo());
				pstmt.executeUpdate();
			}
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
				if (pstmtEnt != null) {
					pstmtEnt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeEQMTpoEquipo", ex2);
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
			TVEQMTpoEquipo vEQMTpoEquipo = (TVEQMTpoEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMTpoEquipo" + " set lActivo= ? "
					+ "where iCveClasificacion = ?" + " and iCveTpoEquipo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vEQMTpoEquipo.getICveClasificacion());
			pstmt.setInt(3, vEQMTpoEquipo.getICveTpoEquipo());
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