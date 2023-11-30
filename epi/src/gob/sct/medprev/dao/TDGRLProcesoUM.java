package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLProcesoUM DAO
 * </p>
 * <p>
 * Description: TD para GRLProcesoUM
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

public class TDGRLProcesoUM extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLProcesoUM() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLProcesoUM = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLProcesoUM vGRLProcesoUM;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveUniMed," + "iCveProceso,"
					+ "lInterconsulta" + " from GRLProcesoUM " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLProcesoUM = new TVGRLProcesoUM();
				vGRLProcesoUM.setICveUniMed(rset.getInt(1));
				vGRLProcesoUM.setICveProceso(rset.getInt(2));
				vGRLProcesoUM.setLInterconsulta(rset.getInt(3));
				vcGRLProcesoUM.addElement(vGRLProcesoUM);
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
			return vcGRLProcesoUM;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindDsc(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLProcesoUM = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLProcesoUM vGRLProcesoUM;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "GRLProcesoUM.iCveUniMed,"
					+ "GRLProcesoUM.iCveProceso,"
					+ "GRLProcesoUM.lInterconsulta,"
					+ "GRLUniMed.cDscUniMed,"
					+ "GRLProceso.cDscProceso"
					+ " from GRLProcesoUM "
					+ " join GRLUniMed on GRLUniMed.iCveUniMed = GRLProcesoUM.iCveUniMed "
					+ " join GRLProceso on GRLProceso.iCveProceso = GRLProcesoUM.iCveProceso "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLProcesoUM = new TVGRLProcesoUM();
				vGRLProcesoUM.setICveUniMed(rset.getInt(1));
				vGRLProcesoUM.setICveProceso(rset.getInt(2));
				vGRLProcesoUM.setLInterconsulta(rset.getInt(3));
				vGRLProcesoUM.setCDscUniMed(rset.getString(4));
				vGRLProcesoUM.setCDscProceso(rset.getString(5));
				vcGRLProcesoUM.addElement(vGRLProcesoUM);
			}
		} catch (Exception ex) {
			warn("FindDsc", ex);
			throw new DAOException("FindDsc");
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
				warn("FindDsc.close", ex2);
			}
			return vcGRLProcesoUM;
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
			TVGRLProcesoUM vGRLProcesoUM = (TVGRLProcesoUM) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLProcesoUM(" + "iCveUniMed," + "iCveProceso,"
					+ "lInterconsulta" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vGRLProcesoUM.getICveUniMed());
			pstmt.setInt(2, vGRLProcesoUM.getICveProceso());
			pstmt.setInt(3, vGRLProcesoUM.getLInterconsulta());
			pstmt.executeUpdate();
			cClave = "" + vGRLProcesoUM.getICveUniMed();
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
			TVGRLProcesoUM vGRLProcesoUM = (TVGRLProcesoUM) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLProcesoUM" + " set lInterconsulta= ?, "
					+ "where iCveUniMed = ? " + " and iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLProcesoUM.getLInterconsulta());
			pstmt.setInt(2, vGRLProcesoUM.getICveUniMed());
			pstmt.setInt(3, vGRLProcesoUM.getICveProceso());
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
			TVGRLProcesoUM vGRLProcesoUM = (TVGRLProcesoUM) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLProcesoUM" + " where iCveUniMed = ?"
					+ " and iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLProcesoUM.getICveUniMed());
			pstmt.setInt(2, vGRLProcesoUM.getICveProceso());
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
				warn("delete.closeGRLProcesoUM", ex2);
			}
			return cClave;
		}
	}
}