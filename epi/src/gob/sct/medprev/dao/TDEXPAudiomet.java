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
 * Title: Data Acces Object de EXPAudiomet DAO
 * </p>
 * <p>
 * Description: DAO Tabla EXPAudiomet
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

public class TDEXPAudiomet extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPAudiomet() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPAudiomet = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPAudiomet vEXPAudiomet;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen," + "iOido,"
					+ "iTipo," + "iX," + "iY," + "iCveServicio"
					+ " from EXPAudiomet " + cWhere
					+ " order by iCveExpediente";
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPAudiomet = new TVEXPAudiomet();
				vEXPAudiomet.setICveExpediente(rset.getInt(1));
				vEXPAudiomet.setINumExamen(rset.getInt(2));
				vEXPAudiomet.setIOido(rset.getInt(3));
				vEXPAudiomet.setITipo(rset.getInt(4));
				vEXPAudiomet.setIX(rset.getInt(5));
				vEXPAudiomet.setIY(rset.getInt(6));
				vEXPAudiomet.setICveServicio(rset.getInt(7));
				vcEXPAudiomet.addElement(vEXPAudiomet);
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
			return vcEXPAudiomet;
		}
	}
	
	

	/**
	 * Metodo Find By All
	 */
	public boolean ValidaCapturaAudiometria(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPAudiomet = new Vector();
		Boolean GraficaCapturada = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPAudiomet vEXPAudiomet;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen," + "iOido,"
					+ "iTipo," + "iX," + "iY," + "iCveServicio"
					+ " from EXPAudiomet " + cWhere
					+ " order by iCveExpediente";
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				GraficaCapturada= true;
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
			return GraficaCapturada;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj, Object val)
			throws DAOException {
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
			TVEXPAudiomet vEXPAudiomet = (TVEXPAudiomet) val;
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPAudiomet(" + "iCveExpediente,"
					+ "iNumExamen," + "iOido," + "iTipo," + "iX," + "iY,"
					+ "iCveServicio " + ")values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vEXPDictamenServ.getICveExpediente());
			pstmt.setInt(2, vEXPDictamenServ.getINumExamen());
			pstmt.setInt(3, vEXPAudiomet.getIOido());
			pstmt.setInt(4, vEXPAudiomet.getITipo());
			pstmt.setInt(5, vEXPAudiomet.getIX());
			pstmt.setInt(6, vEXPAudiomet.getIY());
			pstmt.setInt(7, vEXPAudiomet.getICveServicio());

			pstmt.executeUpdate();
			cClave = "" + vEXPDictamenServ.getICveExpediente();
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
	 * Metodo Insert
	 */
	public Object insert2(Connection conGral, Object obj, Object val, int exp, int exa)
			throws DAOException {
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
			TVEXPAudiomet vEXPAudiomet = (TVEXPAudiomet) val;
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPAudiomet(" + "iCveExpediente,"
					+ "iNumExamen," + "iOido," + "iTipo," + "iX," + "iY,"
					+ "iCveServicio " + ")values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, exp);
			pstmt.setInt(2, exa);
			pstmt.setInt(3, vEXPAudiomet.getIOido());
			pstmt.setInt(4, vEXPAudiomet.getITipo());
			pstmt.setInt(5, vEXPAudiomet.getIX());
			pstmt.setInt(6, vEXPAudiomet.getIY());
			pstmt.setInt(7, vEXPAudiomet.getICveServicio());

			pstmt.executeUpdate();
			cClave = "" + exp;
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
			TVEXPAudiomet vEXPAudiomet = (TVEXPAudiomet) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPAudiomet" + "where iCveExpediente = ? "
					+ "and iNumExamen = ?" + "and iOido = ?" + "and iTipo = ?"
					+ "and iX = ?" + " and iY = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPAudiomet.getICveExpediente());
			pstmt.setInt(2, vEXPAudiomet.getINumExamen());
			pstmt.setInt(3, vEXPAudiomet.getIOido());
			pstmt.setInt(4, vEXPAudiomet.getITipo());
			pstmt.setInt(5, vEXPAudiomet.getIX());
			pstmt.setInt(6, vEXPAudiomet.getIY());
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
			TVEXPAudiomet vEXPAudiomet = (TVEXPAudiomet) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPAudiomet" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveServicio = ?";
			/*
			 * + " and iOido = ?" + " and iTipo = ?" + " and iX = ?" +
			 * " and iY = ?";
			 */
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPAudiomet.getICveExpediente());
			pstmt.setInt(2, vEXPAudiomet.getINumExamen());
			pstmt.setInt(3, vEXPAudiomet.getICveServicio());
			/*
			 * pstmt.setInt(3, vEXPAudiomet.getIOido()); pstmt.setInt(4,
			 * vEXPAudiomet.getITipo()); pstmt.setInt(5, vEXPAudiomet.getIX());
			 * pstmt.setInt(6, vEXPAudiomet.getIY());
			 */
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
				warn("delete.closeEXPAudiomet", ex2);
			}
			return cClave;
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
			String cSQL = " DELETE FROM EXPAudiomet "
					+ " WHERE iCveExpediente = ? " + " AND iNumExamen = ? ";

			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, Integer.parseInt(cCveExpediente));
			pstmt.setInt(2, Integer.parseInt(cNumExamen));
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

	public int Liberar2(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iRset = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " DELETE FROM EXPAudiomet "
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