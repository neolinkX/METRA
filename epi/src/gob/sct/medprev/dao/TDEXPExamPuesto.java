package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EXPExamPuesto DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPExamPuesto
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

public class TDEXPExamPuesto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPExamPuesto() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		return FindByAll(new HashMap());
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamPuesto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPExamPuesto vEXPExamPuesto;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveMdoTrans,a.iCvePuesto,"
					+ "b.cDscMdoTrans,c.cDscPuesto,d.cDscCategoria,e.cDscGrupo,f.iCvePerfil, d.iCveCategoria "
					+ "from EXPExamPuesto a inner join GRLMdoTrans b on ("
					+ "a.iCveMdoTrans=b.iCveMdoTrans) inner join GRLPuesto c on ("
					+ "a.iCveMdoTrans=c.iCveMdoTrans and a.iCvePuesto=c.iCvePuesto) "
					+ "inner join GRLCategoria d on (c.iCveMdoTrans=d.iCveMdoTrans and "
					+ "c.iCveCategoria=d.iCveCategoria) inner join GRLGrupo e on ("
					+ "c.iCveMdoTrans=e.iCveMdoTrans and c.iCveGrupo=e.iCveGrupo) "
					+ "inner join MEDPerfilMC f on (c.iCveMdoTrans=f.iCveMdoTrans and "
					+ "c.iCveGrupo=f.iCveGrupo and f.lvigente = 1)";
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");

			if (cCveExpediente != null) {
				cSQL += " where iCveExpediente=? ";
				cWhereAnd = " and";
			}
			if (cNumExamen != null) {
				cSQL += cWhereAnd + " iNumExamen=? ";
			}
			cSQL += "order by iCveExpediente,a.iNumExamen,a.iCveMdoTrans,a.iCvePuesto";

			pstmt = conn.prepareStatement(cSQL);
			int i = 1;

			if (cCveExpediente != null) {
				pstmt.setInt(i++, Integer.parseInt(cCveExpediente));
			}
			if (cNumExamen != null)
				pstmt.setInt(i++, Integer.parseInt(cNumExamen));

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamPuesto = new TVEXPExamPuesto();
				vEXPExamPuesto.setICveExpediente(rset.getInt("iCveExpediente"));
				vEXPExamPuesto.setINumExamen(rset.getInt("iNumExamen"));
				vEXPExamPuesto.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				vEXPExamPuesto.setICvePuesto(rset.getInt("iCvePuesto"));
				vEXPExamPuesto.setCDscMdoTrans(rset.getString("cDscMdoTrans"));
				vEXPExamPuesto.setCDscPuesto(rset.getString("cDscPuesto"));
				vEXPExamPuesto
						.setCDscCategoria(rset.getString("cDscCategoria"));
				vEXPExamPuesto.setCDscGrupo(rset.getString("cDscGrupo"));
				vEXPExamPuesto.setICvePerfil(rset.getInt("iCvePerfil"));
				vEXPExamPuesto.setICveCategoria(rset.getInt("iCveCategoria"));

				vcEXPExamPuesto.addElement(vEXPExamPuesto);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcEXPExamPuesto;
	}
	

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(int transporte, int categoria, int expediente, int examen) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamPuesto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPExamPuesto vEXPExamPuesto;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select ep.icvepuesto "
								+ "from expexampuesto as ep, 	"
								+ "grlpuesto as gp " + "where "
								+ "gp.icvemdotrans = ep.icvemdotrans and "
								+ "gp.icvepuesto = ep.icvepuesto and "
								+ "gp.icvemdotrans = ? and "
								+ "gp.icvecategoria = ? and "
								+ "ep.icveexpediente = ? and "
								+ "ep.inumexamen = ?";
			
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, transporte);
			pstmt.setInt(2, categoria);
			pstmt.setInt(3, expediente);
			pstmt.setInt(4, examen);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamPuesto = new TVEXPExamPuesto();
				vEXPExamPuesto.setICvePuesto(rset.getInt("iCvePuesto"));
				vcEXPExamPuesto.addElement(vEXPExamPuesto);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcEXPExamPuesto;
	}	

	/**
	 * Metodo Find By All
	 */
	public String CdsPuesto(String Query) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String Regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = Query;

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regresa = rset.getString("cDscPuesto");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return Regresa;
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
			TVEXPExamPuesto vEXPExamPuesto = (TVEXPExamPuesto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPExamPuesto(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveMdoTrans," + "iCvePuesto"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEXPExamPuesto.getICveExpediente());
			pstmt.setInt(2, vEXPExamPuesto.getINumExamen());
			pstmt.setInt(3, vEXPExamPuesto.getICveMdoTrans());
			pstmt.setInt(4, vEXPExamPuesto.getICvePuesto());
			pstmt.executeUpdate();
			cClave = "" + vEXPExamPuesto.getICveExpediente();
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
	public int insert(Connection conGral, Vector vcEXPExamPuesto)
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
			String cSQL = "insert into EXPExamPuesto ( "
					+ "iCveExpediente,iNumExamen,iCveMdoTrans,iCvePuesto ) "
					+ "values (?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			for (Enumeration eEXPExamPuesto = vcEXPExamPuesto.elements(); eEXPExamPuesto
					.hasMoreElements();) {
				TVEXPExamPuesto vEXPExamPuesto = (TVEXPExamPuesto) eEXPExamPuesto
						.nextElement();
				pstmt.setInt(1, vEXPExamPuesto.getICveExpediente());
				pstmt.setInt(2, vEXPExamPuesto.getINumExamen());
				pstmt.setInt(3, vEXPExamPuesto.getICveMdoTrans());
				pstmt.setInt(4, vEXPExamPuesto.getICvePuesto());
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
			TVEXPExamPuesto vEXPExamPuesto = (TVEXPExamPuesto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamPuesto" + "where iCveExpediente = ? "
					+ "and iNumExamen = ?" + "and iCveMdoTrans = ?"
					+ " and iCvePuesto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamPuesto.getICveExpediente());
			pstmt.setInt(2, vEXPExamPuesto.getINumExamen());
			pstmt.setInt(3, vEXPExamPuesto.getICveMdoTrans());
			pstmt.setInt(4, vEXPExamPuesto.getICvePuesto());
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
			TVEXPExamPuesto vEXPExamPuesto = (TVEXPExamPuesto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPExamPuesto" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveMdoTrans = ?"
					+ " and iCvePuesto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamPuesto.getICveExpediente());
			pstmt.setInt(2, vEXPExamPuesto.getINumExamen());
			pstmt.setInt(3, vEXPExamPuesto.getICveMdoTrans());
			pstmt.setInt(4, vEXPExamPuesto.getICvePuesto());
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
				warn("delete.closeEXPExamPuesto", ex2);
			}
			return cClave;
		}
	}
}
