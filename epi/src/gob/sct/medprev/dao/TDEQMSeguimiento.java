package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EQMSeguimiento DAO
 * </p>
 * <p>
 * Description: DAO para la administraci�n de la Informaci�n de la tabla
 * EQMSeguimiento
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hern�ndez Garc�a
 * @version 1.0
 */

public class TDEQMSeguimiento extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEQMSeguimiento() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMSeguimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMSeguimiento vEQMSeguimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEquipo," + "iCveMantenimiento,"
					+ "iCveMovimiento," + "iCveProceso," + "iCveEtapa,"
					+ "dtSolicitud," + "iCveSolicitante," + "iCveUsuReg,"
					+ "iCveUsuSolicita," + "cSolicitante," + "cObservacion"
					+ " from EQMSeguimiento order by iCveEquipo";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMSeguimiento = new TVEQMSeguimiento();
				vEQMSeguimiento.setICveEquipo(rset.getInt(1));
				vEQMSeguimiento.setICveMantenimiento(rset.getInt(2));
				vEQMSeguimiento.setICveMovimiento(rset.getInt(3));
				vEQMSeguimiento.setICveProceso(rset.getInt(4));
				vEQMSeguimiento.setICveEtapa(rset.getInt(5));
				vEQMSeguimiento.setDtSolicitud(rset.getDate(6));
				vEQMSeguimiento.setICveSolicitante(rset.getInt(7));
				vEQMSeguimiento.setICveUsuReg(rset.getInt(8));
				vEQMSeguimiento.setICveUsuSolicita(rset.getInt(9));
				vEQMSeguimiento.setCSolicitante(rset.getString(10));
				vEQMSeguimiento.setCObservacion(rset.getString(11));
				vcEQMSeguimiento.addElement(vEQMSeguimiento);
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
			return vcEQMSeguimiento;
		}
	}

	public Vector FindByAll(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMSeguimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMSeguimiento vEQMSeguimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEquipo," + "iCveMantenimiento,"
					+ "iCveMovimiento," + "iCveProceso," + "iCveEtapa,"
					+ "dtSolicitud," + "iCveSolictante," + "iCveUsuReg,"
					+ "iCveUsuSolicita," + "cSolicitante," + "cObservacion"
					+ " from EQMSeguimiento " + cCondicion;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMSeguimiento = new TVEQMSeguimiento();
				vEQMSeguimiento.setICveEquipo(rset.getInt(1));
				vEQMSeguimiento.setICveMantenimiento(rset.getInt(2));
				vEQMSeguimiento.setICveMovimiento(rset.getInt(3));
				vEQMSeguimiento.setICveProceso(rset.getInt(4));
				vEQMSeguimiento.setICveEtapa(rset.getInt(5));
				vEQMSeguimiento.setDtSolicitud(rset.getDate(6));
				vEQMSeguimiento.setICveSolicitante(rset.getInt(7));
				vEQMSeguimiento.setICveUsuReg(rset.getInt(8));
				vEQMSeguimiento.setICveUsuSolicita(rset.getInt(9));
				vEQMSeguimiento.setCSolicitante(rset.getString(10));
				vEQMSeguimiento.setCObservacion(rset.getString(11));
				vcEQMSeguimiento.addElement(vEQMSeguimiento);
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
			return vcEQMSeguimiento;
		}
	}

	/**
	 * 
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null, pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		String cClave = "";
		boolean lError = false;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEQMSeguimiento vEQMSeguimiento = (TVEQMSeguimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT MAX(iCveMovimiento) FROM EQMSeguimiento WHERE iCveEquipo= ? AND iCveMantenimiento = ? ";
			pstmtMax = conn.prepareStatement(cSQL);
			pstmtMax.setInt(1, vEQMSeguimiento.getICveEquipo());
			pstmtMax.setInt(2, vEQMSeguimiento.getICveMantenimiento());

			rsetMax = pstmtMax.executeQuery();

			if (rsetMax != null)
				if (rsetMax.next())
					iMax = rsetMax.getInt(1);
				else
					iMax = 0;
			else
				iMax = 0;

			iMax++;
			vEQMSeguimiento.setICveMovimiento(iMax);
			cClave = "" + iMax;
			cSQL = "insert into EQMSeguimiento(" + "iCveEquipo,"
					+ "iCveMantenimiento," + "iCveMovimiento," + "iCveProceso,"
					+ "iCveEtapa," + "dtSolicitud," + "iCveSolictante,"
					+ "iCveUsuReg," + "iCveUsuSolicita," + "cSolicitante,"
					+ "cObservacion" + ")values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEQMSeguimiento.getICveEquipo());
			pstmt.setInt(2, vEQMSeguimiento.getICveMantenimiento());
			pstmt.setInt(3, vEQMSeguimiento.getICveMovimiento());
			pstmt.setInt(4, vEQMSeguimiento.getICveProceso());
			pstmt.setInt(5, vEQMSeguimiento.getICveEtapa());
			pstmt.setDate(6, vEQMSeguimiento.getDtSolicitud());
			pstmt.setInt(7, vEQMSeguimiento.getICveSolicitante());
			pstmt.setInt(8, vEQMSeguimiento.getICveUsuReg());
			pstmt.setInt(9, vEQMSeguimiento.getICveUsuSolicita());
			pstmt.setString(10, vEQMSeguimiento.getCSolicitante().toUpperCase()
					.trim());
			pstmt.setString(11, vEQMSeguimiento.getCObservacion().toUpperCase()
					.trim());
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
				warn("insert", ex1);
			}
			warn("insert", ex);
			lError = true;
		} finally {
			try {
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
				}

				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			if (lError)
				throw new DAOException("");
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
			TVEQMSeguimiento vEQMSeguimiento = (TVEQMSeguimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMSeguimiento" + " set iCveProceso= ?, "
					+ "iCveEtapa= ?, " + "dtSolicitud= ?, "
					+ "iCveSolicitante= ?, " + "iCveUsuReg= ?, "
					+ "iCveUsuSolicita= ?, " + "cSolicitante= ?, "
					+ "cObservacion= ? " + "where iCveEquipo = ? "
					+ "and iCveMantenimiento = ?" + " and iCveMovimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMSeguimiento.getICveProceso());
			pstmt.setInt(2, vEQMSeguimiento.getICveEtapa());
			pstmt.setDate(3, vEQMSeguimiento.getDtSolicitud());
			pstmt.setInt(4, vEQMSeguimiento.getICveSolicitante());
			pstmt.setInt(5, vEQMSeguimiento.getICveUsuReg());
			pstmt.setInt(6, vEQMSeguimiento.getICveUsuSolicita());
			pstmt.setString(7, vEQMSeguimiento.getCSolicitante().toUpperCase()
					.trim());
			pstmt.setString(8, vEQMSeguimiento.getCObservacion().toUpperCase()
					.trim());
			pstmt.setInt(9, vEQMSeguimiento.getICveEquipo());
			pstmt.setInt(10, vEQMSeguimiento.getICveMantenimiento());
			pstmt.setInt(11, vEQMSeguimiento.getICveMovimiento());
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
			TVEQMSeguimiento vEQMSeguimiento = (TVEQMSeguimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EQMSeguimiento" + " where iCveEquipo = ?"
					+ " and iCveMantenimiento = ?" + " and iCveMovimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMSeguimiento.getICveEquipo());
			pstmt.setInt(2, vEQMSeguimiento.getICveMantenimiento());
			pstmt.setInt(3, vEQMSeguimiento.getICveMovimiento());
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
				warn("delete.closeEQMSeguimiento", ex2);
			}
			return cClave;
		}
	}
}
