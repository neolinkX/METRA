package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EXPExamGenera DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPExamAplica
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */

public class TDEXPExamGenera extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPExamGenera() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamGenera = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamGenera vEXPExamGenera;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iExamenGenera," + "iCveProceso," + "iNumExamenGenera,"
					+ "dtProgramado," + "dtPosibleAten," + "dtAplicacion,"
					+ "lAplicado"
					+ " from EXPExamGenera order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamGenera = new TVEXPExamGenera();
				vEXPExamGenera.setICveExpediente(rset.getInt(1));
				vEXPExamGenera.setINumExamen(rset.getInt(2));
				vEXPExamGenera.setIExamenGenera(rset.getInt(3));
				vEXPExamGenera.setICveProceso(rset.getInt(4));
				vEXPExamGenera.setINumExamenGenera(rset.getInt(5));
				vEXPExamGenera.setDtProgramado(rset.getDate(6));
				vEXPExamGenera.setDtPosibleAten(rset.getDate(7));
				vEXPExamGenera.setDtAplicacion(rset.getDate(8));
				vEXPExamGenera.setLAplicado(rset.getInt(9));
				vcEXPExamGenera.addElement(vEXPExamGenera);
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
			return vcEXPExamGenera;
		}
	}

	/**
	 * Metodo Insert
	 */
	public int insert(Connection conGral, TVEXPExamGenera vEXPExamGenera)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

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

			// ////////////////////////////////////////

			String cSQLMax = "Select max(iExamenGenera) from EXPExamGenera "
					+ " where iCveExpediente =  "
					+ vEXPExamGenera.getICveExpediente() + " and iNumExamen = "
					+ vEXPExamGenera.getINumExamen();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1) + 1;
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			String cSQL = "insert into EXPExamGenera("
					+ "iCveExpediente,iNumExamen,iExamenGenera,iCveProceso,iNumExamenGenera,"
					+ "dtProgramado,dtPosibleAten,dtAplicacion,lAplicado) "
					+ "values (?,?,?,?,?,?,?,?,?)";

			/*
			 * // System.out.println("ICveExpediente  "+vEXPExamGenera.
			 * getICveExpediente ()); //
			 * System.out.println("INumExamen      "+vEXPExamGenera
			 * .getINumExamen ()); //
			 * System.out.println("IExamenGenera   "+vEXPExamGenera
			 * .getIExamenGenera ()); //
			 * System.out.println("ICveProceso     "+vEXPExamGenera
			 * .getICveProceso ()); //
			 * System.out.println("INumExamenGenera"+vEXPExamGenera
			 * .getINumExamenGenera()); //
			 * System.out.println("DtProgramado    "+
			 * vEXPExamGenera.getDtProgramado ()); //
			 * System.out.println("DtPosibleAten   "
			 * +vEXPExamGenera.getDtPosibleAten ()); //
			 * System.out.println("DtAplicacion    "
			 * +vEXPExamGenera.getDtAplicacion ()); //
			 * System.out.println("LAplicado       "+vEXPExamGenera.getLAplicado
			 * ());
			 */
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamGenera.getICveExpediente());
			pstmt.setInt(2, vEXPExamGenera.getINumExamen());
			pstmt.setInt(3, iMax);
			pstmt.setInt(4, vEXPExamGenera.getICveProceso());
			pstmt.setInt(5, vEXPExamGenera.getINumExamenGenera());
			pstmt.setDate(6, vEXPExamGenera.getDtProgramado());
			pstmt.setDate(7, vEXPExamGenera.getDtPosibleAten());
			pstmt.setDate(8, vEXPExamGenera.getDtAplicacion());
			pstmt.setInt(9, vEXPExamGenera.getLAplicado());
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
	 * Metodo InsertObj Author Javier Mendoza NO BORRAR
	 */
	public int insertObj(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		TVEXPExamGenera vEXPExamGenera = (TVEXPExamGenera) obj;
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

			// ////////////////////////////////////////

			String cSQLMax = "Select max(iExamenGenera) from EXPExamGenera "
					+ " where iCveExpediente =  "
					+ vEXPExamGenera.getICveExpediente() + " and iNumExamen = "
					+ vEXPExamGenera.getINumExamen();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1) + 1;
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			String cSQL = "insert into EXPExamGenera("
					+ "iCveExpediente,iNumExamen,iExamenGenera,iCveProceso,iNumExamenGenera,"
					+ "dtProgramado,dtPosibleAten,dtAplicacion,lAplicado) "
					+ "values (?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamGenera.getICveExpediente());
			pstmt.setInt(2, vEXPExamGenera.getINumExamen());
			pstmt.setInt(3, iMax);
			pstmt.setInt(4, vEXPExamGenera.getICveProceso());
			pstmt.setInt(5, vEXPExamGenera.getINumExamenGenera());
			pstmt.setDate(6, vEXPExamGenera.getDtProgramado());
			pstmt.setDate(7, vEXPExamGenera.getDtPosibleAten());
			pstmt.setDate(8, vEXPExamGenera.getDtAplicacion());
			pstmt.setInt(9, vEXPExamGenera.getLAplicado());
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
			TVEXPExamGenera vEXPExamGenera = (TVEXPExamGenera) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamGenera" + " set iCveProceso= ?, "
					+ "iNumExamenGenera= ?, " + "dtProgramado= ?, "
					+ "dtPosibleAten= ?, " + "dtAplicacion= ?, "
					+ "lAplicado= ? " + "where iCveExpediente = ? "
					+ "and iNumExamen = ?" + " and iExamenGenera = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamGenera.getICveProceso());
			pstmt.setInt(2, vEXPExamGenera.getINumExamenGenera());
			pstmt.setDate(3, vEXPExamGenera.getDtProgramado());
			pstmt.setDate(4, vEXPExamGenera.getDtPosibleAten());
			pstmt.setDate(5, vEXPExamGenera.getDtAplicacion());
			pstmt.setInt(6, vEXPExamGenera.getLAplicado());
			pstmt.setInt(7, vEXPExamGenera.getICveExpediente());
			pstmt.setInt(8, vEXPExamGenera.getINumExamen());
			pstmt.setInt(9, vEXPExamGenera.getIExamenGenera());
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
			TVEXPExamGenera vEXPExamGenera = (TVEXPExamGenera) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPExamGenera" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iExamenGenera = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamGenera.getICveExpediente());
			pstmt.setInt(2, vEXPExamGenera.getINumExamen());
			pstmt.setInt(3, vEXPExamGenera.getIExamenGenera());
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
				warn("delete.closeEXPExamGenera", ex2);
			}
			return cClave;
		}
	}
}


