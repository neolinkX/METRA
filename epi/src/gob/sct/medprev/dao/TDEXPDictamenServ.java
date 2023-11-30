package gob.sct.medprev.dao; 

import java.sql.*;
import java.util.*;
import java.text.*;

import java.text.DateFormat;
import java.util.Date;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.msc.*;
import gob.sct.medprev.util.reglas.RDiagnosticoVigencia;
import gob.sct.medprev.util.reglas.RImpedirExamenMedico;

import com.micper.seguridad.vo.TVDinRep02;
import com.micper.seguridad.vo.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * <p>
 * Title: Data Acces Object de EXPDictamenServ DAO
 * </p>     
 * <p>   
 * Description: Data Access Object para EXPDictamenServ
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

public class TDEXPDictamenServ extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	protected HttpServletRequest httpReq = null;

	public TDEXPDictamenServ() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveMdoTrans," + "iCveCategoria," + "iCveServicio,"
					+ "lDictamen"
					+ " from EXPDictamenServ order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setICveServicio(rset.getInt(5));
				vEXPDictamenServ.setLDictamen(rset.getInt(6));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
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
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveMdoTrans," + "iCveCategoria," + "iCveServicio,"
					+ "lDictamen" + " from EXPDictamenServ " + cWhere
					+ " order by iCveExpediente";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setICveServicio(rset.getInt(5));
				vEXPDictamenServ.setLDictamen(rset.getInt(6));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
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
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllDes(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select " + "          D.iCveExpediente,"
					+ "          D.iNumExamen," + "          D.iCveMdoTrans,"
					+ "          D.iCveCategoria,"
					+ "          D.iCveServicio," + "          D.lDictamen,"
					+ "	  S.cDscServicio"
					+ " from EXPDictamenServ AS D, MEDSERVICIO AS S" + cWhere
					+ " order by D.iCveExpediente";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setICveServicio(rset.getInt(5));
				vEXPDictamenServ.setLDictamen(rset.getInt(6));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
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
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo Find by Categoria Solicitada
	 */
	public Vector FindByCatSol(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			StringBuffer cSQL = new StringBuffer();
			cSQL.append(
					" SELECT EXPExamCat.iCveExpediente, EXPExamCat.iNumExamen, EXPExamCat.iCveMdoTrans,")
					.append("        EXPExamCat.iCveCategoria, EXPExamCat.iCveMotivo, EXPExamPuesto.iCvePuesto,  ")
					.append("        GRLPuesto.cDscPuesto, GRLGrupo.iCveGrupo, GRLGrupo.cDscGrupo,  ")
					.append("        GRLMdoTrans.cDscMdoTrans, GRLCategoria.cDscCategoria, GRLMotivo.cDscMotivo,")
					.append("        EXPExamCat.dtInicioVig, EXPExamCat.dtFinVig")
					.append(" FROM ExpExamCat  ")
					.append("  inner join GRLCategoria on GRLCategoria.iCveMdoTrans  = EXPExamCat.iCveMdoTrans ")
					.append("                         and GRLCategoria.iCveCategoria = EXPExamCat.iCveCategoria ")
					.append("  inner join GRLMdoTrans  on GRLMdoTrans.iCveMdoTrans   = EXPExamCat.iCveMdoTrans ")
					.append("  inner join GRLMotivo    on GRLMotivo.iCveMotivo       = EXPExamCat.iCveMotivo ")
					.append("  inner join EXPExamPuesto on  ExpExamPuesto.iCveExpediente = ExpExamCat.iCveExpediente ")
					.append("                          and  ExpExamPuesto.inumexamen     = ExpExamCat.inumexamen ")
					.append("                          and  ExpExamPuesto.iCveMdoTrans   = ExpExamCat.iCveMdoTrans ")
					.append("  inner join GRLPuesto on GRLPuesto.iCveMdoTrans  = EXPExamPuesto.iCveMdoTrans ")
					.append("                      and GRLPuesto.iCvePuesto    = EXPExamPuesto.iCvePuesto ")
					.append("                      and GRLPuesto.iCveCategoria = EXPExamCat.iCveCategoria  ")
					.append("  inner join GRLGrupo on GRLGrupo.iCveMdoTrans = GRLPuesto.iCveMdoTrans ")
					.append("                     and GRLGrupo.iCveGrupo    = GRLPuesto.iCveGrupo ")
					.append(" where EXPExamCat.iCveExpediente =")
					.append(vEXPDictamenServ.getICveExpediente())
					.append("   and EXPExamCat.iNumExamen = ")
					.append(vEXPDictamenServ.getINumExamen())
					.append(" order by EXPExamCat.iCveMdoTrans, EXPExamCat.iCveCategoria, EXPExamCat.iCveMotivo");

			pstmt = conn.prepareStatement(cSQL.toString());
			System.out.println("Cat dictamenes = "+cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setICveMotivo(rset.getInt(5));
				vEXPDictamenServ.setICvePuesto(rset.getInt(6));
				vEXPDictamenServ.setCDscPuesto(rset.getString(7));
				vEXPDictamenServ.setICveGrupo(rset.getInt(8));
				vEXPDictamenServ.setCDscGrupo(rset.getString(9));
				vEXPDictamenServ.setCDscMdoTrans(rset.getString(10));
				vEXPDictamenServ.setCDscCategoria(rset.getString(11));
				vEXPDictamenServ.setCDscMotivo(rset.getString(12));
				vEXPDictamenServ.setDtInicioVig(rset.getDate(13));
				vEXPDictamenServ.setDtFinVig(rset.getDate(14));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("FindByCatSol", ex);
			throw new DAOException("FindByCatSol");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}


	/**
	 * Metodo Find by Categoria Solicitada
	 */
	public Vector FindByCatSolDos(String exp, String exa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			StringBuffer cSQL = new StringBuffer();
			cSQL.append(
					" SELECT EXPExamCat.iCveExpediente, EXPExamCat.iNumExamen, EXPExamCat.iCveMdoTrans,")
					.append("        EXPExamCat.iCveCategoria, EXPExamCat.iCveMotivo, EXPExamPuesto.iCvePuesto,  ")
					.append("        GRLPuesto.cDscPuesto, GRLGrupo.iCveGrupo, GRLGrupo.cDscGrupo,  ")
					.append("        GRLMdoTrans.cDscMdoTrans, GRLCategoria.cDscCategoria, GRLMotivo.cDscMotivo,")
					.append("        EXPExamCat.dtInicioVig, EXPExamCat.dtFinVig")
					.append(" FROM ExpExamCat  ")
					.append("  inner join GRLCategoria on GRLCategoria.iCveMdoTrans  = EXPExamCat.iCveMdoTrans ")
					.append("                         and GRLCategoria.iCveCategoria = EXPExamCat.iCveCategoria ")
					.append("  inner join GRLMdoTrans  on GRLMdoTrans.iCveMdoTrans   = EXPExamCat.iCveMdoTrans ")
					.append("  inner join GRLMotivo    on GRLMotivo.iCveMotivo       = EXPExamCat.iCveMotivo ")
					.append("  inner join EXPExamPuesto on  ExpExamPuesto.iCveExpediente = ExpExamCat.iCveExpediente ")
					.append("                          and  ExpExamPuesto.inumexamen     = ExpExamCat.inumexamen ")
					.append("                          and  ExpExamPuesto.iCveMdoTrans   = ExpExamCat.iCveMdoTrans ")
					.append("  inner join GRLPuesto on GRLPuesto.iCveMdoTrans  = EXPExamPuesto.iCveMdoTrans ")
					.append("                      and GRLPuesto.iCvePuesto    = EXPExamPuesto.iCvePuesto ")
					.append("                      and GRLPuesto.iCveCategoria = EXPExamCat.iCveCategoria  ")
					.append("  inner join GRLGrupo on GRLGrupo.iCveMdoTrans = GRLPuesto.iCveMdoTrans ")
					.append("                     and GRLGrupo.iCveGrupo    = GRLPuesto.iCveGrupo ")
					.append(" where EXPExamCat.iCveExpediente =")
					.append(exp)
					.append("   and EXPExamCat.iNumExamen = ")
					.append(exa)
					.append(" order by EXPExamCat.iCveMdoTrans, EXPExamCat.iCveCategoria, EXPExamCat.iCveMotivo");

			System.out.println(cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			System.out.println("Cat dictamenes = "+cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setICveMotivo(rset.getInt(5));
				vEXPDictamenServ.setICvePuesto(rset.getInt(6));
				vEXPDictamenServ.setCDscPuesto(rset.getString(7));
				vEXPDictamenServ.setICveGrupo(rset.getInt(8));
				vEXPDictamenServ.setCDscGrupo(rset.getString(9));
				vEXPDictamenServ.setCDscMdoTrans(rset.getString(10));
				vEXPDictamenServ.setCDscCategoria(rset.getString(11));
				vEXPDictamenServ.setCDscMotivo(rset.getString(12));
				vEXPDictamenServ.setDtInicioVig(rset.getDate(13));
				vEXPDictamenServ.setDtFinVig(rset.getDate(14));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("FindByCatSol", ex);
			throw new DAOException("FindByCatSol");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}
	
	
	/**
	 * Metodo Find by Categoria Solicitada B
	 */
	public Vector FindByCatSolb(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = "Select EXPExamCat.iCveExpediente, "
					+ "       EXPExamCat.iNumExamen, "
					+ "       EXPExamCat.iCveMdoTrans, "
					+ "       EXPExamCat.iCveCategoria, "
					+ "       EXPExamCat.iCveMotivo, "
					+ "       GRLMdoTrans.cDscMdoTrans, "
					+ "       GRLCategoria.cDscCategoria, "
					+ "       GRLMotivo.cDscMotivo "
					+ " From  EXPExamCat "
					+ " join  GRLMdoTrans   on GRLMdoTrans.iCveMdoTrans   = EXPExamCat.iCveMdoTrans "
					+ " join  GRLCategoria  on GRLCategoria.iCveCategoria = EXPExamCat.iCveCategoria "
					+ " and   GRLCategoria.iCveMdoTrans                   = EXPExamCat.iCveMdoTrans "
					+ " join  GRLMotivo     on GRLMotivo.iCveMotivo       = EXPExamCat.iCveMotivo "
					+ " Where EXPExamCat.iCveExpediente = "
					+ vEXPDictamenServ.getICveExpediente()
					+ " And   EXPExamCat.iNumExamen = "
					+ vEXPDictamenServ.getINumExamen()
					+ " Order By  EXPExamCat.iCveExpediente,"
					+ "          EXPExamCat.iNumExamen, "
					+ "          EXPExamCat.iCveMdoTrans";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setICveMotivo(rset.getInt(5));
				vEXPDictamenServ.setCDscMdoTrans(rset.getString(6));
				vEXPDictamenServ.setCDscCategoria(rset.getString(7));
				vEXPDictamenServ.setCDscMotivo(rset.getString(8));

				String cSQL2 = " Select "
						+ " ExpExamGrupo.iCveExpediente,"
						+ " ExpExamGrupo.iNumExamen,"
						+ " ExpExamGrupo.iCveMdoTrans,"
						+ " ExpExamGrupo.iCveGrupo, "
						+ " ExpExamPuesto.iCvePuesto, "
						+ " GRLGrupo.cDscGrupo, "
						+ " GRLPuesto.cDscPuesto "
						+ " From   ExpExamGrupo "
						+ " join   ExpExamPuesto on ExpExamPuesto.iCveExpediente = ExpExamGrupo.iCveExpediente "
						+ " And    ExpExamPuesto.iNumExamen = ExpExamGrupo.iNumExamen "
						+ " And    ExpExamPuesto.iCveMdoTrans = ExpExamGrupo.iCveMdoTrans "
						+ " join   GRLGrupo on GRLGrupo.iCveGrupo = ExpExamGrupo.iCveGrupo "
						+ " And    GRLGrupo.iCveMdoTrans = ExpExamGrupo.iCveMdoTrans "
						+ " join   GRLPuesto on GRLPuesto.iCvePuesto = ExpExamPuesto.iCvePuesto "
						+ " And    GRLPuesto.iCveGrupo = ExpExamGrupo.iCveGrupo "
						+ " And    GRLPuesto.iCveMdoTrans = ExpExamGrupo.iCveMdoTrans "
						+ " And    GRLPuesto.iCveCategoria = "
						+ vEXPDictamenServ.getICveCategoria()
						+ " Where  ExpExamGrupo.iCveExpediente = "
						+ vEXPDictamenServ.getICveExpediente()
						+ " And    ExpExamGrupo.iNumExamen = "
						+ vEXPDictamenServ.getINumExamen()
						+ " And    ExpExamGrupo.iCveMdoTrans = "
						+ vEXPDictamenServ.getICveMdoTrans() + " Order by "
						+ " ExpExamGrupo.iCveExpediente,"
						+ " ExpExamGrupo.iNumExamen,"
						+ " ExpExamGrupo.iCveMdoTrans";
				pstmt2 = conn.prepareStatement(cSQL2);
				rset2 = pstmt2.executeQuery();
				while (rset2.next()) {
					vEXPDictamenServ.setICveGrupo(rset2.getInt(4));
					vEXPDictamenServ.setICvePuesto(rset2.getInt(5));
					vEXPDictamenServ.setCDscGrupo(rset2.getString(6));
					vEXPDictamenServ.setCDscPuesto(rset2.getString(7));
					vcEXPDictamenServ.addElement(vEXPDictamenServ);
				}
			}
		} catch (Exception ex) {
			warn("FindByCatSolb", ex);
			throw new DAOException("FindByCatSolb");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rset2 != null) {
					rset2.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByCatSolb.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo FindListaCat
	 */
	public Vector FindListaCat(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "Select GrlCategoria.iCveCategoria, "
					+ "       GrlCategoria.iCveMdoTrans, GrlCategoria.cDscCategoria "
					+ " From  GrlCategoria "
					+ " Where GrlCategoria.iCveMdoTrans in (Select Distinct iCveMdoTrans "
					+ "                                     From  ExpExamGrupo "
					+ "                                     Where iCveExpediente = "
					+ vEXPDictamenServ.getICveExpediente()
					+ "                                     And   iNumExamen = "
					+ vEXPDictamenServ.getINumExamen()
					+ "                                     Order By iCveMdoTrans) "
					+ " Order By iCveCategoria, iCveMdoTrans ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveCategoria(rset.getInt(1));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(2));
				vEXPDictamenServ.setCDscCategoria(rset.getString(3));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("FindListaCat", ex);
			throw new DAOException("FindListaCat");
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
				warn("FindListaCat.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}
	
	/**
	 * Metodo FindListaCat
	 */
	public Vector FindListaCatDos(String exp, String exa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "Select GrlCategoria.iCveCategoria, "
					+ "       GrlCategoria.iCveMdoTrans, GrlCategoria.cDscCategoria "
					+ " From  GrlCategoria "
					+ " Where GrlCategoria.iCveMdoTrans in (Select Distinct iCveMdoTrans "
					+ "                                     From  ExpExamGrupo "
					+ "                                     Where iCveExpediente = "
					+ exp
					+ "                                     And   iNumExamen = "
					+ exa
					+ "                                     Order By iCveMdoTrans) "
					+ " Order By iCveCategoria, iCveMdoTrans ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveCategoria(rset.getInt(1));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(2));
				vEXPDictamenServ.setCDscCategoria(rset.getString(3));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("FindListaCat", ex);
			throw new DAOException("FindListaCat");
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
				warn("FindListaCat.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo findUserbyExp (Utilizado para la SelecciÃ³n de personal por
	 * Expediente)
	 * 
	 * @Autor: Javier Mendoza
	 */
	public TVPERDatos findUserbyExp(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "select " + "iCvePersonal, " + "iCveExpediente, "
					+ "cNombre,cApPaterno,cApMaterno, "
					+ "cSexo,dtNacimiento,iCveNumEmpresa,cRFC,b.cDscServicio "
					+ "from PerDatos, MEDServicio b "
					+ "where iCveExpediente= "
					+ vEXPDictamenServ.getICveExpediente()
					+ " And   b.iCveServicio= "
					+ vEXPDictamenServ.getICveServicio()
					+ " Order by iCveExpediente ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			if (rset != null) {
				while (rset.next()) {
					vPERDatos = new TVPERDatos();
					vPERDatos.setICvePersonal(rset.getInt("iCvePersonal"));
					vPERDatos.setICveExpediente(rset.getInt("iCveExpediente"));
					vPERDatos.setCNombre(rset.getString("cNombre"));
					vPERDatos.setCApPaterno(rset.getString("cApPaterno"));
					vPERDatos.setCApMaterno(rset.getString("cApMaterno"));
					vPERDatos.setCSexo(rset.getString("cSexo"));
					vPERDatos.setDtNacimiento(rset.getDate("dtNacimiento"));
					vPERDatos.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
					vPERDatos.setCRFC(rset.getString("cRFC"));
					vPERDatos.setCPersonaAviso(rset.getString("cDscServicio"));
				}
			}
		} catch (Exception ex) {
			warn("findUserbyExp", ex);
			throw new DAOException("findUserbyExp");
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
				warn("findUserbyExp.close", ex2);
			}
			return vPERDatos;
		}
	}

	/**
	 * Metodo findUserbyExp (Utilizado para la SelecciÃ³n de personal por
	 * Expediente)
	 * 
	 * @Autor: Javier Mendoza
	 */
	public TVPERDatos findUserbyExpDos(String exp) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "select " + "iCvePersonal, " + "iCveExpediente, "
					+ "cNombre,cApPaterno,cApMaterno, "
					+ "cSexo,dtNacimiento,iCveNumEmpresa,cRFC,b.cDscServicio "
					+ "from PerDatos, MEDServicio b "
					+ "where iCveExpediente= "
					+ exp
					+ " And   b.iCveServicio= "
					+ "31"
					+ " Order by iCveExpediente ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			if (rset != null) {
				while (rset.next()) {
					vPERDatos = new TVPERDatos();
					vPERDatos.setICvePersonal(rset.getInt("iCvePersonal"));
					vPERDatos.setICveExpediente(rset.getInt("iCveExpediente"));
					vPERDatos.setCNombre(rset.getString("cNombre"));
					vPERDatos.setCApPaterno(rset.getString("cApPaterno"));
					vPERDatos.setCApMaterno(rset.getString("cApMaterno"));
					vPERDatos.setCSexo(rset.getString("cSexo"));
					vPERDatos.setDtNacimiento(rset.getDate("dtNacimiento"));
					vPERDatos.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
					vPERDatos.setCRFC(rset.getString("cRFC"));
					vPERDatos.setCPersonaAviso(rset.getString("cDscServicio"));
				}
			}
		} catch (Exception ex) {
			warn("findUserbyExp", ex);
			throw new DAOException("findUserbyExp");
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
				warn("findUserbyExp.close", ex2);
			}
			return vPERDatos;
		}
	}

	
	/**
	 * Metodo findUserExp (Utilizado para la SelecciÃ³n de personal por
	 * Expediente)
	 * 
	 * @Autor: Javier Mendoza
	 */
	public TVPERDatos findUserExp(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "select " + "iCvePersonal, " + "iCveExpediente, "
					+ "cNombre,cApPaterno,cApMaterno, "
					+ "cSexo,dtNacimiento,iCveNumEmpresa,cRFC "
					+ "from PerDatos " + "where iCveExpediente= "
					+ vEXPDictamenServ.getICveExpediente()
					+ " Order by iCveExpediente ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			if (rset != null) {
				while (rset.next()) {
					vPERDatos = new TVPERDatos();
					vPERDatos.setICvePersonal(rset.getInt("iCvePersonal"));
					vPERDatos.setICveExpediente(rset.getInt("iCveExpediente"));
					vPERDatos.setCNombre(rset.getString("cNombre"));
					vPERDatos.setCApPaterno(rset.getString("cApPaterno"));
					vPERDatos.setCApMaterno(rset.getString("cApMaterno"));
					vPERDatos.setCSexo(rset.getString("cSexo"));
					vPERDatos.setDtNacimiento(rset.getDate("dtNacimiento"));
					vPERDatos.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
					vPERDatos.setCRFC(rset.getString("cRFC"));
				}
			}
		} catch (Exception ex) {
			warn("findUserbyExp", ex);
			throw new DAOException("findUserbyExp");
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
				warn("findUserbyExp.close", ex2);
			}
			return vPERDatos;
		}
	}
	

	/**
	 * Metodo findUserExp (Utilizado para la SelecciÃ³n de personal por
	 * Expediente)
	 * 
	 * @Autor: Javier Mendoza
	 */
	public TVPERDatos findUserExpDos(String exp) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "select " + "iCvePersonal, " + "iCveExpediente, "
					+ "cNombre,cApPaterno,cApMaterno, "
					+ "cSexo,dtNacimiento,iCveNumEmpresa,cRFC "
					+ "from PerDatos " + "where iCveExpediente= "
					+ exp
					+ " Order by iCveExpediente ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			if (rset != null) {
				while (rset.next()) {
					vPERDatos = new TVPERDatos();
					vPERDatos.setICvePersonal(rset.getInt("iCvePersonal"));
					vPERDatos.setICveExpediente(rset.getInt("iCveExpediente"));
					vPERDatos.setCNombre(rset.getString("cNombre"));
					vPERDatos.setCApPaterno(rset.getString("cApPaterno"));
					vPERDatos.setCApMaterno(rset.getString("cApMaterno"));
					vPERDatos.setCSexo(rset.getString("cSexo"));
					vPERDatos.setDtNacimiento(rset.getDate("dtNacimiento"));
					vPERDatos.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
					vPERDatos.setCRFC(rset.getString("cRFC"));
				}
			}
		} catch (Exception ex) {
			warn("findUserbyExp", ex);
			throw new DAOException("findUserbyExp");
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
				warn("findUserbyExp.close", ex2);
			}
			return vPERDatos;
		}
	}
	
	

	/**
	 * Metodo InsertDicCat - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla EXPDictamenServ
	 */
	public Object insertDicCat(Connection conGral, Object obj,
			int iCveCategoria, int iCveMdoTrans, int lDictamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPDictamenServ "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " and iCveMdoTrans = " + iCveMdoTrans
					+ " and iCveCategoria = " + iCveCategoria
					+ " and iCveServicio = "
					+ vEXPDictamenServ.getICveServicio();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {
				if(vEXPDictamenServ.getICveServicio() == 31 && vEXPDictamenServ.getICveProceso() ==2){////Actualizar Dictamen de EMO al Actualizar Diagnostico y Recomendaciones del Dictaminador 
					cSQL = "update EXPDictamenServ" + " set lDictamen= ? "
							+ "where iCveExpediente = ? " + "and iNumExamen = ?"
							+ "and iCveMdoTrans = ?" + "and iCveCategoria = ?"
							+ " and iCveServicio in(31,1)";
					pstmt = conn.prepareStatement(cSQL);
					pstmt.setInt(1, lDictamen);
					pstmt.setInt(2, vEXPDictamenServ.getICveExpediente());
					pstmt.setInt(3, vEXPDictamenServ.getINumExamen());
					pstmt.setInt(4, iCveMdoTrans);
					pstmt.setInt(5, iCveCategoria);
					//pstmt.setInt(6, vEXPDictamenServ.getICveServicio());
				}else{
					cSQL = "update EXPDictamenServ" + " set lDictamen= ? "
							+ "where iCveExpediente = ? " + "and iNumExamen = ?"
							+ "and iCveMdoTrans = ?" + "and iCveCategoria = ?"
							+ " and iCveServicio = ?";
					pstmt = conn.prepareStatement(cSQL);
					pstmt.setInt(1, lDictamen);
					pstmt.setInt(2, vEXPDictamenServ.getICveExpediente());
					pstmt.setInt(3, vEXPDictamenServ.getINumExamen());
					pstmt.setInt(4, iCveMdoTrans);
					pstmt.setInt(5, iCveCategoria);
					pstmt.setInt(6, vEXPDictamenServ.getICveServicio());
				}
			} else {
				if(vEXPDictamenServ.getICveServicio() == 31 && vEXPDictamenServ.getICveProceso() ==2){///Se agrega dictamen de EMO cuando se dictamina diagnostico y recomendaciones
					cSQL = "insert into EXPDictamenServ(" + "iCveExpediente,"
							+ "iNumExamen," + "iCveMdoTrans," + "iCveCategoria,"
							+ "iCveServicio," + "lDictamen"
							+ ")values(?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(cSQL);
					pstmt.setInt(1, vEXPDictamenServ.getICveExpediente());
					pstmt.setInt(2, vEXPDictamenServ.getINumExamen());
					pstmt.setInt(3, iCveMdoTrans);
					pstmt.setInt(4, iCveCategoria);
					pstmt.setInt(5, 1);
					pstmt.setInt(6, lDictamen);
					pstmt.executeUpdate();
				}
					
				cSQL = "insert into EXPDictamenServ(" + "iCveExpediente,"
							+ "iNumExamen," + "iCveMdoTrans," + "iCveCategoria,"
							+ "iCveServicio," + "lDictamen"
							+ ")values(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(2, vEXPDictamenServ.getINumExamen());
				pstmt.setInt(3, iCveMdoTrans);
				pstmt.setInt(4, iCveCategoria);
				pstmt.setInt(5, vEXPDictamenServ.getICveServicio());
				pstmt.setInt(6, lDictamen);
				
			}

			pstmt.executeUpdate();

			if (vEXPDictamenServ.getICveServicio() == 45) {
				// System.out.println("Si es toxicologico");
				// System.out.println("Fecha actual de operacion para la vigencia");
				if (lDictamen == 0) {
					java.util.Date fecha = new Date();
					// System.out.println
					// (fecha.getYear()+"-"+fecha.getDay()+"-"+fecha.getMonth());
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd");
					String FechaFV = dateFormat.format(fecha.getTime());
					String FechaFV2 = FechaFV + "/";
					String dia;
					String mes;
					String ano;
					StringTokenizer solDatos = new StringTokenizer(FechaFV2,
							"/");
					dia = solDatos.nextToken();
					mes = solDatos.nextToken();
					ano = solDatos.nextToken();
					FechaFV = "" + dia + "-" + mes + "-" + ano;
					// System.out.println(FechaFV);

					Vector vcEXPExamCat = new Vector();
					TVEXPExamCat vEXPExamCat;
					TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
					TDEXPServicio dEXPServicio = new TDEXPServicio();
					try {
						String cClave2 = " where iCveExpediente = "
								+ vEXPDictamenServ.getICveExpediente()
								+ " and iNumExamen = "
								+ vEXPDictamenServ.getINumExamen();
						vcEXPExamCat = dEXPExamCat.FindByAll(cClave2);
					} catch (Exception ex) {
						warn("Sentencia", ex);
					}

					int dictaminado = 0;
					int dictaminador = 0;
					for (int i = 0; i < vcEXPExamCat.size(); i++) {
						vEXPExamCat = (TVEXPExamCat) vcEXPExamCat.get(i);
						dictaminado = vEXPExamCat.getLDictamen();
						dictaminador = vEXPExamCat.getLDitamem();
					}

					// Guardando histirco en bitacora
					// INSERTANDO EN BITACORA
					// TVUsuario vUsuario = (TVUsuario)
					// httpReq.getSession(true).getAttribute("UsrID");
					String result = "";
					String Servicio = "0";
					// Calculando Timestamp para el campo TINIEXA
					java.util.Date utilDate = new java.util.Date(); // fecha
																	// actual
					long lnMilisegundos = utilDate.getTime();
					java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
							lnMilisegundos);
					// System.out.println("sql.Timestamp: "+sqlTimestamp);
					// Guardando el registro en bitacora de cambios
					String Aptitud = "";
					if (dictaminado == 0)
						Aptitud = "No Apto";
					else
						Aptitud = "Apto";
					String Nota1 = "Se cargo el servicio toxicol�gico cuando este era "
							+ Aptitud
							+ ", el cual fue dictaminado por el usuario "
							+ dictaminador;
					String Descripcion = "Informaci�n contenida en la nota medica antes de la modificacion ---> CNOTA = "
							+ Nota1;
					String cSQL5 = "insert into EXPBITMOD "
							+ "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio)"
							+ "values(" + vEXPDictamenServ.getICveExpediente()
							+ ", " + vEXPDictamenServ.getINumExamen()
							+ ", 8, '" + sqlTimestamp + "', '" + Descripcion
							+ "', " + vEXPDictamenServ.getICveUsuDictamen()
							+ ", " + Servicio + ")";
					// GENERANDO DESCRIPCIÃ?N
					try {
						dEXPServicio.Sentencia(cSQL5);
					} catch (Exception ex) {
						warn("Sentencia", ex);
					}

					// liberando el dictamen de diagnostico y recomendaciones
					// del dictaminador
					String cSQL4 = "update EXPSERVICIO Set lConcluido = 0, CNotaMedica = '' "
							+ " Where iCveExpediente = "
							+ vEXPDictamenServ.getICveExpediente()
							+ "  And iNumExamen ="
							+ vEXPDictamenServ.getINumExamen()
							+ " AND ICVESERVICIO IN (45,44,31)";
					pstmt = conn.prepareStatement(cSQL4);
					pstmt.executeUpdate();

					cSQL4 = " DELETE FROM EXPDICTAMENSERV WHERE iCveExpediente = "
							+ vEXPDictamenServ.getICveExpediente()
							+ " And iNumExamen ="
							+ vEXPDictamenServ.getINumExamen()
							+ " AND ICVESERVICIO = 31";
					pstmt = conn.prepareStatement(cSQL4);
					pstmt.executeUpdate();

					// Actualizando informacion en EXPExamCat
					String cSQL2 = "update EXPExamCat Set lDictamen = 0, dtFinVig='"
							+ FechaFV
							+ "', LDITAMEM = "
							+ vEXPDictamenServ.getICveUsuDictamen()
							+ " Where iCveExpediente = "
							+ vEXPDictamenServ.getICveExpediente()
							+ "  And iNumExamen ="
							+ vEXPDictamenServ.getINumExamen() + " ";
					pstmt = conn.prepareStatement(cSQL2);
					pstmt.executeUpdate();

					// Actualizando informacion en PERLicencia
					String cSQL3 = "update PERLicencia Set lDictamen= 0, dtFinVigencia='"
							+ FechaFV
							+ "' Where iCvePersonal  = "
							+ vEXPDictamenServ.getICveExpediente()
							+ "  And iNumExamen ="
							+ vEXPDictamenServ.getINumExamen() + " ";
					pstmt = conn.prepareStatement(cSQL3);
					pstmt.executeUpdate();
				}
			}

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
				warn("insertDicCat", ex1);
			}
			warn("insertDicCat", ex);
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
				warn("insertDicCat.close", ex2);
			}
			return cClave;
		}
	}

	public Object updateEXPExamAplica(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		java.sql.Date hdDtConcluido;
		TFechas dtFecha = new TFechas();
		hdDtConcluido = dtFecha.TodaySQL();

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPExamAplica "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {

				cSQL = "update EXPExamAplica" + " Set lConcluido= ?, "
						+ "     dtConcluido=?, "
						+ " tConcluido = {FN CURTIME()} "
						+ " Where iCveExpediente = ? " + " And iNumExamen = ? ";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, 1);
				pstmt.setDate(2, hdDtConcluido);
				pstmt.setInt(3, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(4, vEXPDictamenServ.getINumExamen());

			}

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
				warn("updateEXPExamAplica", ex1);
			}
			warn("updateEXPExamAplica", ex);
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
				warn("updateEXPExamAplica.close", ex2);
			}
			return cClave;
		}
	} 

	public Object updateEXPEADictaminado(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		java.sql.Date hdDtDictamen;
		TFechas dtFecha = new TFechas();
		hdDtDictamen = dtFecha.TodaySQL();
		PreparedStatement pstmtExamAplica = null;
		PreparedStatement pstmtPerDatos = null;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPExamAplica "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {
				cSQL = "update EXPExamAplica" + " Set lDictaminado= ?, "
						+ "     dtDictamen=? " + " Where iCveExpediente = ? "
						+ " And   iNumExamen = ?";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, 1);
				pstmt.setDate(2, hdDtDictamen);
				pstmt.setInt(3, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(4, vEXPDictamenServ.getINumExamen());
			}

			pstmt.executeUpdate();
			cClave = "" + vEXPDictamenServ.getICveExpediente();

			cSQL = "update EXPExamAplica" + " Set cGpoSang = ?, "
					+ "     lRH = ?," + "     lUsaLentes = ?,"
					+ "     lHipertension = ?," + "     lDiabetes = ?,"
					+ "     lAereo = ?," + "     lContacto = ?,"
					+ "     iCveUsuDictamen = ? "
					+ " Where iCveExpediente = ? " + " And   iNumExamen = ?";

			pstmtExamAplica = conn.prepareStatement(cSQL);
			pstmtExamAplica.setString(1, vEXPDictamenServ.getCGpoSang());
			pstmtExamAplica.setInt(2, vEXPDictamenServ.getLRH());
			pstmtExamAplica.setInt(3, vEXPDictamenServ.getLUsaLentes());
			pstmtExamAplica.setInt(4, vEXPDictamenServ.getLHipertension());
			pstmtExamAplica.setInt(5, vEXPDictamenServ.getLDiabetes());
			pstmtExamAplica.setInt(6, vEXPDictamenServ.getLAereo());
			pstmtExamAplica.setInt(7, vEXPDictamenServ.getLContacto());
			pstmtExamAplica.setInt(8, vEXPDictamenServ.getICveUsuDictamen());
			pstmtExamAplica.setInt(9, vEXPDictamenServ.getICveExpediente());
			pstmtExamAplica.setInt(10, vEXPDictamenServ.getINumExamen());

			pstmtExamAplica.executeUpdate();

			cSQL = "update PERDatos" + " Set cGpoSang = ?, " + "     lRH = ?,"
					+ "     lUsaLentes = ?," + "     lHipertension = ?,"
					+ "     lDiabetes = ?, " + "     lAereo = ?,"
					+ "     lContacto = ? " + " Where iCveExpediente = ? ";

			pstmtPerDatos = conn.prepareStatement(cSQL);
			pstmtPerDatos.setString(1, vEXPDictamenServ.getCGpoSang());
			pstmtPerDatos.setInt(2, vEXPDictamenServ.getLRH());
			pstmtPerDatos.setInt(3, vEXPDictamenServ.getLUsaLentes());
			pstmtPerDatos.setInt(4, vEXPDictamenServ.getLHipertension());
			pstmtPerDatos.setInt(5, vEXPDictamenServ.getLDiabetes());
			pstmtPerDatos.setInt(6, vEXPDictamenServ.getLAereo());
			pstmtPerDatos.setInt(7, vEXPDictamenServ.getLContacto());
			pstmtPerDatos.setInt(8, vEXPDictamenServ.getICveExpediente());

			pstmtPerDatos.executeUpdate();

			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("updateEXPEADictaminado", ex1);
			}
			warn("updateEXPEADictaminado", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {

					pstmt.close();
				}

				if (pstmtExamAplica != null) {
					pstmtExamAplica.close();
				}

				if (pstmtPerDatos != null) {
					pstmtPerDatos.close();
				}

				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("updateEXPEADictaminado.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo insertExamCat - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla EXPExamCat
	 */
	public Object insertExamCat(Connection conGral, Object obj,
			int iCveCategoria, int iCveMdoTrans, int lDictamen, int iCveMotivo)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtNoAp = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			TDPERCatalogoNoAp dPERCatalogoNoAP = new TDPERCatalogoNoAp();
			TVPERCatalogoNoAp vPERCatalogoNoAP = new TVPERCatalogoNoAp();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPExamCat "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " and iCveMdoTrans = " + iCveMdoTrans
					+ " and iCveCategoria = " + iCveCategoria;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {

				TFechas tFecha = new TFechas();
				java.sql.Date dtFecha = null;
				TVGRLCategoria vGRLCategoria = new TVGRLCategoria();
				Vector vcCategoria = new Vector();
				TDGRLCategoria dGRLCategoria = new TDGRLCategoria();

				vcCategoria = dGRLCategoria.FindByAll(" where iCveMdoTrans = "
						+ iCveMdoTrans + " and iCveCategoria = "
						+ iCveCategoria, "");
				if (vcCategoria.size() > 0) {
					vGRLCategoria = (TVGRLCategoria) vcCategoria.get(0);
				}

				int iAnio;
				String cVencimiento = "";
				String cDia = "";
				String cMes = "";

				TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
				TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
				Vector vcEXPExamAplica = new Vector();
				vcEXPExamAplica = dEXPExamAplica
						.FindByAll(" where iCveExpediente = "
								+ vEXPDictamenServ.getICveExpediente()
								+ " and iNumExamen = "
								+ vEXPDictamenServ.getINumExamen());

				if (vcEXPExamAplica.size() > 0) {
					vEXPExamAplica = (TVEXPExamAplica) vcEXPExamAplica.get(0);

					iAnio = tFecha.getIntYear(vEXPExamAplica.getDtSolicitado());
					iAnio = iAnio + vGRLCategoria.getITmpoReexp();

					// dtFecha = vEXPExamAplica.getDtDictamen();
					dtFecha = vEXPExamAplica.getDtSolicitado();

					if (tFecha.getIntDay(dtFecha) < 10) {
						cDia = "0" + tFecha.getIntDay(dtFecha);
					} else {

						cDia = "" + tFecha.getIntDay(dtFecha);

					}

					if (tFecha.getIntMonth(dtFecha) < 10) {
						cMes = "0" + tFecha.getIntMonth(dtFecha);
					} else {
						cMes = "" + tFecha.getIntMonth(dtFecha);

					}
					cVencimiento = cDia + "/" + cMes + "/" + iAnio;
					dtFecha = tFecha.getDateSQL(cDia, cMes, "" + iAnio);
				}

				cSQL = "update EXPExamCat" + " set lDictamen= ?, "
						+ " DtInicioVig = ?, " + " DtFinVig = ?"
						+ "where iCveExpediente = ? " + "and iNumExamen = ?"
						+ "and iCveMdoTrans = ?" + "and iCveCategoria = ?";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, lDictamen);
				pstmt.setDate(2, vEXPExamAplica.getDtSolicitado());
				pstmt.setDate(3, dtFecha);

				pstmt.setInt(4, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(5, vEXPDictamenServ.getINumExamen());
				pstmt.setInt(6, iCveMdoTrans);
				pstmt.setInt(7, iCveCategoria);

			} else {
				TFechas tFecha = new TFechas();
				java.sql.Date dtFecha = null;
				TVGRLCategoria vGRLCategoria = new TVGRLCategoria();
				Vector vcCategoria = new Vector();
				TDGRLCategoria dGRLCategoria = new TDGRLCategoria();

				vcCategoria = dGRLCategoria.FindByAll(" where iCveMdoTrans = "
						+ iCveMdoTrans + " and iCveCategoria = "
						+ iCveCategoria, "");
				if (vcCategoria.size() > 0) {
					vGRLCategoria = (TVGRLCategoria) vcCategoria.get(0);
				}

				int iAnio;
				String cVencimiento = "";
				String cDia = "";
				String cMes = "";

				TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
				TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
				Vector vcEXPExamAplica = new Vector();
				vcEXPExamAplica = dEXPExamAplica
						.FindByAll(" where iCveExpediente = "
								+ vEXPDictamenServ.getICveExpediente()
								+ " and iNumExamen = "
								+ vEXPDictamenServ.getINumExamen());

				if (vcEXPExamAplica.size() > 0) {
					vEXPExamAplica = (TVEXPExamAplica) vcEXPExamAplica.get(0);

					iAnio = tFecha.getIntYear(vEXPExamAplica.getDtSolicitado());
					iAnio = iAnio + vGRLCategoria.getITmpoReexp();

					// dtFecha = vEXPExamAplica.getDtDictamen();
					dtFecha = vEXPExamAplica.getDtSolicitado();

					if (tFecha.getIntDay(dtFecha) < 10) {
						cDia = "0" + tFecha.getIntDay(dtFecha);
					} else {

						cDia = "" + tFecha.getIntDay(dtFecha);

					}

					if (tFecha.getIntMonth(dtFecha) < 10) {
						cMes = "0" + tFecha.getIntMonth(dtFecha);
					} else {
						cMes = "" + tFecha.getIntMonth(dtFecha);

					}
					cVencimiento = cDia + "/" + cMes + "/" + iAnio;
					dtFecha = tFecha.getDateSQL(cDia, cMes, "" + iAnio);

				}

				cSQL = "insert into EXPExamCat(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveMdoTrans," + "iCveCategoria,"
						+ "lDictamen," + "iCveMotivo, " + "DtInicioVig, "
						+ "DTFinVig " + ")values(?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(2, vEXPDictamenServ.getINumExamen());
				pstmt.setInt(3, iCveMdoTrans);
				pstmt.setInt(4, iCveCategoria);
				pstmt.setInt(5, lDictamen);
				pstmt.setInt(6, iCveMotivo);
				pstmt.setDate(7, vEXPExamAplica.getDtSolicitado());
				pstmt.setDate(8, dtFecha);
			}

			pstmt.executeUpdate();
			cClave = "" + vEXPDictamenServ.getICveExpediente();

			if (lDictamen == 0) {

				java.util.Date today = new java.util.Date();
				java.sql.Date defFecha = new java.sql.Date(today.getTime());

				vPERCatalogoNoAP.setICvePersonal(vEXPDictamenServ
						.getICveExpediente());
				vPERCatalogoNoAP.setICveMdoTrans(iCveMdoTrans);
				vPERCatalogoNoAP.setICveCategoria(iCveCategoria);
				vPERCatalogoNoAP.setDtInicio(defFecha);
				vPERCatalogoNoAP.setLVigente(1);
				vPERCatalogoNoAP.setIPeriodo(0);
				vPERCatalogoNoAP.setICveMotivoNoAp(vEXPDictamenServ
						.getICveMotivo());
				dPERCatalogoNoAP.insert(null, vPERCatalogoNoAP);

			}

			// Ingreso de Datos a PERLicencia con las validaciones para
			// la eliminaciÃ³n de registros anteriores
			TVPERLicencia vPERLicencia = new TVPERLicencia();
			TDPERLicencia dPERLicencia = new TDPERLicencia();

			TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
			TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
			Vector vcEXPExamAplica = new Vector();
			String cWhere = " where iCvePersonal = "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " ";
			vcEXPExamAplica = dEXPExamAplica.FindByAll(cWhere);
			if (vcEXPExamAplica.size() > 0) {
				vEXPExamAplica = (TVEXPExamAplica) vcEXPExamAplica.get(0);

			}

			cWhere = " where iCvePersonal = "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " and iCveMdoTrans = " + iCveMdoTrans
					+ " and iCveCategoria = " + iCveCategoria + " ";

			Vector vcPERLicencia = new Vector();
			vcPERLicencia = dPERLicencia.FindByAll(cWhere);
			if (vcPERLicencia.size() > 0) {
				vPERLicencia = (TVPERLicencia) vcPERLicencia.get(0);
				dPERLicencia.delete(null, vPERLicencia);
			}

			// Calcula Vencimiento
			TFechas tFecha = new TFechas();
			java.sql.Date dtFecha;
			TVGRLCategoria vGRLCategoria = new TVGRLCategoria();
			Vector vcCategoria = new Vector();
			TDGRLCategoria dGRLCategoria = new TDGRLCategoria();

			vcCategoria = dGRLCategoria.FindByAll(" where iCveMdoTrans = "
					+ iCveMdoTrans + " and " + " iCveCategoria = "
					+ iCveCategoria, "");
			if (vcCategoria.size() > 0) {
				vGRLCategoria = (TVGRLCategoria) vcCategoria.get(0);

			}

			int iAnio;
			String cVencimiento = "";
			String cDia = "";
			String cMes = "";
			String cAnio = "";

			cDia = "" + tFecha.getIntDay(vEXPExamAplica.getDtSolicitado());
			cMes = "" + tFecha.getIntMonth(vEXPExamAplica.getDtSolicitado());
			iAnio = tFecha.getIntYear(vEXPExamAplica.getDtSolicitado());

			iAnio = iAnio + vGRLCategoria.getITmpoReexp();
			cAnio = "" + iAnio;

			cVencimiento = cDia + "/" + cMes + "/" + iAnio;
			dtFecha = tFecha.getDateSQL(cDia, cMes, cAnio);

			vPERLicencia.setICvePersonal(vEXPDictamenServ.getICveExpediente());
			vPERLicencia.setICveMdoTrans(iCveMdoTrans);
			vPERLicencia.setICveCategoria(iCveCategoria);
			vPERLicencia.setINumExamen(vEXPDictamenServ.getINumExamen());
			vPERLicencia.setLDictamen(lDictamen);
			vPERLicencia.setCConstancia("PENDIENTE");
			vPERLicencia.setCLicencia("PENDIENTE");
			vPERLicencia.setDtIniVigencia(vEXPExamAplica.getDtSolicitado());
			vPERLicencia.setDtFinVigencia(dtFecha);

			dPERLicencia.insert(null, vPERLicencia);

			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insertExamCat", ex1);
			}
			warn("insertExamCat", ex);
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
				warn("insertExamCat.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo insertExpSer - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla EXPServicio
	 */
	public Object insertExpSer(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {

			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPServicio "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " and iCveServicio = "
					+ vEXPDictamenServ.getICveServicio();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {
				cSQL = "update EXPServicio" + " set cNotaMedica= ?, "
						+ " iCveUsuAplica = ? , " + " iCveUsuNotaMed = ?, "
						+ " lConcluido = 1, tsFin= CURRENT TIMESTAMP " + "where iCveExpediente = ? "
						+ "and iNumExamen = ?" + " and iCveServicio = ?";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setString(1, vEXPDictamenServ.getCNotaMedica());
				pstmt.setInt(2, vEXPDictamenServ.getICveUsuDictamen());
				pstmt.setInt(3, vEXPDictamenServ.getICveUsuDictamen());
				pstmt.setInt(4, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(5, vEXPDictamenServ.getINumExamen());
				pstmt.setInt(6, vEXPDictamenServ.getICveServicio());
			} else {
				cSQL = "insert into EXPServicio(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveServicio," + "cNotaMedica, "
						+ " lConcluido " + ")values(?,?,?,?,1)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(2, vEXPDictamenServ.getINumExamen());
				pstmt.setInt(3, vEXPDictamenServ.getICveServicio());
				pstmt.setString(4, vEXPDictamenServ.getCNotaMedica());

			}

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
				warn("insertExpSer", ex1);
			}
			warn("insertExpSer", ex);
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
				warn("insertExpSer.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo insertExpDia - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla ExpDiagnostico
	 */
	public Object insertExpDia(Connection conGral, Object obj,
			String iCveDiagnostico, String iCveEspecialidad)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		// Calculando Timestamp

		java.util.Date utilDate = new java.util.Date(); // fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		// System.out.println("Insertando diagnostico");

		try {

			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPDiagnostico "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " and iCveServicio = "
					+ vEXPDictamenServ.getICveServicio()
					+ " and iCveEspecialidad = " + iCveEspecialidad
					+ " and iCveDiagnostico = " + iCveDiagnostico;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {
				cSQL = "update EXPDiagnostico" + " set iCveEspecialidad= ?, "
						+ " iCveDiagnostico= ? " + " where iCveExpediente =  "
						+ vEXPDictamenServ.getICveExpediente()
						+ " and iNumExamen = "
						+ vEXPDictamenServ.getINumExamen()
						+ " and iCveServicio = "
						+ vEXPDictamenServ.getICveServicio()
						+ " and iCveEspecialidad = " + iCveEspecialidad
						+ " and iCveDiagnostico = " + iCveDiagnostico;
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setString(1, iCveEspecialidad);
				pstmt.setString(2, iCveDiagnostico);

			} else {
				cSQL = "insert into EXPDiagnostico(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveServicio,"
						+ "iCveEspecialidad, " + "iCveDiagnostico, "
						+ "TiDiagnostico " + ")values(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(2, vEXPDictamenServ.getINumExamen());
				pstmt.setInt(3, vEXPDictamenServ.getICveServicio());
				pstmt.setString(4, iCveEspecialidad);
				pstmt.setString(5, iCveDiagnostico);
				pstmt.setTimestamp(6, sqlTimestamp);

			}

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
				warn("insertExpDia", ex1);
			}
			warn("insertExpDia", ex);
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
				warn("insertExpDia.close", ex2);
			}
			return cClave;
		}
	}
	
	
	/**
	 * Metodo insertExpDiaCif - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla ExpDiagnostico
	 */
	public Object insertExpDiaCif(Connection conGral, Object obj,
			String iCveDiagnosticoCif, String iCveRamaCif)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		// Calculando Timestamp

		java.util.Date utilDate = new java.util.Date(); // fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		// System.out.println("Insertando diagnostico");

		try {

			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPDiagnosticoCif "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " and iCveServicio = "
					+ vEXPDictamenServ.getICveServicio()
					+ " and iCveRamaCif = " + iCveRamaCif
					+ " and iCveCif = " + iCveDiagnosticoCif;
			pstmtMax = conn.prepareStatement(cSQLMax);
			//System.out.println(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {
				cSQL = "update EXPDiagnosticoCif" + " set iCveRamaCif= ?, "
						+ " iCveDiagnosticoCif= ? " + " where iCveExpediente =  "
						+ vEXPDictamenServ.getICveExpediente()
						+ " and iNumExamen = "
						+ vEXPDictamenServ.getINumExamen()
						+ " and iCveServicio = "
						+ vEXPDictamenServ.getICveServicio()
						+ " and iCveRamaCif = " + iCveRamaCif
						+ " and iCveDiagnosticoCif = " + iCveDiagnosticoCif;
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setString(1, iCveRamaCif);
				pstmt.setString(2, iCveDiagnosticoCif);

			} else {
				cSQL = "insert into EXPDiagnosticoCif(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveServicio,"
						+ "iCveRamaCif, " + "iCveCif, "
						+ "TiDiagnosticoCif " + ")values(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(2, vEXPDictamenServ.getINumExamen());
				pstmt.setInt(3, vEXPDictamenServ.getICveServicio());
				pstmt.setString(4, iCveRamaCif);
				pstmt.setString(5, iCveDiagnosticoCif);
				pstmt.setTimestamp(6, sqlTimestamp);

			}

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
				warn("insertExpDia", ex1);
			}
			warn("insertExpDia", ex);
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
				warn("insertExpDia.close", ex2);
			}
			return cClave;
		}
	}	

	/**
	 * Metodo insertExpReco - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla ExpDiagnostico
	 */
	public Object insertExpReco(Connection conGral, Object obj,
			String iCveEspecialidad, String iCveRecomendacion)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		// Calculando Timestamp

		java.util.Date utilDate = new java.util.Date(); // fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		// System.out.println("Insertando recomendacion");

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPRecomendacion "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " and iCveServicio = "
					+ vEXPDictamenServ.getICveServicio()
					+ " and iCveEspecialidad = " + iCveEspecialidad
					+ " and iCveRecomendacion = " + iCveRecomendacion;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			if (iMax > 0) {
				cSQL = "update EXPRecomendacion" + " set iCveEspecialidad= ?, "
						+ " iCveRecomendacion= ? "
						+ " where iCveExpediente =  "
						+ vEXPDictamenServ.getICveExpediente()
						+ " and iNumExamen = "
						+ vEXPDictamenServ.getINumExamen()
						+ " and iCveServicio = "
						+ vEXPDictamenServ.getICveServicio()
						+ " and iCveEspecialidad = " + iCveEspecialidad
						+ " and iCveRecomendacion = " + iCveRecomendacion;
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setString(1, iCveEspecialidad);
				pstmt.setString(2, iCveRecomendacion);
				// System.out.println(cSQL);
			} else {
				cSQL = "insert into EXPRecomendacion(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveServicio,"
						+ "iCveEspecialidad, " + "iCveRecomendacion, "
						+ "TiRecomendaci�n " + ")values(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEXPDictamenServ.getICveExpediente());
				pstmt.setInt(2, vEXPDictamenServ.getINumExamen());
				pstmt.setInt(3, vEXPDictamenServ.getICveServicio());
				pstmt.setString(4, iCveEspecialidad);
				pstmt.setString(5, iCveRecomendacion);
				pstmt.setTimestamp(6, sqlTimestamp);
				// System.out.println(cSQL);
			}

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
				warn("insertExpReco", ex1);
			}
			warn("insertExpReco", ex);
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
				warn("insertExpReco.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo lConcluidoExpServ - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla ExpDiagnostico
	 */
	public int lConcluidoExpServ(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPServicio "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " And lConcluido = 0";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insertExpReco", ex1);
			}
			warn("insertExpReco", ex);
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
				warn("insertExpReco.close", ex2);
			}
			return iMax;
		}
	}

	/**
	 * Metodo lConcluidoExpExamAplica - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla ExpDiagnostico
	 */
	public int lConcluidoExpExamAplica(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPExamAplica "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " And lConcluido = 0";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insertExpReco", ex1);
			}
			warn("insertExpReco", ex);
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
				warn("insertExpReco.close", ex2);
			}
			return iMax;
		}
	}

	/**
	 * Metodo lConcluidoExpExamAplica - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla ExpDiagnostico
	 */
	public int lDictaminadoExpExamAplica(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
 
			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPExamAplica "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " And lDictaminado = 0";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insertExpReco", ex1);
			}
			warn("insertExpReco", ex);
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
				warn("insertExpReco.close", ex2);
			}
			return iMax;
		}
	}
	
	public int lDictaminadoExpExamAplicaDos(Connection conGral, String exp, String exa)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
 
			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPExamAplica "
					+ " where iCveExpediente =  "
					+ exp
					+ " and iNumExamen = " + exa
					+ " And lDictaminado = 0";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insertExpReco", ex1);
			}
			warn("insertExpReco", ex);
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
				warn("insertExpReco.close", ex2);
			}
			return iMax;
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
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPDictamenServ" + " set lDictamen= ? "
					+ "where iCveExpediente = ? " + "and iNumExamen = ?"
					+ "and iCveMdoTrans = ?" + "and iCveCategoria = ?"
					+ " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPDictamenServ.getLDictamen());
			pstmt.setInt(2, vEXPDictamenServ.getICveExpediente());
			pstmt.setInt(3, vEXPDictamenServ.getINumExamen());
			pstmt.setInt(4, vEXPDictamenServ.getICveMdoTrans());
			pstmt.setInt(5, vEXPDictamenServ.getICveCategoria());
			pstmt.setInt(6, vEXPDictamenServ.getICveServicio());
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
	 * Metodo Find by EXPServicio
	 */
	public Vector findByExpSer(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = "select cNotaMedica from EXPServicio "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " and iCveServicio = "
					+ vEXPDictamenServ.getICveServicio();

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setCNotaMedica(rset.getString(1));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("FindByCatSol", ex);
			throw new DAOException("FindByCatSol");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}
	
	/**
	 * Metodo Find by EXPServicio
	 */
	public Vector findByExpSerDos(String exp,String exa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = "select cNotaMedica from EXPServicio "
					+ " where iCveExpediente =  "
					+ exp
					+ " and iNumExamen = " + exa
					+ " and iCveServicio = "
					+ "31";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setCNotaMedica(rset.getString(1));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("FindByCatSol", ex);
			throw new DAOException("FindByCatSol");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}
	

	/**
	 * Metodo findByExpDicSer
	 */
	public Vector findByExpDicSer(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " Select a.iCveExpediente, a.iNumExamen, "
					+ "        a.iCveMdoTrans, a.iCveCategoria,  "
					+ "        a.iCveServicio, a.lDictamen, b.cDscCategoria, c.cDscMdoTrans "
					+ " From   EXPDictamenServ a, GRLCategoria b, GRLMdoTrans c "
					+ " Where  a.iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " And    a.iNumExamen = "
					+ vEXPDictamenServ.getINumExamen()
					+ " And    a.iCveServicio = "
					+ vEXPDictamenServ.getICveServicio()
					+ " And    a.iCveCategoria = b.iCveCategoria"
					+ " And    a.iCveMdoTrans = b.iCveMdoTrans"
					+ " And    a.iCveMdoTrans = c.iCveMdoTrans";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setICveServicio(rset.getInt(5));
				vEXPDictamenServ.setLDictamen(rset.getInt(6));
				vEXPDictamenServ.setCDscCategoria(rset.getString(7));
				vEXPDictamenServ.setCDscMdoTrans(rset.getString(8));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpDicSer", ex);
			throw new DAOException("findByExpDicSer");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}


	/**
	 * Metodo findByExpDicSer
	 */
	public Vector findByExpDicSerDos(String exp, String exa, String servicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " Select a.iCveExpediente, a.iNumExamen, "
					+ "        a.iCveMdoTrans, a.iCveCategoria,  "
					+ "        a.iCveServicio, a.lDictamen, b.cDscCategoria, c.cDscMdoTrans "
					+ " From   EXPDictamenServ a, GRLCategoria b, GRLMdoTrans c "
					+ " Where  a.iCveExpediente =  "
					+ exp
					+ " And    a.iNumExamen = "
					+ exa
					+ " And    a.iCveServicio = "
					+ servicio
					+ " And    a.iCveCategoria = b.iCveCategoria"
					+ " And    a.iCveMdoTrans = b.iCveMdoTrans"
					+ " And    a.iCveMdoTrans = c.iCveMdoTrans";

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setICveServicio(rset.getInt(5));
				vEXPDictamenServ.setLDictamen(rset.getInt(6));
				vEXPDictamenServ.setCDscCategoria(rset.getString(7));
				vEXPDictamenServ.setCDscMdoTrans(rset.getString(8));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpDicSer", ex);
			throw new DAOException("findByExpDicSer");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	
	
	/**
	 * Metodo findByExpExamCat
	 */
	public Vector findByExpExamCat(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			StringBuffer cSQL = new StringBuffer();
			cSQL.append(" SELECT a.iCveExpediente, a.iNumExamen, ")
					.append("        a.iCveMdoTrans, a.iCveCategoria,")
					.append("        a.lDictamen, b.cDscCategoria, ")
					.append("        c.cDscMdoTrans, a.dtInicioVig, ")
					.append("        a.dtFinVig, a.lDitamEm, ")
					.append("        a.dtDictaminado, b.iTmpoReexp")
					.append(" from EXPExamCat a ")
					.append("  inner join GRLCategoria b on b.iCveMdoTrans  = a.iCveMdoTrans ")
					.append("                           and b.iCveCategoria = a.iCveCategoria ")
					.append("  inner join GRLMdoTrans c on c.iCveMdoTrans = a.iCveMdoTrans ")
					.append(" where a.iCveExpediente = ")
					.append(vEXPDictamenServ.getICveExpediente())
					.append("   and a.iNumExamen =  ")
					.append(vEXPDictamenServ.getINumExamen())
					.append("  order by icvemdotrans, icvecategoria ");

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setLDictamen(rset.getInt(5));
				vEXPDictamenServ.setCDscCategoria(rset.getString(6));
				vEXPDictamenServ.setCDscMdoTrans(rset.getString(7));
				vEXPDictamenServ.setDtInicioVig(rset.getDate(8));
				vEXPDictamenServ.setDtFinVig(rset.getDate(9));
				// Se agrega la informaciÃ³n de si estÃ³ o no dictaminada la
				// categorÃ?Â­a
				vEXPDictamenServ.setLDictamEm(rset.getInt(10));
				vEXPDictamenServ.setDtDictaminado(rset.getDate(11));
				vEXPDictamenServ.setITmpoReexp(rset.getInt(12));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpDicSer", ex);
			throw new DAOException("findByExpDicSer");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}
	
	
	/**
	 * Metodo findByExpExamCat
	 */
	public Vector findByExpExamCatDos(String exp, String exa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			StringBuffer cSQL = new StringBuffer();
			cSQL.append(" SELECT a.iCveExpediente, a.iNumExamen, ")
					.append("        a.iCveMdoTrans, a.iCveCategoria,")
					.append("        a.lDictamen, b.cDscCategoria, ")
					.append("        c.cDscMdoTrans, a.dtInicioVig, ")
					.append("        a.dtFinVig, a.lDitamEm, ")
					.append("        a.dtDictaminado, b.iTmpoReexp")
					.append(" from EXPExamCat a ")
					.append("  inner join GRLCategoria b on b.iCveMdoTrans  = a.iCveMdoTrans ")
					.append("                           and b.iCveCategoria = a.iCveCategoria ")
					.append("  inner join GRLMdoTrans c on c.iCveMdoTrans = a.iCveMdoTrans ")
					.append(" where a.iCveExpediente = ")
					.append(exp)
					.append("   and a.iNumExamen =  ")
					.append(exa)
					.append("  order by icvemdotrans, icvecategoria ");

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setLDictamen(rset.getInt(5));
				vEXPDictamenServ.setCDscCategoria(rset.getString(6));
				vEXPDictamenServ.setCDscMdoTrans(rset.getString(7));
				vEXPDictamenServ.setDtInicioVig(rset.getDate(8));
				vEXPDictamenServ.setDtFinVig(rset.getDate(9));
				// Se agrega la informaciÃ³n de si estÃ³ o no dictaminada la
				// categorÃ?Â­a
				vEXPDictamenServ.setLDictamEm(rset.getInt(10));
				vEXPDictamenServ.setDtDictaminado(rset.getDate(11));
				vEXPDictamenServ.setITmpoReexp(rset.getInt(12));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpDicSer", ex);
			throw new DAOException("findByExpDicSer");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo findByExpRec
	 */
	public Vector findByExpRec(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " Select a.iCveExpediente, a.iNumExamen, "
					+ "        a.iCveServicio,a.iCveEspecialidad,"
					+ "        a.iCveRecomendacion, b.cDscBreve"
					+ " From   EXPRecomendacion a, MEDRecomendacion b "
					+ " Where  a.iCveExpediente = "
					+ vEXPDictamenServ.getICveExpediente()
					+ " And    a.iNumExamen     = "
					+ vEXPDictamenServ.getINumExamen()
					+ " And    a.iCveServicio   = "
					+ vEXPDictamenServ.getICveServicio()
					+ " And    a.iCveRecomendacion = b.iCveRecomendacion "
					+ " And    a.iCveEspecialidad  = b.iCveEspecialidad "
					+ " Order by TIRecomendaci�n ASC ";

			pstmt = conn.prepareStatement(cSQL);
			// System.out.println();
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveServicio(rset.getInt(3));
				vEXPDictamenServ.setICveEspecialidad(rset.getInt(4));
				vEXPDictamenServ.setICveRecomendacion(rset.getInt(5));
				vEXPDictamenServ.setCDscRecomendacion(rset.getString(6));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpRec", ex);
			throw new DAOException("findByExpRec");
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
				warn("findByExpRec.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}
	

	/**
	 * Metodo findByExpRec
	 */
	public Vector findByExpRecDos(String exp, String exa, String serv) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " Select a.iCveExpediente, a.iNumExamen, "
					+ "        a.iCveServicio,a.iCveEspecialidad,"
					+ "        a.iCveRecomendacion, b.cDscBreve"
					+ " From   EXPRecomendacion a, MEDRecomendacion b "
					+ " Where  a.iCveExpediente = "
					+ exp
					+ " And    a.iNumExamen     = "
					+ exa
					+ " And    a.iCveServicio   = "
					+ serv
					+ " And    a.iCveRecomendacion = b.iCveRecomendacion "
					+ " And    a.iCveEspecialidad  = b.iCveEspecialidad "
					+ " Order by TIRecomendaci�n ASC ";

			pstmt = conn.prepareStatement(cSQL);
			// System.out.println();
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveServicio(rset.getInt(3));
				vEXPDictamenServ.setICveEspecialidad(rset.getInt(4));
				vEXPDictamenServ.setICveRecomendacion(rset.getInt(5));
				vEXPDictamenServ.setCDscRecomendacion(rset.getString(6));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpRec", ex);
			throw new DAOException("findByExpRec");
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
				warn("findByExpRec.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo findByExpDia
	 */
	public Vector findByExpDia(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " Select a.iCveExpediente, a.iNumExamen, "
					+ "        a.iCveServicio,a.iCveEspecialidad,"
					+ "        a.iCveDiagnostico, b.cDscBreve,"
					+ "        a.CMotivacion "
					+ " From   EXPDiagnostico a, MEDDiagnostico b "
					+ " Where  a.iCveExpediente = "
					+ vEXPDictamenServ.getICveExpediente()
					+ " And    a.iNumExamen     = "
					+ vEXPDictamenServ.getINumExamen()
					+ " And    a.iCveServicio   = "
					+ vEXPDictamenServ.getICveServicio()
					+ " And    a.iCveDiagnostico = b.iCveDiagnostico "
					+ " And    a.iCveEspecialidad  = b.iCveEspecialidad "
					+ " Order by TIDiagnostico ASC ";

			// System.out.print(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveServicio(rset.getInt(3));
				vEXPDictamenServ.setICveEspecialidad(rset.getInt(4));
				vEXPDictamenServ.setICveDiagnostico(rset.getInt(5));
				vEXPDictamenServ.setCDscDiagnostico(rset.getString(6));
				vEXPDictamenServ.setCMotivacion(rset.getString(7));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpDia", ex);
			throw new DAOException("findByExpDia");
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
				warn("findByExpDia.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}
	
	/**
	 * Metodo findByExpDia
	 */
	public Vector findByExpDiaDos(String exp, String exa, String serv) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " Select a.iCveExpediente, a.iNumExamen, "
					+ "        a.iCveServicio,a.iCveEspecialidad,"
					+ "        a.iCveDiagnostico, b.cDscBreve,"
					+ "        a.CMotivacion "
					+ " From   EXPDiagnostico a, MEDDiagnostico b "
					+ " Where  a.iCveExpediente = "
					+ exp
					+ " And    a.iNumExamen     = "
					+ exa
					+ " And    a.iCveServicio   = "
					+ serv
					+ " And    a.iCveDiagnostico = b.iCveDiagnostico "
					+ " And    a.iCveEspecialidad  = b.iCveEspecialidad "
					+ " Order by TIDiagnostico ASC ";

			// System.out.print(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveServicio(rset.getInt(3));
				vEXPDictamenServ.setICveEspecialidad(rset.getInt(4));
				vEXPDictamenServ.setICveDiagnostico(rset.getInt(5));
				vEXPDictamenServ.setCDscDiagnostico(rset.getString(6));
				vEXPDictamenServ.setCMotivacion(rset.getString(7));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpDia", ex);
			throw new DAOException("findByExpDia");
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
				warn("findByExpDia.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}
	
	/**
	 * Metodo findByExpDia
	 */
	public Vector findByExpDiaCif(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " Select a.iCveExpediente, a.iNumExamen, "
					+ "        a.iCveServicio,a.iCveRamaCif,"
					+ "        a.iCveCif, b.cDscRamaCif "
					+ " From   EXPDiagnosticoCif a, MEDCif b "
					+ " Where  a.iCveExpediente = "
					+ vEXPDictamenServ.getICveExpediente()
					+ " And    a.iNumExamen     = "
					+ vEXPDictamenServ.getINumExamen()
					+ " And    a.iCveServicio   = "
					+ vEXPDictamenServ.getICveServicio()
					+ " And    a.iCveCif = b.iCveCif "
					+ " And    a.iCveRamaCif  = b.iCveRamaCif "
					+ " Order by TIDiagnosticoCif ASC ";

		 //System.out.print(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveServicio(rset.getInt(3));
				vEXPDictamenServ.setICveEspecialidad(rset.getInt(4));
				vEXPDictamenServ.setICveDiagnostico(rset.getInt(5));
				vEXPDictamenServ.setCDscDiagnostico(rset.getString(6));
				//vEXPDictamenServ.setCMotivacion(rset.getString(7));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpDia", ex);
			throw new DAOException("findByExpDia");
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
				warn("findByExpDia.close", ex2);
			}
			return vcEXPDictamenServ;
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
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPDictamenServ" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveMdoTrans = ?"
					+ " and iCveCategoria = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPDictamenServ.getICveExpediente());
			pstmt.setInt(2, vEXPDictamenServ.getINumExamen());
			pstmt.setInt(3, vEXPDictamenServ.getICveMdoTrans());
			pstmt.setInt(4, vEXPDictamenServ.getICveCategoria());
			pstmt.setInt(5, vEXPDictamenServ.getICveServicio());
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
				warn("delete.closeEXPDictamenServ", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo getEXPDictamenYNotaMedica
	 */
	public Vector getEXPDictamenYNotaMedica(HashMap hmFiltros)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveMdoTrans,a.iCveCategoria,"
					+ "a.iCveServicio,a.lDictamen,b.cNotaMedica,c.cDscServicio,"
					+ "d.cDscCategoria "
					+ "from EXPDictamenServ a inner join EXPServicio b on ("
					+ "a.iCveExpediente=b.iCveExpediente and a.iNumExamen=b.iNumExamen "
					+ "and a.iCveServicio=b.iCveServicio) inner join MEDServicio c on ("
					+ "a.iCveServicio=c.iCveServicio) inner join GRLCategoria d on ("
					+ "a.iCveMdoTrans=d.iCveMdoTrans and a.iCveCategoria=d.iCveCategoria)";
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");

			if (cCveExpediente != null) {
				cSQL += " where a.iCveExpediente=?";
				cWhereAnd = " and";
			}
			if (cNumExamen != null) {
				cSQL += cWhereAnd + " a.iNumExamen=?";
			}
			cSQL += " order by a.iCveExpediente,a.iNumExamen,a.iCveServicio,a.lDictamen";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			// Antes con valor de i = 1;
			int i = 0;
			if (cCveExpediente != null) {
				i = i + 1;
				pstmt.setInt(i, Integer.parseInt(cCveExpediente));
			}
			if (cNumExamen != null) {
				i = i + 1;
				pstmt.setInt(i, Integer.parseInt(cNumExamen));
			}
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset
						.getInt("iCveExpediente"));
				vEXPDictamenServ.setINumExamen(rset.getInt("iNumExamen"));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				vEXPDictamenServ.setICveCategoria(rset.getInt("iCveCategoria"));
				vEXPDictamenServ.setICveServicio(rset.getInt("iCveServicio"));
				vEXPDictamenServ.setLDictamen(rset.getInt("lDictamen"));
				vEXPDictamenServ.setCNotaMedica(rset.getString("cNotaMedica"));
				vEXPDictamenServ
						.setCDscServicio(rset.getString("cDscServicio"));
				vEXPDictamenServ.setCDscCategoria(rset
						.getString("cDscCategoria"));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("getEXPDictamenYNotaMedica", ex);
			throw new DAOException("getEXPDictamenYNotaMedica");
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
				warn("getEXPDictamenYNotaMedica.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo getEXPDictamenYNotaMedica
	 */
	public Vector getEXPDictamenYNotaMedicanNoEPI(HashMap hmFiltros)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveMdoTrans,a.iCveCategoria,"
					+ "a.iCveServicio,a.lDictamen,b.cNotaMedica,c.cDscServicio,"
					+ "d.cDscCategoria "
					+ "from EXPDictamenServ a inner join EXPServicio b on ("
					+ "a.iCveExpediente=b.iCveExpediente and a.iNumExamen=b.iNumExamen "
					+ "and a.iCveServicio=b.iCveServicio) inner join MEDServicio c on ("
					+ "a.iCveServicio=c.iCveServicio) inner join GRLCategoria d on ("
					+ "a.iCveMdoTrans=d.iCveMdoTrans and a.iCveCategoria=d.iCveCategoria)";
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");

			if (cCveExpediente != null) {
				cSQL += " where a.iCveExpediente=?";
				cWhereAnd = " and";
			}
			if (cNumExamen != null) {
				cSQL += cWhereAnd + " a.iNumExamen=?";
			}
			cSQL += " order by a.iCveExpediente,a.iNumExamen,a.iCveServicio,a.lDictamen";
			pstmt = conn.prepareStatement(cSQL);

			// Antes con valor de i = 1;
			int i = 0;
			if (cCveExpediente != null) {
				i = i + 1;
				pstmt.setInt(i, Integer.parseInt(cCveExpediente));
			}
			if (cNumExamen != null) {
				i = i + 1;
				pstmt.setInt(i, Integer.parseInt(cNumExamen));
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset
						.getInt("iCveExpediente"));
				vEXPDictamenServ.setINumExamen(rset.getInt("iNumExamen"));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				vEXPDictamenServ.setICveCategoria(rset.getInt("iCveCategoria"));
				vEXPDictamenServ.setICveServicio(rset.getInt("iCveServicio"));
				vEXPDictamenServ.setLDictamen(rset.getInt("lDictamen"));
				vEXPDictamenServ.setCNotaMedica(rset.getString("cNotaMedica"));
				vEXPDictamenServ
						.setCDscServicio(rset.getString("cDscServicio"));
				vEXPDictamenServ.setCDscCategoria(rset
						.getString("cDscCategoria"));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("getEXPDictamenYNotaMedicanNoEPI", ex);
			throw new DAOException("getEXPDictamenYNotaMedicanNoEPI");
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
				warn("getEXPDictamenYNotaMedicanNoEPI.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector getUniMed(int iCveProceso, int iCveUniMed) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vcUniMed = new Vector();
		TVGRLUniMed vGRLUniMed;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select GRLUniMed.iCveUniMed,cDscUniMed,GRLProceso.iCveProceso,cDscProceso, "
					+ "SEGUsuario.iCveUsuario,SEGUsuario.cNombre,SEGUsuario.cApPaterno, "
					+ "SEGUsuario.cApMaterno, GRLPais.cNombre Pais, GRLEntidadFed.cNombre Estado "
					+ "from GRLUMUsuario  "
					+ "JOIN GRLProceso ON GRLUMUsuario.iCveProceso = GRLProceso.iCveProceso "
					+ "JOIN GRLUniMed ON GRLUMUsuario.iCveUniMed = GRLUniMed.iCveUniMed  "
					+ "JOIN SEGUsuario ON GRLUMUsuario.iCveUsuario = SEGUsuario.iCveUsuario "
					+ "JOIN GRLPais ON GRLPais.iCvePais = GRLUniMed.iCvePais "
					+ "JOIN GRLEntidadFed ON GRLEntidadFed.iCveEntidadFed = GRLUniMed.iCveEstado "
					+ "And  GRLEntidadFed.iCvePais = GRLUniMed.iCvePais "
					+ "WHERE GRLProceso.iCveProceso = "
					+ iCveProceso
					+ " AND GRLUniMed.iCveUniMed = " + iCveUniMed;

			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUniMed = new TVGRLUniMed();
				vGRLUniMed.setICveUniMed(lRSet.getInt(1));
				vGRLUniMed.setCDscUniMed(lRSet.getString(2));
				vGRLUniMed.setCDscUMPais(lRSet.getString(9));
				vGRLUniMed.setCDscUMEstado(lRSet.getString(10));
				vcUniMed.add(vGRLUniMed);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
			}
			return vcUniMed;
		}
	}

	/**
	 * Metodo checkDelete
	 */
	public int checkDelete(String cExpediente, String cExamen, String cServicio)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int count = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRecomendacion vEXPRecomendacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " SELECT * " + " FROM EXPDictamenServ "
					+ " WHERE iCveExpediente = " + cExpediente
					+ " AND iNumExamen = " + cExamen + " AND iCveServicio = "
					+ cServicio;
			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				count++;
			}
			stmt.close();
			if (count != 0) {
				cSQL = " DELETE " + " FROM EXPDictamenServ "
						+ " WHERE iCveExpediente = " + cExpediente
						+ " AND iNumExamen = " + cExamen
						+ " AND iCveServicio = " + cServicio;

				stmt = conn.createStatement();
				count = stmt.executeUpdate(cSQL);
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
				warn("checkDelete.close", ex2);
			}
		}
		return count;
	}

	/**
	 * Metodo lConcluidoExpExamAplica - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla ExpDiagnostico
	 */
	public int lDictamenCat(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			StringBuffer cSQLMax = new StringBuffer();
			cSQLMax.append(" select count(EC.lDitamEm) ")
					.append(" from EXPExamCat EC ")
					.append(" where EC.iCveExpediente = ")
					.append(vEXPDictamenServ.getICveExpediente())
					.append("   and EC.iNumExamen     = ")
					.append(vEXPDictamenServ.getINumExamen())
					.append("   and EC.lDitamEm       = 0 ");
			pstmtMax = conn.prepareStatement(cSQLMax.toString());
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("lDictamenCat", ex1);
			}
			warn("lDictamenCat", ex);
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
				warn("lDictamenCat.close", ex2);
			}
			return iMax;
		}
	}

	
	/**
	 * Metodo lConcluidoExpExamAplica - JMG - NO Borrar
	 * 
	 * @Este Metodo se encarga de insertar en la tabla ExpDiagnostico
	 */
	public int lDictamenCatDos(Connection conGral, String exp, String exa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			StringBuffer cSQLMax = new StringBuffer();
			cSQLMax.append(" select count(EC.lDitamEm) ")
					.append(" from EXPExamCat EC ")
					.append(" where EC.iCveExpediente = ")
					.append(exp)
					.append("   and EC.iNumExamen     = ")
					.append(exa)
					.append("   and EC.lDitamEm       = 0 ");
			pstmtMax = conn.prepareStatement(cSQLMax.toString());
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("lDictamenCat", ex1);
			}
			warn("lDictamenCat", ex);
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
				warn("lDictamenCat.close", ex2);
			}
			return iMax;
		}
	}

	
	/**
	 * guarDictExamCat Metodo encargado de almacenar la informaciÃ³n de los
	 * Dictamenes del Examen. El dictamen se emite por categorÃ?Â­a.
	 * 
	 * @param vDatos
	 *            Object Vector de TVEXPExamCat que contienen la informacion de
	 *            las categorÃ?Â­as dictaminadas.
	 * @return boolean Indica si la actualizaciÃ³n fue correcta.
	 */
	public boolean guarDictExamCat(Object vDatos, Object obj)
			throws DAOException {
		int iNumQSU = -1;
		boolean lResultado = false;
		HashMap hCamposQ1 = new HashMap();
		StringBuffer cCondicion = new StringBuffer();
		ArrayList aQueryB = new ArrayList(), aQueryI = new ArrayList();
		QueryManager qQuery = new QueryManager("07", "ConDBModulo");
		TFechas Fecha = new TFechas();
		TNumeros Numeros = new TNumeros();
		java.sql.Date dtFinV;
		java.sql.Date dtFinVSintomas;
		java.sql.Date dtFinVInsulinodependiente;
		java.sql.Date dtFinVAnticoagulante;
		java.sql.Date dtFinVDiagnostico;
		int IUnidadMedica = 0;
		int IModulo = 0;
		TVDinRep02 vResultado = new TVDinRep02();
		try {
			// Obtener el Registro del EXPExamAplica
			TVEXPDictamenServ vEXPDict = (TVEXPDictamenServ) obj;
			TVEXPExamAplica vExamA = new TVEXPExamAplica();
			TDEXPExamAplica dExamA = new TDEXPExamAplica();
			TDPERDatos dPERDatos = new TDPERDatos();
			cCondicion.append("where EXPExamAplica.iCveExpediente = ")
					.append(vEXPDict.getICveExpediente())
					.append("  and EXPExamAplica.iNumExamen     = ")
					.append(vEXPDict.getINumExamen());
			Vector vExamAplica = new TDEXPExamAplica().FindByAll(cCondicion
					.toString());

			///OBteneos Unidad y modulo
			IUnidadMedica = dExamA.RegresaInt("Select icveunimed from expexamaplica where icveexpediente = "+vEXPDict.getICveExpediente()+" and inumexamen ="+vEXPDict.getINumExamen());
			IModulo = dExamA.RegresaInt("Select icvemodulo from expexamaplica where icveexpediente = "+vEXPDict.getICveExpediente()+" and inumexamen ="+vEXPDict.getINumExamen());
			
			
			
			// Calculamos fecha de nacimiento
			Vector vcPersonal = new Vector();
			TVPERDatos vPerDatos;
			java.util.Date fecha2 = new java.util.Date();
			java.sql.Date FechaNacimiento = new java.sql.Date(fecha2.getTime());
			vcPersonal = dPERDatos.FindByAll(vEXPDict.getICveExpediente() + "");
			if (vcPersonal.size() > 0) {
				for (int i = 0; i < vcPersonal.size(); i++) {
					vPerDatos = (TVPERDatos) vcPersonal.get(i);
					FechaNacimiento = vPerDatos.getDtNacimiento();
				}
			}

			Calendar hoy = Calendar.getInstance();
			Calendar nac = Calendar.getInstance();
			nac.setTime(FechaNacimiento);
			int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
			if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
					.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE)) {
				edad--;
			}
			 System.out.println("edad -- = " +edad);

			// Incrementamos 6 meses al dia actual
			hoy.add(Calendar.MONTH, 7);

			int ano = hoy.get(Calendar.YEAR);
			int mes = hoy.get(Calendar.MONTH);
			int dia = hoy.get(Calendar.DATE);

			 System.out.println(ano +" - "+mes+" - "+dia);

			String str_date = ano + "-" + mes + "-" + dia;

			// Convierte String a Date
			// String str_date="0000-06-00";
			DateFormat formatter;
			Date date;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = (Date) formatter.parse(str_date);

			java.util.Date y = new java.util.Date();
			java.util.Date FinV = new java.util.Date();
			java.util.Date FinV2 = new java.util.Date();
			y = date;

			java.sql.Date I6M = new java.sql.Date(y.getTime());
			 System.out.println("fecha fin vigencia I6M = " + I6M);

			// Traer informacion de puesto, categoria y modo de transporte.
			Vector vcExpExamcat = new Vector();
			TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
			TVEXPExamCat vEXPExamCat;
			int transporte = 0;
			int puesto = 0;
			int categoria = 0;

			System.out.println("op1");
			
			// Se encontro el ExamAplica
			if (vExamAplica != null && vExamAplica.size() > 0) {
				vExamA = (TVEXPExamAplica) vExamAplica.get(0);
				// Genera el Vector de los Query Structure para las
				// actualizaciones
				QueryStructure[] tmp = new QueryStructure[(((Vector) vDatos)
						.size() * 7) + 2];
				// Obtener los datos de las CategorÃ?Â­as
				System.out.println("op2");
				for (int i = 0; i < ((Vector) vDatos).size(); i++) {
					TVEXPDictamenServ vExamCat = new TVEXPDictamenServ();
					QueryStructure qBuscar01, qBuscar02, qBuscar03, qBuscar04, qBuscar05;
					int iConsCNA = 0;
					vExamCat = (TVEXPDictamenServ) ((Vector) vDatos).get(i);
					// Se asigna categoria y transporte
					transporte = vExamCat.getICveMdoTrans();
					categoria = vExamCat.getICveCategoria();
					aQueryB.clear();
					// Generar el update para la Entidad EXPExamCat
					iNumQSU++;
					cCondicion = new StringBuffer();
					System.out.println("op3");
					dtFinV = Fecha
							.getSQLDatefromSQLString(
							// Se modificaron las siguientes lineas con la
							// finalidad de insertar correctamente el fin de
							// vigencia en Perlicencia
							String.valueOf(Fecha.getIntYear(Fecha.TodaySQL())
									+ vExamCat.getITmpoReexp())
									+ "-"
									+ Numeros.getNumeroSinSeparador(Integer
											.valueOf(String.valueOf(Fecha
													.getIntMonth(Fecha
															.TodaySQL()))), 2)
									+ "-"
									+ Numeros.getNumeroSinSeparador(
											Integer.valueOf(String.valueOf(Fecha
													.getIntDay(Fecha.TodaySQL()))),
											2));

					// / Calculando nuevas Vigencias por puesto
					// AG SA L 2011/09/06

					TDGRLCONVIGPUE dGRLCONVIGPUE = new TDGRLCONVIGPUE();
					TVGRLCONVIGPUE vGRLCONVIGPUE = new TVGRLCONVIGPUE();
					Vector vcGRLCONVIGPUE = new Vector();
					Calendar hoy2 = Calendar.getInstance();
					try {
						String SQLQ = "select ep.icvepuesto "
								+ "from 	expexampuesto as ep, 	"
								+ "grlpuesto as gp " + "where "
								+ "gp.icvemdotrans = ep.icvemdotrans and "
								+ "gp.icvepuesto = ep.icvepuesto and "
								+ "gp.icvemdotrans = " + transporte + " and "
								+ "gp.icvecategoria = " + categoria + " and "
								+ "ep.icveexpediente = "
								+ vEXPDict.getICveExpediente() + " and "
								+ "ep.inumexamen = " + vEXPDict.getINumExamen();
						String puestoS = dEXPExamCat.SentenciaSQL(SQLQ);

						vcGRLCONVIGPUE = dGRLCONVIGPUE
								.FindByAll(" WHERE ICVEMDOTRANS = "
										+ transporte + " AND ICVECATEGORIA = "
										+ categoria + " AND  ICVEPUESTO = "
										+ puestoS + " AND LACTIVO = 1");
					} catch (Exception Ex) {
						vcGRLCONVIGPUE = new Vector();
					}
					
					if (vcGRLCONVIGPUE.size() > 0) {
						for (int j = 0; j < vcGRLCONVIGPUE.size(); j++) {
							vGRLCONVIGPUE = (TVGRLCONVIGPUE) vcGRLCONVIGPUE
									.get(j);
							if (vGRLCONVIGPUE.getIanosVigencia() > 0
									&& vGRLCONVIGPUE.getIMesesVigencia() > 0) {
								if (edad > vGRLCONVIGPUE.getIEdMayor()
										&& edad < vGRLCONVIGPUE.getIEdMenor()) {
									str_date = this
											.SenFindBy("select current date +"
													+ vGRLCONVIGPUE
															.getIanosVigencia()
													+ "  YEARS + "
													+ vGRLCONVIGPUE
															.getIMesesVigencia()
													+ " MONTHS from sysibm.sysdummy1");
									// Convierte String a Date
									date = (Date) formatter.parse(str_date);
									java.util.Date y2 = new java.util.Date();
									y2 = date;
									java.sql.Date CalVigencia = new java.sql.Date(
											y2.getTime());
									dtFinV = CalVigencia;
								}
							} else {
								if (vGRLCONVIGPUE.getIanosVigencia() > 0) {System.out.println("op8");
									if (edad > vGRLCONVIGPUE.getIEdMayor()
											&& edad < vGRLCONVIGPUE
													.getIEdMenor()) {
										str_date = this
												.SenFindBy("select current date +"
														+ vGRLCONVIGPUE
																.getIanosVigencia()
														+ "  YEARS from sysibm.sysdummy1");
										// Convierte String a Date
										date = (Date) formatter.parse(str_date);
										java.util.Date y2 = new java.util.Date();
										y2 = date;
										java.sql.Date CalVigencia = new java.sql.Date(
												y2.getTime());
										dtFinV = CalVigencia;
									}
								}
								if (vGRLCONVIGPUE.getIMesesVigencia() > 0) {
									if (edad > vGRLCONVIGPUE.getIEdMayor()
											&& edad < vGRLCONVIGPUE
													.getIEdMenor()) {
										str_date = this
												.SenFindBy("select current date +"
														+ vGRLCONVIGPUE
																.getIMesesVigencia()
														+ " MONTHS from sysibm.sysdummy1");
										// Convierte String a Date
										date = (Date) formatter.parse(str_date);

										java.util.Date y2 = new java.util.Date();
										y2 = date;
										java.sql.Date CalVigencia = new java.sql.Date(
												y2.getTime());
										dtFinV = CalVigencia;

									}
								}
							}
						}
					}
					
					// / Calculando nuevas Vigencias por sintoma
					// AG SA L 2016/11/08
					TDGRLCONVIGSINT dGRLCONVIGSINT = new TDGRLCONVIGSINT();
					TVGRLCONVIGSINT vGRLCONVIGSINT = new TVGRLCONVIGSINT();
					Vector vcGRLCONVIGSIN = new Vector();
					Calendar hoy3 = Calendar.getInstance();
					boolean SeAplicoVigenciaPorSintoma = false;
					try {
						String SQLQ = "select ep.icvepuesto "
								+ "from expexampuesto as ep, 	"
								+ "grlpuesto as gp " + "where "
								+ "gp.icvemdotrans = ep.icvemdotrans and "
								+ "gp.icvepuesto = ep.icvepuesto and "
								+ "gp.icvemdotrans = " + transporte + " and "
								+ "gp.icvecategoria = " + categoria + " and "
								+ "ep.icveexpediente = "
								+ vEXPDict.getICveExpediente() + " and "
								+ "ep.inumexamen = " + vEXPDict.getINumExamen();
						String puestoS = dEXPExamCat.SentenciaSQL(SQLQ);
						dtFinVSintomas = dGRLCONVIGSINT.DeterminaVigenciaPorSintoma(edad, puestoS+"", transporte, categoria, 
								vEXPDict.getICveExpediente(), vEXPDict.getINumExamen());
						//if(dtFinV > dtFinVSintomas before){
						System.out.println("dtFinV "+dtFinV+" es anterior a dtFinVSintomas="+dtFinVSintomas);
				 	    if(dtFinV.compareTo(dtFinVSintomas) > 0){
							dtFinV = dtFinVSintomas;
							SeAplicoVigenciaPorSintoma = true;
							System.out.println("dtFinV "+dtFinV+" es mayor a dtFinVSintomas = "+dtFinVSintomas);
						}
				 	    
				 	   
				 	   if (dtFinV.compareTo(dtFinVSintomas) > 0) {
				            System.out.println("Date1 is after Date2");
				        } else if (dtFinV.compareTo(dtFinVSintomas) < 0) {
				            System.out.println("Date1 is before Date2");
				        } else if (dtFinV.compareTo(dtFinVSintomas) == 0) {
				            System.out.println("Date1 is equal to Date2");
				        } else {
				            System.out.println("How to get here?");
				        }
						
						//System.out.println("Puesto = "+puestoS);
						
										
					} catch (Exception Ex) {
						vcGRLCONVIGPUE = new Vector();
					}
					
					///************************************* EXCEPCIONES A LAS REGLAS **************************************************************//
						boolean CDI = false;
						RImpedirExamenMedico rImpedirExamen = new RImpedirExamenMedico();
						CDI = rImpedirExamen.ExamenEnCDIoCenma(vEXPDict.getICveExpediente()+"", vEXPDict.getINumExamen()+"");
						
							// Calculando Vigencia Insulinodependiente solo 6 meses CDI y CENMA NO APTO OTRAS UNIDADES
							// AG SA L 2017/04/05
							boolean InsulinoDependiente = false;							
							try {
								System.out.println("****************** InsulinoDependiente *************************");
								InsulinoDependiente = rImpedirExamen.DiagnosticoDeDiabetesMellitusInsulinodependiente(vEXPDict.getICveExpediente()+"");
								dtFinVInsulinodependiente = rImpedirExamen.DeterminaVigenciaInsulinodependiente();
								if(InsulinoDependiente){
									if(CDI){
										if(rImpedirExamen.NumeroDeReglasDeNoAptitudActivas(vEXPDict.getICveExpediente()+"")==1){
										if(dtFinV.compareTo(dtFinVInsulinodependiente) > 0){
											dtFinV = dtFinVInsulinodependiente;
											System.out.println("dtFinVInsulinodependiente = "+dtFinVInsulinodependiente);
										}
										}
									}							
								}
												
							} catch (Exception Ex) {
								System.out.println("Error al calcular Vigencia Insulinodependiente");
							}
							
														
							// Calculando Vigencia USO DE ANTICOAGULANTES solo 6 meses CDI y CENMA NO APTO OTRAS UNIDADES
							// AG SA L 2017/04/07
							boolean Anticoagulantes = false;
							try {
								System.out.println("****************** Anticoagulantes *************************");
								Anticoagulantes = rImpedirExamen.DiagnosticoDeUsoDeAnticoagulantes(vEXPDict.getICveExpediente()+"");
								dtFinVAnticoagulante = rImpedirExamen.DeterminaVigenciaAnticoagulante();
								if(Anticoagulantes){
									System.out.println("****************** TRUE *************************");
									if(CDI){
										System.out.println("****************** CDI ********************");
										if(dtFinV.compareTo(dtFinVAnticoagulante) > 0){
										if(rImpedirExamen.NumeroDeReglasDeNoAptitudActivas(vEXPDict.getICveExpediente()+"")==1){
											dtFinV = dtFinVAnticoagulante;
											System.out.println("dtFinVAnticoagulante = "+dtFinVAnticoagulante);
											System.out.println("dtFinV = "+dtFinV);
										}
										}
									}							
								}
												
							} catch (Exception Ex) {
								System.out.println("Error al calcular Vigencia ANTICOAGULANTES");
							}
							
							
							
							
							
					///************************************* VIGENCIA POR DIAGNOSTICO **************************************************************//
							System.out.println("****************** VIGENCIA POR DIAGNOSTICO *****************************");
							RDiagnosticoVigencia rDiagnosticoVigencia = new RDiagnosticoVigencia();
							dtFinVDiagnostico = rDiagnosticoVigencia.FechaDeReglaDeVigenciaPorDiagnostico(vEXPDict.getICveExpediente()+"", vEXPDict.getINumExamen()+"", IUnidadMedica, IModulo);
							if(dtFinV.compareTo(dtFinVDiagnostico) > 0){
								dtFinV = dtFinVDiagnostico;
								System.out.println("dtFinVDiagnostico = "+dtFinVDiagnostico);
							}
							System.out.println("****************** FIN VIGENCIA POR DIAGNOSTICO *************************");
					
							
							
							
							/////Validar Vigencias para no aptitud////
							int Aptitud = vExamCat.getLDictamen();
							System.out.println("/dtFinV = "+dtFinV);
							System.out.println("Fecha.TodaySQL() = "+Fecha.TodaySQL());
							if (String.valueOf(dtFinV.toString()).contentEquals(String.valueOf(Fecha.TodaySQL()))) {
								Aptitud = 0;
								System.out.println("dtFinV = Fecha.TodaySQL()");
							}
									
							
				/*	
							
					System.out.println("op12");
					 System.out.print(categoria
					 +"===================== "+dtFinV +
					 "===================== ");*/
					hCamposQ1.clear();
				
					
					
					//hCamposQ1.put("lDictamen",String.valueOf(vExamCat.getLDictamen()));
					hCamposQ1.put("lDictamen",String.valueOf(Aptitud)); //// 26/06/2017 L.A.S.
					// hCamposQ1.put("dtInicioVig", "'" +
					// vExamA.getDtSolicitado() + "'"); Se modifico para
					// insertar correctamente el inicio de vigencia
					hCamposQ1.put("dtInicioVig", "'" + Fecha.TodaySQL() + "'");

					hCamposQ1.put("dtFinVig", "'" + dtFinV + "'");
					hCamposQ1.put("lDitamEm",
							String.valueOf(vEXPDict.getICveUsuDictamen()));
					hCamposQ1
							.put("dtDictaminado", "'" + Fecha.TodaySQL() + "'");
					cCondicion.append(" iCveExpediente    = ")
							.append(vExamCat.getICveExpediente())
							.append(" and iNumExamen    = ")
							.append(vExamCat.getINumExamen())
							.append(" and iCveMdoTrans    = ")
							.append(vExamCat.getICveMdoTrans())
							.append(" and iCveCategoria = ")
							.append(vExamCat.getICveCategoria());
					tmp[iNumQSU] = new QueryStructure(hCamposQ1, " EXPExamCat",
							cCondicion.toString(), QueryStructure.UPDATE);
					aQueryI.add(tmp[iNumQSU]);

					// Realizar la buqueda en el Catalogo de No Aptos para la
					// categoria indicada
					hCamposQ1.clear();
					cCondicion = new StringBuffer();
					hCamposQ1.put("*", "");
					cCondicion.append(" iCvePersonal      = ")
							.append(vExamCat.getICveExpediente())
							.append(" and iCveMdoTrans  = ")
							.append(vExamCat.getICveMdoTrans())
							.append(" and iCveCategoria = ")
							.append(vExamCat.getICveCategoria())
							.append(" and lVigente      = 1 ");
					qBuscar01 = new QueryStructure(hCamposQ1,
							" PERCatalogoNoAp", cCondicion.toString(),
							QueryStructure.SELECT);
					aQueryB.add(qBuscar01);
					// Realizar la busqueda en el Catalogo de No Aptos para la
					// categorÃ?Â­a desconocida
					cCondicion = new StringBuffer();
					cCondicion
							.append(" iCvePersonal      = ")
							.append(vExamCat.getICveExpediente())
							.append(" and iCveMdoTrans  = ")
							.append(vExamCat.getICveMdoTrans())
							.append(" and iCveCategoria = ")
							.append(VParametros
									.getPropEspecifica("EPICatDesconocida"))
							.append(" and lVigente      = 1 ");
					qBuscar02 = new QueryStructure(hCamposQ1,
							" PERCatalogoNoAp", cCondicion.toString(),
							QueryStructure.SELECT);
					aQueryB.add(qBuscar02);
					// Realizar la bÃ?Âºsqueda en PERLicencias para la
					// categoria
					cCondicion = new StringBuffer();
					cCondicion.append(" iCvePersonal      = ")
							.append(vExamCat.getICveExpediente())
							.append(" and iCveMdoTrans  = ")
							.append(vExamCat.getICveMdoTrans())
							.append(" and iCveCategoria = ")
							.append(vExamCat.getICveCategoria());
					qBuscar03 = new QueryStructure(hCamposQ1, " PERLicencia ",
							cCondicion.toString(), QueryStructure.SELECT);
					aQueryB.add(qBuscar03);
					// Realizar la busqueda en PERLicencias para la
					// categorÃ?Â­a
					// desconocida
					cCondicion = new StringBuffer();
					cCondicion
							.append(" iCvePersonal      = ")
							.append(vExamCat.getICveExpediente())
							.append(" and iCveMdoTrans  = ")
							.append(vExamCat.getICveMdoTrans())
							.append(" and iCveCategoria = ")
							.append(VParametros
									.getPropEspecifica("EPICatDesconocida"));
					qBuscar04 = new QueryStructure(hCamposQ1, " PERLicencia ",
							cCondicion.toString(), QueryStructure.SELECT);
					aQueryB.add(qBuscar04);
					// Buscar consecutivo para el catalogo de No Aptos para la
					// categoria especifica
					System.out.println("op13");
					if (vExamCat.getLDictamen() == 0) {System.out.println("op14");
						hCamposQ1.clear();
						cCondicion = new StringBuffer();
						hCamposQ1
								.put("max(ICVECATALOGONOAP) + 1 AS ICVECATALOGONOAP",
										"");
						cCondicion.append(" iCvePersonal      = ")
								.append(vExamCat.getICveExpediente())
								.append(" and iCveMdoTrans  = ")
								.append(vExamCat.getICveMdoTrans())
								.append(" and iCveCategoria = ")
								.append(vExamCat.getICveCategoria());
						qBuscar05 = new QueryStructure(hCamposQ1,
								" PERCatalogoNoAp ", cCondicion.toString(),
								QueryStructure.SELECT);
						aQueryB.add(qBuscar05);
					}
					// Ejecutar las Busquedas para obtener los datos y generar
					// los siguientes Insert's
					// y update's
					aQueryB = qQuery.manageTransaction(aQueryB);
					// Se analiza el resultado para la generaciÃ³n
					if (aQueryB != null && aQueryB.size() > 0) {System.out.println("op15");
						// *************************** ACTUALIZACIONES
						// ******************************** //
						// Campos para Actualizar CatÃ?Â¡logo de No Aptos
						hCamposQ1.clear();
						hCamposQ1.put("lVigente", "0");
						hCamposQ1.put("dtFin", "'" + Fecha.TodaySQL() + "'");
						hCamposQ1.put("iCveUsuBaja",
								String.valueOf(vEXPDict.getICveUsuDictamen()));
						// Resultado del Catalogo de No Aptos por categoria
						if (((ArrayList) ((ArrayList) aQueryB.get(0))) != null
								&& ((ArrayList) ((ArrayList) aQueryB.get(0)))
										.size() > 0) {System.out.println("op16");
							vResultado = new TVDinRep02();
							vResultado = (TVDinRep02) ((ArrayList) ((ArrayList) aQueryB
									.get(0))).get(0);
							// Generar update para el CatÃ?Â¡logo de No Aptos
							// de
							// la categorÃ?Â­a especÃ?Â­fica
							cCondicion = new StringBuffer();
							cCondicion.append(" iCvePersonal      = ")
									.append(vExamCat.getICveExpediente())
									.append(" and iCveMdoTrans  = ")
									.append(vExamCat.getICveMdoTrans())
									.append(" and iCveCategoria = ")
									.append(vExamCat.getICveCategoria())
									.append(" and lVigente      = 1 ");
							iNumQSU++;
							tmp[iNumQSU] = new QueryStructure(hCamposQ1,
									" PERCatalogoNoAp", cCondicion.toString(),
									QueryStructure.UPDATE);
							aQueryI.add(tmp[iNumQSU]);
						} // CatÃ?Â¡logo No Aptos - Categoria Especifica

						// Resultado del Catalogo de No Aptos para Categoria
						// desconocida
						if (((ArrayList) ((ArrayList) aQueryB.get(1))) != null
								&& ((ArrayList) ((ArrayList) aQueryB.get(1)))
										.size() > 0) {System.out.println("op17");
							vResultado = new TVDinRep02();
							vResultado = (TVDinRep02) ((ArrayList) ((ArrayList) aQueryB
									.get(1))).get(0);
							// Generar update para el Catalogo de No Aptos de la
							// categoria desconocida
							cCondicion = new StringBuffer();
							cCondicion
									.append(" iCvePersonal      = ")
									.append(vExamCat.getICveExpediente())
									.append(" and iCveMdoTrans  = ")
									.append(vExamCat.getICveMdoTrans())
									.append(" and iCveCategoria = ")
									.append(VParametros
											.getPropEspecifica("EPICatDesconocida"))
									.append(" and lVigente      = 1 ");
							iNumQSU++;
							tmp[iNumQSU] = new QueryStructure(hCamposQ1,
									" PERCatalogoNoAp", cCondicion.toString(),
									QueryStructure.UPDATE);
							aQueryI.add(tmp[iNumQSU]);
						} // Catalogo No Aptos - Categoria Desconocida
							// Resultado de PERLicencias
						hCamposQ1.clear();
						// 15 dic 2010
						if (((ArrayList) ((ArrayList) aQueryB.get(2))) != null
								&& ((ArrayList) ((ArrayList) aQueryB.get(2)))
										.size() > 0) {System.out.println("op18");
							vResultado = new TVDinRep02();
							vResultado = (TVDinRep02) ((ArrayList) ((ArrayList) aQueryB
									.get(2))).get(0);
							// Generar update para PERLicencias para la
							// categorÃ?Â­a especÃ?Â­fica
							cCondicion = new StringBuffer();
							cCondicion.append(" iCvePersonal      = ")
									.append(vExamCat.getICveExpediente())
									.append(" and iNumexamen  = ")
									.append(vExamCat.getINumExamen())
									.append(" and iCveMdoTrans  = ")
									.append(vExamCat.getICveMdoTrans())
									.append(" and iCveCategoria = ")
									.append(vExamCat.getICveCategoria());
							iNumQSU++;
							tmp[iNumQSU] = new QueryStructure(hCamposQ1,
									" PERLicencia", cCondicion.toString(),
									QueryStructure.DELETE);
							aQueryI.add(tmp[iNumQSU]);
						} // PerLicencias - CategorÃ?Â­a especÃ?Â­fica
							// Resultado de PERLicencias CategorÃ?Â­a
							// desconocida
						if (((ArrayList) ((ArrayList) aQueryB.get(3))) != null
								&& ((ArrayList) ((ArrayList) aQueryB.get(3)))
										.size() > 0) {System.out.println("op19");
							vResultado = new TVDinRep02();
							vResultado = (TVDinRep02) ((ArrayList) ((ArrayList) aQueryB
									.get(3))).get(0);
							// Generar update para PERLicencias para la
							// categorÃ?Â­a especÃ?Â­fica
							cCondicion = new StringBuffer();
							cCondicion
									.append(" iCvePersonal      = ")
									.append(vExamCat.getICveExpediente())
									.append(" and iNumexamen  = ")
									.append(vExamCat.getINumExamen())
									.append(" and iCveMdoTrans  = ")
									.append(vExamCat.getICveMdoTrans())
									.append(" and iCveCategoria = ")
									.append(VParametros
											.getPropEspecifica("EPICatDesconocida"));
							iNumQSU++;
							tmp[iNumQSU] = new QueryStructure(hCamposQ1,
									" PERLicencia", cCondicion.toString(),
									QueryStructure.DELETE);
							aQueryI.add(tmp[iNumQSU]);
						} // 15 dic 2010 */
							// PerLicencias - CategorÃ?Â­a desconocida
							// Consecutivo del CatÃ?Â¡logo de No Aptos
						if (vExamCat.getLDictamen() == 0) {System.out.println("op20");
							if (((ArrayList) ((ArrayList) aQueryB.get(4))) != null
									&& ((ArrayList) ((ArrayList) aQueryB.get(4)))
											.size() > 0) {System.out.println("op21");
								vResultado = new TVDinRep02();
								vResultado = (TVDinRep02) ((ArrayList) ((ArrayList) aQueryB
										.get(4))).get(0);
								if (vResultado.getInt("ICVECATALOGONOAP") == 0)
									iConsCNA = 1;
								else
									iConsCNA = vResultado
											.getInt("ICVECATALOGONOAP");
							}
						} // Consecutivo del Catalogo de No Aptos

					}// Existen actualizaciones para la categorÃ?Â­a
						// ************************ SENTENCIAS POR CADA
						// CATEGORÃ?Â­A *********************** //
						// Si el Dictamen es No Apto se agrega al catÃ?Â¡logo
						// de
						// No Aptos
					if (vExamCat.getLDictamen() == 0) {System.out.println("op22");
						hCamposQ1.clear();
						hCamposQ1.put("iCvePersonal",
								String.valueOf(vExamCat.getICveExpediente()));
						hCamposQ1.put("iCveMdoTrans",
								String.valueOf(vExamCat.getICveMdoTrans()));
						hCamposQ1.put("iCveCategoria",
								String.valueOf(vExamCat.getICveCategoria()));
						hCamposQ1.put("ICVECATALOGONOAP",
								String.valueOf(iConsCNA));
						hCamposQ1.put("lVigente", "1");
						hCamposQ1.put("dtInicio", "'" + Fecha.TodaySQL() + "'");
						hCamposQ1.put("iCveUsuAgrega",
								String.valueOf(vEXPDict.getICveUsuDictamen()));
						hCamposQ1.put("dtCapIni", "'" + Fecha.TodaySQL() + "'");
						hCamposQ1.put("iCveUniMed",
								String.valueOf(vExamA.getICveUniMed()));
						hCamposQ1.put("iCveMotivoNoAp", "1");
						hCamposQ1.put("iPeriodo", "0");
						iNumQSU++;
						tmp[iNumQSU] = new QueryStructure(hCamposQ1,
								" PERCatalogoNoAp", "", QueryStructure.INSERT);
						aQueryI.add(tmp[iNumQSU]);
					}
					// Crear registro de PERLicencias
					System.out.println("op23");
					hCamposQ1.clear();
					hCamposQ1.put("iCvePersonal",
							String.valueOf(vExamCat.getICveExpediente()));
					hCamposQ1.put("iCveMdoTrans",
							String.valueOf(vExamCat.getICveMdoTrans()));
					hCamposQ1.put("iCveCategoria",
							String.valueOf(vExamCat.getICveCategoria()));
					hCamposQ1.put("cConstancia", "''");
					hCamposQ1.put("iNumExamen",
							String.valueOf(vExamA.getINumExamen()));
					hCamposQ1.put("lDictamen",
							String.valueOf(vExamCat.getLDictamen()));
					hCamposQ1.put("cLicencia", "''");
					// hCamposQ1.put("dtIniVigencia", "'" +
					// vExamA.getDtSolicitado() + "'"); Se modifico esta parte
					// para que se registre en correctamente el inicio de
					// vigencia en PERLICENCIA
					hCamposQ1
							.put("dtIniVigencia", "'" + Fecha.TodaySQL() + "'");
					hCamposQ1.put("dtFinVigencia", "'" + dtFinV + "'");
					iNumQSU++;
					tmp[iNumQSU] = new QueryStructure(hCamposQ1,
							" PERLicencia", "", QueryStructure.INSERT);
					aQueryI.add(tmp[iNumQSU]);
				}// CategorÃ?Â­as a Actualizar
					// Generar la actualizaciÃ³n del ExamAplica
				if (vExamA.getLDictaminado() == 0) {System.out.println("op24");
					cCondicion = new StringBuffer();
					// Actualizar PERDatos
					hCamposQ1.clear();
					hCamposQ1.put("cGpoSang", "'" + vEXPDict.getCGpoSang()
							+ "'");
					hCamposQ1.put("lRH", String.valueOf(vEXPDict.getLRH()));
					hCamposQ1.put("lUsaLentes",
							String.valueOf(vEXPDict.getLUsaLentes()));
					hCamposQ1.put("lAereo",
							String.valueOf(vEXPDict.getLAereo()));
					hCamposQ1.put("lContacto",
							String.valueOf(vEXPDict.getLContacto()));
					hCamposQ1.put("lHipertension",
							String.valueOf(vEXPDict.getLHipertension()));
					hCamposQ1.put("lDiabetes",
							String.valueOf(vEXPDict.getLDiabetes()));
					cCondicion.append("iCvePersonal = ").append(
							vEXPDict.getICveExpediente());
					iNumQSU++;
					tmp[iNumQSU] = new QueryStructure(hCamposQ1, " PERDatos",
							cCondicion.toString(), QueryStructure.UPDATE);
					aQueryI.add(tmp[iNumQSU]);

					// Actualizar ExamAplica
					cCondicion = new StringBuffer();
					hCamposQ1.put("lDictaminado", "1");
					hCamposQ1.put("dtDictamen", "'" + Fecha.TodaySQL() + "'");
					hCamposQ1.put("iCveUsuDictamen",
							String.valueOf(vEXPDict.getICveUsuDictamen()));
					cCondicion.append("iCveExpediente = ")
							.append(vEXPDict.getICveExpediente())
							.append("  and iNumExamen     = ")
							.append(vEXPDict.getINumExamen());
					iNumQSU++;
					tmp[iNumQSU] = new QueryStructure(hCamposQ1,
							" EXPExamAplica", cCondicion.toString(),
							QueryStructure.UPDATE);
					aQueryI.add(tmp[iNumQSU]);
					System.out.println("op25");
				} // El examen no estÃ?Â¡ dictaminado
					// Debug
					// for(int j=0; j < aQueryI.size(); j++){
					// System.out.println("Query " + j + ":" +
					// qQuery.generateQuery((QueryStructure)aQueryI.get(j)));
					// }
					// }
				System.out.println("op26");
				// Ejecutar las sentencias de ActualizaciÃ³n e inserciÃ³n.
				//System.out.println(aQueryI.size());
				//System.out.println(aQueryI.get(1));
				//System.out.println(aQueryI.get(2));
				//System.out.println(aQueryI.get(3));
				//System.out.println(aQueryI.get(4));
				//System.out.println(aQueryI.get(5));
				
				qQuery.manageTransaction(aQueryI);
				lResultado = true;
			} // Exam Aplica
		} // try
		catch (Exception ex) {
			ex.printStackTrace();
			lResultado = false;
		} // catch
		finally {
			return lResultado;
		} // finally
	} // Metodo

	/**
	 * Metodo findByExpDicSer
	 */
	public Vector getExpDicSer(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " Select a.iCveExpediente, a.iNumExamen, "
					+ "        a.iCveMdoTrans, a.iCveCategoria,  "
					+ "        a.iCveServicio, a.lDictamen, b.cDscCategoria, c.cDscMdoTrans "
					+ " From   EXPDictamenServ a, GRLCategoria b, GRLMdoTrans c "
					+ " Where  a.iCveExpediente =  " + cExpediente
					+ " And    a.iNumExamen = " + cExamen
					+ " And    a.iCveServicio = " + cServicio
					+ " And    a.iCveCategoria = b.iCveCategoria"
					+ " And    a.iCveMdoTrans = b.iCveMdoTrans"
					+ " And    a.iCveMdoTrans = c.iCveMdoTrans";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setICveExpediente(rset.getInt(1));
				vEXPDictamenServ.setINumExamen(rset.getInt(2));
				vEXPDictamenServ.setICveMdoTrans(rset.getInt(3));
				vEXPDictamenServ.setICveCategoria(rset.getInt(4));
				vEXPDictamenServ.setICveServicio(rset.getInt(5));
				vEXPDictamenServ.setLDictamen(rset.getInt(6));
				vEXPDictamenServ.setCDscCategoria(rset.getString(7));
				vEXPDictamenServ.setCDscMdoTrans(rset.getString(8));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("findByExpDicSer", ex);
			throw new DAOException("findByExpDicSer");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public String SenFindBy(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		String Regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regresa = rset.getString(1);
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
			return Regresa;
		}
	}

	/**
	 * MÃ©todo Find By All
	 */
	public int CountNoApt(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRegSin = new Vector();
		int regresa = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRegSin vEXPRegSin;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = cWhere;

			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getInt(1);
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
			return regresa;
		}
	}

}
