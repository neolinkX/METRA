package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de PERCatMotRubNoAp DAO
 * </p>
 * <p>
 * Description: Motivos de no aptitud
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */

public class TDPERCatMotRubNoAp extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERCatMotRubNoAp() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERCatMotRubNoAp = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERCatMotRubNoAp vPERCatMotRubNoAp;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select                 " + "iCvePersonal,          "
					+ "iCveMdoTrans,          " + "iCveCategoria,         "
					+ "iCveCatalogoNoAp,      " + "iCveMotivoNoAp,        "
					+ "iCveRubroNoAp          " + " from PERCatMotRubNoAp "
					+ cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERCatMotRubNoAp = new TVPERCatMotRubNoAp();
				vPERCatMotRubNoAp.setiCvePersonal(rset.getInt(1));
				vPERCatMotRubNoAp.setiCveMdoTrans(rset.getInt(2));
				vPERCatMotRubNoAp.setiCveCategoria(rset.getInt(3));
				vPERCatMotRubNoAp.setiCveCatalogoNoAp(rset.getInt(4));
				vPERCatMotRubNoAp.setICveMotivoNoAp(rset.getInt(5));
				vPERCatMotRubNoAp.setICveRubroNoAp(rset.getInt(6));
				vcPERCatMotRubNoAp.addElement(vPERCatMotRubNoAp);
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
			return vcPERCatMotRubNoAp;
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
			TVPERCatMotRubNoAp vPERCatMotRubNoAp = (TVPERCatMotRubNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into PERCatMotRubNoAp("
					+ "iCvePersonal,                "
					+ "iCveMdoTrans,                "
					+ "iCveCategoria,               "
					+ "iCveCatalogoNoAp,            "
					+ "iCveMotivoNoAp,              "
					+ "iCveRubroNoAp                " + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERCatMotRubNoAp.getiCvePersonal());
			pstmt.setInt(2, vPERCatMotRubNoAp.getiCveMdoTrans());
			pstmt.setInt(3, vPERCatMotRubNoAp.getiCveCategoria());
			pstmt.setInt(4, vPERCatMotRubNoAp.getiCveCatalogoNoAp());
			pstmt.setInt(5, vPERCatMotRubNoAp.getICveMotivoNoAp());
			pstmt.setInt(6, vPERCatMotRubNoAp.getICveRubroNoAp());
			pstmt.executeUpdate();
			cClave = "" + vPERCatMotRubNoAp.getICveRubroNoAp();
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
			TVPERCatMotRubNoAp vPERCatMotRubNoAp = (TVPERCatMotRubNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERCatMotRubNoAp  "
					+ " where iCvePersonal     = ?   "
					+ "   and iCveMdoTrans     = ?   "
					+ "   and iCveCategoria    = ?   "
					+ "   and iCveCatalogoNoAp = ?   "
					+ "   and iCveMotivoNoAp   = ?   "
					+ "   and iCveRubroNoAp    = ?   ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERCatMotRubNoAp.getiCvePersonal());
			pstmt.setInt(2, vPERCatMotRubNoAp.getiCveMdoTrans());
			pstmt.setInt(3, vPERCatMotRubNoAp.getiCveCategoria());
			pstmt.setInt(4, vPERCatMotRubNoAp.getiCveCatalogoNoAp());
			pstmt.setInt(5, vPERCatMotRubNoAp.getICveMotivoNoAp());
			pstmt.setInt(6, vPERCatMotRubNoAp.getICveRubroNoAp());
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
				warn("delete.closePERCatMotRubNoAp", ex2);
			}
			return cClave;
		}
	}

	public Object deleteTodos(Connection conGral, Object obj)
			throws DAOException {
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
			TVPERCatMotRubNoAp vPERCatMotRubNoAp = (TVPERCatMotRubNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERCatMotRubNoAp  "
					+ " where iCvePersonal     = ?   "
					+ "   and iCveMdoTrans     = ?   "
					+ "   and iCveCategoria    = ?   "
					+ "   and iCveCatalogoNoAp = ?   " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERCatMotRubNoAp.getiCvePersonal());
			pstmt.setInt(2, vPERCatMotRubNoAp.getiCveMdoTrans());
			pstmt.setInt(3, vPERCatMotRubNoAp.getiCveCategoria());
			pstmt.setInt(4, vPERCatMotRubNoAp.getiCveCatalogoNoAp());
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
				warn("delete.closePERCatMotRubNoAp", ex2);
			}
			return cClave;
		}
	}

}
