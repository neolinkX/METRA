package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMAlmacen DAO
 * </p>
 * <p>
 * Description: DAO Tabla ALMAlmacen
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

public class TDALMAlmacen extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMAlmacen() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMAlmacen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMAlmacen vALMAlmacen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveAlmacen, "
					+ "cDscAlmacen, "
					+ "lActivo, "
					+ "iCveUsuResp, "
					+ "ALMAlmacen.iCveUniMed, "
					+ "GRLUniMed.cDscUniMed, "
					+ "SEGUsuario.iCveUsuario, "
					+ "SEGUsuario.cNombre, "
					+ "SEGUsuario.cApPaterno, "
					+ "SEGUsuario.cApMaterno "
					+ "from "
					+ "        ALMAlmacen "
					+ "join	GRLUniMed on ALMAlmacen.iCveUniMed = GRLUniMed.iCveUniMed "
					+ "join	SEGUsuario on ALMAlmacen.iCveUsuResp = SEGUsuario.iCveUsuario "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMAlmacen = new TVALMAlmacen();

				vALMAlmacen.setICveAlmacen(rset.getInt(1));
				vALMAlmacen.setCDscAlmacen(rset.getString(2));
				vALMAlmacen.setLActivo(rset.getInt(3));
				vALMAlmacen.setICveUsuResp(rset.getInt(4));
				vALMAlmacen.setICveUniMed(rset.getInt(5));
				vALMAlmacen.setCDscUniMed(rset.getString(6));
				vALMAlmacen.setICveUsuario(rset.getInt(7));
				vALMAlmacen.setCNombre(rset.getString(8));
				vALMAlmacen.setCApPaterno(rset.getString(9));
				vALMAlmacen.setCApMaterno(rset.getString(10));

				vcALMAlmacen.addElement(vALMAlmacen);
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
			return vcALMAlmacen;
		}
	}

	/**
	 * Metodo FindDsc
	 */
	public String FindDsc(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cDsc = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "cDscAlmacen " + "from ALMAlmacen " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				cDsc = rset.getString(1);
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
			return cDsc;
		}
	}

	public Vector FindByCustomWhere(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMAlmacen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMAlmacen vALMAlmacen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct " + " ALMAlmacen.iCveAlmacen,"
					+ " ALMAlmacen.iCveUniMed," + " ALMAlmacen.cDscAlmacen,"
					+ " ALMAlmacen.iCveUsuResp," + " ALMAlmacen.lActivo"
					+ " from ALMAlmacen " + cFiltro;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMAlmacen = new TVALMAlmacen();
				vALMAlmacen.setICveAlmacen(rset.getInt(1));
				vALMAlmacen.setICveUniMed(rset.getInt(2));
				vALMAlmacen.setCDscAlmacen(rset.getString(3));
				vALMAlmacen.setICveUsuResp(rset.getInt(4));
				vALMAlmacen.setLActivo(rset.getInt(5));
				vcALMAlmacen.addElement(vALMAlmacen);
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
			return vcALMAlmacen;
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
		int iMax = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVALMAlmacen vALMAlmacen = (TVALMAlmacen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into ALMAlmacen(" + "iCveAlmacen," + "iCveUniMed,"
					+ "cDscAlmacen," + "iCveUsuResp," + "lActivo"
					+ ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveAlmacen) from ALMAlmacen";
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
			vALMAlmacen.setICveAlmacen(iMax);
			// ******************************************************************
			pstmt.setInt(1, vALMAlmacen.getICveAlmacen());
			pstmt.setInt(2, vALMAlmacen.getICveUniMed());
			pstmt.setString(3, vALMAlmacen.getCDscAlmacen().toUpperCase()
					.trim());
			pstmt.setInt(4, vALMAlmacen.getICveUsuResp());
			pstmt.setInt(5, vALMAlmacen.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vALMAlmacen.getICveAlmacen();
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
			TVALMAlmacen vALMAlmacen = (TVALMAlmacen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMAlmacen" + " set iCveUniMed= ?, "
					+ "cDscAlmacen= ?, " + "iCveUsuResp= ?, " + "lActivo= ? "
					+ "where iCveAlmacen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMAlmacen.getICveUniMed());
			pstmt.setString(2, vALMAlmacen.getCDscAlmacen().toUpperCase()
					.trim());
			pstmt.setInt(3, vALMAlmacen.getICveUsuResp());
			pstmt.setInt(4, vALMAlmacen.getLActivo());
			pstmt.setInt(5, vALMAlmacen.getICveAlmacen());
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
			TVALMAlmacen vALMAlmacen = (TVALMAlmacen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMAlmacen" + " where iCveAlmacen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMAlmacen.getICveAlmacen());
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
				warn("delete.closeALMAlmacen", ex2);
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
			TVALMAlmacen vALMAlmacen = (TVALMAlmacen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMAlmacen" + " set lActivo= ? "
					+ "where iCveAlmacen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vALMAlmacen.getICveAlmacen());

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
