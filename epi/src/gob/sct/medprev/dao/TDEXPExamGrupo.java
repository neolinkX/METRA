package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EXPExamGrupo DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPExamGrupo
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

public class TDEXPExamGrupo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPExamGrupo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamGrupo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamGrupo vEXPExamGrupo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveMdoTrans," + "iCveGrupo," + "iCvePerfil"
					+ " from EXPExamGrupo order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamGrupo = new TVEXPExamGrupo();
				vEXPExamGrupo.setICveExpediente(rset.getInt(1));
				vEXPExamGrupo.setINumExamen(rset.getInt(2));
				vEXPExamGrupo.setICveMdoTrans(rset.getInt(3));
				vEXPExamGrupo.setICveGrupo(rset.getInt(4));
				vEXPExamGrupo.setICvePerfil(rset.getInt(5));
				vcEXPExamGrupo.addElement(vEXPExamGrupo);
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
			return vcEXPExamGrupo;
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
		Vector vcEXPExamGrupo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamGrupo vEXPExamGrupo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveMdoTrans," + "iCveGrupo," + "iCvePerfil"
					+ " from EXPExamGrupo " + cWhere
					+ " order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamGrupo = new TVEXPExamGrupo();
				vEXPExamGrupo.setICveExpediente(rset.getInt(1));
				vEXPExamGrupo.setINumExamen(rset.getInt(2));
				vEXPExamGrupo.setICveMdoTrans(rset.getInt(3));
				vEXPExamGrupo.setICveGrupo(rset.getInt(4));
				vEXPExamGrupo.setICvePerfil(rset.getInt(5));
				vcEXPExamGrupo.addElement(vEXPExamGrupo);
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
			return vcEXPExamGrupo;
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
			TVEXPExamGrupo vEXPExamGrupo = (TVEXPExamGrupo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPExamGrupo(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveMdoTrans," + "iCveGrupo,"
					+ "iCvePerfil" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEXPExamGrupo.getICveExpediente());
			pstmt.setInt(2, vEXPExamGrupo.getINumExamen());
			pstmt.setInt(3, vEXPExamGrupo.getICveMdoTrans());
			pstmt.setInt(4, vEXPExamGrupo.getICveGrupo());
			pstmt.setInt(5, vEXPExamGrupo.getICvePerfil());
			pstmt.executeUpdate();
			cClave = "" + vEXPExamGrupo.getICveExpediente();
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
	public int insert(Connection conGral, Vector vcEXPExamGrupo)
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
			String cSQL = "insert into EXPExamGrupo ( "
					+ "iCveExpediente,iNumExamen,iCveMdoTrans,iCveGrupo,iCvePerfil ) "
					+ "values (?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			for (Enumeration eEXPExamGrupo = vcEXPExamGrupo.elements(); eEXPExamGrupo
					.hasMoreElements();) {
				TVEXPExamGrupo vEXPExamGrupo = (TVEXPExamGrupo) eEXPExamGrupo
						.nextElement();
				pstmt.setInt(1, vEXPExamGrupo.getICveExpediente());
				pstmt.setInt(2, vEXPExamGrupo.getINumExamen());
				pstmt.setInt(3, vEXPExamGrupo.getICveMdoTrans());
				pstmt.setInt(4, vEXPExamGrupo.getICveGrupo());
				pstmt.setInt(5, vEXPExamGrupo.getICvePerfil());
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
	 * Metodo Insert
	 */
	public int insertverifica(Connection conGral, Vector vcEXPExamGrupo)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtVerifica = null;
		ResultSet rset = null;
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

			String cSQL = "select iCveGrupo " + "where iCveExpediente = ? "
					+ "and  iNumExamen = ? " + "and iCveMdoTrans = ? "
					+ "and iCveGrupo = ? ";

			pstmtVerifica = conn.prepareStatement(cSQL);
			for (Enumeration eEXPExamGrupo = vcEXPExamGrupo.elements(); eEXPExamGrupo
					.hasMoreElements();) {
				TVEXPExamGrupo vEXPExamGrupo = (TVEXPExamGrupo) eEXPExamGrupo
						.nextElement();
				pstmtVerifica.setInt(1, vEXPExamGrupo.getICveExpediente());
				pstmtVerifica.setInt(2, vEXPExamGrupo.getINumExamen());
				pstmtVerifica.setInt(3, vEXPExamGrupo.getICveMdoTrans());
				pstmtVerifica.setInt(4, vEXPExamGrupo.getICveGrupo());

				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();
				while (rset.next()) {

					cSQL = "insert into EXPExamGrupo ( "
							+ "iCveExpediente,iNumExamen,iCveMdoTrans,iCveGrupo,iCvePerfil ) "
							+ "values (?,?,?,?,?)";

					pstmt = conn.prepareStatement(cSQL);
					// for (Enumeration eEXPExamGrupo =
					// vcEXPExamGrupo.elements();
					// eEXPExamGrupo.hasMoreElements(); ) {
					// TVEXPExamGrupo vEXPExamGrupo = (TVEXPExamGrupo)
					// eEXPExamGrupo.nextElement();
					pstmt.setInt(1, vEXPExamGrupo.getICveExpediente());
					pstmt.setInt(2, vEXPExamGrupo.getINumExamen());
					pstmt.setInt(3, vEXPExamGrupo.getICveMdoTrans());
					pstmt.setInt(4, vEXPExamGrupo.getICveGrupo());
					pstmt.setInt(5, vEXPExamGrupo.getICvePerfil());
					iCta += pstmt.executeUpdate();
				}
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
				warn("insertverifica", ex1);
			}
			warn("insertverifica", ex);
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
				warn("insertverifica.close", ex2);
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
			TVEXPExamGrupo vEXPExamGrupo = (TVEXPExamGrupo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamGrupo" + " set iCvePerfil= ?, "
					+ "where iCveExpediente = ? " + "and iNumExamen = ?"
					+ "and iCveMdoTrans = ?" + " and iCveGrupo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamGrupo.getICvePerfil());
			pstmt.setInt(2, vEXPExamGrupo.getICveExpediente());
			pstmt.setInt(3, vEXPExamGrupo.getINumExamen());
			pstmt.setInt(4, vEXPExamGrupo.getICveMdoTrans());
			pstmt.setInt(5, vEXPExamGrupo.getICveGrupo());
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
			TVEXPExamGrupo vEXPExamGrupo = (TVEXPExamGrupo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPExamGrupo" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveMdoTrans = ?"
					+ " and iCveGrupo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamGrupo.getICveExpediente());
			pstmt.setInt(2, vEXPExamGrupo.getINumExamen());
			pstmt.setInt(3, vEXPExamGrupo.getICveMdoTrans());
			pstmt.setInt(4, vEXPExamGrupo.getICveGrupo());
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
				warn("delete.closeEXPExamGrupo", ex2);
			}
			return cClave;
		}
	}
}
