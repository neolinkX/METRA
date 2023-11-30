package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXSustancia DAO
 * </p>
 * <p>
 * Description: DAO de la entitad TOXSustancia
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

public class TDTOXSustancia extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXSustancia() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXSustancia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXSustancia vTOXSustancia;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ " iCveSustancia, cDscSustancia, cTitRepConf, cPrefLoteConf, lActivo,"
					+ " lAnalizada, iMuestrasLC, cSustUnifica, "
					+ " cAbrevEqAC, lPositiva, lValidaCtrol, lValConcentracion "
					+ "from TOXSustancia " + cWhere + cOrderBy;
			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(cSQL);
			while (rset.next()) {
				vTOXSustancia = new TVTOXSustancia();
				vTOXSustancia.setiCveSustancia(rset.getInt("iCveSustancia"));
				vTOXSustancia.setcDscSustancia(rset.getString("cDscSustancia"));
				vTOXSustancia.setcTitRepConf(rset.getString("cTitRepConf"));
				vTOXSustancia.setcPrefLoteConf(rset.getString("cPrefLoteConf"));
				vTOXSustancia.setlActivo(rset.getInt("lActivo"));
				vTOXSustancia.setlAnalizada(rset.getInt("lAnalizada"));
				vTOXSustancia.setIMuestrasLC(rset.getInt("iMuestrasLC"));
				vTOXSustancia.setCSustUnifica(rset.getString("cSustUnifica"));
				vTOXSustancia.setCAbrevEqAC(rset.getString("cAbrevEqAC"));
				vTOXSustancia.setLValidaCtrol(rset.getInt("lValidaCtrol"));
				vTOXSustancia.setLValConcentracion(rset
						.getInt("lValConcentracion"));
				vcTOXSustancia.addElement(vTOXSustancia);
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
			return vcTOXSustancia;
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
			TVTOXSustancia vTOXSustancia = (TVTOXSustancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXSustancia(" + "iCveSustancia,"
					+ "cDscSustancia," + "cTitRepConf," + "cPrefLoteConf,"
					+ "lActivo," + "lAnalizada" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vTOXSustancia.getiCveSustancia());
			pstmt.setString(2, vTOXSustancia.getcDscSustancia());
			pstmt.setString(3, vTOXSustancia.getcTitRepConf());
			pstmt.setString(4, vTOXSustancia.getcPrefLoteConf());
			pstmt.setInt(5, vTOXSustancia.getlActivo());
			pstmt.setInt(6, vTOXSustancia.getlAnalizada());
			pstmt.executeUpdate();
			cClave = "" + vTOXSustancia.getiCveSustancia();
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
			TVTOXSustancia vTOXSustancia = (TVTOXSustancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXSustancia" + " set cDscSustancia= ?, "
					+ "cTitRepConf= ?, " + "cPrefLoteConf= ?, "
					+ "lActivo= ?, " + "lAnalizada= ? "
					+ "where iCveSustancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vTOXSustancia.getcDscSustancia());
			pstmt.setString(2, vTOXSustancia.getcTitRepConf());
			pstmt.setString(3, vTOXSustancia.getcPrefLoteConf());
			pstmt.setInt(4, vTOXSustancia.getlActivo());
			pstmt.setInt(5, vTOXSustancia.getlAnalizada());
			pstmt.setInt(6, vTOXSustancia.getiCveSustancia());
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
			TVTOXSustancia vTOXSustancia = (TVTOXSustancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXSustancia" + " where iCveSustancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXSustancia.getiCveSustancia());
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
				warn("delete.closeTOXSustancia", ex2);
			}
			return cClave;
		}
	}
}
