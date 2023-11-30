package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMUnidad DAO
 * </p>
 * <p>
 * Description: DAO para ALMUnidad
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Juan Vazquez
 * @version 1.0
 */

public class TDALMUnidad extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMUnidad() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMUnidad = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMUnidad vALMUnidad;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveUnidad," + "cDscUnidad," + "cAbreviatura,"
					+ "lActivo" + " from ALMUnidad " + cFiltro + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMUnidad = new TVALMUnidad();
				vALMUnidad.setICveUnidad(rset.getInt(1));
				vALMUnidad.setCDscUnidad(rset.getString(2));
				vALMUnidad.setCAbreviatura(rset.getString(3));
				vALMUnidad.setLActivo(rset.getInt(4));
				vcALMUnidad.addElement(vALMUnidad);
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
			return vcALMUnidad;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		ResultSet rset = null;
		int iCve = 0;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVALMUnidad vALMUnidad = (TVALMUnidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "";
			cSQL = "Select Max(iCveUnidad) from ALMUnidad";
			pstmt2 = conn.prepareStatement(cSQL);
			rset = pstmt2.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1) + 1;
			}
			rset.close();
			pstmt2.close();

			vALMUnidad.setICveUnidad(iCve);

			cSQL = "insert into ALMUnidad(" + "iCveUnidad," + "cDscUnidad,"
					+ "cAbreviatura," + "lActivo" + ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vALMUnidad.getICveUnidad());
			pstmt.setString(2, vALMUnidad.getCDscUnidad().toUpperCase().trim());
			pstmt.setString(3, vALMUnidad.getCAbreviatura().toUpperCase()
					.trim());
			pstmt.setInt(4, vALMUnidad.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vALMUnidad.getICveUnidad();
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
			TVALMUnidad vALMUnidad = (TVALMUnidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMUnidad" + " set cDscUnidad= ?, "
					+ "cAbreviatura= ?, " + "lActivo= ? "
					+ "where iCveUnidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vALMUnidad.getCDscUnidad().toUpperCase().trim());
			pstmt.setString(2, vALMUnidad.getCAbreviatura().toUpperCase()
					.trim());
			pstmt.setInt(3, vALMUnidad.getLActivo());
			pstmt.setInt(4, vALMUnidad.getICveUnidad());
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
			TVALMUnidad vALMUnidad = (TVALMUnidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMUnidad" + " where iCveUnidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMUnidad.getICveUnidad());
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
				warn("delete.closeALMUnidad", ex2);
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
			TVALMUnidad vALMUnidad = (TVALMUnidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMUnidad" + " set lActivo = 0 "
					+ "where iCveUnidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMUnidad.getICveUnidad());
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
