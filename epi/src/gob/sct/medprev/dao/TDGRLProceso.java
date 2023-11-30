package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLProceso DAO
 * </p>
 * <p>
 * Description: Data Acces Object para GRLProceso
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

public class TDGRLProceso extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLProceso() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLProceso = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLProceso vGRLProceso;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "iCveProceso,cDscProceso,iRefNum,lActivo "
					+ "from GRLProceso";
			String cCveProceso = (String) hmFiltros.get("iCveProceso");
			String cActivo = (String) hmFiltros.get("lActivo");
			if (cCveProceso != null) {
				cSQL += " where iCveProceso=?";
				cWhereAnd = " and";
			}
			if (cActivo != null) {
				cSQL += cWhereAnd + " lActivo=?";
			}
			cSQL += " order by iCveProceso";
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveProceso != null)
				pstmt.setInt(i++, Integer.parseInt(cCveProceso));
			if (cActivo != null)
				pstmt.setInt(i++, Integer.parseInt(cActivo));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLProceso = new TVGRLProceso();
				vGRLProceso.setICveProceso(rset.getInt("iCveProceso"));
				vGRLProceso.setCDscProceso(rset.getString("cDscProceso"));
				vGRLProceso.setIRefNum(rset.getInt("iRefNum"));
				vGRLProceso.setLActivo(rset.getInt("lActivo"));
				vcGRLProceso.addElement(vGRLProceso);
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
		return vcGRLProceso;
	}

	/**
	 * Metodo Find By All con Filtro y Orden
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLProceso = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLProceso vGRLProceso;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveProceso," + "cDscProceso," + "iRefNum,"
					+ "lActivo," + "iRefAdicional" + " from GRLProceso "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLProceso = new TVGRLProceso();
				vGRLProceso.setICveProceso(rset.getInt(1));
				vGRLProceso.setCDscProceso(rset.getString(2));
				vGRLProceso.setIRefNum(rset.getInt(3));
				vGRLProceso.setLActivo(rset.getInt(4));
				vGRLProceso.setIRefAdicional(rset.getInt(5));
				vcGRLProceso.addElement(vGRLProceso);
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
			return vcGRLProceso;
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
			TVGRLProceso vGRLProceso = (TVGRLProceso) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLProceso(" + "iCveProceso," + "cDscProceso,"
					+ "iRefNum," + "iRefAdicional," + "lActivo"
					+ ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveProceso) from GRLProceso";
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
			vGRLProceso.setICveProceso(iMax);
			// ******************************************************************
			pstmt.setInt(1, vGRLProceso.getICveProceso());
			pstmt.setString(2, vGRLProceso.getCDscProceso());
			pstmt.setInt(3, vGRLProceso.getIRefNum());
			pstmt.setInt(4, vGRLProceso.getIRefAdicional());
			pstmt.setInt(5, vGRLProceso.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vGRLProceso.getICveProceso();
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
			TVGRLProceso vGRLProceso = (TVGRLProceso) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLProceso" + " set cDscProceso= ?, "
					+ "iRefNum= ?, " + "lActivo= ? " + "where iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vGRLProceso.getCDscProceso());
			pstmt.setInt(2, vGRLProceso.getIRefNum());
			pstmt.setInt(3, vGRLProceso.getLActivo());
			pstmt.setInt(4, vGRLProceso.getICveProceso());
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
			TVGRLProceso vGRLProceso = (TVGRLProceso) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLProceso" + " where iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLProceso.getICveProceso());
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
				warn("delete.closeGRLProceso", ex2);
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
			TVGRLProceso vGRLProceso = (TVGRLProceso) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLProceso" + " set lActivo= ? "
					+ " where iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vGRLProceso.getICveProceso());
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
