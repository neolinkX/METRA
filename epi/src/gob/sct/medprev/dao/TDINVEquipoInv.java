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
 * Title: Data Acces Object de INVEquipoInv DAO
 * </p>
 * <p>
 * Description: DAO Tabla INVEquipoInv
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 */

public class TDINVEquipoInv extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVEquipoInv() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVEquipoInv = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVEquipoInv vINVEquipoInv;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMdoTrans," + "iIDDGPMPT,"
					+ "iCveUsuInv" + " from INVEquipoInv ";

			if (cWhere != null) {
				cSQL = cSQL + cWhere;
			}

			cSQL = cSQL + " order by iCveUsuInv ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVEquipoInv = new TVINVEquipoInv();
				vINVEquipoInv.setIAnio(rset.getInt(1));
				vINVEquipoInv.setICveMdoTrans(rset.getInt(2));
				vINVEquipoInv.setIIDDGPMPT(rset.getInt(3));
				vINVEquipoInv.setICveUsuInv(rset.getInt(4));
				vcINVEquipoInv.addElement(vINVEquipoInv);
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
			return vcINVEquipoInv;
		}
	}

	/**
	 * Metodo FindMedico
	 */
	public int FindMedico(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVEquipoInv = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVEquipoInv vINVEquipoInv;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMdoTrans," + "iIDDGPMPT,"
					+ "iCveUsuInv" + " from INVEquipoInv ";

			if (cWhere != null) {
				cSQL = cSQL + cWhere;
			}

			cSQL = cSQL + " order by iCveUsuInv ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVEquipoInv = new TVINVEquipoInv();
				vINVEquipoInv.setIAnio(rset.getInt(1));
				vINVEquipoInv.setICveMdoTrans(rset.getInt(2));
				vINVEquipoInv.setIIDDGPMPT(rset.getInt(3));
				vINVEquipoInv.setICveUsuInv(rset.getInt(4));
				iClave = rset.getInt(4);
				vcINVEquipoInv.addElement(vINVEquipoInv);
			}
		} catch (Exception ex) {
			warn("FindMedico", ex);
			throw new DAOException("FindMedico");
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
				warn("FindMedico.close", ex2);
			}
			return iClave;
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
			TVINVEquipoInv vINVEquipoInv = (TVINVEquipoInv) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into INVEquipoInv(" + "iAnio," + "iCveMdoTrans,"
					+ "iIDDGPMPT," + "iCveUsuInv" + ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVEquipoInv.getIAnio());
			pstmt.setInt(2, vINVEquipoInv.getICveMdoTrans());
			pstmt.setInt(3, vINVEquipoInv.getIIDDGPMPT());
			pstmt.setInt(4, vINVEquipoInv.getICveUsuInv());
			pstmt.executeUpdate();
			cClave = "" + vINVEquipoInv.getIAnio();
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
			TVINVEquipoInv vINVEquipoInv = (TVINVEquipoInv) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update INVEquipoInv" + "where iAnio = ? "
					+ "and iCveMdoTrans = ?" + "and iIDDGPMPT = ?"
					+ " and iCveUsuInv = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVEquipoInv.getIAnio());
			pstmt.setInt(2, vINVEquipoInv.getICveMdoTrans());
			pstmt.setInt(3, vINVEquipoInv.getIIDDGPMPT());
			pstmt.setInt(4, vINVEquipoInv.getICveUsuInv());
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
			TVINVEquipoInv vINVEquipoInv = (TVINVEquipoInv) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from INVEquipoInv" + " where iAnio = ?"
					+ " and iCveMdoTrans = ?" + " and iIDDGPMPT = ?"
					+ " and iCveUsuInv = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVEquipoInv.getIAnio());
			pstmt.setInt(2, vINVEquipoInv.getICveMdoTrans());
			pstmt.setInt(3, vINVEquipoInv.getIIDDGPMPT());
			pstmt.setInt(4, vINVEquipoInv.getICveUsuInv());
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
				warn("delete.closeINVEquipoInv", ex2);
			}
			return cClave;
		}
	}
}