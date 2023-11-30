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
 * Title: Data Acces Object de GRLReqxMotivo DAO
 * </p>
 * <p>
 * Description: DAO GRLReqxMotivo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */

public class TDGRLReqxMotivo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLReqxMotivo() {
	}

	/**
	 * Metodo Find By All con filtro y orden
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLReqxMotivo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLReqxMotivo vGRLReqxMotivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMotivo," + "iCveRequisito,"
					+ "lObligatorio," + "iCopias" + " from GRLReqxMotivo "
					+ cWhere + cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLReqxMotivo = new TVGRLReqxMotivo();
				vGRLReqxMotivo.setICveMotivo(rset.getInt(1));
				vGRLReqxMotivo.setICveRequisito(rset.getInt(2));
				vGRLReqxMotivo.setLObligatorio(rset.getInt(3));
				vGRLReqxMotivo.setICopias(rset.getInt(4));
				vcGRLReqxMotivo.addElement(vGRLReqxMotivo);
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
			return vcGRLReqxMotivo;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllWithReq(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLReqxMotivo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLReqxMotivo vGRLReqxMotivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select a.iCveRequisito, a.lObligatorio, "
					+ "a.iCopias, cDscReqBreve, cDscRequisito  "
					+ " From GRLReqxMotivo a                   "
					+ " left join  GRLRequisito on GRLRequisito.iCveRequisito = a.iCveRequisito "
					+ cWhere;
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLReqxMotivo = new TVGRLReqxMotivo();
				vGRLReqxMotivo.setICveRequisito(rset.getInt(1));
				vGRLReqxMotivo.setLObligatorio(rset.getInt(2));
				vGRLReqxMotivo.setICopias(rset.getInt(3));
				vGRLReqxMotivo.setCDscReqBreve(rset.getString(4));
				vGRLReqxMotivo.setCDscRequisito(rset.getString(5));
				vcGRLReqxMotivo.addElement(vGRLReqxMotivo);
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
			return vcGRLReqxMotivo;
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
			TVGRLReqxMotivo vGRLReqxMotivo = (TVGRLReqxMotivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLReqxMotivo(" + "iCveMotivo,"
					+ "iCveRequisito," + "lObligatorio," + "iCopias"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vGRLReqxMotivo.getICveMotivo());
			pstmt.setInt(2, vGRLReqxMotivo.getICveRequisito());
			pstmt.setInt(3, vGRLReqxMotivo.getLObligatorio());
			pstmt.setInt(4, vGRLReqxMotivo.getICopias());
			pstmt.executeUpdate();
			cClave = "" + vGRLReqxMotivo.getICveMotivo();
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
			TVGRLReqxMotivo vGRLReqxMotivo = (TVGRLReqxMotivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLReqxMotivo" + " set lObligatorio= ?, "
					+ "iCopias= ? " + "where iCveMotivo = ? "
					+ " and iCveRequisito = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLReqxMotivo.getLObligatorio());
			pstmt.setInt(2, vGRLReqxMotivo.getICopias());
			pstmt.setInt(3, vGRLReqxMotivo.getICveMotivo());
			pstmt.setInt(4, vGRLReqxMotivo.getICveRequisito());
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
			TVGRLReqxMotivo vGRLReqxMotivo = (TVGRLReqxMotivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLReqxMotivo" + " where iCveMotivo = ?"
					+ " and iCveRequisito = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLReqxMotivo.getICveMotivo());
			pstmt.setInt(2, vGRLReqxMotivo.getICveRequisito());
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
				warn("delete.closeGRLReqxMotivo", ex2);
			}
			return cClave;
		}
	}
}