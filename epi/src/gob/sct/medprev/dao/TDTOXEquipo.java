package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXEquipo DAO
 * </p>
 * <p>
 * Description: DAO para la tabla TOXEquipo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hern�ndez Garc�a
 * @version 1.0
 */

public class TDTOXEquipo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXEquipo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXEquipo vTOXEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "TOXEquipo.iCveEquipo, "
					+ "TOXEquipo.lCuantCual, "
					+ "TOXEquipo.iCarruseles, "
					+ "TOXEquipo.iPosiciones, "
					+ "EQMEquipo.cDscEquipo, "
					+ "EQMEquipo.cNumSerie, "
					+ "EQMEquipo.cModelo, "
					+ "EQMEquipo.cCveEquipo "
					+ "from 	TOXEquipo  "
					+ "join EQMEquipo on EQMEquipo.iCveEquipo = TOXEquipo.iCveEquipo "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXEquipo = new TVTOXEquipo();
				vTOXEquipo.setICveEquipo(rset.getInt(1));
				vTOXEquipo.setLCuantCual(rset.getInt(2));
				vTOXEquipo.setICarrucel(rset.getInt(3));
				vTOXEquipo.setIPosiciones(rset.getInt(4));
				vTOXEquipo.setCDscEquipo(rset.getString(5));
				vTOXEquipo.setCNumSerie(rset.getString(6));
				vTOXEquipo.setCModelo(rset.getString(7));
				vTOXEquipo.setCCveEquipo(rset.getString(8));

				vcTOXEquipo.addElement(vTOXEquipo);
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
			return vcTOXEquipo;
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
			TVTOXEquipo vTOXEquipo = (TVTOXEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXEquipo(" + "iCveEquipo," + "lCuantCual,"
					+ "iCarruseles," + "iPosiciones" + ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vTOXEquipo.getICveEquipo());
			pstmt.setInt(2, vTOXEquipo.getLCuantCual());
			pstmt.setInt(3, vTOXEquipo.getICarrucel());
			pstmt.setInt(4, vTOXEquipo.getIPosiciones());
			pstmt.executeUpdate();
			cClave = "" + vTOXEquipo.getICveEquipo();
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
			TVTOXEquipo vTOXEquipo = (TVTOXEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXEquipo" + " set lCuantCual= ?, "
					+ "iCarruseles= ?, " + "iPosiciones= ? "
					+ "where iCveEquipo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXEquipo.getLCuantCual());
			pstmt.setInt(2, vTOXEquipo.getICarrucel());
			pstmt.setInt(3, vTOXEquipo.getIPosiciones());
			pstmt.setInt(4, vTOXEquipo.getICveEquipo());
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
			TVTOXEquipo vTOXEquipo = (TVTOXEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXEquipo" + " where iCveEquipo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXEquipo.getICveEquipo());
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
				warn("delete.closeTOXEquipo", ex2);
			}
			return cClave;
		}
	}
}
