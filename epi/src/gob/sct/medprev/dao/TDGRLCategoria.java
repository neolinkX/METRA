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
 * Title: Data Acces Object de GRLCategoria DAO
 * </p>
 * <p>
 * Description: DAO Tabla GRLCategoria
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

public class TDGRLCategoria extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLCategoria() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cModoTrans) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLCategoria = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLCategoria vGRLCategoria;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMdoTrans," + "iCveCategoria,"
					+ "cDscCategoria," + "cDscBreve," + "iTmpoReexp"
					+ " from GRLCategoria " + " where iCveMdoTrans = "
					+ cModoTrans + " " + "order by iCveMdoTrans";
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLCategoria = new TVGRLCategoria();
				vGRLCategoria.setICveMdoTrans(rset.getInt(1));
				vGRLCategoria.setICveCategoria(rset.getInt(2));
				vGRLCategoria.setCDscCategoria(rset.getString(3));
				vGRLCategoria.setCDscBreve(rset.getString(4));
				vGRLCategoria.setITmpoReexp(rset.getInt(5));
				vcGRLCategoria.addElement(vGRLCategoria);
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
			return vcGRLCategoria;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLCategoria = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLCategoria vGRLCategoria;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMdoTrans," + "iCveCategoria,"
					+ "cDscCategoria," + "cDscBreve," + "iTmpoReexp,"
					+ "lActivo" + " from GRLCategoria " + cWhere + cOrderBy;
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLCategoria = new TVGRLCategoria();
				vGRLCategoria.setICveMdoTrans(rset.getInt(1));
				vGRLCategoria.setICveCategoria(rset.getInt(2));
				vGRLCategoria.setCDscCategoria(rset.getString(3));
				vGRLCategoria.setCDscBreve(rset.getString(4));
				vGRLCategoria.setITmpoReexp(rset.getInt(5));
				vGRLCategoria.setLActivo(rset.getInt(6));

				vcGRLCategoria.addElement(vGRLCategoria);
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
			return vcGRLCategoria;
		}
	}

	/**
	 * Metodo FindWhere
	 */
	public Vector FindWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLCategoria = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLCategoria vGRLCategoria;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select " + "iCveMdoTrans," + "iCveCategoria,"
					+ "cDscCategoria," + "cDscBreve," + "iTmpoReexp,"
					+ "lActivo" + " from GRLCategoria " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLCategoria = new TVGRLCategoria();
				vGRLCategoria.setICveMdoTrans(rset.getInt(1));
				vGRLCategoria.setICveCategoria(rset.getInt(2));
				vGRLCategoria.setCDscCategoria(rset.getString(3));
				vGRLCategoria.setCDscBreve(rset.getString(4));
				vGRLCategoria.setITmpoReexp(rset.getInt(5));
				vGRLCategoria.setLActivo(rset.getInt(6));

				vcGRLCategoria.addElement(vGRLCategoria);
			}
		} catch (Exception ex) {
			warn("FindWhere", ex);
			throw new DAOException("FindWhere");
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
				warn("FindWhere.close", ex2);
			}
			return vcGRLCategoria;
		}
	}

	/**
	 * le Metodo Insert
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
			TVGRLCategoria vGRLCategoria = (TVGRLCategoria) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLCategoria(" + "iCveMdoTrans,"
					+ "iCveCategoria," + "cDscCategoria," + "cDscBreve,"
					+ "iTmpoReexp," + "lActivo" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveCategoria) from GRLCategoria where iCveMdoTrans = "
					+ vGRLCategoria.getICveMdoTrans();
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
			vGRLCategoria.setICveCategoria(iMax);
			// ******************************************************************
			pstmt.setInt(1, vGRLCategoria.getICveMdoTrans());
			pstmt.setInt(2, vGRLCategoria.getICveCategoria());
			pstmt.setString(3, vGRLCategoria.getCDscCategoria());
			pstmt.setString(4, vGRLCategoria.getCDscBreve());
			pstmt.setInt(5, vGRLCategoria.getITmpoReexp());
			pstmt.setInt(6, vGRLCategoria.getLActivo());

			pstmt.executeUpdate();
			cClave = "" + vGRLCategoria.getICveCategoria();
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
			TVGRLCategoria vGRLCategoria = (TVGRLCategoria) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLCategoria" + " set cDscCategoria= ?, "
					+ "cDscBreve= ?, " + "iTmpoReexp= ?, " + "lActivo= ? "
					+ "where iCveMdoTrans = ? " + " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vGRLCategoria.getCDscCategoria());
			pstmt.setString(2, vGRLCategoria.getCDscBreve());
			pstmt.setInt(3, vGRLCategoria.getITmpoReexp());
			pstmt.setInt(4, vGRLCategoria.getLActivo());
			pstmt.setInt(5, vGRLCategoria.getICveMdoTrans());
			pstmt.setInt(6, vGRLCategoria.getICveCategoria());
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
			TVGRLCategoria vGRLCategoria = (TVGRLCategoria) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLCategoria" + " where iCveMdoTrans = ?"
					+ " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLCategoria.getICveMdoTrans());
			pstmt.setInt(2, vGRLCategoria.getICveCategoria());
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
				warn("delete.closeGRLCategoria", ex2);
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
			TVGRLCategoria vGRLCategoria = (TVGRLCategoria) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLCategoria" + " set lActivo= ? "
					+ " where iCveMdoTrans = ?" + " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vGRLCategoria.getICveMdoTrans());
			pstmt.setInt(3, vGRLCategoria.getICveCategoria());
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
