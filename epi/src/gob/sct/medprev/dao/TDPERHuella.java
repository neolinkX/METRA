package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de PERHuella DAO
 * </p>
 * <p>
 * Description: DAO de la entidad PERHuella
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

public class TDPERHuella extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERHuella() {
	}

	/**
	 * Metodo Find By All utilizado por Digitalizaci�n JESR
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERHuella = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERHuella vPERHuella;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "iCveHuella," + "dtCapturada"
					+ " from PERHuella " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERHuella = new TVPERHuella();
				vPERHuella.setICvePersonal(rset.getInt(1));
				vPERHuella.setICveHuella(rset.getInt(2));
				vPERHuella.setDtCapturada(rset.getDate(3));
				vcPERHuella.addElement(vPERHuella);
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
			return vcPERHuella;
		}
	}

	/**
	 * /** Metodo Delete
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
			TVPERHuella vPERHuella = (TVPERHuella) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERHuella" + " where iCvePersonal = ?"
					+ " and iCveHuella = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERHuella.getICvePersonal());
			pstmt.setInt(2, vPERHuella.getICveHuella());
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
				warn("delete.closePERHuella", ex2);
			}
			return cClave;
		}
	}

	public void saveFile(byte[] file, int iCvePersonal, int iCveHuella)
			throws DAOException {
		TVPERFoto vPERFoto = new TVPERFoto();
		PreparedStatement pstmt = null;
		dbConn = null;
		conn = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			StringBuffer query = new StringBuffer("INSERT INTO PERHUELLA ");
			query.append("(ICVEPERSONAL,ICVEHUELLA,DTCAPTURADA,BHUELLA)");
			query.append("VALUES(?,?,?,?)");
			String instruccion = query.toString();
			pstmt = conn.prepareStatement(instruccion);
			pstmt.setInt(1, iCvePersonal);
			pstmt.setInt(2, iCveHuella);
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

	public byte[] getFile(int iCvePersona, int iCveHuella) throws DAOException {

		dbConn = null;
		conn = null;
		Statement stmt = null;
		ResultSet file = null;
		byte[] archivo = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			StringBuffer query = new StringBuffer(
					"SELECT BHUELLA FROM PERHUELLA ");
			query.append(" WHERE ICVEPERSONAL=" + iCvePersona);
			query.append("   AND ICVEHUELLA=" + iCveHuella);

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
