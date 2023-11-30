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
 * Title: Data Acces Object de EXPRama DAO
 * </p>
 * <p>
 * Description: DAO EXPRama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */

public class TDEXPRama extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted; // Agregado por Rafael Alcocer Caldera 08/Nov/2006
	private int iUpdated; // Agregado por Rafael Alcocer Caldera 08/Nov/2006

	public TDEXPRama() {
	}

	/**
	 * Método Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRama vEXPRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveServicio," + "iCveRama,"
					+ "lConcluido,"
					+ // <------------ Observaci�n: el nombre del campo
						// eraincorrecto, hab�an puesto iConcluido
					"dtInicio," + "dtFin," + "iCveUsuAplica"
					+ ",EXPRama.tsInicio,EXPRama.tsFin "
					+ " from EXPRama order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRama = new TVEXPRama();
				vEXPRama.setICveExpediente(rset.getInt(1));
				vEXPRama.setINumExamen(rset.getInt(2));
				vEXPRama.setICveServicio(rset.getInt(3));
				vEXPRama.setICveRama(rset.getInt(4));
				vEXPRama.setIConcluido(rset.getInt(5));
				vEXPRama.setDtInicio(rset.getDate(6));
				vEXPRama.setDtFin(rset.getDate(7));
				vEXPRama.setICveUsuAplica(rset.getInt(8));
				// vEXPRama.setTsInicio(rset.getTimestamp(9));
				// vEXPRama.setTsFin(rset.getTimestamp(10));
				vcEXPRama.addElement(vEXPRama);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcEXPRama;
		}
	}

	/**
	 * Método FindRec
	 */
	public Vector FindRec(String cExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRama = new Vector();
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRama vEXPRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			// va por el �ltimo ex�men
			String cSQLMax = "select max(iNumExamen) from EXPResultado where iCveExpediente = "
					+ cExpediente;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}

			cSQL = "select "
					+ "EXPRama.iCveExpediente, "
					+ "EXPRama.iNumExamen, "
					+ "EXPRama.iCveServicio, "
					+ "EXPRama.iCveRama, "
					+ "EXPRama.lConcluido, "
					+ "EXPRama.dtInicio, "
					+ "EXPRama.dtFin, "
					+ "EXPRama.iCveUsuAplica, "
					+ "MEDServicio.cDscServicio, "
					+ "MEDRama.cDscRama "
					+ ",EXPRama.tsInicio,EXPRama.tsFin "
					+ "from EXPRama "
					+ "inner join MEDServicio on MEDServicio.iCveServicio = EXPRama.iCveServicio "
					+ "inner join MEDRama on MEDRama.iCveServicio = EXPRama.iCveServicio "
					+ "            and MEDRama.iCveRama = EXPRama.iCveRama "
					+ "where EXPRama.iCveExpediente = " + cExpediente
					+ "  and EXPRama.iNumExamen = " + iMax
					+ " order by MEDRama.iCveServicio, "
					+ "         MEDRama.iOrden ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRama = new TVEXPRama();
				vEXPRama.setICveExpediente(rset.getInt(1));
				vEXPRama.setINumExamen(rset.getInt(2));
				vEXPRama.setICveServicio(rset.getInt(3));
				vEXPRama.setICveRama(rset.getInt(4));
				vEXPRama.setIConcluido(rset.getInt(5));
				vEXPRama.setDtInicio(rset.getDate(6));
				vEXPRama.setDtFin(rset.getDate(7));
				vEXPRama.setICveUsuAplica(rset.getInt(8));
				vEXPRama.setCDscServicio(rset.getString(9));
				vEXPRama.setCDscRama(rset.getString(10));
				vEXPRama.setTsInicio(rset.getTimestamp(11));
				vEXPRama.setTsFin(rset.getTimestamp(12));
				vcEXPRama.addElement(vEXPRama);
			}
		} catch (Exception ex) {
			warn("FindRec", ex);
			throw new DAOException("FindRec");
		} finally {
			try {
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindRec.close", ex2);
			}
			return vcEXPRama;
		}
	}

	/**
	 * Método FindRec
	 */
	public Vector FindRec(String cExpediente, String cExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRama = new Vector();
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRama vEXPRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "EXPRama.iCveExpediente, "
					+ "EXPRama.iNumExamen, "
					+ "EXPRama.iCveServicio, "
					+ "EXPRama.iCveRama, "
					+ "EXPRama.lConcluido, "
					+ "EXPRama.dtInicio, "
					+ "EXPRama.dtFin, "
					+ "EXPRama.iCveUsuAplica, "
					+ "MEDServicio.cDscServicio, "
					+ "MEDRama.cDscRama "
					+ ",EXPRama.tsInicio,EXPRama.tsFin "
					+ "from EXPRama "
					+ "inner join MEDServicio on MEDServicio.iCveServicio = EXPRama.iCveServicio "
					+ "inner join MEDRama on MEDRama.iCveServicio = EXPRama.iCveServicio "
					+ "            and MEDRama.iCveRama = EXPRama.iCveRama "
					+ "where EXPRama.iCveExpediente = " + cExpediente
					+ "  and EXPRama.iNumExamen = " + cExamen
					+ " order by MEDRama.iCveServicio, "
					+ "         MEDRama.iOrden ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRama = new TVEXPRama();
				vEXPRama.setICveExpediente(rset.getInt(1));
				vEXPRama.setINumExamen(rset.getInt(2));
				vEXPRama.setICveServicio(rset.getInt(3));
				vEXPRama.setICveRama(rset.getInt(4));
				vEXPRama.setIConcluido(rset.getInt(5));
				vEXPRama.setDtInicio(rset.getDate(6));
				vEXPRama.setDtFin(rset.getDate(7));
				vEXPRama.setICveUsuAplica(rset.getInt(8));
				vEXPRama.setCDscServicio(rset.getString(9));
				vEXPRama.setCDscRama(rset.getString(10));
				vEXPRama.setTsInicio(rset.getTimestamp(11));
				vEXPRama.setTsFin(rset.getTimestamp(12));
				vcEXPRama.addElement(vEXPRama);
			}
		} catch (Exception ex) {
			warn("FindRec", ex);
			throw new DAOException("FindRec");
		} finally {
			try {
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindRec.close", ex2);
			}
			return vcEXPRama;
		}
	}

	/**
	 * Método getLConcluido
	 */
	public Vector getLConcluido(String cExpediente, String cExamen,
			String cServicio, String cRama) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRama vEXPRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "lConcluido, " + "iNumExamen "
					+ ",EXPRama.tsInicio,EXPRama.tsFin " + "from EXPRama "
					+ "where iCveExpediente =  " + cExpediente
					+ "  and iNumExamen =  " + cExamen
					+ "  and iCveServicio = " + cServicio
					+ "  and iCveRama =  " + cRama;
			pstmt = conn.prepareStatement(cSQL);
