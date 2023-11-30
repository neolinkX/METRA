/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dao;

import com.micper.ingsw.TParametro;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.dwr.vo.GrlTipoOperaBit;
import gob.sct.medprev.vo.*;

/**
 * 
 * @author admin
 */
public class TDEXPBITMOD extends DAOBase {

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");// MEDPREV
	private String dataSourceName2 = VParametros.getPropEspecifica("ConDB");// ADMSEG

	/**
	 * Metodo Find By All
	 */
	public List<GrlTipoOperaBit> findAllTipoOperaBit() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<GrlTipoOperaBit> listaTipoOpera = new ArrayList<GrlTipoOperaBit>();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			GrlTipoOperaBit grlTipoOperaBit;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT IOPERACION, CDESCRIPCION FROM GRLTIPOOPERABIT";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			grlTipoOperaBit = new GrlTipoOperaBit();
			grlTipoOperaBit.setiOperacion(0);
			grlTipoOperaBit.setcDescripcion("SELECCIONE...");
			listaTipoOpera.add(grlTipoOperaBit);
			while (rset.next()) {
				grlTipoOperaBit = new GrlTipoOperaBit();
				grlTipoOperaBit.setiOperacion(rset.getInt("IOPERACION"));
				grlTipoOperaBit.setcDescripcion(rset.getString("CDESCRIPCION"));
				listaTipoOpera.add(grlTipoOperaBit);
			}
		} catch (Exception ex) {
			warn("FindAll", ex);
			throw new DAOException("FindAll");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindAll.close", ex2);
			}
			return listaTipoOpera;
		}
	}

	public List<ExpBitMod> findExpBitMod(String persona, String usuario,
			String operacion, String fechaInicio, String fechaFin)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<ExpBitMod> listaBit = new ArrayList<ExpBitMod>();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			ExpBitMod expBitMod;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			boolean and = false;

			cSQL = "SELECT mod.*, op.CDESCRIPCION as DESCOP, (su.CNOMBRE || ' ' || su.CAPPATERNO || ' ' || su.CAPMATERNO) as nombreComp"
					+ " FROM EXPBITMOD mod join GRLTIPOOPERABIT op on op.IOPERACION = mod.IOPERACION "
					+ " join SEGUSUARIO su on mod.ICVEUSUREALIZA = su.ICVEUSUARIO where ";
			if (!persona.equals("")) {
				cSQL += " mod.ICVEEXPEDIENTE = " + persona;
				and = true;
			}
			if (!usuario.equals("")) {
				if (and) {
					cSQL += " and mod.ICVEUSUREALIZA = " + usuario;
				} else {
					cSQL += " mod.ICVEUSUREALIZA = " + usuario;
					and = true;
				}
			}
			if (!operacion.equals("")) {
				if (and) {
					cSQL += " and mod.IOPERACION = " + operacion;
				} else {
					cSQL += " mod.IOPERACION = " + operacion;
					and = true;
				}
			}
			if (!fechaInicio.equals("") && !fechaFin.equals("")) {
				if (and) {
					cSQL += " and mod.DTREALIZADO BETWEEN '" + fechaInicio
							+ " 00:00:00.0' and '" + fechaFin + " 00:00:00.0' ";
				} else {
					cSQL += " mod.DTREALIZADO BETWEEN '" + fechaInicio
							+ " 00:00:00.0' and '" + fechaFin + " 00:00:00.0' ";
					and = true;
				}
			}

			// System.out.println("revisando " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm");
			while (rset.next()) {
				expBitMod = new ExpBitMod();
				// expBitMod.setiOperacion(rset.getString("IOPERACION"));
				// expBitMod.setcDescripcion(rset.getString("CDESCRIPCION"));
				expBitMod.setDescripcion(rset.getString("CDESCRIPCION"));
				expBitMod.setDtRealizado(formatoDelTexto.format(rset
						.getTimestamp("DTREALIZADO")));
				expBitMod.setOperacion(rset.getString("DESCOP"));
				expBitMod.setiCveExpediente(rset.getString("ICVEEXPEDIENTE"));
				expBitMod.setiCveRama(rset.getString("ICVERAMA"));
				expBitMod.setiCveServicio(rset.getString("ICVESERVICIO"));
				expBitMod.setiCveSintoma(rset.getString("ICVESINTOMA"));
				expBitMod.setiCveUsuarioRealiza(rset.getString("nombreComp"));
				expBitMod.setiNumExamen(rset.getString("INUMEXAMEN"));
				expBitMod.setlDictamen(rset.getString("LDICTAMEN"));
				listaBit.add(expBitMod);
			}
		} catch (Exception ex) {
			warn("FindAll", ex);
			throw new DAOException("FindAll");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindAll.close", ex2);
			}
			return listaBit;
		}
	}

	/**
	 * Metodo Insert
	 */
	public int insertDictamenes(Connection conGral, ExpBitMod extBitModVO)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = "insert into EXPBITMOD "
					+ " (icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, ldictamen) "
					+ " values (" + extBitModVO.getiCveExpediente() + " ,"
					+ extBitModVO.getiNumExamen() + " ,"
					+ extBitModVO.getOperacion() + " ,'" + sqlTimestamp
					+ "' ,'" + extBitModVO.getDescripcion() + "' ,"
					+ extBitModVO.getiCveUsuarioRealiza() + " ,"
					+ extBitModVO.getlDictamen() + " )";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			iCta = pstmt.executeUpdate();
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	/**
	 * Metodo Insert
	 */
	public int insertServicios(Connection conGral, ExpBitMod extBitModVO)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = "insert into EXPBITMOD "
					+ " (icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio) "
					+ " values (" + extBitModVO.getiCveExpediente() + " ,"
					+ extBitModVO.getiNumExamen() + " ,"
					+ extBitModVO.getOperacion() + " ,'" + sqlTimestamp
					+ "' ,'" + extBitModVO.getDescripcion() + "' ,"
					+ extBitModVO.getiCveUsuarioRealiza() + " ,"
					+ extBitModVO.getiCveServicio() + " )";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			iCta = pstmt.executeUpdate();
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	/**
	 * Metodo Insert
	 */
	public int insertaRegistroBitacoraBiometricos(ExpBitMod extBitModVO)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = "insert into EXPBITMOD "
					+ " (icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio) "
					+ " values (" + extBitModVO.getiCveExpediente() + " ,"
					+ extBitModVO.getiNumExamen() + " ,"
					+ extBitModVO.getOperacion() + " ,'" + sqlTimestamp
					+ "' ,'" + extBitModVO.getDescripcion() + "' ,"
					+ extBitModVO.getiCveUsuarioRealiza() + " ,"
					+ extBitModVO.getiCveServicio() + " )";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			iCta = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
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
				conn.close();
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	public int findAccesosIncorrectosConcecutivos(String icveUsuario)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = "SELECT" + " COALESCE(LFALLIDO,0) AS LFALLIDO"
					+ " FROM SEGAccPwd " + " WHERE ICVEUSUARIO = "
					+ icveUsuario + "  AND DATE(TINIACCESO) = CURRENT DATE "
					+ " ORDER BY TINIACCESO";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				// System.out.println("valor " + rset.getInt("LFALLIDO") +
				// " contador " + iCta);
				if (rset.getInt("LFALLIDO") > 0) {
					iCta = iCta + 1;
				} else {
					iCta = 0;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	public int findValidacionBiometricaUsuarioXModulo(String icveUsuario)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = "SELECT DISTINCT(B.LVALIDA) AS MODULOSBIOMETRICOS FROM GRLUSUMEDICOS A "
					+ " JOIN GRLMODULO B ON (A.ICVEUNIMED = B.ICVEUNIMED AND A.ICVEMODULO = B.ICVEMODULO) "
					+ " WHERE ICVEUSUARIO = "
					+ icveUsuario
					+ " ORDER BY B.LVALIDA DESC ";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				if (rset.getInt("MODULOSBIOMETRICOS") > iCta) {
					iCta = rset.getInt("MODULOSBIOMETRICOS");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	public int findValidacionBiometricaUsuarioXModulo5(String ICVEUNIMED,
			String ICVEMODULO) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = " SELECT LVALIDA FROM GRLMODULO WHERE (ICVEUNIMED ="
					+ ICVEUNIMED + " AND ICVEMODULO =" + ICVEMODULO + ")";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				if (rset.getInt("LVALIDA") > iCta) {
					iCta = rset.getInt("LVALIDA");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	public int findValidacionBiometricaUsuarioXModulo3(String icveUsuario,
			String ICVEEXPEDIENTE, String INUMEXAMEN) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = "SELECT DISTINCT(B.LVALIDA) AS MODULOSBIOMETRICOS FROM GRLUSUMEDICOS A "
					+ " INNER JOIN GRLMODULO B ON (A.ICVEUNIMED = B.ICVEUNIMED AND A.ICVEMODULO = B.ICVEMODULO)"
					+ " INNER JOIN EXPEXAMAPLICA E ON (A.ICVEUNIMED = E.ICVEUNIMED AND A.ICVEMODULO = E.ICVEMODULO)"
					+ " WHERE A.ICVEUSUARIO = "
					+ icveUsuario
					+ " AND E.ICVEEXPEDIENTE = "
					+ ICVEEXPEDIENTE
					+ " AND E.INUMEXAMEN = "
					+ INUMEXAMEN
					+ " ORDER BY B.LVALIDA DESC";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				if (rset.getInt("MODULOSBIOMETRICOS") > iCta) {
					iCta = rset.getInt("MODULOSBIOMETRICOS");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	public int findExamenDictaminado(String iCveExp, String iNumExm)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = " SELECT LDICTAMINADO FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = "
					+ iCveExp + " AND INUMEXAMEN = " + iNumExm;

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				iCta = rset.getInt("LDICTAMINADO");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	public int findAccesosIncorrectosConcecutivosExp(String icveExpediente)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = " SELECT COALESCE(LFALLIDO,0) AS LFALLIDO FROM SEGACCEXP "
					+ " WHERE ICVEXPEDIENTE =  "
					+ icveExpediente
					+ " AND DATE(TINIACCESO) = CURRENT DATE  "
					+ " ORDER BY TINIACCESO";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				// System.out.println("valor " + rset.getInt("LFALLIDO") +
				// " contador " + iCta);
				if (rset.getInt("LFALLIDO") > 0) {
					iCta = iCta + 1;
				} else {
					iCta = 0;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
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
				conn.close();
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	public int findCLUES(String USUARIO, String CLUES) throws DAOException {
		int iCta = 0;// Se inicializa como cero apra indicar que la CLUES NO ES
						// CORRECTA
		if (findUsuario(USUARIO) > 0) {// Si se encontro el usuario se busca la
										// clue
			DbConnection dbConn = null;
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);

				java.util.Date utilDate = new java.util.Date(); // fecha actual
				long lnMilisegundos = utilDate.getTime();
				java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
						lnMilisegundos);
				String cSQL = " SELECT U.ICVEUSUARIO FROM "
						+ " SEGUSUARIO AS USU "
						+ " JOIN GRLUSUMEDICOS AS U ON USU.ICVEUSUARIO = U.ICVEUSUARIO"
						+ " JOIN GRLMODULO AS M ON (U.ICVEUNIMED = M.ICVEUNIMED AND U.ICVEMODULO = M.ICVEMODULO)"
						+ " WHERE USU.CUSUARIO = '" + USUARIO + "' "
						+ " AND M.CCLUES = UPPER('" + CLUES + "') "
						+ " GROUP BY (U.ICVEUSUARIO)";

			 System.out.println(cSQL);
				pstmt = conn.prepareStatement(cSQL);
				ResultSet rset = pstmt.executeQuery();

				while (rset.next()) {
					iCta++;// Se aumenta el contador de numero de modulos
							// asignados que contienen CLUES o que las requieren
							// para acceder
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				try {
					conn.rollback();
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
					conn.close();
					if (dbConn != null) {
						dbConn.closeConnection();
					}
				} catch (Exception ex2) {
					warn("insert.close", ex2);
				}
			}
		} else {
			iCta = -1;// Se regresa -1 para indicar que no se encontro el
						// usuario escrito
		}
		return iCta;
	}

	public int findUsuario(String USUARIO) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			dbConn = new DbConnection(dataSourceName2);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = " SELECT ICVEUSUARIO FROM SEGUSUARIO WHERE CUSUARIO = '"
					+ USUARIO + "' ";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				iCta++;
			}
			if (iCta <= 0) {
				iCta = -1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
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
				conn.close();
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	/**
	 * Metodo Insert
	 */
	public int insertServiciosIpMacName(Connection conGral,
			ExpBitMod extBitModVO) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = "insert into EXPBITMOD "
					+ " (icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio, CMACADDRESS, CCOMPUTERNAME, CIPACCESO) "
					+ " values (" + extBitModVO.getiCveExpediente() + " ,"
					+ extBitModVO.getiNumExamen() + " ,"
					+ extBitModVO.getOperacion() + " ,'" + sqlTimestamp
					+ "' ,'" + extBitModVO.getDescripcion() + "' ,"
					+ extBitModVO.getiCveUsuarioRealiza() + " ,"
					+ extBitModVO.getiCveServicio() + " , '"
					+ extBitModVO.getMacAddress() + "' , '"
					+ extBitModVO.getComputerName() + "' , '"
					+ extBitModVO.getIpAddress() + "')";

			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			iCta = pstmt.executeUpdate();
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}

	/**
	 * Metodo Insert
	 */
	public int insertDictamenesIpMacName(Connection conGral,
			ExpBitMod extBitModVO) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			String cSQL = "insert into EXPBITMOD "
					+ " (icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, ldictamen, CMACADDRESS, CCOMPUTERNAME, CIPACCESO) "
					+ " values (" + extBitModVO.getiCveExpediente() + " ,"
					+ extBitModVO.getiNumExamen() + " ,"
					+ extBitModVO.getOperacion() + " ,'" + sqlTimestamp
					+ "' ,'" + extBitModVO.getDescripcion() + "' ,"
					+ extBitModVO.getiCveUsuarioRealiza() + " ,"
					+ extBitModVO.getlDictamen() + " , '"
					+ extBitModVO.getMacAddress() + "' , '"
					+ extBitModVO.getComputerName() + "' , '"
					+ extBitModVO.getIpAddress() + "')";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			iCta = pstmt.executeUpdate();
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}
}
