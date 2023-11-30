/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Title: Data Acces Object de MEDAReg DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * 
 * @author AG SA
 * @version 1.0
 */

public class TDEXPSERVPRef extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPSERVPRef() {
	}

	/*
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPSERVPRef = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPSERVPRef vEXPSERVPRef;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " SELECT    ICVEEXPEDIENTE, " + "INUMEXAMEN, "
					+ "ICVESERVICIO, " + "ICVERAMA, " + "ICVEDOCUMENTO,"
					+ "IORDEN, " + "X1, 	" + "Y1, " + "X2, 	" + "Y2, "
					+ "width, 	" + "height, " + "CDESCRIPCION "
					+ "FROM EXPSERVPRef WHERE " + cWhere;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPSERVPRef = new TVEXPSERVPRef();
				vEXPSERVPRef.setiCveExpediente(rset.getInt(1));
				vEXPSERVPRef.setiNumExamen(rset.getInt(2));
				vEXPSERVPRef.setiCveServicio(rset.getInt(3));
				vEXPSERVPRef.setiCveRama(rset.getInt(4));
				vEXPSERVPRef.setiCveDocumento(rset.getInt(5));
				vEXPSERVPRef.setiOrden(rset.getInt(6));
				vEXPSERVPRef.setx1(rset.getInt(7));
				vEXPSERVPRef.sety1(rset.getInt(8));
				vEXPSERVPRef.setx2(rset.getInt(9));
				vEXPSERVPRef.sety2(rset.getInt(10));
				vEXPSERVPRef.setwidth(rset.getInt(11));
				vEXPSERVPRef.setheight(rset.getInt(12));
				vEXPSERVPRef.setcDescripcion(rset.getString(13));
				vcEXPSERVPRef.addElement(vEXPSERVPRef);
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
			return vcEXPSERVPRef;
		}
	}

	/**
	 * Metodo Insert - Acciones
	 */
	// public Object insert(Connection conGral, Object obj) throws DAOException
	// {
	public String insert(Connection conGral, Object obj2) throws DAOException {
		// System.out.println("Entrando en el insert");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		PreparedStatement pstmtMax2 = null;
		ResultSet rsetMax2 = null;
		String cClave = "";
		String iMax = "";
		int iMax2 = 0;
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			String cSQL = "";
			TVEXPSERVPRef vEXPSERVPRef = (TVEXPSERVPRef) obj2;
			Vector Servicios = new Vector();
			String Resultado = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// System.out.println("iMax2 = "+iMax2);

			iMax = this.FindByAlS("Select max(iorden) from EXPSERVPRef where "
					+ "ICVEEXPEDIENTE = " + vEXPSERVPRef.getiCveExpediente()
					+ " and " + "INUMEXAMEN = " + vEXPSERVPRef.getiNumExamen()
					+ " and " + "ICVESERVICIO = "
					+ vEXPSERVPRef.getiCveServicio() + " and " + "ICVERAMA = "
					+ vEXPSERVPRef.getiCveRama() + " and " + "ICVEDOCUMENTO = "
					+ vEXPSERVPRef.getiCveDocumento() + " ");

			cSQL = "INSERT INTO EXPSERVPRef (ICVEEXPEDIENTE, " + "INUMEXAMEN, "
					+ "ICVESERVICIO, " + "ICVERAMA, " + "ICVEDOCUMENTO,"
					+ "IORDEN, " + "X1, 	" + "Y1, " + "X2, 	" + "Y2, "
					+ "width, 	" + "height, " + "CDESCRIPCION, "
					+ "TSCAPTURA)   " + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			if (iMax != null)
				if (!iMax.equals(""))
					iMax2 = Integer.parseInt(iMax);
			// System.out.println(iMax2);
			// System.out.println("Clave de documento = "+vEXPSERVPRef.getiCveDocumento());
			// ///////////////////////////////////
			pstmt.setInt(1, vEXPSERVPRef.getiCveExpediente());
			pstmt.setInt(2, vEXPSERVPRef.getiNumExamen());
			pstmt.setInt(3, vEXPSERVPRef.getiCveServicio());
			pstmt.setInt(4, vEXPSERVPRef.getiCveRama());
			pstmt.setInt(5, vEXPSERVPRef.getiCveDocumento());
			pstmt.setInt(6, iMax2 + 1);
			pstmt.setInt(7, vEXPSERVPRef.getx1());
			pstmt.setInt(8, vEXPSERVPRef.gety1());
			pstmt.setInt(9, vEXPSERVPRef.getx2());
			pstmt.setInt(10, vEXPSERVPRef.gety2());
			pstmt.setInt(11, vEXPSERVPRef.getwidth());
			pstmt.setInt(12, vEXPSERVPRef.getheight());
			pstmt.setString(13, vEXPSERVPRef.getcDescripcion());// REGLA
			pstmt.setTimestamp(14, sqlTimestamp);

			pstmt.executeUpdate();
			cClave = "" + iMax;
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
	 * Metodo Find By All
	 * 
	 */
	public String FindByAlS(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String Respuesta = "";
		int contador = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = cWhere;

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Respuesta = rset.getString(1);
				contador++;
			}

			if (contador == 0) {
				Respuesta = "0";
			}
			// System.out.println("Respuesta = "+Respuesta);

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
			return Respuesta;
		}
	}

	public int Liberar(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iRset = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " DELETE FROM EXPServPref "
					+ " WHERE iCveExpediente = ? " + " AND iNumExamen = ? "
					+ " AND iCveServicio = ? ";

			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			String cServicio = (String) hmFiltros.get("iCveServicio");

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, Integer.parseInt(cCveExpediente));
			pstmt.setInt(2, Integer.parseInt(cNumExamen));
			pstmt.setInt(3, Integer.parseInt(cServicio));
			iRset = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("Liberar", ex2);
			}
		}
		return iRset;
	}

}