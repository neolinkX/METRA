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
 * Title: Data Acces Object de GRLModulo DAO
 * </p>
 * <p>
 * Description: DAO Tabla GRLModulo
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

public class TDGRLModulo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLModulo() {
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLModulo vGRLModulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveUniMed," + "iCveModulo," + "cDscModulo,"
					+ "cCalle," + "cColonia," + "iCP," + "cCiudad,"
					+ "iCvePais," + "iCveEstado," + "iCveMunicipio," + "cTel,"
					+ "cCorreoe," + "linterconsulta, " + "lVigente, " +

					"LVALIDA " + " from GRLModulo " + cWhere;

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLModulo = new TVGRLModulo();
				vGRLModulo.setICveUniMed(rset.getInt(1));
				vGRLModulo.setICveModulo(rset.getInt(2));
				vGRLModulo.setCDscModulo(rset.getString(3));
				vGRLModulo.setCCalle(rset.getString(4));
				vGRLModulo.setCColonia(rset.getString(5));
				vGRLModulo.setICP(rset.getInt(6));
				vGRLModulo.setCCiudad(rset.getString(7));
				vGRLModulo.setICvePais(rset.getInt(8));
				vGRLModulo.setICveEstado(rset.getInt(9));
				vGRLModulo.setICveMunicipio(rset.getInt(10));
				vGRLModulo.setCTel(rset.getString(11));
				vGRLModulo.setCCorreoe(rset.getString(12));
				vGRLModulo.setLinterconsulta(rset.getInt(13));
				vGRLModulo.setLVigente(rset.getInt(14));
				vGRLModulo.setlValida(rset.getInt(15));
				vcGRLModulo.addElement(vGRLModulo);
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
			return vcGRLModulo;
		}
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector FindByAll2(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLModulo vGRLModulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveUniMed," + "iCveModulo," + "cDscModulo,"
					+ "linterconsulta, " + "lVigente, " + "LVALIDA "
					+ " from GRLModulo " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLModulo = new TVGRLModulo();
				vGRLModulo.setICveUniMed(rset.getInt(1));
				vGRLModulo.setICveModulo(rset.getInt(2));
				vGRLModulo.setCDscModulo(rset.getString(3));
				vGRLModulo.setLinterconsulta(rset.getInt(4));
				vGRLModulo.setLVigente(rset.getInt(5));
				vGRLModulo.setlValida(rset.getInt(6));
				vcGRLModulo.addElement(vGRLModulo);
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
			return vcGRLModulo;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLModulo vGRLModulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveUniMed," + "iCveModulo," + "cDscModulo,"
					+ "cCalle," + "cColonia," + "iCP," + "cCiudad,"
					+ "iCvePais," + "iCveEstado," + "iCveMunicipio," + "cTel,"
					+ "cCorreoe," + "linterconsulta, " + "lVigente, "
					+ "cClues, " + "LVALIDA " + " from GRLModulo " + cWhere
					+ cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLModulo = new TVGRLModulo();
				vGRLModulo.setICveUniMed(rset.getInt(1));
				vGRLModulo.setICveModulo(rset.getInt(2));
				vGRLModulo.setCDscModulo(rset.getString(3));
				vGRLModulo.setCCalle(rset.getString(4));
				vGRLModulo.setCColonia(rset.getString(5));
				vGRLModulo.setICP(rset.getInt(6));
				vGRLModulo.setCCiudad(rset.getString(7));
				vGRLModulo.setICvePais(rset.getInt(8));
				vGRLModulo.setICveEstado(rset.getInt(9));
				vGRLModulo.setICveMunicipio(rset.getInt(10));
				vGRLModulo.setCTel(rset.getString(11));
				vGRLModulo.setCCorreoe(rset.getString(12));
				vGRLModulo.setLinterconsulta(rset.getInt(13));
				vGRLModulo.setLVigente(rset.getInt(14));
				vGRLModulo.setCClues(rset.getString(15));
				vGRLModulo.setlValida(rset.getInt(16));
				vcGRLModulo.addElement(vGRLModulo);
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
			return vcGRLModulo;
		}
	}

	/**
	 * Metodo Find Dsc
	 */
	public Vector FindDsc(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLModulo vGRLModulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "GRLModulo.iCveUniMed,"
					+ "GRLModulo.iCveModulo,"
					+ "GRLModulo.cDscModulo,"
					+ "GRLModulo.cCalle,"
					+ "GRLModulo.cColonia,"
					+ "GRLModulo.iCP,"
					+ "GRLModulo.cCiudad,"
					+ "GRLModulo.iCvePais,"
					+ "GRLModulo.iCveEstado,"
					+ "GRLModulo.iCveMunicipio,"
					+ "GRLModulo.cTel,"
					+ "GRLModulo.cCorreoe,"
					+ "GRLModulo.linterconsulta, "
					+ "GRLModulo.lVigente, "
					+ "GRLUniMed.cDscUniMed "
					+ " from GRLModulo "
					+ " join GRLUniMed on GRLUniMed.iCveUniMed = GRLModulo.iCveUniMed "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLModulo = new TVGRLModulo();
				vGRLModulo.setICveUniMed(rset.getInt(1));
				vGRLModulo.setICveModulo(rset.getInt(2));
				vGRLModulo.setCDscModulo(rset.getString(3));
				vGRLModulo.setCCalle(rset.getString(4));
				vGRLModulo.setCColonia(rset.getString(5));
				vGRLModulo.setICP(rset.getInt(6));
				vGRLModulo.setCCiudad(rset.getString(7));
				vGRLModulo.setICvePais(rset.getInt(8));
				vGRLModulo.setICveEstado(rset.getInt(9));
				vGRLModulo.setICveMunicipio(rset.getInt(10));
				vGRLModulo.setCTel(rset.getString(11));
				vGRLModulo.setCCorreoe(rset.getString(12));
				vGRLModulo.setLinterconsulta(rset.getInt(13));
				vGRLModulo.setLVigente(rset.getInt(14));
				vGRLModulo.setCDscUniMed(rset.getString(15));

				vcGRLModulo.addElement(vGRLModulo);
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
			return vcGRLModulo;
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
			TVGRLModulo vGRLModulo = (TVGRLModulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLModulo(" + "iCveUniMed," + "iCveModulo,"
					+ "cDscModulo," + "cCalle," + "cColonia," + "iCP,"
					+ "cCiudad," + "iCvePais," + "iCveEstado,"
					+ "iCveMunicipio," + "cTel," + "cCorreoe,"
					+ "linterconsulta," + "lVigente," + "cClues, " + "LVALIDA"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveModulo) from GRLModulo where iCveUniMed = "
					+ vGRLModulo.getICveUniMed();
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
			vGRLModulo.setICveModulo(iMax);
			// ******************************************************************
			pstmt.setInt(1, vGRLModulo.getICveUniMed());
			pstmt.setInt(2, vGRLModulo.getICveModulo());
			pstmt.setString(3, vGRLModulo.getCDscModulo());
			pstmt.setString(4, vGRLModulo.getCCalle());
			pstmt.setString(5, vGRLModulo.getCColonia());
			pstmt.setInt(6, vGRLModulo.getICP());
			pstmt.setString(7, vGRLModulo.getCCiudad());
			pstmt.setInt(8, vGRLModulo.getICvePais());
			pstmt.setInt(9, vGRLModulo.getICveEstado());
			pstmt.setInt(10, vGRLModulo.getICveMunicipio());
			pstmt.setString(11, vGRLModulo.getCTel());
			pstmt.setString(12, vGRLModulo.getCCorreoe());
			pstmt.setInt(13, vGRLModulo.getLinterconsulta());
			pstmt.setInt(14, vGRLModulo.getLVigente());
			pstmt.setString(15, vGRLModulo.getCClues());
			pstmt.setInt(16, vGRLModulo.getlValida());

			pstmt.executeUpdate();
			cClave = "" + vGRLModulo.getICveModulo();
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
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
			TVGRLModulo vGRLModulo = (TVGRLModulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLModulo" + " set cDscModulo= ?, " + "cCalle= ?, "
					+ "cColonia= ?, " + "iCP= ?, " + "cCiudad= ?, "
					+ "iCvePais= ?, " + "iCveEstado= ?, "
					+ "iCveMunicipio= ?, " + "cTel= ?, " + "cCorreoe= ?, "
					+ "linterconsulta= ?, " + "lVigente= ?, " + "cClues= ?, "
					+ "lValida= ? " + "where iCveUniMed = ? "
					+ " and iCveModulo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vGRLModulo.getCDscModulo());
			pstmt.setString(2, vGRLModulo.getCCalle());
			pstmt.setString(3, vGRLModulo.getCColonia());
			pstmt.setInt(4, vGRLModulo.getICP());
			pstmt.setString(5, vGRLModulo.getCCiudad());
			pstmt.setInt(6, vGRLModulo.getICvePais());
			pstmt.setInt(7, vGRLModulo.getICveEstado());
			pstmt.setInt(8, vGRLModulo.getICveMunicipio());
			pstmt.setString(9, vGRLModulo.getCTel());
			pstmt.setString(10, vGRLModulo.getCCorreoe());
			pstmt.setInt(11, vGRLModulo.getLinterconsulta());
			pstmt.setInt(12, vGRLModulo.getLVigente());
			// System.out.println("LLega la clue: " + vGRLModulo.getCClues());
			pstmt.setString(13, vGRLModulo.getCClues());
			pstmt.setInt(14, vGRLModulo.getlValida());
			pstmt.setInt(15, vGRLModulo.getICveUniMed());
			pstmt.setInt(16, vGRLModulo.getICveModulo());

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
			TVGRLModulo vGRLModulo = (TVGRLModulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLModulo" + " where iCveUniMed = ?"
					+ " and iCveModulo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLModulo.getICveUniMed());
			pstmt.setInt(2, vGRLModulo.getICveModulo());
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
				warn("delete.closeGRLModulo", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo getComboModulos
	 * 
	 * @param cCveUniMed
	 *            la cadena con la clave de la Unidad de Medicina
	 * @return un Vector con TVGRLModulo's con los registros encontrados, en
	 *         caso de no encontrar registros regresa null
	 * @throws DAOException
	 *             en caso de errores lanza una DAOException
	 */
	public Vector getComboModulos(int iCveUniMed) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVDinamico vCombo;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select iCveModulo,cDscModulo from GRLModulo "
					+ "where iCveUniMed=? order by cDscModulo";
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, iCveUniMed);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCombo = new TVDinamico();
				vCombo.put("cIndice",
						Integer.toString(rset.getInt("iCveModulo")));
				vCombo.put("cDescripcion", rset.getString("cDscModulo"));
				vcGRLModulo.addElement(vCombo);
			}
		} catch (Exception ex) {
			warn("getComboModulos", ex);
			throw new DAOException("getComboModulos");
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
				warn("getComboModulos.close", ex2);
			}
			return vcGRLModulo;
		}
	}

	/**
	 * Metodo getComboModulos
	 * 
	 * @param cCveUniMed
	 *            la cadena con la clave de la Unidad de Medicina
	 * @return un Vector con TVGRLModulo's con los registros encontrados, en
	 *         caso de no encontrar registros regresa null
	 * @throws DAOException
	 *             en caso de errores lanza una DAOException
	 */
	public Vector getComboModulos2(int iCveUniMed, int iCveUser)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVDinamico vCombo;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select M.iCveModulo,M.cDscModulo from GRLModulo AS M, GRLUSUMEDICOS AS U"
					+ "    where "
					+ "		M.ICVEUNIMED = U.ICVEUNIMED AND"
					+ "		M.ICVEMODULO = U.ICVEMODULO AND"
					+ "		M.iCveUniMed= ? AND"
					+ "		U.ICVEUSUARIO = ? "
					+ "		GROUP BY M.iCveModulo,M.cDscModulo"
					+ "	        order by M.cDscModulo";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, iCveUniMed);
			pstmt.setInt(2, iCveUser);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCombo = new TVDinamico();
				vCombo.put("cIndice",
						Integer.toString(rset.getInt("iCveModulo")));
				vCombo.put("cDescripcion", rset.getString("cDscModulo"));
				vcGRLModulo.addElement(vCombo);
			}
		} catch (Exception ex) {
			warn("getComboModulos", ex);
			throw new DAOException("getComboModulos");
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
				warn("getComboModulos.close", ex2);
			}
			return vcGRLModulo;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLModulo vGRLModulo;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "iCveUniMed,iCveModulo,cDscModulo,cCalle,cColonia,iCP,cCiudad,"
					+ "iCvePais,iCveEstado,iCveMunicipio,cTel,cCorreoe,linterconsulta "
					+ "from GRLModulo";
			String cCveUniMed = (String) hmFiltros.get("iCveUniMed");
			String cCveModulo = (String) hmFiltros.get("iCveModulo");
			if (cCveUniMed != null) {
				cSQL += " where iCveUniMed=?";
				cWhereAnd = " and";
			}
			if (cCveModulo != null) {
				cSQL += cWhereAnd + " iCveModulo=?";
			}
			cSQL += " order by iCveUniMed,iCveModulo";
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveUniMed != null)
				pstmt.setInt(i++, Integer.parseInt(cCveUniMed));
			if (cCveModulo != null)
				pstmt.setInt(i++, Integer.parseInt(cCveModulo));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLModulo = new TVGRLModulo();
				vGRLModulo.setICveUniMed(rset.getInt("iCveUniMed"));
				vGRLModulo.setICveModulo(rset.getInt("iCveModulo"));
				vGRLModulo.setCDscModulo(rset.getString("cDscModulo"));
				vGRLModulo.setCCalle(rset.getString("cCalle"));
				vGRLModulo.setCColonia(rset.getString("cColonia"));
				vGRLModulo.setICP(rset.getInt("iCP"));
				vGRLModulo.setCCiudad(rset.getString("cCiudad"));
				vGRLModulo.setICvePais(rset.getInt("iCvePais"));
				vGRLModulo.setICveEstado(rset.getInt("iCveEstado"));
				vGRLModulo.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vGRLModulo.setCTel(rset.getString("cTel"));
				vGRLModulo.setCCorreoe(rset.getString("cCorreoe"));
				vGRLModulo.setLinterconsulta(rset.getInt("linterconsulta"));
				vcGRLModulo.addElement(vGRLModulo);
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
			return vcGRLModulo;
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
			TVGRLModulo vGRLModulo = (TVGRLModulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLModulo" + " set lVigente= ? "
					+ "where iCveUniMed = ?" + "and iCveModulo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vGRLModulo.getICveUniMed());
			pstmt.setInt(3, vGRLModulo.getICveModulo());
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

	public Vector buscarInfoIngresos(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVGRLModulo vGRLModulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select U.iCveUddAdmiva, M.iCveOficina, ")
					.append("        M.cCalle || '<br>' || M.cColonia || ' C.P. ' || CHAR(M.iCP) || '<br>' || EM.cNombre || ', ' || EF.cNombre as cDireccion ")
					.append(" from GRLModulo M ")
					.append(" inner join GRLUniMed U on U.iCveUniMed = M.iCveUniMed ")
					.append(" inner join GRLEntidadFed  EF on EF.iCvePais = M.iCvePais ")
					.append("                             and EF.iCveEntidadFed = M.iCveEstado ")
					.append(" inner join GRLMunicipio EM on EM.iCvePais = M.iCvePais ")
					.append("                           and EM.iCveEntidadFed = M.iCveEstado ")
					.append("                           and EM.iCveMunicipio = M.iCveMunicipio ")
					.append(cWhere);

			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLModulo = new TVGRLModulo();
				vGRLModulo.setICveUddAdmiva(rset.getInt(1));
				vGRLModulo.setICveOficina(rset.getInt(2));
				vGRLModulo.setCCalle(rset.getString(3));
				vcGRLModulo.addElement(vGRLModulo);
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
			return vcGRLModulo;
		}
	}
	
	/**
	 * Método findHuellasValidas(regresa el true si tiene una toma de huellas posterior al 29 de abril del 2013)
	 * 
	 * @Autor: AG SA
	 */
	public boolean findNoValidaHuellasModulo(String cUnidad, String cModulo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean regreso = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "SELECT LVALIDA FROM GRLMODULO " +
						  "WHERE ICVEUNIMED = "+cUnidad+" AND ICVEMODULO = "+cModulo+
						  " and lValida < 2";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regreso = true;
			}
		} catch (Exception ex) {
			warn("RegresaInt", ex);
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
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}	
		
	

}
