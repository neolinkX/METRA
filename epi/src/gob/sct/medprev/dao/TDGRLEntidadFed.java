package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLEntidadFed DAO
 * </p>
 * <p>
 * Description: Data Access Object para GRLEntidadFed
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */

public class TDGRLEntidadFed extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLEntidadFed() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		return FindByAll("", "");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrder) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLEntidadFed = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLEntidadFed vGRLEntidadFed;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iCvePais,iCveEntidadFed,cNombre,cAbreviatura "
					+ "from GRLEntidadFed";
			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			if (cOrder != null && cOrder.length() != 0) {
				cSQL += cOrder;
			} else {
				cSQL += " order by iCvePais,iCveEntidadFed";
			}
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLEntidadFed = new TVGRLEntidadFed();
				vGRLEntidadFed.setICvePais(rset.getInt("iCvePais"));
				vGRLEntidadFed.setICveEntidadFed(rset.getInt("iCveEntidadFed"));
				vGRLEntidadFed.setCNombre(rset.getString("cAbreviatura") +"   "+ rset.getString("cNombre"));
				vGRLEntidadFed.setCAbreviatura(rset.getString("cAbreviatura"));
				vcGRLEntidadFed.addElement(vGRLEntidadFed);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcGRLEntidadFed;
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
			TVGRLEntidadFed vGRLEntidadFed = (TVGRLEntidadFed) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLEntidadFed(" + "iCvePais,"
					+ "iCveEntidadFed," + "cNombre," + "cAbreviatura"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vGRLEntidadFed.getICvePais());
			pstmt.setInt(2, vGRLEntidadFed.getICveEntidadFed());
			pstmt.setString(3, vGRLEntidadFed.getCNombre());
			pstmt.setString(4, vGRLEntidadFed.getCAbreviatura());
			pstmt.executeUpdate();
			cClave = "" + vGRLEntidadFed.getICvePais();
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
			TVGRLEntidadFed vGRLEntidadFed = (TVGRLEntidadFed) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLEntidadFed" + " set cNombre= ?, "
					+ "cAbreviatura= ? " + "where iCvePais = ? "
					+ " and iCveEntidadFed = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vGRLEntidadFed.getCNombre());
			pstmt.setString(2, vGRLEntidadFed.getCAbreviatura());
			pstmt.setInt(3, vGRLEntidadFed.getICvePais());
			pstmt.setInt(4, vGRLEntidadFed.getICveEntidadFed());
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
			TVGRLEntidadFed vGRLEntidadFed = (TVGRLEntidadFed) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLEntidadFed" + " where iCvePais = ?"
					+ " and iCveEntidadFed = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLEntidadFed.getICvePais());
			pstmt.setInt(2, vGRLEntidadFed.getICveEntidadFed());
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
				warn("delete.closeGRLEntidadFed", ex2);
			}
			return cClave;
		}
	}
}
