package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de PERFirma DAO
 * </p>
 * <p>
 * Description: DAO de la Entidad PERFirma
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

public class TDPERFirma extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERFirma() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERFirma = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERFirma vPERFirma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "dtCapturada"
					+ " from PERFirma " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERFirma = new TVPERFirma();
				vPERFirma.setICvePersonal(rset.getInt(1));
				vPERFirma.setDtCapturada(rset.getDate(2));
				vcPERFirma.addElement(vPERFirma);
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
			return vcPERFirma;
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
			TVPERFirma vPERFirma = (TVPERFirma) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERFirma" + " where iCvePersonal = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERFirma.getICvePersonal());
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
				warn("delete.closePERFirma", ex2);
			}
			return cClave;
		}
	}

	public void saveFile(byte[] file, int iCvePersonal) throws DAOException {
		TVPERFoto vPERFoto = new TVPERFoto();
		PreparedStatement pstmt = null;
		dbConn = null;
		conn = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			StringBuffer query = new StringBuffer("INSERT INTO PERFirma ");
			query.append("(ICVEPERSONAL,BFIRMA,DTCAPTURADA)");
			query.append("VALUES(?,?,?)");
			String instruccion = query.toString();
			pstmt = conn.prepareStatement(instruccion);
			pstmt.setInt(1, iCvePersonal);
			pstmt.setBytes(2, file);
			pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
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

	public byte[] getFile(int iCvePersona) throws DAOException {

		dbConn = null;
		conn = null;
		Statement stmt = null;
		ResultSet file = null;
		byte[] archivo = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			StringBuffer query = new StringBuffer(
					"SELECT BFIRMA FROM PERFIRMA ");
			query.append(" WHERE ICVEPERSONAL=" + iCvePersona);

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
