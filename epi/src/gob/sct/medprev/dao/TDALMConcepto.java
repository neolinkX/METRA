package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMConcepto DAO
 * </p>
 * <p>
 * Description: TD de ALMConcepto
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Juan Manuel Vazquez
 * @version 1.0
 */

public class TDALMConcepto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMConcepto() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMConcepto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMConcepto vALMConcepto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "C.iCveTpoMovimiento,"
					+ "C.iCveConcepto,"
					+ "C.cDscConcepto,"
					+ "C.lActivo,"
					+ "M.cDscBreve cDscMovimiento "
					+ " from ALMConcepto C "
					+ " left join ALMTpoMovimiento M on ( C.iCveTpoMovimiento = M.iCveTpoMovimiento )"
					+ cFiltro + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMConcepto = new TVALMConcepto();
				vALMConcepto.setICveTpoMovimiento(rset.getInt(1));
				vALMConcepto.setICveConcepto(rset.getInt(2));
				vALMConcepto.setCDscConcepto(rset.getString(3));
				vALMConcepto.setLActivo(rset.getInt(4));
				vALMConcepto.setCDscMovimiento(rset.getString(5));

				vcALMConcepto.addElement(vALMConcepto);

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
			return vcALMConcepto;
		}
	}

	public Vector FindByCustomWhere(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMConcepto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMConcepto vALMConcepto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMConcepto.iCveTpoMovimiento,"
					+ " ALMConcepto.iCveConcepto,"
					+ " ALMConcepto.cDscConcepto," + " ALMConcepto.lActivo "
					+ " from ALMConcepto " + cFiltro + "";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMConcepto = new TVALMConcepto();
				vALMConcepto.setICveTpoMovimiento(rset.getInt(1));
				vALMConcepto.setICveConcepto(rset.getInt(2));
				vALMConcepto.setCDscConcepto(rset.getString(3));
				vALMConcepto.setLActivo(rset.getInt(4));
				vcALMConcepto.addElement(vALMConcepto);

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
			return vcALMConcepto;
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
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		int iCve = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVALMConcepto vALMConcepto = (TVALMConcepto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "";
			cSQL = "Select Max(iCveConcepto) from ALMConcepto "
					+ "where iCveTpoMovimiento = "
					+ vALMConcepto.getICveTpoMovimiento();

			pstmt2 = conn.prepareStatement(cSQL);
			rset = pstmt2.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1) + 1;
			}
			rset.close();
			pstmt2.close();

			vALMConcepto.setICveConcepto(iCve);

			cSQL = "insert into ALMConcepto(" + "iCveTpoMovimiento,"
					+ "iCveConcepto," + "cDscConcepto," + "lActivo"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vALMConcepto.getICveTpoMovimiento());
			pstmt.setInt(2, vALMConcepto.getICveConcepto());
			pstmt.setString(3, vALMConcepto.getCDscConcepto().toUpperCase()
					.trim());
			pstmt.setInt(4, vALMConcepto.getLActivo());

			pstmt.executeUpdate();
			cClave = "" + vALMConcepto.getICveConcepto();
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
			TVALMConcepto vALMConcepto = (TVALMConcepto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMConcepto" + " set cDscConcepto= ?, "
					+ "lActivo= ? " + "where iCveTpoMovimiento = ? "
					+ " and iCveConcepto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vALMConcepto.getCDscConcepto().toUpperCase()
					.trim());
			pstmt.setInt(2, vALMConcepto.getLActivo());

			pstmt.setInt(3, vALMConcepto.getICveTpoMovimiento());
			pstmt.setInt(4, vALMConcepto.getICveConcepto());
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
			TVALMConcepto vALMConcepto = (TVALMConcepto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMConcepto" + " where iCveTpoMovimiento = ?"
					+ " and iCveConcepto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMConcepto.getICveTpoMovimiento());
			pstmt.setInt(2, vALMConcepto.getICveConcepto());
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
				warn("delete.closeALMConcepto", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Disable
	 */
	public Object disable(Connection conGral, Object obj) throws DAOException {
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
			TVALMConcepto vALMConcepto = (TVALMConcepto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMConcepto" + " set lActivo= 0 "
					+ "where iCveTpoMovimiento = ?" + " and iCveConcepto = ?";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMConcepto.getICveTpoMovimiento());
			pstmt.setInt(2, vALMConcepto.getICveConcepto());
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
				warn("disable.close", ex2);
			}
			return cClave;
		}
	}
}
