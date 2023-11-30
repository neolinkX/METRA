package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de PERCatalogoNoAp DAO
 * </p>   
 * <p>
 * Description: DAO Tabla PERCatalogoNoAp
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

public class TDPERCatalogoNoAp extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERCatalogoNoAp() {
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author Marco A. Gonz�lez Paz
	 * @parameter cWhere Recibe la condicion para buscar un registro especifico
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		//System.out.println("FindByAll -1");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERCatalogoNoAp = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERCatalogoNoAp vPERCatalogoNoAp;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "iCveMdoTrans,"
					+ "iCveCategoria," + "iCveCatalogoNoAp," + "lVigente,"
					+ "dtInicio," + "dtFin," + "iPeriodo," + "iCveMotivoNoAp"
					+ " from PERCatalogoNoAp ";
			if (cWhere != null) {
				cSQL = cSQL + cWhere;
			}
			cSQL = cSQL + " order by iCvePersonal";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERCatalogoNoAp = new TVPERCatalogoNoAp();
				vPERCatalogoNoAp.setICvePersonal(rset.getInt(1));
				vPERCatalogoNoAp.setICveMdoTrans(rset.getInt(2));
				vPERCatalogoNoAp.setICveCategoria(rset.getInt(3));
				vPERCatalogoNoAp.setICveCatalogoNoAp(rset.getInt(4));
				vPERCatalogoNoAp.setLVigente(rset.getInt(5));
				vPERCatalogoNoAp.setDtInicio(rset.getDate(6));
				vPERCatalogoNoAp.setDtFin(rset.getDate(7));
				vPERCatalogoNoAp.setIPeriodo(rset.getInt(8));
				vPERCatalogoNoAp.setICveMotivoNoAp(rset.getInt(9));
				vcPERCatalogoNoAp.addElement(vPERCatalogoNoAp);
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
			return vcPERCatalogoNoAp;
		}
	}

	/**
	 * Metodo FindDsc
	 */
	public Vector FindDsc(String cWhere, String cOrderBy) throws DAOException {
		//System.out.println("FindDsc -1");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERCatalogoNoAp = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERCatalogoNoAp vPERCatalogoNoAp;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "SELECT " + " EXP.TIDIAGNOSTICO, "
					+ " EA.ICVEEXPEDIENTE AS iCvePersonal,"
					+ " GC.iCveMdoTrans," + " GT.CDSCMDOTRANS,"
					+ " GC.iCveCategoria," + " GC.CDSCCATEGORIA,"
					+ " EA.INUMEXAMEN AS iCveCatalogoNoAp,"
					+ " EA.INUMEXAMEN AS lVigente,"
					+ " EA.DTSOLICITADO AS dtInicio,"
					+ " EA.DTDICTAMEN AS dtFin,"
					+ " EA.INUMEXAMEN AS iPeriodo," + " GM.CDSCMOTIVO,"
					+ " GU.CDSCUNIMED AS cDscUniMed,"
					+ " MD.CDSCDIAGNOSTICO AS cDscDiagnostico,"
					+ " PE.CNOMBRE, " + " PE.CAPPATERNO," + " PE.CAPMATERNO"
					+ " FROM " + " EXPEXAMAPLICA AS EA," + " PERDATOS AS PE,"
					+ " GRLMOTIVO AS GM," + " GRLMDOTRANS AS GT, "
					+ " GRLCATEGORIA AS GC," + " MEDDIAGNOSTICO AS MD,"
					+ " EXPDIAGNOSTICO AS EXP," + " GRLUNIMED AS GU,"
					+ " PERCatalogoNoAp as PCN," + " EXPEXAMCAT AS EC" + cWhere;
			// +cOrderBy;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			int anteriorExp = 0;
			int anteriorExa = 0;
			while (rset.next()) {
				int numExpediente = rset.getInt(2);
				int numExamen = rset.getInt(7);
				if (numExpediente != anteriorExp || numExamen != anteriorExa) {
					vPERCatalogoNoAp = new TVPERCatalogoNoAp();
					vPERCatalogoNoAp.setICvePersonal(rset.getInt(2));
					vPERCatalogoNoAp.setICveMdoTrans(rset.getInt(3));
					vPERCatalogoNoAp.setCDscMdoTrans(rset.getString(4));
					vPERCatalogoNoAp.setICveCategoria(rset.getInt(5));
					vPERCatalogoNoAp.setCDscCategoria(rset.getString(6));
					vPERCatalogoNoAp.setICveCatalogoNoAp(rset.getInt(7));
					vPERCatalogoNoAp.setLVigente(rset.getInt(8));
					vPERCatalogoNoAp.setDtInicio(rset.getDate(9));
					vPERCatalogoNoAp.setDtFin(rset.getDate(10));
					String fecha = rset.getString(9);
					fecha = "" + fecha.charAt(0) + "" + fecha.charAt(1) + ""
							+ fecha.charAt(2) + "" + fecha.charAt(3) + "";
					int perdiodo = Integer.parseInt(fecha.trim());
					vPERCatalogoNoAp.setIPeriodo(perdiodo);
					vPERCatalogoNoAp.setCDscMotivo(rset.getString(12));
					vPERCatalogoNoAp.setCDscUniMed(rset.getString(13));
					vPERCatalogoNoAp.setCDscRubroNoAp(rset.getString(14));
					vPERCatalogoNoAp.setCNombre(rset.getString(15));
					vPERCatalogoNoAp.setCApPaterno(rset.getString(16));
					vPERCatalogoNoAp.setCApMaterno(rset.getString(17));

					java.util.Date dtFechaTmp = new java.util.Date();
					SimpleDateFormat formato = new SimpleDateFormat(
							"dd/MM/yyyy");
					dtFechaTmp = rset.getDate(9);
					if (dtFechaTmp != null) {
						vPERCatalogoNoAp.setCDtInicio(formato
								.format(dtFechaTmp));
					} else {
						vPERCatalogoNoAp.setCDtInicio("");
					}
					dtFechaTmp = rset.getDate(10);
					if (dtFechaTmp != null) {
						vPERCatalogoNoAp.setCDtFin(formato.format(dtFechaTmp));
					} else {
						vPERCatalogoNoAp.setCDtFin("");
					}

					vcPERCatalogoNoAp.addElement(vPERCatalogoNoAp);
				}
				anteriorExp = numExpediente;
				anteriorExa = numExamen;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return vcPERCatalogoNoAp;
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
			TVPERCatalogoNoAp vPERCatalogoNoAp = (TVPERCatalogoNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ////////////////////////////////////////

			String cSQLMax = "select max(iCveCatalogoNoAp) from PERCatalogoNoAp "
					+ " where iCvePersonal =  "
					+ vPERCatalogoNoAp.getICvePersonal()
					+ " and iCveMdoTrans = "
					+ vPERCatalogoNoAp.getICveMdoTrans()
					+ " and iCveCategoria = "
					+ vPERCatalogoNoAp.getICveCategoria();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			cSQL = "insert into PERCatalogoNoAp(" + "iCveUniMed,"
					+ "iCvePersonal," + "iCveMdoTrans," + "iCveCategoria,"
					+ "iCveCatalogoNoAp," + "lVigente," + "dtInicio,"
					+ "iPeriodo," + "iCveMotivoNoAp," + "iCveUsuAgrega,"
					+ "iNumExamen"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vPERCatalogoNoAp.getICveUniMed());
			pstmt.setInt(2, vPERCatalogoNoAp.getICvePersonal());
			pstmt.setInt(3, vPERCatalogoNoAp.getICveMdoTrans());
			pstmt.setInt(4, vPERCatalogoNoAp.getICveCategoria());
			pstmt.setInt(5, iMax + 1);
			pstmt.setInt(6, vPERCatalogoNoAp.getLVigente());
			pstmt.setDate(7, vPERCatalogoNoAp.getDtInicio());
			pstmt.setInt(8, vPERCatalogoNoAp.getIPeriodo());
			pstmt.setInt(9, vPERCatalogoNoAp.getICveMotivoNoAp());
			pstmt.setInt(10, vPERCatalogoNoAp.getICveUsuAgrega());
			pstmt.setInt(11, vPERCatalogoNoAp.getINumExamen());
			pstmt.executeUpdate();
			cClave = "" + vPERCatalogoNoAp.getICvePersonal();
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
			TVPERCatalogoNoAp vPERCatalogoNoAp = (TVPERCatalogoNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERCatalogoNoAp" + " set lVigente= ?, "
					+ "dtInicio= ?, " + "dtFin= ?, " + "iPeriodo= ?, "
					+ "iCveMotivoNoAp= ? " + "where iCvePersonal = ? "
					+ "and iCveMdoTrans = ?" + "and iCveCategoria = ?"
					+ " and iCveCatalogoNoAp = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERCatalogoNoAp.getLVigente());
			pstmt.setDate(2, vPERCatalogoNoAp.getDtInicio());
			pstmt.setDate(3, vPERCatalogoNoAp.getDtFin());
			pstmt.setInt(4, vPERCatalogoNoAp.getIPeriodo());
			pstmt.setInt(5, vPERCatalogoNoAp.getICveMotivoNoAp());
			pstmt.setInt(6, vPERCatalogoNoAp.getICvePersonal());
			pstmt.setInt(7, vPERCatalogoNoAp.getICveMdoTrans());
			pstmt.setInt(8, vPERCatalogoNoAp.getICveCategoria());
			pstmt.setInt(9, vPERCatalogoNoAp.getICveCatalogoNoAp());
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
			TVPERCatalogoNoAp vPERCatalogoNoAp = (TVPERCatalogoNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERCatalogoNoAp" + " where iCvePersonal = ?"
					+ " and iCveMdoTrans = ?" + " and iCveCategoria = ?"
					+ " and iCveCatalogoNoAp = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERCatalogoNoAp.getICvePersonal());
			pstmt.setInt(2, vPERCatalogoNoAp.getICveMdoTrans());
			pstmt.setInt(3, vPERCatalogoNoAp.getICveCategoria());
			pstmt.setInt(4, vPERCatalogoNoAp.getICveCatalogoNoAp());
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
				warn("delete.closePERCatalogoNoAp", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo estaEnNoAptos
	 */
	public boolean estaEnNoAptos(int iCvePersonal, int iCveMdotrans,
			int iCveCategoria) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean bTmp = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVPERCatalogoNoAp vPERCatalogoNoAp;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select iCvePersonal from PERCatalogoNoAp where lVigente=1 "
					+ "and iCvePersonal=? and iCveMdoTrans=? and iCveCategoria=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iCvePersonal);
			pstmt.setInt(2, iCveMdotrans);
			pstmt.setInt(3, iCveCategoria);
			rset = pstmt.executeQuery();
			bTmp = rset.next();
		} catch (Exception ex) {
			warn("estaEnNoAptos", ex);
			throw new DAOException("estaEnNoAptos");
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
				warn("estaEnNoAptos.close", ex2);
			}
			return bTmp;
		}
	}

	/**
	 * Metodo Disable
	 */
	public Object disable(Connection conGral, Object obj) throws DAOException {
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
			TVPERCatalogoNoAp vPERCatalogoNoAp = (TVPERCatalogoNoAp) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERCatalogoNoAp" + " set lVigente = ?, "
					+ " iCveUsuBaja = ?, " + " dtFin = ? "
					+ "where iCvePersonal = ? " + "and iCveMdoTrans = ? "
					+ "and iCveCategoria = ? " + "and iCveMotivoNoAp = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vPERCatalogoNoAp.getICveUsuBaja());
			pstmt.setDate(3, vPERCatalogoNoAp.getDtFin());
			pstmt.setInt(4, vPERCatalogoNoAp.getICvePersonal());
			pstmt.setInt(5, vPERCatalogoNoAp.getICveMdoTrans());
			pstmt.setInt(6, vPERCatalogoNoAp.getICveCategoria());
			pstmt.setInt(7, vPERCatalogoNoAp.getICveMotivoNoAp());
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
				warn("disable.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo FindDsc
	 */
	public Vector FindNoAptitudes(String cWhere, String cOrderBy)
			throws DAOException {
		//System.out.println("FindNoAptitudes -1");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERCatalogoNoAp = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERCatalogoNoAp vPERCatalogoNoAp;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " select PERCatalogoNoAp.iCvePersonal,     "
					+ "        PERCatalogoNoAp.iCveUniMed,       "
					+ "        GRLUniMed.cDscUniMed,             "
					+ "        PERCatalogoNoAp.iCveMdoTrans,     "
					+ "        GRLMdoTrans.cDscMdoTrans,         "
					+ "        PERCatalogoNoAp.iCveCategoria,    "
					+ "        GRLCategoria.cDscCategoria,       "
					+ "        PERCatalogoNoAp.iCveCatalogoNoAp, "
					+ "        PERCatalogoNoAp.lVigente,         "
					+ "        PERCatMotRubNoAp.iCveMotivoNoAp,  "
					+ "        PERMotivoNoAp.cDscMotivoNoAp,     "
					+ "        PERCatMotRubNoAp.iCveRubroNoAp,   "
					+ "        PERRubroNoAp.cDscRubroNoAp,        "
					+ "        PERCatalogoNoAp.iNumExamen        "
					
					+ " from PERCatalogoNoAp                     "
					+ "      join GRLUniMed    on GRLUniMed.iCveUniMed       = PERCatalogoNoAp.iCveUniMed    "
					+ "      join GRLMdoTrans  on GRLMdoTRans.iCveMdoTrans   = PERCatalogoNoAp.iCveMdoTrans  "
					+ "      join GRLCategoria on GRLCategoria.iCveMdoTrans  = PERCatalogoNoAp.iCveMdoTrans  "
					+ "                       and GRLCategoria.iCveCategoria = PERCatalogoNoAp.iCveCategoria "
					+ " left join PERCatMotRubNoAp on PERCatMotRubNoAp.iCvePersonal     = PERCatalogoNoAp.iCvePersonal  "
					+ "                           and PERCatMotRubNoAp.iCveMdoTrans     = PERCatalogoNoAp.iCveMdoTrans  "
					+ "                           and PERCatMotRubNoAp.iCveCategoria    = PERCatalogoNoAp.iCveCategoria "
					+ "                           and PERCatMotRubNoAp.iCveCatalogoNoAp = PERCatalogoNoAp.iCveCatalogoNoAp "
					+ " left join PERRubroNoAp on PERRubroNoAp.iCveMotivoNoAp = PERCatMotRubNoAp.iCveMotivoNoAp "
					+ "                       and PERRubroNoAp.iCveRubroNoAp  = PERCatMotRubNoAp.iCveRubroNoAp  "
					+ " left join PERMotivoNoAp on PERMotivoNoAp.iCveMotivoNoAp = PERRubroNoAp.iCveMotivoNoAp   "
					+ cWhere + cOrderBy;

			//System.out.println(cSQL);
			
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERCatalogoNoAp = new TVPERCatalogoNoAp();
				vPERCatalogoNoAp.setICvePersonal(rset.getInt(1));
				vPERCatalogoNoAp.setICveUniMed(rset.getInt(2));
				vPERCatalogoNoAp.setCDscUniMed(rset.getString(3));
				vPERCatalogoNoAp.setICveMdoTrans(rset.getInt(4));
				vPERCatalogoNoAp.setCDscMdoTrans(rset.getString(5));
				vPERCatalogoNoAp.setICveCategoria(rset.getInt(6));
				vPERCatalogoNoAp.setCDscCategoria(rset.getString(7));
				vPERCatalogoNoAp.setICveCatalogoNoAp(rset.getInt(8));
				vPERCatalogoNoAp.setLVigente(rset.getInt(9));
				vPERCatalogoNoAp.setICveMotivoNoAp(rset.getInt(10));
				vPERCatalogoNoAp.setCDscMotivoNoAp(rset.getString(11));
				vPERCatalogoNoAp.setICveRubroNoAp(rset.getInt(12));
				vPERCatalogoNoAp.setCDscRubroNoAp(rset.getString(13));
				vPERCatalogoNoAp.setINumExamen(rset.getInt(14));
				vcPERCatalogoNoAp.addElement(vPERCatalogoNoAp);
			}
		} catch (Exception ex) {
			warn("FindNoAptitudes", ex);
			throw new DAOException("FindNoAptitudes");
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
				warn("FindNoAptitudes.close", ex2);
			}
			return vcPERCatalogoNoAp;
		}
	}

	public Vector AgregadosNoAp(String cWhere, String cOrderBy)
			throws DAOException {
		//System.out.println("AgregadosNoAp -1");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERCatalogoNoAp = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERCatalogoNoAp vPERCatalogoNoAp;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "SELECT ICVEPERSONAL, DTINICIO, G.CDSCUNIMED,T.CDSCMDOTRANS, C.CDSCCATEGORIA  "
					+ " FROM PERCATALOGONOAP PER, GRLUNIMED G, GRLMDOTRANS T, GRLCATEGORIA C "
					+ " WHERE NOT EXISTS "
					+ "                  (SELECT ICVEEXPEDIENTE FROM EXPEXAMCAT EXA "
					+ "                  WHERE PER.ICVEPERSONAL = EXA.ICVEEXPEDIENTE AND PER.DTINICIO = EXA.DTINICIOVIG) "
					+ " AND   PER.ICVEUNIMED = G.ICVEUNIMED "
					+ " AND   PER.ICVEMDOTRANS = T.ICVEMDOTRANS "
					+ " AND   PER.ICVECATEGORIA = C.ICVECATEGORIA "
					+ " AND   PER.ICVEMDOTRANS = C.ICVEMDOTRANS AND  " +
					// " AND          ICVEPERSONAL    >     1"+
					cWhere + cOrderBy;

			//System.out.println(cSQL);
			
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERCatalogoNoAp = new TVPERCatalogoNoAp();
				vPERCatalogoNoAp.setICvePersonal(rset.getInt(1));
				vPERCatalogoNoAp.setDtInicio(rset.getDate(2));
				vPERCatalogoNoAp.setCDscUniMed(rset.getString(3));
				vPERCatalogoNoAp.setCDscMdoTrans(rset.getString(4));
				vPERCatalogoNoAp.setCDscCategoria(rset.getString(5));
				vcPERCatalogoNoAp.addElement(vPERCatalogoNoAp);
			}
		} catch (Exception ex) {
			warn("FindNoAptitudes", ex);
			throw new DAOException("FindNoAptitudes");
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
				warn("FindNoAptitudes.close", ex2);
			}
			return vcPERCatalogoNoAp;
		}
	}
	
	@SuppressWarnings("finally")
	public int ExamenMaximo(String cICvePersonal)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int regresa = 0;
		int contadorexa = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			contadorexa=this.ContadorDeExamen(cICvePersonal);
			cSQL = "SELECT MAX (INUMEXAMEN) FROM PERCATALOGONOAP WHERE ICVEPERSONAL = "+cICvePersonal;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getInt(1);
			}
			if(contadorexa > 0 && regresa == 0){
				regresa = contadorexa;
			}
		} catch (Exception ex) {
			warn("FindNoAptitudes", ex);
			throw new DAOException("FindNoAptitudes");
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
			} catch (final Exception ex2) {
				warn("FindNoAptitudes.close", ex2);
			}
			return regresa;
		}
	}
	
	
	@SuppressWarnings("finally")
	public int ContadorDeExamen(String cICvePersonal)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int regresa = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT COUNT (INUMEXAMEN) FROM PERCATALOGONOAP WHERE ICVEPERSONAL = "+cICvePersonal;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getInt(1);
			}
		} catch (Exception ex) {
			warn("FindNoAptitudes", ex);
			throw new DAOException("FindNoAptitudes");
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
			} catch (final Exception ex2) {
				warn("FindNoAptitudes.close", ex2);
			}
			return regresa;
		}
	}

}
