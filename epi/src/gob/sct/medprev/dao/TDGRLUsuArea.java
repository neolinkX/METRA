package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXArea DAO
 * </p>
 * <p>
 * Description: DAP de la Tabla TOXArea
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

public class TDGRLUsuArea extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLUsuArea() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcUsuarios = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVGRLUsuArea vGRLUsuArea;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select GRLUsuArea.iCveUnimed, ")
					.append("        GRLUsuArea.iCveModulo, ")
					.append("        GRLUsuArea.iCveArea,   ")
					.append("        GRLUsuArea.iCveUsuario, ")
					.append("        cDscArea, ")
					.append("        cNombre,  ")
					.append("        cApPaterno,  ")
					.append("        cApMaterno  ")
					.append("        from GRLUsuArea  ")
					.append(" inner join GRLArea on GRLArea.iCveArea = GRLUsuArea.iCveArea ")
					.append(" inner join SEGUsuario on SEGUsuario.iCveUsuario = GRLUsuArea.iCveUsuario ")
					.append(" inner join GRLUsuMedicos UM on UM.iCveUsuario = SEGUsuario.iCveUsuario ")
					.append("                            and UM.iCveUniMed  =  GRLUsuArea.iCveUnimed ")
					.append("                            and UM.iCveModulo  =  GRLUsuArea.iCveModulo ")
					.append(cWhere);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUsuArea = new TVGRLUsuArea();
				vGRLUsuArea.setICveUniMed(rset.getInt(1));
				vGRLUsuArea.setICveModulo(rset.getInt(2));
				vGRLUsuArea.setICveArea(rset.getInt(3));
				vGRLUsuArea.setICveUsuario(rset.getInt(4));
				vGRLUsuArea.setCDscArea(rset.getString(5));
				vGRLUsuArea.setCNombreCompleto(rset.getString(6) + " "
						+ rset.getString(7) + " " + rset.getString(8));
				vcUsuarios.addElement(vGRLUsuArea);
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
			return vcUsuarios;
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
			TVTOXArea vTOXArea = (TVTOXArea) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXArea(" + "iCveArea," + "cDscArea,"
					+ "lActivo" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vTOXArea.getICveArea());
			pstmt.setString(2, vTOXArea.getCDscArea());
			pstmt.setInt(3, vTOXArea.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vTOXArea.getICveArea();
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
			TVTOXArea vTOXArea = (TVTOXArea) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXArea" + " set cDscArea= ?, " + "lActivo= ? "
					+ "where iCveArea = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vTOXArea.getCDscArea());
			pstmt.setInt(2, vTOXArea.getLActivo());
			pstmt.setInt(3, vTOXArea.getICveArea());
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
			TVTOXArea vTOXArea = (TVTOXArea) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXArea" + " where iCveArea = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXArea.getICveArea());
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
				warn("delete.closeTOXArea", ex2);
			}
			return cClave;
		}
	}
}
