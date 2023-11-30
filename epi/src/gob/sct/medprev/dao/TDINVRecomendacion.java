package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de INVRecomendacion DAO
 * </p>
 * <p>
 * Description: Data Access Object para INVRecomendacion
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

public class TDINVRecomendacion extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVRecomendacion() {
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
		Vector vcINVRecomendacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRecomendacion vINVRecomendacion;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iCveRecomendacion,cDscRecomendacion,cCveInterna,lActivo "
					+ "from INVRecomendacion";
			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			if (cOrder != null && cOrder.length() != 0) {
				cSQL += cOrder;
			} else {
				cSQL += " order by iCveRecomendacion";
			}
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRecomendacion = new TVINVRecomendacion();
				vINVRecomendacion.setICveRecomendacion(rset
						.getInt("iCveRecomendacion"));
				vINVRecomendacion.setCDscRecomendacion(rset
						.getString("cDscRecomendacion"));
				vINVRecomendacion.setCCveInterna(rset.getString("cCveInterna"));
				vINVRecomendacion.setLActivo(rset.getInt("lActivo"));
				vcINVRecomendacion.addElement(vINVRecomendacion);
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
		return vcINVRecomendacion;
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral,
			TVINVRecomendacion vINVRecomendacion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		ResultSet rslt = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			stmt = conn.createStatement();
			rslt = stmt
					.executeQuery("select max(iCveRecomendacion) cta from INVRecomendacion");
			if (rslt.next()) {
				vINVRecomendacion.setICveRecomendacion(rslt.getInt("cta") + 1);
			} else {
				vINVRecomendacion.setICveRecomendacion(1);
			}
			rslt.close();
			stmt.close();
			if (vINVRecomendacion.getICveRecomendacion() > 0) {
				String cSQL = "insert into INVRecomendacion ("
						+ "iCveRecomendacion,cDscRecomendacion,cCveInterna,lActivo"
						+ ") values (?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vINVRecomendacion.getICveRecomendacion());
				pstmt.setString(2, vINVRecomendacion.getCDscRecomendacion());
				pstmt.setString(3, vINVRecomendacion.getCCveInterna());
				pstmt.setInt(4, vINVRecomendacion.getLActivo());
				pstmt.executeUpdate();
				cClave = "" + vINVRecomendacion.getICveRecomendacion();
				if (conGral == null) {
					conn.commit();
				}
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
				if (rslt != null) {
					rslt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo update
	 */
	public Object update(Connection conGral,
			TVINVRecomendacion vINVRecomendacion) throws DAOException {
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "update INVRecomendacion "
					+ "set cDscRecomendacion=?,cCveInterna=?,lActivo=? "
					+ "where iCveRecomendacion=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vINVRecomendacion.getCDscRecomendacion());
			pstmt.setString(2, vINVRecomendacion.getCCveInterna());
			pstmt.setInt(3, vINVRecomendacion.getLActivo());
			pstmt.setInt(4, vINVRecomendacion.getICveRecomendacion());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo disable
	 */
	public Object disable(Connection conGral,
			TVINVRecomendacion vINVRecomendacion) throws DAOException {
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "update INVRecomendacion " + "set lActivo=? "
					+ "where iCveRecomendacion=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRecomendacion.getLActivo());
			pstmt.setInt(2, vINVRecomendacion.getICveRecomendacion());
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
				warn("disable", ex1);
			}
			warn("disable", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("disable.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo Delete
	 */
	public Object delete(Connection conGral,
			TVINVRecomendacion vINVRecomendacion) throws DAOException {
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "delete from INVRecomendacion"
					+ " where iCveRecomendacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRecomendacion.getICveRecomendacion());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("delete.closeINVRecomendacion", ex2);
			}
		}
		return cClave;
	}

	// Genera el Archivo JS de Recomendaciones ...
	public boolean genJS() {
		DbConnection dbConn = null;
		Connection conn = null;
		boolean lSuccess = false;
		TGeneradorJS jsDiag = new TGeneradorJS();
		PreparedStatement pstmt1 = null, pstmt2 = null;
		ResultSet rset1 = null, rset2 = null;
		String cSQL = "";
		int i = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			StringBuffer sbJS = new StringBuffer();
			sbJS.append("var aINVRecID = new Array(); ");
			cSQL = "select "
					+ "iCveRecomendacion,cDscRecomendacion,cCveInterna "
					+ "from INVRecomendacion where lActivo = 1 order by cCveInterna";
			pstmt1 = conn.prepareStatement(cSQL);
			rset1 = pstmt1.executeQuery();
			i = 0;
			while (rset1.next()) {
				sbJS.append("aINVRecID[" + i + "]=['"
						+ rset1.getInt("iCveRecomendacion") + "','"
						+ rset1.getString("cDscRecomendacion") + "','"
						+ rset1.getString("cCveInterna") + "']; ");
				i++;
			}

			sbJS.append("var aINVRecDsc = new Array(); ");
			cSQL = "select "
					+ "iCveRecomendacion,cDscRecomendacion,cCveInterna "
					+ "from INVRecomendacion where lActivo = 1 order by cDscRecomendacion";
			pstmt2 = conn.prepareStatement(cSQL);
			rset2 = pstmt1.executeQuery();
			i = 0;
			while (rset2.next()) {
				sbJS.append("aINVRecDsc[" + i + "]=['"
						+ rset2.getInt("iCveRecomendacion") + "','"
						+ rset2.getString("cDscRecomendacion") + "','"
						+ rset2.getString("cCveInterna") + "']; ");
				i++;
			}

			lSuccess = jsDiag.createJS(
					VParametros.getPropEspecifica("RutaGenJS"), "invReco.js",
					sbJS, true);
		} catch (Exception e) {
			warn("genJS", e);
		} finally {
			try {
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (rset1 != null) {
					rset1.close();
				}
				if (rset2 != null) {
					rset2.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("genJS.close", ex2);
			}
			return lSuccess;
		}
	}

}