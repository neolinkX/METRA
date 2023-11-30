package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;
import com.micper.util.TFechas;
import com.micper.seguridad.vo.TVDinRep02;
import java.util.StringTokenizer;

/**
 * <p>
 * Title: Data Acces Object de EXPExamAplica DAO
 * </p>
 * <p>
 * Description: Data Access Object para TDTSEGEXAR
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author AG SA SANDOVAL
 * @version 1.0
 */

public class TDTSEGEXAR extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted;
	private int iUpdated;

	public TDTSEGEXAR() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTSEGEXAR = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTSEGEXAR vTSEGEXAR;

			String cSQL = "SELECT " + "COUNT(EXS.ICVESERVICIO) AS NumServC, "
					+ "EA.ICVEEXPEDIENTE, " + "EA.INUMEXAMEN, "
					+ "EA.ICVEUNIMED, " + "EA.ICVEMODULO, "
					+ "EA.DTSOLICITADO, " + "EA.DTDICTAMEN, "
					+ "EA.ICVEUSUDICTAMEN, " + "EC.LDICTAMEN " + "FROM  "
					+ "EXPEXAMAPLICA AS EA,  " + "EXPSERVICIO EXS, "
					+ "EXPEXAMCAT EC " + "WHERE "
					+ "EA.ICVEEXPEDIENTE = EC.ICVEEXPEDIENTE      AND "
					+ "EA.ICVEEXPEDIENTE = EXS.ICVEEXPEDIENTE     AND "
					+ "EC.INUMEXAMEN = EA.INUMEXAMEN              AND "
					+ "EC.INUMEXAMEN = EXS.INUMEXAMEN             AND "
					+ "EC.ICVEMOTIVO IN (1,6)                     AND "
					+ "EXS.ICVESERVICIO IN (6,5,10,12,7,11)       AND "
					+ "EXS.LCONCLUIDO = 0                         AND "
					+ "EA.ICVEPROCESO = 1                         AND "
					+ "EA.LDICTAMINADO = 1                        AND "
					+ "EA.ICVEUNIMED = ?                          AND "
					+ "EA.ICVEMODULO = ?                          AND "
					+ "EA.DTSOLICITADO >= ?                        AND "
					+ "EA.DTSOLICITADO <= ?                        AND "
					+ "EA.ICVEEXPEDIENTE > 1	" + "GROUP BY "
					+ "EA.ICVEEXPEDIENTE, " + "EA.INUMEXAMEN, "
					+ "EA.ICVEUNIMED, " + "EA.ICVEMODULO, "
					+ "EA.DTSOLICITADO, " + "EA.DTDICTAMEN, "
					+ "EA.ICVEUSUDICTAMEN, " + "EC.LDICTAMEN ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1,
					Integer.parseInt((String) hmFiltro.get("iCveUniMed")));
			pstmt.setInt(2,
					Integer.parseInt((String) hmFiltro.get("iCveModulo")));
			// pstmt.setInt(3, Integer.parseInt( (String)
			// hmFiltro.get("iCveProceso")));
			pstmt.setDate(3, (java.sql.Date) hmFiltro.get("dtConcluido"));
			pstmt.setDate(4, (java.sql.Date) hmFiltro.get("dtConcluido2"));

			// System.out.println(hmFiltro.get("dtConcluido"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTSEGEXAR = new TVTSEGEXAR();
				vTSEGEXAR.setNumServC(rset.getInt("NumServC"));
				vTSEGEXAR.setICveExpediente(rset.getInt("iCveExpediente"));
				vTSEGEXAR.setINumExamen(rset.getInt("iNumExamen"));
				vTSEGEXAR.setICveUniMed(rset.getInt("iCveUniMed"));
				vTSEGEXAR.setICveModulo(rset.getInt("iCveModulo"));
				vTSEGEXAR.setDtSolicitado(rset.getDate("dtSolicitado"));
				vTSEGEXAR.setLDictamen(rset.getInt("lDictamen"));
				vTSEGEXAR.setICveUsuDictamen(rset.getInt("IcveUsuDictamen"));
				vcTSEGEXAR.addElement(vTSEGEXAR);
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
		}
		return vcTSEGEXAR;
	}

	public Vector FindByAllS(String NumExp, String NumExa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTSEGEXAR = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTSEGEXAR vTSEGEXAR;

			String cSQL = "SELECT " + "COUNT(EXS.ICVESERVICIO) AS NumServC, "
					+ "EA.ICVEEXPEDIENTE, " + "EA.INUMEXAMEN, "
					+ "EA.ICVEUNIMED, " + "EA.ICVEMODULO, "
					+ "EA.DTSOLICITADO, " + "EA.DTDICTAMEN, "
					+ "EA.ICVEUSUDICTAMEN, " + "EC.LDICTAMEN " + "FROM  "
					+ "EXPEXAMAPLICA AS EA,  " + "EXPSERVICIO EXS, "
					+ "EXPEXAMCAT EC " + "WHERE "
					+ "EA.ICVEEXPEDIENTE = EC.ICVEEXPEDIENTE      AND "
					+ "EA.ICVEEXPEDIENTE = EXS.ICVEEXPEDIENTE     AND "
					+ "EC.INUMEXAMEN = EA.INUMEXAMEN              AND "
					+ "EC.INUMEXAMEN = EXS.INUMEXAMEN             AND "
					+ "EC.ICVEMOTIVO IN (1,6)                     AND "
					+ "EXS.ICVESERVICIO IN (6,5,10,12,7,11)       AND "
					+ "EXS.LCONCLUIDO = 0                         AND "
					+ "EA.ICVEPROCESO = 1                         AND "
					+ "EA.LDICTAMINADO = 1                        AND "
					+ "EA.INUMEXAMEN = " + NumExa + "                 AND "
					+ "EA.ICVEEXPEDIENTE = " + NumExp + "  " + "GROUP BY "
					+ "EA.ICVEEXPEDIENTE, " + "EA.INUMEXAMEN, "
					+ "EA.ICVEUNIMED, " + "EA.ICVEMODULO, "
					+ "EA.DTSOLICITADO, " + "EA.DTDICTAMEN, "
					+ "EA.ICVEUSUDICTAMEN, " + "EC.LDICTAMEN ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTSEGEXAR = new TVTSEGEXAR();
				vTSEGEXAR.setNumServC(rset.getInt("NumServC"));
				vTSEGEXAR.setICveExpediente(rset.getInt("iCveExpediente"));
				vTSEGEXAR.setINumExamen(rset.getInt("iNumExamen"));
				vTSEGEXAR.setICveUniMed(rset.getInt("iCveUniMed"));
				vTSEGEXAR.setICveModulo(rset.getInt("iCveModulo"));
				vTSEGEXAR.setDtSolicitado(rset.getDate("dtSolicitado"));
				vTSEGEXAR.setDtDictamen(rset.getDate("dtDictamen"));
				vTSEGEXAR.setLDictamen(rset.getInt("lDictamen"));
				vTSEGEXAR.setICveUsuDictamen(rset.getInt("IcveUsuDictamen"));
				vcTSEGEXAR.addElement(vTSEGEXAR);
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
		}
		return vcTSEGEXAR;
	}

	public Vector FindByAll2(String NumExp, String NumExa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTSEGEXAR = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTSEGEXAR vTSEGEXAR;

			String cSQL = "SELECT     " + "        ICVEEXPEDIENTE,   "
					+ "        INUMEXAMEN,    " + "        ICVEUNIMED,    "
					+ "        ICVEMODULO,    " + "        DTSOLICITADO,    "
					+ "        DTDICTAMEN,    "
					+ "        ICVEUSUDICTAMEN,    " + "        LDICTAMEN,    "
					+ "        NUMSERVC,    " + "        ESTATUS    "
					+ " FROM    " + "        TSEGEXAR    " + " WHERE    "
					+ "        ICVEEXPEDIENTE = " + NumExp + "	AND    "
					+ "        INUMEXAMEN = " + NumExa + "	";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTSEGEXAR = new TVTSEGEXAR();
				vTSEGEXAR.setICveExpediente(rset.getInt("iCveExpediente"));
				vTSEGEXAR.setINumExamen(rset.getInt("iNumExamen"));
				vTSEGEXAR.setICveUniMed(rset.getInt("iCveUniMed"));
				vTSEGEXAR.setICveModulo(rset.getInt("iCveModulo"));
				vTSEGEXAR.setDtSolicitado(rset.getDate("dtSolicitado"));
				vTSEGEXAR.setDtDictamen(rset.getDate("dtDictamen"));
				vTSEGEXAR.setICveUsuDictamen(rset.getInt("IcveUsuDictamen"));
				vTSEGEXAR.setLDictamen(rset.getInt("lDictamen"));
				vTSEGEXAR.setNumServC(rset.getInt("NumServC"));
				vTSEGEXAR.setEstatus(rset.getInt("Estatus"));
				vcTSEGEXAR.addElement(vTSEGEXAR);
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
		}
		return vcTSEGEXAR;
	}

	public Vector FindByAll3(String DTSOLICITADO, String DTSOLICITADO2,
			String ICVEUNIMED, String ICVEMODULO) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTSEGEXAR = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTSEGEXAR vTSEGEXAR;

			String cSQL = "SELECT     " + "        ICVEEXPEDIENTE,   "
					+ "        INUMEXAMEN,    " + "        ICVEUNIMED,    "
					+ "        ICVEMODULO,    " + "        DTSOLICITADO,    "
					+ "        DTDICTAMEN,    "
					+ "        ICVEUSUDICTAMEN,    " + "        LDICTAMEN,    "
					+ "        NUMSERVC,    " + "        ESTATUS    "
					+ " FROM    " + "        TSEGEXAR    " + " WHERE    "
					+ "        ESTATUS = 2	AND    "
					+ "        DTSOLICITADO >= '" + DTSOLICITADO + "'   AND  "
					+ "        DTSOLICITADO <= '" + DTSOLICITADO2 + "'   AND  "
					+ "        ICVEUNIMED = " + ICVEUNIMED + "  AND  "
					+ "        ICVEMODULO = " + ICVEMODULO + "   ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTSEGEXAR = new TVTSEGEXAR();
				vTSEGEXAR.setICveExpediente(rset.getInt("iCveExpediente"));
				vTSEGEXAR.setINumExamen(rset.getInt("iNumExamen"));
				vTSEGEXAR.setICveUniMed(rset.getInt("iCveUniMed"));
				vTSEGEXAR.setICveModulo(rset.getInt("iCveModulo"));
				vTSEGEXAR.setDtSolicitado(rset.getDate("dtSolicitado"));
				vTSEGEXAR.setDtDictamen(rset.getDate("dtDictamen"));
				vTSEGEXAR.setICveUsuDictamen(rset.getInt("IcveUsuDictamen"));
				vTSEGEXAR.setLDictamen(rset.getInt("lDictamen"));
				vTSEGEXAR.setNumServC(rset.getInt("NumServC"));
				vTSEGEXAR.setEstatus(rset.getInt("Estatus"));
				vcTSEGEXAR.addElement(vTSEGEXAR);
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
		}
		return vcTSEGEXAR;
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll4(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTSEGEXAR = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTSEGEXAR vTSEGEXAR;

			String cSQL = "SELECT "
					+ "COUNT(DISTINCT EXS.ICVESERVICIO) AS NumServC, "
					+ "EA.ICVEEXPEDIENTE, " + "EA.INUMEXAMEN, "
					+ "EA.ICVEUNIMED, " + "EA.ICVEMODULO, "
					+ "EA.DTSOLICITADO, " + "EA.DTDICTAMEN, "
					+ "EA.ICVEUSUDICTAMEN, " + "EC.LDICTAMEN " + "FROM  "
					+ "EXPEXAMAPLICA AS EA,  " + "EXPSERVICIO EXS, "
					+ "EXPEXAMCAT EC, " + "TSEGEXAR AS T, "
					+ "GRLUSUMEDICOS GRM  " +

					"WHERE " +

					"    GRM.ICVEUSUARIO = 71 			AND "
					+ "    GRM.ICVEUNIMED = EA.ICVEUNIMED 		AND "
					+ "    GRM.ICVEPROCESO = EA.ICVEPROCESO	        AND "
					+ "    GRM.ICVEMODULO = EA.ICVEMODULO		AND "
					+ "    GRM.ICVESERVICIO = EXS.ICVESERVICIO	AND " +

					"EA.ICVEEXPEDIENTE = EC.ICVEEXPEDIENTE      AND "
					+ "EA.ICVEEXPEDIENTE = EXS.ICVEEXPEDIENTE     AND "
					+ "EA.ICVEEXPEDIENTE = T.ICVEEXPEDIENTE       AND "
					+ "EA.INUMEXAMEN = T.INUMEXAMEN		 AND " +

					"EC.INUMEXAMEN = EA.INUMEXAMEN              AND "
					+ "EC.INUMEXAMEN = EXS.INUMEXAMEN             AND " +

					"T.ESTATUS = 1                              AND " +

					"EC.ICVEMOTIVO IN (1,6)                     AND "
					+ "EXS.ICVESERVICIO IN (6,5,10,12,7,11)       AND "
					+ "EXS.LCONCLUIDO = 0                         AND "
					+ "EA.ICVEPROCESO = 1                         AND "
					+ "EA.LDICTAMINADO = 1                        AND "
					+ "EA.ICVEUNIMED = ?                          AND "
					+ "EA.ICVEMODULO = ?                          AND "
					+ "EA.DTSOLICITADO >= ?                        AND "
					+ "EA.DTSOLICITADO <= ?                        AND "
					+ "EA.ICVEEXPEDIENTE > 1	" +

					"GROUP BY " + "EA.ICVEEXPEDIENTE, " + "EA.INUMEXAMEN, "
					+ "EA.ICVEUNIMED, " + "EA.ICVEMODULO, "
					+ "EA.DTSOLICITADO, " + "EA.DTDICTAMEN, "
					+ "EA.ICVEUSUDICTAMEN, " + "EC.LDICTAMEN ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1,
					Integer.parseInt((String) hmFiltro.get("iCveUniMed")));
			pstmt.setInt(2,
					Integer.parseInt((String) hmFiltro.get("iCveModulo")));
			// pstmt.setInt(3, Integer.parseInt( (String)
			// hmFiltro.get("iCveProceso")));
			pstmt.setDate(3, (java.sql.Date) hmFiltro.get("dtConcluido"));
			pstmt.setDate(4, (java.sql.Date) hmFiltro.get("dtConcluido2"));

			// System.out.println(hmFiltro.get("dtConcluido"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTSEGEXAR = new TVTSEGEXAR();
				vTSEGEXAR.setNumServC(rset.getInt("NumServC"));
				vTSEGEXAR.setICveExpediente(rset.getInt("iCveExpediente"));
				vTSEGEXAR.setINumExamen(rset.getInt("iNumExamen"));
				vTSEGEXAR.setICveUniMed(rset.getInt("iCveUniMed"));
				vTSEGEXAR.setICveModulo(rset.getInt("iCveModulo"));
				vTSEGEXAR.setDtSolicitado(rset.getDate("dtSolicitado"));
				vTSEGEXAR.setLDictamen(rset.getInt("lDictamen"));
				vTSEGEXAR.setICveUsuDictamen(rset.getInt("IcveUsuDictamen"));
				vcTSEGEXAR.addElement(vTSEGEXAR);
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
		}
		return vcTSEGEXAR;
	}

	/**
	 * Metodo Insert
	 */
	public String insert(Connection conGral, String NumExp, String NumExa)
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
			Vector vcTSEGEXAR = new Vector();
			TVTSEGEXAR vTSEGEXAR;

			try {
				vcTSEGEXAR = this.FindByAllS(NumExp, NumExa);
			} catch (Exception e) {
				vcTSEGEXAR = new Vector();
				e.printStackTrace();
			}
			for (int i = 0; i < vcTSEGEXAR.size(); i++) {
				vTSEGEXAR = (TVTSEGEXAR) vcTSEGEXAR.get(i);
				try {
					// INSERTANDO EN TSEGEXAR
					String cSQL = "";
					// TVEXPServicio vEXPServicio = (TVEXPServicio) obj;
					conn.setAutoCommit(false);
					conn.setTransactionIsolation(2);
					cSQL = "INSERT INTO TSegExaR " + "    (ICVEEXPEDIENTE, "
							+ "    INUMEXAMEN, " + "    ICVEUNIMED, "
							+ "    ICVEMODULO, " + "    DTSOLICITADO, "
							+ "    DTDICTAMEN, " + "    ICVEUSUDICTAMEN, "
							+ "    LDICTAMEN, " + "    NUMSERVC, "
							+ "    ESTATUS "
							+ "    )values(?,?,?,?,?,?,?,?,?,?)";

					pstmt = conn.prepareStatement(cSQL);
					// System.out.println(cSQL);
					// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE
					// LA TABLA
					pstmt.setInt(1, vTSEGEXAR.getICveExpediente());
					pstmt.setInt(2, vTSEGEXAR.getINumExamen());
					pstmt.setInt(3, vTSEGEXAR.getICveUniMed());
					pstmt.setInt(4, vTSEGEXAR.getICveModulo());
					pstmt.setDate(5, vTSEGEXAR.getDtSolicitado());
					pstmt.setDate(6, vTSEGEXAR.getDtDictamen());
					pstmt.setInt(7, vTSEGEXAR.getICveUsuDictamen());
					pstmt.setInt(8, vTSEGEXAR.getLDictamen());
					pstmt.setInt(9, vTSEGEXAR.getNumServC());
					pstmt.setInt(10, 0);
					// debug

					// System.out.println(vTSEGEXAR.getICveExpediente());
					// System.out.println(vTSEGEXAR.getINumExamen());
					// System.out.println(vTSEGEXAR.getICveUniMed());
					// System.out.println(vTSEGEXAR.getICveModulo());
					// System.out.println(vTSEGEXAR.getDtSolicitado());
					// System.out.println(vTSEGEXAR.getDtDictamen());
					// System.out.println(vTSEGEXAR.getICveUsuDictamen());
					// System.out.println(vTSEGEXAR.getLDictamen());
					// System.out.println(vTSEGEXAR.getNumServC());

					iInserted = pstmt.executeUpdate();
					cClave = "" + vTSEGEXAR.getICveExpediente();
					if (conGral == null) {
						conn.commit();
					}

				} catch (Exception e) {
					vcTSEGEXAR = new Vector();
					e.printStackTrace();
				}

			}

		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				ex.printStackTrace();
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
	 * Metodo update
	 */
	public Object update(Connection conGral, String NumExp, String NumExa,
			String Sentencia) throws DAOException {
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
			// TVPERDatos vPERDatos = (TVPERDatos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = Sentencia;

			pstmt = conn.prepareStatement(cSQL);
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

	public Object updateEstatus(Connection conGral, Object obj)
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
			TVEXPServicio vEXPServicio = (TVEXPServicio) obj;
			// log("servicio recibido para actualizar: " +
			// vEXPServicio.toHashMap().toString());
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TSEGEXAR" + " set Estatus = 2 "
					+ " where iCveExpediente = ? " + " and iNumExamen = ?";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPServicio.getICveExpediente());
			pstmt.setInt(2, vEXPServicio.getINumExamen());
			iUpdated = pstmt.executeUpdate();
			cClave = "" + vEXPServicio.getICveServicio();
			// log("el servicio " + cClave + " se ha actualizado...");
			// System.out.println();
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
				if (dbConn != null) {
					if (dbConn != null)
						dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

}