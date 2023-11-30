package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de PERLicencia DAO 
 * </p>
 * <p>
 * Description: DAO Tabal PerTpoLicencia
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 */

public class TDPERLicencia extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERLicencia() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERLicencia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERLicencia vPERLicencia;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "iCveMdoTrans,"
					+ "iCveCategoria," + "cLicencia," + "lDictamen"
					+ " from PERLicencia " + cWhere + " order by iCvePersonal ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERLicencia = new TVPERLicencia();
				vPERLicencia.setICvePersonal(rset.getInt(1));
				vPERLicencia.setICveMdoTrans(rset.getInt(2));
				vPERLicencia.setICveCategoria(rset.getInt(3));
				vPERLicencia.setCLicencia(rset.getString(4));
				vPERLicencia.setLDictamen(rset.getInt(5));
				vcPERLicencia.addElement(vPERLicencia);
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
			return vcPERLicencia;
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
			TVPERLicencia vPERLicencia = (TVPERLicencia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into PERLicencia(" + "iCvePersonal,"
					+ "iCveMdoTrans," + "iCveCategoria," + "cLicencia,"
					+ "lDictamen," + "cConstancia," + "iNumExamen,"
					+ "dtIniVigencia," + "dtFinVigencia "
					+ ")values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vPERLicencia.getICvePersonal());
			pstmt.setInt(2, vPERLicencia.getICveMdoTrans());
			pstmt.setInt(3, vPERLicencia.getICveCategoria());
			pstmt.setString(4, vPERLicencia.getCLicencia());
			pstmt.setInt(5, vPERLicencia.getLDictamen());
			pstmt.setString(6, vPERLicencia.getCConstancia());
			pstmt.setInt(7, vPERLicencia.getINumExamen());
			pstmt.setDate(8, vPERLicencia.getDtIniVigencia());
			pstmt.setDate(9, vPERLicencia.getDtFinVigencia());

			pstmt.executeUpdate();
			cClave = "" + vPERLicencia.getICvePersonal();
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
			TVPERTpoLicencia vPERTpoLicencia = (TVPERTpoLicencia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERTpoLicencia" + " set cLicencia= ?, "
					+ "lDictamen= ? " + "where iCvePersonal = ? "
					+ "and iCveMdoTrans = ?" + " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vPERTpoLicencia.getCLicencia());
			pstmt.setInt(2, vPERTpoLicencia.getLDictamen());
			pstmt.setInt(3, vPERTpoLicencia.getICvePersonal());
			pstmt.setInt(4, vPERTpoLicencia.getICveMdoTrans());
			pstmt.setInt(5, vPERTpoLicencia.getICveCategoria());
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
			TVPERLicencia vPERLicencia = (TVPERLicencia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERLicencia" + " where iCvePersonal = ?"
					+ " and iCveMdoTrans = ?" + " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERLicencia.getICvePersonal());
			pstmt.setInt(2, vPERLicencia.getICveMdoTrans());
			pstmt.setInt(3, vPERLicencia.getICveCategoria());
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
				warn("delete.closePERLicencia", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Delete
	 */
	public Object deletePERLicencia(Connection conGral, Object obj)
			throws DAOException {
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
			TVPERLicencia vPERLicencia = (TVPERLicencia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERLicencia" + " where iCvePersonal = ?"
					+ " and iCveMdoTrans = ?" + " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERLicencia.getICvePersonal());
			pstmt.setInt(2, vPERLicencia.getICveMdoTrans());
			pstmt.setInt(3, vPERLicencia.getICveCategoria());
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
				warn("delete.closePERLicencia", ex2);
			}
			return cClave;
		}
	}

}