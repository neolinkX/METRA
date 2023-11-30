package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ExamAplica DAO
 * </p>
 * <p>
 * Description: Inicio del Examen
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */

public class TDExamAplica extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDExamAplica() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcExamAplica = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVExamAplica vExamAplica;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveClasificacion," + "iCveTpoLic,"
					+ "dtAplicacion," + "iCveDictamen," + "iCveExpediente,"
					+ "iCveTipoExamen," + "iCvePersonal," + "iNumExamen, "
					+ "TInicio, " + "TConcluido "
					+ " from EXPExamAplica order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vExamAplica = new TVExamAplica();
				vExamAplica.setICveClasificacion(rset.getInt(1));
				vExamAplica.setICveTpoLic(rset.getInt(2));
				vExamAplica.setDtAplicacion(rset.getDate(3));
				vExamAplica.setICveDictamen(rset.getInt(4));
				vExamAplica.setICveExpediente(rset.getInt(5));
				vExamAplica.setICveTipoExamen(rset.getInt(6));
				vExamAplica.setICvePersonal(rset.getInt(7));
				vExamAplica.setINumExamen(rset.getInt(8));
				vcExamAplica.addElement(vExamAplica);
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
			return vcExamAplica;
		}
	}

	/**
	 * 
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
			TVExamAplica vExamAplica = (TVExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPExamAplica(" + "iCveClasificacion,"
					+ "iCveTpoLic," + "dtAplicacion," + "iCveDictamen,"
					+ "iCveExpediente," + "iCveTipoExamen," + "iCvePersonal,"
					+ "iNumExamen," + "cConstancia)values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vExamAplica.getICveClasificacion());
			pstmt.setInt(2, vExamAplica.getICveTpoLic());
			pstmt.setDate(3, vExamAplica.getDtAplicacion());
			pstmt.setInt(4, vExamAplica.getICveDictamen());
			pstmt.setInt(5, vExamAplica.getICveExpediente());
			pstmt.setInt(6, vExamAplica.getICveTipoExamen());
			pstmt.setInt(7, vExamAplica.getICvePersonal());
			pstmt.setInt(8, vExamAplica.getINumExamen());
			pstmt.setString(9, "TDExamAplica-insert");
			pstmt.executeUpdate();
			cClave = "" + vExamAplica.getICveExpediente();
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
			TVExamAplica vExamAplica = (TVExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamAplica" + " set iCveDictamen= ?, "
					+ "iCveExpediente= ?, " + "iCveTipoExamen= ?, "
					+ "iCvePersonal= ?, " + "iNumExamen= ? "
					+ "where iCveExpediente = ? " + "and iCvePersonal = ?"
					+ " and iNumExamen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vExamAplica.getICveDictamen());
			pstmt.setInt(2, vExamAplica.getICveExpediente());
			pstmt.setInt(3, vExamAplica.getICveTipoExamen());
			pstmt.setInt(4, vExamAplica.getICvePersonal());
			pstmt.setInt(5, vExamAplica.getINumExamen());
			pstmt.setInt(6, vExamAplica.getICveExpediente());
			pstmt.setInt(7, vExamAplica.getICvePersonal());
			pstmt.setInt(8, vExamAplica.getINumExamen());
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
			TVExamAplica vExamAplica = (TVExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPExamAplica" + " where iCveExpediente = ?"
					+ " and iCvePersonal = ?" + " and iNumExamen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vExamAplica.getICveExpediente());
			pstmt.setInt(2, vExamAplica.getICvePersonal());
			pstmt.setInt(3, vExamAplica.getINumExamen());
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
				warn("delete.closeExamAplica", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String iCveUniMed, String iCveModulo,
			String iCveServicio, String cFecha, String cOrdenar)
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
			TVExamAplica vExamAplica;
			cSQL = " SELECT ea.dtSolicitado, ea.dtDictamen, d.iCveExpediente, d.cNombre, d.cApPaterno, "
					+ " d.cApMaterno, d.cRFC, c.cDscCategoria, ds.lDictamen AS lDictServicio, "
					+ " ec.lDictamen AS lDictExamen, md.cDscDiagnostico, md.cDscBreve,  ed.Tidiagnostico  "
					+ " FROM EXPExamAplica ea "
					+ " JOIN PERDatos d ON d.iCveExpediente = ea.iCveExpediente "
					+ " JOIN EXPDictamenServ ds ON ds.iCveExpediente = ea.iCveExpediente "
					+ " AND ds.iNumExamen = ea.iNumExamen "
					+ " JOIN EXPExamCat ec ON ec.iCveExpediente = ds.iCveExpediente "
					+ " AND ec.iNumExamen = ds.iNumExamen "
					+ " AND ec.iCveMdoTrans = ds.iCveMdoTrans "
					+ " AND ec.iCvecategoria = ds.iCvecategoria "
					+ " JOIN GRLCategoria c ON c.iCveMdoTrans = ds.iCveMdoTrans "
					+ " AND c.iCvecategoria = ds.iCvecategoria "
					+ " LEFT JOIN EXPDiagnostico ed ON ed.iNumExamen = ds.iNumExamen "
					+ " AND ed.iCveExpediente = ds.iCveExpediente "
					+ " AND ed.iCveServicio = ds.iCveServicio "
					+ " LEFT JOIN MEDDiagnostico md ON md.iCveEspecialidad = ed.iCveEspecialidad "
					+ " AND md.iCveDiagnostico = ed.iCveDiagnostico "
					+ " WHERE ea.ldictaminado = 1 and "
					+ cFecha
					+ (iCveUniMed.trim().length() > 0 ? " AND ea.iCveUniMed = "
							+ iCveUniMed : "")
					+ (iCveModulo.trim().length() > 0 ? " AND ea.iCveModulo = "
							+ iCveModulo : "")
					+ (iCveServicio.trim().length() > 0 ? " AND ds.iCveServicio = "
							+ iCveServicio
							: "") + " ORDER BY " + cOrdenar;

			//System.out.println(cSQL);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				vExamAplica = new TVExamAplica();
				vExamAplica.setDtSolicitado(rset.getDate("dtSolicitado"));
				vExamAplica.setDtDictamen(rset.getDate("dtDictamen"));
				vExamAplica.setICveExpediente(rset.getInt("iCveExpediente"));
				vExamAplica.setCNombre(rset.getString("cNombre"));
				vExamAplica.setCApPaterno(rset.getString("cApPaterno"));
				vExamAplica.setCApMaterno(rset.getString("cApMaterno"));
				vExamAplica.setCRFC(rset.getString("cRFC"));
				vExamAplica.setCDscCategoria(rset.getString("cDscCategoria"));
				vExamAplica.setLDictExamen(rset.getInt("lDictExamen"));
				vExamAplica.setLDictServicio(rset.getInt("lDictServicio"));
				vExamAplica.setCDscDiagnostico(rset
						.getString("cDscDiagnostico"));
				vExamAplica.setCDscBreve(rset.getString("cDscBreve"));
				vExamAplica.setCTIniExa(rset.getString("Tidiagnostico"));
				vRegresa.addElement(vExamAplica);
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
				warn("FindByAll.close", ex2);
			}
			return vRegresa;
		}
	}

}


