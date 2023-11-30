package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de PERMotivoNoAp DAO
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

public class TDPERMotivoNoAp extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERMotivoNoAp() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERMotivoNoAp = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERMotivoNoAp vPERMotivoNoAp;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMotivoNoAp," + "cDscMotivoNoAp,"
					+ "iPeriodo" + " from PERMotivoNoAp " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERMotivoNoAp = new TVPERMotivoNoAp();
				vPERMotivoNoAp.setICveMotivoNoAp(rset.getInt(1));
				vPERMotivoNoAp.setCDscMotivoNoAp(rset.getString(2));
				vPERMotivoNoAp.setIPeriodo(rset.getInt(3));
				vcPERMotivoNoAp.addElement(vPERMotivoNoAp);
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
			return vcPERMotivoNoAp;
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
			TVPERMotivoNoAp vPERMotivoNoAp = (TVPERMotivoNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into PERMotivoNoAp(" + "iCveMotivoNoAp,"
					+ "cDscMotivoNoAp," + "iPeriodo" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveMotivoNoAp) from PERMotivoNoAp";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			if (iMax == 0) {
				iMax = 1;
			} else {
				iMax += 1;
			}
			vPERMotivoNoAp.setICveMotivoNoAp(iMax);
			// ******************************************************************
			pstmt.setInt(1, vPERMotivoNoAp.getICveMotivoNoAp());
			pstmt.setString(2, vPERMotivoNoAp.getCDscMotivoNoAp());
			pstmt.setInt(3, vPERMotivoNoAp.getIPeriodo());
			pstmt.executeUpdate();
			cClave = "" + vPERMotivoNoAp.getICveMotivoNoAp();
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
			TVPERMotivoNoAp vPERMotivoNoAp = (TVPERMotivoNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERMotivoNoAp" + " set cDscMotivoNoAp= ?, "
					+ "iPeriodo= ? " + "where iCveMotivoNoAp = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vPERMotivoNoAp.getCDscMotivoNoAp());
			pstmt.setInt(2, vPERMotivoNoAp.getIPeriodo());
			pstmt.setInt(3, vPERMotivoNoAp.getICveMotivoNoAp());
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
			TVPERMotivoNoAp vPERMotivoNoAp = (TVPERMotivoNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERMotivoNoAp" + " where iCveMotivoNoAp = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERMotivoNoAp.getICveMotivoNoAp());
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
				warn("delete.closePERMotivoNoAp", ex2);
			}
			return cClave;
		}
	}
}
