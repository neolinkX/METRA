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

import java.io.*;

import com.micper.util.logging.*;

/**
 * <p>
 * Title: Data Acces Object de EXPExamAplica DAO
 * </p>
 * <p>
 * Description: Data Access Object para TDTSSEGEXAR
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

public class TDTSSEGEXAR extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted;
	private int iUpdated;

	public TDTSSEGEXAR() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String NumExp, String NumExa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTSSEGEXAR = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTSSEGEXAR vTSSEGEXAR;

			String cSQL = "SELECT  "
					+ "        EA.ICVEEXPEDIENTE,  "
					+ "        EA.INUMEXAMEN,  "
					+ "        EXS.ICVESERVICIO,  "
					+ "        MED.CDSCSERVICIO,  "
					+ "        EXS.LCONCLUIDO,  "
					+ "        EXS.ICVEUSUAPLICA  "
					+ "FROM    "
					+ "        EXPEXAMAPLICA AS EA,   "
					+ "        EXPSERVICIO EXS,  "
					+ "        EXPEXAMCAT EC,  "
					+ "        MEDSERVICIO MED  "
					+ "WHERE   "
					+ "        EA.ICVEEXPEDIENTE = EC.ICVEEXPEDIENTE      AND  "
					+ "        EA.ICVEEXPEDIENTE = EXS.ICVEEXPEDIENTE     AND  "
					+ "        EA.INUMEXAMEN = EC.INUMEXAMEN              AND  "
					+ "        EA.INUMEXAMEN = EXS.INUMEXAMEN             AND  "
					+ "        EXS.ICVESERVICIO = MED.ICVESERVICIO        AND  "
					+ "        EC.ICVEMOTIVO IN (1,6)                     AND  "
					+ "        EXS.ICVESERVICIO IN (6,5,10,12,7,11)       AND  "
					+ "        EXS.LCONCLUIDO = 0                         AND  "
					+ "        EA.ICVEPROCESO = 1 			 AND  "
					+ "        EA.LDICTAMINADO = 1                        AND  "
					+ "        EA.ICVEEXPEDIENTE = " + NumExp
					+ "             AND  " + "        EA.INUMEXAMEN = "
					+ NumExa + "  " + "GROUP BY   "
					+ "        EA.ICVEEXPEDIENTE,  "
					+ "        EA.INUMEXAMEN,  "
					+ "        EXS.ICVESERVICIO,  "
					+ "        MED.CDSCSERVICIO,  "
					+ "        EXS.LCONCLUIDO,   "
					+ "        EXS.ICVEUSUAPLICA  ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTSSEGEXAR = new TVTSSEGEXAR();
				vTSSEGEXAR.setICveExpediente(rset.getInt("iCveExpediente"));
				vTSSEGEXAR.setINumExamen(rset.getInt("iNumExamen"));
				vTSSEGEXAR.setICveServicio(rset.getInt("iCveServicio"));
				vTSSEGEXAR.setCDscServicio(rset.getString("cDscServicio"));
				vTSSEGEXAR.setLConcluido(rset.getInt("lConcluido"));
				vTSSEGEXAR.setICveUsuAplica(rset.getInt("iCveUsuAplica"));
				vcTSSEGEXAR.addElement(vTSSEGEXAR);
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
		return vcTSSEGEXAR;
	}

	public Vector FindByAll2(String NumExp, String NumExa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTSSEGEXAR = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTSSEGEXAR vTSSEGEXAR;

			String cSQL = "SELECT " + "         TS.ICVEEXPEDIENTE,  "
					+ "         TS.INUMEXAMEN,      "
					+ "         TS.ICVESERVICIO,    "
					+ "         MED.CDSCSERVICIO,   "
					+ "         TS.LCONCLUIDO,      "
					+ "         TS.ICVEUSUAPLICA    "
					+ "  FROM                       "
					+ "         TSSEGEXAR AS TS,    "
					+ "         MEDSERVICIO MED     "
					+ "  WHERE                      "
					+ "        TS.ICVESERVICIO = MED.ICVESERVICIO AND  "
					+ "        TS.ICVEEXPEDIENTE = " + NumExp + "  AND  "
					+ "        TS.INUMEXAMEN = " + NumExa + "  ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTSSEGEXAR = new TVTSSEGEXAR();
				vTSSEGEXAR.setICveExpediente(rset.getInt("iCveExpediente"));
				vTSSEGEXAR.setINumExamen(rset.getInt("iNumExamen"));
				vTSSEGEXAR.setICveServicio(rset.getInt("iCveServicio"));
				vTSSEGEXAR.setCDscServicio(rset.getString("cDscServicio"));
				vTSSEGEXAR.setLConcluido(rset.getInt("lConcluido"));
				vTSSEGEXAR.setICveUsuAplica(rset.getInt("iCveUsuAplica"));
				vcTSSEGEXAR.addElement(vTSSEGEXAR);
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
		return vcTSSEGEXAR;
	}

	public Vector FindByAll3(String NumExp, String NumExa, String NumUnimed,
			String NumModulo, String idSystem) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTSSEGEXAR = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTSSEGEXAR vTSSEGEXAR;

			String cSQL = "SELECT                                            "
					+ "       DISTINCT TS.ICVESERVICIO,                  "
					+ "       TS.ICVEEXPEDIENTE,                         "
					+ "       TS.INUMEXAMEN,                             "
					+ "       TS.ICVESERVICIO,                           "
					+ "       MED.CDSCSERVICIO,                          "
					+ "       TS.LCONCLUIDO,                             "
					+ "       TS.ICVEUSUAPLICA                           "
					+ "FROM                                              "
					+ "       TSSEGEXAR AS TS,                           "
					+ "       TSEGEXAR AS T,                             "
					+ "       EXPEXAMAPLICA AS EA,                       "
					+ "       GRLUSUMEDICOS GRM,                         "
					+ "       MEDSERVICIO MED                            "
					+ "WHERE                                             "
					+ "      GRM.ICVEUSUARIO = "
					+ idSystem
					+ " AND          "
					+ "      GRM.ICVEUNIMED = EA.ICVEUNIMED  AND         "
					+ "      GRM.ICVEPROCESO = EA.ICVEPROCESO AND        "
					+ "      GRM.ICVEMODULO = EA.ICVEMODULO AND          "
					+ "      GRM.ICVESERVICIO = TS.ICVESERVICIO  AND     "
					+ "      TS.ICVEEXPEDIENTE = EA.ICVEEXPEDIENTE	  AND                "
					+ "      TS.ICVEEXPEDIENTE = T.ICVEEXPEDIENTE 	  AND                "
					+ "      TS.INUMEXAMEN = T.INUMEXAMEN		  AND                "
					+ "      TS.INUMEXAMEN = EA.INUMEXAMEN		  AND                "
					+ "      EA.INUMEXAMEN = T.INUMEXAMEN		  AND                "
					+ "      EA.ICVEUNIMED = "
					+ NumUnimed
					+ "	          AND                "
					+ "      EA.ICVEMODULO = "
					+ NumModulo
					+ "		  AND                "
					+ "      EA.ICVEPROCESO = 1                            AND                "
					+ "      TS.ICVESERVICIO = MED.ICVESERVICIO 	          AND                "
					+ "      TS.LCONCLUIDO = 0   			  AND                "
					+ "      T.ESTATUS = 1				  AND                "
					+ "      TS.ICVEEXPEDIENTE =  "
					+ NumExp
					+ "               AND                "
					+ "      TS.INUMEXAMEN =  " + NumExa + " ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTSSEGEXAR = new TVTSSEGEXAR();
				vTSSEGEXAR.setICveExpediente(rset.getInt("iCveExpediente"));
				vTSSEGEXAR.setINumExamen(rset.getInt("iNumExamen"));
				vTSSEGEXAR.setICveServicio(rset.getInt("iCveServicio"));
				vTSSEGEXAR.setCDscServicio(rset.getString("cDscServicio"));
				vTSSEGEXAR.setLConcluido(rset.getInt("lConcluido"));
				vTSSEGEXAR.setICveUsuAplica(rset.getInt("iCveUsuAplica"));
				vcTSSEGEXAR.addElement(vTSSEGEXAR);
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
		return vcTSSEGEXAR;
	}

	public Vector Ramas(String Servicio, String NumUnimed, String NumModulo,
			String idSystem) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVMEDRama vMEDRama;

			String cSQL = "SELECT  " + "      DISTINCT MER.CDSCRAMA,   "
					+ "      MER.ICVERAMA   " +

					"FROM   " + "      MEDRAMA MER,   "
					+ "      GRLUSUMEDICOS GRM   " +

					"WHERE   "
					+ "      GRM.ICVESERVICIO = MER.ICVESERVICIO	AND   " +

					"      GRM.ICVEUSUARIO = " + idSystem + "		AND   "
					+ "      GRM.ICVEUNIMED = " + NumUnimed + "		AND   "
					+ "      GRM.ICVEPROCESO = 1			AND   "
					+ "      GRM.ICVEMODULO = " + NumModulo
					+ "             AND   " + "      GRM.ICVESERVICIO = "
					+ Servicio + "		AND   " +

					"      MER.LACTIVO = 1		   ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDRama = new TVMEDRama();
				vMEDRama.setCDscRama(rset.getString("CDSCRAMA"));
				vMEDRama.setICveRama(rset.getInt("ICVERAMA"));
				vcMEDRama.addElement(vMEDRama);
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
		return vcMEDRama;
	}

	public String SentenciaSQL(String SentenciaSQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String Regresa = "";
		Vector vcTSSEGEXAR = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			// System.out.println("SentenciaSQL == "+SentenciaSQL);
			String cSQL = SentenciaSQL;

			// System.out.println("cSQL = " + cSQL);

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regresa = rset.getString(1);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("SentenciaSQL");
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
		return Regresa;
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
			Vector vcTSSEGEXAR = new Vector();
			TVTSSEGEXAR vTSSEGEXAR;

			try {
				vcTSSEGEXAR = this.FindByAll(NumExp, NumExa);
			} catch (Exception e) {
				vcTSSEGEXAR = new Vector();
				e.printStackTrace();
			}
			for (int i = 0; i < vcTSSEGEXAR.size(); i++) {
				vTSSEGEXAR = (TVTSSEGEXAR) vcTSSEGEXAR.get(i);
				try {
					// INSERTANDO EN TSSEGEXAR
					String cSQL = "";
					// TVEXPServicio vEXPServicio = (TVEXPServicio) obj;
					conn.setAutoCommit(false);
					conn.setTransactionIsolation(2);
					cSQL = "INSERT INTO TSSEGEXAR  "
							+ "    (ICVEEXPEDIENTE,   "
							+ "    INUMEXAMEN,        "
							+ "    ICVESERVICIO,      "
							+ "    LCONCLUIDO,        "
							+ "    ICVEUSUAPLICA      "
							+ "    )values(?,?,?,?,?)";

					pstmt = conn.prepareStatement(cSQL);
					// System.out.println(cSQL);
					// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE
					// LA TABLA
					pstmt.setInt(1, vTSSEGEXAR.getICveExpediente());
					pstmt.setInt(2, vTSSEGEXAR.getINumExamen());
					pstmt.setInt(3, vTSSEGEXAR.getICveServicio());
					pstmt.setInt(4, vTSSEGEXAR.getLConcluido());
					pstmt.setInt(5, 0);
					// debug

					// System.out.println(vTSSEGEXAR.getICveExpediente());
					// System.out.println(vTSSEGEXAR.getINumExamen());
					// System.out.println(vTSSEGEXAR.getICveServicio());
					// System.out.println(vTSSEGEXAR.getLConcluido());

					iInserted = pstmt.executeUpdate();
					cClave = "" + vTSSEGEXAR.getICveExpediente();
					if (conGral == null) {
						conn.commit();
					}

				} catch (Exception e) {
					vcTSSEGEXAR = new Vector();
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
			TVEXPServicio vEXPServicio = (TVEXPServicio) obj;
			// log("servicio recibido para actualizar: " +
			// vEXPServicio.toHashMap().toString());
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TSSEGEXAR" + " set lConcluido= ?, "
					+ " iCveUsuAplica= ? " + " where iCveExpediente = ? "
					+ " and iNumExamen = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPServicio.getLConcluido());
			pstmt.setInt(2, vEXPServicio.getICveUsuAplica());
			pstmt.setInt(3, vEXPServicio.getICveExpediente());
			pstmt.setInt(4, vEXPServicio.getINumExamen());
			pstmt.setInt(5, vEXPServicio.getICveServicio());
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