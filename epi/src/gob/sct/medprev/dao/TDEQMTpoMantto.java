package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EQMTpoMantto DAO
 * </p>
 * <p>
 * Description: Calibraci�n de Equipo - Tipo de Mantenimiento
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

public class TDEQMTpoMantto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEQMTpoMantto() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMTpoMantto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMTpoMantto vEQMTpoMantto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveTpoMantto," + "cDscTpoMantto,"
					+ "cDscBreve," + "lActivo" + " from EQMTpoMantto " + cWhere
					+ cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMTpoMantto = new TVEQMTpoMantto();
				vEQMTpoMantto.setICveTpoMantto(rset.getInt(1));
				vEQMTpoMantto.setCDscTpoMantto(rset.getString(2));
				vEQMTpoMantto.setCDscBreve(rset.getString(3));
				vEQMTpoMantto.setLActivo(rset.getInt(4));
				vcEQMTpoMantto.addElement(vEQMTpoMantto);
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
			return vcEQMTpoMantto;
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
			TVEQMTpoMantto vEQMTpoMantto = (TVEQMTpoMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EQMTpoMantto(" + "iCveTpoMantto,"
					+ "cDscTpoMantto," + "cDscBreve," + "lActivo"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveTpoMantto) from EQMTpoMantto";
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
			vEQMTpoMantto.setICveTpoMantto(iMax);
			// ******************************************************************
			pstmt.setInt(1, vEQMTpoMantto.getICveTpoMantto());
			pstmt.setString(2, vEQMTpoMantto.getCDscTpoMantto().toUpperCase()
					.trim());
			pstmt.setString(3, vEQMTpoMantto.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setInt(4, vEQMTpoMantto.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vEQMTpoMantto.getICveTpoMantto();
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
			TVEQMTpoMantto vEQMTpoMantto = (TVEQMTpoMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMTpoMantto" + " set cDscTpoMantto= ?, "
					+ "cDscBreve= ?, " + "lActivo= ? "
					+ "where iCveTpoMantto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vEQMTpoMantto.getCDscTpoMantto().toUpperCase()
					.trim());
			pstmt.setString(2, vEQMTpoMantto.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setInt(3, vEQMTpoMantto.getLActivo());
			pstmt.setInt(4, vEQMTpoMantto.getICveTpoMantto());
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
			TVEQMTpoMantto vEQMTpoMantto = (TVEQMTpoMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQLEnt = "select count(iCveTpoMantto) from  where iCveTpoMantto = ?";
			pstmtEnt = conn.prepareStatement(cSQLEnt);
			pstmtEnt.setInt(1, vEQMTpoMantto.getICveTpoMantto());
			rset = pstmtEnt.executeQuery();
			while (rset.next()) {
				iEntidades = rset.getInt(1);
			}
			if (iEntidades == 0) {
				cSQL = "delete from EQMTpoMantto" + " where iCveTpoMantto = ?";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEQMTpoMantto.getICveTpoMantto());
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
				warn("delete.closeEQMTpoMantto", ex2);
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
			TVEQMTpoMantto vEQMTpoMantto = (TVEQMTpoMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMTpoMantto" + " set lActivo= ? "
					+ "where iCveTpoMantto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vEQMTpoMantto.getICveTpoMantto());
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
