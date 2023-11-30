package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;

/** 
 * <p>
 * Title: Data Acces Object de EXPFUNDictamen DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPDiagnostico
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: INFOTEC
 * </p>
 * 
 * @author AG SA
 * @version 1.0
 */

public class TDEXPFunDictamen extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPFunDictamen() {
	}

	/**
	 * M�todo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDiagnostico = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPDiagnostico vEXPDiagnostico;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveServicio," + "iCveEspecialidad," + "iCveDiagnostico"
					+ " from EXPDiagnostico order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDiagnostico = new TVEXPDiagnostico();
				vEXPDiagnostico.setICveExpediente(rset.getInt(1));
				vEXPDiagnostico.setINumExamen(rset.getInt(2));
				vEXPDiagnostico.setICveServicio(rset.getInt(3));
				vEXPDiagnostico.setICveEspecialidad(rset.getInt(4));
				vEXPDiagnostico.setICveDiagnostico(rset.getInt(5));
				vcEXPDiagnostico.addElement(vEXPDiagnostico);
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
			return vcEXPDiagnostico;
		}
	}

	/**
	 * M�todo Find By All
	 */
	public String FindByAll(String Query) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDiagnostico = new Vector();
		String regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPDiagnostico vEXPDiagnostico;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = Query;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getString(1);
			}
		 System.out.println(Query);
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
			return regresa;
		}
	}

	/**
	 * M�todo Find By All
	 */
	public int FindByAllI(String Query) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDiagnostico = new Vector();
		int regresa = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPDiagnostico vEXPDiagnostico;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = Query;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getInt(1);
			}
			// System.out.println(Query);
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
			return regresa;
		}
	}

	public Object insert(Connection conGral, int ICVEEXPEDIENTE,
			int INUMEXAMEN, int ICVESERVICIO, int LDICTAMENSERV,
			String cFundamentacion) throws DAOException {
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
			// TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Calculando Timestamp para el campo TINIEXA
			// AG SA 25 mayo 2010

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			// System.out.println("sql.Timestamp: "+sqlTimestamp);

			String cSQL = "insert into EXPFUNDICTAMEN("
					+ "iCveExpediente,iNumExamen,iCveServicio,cFundamentacion,lDictamenServ"
					+ ",tIDiagnostico" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// System.out.println(cSQL);

			pstmt.setInt(1, ICVEEXPEDIENTE);
			pstmt.setInt(2, INUMEXAMEN);
			pstmt.setInt(3, ICVESERVICIO);
			pstmt.setString(4, cFundamentacion);
			pstmt.setInt(5, LDICTAMENSERV);
			pstmt.setTimestamp(6, sqlTimestamp);
			pstmt.executeUpdate();
			cClave = "" + INUMEXAMEN;
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
		return cClave;
	}

	/**
	 * Método Delete de Fundamentacion
	 */
	public Object delete(Connection conGral, String cCveExpediente,
			String cNumExamen, String cServicio, String Usuario)
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ExpFunDictamen" + " where iCveExpediente = "
					+ cCveExpediente + " AND iNumExamen = " + cNumExamen
					+ " AND iCveServicio = " + cServicio;
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
				warn("delete.closeExpFunDictamen", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método Delete de Fundamentacion
	 */
	public Object BitMod(Connection conGral, String cCveExpediente,
			String cNumExamen, String cServicio, String Usuario)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iEntidades = 0;
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String ExFund = dEXPExamAplica
					.RegresaS("Select count(icveexpediente) from expfundictamen where iCveExpediente = "
							+ cCveExpediente
							+ " AND iNumExamen = "
							+ cNumExamen);
			if (!ExFund.equals("0")) {
				String FundDic = dEXPExamAplica
						.RegresaS("Select cfundamentacion from expfundictamen where iCveExpediente = "
								+ cCveExpediente
								+ " AND iNumExamen = "
								+ cNumExamen);
				ExpBitMod mod = new ExpBitMod();
				mod.setiCveExpediente(cCveExpediente);
				mod.setiNumExamen(cNumExamen);
				mod.setOperacion("4");// de liberar Servicio
				mod.setDescripcion("Se libero Diagnostico y se elimino fundamentacion = "
						+ FundDic);
				mod.setiCveUsuarioRealiza(Usuario);
				mod.setiCveServicio(cServicio);
				int insert = new TDEXPBITMOD().insertServicios(null, mod);
			}
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
				warn("delete.closeExpFunDictamen", ex2);
			}
			return cClave;
		}
	}

}