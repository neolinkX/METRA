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
 * Title: Data Acces Object de EXPInterconsulta DAO
 * </p>
 * <p>
 * Description: Data Access Object de la tabla EXPInterconsulta
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sanchez
 * @version 1.0
 */

public class TDEXPInterconsulta extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPInterconsulta() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByAll() throws DAOException {
		return this.findByAll("");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByAll(String where) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPInterconsulta = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPInterconsulta vEXPInterconsulta;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveServicio," + "iCveUniMed," + "iCveModulo,"
					+ "dtSolicitud," + "dtPropAtencion," + "cMtvoSolicitud"
					+ " from EXPInterconsulta " + where
					+ " order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPInterconsulta = new TVEXPInterconsulta();
				vEXPInterconsulta.setICveExpediente(rset.getInt(1));
				vEXPInterconsulta.setINumExamen(rset.getInt(2));
				vEXPInterconsulta.setICveServicio(rset.getInt(3));
				vEXPInterconsulta.setICveUniMed(rset.getInt(4));
				vEXPInterconsulta.setICveModulo(rset.getInt(5));
				vEXPInterconsulta.setDtSolicitud(rset.getDate(6));
				vEXPInterconsulta.setDtPropAtencion(rset.getDate(7));
				vEXPInterconsulta.setCMtvoSolicitud(rset.getString(8));
				vcEXPInterconsulta.addElement(vEXPInterconsulta);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcEXPInterconsulta;
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
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPInterconsulta vEXPInterconsulta = (TVEXPInterconsulta) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPInterconsulta(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveUniMed,"
					+ "iCveModulo," + "dtSolicitud," + "dtPropAtencion,"
					+ "cMtvoSolicitud" + ")values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEXPInterconsulta.getICveExpediente());
			pstmt.setInt(2, vEXPInterconsulta.getINumExamen());
			pstmt.setInt(3, vEXPInterconsulta.getICveServicio());
			pstmt.setInt(4, vEXPInterconsulta.getICveUniMed());
			pstmt.setInt(5, vEXPInterconsulta.getICveModulo());
			pstmt.setDate(6, vEXPInterconsulta.getDtSolicitud());
			pstmt.setDate(7, vEXPInterconsulta.getDtPropAtencion());
			pstmt.setString(8, vEXPInterconsulta.getCMtvoSolicitud());
			pstmt.executeUpdate();
			cClave = "" + vEXPInterconsulta.getICveExpediente();
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
				if (dbConn != null)
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
			TVEXPInterconsulta vEXPInterconsulta = (TVEXPInterconsulta) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPInterconsulta" + " set iCveUniMed= ?, "
					+ "iCveModulo= ?, " + "dtSolicitud= ?, "
					+ "dtPropAtencion= ?, " + "cMtvoSolicitud= ? "
					+ "where iCveExpediente = ? " + "and iNumExamen = ?"
					+ " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPInterconsulta.getICveUniMed());
			pstmt.setInt(2, vEXPInterconsulta.getICveModulo());
			pstmt.setDate(3, vEXPInterconsulta.getDtSolicitud());
			pstmt.setDate(4, vEXPInterconsulta.getDtPropAtencion());
			pstmt.setString(5, vEXPInterconsulta.getCMtvoSolicitud());
			pstmt.setInt(6, vEXPInterconsulta.getICveExpediente());
			pstmt.setInt(7, vEXPInterconsulta.getINumExamen());
			pstmt.setInt(8, vEXPInterconsulta.getICveServicio());
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
				if (dbConn != null)
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
			TVEXPInterconsulta vEXPInterconsulta = (TVEXPInterconsulta) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPInterconsulta" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPInterconsulta.getICveExpediente());
			pstmt.setInt(2, vEXPInterconsulta.getINumExamen());
			pstmt.setInt(3, vEXPInterconsulta.getICveServicio());
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeEXPInterconsulta", ex2);
			}
			return cClave;
		}
	}
}
