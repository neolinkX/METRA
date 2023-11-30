package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de PERFoto DAO
 * </p>
 * <p>
 * Description: DAO de la entidad PERFoto
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Su�rez Romero
 * @version 1.0
 */

public class TDPERFoto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERFoto() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERFoto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERFoto vPERFoto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "iCveFoto," + "dtCapturada"
					+ " from PERFoto " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERFoto = new TVPERFoto();
				vPERFoto.setICvePersonal(rset.getInt(1));
				vPERFoto.setICveFoto(rset.getInt(2));
				vPERFoto.setDtCapturada(rset.getDate(3));
				vcPERFoto.addElement(vPERFoto);
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
			return vcPERFoto;
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
			TVPERFoto vPERFoto = (TVPERFoto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into PERFoto(" + "iCvePersonal," + "iCveFoto,"
					+ "dtCapturada" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vPERFoto.getICvePersonal());
			pstmt.setInt(2, vPERFoto.getICveFoto());
			pstmt.setDate(3, vPERFoto.getDtCapturada());
			pstmt.executeUpdate();
			cClave = "" + vPERFoto.getICvePersonal();
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
			TVPERFoto vPERFoto = (TVPERFoto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERFoto" + " set dtCapturada= ?, "
					+ "where iCvePersonal = ? " + " and iCveFoto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vPERFoto.getDtCapturada());
			pstmt.setInt(2, vPERFoto.getICvePersonal());
			pstmt.setInt(3, vPERFoto.getICveFoto());
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
			TVPERFoto vPERFoto = (TVPERFoto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERFoto" + " where iCvePersonal = ?"
					+ " and iCveFoto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERFoto.getICvePersonal());
			pstmt.setInt(2, vPERFoto.getICveFoto());
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
				warn("delete.closePERFoto", ex2);
			}
			return cClave;
		}
	}

	public void saveFile(byte[] file, int iCvePersonal) throws DAOException {
		TVPERFoto vPERFoto = new TVPERFoto();
		PreparedStatement pstmt = null, pstmtdel = null;
		ResultSet rset = null;
		dbConn = null;
		conn = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String instruccion = "SELECT MAX(ICVEFOTO) FROM PERFOTO "
					+ "WHERE ICVEPERSONAL = ? ";

			pstmtdel = conn.prepareStatement(instruccion);
			pstmtdel.setInt(1, iCvePersonal);
			rset = pstmtdel.executeQuery();

			int iCveFoto = 0;
			while (rset.next()) {
				iCveFoto = rset.getInt(1) + 1;
			}
			StringBuffer query = new StringBuffer("INSERT INTO PERFOTO ");
			query.append("(ICVEPERSONAL,ICVEFOTO,DTCAPTURADA,BFOTOGRAFIA)");
			query.append("VALUES(?,?,?,?)");
			instruccion = query.toString();
			pstmt = conn.prepareStatement(instruccion);
			pstmt.setInt(1, iCvePersonal);
			pstmt.setInt(2, iCveFoto);
			pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setBytes(4, file);
			pstmt.execute();
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception e) {
				warn("rollback saveFile", e);
			}
			warn("saveFile", ex);
			throw new DAOException("saveFile");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtdel != null) {
					pstmtdel.close();
				}

				if (conn != null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception e) {
				warn("Close saveFile", e);
			}
		}
	}

	public byte[] getFile(int iCvePersona, int iCveFoto) throws DAOException {

		dbConn = null;
		conn = null;
		Statement stmt = null;
		ResultSet file = null;
		byte[] archivo = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			StringBuffer query = new StringBuffer(
					"SELECT BFOTOGRAFIA FROM PERFOTO ");
			query.append(" WHERE ICVEPERSONAL=" + iCvePersona);
			query.append("   AND ICVEFOTO=" + iCveFoto);

			String instruccion = query.toString();

			stmt = conn.createStatement();
			file = stmt.executeQuery(instruccion);
			if (file.next()) {
				archivo = file.getBytes(1);
			}
		} catch (Exception e) {
			warn("getFile", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (file != null) {
					file.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception e) {
				warn("Close getFile", e);
			}
			return archivo;
		}
	}
}