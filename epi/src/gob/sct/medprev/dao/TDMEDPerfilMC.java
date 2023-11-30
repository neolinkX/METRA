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
 * Title: Data Acces Object de MEDPerfilMC DAO
 * </p>
 * <p>
 * Description: Data Access Object para MEDPerfilMC
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sanchez
 * @version 1.0
 */

public class TDMEDPerfilMC extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDPerfilMC() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByAll(String where) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilMC = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilMC vMEDPerfilMC;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePerfil," + "iCveMdoTrans," + "iCveGrupo,"
					+ "dtInicio," + "dtFin," + "lVigente"
					+ " from MEDPerfilMC " + where + " order by iCvePerfil";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilMC = new TVMEDPerfilMC();
				vMEDPerfilMC.setICvePerfil(rset.getInt(1));
				vMEDPerfilMC.setICveMdoTrans(rset.getInt(2));
				vMEDPerfilMC.setICveGrupo(rset.getInt(3));
				vMEDPerfilMC.setDtInicio(rset.getDate(4));
				vMEDPerfilMC.setDtFin(rset.getDate(5));
				vMEDPerfilMC.setLVigente(rset.getInt(6));
				vcMEDPerfilMC.addElement(vMEDPerfilMC);
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
			return vcMEDPerfilMC;
		}
	}

	/**
	 * Metodo find by where clause
	 */
	public Vector findByWhere(String where, String orderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilMC = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilMC vMEDPerfilMC;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePerfil," + "iCveMdoTrans," + "iCveGrupo,"
					+ "dtInicio," + "dtFin," + "lVigente"
					+ " from MEDPerfilMC " + where + orderBy;
			pstmt = conn.prepareStatement(cSQL);
			// System.out.println(this.getClass().getName()+" SQL (by where): "
			// + cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilMC = new TVMEDPerfilMC();
				vMEDPerfilMC.setICvePerfil(rset.getInt(1));
				vMEDPerfilMC.setICveMdoTrans(rset.getInt(2));
				vMEDPerfilMC.setICveGrupo(rset.getInt(3));
				vMEDPerfilMC.setDtInicio(rset.getDate(4));
				vMEDPerfilMC.setDtFin(rset.getDate(5));
				vMEDPerfilMC.setLVigente(rset.getInt(6));
				vcMEDPerfilMC.addElement(vMEDPerfilMC);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByWhere");
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
				warn("FindByWhere.close", ex2);
			}
			return vcMEDPerfilMC;
		}
	}

	/**
	 * Metodo find perfil gpo mod
	 */
	public Vector findPerfilGpoMdo(String where, String orderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilMC = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilMC vMEDPerfilMC;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "p.iCvePerfil,"
					+ "p.iCveMdoTrans,"
					+ "p.iCveGrupo,"
					+ "p.dtInicio,"
					+ "p.dtFin,"
					+ "p.lVigente,"
					+ "g.cDscGrupo, "
					+ "m.cDscMdoTrans "
					+ " from GRLGrupo g JOIN GRLMdoTrans m "
					+ " ON g.iCveMdoTrans = m.iCveMdoTrans "
					+ " JOIN MEDPerfilMC p ON (p.iCveMdoTrans = g.iCveMdoTrans "
					+ " AND p.iCveGrupo = g.iCveGrupo) " + where + orderBy;
			// System.out.println(this.getClass().getName()+" SQL (grupo-modo_trans): "
			// + cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilMC = new TVMEDPerfilMC();
				vMEDPerfilMC.setICvePerfil(rset.getInt(1));
				vMEDPerfilMC.setICveMdoTrans(rset.getInt(2));
				vMEDPerfilMC.setICveGrupo(rset.getInt(3));
				vMEDPerfilMC.setDtInicio(rset.getDate(4));
				vMEDPerfilMC.setDtFin(rset.getDate(5));
				vMEDPerfilMC.setLVigente(rset.getInt(6));
				vMEDPerfilMC.setCDscGrupo(rset.getString(7));
				vMEDPerfilMC.setCDscMdoTrans(rset.getString(8));
				vcMEDPerfilMC.addElement(vMEDPerfilMC);
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
			return vcMEDPerfilMC;
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
			TVMEDPerfilMC vMEDPerfilMC = (TVMEDPerfilMC) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDPerfilMC(" + "iCvePerfil," + "iCveMdoTrans,"
					+ "iCveGrupo," + "dtInicio," + "dtFin," + "lVigente"
					+ ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA

			vMEDPerfilMC.setICvePerfil(this.getNextAvailable(conn,
					"iCvePerfil", "MEDPerfilMC"));

			// ******************************************************************

			pstmt.setInt(1, vMEDPerfilMC.getICvePerfil());
			pstmt.setInt(2, vMEDPerfilMC.getICveMdoTrans());
			pstmt.setInt(3, vMEDPerfilMC.getICveGrupo());
			pstmt.setDate(4, vMEDPerfilMC.getDtInicio());
			pstmt.setDate(5, vMEDPerfilMC.getDtFin());
			pstmt.setInt(6, vMEDPerfilMC.getLVigente());

			// System.out.println("insertando: " +
			// vMEDPerfilMC.toHashMap().toString());

			pstmt.executeUpdate();
			cClave = "" + vMEDPerfilMC.getICvePerfil();
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
					if (dbConn != null) {
						dbConn.closeConnection();
					}
				}
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
			TVMEDPerfilMC vMEDPerfilMC = (TVMEDPerfilMC) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDPerfilMC" + " set iCveMdoTrans= ?, "
					+ "iCveGrupo= ?, " + "dtInicio= ?, " + "dtFin= ?, "
					+ "lVigente= ? " + "where iCvePerfil = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDPerfilMC.getICveMdoTrans());
			pstmt.setInt(2, vMEDPerfilMC.getICveGrupo());
			pstmt.setDate(3, vMEDPerfilMC.getDtInicio());
			pstmt.setDate(4, vMEDPerfilMC.getDtFin());
			pstmt.setInt(5, vMEDPerfilMC.getLVigente());
			pstmt.setInt(6, vMEDPerfilMC.getICvePerfil());
			// System.out.println("-a punto de actualizar: " +
			// vMEDPerfilMC.toHashMap().toString());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
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
			TVMEDPerfilMC vMEDPerfilMC = (TVMEDPerfilMC) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDPerfilMC" + " where iCvePerfil = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDPerfilMC.getICvePerfil());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("delete.closeMEDPerfilMC", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo que devuelve el siguiente n�mero de �ndice disponible, antes
	 * de insertar en una tabla. Es una manera de emular una secuencia
	 * autom�tica del RDBMS.
	 * 
	 * @param campo
	 *            el nombre del campo sobre el que se calcular� el siguiente
	 *            n�mero de la secuencia
	 * @param tabla
	 *            el nombre de la tabla que contiene el campo
	 * @return un entero con el n�mero obtenido, correspondiente al siguiente
	 *         disponible de la secuencia
	 * @throws java.sql.SQLException
	 *             en caso de presentarse cualquier excepci�n durante la
	 *             consulta a la base de datos
	 * @author Romeo S�nchez
	 */
	private int getNextAvailable(Connection conGral, String campo, String tabla)
			throws java.sql.SQLException, DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		String cClave = "";
		int iEntidades = 0;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		String cSQLMax = "SELECT MAX(" + campo + ")+1 FROM " + tabla;

		try {
			dbConn = new DbConnection(dataSourceName);
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return iMax == 0 ? 1 : iMax; // para evitar que devuelva 0 en caso
											// de ser el primer registro
		}
	}
}