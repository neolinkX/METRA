package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EXPRefConcepto DAO
 * </p>
 * <p>
 * Description: Data Acces Object para EXPRefConcepto
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Gabriela P�rez
 * @version 1.0
 */

public class TDEXPRefConcepto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPRefConcepto() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRefConcepto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPRefConcepto vEXPRefConcepto;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select " + "iCveConcepto, iEjercicio, iRefNumerica "
					+ "from EXPRefConcepto";
			String cCveConcepto = (String) hmFiltros.get("iCveConcepto");
			String cRefNumerica = (String) hmFiltros.get("iRefNumerica");
			if (cCveConcepto != null) {
				cSQL += " where iCveConcepto=?";
				cWhereAnd = " and";
			}
			if (cRefNumerica != null) {
				cSQL += cWhereAnd + " iRefNumerica=?";
			}
			cSQL += " order by iCveConcepto";
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveConcepto != null)
				pstmt.setInt(i++, Integer.parseInt(cCveConcepto));
			if (cRefNumerica != null)
				pstmt.setInt(i++, Integer.parseInt(cRefNumerica));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRefConcepto = new TVEXPRefConcepto();
				vEXPRefConcepto.setICveConcepto(rset.getInt("iCveConcepto"));
				vEXPRefConcepto.setIEjercicio(rset.getInt("iEjercicio"));
				vEXPRefConcepto.setIRefNumerica(rset.getInt("iRefNumerica"));
				vcEXPRefConcepto.addElement(vEXPRefConcepto);
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
		}
		return vcEXPRefConcepto;
	}

	/**
	 * Metodo Find By All con Filtro y Orden
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRefConcepto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRefConcepto vEXPRefConcepto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "EXPRefConcepto.iEjercicio,"
					+ "EXPRefConcepto.iCveConcepto,"
					+ "EXPRefConcepto.iRefNumerica" + " from EXPRefConcepto "
					+ cWhere + cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRefConcepto = new TVEXPRefConcepto();
				vEXPRefConcepto.setIEjercicio(rset.getInt(1));
				vEXPRefConcepto.setICveConcepto(rset.getInt(2));
				vEXPRefConcepto.setIRefNumerica(rset.getInt(3));
				vcEXPRefConcepto.addElement(vEXPRefConcepto);
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
			return vcEXPRefConcepto;
		}
	}

	/**
	 * Metodo Find By All con Filtro y Orden
	 */
	public Vector FindByAllCon(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRefConcepto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRefConcepto vEXPRefConcepto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select"
					+ "          EXPRefConcepto.iEjercicio,"
					+ "          EXPRefConcepto.iCveConcepto,"
					+ "          EXPRefConcepto.iRefNumerica,"
					+ "          ExpConcepto.cDscConcepto"
					+ "           from EXPRefConcepto, ExpConcepto"
					+ "	where ExpConcepto.iCveConcepto = EXPRefConcepto.iCveConcepto "
					+ cWhere + " " + cOrderBy;
			// "	Order By EXPRefConcepto.iEjercicio ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRefConcepto = new TVEXPRefConcepto();
				vEXPRefConcepto.setIEjercicio(rset.getInt(1));
				vEXPRefConcepto.setICveConcepto(rset.getInt(2));
				vEXPRefConcepto.setIRefNumerica(rset.getInt(3));
				vEXPRefConcepto.setCDscConcepto(rset.getString(4));
				vcEXPRefConcepto.addElement(vEXPRefConcepto);
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
			return vcEXPRefConcepto;
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
		String cClave = "";
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPRefConcepto vEXPRefConcepto = (TVEXPRefConcepto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPRefConcepto(" + "iEjercicio,"
					+ "iCveConcepto," + "iRefNumerica" + ")values(?,?,?)";
			pstmt = conn.prepareStatement(cSQL);
			// ******************************************************************
			pstmt.setInt(1, vEXPRefConcepto.getIEjercicio());
			pstmt.setInt(2, vEXPRefConcepto.getICveConcepto());
			pstmt.setInt(3, vEXPRefConcepto.getIRefNumerica());
			pstmt.executeUpdate();
			cClave = "" + vEXPRefConcepto.getIEjercicio();
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
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
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
			TVEXPRefConcepto vEXPRefConcepto = (TVEXPRefConcepto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPRefConcepto"
					+ " set iRefNumerica= ? , iEjercicio = ?, iCveConcepto = ? "
					+ " where iEjercicio = ? " + "  and iCveConcepto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPRefConcepto.getIRefNumerica());
			pstmt.setInt(2, vEXPRefConcepto.getIEjercicio());
			pstmt.setInt(3, vEXPRefConcepto.getICveConcepto());
			pstmt.setInt(4, vEXPRefConcepto.getIEjercicio2());
			pstmt.setInt(5, vEXPRefConcepto.getICveConcepto2());
			System.out.println("datos");
			System.out.println(vEXPRefConcepto.getIRefNumerica());
			System.out.println(vEXPRefConcepto.getIEjercicio());
			System.out.println(vEXPRefConcepto.getICveConcepto());
			System.out.println(vEXPRefConcepto.getIEjercicio2());
			System.out.println(vEXPRefConcepto.getICveConcepto2());
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
			TVEXPRefConcepto vEXPRefConcepto = (TVEXPRefConcepto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPRefConcepto" + " where iEjercicio   = ? "
					+ "   and iCveConcepto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPRefConcepto.getIEjercicio());
			pstmt.setInt(2, vEXPRefConcepto.getICveConcepto());
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
				warn("delete.closeEXPRefConcepto", ex2);
			}
			return cClave;
		}
	}

}
