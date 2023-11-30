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
 * Title: Data Acces Object de MEDPerfilDiag DAO
 * </p>
 * <p>
 * Description: Data Access Object de la tabla MEDPerfilDiag
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo S�nchez
 * @version 1.0
 */

public class TDMEDPerfilDiag extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDPerfilDiag() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilDiag = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilDiag vMEDPerfilDiag;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePerfil," + "iCveEspecialidad,"
					+ "iCveDiagnostico," + "lAlarma"
					+ " from MEDPerfilDiag order by iCvePerfil";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilDiag = new TVMEDPerfilDiag();
				vMEDPerfilDiag.setICvePerfil(rset.getInt(1));
				vMEDPerfilDiag.setICveEspecialidad(rset.getInt(2));
				vMEDPerfilDiag.setICveDiagnostico(rset.getInt(3));
				vMEDPerfilDiag.setLAlarma(rset.getInt(4));
				vcMEDPerfilDiag.addElement(vMEDPerfilDiag);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDPerfilDiag;
		}
	}

	/**
	 * Metodo sobrecargado que recibe s�lo una cla�sula WHERE
	 * 
	 * @param where
	 *            la cl�usula WHERE, incluy�ndola
	 * @return un Vector de Value Objects correspondientes a los registros
	 *         encontrados
	 * @throws DAOException
	 *             al existir una excepci�n durante la consulta
	 * @author Romeo Sanchez
	 */
	public Vector findByWhere(String where) throws DAOException {
		return this.findByWhere(where, "");
	}

	/**
	 * Metodo que recibe una cla�sula WHERE y ORDER BY
	 * 
	 * @param where
	 *            la cl�usula WHERE, incluy�ndola
	 * @param orderBy
	 *            la cl�usula ORDER BY, incluy�ndola
	 * @return un Vector de Value Objects correspondientes a los registros
	 *         encontrados
	 * @throws DAOException
	 *             al existir una excepci�n durante la consulta
	 * @author Romeo Sanchez
	 */
	public Vector findByWhere(String where, String orderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilDiag = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilDiag vMEDPerfilDiag;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePerfil," + "iCveEspecialidad,"
					+ "iCveDiagnostico," + "lAlarma" + " from MEDPerfilDiag "
					+ where + " " + orderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilDiag = new TVMEDPerfilDiag();
				vMEDPerfilDiag.setICvePerfil(rset.getInt(1));
				vMEDPerfilDiag.setICveEspecialidad(rset.getInt(2));
				vMEDPerfilDiag.setICveDiagnostico(rset.getInt(3));
				vMEDPerfilDiag.setLAlarma(rset.getInt(4));
				vcMEDPerfilDiag.addElement(vMEDPerfilDiag);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDPerfilDiag;
		}
	}

	/**
	 * Metodo que busca en la tabla MEDPerfilDiag, y en las tablas parent
	 * MEDPerfil, y MEDDiagnostico, seg�n el filtro especificado.
	 * 
	 * @param where
	 *            la cl�usula WHERE, incluy�ndola
	 * @param orderBy
	 *            la cl�usula ORDER BY, incluy�ndola
	 * @return un Vector de Value Objects correspondientes a los registros
	 *         encontrados
	 * @throws DAOException
	 *             al existir una excepci�n durante la consulta
	 * @author Romeo Sanchez
	 */
	public Vector findByPerfilDiag(String where, String orderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilDiag = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilDiag vMEDPerfilDiag;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT "
					+ " p.iCvePerfil, "
					+ " d.iCveEspecialidad, "
					+ " d.iCveDiagnostico, "
					+ // necesario para tener el diagn�stico actual
					" p.lAlarma, " + " d.cDscDiagnostico, " + " d.cDscBreve, "
					+ " d.cCIE, " + " d.lActivo " + " FROM MEDPerfilDiag p  "
					+ " RIGHT JOIN MEDDiagnostico d ON "
					+ " p.iCveDiagnostico = d.iCveDiagnostico "
					+ " WHERE d.lActivo=1 " + where + " " + orderBy; // mostrar
																		// s�lo
																		// diagn�sticos
																		// activos

			// System.out.println(this.getClass().getName()+" SQL: "+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilDiag = new TVMEDPerfilDiag();
				vMEDPerfilDiag.setICvePerfil(rset.getInt(1));
				vMEDPerfilDiag.setICveEspecialidad(rset.getInt(2));
				vMEDPerfilDiag.setICveDiagnostico(rset.getInt(3));
				vMEDPerfilDiag.setLAlarma(rset.getInt(4));
				vMEDPerfilDiag.setCDscDiagnostico(rset.getString(5));
				vMEDPerfilDiag.setCDscBreve(rset.getString(6));
				vMEDPerfilDiag.setCCIE(rset.getString(7));
				vMEDPerfilDiag.setLActivo(rset.getInt(8));

				vcMEDPerfilDiag.addElement(vMEDPerfilDiag);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDPerfilDiag;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		// System.out.println(this.getClass().getName()+".insert");

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
			TVMEDPerfilDiag vMEDPerfilDiag = (TVMEDPerfilDiag) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDPerfilDiag(" + "iCvePerfil,"
					+ "iCveEspecialidad," + "iCveDiagnostico," + "lAlarma"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vMEDPerfilDiag.getICvePerfil());
			pstmt.setInt(2, vMEDPerfilDiag.getICveEspecialidad());
			pstmt.setInt(3, vMEDPerfilDiag.getICveDiagnostico());
			pstmt.setInt(4, vMEDPerfilDiag.getLAlarma());
			// System.out.println("datos a insertar: " +
			// vMEDPerfilDiag.toHashMap().toString());
			pstmt.executeUpdate();
			// System.out.println("Ok. Insertados");
			cClave = "" + vMEDPerfilDiag.getICvePerfil();
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
				if (dbConn != null)
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
			TVMEDPerfilDiag vMEDPerfilDiag = (TVMEDPerfilDiag) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDPerfilDiag" + " set lAlarma = ? "
					+ "where iCvePerfil = ? " + "and iCveEspecialidad = ?"
					+ " and iCveDiagnostico = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDPerfilDiag.getLAlarma());
			pstmt.setInt(2, vMEDPerfilDiag.getICvePerfil());
			pstmt.setInt(3, vMEDPerfilDiag.getICveEspecialidad());
			pstmt.setInt(4, vMEDPerfilDiag.getICveDiagnostico());
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
				if (dbConn != null)
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
			TVMEDPerfilDiag vMEDPerfilDiag = (TVMEDPerfilDiag) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDPerfilDiag" + " where iCvePerfil = ?"
					+ " and iCveEspecialidad = ?" + " and iCveDiagnostico = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDPerfilDiag.getICvePerfil());
			pstmt.setInt(2, vMEDPerfilDiag.getICveEspecialidad());
			pstmt.setInt(3, vMEDPerfilDiag.getICveDiagnostico());
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
				warn("delete.closeMEDPerfilDiag", ex2);
			}
			return cClave;
		}
	}
}
