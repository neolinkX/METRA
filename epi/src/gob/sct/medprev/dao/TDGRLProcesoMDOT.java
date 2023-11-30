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
 * Title: Data Acces Object de GRLProcesoMDOT DAO
 * </p>
 * <p>
 * Description: DAOGRLProcesoMDOT
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

public class TDGRLProcesoMDOT extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLProcesoMDOT() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLProcesoMDOT = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLProcesoMDOT vGRLProcesoMDOT;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveUniMed," + "iCveProceso," + "iCveModulo,"
					+ "iCveMdoTrans"
					+ " from GRLProcesoMDOT order by iCveUniMed";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLProcesoMDOT = new TVGRLProcesoMDOT();
				vGRLProcesoMDOT.setICveUniMed(rset.getInt(1));
				vGRLProcesoMDOT.setICveProceso(rset.getInt(2));
				vGRLProcesoMDOT.setICveModulo(rset.getInt(3));
				vGRLProcesoMDOT.setICveMdoTrans(rset.getInt(4));
				vcGRLProcesoMDOT.addElement(vGRLProcesoMDOT);
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
			return vcGRLProcesoMDOT;
		}
	}

	/**
	 * Metodo FindMTXMod
	 */
	public Vector FindMTXMod(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLProcesoMDOT = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLProcesoMDOT vGRLProcesoMDOT;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct GRLProcesoMDoT.iCveMdoTrans, GRLProcesoMDoT.iCveUniMed, "
					+ "GRLProcesoMDoT.iCveModulo, GRLMdoTrans.cDscMdoTrans "
					+ "from GRLProcesoMDoT "
					+ "inner join GRLMdoTrans on GRLMdoTrans.iCveMdoTrans = GRLProcesoMDoT.iCveMdoTrans "
					+ cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLProcesoMDOT = new TVGRLProcesoMDOT();
				vGRLProcesoMDOT.setICveUniMed(rset.getInt(2));
				vGRLProcesoMDOT.setCDscMdoTrans(rset.getString(4));
				vGRLProcesoMDOT.setICveModulo(rset.getInt(3));
				vGRLProcesoMDOT.setICveMdoTrans(rset.getInt(1));
				vcGRLProcesoMDOT.addElement(vGRLProcesoMDOT);
			}
		} catch (Exception ex) {
			warn("FindMTXMod", ex);
			throw new DAOException("FindMTXMod");
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
				warn("FindMTXMod.close", ex2);
			}
			return vcGRLProcesoMDOT;
		}
	}

	/**
	 * Metodo FindByUMMDPR
	 */
	public Vector FindByUMMDPR(String cWhere, String cOrder)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLProcesoMDOT = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLProcesoMDOT vGRLProcesoMDOT;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select a.iCveUniMed," + " a.iCveProceso,"
					+ " a.iCveModulo, " + " a.iCveMdoTrans,"
					+ " b.cDscMdoTrans "
					+ " From   GRLProcesoMDOT a, GRLMdoTrans b " + cWhere + " "
					+ cOrder;
			// System.out.println("TDGRLPROCESO "+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLProcesoMDOT = new TVGRLProcesoMDOT();
				vGRLProcesoMDOT.setICveUniMed(rset.getInt(1));
				vGRLProcesoMDOT.setICveProceso(rset.getInt(2));
				vGRLProcesoMDOT.setICveModulo(rset.getInt(3));
				vGRLProcesoMDOT.setICveMdoTrans(rset.getInt(4));
				vGRLProcesoMDOT.setCDscMdoTrans(rset.getString(5));
				vcGRLProcesoMDOT.addElement(vGRLProcesoMDOT);
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
			return vcGRLProcesoMDOT;
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
			TVGRLProcesoMDOT vGRLProcesoMDOT = (TVGRLProcesoMDOT) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLProcesoMDOT(" + "iCveUniMed,"
					+ "iCveProceso," + "iCveModulo," + "iCveMdoTrans"
					+ ")values(" + vGRLProcesoMDOT.getICveUniMed() + ", "
					+ vGRLProcesoMDOT.getICveProceso() + ", "
					+ vGRLProcesoMDOT.getICveModulo() + ", "
					+ vGRLProcesoMDOT.getICveMdoTrans() + ")";
			// System.out.println("slw"+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			// pstmt.setInt(1, vGRLProcesoMDOT.getICveUniMed());
			// pstmt.setInt(2, vGRLProcesoMDOT.getICveProceso());
			// pstmt.setInt(3, vGRLProcesoMDOT.getICveModulo());
			// pstmt.setInt(4, vGRLProcesoMDOT.getICveMdoTrans());
			pstmt.executeUpdate();
			cClave = "" + vGRLProcesoMDOT.getICveUniMed();
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
			TVGRLProcesoMDOT vGRLProcesoMDOT = (TVGRLProcesoMDOT) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLProcesoMDOT" + "where iCveUniMed = ? "
					+ "and iCveProceso = ?" + "and iCveModulo = ?"
					+ " and iCveMdoTrans = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLProcesoMDOT.getICveUniMed());
			pstmt.setInt(2, vGRLProcesoMDOT.getICveProceso());
			pstmt.setInt(3, vGRLProcesoMDOT.getICveModulo());
			pstmt.setInt(4, vGRLProcesoMDOT.getICveMdoTrans());
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
			TVGRLProcesoMDOT vGRLProcesoMDOT = (TVGRLProcesoMDOT) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLProcesoMDOT" + " where iCveUniMed = ?"
					+ " and iCveProceso = ?" + " and iCveModulo = ?"
					+ " and iCveMdoTrans = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLProcesoMDOT.getICveUniMed());
			pstmt.setInt(2, vGRLProcesoMDOT.getICveProceso());
			pstmt.setInt(3, vGRLProcesoMDOT.getICveModulo());
			pstmt.setInt(4, vGRLProcesoMDOT.getICveMdoTrans());
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
				warn("delete.closeGRLProcesoMDOT", ex2);
			}
			return cClave;
		}
	}
}