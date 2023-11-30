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
 * Title: Data Acces Object de INVRama DAO
 * </p>
 * <p>
 * Description: DAO INVRama
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

public class TDINVRama extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVRama() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVRama vINVRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMdoTrans," + "iIDDGPMPT,"
					+ "iCveServicio," + "iCveRama," + "lConcluido,"
					+ "dtInicio," + "dtFin," + "iCveUsuAplica"
					+ " from INVRama order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRama = new TVINVRama();
				vINVRama.setIAnio(rset.getInt(1));
				vINVRama.setICveMdoTrans(rset.getInt(2));
				vINVRama.setIIDDGPMPT(rset.getInt(3));
				vINVRama.setICveServicio(rset.getInt(4));
				vINVRama.setICveRama(rset.getInt(5));
				vINVRama.setLConcluido(rset.getInt(6));
				vINVRama.setDtInicio(rset.getDate(7));
				vINVRama.setDtFin(rset.getDate(8));
				vINVRama.setICveUsuAplica(rset.getInt(9));
				vcINVRama.addElement(vINVRama);
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
			return vcINVRama;
		}
	}

	public Vector FindCustomWhere(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVRama vINVRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMdoTrans," + "iIDDGPMPT,"
					+ "iCveServicio," + "iCveRama," + "lConcluido,"
					+ "dtInicio," + "dtFin," + "iCveUsuAplica"
					+ " from INVRama " + cCondicion + " order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRama = new TVINVRama();
				vINVRama.setIAnio(rset.getInt(1));
				vINVRama.setICveMdoTrans(rset.getInt(2));
				vINVRama.setIIDDGPMPT(rset.getInt(3));
				vINVRama.setICveServicio(rset.getInt(4));
				vINVRama.setICveRama(rset.getInt(5));
				vINVRama.setLConcluido(rset.getInt(6));
				vINVRama.setDtInicio(rset.getDate(7));
				vINVRama.setDtFin(rset.getDate(8));
				vINVRama.setICveUsuAplica(rset.getInt(9));
				vcINVRama.addElement(vINVRama);
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
			return vcINVRama;
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
			TVINVRama vINVRama = (TVINVRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into INVRama(" + "iAnio," + "iCveMdoTrans,"
					+ "iIDDGPMPT," + "iCveServicio," + "iCveRama,"
					+ "lConcluido," + "dtInicio," + "dtFin," + "iCveUsuAplica"
					+ ")values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vINVRama.getIAnio());
			pstmt.setInt(2, vINVRama.getICveMdoTrans());
			pstmt.setInt(3, vINVRama.getIIDDGPMPT());
			pstmt.setInt(4, vINVRama.getICveServicio());
			pstmt.setInt(5, vINVRama.getICveRama());
			pstmt.setInt(6, vINVRama.getLConcluido());
			pstmt.setDate(7, vINVRama.getDtInicio());
			pstmt.setDate(8, vINVRama.getDtFin());
			pstmt.setInt(9, vINVRama.getICveUsuAplica());
			pstmt.executeUpdate();
			cClave = "" + vINVRama.getIAnio();
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
	public Object insertFromExpExamen(Connection conGral, int iAnio,
			int iCveMdoTrans, int iIDDGPMPT, int iCveServicio, int iCveRama,
			int iCveUsuario) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		TFechas fechas = new TFechas();
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into INVRama(" + "iAnio," + "iCveMdoTrans,"
					+ "iIDDGPMPT," + "iCveServicio," + "iCveRama,"
					+ "dtInicio," + "iCveUsuAplica" + ")values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iCveMdoTrans);
			pstmt.setInt(3, iIDDGPMPT);
			pstmt.setInt(4, iCveServicio);
			pstmt.setInt(5, iCveRama);
			pstmt.setDate(6, fechas.TodaySQL());
			pstmt.setInt(7, iCveUsuario);
			pstmt.executeUpdate();
			cClave = "" + iAnio;
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
			TVINVRama vINVRama = (TVINVRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update INVRama" + " set lConcluido= ?, " + "dtInicio= ?, "
					+ "dtFin= ?, " + "iCveUsuAplica= ? " + "where iAnio = ? "
					+ "and iCveMdoTrans = ?" + "and iIDDGPMPT = ?"
					+ "and iCveServicio = ?" + " and iCveRama = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRama.getLConcluido());
			pstmt.setDate(2, vINVRama.getDtInicio());
			pstmt.setDate(3, vINVRama.getDtFin());
			pstmt.setInt(4, vINVRama.getICveUsuAplica());
			pstmt.setInt(5, vINVRama.getIAnio());
			pstmt.setInt(6, vINVRama.getICveMdoTrans());
			pstmt.setInt(7, vINVRama.getIIDDGPMPT());
			pstmt.setInt(8, vINVRama.getICveServicio());
			pstmt.setInt(9, vINVRama.getICveRama());
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
			TVINVRama vINVRama = (TVINVRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from INVRama" + " where iAnio = ?"
					+ " and iCveMdoTrans = ?" + " and iIDDGPMPT = ?"
					+ " and iCveServicio = ?" + " and iCveRama = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRama.getIAnio());
			pstmt.setInt(2, vINVRama.getICveMdoTrans());
			pstmt.setInt(3, vINVRama.getIIDDGPMPT());
			pstmt.setInt(4, vINVRama.getICveServicio());
			pstmt.setInt(5, vINVRama.getICveRama());
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
				warn("delete.closeINVRama", ex2);
			}
			return cClave;
		}
	}
}