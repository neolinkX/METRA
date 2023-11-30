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
 * Title: Data Acces Object de INVRecomFinal DAO
 * </p>
 * <p>
 * Description: DAO Recomendacion Final
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */

public class TDINVRecomFinal extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVRecomFinal() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRecomFinal = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVRecomFinal vINVRecomFinal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int count;
			cSQL = " Select " + "       a.iAnio," + "       a.iCveMdoTrans,"
					+ "       a.iIDDGPMPT," + "       a.iCveRecomendacion,"
					+ "       b.cDscRecomendacion"
					+ " From  INVRecomFinal a,  INVRecomendacion b" + cWhere;
			if (cWhere.trim().length() > 0) {
				cSQL += " And a.iCveRecomendacion = b.iCveRecomendacion";
			} else {
				cSQL += " Where a.iCveRecomendacion = b.iCveRecomendacion";
			}

			cSQL += " Order by a.iAnio";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRecomFinal = new TVINVRecomFinal();
				vINVRecomFinal.setIAnio(rset.getInt(1));
				vINVRecomFinal.setICveMdoTrans(rset.getInt(2));
				vINVRecomFinal.setIIDDGPMPT(rset.getInt(3));
				vINVRecomFinal.setICveRecomendacion(rset.getInt(4));
				vINVRecomFinal.setCDscRecomendacion(rset.getString(5));
				vcINVRecomFinal.addElement(vINVRecomFinal);
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
			return vcINVRecomFinal;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj,
			String cCveRecomendacion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVINVRecomFinal vINVRecomFinal = (TVINVRecomFinal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from INVRecomFinal "
					+ " Where iAnio  =" + vINVRecomFinal.getIAnio()
					+ " And iCveMdoTrans = " + vINVRecomFinal.getICveMdoTrans()
					+ " And iIDDGPMPT=" + vINVRecomFinal.getIIDDGPMPT()
					+ " And iCveRecomendacion = " + cCveRecomendacion;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {
				cSQL = "Update INVRecomFinal" + " Set  iCveRecomendacion = ?"
						+ " Where iAnio = ? " + " And iCveMdoTrans = ?"
						+ " And iIDDGPMPT = ?" + " And iCveRecomendacion = ?";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, Integer.parseInt(cCveRecomendacion));
				pstmt.setInt(2, vINVRecomFinal.getIAnio());
				pstmt.setInt(3, vINVRecomFinal.getICveMdoTrans());
				pstmt.setInt(4, vINVRecomFinal.getIIDDGPMPT());
				pstmt.setInt(5, Integer.parseInt(cCveRecomendacion));

			} else {
				cSQL = "insert into INVRecomFinal(" + "iAnio,"
						+ "iCveMdoTrans," + "iIDDGPMPT," + "iCveRecomendacion"
						+ ")values(?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vINVRecomFinal.getIAnio());
				pstmt.setInt(2, vINVRecomFinal.getICveMdoTrans());
				pstmt.setInt(3, vINVRecomFinal.getIIDDGPMPT());
				pstmt.setInt(4, Integer.parseInt(cCveRecomendacion));
			}

			pstmt.executeUpdate();
			cClave = "" + vINVRecomFinal.getIAnio();
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
	 * Metodo Insert
	 */
	public Object insertNota(Connection conGral, Object obj, String cConclusion)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVINVRecomFinal vINVRecomFinal = (TVINVRecomFinal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from INVRegistro "
					+ " Where iAnio  =" + vINVRecomFinal.getIAnio()
					+ " And iCveMdoTrans = " + vINVRecomFinal.getICveMdoTrans()
					+ " And iIDDGPMPT=" + vINVRecomFinal.getIIDDGPMPT();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {
				cSQL = "Update INVRegistro" + " Set  cConclusion = ?"
						+ " Where iAnio = ? " + " And iCveMdoTrans = ?"
						+ " And iIDDGPMPT = ?";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setString(1, cConclusion);
				pstmt.setInt(2, vINVRecomFinal.getIAnio());
				pstmt.setInt(3, vINVRecomFinal.getICveMdoTrans());
				pstmt.setInt(4, vINVRecomFinal.getIIDDGPMPT());

			}

			pstmt.executeUpdate();
			cClave = "" + vINVRecomFinal.getIAnio();
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
			TVINVRecomFinal vINVRecomFinal = (TVINVRecomFinal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update INVRecomFinal" + "where iAnio = ? "
					+ "and iCveMdoTrans = ?" + "and iIDDGPMPT = ?"
					+ " and iCveRecomendacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRecomFinal.getIAnio());
			pstmt.setInt(2, vINVRecomFinal.getICveMdoTrans());
			pstmt.setInt(3, vINVRecomFinal.getIIDDGPMPT());
			pstmt.setInt(4, vINVRecomFinal.getICveRecomendacion());
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
			TVINVRecomFinal vINVRecomFinal = (TVINVRecomFinal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from INVRecomFinal" + " where iAnio = ?"
					+ " and iCveMdoTrans = ?" + " and iIDDGPMPT = ?"
					+ " and iCveRecomendacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRecomFinal.getIAnio());
			pstmt.setInt(2, vINVRecomFinal.getICveMdoTrans());
			pstmt.setInt(3, vINVRecomFinal.getIIDDGPMPT());
			pstmt.setInt(4, vINVRecomFinal.getICveRecomendacion());
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
				warn("delete.closeINVRecomFinal", ex2);
			}
			return cClave;
		}
	}
}