package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMFamilia DAO
 * </p>
 * <p>
 * Description: DAO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrej�n Adame
 * @version 1.0
 */

public class TDALMFamilia extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMFamilia() {
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMFamilia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMFamilia vALMFamilia;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select distinct " + " ALMFamilia.iCveTpoArticulo,  "
					+ " ALMFamilia.iCveFamilia,  "
					+ " ALMFamilia.cDscFamilia,   " + " ALMFamilia.cDscBreve, "
					+ " ALMFamilia.lActivo  " + " from ALMFamilia " + cWhere
					+ " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMFamilia = new TVALMFamilia();
				vALMFamilia.setiCveTpoArticulo(rset.getInt(1));
				vALMFamilia.setiCveFamilia(rset.getInt(2));
				vALMFamilia.setcDscFamilia(rset.getString(3));
				vALMFamilia.setcDscBreve(rset.getString(4));
				vALMFamilia.setlActivo(rset.getInt(5));
				vcALMFamilia.addElement(vALMFamilia);
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
			return vcALMFamilia;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMFamilia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMFamilia vALMFamilia;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "F.iCveTpoArticulo,"
					+ "F.iCveFamilia,"
					+ "F.cDscFamilia,"
					+ "F.cDscBreve,"
					+ "F.lActivo,"
					+ "A.cDscBreve cDscArticulo"
					+ " from ALMFamilia F "
					+ " left join ALMTpoArticulo A on ( F.iCveTpoArticulo = A.iCveTpoArticulo )"
					+ cFiltro + cOrden;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMFamilia = new TVALMFamilia();
				vALMFamilia.setiCveTpoArticulo(rset.getInt(1));
				vALMFamilia.setiCveFamilia(rset.getInt(2));
				vALMFamilia.setcDscFamilia(rset.getString(3));
				vALMFamilia.setcDscBreve(rset.getString(4));
				vALMFamilia.setlActivo(rset.getInt(5));
				vALMFamilia.setCDscArticulo(rset.getString(6));

				vcALMFamilia.addElement(vALMFamilia);
			}
		}

		catch (Exception ex) {
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
			return vcALMFamilia;
		}
	}

	/*****/

	/*
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		int iCve = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVALMFamilia vALMFamilia = (TVALMFamilia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "";
			cSQL = "Select Max(iCveFamilia) from ALMFamilia "
					+ "where iCveTpoArticulo = "
					+ vALMFamilia.getiCveTpoArticulo();

			pstmt2 = conn.prepareStatement(cSQL);
			rset = pstmt2.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1) + 1;
			}
			rset.close();
			pstmt2.close();

			vALMFamilia.setiCveFamilia(iCve);

			cSQL = "insert into ALMFamilia(" + "iCveTpoArticulo,"
					+ "iCveFamilia," + "cDscFamilia," + "cDscBreve,"
					+ "lActivo" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vALMFamilia.getiCveTpoArticulo());
			pstmt.setInt(2, vALMFamilia.getiCveFamilia());
			pstmt.setString(3, vALMFamilia.getcDscFamilia().toUpperCase()
					.trim());
			pstmt.setString(4, vALMFamilia.getcDscBreve().toUpperCase().trim());
			pstmt.setInt(5, vALMFamilia.getlActivo());
			pstmt.executeUpdate();

			cClave = "" + vALMFamilia.getiCveFamilia();

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
			TVALMFamilia vALMFamilia = (TVALMFamilia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMFamilia" + " set cDscFamilia= ?, "
					+ "cDscBreve= ?, " + "lActivo= ? "
					+ "where iCveTpoArticulo = ? " + " and iCveFamilia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vALMFamilia.getcDscFamilia().toUpperCase()
					.trim());
			pstmt.setString(2, vALMFamilia.getcDscBreve().toUpperCase().trim());
			pstmt.setInt(3, vALMFamilia.getlActivo());
			pstmt.setInt(4, vALMFamilia.getiCveTpoArticulo());
			pstmt.setInt(5, vALMFamilia.getiCveFamilia());
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
			TVALMFamilia vALMFamilia = (TVALMFamilia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMFamilia" + " where iCveTpoArticulo = ?"
					+ " and iCveFamilia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMFamilia.getiCveTpoArticulo());
			pstmt.setInt(2, vALMFamilia.getiCveFamilia());
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
				warn("delete.closeALMFamilia", ex2);
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
			TVALMFamilia vALMFamilia = (TVALMFamilia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMFamilia" + " set lActivo=0 "
					+ "where iCveTpoArticulo = ?" + " and iCveFamilia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMFamilia.getiCveTpoArticulo());
			pstmt.setInt(2, vALMFamilia.getiCveFamilia());

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