System.out.println(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRama = new TVEXPRama();
				vEXPRama.setIConcluido(rset.getInt(1));
				vEXPRama.setINumExamen(rset.getInt(2));
				// vEXPRama.setTsInicio(rset.getTimestamp(3));
				// vEXPRama.setTsFin(rset.getTimestamp(4));
				vcEXPRama.addElement(vEXPRama);
			}
		} catch (Exception ex) {
			warn("getLConcluido", ex);
			throw new DAOException("getLConcluido");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getLConcluido.close", ex2);
			}
			return vcEXPRama;
		}
	}

	/**
	 * Método getUsuAplicaVM para varios m�dicos
	 */
	public Vector getUsuAplicaVM(String cExpediente, String cExamen,
			String cServicio, String cRama) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRama vEXPRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EXPRama.iCveUsuAplica, "
					+ "SEGUsuario.cNombre, "
					+ "SEGUsuario.cApPaterno, "
					+ "SEGUsuario.cApMaterno "
					+ ",EXPRama.tsInicio,EXPRama.tsFin "
					+ "from EXPRama "
					+ "join SEGUsuario on SEGUsuario.iCveUsuario = EXPRama.iCveUsuAplica "
					+ "where EXPRama.iCveExpediente =  " + cExpediente
					+ "  and EXPRama.iNumExamen =  " + cExamen
					+ "  and EXPRama.iCveServicio = " + cServicio
					+ "  and EXPRama.iCveRama =  " + cRama;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRama = new TVEXPRama();
				vEXPRama.setICveUsuAplica(rset.getInt(1));
				vEXPRama.setCNombre(rset.getString(2));
				vEXPRama.setCApPaterno(rset.getString(3));
				vEXPRama.setCApMaterno(rset.getString(4));
				vEXPRama.setTsInicio(rset.getTimestamp(5));
				vEXPRama.setTsFin(rset.getTimestamp(6));
				vcEXPRama.addElement(vEXPRama);
			}
		} catch (Exception ex) {
			warn("getUsuAplicaVM", ex);
			throw new DAOException("getUsuAplicaVM");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getUsuAplicaVM.close", ex2);
			}
			return vcEXPRama;
		}
	}

	/**
	 * Método getUsuAplicaUM para un m�dico
	 */
	public Vector getUsuAplicaUM(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRama vEXPRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "distinct EXPRama.iCveUsuAplica, "
					+ "SEGUsuario.cNombre, "
					+ "SEGUsuario.cApPaterno, "
					+ "SEGUsuario.cApMaterno "
					+ ",EXPRama.tsInicio,EXPRama.tsFin "
					+ "from EXPRama "
					+ "join SEGUsuario on SEGUsuario.iCveUsuario = EXPRama.iCveUsuAplica "
					+ "where EXPRama.iCveExpediente =  " + cExpediente
					+ "  and EXPRama.iNumExamen =  " + cExamen
					+ "  and EXPRama.iCveServicio = " + cServicio;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRama = new TVEXPRama();
				vEXPRama.setICveUsuAplica(rset.getInt(1));
				vEXPRama.setCNombre(rset.getString(2));
				vEXPRama.setCApPaterno(rset.getString(3));
				vEXPRama.setCApMaterno(rset.getString(4));
				// vEXPRama.setTsInicio(rset.getTimestamp(5));
				// vEXPRama.setTsFin(rset.getTimestamp(16));
				vcEXPRama.addElement(vEXPRama);
			}
		} catch (Exception ex) {
			warn("getUsuAplicaUM", ex);
			throw new DAOException("getUsuAplicaUM");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getUsuAplicaUM.close", ex2);
			}
			return vcEXPRama;
		}
	}

	/**
	 * Método que obtiene los registros de la tabla EXPRama, en base a la llave
	 * primaria
	 * 
	 * @param hmFiltros
	 *            una colecci�n con los valores de los campos usados como
	 *            filtros (llave primaria)
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 */
	public Vector findByPK(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPRama vEXPRama;
			String cWhereAnd = " where";
			String cSQL = cSQL = "select "
					+ "EXPRama.iCveExpediente,"
					+ "EXPRama.iNumExamen,"
					+ "EXPRama.iCveServicio,"
					+ "EXPRama.iCveRama,"
					+ "EXPRama.lConcluido,"
					+ "EXPRama.dtInicio,"
					+ "EXPRama.dtFin,"
					+ "EXPRama.iCveUsuAplica,"
					+ "MEDRama.cDscRama "
					+ ",EXPRama.tsInicio,EXPRama.tsFin "
					+ " from EXPRama JOIN MEDRama "
					+ " ON (EXPRama.iCveServicio=MEDRama.iCveServicio AND EXPRama.iCveRama=MEDRama.iCveRama)";

			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			String cServicio = (String) hmFiltros.get("iCveServicio");
			String cRama = (String) hmFiltros.get("iCveRama");
			String cConcluido = (String) hmFiltros.get("lConcluido");
			if (cCveExpediente != null) {
				cSQL += cWhereAnd + " EXPRama.iCveExpediente=?";
				cWhereAnd = " and";
			}
			if (cNumExamen != null) {
				cSQL += cWhereAnd + " EXPRama.iNumExamen=?";
			}
			if (cServicio != null) {
				cSQL += cWhereAnd + " EXPRama.iCveServicio=?";
			}
			if (cRama != null) {
				cSQL += cWhereAnd + " EXPRama.iCveRama=?";
			}
			if (cConcluido != null) {
				cSQL += cWhereAnd + " EXPRama.lConcluido=?";
			}
			cSQL += " order by iOrden";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveExpediente != null)
				pstmt.setInt(i++, Integer.parseInt(cCveExpediente));
			if (cNumExamen != null)
				pstmt.setInt(i++, Integer.parseInt(cNumExamen));
			if (cServicio != null)
				pstmt.setInt(i++, Integer.parseInt(cServicio));
			if (cRama != null)
				pstmt.setInt(i++, Integer.parseInt(cRama));
			if (cConcluido != null)
				pstmt.setInt(i++, Integer.parseInt(cConcluido));
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vEXPRama = new TVEXPRama();
				vEXPRama.setICveExpediente(rset.getInt("iCveExpediente"));
				vEXPRama.setINumExamen(rset.getInt("iNumExamen"));
				vEXPRama.setICveServicio(rset.getInt("iCveServicio"));
				vEXPRama.setICveRama(rset.getInt("iCveRama"));
				vEXPRama.setIConcluido(rset.getInt("lConcluido")); // en el V.O.
																	// pusieron
																	// incorrectamente
																	// iConcluido,
																	// en vez de
																	// lConcluido
				vEXPRama.setDtInicio(rset.getDate("dtInicio"));
				vEXPRama.setDtFin(rset.getDate("dtFin"));
				vEXPRama.setICveUsuAplica(rset.getInt("iCveUsuAplica"));
				vEXPRama.setCDscRama(rset.getString("cDscRama"));
				vEXPRama.setTsInicio(rset.getTimestamp(10));
				vEXPRama.setTsFin(rset.getTimestamp(11));
				vcEXPRama.addElement(vEXPRama);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcEXPRama;
	}

	/**
	 * Método que obtiene los registros de la tabla EXPRama, en base a la llave
	 * primaria
	 * 
	 * @param hmFiltros
	 *            una colecci�n con los valores de los campos usados como
	 *            filtros (llave primaria)
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 */
	public Vector findByPK2(String Filtros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPRama vEXPRama;
			String cWhereAnd = " where";
			String cSQL = cSQL = "select "
					+ "EXPRama.iCveExpediente,"
					+ "EXPRama.iNumExamen,"
					+ "EXPRama.iCveServicio,"
					+ "EXPRama.iCveRama,"
					+ "EXPRama.lConcluido,"
					+ "EXPRama.dtInicio,"
					+ "EXPRama.dtFin,"
					+ "EXPRama.iCveUsuAplica,"
					+ "MEDRama.cDscRama "
					+ ",EXPRama.tsInicio,EXPRama.tsFin "
					+ " from EXPRama JOIN MEDRama "
					+ " ON (EXPRama.iCveServicio=MEDRama.iCveServicio AND EXPRama.iCveRama=MEDRama.iCveRama) "
					+ " where " + Filtros + " order by iOrden";

			/*
			 * String cCveExpediente=(String)hmFiltros.get("iCveExpediente");
			 * String cNumExamen =(String)hmFiltros.get("iNumExamen"); String
			 * cServicio =(String)hmFiltros.get("iCveServicio"); String cRama
			 * =(String)hmFiltros.get("iCveRama"); String cConcluido =
			 * (String)hmFiltros.get("lConcluido"); if(cCveExpediente!=null){
			 * cSQL+=cWhereAnd+" EXPRama.iCveExpediente=?"; cWhereAnd = " and";
			 * } if(cNumExamen!=null){ cSQL+=cWhereAnd+" EXPRama.iNumExamen=?";
			 * } if(cServicio!=null){ cSQL+=cWhereAnd+" EXPRama.iCveServicio=?";
			 * } if(cRama!=null){ cSQL+=cWhereAnd+" EXPRama.iCveRama=?"; }
			 * if(cConcluido!=null){ cSQL+=cWhereAnd+" EXPRama.lConcluido=?"; }
			 * cSQL+=" order by iOrden";
			 */
			pstmt = conn.prepareStatement(cSQL);
			// System.out.println(cSQL);
			int i = 1;
			/*
			 * if(cCveExpediente!=null)
			 * pstmt.setInt(i++,Integer.parseInt(cCveExpediente)); if(cNumExamen
			 * !=null) pstmt.setInt(i++,Integer.parseInt(cNumExamen ));
			 * if(cServicio !=null) pstmt.setInt(i++,Integer.parseInt(cServicio
			 * )); if(cRama !=null) pstmt.setInt(i++,Integer.parseInt(cRama ));
			 * if(cConcluido !=null)
			 * pstmt.setInt(i++,Integer.parseInt(cConcluido ));
			 */
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vEXPRama = new TVEXPRama();
				vEXPRama.setICveExpediente(rset.getInt("iCveExpediente"));
				vEXPRama.setINumExamen(rset.getInt("iNumExamen"));
				vEXPRama.setICveServicio(rset.getInt("iCveServicio"));
				vEXPRama.setICveRama(rset.getInt("iCveRama"));
				vEXPRama.setIConcluido(rset.getInt("lConcluido")); // en el V.O.
																	// pusieron
																	// incorrectamente
																	// iConcluido,
																	// en vez de
																	// lConcluido
				vEXPRama.setDtInicio(rset.getDate("dtInicio"));
				vEXPRama.setDtFin(rset.getDate("dtFin"));
				vEXPRama.setICveUsuAplica(rset.getInt("iCveUsuAplica"));
				vEXPRama.setCDscRama(rset.getString("cDscRama"));
				vEXPRama.setTsInicio(rset.getTimestamp(10));
				vEXPRama.setTsFin(rset.getTimestamp(11));
				vcEXPRama.addElement(vEXPRama);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcEXPRama;
	}

	/**
	 * Método Insert
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
			TVEXPRama vEXPRama = (TVEXPRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPRama(" + "iCveExpediente," + "iNumExamen,"
					+ "iCveServicio," + "iCveRama," + "lConcluido,"
					+ "dtInicio," + "dtFin," + "iCveUsuAplica"
					+ ")values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPRama.getICveExpediente());
			pstmt.setInt(2, vEXPRama.getINumExamen());
			pstmt.setInt(3, vEXPRama.getICveServicio());
			pstmt.setInt(4, vEXPRama.getICveRama());
			pstmt.setInt(5, vEXPRama.getIConcluido());
			pstmt.setDate(6, vEXPRama.getDtInicio());
			pstmt.setDate(7, vEXPRama.getDtFin());
			pstmt.setInt(8, vEXPRama.getICveUsuAplica());
			iInserted = pstmt.executeUpdate();
			cClave = "" + vEXPRama.getICveExpediente();
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método insertFromExpExamAplica
	 */
	public Object insertFromExpExamAplica(Connection conGral, Object obj,
			int iCveServicio, int iCveRama) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPRama(" + "iCveExpediente," + "iNumExamen,"
					+ "iCveServicio," + "iCveRama," + "lConcluido)"
					+ " values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPRama "
					+ "Where iCveExpediente = "
					+ vEXPExamAplica.getICveExpediente()
					+ " And  iNumExamen = " + vEXPExamAplica.getINumExamen()
					+ " And  iCveServicio = " + iCveServicio
					+ " And  iCveRama = " + iCveRama;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			pstmt.setInt(1, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(2, vEXPExamAplica.getINumExamen());
			pstmt.setInt(3, iCveServicio);
			pstmt.setInt(4, iCveRama);
			pstmt.setInt(5, 0);
			if (iMax == 0) {
				pstmt.executeUpdate();
			}
			cClave = "" + vEXPExamAplica.getICveExpediente();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insertFromExpExamAplica", ex1);
			}
			warn("insertFromExpExamAplica", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insertFromExpExamAplica.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método update
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
			TVEXPRama vEXPRama = (TVEXPRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPRama" + " set lConcluido= ?, " + "dtInicio= ?, "
					+ "dtFin= ?, " + "tsFin= CURRENT TIMESTAMP, " + "iCveUsuAplica= ? "
					+ "where iCveExpediente = ? " + "and iNumExamen = ?"
					+ "and iCveServicio = ?" + " and iCveRama = ?";
			
			System.out.println(cSQL);
			System.out.println("usu="+vEXPRama.getICveUsuAplica());
			
			
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPRama.getIConcluido());
			pstmt.setDate(2, vEXPRama.getDtInicio());
			pstmt.setDate(3, vEXPRama.getDtFin());
			pstmt.setInt(4, vEXPRama.getICveUsuAplica());
			pstmt.setInt(5, vEXPRama.getICveExpediente());
			pstmt.setInt(6, vEXPRama.getINumExamen());
			pstmt.setInt(7, vEXPRama.getICveServicio());
			pstmt.setInt(8, vEXPRama.getICveRama());
			iUpdated = pstmt.executeUpdate();
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
				if (dbConn != null)
					if (dbConn != null)
						dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método Delete
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
			TVEXPRama vEXPRama = (TVEXPRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPRama" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveServicio = ?"
					+ " and iCveRama = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPRama.getICveExpediente());
			pstmt.setInt(2, vEXPRama.getINumExamen());
			pstmt.setInt(3, vEXPRama.getICveServicio());
			pstmt.setInt(4, vEXPRama.getICveRama());
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeEXPRama", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Atributo que define si los mensajes de depuraci�n se env�an a la consola.
	 * Si se requiere que los mensajes enviados por el Método log() no se
	 * muestren, es necesario establecer su valor a <code>false</code>.
	 */
	private boolean debug = false;

	/**
	 * Método que env�a un Object a la consola, como String, si la bandera debug
	 * est� activada. El nombre cualificado de la clase se env�a antes del
	 * objeto.
	 * 
	 * @param obj
	 *            el objeto que ser� enviado a la consola como String
	 * @author Romeo Sanchez
	 */
	private void log(Object obj) {
		// ************* No modificar *************
		if (debug)
			System.out
					.println(this.getClass().getName() + ":" + obj.toString());
	}

	public int Liberar(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iRset = 0;
		try {
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			String cServicio = (String) hmFiltros.get("iCveServicio");
			String cRama = "";
			if (hmFiltros.get("iCveRama") != null)
				cRama = (String) hmFiltros.get("iCveRama");

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " UPDATE EXPRama SET " + " lConcluido = 0, "
					+ " iCveUsuAplica = 0 " + " WHERE iCveServicio = ? "
					+ " AND iCveExpediente = ? " + " AND iNumExamen = ? "
					+ (cRama.trim().length() > 0 ? " AND iCveRama = ? " : "");

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, Integer.parseInt(cServicio));
			pstmt.setInt(2, Integer.parseInt(cCveExpediente));
			pstmt.setInt(3, Integer.parseInt(cNumExamen));
			if (cRama.trim().length() > 0)
				pstmt.setInt(4, Integer.parseInt(cRama));

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

	/**
	 * Agregado por Rafael Alcocer Caldera 08/Nov/2006
	 * 
	 * @return int
	 */
	public int getIInserted() {
		return iInserted;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 08/Nov/2006
	 * 
	 * @return int
	 */
	public int getIUpdated() {
		return iUpdated;
	}

	/**
	 * Método REGRESA ENTERO (Utilizado en la sección de ADMINISTRACIÓN DE
	 * EXPEDIENTES BORRAR EXPEDIENTE )
	 * 
	 * @Autor: AG SA
	 */
	public int RegresaInt(String SQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		int regreso = 0;
		// TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = SQL;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			vPERDatos = new TVPERDatos();
			while (rset.next()) {
				regreso = rset.getInt(1);
				// System.out.println(regreso);
			}

		} catch (Exception ex) {
			warn("RegresaInt", ex);
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
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}

	/**
	 * Método update
	 */
	public String updateFechaInicioRama(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL = "";
			//TVEXPRama vEXPRama = (TVEXPRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPRama" + " set tsInicio= CURRENT TIMESTAMP "
					+ " where "+cWhere;
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			iUpdated = pstmt.executeUpdate();
			cClave = "";
			if (dbConn == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (dbConn == null) {
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
				if (dbConn == null) {
					conn.close();
				}
				if (dbConn != null)
					if (dbConn != null)
						dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	
}
