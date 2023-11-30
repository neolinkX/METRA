package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLPuesto DAO
 * </p>
 * <p>
 * Description: DAO Tabla GRLPuesto
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

public class TDGRLPuesto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLPuesto() {
	}

	/**
	 * M�todo Find By All
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector FindByAll(String cModoTrans, String cCategoria)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLPuesto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLPuesto vGRLPuesto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMdoTrans," + "iCvePuesto,"
					+ "iCveCategoria," + "cDscPuesto" + " from GRLPuesto ";
			boolean lWhere = false;
			if (cModoTrans != null && cModoTrans.compareTo("") != 0) {
				cSQL = cSQL + "where iCveMdoTrans = " + cModoTrans;
				lWhere = true;
			}

			if (cCategoria != null && cCategoria.compareTo("") != 0) {
				if (lWhere) {
					cSQL = cSQL + "   and iCveCategoria = " + cCategoria +" and lactivo = 1";
				} else {
					cSQL = cSQL + " where iCveCategoria = " + cCategoria;
				}
			}

			cSQL = cSQL + " order by iCveMdoTrans";

			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLPuesto = new TVGRLPuesto();
				vGRLPuesto.setICveModoTrans(rset.getInt(1));
				vGRLPuesto.setICvePuesto(rset.getInt(2));
				vGRLPuesto.setICveCategoria(rset.getInt(3));
				vGRLPuesto.setCDscPuesto(rset.getString(4));
				vcGRLPuesto.addElement(vGRLPuesto);
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
			return vcGRLPuesto;
		}
	}

	/**
	 * M�todo Find By All
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector FindByAll2(String cModoTrans, String cCategoria)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLPuesto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLPuesto vGRLPuesto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMdoTrans," + "iCvePuesto,"
					+ "iCveCategoria," + "cDscPuesto" + " from GRLPuesto ";
			boolean lWhere = false;
			if (cModoTrans != null && cModoTrans.compareTo("") != 0) {
				cSQL = cSQL + "where lactivo = 1 and iCveMdoTrans = "
						+ cModoTrans;
				lWhere = true;
			}

			if (cCategoria != null && cCategoria.compareTo("") != 0) {
				if (lWhere) {
					cSQL = cSQL + "   and iCveCategoria = " + cCategoria;
				} else {
					cSQL = cSQL + " where lactivo = 1 and iCveCategoria = "
							+ cCategoria;
				}
			}

			cSQL = cSQL + " order by iCveMdoTrans";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLPuesto = new TVGRLPuesto();
				vGRLPuesto.setICveModoTrans(rset.getInt(1));
				vGRLPuesto.setICvePuesto(rset.getInt(2));
				vGRLPuesto.setICveCategoria(rset.getInt(3));
				vGRLPuesto.setCDscPuesto(rset.getString(4));
				vcGRLPuesto.addElement(vGRLPuesto);
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
			return vcGRLPuesto;
		}
	}

	/**
	 * M�todo Find By All
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector FindDsc(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLPuesto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLPuesto vGRLPuesto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMdoTrans, " + "iCvePuesto,   "
					+ "iCveCategoria," + "cDscPuesto,   " + "iEdadMinima,  "
					+ "iCveGrupo,     " + "lActivo" + // FCSReq2 -Se Agrego-
					" from GRLPuesto " + cWhere + cOrderBy;

			// System.out.println("\n********** TDGRLPuesto.java - cSQL 3 : \n"
			// +cSQL+ " **********"); //FCSReq2 3

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLPuesto = new TVGRLPuesto();
				vGRLPuesto.setICveModoTrans(rset.getInt(1));
				vGRLPuesto.setICvePuesto(rset.getInt(2));
				vGRLPuesto.setICveCategoria(rset.getInt(3));
				vGRLPuesto.setCDscPuesto(rset.getString(4));
				vGRLPuesto.setIEdadMinima(rset.getInt(5));
				vGRLPuesto.setICveGrupo(rset.getInt(6));
				vGRLPuesto.setLActivo(rset.getInt(7)); // FCSReq2 -Se Agrego-
				vcGRLPuesto.addElement(vGRLPuesto);
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
			return vcGRLPuesto;
		}
	}

	/**
	 * M�todo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVGRLPuesto vGRLPuesto = (TVGRLPuesto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLPuesto(" + "iCveMdoTrans, "
					+ "iCvePuesto,   " + "iCveCategoria," + "cDscPuesto,   "
					+ "iEdadMinima,  " + "iCveGrupo,     " + "lActivo     "
					+ ")values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			String cSQLMax = "select max(iCvePuesto) from GRLPuesto where iCveMdoTrans = "
					+ vGRLPuesto.getICveModoTrans();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			if (iMax == 0) {
				iMax = 1;
			} else {
				iMax += 1;
			}
			vGRLPuesto.setICvePuesto(iMax);
			// ******************************************************************

			pstmt.setInt(1, vGRLPuesto.getICveModoTrans());
			pstmt.setInt(2, vGRLPuesto.getICvePuesto());
			pstmt.setInt(3, vGRLPuesto.getICveCategoria());
			pstmt.setString(4, vGRLPuesto.getCDscPuesto());
			pstmt.setInt(5, vGRLPuesto.getIEdadMinima());
			pstmt.setInt(6, vGRLPuesto.getICveGrupo());
			pstmt.setInt(7, vGRLPuesto.getLActivo());

			pstmt.executeUpdate();
			cClave = "" + vGRLPuesto.getICvePuesto();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
				}
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
	 * M�todo update
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
			TVGRLPuesto vGRLPuesto = (TVGRLPuesto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLPuesto" + " set iCveCategoria = ?, "
					+ "     cDscPuesto    = ?, " + "     iEdadMinima   = ?, "
					+ "     iCveGrupo     = ?,  " + "     lActivo       = ?  " + // FCSReq2
																					// -Se
																					// agrego-
					" where iCveMdoTrans = ? " + "   and iCvePuesto   = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLPuesto.getICveCategoria());
			pstmt.setString(2, vGRLPuesto.getCDscPuesto());
			pstmt.setInt(3, vGRLPuesto.getIEdadMinima());
			pstmt.setInt(4, vGRLPuesto.getICveGrupo());
			pstmt.setInt(5, vGRLPuesto.getLActivo()); // FCSReq2 -Se agrego-
			pstmt.setInt(6, vGRLPuesto.getICveModoTrans());
			pstmt.setInt(7, vGRLPuesto.getICvePuesto());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
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
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * M�todo Delete
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
			TVGRLPuesto vGRLPuesto = (TVGRLPuesto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLPuesto" + " where iCveMdoTrans = ?"
					+ " and iCvePuesto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLPuesto.getICveModoTrans());
			pstmt.setInt(2, vGRLPuesto.getICvePuesto());
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
				warn("delete.closeGRLPuesto", ex2);
			}
			return cClave;
		}
	}

	public Vector FindByAll(HashMap hmFiltros) throws DAOException {
		String cSQL = "select "
				+ "a.iCveMdoTrans,a.iCvePuesto,a.iCveGrupo,a.iCveCategoria,a.cDscPuesto,"
				+ "a.iEdadMinima,c.cDscGrupo,d.cDscCategoria,e.cDscMdoTrans,f.iNumExamen "
				+ "from GRLPuesto a "
				+ "inner join GRLProcesoMdoT b on ("
				+ "a.iCveMdoTrans=b.iCveMdoTrans and b.iCveProceso=? and "
				+ "b.iCveUniMed=? and b.iCveModulo=? and a.lactivo = 1) "
				+ "inner join GRLGrupo c on ("
				+ "a.iCveMdoTrans=c.iCveMdoTrans and a.iCveGrupo=c.iCveGrupo) "
				+ "inner join GRLCategoria d on ("
				+ "a.iCveMdoTrans=d.iCveMdoTrans and a.iCveCategoria=d.iCveCategoria) "
				+ "inner join GRLMdoTrans e on (a.iCveMdoTrans=e.iCveMdoTrans) "
				+ "left join EXPExamCat f on( f.iCveExpediente = ? "
				+ "and f.iNumExamen = ? "
				+ "and f.iCveMdoTrans = a.iCveMdoTrans "
				+ "and f.iCveCategoria = a.iCveCategoria )"
				+ " order by e.cDscMdoTrans,d.cDscCategoria,a.cDscPuesto";
		//System.out.println(cSQL);
		return executeQueryCat(cSQL, hmFiltros);
	}

	public Vector FindByAll(HashMap hmFiltros, String cPuestos)
			throws DAOException {
		if (cPuestos == null || cPuestos.length() == 0) {
			return new Vector();
		}
		String cSQL = "select "
				+ "a.iCveMdoTrans,a.iCvePuesto,a.iCveGrupo,a.iCveCategoria,a.cDscPuesto,"
				+ "a.iEdadMinima,c.cDscGrupo,d.cDscCategoria,e.cDscMdoTrans "
				+ "from GRLPuesto a "
				+ "inner join GRLProcesoMdoT b on ("
				+ "a.iCveMdoTrans=b.iCveMdoTrans and b.iCveProceso=? and "
				+ "b.iCveUniMed=? and b.iCveModulo=?) "
				+ "inner join GRLGrupo c on ("
				+ "a.iCveMdoTrans=c.iCveMdoTrans and a.iCveGrupo=c.iCveGrupo) "
				+ "inner join GRLCategoria d on ("
				+ "a.iCveMdoTrans=d.iCveMdoTrans and a.iCveCategoria=d.iCveCategoria) "
				+ "inner join GRLMdoTrans e on (a.iCveMdoTrans=e.iCveMdoTrans) "
				+ "where (a.iCveMdoTrans,a.iCvePuesto) in (values " + cPuestos
				+ ")" + "order by e.cDscMdoTrans,d.cDscCategoria,a.cDscPuesto";
		return executeQuery(cSQL, hmFiltros);
	}

	private Vector executeQuery(String cSQL, HashMap hmFiltros)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLPuesto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLPuesto vGRLPuesto;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1,
					Integer.parseInt((String) hmFiltros.get("iCveProceso")));
			pstmt.setInt(2,
					Integer.parseInt((String) hmFiltros.get("iCveUniMed")));
			pstmt.setInt(3,
					Integer.parseInt((String) hmFiltros.get("iCveModulo")));

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLPuesto = new TVGRLPuesto();
				vGRLPuesto.setICveModoTrans(rset.getInt("iCveMdoTrans"));
				vGRLPuesto.setICvePuesto(rset.getInt("iCvePuesto"));
				vGRLPuesto.setICveGrupo(rset.getInt("iCveGrupo"));
				vGRLPuesto.setICveCategoria(rset.getInt("iCveCategoria"));
				vGRLPuesto.setCDscPuesto(rset.getString("cDscPuesto"));
				vGRLPuesto.setIEdadMinima(rset.getInt("iEdadMinima"));
				vGRLPuesto.setCDscGrupo(rset.getString("cDscGrupo"));
				vGRLPuesto.setCDscCategoria(rset.getString("cDscCategoria"));
				vGRLPuesto.setCDscMdoTrans(rset.getString("cDscMdoTrans"));
				vcGRLPuesto.addElement(vGRLPuesto);
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
			return vcGRLPuesto; 
		}
	}

	private Vector executeQueryCat(String cSQL, HashMap hmFiltros)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLPuesto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLPuesto vGRLPuesto;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			pstmt = conn.prepareStatement(cSQL);

			int iLastExam = 0;
			TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
			iLastExam = dEXPExamAplica.FindLastEPI(
					(String) hmFiltros.get("iCvePersonal"),
					(String) hmFiltros.get("iCveProceso"));

			pstmt.setInt(1,
					Integer.parseInt((String) hmFiltros.get("iCveProceso")));
			pstmt.setInt(2,
					Integer.parseInt((String) hmFiltros.get("iCveUniMed")));
			pstmt.setInt(3,
					Integer.parseInt((String) hmFiltros.get("iCveModulo")));
			pstmt.setInt(4,
					Integer.parseInt((String) hmFiltros.get("iCvePersonal")));
			pstmt.setInt(5, Integer.parseInt("" + iLastExam));

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLPuesto = new TVGRLPuesto();
				vGRLPuesto.setICveModoTrans(rset.getInt("iCveMdoTrans"));
				vGRLPuesto.setICvePuesto(rset.getInt("iCvePuesto"));
				vGRLPuesto.setICveGrupo(rset.getInt("iCveGrupo"));
				vGRLPuesto.setICveCategoria(rset.getInt("iCveCategoria"));
				vGRLPuesto.setCDscPuesto(rset.getString("cDscPuesto"));
				vGRLPuesto.setIEdadMinima(rset.getInt("iEdadMinima"));
				vGRLPuesto.setCDscGrupo(rset.getString("cDscGrupo"));
				vGRLPuesto.setCDscCategoria(rset.getString("cDscCategoria"));
				vGRLPuesto.setCDscMdoTrans(rset.getString("cDscMdoTrans"));
				vGRLPuesto.setIExamen(rset.getInt("iNumExamen"));
				vcGRLPuesto.addElement(vGRLPuesto);
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
			return vcGRLPuesto;
		}
	}

}
