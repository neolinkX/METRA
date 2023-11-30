package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMArea DAO
 * </p>
 * <p>
 * Description: DAO Tabla ALMArea
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

public class TDALMArea extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMArea() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArea = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArea vALMArea;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveArea, "
					+ "cDscArea, "
					+ "ALMArea.cDscBreve, "
					+ "ALMArea.iCveTpoArticulo, "
					+ "cObservacion, "
					+ "ALMArea.lActivo, "
					+ "ALMArea.iCveAlmacen, "
					+ "ALMAlmacen.cDscAlmacen, "
					+ "ALMTpoArticulo.cDscTpoArticulo, "
					+ "ALMTpoArticulo.cDscBreve "
					+ "from  "
					+ "ALMArea "
					+ "join ALMAlmacen on ALMArea.iCveAlmacen = ALMAlmacen.iCveAlmacen "
					+ "join ALMTpoArticulo on ALMArea.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArea = new TVALMArea();
				vALMArea.setICveArea(rset.getInt(1));
				vALMArea.setCDscArea(rset.getString(2));
				vALMArea.setCDscBreve(rset.getString(3));
				vALMArea.setICveTpoArticulo(rset.getInt(4));
				vALMArea.setCObservacion(rset.getString(5));
				vALMArea.setLActivo(rset.getInt(6));
				vALMArea.setICveAlmacen(rset.getInt(7));
				vALMArea.setCDscAlmacen(rset.getString(8));
				vALMArea.setCDscTpoArticulo(rset.getString(9));
				vALMArea.setCDscBreveTpoArticulo(rset.getString(10));

				vcALMArea.addElement(vALMArea);
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
			return vcALMArea;
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
			TVALMArea vALMArea = (TVALMArea) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into ALMArea(" + "iCveAlmacen," + "iCveArea,"
					+ "cDscArea," + "cDscBreve," + "iCveTpoArticulo,"
					+ "cObservacion," + "lActivo" + ")values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveArea) from ALMArea";
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
			vALMArea.setICveArea(iMax);
			// ******************************************************************
			pstmt.setInt(1, vALMArea.getICveAlmacen());
			pstmt.setInt(2, vALMArea.getICveArea());
			pstmt.setString(3, vALMArea.getCDscArea().toUpperCase().trim());
			pstmt.setString(4, vALMArea.getCDscBreve().toUpperCase().trim());
			pstmt.setInt(5, vALMArea.getICveTpoArticulo());
			pstmt.setString(6, vALMArea.getCObservacion().toUpperCase().trim());
			pstmt.setInt(7, vALMArea.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vALMArea.getICveArea();
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
			TVALMArea vALMArea = (TVALMArea) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMArea" + " set cDscArea= ?, " + "cDscBreve= ?, "
					+ "iCveTpoArticulo= ?, " + "cObservacion= ?, "
					+ "lActivo= ? " + "where iCveAlmacen = ? "
					+ " and iCveArea = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vALMArea.getCDscArea().toUpperCase().trim());
			pstmt.setString(2, vALMArea.getCDscBreve().toUpperCase().trim());
			pstmt.setInt(3, vALMArea.getICveTpoArticulo());
			pstmt.setString(4, vALMArea.getCObservacion().toUpperCase().trim());
			pstmt.setInt(5, vALMArea.getLActivo());
			pstmt.setInt(6, vALMArea.getICveAlmacen());
			pstmt.setInt(7, vALMArea.getICveArea());

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
			TVALMArea vALMArea = (TVALMArea) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMArea" + " where iCveAlmacen = ?"
					+ " and iCveArea = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMArea.getICveAlmacen());
			pstmt.setInt(2, vALMArea.getICveArea());
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
				warn("delete.closeALMArea", ex2);
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
			TVALMArea vALMArea = (TVALMArea) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMArea" + " set lActivo= ? "
					+ "where iCveAlmacen = ?" + " and iCveArea = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vALMArea.getICveAlmacen());
			pstmt.setInt(3, vALMArea.getICveArea());

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