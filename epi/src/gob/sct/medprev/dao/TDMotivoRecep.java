package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de MotivoRecep DAO
 * </p>
 * <p>
 * Description: DAO para la tabla TOXMotivoRecep
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

public class TDMotivoRecep extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMotivoRecep() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMotivoRecep = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMotivoRecep vMotivoRecep;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select iCveMotRecep, TOXTipoRecep.iCveTipoRecep, TOXTipoRecep.cDscTipoRecep,cDscMotRecep,cDscLong "
					+ "from TOXMotivoRecep "
					+ " left join TOXTipoRecep on TOXTipoRecep.iCveTipoRecep = TOXMotivoRecep.iCveTipoRecep"
					+ cFiltro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMotivoRecep = new TVMotivoRecep();
				vMotivoRecep.setICveMotRecep(rset.getInt(1));
				vMotivoRecep.setICveTipoRecep(rset.getInt(2));
				vMotivoRecep.setCDscTipoRecep(rset.getString(3));
				vMotivoRecep.setCDscMotRecep(rset.getString(4));
				vMotivoRecep.setCDscLong(rset.getString(5));
				vcMotivoRecep.addElement(vMotivoRecep);
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
			return vcMotivoRecep;
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
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVMotivoRecep vMotivoRecep = (TVMotivoRecep) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXMotivoRecep(" + "iCveMotRecep,"
					+ "iCveTipoRecep," + "cDscMotRecep," + "cDscLong"
					+ ")values(?,?,?,?)";
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveMotRecep) from TOXMotivoRecep";
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
			vMotivoRecep.setICveMotRecep(iMax);
			// *****************************************************************
			pstmt.setInt(1, vMotivoRecep.getICveMotRecep());
			pstmt.setInt(2, vMotivoRecep.getICveTipoRecep());
			pstmt.setString(3, vMotivoRecep.getCDscMotRecep());
			pstmt.setString(4, vMotivoRecep.getCDscLong());
			pstmt.executeUpdate();
			cClave = "" + vMotivoRecep.getICveMotRecep();
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
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
			TVMotivoRecep vMotivoRecep = (TVMotivoRecep) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "update TOXMotivoRecep" + " set iCveTipoRecep= ?, "
					+ "cDscMotRecep= ?,cDscLong = ? "
					+ "where iCveMotRecep = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMotivoRecep.getICveTipoRecep());
			pstmt.setString(2, vMotivoRecep.getCDscMotRecep());
			pstmt.setString(3, vMotivoRecep.getCDscLong());
			pstmt.setInt(4, vMotivoRecep.getICveMotRecep());
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
			TVMotivoRecep vMotivoRecep = (TVMotivoRecep) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXMotivoRecep" + " where iCveMotRecep = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMotivoRecep.getICveMotRecep());
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
				warn("delete.closeMotivoRecep", ex2);
			}
			return cClave;
		}
	}
}
