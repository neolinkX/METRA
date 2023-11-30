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
 * Title: Data Acces Object de INVServicio DAO
 * </p>
 * <p>
 * Description: DAO INVServicio
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

public class TDINVServicio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVServicio() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVServicio vINVServicio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMdoTrans," + "iIDDGPMPT,"
					+ "iCveServicio," + "lConcluido," + "dtInicio," + "dtFin,"
					+ "iCveUsuAplica" + " from INVServicio order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVServicio = new TVINVServicio();
				vINVServicio.setIAnio(rset.getInt(1));
				vINVServicio.setICveMdoTrans(rset.getInt(2));
				vINVServicio.setIIDDGPMPT(rset.getInt(3));
				vINVServicio.setICveServicio(rset.getInt(4));
				vINVServicio.setLConcluido(rset.getInt(5));
				vINVServicio.setDtInicio(rset.getDate(6));
				vINVServicio.setDtFin(rset.getDate(7));
				vINVServicio.setICveUsuAplica(rset.getInt(8));
				vcINVServicio.addElement(vINVServicio);
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
			return vcINVServicio;
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
			TVINVServicio vINVServicio = (TVINVServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into INVServicio(" + "iAnio," + "iCveMdoTrans,"
					+ "iIDDGPMPT," + "iCveServicio," + "lConcluido,"
					+ "dtInicio," + "dtFin," + "iCveUsuAplica"
					+ ")values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vINVServicio.getIAnio());
			pstmt.setInt(2, vINVServicio.getICveMdoTrans());
			pstmt.setInt(3, vINVServicio.getIIDDGPMPT());
			pstmt.setInt(4, vINVServicio.getICveServicio());
			pstmt.setInt(5, vINVServicio.getLConcluido());
			pstmt.setDate(6, vINVServicio.getDtInicio());
			pstmt.setDate(7, vINVServicio.getDtFin());
			pstmt.setInt(8, vINVServicio.getICveUsuAplica());
			pstmt.executeUpdate();
			cClave = "" + vINVServicio.getIAnio();
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
			int iCveMdoTrans, int iIDDGPMPT, int iCveServicio, int iCveUsuario)
			throws DAOException {
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
			cSQL = "insert into INVServicio(" + "iAnio," + "iCveMdoTrans,"
					+ "iIDDGPMPT," + "iCveServicio," + "iCveUsuAplica,"
					+ "dtInicio" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iCveMdoTrans);
			pstmt.setInt(3, iIDDGPMPT);
			pstmt.setInt(4, iCveServicio);
			pstmt.setInt(5, iCveUsuario);
			pstmt.setDate(6, fechas.TodaySQL());

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
			TVINVServicio vINVServicio = (TVINVServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update INVServicio" + " set lConcluido= ?, "
					+ "dtInicio= ?, " + "dtFin= ?, " + "iCveUsuAplica= ? "
					+ "where iAnio = ? " + "and iCveMdoTrans = ?"
					+ "and iIDDGPMPT = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVServicio.getLConcluido());
			pstmt.setDate(2, vINVServicio.getDtInicio());
			pstmt.setDate(3, vINVServicio.getDtFin());
			pstmt.setInt(4, vINVServicio.getICveUsuAplica());
			pstmt.setInt(5, vINVServicio.getIAnio());
			pstmt.setInt(6, vINVServicio.getICveMdoTrans());
			pstmt.setInt(7, vINVServicio.getIIDDGPMPT());
			pstmt.setInt(8, vINVServicio.getICveServicio());
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
			TVINVServicio vINVServicio = (TVINVServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from INVServicio" + " where iAnio = ?"
					+ " and iCveMdoTrans = ?" + " and iIDDGPMPT = ?"
					+ " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVServicio.getIAnio());
			pstmt.setInt(2, vINVServicio.getICveMdoTrans());
			pstmt.setInt(3, vINVServicio.getIIDDGPMPT());
			pstmt.setInt(4, vINVServicio.getICveServicio());
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
				warn("delete.closeINVServicio", ex2);
			}
			return cClave;
		}
	}
}