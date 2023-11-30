package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de VEHTpoMantto DAO
 * </p>
 * <p>
 * Description: Control de Veh�culos - Tipos de Mantenimiento
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

public class TDVEHTpoMantto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDVEHTpoMantto() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHTpoMantto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHTpoMantto vVEHTpoMantto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveTpoMantto," + "cDscTpoMantto,"
					+ "cDscBreve," + "lActivo"
					+ " from VEHTpoMantto order by iCveTpoMantto";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHTpoMantto = new TVVEHTpoMantto();
				vVEHTpoMantto.setICveTpoMantto(rset.getInt(1));
				vVEHTpoMantto.setCDscTpoMantto(rset.getString(2));
				vVEHTpoMantto.setCDscBreve(rset.getString(3));
				vVEHTpoMantto.setLActivo(rset.getInt(4));
				vcVEHTpoMantto.addElement(vVEHTpoMantto);
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
			return vcVEHTpoMantto;
		}
	}

	/**
	 * Metodo Find By All con Filtro y Orden
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHTpoMantto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHTpoMantto vVEHTpoMantto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveTpoMantto," + "cDscTpoMantto,"
					+ "cDscBreve," + "lActivo" + " from VEHTpoMantto " + cWhere
					+ cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHTpoMantto = new TVVEHTpoMantto();
				vVEHTpoMantto.setICveTpoMantto(rset.getInt(1));
				vVEHTpoMantto.setCDscTpoMantto(rset.getString(2));
				vVEHTpoMantto.setCDscBreve(rset.getString(3));
				vVEHTpoMantto.setLActivo(rset.getInt(4));
				vcVEHTpoMantto.addElement(vVEHTpoMantto);
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
			return vcVEHTpoMantto;
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
			TVVEHTpoMantto vVEHTpoMantto = (TVVEHTpoMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into VEHTpoMantto(" + "iCveTpoMantto,"
					+ "cDscTpoMantto," + "cDscBreve," + "lActivo"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveTpoMantto) from VEHTpoMantto ";
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
			vVEHTpoMantto.setICveTpoMantto(iMax);
			// ******************************************************************
			pstmt.setInt(1, vVEHTpoMantto.getICveTpoMantto());
			pstmt.setString(2, vVEHTpoMantto.getCDscTpoMantto().toUpperCase()
					.trim());
			pstmt.setString(3, vVEHTpoMantto.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setInt(4, vVEHTpoMantto.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vVEHTpoMantto.getICveTpoMantto();
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
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
			TVVEHTpoMantto vVEHTpoMantto = (TVVEHTpoMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHTpoMantto" + " set cDscTpoMantto= ?, "
					+ "cDscBreve= ?, " + "lActivo= ? "
					+ "where iCveTpoMantto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vVEHTpoMantto.getCDscTpoMantto().toUpperCase()
					.trim());
			pstmt.setString(2, vVEHTpoMantto.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setInt(3, vVEHTpoMantto.getLActivo());
			pstmt.setInt(4, vVEHTpoMantto.getICveTpoMantto());
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
			TVVEHTpoMantto vVEHTpoMantto = (TVVEHTpoMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from VEHTpoMantto" + " where iCveTpoMantto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHTpoMantto.getICveTpoMantto());
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
				warn("delete.closeVEHTpoMantto", ex2);
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
			TVVEHTpoMantto vVEHTpoMantto = (TVVEHTpoMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHTpoMantto" + " set lActivo= ? "
					+ "where iCveTpoMantto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vVEHTpoMantto.getICveTpoMantto());
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
