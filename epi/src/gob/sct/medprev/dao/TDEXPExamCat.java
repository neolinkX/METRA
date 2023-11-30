package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.text.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;
import com.micper.util.TFechas;

/** 
 * <p>
 * Title: Data Acces Object de EXPExamCat DAO
 * </p>   
 * <p>
 * Description: Data Access Object para EXPExamCat
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

public class TDEXPExamCat extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPExamCat() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamCat = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamCat vEXPExamCat;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveMdoTrans," + "iCveCategoria," + "iCveMotivo,"
					+ "lDictamen," + "cRefAlfanum," + "dtMovIngreso,"
					+ "iVigMeses," + "dtInicioVig," + "dtFinVig," + "lDitamem"
					+ " from EXPExamCat " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamCat = new TVEXPExamCat();
				vEXPExamCat.setICveExpediente(rset.getInt(1));
				vEXPExamCat.setINumExamen(rset.getInt(2));
				vEXPExamCat.setICveMdoTrans(rset.getInt(3));
				vEXPExamCat.setICveCategoria(rset.getInt(4));
				vEXPExamCat.setICveMotivo(rset.getInt(5));
				vEXPExamCat.setLDictamen(rset.getInt(6));
				vEXPExamCat.setCRefAlfanum(rset.getString(7));
				vEXPExamCat.setDtMovIngreso(rset.getDate(8));
				vEXPExamCat.setIVigMeses(rset.getInt(9));
				vEXPExamCat.setDtInicioVig(rset.getDate(10));
				vEXPExamCat.setDtFinVig(rset.getDate(11));
				vEXPExamCat.setLDitamem(rset.getInt(12));
				// System.out.println("Dictaminador = "+rset.getInt(12));
				vcEXPExamCat.addElement(vEXPExamCat);
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
			return vcEXPExamCat;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindBy(String cExpediente, String cExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamCat = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamCat vEXPExamCat;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "     EXPExamCat.iCveMdoTrans, EXPExamCat.iCveCategoria, "
					+ "     GRLMdoTrans.cDscMdoTrans, GRLCategoria.cDscBreve, GRLMotivo.cDscMotivo, EXPExamCat.lDictamen "
					+ "  from EXPExamCat "
					+ "   join GRLMdoTrans "
					+ "      on GRLMdoTrans.iCveMdoTrans = EXPExamCat.iCveMdoTrans "
					+ "   join GRLCategoria "
					+ "     on GRLCategoria.iCveMdoTrans = EXPExamCat.iCveMdoTrans "
					+ "    and GRLCategoria.iCveCategoria = EXPExamCat.iCveCategoria "
					+ "   join GRLMotivo "
					+ "     on GRLMotivo.iCveMotivo = EXPExamCat.iCveMotivo "
					+ "    where EXPExamCat.iCveExpediente = " + cExpediente
					+ "         and EXPExamCat.iNumExamen   = " + cExamen;

			pstmt = conn.prepareStatement(cSQL);
			//System.out.println(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamCat = new TVEXPExamCat();

				vEXPExamCat.setICveMdoTrans(rset.getInt(1));
				vEXPExamCat.setICveCategoria(rset.getInt(2));
				vEXPExamCat.setCDscMdoTrans(rset.getString(3));
				vEXPExamCat.setCDscCategoria(rset.getString(4));
				vEXPExamCat.setCDscMotivo(rset.getString(5));
				vEXPExamCat.setLDictamen(rset.getInt(6));

				vcEXPExamCat.addElement(vEXPExamCat);
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
			return vcEXPExamCat;
		}
	}

	/**
	 * Metodo Find ByCat
	 */
	public Vector FindByCat(String cExpediente, String cExamen,
			String cMdoTrans, String cCategoria) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamCat = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamCat vEXPExamCat;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "     EXPExamCat.iCveMdoTrans, EXPExamCat.iCveCategoria, "
					+ "     GRLMdoTrans.cDscMdoTrans, GRLCategoria.cDscBreve, GRLMotivo.cDscMotivo, EXPExamCat.lDictamen "
					+ "  from EXPExamCat "
					+ "   join GRLMdoTrans "
					+ "      on GRLMdoTrans.iCveMdoTrans = EXPExamCat.iCveMdoTrans "
					+ "   join GRLCategoria "
					+ "     on GRLCategoria.iCveMdoTrans = EXPExamCat.iCveMdoTrans "
					+ "    and GRLCategoria.iCveCategoria = EXPExamCat.iCveCategoria "
					+ "   join GRLMotivo "
					+ "     on GRLMotivo.iCveMotivo = EXPExamCat.iCveMotivo "
					+ "    where EXPExamCat.iCveExpediente = " + cExpediente
					+ "         and EXPExamCat.iNumExamen   = " + cExamen
					+ "         and EXPExamCat.iCveMdoTrans = " + cMdoTrans
					+ "         and EXPExamCat.iCveCategoria = " + cCategoria;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamCat = new TVEXPExamCat();

				vEXPExamCat.setICveMdoTrans(rset.getInt(1));
				vEXPExamCat.setICveCategoria(rset.getInt(2));
				vEXPExamCat.setCDscMdoTrans(rset.getString(3));
				vEXPExamCat.setCDscCategoria(rset.getString(4));
				vEXPExamCat.setCDscMotivo(rset.getString(5));
				vEXPExamCat.setLDictamen(rset.getInt(6));

				vcEXPExamCat.addElement(vEXPExamCat);
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
			return vcEXPExamCat;
		}
	}

	/**
	 * Metodo FindByExpedienteExamen
	 */
	public Vector FindByExpedienteExamen(String cExpediente, String cExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamCat = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamCat vEXPExamCat;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EXPExamCat.iCveExpediente,"
					+ "EXPExamCat.iNumExamen,"
					+ "EXPExamCat.iCveMdoTrans,"
					+ "EXPExamCat.iCveCategoria,"
					+ "EXPExamCat.iCveMotivo,"
					+ "EXPExamCat.lDictamen,"
					+ "EXPExamCat.cRefAlfanum,"
					+ "EXPExamCat.dtMovIngreso,"
					+ "EXPExamCat.iVigMeses,"
					+ "EXPExamCat.dtInicioVig,"
					+ "EXPExamCat.dtFinVig,"
					+ "GRLMdoTrans.cDscMdoTrans,"
					+ "GRLCategoria.cDscCategoria, "
					+ "GRLMotivo.cDscMotivo, "
					+ "GRLMotivo.lPago "
					+ "from EXPExamCat "
					+ "inner join GRLMdoTrans ON GRLMdoTrans.iCveMdoTrans = EXPExamCat.iCveMdoTrans "
					+ "inner join GRLCategoria ON GRLCategoria.iCveMdoTrans = EXPExamCat.iCveMdoTrans "
					+ "inner join GRLMotivo ON GRLMotivo.iCveMotivo = EXPExamCat.iCveMotivo "
					+ "and GRLCategoria.iCveCategoria = EXPExamCat.iCveCategoria "
					+ "where EXPExamCat.iCveExpediente = " + cExpediente
					+ " and EXPExamCat.iNumExamen = " + cExamen
					+ " order by EXPExamCat.iCveMdoTrans";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamCat = new TVEXPExamCat();
				vEXPExamCat.setICveExpediente(rset.getInt(1));
				vEXPExamCat.setINumExamen(rset.getInt(2));
				vEXPExamCat.setICveMdoTrans(rset.getInt(3));
				vEXPExamCat.setICveCategoria(rset.getInt(4));
				vEXPExamCat.setICveMotivo(rset.getInt(5));
				vEXPExamCat.setLDictamen(rset.getInt(6));
				vEXPExamCat.setCRefAlfanum(rset.getString(7));
				vEXPExamCat.setDtMovIngreso(rset.getDate(8));
				vEXPExamCat.setIVigMeses(rset.getInt(9));
				vEXPExamCat.setDtInicioVig(rset.getDate(10));
				vEXPExamCat.setDtFinVig(rset.getDate(11));
				vEXPExamCat.setCDscMdoTrans(rset.getString(12));
				vEXPExamCat.setCDscCategoria(rset.getString(13));
				vEXPExamCat.setCDscMotivo(rset.getString(14));
				vEXPExamCat.setLPago(rset.getInt(15));
				vcEXPExamCat.addElement(vEXPExamCat);
			}
		} catch (Exception ex) {
			warn("FindByExpedienteExamen", ex);
			throw new DAOException("FindByExpedienteExamen");
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
				warn("FindByExpedienteExamen.close", ex2);
			}
			return vcEXPExamCat;
		}
	}

	/**
	 * Metodo Insert
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
			TVEXPExamCat vEXPExamCat = (TVEXPExamCat) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPExamCat(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveMdoTrans," + "iCveCategoria,"
					+ "iCveMotivo," + "lDictamen," + "cRefAlfanum,"
					+ "dtMovIngreso," + "iVigMeses," + "dtInicioVig,"
					+ "dtFinVig" + ")values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// System.out.println(cSQL);

			// DEBE DE INGRESAR Cï¿½DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEXPExamCat.getICveExpediente());
			pstmt.setInt(2, vEXPExamCat.getINumExamen());
			pstmt.setInt(3, vEXPExamCat.getICveMdoTrans());
			pstmt.setInt(4, vEXPExamCat.getICveCategoria());
			pstmt.setInt(5, vEXPExamCat.getICveMotivo());
			pstmt.setInt(6, vEXPExamCat.getLDictamen());
			pstmt.setString(7, vEXPExamCat.getCRefAlfanum());
			pstmt.setDate(8, vEXPExamCat.getDtMovIngreso());
			pstmt.setInt(9, vEXPExamCat.getIVigMeses());
			pstmt.setDate(10, vEXPExamCat.getDtInicioVig());
			pstmt.setDate(11, vEXPExamCat.getDtFinVig());
			pstmt.executeUpdate();
			cClave = "" + vEXPExamCat.getICveExpediente();
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
	public int insert(Connection conGral, Vector vcEXPExamCat)
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
			String cSQL = "insert into EXPExamCat ("
					+ "iCveExpediente,iNumExamen,iCveMdoTrans,iCveCategoria,iCveMotivo,"
					+ "lDictamen,cRefAlfanum,dtMovIngreso,iVigMeses,dtInicioVig,dtFinVig, lDitamEm ) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,0)";

			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			for (Enumeration eEXPExamCat = vcEXPExamCat.elements(); eEXPExamCat
					.hasMoreElements();) {
				TVEXPExamCat vEXPExamCat = (TVEXPExamCat) eEXPExamCat
						.nextElement();
				
				System.out.println( vEXPExamCat.getICveExpediente());
				System.out.println( vEXPExamCat.getINumExamen());
				System.out.println( vEXPExamCat.getICveMdoTrans());
				System.out.println( vEXPExamCat.getICveCategoria());
				System.out.println( vEXPExamCat.getICveMotivo());
				System.out.println( vEXPExamCat.getLDictamen());
				System.out.println( vEXPExamCat.getCRefAlfanum());
				System.out.println( vEXPExamCat.getDtMovIngreso());
				System.out.println( vEXPExamCat.getIVigMeses());
				System.out.println( vEXPExamCat.getDtInicioVig());
				System.out.println( vEXPExamCat.getDtFinVig());
				
				
				
				
				
				pstmt.setInt(1, vEXPExamCat.getICveExpediente());
				pstmt.setInt(2, vEXPExamCat.getINumExamen());
				pstmt.setInt(3, vEXPExamCat.getICveMdoTrans());
				pstmt.setInt(4, vEXPExamCat.getICveCategoria());
				pstmt.setInt(5, vEXPExamCat.getICveMotivo());
				pstmt.setInt(6, vEXPExamCat.getLDictamen());
				pstmt.setString(7, vEXPExamCat.getCRefAlfanum());
				pstmt.setDate(8, vEXPExamCat.getDtMovIngreso());
				pstmt.setInt(9, vEXPExamCat.getIVigMeses());
				pstmt.setDate(10, vEXPExamCat.getDtInicioVig());
				pstmt.setDate(11, vEXPExamCat.getDtFinVig());
				iCta += pstmt.executeUpdate();
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
			TVEXPExamCat vEXPExamCat = (TVEXPExamCat) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamCat" + " set iCveMotivo= ?, "
					+ "lDictamen= ?, " + "cRefAlfanum= ?, "
					+ "dtMovIngreso= ?, " + "iVigMeses= ?, "
					+ "dtInicioVig= ?, " + "dtFinVig= ? "
					+ "where iCveExpediente = ? " + "and iNumExamen = ?"
					+ "and iCveMdoTrans = ?" + " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamCat.getICveMotivo());
			pstmt.setInt(2, vEXPExamCat.getLDictamen());
			pstmt.setString(3, vEXPExamCat.getCRefAlfanum());
			pstmt.setDate(4, vEXPExamCat.getDtMovIngreso());
			pstmt.setInt(5, vEXPExamCat.getIVigMeses());
			pstmt.setDate(6, vEXPExamCat.getDtInicioVig());
			pstmt.setDate(7, vEXPExamCat.getDtFinVig());
			pstmt.setInt(8, vEXPExamCat.getICveExpediente());
			pstmt.setInt(9, vEXPExamCat.getINumExamen());
			pstmt.setInt(10, vEXPExamCat.getICveMdoTrans());
			pstmt.setInt(11, vEXPExamCat.getICveCategoria());
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
	 * Metodo updateEMOCat
	 */
	public Object updateEMOCat(Connection conGral, Object obj)
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
			TVEXPExamCat vEXPExamCat = (TVEXPExamCat) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamCat " + "set lDictamen= ? "
					+ "where iCveExpediente = ? " + "and iNumExamen = ? "
					+ "and iCveMdoTrans = ? " + "and iCveCategoria = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamCat.getLDictamen());
			pstmt.setInt(2, vEXPExamCat.getICveExpediente());
			pstmt.setInt(3, vEXPExamCat.getINumExamen());
			pstmt.setInt(4, vEXPExamCat.getICveMdoTrans());
			pstmt.setInt(5, vEXPExamCat.getICveCategoria());
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
				warn("updateEMOCat", ex1);
			}
			warn("updateEMOCat", ex);
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
				warn("updateEMOCat.close", ex2);
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
			TVEXPExamCat vEXPExamCat = (TVEXPExamCat) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPExamCat" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveMdoTrans = ?"
					+ " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamCat.getICveExpediente());
			pstmt.setInt(2, vEXPExamCat.getINumExamen());
			pstmt.setInt(3, vEXPExamCat.getICveMdoTrans());
			pstmt.setInt(4, vEXPExamCat.getICveCategoria());
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
				warn("delete.closeEXPExamCat", ex2);
			}
			return cClave;
		}
	}

	public Vector InfoConstancia(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamCat = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVEXPDictamen VDictamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			// Bï¿½squeda
			cSQL.append(" select EC.iCveExpediente,")
					// 1
					// .append("        PD.cApPaterno || ' ' || PD.cApMaterno || ' ' || PD.cNombre as cNombre, ")
					// // 2
					.append("        PD.cNombre || ' ' || PD.cApPaterno || ' ' || PD.cApMaterno as cNombre, ")
					// 2
					.append("        PD.cRFC, PD.cCURP, PD.cSexo, PD.cExpediente, ")
					// 3,4,5,6
					.append("        EC.lDictamen, EC.dtInicioVig, EC.dtFinVig, ")
					// 7,8,9
					.append("        T.cDscMdoTrans, C.cDscCategoria, MC.cDscMotivo, ")
					// 10,11,12
					.append("        PD.cGpoSang, PD.lRH, ")
					// 13,14
					.append("        PD.lUsaLentes, PD.lAereo, PD.lContacto, ")
					// 15,16,17
					.append("        PD.lDiabetes, PD.lHipertension, ")
					// 18,19
					.append("        U.cApPaterno || ' ' || U.cApMaterno || ', ' || U.cNombre as cDictamen, ")
					// 20
					.append("        UM.cDscUniMed, M.cNombre, E.cNombre, P.cNombre, ")
					// 21,22,23,24
					.append("        EC.dtDictaminado, ")
					// 25
					.append("        D.cCalle, D.cNumExt, D.cNumInt, D.cColonia, ")
					// 26,27,28,29
					.append("        D.iCP, MD.cNombre, ED.cNombre, PDP.cNombre, ")
					// 30,31,32,33
					.append("        PR.cDscProceso, ")
					// 34
					.append("        EA.dtSolicitado, ")
					// 35
					.append("        IC.cCedula, ")
					// 36
					.append("        EC.lDitamEm, ")
					// 37
					.append("        EC.iCveMdoTrans, ")
					// 38
					.append("        GM.cDscModulo,   PD.DtNacimiento, ")
					// 39, 40
					.append("        C.cDscCategoria_Ingles,    T.cDscMdoTrans_Ingles ")
					// 41, 42
					.append(" from EXPExamCat EC ")
					.append(" inner join PERDatos  PD on PD.iCveExpediente = EC.iCveExpediente ")
					.append(" inner join EXPExamAplica EA on EA.iCveExpediente = EC.iCveExpediente ")
					.append("                             and EA.iNumExamen = EC.iNumExamen ")
					.append(" inner join GRLMdoTrans   T  on T.iCveMdoTrans =  EC.iCveMdoTrans ")
					.append(" inner join GRLCategoria  C  on C.iCveMdoTrans  =  EC.iCveMdoTrans ")
					.append("                             and C.iCveCategoria = EC.iCveCategoria ")
					.append(" inner join GRLProceso  PR on PR.iCveProceso = EA.iCveProceso ")
					.append(" inner join GRLMotivo     MC on MC.iCveMotivo  = EC.iCveMotivo ")
					.append(" left join SEGUsuario    U  on U.iCveUsuario   = EC.lDitamEm ")
					.append(" inner join GRLUniMed     UM on UM.iCveUniMed   = EA.iCveUniMed ")
					.append(" inner join GRLPais       P  on P.iCvePais      = UM.iCvePais ")
					.append(" inner join GRLEntidadFed   E   on E.iCvePais    = UM.iCvePais ")
					.append("                            and E.iCveEntidadFed = UM.iCveEstado ")
					.append(" inner join GRLMunicipio  M  on M.iCvePais       = UM.iCvePais ")
					.append("                            and M.iCveEntidadFed = UM.iCveEstado ")
					.append("                            and M.iCveMunicipio  = UM.iCveMunicipio ")
					.append(" inner join PERDireccion  D on D.iCvePersonal  = PD.iCvePersonal ")
					// .append("                           and D.iCveDireccion = PD.iCveDireccion ")
					.append("                           and D.iCveDireccion = (Select max (pn.icvedireccion) from perdireccion as pn where pn.icvepersonal = PD.iCvePersonal) ")
					.append(" inner join GRLPais       PDP  on PDP.iCvePais  = D.iCvePais ")
					.append(" inner join GRLEntidadFed  ED  on ED.iCvePais     = D.iCvePais ")
					.append("                            and ED.iCveEntidadFed = D.iCveEstado ")
					.append(" left join GRLMunicipio  MD  on MD.iCvePais       = D.iCvePais ")
					.append("                            and MD.iCveEntidadFed = D.iCveEstado ")
					.append("                            and MD.iCveMunicipio  = D.iCveMunicipio ")
					.append(" left join GRLUsuario IC on IC.iCveUsuario = U.iCveUsuario ")
					.append(" left join GRLModulo GM on GM.iCveUniMed = EA.iCveUniMed ")
					.append("                       and GM.iCveModulo = EA.iCveModulo ")
					.append(cFiltro);

			// System.out.println("SQL: " + cSQL);

			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				// Obtener informaciï¿½n del vector
				VDictamen = new TVEXPDictamen();
				VDictamen.VExamCat.setICveExpediente(rset.getInt(1));
				VDictamen.VPerDatos.setCNombre(rset.getString(2));
				VDictamen.VPerDatos.setCRFC(rset.getString(3));
				VDictamen.VPerDatos.setCCURP(rset.getString(4));
				VDictamen.VPerDatos.setCSexo(rset.getString(5));
				VDictamen.VPerDatos.setCExpediente(rset.getString(6));
				VDictamen.VExamCat.setLDictamen(rset.getInt(7));
				VDictamen.VExamCat.setDtInicioVig(rset.getDate(8));
				VDictamen.VExamCat.setDtFinVig(rset.getDate(9));
				VDictamen.VExamCat.setCDscMdoTrans(rset.getString(10));
				VDictamen.VExamCat.setCDscCategoria(rset.getString(11));
				VDictamen.VExamCat.setCDscMotivo(rset.getString(12));
				VDictamen.VPerDatos.setCGpoSang(rset.getString(13));
				VDictamen.VPerDatos.setLRH(rset.getInt(14));
				VDictamen.VPerDatos.setLUsaLentes(rset.getInt(15));
				VDictamen.VPerDatos.setLAereo(rset.getInt(16));
				VDictamen.VPerDatos.setLContacto(rset.getInt(17));
				VDictamen.VPerDatos.setLDiabetes(rset.getInt(18));
				VDictamen.VPerDatos.setLHipertension(rset.getInt(19));
				VDictamen.setCDictaminador(rset.getString(20));
				VDictamen.VUniMed.setCDscUniMed(rset.getString(21));
				VDictamen.VUniMed.setCDscUMMunicipio(rset.getString(22));
				VDictamen.VUniMed.setCDscUMEstado(rset.getString(23));
				VDictamen.VUniMed.setCDscUMPais(rset.getString(24));
				VDictamen.setDtDictamen(rset.getDate(25));
				VDictamen.VPerDatos.setCCalle(rset.getString(26) == null ? ""
						: rset.getString(26));
				VDictamen.VPerDatos.setCNumExt(rset.getString(27) == null ? ""
						: rset.getString(27));
				VDictamen.VPerDatos.setCNumInt(rset.getString(28) == null ? ""
						: rset.getString(28));
				VDictamen.VPerDatos.setCColonia(rset.getString(29));
				VDictamen.VPerDatos.setICP(rset.getInt(30));
				VDictamen.VPerDatos.setCDscMunicipio(rset.getString(31));
				VDictamen.VPerDatos.setCDscEstado(rset.getString(32));
				VDictamen.VPerDatos.setCDscPais(rset.getString(33));
				VDictamen.setCDscProceso(rset.getString(34));
				VDictamen.setDtSolicitado(rset.getDate(35));
				VDictamen.vUsuario.setCCedula(rset.getString(36));
				VDictamen.vUsuario.setICveUsuario(rset.getInt(37));
				VDictamen.VExamCat.setICveMdoTrans(rset.getInt(38));
				VDictamen.vModulo.setCDscModulo(rset.getString(39));
				VDictamen.VPerDatos.setCDscFecNacimiento(rset.getString(40));
				VDictamen.VExamCat.setCDscCategoriaIng(rset.getString(41));
				VDictamen.VExamCat.setCDscMdoTransIng(rset.getString(42));

				vcEXPExamCat.addElement(VDictamen);
			}
		} catch (Exception ex) {
			warn("InfoConstancia", ex);
			throw new DAOException("InfoConstancia");
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
				warn("InfoConstancia.close", ex2);
			}
			return vcEXPExamCat;
		}
	}

	public Vector FindForFree(String cCveExpediente, String cNumExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vRegresa = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamCat tvEXPExamCat;

			// Bï¿½squeda
			cSQL = " SELECT ec.*, mt.cDscMdoTrans, cat.cDscCategoria, m.cDscMotivo "
					+ " FROM EXPExamCat ec "
					+ " JOIN GRLMdoTrans mt ON mt.iCveMdoTrans = ec.iCveMdoTrans "
					+ " JOIN GRLCategoria cat ON cat.iCveMdoTrans = ec.iCveMdoTrans "
					+ " AND cat.iCveCategoria = ec.iCveCategoria "
					+ " JOIN EXPExamAplica ea ON ea.iCveExpediente = ec.iCveExpediente "
					+ " AND ea.iNumExamen = ec.iNumExamen "
					+ " JOIN GRLMotivo m ON m.iCveProceso = ea.iCveProceso "
					+ " AND m.iCveMotivo = ec.iCveMotivo "
					+ " WHERE ec.iCveExpediente = "
					+ cCveExpediente
					+ " AND ec.iNumExamen = " + cNumExamen;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				// Obtener informaciï¿½n del vector
				tvEXPExamCat = new TVEXPExamCat();
				tvEXPExamCat.setLDictamen(rset.getInt("lDictamen"));
				tvEXPExamCat.setCDscMdoTrans(rset.getString("cDscMdoTrans"));
				tvEXPExamCat.setCDscCategoria(rset.getString("cDscCategoria"));
				tvEXPExamCat.setCDscMotivo(rset.getString("cDscMotivo"));
				vRegresa.addElement(tvEXPExamCat);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("InfoConstancia.close", ex2);
			}
			return vRegresa;
		}
	}

	public boolean FreeService(int iCveExpediente, int iNumExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		int iUpdate = 0;
		boolean bReturn = false;
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL = "";

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " UPDATE EXPExamCat SET lDictamen = 0, lDitamEm = 0, dtDictaminado = null "
					+ " WHERE iCveExpediente = "
					+ iCveExpediente
					+ " AND iNumExamen = " + iNumExamen;

			stmt = conn.createStatement();
			iUpdate = stmt.executeUpdate(cSQL);

			if (stmt != null) {
				stmt.close();
			}

			cSQL = " UPDATE EXPExamAplica SET lDictaminado = 0 "
					+ " WHERE iCveExpediente = " + iCveExpediente
					+ " AND iNumExamen = " + iNumExamen;

			stmt = conn.createStatement();
			iUpdate = stmt.executeUpdate(cSQL);

			if (iUpdate != 0) {
				conn.commit();
				bReturn = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			bReturn = false;
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
			}
		}
		return bReturn;
	}

	public Vector freeExam(String cCveExpediente, String cNumExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vRegresa = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPExamCat tvEXPExamCat;

			// Bï¿½squeda
			String cSQL = " SELECT ec.*, mt.cDscMdoTrans, cat.cDscCategoria, m.cDscMotivo, "
					+ " ep.iCvePuesto "
					+ " FROM EXPExamCat ec "
					+ " JOIN GRLMdoTrans mt ON mt.iCveMdoTrans = ec.iCveMdoTrans "
					+ " JOIN GRLCategoria cat ON cat.iCveMdoTrans = ec.iCveMdoTrans "
					+ " AND cat.iCveCategoria = ec.iCveCategoria "
					+ " JOIN GRLMotivo m ON m.iCveMotivo = ec.iCveMotivo "
					+ " JOIN EXPExamPuesto ep ON ep.iCveExpediente = ec.iCveExpediente "
					+ " AND  ep.iNumExamen = ec.iNumExamen "
					+ " AND  ep.iCveMdoTrans = ec.iCveMdoTrans "
					+ " JOIN GRLPuesto p ON p.iCveMdoTrans = ec.iCveMdoTrans "
					+ " AND p.iCvePuesto = ep.iCvePuesto "
					+ " AND p.iCveCategoria = ec.iCveCategoria "
					+ " WHERE ec.iCveExpediente = "
					+ cCveExpediente
					+ " AND ec.iNumExamen = "
					+ cNumExamen
					+ " ORDER BY cDscMdoTrans, cDscCategoria, cDscMotivo ";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				// Obtener informaciï¿½n del vector
				tvEXPExamCat = new TVEXPExamCat();
				tvEXPExamCat.setLDictamen(rset.getInt("lDictamen"));
				tvEXPExamCat.setCDscMdoTrans(rset.getString("cDscMdoTrans"));
				tvEXPExamCat.setCDscCategoria(rset.getString("cDscCategoria"));
				tvEXPExamCat.setCDscMotivo(rset.getString("cDscMotivo"));
				tvEXPExamCat.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				tvEXPExamCat.setICveCategoria(rset.getInt("iCveCategoria"));
				tvEXPExamCat.setICveMotivo(rset.getInt("iCveMotivo"));
				tvEXPExamCat.setICvePuesto(rset.getInt("iCvePuesto"));
				vRegresa.addElement(tvEXPExamCat);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
			}
			return vRegresa;
		}
	}

	// Cambio de trasporte/categorias/puesto/motivo
	// en las tablas EXPEXAMGRUPO/ EXPEXAMPUESTO/EXPEXAMCAT/EXPDictamenServ
	// AG SA SANDOVAL 20 DE ENERO 2011
	public int UpdateExam(TVEXPExamCat tvExamCat, String User,
			ExpBitMod bitacora) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iUpdate = 0;
		boolean bReturn = false;
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " UPDATE EXPExamCat SET "
					+
					// " iCveExpediente = ?, "+
					// " iNumExamen = ?, "+
					" iCveMdoTrans = ?, " + " iCveCategoria = ?, "
					+ " iCveMotivo = ? " + " WHERE iCveExpediente = ? "
					+ " AND iNumExamen = ? " + " AND iCveMdoTrans = ? "
					+ " AND iCveCategoria = ? ";

			pstmt = conn.prepareStatement(cSQL);
			// pstmt.setInt(1, tvExamCat.getICveExpediente());
			// pstmt.setInt(2, tvExamCat.getINumExamen());
			pstmt.setInt(1, tvExamCat.getICveMdoTrans());
			pstmt.setInt(2, tvExamCat.getICveCategoria());
			pstmt.setInt(3, tvExamCat.getICveMotivo());
			pstmt.setInt(4, tvExamCat.getICveExpediente());
			pstmt.setInt(5, tvExamCat.getINumExamen());
			pstmt.setInt(6, tvExamCat.getICveMdoTransInicial());
			pstmt.setInt(7, tvExamCat.getICveCategoriaInicial());

			iUpdate = pstmt.executeUpdate();
			// System.out.println("iUpdate1: "+iUpdate);

			if (pstmt != null) {
				pstmt.close();
			}

			cSQL = " UPDATE EXPExamPuesto SET "
					+
					// " iCveExpediente = ?, "+
					// " iNumExamen = ?, "+
					" iCveMdoTrans = ?, " + " iCvePuesto = ? "
					+ " WHERE iCveExpediente = ? " + " AND iNumExamen = ? "
					+ " AND iCveMdoTrans = ? " + " AND iCvePuesto = ? ";

			pstmt = conn.prepareStatement(cSQL);
			// pstmt.setInt(1, tvExamCat.getICveExpediente());
			// pstmt.setInt(2, tvExamCat.getINumExamen());
			pstmt.setInt(1, tvExamCat.getICveMdoTrans());
			pstmt.setInt(2, tvExamCat.getICvePuesto());
			pstmt.setInt(3, tvExamCat.getICveExpediente());
			pstmt.setInt(4, tvExamCat.getINumExamen());
			pstmt.setInt(5, tvExamCat.getICveMdoTransInicial());
			pstmt.setInt(6, tvExamCat.getICvePuestoInicial());
			iUpdate += pstmt.executeUpdate();

			// System.out.println("iUpdate2: "+iUpdate);

			// /Se agregaron las siguientes 2 sentencias
			// AG SA 10 de enero 2010
			cSQL = " UPDATE EXPDictamenServ SET "
					+
					// " iCveExpediente = ?, "+
					// " iNumExamen = ?, "+
					" iCveMdoTrans = ?, " + " iCveCategoria = ? "
					+ " WHERE iCveExpediente = ? " + " AND iNumExamen = ? "
					+ " AND iCveMdoTrans = ? " + " AND iCveCategoria = ?";

			pstmt = conn.prepareStatement(cSQL);
			// pstmt.setInt(1, tvExamCat.getICveExpediente());
			// pstmt.setInt(2, tvExamCat.getINumExamen());
			pstmt.setInt(1, tvExamCat.getICveMdoTrans());
			pstmt.setInt(2, tvExamCat.getICveCategoria());
			pstmt.setInt(3, tvExamCat.getICveExpediente());
			pstmt.setInt(4, tvExamCat.getINumExamen());
			pstmt.setInt(5, tvExamCat.getICveMdoTransInicial());
			pstmt.setInt(6, tvExamCat.getICveCategoriaInicial());
			// pstmt.setInt(6, tvExamCat.getICvePuestoInicial());
			iUpdate += pstmt.executeUpdate();
			// // System.out.println("iUpdate2: "+iUpdate);

			cSQL = " UPDATE EXPExamGrupo SET "
					+
					// " iCveExpediente = ?, "+
					// " iNumExamen = ?, "+
					" iCveMdoTrans = ? "
					+
					// " iCveGrupo = ? "+
					" WHERE iCveExpediente = ? " + " AND iNumExamen = ? "
					+ " AND iCveMdoTrans = ? ";
			// " AND iCvePuesto = ? "

			pstmt = conn.prepareStatement(cSQL);
			// pstmt.setInt(1, tvExamCat.getICveExpediente());
			// pstmt.setInt(2, tvExamCat.getINumExamen());
			pstmt.setInt(1, tvExamCat.getICveMdoTrans());
			// pstmt.setInt(2, tvExamCat.getICvePuesto());
			pstmt.setInt(2, tvExamCat.getICveExpediente());
			pstmt.setInt(3, tvExamCat.getINumExamen());
			pstmt.setInt(4, tvExamCat.getICveMdoTransInicial());
			// pstmt.setInt(5, tvExamCat.getICvePuestoInicial());
			// System.out.println(cSQL);
			iUpdate += pstmt.executeUpdate();

			// Guardando el registro en bitacora de cambios
			cSQL = "insert into EXPBITMOD "
					+ "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, CMACADDRESS, CCOMPUTERNAME, CIPACCESO)"
					+ "values(?, ?, 1, ?, ?, ?, ?, ?, ?)";

			// Calculando Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			// System.out.println("sql.Timestamp: "+sqlTimestamp);
			// GENERANDO DESCRIPCIÃ“N
			String Descripcion = "Información contenida antes de la modificación "
					+ "ClaveTransporte = "
					+ tvExamCat.getICveMdoTransInicial()
					+ " ClaveCategora = "
					+ tvExamCat.getICveCategoriaInicial()
					+ " ClavePuesto = "
					+ tvExamCat.getICvePuestoInicial()
					+ " ClaveMotivo = " + tvExamCat.getICveMotivoInicial();

			// System.out.println("User = "+User);
			int usuario = Integer.parseInt(User);

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, tvExamCat.getICveExpediente());
			pstmt.setInt(2, tvExamCat.getINumExamen());
			pstmt.setTimestamp(3, sqlTimestamp);
			pstmt.setString(4, Descripcion);
			pstmt.setInt(5, usuario);
			pstmt.setString(6, bitacora.getMacAddress());
			pstmt.setString(7, bitacora.getComputerName());
			pstmt.setString(8, bitacora.getIpAddress());
			iUpdate += pstmt.executeUpdate();
			/*
			 * // System.out.println(
			 * "////////////////////     VALIDANDO FICHEROS     //////////////////////"
			 * ); String sFichero = "C:\\img\\img.jpg"; File fichero = new
			 * File(sFichero); if (fichero.exists()) //
			 * System.out.println("El fichero " + sFichero + " existe"); else //
			 * System.out.println("Pues va a ser que no");
			 */

			// System.out.println("iUpdate2: "+iUpdate);

			if (iUpdate != 0) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			iUpdate = 0;
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
			}
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
			} catch (Exception ex2) {
			}
		}
		return iUpdate;
	}

	public Object updateRef(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		TFechas tFecha = new TFechas();
		Calendar calendar = new GregorianCalendar();
		String tMovIngreso = "";

		DecimalFormat hrFmt = new DecimalFormat("00");

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";

			Vector vcEXPExamCat = new Vector();
			vcEXPExamCat = (Vector) obj;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "update EXPExamCat" + " set cRefAlfanum = ?, "
					+ " iRefNumerica= ?, " + " dtMovIngreso = ?, "
					+ " tMovIngreso = {FN CURTIME()} "
					+ " where iCveExpediente = ? " + " and iNumExamen = ? "
					+ " and iCveMdoTrans = ? " + " and iCveCategoria = ? ";

			for (int i = 0; i < vcEXPExamCat.size(); i++) {

				TVEXPExamCat vEXPExamCat = (TVEXPExamCat) vcEXPExamCat.get(i);
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setString(1, vEXPExamCat.getCRefAlfanum());
				pstmt.setInt(2, vEXPExamCat.getIRefNumerica());
				pstmt.setDate(3, tFecha.TodaySQL());
				pstmt.setInt(4, vEXPExamCat.getICveExpediente());
				pstmt.setInt(5, vEXPExamCat.getINumExamen());
				pstmt.setInt(6, vEXPExamCat.getICveMdoTrans());
				pstmt.setInt(7, vEXPExamCat.getICveCategoria());
				pstmt.executeUpdate();
			}
			Calendar.getInstance();

			tMovIngreso = ""
					+ hrFmt.format(new Double(calendar
							.get(Calendar.HOUR_OF_DAY))) + ":"
					+ hrFmt.format(new Double(calendar.get(Calendar.MINUTE)))
					+ ":"
					+ hrFmt.format(new Double(calendar.get(Calendar.SECOND)));
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
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
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return tMovIngreso;
		}
	}

	// Obtener si se hizo una revaloracion
	public String FindByRevaloracion(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String vRegresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamCat tvEXPExamCat;

			// Bï¿½squeda
			cSQL = "SELECT " + "          EA.DTSOLICITADO "
					+ " FROM 	EXPEXAMAPLICA AS EA, EXPEXAMCAT AS EC " + cWhere;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				// Obtener informaciï¿½n del vector
				vRegresa = rset.getString(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("InfoConstancia.close", ex2);
			}
			return vRegresa;
		}
	}

	//
	public Vector FindByDictamen(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vRegresa = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVDICTAMEN tvDICTAMEN;

			// Bï¿½squeda
			String cSQL = " SELECT "
					+ "   EA.DTSOLICITADO, "
					+ "   GRLP.CDSCPROCESO, "
					+ "   EA.ICVEPROCESO, "
					+ "   GRLM.CDSCMOTIVO, "
					+ "   EA.INUMEXAMEN, "
					+ "   EC.LDICTAMEN "
					+ "  FROM	"
					+ "  EXPEXAMAPLICA AS EA, EXPEXAMCAT AS EC, GRLMOTIVO AS GRLM, GRLPROCESO AS GRLP "
					+ cWhere;
			// System.out.println(cSQL);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				// Obtener informaciï¿½n del vector
				tvDICTAMEN = new TVDICTAMEN();
				tvDICTAMEN.setDTSolicitado(rset.getDate("DTSOLICITADO"));
				tvDICTAMEN.setCDSProceso(rset.getString("CDSCPROCESO"));
				tvDICTAMEN.setICveProceso(rset.getInt("ICVEPROCESO"));
				tvDICTAMEN.setCDscMotivo(rset.getString("CDSCMOTIVO"));
				tvDICTAMEN.setLDictamen(rset.getInt("LDICTAMEN"));
				vRegresa.addElement(tvDICTAMEN);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
			}
			return vRegresa;
		}
	}

	/**
	 * Metodo FindByExpedienteExamen
	 */
	public String SentenciaSQL(String Sentencia) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamCat = new Vector();
		String Regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamCat vEXPExamCat;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = Sentencia;
			System.out.println(Sentencia);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regresa = rset.getString(1);
			}
			//System.out.println(Sentencia);
			System.out.println(Regresa);
		} catch (Exception ex) {
			warn("FindByExpedienteExamen", ex);
			throw new DAOException("FindByExpedienteExamen");
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
				warn("FindByExpedienteExamen.close", ex2);
			}
			return Regresa;
		}
	}

	/*
	 * Metodo Find By All
	 */
	public int FindByInt(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		int resultado = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = cWhere;
			System.out.println(cWhere);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				resultado = rset.getInt(1);
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
			return resultado;
		}
	}

}
