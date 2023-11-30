package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMSeccion DAO
 * </p>
 * <p>
 * Description: DAO Tabla ALMSeccion
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author leonel Popoca G.
 * @version 1.0
 */

public class TDALMSeccion extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMSeccion() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSeccion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSeccion vALMSeccion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "ALMSeccion.iCveAlmacen,  "
					+ "ALMSeccion.iCveArea, "
					+ "iCveSeccion, "
					+ "cDscSeccion, "
					+ "ALMSeccion.cObservacion, "
					+ "ALMSeccion.lActivo, "
					+ "ALMAlmacen.cDscAlmacen, "
					+ "ALMArea.cDscArea "
					+ "from ALMSeccion  "
					+ "join ALMAlmacen on ALMSeccion.iCveAlmacen = ALMAlmacen.iCveAlmacen "
					+ "join ALMArea on ALMSeccion.iCveArea = ALMArea.iCveArea "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSeccion = new TVALMSeccion();
				vALMSeccion.setICveAlmacen(rset.getInt(1));
				vALMSeccion.setICveArea(rset.getInt(2));
				vALMSeccion.setICveSeccion(rset.getInt(3));
				vALMSeccion.setCDscSeccion(rset.getString(4));
				vALMSeccion.setCObservacion(rset.getString(5));
				vALMSeccion.setLActivo(rset.getInt(6));
				vALMSeccion.setCDscAlmacen(rset.getString(7));
				vALMSeccion.setCDscArea(rset.getString(8));

				vcALMSeccion.addElement(vALMSeccion);
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
			return vcALMSeccion;
		}
	}

	public Vector FindByCustomWhere(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSeccion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSeccion vALMSeccion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "ALMSeccion.iCveAlmacen,"
					+ "ALMSeccion.iCveArea," + "ALMSeccion.iCveSeccion,"
					+ "ALMSeccion.cDscSeccion," + "ALMArea.cDscBreve,"
					+ "ALMSeccion.lActivo" + " from ALMSeccion " + cvFiltro;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSeccion = new TVALMSeccion();
				vALMSeccion.setICveAlmacen(rset.getInt(1));
				vALMSeccion.setICveArea(rset.getInt(2));
				vALMSeccion.setICveSeccion(rset.getInt(3));
				vALMSeccion.setCDscSeccion(rset.getString(4));
				vALMSeccion.setCObservacion(rset.getString(5));
				vALMSeccion.setLActivo(rset.getInt(6));
				vcALMSeccion.addElement(vALMSeccion);
			}
		} catch (Exception ex) {
			warn("FindByCustomWhere", ex);
			throw new DAOException("FindByCustomWhere");
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
			return vcALMSeccion;
		}
	}

	/**
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
			TVALMSeccion vALMSeccion = (TVALMSeccion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into ALMSeccion(" + "iCveAlmacen," + "iCveArea,"
					+ "iCveSeccion," + "cDscSeccion," + "cObservacion,"
					+ "lActivo" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveSeccion) from ALMSeccion";
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
			vALMSeccion.setICveSeccion(iMax);
			// ******************************************************************
			pstmt.setInt(1, vALMSeccion.getICveAlmacen());
			pstmt.setInt(2, vALMSeccion.getICveArea());
			pstmt.setInt(3, vALMSeccion.getICveSeccion());
			pstmt.setString(4, vALMSeccion.getCDscSeccion().toUpperCase()
					.trim());
			pstmt.setString(5, vALMSeccion.getCObservacion().toUpperCase()
					.trim());
			pstmt.setInt(6, vALMSeccion.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vALMSeccion.getICveSeccion();
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
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
			TVALMSeccion vALMSeccion = (TVALMSeccion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMSeccion" + " set cDscSeccion= ?, "
					+ "cObservacion= ?, " + "lActivo= ? "
					+ "where iCveAlmacen = ? " + "and iCveArea = ?"
					+ " and iCveSeccion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vALMSeccion.getCDscSeccion().toUpperCase()
					.trim());
			pstmt.setString(2, vALMSeccion.getCObservacion().toUpperCase()
					.trim());
			pstmt.setInt(3, vALMSeccion.getLActivo());
			pstmt.setInt(4, vALMSeccion.getICveAlmacen());
			pstmt.setInt(5, vALMSeccion.getICveArea());
			pstmt.setInt(6, vALMSeccion.getICveSeccion());
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
			TVALMSeccion vALMSeccion = (TVALMSeccion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMSeccion" + " where iCveAlmacen = ?"
					+ " and iCveArea = ?" + " and iCveSeccion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMSeccion.getICveAlmacen());
			pstmt.setInt(2, vALMSeccion.getICveArea());
			pstmt.setInt(3, vALMSeccion.getICveSeccion());
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
				warn("delete.closeALMSeccion", ex2);
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
			TVALMSeccion vALMSeccion = (TVALMSeccion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMSeccion" + " set lActivo= ? "
					+ "where iCveAlmacen = ?" + "and iCveArea = ?"
					+ " and iCveSeccion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vALMSeccion.getICveAlmacen());
			pstmt.setInt(3, vALMSeccion.getICveArea());
			pstmt.setInt(4, vALMSeccion.getICveSeccion());
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

	/*
	 * Obtiene el n�mero m�ximo de registros
	 */
	public int ObtenerRegMax() {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQLMax = "select max(iCveSeccion) from ALMSeccion";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
		} catch (Exception ex) {
		} finally {
			try {
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return iMax;
		}
	}
}